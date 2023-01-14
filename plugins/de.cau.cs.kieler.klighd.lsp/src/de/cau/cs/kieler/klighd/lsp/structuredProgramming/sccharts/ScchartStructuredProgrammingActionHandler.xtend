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

@Singleton
class ScchartStructuredProgrammingActionHandler extends AbstractActionHandler {
    
    @Accessors KGraphLanguageClient client;

    @Inject
    KGraphDiagramState diagramState
    
    @Inject
    KGraphLanguageServerExtension languageServer
    
    List<KNode> changedNodes
    
    Position pre_range
    
    new() {
        this.supportedMessages = newHashMap(
           DeleteAction.KIND -> DeleteAction,
           RenameNodeAction.KIND -> RenameNodeAction,
           AddSuccessorNodeAction.KIND -> AddSuccessorNodeAction,
           AddHirachicalNodeAction.KIND -> AddHirachicalNodeAction,
           RenameEdgeAction.KIND -> RenameEdgeAction,
           ChangeDestinationAction.KIND -> ChangeDestinationAction,
           ChangeSourceAction.KIND -> ChangeSourceAction,
           ChangeIOAction.KIND -> ChangeIOAction,
           RenameRegionAction.KIND -> RenameRegionAction,
           AddConcurrentRegionAction.KIND -> AddConcurrentRegionAction
        )
    }
    
    override handle(Action action, String clientId, KGraphDiagramServer server) {
        if(action.kind == DeleteAction.KIND){
            val uri = diagramState.getURIString(clientId)
            val resource = languageServer.getResource(uri);
            val outputStream = new ByteArrayOutputStream
            resource.save(outputStream, emptyMap)
            val codeAfter = outputStream.toString().trim()
            println(codeAfter)
        
            // The range is the length of the previous file.
            pre_range = new Position(codeAfter.split("\r\n|\r|\n").length,0)
            
            this.delete(action as DeleteAction, clientId, server)
            this.updateDocument(uri)
        }else if(action.kind == RenameNodeAction.KIND){
            println("rename")
        }else if(action.kind == AddSuccessorNodeAction.KIND){
            println("succ")
        }else if(action.kind == AddHirachicalNodeAction.KIND){
            println("hirach")
        }else if(action.kind == RenameEdgeAction.KIND){
            println("renameEdge")
        }else if(action.kind == ChangeDestinationAction.KIND){
            println("changeDest")
        }else if(action.kind == ChangeSourceAction.KIND){
            println("changeSource")
        }else if(action.kind == ChangeIOAction.KIND){
            println("changeIO")
        }else if(action.kind == RenameRegionAction.KIND){
            println("renameRegion")
        }else if(action.kind == AddConcurrentRegionAction.KIND){
            println("addConcurrentRegion")
        }else{
            throw new IllegalArgumentException("Action " + action.kind + " not supported by handler " + this.class.simpleName)
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
            kEdge.source.outgoingEdges.remove(kEdge)
            kEdge.target.incomingEdges.remove(kEdge)
            deleteEdge(kEdge);
        } 
    }
    
    def deleteEdge(KEdge kNode){
        
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
    
    
    def updateDocument(String uri){
        val resource = languageServer.getResource(uri);
        
        

        val outputStream = new ByteArrayOutputStream
        resource.save(outputStream, emptyMap)
        val codeAfter = outputStream.toString().trim()
        println(codeAfter) //returns correct code for deletion !
        
        // The range is the length of the previous file.
        val Range range = new Range(new Position(0, 0), pre_range)
        if(this.client === null){
            println("client nulll")
        }else{
            this.client.replaceContentInFile(uri, codeAfter, range)        
        }
    }
}