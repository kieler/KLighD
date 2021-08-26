/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018,2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.utils

import com.google.common.base.Predicate
import com.google.common.base.Strings
import de.cau.cs.kieler.klighd.IStyleModifier.StyleModificationContext
import de.cau.cs.kieler.klighd.KlighdDataManager
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPoint
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout
import de.cau.cs.kieler.klighd.krendering.KAreaPlacementData
import de.cau.cs.kieler.klighd.krendering.KContainerRendering
import de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData
import de.cau.cs.kieler.klighd.krendering.KGridPlacement
import de.cau.cs.kieler.klighd.krendering.KPlacement
import de.cau.cs.kieler.klighd.krendering.KPointPlacementData
import de.cau.cs.kieler.klighd.krendering.KPolygon
import de.cau.cs.kieler.klighd.krendering.KPolyline
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingLibrary
import de.cau.cs.kieler.klighd.krendering.KRenderingRef
import de.cau.cs.kieler.klighd.krendering.KStyle
import de.cau.cs.kieler.klighd.microlayout.Bounds
import de.cau.cs.kieler.klighd.microlayout.DecoratorPlacementUtil
import de.cau.cs.kieler.klighd.microlayout.DecoratorPlacementUtil.Decoration
import de.cau.cs.kieler.klighd.microlayout.GridPlacementUtil
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil
import java.awt.geom.Point2D
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map

import static com.google.common.collect.Iterables.filter

import static extension de.cau.cs.kieler.klighd.lsp.utils.SprottyProperties.*

/**
 * Utility class to provide some functionality to persist prepare the rendering of a {@link KGraphElement}.
 * 
 * @author nre
 */
final class RenderingPreparer {

    /**
     * Prepares a KGraphElement to be rendered in an external viewer.
     * Calculates the position, width and height of each rendering of the parameters {@code element} from KLighD's 
     * micro layout and persists the bounds and in case of a Decorator the decoration in the properties of the 
     * rendering.
     * See {@link #setBounds} and {@link #setDecoration}.
     * In case of a {@link KRenderingRef} the bounds and decoration are persisted for every referenced rendering as a map
     * inside the properties of the reference.
     * For example: <id of the rendering in the library: bounds in this instance>
     * Furthermore, for every rendering a unique ID is generated.
     * Finally, modifiable styles defined by the synthesis are processed for the rendering.
     * 
     * @param element The parent element containing the graph to calculate all rendering bounds for.
     */
    static def void prepareRendering(KGraphElement element) {
        // calculate the sizes of all renderings:
        for (data : element.data) {
            switch(data) {
                KRenderingLibrary: {
                    // The library needs to generate ids for all later KRenderingRefs to refer to, but no own bounds,
                    // since these are generic renderings.
                    for (rendering : data.renderings) {
                        if (rendering instanceof KRendering) {
                            KRenderingIdGenerator.generateIdsRecursive(rendering)
                        }
                    }
                }
                KRenderingRef: {
                    // all references to KRenderings need to place a map with the ids of the renderings and their 
                    // sizes and their decoration in this case in the properties of the reference.
                    var boundsMap = new HashMap<String, Bounds>
                    var decorationMap = new HashMap<String, Decoration>
                    handleKRendering(element, data.rendering, boundsMap, decorationMap)
                    // add new Property to contain the boundsMap
                    data.properties.put(CALCULATED_BOUNDS_MAP, boundsMap)
                    // and the decorationMap
                    data.properties.put(CALCULATED_DECORATION_MAP, decorationMap)
                    // remember the id of the rendering in the reference
                    data.renderingId = data.rendering.renderingId
                    
                }
                KRendering: {
                    // every rendering needs an ID, generate it here
                    KRenderingIdGenerator.generateIdsRecursive(data)
                    handleKRendering(element, data, null, null)
                }
            }
        }
        
        // Recursively call this method for every child KGraphElement of this.
        // (all labels, child nodes, outgoing edges and ports)
        
        if (element instanceof KLabeledGraphElement) {
            for (label : element.labels) {
                prepareRendering(label)
            }
        }
        if (element instanceof KNode) {
            for (node : element.children) {
                prepareRendering(node)
            }
            for (edge : element.outgoingEdges) {
                prepareRendering(edge)
            }
            for (port : element.ports) {
                prepareRendering(port)
            }
        }
    }
    
