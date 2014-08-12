/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.syntheses;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.util.KlighdProperties;

/**
 * Collection of KGraph/KRendering view model configuration methods.
 * 
 * @author chsch
 */
public final class DiagramSyntheses {

    /**
     * Hidden standard constructor.
     */
    private DiagramSyntheses() {
    }

    /**
     * Configures the provided {@link KRendering} to represent the parent {@link KNode} if it is in
     * <b>collapsed</b> state. This information is evaluated for those {@link KRendering
     * KRenderings} only that are directly attached to {@link KNode KNodes}. For other
     * {@link KRendering KRenderings} this configuration has no effect.
     * 
     * @param rendering
     *            the {@link KRendering} to be configured
     * @return <code>rendering</code> for convenience
     */
    public static KRendering setAsCollapsedView(final KRendering rendering) {
        rendering.getProperties().removeKey(KlighdProperties.EXPANDED_RENDERING);
        rendering.setProperty(KlighdProperties.COLLAPSED_RENDERING, true);
        return rendering;
    }

    /**
     * Configures the provided {@link KRendering} to represent the parent {@link KNode} if it is in
     * <b>expanded</b> state. This information is evaluated for those {@link KRendering KRenderings}
     * only that are directly attached to {@link KNode KNodes}. For other {@link KRendering
     * KRenderings} this configuration has no effect.
     * 
     * @param rendering
     *            the {@link KRendering} to be configured
     * @return <code>rendering</code> for convenience
     */
    public static KRendering setAsExpandedView(final KRendering rendering) {
        rendering.getProperties().removeKey(KlighdProperties.COLLAPSED_RENDERING);
        rendering.setProperty(KlighdProperties.EXPANDED_RENDERING, true);
        return rendering;
    }

    /**
     * Configures the provided {@link KRendering} to be visible only if the diagram zoom scale is
     * strictly less then the given <code>upperBounds</code>.
     * 
     * @param rendering
     *            the {@link KRendering} to be configured
     * @param upperBound
     *            the desired upper bound
     * @return <code>rendering</code> for convenience
     */
    public static KRendering setUpperVisibilityScaleBound(final KRendering rendering,
            final float upperBound) {
        rendering.setProperty(KlighdProperties.VISIBILITY_SCALE_UPPER_BOUND, upperBound);
        return rendering;
    }

    /**
     * Configures the provided {@link KGraphElement} to be visible only if the diagram zoom scale is
     * strictly less then the given <code>upperBounds</code>.
     * 
     * @param kgraphElement
     *            the {@link KGraphElement} to be configured
     * @param upperBound
     *            the desired upper bound
     * @return <code>rendering</code> for convenience
     */
    public static KGraphElement setUpperVisibilityScaleBound(final KGraphElement kgraphElement,
            final float upperBound) {
        kgraphElement.getData(KLayoutData.class).setProperty(
                KlighdProperties.VISIBILITY_SCALE_UPPER_BOUND, upperBound);
        return kgraphElement;
    }

    /**
     * Configures the provided {@link KRendering} to be visible only if the diagram zoom scale is
     * less or equal then the given <code>lowerBound</code>.
     * 
     * @param rendering
     *            the {@link KRendering} to be configured
     * @param lowerBound
     *            the desired lower bound
     * @return <code>rendering</code> for convenience
     */
    public static KRendering setLowerVisibilityScaleBound(final KRendering rendering,
            final float lowerBound) {
        rendering.setProperty(KlighdProperties.VISIBILITY_SCALE_LOWER_BOUND, lowerBound);
        return rendering;
    }

    /**
     * Configures the provided {@link KGraphElement} to be visible only if the diagram zoom scale is
     * less or equal then the given <code>lowerBound</code>.
     * 
     * @param kgraphElement
     *            the {@link KGraphElement} to be configured
     * @param lowerBound
     *            the desired lower bound
     * @return <code>rendering</code> for convenience
     */
    public static KGraphElement setLowerVisibilityScaleBound(final KGraphElement kgraphElement,
            final float lowerBound) {
        kgraphElement.getData(KLayoutData.class).setProperty(
                KlighdProperties.VISIBILITY_SCALE_LOWER_BOUND, lowerBound);
        return kgraphElement;
    }

    /**
     * Configures the provided {@link KNode} to be shown in the diagram in collapsed fashion.
     * 
     * @param node
     *            the {@link KNode} to be configured
     * @return <code>node</code> for convenience
     */
    public static KNode initiallyCollapse(final KNode node) {
        node.getData(KLayoutData.class).setProperty(KlighdProperties.EXPAND, false);
        return node;
    }

    /**
     * Configures the provided {@link KNode} to be shown in the diagram in expanded fashion.<br>
     * Note that this is the default configuration, so calling this method is not required in common
     * case.
     * 
     * @param node
     *            the {@link KNode} to be configured
     * @return <code>node</code> for convenience
     */
    public static KNode initiallyExpand(final KNode node) {
        node.getData(KLayoutData.class).setProperty(KlighdProperties.EXPAND, true);
        return node;
    }

