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
    
    IProperty<String> predProp = LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_PRED_OF
    IProperty<String> succProp = LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_SUCC_OF
    
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
     * @param target The moved node
     * @param newPos The position {@code target} is moved to
     * @param newLayerNodes Nodes of the layer {@code target} is moved to
     * @param oldLayerNodes Nodes of the layer {@code target} was original in
     */
    def reevaluateRelCons(KNode target, int newPos, List<KNode> newLayerNodes, List<KNode> oldLayerNodes) {
        val chainNodes = InteractiveUtil.getChain(target, oldLayerNodes)
        var startOfChain = chainNodes.get(0)
        var endOfChain = chainNodes.get(chainNodes.size - 1)
        var pos = newPos
        // determine pos of new successor
        if (newLayerNodes.contains(target)) {
            val oldPos = target.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)
            if (newPos > oldPos) {
                pos++
            }
        }
        
        if (pos > 0 && pos < newLayerNodes.size) {
            // update rel cons of the new pred and succ
            val predNode = newLayerNodes.get(pos - 1)
            val succNode = newLayerNodes.get(pos)
            if (predNode.getProperty(predProp) !== null) {
                changedNodes.add(new ConstraintProperty(predNode, predProp, startOfChain.labels.get(0).text))
            }
            if (succNode.getProperty(succProp) !== null) {
                changedNodes.add(new ConstraintProperty(succNode, succProp, endOfChain.labels.get(0).text))
            }            
        }
    }
    
    /**
     * Updates relative constraints that have the moved node as target and of the target.
     * 
     * @param target The moved node
     * @param newPos The position {@code target} is moved to
     * @param newLayerNodes Nodes of the layer {@code target} is moved to
     * @param oldLayerNodes Nodes of the layer {@code target} was original in
     */
    def checkRelCons(KNode target, int newPos, List<KNode> newLayerNodes, List<KNode> oldLayerNodes, IProperty<String> prop) {
        val oldPos = target.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)
        var forbidden = false
        // delete relative constraints that are overwritten by new rel cons of the target
        switch(prop) {
            case predProp: {
                if (oldPos + 1 < oldLayerNodes.size) {
                    changedNodes.add(new ConstraintProperty(oldLayerNodes.get(oldPos + 1), succProp, null))
                }
                // if the chains can not be merged, delete old rel cons and only merge moved node with chain
                val chain = InteractiveUtil.getChain(newLayerNodes.get(newPos), newLayerNodes)
                forbidden = InteractiveUtil.isMergeImpossible(InteractiveUtil.getChain(target, oldLayerNodes), chain)
                if (forbidden) {
                    if (oldPos - 1 >= 0) {
                        changedNodes.add(new ConstraintProperty(oldLayerNodes.get(oldPos - 1), predProp, null))
                    }
                    changedNodes.add(new ConstraintProperty(target, succProp, null))
                }
            }
            case succProp: {
                if (oldPos - 1 >= 0) {
                    changedNodes.add(new ConstraintProperty(oldLayerNodes.get(oldPos - 1), predProp, null))
                }
                // if the chains can not be merged, delete old rel cons and only merge moved node with chain
                val chain = InteractiveUtil.getChain(newLayerNodes.get(newPos - 1), newLayerNodes)
                forbidden = InteractiveUtil.isMergeImpossible(InteractiveUtil.getChain(target, oldLayerNodes), chain)
                if (forbidden) {
                    if (oldPos + 1 < oldLayerNodes.size) {
                        changedNodes.add(new ConstraintProperty(oldLayerNodes.get(oldPos + 1), succProp, null))
                    }
                    changedNodes.add(new ConstraintProperty(target, predProp, null))
                }
            }
        }
    }
    
    /**
     * Reevaluates relative constraints after a node is moved within its chain.
     * @param target The moved node
     * @param layerNodes The nodes that are in the same layer as {@code target}
     */
    def reevaluateRCAfterSwapInChain(KNode target, List<KNode> layerNodes) {
        // remove rel cons of target
        changedNodes.add(new ConstraintProperty(target, predProp, null))
        changedNodes.add(new ConstraintProperty(target, succProp, null))
        // must be done in order for correct calculation of the chain in later reevaluation
        target.setProperty(predProp, null)
        target.setProperty(succProp, null)
        
        // remove rel cons of pred and succ that could have the moved node as target
        val oldPos = target.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)
        if (oldPos - 1 >= 0) {
            val oldPred = layerNodes.get(oldPos - 1)
            oldPred.setProperty(predProp, null)
            changedNodes.add(new ConstraintProperty(oldPred, predProp, null))
        }
        if (oldPos + 1 < layerNodes.size) {
            val oldSucc = layerNodes.get(oldPos + 1)
            changedNodes.add(new ConstraintProperty(oldSucc, succProp, null))
            oldSucc.setProperty(succProp, null)
        }
    }
    
}