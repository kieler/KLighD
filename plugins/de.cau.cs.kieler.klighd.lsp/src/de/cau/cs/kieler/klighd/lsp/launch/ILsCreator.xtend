/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.launch

import com.google.inject.Injector
import com.google.inject.Module
import java.io.InputStream
import java.io.OutputStream
import java.util.concurrent.ExecutorService
import java.util.function.Function
import org.eclipse.lsp4j.jsonrpc.MessageConsumer
import org.eclipse.xtext.ide.server.LanguageServerImpl

/**
 * Interface for convenient creation of a language server.
 * This involves binding of modules and creating, starting, and configure logging for an LS.
 * 
 * @author nre
 */
interface ILsCreator {
    
    /**
     * Binds all necessary classes to start the LS.
     * @param socket boolean whether modules for socket or stdio case are generated.
     */
    def Module createLSModules(boolean socket)
    
    /**
     * Build and starts a LS and handles logging configuration if necessary.
     * Registers all ILanguageServerExtensions registered via ServiceLoader as extensions to the existing ls given as
     * parameter.
     * @param injector The Injector used to get all LSExtensions. KGraphLSExtension must be registered.
     * @param ls The LangaugeServerImpl that is started. Usually the KGraphLSExtension, which is a DiagramLanguageServer.
     * @param in The InputStream for communication.
     * @param out The OutputStream for communication.
     * @param executorService The executor service used to start threads.
     * @param wrapper A function for plugging in additional message consumers.
     * @param socket Whether the language server is created for the socket or stdio case.
     */
    def void buildAndStartLS(Injector injector, LanguageServerImpl ls, InputStream in, OutputStream out,
        ExecutorService executorService, Function<MessageConsumer, MessageConsumer> wrapper, boolean socket)
    
}