/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.launcher

import com.google.gson.GsonBuilder
import com.google.inject.Guice
import de.cau.cs.kieler.kgraph.text.KGraphRuntimeModule
import de.cau.cs.kieler.kgraph.text.ide.KGraphIdeModule
import de.cau.cs.kieler.kgraph.text.ide.KGraphIdeSetup
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramModule
import de.cau.cs.kieler.klighd.lsp.gson_utils.KGraphTypeAdapterUtil
import io.typefox.sprotty.layout.ElkLayoutEngine
import java.net.InetSocketAddress
import java.nio.channels.AsynchronousServerSocketChannel
import java.nio.channels.Channels
import java.util.concurrent.Executors
import java.util.function.Consumer
import org.apache.log4j.Logger
import org.eclipse.elk.alg.layered.options.LayeredMetaDataProvider
import org.eclipse.elk.core.util.persistence.ElkGraphResourceFactory
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.lsp4j.jsonrpc.Launcher
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.xtext.ide.server.LanguageServerImpl
import org.eclipse.xtext.ide.server.ServerModule
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.util.Modules2

/**
 * Launches the KGraph server as a standalone program. Has to be started separately and communicates with any client
 * via the url and port specified below.
 * 
 * Based on the yang-lsp implementation by TypeFox.
 * 
 * @author nir
 * @see <a href="https://github.com/theia-ide/yang-lsp/blob/master/yang-lsp/io.typefox.yang.diagram/src/main/java/io/typefox/yang/diagram/RunSocketServer.xtend">
 *      RunSocketServer</a>
 */
public class RunSocketServer {
	static val LOG = Logger.getLogger(RunSocketServer)

	public static def void main(String[] args) throws Exception {
		// Initialize ELK
		ElkLayoutEngine.initialize(new LayeredMetaDataProvider)
		Resource.Factory.Registry.INSTANCE.extensionToFactoryMap.put('elkg', new ElkGraphResourceFactory)
		
		// Do a manual setup that includes the KGraph diagram module
		new KGraphIdeSetup {
			override createInjector() {
				Guice.createInjector(Modules2.mixin(
				    new KGraphRuntimeModule, 
				    new KGraphIdeModule, 
				    new KGraphDiagramModule// ,
//				    new Module() {
//                        override configure(Binder binder) {
//                            binder.bindScope(ViewSynthesisShared, Scopes.SINGLETON)
//                        }
//                        
//                    }
				))
			}
		}.createInjectorAndDoEMFRegistration()
		
		val injector = Guice.createInjector(Modules2.mixin(new ServerModule, [
			bind(IResourceServiceProvider.Registry).toProvider(IResourceServiceProvider.Registry.RegistryProvider)
		]))
		val serverSocket = AsynchronousServerSocketChannel.open.bind(new InetSocketAddress("localhost", 5007))
		val threadPool = Executors.newCachedThreadPool()
		
		while (true) {
			val socketChannel = serverSocket.accept.get
			val in = Channels.newInputStream(socketChannel)
			val out = Channels.newOutputStream(socketChannel)
			val Consumer<GsonBuilder> configureGson = [ gsonBuilder |
                KGraphTypeAdapterUtil.configureGson(gsonBuilder)
			]
			val languageServer = injector.getInstance(LanguageServerImpl)
			val launcher = Launcher.createIoLauncher(languageServer, LanguageClient, in, out, threadPool, [it], configureGson)
			languageServer.connect(launcher.remoteProxy)
			launcher.startListening
			LOG.info("Started language server for client " + socketChannel.remoteAddress)
		}
	}
}
