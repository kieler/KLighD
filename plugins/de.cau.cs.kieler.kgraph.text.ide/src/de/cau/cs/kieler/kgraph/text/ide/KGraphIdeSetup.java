/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2019 by
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
package de.cau.cs.kieler.kgraph.text.ide;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.resource.impl.BinaryGrammarResourceFactoryImpl;
import org.eclipse.xtext.util.Modules2;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.cau.cs.kieler.kgraph.text.KGraphRuntimeModule;
import de.cau.cs.kieler.kgraph.text.KGraphStandaloneSetup;

/**
 * Initialization support for running Xtext languages as language servers.
 * 
 * @author sdo
 */
public class KGraphIdeSetup extends KGraphStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(
            new KGraphRuntimeModule(), 
            new KGraphIdeModule()
		));
	}
	
	public static Injector doSetup() {
        if (injector == null) {
            injector = new KGraphIdeSetup().createInjectorAndDoEMFRegistration();
        }
        return injector;
    }
	
	@Override
    public Injector createInjectorAndDoEMFRegistration() {
        // register default ePackages
        if (!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().containsKey("ecore"))
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
                "ecore", new EcoreResourceFactoryImpl());
        if (!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().containsKey("xmi"))
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
                "xmi", new XMIResourceFactoryImpl());
        if (!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().containsKey("xtextbin"))
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
                "xtextbin", new BinaryGrammarResourceFactoryImpl());
        if (!EPackage.Registry.INSTANCE.containsKey(XtextPackage.eNS_URI))
            EPackage.Registry.INSTANCE.put(XtextPackage.eNS_URI, XtextPackage.eINSTANCE);

        Injector injector = createInjector();
        register(injector);
        return injector;
    }
	
}
