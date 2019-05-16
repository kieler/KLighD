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
package de.cau.cs.kieler.klighd.lsp.constraints

import org.eclipse.xtext.ide.server.ILanguageServerExtension
import org.eclipse.xtext.ide.server.ILanguageServerAccess
import javax.inject.Singleton
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * @author jet, cos
 *
 */
 @Singleton
class ConstraintsLanguageServerExtension implements ILanguageServerExtension, 
ConstraintsCommandExtension {
    
    @Accessors ConstraintsLanguageClient client;
    
    
    override initialize(ILanguageServerAccess access) {
        
    }
    
    override sayHello(String msg) {
        println("Hallo "+msg)
        client.sayGoodbye("Good bye my old friend")
    }
    
}