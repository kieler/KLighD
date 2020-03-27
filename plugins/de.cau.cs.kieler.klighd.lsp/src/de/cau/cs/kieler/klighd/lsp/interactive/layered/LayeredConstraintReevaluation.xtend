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

import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.interactive.ConstraintProperty
import de.cau.cs.kieler.klighd.lsp.interactive.InteractiveUtil
import java.util.HashSet
import java.util.LinkedList
import java.util.List
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Class to reevaluate constraint set for the layered algorithm since they may become obsolete or have to be changed
 * if some node is moved.
 * @author cos, sdo
 * 
 */
class LayeredConstraintReevaluation {

    @Accessors(PUBLIC_GETTER)
    LinkedList<ConstraintProperty> changedNodes = newLinkedList()
    
    @Accessors(PUBLIC_GETTER)
    KNode target

    new(KNode target) {
        this.target = target
    }

    /**
     * Adjusts position constraints in a layer after one node has been introduced to it.
     * @param newNodesOfLayer nodes of the new layer
     * @param oldNodesOfLayer the nodes of old layer
     * @param target the moved node
     * @param newPos the new position of the node
     */
    def reevaluatePositionConstraintsAfterLayerSwap(List<KNode> newNodesOfLayer, List<KNode> oldNodesOfLayer, KNode target,
        int newPos) {

        // formerLayer != newLayer -- should always be true - it doesn't cause errors if it's not, though.
        // The node is "deleted" from its old layer if it had a position constraint the old layer 
        // needs to be reevaluated
        offsetPosConstraintsOfLayerFrom(oldNodesOfLayer, -1,
            target.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_I_D), target)

