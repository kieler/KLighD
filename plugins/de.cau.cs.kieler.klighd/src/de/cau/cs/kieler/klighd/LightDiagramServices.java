/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.elk.core.LayoutConfigurator;
import org.eclipse.elk.core.klayoutdata.KLayoutData;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.service.DiagramLayoutEngine;
import org.eclipse.elk.core.service.DiagramLayoutEngine.Parameters;
import org.eclipse.elk.core.service.ElkServicePlugin;
import org.eclipse.elk.core.util.IElkCancelIndicator;
import org.eclipse.elk.core.util.Pair;
import org.eclipse.elk.graph.KNode;
import org.eclipse.elk.graph.properties.IPropertyHolder;
import org.eclipse.elk.graph.properties.MapPropertyHolder;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.klighd.internal.ILayoutConfigProvider;
import de.cau.cs.kieler.klighd.internal.ILayoutRecorder;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;

/**
 * Collection of basic KLighD services.
 *
 * @author chsch
 *
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public final class LightDiagramServices {

    /**
     * A private constructor to prevent instantiation.
     */
    private LightDiagramServices() {
        // do nothing
    }


    /**
     * Updates the diagram represented by the given {@link ViewContext}.<br>
     * <br>
     * The <code>viewContext</code>'s current input model is re-evaluated.
     *
     * @param viewContext
     *            the {@link ViewContext} of the diagram to be updated
     * @return <code>true</code> if update could be performed successfully, <code>false</code>
     *         otherwise
     * @deprecated Due to the various configuration options use
     *             {@link #updateDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static boolean updateDiagram(final ViewContext viewContext) {
        return new LightDiagramLayoutConfig(viewContext)
                    .performUpdate();
    }

    /**
     * Updates the diagram represented by the given {@link ViewContext}.<br>
     * <br>
     * The <code>viewContext</code>'s current input model is re-evaluated.
     *
     * @param viewContext
     *            the {@link ViewContext} of the diagram to be updated
     * @param animate
     *            layout with or without animation
     * @return <code>true</code> if update could be performed successfully, <code>false</code>
     *         otherwise
     * @deprecated Due to the various configuration options use
     *             {@link #updateDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static boolean updateDiagram(final ViewContext viewContext, final boolean animate) {
        return new LightDiagramLayoutConfig(viewContext)
                    .animate(animate)
                    .performUpdate();
    }

    /**
     * Updates the diagram represented by the given {@link ViewContext}.<br>
     * <br>
     * The <code>viewContext</code>'s current input model replaced by <code>model</code>.
     *
     * @param viewContext
     *            the {@link ViewContext} of the diagram to be updated
     * @param model
     *            the new model, if <code>null</code> the current input model is taken
     * @return <code>true</code> if update could be performed successfully, <code>false</code>
     *         otherwise
     * @deprecated Due to the various configuration options use
     *             {@link #updateDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static boolean updateDiagram(final ViewContext viewContext, final Object model) {
        return new LightDiagramLayoutConfig(viewContext)
                    .model(model)
                    .performUpdate();
    }

    /**
     * Updates the diagram represented by the given {@link ViewContext}.<br>
     * <br>
     * The <code>viewContext</code>'s current input model is re-evaluated.
     *
     * @param viewContext
     *            the {@link ViewContext} of the diagram to be updated
     * @param properties
     *            an {@link IPropertyHolder} that may influence the diagram update, e.g. via the
     *            {@link KlighdSynthesisProperties#REQUESTED_UPDATE_STRATEGY} property configuration
     * @return <code>true</code> if update could be performed successfully, <code>false</code>
     *         otherwise
     * @deprecated Due to the various configuration options use
     *             {@link #updateDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static boolean updateDiagram(final ViewContext viewContext,
            final IPropertyHolder properties) {
        return new LightDiagramLayoutConfig(viewContext)
                    .properties(properties)
                    .performUpdate();
    }

    /**
     * Updates the diagram represented by the given {@link ViewContext}.<br>
     * <br>
     * The <code>viewContext</code>'s current input model replaced by <code>model</code>.
     *
     * @param viewContext
     *            the {@link ViewContext} of the diagram to be updated
     * @param model
     *            the new model, if <code>null</code> the current input model is taken
     * @param animate
     *            layout with or without animation
     * @return <code>true</code> if update could be performed successfully, <code>false</code>
     *         otherwise
     * @deprecated Due to the various configuration options use
     *             {@link #updateDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static boolean updateDiagram(final ViewContext viewContext, final Object model,
            final boolean animate) {
        return new LightDiagramLayoutConfig(viewContext)
                    .model(model)
                    .animate(animate)
                    .performUpdate();
    }

    /**
     * Updates the diagram represented by the given {@link ViewContext}.<br>
     * <br>
     * The <code>viewContext</code>'s current input model replaced by <code>model</code>.
     *
     * @param viewContext
     *            the {@link ViewContext} of the diagram to be updated
     * @param model
     *            the new model, if <code>null</code> the current input model is taken
     * @param properties
     *            an {@link IPropertyHolder} that may influence the diagram update, e.g. via the
     *            {@link KlighdSynthesisProperties#REQUESTED_UPDATE_STRATEGY} property configuration
     * @return <code>true</code> if update could be performed successfully, <code>false</code>
     *         otherwise
     * @deprecated Due to the various configuration options use
     *             {@link #updateDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static boolean updateDiagram(final ViewContext viewContext, final Object model,
            final IPropertyHolder properties) {
        return new LightDiagramLayoutConfig(viewContext)
                    .model(model)
                    .properties(properties)
                    .performUpdate();
    }

    /**
     * Updates the diagram represented by the given {@link ViewContext}.<br>
     * <br>
     * The <code>viewContext</code>'s current input model is re-evaluated.
     *
     * @param viewContext
     *            the {@link ViewContext} of the diagram to be updated
     * @param properties
     *            an {@link IPropertyHolder} that may influence the diagram update, e.g. via the
     *            {@link KlighdSynthesisProperties#REQUESTED_UPDATE_STRATEGY} property configuration
     * @param animate
     *            layout with or without animation
     * @return <code>true</code> if update could be performed successfully, <code>false</code>
     *         otherwise
     * @deprecated Due to the various configuration options use
     *             {@link #updateDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static boolean updateDiagram(final ViewContext viewContext,
            final IPropertyHolder properties, final boolean animate) {
        return new LightDiagramLayoutConfig(viewContext)
                    .properties(properties)
                    .animate(animate)
                    .performUpdate();
    }

    /**
     * Updates the diagram represented by the given {@link ViewContext}.<br>
     * <br>
     * The <code>viewContext</code>'s current input model replaced by <code>model</code>.
     *
     * @param viewContext
     *            the {@link ViewContext} of the diagram to be updated
     * @param model
     *            the new model, if <code>null</code> the current input model is taken
     * @param properties
     *            an {@link IPropertyHolder} that may influence the diagram update, e.g. via the
     *            {@link KlighdSynthesisProperties#REQUESTED_UPDATE_STRATEGY} property configuration
     * @param animate
     *            layout with or without animation
     * @return <code>true</code> if update could be performed successfully, <code>false</code>
     *         otherwise
     * @deprecated Due to the various configuration options use
     *             {@link #updateDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static boolean updateDiagram(final ViewContext viewContext, final Object model,
            final IPropertyHolder properties, final Boolean animate) {
        return new LightDiagramLayoutConfig(viewContext)
                    .animate(animate)
                    .model(model)
                    .properties(properties)
                    .performUpdate();
    }

    /**
     * Updates the diagram according to the specification in the given
     * {@link LightDiagramLayoutConfig}. <br>
     *
     * @param config
     *            the {@link LightDiagramLayoutConfig} of the diagram to be updated
     * 
     * @return <code>true</code> if update could be performed successfully, <code>false</code>
     *         otherwise
     */
    public static boolean updateDiagram(final LightDiagramLayoutConfig config) {

        if (config == null) {
            return false;
        }
        
        ViewContext theViewContext;
        // Ensure that the viewContext is present
        if (config.viewContext() == null) {
            Pair<IDiagramWorkbenchPart, ViewContext> dwpandvc = 
                    determineDWPandVC(config.workbenchPart(), config.viewContext());
            theViewContext = dwpandvc.getSecond();
        } else {
            theViewContext = config.viewContext();
        }
        
        final Object currentInputModel = theViewContext.getInputModel();
        if (config.model() == null && currentInputModel == null) {
            return false;
        }

        // update the view context and viewer
        final Object theModel = (config.model() != null ? config.model() : currentInputModel);

        theViewContext.getLayoutRecorder().startRecording();

        // update the view context
        final boolean successful = theViewContext.update(theModel, config.properties());

        // in case the view update didn't work properly
        // consider this as a failure according to the method doc!
        if (!successful) {
            return false;
        }

        final IDiagramWorkbenchPart diagramWP = theViewContext.getDiagramWorkbenchPart();

        if (diagramWP != null) {
            diagramWP.getSite().getPage().bringToTop(diagramWP);
        }

        config.performLayout();

        return true;
    }


    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings, the 'zoomStyle' is
     * taken from the corresponding {@link ViewContext} (toggle buttons).
     *
     * @param viewContext
     *            the viewContext whose diagram is to be arranged
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final ViewContext viewContext) {
        new LightDiagramLayoutConfig(viewContext)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings, the 'zoomStyle' is
     * taken from the corresponding {@link ViewContext} (toggle buttons).
     *
     * @param viewContext
     *            the viewContext whose diagram is to be arranged
     * @param options
     *            a list of layout options
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final ViewContext viewContext,
            final List<LayoutConfigurator> options) {
        new LightDiagramLayoutConfig(viewContext)
                .options(options)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configuration of 'zoomStyle' is taken from the corresponding {@link ViewContext} (toggle
     * buttons).
     *
     * @param viewContext
     *            the viewContext whose diagram is to be arranged
     * @param animate
     *            layout with or without animation
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final ViewContext viewContext, final boolean animate) {
        new LightDiagramLayoutConfig(viewContext)
                .animate(animate)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configuration of 'zoomStyle' is taken from the corresponding {@link ViewContext} (toggle
     * buttons).
     *
     * @param viewContext
     *            the viewContext whose diagram is to be arranged
     * @param animate
     *            layout with or without animation
     * @param options
     *            a list of layout options
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final ViewContext viewContext, final boolean animate,
            final List<LayoutConfigurator> options) {
        new LightDiagramLayoutConfig(viewContext)
                .animate(animate)
                .options(options)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings.
     *
     * @param viewContext
     *            the viewContext whose diagram is to be arranged
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final ViewContext viewContext, final ZoomStyle zoomStyle) {
        new LightDiagramLayoutConfig(viewContext)
                .zoomStyle(zoomStyle)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings.
     *
     * @param viewContext
     *            the viewContext whose diagram is to be arranged
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     * @param options
     *            an optional list of layout options
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final ViewContext viewContext, final ZoomStyle zoomStyle,
            final List<LayoutConfigurator> options) {
        new LightDiagramLayoutConfig(viewContext)
                .zoomStyle(zoomStyle)
                .options(options)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings.
     *
     * @param viewContext
     *            the viewContext whose diagram is to be arranged
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     * @param focusNode
     *            the {@link KNode} to focus in case <code>zoomStyle</code> is
     *            {@link ZoomStyle#ZOOM_TO_FOCUS}, is ignored otherwise
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final ViewContext viewContext, final ZoomStyle zoomStyle,
            final KNode focusNode) {
        new LightDiagramLayoutConfig(viewContext)
                .zoomStyle(zoomStyle)
                .focusNode(focusNode)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings.
     *
     * @param viewContext
     *            the viewContext whose diagram is to be arranged
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     * @param focusNode
     *            the {@link KNode} to focus in case <code>zoomStyle</code> is
     *            {@link ZoomStyle#ZOOM_TO_FOCUS}, is ignored otherwise
     * @param options
     *            an optional list of layout options
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final ViewContext viewContext, final ZoomStyle zoomStyle,
            final KNode focusNode, final List<LayoutConfigurator> options) {
        new LightDiagramLayoutConfig(viewContext)
                .zoomStyle(zoomStyle)
                .focusNode(focusNode)
                .options(options)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.
     *
     * @param viewContext
     *            the viewContext whose diagram is to be arranged
     * @param animate
     *            layout with or without animation
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final ViewContext viewContext, final boolean animate,
            final ZoomStyle zoomStyle) {
        new LightDiagramLayoutConfig(viewContext)
                .animate(animate)
                .zoomStyle(zoomStyle)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.
     *
     * @param viewContext
     *            the viewContext whose diagram is to be arranged
     * @param animate
     *            layout with or without animation
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     * @param options
     *            an optional list of layout options
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final ViewContext viewContext, final boolean animate,
            final ZoomStyle zoomStyle, final List<LayoutConfigurator> options) {
        new LightDiagramLayoutConfig(viewContext)
                .animate(animate)
                .zoomStyle(zoomStyle)
                .options(options)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.
     *
     * @param viewContext
     *            the viewContext whose diagram is to be arranged
     * @param animate
     *            layout with or without animation
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     * @param focusNode
     *            the {@link KNode} to focus in case <code>zoomStyle</code> is
     *            {@link ZoomStyle#ZOOM_TO_FOCUS}, is ignored otherwise
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final ViewContext viewContext, final boolean animate,
            final ZoomStyle zoomStyle, final KNode focusNode) {
        new LightDiagramLayoutConfig(viewContext)
                .animate(animate)
                .zoomStyle(zoomStyle)
                .focusNode(focusNode)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.
     *
     * @param viewContext
     *            the viewContext whose diagram is to be arranged
     * @param animate
     *            layout with or without animation
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     * @param focusNode
     *            the {@link KNode} to focus in case <code>zoomStyle</code> is
     *            {@link ZoomStyle#ZOOM_TO_FOCUS}, is ignored otherwise
     * @param options
     *            an optional list of layout options
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final ViewContext viewContext, final boolean animate,
            final ZoomStyle zoomStyle, final KNode focusNode,
            final List<LayoutConfigurator> options) {
        new LightDiagramLayoutConfig(viewContext)
                .animate(animate)
                .zoomStyle(zoomStyle)
                .focusNode(focusNode)
                .options(options)
                .performLayout();
    }


    /**
     * Performs the automatic layout on the diagram represented by the given
     * {@link IDiagramWorkbenchPart}.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings, the 'zoomStyle' is
     * taken from the corresponding {@link ViewContext} (toggle buttons).
     *
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart) {
        new LightDiagramLayoutConfig(viewPart)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given
     * {@link IDiagramWorkbenchPart}.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings, the 'zoomStyle' is
     * taken from the corresponding {@link ViewContext} (toggle buttons).
     *
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param options
     *            an optional list of layout options
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart,
            final List<LayoutConfigurator> options) {
        new LightDiagramLayoutConfig(viewPart)
                .options(options)
                .performLayout();
    }


    /**
     * Performs the automatic layout on the diagram represented by the given
     * {@link IDiagramWorkbenchPart}.<br>
     * <br>
     * The configurations of 'zoomStyle' is taken from the corresponding {@link ViewContext} (toggle
     * buttons).
     *
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param animate
     *            layout with or without animation
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart, final boolean animate) {
        new LightDiagramLayoutConfig(viewPart)
                .animate(animate)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given
     * {@link IDiagramWorkbenchPart} / {@link IViewer}.
     * <br>
     * The configurations of 'zoomStyle' is taken from the corresponding {@link ViewContext} (toggle
     * buttons).
     *
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param animate
     *            layout with or without animation
     * @param options
     *            an optional list of layout options
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart, final boolean animate,
            final List<LayoutConfigurator> options) {
        new LightDiagramLayoutConfig(viewPart)
                .animate(animate)
                .options(options)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given
     * {@link IDiagramWorkbenchPart}.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings.
     *
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart,
            final ZoomStyle zoomStyle) {
        new LightDiagramLayoutConfig(viewPart)
                .zoomStyle(zoomStyle)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given
     * {@link IDiagramWorkbenchPart}.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings.
     *
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     * @param options
     *            an optional list of layout options
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart,
            final ZoomStyle zoomStyle, final List<LayoutConfigurator> options) {
        new LightDiagramLayoutConfig(viewPart)
                .zoomStyle(zoomStyle)
                .options(options)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings.
     *
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     * @param focusNode
     *            the {@link KNode} to focus in case <code>zoomStyle</code> is
     *            {@link ZoomStyle#ZOOM_TO_FOCUS}, is ignored otherwise
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart,
            final ZoomStyle zoomStyle, final KNode focusNode) {
        new LightDiagramLayoutConfig(viewPart)
                .zoomStyle(zoomStyle)
                .focusNode(focusNode)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings.
     *
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     * @param focusNode
     *            the {@link KNode} to focus in case <code>zoomStyle</code> is
     *            {@link ZoomStyle#ZOOM_TO_FOCUS}, is ignored otherwise
     * @param options
     *            an optional list of layout options
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart,
            final ZoomStyle zoomStyle, final KNode focusNode,
            final List<LayoutConfigurator> options) {
        new LightDiagramLayoutConfig(viewPart)
                .zoomStyle(zoomStyle)
                .options(options)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given
     * {@link IDiagramWorkbenchPart}.
     *
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param animate
     *            layout with or without animation
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied, may be <code>null</code>
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart, final boolean animate,
            final ZoomStyle zoomStyle) {
        new LightDiagramLayoutConfig(viewPart)
                .animate(animate)
                .zoomStyle(zoomStyle)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given
     * {@link IDiagramWorkbenchPart}.
     *
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param animate
     *            layout with or without animation
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied, may be <code>null</code>
     * @param options
     *            an optional list of layout options
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart, final boolean animate,
            final ZoomStyle zoomStyle, final List<LayoutConfigurator> options) {
        new LightDiagramLayoutConfig(viewPart)
                .animate(animate)
                .zoomStyle(zoomStyle)
                .options(options)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.
     *
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param animate
     *            layout with or without animation
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     * @param focusNode
     *            the {@link KNode} to focus in case <code>zoomStyle</code> is
     *            {@link ZoomStyle#ZOOM_TO_FOCUS}, is ignored otherwise
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart, final boolean animate,
            final ZoomStyle zoomStyle, final KNode focusNode) {
        new LightDiagramLayoutConfig(viewPart)
                .animate(animate)
                .zoomStyle(zoomStyle)
                .focusNode(focusNode)
                .performLayout();
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.
     *
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param animate
     *            layout with or without animation
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     * @param focusNode
     *            the {@link KNode} to focus in case <code>zoomStyle</code> is
     *            {@link ZoomStyle#ZOOM_TO_FOCUS}, is ignored otherwise
     * @param options
     *            an optional list of layout options
     * @deprecated Due to the various configuration options use
     *             {@link #layoutDiagram(LightDiagramLayoutConfig)} with a fresh
     *             {@link LightDiagramLayoutConfig} instead.
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart, final boolean animate,
            final ZoomStyle zoomStyle, final KNode focusNode,
            final List<LayoutConfigurator> options) {
        new LightDiagramLayoutConfig(viewPart)
                .animate(animate)
                .zoomStyle(zoomStyle)
                .focusNode(focusNode)
                .options(options)
                .performLayout();
    }


    /**
     * Performs the automatic layout on the diagram represented in the given
     * {@link LightDiagramLayoutConfig}.
     * 
     * @param config
     *            The {@link LightDiagramLayoutConfig} to be used in this layout process.
     */
    public static void layoutDiagram(final LightDiagramLayoutConfig config) {

        if (config == null) {
            final String msg = "KlighD LightDiagramServices: Could not perform layout since no"
                    + "configuration has been specified.";
            StatusManager.getManager()
                    .handle(new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg));
            return;
        }

        final Pair<IDiagramWorkbenchPart, ViewContext> pair =
                determineDWPandVC(config.workbenchPart(), config.viewContext());

        if (pair == null || pair.getSecond() == null) {
            final String msg = "KLighD LightDiagramServices: Could not perform layout since no "
                    + "ViewContext could be determined for IDiagramWorkbenchPart "
                    + config.workbenchPart() + ". "
                    + "Is the diagram correctly and completely initialized?";
            StatusManager.getManager()
                    .handle(new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg));
            return;
        }

        final IDiagramWorkbenchPart thePart = pair.getFirst();
        final ViewContext theViewContext = pair.getSecond();

        final ILayoutRecorder recorder = theViewContext.getLayoutRecorder();
        final KNode viewModel = theViewContext.getViewModel();
        final KLayoutData layoutData =
                viewModel != null ? viewModel.getData(KLayoutData.class) : null;

        if (layoutData != null) {
            theViewContext.setProperty(KlighdInternalProperties.NEXT_ZOOM_STYLE,
                    config.zoomStyle());
            theViewContext.setProperty(KlighdInternalProperties.NEXT_FOCUS_NODE,
                    config.focusNode());

            // Activate the ELK Service plug-in so all layout options are loaded
            ElkServicePlugin.getInstance();

            // Our parameters for the layout run
            Parameters layoutParameters = new Parameters();
            final LayoutConfigurator extendedConfigurator = layoutParameters.addLayoutRun();

            // Animation
            final boolean doAnimate = config.animate() != null ? config.animate().booleanValue()
                    : KlighdPlugin.getDefault().getPreferenceStore()
                            .getBoolean(KlighdPreferences.ANIMATE_LAYOUT);
            layoutParameters.getGlobalSettings().setProperty(CoreOptions.ANIMATE, doAnimate);

            // Animation time properties
            if (config.animationTimeFactor() != null) {
                    layoutParameters.getGlobalSettings().setProperty(CoreOptions.ANIM_TIME_FACTOR, 
                            config.animationTimeFactor());
            }
            if (config.minAnimationTime() != null) {
                layoutParameters.getGlobalSettings().setProperty(CoreOptions.MIN_ANIM_TIME, 
                        config.minAnimationTime());
            }
            if (config.maxAnimationTime() != null) {
                layoutParameters.getGlobalSettings().setProperty(CoreOptions.MAX_ANIM_TIME, 
                        config.maxAnimationTime());
            }

            if (thePart instanceof ILayoutConfigProvider) {
                extendedConfigurator
                        .overrideWith(((ILayoutConfigProvider) thePart).getLayoutConfig());
            }

            if (config.options() != null) {
                for (LayoutConfigurator c : Collections2.filter(config.options(),
                        Predicates.notNull())) {
                    extendedConfigurator.overrideWith(c);
                }
            }

            final List<? extends LayoutConfigurator> additionalConfigs =
                    theViewContext.getAdditionalLayoutConfigs();

            final Object diagramPart = recorder != null ? recorder : theViewContext;

            final IElkCancelIndicator cancelationIndicator =
                    thePart != null ? new DispositionAwareCancelationHandle(thePart) : null;

            if (additionalConfigs.isEmpty()) {
                DiagramLayoutEngine.invokeLayout(thePart, diagramPart, cancelationIndicator,
                        layoutParameters);
            } else {
                for (LayoutConfigurator c : additionalConfigs) {
                    layoutParameters.addLayoutRun(c);
                }

                DiagramLayoutEngine.invokeLayout(thePart, diagramPart, cancelationIndicator,
                        layoutParameters);
            }

        } else {
            if (recorder != null) {
                recorder.stopRecording(config.zoomStyle(), null, 0);
            }
        }
    }

    private static Pair<IDiagramWorkbenchPart, ViewContext> determineDWPandVC(
            final IDiagramWorkbenchPart workbenchPart, final ViewContext viewContext) {

        final IDiagramWorkbenchPart thePart;
        final ViewContext theViewContext;

        if (workbenchPart != null) {
            thePart = workbenchPart;
            final IViewer theViewer = thePart.getViewer();
            if (theViewer != null) {
                theViewContext = theViewer.getViewContext();
            } else {
                theViewContext = null;
            }

        } else if (viewContext != null) {
            theViewContext = viewContext;
            thePart = theViewContext.getDiagramWorkbenchPart();

        } else {
            return null;
        }

        return Pair.of(thePart, theViewContext);
    }

    /**
     * An implementation of {@link ILayoutCancelationIndicator} checking the provided
     * {@link IDiagramWorkbenchPart} for disposition. Is handed over to the
     * {@link DiagramLayoutEngine} in order to let it cancel layout runs if the corresponding
     * {@link IDiagramWorkbenchPart} has been closed in the meantime.
     *
     * @author chsch
     */
    private static final class DispositionAwareCancelationHandle implements IElkCancelIndicator {

        private final IDiagramWorkbenchPart workbenchPart;

        /**
         * Constructor.
         *
         * @param wb
         *            the {@link IDiagramWorkbenchPart} to test for disposition
         */
        private DispositionAwareCancelationHandle(final IDiagramWorkbenchPart wb) {
            workbenchPart = wb;
        }

        /**
         * {@inheritDoc}
         */
        public boolean isCanceled() {
            // by convention (I would like to say 'by definition' but there's no definition)
            //  the employed viewer is only reset to 'null' during IDiagramWorkbenchPart.dispose()
            return workbenchPart.getViewer() == null;
        }
    }

    /* ---------------------------------------- */
    /*     diagram zooming API                  */
    /* ---------------------------------------- */

    /**
     * Performs zoom on the diagram represented by the given {@link ViewContext} based on the
     * current {@link ZoomStyle} defined for the view context.
     *
     * The configurations of 'animate' is taken from the preference settings.
     *
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     */
    public static void zoomDiagram(final ViewContext viewContext) {
        zoomDiagram(null, viewContext, null);
    }

    /**
     * Performs zoom on the diagram represented by the given {@link ViewContext} based on the
     * current {@link ZoomStyle} defined for the view context.
     *
     * The configurations of 'animate' is taken from the preference settings.
     *
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     * @param animate
     *            zoom with or without animation
     */
    public static void zoomDiagram(final ViewContext viewContext, final boolean animate) {
        zoomDiagram(null, viewContext, animate);
    }

    /**
     * Performs zoom on the diagram represented by the given {@link IDiagramWorkbenchPart} based on
     * the current {@link ZoomStyle} defined for the view context.
     *
     * The configurations of 'animate' are taken from the preference settings.
     *
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     */
    public static void zoomDiagram(final IDiagramWorkbenchPart viewPart) {
        zoomDiagram(viewPart, null, null);
    }

    /**
     * Performs zoom on the diagram represented by the given {@link IDiagramWorkbenchPart} based on
     * the current {@link ZoomStyle} defined for the view context.
     *
     * The configurations of 'animate' are taken from the preference settings.
     *
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param animate
     *            zoom with or without animation
     */
    public static void zoomDiagram(final IDiagramWorkbenchPart viewPart, final boolean animate) {
        zoomDiagram(viewPart, null, animate);
    }

    /**
     * Performs zoom on the diagram represented by the given {@link IDiagramWorkbenchPart} of
     * {@link ViewContext} based on the current {@link ZoomStyle} defined for the view context.
     *
     * @param workbenchPart
     *            the {@link IDiagramWorkbenchPart} part showing the diagram to zoom
     * @param viewContext
     *            the {@link ViewContext} whose diagram is to be zoomed
     * @param animate
     *            zoom with or without animation
     */
    private static void zoomDiagram(final IDiagramWorkbenchPart workbenchPart,
            final ViewContext viewContext, final Boolean animate) {

        final Pair<IDiagramWorkbenchPart, ViewContext> pair =
                determineDWPandVC(workbenchPart, viewContext);

        if (pair == null || pair.getSecond() == null) {
            final String msg = "KLighD LightDiagramServices: Could not perform zoom since no "
                    + "ViewContext could be determined for IDiagramWorkbenchPart "
                    + workbenchPart + ". "
                    + "Is the diagram correctly and completely initialized?";
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg));
            return;
        }

        final ViewContext theViewContext = pair.getSecond();
        final boolean doAnimate = animate != null
                ? animate.booleanValue() : KlighdPlugin.getDefault()
                        .getPreferenceStore().getBoolean(KlighdPreferences.ANIMATE_LAYOUT);

        if (theViewContext.getZoomStyle() != ZoomStyle.NONE) {
            theViewContext.getViewer().zoom(theViewContext.getZoomStyle(),
                    doAnimate ? KlighdConstants.DEFAULT_ANIMATION_TIME : 0);
        }
    }


    /**
     * Translates the given <code>model</code> by means of the known diagram synthesis translations.<br>
     * Incorporates constraints given in the <code>propertyHolders</code> as well as the diagram
     * {@link SynthesisOption} settings from <code>otherVC</code>.<br>
     * <br>
     * <b>Note:</b> If no matching diagram synthesis is available this method will return an empty
     * {@link KNode}.
     *
     * @param model
     *            the model to be translated into a diagram
     * @param otherVC
     *            the view context to take {@link SynthesisOption} settings from while translating
     *            <code>model</code>, may be <code>null</code>
     * @param propertyHolders
     *            the property holders
     * @return the resulting view model, may be an empty {@link KNode} in case no matching diagram
     *         synthesis is available.
     */
    public static KNode translateModel(final Object model, final ViewContext otherVC,
            final IPropertyHolder... propertyHolders) {
        return translateModel2(model, otherVC, propertyHolders).getViewModel();
    }

    /**
     * Translates the given <code>model</code> by means of the known diagram synthesis translations.<br>
     * Incorporates constraints given in the <code>propertyHolders</code> as well as the diagram
     * {@link SynthesisOption} settings from <code>otherVC</code>.<br>
     * <br>
     * <b>Note:</b> If no matching diagram synthesis is available {@link ViewContext#getViewModel()
     * getViewModel()} being called on the result of this method will return an empty {@link KNode}.
     *
     * @param model
     *            the model to be translated into a diagram
     * @param otherVC
     *            the view context to take {@link SynthesisOption} settings from while translating
     *            <code>model</code>, may be <code>null</code>
     * @param propertyHolders
     *            the property holders
     * @return the view context
     */
    public static ViewContext translateModel2(final Object model, final ViewContext otherVC,
            final IPropertyHolder... propertyHolders) {
        final ViewContext vc = new ViewContext(otherVC, model).configure(
                KlighdSynthesisProperties.create(propertyHolders));
        vc.update(model);
        return vc;
    }


    /* ---------------------------------------- */
    /*     Off-screen diagram rendering API     */
    /* ---------------------------------------- */

    /**
     * Translates the given <code>model</code> by means of the known diagram synthesis translations
     * and renders it off-screen into the given format, if a matching {@link IOffscreenRenderer} is
     * available.<br>
     * <b>Caution:</b> If the target file already exists it is likely to be overwritten!
     *
     * @param model
     *            the model to be translated into a diagram
     * @param format
     *            the desired diagram format
     * @param targetFileName
     *            the name of the file to write the rendered diagram to
     * @return the {@link String} representation of the desired diagram, or <code>null</code> if no
     *         matching off-screen renderer of diagram synthesis exists
     */
    public static IStatus renderOffScreen(final Object model, final String format,
            final String targetFileName) {
        return renderOffScreen(model, format, targetFileName, null);
    }

    /**
     * Translates the given <code>model</code> by means of the known diagram synthesis translations
     * and renders it off-screen into the given format, if a matching {@link IOffscreenRenderer} is
     * available.<br>
     * <b>Caution:</b> If the target file already exists it is likely to be overwritten!
     *
     * @param model
     *            the model to be translated into a diagram
     * @param format
     *            the desired diagram format
     * @param targetFileName
     *            the name of the file to write the rendered diagram to
     * @param properties
     *            an {@link IPropertyHolder} containing configurations in terms of the properties
     *            defined in {@link IOffscreenRenderer}
     * @return the {@link String} representation of the desired diagram, or <code>null</code> if no
     *         matching off-screen renderer of diagram synthesis exists
     */
    public static IStatus renderOffScreen(final Object model, final String format,
            final String targetFileName, final IPropertyHolder properties) {

        final FileOutputStream output;
        try {
            output = new FileOutputStream(targetFileName);
        } catch (final Exception e) {
            final String msg = "KLighD: Target image file " + targetFileName
                    + " cannot be created or accessed." + KlighdPlugin.LINE_SEPARATOR
                    + "Is the (absolute or relative) path correct? Are the permissions sufficient?";
            return new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg, e);
        }

        final IStatus result = renderOffScreen(model, format, output, properties);

        try {
            output.close();
        } catch (final IOException e) {
            final String msg = "KLighD: Error occurred while closing the output stream employed "
                    + "for writing file " + targetFileName + ".";
            return new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg, e);
        }

        return result;
    }

    /**
     * Translates the given <code>model</code> by means of the known diagram synthesis translations
     * and renders it off-screen into the given format, if a matching {@link IOffscreenRenderer} is
     * available.<br>
     *
     * @param model
     *            the model to be translated into a diagram
     * @param format
     *            the desired diagram format
     * @param output
     *            the OutputStream to write the rendered diagram to, e.g. a
     *            {@link java.io.FileOutputStream FileOutputStream}
     * @return the {@link String} representation of the desired diagram, or <code>null</code> if no
     *         matching off-screen renderer of diagram synthesis exists
     */
    public static IStatus renderOffScreen(final Object model, final String format,
            final OutputStream output) {
        return renderOffScreen(model, format, output, null);
    }

    /**
     * Translates the given <code>model</code> by means of the known diagram synthesis translations
     * and renders it off-screen into the given format, if a matching {@link IOffscreenRenderer} is
     * available.<br>
     *
     * @param model
     *            the model to be translated into a diagram
     * @param format
     *            the desired diagram format
     * @param output
     *            the OutputStream to write the rendered diagram to, e.g. a
     *            {@link java.io.FileOutputStream FileOutputStream}
     * @param properties
     *            an {@link IPropertyHolder} containing configurations in terms of the properties
     *            defined in {@link IOffscreenRenderer}
     * @return the {@link String} representation of the desired diagram, or <code>null</code> if no
     *         matching off-screen renderer of diagram synthesis exists
     */
    public static IStatus renderOffScreen(final Object model, final String format,
            final OutputStream output, final IPropertyHolder properties) {

        if (model == null) {
            throw new NullPointerException(
                    "KLighD offscreen rendering: The provided model must not be 'null'!");
        } else if (format == null) {
            throw new NullPointerException(
                    "KLighD offscreen rendering: The provided format must not be 'null'!");
        } else if (format.isEmpty()) {
            throw new RuntimeException(
                    "KLighD offscreen rendering: The provided format must not be an empty string!");
        }

        // look for a matching IOffscreeenRenderer
        final IOffscreenRenderer renderer = Iterables.getFirst(
                KlighdDataManager.getInstance().getOffscreenRenderersByFormat(format), null);

        // if none exists ...
        if (renderer == null) {
            // omit the translation and return
            return null;
        }

        // otherwise try to build up a corresponding view context
        final ViewContext viewContext = translateModel2(model, null, properties);

        // if no corresponding diagram synthesis is available and, thus, no diagram has been created...
        if (viewContext.getViewModel() == null
                || viewContext.getViewModel().getChildren().isEmpty()) {
            // skip the rendering call and return
            return null;
        }

        final IPropertyHolder theProperties;
        if (properties != null) {
            theProperties = properties;
        } else {
            theProperties = new MapPropertyHolder();
        }

        theProperties.setProperty(IOffscreenRenderer.OUTPUT_FORMAT, format);

        // finally render the diagram and return the result
        final IStatus result = renderer.render(viewContext, output, theProperties);

        return result;
    }
}
