/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019-2021 by
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

import com.google.common.base.Throwables
import com.google.gson.GsonBuilder
import com.google.inject.Injector
import com.google.inject.name.Names
import de.cau.cs.kieler.kgraph.text.services.KGraphGrammarAccess
import de.cau.cs.kieler.klighd.IKlighdStatusManager
import de.cau.cs.kieler.klighd.Klighd
import de.cau.cs.kieler.klighd.KlighdDataManager
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramModule
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramServerModule
import de.cau.cs.kieler.klighd.lsp.KGraphLanguageClient
import de.cau.cs.kieler.klighd.lsp.LSPUtil
import de.cau.cs.kieler.klighd.lsp.SprottyViewer
import de.cau.cs.kieler.klighd.lsp.gson_utils.KGraphTypeAdapterUtil
import de.cau.cs.kieler.klighd.standalone.KlighdStandaloneSetup
import java.io.InputStream
import java.io.OutputStream
import java.util.Collection
import java.util.List
import java.util.concurrent.ExecutorService
import java.util.function.Consumer
import java.util.function.Function
import org.apache.log4j.AsyncAppender
import org.apache.log4j.Logger
import org.eclipse.core.runtime.IStatus
import org.eclipse.lsp4j.jsonrpc.Launcher.Builder
import org.eclipse.lsp4j.jsonrpc.MessageConsumer
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.sprotty.xtext.launch.DiagramServerLauncher.LanguageClientAppender
import org.eclipse.xtext.Constants
import org.eclipse.xtext.IGrammarAccess
import org.eclipse.xtext.ide.server.ILanguageServerExtension
import org.eclipse.xtext.ide.server.IMultiRootWorkspaceConfigFactory
import org.eclipse.xtext.ide.server.LanguageServerImpl
import org.eclipse.xtext.ide.server.ServerLauncher
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.util.Modules2

/**
 * Abstract helper class to create a language server with KLighD diagrams.
 * Implementations need to 
 * 
 * @author sdo, nre
 */
abstract class AbstractLsCreator implements ILsCreator {
    
    /**
     * The injector this class should use to get all language server extensions and their relevant classes.
     */
    protected Injector injector
    
    /**
     * The language client used for message handling.
     */
    protected KGraphLanguageClient languageClient
    
    /**
     * Binds all necessary classes to start the LS.
     * @param socket boolean whether modules for socket or stdio case are generated.
     */
    override createLSModules(boolean socket) {
        return Modules2.mixin(
            new LanguageServerModule,
            [
                if (socket) {
                    // nothing special to bind
                } else {
                    bind(ServerLauncher).to(LanguageServerLauncher)
                }
                bind(IResourceServiceProvider.Registry).toProvider(IResourceServiceProvider.Registry.RegistryProvider)
    
                // the WorkspaceConfigFactory is overridden to disable the creation of a folder with Xtext nature.
                bind(IMultiRootWorkspaceConfigFactory).to(BasicProjectWorkspaceConfigFactory)
                
                bind(IGrammarAccess).to(KGraphGrammarAccess)
                bind(String).annotatedWith(Names.named(Constants.LANGUAGE_NAME)).toInstance("de.cau.cs.kieler.kgraph.text.KGraph");  
            ],
            // Diagram related bindings
            new KGraphDiagramModule(),
            new KGraphDiagramServerModule()
        )
    }
    
    /**
     * Build and starts a LS and handles logging configuration if necessary.
     * Registers all ILanguageServerExtensions registered via ServiceLoader as extensions to the existing ls given as
     * parameter.
     * @param injector injector used to get all LSExtensions. KGraphLSExtension must be registered.
     * @param ls the LangaugeServerImpl that is started. Usually the KGraphLSExtension, which is a DiagramLanguageServer
     * @param in InputStream for communication
     * @param out OutputStream for communication
     * @param executorService ExecutorService
     * @param wrapper 
     * @param socket whether the LS is created for the socket or stdio case
     */
    override buildAndStartLS(Injector injector, LanguageServerImpl ls, InputStream in, OutputStream out,
        ExecutorService executorService, Function<MessageConsumer, MessageConsumer> wrapper, boolean socket
    ) {
        this.injector = injector
        
        // Setup KLighD.
        KlighdStandaloneSetup.initialize
        // Programmatically register the SprottyViewer. It is not registered via service, as it will only ever be used
        // in this language server case.
        KlighdDataManager.instance.registerViewer(SprottyViewer.Provider.ID, new SprottyViewer.Provider)
        
        // TypeAdapter is needed to be able to send recursive data in json
        val Consumer<GsonBuilder> configureGson = [ gsonBuilder |
            KGraphTypeAdapterUtil.configureGson(gsonBuilder, injector)
        ]
        // Get all LSExtensions to use them as local services
        val localServices = <Object>newArrayList
        localServices.addAll(languageServerExtensions)
        localServices.add(ls)
        
        val launcher = new Builder<LanguageClient>()
                .setLocalServices(localServices as Collection<Object>)
                .setRemoteInterface(remoteInterface)
                .setInput(in)
                .setOutput(out)
                .setExecutorService(executorService)
                .wrapMessages(wrapper)
                .configureGson(configureGson)
                .setClassLoader(this.class.classLoader)
                .create();
        // We only allow KGraphLanguageClient or its subclasses as the remote interface.
        languageClient = launcher.remoteProxy as KGraphLanguageClient
        ls.connect(languageClient)
        onConnect
        val future = launcher.startListening
        if (socket) {
        } else { // case stdio
            // Redirect Log4J output to a file
            Logger.rootLogger => [
                removeAllAppenders()
                addAppender(new AsyncAppender() => [
                    addAppender(new LanguageClientAppender(languageClient))
                ])
            ]
        }
        while (!future.done) {
            Thread.sleep(10_000l)
        }
        onReload
    }
    
    /**
     * Returns all {@code ILanguageServerExtension}s this language server should provide.
     * 
     * @return The language server extensions.
     */
    def abstract List<ILanguageServerExtension> getLanguageServerExtensions()
    
    /**
     * The remote interface class this language server should use.
     */
    def abstract Class<? extends KGraphLanguageClient> getRemoteInterface()
    
    /**
     * Handles what needs to be done after the language server connected.
     */
    def void onConnect() {
        // Register a new status handler that forwards the messages to the client.
        Klighd.setStatusManager( [status, style |
            if (style !== IKlighdStatusManager.NONE) {
                var String type
                switch (status.getSeverity()) {
                case IStatus.INFO:
                    type = "info"
                case IStatus.WARNING:
                    type = "warn"
                case IStatus.ERROR:
                    type = "error"
                default:
                    return
                }
                languageClient.sendMessage(statusToMessage(status), type)
            }
        ])
    }
    
    /**
     * Converts the status into a readable String, including the 
     */
    def String statusToMessage(IStatus status) {
        val stackTrace = (status.exception !== null) ? Throwables.getStackTraceAsString(status.exception) : ""
        return LSPUtil.escapeHtml(status.message + "\n" + stackTrace)
    }
    
    /**
     * Handles what needs to be done on reload of the language server.
     */
    def void onReload() {
        // Defaults a no-op.
    }
    
}
