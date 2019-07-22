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
        // Offset all positional constraint greater or equal to the new one in order to conserve the 
        // established subsequence of nodes below the inserted node
        val targetChoiceCons = ConstraintsUtils.getPosConstraint(target)
        offsetPositionConstraintsInLayerBeginningAt(nodesOfLayer, 1, targetChoiceCons)
    }

    def static reevaluatePositionConstraintsAfterRemoval(List<KNode> nodesOfLayer, KNode removedNode) {
        // Offset all positional constraint greater or equal to the new one in order to conserve the 
        // established subsequence of nodes below the removed node
        val formerPosCons = removedNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT)
        offsetPositionConstraintsInLayerBeginningAt(nodesOfLayer, -1, formerPosCons)
    }

    /**
     * Adjust layer constraints in the graph if a new layer constraints empties a layer and lets it disappear. 
     */
    def static reevaluateAfterEmptyingALayer(int targetLayer, List<KNode> nodes) {
        // If a layer is emptied and disappears from the drawing 
        // then all LayerConstraints with a value higher or equal than
        // the disappeared layer need to be decreased
        for (node : nodes) {
            val layerCons = ConstraintsUtils.getLayerConstraint(node)
            if (layerCons >= targetLayer) {
                ConstraintsUtils.setLayerConstraint(node, layerCons - 1)
            }
        }
    }

    /**
     * Adjusts positional constraints in the source and target layer after one node has been shifted. 
     */
    def static reevaluateAfterShift(
        KNode shiftedNode,
        KNode targetNode,
        ArrayList<KNode> originLayer,
        ArrayList<KNode> targetLayer
    ) {
        // Currently, we only shift from left to right.
        val posIndexOfShifted = shiftedNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_I_D)

        // Decrement all positional constraint values of nodes that are below the shifted node in the original layer.
        offsetPositionConstraintsInLayerBeginningAt(originLayer, -1, posIndexOfShifted)

        // In the case that a static constraint was set also examine the target node
        val targetPosChoiceCons = targetNode.getProperty(
            LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT)
        if (targetPosChoiceCons > 0 && targetPosChoiceCons >= posIndexOfShifted) {
            targetNode.setProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT,
                targetPosChoiceCons - 1)
        }

        // If the shifted node has a layer constraint. It needs to be incremented else the shift would have no effect.
        val layerCons = ConstraintsUtils.getLayerConstraint(shiftedNode)
        if (layerCons != -1) {
            ConstraintsUtils.setLayerConstraint(shiftedNode, layerCons + 1)
        }

        // If the shifted node has a positional constraint. Its target layer needs to be reevaluated. 
        val posCons = ConstraintsUtils.getPosConstraint(shiftedNode)
        if (posCons != -1) {
            offsetPositionConstraintsInLayerBeginningAt(targetLayer, 1, posCons)
        }
    }

    /**
     * Offsets all nodes in a layer by {@code offset} that own a positional constraint that is greater or equal 
     * than {@code startPos}.
     */
    def private static offsetPositionConstraintsInLayerBeginningAt(List<KNode> layer, int offset, int startPos) {
        for (node : layer) {
            val posChoiceCons = ConstraintsUtils.getPosConstraint(node)

            if (posChoiceCons != -1 && posChoiceCons >= startPos) {
                ConstraintsUtils.setPosConstraint(node, posChoiceCons - 1)

            }
        }
    }

}