        // The node is added at the new position in the new layer.
        offsetPosConstraintsOfLayerFrom(newNodesOfLayer, 1, newPos, target)

    }

    /**
     * Adjusts position constraints in a layer after one node has been introduced to it.
     * @param nodesOfLayer the nodes of the layer
     * @param target the moved node
     * @param newPos the new position of the node
     */
    def reevaluatePositionConstraintsAfterPosChangeInLayer(List<KNode> nodesOfLayer, KNode target, int newPos) {
        val oldPos = target.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_I_D)

        if (newPos < oldPos) {
            // new position constraint is above the old position
            // increment all position constraints of nodes that weren't below the target beforehand
            offsetPosConstraintsOfLayerFromTo(nodesOfLayer, 1, newPos, oldPos, target)

        } else {
            // oldPos < newPos new position constraint is below the old position
            // Decrement all position constraints of nodes that weren't above the target beforehand
            offsetPosConstraintsOfLayerFromTo(nodesOfLayer, -1, oldPos, newPos, target)

        }

    }

    /**
     * Reevaluates position constraints in a layer after a node is removed.
     * @param nodesOfLayer the nodes in the layer
     * @param removedNode the removed node
     * 
     */
    def reevaluatePositionConstraintsAfterRemoval(List<KNode> nodesOfLayer, KNode removedNode) {
        // Offset all positional constraint greater or equal to the new one in order to conserve the 
        // established subsequence of nodes below the removed node
        val formerPosCons = ConstraintsUtils.getPosConstraint(removedNode)
        if (formerPosCons !== -1) {
            offsetPosConstraintsOfLayerFrom(nodesOfLayer, -1, formerPosCons, removedNode)
        }
    }

    /**
     * Optional reevaluation when emptying a layer should lead to its disappearance.
     * Adjust layer constraints in the graph if a new layer constraint empties a layer and lets it disappear.
     * @param target the removed node
     * @param targetLayer the layer id
     * @param nodes the nodes in the graph
     */
    def reevaluateAfterEmptyingALayer(KNode target, int targetLayer, List<KNode> nodes) {

        val layerConsTarget = ConstraintsUtils.getLayerConstraint(target)
        val layerId = target.getProperty(LayeredOptions.LAYERING_LAYER_I_D)
        val origLayer = if (layerConsTarget > layerId) layerConsTarget else layerId
        val origLayerL = InteractiveUtil.getNodesOfLayer(origLayer, nodes)

        if (origLayerL.length == 1) {
            /* If a layer is emptied and disappears from the drawing 
             * then all LayerConstraints with a value higher or equal than
             * the disappeared layer need to be decremented*/
            for (node : nodes) {
                val layerCons = ConstraintsUtils.getLayerConstraint(node)
                if (layerCons >= origLayer) {
                    changedNodes.add(new ConstraintProperty(node, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, layerCons - 1))
                }
            }

            if (origLayer <= targetLayer) {
                return true
            }
        }
        return false
    }

    /**
     * Shifting-Reevaluation
     * Simulates the shifting of nodes in order to adjust layer and position constraints.
     * 
     * @param insertedNode the node that is the target of the constraints
     * @param posCons the value of the position constraint for insertedNode
     * @param layerCons the value of the layer constraint for insertedNode
     * @param oldLayerNodes The origin layer of insertedNode
     * @param newLayerNodes The target layer of insertedNode
     * @param nodes All nodes of the current graph
     */
    def void shiftIfNecessary(KNode insertedNode, int newLayerId, int layerCons, int newPosId, int posCons,
        List<KNode> oldLayerNodes, List<KNode> newLayerNodes, List<KNode> nodes) {

        val adjacentNodes = InteractiveUtil.getAdjacentNodes(insertedNode)

        var List<KNode> shiftedNodes = newArrayList()

        for (n : adjacentNodes) {
            if (newLayerNodes.contains(n)) {
                // If the shifted node has a layer constraint. It needs to be incremented else the shift would have no effect.
                shiftedNodes.add(n)
                if (ConstraintsUtils.getLayerConstraint(n) !== -1 && false) { // TODO
                    changedNodes.add(new ConstraintProperty(n, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, layerCons + 1))

                }
                // Test whether the shift leads to more shifts in the next layer.
                val nextNextLayerNodes = InteractiveUtil.getNodesOfLayer(newLayerId + 1, nodes)
                val nPosId = n.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_I_D)
                val nPosCons = ConstraintsUtils.getPosConstraint(n)

                shiftIfNecessary(n, newLayerId + 1, layerCons + 1, nPosId, nPosCons, newLayerNodes, nextNextLayerNodes, nodes)

            }
        }
    }

    /**
     * Position Reevaluation for Blockshifting
     * Adjusts positional constraints in the source and target layer after one node has been shifted.
     * @param shiftedNode the shifted node
     * @param targetNode the node that might be changed
     * @param posCons the layer the target node
     * @param originLayer the previous layer of the node
     * @param targetLayer the new layer of the node 
     */
    def reevaluateAfterShift(KNode shiftedNode, KNode targetNode, int posCons, List<KNode> originLayer,
        List<KNode> targetLayer) {
        // Currently, we only shift from left to right.
        // Get the position of the shiftedNode - it's the same position on which it will end up in its new layer
        val posIndexOfShifted = shiftedNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_I_D)
        val posConsShifted = ConstraintsUtils.getPosConstraint(shiftedNode)
        if (posConsShifted !== -1) {
            reevaluatePositionConstraintsAfterLayerSwap(originLayer, targetLayer, shiftedNode, posConsShifted)

        } else {
            reevaluatePositionConstraintsAfterLayerSwap(originLayer, targetLayer, shiftedNode, posIndexOfShifted)

        }
        // Reevaluate the position constraints in the source and target layer accordingly
        // Also examine the position constraint of the target node
        if (posCons > 0 && posCons >= posIndexOfShifted) {
            changedNodes.add(new ConstraintProperty(targetNode, LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, posCons - 1))
        }

    }

    /**
     * Offsets all nodes in a layer by {@code offset} that own a positional constraint that is greater or equal 
     * than {@code startPos} and smaller than {@code endPos}.
     * 
     * @param layer The layer to examine as a list including its nodes ordered by position.
     * @param offset The offset that should be applied on the position constraints
     * @param startPos The smallest position of the layer
     * @param endPos The biggest position of the layer
     * @param target The target node
     * 
     */
    def private offsetPosConstraintsOfLayerFromTo(List<KNode> layer, int offset, int startPos, int endPos,
        KNode target) {

        if (!layer.empty) {
            for (var i = startPos; i < endPos + 1; i++) {
                val node = layer.get(i)
                val posChoiceCons = ConstraintsUtils.getPosConstraint(node)

                if (node != target && posChoiceCons !== -1) {
                    changedNodes.add(new ConstraintProperty(node, LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, posChoiceCons + offset))
                }
            }
        }
    }

    /**
     * Offsets all nodes in a layer by {@code offset} that own a positional constraint that is greater or equal 
     * than {@code startPos}.
     * 
     * @param layer The layer to examine as a list including its nodes ordered by position.
     * @param The offset that should be applied on the position constraints
     * @param startPos The smallest position of the layer
     * @param target The target node
     * 
     */
    def private offsetPosConstraintsOfLayerFrom(List<KNode> layer, int offset, int startPos, KNode target) {
        offsetPosConstraintsOfLayerFromTo(layer, offset, startPos, layer.length - 1, target)
    }
}
