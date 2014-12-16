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

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.krendering.KAreaPlacementData;
import de.cau.cs.kieler.core.krendering.KBackground;
import de.cau.cs.kieler.core.krendering.KBottomPosition;
import de.cau.cs.kieler.core.krendering.KContainerRendering;
import de.cau.cs.kieler.core.krendering.KInvisibility;
import de.cau.cs.kieler.core.krendering.KLeftPosition;
import de.cau.cs.kieler.core.krendering.KLineStyle;
import de.cau.cs.kieler.core.krendering.KLineWidth;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KRightPosition;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KTopPosition;
import de.cau.cs.kieler.core.krendering.LineStyle;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutMetaDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.klighd.util.ExpansionAwareLayoutOption.ExpansionAwareLayoutOptionData;

/**
 * Collection of KGraph/KRendering view model configuration methods.
 *
 * @author chsch
 *
 * @kieler.design proposed by chsch
 */
public final class DiagramSyntheses {

    /**
     * Hidden standard constructor.
     */
    private DiagramSyntheses() {
    }

    /**
     * Convenience method for defining layout options for {@link KGraphElement KGraphElements}.
     *
     * @param <R>
     *            the concrete type of <code>element</code>
     * @param <T>
     *            the property value type
     * @param element
     *            the element to set the layout option on
     * @param option
     *            the particular layout option, e.g. one of
     *            {@link de.cau.cs.kieler.kiml.options.LayoutOptions LayoutOptions}
     * @param value
     *            the option value
     * @return <code>element</code> allowing to perform multiple operations on it in one statement
     */
    public static <R extends KGraphElement, T> R setLayoutOption(final R element,
            final IProperty<T> option, final T value) {
        element.getData(KLayoutData.class).setProperty(option, value);
        return element;
    }

    /**
     * Convenience method for defining layout options for {@link KGraphElement KGraphElements} 
     * based on ids and possibly string representations of properties defined by 
     * {@link java.util.EnumSet}s.
     *
     * @param <R>
     *            the concrete type of <code>element</code>
     * @param element
     *            the element to set the layout option on
     * @param optionId
     *            the particular layout option's id, e.g. one of
     *            {@link de.cau.cs.kieler.kiml.options.LayoutOptions LayoutOptions}
     * @param value
     *            the option value. It is possible to pass string representations 
     *            of EnumSets as well as any non-string property.
     * @return <code>element</code> allowing to perform multiple operations on it in one statement
     */
    public static <R extends KGraphElement> R setLayoutOption(final R element,
            final String optionId, final Object value) {
        final LayoutOptionData option = LayoutMetaDataService.getInstance().getOptionData(optionId);
        if (option != null) {
            Object realValue = value;
            if (value instanceof String) {
                realValue = option.parseValue((String) value);
            }
            element.getData(KLayoutData.class).setProperty(option, realValue);
        }
        return element;
    }

    /**
     * Convenience method for defining collapse/expand state dependent layout options for
     * {@link KNode KNodes}.
     *
     * @param <T>
     *            the property value type
     * @param node
     *            the node to set the layout option on
     * @param option
     *            the particular layout option, e.g. one of
     *            {@link de.cau.cs.kieler.kiml.options.LayoutOptions LayoutOptions}
     * @param collapsedValue
     *            the value in case <code>node</code> is collapsed
     * @param expandedValue
     *            the value in case <code>node</code> is expanded
     * @return <code>node</code> allowing to perform multiple operations on it in one statement
     */
    public static <T> KNode setExpansionAwareLayoutOption(final KNode node, final IProperty<T> option,
            final T collapsedValue, final T expandedValue) {
        final KLayoutData sl = node.getData(KLayoutData.class);
        if (sl != null) {
            ExpansionAwareLayoutOption.setProperty(sl, option, collapsedValue, expandedValue);
        }
        return node;
    }

