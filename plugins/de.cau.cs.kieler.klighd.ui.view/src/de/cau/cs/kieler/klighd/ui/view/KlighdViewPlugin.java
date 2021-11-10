/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
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
package de.cau.cs.kieler.klighd.ui.view;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author als
 */
public class KlighdViewPlugin extends AbstractUIPlugin {

    /** The plug-in ID. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.klighd.ui.view"; //$NON-NLS-1$

    /** The shared instance. */
    private static KlighdViewPlugin plugin;

    /**
     * The constructor.
     */
    public KlighdViewPlugin() {
    }

    /**
     * {@inheritDoc}
     */
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /**
     * {@inheritDoc}
     */
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     *
     * @return the shared instance
     */
    public static KlighdViewPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path.
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(final String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }
}
