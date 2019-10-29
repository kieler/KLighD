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

import com.google.inject.Guice
import java.net.InetSocketAddress
import java.nio.channels.AsynchronousServerSocketChannel
import java.nio.channels.Channels
import java.util.concurrent.Executors
import org.apache.log4j.Logger
import org.eclipse.equinox.app.IApplication
import org.eclipse.equinox.app.IApplicationContext
import org.eclipse.xtext.ide.server.LanguageServerImpl

/**
 * Entry point for the language server application for the KIELER Pragmatics project based around the KGraph language.<br>
 * Expects none or host and port as arguments. If none the language server is started via<br>
 * stdin/out, otherwise it is started via a socket connection on specified host and port. If only one argument<br>
 * is provided it will be interpreted as port and host will be localhost<br>
 * <br>
 * <b>Note:</b> On MacOS X make sure to add "-Djava.awt.headless=true" to the vmargs!
 * Otherwise the application will freeze! 
 * 
 * @see LanguageServerLauncher
 * 
 * @author sdo
 */
class LanguageServer implements IApplication {
    
    static val defaultHost = "localhost"
    
    static val LOG = Logger.getLogger(LanguageServer)
    
    extension LanguageRegistration languageRegistration = new LanguageRegistration
    
    extension LSCreator creator = new LSCreator
    
    /**
     * Starts the language server.
     */
    override start(IApplicationContext context) throws Exception {
        var host = System.getProperty("host")
        var portArg = System.getProperty("port")
        if (portArg !== null) {
            // debug case, communicate via socket
            if (host === null) {
                host = defaultHost
            }
            var port = 0
            try {
                port = Integer.parseInt(portArg)
            } catch (NumberFormatException e) {
                println(e.stackTrace)
                return 1
            }
            println("Connection to: " + host + ":" + port)
            // Register all languages
            println("Starting language server socket")
            bindAndRegisterLanguages()
            
            this.run(host, port)
            return EXIT_OK 
        } else {
            LanguageServerLauncher.main(#[])
            return EXIT_OK
        }
    }
    
    override stop() {
        // implementation not needed
    }
    
    /**
     * Starts the language server (has to be separate method, since start method must have a "reachable" return
     */
    def run(String host,  int port) {
        val serverSocket = AsynchronousServerSocketChannel.open.bind(new InetSocketAddress(host, port))
        val threadPool = Executors.newCachedThreadPool()
        while (true) {
            val socketChannel = serverSocket.accept.get            
            val in = Channels.newInputStream(socketChannel)
            val out = Channels.newOutputStream(socketChannel)
            val injector = Guice.createInjector(createLSModules(true))
            val ls = injector.getInstance(LanguageServerImpl)
            buildAndStartLS(injector, ls, in, out, threadPool, [it], true)
            LOG.info("Started language server for client " + socketChannel.remoteAddress)
        }
    }
    
}