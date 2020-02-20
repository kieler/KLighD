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
package de.cau.cs.kieler.klighd.lsp.interactive.layered

import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KNode
import java.util.ArrayList
import java.util.List
import org.eclipse.elk.alg.layered.options.CrossingMinimizationStrategy
import org.eclipse.elk.alg.layered.options.CycleBreakingStrategy
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.elk.alg.layered.options.LayeringStrategy
import org.eclipse.elk.core.math.KVector
import org.eclipse.elk.core.options.Direction
import org.eclipse.emf.common.util.EList
import de.cau.cs.kieler.klighd.lsp.interactive.InteractiveLayout

/**
 * @author sdo
 *
 */
class LayeredInteractiveUtil {

    /**
     * Sets the coordinates of the nodes in the graph, which root is the given node. 
     * 
     * @param root Root of the graph
     */
    public static def void setCoordinatesDepthFirst(KNode root) {
        for (n : root.children) {
            if (!n.children.empty) {
                InteractiveLayout.setRequiredInteractiveOptions(n)
            }
        }
        if (!root.children.empty && root.getProperty(LayeredOptions.INTERACTIVE_LAYOUT)) {
            setCoordinates(root)
            setInteractiveStrategies(root)
        }

    }
    
    /**
     * Sets the coordinates of the nodes in the graph {@code root} according to the set constraints.
     * 
     * @param root The root of the graph that should be layouted.
     */
    private static def setCoordinates(KNode root) {
        var layers = calcLayerNodes(root.children)
        val direction = root.getProperty(LayeredOptions.DIRECTION)
        setCoordinateInLayoutDirection(layers, direction)
        for (layer : layers) {
            if (layer.size > 0) {
                setCoordinatesOrthogonalToLayoutDirection(layer, direction)
            }
        }
        return
    }

    /**
     * Calculates the layers the {@code nodes} belong to.
     * 
     *  @param nodes The nodes of the graph for which the layers should be calculated.
     */
    private static def calcLayerNodes(EList<KNode> nodes) {
        var allNodes = newArrayList()
        var nodesWithLayerConstraint = newArrayList()
        // copy all nodes in another list
        // save the nodes which layer constraint are set in a separate list
        for (node : nodes) {
            allNodes.add(node)
            if (node.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) !== -1) {
                nodesWithLayerConstraint.add(node)
            }
        }

        // calculate layers for nodes without constraints
        var layerNodes = initialLayers(allNodes)

        // add the nodes with constraints
        assignLayersToNodesWithProperty(nodesWithLayerConstraint, layerNodes)

