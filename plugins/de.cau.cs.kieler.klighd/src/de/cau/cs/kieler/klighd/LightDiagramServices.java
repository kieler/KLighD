/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
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
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.kiml.config.CompoundLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.service.KimlServicePlugin;
import de.cau.cs.kieler.klighd.internal.ILayoutConfigProvider;
import de.cau.cs.kieler.klighd.internal.ILayoutRecorder;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;

/**
 * Singleton for accessing basic KLighD services.
 * 
 * @author chsch
 */
public final class LightDiagramServices {

    /**
     * A private constructor to prevent instantiation.
     */
    private LightDiagramServices() {
        // do nothing
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configurations of 'animate' and 'zoomToFit' are taken from the preference settings. 
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     */
    public static void layoutDiagram(final ViewContext viewContext) {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean animate = preferenceStore.getBoolean(KlighdPreferences.ANIMATE_LAYOUT);
        final boolean zoomToFit = viewContext.isZoomToFit();
        
        layoutDiagram(viewContext, animate, zoomToFit, Collections.<ILayoutConfig>emptyList());
    }

    /**
     * Performs the automatic layout and zoomToFit on the diagram represented by the given view
     * context.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings.
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     */
    public static void layoutAndZoomDiagram(final ViewContext viewContext) {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean animate = preferenceStore.getBoolean(KlighdPreferences.ANIMATE_LAYOUT);
        final boolean zoomToFit = true;
        
        layoutDiagram(viewContext, animate, zoomToFit, Collections.<ILayoutConfig>emptyList());
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configuration of 'zoomToFit' is taken from the preference settings. 
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     * @param animate
     *            layout with or without animation
     */
    public static void layoutDiagram(final ViewContext viewContext, final boolean animate) {
        final boolean zoomToFit = viewContext.isZoomToFit();

        layoutDiagram(viewContext, animate, zoomToFit, Collections.<ILayoutConfig>emptyList());
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     * @param animate
     *            layout with or without animation
     * @param zoomToFit
     *            layout with or without animation
     */
    public static void layoutDiagram(final ViewContext viewContext, final boolean animate,
            final boolean zoomToFit) {
        layoutDiagram(viewContext, animate, zoomToFit, Collections.<ILayoutConfig>emptyList());
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configurations of 'animate' and 'zoomToFit' are taken from the preference settings.
     * 
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     * @param options
     *            an optional list of layout options
     */
    public static void layoutDiagram(final ViewContext viewContext, final List<ILayoutConfig> options) {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean animate = preferenceStore.getBoolean(KlighdPreferences.ANIMATE_LAYOUT);
        final boolean zoomToFit = viewContext.isZoomToFit();
        
        layoutDiagram(viewContext, animate, zoomToFit, options);
    }
    
    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configuration of 'zoomToFit' is taken from the preference settings. 
     * 
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     * @param animate
     *            layout with or without animation
     * @param options
     *            an optional list of layout options
     */
    public static void layoutDiagram(final ViewContext viewContext, final boolean animate,
           final List<ILayoutConfig> options) {
        final boolean zoomToFit = viewContext.isZoomToFit();
        
        layoutDiagram(viewContext, animate, zoomToFit, options);
    }
    
    /**
     * Performs the automatic layout on the diagram represented by the given view context.
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     * @param animate
     *            layout with or without animation
     * @param zoomToFit
     *            layout with or without animation
     * @param options
     *            an optional list of layout options
     */
    public static void layoutDiagram(final ViewContext viewContext, final boolean animate,
            final boolean zoomToFit, final List<ILayoutConfig> options) {
        final IViewer<KNode> diagramViewer = viewContext.getViewer();
        
        layoutDiagram(viewContext.getDiagramWorkbenchPart(), diagramViewer, animate, zoomToFit, options);
    }
    
    /**
     * Performs the automatic layout on the diagram represented by the given
     * {@link IDiagramWorkbenchPart}.<br>
     * <br>
     * The configurations of 'animate' and 'zoomToFit' are taken from the preference settings.
     * 
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart) {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean animate = preferenceStore.getBoolean(KlighdPreferences.ANIMATE_LAYOUT);
        final boolean zoomToFit = viewPart.getViewer().getViewContext().isZoomToFit();
        
        layoutDiagram(viewPart, animate, zoomToFit, Collections.<ILayoutConfig>emptyList());
    }
    
    /**
     * Performs the automatic layout and zoomToFit on the diagram represented by the given
     * {@link IDiagramWorkbenchPart}.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings.
     * 
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     */
    public static void layoutAndZoomDiagram(final IDiagramWorkbenchPart viewPart) {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean animate = preferenceStore.getBoolean(KlighdPreferences.ANIMATE_LAYOUT);
        final boolean zoomToFit = true;

        layoutDiagram(viewPart, animate, zoomToFit, Collections.<ILayoutConfig>emptyList());
    }
    
    /**
     * Performs the automatic layout on the diagram represented by the given
     * {@link IDiagramWorkbenchPart}.<br>
     * <br>
     * The configuration of 'zoomToFit' is taken from the preference settings. 
     * 
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param animate
     *            layout with or without animation
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart, final boolean animate) {
        final boolean zoomToFit;
        if (viewPart.getViewer().getViewContext() != null) {
            zoomToFit = viewPart.getViewer().getViewContext().isZoomToFit();
        } else {
            zoomToFit =
                    ZoomStyle.valueOf(KlighdPlugin.getDefault().getPreferenceStore()
                            .getString(KlighdPreferences.ZOOM_STYLE)) == ZoomStyle.ZOOM_TO_FIT;
        }
        
        layoutDiagram(viewPart, animate, zoomToFit, Collections.<ILayoutConfig>emptyList());
    }
    
    /**
     * Performs the automatic layout on the diagram represented by the given
     * {@link IDiagramWorkbenchPart}.
     * 
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param animate
     *            layout with or without animation
     * @param zoomToFit
     *            layout with or without animation
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart, final boolean animate,
            final boolean zoomToFit) {
        layoutDiagram(viewPart, animate, zoomToFit, Collections.<ILayoutConfig>emptyList());
    }
    
    /**
     * Performs the automatic layout on the diagram represented by the given
     * {@link IDiagramWorkbenchPart}.<br>
     * <br>
     * The configurations of 'animate' and 'zoomToFit' are taken from the preference settings.
     * 
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param options
     *            an optional list of layout options
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart,
            final List<ILayoutConfig> options) {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean animate = preferenceStore.getBoolean(KlighdPreferences.ANIMATE_LAYOUT);
        final boolean zoomToFit = viewPart.getViewer().getViewContext().isZoomToFit();
        
        layoutDiagram(viewPart, animate, zoomToFit, options);
    }
    
    /**
     * Performs the automatic layout on the diagram represented by the given
     * {@link IDiagramWorkbenchPart}.<br>
     * <br>
     * The configuration of 'zoomToFit' is taken from the preference settings. 
     * 
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param animate
     *            layout with or without animation
     * @param options
     *            an optional list of layout options
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart, final boolean animate,
            final List<ILayoutConfig> options) {
        final boolean zoomToFit = viewPart.getViewer().getViewContext().isZoomToFit();
        
        layoutDiagram(viewPart, animate, zoomToFit, options);
    }
    
    /**
     * Performs the automatic layout on the diagram represented by the given
     * {@link IDiagramWorkbenchPart}.
     * 
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param animate
     *            layout with or without animation
     * @param zoomToFit
     *            layout with or without animation
     * @param options
     *            an optional list of layout options
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart, final boolean animate,
            final boolean zoomToFit, final List<ILayoutConfig> options) {
        layoutDiagram(viewPart, viewPart.getViewer(), animate, zoomToFit, options);
    }
    
    /**
     * Performs the automatic layout on the diagram represented by the given
     * {@link IDiagramWorkbenchPart} / {@link IViewer}.
     * 
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param diagramViewer
     *            the viewer that renders the diagram to layout
     * @param animate
     *            layout with or without animation
     * @param zoomToFit
     *            layout with or without animation
     * @param options
     *            an optional list of layout options
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart,
            final IViewer<?> diagramViewer, final boolean animate,
            final boolean zoomToFit, final List<ILayoutConfig> options) {

        final ViewContext viewContext;
        if (diagramViewer != null) {
            viewContext = diagramViewer.getViewContext();
        } else if (viewPart != null) {
            viewContext = viewPart.getViewer().getViewContext();
        } else {
            return;
        }
        
        final ZoomStyle zoomStyle = ZoomStyle.create(false, zoomToFit, viewContext.isZoomToFocus());
        layoutDiagram(viewPart, diagramViewer, animate, zoomStyle, options);
    }


    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings, the 'zoomStyle' is
     * taken from the corresponding {@link ViewContext} (toggle buttons).
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     */
    public static void layoutDiagram2(final ViewContext viewContext) {
        layoutDiagram(viewContext, (List<ILayoutConfig>) null);
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings, the 'zoomStyle' is
     * taken from the corresponding {@link ViewContext} (toggle buttons).
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     * @param options
     *            a list of layout options
     */
    public static void layoutDiagram2(final ViewContext viewContext,
            final List<ILayoutConfig> options) {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean animate = preferenceStore.getBoolean(KlighdPreferences.ANIMATE_LAYOUT);
        
        layoutDiagram(viewContext, animate, viewContext.getZoomStyle(), options);
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configuration of 'zoomStyle' is taken from the corresponding {@link ViewContext} (toggle
     * buttons).
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     * @param animate
     *            layout with or without animation
     */
    public static void layoutDiagram2(final ViewContext viewContext, final boolean animate) {
        layoutDiagram(viewContext, animate, (List<ILayoutConfig>) null);
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configuration of 'zoomStyle' is taken from the corresponding {@link ViewContext} (toggle
     * buttons).
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     * @param animate
     *            layout with or without animation
     * @param options
     *            a list of layout options
     */
    public static void layoutDiagram2(final ViewContext viewContext, final boolean animate,
            final List<ILayoutConfig> options) {
        layoutDiagram(viewContext, animate, viewContext.getZoomStyle(), options);
    }

    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings.
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     */
    public static void layoutDiagram(final ViewContext viewContext, final ZoomStyle zoomStyle) {
        layoutDiagram(viewContext, zoomStyle, null);
    }
    
    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configurations of 'animate' is taken from the preference settings.
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     * @param options
     *            an optional list of layout options
     */
    public static void layoutDiagram(final ViewContext viewContext, final ZoomStyle zoomStyle,
            final List<ILayoutConfig> options) {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean animate = preferenceStore.getBoolean(KlighdPreferences.ANIMATE_LAYOUT);

        layoutDiagram(viewContext, animate, zoomStyle, options);
    }
    
    /**
     * Performs the automatic layout on the diagram represented by the given view context.
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     * @param animate
     *            layout with or without animation
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     */
    public static void layoutDiagram(final ViewContext viewContext, final boolean animate,
            final ZoomStyle zoomStyle) {
        layoutDiagram(viewContext, animate, zoomStyle, null);
    }
    
    /**
     * Performs the automatic layout on the diagram represented by the given view context.
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     * @param animate
     *            layout with or without animation
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied during this layout update
     * @param options
     *            an optional list of layout options
     */
    public static void layoutDiagram(final ViewContext viewContext, final boolean animate,
            final ZoomStyle zoomStyle, final List<ILayoutConfig> options) {
        layoutDiagram(viewContext.getDiagramWorkbenchPart(), viewContext.getViewer(), animate,
                zoomStyle, options);
    }
    
    
    /**
     * Performs the automatic layout on the diagram represented by the given
     * {@link IDiagramWorkbenchPart} / {@link IViewer}.
     * 
     * @param viewPart
     *            the diagram view part showing the diagram to layout
     * @param diagramViewer
     *            the viewer that renders the diagram to layout
     * @param animate
     *            layout with or without animation
     * @param zoomStyle
     *            the {@link ZoomStyle} to be applied, may be <code>null</code>
     * @param options
     *            an optional list of layout options
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart,
            final IViewer<?> diagramViewer, final boolean animate,
            final ZoomStyle zoomStyle, final List<ILayoutConfig> options) {
        
        final ViewContext viewContext;
        if (diagramViewer != null) {
            viewContext = diagramViewer.getViewContext();
        } else if (viewPart != null) {
            viewContext = viewPart.getViewer().getViewContext();
        } else {
            return;
        }
        
        final KNode viewModel = viewContext.getViewModel();
        final KLayoutData layoutData = viewModel != null ? viewModel.getData(KLayoutData.class) : null;
        
        if (layoutData != null) {
            viewContext.setProperty(KlighdInternalProperties.NEXT_ZOOM_STYLE, zoomStyle);

            // Activate the KIML Service plug-in so all layout options are loaded
            KimlServicePlugin.getDefault();

            final CompoundLayoutConfig extendedOptions = new CompoundLayoutConfig();
            extendedOptions.add(new VolatileLayoutConfig()
                    .setValue(LayoutOptions.ANIMATE, animate));

            if (viewPart instanceof ILayoutConfigProvider) {
                extendedOptions.add(((ILayoutConfigProvider) viewPart).getLayoutConfig());
            }

            if (options != null && !options.isEmpty()) {
                extendedOptions.addAll(Collections2.filter(options, Predicates.notNull()));
            }

            final List<? extends ILayoutConfig> additionalConfigs =
                    viewContext.getAdditionalLayoutConfigs();

            if (additionalConfigs.isEmpty()) {
                DiagramLayoutEngine.INSTANCE.layout(viewPart, diagramViewer, extendedOptions);

            } else {
                final List<ILayoutConfig> configs = Lists.<ILayoutConfig>newArrayList(extendedOptions);
                configs.addAll(additionalConfigs);

                DiagramLayoutEngine.INSTANCE.layout(viewPart, diagramViewer,
                        Iterables.toArray(configs, ILayoutConfig.class));
            }
        } else {
            if (diagramViewer instanceof ILayoutRecorder) {
                ((ILayoutRecorder) diagramViewer).stopRecording(zoomStyle, 0);
            }
        }
    }


    /**
     * Performs zoom on the diagram represented by the given view context based on the current
     * {@link ZoomStyle} defined for the view context.
     * 
     * The configurations of 'animate' is taken from the preference settings.
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     */
    public static void zoomDiagram(final ViewContext viewContext) {
        
        if (viewContext == null) {
            return;
        }
        
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean animate = preferenceStore.getBoolean(KlighdPreferences.ANIMATE_LAYOUT);

        if (viewContext.getZoomStyle() != ZoomStyle.NONE) {
            viewContext.getViewer().zoom(viewContext.getZoomStyle(),
                    animate ? KlighdConstants.DEFAULT_ANIMATION_TIME : 0);
        }
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

        // check that we can obtain all information we need
        if (viewPart == null || viewPart.getViewer() == null
                || viewPart.getViewer().getViewContext() == null) {
            return;
        }
        
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean animate = preferenceStore.getBoolean(KlighdPreferences.ANIMATE_LAYOUT);
        final ViewContext viewContext = viewPart.getViewer().getViewContext();

        if (viewContext.getZoomStyle() != ZoomStyle.NONE) {
            viewContext.getViewer().zoom(viewContext.getZoomStyle(),
                    animate ? KlighdConstants.DEFAULT_ANIMATION_TIME : 0);
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
     *            <code>model</code>
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
     *            <code>model</code>
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
        final ViewContext viewContext = translateModel2(model, null);

        // if no corresponding diagram synthesis is available and, thus, no diagram has been created... 
        if (viewContext.getViewModel() == null
                || viewContext.getViewModel().getChildren().isEmpty()) {
            // skip the rendering call and return
            return null;
        }

        // finally render the diagram and return the result
        final IStatus result = renderer.render(viewContext, output, properties);
        return result;
    }
}
