/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
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
package de.cau.cs.kieler.klighd.krendering.extensions;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;

import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared;

/**
 * Activator of the core.krendering.extensions bundle.
 *  We'll see whether we need it in future.
 * 
 * @author chsch
 */
public class KRenderingExtensionsPlugin extends Plugin {

    /** The plug-in ID. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.klighd.krendering.extensions";
    
    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    /**
     * {@inheritDoc}
     */
    public void start(final BundleContext bundleContext) throws Exception {
        KRenderingExtensionsPlugin.context = bundleContext;
    }

    /**
     * {@inheritDoc}
     */
    public void stop(final BundleContext bundleContext) throws Exception {
        KRenderingExtensionsPlugin.context = null;
    }

    /**
     * Convenience method providing a Guice configuration {@link Module} that binds the
     * {@link ViewSynthesisShared} annotation to the generally available {@link Scopes#SINGLETON}
     * scope.
     * 
     * @return the desired {@link Module}
     */
    public static Module createSingletonScopeBindingModule() {
        final Module result = new Module() {
            public void configure(final Binder binder) {
                binder.bindScope(ViewSynthesisShared.class, Scopes.SINGLETON);
            }
        };
        return result;
    }

    /**
     * Convenience method providing a Guice configuration {@link Module} that binds the
     * {@link ViewSynthesisShared} annotation to the generally available {@link Scopes#NO_SCOPE}
     * scope. This configuration basically deactivates the scoping feature.
     * 
     * @return the desired {@link Module}
     */
    public static Module createNoScopeBindingModule() {
        final Module result = new Module() {
            public void configure(final Binder binder) {
                binder.bindScope(ViewSynthesisShared.class, Scopes.NO_SCOPE);
            }
        };
        return result;
    }
}
