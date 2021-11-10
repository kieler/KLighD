/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019,2020 by
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
package de.cau.cs.kieler.klighd.lsp.launch

import com.google.inject.AbstractModule
import java.util.concurrent.ExecutorService
import org.eclipse.lsp4j.services.LanguageServer
import org.eclipse.xtext.ide.ExecutorServiceProvider
import org.eclipse.xtext.ide.server.DefaultProjectDescriptionFactory
import org.eclipse.xtext.ide.server.IProjectDescriptionFactory
import org.eclipse.xtext.ide.server.LanguageServerImpl
import org.eclipse.xtext.resource.IContainer
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.resource.ResourceServiceProviderServiceLoader
import org.eclipse.xtext.resource.containers.ProjectDescriptionBasedContainerManager

/**
 * Bind necessary classes for a language server setup.
 * 
 * @author sdo
 */
class LanguageServerModule extends AbstractModule {
    
    override configure() {
        binder.bind(ExecutorService).toProvider(ExecutorServiceProvider)
        
        bind(LanguageServer).to(LanguageServerImpl)
        bind(IResourceServiceProvider.Registry).toProvider(ResourceServiceProviderServiceLoader)
        bind(IProjectDescriptionFactory).to(DefaultProjectDescriptionFactory)
        bind(IContainer.Manager).to(ProjectDescriptionBasedContainerManager)
    }
    
}