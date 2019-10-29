/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.kgraph.language.server

import com.google.inject.AbstractModule
import java.util.concurrent.ExecutorService
import org.eclipse.xtext.ide.ExecutorServiceProvider
import org.eclipse.xtext.ide.server.DefaultProjectDescriptionFactory
import org.eclipse.xtext.ide.server.IProjectDescriptionFactory
import org.eclipse.xtext.ide.server.LanguageServerImpl
import org.eclipse.xtext.resource.IContainer
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.resource.ResourceServiceProviderServiceLoader
import org.eclipse.xtext.resource.containers.ProjectDescriptionBasedContainerManager

/** 
 * @author sdo
 *
 */
class KeithServerModule extends AbstractModule {
    
    override configure() {
        binder.bind(ExecutorService).toProvider(ExecutorServiceProvider)
        
        bind(org.eclipse.lsp4j.services.LanguageServer).to(LanguageServerImpl)
        bind(IResourceServiceProvider.Registry).toProvider(ResourceServiceProviderServiceLoader)
        bind(IProjectDescriptionFactory).to(DefaultProjectDescriptionFactory)
        bind(IContainer.Manager).to(ProjectDescriptionBasedContainerManager)
    }
    
}