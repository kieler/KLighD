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

import com.google.common.html.HtmlEscapers
import org.eclipse.lsp4j.Range
import org.eclipse.lsp4j.jsonrpc.services.JsonNotification
import org.eclipse.lsp4j.services.LanguageClient

/**
 * Register all server to client notifications and requests.
 * 
 * @author sdo
 */
interface KGraphLanguageClient extends LanguageClient {
    
    /**
     * Send to client if some message should be displayed. {@code type} is one of "info", "warn", and "error".
     */
    @JsonNotification("general/sendMessage")
    def void sendMessage(String message, String type)
    
    /**
     * Send to the client to replace the given range of the file given by the URI by the code string.
     */
    @JsonNotification("general/replaceContentInFile")
    def void replaceContentInFile(String uri, String code, Range range)
    
    /**
     * Escapes the given message to be safely displayable in a client context putting this message into an HTML page
     * to avoid possibilities of XSS attacks and a clearly readable message. Uses Google Guava's {@link HtmlEscapers}
     * for making the message safe and custom String replacement to replace line breaks and tabulators with HTML
     * counterparts.
     * 
     * @param message The unescaped and possibly unsafe message String.
     * @return An escaped and safe String to display in HTML.
     */
    static def String escapeHtml(String message) {
        return HtmlEscapers.htmlEscaper.escape(message)
            // TODO: HTML tags are no longer allowed in Theia to prevent XSS, also preventing
            // any newlines. Look for a solution by following Theia issue #8743:
            // https://github.com/eclipse-theia/theia/issues/8743
//            .replace("\n", "<br>\n")
            .replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;")
    }
    
}