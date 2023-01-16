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

/**
 * @author felixj
 *
 */
@Singleton
class StructuredProgScchartLanguageServerExtension implements ILanguageServerExtension{
    Position pre_range
    
    @Inject
    SCChartsFactoryImpl factory
    
    @Accessors KGraphLanguageClient client;
    
    @Inject
    KGraphDiagramState diagramState
    
    @Inject
    KGraphLanguageServerExtension languageServer
    
    override initialize(ILanguageServerAccess access) {
        factory =  new SCChartsFactoryImpl()
    }
    
    def set_pre(String clientId){
        val uri = diagramState.getURIString(clientId)
        val resource = languageServer.getResource(uri);
        val outputStream = new ByteArrayOutputStream
        resource.save(outputStream, emptyMap)
        val codeAfter = outputStream.toString().trim()
        
        // The range is the length of the previous file.
        pre_range = new Position(codeAfter.split("\r\n|\r|\n").length,0)
    }
    
    def addNewEdge(AddEdgeAction action, String clientId, KGraphDiagramServer server) {
        val uri = diagramState.getURIString(clientId)
        val kNode = LSPUtil.getKNode(diagramState, uri, action.id)
        val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State
        
        //TODO: idealy the user selects a node and the id is transmitted and we could get rid of the string stuff here
        val arr = action.id.split("\\$")
        
        var newTarget = ""
        for(var x = 0; x<arr.length-1; x++){
            newTarget += arr.get(x)+"$"
        }
        newTarget += "N"+action.destination
        println(newTarget)
        
        //idealy instead of newSource use action.new_Source 
        val kDest = LSPUtil.getKNode(diagramState, uri, newTarget)
        val dest =  kDest.getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State
        
        val new_transition = factory.createTransition()

        new_transition.sourceState = node
        new_transition.targetState = dest
        
        dest.incomingTransitions.add(new_transition)
        
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
        val node = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
        
        if(node instanceof State){
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
        }else{
            (node as ControlflowRegion).parentState.regions.remove(node)
        }
        
        

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
        val Map<String, List<TextEdit>> changes = newHashMap
        
        val resource = languageServer.getResource(uri);
        
        val outputStream = new ByteArrayOutputStream
        resource.save(outputStream, emptyMap)
        val codeAfter = outputStream.toString().trim()
        
        // The range is the length of the previous file.
        val Range range = new Range(new Position(0, 0), pre_range)
        
        val TextEdit textEdit = new TextEdit(range, codeAfter)
        changes.put(uri, #[textEdit]);
            
        this.client.replaceContentInFile(uri, codeAfter, range) //doesn't do shit ? 
    }
}