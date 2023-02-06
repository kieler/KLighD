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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.lsp

import com.google.common.html.HtmlEscapers
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KEdge
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.sccharts.State
import de.cau.cs.kieler.kexpressions.impl.ValuedObjectImpl

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
     * Get a {@code KEdge} based by id.
     * 
     * @param diagramState The state of the diagram
     * @param uri The uri of the model file
     * @param edgeId The id of the edge element
     * @return the {@code KEdge} of the edge described by {@code id}.
     * Returns null if the {@code ViewContext} of the resource described by {@code uri} is null.
     * Returns null if the element behind the id is no kEdge.
     */
    static def getKEdge(KGraphDiagramState diagramState, String uri, String nodeId) {
        val kGraphElement = diagramState.getIdToKGraphMap(uri).get(nodeId)

        if (kGraphElement instanceof KEdge) {
            return kGraphElement as KEdge
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
            // Convert newlines to a line ending in "\" so that markdown will display the newline.
            .replace("\n", "\\\n")
            // Replace tabs with four spaces.
            .replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;")
    }
    
    static def ValuedObjectImpl getValuedObjectReference( KGraphDiagramState diagramState, String uri, String name ){
        val root = LSPUtil.getRoot(diagramState, uri)
        val node = root.children.get(0).getProperty(KlighdInternalProperties.MODEL_ELEMEMT) as State 
        for(declaration: node.declarations){
            for(obj: declaration.valuedObjects){
                if(obj.name == name)return obj as ValuedObjectImpl
            }
        }
    }
}
