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

import java.net.URLDecoder
import org.eclipse.emf.common.util.URI
import com.google.inject.Injector
import org.eclipse.xtext.resource.XtextResourceSet
import de.cau.cs.kieler.klighd.kgraph.KNode
import org.eclipse.elk.alg.layered.options.LayeredOptions
import java.util.List
import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.properties.IProperty
import java.util.ArrayList
import java.util.LinkedList

/**
 * Provides a set of utility methods that is used in the constraints package.
 * 
 * @author jet, cos
 * 
 */
class ConstraintsUtils {

    /**
     * Returns the Resource that is specified by a given uri.
     * @param uri The uri that points to the resource.
     * @param injector The injector that is used for getting the resource.
     * @return The Resource
     */
    def static getResourceFromUri(String uri, Injector injector) {

        // The XTextResourceSet is only able to find the file if the prefix file:// is cut away.
        var fileUri = URLDecoder.decode(uri, "UTF-8");
        if (fileUri.startsWith("file://")) {
            fileUri = fileUri.substring(7)
        }

        return injector.getInstance(XtextResourceSet).getResource(URI.createFileURI(fileUri), true)
    }

    /**
     * Calculates the nodes that are in the layer based on the layer ID. The nodes are sorted by their pos id. 
     * @param layer the layer which containing nodes should be calculated
     * @param nodes all nodes the graph contains
     */
    def static List<KNode> getNodesOfLayer(int layer, List<KNode> nodes) {

        var ArrayList<KNode> temp = newArrayList()

        // layer <= maxLayerId: Collect all nodes with the fitting layer id in a list
        var nodeCount = 0

        for (n : nodes) {
            val layerID = n.getProperty(LayeredOptions.LAYERING_LAYER_I_D)
            if (layerID === layer) {
                temp.add(n)
                nodeCount++
            }
        }

        // sort them based on their position id - this is used for speeding up future reevaluation
        temp.sort([ a, b |
            a.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_I_D) -
                b.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_I_D)
        ])

        return temp
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
     * Copies all constraint properties (two at the moment) from kNode to elkNode 
     * by using {@code copyConstraintProp()}.
     * 
     * @param elkNode The target ElkNode
     * @param kNode The source KNode of the property
     */
    static def copyAllConstraints(ElkNode elkNode, KNode kNode) {
        val props = #[
            LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT,
            LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT
        ]
        for (prop : props) {
            copyConstraintProp(elkNode, kNode, prop)
        }

    }

    static def getRootNodeOf(ElkNode node) {
        var ElkNode parent = node
        while (parent.parent !== null) {
            parent = parent.parent
        }
        return parent
    }

    static def getRootNodeOf(KNode node) {
        var KNode parent = node
        while (parent.parent !== null) {
            parent = parent.parent
        }
        return parent
    }

    /**
     * Collects the adjacent nodes of {@code node} in a list.
     * @param node The node of which you want to know the adjacent nodes.
     * @return 
     */
    static def getAdjacentNodes(KNode node) {
        val inEdges = node.incomingEdges
        val outEdges = node.outgoingEdges
        var List<KNode> adjacentNodes = newArrayList()

        for (e : inEdges) {
            adjacentNodes.add(e.source)
        }
        for (e : outEdges) {
            adjacentNodes.add(e.target)
        }
        return adjacentNodes
    }

    /*
     * Several quality of life methods for faster retrieval and setting of constraints 
     * without typing the IProperty enums again and again
     */
    /**
     * Returns the value of the position constraint that is set on the node.
     * @param node the instance of KNode of which you want the constraint value
     */
    def static getPosConstraint(KNode node) {
        return node.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT)
    }

    /**
     * Returns the value of the layer constraint that is set on the node.
     * @param node the instance of KNode of which you want the constraint value
     */
    def static getLayerConstraint(KNode node) {
        return node.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT)
    }

    /**
     * Sets the value of the position constraint that is set on the node.
     * @param node the instance of KNode of which you want the constraint value
     */
    def static setPosConstraint(KNode node, int pos) {
        node.setProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, pos)
    }

    /**
     * Sets the value of the layer constraint that is set on the node.
     * @param node the instance of KNode that should get the constraint.
     * @param layer the value for the layer constraint
     */
    def static setLayerConstraint(KNode node, int layer) {
        node.setProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, layer)
    }

    /**
     * Sets the value of the layer constraint to null.
     * This procedure effectively deletes the constraint from the node.
     * @param node the instance of KNode of which the layer constraint is set to null
     */
    def static nullifyLayerConstraint(KNode node) {
        node.setProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT, null)
    }

    /**
     * Sets the value of the position constraint to null.
     * This procedure effectively deletes the constraint from the node.
     * @param node the instance of KNode of which the position constraint is set to null.
     */
    def static nullifyPosConstraint(KNode node) {
        node.setProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, null)

    }
}