    /**
     * Convenience method for defining collapse/expand state dependent layout options for
     * {@link KPort KPorts}. The collapse/expand state refers to that of the {@link KNode}
     * containing the {@link KPort}.
     *
     * @param <T>
     *            the property value type
     * @param port
     *            the port to set the layout option on
     * @param option
     *            the particular layout option, e.g. one of
     *            {@link de.cau.cs.kieler.kiml.options.LayoutOptions LayoutOptions}
     * @param collapsedValue
     *            the value in case <code>port</code>'s container node is collapsed
     * @param expandedValue
     *            the value in case <code>port</code>'s container node is expanded
     * @return <code>node</code> allowing to perform multiple operations on it in one statement
     */
    public static <T> KPort setExpansionAwareLayoutOption(final KPort port,
            final IProperty<T> option, final T collapsedValue, final T expandedValue) {
        final KLayoutData sl = port.getData(KLayoutData.class);
        ExpansionAwareLayoutOptionData data = sl.getProperty(ExpansionAwareLayoutOption.OPTION);

        if (data == null) {
            data = new ExpansionAwareLayoutOptionData();
            sl.setProperty(ExpansionAwareLayoutOption.OPTION, data);
        }

        data.setProperty(option, collapsedValue, expandedValue);

        return port;
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
            final Number upperBound) {
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
            final Number upperBound) {
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
            final Number lowerBound) {
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
            final Number lowerBound) {
        kgraphElement.getData(KLayoutData.class).setProperty(
                KlighdProperties.VISIBILITY_SCALE_LOWER_BOUND, lowerBound);
        return kgraphElement;
    }

    /**
     * Configures the provided {@link KRendering} to be excluded from the outline diagram view.
     *
     * @param rendering
     *            the {@link KRendering} to be configured
     * @return <code>rendering</code> for convenience
     */
    public static KRendering excludedFromOutline(final KRendering rendering) {
        rendering.setProperty(KlighdProperties.OUTLINE_INVISIBLE, true);
        return rendering;
    }

    /**
     * Configures the provided {@link KRendering} to be excluded from exported diagram images.
     *
     * @param rendering
     *            the {@link KRendering} to be configured
     * @return <code>rendering</code> for convenience
     */
    public static KRendering excludedFromExportedImages(final KRendering rendering) {
        rendering.setProperty(KlighdProperties.EXPORTED_IMAGE_INVISIBLE, true);
        return rendering;
    }

    /**
     * Configures the provided {@link KRendering} to be excluded from diagram printouts.
     *
     * @param rendering
     *            the {@link KRendering} to be configured
     * @return <code>rendering</code> for convenience
     */
    public static KRendering excludedFromPrintouts(final KRendering rendering) {
        rendering.setProperty(KlighdProperties.PRINTOUT_INVISIBLE, true);
        return rendering;
    }

