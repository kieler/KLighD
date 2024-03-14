/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2022 by
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
import de.cau.cs.kieler.klighd.lsp.interactive.ConstraintProperty
import de.cau.cs.kieler.klighd.lsp.interactive.InteractiveUtil
import java.util.List
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Class to reevaluate relative constraint set for the layered algorithm since they may become obsolete or have to be
 * changed if some node is moved.
 * 
 * @author jep
 */
class RelativeConstraintReevaluation {
    
    IProperty<String> predecessorProperty = LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_PRED_OF
    IProperty<String> successorProperty = LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_SUCC_OF
    
    @Accessors(PUBLIC_GETTER)
    List<ConstraintProperty<Object>> changedNodes = newLinkedList()
    
    @Accessors(PUBLIC_GETTER)
    KNode target

    new(KNode target) {
        this.target = target
    }
    
    /**
     * When a node is moved between two other nodes, the relative constraints of them must be updated.
     * 
     * @param target The moved node.
     * @param newPosition The position {@code target} is moved to.
     * @param newLayerNodes Nodes of the layer {@code target} is moved to.
     * @param oldLayerNodes Nodes of the layer {@code target} was original in.
     */
    def reevaluateRelativeConstraints(KNode target, int newPosition, List<KNode> newLayerNodes, List<KNode> oldLayerNodes) {
        val chainNodes = InteractiveUtil.getChain(target, oldLayerNodes)
        var startOfChain = chainNodes.get(0)
        var endOfChain = chainNodes.get(chainNodes.size - 1)
        var position = newPosition
        // Determine newPosition of new successor.
        if (newLayerNodes.contains(target)) {
            val oldPos = target.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)
            if (newPosition > oldPos) {
                position++
            }
        }
        
        if (position > 0 && position < newLayerNodes.size) {
            // Update relative constraint of the new predecessor and successor.
            val predecessor = newLayerNodes.get(position - 1)
            val successor = newLayerNodes.get(position)
            if (predecessor.getProperty(predecessorProperty) !== null) {
                changedNodes.add(new ConstraintProperty(predecessor, predecessorProperty, startOfChain.labels.get(0).text))
            }
            if (successor.getProperty(successorProperty) !== null) {
                changedNodes.add(new ConstraintProperty(successor, successorProperty, endOfChain.labels.get(0).text))
            }            
        }
    }
    
    /**
     * Updates relative constraints that have the moved node as target and of the target.
     * 
     * @param target The moved node.
     * @param newPosition The position {@code target} is moved to.
     * @param newLayerNodes Nodes of the layer {@code target} is moved to.
     * @param oldLayerNodes Nodes of the layer {@code target} was original in.
     * @param property The property to check.
     */
    def checkRelativeConstraints(KNode target, int newPosition, List<KNode> newLayerNodes, List<KNode> oldLayerNodes,
        IProperty<String> property
    ) {
        val oldPosition = target.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)
        var forbidden = false
        // Delete relative constraints that are overwritten by new relative constraint of the target.
        switch(property) {
            case predecessorProperty: {
                if (oldPosition + 1 < oldLayerNodes.size) {
                    changedNodes.add(new ConstraintProperty(oldLayerNodes.get(oldPosition + 1), successorProperty, null))
                }
                // If the chains can not be merged, delete old relative constraint and only merge moved node with chain.
                val chain = InteractiveUtil.getChain(newLayerNodes.get(newPosition), newLayerNodes)
                forbidden = InteractiveUtil.isMergeImpossible(InteractiveUtil.getChain(target, oldLayerNodes), chain)
                if (forbidden) {
                    if (oldPosition - 1 >= 0) {
                        changedNodes.add(new ConstraintProperty(oldLayerNodes.get(oldPosition - 1), predecessorProperty, null))
                    }
                    changedNodes.add(new ConstraintProperty(target, successorProperty, null))
                }
            }
            case successorProperty: {
                if (oldPosition - 1 >= 0) {
                    changedNodes.add(new ConstraintProperty(oldLayerNodes.get(oldPosition - 1), predecessorProperty, null))
                }
                // If the chains can not be merged, delete old relative constraint and only merge moved node with chain.
                val chain = InteractiveUtil.getChain(newLayerNodes.get(newPosition - 1), newLayerNodes)
                forbidden = InteractiveUtil.isMergeImpossible(InteractiveUtil.getChain(target, oldLayerNodes), chain)
                if (forbidden) {
                    if (oldPosition + 1 < oldLayerNodes.size) {
                        changedNodes.add(new ConstraintProperty(oldLayerNodes.get(oldPosition + 1), successorProperty, null))
                    }
                    changedNodes.add(new ConstraintProperty(target, predecessorProperty, null))
                }
            }
        }
    }
    
    /**
     * Reevaluates relative constraints after a node is moved within its chain.
     * 
     * @param target The moved node.
     * @param layerNodes The nodes that are in the same layer as {@code target}.
     */
    def reevaluateRelativeConstraintAfterSwapInChain(KNode target, List<KNode> layerNodes) {
        // Remove relative constraint of target.
        changedNodes.add(new ConstraintProperty(target, predecessorProperty, null))
        changedNodes.add(new ConstraintProperty(target, successorProperty, null))
        // Must be done in order for correct calculation of the chain in later reevaluation.
        target.setProperty(predecessorProperty, null)
        target.setProperty(successorProperty, null)
        
        // Remove relative constraint of predecessor and successor that could have the moved node as target.
        val oldPosition = target.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)
        if (oldPosition - 1 >= 0) {
            val oldPredecessor = layerNodes.get(oldPosition - 1)
            oldPredecessor.setProperty(predecessorProperty, null)
            changedNodes.add(new ConstraintProperty(oldPredecessor, predecessorProperty, null))
        }
        if (oldPosition + 1 < layerNodes.size) {
            val oldSuccessor = layerNodes.get(oldPosition + 1)
            changedNodes.add(new ConstraintProperty(oldSuccessor, successorProperty, null))
            oldSuccessor.setProperty(successorProperty, null)
        }
    }
    
}