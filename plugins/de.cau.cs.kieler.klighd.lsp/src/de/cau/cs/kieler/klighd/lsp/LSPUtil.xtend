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
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.kgraph.KNode

/**
 * Utility methods for graphs in a language server context.
 * 
 * @author sdo
 */
class LSPUtil {

    /**
     * Returns the root node of the resource's model behind a given {@cods uri}.
     * 
     * @param diagramState The state of the diagram
     * @param uri The uri that points at the desired resource.
     * @return The root node of the resource's model
     */
    static def getRoot(KGraphDiagramState diagramState, String uri) {

        var ViewContext viewContext = null
        synchronized (diagramState) {
            viewContext = diagramState.getKGraphContext(uri)
        }

        return viewContext?.viewModel
    }

    /**
     * Get a {@code KNode} based by id.
     * 
     * @param diagramState The state of the diagram
     * @param uri The uri of the model file
     * @param nodeId The id of the node element
     * @return the {@code KNode} of the node described by {@code id}.
     * Returns null if the {@code ViewContext} of the resource described by {@code uri} is null.
     * Returns null if the element behind the id is no kNode.
     */
    static def getKNode(KGraphDiagramState diagramState, String uri, String nodeId) {
        val kGraphElement = diagramState.getIdToKGraphMap(uri).get(nodeId)

        if (kGraphElement instanceof KNode) {
            return kGraphElement as KNode
        } else {
            return null
        }
    }
    
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
