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

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import com.google.common.base.Strings;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.ViewContext;

/**
 * Utility class to retrieve suitable export hooks according to the selected export format.
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
    private static Multimap<String, Class<? extends IExportHook>> formatExportHookMapping = null;

    private ExportHooks() {
        // prevent instantiation
    }

    /**
     * Returns the collection of registered {@link IExportHook IExportHooks} with the given
     * <code>format</code>.
     *
     * @param format
     *            the format an {@link IExportHook} is requested for
     * @param viewContext
     *            the {@link ViewContext} providing access to the diagram' view & source model
     *
     * @return the {@link Iterable} of {@link IExportHook IExportHooks}
     */
    public static Iterable<IExportHook> getExportHooksByFormat(final String format,
            final ViewContext viewContext) {
        if (formatExportHookMapping == null) {
            formatExportHookMapping = ArrayListMultimap.create();

            final IConfigurationElement[] extensions =
                    Platform.getExtensionRegistry().getConfigurationElementsFor(EXTP_ID_EXPORT_HOOKS);

            for (final IConfigurationElement element : extensions) {
                if (ELEMENT_EXPORT_HOOK.equals(element.getName())) {
                    // initialize style modifier from the extension point
                    final IExportHook hook;
                    try {
                        hook = (IExportHook) element.createExecutableExtension(ATTRIBUTE_CLASS);
                    } catch (final CoreException exception) {
                        KlighdDataManager.reportError(
                                EXTP_ID_EXPORT_HOOKS, element, ATTRIBUTE_ID, exception);
                        continue;
                    }

                    final String id = element.getAttribute(ATTRIBUTE_ID);
                    if (hook == null || Strings.isNullOrEmpty(id)) {
                        KlighdDataManager.reportError(EXTP_ID_EXPORT_HOOKS, element, ATTRIBUTE_ID, null);
                        continue;
                    }

                    for (final String f : element.getAttribute(ATTRIBUTE_SUPPORTED_FORMATS)
                            .split("[,\\s]")) {

                        // I know creating instances just for getting the classes is a bit messy ...
                        formatExportHookMapping.put(f, hook.getClass());
                    }
                }
            }
        }

        final List<IExportHook> result = Lists.newArrayList();
        for (final Class<? extends IExportHook> clazz : formatExportHookMapping.get(format)) {
            final IExportHook hook;

            try {
                hook = clazz.newInstance();
            } catch (final Exception e) {
                // should never happen;
                continue;
            }

            hook.setViewContext(viewContext);
            result.add(hook);
        }

        return result;
    }
}
