/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018-2021 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.lsp.gson_utils

import com.google.gson.GsonBuilder
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.lsp.interactive.layered.DeleteLayerConstraintAction
import de.cau.cs.kieler.klighd.lsp.interactive.layered.DeletePositionConstraintAction
import de.cau.cs.kieler.klighd.lsp.interactive.layered.DeleteStaticConstraintAction
import de.cau.cs.kieler.klighd.lsp.interactive.layered.SetLayerConstraintAction
import de.cau.cs.kieler.klighd.lsp.interactive.layered.SetPositionConstraintAction
import de.cau.cs.kieler.klighd.lsp.interactive.layered.SetStaticConstraintAction
import de.cau.cs.kieler.klighd.lsp.interactive.rectpacking.RectpackingDeletePositionConstraintAction
import de.cau.cs.kieler.klighd.lsp.interactive.rectpacking.RectpackingSetPositionConstraintAction
import de.cau.cs.kieler.klighd.lsp.interactive.rectpacking.SetAspectRatioAction
import de.cau.cs.kieler.klighd.lsp.model.CheckedImagesAction
import de.cau.cs.kieler.klighd.lsp.model.PerformActionAction
import de.cau.cs.kieler.klighd.lsp.model.RefreshDiagramAction
import de.cau.cs.kieler.klighd.lsp.model.RefreshLayoutAction
import de.cau.cs.kieler.klighd.lsp.model.RequestDiagramPieceAction
import de.cau.cs.kieler.klighd.lsp.model.SetSynthesisAction
import java.awt.geom.Point2D
import org.eclipse.emf.ecore.EObject
import org.eclipse.sprotty.server.json.ActionTypeAdapter

import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.DeleteAction
import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.RenameStateAction;
import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.AddHierarchicalStateAction;
import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.AddSuccessorStateAction;
import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.ChangeTriggerEffectAction;
import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.RenameRegionAction;
import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.AddConcurrentRegionAction;
import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.ChangeTargetStateAction
import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.ChangeSourceStateAction
import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.ChangeToAbortingTransitionAction
import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.ChangeToTerminatingTransitionAction
import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.ChangeToWeakTransitionAction
import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.AddTransitionAction
import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.ToggleFinalStateAction
import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.EditSemanticDeclarationAction
import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.MakeInitialStateAction
import de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts.ChangePriorityAction

/**
 * Static util class to configure needed gson type adapters for KGraph serialization.
 * 
 * @author nre
 */
class KGraphTypeAdapterUtil {
    def static GsonBuilder configureGson(GsonBuilder gsonBuilder) {
        gsonBuilder
        .registerTypeAdapterFactory(
            new ActionTypeAdapter.Factory => [
                // General Sprotty actions
                addActionKind(CheckedImagesAction.KIND, CheckedImagesAction)
                addActionKind(PerformActionAction.KIND, PerformActionAction)
                addActionKind(SetSynthesisAction.KIND, SetSynthesisAction)
                addActionKind(RefreshDiagramAction.KIND, RefreshDiagramAction)
                addActionKind(RefreshLayoutAction.KIND, RefreshLayoutAction)
                
                // Interactive layered actions
                addActionKind(SetStaticConstraintAction.KIND, SetStaticConstraintAction)
                addActionKind(SetPositionConstraintAction.KIND, SetPositionConstraintAction)
                addActionKind(SetLayerConstraintAction.KIND, SetLayerConstraintAction)
                addActionKind(DeleteStaticConstraintAction.KIND, DeleteStaticConstraintAction)
                addActionKind(DeletePositionConstraintAction.KIND, DeletePositionConstraintAction)
                addActionKind(DeleteLayerConstraintAction.KIND, DeleteLayerConstraintAction)
                
                // Interactive rectpacking actions
                addActionKind(RectpackingSetPositionConstraintAction.KIND, RectpackingSetPositionConstraintAction)
                addActionKind(RectpackingDeletePositionConstraintAction.KIND, RectpackingDeletePositionConstraintAction)
                addActionKind(SetAspectRatioAction.KIND, SetAspectRatioAction)
                
                // Incremental topdown actions
                addActionKind(RequestDiagramPieceAction.KIND, RequestDiagramPieceAction)
                
                // Structured Programming actions
                addActionKind(DeleteAction.KIND, DeleteAction)
                
                addActionKind(RenameStateAction.KIND, RenameStateAction)
                addActionKind(AddSuccessorStateAction.KIND, AddSuccessorStateAction)
                addActionKind(AddHierarchicalStateAction.KIND, AddHierarchicalStateAction)
                addActionKind(AddTransitionAction.KIND, AddTransitionAction)
                addActionKind(ToggleFinalStateAction.KIND, ToggleFinalStateAction)
                addActionKind(MakeInitialStateAction.KIND, MakeInitialStateAction)
                
                addActionKind(ChangeTargetStateAction.KIND, ChangeTargetStateAction)
                addActionKind(ChangeSourceStateAction.KIND, ChangeSourceStateAction)
                addActionKind(ChangeTriggerEffectAction.KIND, ChangeTriggerEffectAction)
                addActionKind(ChangeToAbortingTransitionAction.KIND, ChangeToAbortingTransitionAction)
                addActionKind(ChangeToTerminatingTransitionAction.KIND, ChangeToTerminatingTransitionAction)
                addActionKind(ChangeToWeakTransitionAction.KIND, ChangeToWeakTransitionAction)
                addActionKind(ChangePriorityAction.KIND, ChangePriorityAction)
                
                addActionKind(RenameRegionAction.KIND, RenameRegionAction)
                addActionKind(AddConcurrentRegionAction.KIND, AddConcurrentRegionAction)
                
                addActionKind(EditSemanticDeclarationAction.KIND, EditSemanticDeclarationAction)
            ]
        )
        .registerTypeAdapter(Point2D, new Point2DTypeAdapter)
        .registerTypeHierarchyAdapter(EObject, new EObjectSerializer)
        .registerTypeAdapter(SynthesisOption, new SynthesisOptionSerializer)
    }
}
