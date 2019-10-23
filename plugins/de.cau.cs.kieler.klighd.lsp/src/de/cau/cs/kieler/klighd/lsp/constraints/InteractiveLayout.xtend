/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.constraints

import com.google.inject.Inject
import com.google.inject.Singleton
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.KGraphLayoutEngine
import java.util.ArrayList
import java.util.List
import org.eclipse.elk.alg.layered.options.CrossingMinimizationStrategy
import org.eclipse.elk.alg.layered.options.CycleBreakingStrategy
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.elk.alg.layered.options.LayeringStrategy
import org.eclipse.elk.core.math.KVector
import org.eclipse.emf.common.util.EList

/**
 * @author jet, cos
 * 
 */
@Singleton
class InteractiveLayout {

    @Inject
    private KGraphDiagramState diagramState

    /**
     * Calculates the layout for graphs that contain constraints.
     * 
     * @param id The identifier used in the SGraph model generation and that is used to store diagram generation
     * relevant data in the {@link KGraphDiagramState}.
     * @param layoutE The KGraphLayoutEngine
     */
    public def calcLayout(String id, KGraphLayoutEngine layoutE) {
        var ViewContext viewContext = null
        synchronized (diagramState) {
            viewContext = diagramState.getKGraphContext(id)
        }

        val root = viewContext.viewModel
        if (root.getProperty(LayeredOptions.INTERACTIVE_LAYOUT)) {

            root.setProperty(LayeredOptions.SEPARATE_CONNECTED_COMPONENTS, false)
            setStandardStrats(root)

            prepareParentsForFirstLayout(root)
            // initial layout
            layoutE.onlyLayoutOnKGraph(id)
            // set coordinates
            setCoordinatesDepthFirst(root)
            // activate interactive strategies
            setInteractiveStrats(root)

            layoutE.onlyLayoutOnKGraph(id)

        }
    }

    /**
     * Sets the coordinates of the nodes in the graph {@code root} according to the set constraints.
     * 
     * @param root The root of the graph that should be layoutet.
     */
    private def static setCoordinates(KNode root) {
        var layers = calcLayerNodes(root.children)
        setXCoordinates(layers)
        for (layer : layers) {
            if (layer.size > 0) {
                setYCoordinates(layer)
            }
        }
    }

    /**
     * Calculates the layers the {@code nodes} belong to.
     * 
     *  @param nodes The nodes of the graph for which the layers should be calculated.
     */
    private def static calcLayerNodes(EList<KNode> nodes) {
        var allNs = newArrayList()
        var propNs = newArrayList()
        // copy all nodes in another list
        // save the nodes which layer constraint are set in a separate list
        for (node : nodes) {
            allNs.add(node)
            if (node.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) !== -1) {
                propNs.add(node)
            }
        }

        // calculate layers for nodes without constraints
        var layerNodes = initialLayers(allNs)

        // add the nodes with constraints
        addPropNodes(propNs, layerNodes)

