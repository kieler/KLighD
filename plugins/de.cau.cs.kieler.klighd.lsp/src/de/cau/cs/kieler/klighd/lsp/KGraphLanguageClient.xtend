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
package de.cau.cs.kieler.klighd.lsp

import org.eclipse.lsp4j.jsonrpc.services.JsonNotification
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.lsp4j.Range

/**
 * Register all server to client notifications and requests.
 * 
 * @author sdo
 */
interface KGraphLanguageClient extends LanguageClient {
    
    /**
     * Send to client if some message should be displayed. Type is one of "info", "warn", and "error".
     */
    @JsonNotification("general/sendMessage")
    def void sendMessage(String message, String type)
    
    /**
     * Send to the client to replace the given range of the file given by the uri by the code string.
     */
    @JsonNotification("general/replaceContentInFile")
    def void replaceContentInFile(String uri, String code, Range range)
}