        return layerNodes
    }

    /**
     * Adds the nodes in {@code propNs} to {@code layerNodes} based on their layer constraint.
     * 
     * @param propNs Nodes with set layer constraint that should be added to the layers.
     * @param layerNodes List that contains the layers with their corresponding nodes.
     */
    private static def assignLayersToNodesWithProperty(ArrayList<KNode> nodesWithLayerConstraint, ArrayList<ArrayList<KNode>> layering) {
        // sorting based on layer the node should be in
        nodesWithLayerConstraint.sort(
            [ a, b |
                a.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) -
                    b.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT)
            ]
        )

        // add the nodes with constraints in the correct layer
        var diff = 0
        for (node : nodesWithLayerConstraint) {
            var currentLayer = node.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) - diff;
            if (currentLayer < layering.size) {
                var nodesOfLayer = layering.get(currentLayer)
                // edges in the same layer are not allowed
                // TODO overthink whether this is always desired
                shiftOtherNodes(node, currentLayer, layering, true)
                shiftOtherNodes(node, currentLayer, layering, false)
                nodesOfLayer.add(node)
            } else {
                // diff keeps track of the difference between the layer the node should 
                // be in and the layer the node is really in.
                // In this way nodes with the same layer constraint go in the same layer 
                // although it may not be the layer that is specified by the constraint
                diff = diff + currentLayer - layering.size
                layering.add(newArrayList(node))
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
    private static def void shiftOtherNodes(KNode movedNode, int layer, ArrayList<ArrayList<KNode>> layerNodes,
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
                    shiftOtherNodes(node, layer + 1, layerNodes, false)
                    shiftOtherNodes(node, layer + 1, layerNodes, true)
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
    private static def initialLayers(ArrayList<KNode> nodes) {
        // sorting based on layer ID position
        nodes.sort([ a, b |
            a.getProperty(LayeredOptions.LAYERING_LAYER_I_D) - b.getProperty(LayeredOptions.LAYERING_LAYER_I_D)
        ])

        var layerNodes = newArrayList()
        var nodesOfLayer = newArrayList()
        var currentLayer = -1
        // assign the nodes to layers
        for (node : nodes) {
            var layer = node.getProperty(LayeredOptions.LAYERING_LAYER_I_D)
            if (layer > currentLayer) {
                // node is in a new layer
                if (!nodesOfLayer.isEmpty) {
                    layerNodes.add(nodesOfLayer)
                }
                nodesOfLayer = newArrayList()
                currentLayer = layer
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
        return layerNodes
    }

    /**
     * Sets the x coordinates of the nodes in {@code layers} according to their layer.
     * TODO remove magic constants
     * FIXME The problem is that the height and width of a node are determined by the height and width before the layout with constraints.
     *      They are therefore potentially wrong
     *  
     * @param layers The layers containing the associated nodes, already sorted regarding layers
     */
    private static def setCoordinateInLayoutDirection(ArrayList<ArrayList<KNode>> layers, Direction direction) {
        var float position = 0
        var float nextPosition = 0
        // Assign x (RIGHT, LEFT)/y (UP, DOWN) coordinate such that all nodes get a pseudo position that assigns them to 
        // the correct layer when using interactive mode
        for (nodesOfLayer : layers) {
            for (node : nodesOfLayer) {
                switch (direction) {
                    case UNDEFINED, case RIGHT: {
                        node.xpos = position;
                        if (position + node.width / 2 >= nextPosition) {
                            nextPosition = node.xpos + node.width + 100000
                        }
                    }
                    case LEFT:  {
                        node.xpos = position
                        if (node.xpos <= nextPosition) {
                            nextPosition = node.xpos - 100000
                        }
                    }                    
                    case DOWN: {
                        node.ypos = position;
                        if (position + node.height >= nextPosition) {
                            nextPosition = node.ypos + node.height + 100000
                        }
                    }
                    case UP: {
                        node.ypos = position
                        if (node.ypos <= nextPosition) {
                            nextPosition = node.ypos - 100000
                        }
                    }
                }
            }
            position = nextPosition
               
        }
        return
    }

    /**
     * Sets the positions of the nodes in {@code nodesOfLayer}.
     * TODO remove magic constants
     * 
     * @param nodesOfLayer The list containing nodes that are in the same layer. 
     */
    private static def setCoordinatesOrthogonalToLayoutDirection(List<KNode> nodesOfLayer, Direction direction) {
        // separate node with and without position constraint
        val List<KNode> nodesWithPositionConstraint = newArrayList()
        val List<KNode> nodes = newArrayList()
        for (node : nodesOfLayer) {
            if (node.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT) !== -1) {
                nodesWithPositionConstraint.add(node)
            } else {
                nodes.add(node)
            }
        }

        // determine the order of the nodes
        sortNodesInLayer(nodesWithPositionConstraint, nodes, direction)
        // add the nodes with position constraint at the desired position in the nodes list
        for (node : nodesWithPositionConstraint) {
            var pos = node.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT)
            if (pos < nodes.size) {
                nodes.add(pos, node)
            } else {
                nodes.add(node)
            }
        }

        // set the y positions according to the order of the nodes
        switch (direction) {
            case UNDEFINED, case RIGHT, case LEFT: {
                var yPos = nodes.get(0).ypos
                for (node : nodes) {
                    node.setProperty(LayeredOptions.POSITION, new KVector(node.xpos, yPos))
                    yPos = yPos + node.height + 100000
                }
            }                   
            case DOWN, case UP: {
                var xPos = nodes.get(0).xpos
                for (node : nodes) {
                    node.setProperty(LayeredOptions.POSITION, new KVector(xPos, node.ypos))
                    xPos = xPos + node.width + 100000
                }
            }
        }
    }

    /**
     * Sorts the {@code propNodes} according their position constraint 
     * and {@code nodes} according to relevant coordinate (y for left, right, x for up, down).
     * 
     * @param propNodes The nodes which position constraint is set
     * @param nodes The nodes without position constraints
     */
    private static def sortNodesInLayer(List<KNode> nodesWithPositionConstraint, List<KNode> nodes, Direction direction) {
        // sorting based on position the nodes should have
        nodesWithPositionConstraint.sort(
            [ a, b |
                a.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT) -
                    b.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT)
            ]
        )
        // sorting based on the y(RIGHT/LEFT)/x(DOWN/UP) coordinates
        nodes.sort([ a, b |
            switch(direction) {
                case UNDEFINED, case RIGHT, case LEFT: {
                    return (a.ypos - b.ypos) as int
                }
                case DOWN, case UP: {
                    return (a.xpos - b.xpos) as int
                }
            }
        ])
    }

    /**
     * Sets the (semi) interactive strategies in the phases crossing minimization, layer assignment, 
     * cycle breaking for the graph {@code root}.
     * 
     * @param root The graph which strategies should be set.
     */
    private static def setInteractiveStrategies(KNode root) {
        root.setProperty(LayeredOptions.CROSSING_MINIMIZATION_SEMI_INTERACTIVE, true)
        root.setProperty(LayeredOptions.LAYERING_STRATEGY, LayeringStrategy.INTERACTIVE)
        root.setProperty(LayeredOptions.CYCLE_BREAKING_STRATEGY, CycleBreakingStrategy.INTERACTIVE)
    }

    /**
     * Resets the strategies in the phases crossing minimization, layer assignment, 
     * cycle breaking for the graph {@code root}.
     * 
     * @param root The graph which strategies should be set.
     */
    private static def setStandardStrats(KNode root) {
        root.setProperty(LayeredOptions.CROSSING_MINIMIZATION_STRATEGY, CrossingMinimizationStrategy.LAYER_SWEEP)
        root.setProperty(LayeredOptions.LAYERING_STRATEGY, LayeringStrategy.NETWORK_SIMPLEX)
        root.setProperty(LayeredOptions.CYCLE_BREAKING_STRATEGY, CycleBreakingStrategy.DEPTH_FIRST)
    }
}