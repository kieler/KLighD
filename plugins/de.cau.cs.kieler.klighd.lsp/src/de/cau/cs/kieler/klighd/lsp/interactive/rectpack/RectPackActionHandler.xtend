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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.interactive.rectpack

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.lsp.AbstractActionHandler
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramServer
import org.eclipse.sprotty.Action
import org.eclipse.sprotty.xtext.LanguageAwareDiagramServer

/**
 * @author sdo
 *
 */
class RectPackActionHandler extends AbstractActionHandler {
    
    @Inject
    RectPackInterativeLanguageServerExtension lsExtension
    
    new() {
        this.supportedMessages = newHashMap(
        RectPackSetPositionConstraintAction.KIND -> RectPackSetPositionConstraintAction,
        RectPackDeletePositionConstraintAction.KIND -> RectPackDeletePositionConstraintAction,
        SetAspectRatioAction.KIND -> SetAspectRatioAction)
    }
    
    override handle(Action action, String clientId, LanguageAwareDiagramServer server) {
        if (action instanceof RectPackSetPositionConstraintAction) {
            synchronized((server as KGraphDiagramServer).modelLock) {
                lsExtension.setPositionConstraint(action.constraint, clientId)
            }
        } else if (action instanceof RectPackDeletePositionConstraintAction) {
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
