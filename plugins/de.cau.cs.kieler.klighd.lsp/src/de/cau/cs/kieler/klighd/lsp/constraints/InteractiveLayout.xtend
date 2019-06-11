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
        val children = root.children
        val List<KNode> nodesWithLayerProp = newArrayList()
        val List<KNode> nodes = newArrayList()
        for (node : children) {
            if (node.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) !== -1) {
                nodesWithLayerProp.add(node)
            } else {
                nodes.add(node)
            }
        }

        setXCoordinates(nodesWithLayerProp, nodes)
    }

    private def static setXCoordinates(List<KNode> propNodes, List<KNode> nodes) {

        sortListsForXPos(propNodes, nodes)

        // TODO: only works properly for nodes without edges
        var rightmostX = Float.MIN_VALUE
        var offset = Float.MIN_VALUE
        var currentLayer = -1
        var List<KNode> nodesOfLayer = newArrayList()
        var counter = 0

        for (node : nodes) {
            var posX = node.xpos
            if (posX > rightmostX) {
                var float newOff = 0
                if (counter < propNodes.size) {
                    var propNode = propNodes.get(counter)
                    var ok = true

                    while (ok &&
                        propNode.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) == currentLayer) {
                        propNode.xpos = rightmostX + offset - 1
                        var o = propNode.width
                        if (o > newOff) {
                            newOff = o
                        }
                        nodesOfLayer.add(propNode)
                        counter++
                        if (counter >= propNodes.size) {
                            ok = false
                        } else {
                            propNode = propNodes.get(counter)
                        }
                    }
                }
                offset = offset + newOff
                currentLayer++
                setYCoordinates(nodesOfLayer)
                nodesOfLayer = newArrayList
            }
            nodesOfLayer.add(node)
            if (posX + node.width > rightmostX) {
                rightmostX = posX + node.width
            }
            if (offset != Float.MIN_VALUE) {
                node.xpos = offset + node.xpos + 1
            }
        }

        while (counter < propNodes.size) {
            var propNode = propNodes.get(counter)
            var ok = true
            var float newOff = 0
            while (ok && propNode.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) == currentLayer) {
                propNode.xpos = rightmostX + offset - 1
                nodesOfLayer.add(propNode)
                counter++
                var o = propNode.width
                if (o > newOff) {
                    newOff = o
                }
                if (counter >= propNodes.size) {
                    ok = false
                } else {
                    propNode = propNodes.get(counter)
                }
            }
            offset = offset + newOff + 1
            currentLayer = propNode.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT)
            setYCoordinates(nodesOfLayer)
        }
    }

    private def static sortListsForXPos(List<KNode> propNodes, List<KNode> nodes) {
        // sorting based on layer the nodes should be in 
        propNodes.sort(
            [ a, b |
                a.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) -
                    b.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT)
            ]
        )
        // sorting based on the x coordinates
        nodes.sort([ a, b |
            if (a.xpos > b.xpos) {
                return 1
            } else if (a.xpos < b.xpos) {
                return -1
            } else {
                return 0
            }
        ])
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
