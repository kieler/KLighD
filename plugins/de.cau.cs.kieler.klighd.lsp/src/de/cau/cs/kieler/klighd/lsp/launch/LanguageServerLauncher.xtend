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

import com.google.inject.Inject
import com.google.inject.Injector
import de.cau.cs.kieler.klighd.lsp.gson_utils.ReflectiveMessageValidatorExcludingSKGraph
import java.util.concurrent.Executors
import java.util.function.Function
import org.eclipse.lsp4j.jsonrpc.MessageConsumer
import org.eclipse.xtext.ide.server.LanguageServerImpl
import org.eclipse.xtext.ide.server.LaunchArgs
import org.eclipse.xtext.ide.server.ServerLauncher

/**
 * Do NOT start this directly. This is only called by the LanguageServer.
 * This main method of this class is only called in the stdio case (production case).
 * Used to start language server via stdin/out connection.
 * Defines static methods used by the LangaugeServer to create an injector and a LS.
 * 
 * @author sdo, nre
 */
class LanguageServerLauncher extends ServerLauncher {
    
    static extension ILsCreator creator
    
    @Inject Injector injector
    
    def static void launch(ILanguageRegistration languageRegistration, ILsCreator lsCreator) {
        creator = lsCreator
        // Launch the server
        languageRegistration.bindAndRegisterLanguages()
        launch(ServerLauncher.name, #[], createLSModules(false))
    }
    
    /**
     * Started via launch (called in main).
     * Creates and starts a LS for the stdio case.
     */
    override start(LaunchArgs args) {
        val threadPool = Executors.newCachedThreadPool
        val ls = injector.getInstance(LanguageServerImpl)
        buildAndStartLS(injector, ls, args.in, args.out, threadPool, args.wrapper,false)
    }
    
    private def Function<MessageConsumer, MessageConsumer> getWrapper(LaunchArgs args) {
        [ consumer |
            var result = consumer
            if (args.trace !== null) {
                result = [ message |
                    args.trace.println(message)
                    args.trace.flush()
                    consumer.consume(message)
                ]
            }
            if (args.validate) {
                result = new ReflectiveMessageValidatorExcludingSKGraph(result)
            }
            return result
        ]
    }    
}