        return layerNodes
    }

    /**
     * Adds the nodes in {@code propNs} to {@code layerNodes} based on their layer constraint.
     * 
     * @param propNs Nodes with set layer constraint that should be added to the layers.
     * @param layerNodes List that contains the layers with their corresponding nodes.
     */
    private def static addPropNodes(ArrayList<KNode> propNs, ArrayList<ArrayList<KNode>> layerNodes) {
        // sorting based on layer the node should be in
        propNs.sort(
            [ a, b |
                a.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) -
                    b.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT)
            ]
        )

        // add the nodes with constraints in the correct layer
        var diff = 0
        for (node : propNs) {
            var layer = node.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) - diff;
            if (layer < layerNodes.size) {
                var nodesOfLayer = layerNodes.get(layer)
                // edges in the same layer are not allowed
                shiftOtherNs(node, layer, layerNodes, true)
                shiftOtherNs(node, layer, layerNodes, false)
                nodesOfLayer.add(node)
            } else {
                // diff keeps track of the difference between the layer the node should 
                // be in and the layer the node is really in.
                // In this way nodes with the same layer constraint go in Fthe same layer 
                // although it may not be the layer that is specified by the constraint
                diff = diff + layer - layerNodes.size
                var list = newArrayList()
                list.add(node)
                layerNodes.add(list)
            }
        }
    }

    /**
     * Shifts nodes to the right such that edges in the same layer do not exist.
     * 
     * @param movedNode The node which connected nodes must be shifted .
     * @param layer The layer {@code moveNode} is in.
     * @param layerNodes All existing layers with the containing nodes.
     * @param incoming Determines if incoming or outgoing edges should be considered. True: incoming edges.
     */
    private def static void shiftOtherNs(KNode movedNode, int layer, ArrayList<ArrayList<KNode>> layerNodes,
        boolean incoming) {
        var nodesOfLayer = layerNodes.get(layer)
        // get edges
        var EList<KEdge> edges = null
        if (incoming) {
            edges = movedNode.incomingEdges
        } else {
            edges = movedNode.outgoingEdges
        }

        for (edge : edges) {
            // get connected node
            var KNode node = null
            if (incoming) {
                node = edge.source
            } else {
                node = edge.target
            }
            // shift node to the next layer
            if (nodesOfLayer.contains(node)) {
                nodesOfLayer.remove(node)
                var ArrayList<KNode> newLayer
                if (layer + 1 < layerNodes.size) {
                    newLayer = layerNodes.get(layer + 1)
                    newLayer.add(node)
                    // the connected nodes in the layer the node is shifted to must be shifted too
                    shiftOtherNs(node, layer + 1, layerNodes, false)
                    shiftOtherNs(node, layer + 1, layerNodes, true)
                } else {
                    newLayer = newArrayList()
                    newLayer.add(node)
                    layerNodes.add(newLayer)
                }
            }
        }
    }

    /**
     * Sorts the {@code nodes} in layers based on their layerID.
     * 
     * @param nodes The nodes of the graph which layers should be calculated.
     */
    private def static initialLayers(ArrayList<KNode> nodes) {
        // sorting based on layer ID position
        nodes.sort([ a, b |
            a.getProperty(LayeredOptions.LAYERING_LAYER_I_D) - b.getProperty(LayeredOptions.LAYERING_LAYER_I_D)
        ])

        var layerNodes = newArrayList()
        var nodesOfLayer = newArrayList()
        var currentL = -1
        // assign the nodes to layers
        for (node : nodes) {
            var layer = node.getProperty(LayeredOptions.LAYERING_LAYER_I_D)
            if (layer > currentL) {
                // node is in a new layer
                layerNodes.add(nodesOfLayer)
                nodesOfLayer = newArrayList()
                currentL = layer
            }

            if (node.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) === -1) {
                // nodes with layer constraint should be ignored
                nodesOfLayer.add(node)
            }
        }
        if (!nodesOfLayer.isEmpty) {
            // add the last layer
            layerNodes.add(nodesOfLayer)
        }
        // the first node is added to the second layer
        // thats why the first layer must be removed
        layerNodes.remove(0)
        return layerNodes
    }

    /**
     * Sets the x coordinates of the nodes in {@code layers} according to their layer.
     *  
     * @param layers The layers containing the associated nodes.
     */
    private def static setXCoordinates(ArrayList<ArrayList<KNode>> layers) {
        var float xPos = 0
        var float nextX = 0
        // nodes in the same layer get the same x coordinate
        for (nodesOfLayer : layers) {
            for (node : nodesOfLayer) {
                node.xpos = xPos
                if (xPos + node.width >= nextX) {
                    nextX = xPos + node.width
                }
            }
            // nodes in different layer should not overlap horizontally 
            xPos = nextX + 1
        }

        // adjust the width of the parent of the nodes in the layers
        if (!layers.empty && !layers.get(0).empty) {
            var node = layers.get(0).get(0)
            var padding = node.getProperty(LayeredOptions.PADDING)
            node.parent.width = (padding.left + padding.right + xPos - node.xpos +
                node.getProperty(LayeredOptions.SPACING_NODE_NODE_BETWEEN_LAYERS) * (layers.length - 1)
                        ) as float
        }
    }

    /**
     * Sets the positions of the nodes in {@code nodesOfLayer}.
     * 
     * @param nodesOfLayer The list containing nodes that are in the same layer. 
     */
    private def static setYCoordinates(List<KNode> nodesOfLayer) {

        // separate node with and without position constraint
        val List<KNode> propNodes = newArrayList()
        val List<KNode> nodes = newArrayList()
        for (node : nodesOfLayer) {
            if (node.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT) !== -1) {
                propNodes.add(node)
            } else {
                nodes.add(node)
            }
        }

        // determine the order of the nodes
        sortListsForYPos(propNodes, nodes)
        // add the nodes with position constraint at the desired position in the nodes list
        for (node : propNodes) {
            var pos = node.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT)
            if (pos < nodes.size) {
                nodes.add(pos, node)
            } else {
                nodes.add(node)
            }
        }

        // set the y positions according to the order of the nodes
        var yPos = nodes.get(0).ypos
        for (node : nodes) {
            node.setProperty(LayeredOptions.POSITION, new KVector(node.xpos, yPos))
            yPos = yPos + node.height + 1
        }
    }

    /**
     * Sorts the {@code propNodes} according their position constraint 
     * and {@code nodes} according to their y coordinate.
     * 
     * @param propNodes The nodes which position constraint is set
     * @param nodes The nodes without position constraints
     */
    private def static sortListsForYPos(List<KNode> propNodes, List<KNode> nodes) {
        // sorting based on position the nodes should have
        propNodes.sort(
            [ a, b |
                a.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT) -
                    b.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT)
            ]
        )
        // sorting based on the y coordinates
        nodes.sort([ a, b |
            if (a.ypos > b.ypos) {
                return 1
            } else if (a.ypos < b.ypos) {
                return -1
            } else {
                return 0
            }
        ])
    }

    /**
     * Sets the (semi) interactive strategies in the phases crossing minimization, layer assignment, 
     * cycle breaking for the graph {@code root}.
     * 
     * @param root The graph which strategies should be set.
     */
    private def static setInteractiveStrats(KNode root) {
        root.setProperty(LayeredOptions.CROSSING_MINIMIZATION_SEMI_INTERACTIVE, true)
        root.setProperty(LayeredOptions.LAYERING_STRATEGY, LayeringStrategy.INTERACTIVE)
        root.setProperty(LayeredOptions.CYCLE_BREAKING_STRATEGY, CycleBreakingStrategy.INTERACTIVE)
    }

    /**
     * Sets the interactive_layout property, deactivates separate connected components, and resets strategies
     * on all children of root, having own children.
     */
    private def static void prepareParentsForFirstLayout(KNode root) {
        for (n : root.children) {
            val nestedNodes = n.children
            if (!nestedNodes.empty) {
                n.setProperty(LayeredOptions.INTERACTIVE_LAYOUT, true)
                n.setProperty(LayeredOptions.SEPARATE_CONNECTED_COMPONENTS, false)
                setStandardStrats(n)
                prepareParentsForFirstLayout(n)
            }
        }
    }

    /**
     * Sets the coordinates of the nodes in the graph, which root is the given node. 
     * 
     * @param root Root of the graph
     */
    private def static void setCoordinatesDepthFirst(KNode root) {
        var empty = true
        for (n : root.children) {
            empty = false
            val nestedNodes = n.children
            if (!nestedNodes.empty) {
                empty = false
                setInteractiveStrats(n)
                setCoordinatesDepthFirst(n)
            }
        }
        if (!empty) {
            setCoordinates(root)
        }

    }

    /**
     * Resets the strategies in the phases crossing minimization, layer assignment, 
     * cycle breaking for the graph {@code root}.
     * 
     * @param root The graph which strategies should be set.
     */
    private def static setStandardStrats(KNode root) {
        root.setProperty(LayeredOptions.CROSSING_MINIMIZATION_STRATEGY, CrossingMinimizationStrategy.LAYER_SWEEP)
        root.setProperty(LayeredOptions.LAYERING_STRATEGY, LayeringStrategy.NETWORK_SIMPLEX)
        root.setProperty(LayeredOptions.CYCLE_BREAKING_STRATEGY, CycleBreakingStrategy.DEPTH_FIRST)
    }

}