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
 * This code is provided under the terms of the Eclipse Public License (EPL).
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
//import de.cau.cs.kieler.annotations.Annotatable
//import de.cau.cs.kieler.annotations.TypedStringAnnotation
//import de.cau.cs.kieler.annotations.AnnotationsFactory

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
     * Copies an arbitrary IProperty of a KNode to a State if the value on the KNode 
     * is different to the value on the State.
     * If the new value on the KNode was the default value of the property 
     * then the property is set to null on the State.
     * @param state The target sate
     * @param kNode The source KNode of the property
     * @param annotation The annotation that should be set
     * @param prop Determines which IProperty should be copied
     */
     // FIXME
//    static def <T> copyConstraintAnnotations(Annotatable state, KNode kNode, String annotation, IProperty<T> prop) {
//        val String value = "" + kNode.getProperty(prop)
//        
//        val anns = state.getAnnotations().filter(TypedStringAnnotation)
//
//        // remove old annotation if it exists
//        var TypedStringAnnotation removeA = null
//        for (ann : anns) {
//            if (ann.type.equals(annotation)) {
//                removeA = ann
//            }
//        }
//        if (removeA !== null) {
//            state.annotations.remove(removeA)
//        }
//        
//        // add annotation with new value if the value is not the default one
//        if (kNode.getProperty(prop) !== null && !kNode.getProperty(prop).equals(prop.^default)) {
//            var newA = AnnotationsFactory::eINSTANCE.createTypedStringAnnotation => [
//                it.name = "layout"
//                it.type = annotation
//                it.values += value
//            ]
//            state.annotations.add(newA)    
//        }
//        
//    }

    /**
     * Copies constraint properties depending on the algorithm from kNode to elkNode 
     * by using {@code copyConstraintProp()}.
     * 
     * @param elkNode The target ElkNode
     * @param kNode The source KNode of the property
     */
    static def copyAllConstraints(Object node, KNode kNode) {
        val algorithm = kNode.parent.getProperty(CoreOptions.ALGORITHM)
        
        var List<IProperty<?>> props = #[]
        var List<String> annos = #[]
        if(algorithm === null || algorithm == 'layered' || algorithm == 'org.eclipse.elk.layered') {
            props = #[
                LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT,
                LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT,
                LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_PRED_OF,
                LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_SUCC_OF
            ]
            annos = #[
                "layering.layerChoiceConstraint",
                "crossingMinimization.positionChoiceConstraint",
                "crossingMinimization.inLayerPredOf",
                "crossingMinimization.inLayerSuccOf"
            ]
        } else if ("rectpacking".equals(algorithm)) {
            props = #[
                RectPackingOptions.DESIRED_POSITION,
                RectPackingOptions.ASPECT_RATIO
            ]
            annos = #[
                "desiredPosition",
                "aspectRatio"
            ]
        }
            
        if (node instanceof ElkNode) {
            for (prop : props) {
                copyConstraintProp(node, kNode, prop)
            }
        }
        // FIXME 
//        else if (node instanceof Annotatable) {
//            for (var i = 0; i< annos.size; i++) {
//                copyConstraintAnnotations(node, kNode, annos.get(i), props.get(i))
//            }
//        }
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
    
    /**
     * Determines the nodes that are connected to {@code node} by relative constraints.
     * The returned list of nodes is sorted based on the position of the nodes.
     * 
     * @param node One node of the chain
     * @param layerNodes Nodes that are in the same layer as {@code node}
     */
    static def getChain(KNode node, List<KNode> layerNodes) {
        var pos = layerNodes.indexOf(node)
        var chainNodes = new ArrayList<KNode>();
        chainNodes.add(node)
        
        // from node to the start
        for (var i = pos - 1; i >= 0; i--) {
            if (layerNodes.get(i).getProperty(LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_PRED_OF) !== null
                || layerNodes.get(i + 1).getProperty(LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_SUCC_OF) !== null) {
                    chainNodes.add(0, layerNodes.get(i))
            } else {
                i = -1
            }
        }
        
        // count from node to the end
        for (var i = pos + 1; i < layerNodes.size; i++) {
            if (layerNodes.get(i).getProperty(LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_SUCC_OF) !== null
                || layerNodes.get(i - 1).getProperty(LayeredOptions.CROSSING_MINIMIZATION_IN_LAYER_PRED_OF) !== null) {
                    chainNodes.add(layerNodes.get(i))
            } else {
                i = layerNodes.size
            }
        }
        
        return chainNodes
    }
    
        
    /**
     * Checks whether the nodes of {@code chain1} and the nodes {@code chain2} can be merged to one chain
     * 
     * @param chain1 One of the two chains.
     * @param chain2 Other one of the two chains.
     */
    static def isMergeImpossible(List<KNode> chain1, List<KNode> chain2) {
        var connectedNodes = new ArrayList<KNode>()
        for (n : chain1) {
            var edges = n.outgoingEdges
            for (e : edges) {
                connectedNodes.add(e.target)
            }
            edges = n.incomingEdges
            for (e : edges) {
                connectedNodes.add(e.source)
            }
        }
        
        for (n : connectedNodes) {
            if (chain2.contains(n)) {
                return true
            }
        }
        
        return false
    }
    
}