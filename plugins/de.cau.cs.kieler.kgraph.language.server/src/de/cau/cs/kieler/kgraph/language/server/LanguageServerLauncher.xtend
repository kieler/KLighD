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
 * @author sdo
 *
 */
class LanguageServerLauncher extends ServerLauncher {
    
    static extension LanguageRegistration registration = new LanguageRegistration
    
    static extension LSCreator creator = new LSCreator
    
    @Inject Injector injector
    
    def static void main(String[] args) {
        // Launch the server
        bindAndRegisterLanguages()
        launch(ServerLauncher.name, args, createLSModules(false))
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