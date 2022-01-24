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
package de.cau.cs.kieler.kgraph.text;

import org.eclipse.emf.ecore.EPackage;

import com.google.inject.Injector;

import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;

/**
 * Initialization support for running Xtext languages without equinox extension registry.
 * 
 * @author msp
 */
public class KGraphStandaloneSetup extends KGraphStandaloneSetupGenerated {
    
    protected static Injector injector;

    /**
     * Create an injector and do EMF registration.
     */
    public static Injector doSetup() {
        if (injector == null) {
            injector = new KGraphStandaloneSetup().createInjectorAndDoEMFRegistration();
        }
        return injector;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Injector createInjectorAndDoEMFRegistration() {
        if (!EPackage.Registry.INSTANCE.containsKey(KGraphPackage.eNS_URI))
            EPackage.Registry.INSTANCE.put(KGraphPackage.eNS_URI, KGraphPackage.eINSTANCE);

        return super.createInjectorAndDoEMFRegistration();
    }
}
