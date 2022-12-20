package de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramServer
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageServerExtension
import org.eclipse.sprotty.Action
import javax.inject.Singleton
import de.cau.cs.kieler.klighd.lsp.AbstractActionHandler
import de.cau.cs.kieler.klighd.lsp.LSPUtil
import java.util.regex.PatternSyntaxException
import java.io.ByteArrayOutputStream
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import org.eclipse.elk.graph.ElkNode
import java.util.Map
import java.util.List
import org.eclipse.lsp4j.TextEdit
import org.eclipse.lsp4j.Range
import org.eclipse.lsp4j.Position
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageClient
import org.eclipse.xtend.lib.annotations.Accessors

@Singleton
class ScchartStructuredProgrammingActionHandler extends AbstractActionHandler {
    @Accessors KGraphLanguageClient client;

    @Inject
    KGraphDiagramState diagramState
    
    @Inject
    KGraphLanguageServerExtension languageServer
    
    List<KNode> changedNodes
    
    
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
            this.delete(action as DeleteAction, clientId, server)
            languageServer.updateLayout(uri)
//            this.updateDocument(clientId)
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
            for(y: kNode.incomingEdges){
                y.source.outgoingEdges.remove(y)
                changedNodes.add(y.source);
            }
        
            for(y: kNode.outgoingEdges){
                y.target.incomingEdges.remove(y)
                changedNodes.add(y.target);
            }
        
            kNode.parent.children.remove(kNode)
            
        }   
    
        val kEdge = LSPUtil.getKEdge(diagramState, uri, id)
        if( kEdge !== null ) {
            kEdge.source.outgoingEdges.remove(kEdge)
            kEdge.target.incomingEdges.remove(kEdge)
            changedNodes.add(kEdge.source);
            changedNodes.add(kEdge.target);
        } 
    }
    
    def updateDocument(String uri){
        val resource = languageServer.getResource(uri);
            
        // Get previous file content as String
        var outputStream = new ByteArrayOutputStream
        resource.save(outputStream, emptyMap)
        val codeBefore = outputStream.toString
        
        for (node : changedNodes) {
            val elkNode = node.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
            if (elkNode instanceof ElkNode) {
                copyChanges(elkNode, node)
            }
        }
        val elkNode = changedNodes.get(0).getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
        if (elkNode instanceof ElkNode) {
            val Map<String, List<TextEdit>> changes = newHashMap
            
            // Get changed file as String
            outputStream = new ByteArrayOutputStream
            resource.save(outputStream, emptyMap)
            val codeAfter = outputStream.toString().trim()
            
            // The range is the length of the previous file.
            val Range range = new Range(new Position(0, 0), new Position(codeBefore.split("\r\n|\r|\n").length, 0))
            val TextEdit textEdit = new TextEdit(range, codeAfter)
            changes.put(uri, #[textEdit]);
            this.client.replaceContentInFile(uri, codeAfter, range)
            return
        }
    }
    
    def copyChanges(ElkNode elkNode, KNode kNode){
        
    }
}