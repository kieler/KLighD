package de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramServer
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageServerExtension
import org.eclipse.sprotty.Action
import javax.inject.Singleton
import de.cau.cs.kieler.klighd.lsp.AbstractActionHandler
import de.cau.cs.kieler.klighd.lsp.LSPUtil
import java.io.ByteArrayOutputStream
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import java.util.Map
import java.util.List
import org.eclipse.lsp4j.TextEdit
import org.eclipse.lsp4j.Range
import org.eclipse.lsp4j.Position
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageClient
import org.eclipse.xtend.lib.annotations.Accessors
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.sccharts.State
import de.cau.cs.kieler.sccharts.Transition
import de.cau.cs.kieler.sccharts.impl.StateImpl
import de.cau.cs.kieler.sccharts.impl.TransitionImpl
import de.cau.cs.kieler.sccharts.impl.SCChartsFactoryImpl
import org.eclipse.emf.common.util.EList
import de.cau.cs.kieler.sccharts.Region
import de.cau.cs.kieler.sccharts.impl.ControlflowRegionImpl
import de.cau.cs.kieler.sccharts.ControlflowRegion
import de.cau.cs.kieler.sccharts.PreemptionType

@Singleton
class ScchartStructuredProgrammingActionHandler extends AbstractActionHandler {
    SCChartsFactoryImpl factory
    
    @Accessors KGraphLanguageClient client

    @Inject
    KGraphDiagramState diagramState
    
    @Inject
    KGraphLanguageServerExtension languageServer
    
    List<KNode> changedNodes
    
    Position pre_range
    
    new() {
        factory = new SCChartsFactoryImpl()
        this.supportedMessages = newHashMap(
           DeleteAction.KIND -> DeleteAction,
           RenameNodeAction.KIND -> RenameNodeAction,
           AddSuccessorNodeAction.KIND -> AddSuccessorNodeAction,
           AddHirachicalNodeAction.KIND -> AddHirachicalNodeAction,
           ChangeDestinationAction.KIND -> ChangeDestinationAction,
           ChangeSourceAction.KIND -> ChangeSourceAction,
           ChangeIOAction.KIND -> ChangeIOAction,
           RenameRegionAction.KIND -> RenameRegionAction,
           AddConcurrentRegionAction.KIND -> AddConcurrentRegionAction,
           ChangeToAbortingEdgeAction.KIND -> ChangeToAbortingEdgeAction,
           ChangeToTerminationgEdgeAction.KIND -> ChangeToTerminationgEdgeAction,
           ChangeToWeakEdgeAction.KIND -> ChangeToWeakEdgeAction
        )
    }
    
    override handle(Action action, String clientId, KGraphDiagramServer server) {
        val uri = diagramState.getURIString(clientId)
        val resource = languageServer.getResource(uri);
        val outputStream = new ByteArrayOutputStream
        resource.save(outputStream, emptyMap)
        val codeAfter = outputStream.toString().trim()
        println(codeAfter)
        
        // The range is the length of the previous file.
        pre_range = new Position(codeAfter.split("\r\n|\r|\n").length,0)
        if(action.kind == DeleteAction.KIND){
            delete(action as DeleteAction, clientId, server)
            updateDocument(uri)
        }else if(action.kind == RenameNodeAction.KIND){
            rename(action, clientId, server)
            updateDocument(uri)
        }else if(action.kind == AddSuccessorNodeAction.KIND){
            addSuccessorNode(action as AddSuccessorNodeAction, clientId, server)
            updateDocument(uri)
        }else if(action.kind == AddHirachicalNodeAction.KIND){
            addHirachicalNode(action as AddHirachicalNodeAction, clientId, server)
            updateDocument(uri)
        }else if(action.kind == ChangeDestinationAction.KIND){
            changeDestination(action as ChangeDestinationAction, clientId, server)
            updateDocument(uri)
        }else if(action.kind == ChangeSourceAction.KIND){
            changeSource(action as ChangeSourceAction, clientId, server)
            updateDocument(uri)
        }else if(action.kind == ChangeIOAction.KIND){
            changeIO(action as ChangeIOAction, clientId, server)
            updateDocument(uri)
        }else if(action.kind == RenameRegionAction.KIND){
            rename(action, clientId, server)
            updateDocument(uri)
        }else if(action.kind == AddConcurrentRegionAction.KIND){
            addConcurrentRegion(action as AddConcurrentRegionAction, clientId, server)
            updateDocument(uri)
        }else if(action.kind == ChangeToAbortingEdgeAction.KIND){
            changeToAbort(action as ChangeToAbortingEdgeAction, clientId, server)
            updateDocument(uri)
        }else if(action.kind == ChangeToTerminationgEdgeAction.KIND){
            changeToTerminating(action as ChangeToTerminationgEdgeAction, clientId, server)
            updateDocument(uri)
        }else if(action.kind == ChangeToWeakEdgeAction.KIND){
            changeToWeak(action as ChangeToWeakEdgeAction, clientId, server)
            updateDocument(uri)
        }else{
            throw new IllegalArgumentException("Action " + action.kind + " not supported by handler " + this.class.simpleName)
        }
    }
    
