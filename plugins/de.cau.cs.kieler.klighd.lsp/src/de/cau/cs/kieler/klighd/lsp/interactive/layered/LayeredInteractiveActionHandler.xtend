/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019-2022 by
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
package de.cau.cs.kieler.klighd.lsp.interactive.layered

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.lsp.AbstractActionHandler
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramServer
import org.eclipse.sprotty.Action

/**
 * Handles all sprotty actions for the layered interactive algorithm.
 *
 * @author sdo, jep
 */
class LayeredInteractiveActionHandler extends AbstractActionHandler {
    
    @Inject
    LayeredInteractiveLanguageServerExtension constraintLS
    
    new() {
        this.supportedMessages = newHashMap(
            SetStaticConstraintAction.KIND -> SetStaticConstraintAction,
            SetPositionConstraintAction.KIND -> SetPositionConstraintAction,
            SetLayerConstraintAction.KIND -> SetLayerConstraintAction,
            DeleteStaticConstraintAction.KIND -> DeleteStaticConstraintAction,
            DeletePositionConstraintAction.KIND -> DeletePositionConstraintAction,
            DeleteLayerConstraintAction.KIND -> DeleteLayerConstraintAction,
            SetInLayerPredecessorOfConstraintAction.KIND -> SetInLayerPredecessorOfConstraintAction,
            SetInLayerSuccessorOfConstraintAction.KIND -> SetInLayerSuccessorOfConstraintAction,
            DeleteRelativeConstraintsAction.KIND -> DeleteRelativeConstraintsAction,
            DeleteInLayerPredecessorOfConstraintAction.KIND -> DeleteInLayerPredecessorOfConstraintAction,
            DeleteInLayerSuccessorOfConstraintAction.KIND -> DeleteInLayerSuccessorOfConstraintAction
        )
    }
    
    override handle(Action action, String clientId, KGraphDiagramServer server) {
        if (action instanceof SetStaticConstraintAction) {
            synchronized (server.modelLock) {
                constraintLS.setStaticConstraint(action.constraint, clientId)
            }
        } else if (action instanceof SetPositionConstraintAction) {
            synchronized (server.modelLock) {
                constraintLS.setPositionConstraint(action.constraint, clientId)
            }
        } else if (action instanceof SetLayerConstraintAction) {
            synchronized (server.modelLock) {
                constraintLS.setLayerConstraint(action.constraint, clientId)
            }
        } else if (action instanceof DeleteStaticConstraintAction) {
            synchronized (server.modelLock) {
                constraintLS.deleteStaticConstraint(action.constraint, clientId)
            }
        } else if (action instanceof DeletePositionConstraintAction) {
            synchronized (server.modelLock) {
                constraintLS.deletePositionConstraint(action.constraint, clientId)
            }
        } else if (action instanceof DeleteLayerConstraintAction) {
            synchronized (server.modelLock) {
                constraintLS.deleteLayerConstraint(action.constraint, clientId)
            }
        } else if (action instanceof SetInLayerPredecessorOfConstraintAction) {
            synchronized (server.modelLock) {
                constraintLS.setInLayerPredecessorOfConstraint(action.constraint, clientId)
            }
        } else if (action instanceof SetInLayerSuccessorOfConstraintAction) {
            synchronized (server.modelLock) {
                constraintLS.setInLayerSuccessorOfConstraint(action.constraint, clientId)
            }
        } else if (action instanceof DeleteRelativeConstraintsAction) {
            synchronized (server.modelLock) {
                constraintLS.deleteRelativeConstraints(action.constraint, clientId)
            }
        } else if (action instanceof DeleteInLayerPredecessorOfConstraintAction) {
            synchronized (server.modelLock) {
                constraintLS.deleteInLayerPredecessorOfConstraint(action.constraint, clientId)
            }
        } else if (action instanceof DeleteInLayerSuccessorOfConstraintAction) {
            synchronized (server.modelLock) {
                constraintLS.deleteILSuccOfConstraint(action.constraint, clientId)
            }
        } else {
            throw new IllegalArgumentException("Action " + action.kind + " not supported by handler " +
                this.class.simpleName)
        }
    }
}
