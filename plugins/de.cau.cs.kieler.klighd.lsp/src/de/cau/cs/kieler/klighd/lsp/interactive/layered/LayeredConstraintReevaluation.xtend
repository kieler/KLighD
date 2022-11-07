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

import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil
import de.cau.cs.kieler.klighd.lsp.interactive.ConstraintProperty
import de.cau.cs.kieler.klighd.lsp.interactive.InteractiveUtil
import java.util.List
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Class to reevaluate constraint set for the layered algorithm since they may become obsolete or have to be changed
 * if some node is moved.
 * 
 * @author cos, sdo, jep
 */
class LayeredConstraintReevaluation {

    @Accessors(PUBLIC_GETTER)
    List<ConstraintProperty<Object>> changedNodes = newLinkedList()
    
    @Accessors(PUBLIC_GETTER)
    KNode target

    new(KNode target) {
        this.target = target
    }

    /**
     * Adjusts position constraints in a layer after one node has been introduced to it.
     * 
     * @param newNodesOfLayer The nodes of the new layer.
     * @param oldNodesOfLayer The nodes of old layer.
     * @param target The moved node.
     * @param newPosition The new position of the node.
     */
    def reevaluatePositionConstraintsAfterLayerSwap(List<KNode> newNodesOfLayer, List<KNode> oldNodesOfLayer,
        KNode target, int newPosition) {
        val chainLength = InteractiveUtil.getChain(target, oldNodesOfLayer).size
        // formerLayer != newLayer -- should always be true - it doesn't cause errors if it's not, though.
        // The node is "deleted" from its old layer if it had a position constraint the old layer 
        // needs to be reevaluated.
        offsetPositionConstraintsOfLayerFrom(oldNodesOfLayer, -chainLength,
            target.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID), target)

