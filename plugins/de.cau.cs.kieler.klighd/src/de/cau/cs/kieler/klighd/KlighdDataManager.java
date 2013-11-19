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

import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.syntheses.GuiceBasedSynthesisFactory;

/**
 * Singleton for accessing transformations, viewers, update strategies and layout post processors
 * registered with KLighD.
 * 
 * @author mri, chsch, akoc
 */
public final class KlighdDataManager {

    /** identifier of the extension point for viewer providers. */
    public static final String EXTP_ID_EXTENSIONS = "de.cau.cs.kieler.klighd.extensions";
    
    /** identifier of the extension point for model transformations. */
    private static final String EXTP_ID_DIAGRAM_SYNTHESES = "de.cau.cs.kieler.klighd.diagramSyntheses";

    /** name of the 'viewer' element. */
    private static final String ELEMENT_VIEWER = "viewer";
    
    /** name of the 'transformation' element. */
    private static final String ELEMENT_DIAGRAM_SYNTHESIS = "diagramSynthesis";
    
    /** name of the 'updateStrategy' element. */
    private static final String ELEMENT_UPDATE_STRATEGY = "updateStrategy";
    
    /** name of the 'styleModifier' element. */
    private static final String ELEMENT_STYLE_MODIFIER = "styleModifier";

    /** name of the 'action' element. */
    private static final String ELEMENT_ACTION = "action";

    /** name of the 'id' attribute in the extension points. */
    private static final String ATTRIBUTE_ID = "id";
    
    /** name of the 'class' attribute in the extension points. */
    private static final String ATTRIBUTE_CLASS = "class";
    
    /** the platform-specific newline delimiter. */
    public static final String NEW_LINE = System.getProperty("line.separator");
    
    /** error message if registered class cannot be found. */
    private static final String CORE_EXCEPTION_ERROR_MSG = 
            "The class definition <<CLAZZ>> cannot be found. " + NEW_LINE
            + "Are there any typing mistakes in the registration? " + NEW_LINE
            + "Is the (maybe generated) code available? " + NEW_LINE
            + "If required, is the class name correctly prefixed with "
            + GuiceBasedSynthesisFactory.CLASS_NAME + "?";

    /** error msg if registered class cannot be found. */
    private static final String NO_CLASS_DEF_FOUND_ERROR_MSG = 
            "The class definition <<CLAZZ>> cannot be found. " + NEW_LINE
            + "Are there any typing mistakes in the registration? " + NEW_LINE
            + "Is the (maybe generated) code available?";

    /** the singleton instance. */
    private static KlighdDataManager instance;

    /** the transformations graph used to manage transformations and viewer providers. */
    private TransformationsGraph transformationsGraph = new TransformationsGraph();

    /** the mapping of ids on the associated transformations. */
    private Map<String, ITransformation<?, ?>> idTransformationMapping = Maps.newHashMap();
    
    /** the mapping of ids on the associated viewer providers. */
    private Map<String, IViewerProvider<KNode>> idViewerProviderMapping = Maps.newHashMap();
    
    /** the mapping of ids on the associated update strategies. */
    private Map<String, IUpdateStrategy<KNode>> idUpdateStrategyMapping = Maps.newHashMap();
    
    /** the mapping of ids on the associated style modifiers. */
    private Map<String, IStyleModifier> idStyleModifierMapping = Maps.newHashMap();

