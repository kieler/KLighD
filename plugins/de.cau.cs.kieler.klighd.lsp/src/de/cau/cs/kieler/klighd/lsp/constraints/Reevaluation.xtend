/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.constraints

import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.kgraph.KNode
import org.eclipse.elk.alg.layered.options.LayeredOptions
import java.util.ArrayList
import java.util.List

/**
 * @author cos
 * 
 */
class Reevaluation {

    /**
     * Adjusts positional constraints in a layer after one node received a new positional constraint in it.
     */
    def static reevaluatePositionConstraints(List<KNode> nodesOfLayer, KNode target) {
        for (n : nodesOfLayer) {
            val posChoiceCons = n.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT)
            val targetChoiceCons = n.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT)

            if (posChoiceCons != -1 && posChoiceCons >= targetChoiceCons) {
                n.setProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, posChoiceCons + 1)
            }
        }

    }

    /**
     * Adjusts positional constraints in a layer after one node has been shifted. 
     */
    def static reevaluateAfterShift(KNode shiftedNode, KNode targetNode, ArrayList<KNode> originLayer) {

        val posIndexOfShifted = shiftedNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_I_D)

        // Decrement all positional constraint values of nodes that are below the shifted node
        for (n : originLayer) {
            val posChoiceCons = n.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT)

            if (posChoiceCons != -1 && posChoiceCons >= posIndexOfShifted) {
                n.setProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, posChoiceCons - 1)
            }
        }

        // In the case that a static constraint was set also examine the target node
        val targetPosChoiceCons = targetNode.getProperty(
            LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT)
        if (targetPosChoiceCons > 0 && targetPosChoiceCons >= posIndexOfShifted) {
            targetNode.setProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT,
                targetPosChoiceCons - 1)
        }

    }

}
