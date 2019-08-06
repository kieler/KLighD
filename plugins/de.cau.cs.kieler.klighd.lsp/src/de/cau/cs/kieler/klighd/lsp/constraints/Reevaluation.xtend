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

import de.cau.cs.kieler.klighd.kgraph.KNode
import java.util.HashSet
import java.util.List
import org.eclipse.elk.alg.layered.options.LayeredOptions

/**
 * @author cos
 * 
 */
class Reevaluation {


    HashSet<KNode> changedNodes = newHashSet()
    KNode target

    new(KNode target) {
        this.target = target
    }

    def getTarget() {
        return target
    }

    def getChangedNodes() {
        return changedNodes
    }

    /**
     * Adjusts position constraints in a layer after one node has been introduced to it.
     */
    def reevaluatePosConstraintsAfterLayerSwap(List<KNode> newNodesOfLayer, List<KNode> oldNodesOfLayer, KNode target,
        int newPos) {

        val properNewPos = constraintToProperPosition(target, newNodesOfLayer, newPos)

        // formerLayer != newLayer -- should always be true - it doesn't cause errors if it's not, though.
        // The node is "deleted" from its old layer if it had a position constraint the old layer 
        // needs to be reevaluated
        offsetPosConstraintsOfLayerFrom(oldNodesOfLayer, -1,
            target.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_I_D), target)

        // The node is added at the new position in the new layer.

        offsetPosConstraintsOfLayerFrom(newNodesOfLayer, 1, properNewPos, target)

    }

    /**
     * Translates a constraint value to a position. This is necessary when a user sets a constraint value that is higher 
     * than the count of nodes + 1 in the target layer since then it's constraint is not the position where it will end up.
     */
    def constraintToProperPosition(KNode target, List<KNode> targetLayer, int posConstraint) {
        val layerLen = targetLayer.length
        if (posConstraint > layerLen) {
            // check the last node of the layer if it doesn't have a poscons > layerLength-1
            // then the desired position of the target is layerLen
            val lastNode = targetLayer.get(layerLen - 1)
            val posConsLastNode = ConstraintsUtils.getPosConstraint(lastNode)
            if (posConsLastNode !== -1 && posConsLastNode > layerLen - 1 && posConstraint > posConsLastNode) {
                return layerLen - 1
            }

            // If this is not the case then search the biggest posCons that is still smaller than posConstraint
            var pos = 0
            var tempPosCons = 0
            for (var i = 0; i < layerLen; i++) {
                val n = targetLayer.get(i)
                val posConsOfN = ConstraintsUtils.getPosConstraint(n)

                if (n != target && posConsOfN !== -1) {
                    if (posConsOfN > posConstraint) {
                        return pos
                    } else if (posConsOfN > tempPosCons) {
                        pos = i
                        tempPosCons = posConsOfN
                    }

                }

            }
            return pos
        } else {
            return posConstraint
        }
    }

    def reevaluatePosConstraintsAfterPosChangeInLayer(List<KNode> nodesOfLayer, KNode target, int newPos) {
        val oldPos = target.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_I_D)

        val properNewPos = constraintToProperPosition(target, nodesOfLayer, newPos)
        println("Within one layer: Proper New Pos: " + properNewPos + " based on constraint " + newPos)
        if (properNewPos < oldPos) {
            // new position constraint is above the old position
            // increment all position constraints of nodes that weren't below the target beforehand
            offsetPosConstraintsOfLayerFromTo(nodesOfLayer, 1, properNewPos, oldPos, target)

        } else {
            // oldPos < newPos new position constraint is below the old position
            // Decrement all position constraints of nodes that weren't above the target beforehand

            offsetPosConstraintsOfLayerFromTo(nodesOfLayer, -1, oldPos, properNewPos, target)

        }

    }

    /**
     * Decrements all nodes below a node that has been removed from the layer
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
     */

    def reevaluateAfterEmptyingALayer(KNode target, int targetLayer, List<KNode> nodes) {

        val layerConsTarget = ConstraintsUtils.getLayerConstraint(target)
        val layerId = target.getProperty(LayeredOptions.LAYERING_LAYER_I_D)
        val origLayer = layerConsTarget > layerId ? layerConsTarget : layerId
        val origLayerL = ConstraintsUtils.getNodesOfLayer(origLayer, nodes)

        if (origLayerL.length == 1) {
            /* If a layer is emptied and disappears from the drawing 
             * then all LayerConstraints with a value higher or equal than
             * the disappeared layer need to be decremented*/
            for (node : nodes) {
                val layerCons = ConstraintsUtils.getLayerConstraint(node)
                if (layerCons >= origLayer) {
                    changedNodes.add(node)
                    ConstraintsUtils.setLayerConstraint(node, layerCons - 1)
                }
            }

            if (origLayer <= targetLayer) {
                return true
            }
        }
        return false
    }

    /**
     * Shifting-Reval 
     * Simulates the shifting of nodes in order to adjust layer and position constraints.
     * 
     * @param insertedNode the node that is the target of the constraints
     * @param posCons the value of the position constraint for insertedNode
     * @param layerCons the value of the layer constraint for insertedNode
     * @param oldLayerNodes The origin layer of insertedNode
     * @param newLayerNodes The target layer of insertedNode
     * @param nodes All nodes of the current graph
     */
    def void shiftIfNec(KNode insertedNode, int posCons, int layerCons, List<KNode> oldLayerNodes,
        List<KNode> newLayerNodes, List<KNode> nodes) {

        val inEdges = insertedNode.incomingEdges
        val outEdges = insertedNode.outgoingEdges
        var List<KNode> adjacentNodes = newArrayList()

        for (e : inEdges) {
            adjacentNodes.add(e.source)
        }
        for (e : outEdges) {
            adjacentNodes.add(e.target)
        }

        var List<KNode> shiftedNodes = newArrayList()

        for (n : adjacentNodes) {
            if (newLayerNodes.contains(n)) {
                // If the shifted node has a layer constraint. It needs to be incremented else the shift would have no effect.
                shiftedNodes.add(n)
                if (ConstraintsUtils.getLayerConstraint(n) != -1) {
                    ConstraintsUtils.setLayerConstraint(n, layerCons + 1)
                    changedNodes.add(n)

                }
                // Test whether the shift leads to more shifts in the next layer.
                val nextNextLayerNodes = ConstraintsUtils.getNodesOfLayer(
                    n.getProperty(LayeredOptions.LAYERING_LAYER_I_D) + 1, nodes)
                val nPosId = n.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_I_D)

                shiftIfNec(n, nPosId, layerCons + 1, newLayerNodes, nextNextLayerNodes, nodes)

            }
        }

    // Adjust position constraints in the source layer of the shifted node 
    // and in the target layer of the shifted node
