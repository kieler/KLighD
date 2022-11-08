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

import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KNode
import java.util.ArrayList
import java.util.Comparator
import java.util.List
import java.util.stream.Collectors
import org.eclipse.elk.alg.mrtree.options.MrTreeOptions
import org.eclipse.elk.core.math.KVector
import org.eclipse.elk.core.options.Direction

/**
 * Provides utility methods for the interactive mrtree algorithm.
 * 
 * @author sdo
 *
 */
class MrTreeInteractiveUtil {
     /**
     * Sets the required options for the non interactive layout run.
     */
    static def void setRequiredInteractiveOptions(KNode root) {
        // TODO: Implement if needed
    }
    
    static def List<KNode> getSiblings(KNode n, KNode parent) {
        val lowestParent = getLowestParent(n, parent);
        if (lowestParent === null)
            return new ArrayList<KNode>();
        val siblings = parent.children.stream.
            filter[x | lowestParent == getLowestParent(x, parent)].collect(Collectors.toList)
        return siblings
    }
    
    static def KNode getLowestParent(KNode n, KNode parent) {
        val dirVec = getDirectionVector(parent);
        if (n.incomingEdges.size == 0)
            return null
        val sources = n.incomingEdges.stream().map[x | x.source].collect(Collectors.toList)
        val parents = parent.children.stream.filter[x | sources.contains(x)].collect(Collectors.toList)
        
        if (parents.size < 1)
            return null
        else if (parents.size == 1)
            return parents.get(0)
        else {
            val lowestParentPos = parents.stream.
                map[x | new KVector(x.xpos + x.width / 2, x.ypos + x.height / 2).dotProduct(dirVec)].
                max(Comparator.naturalOrder).get
            val lowestParent = parents.stream.
                filter[x | new KVector(x.xpos + x.width / 2, x.ypos + x.height / 2).dotProduct(dirVec) == lowestParentPos].
                findFirst.get
                
            return lowestParent;
        }
    }
    
    /**
     * Gets a KVector which faces in the direction of the layout direction of parent
     * @param parent the parent node of the graph
     * @return the KVector
     */
    static def KVector getDirectionVector(KNode parent) {
        val direction = parent.getProperty(MrTreeOptions.DIRECTION)
        if (direction == Direction.LEFT)
            return new KVector(-1, 0)
        else if (direction == Direction.RIGHT)
            return new KVector(1, 0)
        else if (direction == Direction.UP)
            return new KVector(0, -1)
        else
            return new KVector(0, 1)
    }
    
    /**
     * Get the child nodes of n within a tree.
     * @param n the n
     * @return the child nodes of n within a tree
     */
    static def List<KNode> getChildren(KNode n) {
        var re = new ArrayList<KNode>();
        for (KEdge out : n.getOutgoingEdges()) {
            re.add(out.getTarget());
        }
        return re.stream().distinct().collect(Collectors.toList());
    }
}