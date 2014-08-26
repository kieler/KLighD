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
package de.cau.cs.kieler.klighd.piccolo.export;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.klighd.KlighdPlugin;

/**
 * 
 *
 * @author csp
 */
public final class ExportHooks {

    /** identifier of the extension point for viewer providers. */
    private static final String EXTP_ID_EXPORT_HOOKS = "de.cau.cs.kieler.klighd.piccolo.exportHooks";

    /** name of the 'exportHook' element. */
    private static final String ELEMENT_EXPORT_HOOK = "exportHook";

    /** name of the 'id' attribute in the extension points. */
    private static final String ATTRIBUTE_ID = "id";

    /** name of the 'class' attribute in the extension points. */
    private static final String ATTRIBUTE_CLASS = "class";

    /** name of the 'supportedFormats' attribute in the 'offscreenRenderer' extensions. */
    private static final String ATTRIBUTE_SUPPORTED_FORMATS = "supportedFormats";

    /** the mapping of formats and the supporting export hooks. */
    private static Multimap<String, IExportHook> formatExportHookMapping = null;
    
    private ExportHooks(){}

    /**
     * Returns the collection of registered {@link IExportHook IExportHooks} with the given
     * <code>format</code>.
     * 
     * @param format
     *            the format an {@link IExportHook} is requested for
     * 
     * @return the {@link Collection} of {@link IOffscreenRenderer IOffscreenRenderers}
     */
    protected static Collection<IExportHook> getExportHooksByFormat(final String format) {
        if (formatExportHookMapping == null) {
            formatExportHookMapping = ArrayListMultimap.create();
    
            final IConfigurationElement[] extensions =
                    Platform.getExtensionRegistry().getConfigurationElementsFor(
                            EXTP_ID_EXPORT_HOOKS);
    
            for (final IConfigurationElement element : extensions) {
                try {
                    if (ELEMENT_EXPORT_HOOK.equals(element.getName())) {
                        // initialize style modifier from the extension point
                        final IExportHook hook =
                                (IExportHook) element.createExecutableExtension(ATTRIBUTE_CLASS);
                        if (hook != null) {
                            final String id = element.getAttribute(ATTRIBUTE_ID);
                            if (id == null || id.length() == 0) {
                                reportError(EXTP_ID_EXPORT_HOOKS, element, ATTRIBUTE_ID, null);
                            } else {
                                for (final String f : element.getAttribute(
                                        ATTRIBUTE_SUPPORTED_FORMATS).split("[,\\s]")) {
                                    formatExportHookMapping.put(f, hook);
                                }
                            }
                        }
                    }
                } catch (final CoreException exception) {
                    StatusManager.getManager().handle(exception, KlighdPlugin.PLUGIN_ID);
                }
            }
        }
    
        return Collections.unmodifiableCollection(formatExportHookMapping.get(format));
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
        final String message =
                "KLighD: Element '" + element.getName() + "' extending extension point '"
                        + extensionPoint + "', contributed by '"
                        + element.getContributor().getName()
                        + "' contains invalid entry in attribute '" + attribute + "'";
        StatusManager.getManager().handle(
                new Status(IStatus.WARNING, KlighdPlugin.PLUGIN_ID, 0, message, exception));
    }

}