//        for (n : shiftedNodes) {
//            val nextNextLayerNodes = ConstraintsUtils.getNodesOfLayer(
//                n.getProperty(LayeredOptions.LAYERING_LAYER_I_D) + 1, nodes)
//            reevaluateAfterShift(n, insertedNode, newLayerNodes, nextNextLayerNodes)
//        }

    }

    /**
     * Position Reevaluation for Blockshifting
     * Adjusts positional constraints in the source and target layer after one node has been shifted. 
     */
    def reevaluateAfterShift(KNode shiftedNode, KNode targetNode, int posCons, List<KNode> originLayer,
        List<KNode> targetLayer) {
        // TODO: Include PosConstraint
        // Currently, we only shift from left to right.
        // Get the position of the shiftedNode - it's the same position on which it will end up in its new layer
        val posIndexOfShifted = shiftedNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_I_D)
        val posConsShifted = ConstraintsUtils.getPosConstraint(shiftedNode)
        if (posConsShifted !== -1) {
            reevaluatePosConstraintsAfterLayerSwap(originLayer, targetLayer, shiftedNode, posConsShifted)

        } else {
            reevaluatePosConstraintsAfterLayerSwap(originLayer, targetLayer, shiftedNode, posIndexOfShifted)

        }
        // Reevaluate the position constraints in the source and target layer accordingly
        // Also examine the position constraint of the target node
        val targetPos = constraintToProperPosition(targetNode, targetLayer, posCons)
        if (targetPos > 0 && targetPos >= posIndexOfShifted) {
            targetNode.setProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, posCons - 1)
            changedNodes.add(targetNode)
        }

    }

    /**
     * Offsets all nodes in a layer by {@code offset} that own a positional constraint that is greater or equal 
     * than {@code startPos} and smaller than {@code endPos}.
     * 
     * @param layer The layer to examine as a list including its nodes ordered by position.
     * @param The offset that should be applied on the position constraints
     * 
     */
    def private offsetPosConstraintsOfLayerFromTo(List<KNode> layer, int offset, int startPos, int endPos,
        KNode target) {

        if (!layer.empty) {
            for (var i = startPos; i < endPos + 1; i++) {
                val node = layer.get(i)
                val posChoiceCons = ConstraintsUtils.getPosConstraint(node)

                if (node != target && posChoiceCons !== -1) {
                    ConstraintsUtils.setPosConstraint(node, posChoiceCons + offset)
                    changedNodes.add(node)
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
     * 
     */
    def private offsetPosConstraintsOfLayerFrom(List<KNode> layer, int offset, int startPos, KNode target) {
        offsetPosConstraintsOfLayerFromTo(layer, offset, startPos, layer.length - 1, target)
    }

    /**
     * Checks the constraints that are currently present in the model.
     * This is necessary since the user is able to add an arbitrary number of constraints to the source code. 
     * On this way they could define constraints that are obscure or harm the processing of the constraints.
     * This should be caught before the layout is done. Constraints that are invalid are deactivated.
     */
    def static ckeckModelConstraints(List<KNode> nodes) {
        checkForFlatEdgeCausingConstraints(nodes)
    }

    /**
     * Eliminates equal position constraints in the same layer since their semantics are unambitious.
     * Parts of the reevaluation for adding a node are reused for this.
     * This should be deactivated in default mode.
     * @param nodes All nodes to examine. Requirement: The nodes must not have constraints that cause flat edges.
     */
    def static eliminateEqualPositionsInSameLayer(List<KNode> nodes) {

        // TODO - still doesn't work completely
        // Find out the maximal value among layer ids and layer constraints
        // in order to init the layerSets arry with this value           
        var maxLayer = 0
        for (n : nodes) {

            // The method is called before the layout has been performed
            // This means a layer id does not need to be equal to the layer constraint
            var actualLayer = n.getProperty(LayeredOptions.LAYERING_LAYER_I_D)

            if (actualLayer > maxLayer) {
                maxLayer = actualLayer
            }
        }

        var HashSet<Integer>[] layerSets = newArrayOfSize(maxLayer + 1)

        for (n : nodes) {

            var actualLayerId = ConstraintsUtils.getLayerConstraint(n)
            if (actualLayerId == -1) {
                actualLayerId = n.getProperty(LayeredOptions.LAYERING_LAYER_I_D)
            }

            var HashSet<Integer> layerSet = layerSets.get(actualLayerId)

            if (layerSet === null) {
                layerSet = new HashSet<Integer>
                layerSets.set(actualLayerId, layerSet)
            }

            val posCons = ConstraintsUtils.getPosConstraint(n)
            // Only examine nodes that actually have a position constraint
            if (posCons != -1) {
                if (layerSet.contains(posCons)) {
                    ConstraintsUtils::setPosConstraint(n, posCons + 1)
                    layerSet.add(posCons + 1)
                }
            }
        }

    }

    /**
     * Checks and corrects constraints that break the upper boundary of the positions in layer or layers.
     * @param nodes All nodes to examine. Requirement: The nodes must not have constraints that cause flat edges.
     */
    def static checkForBoundaryBreakingConstraints(List<KNode> nodes) {
    }

    /**
     * Checks for constraints in the model that cause flat edges (edges in one layer).
     * It performs an adjusted shift reevaluation.
     * While the reevaluation edits constraints that cause flat edges, they need to be handled differently 
     * if they are introduced via a loaded file. 
     */
    def static checkForFlatEdgeCausingConstraints(List<KNode> nodes) {
    }

}
