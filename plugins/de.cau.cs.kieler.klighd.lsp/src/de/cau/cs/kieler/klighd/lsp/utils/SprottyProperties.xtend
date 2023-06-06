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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.lsp.utils

import de.cau.cs.kieler.klighd.kgraph.KGraphElement
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingRef
import de.cau.cs.kieler.klighd.microlayout.Bounds
import de.cau.cs.kieler.klighd.microlayout.DecoratorPlacementUtil.Decoration
import java.util.Map
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.elk.graph.properties.Property

/**
 * A collection of {@link IProperty IProperties} that may be used to persist some additional data on
 * {@link KGraphElement}s to allow functionality with a Sprotty viewer.
 * 
 * @author nre
 */
class SprottyProperties {
    /**
     * Property holding the bounds of any {@link KRendering}. This property can be set if estimation
     * of rendering sizes should not be calculated in time when needed, but up front for all 
     * renderings.
     */
    public static final IProperty<Bounds> CALCULATED_BOUNDS = 
        new Property<Bounds>("klighd.lsp.calculated.bounds", null);
    
    /**
     * Property holding the decoration of any {@link KRendering}. This property can be set if estimation
     * of rendering sizes should not be calculated in time when needed, but up front for all 
     * renderings.
     * This only applies for renderings, that are decorations of edge renderings.
     */
    public static final IProperty<Decoration> CALCULATED_DECORATION =
        new Property<Decoration>("klighd.lsp.calculated.decoration", null);
    
    /**
     * Property holding the bounds for all child {@link KRendering}s in a {@link KRenderingRef}.
     * This property can be set if estimation of rendering sizes should not be calculated in time 
     * when needed, but up front for all renderings.
     */
    public static final IProperty<Map<String, Bounds>> CALCULATED_BOUNDS_MAP = 
        new Property<Map<String, Bounds>>("klighd.lsp.calculated.bounds.map", null);
    
    /**
     * Property holding the decorations for all child {@link KRendering}s in a {@link KRenderingRef}.
     * This only applies for renderings, that are decorations of edge renderings.
     * This property can be set if estimation of rendering sizes should not be calculated in time 
     * when needed, but up front for all renderings.
     */
    public static final IProperty<Map<String, Decoration>> CALCULATED_DECORATION_MAP = 
        new Property<Map<String, Decoration>>("klighd.lsp.calculated.decoration.map", null);
    
    /**
     * Property indicating if an element is currently drawn in its expanded state.
     */
    public static final IProperty<Boolean> EXPANDED =
        new Property<Boolean>("klighd.lsp.expanded", true);
    
    /**
     * The unique identifier of a {@link KRendering}, built hierarchically from the root rendering of an element using
     * the ID of the parent rendering, a separator "$" and a local ID for the child rendering.
     */
    public static final IProperty<String> RENDERING_ID = 
        new Property<String>("klighd.lsp.rendering.id", null);
    
    /**
     * Sets the unique rendering ID on the given rendering.
     * 
     * @param rendering The rendering to set the new ID on.
     * @param id The new ID.
     * @return The previous ID set on this element, or null.
     */
    static def String setRenderingId(KRendering rendering, String id) {
        return rendering.properties.put(SprottyProperties.RENDERING_ID, id) as String
    }
    
    /**
     * Gets the rendering ID of the given rendering.
     * 
     * @param rendering The rendering to get the ID from.
     * @return The ID.
     */
    static def String getRenderingId(KRendering rendering) {
        return rendering.properties.get(SprottyProperties.RENDERING_ID) as String
    }
}