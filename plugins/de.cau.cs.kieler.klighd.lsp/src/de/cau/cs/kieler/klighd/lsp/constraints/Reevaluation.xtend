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
import java.util.HashSet
import java.util.HashMap

/**
 * @author cos
 * 
 */
class Reevaluation {

    /**
     * Adjusts positional constraints in a layer after one node received a new positional constraint in it.
     */
    def static reevaluatePositionConstraintsAfterAdd(List<KNode> nodesOfLayer, KNode target) {
        // Offset all positional constraint greater or equal to the new one in order to conserve the 
        // established subsequence of nodes below the inserted node
        val targetChoiceCons = ConstraintsUtils.getPosConstraint(target)
        offsetPositionConstraintsInLayerBeginningAt(nodesOfLayer, 1, targetChoiceCons, target)
    }

    def static reevaluatePositionConstraintsAfterRemoval(List<KNode> nodesOfLayer, KNode removedNode) {
        // Offset all positional constraint greater or equal to the new one in order to conserve the 
        // established subsequence of nodes below the removed node
        val formerPosCons = removedNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT)
        offsetPositionConstraintsInLayerBeginningAt(nodesOfLayer, -1, formerPosCons, removedNode)
    }

    /**
     * Adjust layer constraints in the graph if a new layer constraint empties a layer and lets it disappear. 
     */
    def static reevaluateAfterEmptyingALayer(int origLayer, int targetLayer, List<KNode> nodes) {

        val origLayerL = ConstraintsUtils.getNodesOfLayer(origLayer, nodes)

        if (origLayerL.length == 1) {
            /* If a layer is emptied and disappears from the drawing 
             * then all LayerConstraints with a value higher or equal than
             * the disappeared layer need to be decremented*/
            for (node : nodes) {
                val layerCons = ConstraintsUtils.getLayerConstraint(node)
                if (layerCons >= targetLayer) {
                    ConstraintsUtils.setLayerConstraint(node, layerCons - 1)
                }
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
        offsetPositionConstraintsInLayerBeginningAt(originLayer, -1, posIndexOfShifted, shiftedNode)

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
            offsetPositionConstraintsInLayerBeginningAt(targetLayer, 1, posCons, shiftedNode)
        }
    }

    /**
     * Offsets all nodes in a layer by {@code offset} that own a positional constraint that is greater or equal 
     * than {@code startPos}.
     */
    def private static offsetPositionConstraintsInLayerBeginningAt(List<KNode> layer, int offset, int startPos, KNode target) {
        for (node : layer) {
            val posChoiceCons = ConstraintsUtils.getPosConstraint(node)

            if (node !== target && posChoiceCons != -1 && posChoiceCons >= startPos) {
                ConstraintsUtils.setPosConstraint(node, posChoiceCons + offset)
            }
        }
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
     
     //TODO - still doesn't work completely
     
     // Find out the maximal value among layer ids and layer constraints
     // in order to init the layerSets arry with this value           
        var maxLayer = 0
        for (n : nodes){
           
           //The method is called before the layout has been performed
           //This means a layer id does not need to be equal to the layer constraint
           var actualLayer = ConstraintsUtils.actualLayer(n)
            if(actualLayer == -1){
                actualLayer = n.getProperty(LayeredOptions.LAYERING_LAYER_I_D)
            }
            
            if(actualLayer > maxLayer){
                maxLayer = actualLayer
            } 
        }
        
       var HashSet<Integer>[] layerSets = newArrayOfSize(maxLayer + 1)
                
        
        for (n : nodes) {
            
            var actualLayerId = ConstraintsUtils.getLayerConstraint(n)
            if(actualLayerId == -1){
                actualLayerId = n.getProperty(LayeredOptions.LAYERING_LAYER_I_D)
            }
            
            var HashSet<Integer> layerSet = layerSets.get(actualLayerId)
            
            if(layerSet === null){
                layerSet = new HashSet<Integer>
                layerSets.set(actualLayerId, layerSet)
            }
            
            val posCons = ConstraintsUtils.getPosConstraint(n)
            // Only examine nodes that actually have a position constraint
            if (posCons != -1) {
                if(layerSet.contains(posCons)){
                    ConstraintsUtils::setPosConstraint(n, posCons+1)
                    layerSet.add(posCons+1)
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
    def static checkForFlatEdgeCausingConstraints(List<KNode> nodes){
        
        
    }

}