    /**
     * Configures the provided {@link KGraphElement} to be initially hidden from the diagram.<br>
     * 
     * @param <T>
     *            the concrete type of the {@link KGraphElement} to be configured
     * @param kge
     *            the {@link KGraphElement} to be configured
     * @return <code>kge</code> for convenience
     */
    public static <T extends KGraphElement> T initiallyHide(final T kge) {
        kge.getData(KLayoutData.class).setProperty(KlighdProperties.SHOW, false);
        return kge;
    }

    /**
     * Configures the provided {@link KGraphElement} to be initially shown in the diagram.<br>
     * Note that this is the default configuration, so calling this method is not required in common
     * case.
     * 
     * @param <T>
     *            the concrete type of the {@link KGraphElement} to be configured
     * @param kge
     *            the {@link KGraphElement} to be configured
     * @return <code>kge</code> for convenience
     */
    public static <T extends KGraphElement> T initiallyShow(final T kge) {
        kge.getData(KLayoutData.class).setProperty(KlighdProperties.SHOW, true);
        return kge;
    }

    /**
     * Configures the initial scale of the provided {@link KNode}.
     * 
     * @param node
     *            the {@link KNode} to be configured
     * @param scale
     *            the initial scale factor of <code>node</code>'s representation in the diagram
     * @return <code>node</code> for convenience
     */
    public static KNode initiallyScale(final KNode node, final float scale) {
        node.getData(KLayoutData.class).setProperty(LayoutOptions.SCALE_FACTOR, scale);
        return node;
    }

    private static final String NO_VIEWCONTEXT_ERROR_MSG =
            "KLighD: Failed to set the initial diagram clip in XX: No ViewContext is available.";

    /**
     * Configures the {@link KNode} the diagram is initially clipped to.<br>
     * Note that this method has to be called on the {@link KNode} to be the initial clip node
     * rather than, e.g., the view model's root node.<br>
     * <br>
     * This method is just an alias of {@link #initiallyClipTo(KNode, ViewContext)} allowing a more
     * intuitive formulation in Xtend's infix notation, like
     * 
     * <pre>
     * viewContext.initiallyClipTo(node);
     * </pre>
     * 
     * @param viewContext
     *            the {@link ViewContext} to perform this definition in
     * @param node
     *            the {@link KNode} to be the initial diagram clip node
     * @return <code>node</code> for convenience
     */
    public static KNode initiallyClipTo(final ViewContext viewContext, final KNode node) {
        return initiallyClipTo(node, viewContext);
    }

    /**
     * Configures the {@link KNode} the diagram is initially clipped to.<br>
     * Note that this method has to be called on the {@link KNode} to be the initial clip node
     * rather than, e.g., the view model's root node.
     * 
     * @param node
     *            the {@link KNode} to be the initial diagram clip node
     * @param viewContext
     *            the {@link ViewContext} to perform this definition in
     * @return <code>node</code> for convenience
     */
    public static KNode initiallyClipTo(final KNode node, final ViewContext viewContext) {
        if (viewContext != null) {
            viewContext.setProperty(KlighdProperties.CLIP, node);
        } else {
            throw new RuntimeException(NO_VIEWCONTEXT_ERROR_MSG);
        }
        return node;
    }

    /**
     * Deactivates the selectability of given {@link KGraphElement}.<br>
     * If done the {@link KGraphElement} can't be selected anymore, other event handling like
     * associated action evaluation will not be affected.
     * 
     * @param kge
     *            the {@link KGraphElement} to configure
     * @return the <code>kge</code> for convenience
     */
    public static KGraphElement suppressSelectability(final KGraphElement kge) {
        kge.getData(KLayoutData.class).setProperty(KlighdProperties.NOT_SELECTABLE, true);
        return kge;
    }

    /**
     * Deactivates the selectability of given {@link KText}.<br>
     * If done the {@link KText} can't be selected anymore, other event handling like associated
     * action evaluation will not be affected.
     * 
     * @param kText
     *            the {@link KText} to configure
     * @return the <code>kText</code> for convenience
     */
    public static KText suppressSelectability(final KText kText) {
        kText.setProperty(KlighdProperties.NOT_SELECTABLE, true);
        return kText;
    }

    /**
     * Configures a tooltip on the provided {@link KRendering root KRendering} of a
     * {@link KGraphElement}. Similar to {@link #setAsCollapsedView(KRendering)} or
     * {@link #setAsExpandedView(KRendering)} this method has no effect on nested {@link KRendering
     * KRendering} (except of blowing up the view model :-P).
     * 
     * @param rendering
     *            the {@link KRendering} to be configured
     * @param tooltip
     *            the tooltip text to be shown while hovering over the corresponding diagram figure
     * @return <code>rendering</code> for convenience
     */
    public static KRendering setTooltip(final KRendering rendering, final String tooltip) {
        rendering.setProperty(KlighdProperties.TOOLTIP, tooltip);
        return rendering;
    }
}
