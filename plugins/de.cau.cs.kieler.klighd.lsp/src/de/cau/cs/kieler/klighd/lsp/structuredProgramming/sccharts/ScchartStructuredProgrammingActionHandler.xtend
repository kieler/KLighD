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
    
    @Inject
    StructuredProgScchartLanguageServerExtension extens
    @Inject
    KGraphDiagramState diagramState

    new() {
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
           ChangeToWeakEdgeAction.KIND -> ChangeToWeakEdgeAction,
           AddEdgeAction.KIND -> AddEdgeAction
        )
    }
    
    override handle(Action action, String clientId, KGraphDiagramServer server) {
        val uri = diagramState.getURIString(clientId)
        
        extens.set_pre(clientId)
        
        
        if(action.kind == DeleteAction.KIND){
            extens.delete(action as DeleteAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == RenameNodeAction.KIND){
            extens.rename(action, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == AddSuccessorNodeAction.KIND){
            synchronized (server.modelLock) {
                extens.addSuccessorNode(action as AddSuccessorNodeAction, clientId, server)
                extens.updateDocument(uri)
            }
        }else if(action.kind == AddHirachicalNodeAction.KIND){
            extens.addHirachicalNode(action as AddHirachicalNodeAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == ChangeDestinationAction.KIND){
            extens.changeDestination(action as ChangeDestinationAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == ChangeSourceAction.KIND){
            extens.changeSource(action as ChangeSourceAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == ChangeIOAction.KIND){
            extens.changeIO(action as ChangeIOAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == RenameRegionAction.KIND){
            extens.rename(action, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == AddConcurrentRegionAction.KIND){
            extens.addConcurrentRegion(action as AddConcurrentRegionAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == ChangeToAbortingEdgeAction.KIND){
            extens.changeToAbort(action as ChangeToAbortingEdgeAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == ChangeToTerminationgEdgeAction.KIND){
            extens.changeToTerminating(action as ChangeToTerminationgEdgeAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == ChangeToWeakEdgeAction.KIND){
            extens.changeToWeak(action as ChangeToWeakEdgeAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == AddEdgeAction.KIND){
            extens.addNewEdge(action as AddEdgeAction, clientId, server)
            extens.updateDocument(uri)
        }else{
            throw new IllegalArgumentException("Action " + action.kind + " not supported by handler " + this.class.simpleName)
        }
    }
}