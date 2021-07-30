/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.klighd.Klighd;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author uru
 */
public class KlighdUIPlugin extends AbstractUIPlugin {

    /** The plug-in ID. */
    public static final String PLUGIN_ID = Klighd.PLUGIN_ID + ".ui"; //$NON-NLS-1$

    /** Menu id of the floating text widget's context menu. */
    public static final String FLOATING_TEXT_MENU_ID = PLUGIN_ID + ".popupMenu.floatingText";

    // The shared instance
    private static KlighdUIPlugin plugin;

    /**
     * The constructor.
     */
    public KlighdUIPlugin() {
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static KlighdUIPlugin getDefault() {
        return plugin;
    }

    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
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

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path.
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptorFromKlighdBase(final String path) {
        return imageDescriptorFromPlugin(Klighd.PLUGIN_ID, path);
    }
}
