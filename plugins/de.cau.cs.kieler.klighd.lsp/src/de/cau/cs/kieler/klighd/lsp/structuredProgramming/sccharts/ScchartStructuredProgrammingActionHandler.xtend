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

/**
 * Action handler for scchart structured changes.
 * basically forwards all incomming actions to the corresponding methods in the language server extension
 * @author fjo
 */
@Singleton
class ScchartStructuredProgrammingActionHandler extends AbstractActionHandler {

    @Inject
    StructuredProgScchartLanguageServerExtension extens

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
            ToggleFinalStateAction.KIND -> ToggleFinalStateAction,
            MakeInitialStateAction.KIND -> MakeInitialStateAction,
            EditSemanticDeclarationAction.KIND -> EditSemanticDeclarationAction,
            ChangePriorityAction.KIND -> ChangePriorityAction
        )
    }

    override handle(Action action, String clientId, KGraphDiagramServer server) {
        if (action.kind == DeleteAction.KIND) {
            synchronized (server.modelLock) {
                extens.delete(action as DeleteAction, clientId, server)
            }
        } else if (action.kind == RenameStateAction.KIND) {
            synchronized (server.modelLock) {
                extens.rename(action, clientId, server)
            }
        } else if (action.kind == AddSuccessorStateAction.KIND) {
            synchronized (server.modelLock) {
                extens.addSuccessorState(action as AddSuccessorStateAction, clientId, server)
            }
        } else if (action.kind == AddHierarchicalStateAction.KIND) {
            synchronized (server.modelLock) {
                extens.addHirachicalNode(action as AddHierarchicalStateAction, clientId, server)
            }
        } else if (action.kind == ChangeTargetStateAction.KIND) {
            synchronized (server.modelLock) {
                extens.changeDestination(action as ChangeTargetStateAction, clientId, server)
            }
        } else if (action.kind == ChangeSourceStateAction.KIND) {
            synchronized (server.modelLock) {
                extens.changeSource(action as ChangeSourceStateAction, clientId, server)
            }
        } else if (action.kind == ChangeTriggerEffectAction.KIND) {
            synchronized (server.modelLock) {
                extens.changeIO(action as ChangeTriggerEffectAction, clientId, server)
            }
        } else if (action.kind == RenameRegionAction.KIND) {
            synchronized (server.modelLock) {
                extens.rename(action, clientId, server)
            }
        } else if (action.kind == AddConcurrentRegionAction.KIND) {
            synchronized (server.modelLock) {
                extens.addConcurrentRegion(action as AddConcurrentRegionAction, clientId, server)
            }
        } else if (action.kind == ChangeToAbortingTransitionAction.KIND) {
            synchronized (server.modelLock) {
                extens.changeToAbort(action as ChangeToAbortingTransitionAction, clientId, server)
            }
        } else if (action.kind == ChangeToTerminatingTransitionAction.KIND) {
            synchronized (server.modelLock) {
                extens.changeToTerminating(action as ChangeToTerminatingTransitionAction, clientId, server)
            }
        } else if (action.kind == ChangeToWeakTransitionAction.KIND) {
            synchronized (server.modelLock) {
                extens.changeToWeak(action as ChangeToWeakTransitionAction, clientId, server)
            }
        } else if (action.kind == AddTransitionAction.KIND) {
            synchronized (server.modelLock) {
                extens.addNewTransition(action as AddTransitionAction, clientId, server)
            }
        } else if (action.kind == ToggleFinalStateAction.KIND) {
            synchronized (server.modelLock) {
                extens.toggleFinalState(action as ToggleFinalStateAction, clientId, server)
            }
        } else if (action.kind == MakeInitialStateAction.KIND) {
            synchronized (server.modelLock) {
                extens.makeInitialState(action as MakeInitialStateAction, clientId, server)
            }
        } else if (action.kind == EditSemanticDeclarationAction.KIND) {
            synchronized (server.modelLock) {
                extens.editSemanticDeclaration(action as EditSemanticDeclarationAction, clientId, server)
            }
        } else if (action.kind == ChangePriorityAction.KIND) {
            synchronized (server.modelLock) {
                extens.changeEdgePriority(action as ChangePriorityAction, clientId, server)
            }
        } else {
            throw new IllegalArgumentException("Action " + action.kind + " not supported by handler " +
                this.class.simpleName)
        }
    }

}
