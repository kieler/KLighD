/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
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
package de.cau.cs.kieler.klighd.test;

import java.net.URL;
import java.util.Iterator;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Assert;
import org.osgi.framework.BundleContext;

import com.google.common.collect.Iterators;

import de.cau.cs.kieler.klighd.Klighd;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphDataUtil;
import de.cau.cs.kieler.klighd.standalone.KlighdStandaloneSetup;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author chsch
 */
public class KlighdTestPlugin extends Plugin {
    
    /** The plug-in ID. **/
    public static final String PLUGIN_ID = "de.cau.cs.kieler.klighd.test";

    private static KlighdTestPlugin plugin;
    
    static {
        if (!Klighd.IS_PLATFORM_RUNNING)
            KlighdStandaloneSetup.initialize();
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
    
    /**
     * Loads 'circuit.kgx' from within this bundle.
     *
     * @return the runtime representation of the test model.
     */
    public static KNode loadTestModel() {
        final ResourceSet set = new ResourceSetImpl();

        final Iterator<URL> it;
        if (Klighd.IS_PLATFORM_RUNNING)
            it = Iterators.forEnumeration(
                    KlighdTestPlugin.getDefault().getBundle().findEntries("/", "circuit.kgx", true));
        else {
            it = Iterators.forArray(KlighdTestPlugin.class.getResource("circuit.kgx"));
        }
        if (!it.hasNext()) {
            Assert.fail("Test model 'circuit.kgx' could not be found!");
        }

        final Resource res = set.getResource(URI.createURI(it.next().toString(), true), true);
        final KNode root = (KNode) res.getContents().get(0);
        KGraphDataUtil.loadDataElements(root);

        return root;
    }
}
