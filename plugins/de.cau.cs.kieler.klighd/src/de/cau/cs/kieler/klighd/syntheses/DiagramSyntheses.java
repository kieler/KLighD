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
     * This method is just an alias of {@link #initiallyClipTo(KNode, ViewContext)} allowing a
     * more intuitive formulation in Xtend's infix notation, like
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
}