    /**
     * Calculate the size and position of the parent rendering of the element and store it in the boundsMap or if the
     * boundsMap is null as a property in the rendering itself. 
     * Also calculated the size, position and decoration for every child rendering.
     * 
     * @param element The element that contains the rendering.
     * @param rendering The rendering to estimate the bounds for. Needs to be the top most rendering of
     * the element.
     * @param boundsMap The map to store the bounds in. If it is null, the bounds should be instead stored in the
     * properties of the {@code rendering} itself.
     * @param decorationMap The map to store the decoration in. If it is null, the decoration should be instead stored in the
     * properties of the {@code rendering} itself. Only applies to the child renderings.
     */
    private static def void handleKRendering(KGraphElement element, KRendering rendering, Map<String, Bounds> boundsMap,
        Map<String, Decoration> decorationMap) {
        var Bounds bounds
        if (element instanceof KShapeLayout) {
            // The parent rendering inherits its bounds from the element containing the rendering.
            bounds = new Bounds(element.width, element.height)
        } else {
            // In this case the element is a KEdge.
            bounds = edgeBounds(element as KEdge)
        }
        // Calculate the bounds of the rendering.
        handleAreaAndPointAndDecoratorPlacementRendering(rendering, bounds, boundsMap, decorationMap, element)
        
        // Calculate the bounds for the junction point rendering.
        if (rendering instanceof KPolyline) {
            if (rendering.junctionPointRendering !== null) {                
                handleAreaAndPointAndDecoratorPlacementRendering(rendering.junctionPointRendering, bounds, boundsMap,
                    decorationMap, element)
            }
        }
    }
    
    /**
     * Calculate the size and position of all child renderings recursively. The boundsMap and decorationMap again indicate
     * if is should be stored in them (not null) or in the rendering's properties themselves.
     * Inspired by {@link de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController}.
     * 
     * @param renderings The child renderings to calculate the sizes and decorations for.
     * @param placement The defined placement of the child renderings.
     * @param parentBounds The bounds of the parent rendering. All child renderings have to fit inside these.
     * @param boundsMap The map to store the bounds in. If it is null, the bounds should be instead stored in the
     * properties of the {@code rendering} itself.
     * @param decorationMap The map to store the decoration in. If it is null, the decoration should be instead stored in the
     * properties of the {@code rendering} itself.
     * @param parent The parent graph element containing this rendering as one of its children.
     */
    private static def void handleChildren(List<KRendering> renderings, KPlacement placement, Bounds parentBounds,
        Map<String, Bounds> boundsMap, Map<String, Decoration> decorationMap, KGraphElement parent) {
        if (placement instanceof KGridPlacement) {
            handleGridPlacementRendering(renderings, placement, parentBounds, boundsMap, decorationMap, parent)
        } else {
            for (rendering : renderings) {
                handleAreaAndPointAndDecoratorPlacementRendering(rendering, parentBounds, boundsMap, decorationMap, parent)
            }
        }
    }
    
    /**
     * Handles the child renderings, if they should be placed in a grid placement.
     * 
     * @see #handleChildren
     */
    private static def void handleGridPlacementRendering(List<KRendering> renderings, KGridPlacement gridPlacement, 
        Bounds parentBounds, Map<String, Bounds> boundsMap, Map<String, Decoration> decorationMap, KGraphElement parent) {
        
        // Evaluate the grid placement micro layout with the help of KLighD.
        val Bounds[] elementBounds = GridPlacementUtil.evaluateGridPlacement(gridPlacement, renderings, parentBounds);
        
        // Apply the element bounds for each rendering.
        for (var i = 0; i < renderings.size; i++) {
            var Map<String, Bounds> usedBoundsMap = boundsMap
            var Map<String, Decoration> usedDecorationMap = decorationMap
            val rendering = renderings.get(i)
            // KRenderingRefs inside other renderings. This reference needs a new bounds- and decoration map to be
            // stored inside it.
            if (rendering instanceof KRenderingRef) {
                usedBoundsMap = new HashMap<String, Bounds>
                usedDecorationMap = new HashMap<String, Decoration>
                
                 // add new Property to contain the boundsMap
                rendering.properties.put(CALCULATED_BOUNDS_MAP, usedBoundsMap)
                // and the decorationMap
                rendering.properties.put(CALCULATED_DECORATION_MAP, usedDecorationMap)    
            }
            
            val bounds = elementBounds.get(i)
            // Decide if the bounds should be put in the boundsMap or in the rendering's properties.
            if (usedBoundsMap === null) {
                rendering.setBounds(bounds)
            } else {
                usedBoundsMap.put(rendering.renderingId, bounds)
            }
            // Process modifiable styles
            processModifiableStyles(rendering, parent)
            // Calculate the bounds and decorations of all child renderings.
            if (rendering instanceof KContainerRendering) {
                handleChildren(rendering.children, rendering.childPlacement, bounds, usedBoundsMap, usedDecorationMap,
                    parent)
            } else if (rendering instanceof KRenderingRef
                && (rendering as KRenderingRef).rendering instanceof KContainerRendering
            ) {
                val referencedRendering = (rendering as KRenderingRef).rendering as KContainerRendering
                handleChildren(referencedRendering.children, referencedRendering.childPlacement, bounds, usedBoundsMap,
                    usedDecorationMap, parent)
            }
        }
    }
    
