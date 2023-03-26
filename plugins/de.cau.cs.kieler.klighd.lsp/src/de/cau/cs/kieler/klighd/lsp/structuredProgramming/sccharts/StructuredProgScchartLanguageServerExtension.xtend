/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramServer
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageClient
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageServerExtension
import de.cau.cs.kieler.klighd.lsp.LSPUtil
import de.cau.cs.kieler.sccharts.ControlflowRegion
import de.cau.cs.kieler.sccharts.PreemptionType
import de.cau.cs.kieler.sccharts.Region
import de.cau.cs.kieler.sccharts.State
import de.cau.cs.kieler.sccharts.Transition
import javax.inject.Singleton
import org.eclipse.sprotty.Action
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.ide.server.ILanguageServerAccess
import org.eclipse.xtext.ide.server.ILanguageServerExtension
import de.cau.cs.kieler.sccharts.impl.SCChartsFactoryImpl
import java.io.ByteArrayOutputStream
import org.eclipse.lsp4j.Range
import org.eclipse.lsp4j.Position
import java.util.List
import java.util.Map
import org.eclipse.lsp4j.TextEdit
import de.cau.cs.kieler.kexpressions.kext.KExtStandaloneParser
import de.cau.cs.kieler.kexpressions.keffects.impl.AssignmentImpl
import de.cau.cs.kieler.kexpressions.impl.ValuedObjectReferenceImpl
import de.cau.cs.kieler.kexpressions.impl.OperatorExpressionImpl
import de.cau.cs.kieler.klighd.lsp.ValuedObjectNotFoundException
import de.cau.cs.kieler.sccharts.extensions.SCChartsTransitionExtensions

/**
 * Extension responsible for adjusting a model based on the received action message in the actionhandler.
 * @author felixj
 */
@Singleton
class StructuredProgScchartLanguageServerExtension implements ILanguageServerExtension {
    // used to change transitions
    @Inject extension SCChartsTransitionExtensions

    // used to communicate with the frontend eg sending error messages
    @Accessors KGraphLanguageClient client;

    // used to create new states transitions and regions
    @Inject
    SCChartsFactoryImpl factory

    // used to get the client uri
    @Inject
    KGraphDiagramState diagramState

    // used to get the model from a given uri
    @Inject
    KGraphLanguageServerExtension languageServer

    // used to store the old file size since updating text content replaces old content for now.
    Position pre_range

    override initialize(ILanguageServerAccess access) {
        factory = new SCChartsFactoryImpl()
    }

    /*simply sets the range to be the files size. */
    def set_pre(String clientId) {
        val uri = diagramState.getURIString(clientId)
        val resource = languageServer.getResource(uri);
        val outputStream = new ByteArrayOutputStream
        resource.save(outputStream, emptyMap)
        val codeBefor = outputStream.toString().trim()
        val lines = codeBefor.split("\r\n|\r|\n")
        // The range is the length of the previous file.
        val last_line = lines.get(lines.length - 1)
        pre_range = new Position(lines.length, last_line.length)
    }

    /* Called to add a new transition to a state. */
    def addNewTransition(AddTransitionAction action, String clientId, KGraphDiagramServer server) {
        set_pre(clientId)

        // get the node corresponding to the selected state on the client. Since the contextmenu was opened for a specific 
        // state we know the id exists and is a state so we can omit a try catch block
        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, action.id)
        val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State

        val new_transition = factory.createTransition()

        // Since trigger and effect are given as strings there may be variables that are not inputs / outputs
        // also we only allow assignments in effects.  
        try {
            changeTrigger(new_transition, action.trigger, uri)
            changeEffect(new_transition, action.effect, uri)
        } catch (ValuedObjectNotFoundException ex) {
            client.sendMessage("During the parsing of the expression " + action.trigger + " the object: " + ex.message +
                " could not be found.", "error")
            return
        } catch (ExpressionParseException ex) {
            client.sendMessage(
                "During the parsing of the expression " + action.effect + " the expression: " + ex.message +
                    " could not be converted to an assignment expression.", "error")
            return
        } catch (NullPointerException ex) {
            client.sendMessage("The expression could not be parsed.", "error")
            return
        }

