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
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Bundle;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.klighd.transformations.XtendBasedTransformation;

/**
 * Singleton for accessing transformations, viewers and update strategies registered with KLighD.
 * 
 * @author mri
 */
public final class KlighdDataManager {

    /** identifier of the extension point for viewer providers. */
    public static final String EXTP_ID_VIEWER_PROVIDERS = "de.cau.cs.kieler.klighd.viewerProviders";
    /** identifier of the extension point for model transformations. */
    public static final String EXTP_ID_MODEL_TRANSFORMATIONS =
            "de.cau.cs.kieler.klighd.modelTransformations";
    /** identifier of the extension point for update strategies. */
    public static final String EXTP_ID_UPDATE_STRATEGIES = "de.cau.cs.kieler.klighd.updateStrategies";

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
    private static KlighdDataManager instance;

    /** the transformations graph used to manage transformations and viewer providers. */
    private TransformationsGraph transformationsGraph = new TransformationsGraph();

    /** the mapping of ids on the associated transformations. */
    private Map<String, ITransformation<?, ?>> idTransformationMapping = Maps.newHashMap();
    /** the mapping of ids on the associated viewer providers. */
    private Map<String, IViewerProvider<?>> idViewerProviderMapping = Maps.newHashMap();
    /** the mapping of ids on the associated update strategies. */
    private Map<String, IUpdateStrategy<?>> idUpdateStrategyMapping = Maps.newHashMap();

    /**
     * A private constructor to prevent instantiation.
     */
    private KlighdDataManager() {
        // do nothing
    }

    /**
     * Creates the singleton and initializes it with the data from the extension point.
     */
    static {
        instance = new KlighdDataManager();
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
    public static KlighdDataManager getInstance() {
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
                            transformationsGraph.addViewerProvider(viewerProvider);
                            idViewerProviderMapping.put(id, viewerProvider);
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
                        transformationsGraph.addTransformation(modelTransformation);
                        idTransformationMapping.put(id, modelTransformation);
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
                transformationsGraph.addTransformation(modelTransformation);
                idTransformationMapping.put(id, modelTransformation);
                
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
     * Returns the graph containing the registered transformations.
     * 
     * @return the transformations graph
     */
    public TransformationsGraph getTransformationsGraph() {
        return transformationsGraph;
    }

    /**
     * Returns the transformation with the given identifier.
     * 
     * @param id
     *            the identifier
     * @return the transformation or null if there is no transformation with the given id
     */
    public ITransformation<?, ?> getTransformationById(final String id) {
        if (id == null) {
            return null;
        }
        return idTransformationMapping.get(id);
    }

    /**
     * Returns the viewer provider with the given identifier.
     * 
     * @param id
     *            the identifier
     * @return the viewer provider or null if there is no viewer provider with the given id
     */
    public IViewerProvider<?> getViewerProviderById(final String id) {
        if (id == null) {
            return null;
        }
        return idViewerProviderMapping.get(id);
    }
    
    /**
     * Returns the update strategy with the given identifier.
     * 
     * @param id
     *            the identifier
     * @return the update strategy
     */
    public IUpdateStrategy<?> getUpdateStrategyById(final String id) {
        return idUpdateStrategyMapping.get(id);
    }
    
}
