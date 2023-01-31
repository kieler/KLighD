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
           RenameStateAction.KIND -> RenameStateAction,
           AddSuccessorStateAction.KIND -> AddSuccessorStateAction,
           AddHierarchicalStateAction.KIND -> AddHierarchicalStateAction,
           ChangeTargetStateAction.KIND -> ChangeTargetStateAction,
           ChangeSourceStateAction.KIND -> ChangeSourceStateAction,
           ChangeTriggerEffectAction.KIND -> ChangeTriggerEffectAction,
           RenameRegionAction.KIND -> RenameRegionAction,
           AddConcurrentRegionAction.KIND -> AddConcurrentRegionAction,
           ChangeToAbortingTransitionAction.KIND -> ChangeToAbortingTransitionAction,
           ChangeToTerminatingTransitionAction.KIND -> ChangeToTerminatingTransitionAction,
           ChangeToWeakTransitionAction.KIND -> ChangeToWeakTransitionAction,
           AddTransitionAction.KIND -> AddTransitionAction,
           ToggleFinalStateAction.KIND -> ToggleFinalStateAction
        )
    }
    
    override handle(Action action, String clientId, KGraphDiagramServer server) {
        val uri = diagramState.getURIString(clientId)
        
        extens.set_pre(clientId)
        
        
        if(action.kind == DeleteAction.KIND){
            extens.delete(action as DeleteAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == RenameStateAction.KIND){
            extens.rename(action, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == AddSuccessorStateAction.KIND){
            synchronized (server.modelLock) {
                extens.addSuccessorNode(action as AddSuccessorStateAction, clientId, server)
                extens.updateDocument(uri)
            }
        }else if(action.kind == AddHierarchicalStateAction.KIND){
            extens.addHirachicalNode(action as AddHierarchicalStateAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == ChangeTargetStateAction.KIND){
            extens.changeDestination(action as ChangeTargetStateAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == ChangeSourceStateAction.KIND){
            extens.changeSource(action as ChangeSourceStateAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == ChangeTriggerEffectAction.KIND){
            extens.changeIO(action as ChangeTriggerEffectAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == RenameRegionAction.KIND){
            extens.rename(action, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == AddConcurrentRegionAction.KIND){
            extens.addConcurrentRegion(action as AddConcurrentRegionAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == ChangeToAbortingTransitionAction.KIND){
            extens.changeToAbort(action as ChangeToAbortingTransitionAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == ChangeToTerminatingTransitionAction.KIND){
            extens.changeToTerminating(action as ChangeToTerminatingTransitionAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == ChangeToWeakTransitionAction.KIND){
            extens.changeToWeak(action as ChangeToWeakTransitionAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == AddTransitionAction.KIND){
            extens.addNewTransition(action as AddTransitionAction, clientId, server)
            extens.updateDocument(uri)
        }else if(action.kind == ToggleFinalStateAction.KIND){
            extens.toggleFinalState(action as ToggleFinalStateAction, clientId, server)
            extens.updateDocument(uri)
        }else{
            throw new IllegalArgumentException("Action " + action.kind + " not supported by handler " + this.class.simpleName)
        }
    }
    
}