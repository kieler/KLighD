/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Bundle;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.klighd.transformations.XtendBasedTransformation;

/**
 * Singleton for accessing the light diagram services.
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

    /** identifier of the extension point for viewer providers. */
    public static final String EXTP_ID_VIEWER_PROVIDERS = "de.cau.cs.kieler.klighd.viewerProviders";
    /** identifier of the extension point for model transformations. */
    public static final String EXTP_ID_MODEL_TRANSFORMATIONS =
            "de.cau.cs.kieler.klighd.modelTransformations";
    /** identifier of the extension point for update strategies. */
    public static final String EXTP_ID_UPDATE_STRATEGIES = "de.cau.cs.kieler.klighd.updateStrategy";

    /** name of the 'viewer' element. */
    public static final String ELEMENT_VIEWER = "viewer";
    /** name of the 'transformation' element. */
    public static final String ELEMENT_TRANSFORMATION = "transformation";
    /** name of the 'xtendBasedTransformation' element. */
    public static final String ELEMENT_XTEND_BASED_TRANSFORMATION = "xtendBasedTransformation";
    /** name of the 'updateStrategy' element. */
    public static final String ELEMENT_UPDATE_STRATEGY = "updateStrategy";

    /** name of the 'id' attribute in the extension points. */
    public static final String ATTRIBUTE_ID = "id";
    /** name of the 'class' attribute in the extension points. */
    public static final String ATTRIBUTE_CLASS = "class";
    /** name of the 'extFile' attribute in the extension points. */
    public static final String ATTRIBUTE_EXTENSION_FILE = "extFile";
    /** name of the 'extension' attribute in the extension points. */
    public static final String ATTRIBUTE_EXTENSION = "extension";
    /** name of the 'EPackage' attribute in the extension points. */
    public static final String ATTRIBUTE_EPACKAGE = "EPackage";
    /** name of the 'EPackageClass' attribute in the extension points. */
    public static final String ATTRIBUTE_EPACKAGE_CLASS = "EPackageClass";
    
    /** error msg if registered class cannot be found. */
    private static final String NO_CLASS_DEF_FOUND_ERROR_MSG = 
            "The class definition <<CLAZZ>> cannot be found. Is the (maybe generated) code available?";

    /** the singleton instance. */
    private static LightDiagramServices instance;

    /** the transformations graph used to manage transformations and viewer providers. */
    private TransformationsGraph transformationsGraph = new TransformationsGraph();

    /** the mapping of ids on the associated update strategies. */
    private Map<String, IUpdateStrategy<?>> idUpdateStrategyMapping = Maps.newHashMap();

    /**
     * A private constructor to prevent instantiation.
     */
    private LightDiagramServices() {
        // do nothing
    }

    /**
     * Creates the singleton and initializes it with the data from the extension point.
     */
    static {
        instance = new LightDiagramServices();
        // load the data from the extension points
        instance.loadViewerProviderExtension();
        instance.loadModelTransformationsExtension();
        instance.loadUpdateStrategyExtension();
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
     * Reports an error that occurred while reading extensions.
     * 
     * @param extensionPoint
     *            the identifier of the extension point
     * @param element
     *            the configuration element
     * @param attribute
     *            the attribute that contains an invalid entry
     * @param exception
     *            an optional exception that was caused by the invalid entry
     */
    private static void reportError(final String extensionPoint,
            final IConfigurationElement element, final String attribute, final Exception exception) {
        String message = "Extension point " + extensionPoint + ": Invalid entry in attribute '"
                + attribute + "' of element " + element.getName() + ", contributed by "
                + element.getContributor().getName();
        IStatus status = new Status(IStatus.WARNING, KlighdPlugin.PLUGIN_ID, 0, message, exception);
        StatusManager.getManager().handle(status);
    }

    /**
     * Loads and registers all viewer provider from the extension point.
     */
    private void loadViewerProviderExtension() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_VIEWER_PROVIDERS);
        for (IConfigurationElement element : extensions) {
            try {
                if (ELEMENT_VIEWER.equals(element.getName())) {
                    // initialize viewer provider from the extension point
                    IViewerProvider<?> viewerProvider = (IViewerProvider<?>) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (viewerProvider != null) {
                        String id = element.getAttribute(ATTRIBUTE_ID);
                        if (id == null || id.length() == 0) {
                            reportError(EXTP_ID_VIEWER_PROVIDERS, element, ATTRIBUTE_ID, null);
                        } else {
                            transformationsGraph.addViewerProvider(id, viewerProvider);
                        }
                    }
                }
            } catch (CoreException exception) {
                StatusManager.getManager().handle(exception, KlighdPlugin.PLUGIN_ID);
            }
        }
    }

    private HashMap<String, EPackage> ePackages = new HashMap<String, EPackage>();

    /**
     * Loads and registers all model transformations from the extension point.
     */
    private void loadModelTransformationsExtension() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_MODEL_TRANSFORMATIONS);
        for (IConfigurationElement element : extensions) {
            if (ELEMENT_TRANSFORMATION.equals(element.getName())) {
                // initialize model transformation from the extension point
                ITransformation<Object, ?> modelTransformation = null;
                try {
                    // temp var is only used for the SuppressWarnings declaration
                    @SuppressWarnings("unchecked")
                    ITransformation<Object, ?> temp = (ITransformation<Object, ?>) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    modelTransformation = temp;
                } catch (CoreException exception) {
                    StatusManager.getManager().handle(exception, KlighdPlugin.PLUGIN_ID);
                } catch (NoClassDefFoundError exception) {
                    StatusManager.getManager().handle(
                            new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID,
                                    NO_CLASS_DEF_FOUND_ERROR_MSG.replace("<<CLAZZ>>",
                                            element.getAttribute(ATTRIBUTE_CLASS)), exception));
                }
                if (modelTransformation != null) {
                    String id = element.getAttribute(ATTRIBUTE_ID);
                    if (id == null || id.length() == 0) {
                        reportError(EXTP_ID_MODEL_TRANSFORMATIONS, element, ATTRIBUTE_ID, null);
                    } else {
                        transformationsGraph.addModelTransformation(id, modelTransformation);
                    }
                }
            } else if (ELEMENT_XTEND_BASED_TRANSFORMATION.equals(element.getName())) {
                //
                // handle xtendBasedTransformation extensions
                //
                String id = element.getAttribute(ATTRIBUTE_ID);
                String extFile = element.getAttribute(ATTRIBUTE_EXTENSION_FILE);
                String extension = element.getAttribute(ATTRIBUTE_EXTENSION);
                Bundle contributingBundle = Platform.getBundle(element.getContributor().getName());
                String coContributingBundlesName = null;

                int index = extFile.indexOf("::");
                if (index != -1) {
                    // in case the extFile's path contain '::' it may involve an
                    // external co-contributing bundle indicated by a '/' in the path
                    index = extFile.indexOf("/");
                    if (index != -1) {
                        // there is a '/'!
                        // determine the co-contributing bundle's name...
                        coContributingBundlesName = extFile.substring(0, index);
                        // ... as well as the actual path of the extFile
                        extFile = extFile.substring(index + 1);
                    }
                }

                // "normalize" the Xtend file path
                extFile = extFile.replaceAll("::", "/");
                if (!extFile.endsWith(".ext")) {
                    extFile = extFile + ".ext";
                }

                URL extFileURL = null;
                if (contributingBundle != null) {
                    // try to reveal the Xtend file in the bundle the extension is declared in
                    extFileURL = contributingBundle.getEntry(extFile);

                    if (extFileURL == null) {
                        extFileURL = contributingBundle.getEntry("src/" + extFile);
                    }

                    if (extFileURL == null) {
                        extFileURL = contributingBundle.getEntry("transformations/" + extFile);
                    }

                    // in case a the Xtend file is located in a bundle different from
                    // 'contributingBundle' try to reveal that bundle and the Xtend file by
                    // means of the 'contributingBundle' entry in the extension (refered to as
                    // coContributingBundlesName)
                    // this, however, should not be used extensively but is helpful during the
                    // prototyping state
                    if (extFileURL == null && coContributingBundlesName != null
                            && !coContributingBundlesName.equals("")) {
                        Bundle coContributingBundle = Platform.getBundle(coContributingBundlesName);
                        extFileURL = coContributingBundle.getEntry(extFile);

                        if (extFileURL == null) {
                            extFileURL = coContributingBundle.getEntry("src/" + extFile);
                        }

                        if (extFileURL == null) {
                            extFileURL = coContributingBundle
                                    .getEntry("transformations/" + extFile);
                        }
                    }
                }

                if (extFileURL == null) {
                    continue;
                }

                // chsch: tried to avoid this and infer the meta models (EPackages) from the EMF
                // registry. Doesn't works since the EPackages are registered lazily. Non-used
                // EPackages are represented by a proxy that is addressed by means of the NSURI
                // (which in turn is not inferable based on the short name mentioned in Xtend
                // transformations).
                List<EPackage> metamodels = new ArrayList<EPackage>();

                for (IConfigurationElement epackageDecl : element.getChildren(ATTRIBUTE_EPACKAGE)) {

                    String ePackageClassName = epackageDecl.getAttribute(ATTRIBUTE_EPACKAGE_CLASS);
                    EPackage ePackageInstance = ePackages.get(ePackageClassName);

                    if (ePackageInstance == null) {
                        try {

                            Class<?> ePackage = contributingBundle.loadClass(ePackageClassName);
                            ePackageInstance = (EPackage) ePackage.getField("eINSTANCE").get(null);
                            this.ePackages.put(ePackageClassName, ePackageInstance);
                        } catch (Exception e) {
                            String msg = "EPackage " + ePackageClassName + " could not be loaded";
                            StatusManager.getManager().addLoggedStatus(
                                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg, e));
                            continue;
                        }
                    }

                    if (ePackageInstance != null) {
                        metamodels.add(ePackageInstance);
                    }

                }
                
                // hack (abuse of runtime type erasure)
                ITransformation<?, ?> modelTransformationTmp = new XtendBasedTransformation(
                        extFileURL, extension, metamodels);
                @SuppressWarnings("unchecked")
                ITransformation<Object, ?> modelTransformation =
                        (ITransformation<Object, ?>) modelTransformationTmp;
                transformationsGraph.addModelTransformation(id, modelTransformation);
                
            }
        }
    }

    /**
     * Loads and registers all update strategies from the extension point.
     */
    private void loadUpdateStrategyExtension() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_UPDATE_STRATEGIES);
        for (IConfigurationElement element : extensions) {
            try {
                if (ELEMENT_UPDATE_STRATEGY.equals(element.getName())) {
                    // initialize update strategy from the extension point
                    IUpdateStrategy<?> updateStrategy = (IUpdateStrategy<?>) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (updateStrategy != null) {
                        String id = element.getAttribute(ATTRIBUTE_ID);
                        if (id == null || id.length() == 0) {
                            reportError(EXTP_ID_UPDATE_STRATEGIES, element, ATTRIBUTE_ID, null);
                        } else {
                            idUpdateStrategyMapping.put(id, updateStrategy);
                        }
                    }
                }
            } catch (CoreException exception) {
                StatusManager.getManager().handle(exception, KlighdPlugin.PLUGIN_ID);
            }
        }
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
        if (transformationsGraph.configureViewContext(viewContext, model)) {
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
        ViewContext viewContext = new ViewContext();

        // copy the properties to the view context
        for (IPropertyHolder propertyHolder : propertyHolders) {
            viewContext.copyProperties(propertyHolder);
        }

        // get the viewer provider request
        String viewerProviderId = viewContext.getProperty(REQUESTED_VIEWER_PROVIDER);
        IViewerProvider<?> viewerProvider = transformationsGraph
                .getViewerProviderById(viewerProviderId);

        // get the transformations request
        List<String> transformationIds = viewContext.getProperty(REQUESTED_TRANSFORMATIONS);
        ITransformation<?, ?>[] transformations = getTransformationsById(transformationIds);
        if (transformations == null) {
            return null;
        }

        // call the fitting configure method on the transformations graph
        boolean success;
        if (viewerProvider != null) {
            if (transformations.length > 0) {
                // viewer and transformations hint
                success = transformationsGraph.configureViewContext(viewContext, model,
                        viewerProvider, transformations);
            } else {
                // viewer hint
                success = transformationsGraph.configureViewContext(viewContext, model,
                        viewerProvider);
            }
        } else if (viewerProviderId == null) {
            if (transformations.length > 0) {
                // transformations hint
                success = transformationsGraph.configureViewContext(viewContext, model,
                        transformations);
            } else {
                // no hints
                success = transformationsGraph.configureViewContext(viewContext, model);
            }
        } else {
            return null;
        }

        // on success return the view context, otherwise return null
        if (success) {
            // get the update strategy request
            String updateStrategyId = viewContext.getProperty(REQUESTED_UPDATE_STRATEGY);
            IUpdateStrategy<?> updateStrategy = idUpdateStrategyMapping.get(updateStrategyId);
            if (updateStrategy != null) {
                Class<?> targetClass = viewContext.getTargetClass();
                // set the update strategy if it is compatible with the transformations
                if (targetClass.equals(updateStrategy.getModelClass())) {
                    viewContext.setUpdateStrategy(updateStrategy);
                }
            }

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
                    objViewer.setModel(newModel);
                }
            }
            return true;
        } catch (Exception e) {
            StatusManager.getManager().addLoggedStatus(
                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID,
                            "Failed to update view context", e));
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
                ITransformation<?, ?> transformation = transformationsGraph
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
            currentModel = transformation.transform(currentModel, objTransformationContext);
        }
        return currentModel;
    }

}
