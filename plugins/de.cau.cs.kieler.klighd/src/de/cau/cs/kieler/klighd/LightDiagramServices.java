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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;

/**
 * Singleton for accessing basic KLighD services.
 * 
 * @author mri
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
                success =
                        transformationsGraph.configureViewContext(viewContext, model,
                                updateStrategy, transformations);
            } else {
                // no hints
                success =
                        transformationsGraph.configureViewContext(viewContext, model,
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

        // update the view context
        try {
            Object newModel = performTransformations(viewContext, model);
            // use update strategy if possible
            if (viewContext.getUpdateStrategy() != null) {
                @SuppressWarnings("unchecked")
                IUpdateStrategy<Object> updateStrategy = (IUpdateStrategy<Object>) viewContext
                        .getUpdateStrategy();
                updateStrategy.update(viewContext.getBaseModel(), newModel, viewContext);
            } else {
                // if no update strategy is present just set the new model into the viewer
                IViewer<?> viewer = viewContext.getProperty(VIEWER);
                if (viewer != null) {
                    @SuppressWarnings("unchecked")
                    IViewer<Object> objViewer = (IViewer<Object>) viewer;
                    objViewer.setModel(newModel, true);
                }
            }
            return true;
        } catch (Exception e) {
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID,
                            "KLighD: LightDiagramService failed to update a view context.", e),
                    StatusManager.LOG);
        }
        return false;
    }

    /**
     * Creates a viewer instance with the viewer provider associated with the view context into the
     * given parent composite and sets the base model of the view context into that viewer if
     * possible.
     * 
     * @param viewContext
     *            the view context
     * @param parent
     *            the parent composite
     * @return the created viewer or null on failure
     */
    public IViewer<?> createViewer(final ViewContext viewContext, final Composite parent) {
        IViewerProvider<?> viewerProvider = viewContext.getViewerProvider();
        if (viewerProvider != null) {
            // create a new viewer
            IViewer<?> viewer = viewerProvider.createViewer(parent);
            // remember the created viewer in a property
            viewContext.setProperty(VIEWER, viewer);
            // set the base model if possible
            if (viewContext.getBaseModel() != null) {
                @SuppressWarnings("unchecked")
                IViewer<Object> objViewer = (IViewer<Object>) viewer;
                objViewer.setModel(viewContext.getBaseModel(), true);
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
                ITransformation<?, ?> transformation =
                        KlighdDataManager.getInstance().getTransformationById(transformationId);
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
            currentModel = transformation.transform(currentModel, objTransformationContext);
        }
        return currentModel;
    }
    
}
