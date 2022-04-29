/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018-2022 by
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

import de.cau.cs.kieler.klighd.KlighdDataManager
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KLabel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout
import de.cau.cs.kieler.klighd.lsp.model.SKEdge
import de.cau.cs.kieler.klighd.lsp.model.SKElement
import de.cau.cs.kieler.klighd.lsp.model.SKLabel
import de.cau.cs.kieler.klighd.lsp.model.SKNode
import de.cau.cs.kieler.klighd.lsp.model.SKPort
import java.util.ArrayList
import java.util.List
import java.util.Map
import org.eclipse.elk.core.math.KVector
import org.eclipse.elk.core.math.KVectorChain
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.sprotty.Dimension
import org.eclipse.sprotty.Point
import org.eclipse.sprotty.SModelElement
import org.eclipse.sprotty.SShapeElement
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil

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
            } else if (kGraphElement instanceof KPort && sModelElement instanceof SKPort
                || kGraphElement instanceof KLabel && sModelElement instanceof SKLabel) {
                mapLayout(kGraphElement as KShapeLayout, sModelElement as SShapeElement)
            } else {
                throw new IllegalArgumentException("The KGraph and SGraph classes do not map to each other: " 
                    + kGraphElement.class + ", " + sModelElement.class)
            }
        ]
    }
    
    /**
     * Maps an edge from KGraph to SGraph
     * 
     * @param kEdge The KGraph edge
     * @param skEdge The SkGraph edge
     */
    private static def mapLayout(KEdge kEdge, SKEdge skEdge) {
        var KNode parent
        if (KGraphUtil.isDescendant(kEdge.target, kEdge.source)) {
            parent = kEdge.source
        } else {
            parent = kEdge.source.parent
        }
        var inset = parent.getInsets();
        val leftInset = inset.left;
        val topInset = inset.top;
        
        // Copy all routing points.
        var ArrayList<Point> routingPoints = new ArrayList<Point>
        val sourcePoint = kEdge.sourcePoint
        val targetPoint = kEdge.targetPoint
        
        if (sourcePoint !== null) {
            routingPoints.add(new Point(sourcePoint.x + leftInset, sourcePoint.y + topInset))
        }
        for (bendPoint : kEdge.bendPoints) {
            routingPoints.add(new Point(bendPoint.x + leftInset, bendPoint.y + topInset))
        }
        if (targetPoint !== null) {
            routingPoints.add(new Point(targetPoint.x + leftInset, targetPoint.y + topInset))
        }
        skEdge.routingPoints = routingPoints
        
        // Copy the bend points.
        skEdge.junctionPoints = new KVectorChain()
        skEdge.junctionPoints.addAllAsCopies(0, kEdge.getProperty(CoreOptions.JUNCTION_POINTS))
        skEdge.junctionPoints.offset(new KVector(leftInset, topInset))

        // map all properties excepts those that are blacklisted
        var properties = kEdge.allProperties;
        var blackList = KlighdDataManager.instance.blacklistedProperties;

        for (propertyKVPair : properties.entrySet()) {
            if (!containsPropertyWithId(blackList, propertyKVPair.key.id)) {
                // TODO: remove this check once https://github.com/kieler/semantics/pull/13 has been merged
                if (!propertyKVPair.key.id.equals("de.cau.cs.kieler.sccharts.ui.tracker")) {
                    skEdge.properties.put(propertyKVPair.key.id, propertyKVPair.value)
                }
            }
        }
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

        var properties = kNode.allProperties;
        var blackList = KlighdDataManager.instance.blacklistedProperties;

        for (propertyKVPair : properties.entrySet()) {
            if (!containsPropertyWithId(blackList, propertyKVPair.key.id)) {
                // TODO: remove this check once https://github.com/kieler/semantics/pull/13 has been merged
                if (!propertyKVPair.key.id.equals("de.cau.cs.kieler.sccharts.ui.tracker")) {
                    skNode.properties.put(propertyKVPair.key.id, propertyKVPair.value)
                }
            }
        }
    }
    
    /**
     * Utility method for checking whether a list of {@link IProperty}s contains a property
     * with the given id.
     * @param propertyList list of properties to check
     * @param id id to be searched for
     * @return true if the list contains a property with the given id, false otherwise
     */
    static def containsPropertyWithId(List<IProperty<?>> propertyList, String id) {
        for (IProperty<?> property : propertyList) {
            if (property.id.equals(id)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Map shape layout from KGraph to SGraph
     * 
     * @param kElement The KGraph shape
     * @param sElement The SGraph shape
     */
    private static def mapLayout(KShapeLayout kElement, SShapeElement /*& SKElement*/ sElement) {
        var leftInset = 0.0; 
        var topInset = 0.0;
        // Edge labels behave differently: the reference point for the position is the source node child area if the
        // target node is directly or indirectly contained by the source node, the source node's parent child area
        // otherwise
        if (kElement instanceof KLabel && (kElement as KLabel).parent instanceof KEdge) {
            val kEdge = (kElement as KLabel).parent as KEdge
            var KNode parent
            if (KGraphUtil.isDescendant(kEdge.target, kEdge.source)) {
                parent = kEdge.source
            } else {
                parent = kEdge.source.parent
            }
            var inset = parent.getInsets();
            leftInset = inset.left;
            topInset = inset.top;
        }
        
        var properties = kElement.allProperties;
        var blackList = KlighdDataManager.instance.blacklistedProperties;
        
        for (propertyKVPair : properties.entrySet()) {
            if (!containsPropertyWithId(blackList, propertyKVPair.key.id)) {
                // TODO: remove this check once https://github.com/kieler/semantics/pull/13 has been merged
                if (!propertyKVPair.key.id.equals("de.cau.cs.kieler.sccharts.ui.tracker")) {
                        (sElement as SKElement).properties.put(propertyKVPair.key.id, propertyKVPair.value)
                }
            }
        }
        
        sElement.position = new Point(kElement.xpos + leftInset, kElement.ypos + topInset)
        sElement.size = new Dimension(kElement.width, kElement.height)
        
    }
}