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
package de.cau.cs.kieler.klighd.lsp.structuredProgramming

import com.google.inject.Injector
import org.eclipse.xtext.ide.server.ILanguageServerExtension
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageClient

/**
 * The default ILanguageServerContribution doesn't have a setClient method
 * This is needed in order to set the client for communication
 * @author felixj
 */
interface IStructuredProgrammingLanguageServerContribution {

    def ILanguageServerExtension getLanguageServerExtension(Injector injector)
    def void setClient(Injector injector, KGraphLanguageClient client)
}
