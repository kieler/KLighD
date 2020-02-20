/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.interactive.layered

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.lsp.AbstractActionHandler
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramServer
import org.eclipse.sprotty.Action
import org.eclipse.sprotty.xtext.LanguageAwareDiagramServer

/**
 * @author sdo
 *
 */
class ConstraintActionHandler extends AbstractActionHandler {
    
    @Inject
    ConstraintsLanguageServerExtension constraintLS
    
    
    new() {
        this.supportedMessages = newHashMap(
            SetStaticConstraintAction.KIND -> SetStaticConstraintAction,
            SetPositionConstraintAction.KIND -> SetPositionConstraintAction,
            SetLayerConstraintAction.KIND -> SetLayerConstraintAction,
            DeleteStaticConstraintAction.KIND -> DeleteStaticConstraintAction,
            DeletePositionConstraintAction.KIND -> DeletePositionConstraintAction,
            DeleteLayerConstraintAction.KIND -> DeleteLayerConstraintAction
        )
    }
    
    override handle(Action action, String clientId, LanguageAwareDiagramServer server) {
        if (action instanceof SetStaticConstraintAction) {
            synchronized((server as KGraphDiagramServer).modelLock) {
                constraintLS.setStaticConstraint(action.constraint, clientId)
            }
        } else if (action instanceof SetPositionConstraintAction) {
            synchronized((server as KGraphDiagramServer).modelLock) {
                constraintLS.setPositionConstraint(action.constraint, clientId)
            }
        } else if (action instanceof SetLayerConstraintAction) {
            synchronized((server as KGraphDiagramServer).modelLock) {
                constraintLS.setLayerConstraint(action.constraint, clientId)
            }
        } else if (action instanceof DeleteStaticConstraintAction) {
            synchronized((server as KGraphDiagramServer).modelLock) {
                constraintLS.deleteStaticConstraint(action.constraint, clientId)
            }
        } else if (action instanceof DeletePositionConstraintAction) {
            synchronized((server as KGraphDiagramServer).modelLock) {
                constraintLS.deletePositionConstraint(action.constraint, clientId)
            }
        } else if (action instanceof DeleteLayerConstraintAction) {
            synchronized((server as KGraphDiagramServer).modelLock) {
                constraintLS.deleteLayerConstraint(action.constraint, clientId)
            }
        } else {
            throw new IllegalArgumentException("Action " + action.kind + " not supported by handler " + this.class.simpleName)
        }
    }
    
}
