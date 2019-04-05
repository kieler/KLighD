/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.setup

import com.google.inject.Guice
import com.google.inject.Injector
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramModule
import java.util.concurrent.ExecutorService
import org.eclipse.xtext.ide.ExecutorServiceProvider
import org.eclipse.xtext.ide.server.DefaultProjectDescriptionFactory
import org.eclipse.xtext.ide.server.IProjectDescriptionFactory
import org.eclipse.xtext.ide.server.IWorkspaceConfigFactory
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.naming.SimpleNameProvider
import org.eclipse.xtext.util.Modules2

/**
 * Setup to create bindings for a language server using the diagram features of this package.
 * 
 * @author nre
 */
class LSPSetup {
    protected static Injector injector
    def static doLSSetup() {
        if (injector === null) {
            injector = new LSPSetup().createInjector()
        }
        return injector
    }
    
    private def createInjector() {
        return Guice.createInjector(Modules2.mixin(
            new KGraphDiagramModule(),
            [
                bind(IProjectDescriptionFactory).to(DefaultProjectDescriptionFactory)
                bind(IWorkspaceConfigFactory).to(KeithProjectWorkspaceConfigFactory)
                bind(IQualifiedNameProvider).to(SimpleNameProvider)
                bind(ExecutorService).toProvider(ExecutorServiceProvider)
            ]
        ))
    }
}