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
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.config.CompoundLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.klighd.internal.preferences.KlighdPreferences;
import de.cau.cs.kieler.klighd.transformations.ReinitializingTransformationProxy;
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

    /** the property for a viewer provider. */
    public static final IProperty<String> REQUESTED_VIEWER_PROVIDER = new Property<String>(
            "klighd.viewerProvider");
    /** the property for a path of transformations (can contain gaps). */
    public static final IProperty<List<String>> REQUESTED_TRANSFORMATIONS = new Property<List<String>>(
            "klighd.transformations", new LinkedList<String>());
    /** the property for an update strategy. */
    public static final IProperty<String> REQUESTED_UPDATE_STRATEGY = new Property<String>(
            "klighd.updateStrategy");

    /** the property for a viewer associated with the view context. */
    public static final IProperty<IViewer<?>> VIEWER = new Property<IViewer<?>>("klighd.viewer");

    /** the singleton instance. */
    private static LightDiagramServices instance = new LightDiagramServices();

    /**
     * A private constructor to prevent instantiation.
     */
    private LightDiagramServices() {
        // do nothing
    }

    /**
     * Returns the singleton instance.
     * 
     * @return the singleton
     */
    public static LightDiagramServices getInstance() {
        return instance;
    }

    /**
     * Creates a view context for the given model if possible.
     * 
     * @param model
     *            the model
     * @return the view context or null if the model and all possible transformations are
     *         unsupported by all viewer providers
     */
    public ViewContext createViewContext(final Object model) {
        ViewContext viewContext = new ViewContext();
        if (KlighdDataManager.getInstance().getTransformationsGraph()
                .configureViewContext(viewContext, model, null)) {
            return viewContext;
        } else {
            return null;
        }
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
    public ViewContext createViewContext(final Object model,
            final IPropertyHolder... propertyHolders) {
        KlighdDataManager dataManager = KlighdDataManager.getInstance();
        TransformationsGraph transformationsGraph = dataManager.getTransformationsGraph();
        ViewContext viewContext = new ViewContext();

        // copy the properties to the view context
        for (IPropertyHolder propertyHolder : propertyHolders) {
            viewContext.copyProperties(propertyHolder);
        }

        // chsch: obtain the instructions on the viewer provider and transformations to
        // be used that might have been added to the viewContext by the foregoing for-loop

        // get the viewer provider request
        String viewerProviderId = viewContext.getProperty(REQUESTED_VIEWER_PROVIDER);
        IViewerProvider<?> viewerProvider = dataManager.getViewerProviderById(viewerProviderId);

        // get the transformations request
        List<String> transformationIds = viewContext.getProperty(REQUESTED_TRANSFORMATIONS);
        ITransformation<?, ?>[] transformations = getTransformationsById(transformationIds);
        if (transformations == null) {
            return null;
        }

        // get the update strategy request
        String updateStrategyId = viewContext.getProperty(REQUESTED_UPDATE_STRATEGY);
        IUpdateStrategy<?> updateStrategy = dataManager.getUpdateStrategyById(updateStrategyId);

        // call the fitting configure method on the transformations graph
        boolean success;
        if (viewerProvider != null) {
            if (transformations.length > 0) {
                // viewer and transformations hint
                success = transformationsGraph.configureViewContext(viewContext, model,
                        viewerProvider, updateStrategy, transformations);
            } else {
                // viewer hint
                success = transformationsGraph.configureViewContext(viewContext, model,
                        viewerProvider, updateStrategy);
            }
        } else if (viewerProviderId == null) {
            if (transformations.length > 0) {
                // transformations hint
                success = transformationsGraph.configureViewContext(viewContext, model,
                        updateStrategy, transformations);
            } else {
                // no hints
                success = transformationsGraph.configureViewContext(viewContext, model,
                        updateStrategy);
            }
        } else {
            return null;
        }

        // on success return the view context, otherwise return null
        if (success) {
            return viewContext;
        } else {
            return null;
        }
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
    public boolean updateViewContext(final ViewContext viewContext, final Object model,
            final IPropertyHolder... propertyHolders) {
        // copy the properties to the view context
        for (IPropertyHolder propertyHolder : propertyHolders) {
            viewContext.copyProperties(propertyHolder);
        }

        // clear out the mapping data of the involved transformation contexts
        viewContext.clearSourceTargetMappings();

        // re-run the involved transformations
        Object viewModel = performTransformations(viewContext, model);
        if (viewModel == null) {
            return false;
        }

        // use update strategy if possible
        if (viewContext.getUpdateStrategy() != null) {
            @SuppressWarnings("unchecked")
            IUpdateStrategy<Object> updateStrategy = (IUpdateStrategy<Object>) viewContext
                    .getUpdateStrategy();
            try {
                updateStrategy.update(viewContext.getViewModel(), viewModel, viewContext);
            } catch (Exception e) {
                StatusManager.getManager().handle(
                        new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID,
                                "KLighD: LightDiagramService failed to update a view context:\n"
                                        + e.getClass().getSimpleName()
                                        + " occured while performing "
                                        + updateStrategy.getClass().getSimpleName() + ":\n"
                                        + e.getMessage(), e), StatusManager.LOG);
                return false;
            }
        } else {
            // if no update strategy is present just set the new model into the viewer
            IViewer<?> viewer = viewContext.getProperty(VIEWER);
            if (viewer != null) {
                @SuppressWarnings("unchecked")
                IViewer<Object> objViewer = (IViewer<Object>) viewer;
                try {
                    objViewer.setModel(viewModel, true);
                } catch (Exception e) {
                    StatusManager.getManager().handle(
                            new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID,
                                    "KLighD: LightDiagramService failed to update a view context:\n"
                                            + e.getClass().getSimpleName() + " updating "
                                            + objViewer.getClass().getSimpleName() + ":\n"
                                            + e.getMessage(), e), StatusManager.LOG);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Creates a viewer instance with the viewer provider associated with the view context into the
     * given parent composite and sets the base model of the view context into that viewer if
     * possible.<br>
     * <br>
     * chsch: added parentViewer parameter as it is needed e.g. in the KlighdActionEventHandler
     * 
     * @param parentViewer
     *            the parent {@link ContextViewer}
     * @param viewContext
     *            the view context
     * @param parent
     *            the parent composite
     * @return the created viewer or null on failure
     */
    public IViewer<?> createViewer(final ContextViewer parentViewer, final ViewContext viewContext,
            final Composite parent) {
        IViewerProvider<?> viewerProvider = viewContext.getViewerProvider();
        if (viewerProvider != null) {
            // create a new viewer
            IViewer<?> viewer = viewerProvider.createViewer(parentViewer, parent);
            // remember the created viewer in a property
            viewContext.setProperty(VIEWER, viewer);
            // set the base model if possible
            if (viewContext.getViewModel() != null) {
                @SuppressWarnings("unchecked")
                IViewer<Object> objViewer = (IViewer<Object>) viewer;
                objViewer.setModel(viewContext.getViewModel(), true);
            }
            return viewer;
        }
        return null;
    }

    /**
     * Returns the array of transformations defined by the list of given transformation ids.
     * 
     * @param transformationIds
     *            the list of transformation ids
     * @return the array of transformations or null if a transformation could not be resolved
     */
    private ITransformation<?, ?>[] getTransformationsById(final List<String> transformationIds) {
        LinkedList<ITransformation<?, ?>> transformations = new LinkedList<ITransformation<?, ?>>();
        if (transformationIds.size() > 0) {
            for (String transformationId : transformationIds) {
                ITransformation<?, ?> transformation = KlighdDataManager.getInstance()
                        .getTransformationById(transformationId);
                if (transformation != null) {
                    transformations.add(transformation);
                } else {
                    return null;
                }
            }
        }
        return transformations.toArray(new ITransformation<?, ?>[0]);
    }

    /**
     * Performs the transformations in the view context for the given source model.
     * 
     * @param viewContext
     *            the view context
     * @param model
     *            the source model
     * @return the target model
     */
    private Object performTransformations(final ViewContext viewContext, final Object model) {
        Object currentModel = model;
        for (TransformationContext<?, ?> transformationContext : viewContext
                .getTransformationContexts()) {
            @SuppressWarnings("unchecked")
            TransformationContext<Object, Object> objTransformationContext =
                    (TransformationContext<Object, Object>) transformationContext;
            ITransformation<Object, Object> transformation = objTransformationContext
                    .getTransformation();
            try {
                currentModel = transformation.transform(currentModel, objTransformationContext);
            } catch (Exception e) {
                if (transformation instanceof ReinitializingTransformationProxy<?, ?>) {
                    transformation = ((ReinitializingTransformationProxy<Object, Object>) transformation)
                            .getDelegate();
                }
                StatusManager
                        .getManager()
                        .handle(new Status(
                                IStatus.ERROR,
                                KlighdPlugin.PLUGIN_ID,
                                "KLighD: LightDiagramService failed to update a view context:\n"
                                        + e.getClass().getSimpleName()
                                        + " occured while performing the transformation "
                                        + transformation.getClass().getSimpleName()
                                        + ":\n"
                                        + e.getMessage()
                                        + "\n Please perform a 'Clean' operation on your project"
                                        + " and re-try.",
                                e), StatusManager.LOG);
                return null;
            }
        }
        return currentModel;
    }

    
    /**
     * Performs the automatic layout on the diagram represented by the given view context.<br>
     * <br>
     * The configurations of 'animate' and 'zoomToFit' are taken from the preference settings. 
     * 
     * @param viewContext
     *            the viewContext whose diagram diagram is to be arranged
     */
    public void layoutDiagram(final ViewContext viewContext) {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean animate = preferenceStore.getBoolean(KlighdPreferences.ANIMATE_LAYOUT);
        final boolean zoomToFit = preferenceStore.getBoolean(KlighdPreferences.ZOOM_TO_FIT);
        
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
    public void layoutDiagram(final ViewContext viewContext, final boolean animate) {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean zoomToFit = preferenceStore.getBoolean(KlighdPreferences.ZOOM_TO_FIT);

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
    public void layoutDiagram(final ViewContext viewContext, final boolean animate,
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
    public void layoutDiagram(final ViewContext viewContext, final List<ILayoutConfig> options) {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean animate = preferenceStore.getBoolean(KlighdPreferences.ANIMATE_LAYOUT);
        final boolean zoomToFit = preferenceStore.getBoolean(KlighdPreferences.ZOOM_TO_FIT);
        
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
    public void layoutDiagram(final ViewContext viewContext, final boolean animate,
           final List<ILayoutConfig> options) {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean zoomToFit = preferenceStore.getBoolean(KlighdPreferences.ZOOM_TO_FIT);
        
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
    public void layoutDiagram(final ViewContext viewContext, final boolean animate,
            final boolean zoomToFit, final List<ILayoutConfig> options) {
        @SuppressWarnings("unchecked")
        IViewer<? extends EObject> diagramViewer = (IViewer<? extends EObject>) viewContext
                .getProperty(LightDiagramServices.VIEWER);
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
    public void layoutDiagram(final IDiagramWorkbenchPart viewPart) {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean animate = preferenceStore.getBoolean(KlighdPreferences.ANIMATE_LAYOUT);
        final boolean zoomToFit = preferenceStore.getBoolean(KlighdPreferences.ZOOM_TO_FIT);
        
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
    public void layoutDiagram(final IDiagramWorkbenchPart viewPart, final boolean animate) {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean zoomToFit = preferenceStore.getBoolean(KlighdPreferences.ZOOM_TO_FIT);
        
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
    public void layoutDiagram(final IDiagramWorkbenchPart viewPart, final boolean animate,
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
    public void layoutDiagram(final IDiagramWorkbenchPart viewPart,
            final List<ILayoutConfig> options) {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean animate = preferenceStore.getBoolean(KlighdPreferences.ANIMATE_LAYOUT);
        final boolean zoomToFit = preferenceStore.getBoolean(KlighdPreferences.ZOOM_TO_FIT);
        
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
    public void layoutDiagram(final IDiagramWorkbenchPart viewPart, final boolean animate,
            final List<ILayoutConfig> options) {
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();
        final boolean zoomToFit = preferenceStore.getBoolean(KlighdPreferences.ZOOM_TO_FIT);
        
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
    public void layoutDiagram(final IDiagramWorkbenchPart viewPart, final boolean animate,
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
    public void layoutDiagram(final IDiagramWorkbenchPart viewPart,
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
        
        final KNode viewModel = (KNode) contextViewer.getCurrentViewContext().getViewModel();
        final KLayoutData layoutData = viewModel != null ? viewModel.getData(KLayoutData.class) : null;

        if (layoutData != null && !layoutData.getProperty(LayoutOptions.NO_LAYOUT)) {
            final List<ILayoutConfig> extendedOptions;
            if (options == null || options.isEmpty()) {
                extendedOptions = Collections.<ILayoutConfig>singletonList(
                        contextViewer.getLightLayoutConfig());
            } else {
                CompoundLayoutConfig compound = new CompoundLayoutConfig();
                compound.addAll(options);
                compound.add(contextViewer.getLightLayoutConfig());
                extendedOptions = Collections.<ILayoutConfig>singletonList(compound);
            }
            DiagramLayoutEngine.INSTANCE.layout(viewPart, diagramViewer, animate, false, false,
                    zoomToFit, extendedOptions);
        } else {
            diagramViewer.setRecording(false);
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
        ViewContext vc = LightDiagramServices.getInstance().createViewContext(model, propertyHolders);
        
        if (vc == null) {
            throw new IllegalStateException("Could not create a View Context for the model "
                    + ". This might be due to a missing transformation.");
        }

        LightDiagramServices.getInstance().updateViewContext(vc, model);
        @SuppressWarnings("unchecked")
        T result = (T) vc.getViewModel();
        if (otherVC != null) {
            otherVC.merge(vc);
        }
        return result;
    }
}
