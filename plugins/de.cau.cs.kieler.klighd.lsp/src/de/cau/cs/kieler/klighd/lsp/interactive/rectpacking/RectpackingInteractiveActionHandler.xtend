/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2020 by
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
package de.cau.cs.kieler.klighd.lsp.interactive.rectpacking

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.lsp.AbstractActionHandler
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramServer
import org.eclipse.sprotty.Action

/**
 * This class handles all sprotty actions for the interactive rectpacking algorithm.
 *
 * @author sdo
 */
class RectpackingInteractiveActionHandler extends AbstractActionHandler {
    
    @Inject
    RectpackingInteractiveLanguageServerExtension lsExtension
    
    new() {
        this.supportedMessages = newHashMap(
        RectpackingSetPositionConstraintAction.KIND -> RectpackingSetPositionConstraintAction,
        RectpackingDeletePositionConstraintAction.KIND -> RectpackingDeletePositionConstraintAction,
        SetAspectRatioAction.KIND -> SetAspectRatioAction)
    }
    
    override handle(Action action, String clientId, KGraphDiagramServer server) {
        if (action instanceof RectpackingSetPositionConstraintAction) {
            synchronized((server as KGraphDiagramServer).modelLock) {
                lsExtension.setPositionConstraint(action.constraint, clientId)
            }
        } else if (action instanceof RectpackingDeletePositionConstraintAction) {
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
