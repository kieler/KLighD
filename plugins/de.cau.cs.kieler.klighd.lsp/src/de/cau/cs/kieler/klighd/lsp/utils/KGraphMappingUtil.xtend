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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
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
import org.eclipse.elk.core.math.KVectorChain
import org.eclipse.elk.core.math.KVector

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
        var leftInset = 0.0;
        var topInset = 0.0;
        if (kedge instanceof KEdge) {
            var parent = kedge.getSource().getParent();
            var inset = parent.getInsets();
            leftInset = inset.left;
            topInset = inset.top;
        }
        
        // Copy all routing points.
        var ArrayList<Point> routingPoints = new ArrayList<Point>
        val sourcePoint = kedge.sourcePoint
        val targetPoint = kedge.targetPoint
        
        if (sourcePoint !== null) {
            routingPoints.add(new Point(sourcePoint.x + leftInset, sourcePoint.y + topInset))
        }
        for (bendPoint : kedge.bendPoints) {
            routingPoints.add(new Point(bendPoint.x + leftInset, bendPoint.y + topInset))
        }
        if (targetPoint !== null) {
            routingPoints.add(new Point(targetPoint.x + leftInset, targetPoint.y + topInset))
        }
        skedge.routingPoints = routingPoints
        
        // Copy the bend points.
        skedge.junctionPoints = new KVectorChain()
        skedge.junctionPoints.addAllAsCopies(0,kedge.getProperty(CoreOptions.JUNCTION_POINTS))
        skedge.junctionPoints.offset(new KVector(leftInset, topInset))
        // Copy render scale.
//        skedge.renderScale = kedge.getProperty(CoreOptions.TOP_DOWN_LAYOUT_RENDER_SCALE);
    }
    
    /**
     * Map node layout from KGraph to SGraph
     * 
     * @param kNode The KGraph node
     * @param skNode The SGraph node
     */
    private static def mapLayout(KNode kNode, SKNode skNode) {
        var leftInset = 0.0; 
        var topInset = 0.0;
        var parent = kNode.getParent();
        if (parent !== null) {
            var inset = parent.getInsets();
            leftInset = inset.left;
            topInset = inset.top;
        }
        
        skNode.position = new Point(kNode.xpos + leftInset, kNode.ypos + topInset)
        skNode.size = new Dimension(kNode.width, kNode.height)
        /*for (property : KlighdDataManager.instance.preservedProperties) {
            skNode.properties.put(property.id.substring(property.id.lastIndexOf('.') + 1), kNode.getProperty(property))
            
        }*/
        // TODO: add logic to omit blacklisted stuff later
        // How can I identify which properties cause a stackoverflow?
        // kNode.allProperties.forEach [property, value | skNode.properties.put(property.id, value)]
        var blackList = new ArrayList<String>();
        blackList.add("klighd.modelElement");
        blackList.add("de.cau.cs.kieler.sccharts.ui.tracker");
        // couldn't find anything else that causes problems yet 
        // (checked elkgraph, sccharts, elkt with some different options)
        var properties = kNode.allProperties;
        var propertyKeys = properties.keySet
        for (key : propertyKeys) {
            System.out.println(key.id + " " + properties.get(key));
            if (!blackList.contains(key.id)) {
                skNode.properties.put(key.id, properties.get(key));
            }
        }
    }
    
    /**
     * Map shape layout from KGraph to SGraph
     * 
     * @param kElement The KGraph shape
     * @param sElement The SGraph shape
     */
    private static def mapLayout(KShapeLayout kElement, SShapeElement sElement) {
        var leftInset = 0.0; 
        var topInset = 0.0;
        
        if (kElement instanceof KLabel){
            var parent = kElement.getParent();
            var KNode grandParent = null;
            
            if (parent instanceof KNode) {
                grandParent = parent.getParent();
            } else if (parent instanceof KEdge) {
                grandParent = parent.getSource().getParent();
            } else if (parent instanceof KPort) {
                grandParent = parent.getNode().getParent();
            }
            
            if (grandParent !== null) {
                var inset = grandParent.getInsets();
                leftInset = inset.left;
                topInset = inset.top;
            }
        } else if (kElement instanceof KPort) {
            var parent = kElement.getNode().getParent();
            if (parent !== null) {
                var inset = parent.getInsets();
                leftInset = inset.left;
                topInset = inset.top;
            }
        }      
        
        sElement.position = new Point(kElement.xpos + leftInset, kElement.ypos + topInset)
        sElement.size = new Dimension(kElement.width, kElement.height)
    }
}