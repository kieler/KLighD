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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.lsp.interactive.layered

import de.cau.cs.kieler.klighd.kgraph.KNode
import org.eclipse.elk.alg.layered.options.LayeredOptions

/**
 * Provides a set of utility methods that is used in the constraints package.
 * 
 * @author jet, cos, sdo
 */
class ConstraintsUtils {

    /*
     * Several quality of life methods for faster retrieval and setting of constraints 
     * without typing the IProperty enums again and again
     */
    /**
     * Returns the value of the position constraint that is set on the node.
     * 
     * @param node the instance of KNode of which you want the constraint value
     * @return The position constraint of the given node.
     */
    def static getPosConstraint(KNode node) {
        return node.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT)
    }

    /**
     * Returns the value of the layer constraint that is set on the node.
     * 
     * @param node the instance of KNode of which you want the constraint value
     * @return The layer constraint of the given node.
     */
    def static getLayerConstraint(KNode node) {
        return node.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT)
    }

    /**
     * Sets the value of the position constraint that is set on the node.
     * 
     * @param node The instance of KNode of which you want the constraint value
     * @param pos The new desired position of the node.
     */
    def static setPosConstraint(KNode node, int pos) {
        node.setProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, pos)
    }

    /**
     * Sets the value of the layer constraint that is set on the node.
     * 
     * @param node the instance of KNode that should get the constraint.
     * @param layer the value for the layer constraint
     */
    def static setLayerConstraint(KNode node, int layer) {
        node.setProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, layer)
    }

    /**
     * Sets the value of the layer constraint to null.
     * This procedure effectively deletes the constraint from the node.
     * 
     * @param node the instance of KNode of which the layer constraint is set to null
     */
    def static nullifyLayerConstraint(KNode node) {
        node.setProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, null)
    }

    /**
     * Sets the value of the position constraint to null.
     * This procedure effectively deletes the constraint from the node.
     * 
     * @param node the instance of KNode of which the position constraint is set to null.
     */
    def static nullifyPosConstraint(KNode node) {
        node.setProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, null)
    }
}
