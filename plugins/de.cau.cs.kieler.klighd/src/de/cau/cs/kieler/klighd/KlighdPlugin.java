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

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.klighd.internal.IKlighdTrigger;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author mri
 * @author chsch
 */
public class KlighdPlugin extends AbstractUIPlugin {

    /** the plug-in ID. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.klighd";

    /** A definition place of the platform-specific line separator. */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /** the shared instance. */
    private static KlighdPlugin plugin;

    /**
     * The constructor.
     */
    public KlighdPlugin() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static KlighdPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path.
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(final String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }


    /* -------------------------- */
    /*   Access to KIVi-binding   */
    /* -------------------------- */
    
    private static IKlighdTrigger statusTrigger = null;
    
    /**
     * Provides the singleton instance of {@link IKlighdTrigger} being in charge of firing KIVi
     * triggers, or a {@link IKlighdTrigger.NullTrigger} if the fragment
     * <code>de.cau.cs.kieler.klighd.kivi</code> is not available.<br>
     * <br>
     * <b>Note:</b> In order to work properly this code requires the declaration of
     * <pre> DynamicImport-Package: de.cau.cs.kieler.klighd.kivi.internal </pre>
     * in this plug-in's MANIFEST.MF.<br>
     * <br>
     * 
     * @return the available {@link IKlighdTrigger}
     */
    public static synchronized IKlighdTrigger getTrigger() {
        if (statusTrigger == null) {
            try {
                final String className = PLUGIN_ID + ".kivi.internal.KlighdTrigger";
                statusTrigger = (IKlighdTrigger) Class.forName(className).newInstance();
                // .getMethod("getInstance").invoke(null);
            } catch (final Exception e) {
                statusTrigger = new IKlighdTrigger.NullTrigger();
            }
        }
        return statusTrigger;
    }
}
