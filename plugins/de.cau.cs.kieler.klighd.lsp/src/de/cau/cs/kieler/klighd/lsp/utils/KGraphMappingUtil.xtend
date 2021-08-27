/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018,2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.utils

import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KEdgeLayout
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout
import de.cau.cs.kieler.klighd.lsp.model.SKEdge
import de.cau.cs.kieler.klighd.lsp.model.SKLabel
import de.cau.cs.kieler.klighd.lsp.model.SKNode
import de.cau.cs.kieler.klighd.lsp.model.SKPort
import java.util.ArrayList
import java.util.Map
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.sprotty.Dimension
import org.eclipse.sprotty.Point
import org.eclipse.sprotty.SModelElement
import org.eclipse.sprotty.SShapeElement
import de.cau.cs.kieler.klighd.KlighdDataManager

/**
 * A helper class containing static methods for mapping of KGraph and SGraph bounds.
 * 
 * @author nre
 */
class KGraphMappingUtil {
    
    /**
     * Map the layout of each KGraph element in the map to their corresponding SGraph elements.
     * 
     * @param mapping The mapping between KGraph and SGraph elements
     */
    static def mapLayout(Map<KGraphElement, SModelElement> mapping) {
        mapping.forEach[kGraphElement, sModelElement |
            // Layout data looks different for different KGraph Element Types
            if (kGraphElement instanceof KNode && sModelElement instanceof SKNode) {
                mapLayout(kGraphElement as KNode, sModelElement as SKNode)
            } else if (kGraphElement instanceof KEdge && sModelElement instanceof SKEdge) {
                mapLayout(kGraphElement as KEdge, sModelElement as SKEdge)
            } else if (kGraphElement instanceof KPort && sModelElement instanceof SKPort) {
                mapLayout(kGraphElement as KPort, sModelElement as SKPort)
            } else if (kGraphElement instanceof KLabel && sModelElement instanceof SKLabel) {
                mapLayout(kGraphElement as KLabel, sModelElement as SKLabel)
            } else {
                throw new IllegalArgumentException("The KGraph and SGraph classes do not map to each other: " 
                    + kGraphElement.class + ", " + sModelElement.class)
            }
        ]
    }
    
    /**
     * Maps an edge from KGraph to SGraph
     * 
     * @param kedge The KGraph edge
     * @param skedge The SkGraph edge
     */
    private static def mapLayout(KEdgeLayout kedge, SKEdge skedge) {
        // Copy all routing points.
        var ArrayList<Point> routingPoints = new ArrayList<Point>
        val sourcePoint = kedge.sourcePoint
        val targetPoint = kedge.targetPoint
        if (sourcePoint !== null) {
            routingPoints.add(new Point(sourcePoint.x, sourcePoint.y))
        }
        for (bendPoint : kedge.bendPoints) {
            routingPoints.add(new Point(bendPoint.x, bendPoint.y))
        }
        if (targetPoint !== null) {
            routingPoints.add(new Point(targetPoint.x, targetPoint.y))
        }
        skedge.routingPoints = routingPoints
        
        // Copy the bend points.
        skedge.junctionPoints = kedge.getProperty(CoreOptions.JUNCTION_POINTS)
        // Copy render scale.
        skedge.renderScale = kedge.getProperty(CoreOptions.TOP_DOWN_LAYOUT_RENDER_SCALE);
    }
    
    /**
     * Map node layout from KGraph to SGraph
     * 
     * @param kNode The KGraph node
     * @param skNode The SGraph node
     */
    private static def mapLayout(KNode kNode, SKNode skNode) {
        skNode.position = new Point(kNode.xpos, kNode.ypos)
        skNode.size = new Dimension(kNode.width, kNode.height)
        for (property : KlighdDataManager.instance.preservedProperties) {
            skNode.properties.put(property.id.substring(property.id.lastIndexOf('.') + 1), kNode.getProperty(property))
        }
    }
    
    /**
     * Map shape layout from KGraph to SGraph
     * 
     * @param kElement The KGraph shape
     * @param sElement The SGraph shape
     */
    private static def mapLayout(KShapeLayout kElement, SShapeElement sElement) {
        sElement.position = new Point(kElement.xpos, kElement.ypos)
        sElement.size = new Dimension(kElement.width, kElement.height)
    }
}