        // The node is added at the new position in the new layer.
        offsetPositionConstraintsOfLayerFrom(newNodesOfLayer, chainLength, newPosition, target)
    }

    /**
     * Adjusts position constraints in a layer after one node has been introduced to it.
     * 
     * @param nodesOfLayer The nodes of the layer.
     * @param target The moved node.
     * @param newPosition The new position of the node.
     */
    def reevaluatePositionConstraintsAfterPositionChangeInLayer(List<KNode> nodesOfLayer, KNode target,
        int newPosition) {
        val oldPosition = target.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)

        val chainLength = InteractiveUtil.getChain(target, nodesOfLayer).size
        if (newPosition < oldPosition) {
            // New position constraint is above the old position
            // increment all position constraints of nodes that weren't below the target beforehand.
            offsetPositionConstraintsOfLayerFromTo(nodesOfLayer, chainLength, newPosition, oldPosition, target)

        } else {
            // oldPos < newPos: New position constraint is below the old position.
            // Decrement all position constraints of nodes that weren't above the target beforehand.
            offsetPositionConstraintsOfLayerFromTo(nodesOfLayer, -chainLength, oldPosition, newPosition, target)
        }
    }

    /**
     * Reevaluates position constraints in a layer after a node is removed.
     * 
     * @param nodesOfLayer The nodes in the layer.
     * @param removedNode The removed node.
     */
    def reevaluatePositionConstraintsAfterRemoval(List<KNode> nodesOfLayer, KNode removedNode) {
        // Offset all position constraints greater or equal to the new one in order to conserve the 
        // established subsequence of nodes below the removed node.
        val formerPositionConstraint = ConstraintsUtils.getPositionConstraint(removedNode)
        if (formerPositionConstraint !== null) {
            offsetPositionConstraintsOfLayerFrom(nodesOfLayer, -1, formerPositionConstraint, removedNode)
        }
    }

    /**
     * Optional: Reevaluation when emptying a layer should lead to its disappearance.
     * Adjust layer constraints in the graph if a new layer constraint empties a layer and lets it disappear.
     * 
     * @param target The removed node.
     * @param targetLayer The layer id.
     * @param nodes The nodes in the graph.
     * @return True, if layers were deleted such that the targetLayer is out of bounds.
     */
    def reevaluateAfterEmptyingALayer(KNode target, int targetLayer, List<KNode> nodes) {

        val layerConstraintTarget = ConstraintsUtils.getLayerConstraint(target)
        val layerId = target.getProperty(LayeredOptions.LAYERING_LAYER_ID)
        var originalLayerIndex = 0
        if (layerConstraintTarget === null || layerConstraintTarget <= layerId) {
            originalLayerIndex = layerId
        } else {
            originalLayerIndex = layerConstraintTarget
        }
        val originalLayer = InteractiveUtil.getNodesOfLayer(originalLayerIndex, nodes)

        if (originalLayer.length == 1) {
            // If a layer is emptied and disappears from the drawing 
            // then all layer constraint with a value higher or equal than
            // the disappeared layer need to be decremented.
            for (node : nodes) {
                val layerConstraint = ConstraintsUtils.getLayerConstraint(node)
                if (layerConstraint !== null && layerConstraint >= originalLayerIndex) {
                    changedNodes.add(new ConstraintProperty(node, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, layerConstraint - 1))
                }
            }

            if (originalLayerIndex <= targetLayer) {
                return true
            }
        }
        return false
    }

    /**
     * Shifting-Reevaluation:
     * Simulates the shifting of nodes in order to adjust layer and position constraints.
     * 
     * @param insertedNode The node that is the target of the constraints.
     * @param newLayerId The id of the new layer.
     * @param layerConstraint The value of the layer constraint for insertedNode.
     * @param newPositionId The new position in the layer.
     * @param positionConstraint The value of the position constraint for insertedNode.
     * @param oldLayerNodes The origin layer of insertedNode.
     * @param newLayerNodes The target layer of insertedNode.
     * @param nodes All nodes of the current graph.
     */
    def void shiftIfNecessary(KNode insertedNode, int newLayerId, int layerConstraint, int newPositionId,
        int positionConstraint, List<KNode> oldLayerNodes, List<KNode> newLayerNodes, List<KNode> nodes) {

        val adjacentNodes = KGraphUtil.getAdjacentNodes(insertedNode)

        var List<KNode> shiftedNodes = newArrayList()

        for (n : adjacentNodes) {
            if (newLayerNodes.contains(n)) {
                // If the shifted node has a layer constraint.
                // It needs to be incremented else the shift would have no effect.
                shiftedNodes.add(n)
                // Test whether the shift leads to more shifts in the next layer.
                val nextNextLayerNodes = InteractiveUtil.getNodesOfLayer(newLayerId + 1, nodes)
                val nPositionId = n.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)
                val nPositionConstraint = ConstraintsUtils.getPositionConstraint(n)

                shiftIfNecessary(n, newLayerId + 1, layerConstraint + 1, nPositionId, nPositionConstraint,
                    newLayerNodes, nextNextLayerNodes, nodes)
            }
        }
    }

    /**
     * Position Reevaluation for Blockshifting.
     * Adjusts positional constraints in the source and target layer after one node has been shifted.
     * 
     * @param shiftedNode The shifted node.
     * @param targetNode The node that might be changed.
     * @param positionConstraint The layer the target node.
     * @param oldLayer The previous layer of the node.
     * @param targetLayer The new layer of the node.
     */
    def reevaluateAfterShift(KNode shiftedNode, KNode targetNode, int positionConstraint, List<KNode> oldLayer,
        List<KNode> targetLayer) {
        // Currently, we only shift from left to right.
        // Get the position of the shiftedNode - it's the same position on which it will end up in its new layer.
        val positionIndexOfShifted = shiftedNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)
        val positionConstraintOfShifted = ConstraintsUtils.getPositionConstraint(shiftedNode)
        if (positionConstraintOfShifted !== null) {
            reevaluatePositionConstraintsAfterLayerSwap(oldLayer, targetLayer, shiftedNode, positionConstraintOfShifted)

        } else {
            reevaluatePositionConstraintsAfterLayerSwap(oldLayer, targetLayer, shiftedNode, positionIndexOfShifted)
        }
        // Reevaluate the position constraints in the source and target layer accordingly.
        // Also examine the position constraint of the target node.
        if (positionConstraint > 0 && positionConstraint >= positionIndexOfShifted) {
            changedNodes.add(
                new ConstraintProperty(targetNode, LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT,
                    positionConstraint - 1))
        }
    }

    /**
     * Offsets all nodes in a layer by {@code offset} that own a positional constraint that is greater or equal 
     * than {@code startPos} and smaller than {@code endPos}.
     * 
     * @param layer The layer to examine as a list including its nodes ordered by position.
     * @param offset The offset that should be applied on the position constraints.
     * @param startPosition The smallest position of the layer.
     * @param endPosition The biggest position of the layer.
     * @param target The target node.
     */
    def private offsetPositionConstraintsOfLayerFromTo(List<KNode> layer, int offset, int startPosition,
        int endPosition, KNode target) {

        if (!layer.empty) {
            for (var currentPosition = startPosition; currentPosition < endPosition + 1; currentPosition++) {
                val node = layer.get(currentPosition)
                val positionConstraint = ConstraintsUtils.getPositionConstraint(node)

                if (node != target && positionConstraint !== null) {
                    changedNodes.add(
                        new ConstraintProperty(node, LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT,
                            Math.max(0, positionConstraint + offset)))
                }
            }
        }
    }

    /**
     * Offsets all nodes in a layer by {@code offset} that own a positional constraint that is greater or equal 
     * than {@code startPosition}.
     * 
     * @param layer The layer to examine as a list including its nodes ordered by position.
     * @param offset The offset that should be applied on the position constraints.
     * @param startPosition The smallest position of the layer.
     * @param target The target node.
     */
    def private offsetPositionConstraintsOfLayerFrom(List<KNode> layer, int offset, int startPosition, KNode target) {
        offsetPositionConstraintsOfLayerFromTo(layer, offset, startPosition, layer.length - 1, target)
    }
    
    /**
     * Updates position constraints of the moved node and the one in its chain.
     * 
     * @param node The moved node.
     * @param newPosition The new position of the moved node.
     * @param chain The chain of {@code node}.
     */
    def reevaluatePositionConstraintInChain(KNode node, int newPosition, List<KNode> chain) {
        val offset = chain.indexOf(node)
        for (var i = 0; i < chain.size; i++) {
            val chainedNode = chain.get(i)
            if (chainedNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT) !== null) {
                val currentPosition = newPosition - (offset - i)
                changedNodes.add(
                    new ConstraintProperty(chainedNode, LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT,
                        currentPosition))
            }
        }
    }
    
    /**
     * Updates layer constraint of the moved node and all other nodes in the chain to the layer of the target.
     * 
     * @param layer New value of the layer constraints.
     * @param chain Nodes of the chain the moved node is in.
     */
    def reevaluateLayerConstraintsInChain(int layer, List<KNode> chain) {
        for (chainedNode : chain) {
            if (chainedNode.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) !== null) {
                changedNodes.add(new ConstraintProperty(chainedNode, LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT,
                    layer))
            }
        }
    }
}