    def changeToWeak(ChangeToWeakEdgeAction action, String clientId, KGraphDiagramServer server) {
        val uri = diagramState.getURIString(clientId)
        val kEdge = LSPUtil.getKEdge(diagramState, uri, action.id)
        val edge = kEdge.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as Transition
        
        edge.preemption = PreemptionType.WEAK
    }
    
    def changeToTerminating(ChangeToTerminationgEdgeAction action, String clientId, KGraphDiagramServer server) {
        val uri = diagramState.getURIString(clientId)
        val kEdge = LSPUtil.getKEdge(diagramState, uri, action.id)
        val edge = kEdge.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as Transition
        
        edge.preemption = PreemptionType.TERMINATION
    }
    
    def changeToAbort(ChangeToAbortingEdgeAction action, String clientId, KGraphDiagramServer server) {
        val uri = diagramState.getURIString(clientId)
        val kEdge = LSPUtil.getKEdge(diagramState, uri, action.id)
        val edge = kEdge.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as Transition
        
        edge.preemption = PreemptionType.STRONG
    }
    
    def changeIO(ChangeIOAction action, String clientId, KGraphDiagramServer server) {
        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, action.id)
        val edge = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as Transition
        //TODO: change IO
    }
    
    def changeDestination(ChangeDestinationAction action, String clientId, KGraphDiagramServer server) {
        val uri = diagramState.getURIString(clientId)
        val kEdge = LSPUtil.getKEdge(diagramState, uri, action.id)
        val edge = kEdge.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as Transition
        
        //TODO: idealy the user selects a node and the id is transmitted and we could get rid of the string stuff here
        val arr = action.id.split("\\$")
        
        var newTarget = ""
        for(var x = 0; x<arr.length-2; x++){
            newTarget += arr.get(x)+"$"
        }
        newTarget += "N"+action.new_dest
        
        //idealy instead of newSource use action.new_Source 
        val kNode = LSPUtil.getKNode(diagramState, uri, newTarget)
        val node =  kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State
        
        edge.targetState.incomingTransitions.remove(edge)
        edge.targetState = node
        
        node.incomingTransitions.add(edge)
    }
    
    def changeSource(ChangeSourceAction action, String clientId, KGraphDiagramServer server) {
        val uri = diagramState.getURIString(clientId)
        val kEdge = LSPUtil.getKEdge(diagramState, uri, action.id)
        val edge = kEdge.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as Transition
        
        
        //TODO: idealy the user selects a node and the id is transmitted and we could get rid of the string stuff here
        val arr = action.id.split("\\$")
        
        var newSource = ""
        for(var x = 0; x<arr.length-2; x++){
            newSource += arr.get(x)+"$"
        }
        newSource += "N"+action.new_Source
        
        //idealy instead of newSource use action.new_Source 
        val kNode = LSPUtil.getKNode(diagramState, uri, newSource)
        val node =  kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State
        
        edge.sourceState.outgoingTransitions.remove(edge)
        edge.sourceState = node
        
        node.outgoingTransitions.add(edge)
    }
    
    def addHirachicalNode(AddHirachicalNodeAction action, String clientId, KGraphDiagramServer server) {
        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, action.id)
        val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State

        val newRegion = factory.createControlflowRegion()
        newRegion.name = action.region_name
        
        val newState = factory.createState()
        newState.name = action.next_name
        newState.initial = true
      
        newRegion.states.add(newState)
        
        node.regions.add(newRegion)        
    }
    
    def addConcurrentRegion(AddConcurrentRegionAction action, String clientId, KGraphDiagramServer server) {
        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, action.id)
        val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
        
        val newRegion = factory.createControlflowRegion()
        newRegion.name = action.new_name
        
        val initState = factory.createState()
        initState.name = action.initialStateName
        initState.initial = true
        
        newRegion.states.add(initState)
        
        if(node instanceof ControlflowRegion){
            (node as ControlflowRegion).parentState.regions.add(newRegion)
        }
    }
    
    def rename(Action action, String clientId, KGraphDiagramServer server) {
        val uri = diagramState.getURIString(clientId)
        if(action.kind === RenameNodeAction.KIND){
            val kNode = LSPUtil.getKNode(diagramState, uri, (action as RenameNodeAction).id)
            val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
            (node as State).name = (action as RenameNodeAction).newName
        }else if(action.kind === RenameRegionAction.KIND){
            val kNode = LSPUtil.getKNode(diagramState, uri, (action as RenameRegionAction).id)
            val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
            (node as Region).name = (action as RenameRegionAction).newName
        }
        
    }
    
    def delete(DeleteAction action, String clientId, KGraphDiagramServer server){
        try{
            val nodes = action.id.split(":");
            for( x: nodes){
                this.deleteSingleElem(x, clientId, server)
            }
        }catch(NullPointerException ex){
            //single element was send
            this.deleteSingleElem(action.id, clientId, server)
        }
    }
    
    def deleteSingleElem(String id, String clientId, KGraphDiagramServer server){
        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, id)
        
        if(kNode !== null && kNode.parent !== null){
            deleteNode(kNode);
        }   
    
        val kEdge = LSPUtil.getKEdge(diagramState, uri, id)
        if( kEdge !== null ) {
            deleteEdge(kEdge);
        } 
    }
    
    def deleteEdge(KEdge kEdge){
        val edge = kEdge.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
        val source = kEdge.source.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State   
        val target = kEdge.target.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State
        
        source.outgoingTransitions.remove(edge)
        target.incomingTransitions.remove(edge)
    }
    
    def void deleteNode(KNode kNode){
        val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State
        
        
        for(incommingEdge: kNode.incomingEdges){
            
            val source = incommingEdge.source.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State
            
            val transitions = source.getOutgoingTransitions()
            val to_del = newArrayList
            
            for(transition: transitions){            
                if(transition.getTargetState() === node){
                    to_del.add(transition)
                }
            }  
            for(t: to_del){
                source.outgoingTransitions.remove(t)
            }
            
        }
    
        for(outgoingEdge: kNode.outgoingEdges){
            val target = outgoingEdge.target.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State
            
            val transitions = target.getIncomingTransitions()
            val to_del = newArrayList
            
            for(transition: transitions){            
                if(transition.getSourceState() === node){
                    to_del.add(transition)
                }   
            }
            for(t: to_del){
                target.incomingTransitions.remove(t)
            }
        }
        
        node.parentRegion.states.remove(node)
    }
    
    def addSuccessorNode(AddSuccessorNodeAction action, String clientId, KGraphDiagramServer server){
        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, action.id)
        val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State
        
        val new_State = factory.createState()
        new_State.name = action.newNodeName
        
        val new_transition = factory.createTransition()

        new_transition.sourceState = node
        new_transition.targetState = new_State
        
        // some way to do this
//        new_transition.trigger = action.edgeInput
//        new_transition.effects = action.edgeOutput
        
        new_State.incomingTransitions.add(new_transition)
        node.outgoingTransitions.add(new_transition)
        
        node.parentRegion.states.add(new_State)
        
    }
    
    def updateDocument(String uri){
        val resource = languageServer.getResource(uri);
        
        val outputStream = new ByteArrayOutputStream
        resource.save(outputStream, emptyMap)
        val codeAfter = outputStream.toString().trim()
        println(codeAfter) //returns correct code for deletion !
        
        // The range is the length of the previous file.
        val Range range = new Range(new Position(0, 0), pre_range)
        if(this.client === null){
            println("client null")
        }else{
            this.client.replaceContentInFile(uri, codeAfter, range)        
        }
    }
}