/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2020 by
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
package de.cau.cs.kieler.klighd.lsp.interactive

import de.cau.cs.kieler.klighd.kgraph.KNode
import java.util.ArrayList
import java.util.List
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.elk.alg.rectpacking.options.RectPackingOptions
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.properties.IProperty

/**
 * Provides utility methods for interactive layout.
 *
 * @author sdo
 */
class InteractiveUtil {

    /**
     * Calculates the nodes that are in the layer based on the layer ID. The nodes are sorted by their pos id. 
     * @param layer the layer which containing nodes should be calculated
     * @param nodes all nodes the graph contains
     * @return sorted list of nodes in the specified layer
     */
    def static List<KNode> getNodesOfLayer(int layer, List<KNode> nodes) {

        var ArrayList<KNode> sortedNodes = newArrayList()

        // layer <= maxLayerId: Collect all nodes with the fitting layer id in a list
        var nodeCount = 0

        for (n : nodes) {
            val layerID = n.getProperty(LayeredOptions.LAYERING_LAYER_ID)
            if (layerID === layer) {
                sortedNodes.add(n)
                nodeCount++
            }
        }

        // sort them based on their position id - this is used for speeding up future reevaluation
        sortedNodes.sort([ a, b |
            a.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID) -
                b.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_ID)
        ])

        return sortedNodes
    }

    /**
     * Copies an arbitrary IProperty of a KNode to an ElkNode if the value on the KNode 
     * is different to the value on the ElkNode.
     * If the new value on the KNode was the default value of the property 
     * then the property is set to null on the ElkNode.
     * @param elkNode The target ElkNode
     * @param kNode The source KNode of the property
     * @param prop Determines which IProperty should be copied
     */
    static def <T> copyConstraintProp(ElkNode elkNode, KNode kNode, IProperty<T> prop) {
        val kNodeValue = kNode.getProperty(prop)

        if (elkNode.getProperty(prop) !== kNodeValue) {
            if (kNodeValue === prop.^default) {
                elkNode.setProperty(prop, null)
            } else {
                elkNode.setProperty(prop, kNodeValue)
            }
        }
    }

    /**
     * Copies constraint properties depending on the algorithm from kNode to elkNode 
     * by using {@code copyConstraintProp()}.
     * 
     * @param elkNode The target ElkNode
     * @param kNode The source KNode of the property
     */
    static def copyAllConstraints(ElkNode elkNode, KNode kNode) {
        val algorithm = kNode.parent.getProperty(CoreOptions.ALGORITHM)
        var List<IProperty<?>> props = #[]
        if(algorithm === null || algorithm == 'layered') {
            props = #[
                LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT,
                LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT
            ]
        } else if ("rectpacking".equals(algorithm)) {
            props = #[
                RectPackingOptions.DESIRED_POSITION,
                RectPackingOptions.ASPECT_RATIO
            ]
        }
        for (prop : props) {
            copyConstraintProp(elkNode, kNode, prop)
        }
    }

    /**
     * Determines the root of the given node.
     * 
     * @param node ElkNode, which root should be returned.
     * @return The root node of a node. If the node itself has no parent the original node is returned.
     */
    static def getRootNodeOf(ElkNode node) {
        var ElkNode parent = node
        while (parent.parent !== null) {
            parent = parent.parent
        }
        return parent
    }
}