    /**
     * Configures the provided {@link KRendering} to be excluded from exported diagram images and
     * printouts.
     *
     * @param rendering
     *            the {@link KRendering} to be configured
     * @return <code>rendering</code> for convenience
     */
    public static KRendering excludedFromExportedImagesAndPrintouts(final KRendering rendering) {
        rendering.setProperty(KlighdProperties.EXPORTED_IMAGE_INVISIBLE, true);
        rendering.setProperty(KlighdProperties.PRINTOUT_INVISIBLE, true);
        return rendering;
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

    /**
     * Wraps the given rendering in a rendering displayed on selection and adds that to the given KGraph
     * element. The selection rendering is returned to be modified further and is the one obtained by
     * calling {@link #wrapWithStandardNodeSelectionRendering(ren)}.
     *
     * @param kge the KGraph element the rendering should be added to.
     * @param ren the rendering to be wrapped. May be {@code null}, in which case no rendering is added
     *            to the selection rendering.
     * @return the selection rendering.
     */
    public static KContainerRendering addRenderingWithStandardSelectionWrapper(final KGraphElement kge,
            final KRendering ren) {

        final KContainerRendering selectionRendering = wrapWithStandardNodeSelectionRendering(ren);
        kge.getData().add(selectionRendering);
        return selectionRendering;
    }

    /** Corner size of the rounded rectangle used to display selections. */
    private static final float SELECTION_RECTANGLE_CORNER_SIZE = 3.0f;
    /** How much larger the selection rectangle should be than the original rendering. */
    private static final float SELECTION_RECTANGLE_ENLARGEMENT = 3.0f;
    /** Red component of the standard selection color. */
    private static final int SELECTION_COLOR_R = 180;
    /** Green component of the standard selection color. */
    private static final int SELECTION_COLOR_G = 213;
    /** Blue component of the standard selection color. */
    private static final int SELECTION_COLOR_B = 255;

    /**
     * Creates a rendering displayed when an element is selected and wraps the given rendering with it.
     * The selection rendering's color is retrieved from the operating system's colors and defaults to a
     * standard color if the application is run headlessly.
     *
     * @param ren the rendering to be wrapped by the selection wrapper. May be {@code null}, in which
     *            case the raw selection rendering is returned.
     * @return the selection rendering.
     */
    public static KContainerRendering wrapWithStandardNodeSelectionRendering(final KRendering ren) {
        // Retrieve the color to be used for selections (this is done here instead of just once
        // statically to handle system color changes)
        int selectionR = SELECTION_COLOR_R;
        int selectionG = SELECTION_COLOR_G;
        int selectionB = SELECTION_COLOR_B;

        final Display display = Display.getCurrent();
        if (display != null) {
            final Color selectionColor = display.getSystemColor(SWT.COLOR_LIST_SELECTION);
            selectionR = selectionColor.getRed();
            selectionG = selectionColor.getGreen();
            selectionB = selectionColor.getBlue();
        }

        final KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        final KRectangle containerRendering = factory.createKRectangle();

        // Make container rectangle invisible
        final KInvisibility containerInvisibility = factory.createKInvisibility();
        containerInvisibility.setInvisible(true);
        containerRendering.getStyles().add(containerInvisibility);

        // Rounded rectangle used to display the selection
        final KRoundedRectangle selectionRectangle = factory.createKRoundedRectangle();
        selectionRectangle.setCornerWidth(SELECTION_RECTANGLE_CORNER_SIZE);
        selectionRectangle.setCornerHeight(SELECTION_RECTANGLE_CORNER_SIZE);
        containerRendering.getChildren().add(selectionRectangle);

        // Background color of the rounded rectangle
        final KBackground selectionRectangleBackground = factory.createKBackground();
        selectionRectangleBackground.setColor(selectionR, selectionG, selectionB);
        selectionRectangle.getStyles().add(selectionRectangleBackground);

        // Line style and with of the rounded rectangle
        final KLineStyle selectionRectangleLineStyle = factory.createKLineStyle();
        selectionRectangleLineStyle.setLineStyle(LineStyle.DASH);
        selectionRectangle.getStyles().add(selectionRectangleLineStyle);

        final KLineWidth selectionRectangleLineWidth = factory.createKLineWidth();
        selectionRectangleLineWidth.setLineWidth(1.0f);
        selectionRectangle.getStyles().add(selectionRectangleLineWidth);

        // Make selection rectangle only visible on selection
        KInvisibility selectionVisibility = factory.createKInvisibility();
        selectionVisibility.setInvisible(true);
        selectionRectangle.getStyles().add(selectionVisibility);

        selectionVisibility = factory.createKInvisibility();
        selectionVisibility.setInvisible(false);
        selectionVisibility.setSelection(true);
        selectionRectangle.getStyles().add(selectionVisibility);

        // Make the selection rectangle a bit larger than the original rendering
        final KTopPosition topPosition = factory.createKTopPosition()
                .setPosition(-SELECTION_RECTANGLE_ENLARGEMENT, 0);
        final KLeftPosition leftPosition = factory.createKLeftPosition()
                .setPosition(-SELECTION_RECTANGLE_ENLARGEMENT, 0);
        final KPosition topLeftPosition = factory.createKPosition()
                .setPositions(leftPosition, topPosition);

        final KBottomPosition bottomPosition = factory.createKBottomPosition()
                .setPosition(-SELECTION_RECTANGLE_ENLARGEMENT, 0);
        final KRightPosition rightPosition = factory.createKRightPosition()
                .setPosition(-SELECTION_RECTANGLE_ENLARGEMENT, 0);
        final KPosition bottomRightPosition = factory.createKPosition()
                .setPositions(rightPosition, bottomPosition);

        final KAreaPlacementData areaPlacementData = factory.createKAreaPlacementData();
        areaPlacementData.setTopLeft(topLeftPosition);
        areaPlacementData.setBottomRight(bottomRightPosition);

        selectionRectangle.setPlacementData(areaPlacementData);

        // Add the original rendering to the container rendering
        if (ren != null) {
            containerRendering.getChildren().add(ren);
        }

        return containerRendering;
    }
}
