package de.cau.cs.kieler.klighd.ui.wizard;

/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.service.AbstractGenericModule;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.ui.wizard.IProjectCreator;
import org.eclipse.xtext.util.Modules2;
import org.osgi.framework.BundleContext;

import com.google.common.collect.Maps;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provider;

/**
 * 
 */
public class KlighdWizardPlugin extends AbstractUIPlugin {

    /** The plug-in ID. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.klighd.ui.wizard"; //$NON-NLS-1$

    private static final Logger LOGGER = Logger.getLogger(KlighdWizardPlugin.class);

    private static BundleContext context;

    // The shared instance
    private static KlighdWizardPlugin plugin;

    static BundleContext getContext() {
        return context;
    }

    private Map<String, Injector> injectors = Collections.synchronizedMap(Maps
            .<String, Injector> newHashMapWithExpectedSize(1));

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext bundleContext) throws Exception {
        KlighdWizardPlugin.context = bundleContext;
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext bundleContext) throws Exception {
        injectors.clear();
        plugin = null;
        KlighdWizardPlugin.context = null;
    }

    public static KlighdWizardPlugin getInstance() {
        return plugin;
    }

    public Injector getInjector(String language) {
        synchronized (injectors) {
            Injector injector = injectors.get(language);
            if (injector == null) {
                injectors.put(language, injector = createInjector(language));
            }
            return injector;
        }
    }

    public Provider<IWorkspace> provideIWorkspace() {
        return new Provider<IWorkspace>() {
            public IWorkspace get() {
                return ResourcesPlugin.getWorkspace();
            }
        };
    }

    public void configureIWorkbench(Binder binder) {
        if (PlatformUI.isWorkbenchRunning()) {
            binder.bind(IWorkbench.class).toProvider(new Provider<IWorkbench>() {
                public IWorkbench get() {
                    return (PlatformUI.isWorkbenchRunning()) ? PlatformUI.getWorkbench() : null;
                }
            });
        }
    }

    protected Injector createInjector(String language) {
        try {
            Module runtimeModule = new AbstractGenericModule() {
                @Override
                public void configure(Binder binder) {
                    super.configure(binder);

                    binder.bind(IProjectCreator.class).to(KlighdProjectCreator.class);
                    binder.bind(IWorkspace.class).toProvider(new Provider<IWorkspace>() {
                        public IWorkspace get() {
                            return ResourcesPlugin.getWorkspace();
                        }
                    });
                }

            };
            Module sharedStateModule = getSharedStateModule();
            Module mergedModule = Modules2.mixin(runtimeModule, sharedStateModule);
            return Guice.createInjector(mergedModule);
        } catch (Exception e) {
            LOGGER.error("Failed to create injector for " + language);
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Failed to create injector for " + language, e);
        }
    }

    protected Module getSharedStateModule() {
        return new SharedStateModule();
    }

}
