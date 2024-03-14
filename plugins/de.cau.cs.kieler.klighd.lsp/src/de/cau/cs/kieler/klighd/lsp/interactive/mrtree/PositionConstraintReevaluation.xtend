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
package de.cau.cs.kieler.klighd.lsp.interactive.mrtree

import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.interactive.ConstraintProperty
import java.util.LinkedList
import org.eclipse.elk.alg.mrtree.options.MrTreeOptions
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Class to reevalute position constraints for MrTree.
 * 
 * @author jnc, sdo
 */
class PositionConstraintReevaluation {
    @Accessors(PUBLIC_GETTER)
    KNode target
    
    @Accessors(PUBLIC_GETTER)
    LinkedList<ConstraintProperty<Object>> changedNodes = newLinkedList()
    
    new(KNode target) {
        this.target = target
    }
    
    /**
     * Adjusts position constraints in a layer after one node has been introduced to it.
     * @param parent the nodes of the layer
     * @param target the moved node
     * @param newPosition the new position of the node
     */
    def reevaluatePositionConstraintsAfterPosChangeInLayer(KNode parent, KNode target, int newPosition) {
        var targetSiblings = MrTreeInteractiveUtil.getSiblings(target, parent)
        
        // There is no point in sorting a list with less than 2 elements.
        if (targetSiblings.size <= 1) {
            return
        }
         
        // Sort list by node positions.
        if (parent.getProperty(MrTreeOptions.DIRECTION).horizontal)
            targetSiblings = targetSiblings.sortBy[ypos]
        else
            targetSiblings = targetSiblings.sortBy[xpos]
        
        // Set target node to its target position.
        val newPositionInBounds = 
            if (newPosition >= targetSiblings.length) targetSiblings.length - 1 
            else newPosition
        if (newPositionInBounds == targetSiblings.indexOf(target)) {
            return
        }
        targetSiblings.remove(target);
        targetSiblings.add(newPositionInBounds, target);
        
        // Set node position constraint to its list index.
        for (var i = 0; i < targetSiblings.length; i++) {
            changedNodes.add(new ConstraintProperty(targetSiblings.get(i), MrTreeOptions.POSITION_CONSTRAINT, i))
        }
    }
}