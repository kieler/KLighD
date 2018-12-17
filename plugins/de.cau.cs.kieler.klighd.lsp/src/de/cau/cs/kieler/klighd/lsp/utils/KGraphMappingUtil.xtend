/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
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
import io.typefox.sprotty.api.Dimension
import io.typefox.sprotty.api.Point
import io.typefox.sprotty.api.SEdge
import io.typefox.sprotty.api.SModelElement
import io.typefox.sprotty.api.SShapeElement
import java.util.ArrayList
import java.util.Map

/**
 * A helper class containing static methods for mapping of KGraph and SGraph bounds.
 * 
 * @author nir
 */
public class KGraphMappingUtil {    
    /**
     * Map the layout of each KGraph element in the map to their corresponding SGraph elements.
     */
    public static def mapLayout(Map<KGraphElement, SModelElement> mapping) {
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
    
    private static def mapLayout(KEdgeLayout kedge, SEdge skedge) {
        // copy all routing points
        var ArrayList<Point> routingPoints = new ArrayList<Point>
        var sourcePoint = kedge.sourcePoint
        var targetPoint = kedge.targetPoint
        routingPoints.add(new Point(sourcePoint.x, sourcePoint.y))
        for (bendPoint : kedge.bendPoints) {
            routingPoints.add(new Point(bendPoint.x, bendPoint.y))
        }
        routingPoints.add(new Point(targetPoint.x, targetPoint.y))
        skedge.routingPoints = routingPoints
    }
    
    private static def mapLayout(KShapeLayout kElement, SShapeElement sElement) {
        sElement.position = new Point(kElement.xpos, kElement.ypos)
        sElement.size = new Dimension(kElement.width, kElement.height)
    }
}