    /**
     * Handles the child renderings, if they should be placed as an area, point or decorator placement.
     * 
     * @see #handleChildren
     */
    private static def void handleAreaAndPointAndDecoratorPlacementRendering(KRendering rendering, Bounds parentBounds,
        Map<String, Bounds> boundsMap, Map<String, Decoration> decorationMap, KGraphElement parent) {
        var placementData = rendering.placementData
        var Bounds bounds
        var Decoration decoration = null
        var Map<String, Bounds> usedBoundsMap = boundsMap
        var Map<String, Decoration> usedDecorationMap = decorationMap
        
        // KRenderingRefs inside other renderings. This reference needs a new bounds- and decoration map to be stored
        // inside it.
        if (rendering instanceof KRenderingRef) {
            usedBoundsMap = new HashMap<String, Bounds>
            usedDecorationMap = new HashMap<String, Decoration>
            placementData = rendering.rendering.placementData
            
             // add new Property to contain the boundsMap
            rendering.properties.put(CALCULATED_BOUNDS_MAP, usedBoundsMap)
            // and the decorationMap
            rendering.properties.put(CALCULATED_DECORATION_MAP, usedDecorationMap)
        }
        
        switch (placementData) {
            KAreaPlacementData: {
                // Evaluate the area placement micro layout with the help of KLighD.
                bounds = PlacementUtil.evaluateAreaPlacement(placementData, parentBounds)
            }
            KPointPlacementData: {
                // Evaluate the point placement micro layout with the help of KLighD.
                bounds = PlacementUtil.evaluatePointPlacement(rendering, placementData, parentBounds)
            }
            KDecoratorPlacementData: {
                // Decorator placements can only be evaluated if the path they should decorate is known.
                // to call KLighD's DecoratorPlacementUtil#evaluateDecoratorPlacement the points of the path of the
                // parent rendering have to be stored.
                var Point2D[] path = #[]
//                var path = new KlighdPath(rendering) // TODO: Can I also only use the points of the rendering?
                val parentRendering = rendering.eContainer
                if (parentRendering instanceof KPolygon) {
                    // For a KPolygon as the parent rendering the points it have to be evaluated first.
                    var points = newArrayOfSize(parentRendering.points.size)
                    for (var i = 0; i < parentRendering.points.size; i++) {
                        points.set(i, 
                            PlacementUtil.evaluateKPosition(parentRendering.points.get(i), parentBounds, true).toPoint2D)
                    }
                    // The path is a polygon as the parent rendering indicates.
                    path = points
                } else if (parentRendering instanceof KPolyline) {
                    // For a KPolyline as the parent rendering the points have to be extracted from the parent edge,
                    // if it is one or the point list of the polyline (preference to the parent's edge points).
                    var List<KPoint> pointList = new ArrayList()
                    if (parent instanceof KEdge) {
                        val edge = parent as KEdge
                        
                        pointList.add(edge.sourcePoint)
                        pointList.addAll(edge.bendPoints)
                        pointList.add(edge.targetPoint)
                    } else if (!parentRendering.points.empty) {
                        pointList.addAll(parentRendering.points.map[position | 
                            PlacementUtil.evaluateKPosition(position, parentBounds, true)])
                    } else {
                        throw new IllegalArgumentException("The parent element of the KPolyline is not a KEdge or " +
                            "the pointList of the KPolyline rendering is empty")
                    }
                    
                    // Convert the KPoint list to a needed Point2D.Float array.
                    var points = newArrayOfSize(pointList.size)
                    for (var i = 0; i < pointList.size; i++) {
                        val point = pointList.get(i)
                        points.set(i, new Point2D.Float(point.x, point.y))
                    }
                    // The path is a polyline as the parent rendering indicates.
                    path = points
                } else {
                    throw new IllegalArgumentException("A decorator placement is only applicable to KPolygons or " +
                        "KPolylines")
                }
                
                // Now evaluate the decorator placement micro layout with the help of KLighD.
                decoration = DecoratorPlacementUtil.evaluateDecoratorPlacement(placementData, path)
                bounds = decoration.bounds
            }
            default: {
                // If no placementData is defined, assume the width and height of the parent object
                // placed at the top left corner.
                bounds = new Bounds(parentBounds.width, parentBounds.height)
            }
        }
        // Decide if the bounds and decoration should be put in the boundsMap/decorationMap or in the rendering's
        // properties.
        if (usedBoundsMap === null) {
            rendering.setBounds(bounds)
            if (decoration !== null) {
                rendering.setDecoration(decoration)
            }
        } else {
            usedBoundsMap.put(rendering.renderingId, bounds)
            if (decoration !== null) {
                usedDecorationMap.put(rendering.renderingId, decoration)
            }
        }
        // Process modifiable styles
        processModifiableStyles(rendering, parent)
        // Calculate the bounds and decorations of all child renderings.
        if (rendering instanceof KContainerRendering) {
            handleChildren(rendering.children, rendering.childPlacement, bounds, usedBoundsMap, usedDecorationMap, parent)
        } else if (rendering instanceof KRenderingRef
            && (rendering as KRenderingRef).rendering instanceof KContainerRendering
        ) {
            val referencedRendering = (rendering as KRenderingRef).rendering as KContainerRendering
            handleChildren(referencedRendering.children, referencedRendering.childPlacement, bounds, usedBoundsMap,
                usedDecorationMap, parent)
        }
    }
    
