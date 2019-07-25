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
     * Calculates the nodes that are in the layer based on the layer ID. The  nodes receive a list position 
     * respecting their position in the layer. If the pos constraint is set it is used as the position of the node 
     * else the position id is used.
     * After the interactive layout the pos constraint is equal to the position id. 
     * @param layer the layer which containing nodes should be calculated
     * @param nodes all nodes the graph contains
     */
    def static getNodesOfLayer(int layer, List<KNode> nodes) {
        val maxPos = maxActualPositionInLayer(nodes, layer)
        
        var KNode[] nodesOfLayer = newArrayOfSize(maxPos)

        for (node : nodes) {
            if (node.getProperty(LayeredOptions.LAYERING_LAYER_I_D) === layer) {
                var pos = ConstraintsUtils.getPosConstraint(node)

                if (pos === -1) {
                    pos = node.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_I_D)
                }

            }
        }
        return nodesOfLayer
    }

    /**
     * The actual layer of a KNode is the layer where it will end up after the interactive layout.
     * The actual layer of a KNode is determined by the layer constraint value.
     * If there is no layer constraint value the actual layer of a KNode is its layer id.
     * After the interactive layout the layer id is equals to the layer constraint value.
     * @param node the node of which you want to know the actual layer
     */
    def static actualLayer(KNode node) {

        val layerCandidate = ConstraintsUtils.getLayerConstraint(node)
        if (layerCandidate == -1) {
            return node.getProperty(LayeredOptions.LAYERING_LAYER_I_D)
        } else {
            return layerCandidate
        }

    }

    /**
     * The actual position of a KNode is the position where it will end up after the interactive layout.
     * The actual position of a KNode is determined by the position constraint value.
     * If there is no position constraint value the actual position of a KNode is its position id.
     * After the interactive layout the position id is equals to the position constraint value.
     * @param node the node of which you want to know the actual position
     */
    def static actualPos(KNode node) {
        val posCandidate = ConstraintsUtils.getPosConstraint(node)
        if (posCandidate == -1) {
            return node.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_I_D)
        } else {
            return posCandidate
        }
    }

    /**
     * Searches the maximal actual layer in a list of KNodes.
     * @param nodes All nodes that should be included in this search.
     */
    def static maxActualLayer(List<KNode> nodes) {
        var maxLayer = 0
        for (n : nodes) {

            // The method is called before the layout has been performed
            // This means a layer id does not need to be equal to the layer constraint
            var actualLayer = actualLayer(n)
            if (actualLayer == -1) {
                actualLayer = n.getProperty(LayeredOptions.LAYERING_LAYER_I_D)
            }

            if (actualLayer > maxLayer) {
                maxLayer = actualLayer
            }
        }
        return maxLayer
    }
/**
 * Searches the maximal position in the layer denoted by {@code layer} in a list of KNodes.
 * Returns -1 if there is no node in the layer or if the list of nodes is empty.
 * @param nodes List of KNodes in which the method should search
 * @param layer The layer index of the layer of which you want the maximal position
 * @return the maximal position in the layer
 */
    def static maxActualPositionInLayer(List<KNode> nodes, int layer) {
        var maxPosInLayer = -1
        for (n : nodes) {
            if (actualLayer(n) === layer) {
                val posCandidate = actualPos(n)
                if (posCandidate > maxPosInLayer) {
                    maxPosInLayer = posCandidate
                }
            }
        }
        return maxPosInLayer
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
     * Creates a List of layer lists that models the actual layering of a graph that is coined 
     * by layer constraints or layer ids if a node has no layer constraint.
     * It expects that there are no constraints on the nodes that cause flat edges since
     * these alter the actual layer of a node in the interactive layout 
     * process because ElkLayered does not allow flat edges.
     * 
     * @param nodes All nodes that should be included in the layering. 
     * Requirement: The nodes must not have constraints that cause flat edges.
     */
    def static getLayering(List<KNode> nodes) {
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