        // Since the destination was selected it may be a region or a state outside the parent region of the selected node
        try {
            val kDest = LSPUtil.getKNode(diagramState, uri, action.destination)
            val dest = kDest.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State
            // need to check if the destination is in the same region as the source
            if (dest.parentRegion !== node.parentRegion) {
                client.sendMessage("The selected state is not part of the same region.", "error")
                return
            }
            new_transition.sourceState = node
            new_transition.targetState = dest
        } catch (ClassCastException ex) {
            // Catching the moment when not a state is selected as destination
            client.sendMessage("The selected element is not a targetable state.", "error")
            return
        }

        updateDocument(uri)
    }

    /* changes a given transition to a weak transition */
    def changeToWeak(ChangeToWeakTransitionAction action, String clientId, KGraphDiagramServer server) {
        set_pre(clientId)

        val uri = diagramState.getURIString(clientId)
        val kEdge = LSPUtil.getKEdge(diagramState, uri, action.id)
        val edge = kEdge.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as Transition

        edge.preemption = PreemptionType.WEAK

        updateDocument(uri)
    }

    /*changes a given transition to a terminating transition */
    def changeToTerminating(ChangeToTerminatingTransitionAction action, String clientId, KGraphDiagramServer server) {
        set_pre(clientId)

        val uri = diagramState.getURIString(clientId)
        val kEdge = LSPUtil.getKEdge(diagramState, uri, action.id)
        val edge = kEdge.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as Transition

        edge.preemption = PreemptionType.TERMINATION

        updateDocument(uri)
    }

    /*changes a given transition to a aborting transition */
    def changeToAbort(ChangeToAbortingTransitionAction action, String clientId, KGraphDiagramServer server) {
        set_pre(clientId)

        val uri = diagramState.getURIString(clientId)
        val kEdge = LSPUtil.getKEdge(diagramState, uri, action.id)
        val edge = kEdge.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as Transition

        edge.preemption = PreemptionType.STRONG

        updateDocument(uri)
    }

    /*changes the trigger and effect of a transition */
    def changeIO(ChangeTriggerEffectAction action, String clientId, KGraphDiagramServer server) {
        set_pre(clientId)

        val uri = diagramState.getURIString(clientId)
        val kEdge = LSPUtil.getKEdge(diagramState, uri, action.id)
        var edge = kEdge.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as Transition

        // currently as workaround we delete the edge and add it again since adjusting trigger and effect directly
        // introduces some weird spacing in the updateDocument step
        val prio = edge.priority
        val target = edge.targetState
        val source = edge.sourceState

        val newedge = createTransitionTo(source, target)

        // Since trigger and effect are given as strings there may be variables that are not inputs / outputs
        // also we only allow assignments in effects.  
        try {
            changeTrigger(newedge, action.trigger, uri)
            changeEffect(newedge, action.effect, uri)
            setSpecificPriority(newedge, prio)
        } catch (ValuedObjectNotFoundException ex) {
            client.sendMessage("During the parsing of the expression " + action.trigger + " the object: " + ex.message +
                " could not be found.", "error")
            return
        } catch (ExpressionParseException ex) {
            client.sendMessage(
                "During the parsing of the expression " + action.effect + " the expression: " + ex.message +
                    " could not be converted to an assignment expression.", "error")
            return
        } catch (NullPointerException ex) {
            client.sendMessage("The expression could not be parsed.", "error")
            return
        }
        deleteEdge(kEdge)

        updateDocument(uri)
    }

    /*method to change the destination of a given transition */
    def changeDestination(ChangeTargetStateAction action, String clientId, KGraphDiagramServer server) {
        set_pre(clientId)

        val uri = diagramState.getURIString(clientId)
        val kEdge = LSPUtil.getKEdge(diagramState, uri, action.id)
        val edge = kEdge.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as Transition

        // since the destination is selected by the user it may be inside another region or be no state atall        
        try {
            val kNode = LSPUtil.getKNode(diagramState, uri, action.new_target)
            val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State
            // checking if the selected state is inside the same region as the source
            if (node.parentRegion !== edge.targetState.parentRegion) {
                client.sendMessage("The selected state is not part of the same region.", "error")
                return
            }

            edge.targetState.incomingTransitions.remove(edge)
            edge.targetState = node

            node.incomingTransitions.add(edge)
        } catch (ClassCastException ex) {
            // catching the cases where the selected element is not a state
            client.sendMessage("The selected element is not a targetable state.", "error")
            return
        }

        updateDocument(uri)
    }

    /*Method to change the source of a given transition */
    def changeSource(ChangeSourceStateAction action, String clientId, KGraphDiagramServer server) {
        set_pre(clientId)

        val uri = diagramState.getURIString(clientId)
        val kEdge = LSPUtil.getKEdge(diagramState, uri, action.id)
        val edge = kEdge.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as Transition

        // we need to check if the selected element is a state and if the selected element is inside the same region
        try {
            val kNode = LSPUtil.getKNode(diagramState, uri, action.new_source)
            val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State
            // checking the the target and source are in the same region
            if (node.parentRegion !== edge.targetState.parentRegion) {
                client.sendMessage("The selected state is not part of the same region.", "error")
                return
            }

            edge.sourceState.outgoingTransitions.remove(edge)
            edge.sourceState = node

            node.outgoingTransitions.add(edge)
        } catch (ClassCastException ex) {
            // catching all cases where not a state was selected.
            client.sendMessage("The selected element is not a targetable state.", "error")
            return
        }

        updateDocument(uri)
    }

    /*Method to add hirarchical behavior to a node */
    def addHirachicalNode(AddHierarchicalStateAction action, String clientId, KGraphDiagramServer server) {
        set_pre(clientId)
        // we need to make sure that the new states name is given
        if (action.state_name.equals("")) {
            this.client.sendMessage("You must provide a state id.", "error")
            return
        }
        val newState = factory.createState()
        val state_id = getId(action.state_name)
        // we need to make sure the new name obeys the rules for state names.
        if (state_id.equals("_") || state_id.equals("")) {
            this.client.sendMessage("The state id needs to have atleast one number or letter in it.", "error")
            return
        }
        // we want to display the desired name as label in the graph
        if (!state_id.equals(action.state_name)) {
            newState.label = action.state_name
        }
        newState.name = state_id
        newState.initial = true

        // Regions follow a laxer definition for id's there may be regions that have no name etc.
        val newRegion = factory.createControlflowRegion()
        if (!action.region_name.equals("")) {
            val region_id = getId(action.region_name)

            if (!region_id.equals(action.region_name))
                newRegion.label = action.region_name

            newRegion.name = region_id
        }

        newRegion.states.add(newState)

        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, action.id)
        val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State

        node.regions.add(newRegion)

        updateDocument(uri)
    }

    /*Method to add a concurrent behavior. */
    def addConcurrentRegion(AddConcurrentRegionAction action, String clientId, KGraphDiagramServer server) {
        set_pre(clientId)

        // To make sure there is a name given for the state.
        if (action.state_name.equals("")) {
            this.client.sendMessage("You must provide a state id.", "error")
            return
        }
        val newState = factory.createState()
        val state_id = getId(action.state_name)
        // we need to make sure the new name obeys the rules for state names.
        if (state_id.equals("_") || state_id.equals("")) {
            this.client.sendMessage("The state id needs to have atleast one number or letter in it.", "error")
            return
        }
        // we want to display the desired name as label in the graph
        if (!state_id.equals(action.state_name)) {
            newState.label = action.state_name
        }
        newState.name = state_id
        newState.initial = true

        // Regions follow a laxer definition for id's there may be regions that have no name etc.
        val newRegion = factory.createControlflowRegion()
        if (!action.region_name.equals("")) {
            val region_id = getId(action.region_name)

            if (!region_id.equals(action.region_name))
                newRegion.label = action.region_name

            if (!(region_id.equals("") || region_id.equals("_")))
                newRegion.name = region_id
        }

        newRegion.states.add(newState)

        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, action.id)
        val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)

        if (node instanceof ControlflowRegion) {
            (node as ControlflowRegion).parentState.regions.add(newRegion)
        }

        updateDocument(uri)
    }

    /*Method to rename states and regions */
    def rename(Action action, String clientId, KGraphDiagramServer server) {
        set_pre(clientId)

        val uri = diagramState.getURIString(clientId)
        // We need to differentiate between states and regions
        if (action.kind === RenameStateAction.KIND) {
            val kNode = LSPUtil.getKNode(diagramState, uri, (action as RenameStateAction).id)
            val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
            // we need to make sure the new name obeys the rules for state ids
            if ((action as RenameStateAction).state_name.equals("")) {
                this.client.sendMessage("You must provide a state id.", "error")
                return
            }
            val state_id = getId((action as RenameStateAction).state_name)
            if (state_id.equals("_") || state_id.equals("")) {
                this.client.sendMessage("The state id needs to have atleast one number or letter in it.", "error")
                return
            }
            if (!state_id.equals((action as RenameStateAction).state_name)) {
                (node as State).label = (action as RenameStateAction).state_name
            }
            (node as State).name = state_id

        } else if (action.kind === RenameRegionAction.KIND) {
            val kNode = LSPUtil.getKNode(diagramState, uri, (action as RenameRegionAction).id)
            val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
            // we need to make sure the new name obeys the rules for region names.
            if((action as RenameRegionAction).region_name.equals("")) return

            val region_id = getId((action as RenameRegionAction).region_name)

            if (!region_id.equals((action as RenameRegionAction).region_name)) {
                (node as Region).label = (action as RenameRegionAction).region_name
            }

            (node as Region).name = region_id

        }

        updateDocument(uri)
    }

    /*Method to delete elements ie. Regions, States and Transitions */
    def delete(DeleteAction action, String clientId, KGraphDiagramServer server) {
        set_pre(clientId)

        try {
            // for multiple slected elements
            val nodes = action.id.split(":");
            for (x : nodes) {
                this.deleteSingleElem(x, clientId, server)
            }
        } catch (NullPointerException ex) {
            // single element was send and should be deleted
            this.deleteSingleElem(action.id, clientId, server)
        }

        val uri = diagramState.getURIString(clientId)

        updateDocument(uri)
    }

    /*Method to add a successor state to a given state */
    def addSuccessorState(AddSuccessorStateAction action, String clientId, KGraphDiagramServer server) {
        set_pre(clientId)

        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, action.id)
        val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State
        // we need to make sure a name for the new state is given
        if (action.state_name.equals("")) {
            this.client.sendMessage("You must provide a state id.", "error")
            return
        }
        val newState = factory.createState()
        val state_id = getId(action.state_name)
        // we need to make sure the new name obeys the rules for state id's
        if (state_id.equals("_") || state_id.equals("")) {
            this.client.sendMessage("The state id needs to have atleast one number or letter in it.", "error")
            return
        }
        // we want to display the given name in the graph and have the id only as id
        if (!state_id.equals(action.state_name)) {
            newState.label = action.state_name
        }
        newState.name = state_id

        val new_transition = factory.createTransition()

        // the trigger and effect are given as strings and can therefore can be false.
        // The Variables may not be initialised or the effect may not be an expression that assigns somthing.
        try {
            changeTrigger(new_transition, action.trigger, uri)
            changeEffect(new_transition, action.effect, uri)
        } catch (ValuedObjectNotFoundException ex) {
            client.sendMessage("During the parsing of the expression " + action.trigger + " the object: " + ex.message +
                " could not be found.", "error")
            return
        } catch (ExpressionParseException ex) {
            client.sendMessage(
                "During the parsing of the expression " + action.effect + " the expression: " + ex.message +
                    " could not be converted to an assignment expression.", "error")
            return
        } catch (NullPointerException ex) {
            client.sendMessage("The expression could not be parsed.", "error")
            return
        }

        new_transition.sourceState = node
        new_transition.targetState = newState

        node.parentRegion.states.add(newState)

        updateDocument(uri)
    }

    /*Method to toggle a state to be final or not */
    def toggleFinalState(ToggleFinalStateAction action, String clientId, KGraphDiagramServer server) {
        set_pre(clientId)

        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, action.id)
        val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State

        node.final = !node.final

        updateDocument(uri)
    }

    /*Send from client to server to indicate that the focused tab should be different. */
    def editSemanticDeclaration(EditSemanticDeclarationAction action, String clientId,
        de.cau.cs.kieler.klighd.lsp.KGraphDiagramServer server) {
        val uri = diagramState.getURIString(clientId)
        this.client.sendMessage(uri, "switchEditor")
    }

    /*Makes the desired state initial and changes the old initial state to be normal. */
    def makeInitialState(MakeInitialStateAction action, String clientId,
        de.cau.cs.kieler.klighd.lsp.KGraphDiagramServer server) {
        set_pre(clientId)

        // since the action is triggered with the contextmenu for states the action id is the id of a state and thus we can omit the try catch's
        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, action.id)
        val new_init = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State

        // changes the old initial state to be normal
        for (node : new_init.parentRegion.states) {
            if(node.initial) node.initial = false
        }

        new_init.initial = true

        updateDocument(uri)
    }

    /*Method to change a transitions priority. The edge moves up/down the hirachy and the others are moved up or down by one */
    def changeEdgePriority(ChangePriorityAction action, String clientId, KGraphDiagramServer server) {
        set_pre(clientId)
        val uri = diagramState.getURIString(clientId)
        val kEdge = LSPUtil.getKEdge(diagramState, uri, action.id)
        val edge = kEdge.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
        val i = Integer.parseInt(action.priority)
        setSpecificPriority(edge as Transition, i)
        updateDocument(uri)
    }

    /*Helper method to delete a single element */
    def deleteSingleElem(String id, String clientId, KGraphDiagramServer server) {
        val uri = diagramState.getURIString(clientId)
        // kNode may be states or regions.
        val kNode = LSPUtil.getKNode(diagramState, uri, id)

        if (kNode !== null && kNode.parent !== null) {
            deleteNode(kNode);
        }
        // edges are transitions
        val kEdge = LSPUtil.getKEdge(diagramState, uri, id)
        if (kEdge !== null) {
            deleteEdge(kEdge);
        }
    }

    /*Helper method to delete edges by removing the edge from source and target */
    def deleteEdge(KEdge kEdge) {
        val edge = kEdge.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
        val source = kEdge.source.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State
        val target = kEdge.target.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State

        source.outgoingTransitions.remove(edge)
        target.incomingTransitions.remove(edge)
    }

    /*Helper method to delete nodes or regions */
    def void deleteNode(KNode kNode) {
        val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)

        if (node instanceof State) {
            // if we delete the last state in a region we instead want to delete the region itself
            if (node.parentRegion.states.length === 1) {
                node.parentRegion.parentState.regions.remove(node.parentRegion)
                return
            }
            // for all incomming edges we need to delete edge and remove it from the source
            for (incommingEdge : kNode.incomingEdges) {

                val source = incommingEdge.source.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State

                val transitions = source.getOutgoingTransitions()
                val to_del = newArrayList

                for (transition : transitions) {
                    if (transition.getTargetState() === node) {
                        to_del.add(transition)
                    }
                }
                for (t : to_del) {
                    source.outgoingTransitions.remove(t)
                }

            }
            // for all outgoing edges we need to delete the edge and remove it from the target
            for (outgoingEdge : kNode.outgoingEdges) {
                val target = outgoingEdge.target.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State

                val transitions = target.getIncomingTransitions()
                val to_del = newArrayList

                for (transition : transitions) {
                    if (transition.getSourceState() === node) {
                        to_del.add(transition)
                    }
                }
                for (t : to_del) {
                    target.incomingTransitions.remove(t)
                }
            }
            // if the node is initial we want to make any other node initial
            if (node.initial) {
                if (node.parentRegion.states.get(0) !== node) {
                    node.parentRegion.states.get(0).initial = true
                } else {
                    node.parentRegion.states.get(1).initial = true
                }

            }
            // finaly we want to remove the node from the region. 
            node.parentRegion.states.remove(node)
        } else {
            // in case of regions we want to delete them from the parent state
            (node as ControlflowRegion).parentState.regions.remove(node)
        }

    }

    /*Helper method to change the trigger of an transition. Similar to change effect*/
    def changeTrigger(Transition transition, String trigger, String uri) {
        if (trigger != "") {
            // may yield nothing and thus throw a nullpointer exception (Handeled when the mehtod is used)
            val new_trigger = KExtStandaloneParser.parseExpression(trigger)

            // The expression may consist of multiple operators
            if ((new_trigger instanceof OperatorExpressionImpl)) {
                _changeTriggerSubExpressions(transition, new_trigger, uri)

            } else if (new_trigger instanceof ValuedObjectReferenceImpl) {
                // If there is a valued objectreverence we need to change the reference to the one in the model
                (new_trigger as ValuedObjectReferenceImpl).valuedObject = LSPUtil.getValuedObjectReference(diagramState,
                    uri, (new_trigger as ValuedObjectReferenceImpl).valuedObject.name)
            }

            transition.trigger = new_trigger
        }
    }

    /*Helper method to change the valued object references recursively for all valued objects */
    def void _changeTriggerSubExpressions(Transition transition, OperatorExpressionImpl trigger, String uri) {
        for (exp : trigger.subExpressions) {
            // If we have an operator we want to change the subexcpressions
            if (exp instanceof OperatorExpressionImpl) {
                _changeTriggerSubExpressions(transition, exp, uri)

            } else if (exp instanceof ValuedObjectReferenceImpl) {
                // if we have a object reference we want to change it to the one in the model
                exp.valuedObject = LSPUtil.getValuedObjectReference(diagramState, uri, exp.valuedObject.name)
            }
        }
    }

    /*Helper function to change the effect of a transition. Similar to change Trigger*/
    def changeEffect(Transition new_transition, String total_effect_String, String uri) {
        // we want to delete any old effects
        new_transition.effects.removeAll(new_transition.effects)

        if (total_effect_String != "") {
            // since there may be multiple assignments in a effect we need to split those up before parsing.
            val effects_String = total_effect_String.split(";")
            for (effect_string : effects_String) {
                try {
                    // may be null if no expression could be generated   
                    val effect = KExtStandaloneParser.parseEffect(effect_string);
                    // need to update the valued object since the parser generates dummys
                    // cast can throw a class cast exception since the user input may be no assingment
                    (effect as AssignmentImpl).reference.valuedObject = LSPUtil.
                        getValuedObjectReference(diagramState, uri,
                            (effect as AssignmentImpl).reference.valuedObject.name)
                    new_transition.effects.add(effect)
                } catch (ClassCastException ex) {
                    // simply rethrown for readability in the catch cases outside of method
                    throw new ExpressionParseException(effect_string)
                }

            }
        }
    }

    /*updates the textual representation on the client.
     * for now everything is replaced.*/
    def updateDocument(String uri) {
        val Map<String, List<TextEdit>> changes = newHashMap

        val resource = languageServer.getResource(uri);

        val outputStream = new ByteArrayOutputStream
        resource.save(outputStream, emptyMap)
        val codeAfter = outputStream.toString().trim()

        // The range is the length of the previous file.
        val Range range = new Range(new Position(0, 0), pre_range)

        val TextEdit textEdit = new TextEdit(range, codeAfter)
        changes.put(uri, #[textEdit]);

        this.client.replaceContentInFile(uri, codeAfter, range)
    }

    /*removes all charactes that can not be in an id from a string and returns the resulting string */
    def getId(String name) {
        return name.replaceAll("[^a-zA-Z0-9_]", "")
    }
}

/**
 * Simple exception for readability when error arises during the parsing of a expression
 * @author fjo  
 */
class ExpressionParseException extends Exception {

    new(String expression) {
        super(expression)
    }

}
