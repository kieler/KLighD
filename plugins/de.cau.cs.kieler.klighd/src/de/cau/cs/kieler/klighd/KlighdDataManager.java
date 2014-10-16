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

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.klighd.internal.ISynthesis;
import de.cau.cs.kieler.klighd.syntheses.GuiceBasedSynthesisFactory;

/**
 * Singleton for accessing transformations, viewers, update strategies and layout post processors
 * registered with KLighD.
 *
 * @author mri, chsch, akoc, csp
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

    /** name of the 'exporter' element. */
    private static final String ELEMENT_EXPORTER = "exporter";

    /** name of the 'exportHook' element. */
    private static final String ELEMENT_EXPORT_BRANDING = "exportBranding";

    /** name of the 'offscreenRenderer' element. */
    private static final String ELEMENT_OFFSCREEN_RENDERER = "offscreenRenderer";

    /** name of the 'id' attribute in the extension points. */
    private static final String ATTRIBUTE_ID = "id";

    /** name of the 'class' attribute in the extension points. */
    private static final String ATTRIBUTE_CLASS = "class";

    /** name of the 'supportedFormats' attribute in the 'offscreenRenderer' extensions. */
    private static final String ATTRIBUTE_SUPPORTED_FORMATS = "supportedFormats";

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

    /** the mapping of ids to the corresponding instances of {@link ISynthesis}. */
    private Map<String, ISynthesis> idSynthesisMapping = Maps.newHashMap();

    /** the mapping of types to the corresponding instances of {@link ISynthesis}. */
    private Multimap<Class<?>, ISynthesis> typeSynthesisMapping = ArrayListMultimap.create();

    /**
     * A caching map that avoids the effort of determining the correct syntheses for a given type
     * each time. */
    private Map<Class<?>, Iterable<ISynthesis>> concreteTypeSynthesisMapping = Maps.newHashMap();

    /** the mapping of ids on the associated viewer providers. */
    private Map<String, IViewerProvider> idViewerProviderMapping = Maps.newHashMap();

    /** the mapping of ids on the associated update strategies. */
    private Map<String, IUpdateStrategy> idUpdateStrategyMapping = Maps.newHashMap();

    private IUpdateStrategy highestPriorityUpdateStrategy = null;

    /** the mapping of ids on the associated style modifiers. */
    private Map<String, IStyleModifier> idStyleModifierMapping = Maps.newHashMap();

    /** the mapping of ids on the associated actions. */
    private BiMap<String, IAction> idActionMapping = HashBiMap.create();

    /** the mapping of ids on the associated configuration elements describing the exporters. */
    private Map<String, IConfigurationElement> exportersMap = Maps.newHashMap();

    /** the list of the available exporters' descriptors. */
    private List<ExporterDescriptor> descriptors = Lists.newArrayList();

    /** the mapping of formats and the supporting off-screen renderers. */
    private Multimap<String, IOffscreenRenderer> formatOffscreenRendererMapping = null;

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
        } catch (final Exception e) {
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
    // is currently also used by klighd.piccolo's ExportHooks class
    public static void reportError(final String extensionPoint,
            final IConfigurationElement element, final String attribute, final Exception exception) {
        final String message =
                "KLighD: Element '" + element.getName() + "' extending extension point '"
                        + extensionPoint + "', contributed by '"
                        + element.getContributor().getName()
                        + "' contains invalid entry in attribute '" + attribute + "'";
        StatusManager.getManager().handle(
                new Status(IStatus.WARNING, KlighdPlugin.PLUGIN_ID, 0, message, exception));
    }


    /**
     * Data record containing the information about an exporter.
     */
    public static final class ExporterDescriptor {

        // SUPPRESS CHECKSTYLE NEXT 5 Visibility|Javadoc
        public final String exporterId;
        public final String subFormatId;
        public final String description;
        public final String fileExtension;
        public final boolean supportsTiling;

        private ExporterDescriptor(final String exporterId, final String subFormatId,
                final String description, final String fileExtension, final boolean supportsTiling) {
            this.exporterId = exporterId;
            this.subFormatId = subFormatId;
            this.description = description;
            this.fileExtension = fileExtension;
            this.supportsTiling = supportsTiling;
        }
    }

    /**
     * Loads all registered extensions from the extension point 'extensions' and builds up the
     * corresponding mappings.
     */
    private void loadViewerProviderExtension() {
        final IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_EXTENSIONS);

        for (final IConfigurationElement element : extensions) {
            try {
                if (ELEMENT_VIEWER.equals(element.getName())) {
                    // initialize viewer provider from the extension point
                    final IViewerProvider viewerProvider =
                            (IViewerProvider) element.createExecutableExtension(ATTRIBUTE_CLASS);
                    if (viewerProvider != null) {
                        final String id = element.getAttribute(ATTRIBUTE_ID);
                        if (id == null || id.length() == 0) {
                            reportError(EXTP_ID_EXTENSIONS, element, ATTRIBUTE_ID, null);
                        } else {
                            idViewerProviderMapping.put(id, viewerProvider);
                        }
                    }
                } else if (ELEMENT_UPDATE_STRATEGY.equals(element.getName())) {
                    // initialize update strategy from the extension point
                    final IUpdateStrategy updateStrategy = (IUpdateStrategy) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (updateStrategy != null) {
                        final String id = element.getAttribute(ATTRIBUTE_ID);
                        if (id == null || id.length() == 0) {
                            reportError(EXTP_ID_EXTENSIONS, element, ATTRIBUTE_ID, null);
                        } else {
                            idUpdateStrategyMapping.put(id, updateStrategy);
                            if (this.highestPriorityUpdateStrategy == null
                                    || this.highestPriorityUpdateStrategy.getPriority() < updateStrategy
                                            .getPriority()) {
                                this.highestPriorityUpdateStrategy = updateStrategy;
                            }
                        }
                    }
                } else if (ELEMENT_STYLE_MODIFIER.equals(element.getName())) {
                    // initialize style modifier from the extension point
                    final IStyleModifier styleModifier = (IStyleModifier) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (styleModifier != null) {
                        final String id = element.getAttribute(ATTRIBUTE_ID);
                        if (id == null || id.length() == 0) {
                            reportError(EXTP_ID_EXTENSIONS, element, ATTRIBUTE_ID, null);
                        } else {
                            idStyleModifierMapping.put(id, styleModifier);
                        }
                    }
                } else if (ELEMENT_ACTION.equals(element.getName())) {
                    // initialize style modifier from the extension point
                    final IAction action = (IAction) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (action != null) {
                        final String id = element.getAttribute(ATTRIBUTE_ID);
                        if (id == null || id.length() == 0) {
                            reportError(EXTP_ID_EXTENSIONS, element, ATTRIBUTE_ID, null);
                        } else {
                            idActionMapping.put(id, action);
                        }
                    }
                } else if (ELEMENT_EXPORTER.equals(element.getName())) {
                    // store the generator
                    final String id = element.getAttribute(ATTRIBUTE_ID);
                    if (id == null || id.length() == 0) {
                        reportError(EXTP_ID_EXTENSIONS, element, ATTRIBUTE_ID, null);
                    } else {
                        String subFormat = element.getAttribute("subFormat");
                        if (subFormat == null) {
                            subFormat = "";
                        }
                        final String extension = element.getAttribute("extension");
                        final String descr = element.getAttribute("description");
                        final boolean supportsTiling =
                                Boolean.parseBoolean(element.getAttribute("supportsTiling"));

                        // create the descriptor
                        final ExporterDescriptor descriptor =
                                new ExporterDescriptor(id, subFormat, descr, extension, supportsTiling);
                        descriptors.add(descriptor);

                        // put the configuration element into the map to lazy load the exporter later
                        exportersMap.put(id, element);
                    }
                }
            } catch (final CoreException exception) {
                StatusManager.getManager().handle(exception, KlighdPlugin.PLUGIN_ID);
            }
        }
    }

    /**
     * Loads the registered {@link de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
     * AbstractDiagramSynthesis} from the extension point and builds up the
     * {@link #typeSynthesisMapping}.
     */
    private void loadModelTransformationsExtension() {
        final IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_DIAGRAM_SYNTHESES);

        for (final IConfigurationElement element : extensions) {
            if (ELEMENT_DIAGRAM_SYNTHESIS.equals(element.getName())) {
                // initialize model transformation from the extension point
                ISynthesis synthesis = null;
                try {
                    synthesis =
                            (ISynthesis) element.createExecutableExtension(ATTRIBUTE_CLASS);

                } catch (final CoreException exception) {
                    StatusManager.getManager().handle(
                            new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID,
                                    CORE_EXCEPTION_ERROR_MSG.replace("<<CLAZZ>>",
                                            element.getAttribute(ATTRIBUTE_CLASS)), exception));
                } catch (final NoClassDefFoundError exception) {
                    final String msg =
                            NO_CLASS_DEF_FOUND_ERROR_MSG.replace("<<CLAZZ>>",
                                    element.getAttribute(ATTRIBUTE_CLASS).replaceFirst(
                                            GuiceBasedSynthesisFactory.CLASS_NAME + ":", ""));
                    StatusManager.getManager().handle(
                            new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg, exception));
                } catch (final WrappedException exception) {
                    final String msg =
                            NO_CLASS_DEF_FOUND_ERROR_MSG.replace("<<CLAZZ>>",
                                    element.getAttribute(ATTRIBUTE_CLASS).replaceFirst(
                                            GuiceBasedSynthesisFactory.CLASS_NAME + ":", ""));
                    StatusManager.getManager().handle(
                            new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg, exception
                                    .getCause()));
                }

                if (synthesis != null) {
                    final String id = element.getAttribute(ATTRIBUTE_ID);
                    if (id == null || id.length() == 0) {
                        reportError(EXTP_ID_DIAGRAM_SYNTHESES, element, ATTRIBUTE_ID, null);
                    } else {
                        try {
                            idSynthesisMapping.put(id, synthesis);
                            typeSynthesisMapping.put(synthesis.getSourceClass(), synthesis);

                        } catch (final WrappedException exception) {
                            StatusManager.getManager().handle(
                                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, exception
                                            .getMessage(), exception.getCause()));
                        } catch (final Exception exception) {
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

        // I hope the ImmutableMultiMap is better wrt. to query performance
        //  so this immutable copy of the temporary map is created.
        this.typeSynthesisMapping = ImmutableMultimap.copyOf(typeSynthesisMapping);
    }


    /**
     * Returns the list of registered {@link ISynthesis} implementations whose input types are
     * compatible to <code>type</code>.
     *
     * @param type
     *            the type the of model to be translated
     * @return the matching {@link ISynthesis} implementations
     */
    public Iterable<ISynthesis> getAvailableSyntheses(final Class<?> type) {

        if (type == null) {
            return null;
        } else {

            final Iterable<ISynthesis> knownSyntheses = this.concreteTypeSynthesisMapping.get(type);
            if (knownSyntheses != null) {
                // if the fitting syntheses have been determined already, use that those
                return knownSyntheses;

            } else {
                // otherwise reveal those input types of registered ISynthesis implementations
                //  that are compatible to 'type'
                final List<Class<?>> validTypes =
                        Lists.newArrayList(Iterables.filter(typeSynthesisMapping.keySet(),
                                new Predicate<Class<?>>() {
                                    public boolean apply(final Class<?> clazz) {
                                        return clazz.isAssignableFrom(type);
                                    }
                                }));

                final Collection<ISynthesis> res;
                if (validTypes.isEmpty()) {
                    res = Collections.emptyList();

                } else {
                    // sort them s.t. the most concrete type is at position 0
                    Collections.sort(validTypes, TYPE_SORTER);

                    // and reveal the collection of related ISynthesis from the main mapping
                    res = typeSynthesisMapping.get(validTypes.get(0));
                }

                this.concreteTypeSynthesisMapping.put(type, res);
                return res;
            }
        }
    }

    /** Sorts a list of types s.t. a super type is place after a sub type. */
    private static final Comparator<Class<?>> TYPE_SORTER = new Comparator<Class<?>>() {

        /**
         * {@inheritDoc}
         */
        public int compare(final Class<?> o1, final Class<?> o2) {
            if (o1.isAssignableFrom(o2)) {
                return 1;
            } else if (o2.isAssignableFrom(o1)) {
                return -1;
            } else {
                return 0;
            }
        }
    };


    /**
     * Returns the {@link ISynthesis} with the given identifier.
     *
     * @param id
     *            the identifier
     * @return the {@link ISynthesis} instance or <code>null</code> if there is no synthesis with
     *         the given id
     */
    public ISynthesis getDiagramSynthesisById(final String id) {
        if (id == null) {
            return null;
        }
        return idSynthesisMapping.get(id);
    }


    /**
     * Returns all registered instances of {@link IViewerProvider}.
     *
     * @return an immutable collection of the registered {@link IViewerProvider IViewerProviders}
     */
    public Collection<IViewerProvider> getAvailableViewerProviders() {
        return Collections.unmodifiableCollection(idViewerProviderMapping.values());
    }

    /**
     * Returns the viewer provider with the given identifier.
     *
     * @param id
     *            the identifier
     * @return the viewer provider or null if there is no viewer provider with the given id
     */
    public IViewerProvider getViewerProviderById(final String id) {
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
    public IUpdateStrategy getUpdateStrategyById(final String id) {
        return idUpdateStrategyMapping.get(id);
    }


    /**
     * Returns one of those registered {@link IUpdateStrategy IUpdateStrategies} with the highest
     * priority. If multiple {@link IUpdateStrategy IUpdateStrategies} with the maximal priority
     * are registered, no assertion on the provided one can be made!
     *
     * @return one of those registered {@link IUpdateStrategy IUpdateStrategies} with the highest
     *         priority.
     */
    public IUpdateStrategy getHighestPriorityUpdateStrategy() {
        return this.highestPriorityUpdateStrategy;
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

    /**
     * @return return a copy of the original list
     */
    public List<ExporterDescriptor> getAvailableExporters() {
        return Lists.newLinkedList(descriptors);
    }

    /**
     * @param id
     *            the id of the registered {@link IDiagramExporter}.
     * @return the registered exporter for the passed id.
     *
     * @throws IllegalArgumentException
     *             if the passed {@code id} is not registered.
     */
    public IDiagramExporter getExporter(final String id) {
        IDiagramExporter exporter = null;
        IConfigurationElement element = null;
        try {
            element = exportersMap.get(id);
        if (element == null) {
            throw new IllegalArgumentException("Id of " + IDiagramExporter.class + " not registered: "
                    + id + ".");
        }
        exporter = (IDiagramExporter) element.createExecutableExtension("class");
        } catch (final CoreException exception) {
            reportError(EXTP_ID_EXTENSIONS, element, ATTRIBUTE_ID, exception);
        }
        return exporter;
    }

//    /** name of the 'supportedFormats' attribute in the 'offscreenRenderer' extensions. */
//    private static final String ATTRIBUTE_SUPPORTED_FORMATS = "supportedFormats";

    /** the mapping of formats and the supporting export hooks. */
    private static Multimap<String, IConfigurationElement> formatExportHookMapping = null;

    /**
     * Returns the collection of registered {@link IExportBranding IExportHooks} with the given
     * <code>format</code>.
     *
     * @param format
     *            the format an {@link IExportBranding} is requested for
     * @param viewContext
     *            the {@link ViewContext} providing access to the diagram' view & source model
     *
     * @return the {@link Iterable} of {@link IExportBranding IExportHooks}
     */
    public static Iterable<IExportBranding> getExportBrandingByFormat(final String format,
            final ViewContext viewContext) {
        if (formatExportHookMapping == null) {
            formatExportHookMapping = ArrayListMultimap.create();

            final IConfigurationElement[] extensions =
                    Platform.getExtensionRegistry().getConfigurationElementsFor(EXTP_ID_EXTENSIONS);

            for (final IConfigurationElement element : extensions) {
                if (!ELEMENT_EXPORT_BRANDING.equals(element.getName())) {
                    continue;
                }

                // requesting an exportBranding by 'id' is currently not supported
                // if (Strings.isNullOrEmpty(element.getAttribute(ATTRIBUTE_ID))) {
                //     KlighdDataManager.reportError(EXTP_ID_EXTENSIONS, element, ATTRIBUTE_ID, null);
                //     continue;
                // }

                for (final String f : Strings.nullToEmpty(
                        element.getAttribute(ATTRIBUTE_SUPPORTED_FORMATS)).split("[,\\s]")) {
                    formatExportHookMapping.put(f, element);
                }
            }
        }

        final List<IExportBranding> result = Lists.newArrayList();
        for (final IConfigurationElement element : formatExportHookMapping.get(format)) {
            final IExportBranding branding;

            try {
                branding = (IExportBranding) element.createExecutableExtension(ATTRIBUTE_CLASS);
                branding.setViewContext(viewContext);
            } catch (final Exception e) {
                KlighdDataManager.reportError(EXTP_ID_EXTENSIONS, element, ATTRIBUTE_CLASS, e);
                continue;
            }

            if (branding.isEnabled()) {
                result.add(branding);
            }
        }

        return result;
    }

    /**
     * Returns the collection of registered {@link IOffscreenRenderer IOffscreenRenderers} with the
     * given <code>format</code>.
     *
     * @param format the format an {@link IOffscreenRenderer} is requested for
     *
     * @return the {@link Collection} of
     */
    public Collection<IOffscreenRenderer> getOffscreenRenderersByFormat(final String format) {
        if (formatOffscreenRendererMapping == null) {
            formatOffscreenRendererMapping = ArrayListMultimap.create();

            final IConfigurationElement[] extensions =
                    Platform.getExtensionRegistry().getConfigurationElementsFor(EXTP_ID_EXTENSIONS);

            for (final IConfigurationElement element : extensions) {
                try {
                    if (ELEMENT_OFFSCREEN_RENDERER.equals(element.getName())) {
                        // initialize style modifier from the extension point
                        final IOffscreenRenderer renderer =
                                (IOffscreenRenderer) element
                                        .createExecutableExtension(ATTRIBUTE_CLASS);
                        if (renderer != null) {
                            final String id = element.getAttribute(ATTRIBUTE_ID);
                            if (id == null || id.length() == 0) {
                                reportError(EXTP_ID_EXTENSIONS, element, ATTRIBUTE_ID, null);
                            } else {
                                for (final String f : element.getAttribute(ATTRIBUTE_SUPPORTED_FORMATS)
                                        .split("[,\\s]")) {
                                    formatOffscreenRendererMapping.put(f, renderer);
                                }
                            }
                        }
                    }
                } catch (final CoreException exception) {
                    StatusManager.getManager().handle(exception, KlighdPlugin.PLUGIN_ID);
                }
            }
        }

        return Collections.unmodifiableCollection(formatOffscreenRendererMapping.get(format));
    }
}
