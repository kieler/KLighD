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

        println("ourLayout")
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

        nodesWithLayerProp.sort(
            [ a, b |
                a.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT) -
                    b.getProperty(LayeredOptions.LAYERING_LAYER_CHOICE_CONSTRAINT)
            ]
        )

        nodes.sort([ a, b |
            if (a.xpos > b.xpos) {
                return 1
            } else if (a.xpos < b.xpos) {
                return -1
            } else {
                return 0
            }
        ])

        setXCoordinates(nodesWithLayerProp, nodes)
    }

    def static setXCoordinates(List<KNode> nodesWithLayerProp, List<KNode> nodes) {
        // TODO: edit this method. Currently it doesn't work properly
        // works for nodes without edges
        var rightmostX = Float.MIN_VALUE
        var offset = Float.MIN_VALUE
        var currentLayer = -1
        var List<KNode> nodesOfLayer = newArrayList()
        var counter = 0

        for (node : nodes) {
            var posX = node.xpos
            if (posX > rightmostX) {
                var float newOff = 0
                if (counter < nodesWithLayerProp.size) {
                    var propNode = nodesWithLayerProp.get(counter)
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
                        if (counter >= nodesWithLayerProp.size) {
                            ok = false
                        } else {
                            propNode = nodesWithLayerProp.get(counter)
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

        while (counter < nodesWithLayerProp.size) {
            var propNode = nodesWithLayerProp.get(counter)
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
                if (counter >= nodesWithLayerProp.size) {
                    ok = false
                } else {
                    propNode = nodesWithLayerProp.get(counter)
                }
            }
            offset = offset + newOff + 1
            currentLayer++
            setYCoordinates(nodesOfLayer)
        }
    }

    def static setYCoordinates(List<KNode> nodes) {
        // TODO: implement
    }

    private def static setInteractiveStrats(KNode root) {
        root.setProperty(LayeredOptions.SEPARATE_CONNECTED_COMPONENTS, false)
        root.setProperty(LayeredOptions.CROSSING_MINIMIZATION_STRATEGY, CrossingMinimizationStrategy.INTERACTIVE)
        root.setProperty(LayeredOptions.LAYERING_STRATEGY, LayeringStrategy.INTERACTIVE)
        root.setProperty(LayeredOptions.CYCLE_BREAKING_STRATEGY, CycleBreakingStrategy.INTERACTIVE)
    }

}