    /**
     * Calculates the bounding box of a KEdge.
     * 
     * @param edge The edge to calculate the bounds for.
     * @return The bounding box.
     */
    private static def Bounds edgeBounds(KEdge edge) {
        var float minX = Float.MAX_VALUE
        var float minY = Float.MAX_VALUE
        var float maxX = Float.MIN_VALUE
        var float maxY = Float.MIN_VALUE
        var List<KPoint> pointList = new ArrayList()
        if (edge.sourcePoint !== null) {
            pointList.add(edge.sourcePoint)
        }
        pointList.addAll(edge.bendPoints)
        if (edge.targetPoint !== null) {
            pointList.add(edge.targetPoint)
        }
        for (point : pointList) {
            if (point.x < minX) {
                minX = point.x
            }
            if (point.x > maxX) {
                maxX = point.x
            }
            if (point.y < minY) {
                minY = point.y
            }
            if (point.y > maxY) {
                maxY = point.y
            }
        }
        return new Bounds(minX, minY, maxX - minX, maxY - minY)
    }
    
    /**
     * Convenience method to set the calculated bounds property of the given rendering.
     * 
     * @param rendering The rendering to configure the bounds for.
     * @param bounds The bounds to set.
     */
    private static def setBounds(KRendering rendering, Bounds bounds) {
        rendering.properties.put(CALCULATED_BOUNDS, bounds)
    }
    
    /**
     * Convenience method to set the calculated decoration property of the given rendering.
     * 
     * @param rendering The rendering to configure the decoration for.
     * @param bounds The decoration to set.
     */
    private static def setDecoration(KRendering rendering, Decoration decoration) {
        rendering.properties.put(CALCULATED_DECORATION, decoration)
    }
    
    /**
     * A filter that lets only styles with a valid modifier id pass.
     */
    static val Predicate<KStyle> MODIFIED_STYLE_FILTER = new Predicate<KStyle>() {
        override apply(KStyle style) {
            return !Strings.isNullOrEmpty(style.getModifierId())
                    && KlighdDataManager.getInstance()
                        .getStyleModifierById(style.getModifierId()) !== null;
        }
    };

    static val StyleModificationContext singletonModContext = new StyleModificationContext();
    
    /**
     * @see de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController#processModifiableStyles
     */
    private static def void processModifiableStyles(KRendering rendering, KGraphElement parent) {
        val styles = if (rendering instanceof KRenderingRef)
                rendering.rendering.styles
            else
                rendering.styles
        
        val Iterable<KStyle> localModifiedStyles = filter(styles, MODIFIED_STYLE_FILTER);

        var boolean deliver
        for (s : localModifiedStyles) {
            deliver  = s.eDeliver();
            s.eSetDeliver(false);
            KlighdDataManager.getInstance().getStyleModifierById(s.getModifierId()).modify(
                singletonModContext.configure(s, parent));
            s.eSetDeliver(deliver);
        }
    }
}