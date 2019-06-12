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

import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramServer
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.KGraphLayoutEngine
import org.eclipse.xtext.ide.server.ILanguageServerAccess.Context
import de.cau.cs.kieler.klighd.kgraph.KNode
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.elk.alg.layered.options.CrossingMinimizationStrategy
import org.eclipse.elk.alg.layered.options.LayeringStrategy
import org.eclipse.elk.alg.layered.options.CycleBreakingStrategy
import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.elk.graph.ElkNode
import java.util.List
import org.eclipse.emf.common.util.EList
import java.util.ArrayList
import de.cau.cs.kieler.klighd.kgraph.KEdge

/**
 * @author jet, cos
 * 
 */
@Singleton
class InteractiveLayout {

    @Inject
    private KGraphDiagramState diagramState

    public def calcLayout(String id, KGraphLayoutEngine layoutE) {

        // layout
        var ViewContext viewContext = null
        // val id = context.resource.URI.toString
        synchronized (diagramState) {
            viewContext = diagramState.getKGraphContext(id)
        }

        val root = viewContext.viewModel

        // Test
        // println("ourLayout")
        // initiales layout
        layoutE.onlyLayoutOnKGraph(id)
        // Koordinaten der Knoten anpassen
        setCoordinates(root)
        // interactive strategies aktivieren
        setInteractiveStrats(root)
        // nochmal layout oder einfach weiterlaufen lassen?
        layoutE.onlyLayoutOnKGraph(id)
    }

    private def static setCoordinates(KNode root) {
        var layers = calcLayerNodes(root.children)
        setXCoordinates(layers)
        for (layer : layers) {
            setYCoordinates(layer)
        }
    }

    private def static calcLayerNodes(EList<KNode> allNodes) {
        var allNs = newArrayList()
        var propNs = newArrayList()
        for (node : allNodes) {
            allNs.add(node)
            if (node.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) !== -1) {
                propNs.add(node)
            }
        }

        // sorting based on x position
        allNs.sort([ a, b |
            if (a.xpos > b.xpos) {
                return 1
            } else if (a.xpos < b.xpos) {
                return -1
            } else {
                return 0
            }
        ])

        var layerNodes = initialLayers(allNs)

        // sorting based on layer the node should be in
        propNs.sort(
            [ a, b |
                a.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) -
                    b.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT)
            ]
        )

        var diff = 0
        for (node : propNs) {
            var layer = node.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) - diff;
            if (layer < layerNodes.size) {
                var nodesOfLayer = layerNodes.get(layer)
                shiftOtherNs(node, nodesOfLayer, layerNodes, true)
                nodesOfLayer.add(node)
            } else {
                diff = diff + layer - layerNodes.size
                var list = newArrayList()
                list.add(node)
                layerNodes.add(list)
            }
        }

        return layerNodes
    }

    private def static void shiftOtherNs(KNode movedNode, ArrayList<KNode> nodesOfLayer,
        ArrayList<ArrayList<KNode>> layerNodes, boolean incoming) {
        var EList<KEdge> edges = null
        if (incoming) {
            edges = movedNode.incomingEdges
        } else {
            edges = movedNode.outgoingEdges
        }
        var layer = movedNode.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT);
        for (edge : edges) {
            var KNode node = null
            if (incoming) {
                node = edge.source
            } else {
                node = edge.target
            }
            if (nodesOfLayer.contains(node)) {
                nodesOfLayer.remove(node)
                if (layer + 1 < layerNodes.size) {
                    layerNodes.get(layer + 1).add(node)
                    shiftOtherNs(node, layerNodes.get(layer + 1), layerNodes, false)
                } else {
                    var list = newArrayList()
                    list.add(node)
                    layerNodes.add(list)
                }
            }
        }
    }

    private def static initialLayers(ArrayList<KNode> nodes) {
        var layerNodes = newArrayList()
        var rightmostX = Float.MIN_VALUE
        var nodesOfLayer = newArrayList()
        for (node : nodes) {
            var posX = node.xpos
            if (posX > rightmostX) {
                layerNodes.add(nodesOfLayer)
                nodesOfLayer = newArrayList()
            }
            if (node.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) === -1) {
                nodesOfLayer.add(node)
            }
            if (posX + node.width > rightmostX) {
                rightmostX = posX + node.width
            }

        }
        if (!nodesOfLayer.isEmpty) {
            layerNodes.add(nodesOfLayer)
        }
        layerNodes.remove(0)
        return layerNodes
    }

    private def static setXCoordinates(ArrayList<ArrayList<KNode>> layers) {
        var float xPos = 0
        var float nextX = 0
        for (var i = 0; i < layers.size; i++) {
            var nodesOfLayer = layers.get(i)
            for (node : nodesOfLayer) {
                node.xpos = xPos
                if (xPos + node.width >= nextX) {
                    nextX = xPos + node.width
                }
            }
            xPos = nextX + 1
        }
    }

    private def static setYCoordinates(List<KNode> nodesOfLayer) {
        val List<KNode> propNodes = newArrayList()
        val List<KNode> nodes = newArrayList()
        for (node : nodesOfLayer) {
            if (node.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT) !== -1) {
                propNodes.add(node)
            } else {
                nodes.add(node)
            }
        }
        sortListsForYPos(propNodes, nodes)

        var currentPos = 0
        var counter = 0
        var maxY = Float.MIN_VALUE
        for (var i = 0; i < nodes.size; i++) {
            var ok = true
            if (counter < propNodes.size) {
                var propNode = propNodes.get(counter)
                if (propNode.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT) ===
                    currentPos) {
                    propNode.ypos = maxY
                    maxY++
                    currentPos++
                    i--
                    ok = false
                }
            }
            if (ok) {
                var node = nodes.get(i)
                node.ypos = maxY
                maxY = maxY + node.height + 1
                currentPos++
            }
        }
    }

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

    private def static setInteractiveStrats(KNode root) {
        root.setProperty(LayeredOptions.SEPARATE_CONNECTED_COMPONENTS, false)
        root.setProperty(LayeredOptions.CROSSING_MINIMIZATION_STRATEGY, CrossingMinimizationStrategy.INTERACTIVE)
        root.setProperty(LayeredOptions.LAYERING_STRATEGY, LayeringStrategy.INTERACTIVE)
        root.setProperty(LayeredOptions.CYCLE_BREAKING_STRATEGY, CycleBreakingStrategy.INTERACTIVE)
    }

}
