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
package de.cau.cs.kieler.klighd.test;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author chsch
 */
public class KlighdTestPlugin extends Plugin {
    
    /** The plug-in ID. **/
    public static final String PLUGIN_ID = "de.cau.cs.kieler.klighd.test";

    private static KlighdTestPlugin plugin;
    
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
        super.stop(context);
        plugin = null;
    }
    
    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static KlighdTestPlugin getDefault() {
        return plugin;
    }
}
