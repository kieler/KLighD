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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.wizard;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

/**
 * A specialized {@link AbstractGuiceAwareExecutableExtensionFactory} required for using Guice in
 * combination with the Eclipse extension points.
 * 
 * @author uru
 */
public class KlighdExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

    @Override
    protected Bundle getBundle() {
        return KlighdWizardPlugin.getInstance().getBundle();
    }

    @Override
    protected Injector getInjector() {
        return KlighdWizardPlugin.getInstance().getInjector();
    }

}