    /** the mapping of ids on the associated actions. */
    private BiMap<String, IAction> idActionMapping = HashBiMap.create();

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
        try {
            instance.loadModelTransformationsExtension();
        } catch (Exception e) {
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID,
                            "KLighD: Unexptected failure while loading registered transformations.", e));
        }
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
        String message =
                "KLighD: Element '" + element.getName() + "' extending extension point '"
                        + extensionPoint + "', contributed by '"
                        + element.getContributor().getName()
                        + "' contains invalid entry in attribute '" + attribute + "'";
        StatusManager.getManager().handle(
                new Status(IStatus.WARNING, KlighdPlugin.PLUGIN_ID, 0, message, exception));
    }

    /**
     * Loads and registers all viewer provider from the extension point.
     */
    private void loadViewerProviderExtension() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_EXTENSIONS);
        for (IConfigurationElement element : extensions) {
            try {
                if (ELEMENT_VIEWER.equals(element.getName())) {
                    // initialize viewer provider from the extension point
                    @SuppressWarnings("unchecked")
                    IViewerProvider<KNode> viewerProvider = (IViewerProvider<KNode>) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (viewerProvider != null) {
                        String id = element.getAttribute(ATTRIBUTE_ID);
                        if (id == null || id.length() == 0) {
                            reportError(EXTP_ID_EXTENSIONS, element, ATTRIBUTE_ID, null);
                        } else {
                            transformationsGraph.addViewerProvider(viewerProvider);
                            idViewerProviderMapping.put(id, viewerProvider);
                        }
                    }
                } else if (ELEMENT_UPDATE_STRATEGY.equals(element.getName())) {
                    // initialize update strategy from the extension point
                    @SuppressWarnings("unchecked")
                    IUpdateStrategy<KNode> updateStrategy = (IUpdateStrategy<KNode>) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (updateStrategy != null) {
                        String id = element.getAttribute(ATTRIBUTE_ID);
                        if (id == null || id.length() == 0) {
                            reportError(EXTP_ID_EXTENSIONS, element, ATTRIBUTE_ID, null);
                        } else {
                            transformationsGraph.addUpdateStrategy(updateStrategy);
                            idUpdateStrategyMapping.put(id, updateStrategy);
                        }
                    }
                } else if (ELEMENT_STYLE_MODIFIER.equals(element.getName())) {
                    // initialize style modifier from the extension point
                    IStyleModifier styleModifier = (IStyleModifier) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (styleModifier != null) {
                        String id = element.getAttribute(ATTRIBUTE_ID);
                        if (id == null || id.length() == 0) {
                            reportError(EXTP_ID_EXTENSIONS, element, ATTRIBUTE_ID, null);
                        } else {
                            idStyleModifierMapping.put(id, styleModifier);
                        }
                    }
                } else if (ELEMENT_ACTION.equals(element.getName())) {
                    // initialize style modifier from the extension point
                    IAction action = (IAction) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (action != null) {
                        String id = element.getAttribute(ATTRIBUTE_ID);
                        if (id == null || id.length() == 0) {
                            reportError(EXTP_ID_EXTENSIONS, element, ATTRIBUTE_ID, null);
                        } else {
                            idActionMapping.put(id, action);
                        }
                    }
                }
            } catch (CoreException exception) {
                StatusManager.getManager().handle(exception, KlighdPlugin.PLUGIN_ID);
            }
        }
    }
    
    /**
     * Loads and registers all model transformations from the extension point.
     */
    private void loadModelTransformationsExtension() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_DIAGRAM_SYNTHESES);
        for (IConfigurationElement element : extensions) {
            if (ELEMENT_DIAGRAM_SYNTHESIS.equals(element.getName())) {
                // initialize model transformation from the extension point
                ITransformation<?, ?> modelTransformation = null;
                try {
                    modelTransformation =
                            (ITransformation<?, ?>) element
                                    .createExecutableExtension(ATTRIBUTE_CLASS);

                } catch (CoreException exception) {
                    StatusManager.getManager().handle(
                            new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID,
                                    CORE_EXCEPTION_ERROR_MSG.replace("<<CLAZZ>>",
                                            element.getAttribute(ATTRIBUTE_CLASS)), exception));
                } catch (NoClassDefFoundError exception) {
                    final String msg =
                            NO_CLASS_DEF_FOUND_ERROR_MSG.replace("<<CLAZZ>>",
                                    element.getAttribute(ATTRIBUTE_CLASS).replaceFirst(
                                            GuiceBasedSynthesisFactory.CLASS_NAME + ":", ""));
                    StatusManager.getManager().handle(
                            new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg, exception));
                } catch (WrappedException exception) {
                    final String msg =
                            NO_CLASS_DEF_FOUND_ERROR_MSG.replace("<<CLAZZ>>",
                                    element.getAttribute(ATTRIBUTE_CLASS).replaceFirst(
                                            GuiceBasedSynthesisFactory.CLASS_NAME + ":", ""));
                    StatusManager.getManager().handle(
                            new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg, exception
                                    .getCause()));
                }

                if (modelTransformation != null) {
                    String id = element.getAttribute(ATTRIBUTE_ID);
                    if (id == null || id.length() == 0) {
                        reportError(EXTP_ID_DIAGRAM_SYNTHESES, element, ATTRIBUTE_ID, null);
                    } else {
                        try {
                            transformationsGraph.addTransformation(modelTransformation);
                            idTransformationMapping.put(id, modelTransformation);
                        } catch (WrappedException exception) {
                            StatusManager.getManager().handle(
                                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, exception
                                            .getMessage(), exception.getCause()));
                        } catch (Exception exception) {
                            final String msg =
                                    "KLighD: An unexpected exception occured while loading "
                                            + "diagram synthesis " + id
                                            + ". See attached trace for details." + NEW_LINE
                                            + exception.getMessage();
                            StatusManager.getManager().handle(
                                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg,
                                            exception.getCause()));
                        }
                    }
                }
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
    public IViewerProvider<KNode> getViewerProviderById(final String id) {
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
    public IUpdateStrategy<KNode> getUpdateStrategyById(final String id) {
        return idUpdateStrategyMapping.get(id);
    }
    
    /**
     * Returns the style modifier with the given identifier.
     * 
     * @param id
     *            the identifier
     * @return the style modifier
     */
    public IStyleModifier getStyleModifierById(final String id) {
        return idStyleModifierMapping.get(id);
    }
    
    /**
     * Returns the action with the given identifier.
     * 
     * @param id
     *            the identifier
     * @return the action
     */
    public IAction getActionById(final String id) {
        return idActionMapping.get(id);
    }
    
    /**
     * Returns the id of the given {@link IAction}.
     * 
     * @param action
     *            the {@link IAction}
     * @return its id
     */
    public String getActionsId(final IAction action) {
        return idActionMapping.inverse().get(action);
    }
    
    /**
     * Returns the set of registered action ids.
     * 
     * @return the registered action ids
     */
    public Set<String> getActionIds() {
        return idActionMapping.keySet();
    }
    
}
