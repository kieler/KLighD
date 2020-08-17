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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.launch

import com.google.inject.Guice
import de.cau.cs.kieler.klighd.Klighd
import java.net.InetSocketAddress
import java.nio.channels.AsynchronousServerSocketChannel
import java.nio.channels.Channels
import java.util.concurrent.Executors
import org.apache.log4j.Logger
import org.eclipse.lsp4j.services.LanguageServer
import org.eclipse.xtext.ide.server.LanguageServerImpl

/**
 * Extensible entry point for the language server applications for the projects based around KLighD diagrams.<br>
 * Expects none or host and port as arguments. If none the language server is started via<br>
 * stdin/out, otherwise it is started via a socket connection on specified host and port. If only one argument<br>
 * is provided it will be interpreted as port and host will be localhost<br>
 * <br>
 * <b>Note:</b> On MacOS X make sure to add "-Djava.awt.headless=true" to the vmargs!
 * Otherwise the application will freeze!<br>
 * <br>
 * To start this as a plain Java application together with other plug-ins, make sure to add those projects and their
 * project folders into the classpath.
 * 
 * @see LanguageServerLauncher
 * 
 * @author sdo, nre
 */
abstract class AbstractLanguageServer implements Runnable {
    
    static val defaultHost = "localhost"
    
    static val LOG = Logger.getLogger(LanguageServer)
    
    extension ILanguageRegistration languageRegistration
    
    extension ILsCreator creator

    /**
     * Configure this the launch of this language server with the language registration, a language server creator and
     * the instance of the calling class.
     * 
     * @param languageRegistration The registration used to register all languages for this server.
     * @param lsCreator The helping creator class to create this language server.
     */
    def configureAndRun(ILanguageRegistration languageRegistration, ILsCreator lsCreator) {
        this.languageRegistration = languageRegistration
        this.creator = lsCreator
        Klighd.IS_LANGUAGE_SERVER = true
        run
    }
    
    /**
     * Starts the language server.
     */
    override run() {
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
                return
            }
            println("Connection to: " + host + ":" + port)
            // Register all languages
            println("Starting language server socket")
            bindAndRegisterLanguages()
            
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
        } else {
            LanguageServerLauncher.launch(languageRegistration, creator)
        }
    }
    
}