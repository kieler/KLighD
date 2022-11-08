/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2022 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.interactive.mrtree

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.lsp.AbstractActionHandler
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramServer
import org.eclipse.sprotty.Action

/**
 * This class handles all sprotty actions for the interactive mrtree algorithm.
 * 
 * @author sdo
 *
 */
class MrTreeActionHandler extends AbstractActionHandler {
    
    @Inject
    MrTreeInteractiveLanguageServerExtension lsExtension
    
    new() {
        this.supportedMessages = newHashMap(
        MrTreeSetPositionConstraintAction.KIND -> MrTreeSetPositionConstraintAction,
        MrTreeDeletePositionConstraintAction.KIND -> MrTreeDeletePositionConstraintAction,
        SetAspectRatioAction.KIND -> SetAspectRatioAction)
    }
    
    override handle(Action action, String clientId, KGraphDiagramServer server) {
        if (action instanceof MrTreeSetPositionConstraintAction) {
            synchronized((server as KGraphDiagramServer).modelLock) {
                lsExtension.setPositionConstraint(action.constraint, clientId)
            }
        } else if (action instanceof MrTreeDeletePositionConstraintAction) {
            synchronized((server as KGraphDiagramServer).modelLock) {
                lsExtension.deletePositionConstraint(action.constraint, clientId)
            }
        } else if (action instanceof SetAspectRatioAction) {
            synchronized((server as KGraphDiagramServer).modelLock) {
                lsExtension.setAspectRatio(action.constraint, clientId)
            }
        } else {
            throw new IllegalArgumentException("Action " + action.kind + " not supported by handler " + this.class.simpleName)
        }
    }
    
}
