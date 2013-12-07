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

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.kiml.config.CompoundLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.service.KimlServicePlugin;
import de.cau.cs.kieler.klighd.internal.ILayoutRecorder;
import de.cau.cs.kieler.klighd.internal.preferences.KlighdPreferences;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import de.cau.cs.kieler.klighd.views.DiagramViewManager;
import de.cau.cs.kieler.klighd.views.DiagramViewPart;
import de.cau.cs.kieler.klighd.views.IDiagramWorkbenchPart;

/**
 * Singleton for accessing basic KLighD services.
 * 
 * @author mri
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
     * Creates a view context for the given model if possible.
     * 
     * @param model
     *            the model
     * @return the view context or null if the model and all possible transformations are
     *         unsupported by all viewer providers
     */
    public static ViewContext createViewContext(final Object model) {
        return new ViewContext(model).configure();
    }


    /**
     * Creates a view context for the given model if possible. The properties from the given
     * property holders are copied to the view context.
     * 
     * @param model
     *            the model
     * @param propertyHolders
     *            the property holders
     * @return the view context or null if the model and all possible transformations are
     *         unsupported by all viewer providers
     */
    public static ViewContext createViewContext(final Object model,
            final IPropertyHolder... propertyHolders) {
        final ViewContext context = new ViewContext(model);
        
        if (propertyHolders != null) {
            final KlighdSynthesisProperties ksp = KlighdSynthesisProperties.newInstance();
            
            for (IPropertyHolder h : propertyHolders) {
                ksp.copyProperties(h);
            }

            context.configure(ksp);
            
        } else {
            context.configure();
        }
        
        return context;
    }


    /**
     * Updates the view context with the given model. The properties from the given property holders
     * are copied to the view context.
     * 
     * @param viewContext
     *            the view context
     * @param model
     *            the model
     * @param propertyHolders
     *            the property holders
     * @return true if the view context has been updated successfully; false else
     */
    public static boolean updateViewContext(final ViewContext viewContext, final Object model,
            final IPropertyHolder... propertyHolders) {
        final IUpdateStrategy<KNode> updateStrategy;
        
        if (propertyHolders != null) {
            final KlighdSynthesisProperties ksp = KlighdSynthesisProperties.newInstance();
            for (IPropertyHolder h : propertyHolders) {
                ksp.copyProperties(h);
            }
            final String usId = ksp.getProperty(KlighdSynthesisProperties.REQUESTED_UPDATE_STRATEGY);
            updateStrategy = KlighdDataManager.getInstance().getUpdateStrategyById(usId);
            
        } else {
            updateStrategy = null;
        }
        
        viewContext.update(model, updateStrategy);
        return true;
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
        IViewer<KNode> diagramViewer = viewContext.getViewer();
        DiagramViewPart viewPart = DiagramViewManager.getInstance().getView(
                diagramViewer.getContextViewer().getViewPartId());
        
        layoutDiagram(viewPart, diagramViewer, animate, zoomToFit, options);
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
        final boolean zoomToFit = viewPart.getContextViewer().getViewContext().isZoomToFit();
        
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
        if (viewPart.getContextViewer().getViewContext() != null) {
            zoomToFit = viewPart.getContextViewer().getViewContext().isZoomToFit();
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
        final boolean zoomToFit = viewPart.getContextViewer().getViewContext().isZoomToFit();
        
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
        final boolean zoomToFit = viewPart.getContextViewer().getViewContext().isZoomToFit();
        
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
     * @param options
     *            an optional list of layout options
     */
    public static void layoutDiagram(final IDiagramWorkbenchPart viewPart, final boolean animate,
            final boolean zoomToFit, final List<ILayoutConfig> options) {
        layoutDiagram(viewPart, viewPart.getContextViewer(), animate, zoomToFit,
                Collections.<ILayoutConfig>emptyList());
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
        
        final ContextViewer contextViewer;
        if (viewPart != null) {
            contextViewer = viewPart.getContextViewer();
        } else if (diagramViewer != null) {
            contextViewer = diagramViewer.getContextViewer();
        } else {
            return;
        }
        
        final KNode viewModel = (KNode) contextViewer.getViewContext().getViewModel();
        final KLayoutData layoutData = viewModel != null ? viewModel.getData(KLayoutData.class) : null;
        final ViewContext vc = contextViewer.getViewContext(); 
        
        if (layoutData != null) {
            // Activate the KIML Service plugin so all layout options are loaded
            KimlServicePlugin.getDefault();
            final CompoundLayoutConfig extendedOptions = new CompoundLayoutConfig();
            extendedOptions.add(new VolatileLayoutConfig()
                    .setValue(LayoutOptions.ANIMATE, animate)
                    .setValue(LayoutOptions.ZOOM_TO_FIT, zoomToFit));
            extendedOptions.add(contextViewer.getLightLayoutConfig());
            if (options != null && !options.isEmpty()) {
                extendedOptions.addAll(Collections2.filter(options, Predicates.notNull()));
            }
            DiagramLayoutEngine.INSTANCE.layout(viewPart, diagramViewer, extendedOptions);
        } else {
            ZoomStyle zoomStyle = ZoomStyle.create(zoomToFit, vc.isZoomToFocus());
            if (diagramViewer instanceof ILayoutRecorder) {
                ((ILayoutRecorder) diagramViewer).stopRecording(zoomStyle, 0);
            }
        }
    }


    /**
     * Translates the given <code>model</code> by means of the known diagram synthesis translations.
     * Incorporates constraints given in the <code>propertyHolders</code>.
     * @param model
     *            the model
     * @param otherVC
     *            the view context to merge mappings created while translating <code>model</code> into
     * @param propertyHolders
     *            the property holders
     * 
     * @param <T>
     *            the expected type of the result
     * @return the view context or null if the model and all possible transformations are
     *         unsupported by all viewer providers
     */
    public static <T> T translateModel(final Object model, final ViewContext otherVC,
            final IPropertyHolder... propertyHolders) {
        ViewContext vc = createViewContext(model, propertyHolders);
        
        if (vc == null) {
            throw new IllegalStateException("Could not create a View Context for the model "
                    + ". This might be due to a missing transformation.");
        }

        updateViewContext(vc, model);
        @SuppressWarnings("unchecked")
        T result = (T) vc.getViewModel();
        if (otherVC != null) {
            otherVC.merge(vc);
        }
        return result;
    }
}
