/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2020-2024 by
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
import com.google.gson.JsonElement
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.KColor
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory
import de.cau.cs.kieler.klighd.util.ColorPreferences
import de.cau.cs.kieler.klighd.util.ColorThemeKind
import java.awt.Color

/**
 * Utility methods for graphs in a language server context.
 * 
 * @author sdo
 */
class LSPUtil {

    /**
     * Returns the root node of the resource's model behind a given {@code uri}.
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
            // Convert newlines to a line ending in "\" so that markdown will display the newline.
            .replace("\n", "\\\n")
            // Replace tabs with four spaces.
            .replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;")
    }
    
    
    
    /**
     * Parses a jsonElement for color preferences from the client. unreadable colors are defaulted to black and white.
     */
    static def ColorPreferences parseColorPreferences(JsonElement jsonColors) {
        if (jsonColors === null || !jsonColors.isJsonObject) return null
        val kind = jsonColors.asJsonObject.get("kind")
        val foreground = jsonColors.asJsonObject.get("foreground")
        val background = jsonColors.asJsonObject.get("background")
        val highlight = jsonColors.asJsonObject.get("highlight")
        val ColorThemeKind colorKind = if (kind === null) ColorThemeKind.LIGHT else ColorThemeKind.values.get(kind.asInt)
        val foregroundColor = parseColor(foreground)
        val backgroundColor = parseColor(background)
        val highlightColor = parseColor(highlight)
        
        return new ColorPreferences(colorKind, foregroundColor, backgroundColor, highlightColor)
    }
    
    /**
     * Parses a single color string in the form #RRGGBB into a KColor, or null if the string is unparsable.
     */
    static def KColor parseColor(String stringColor) {
        var KColor color = null
        try {
            val awtColor = Color.decode(stringColor)
            color = KRenderingFactory.eINSTANCE.createKColor
            color.red = awtColor.red
            color.green = awtColor.green
            color.blue = awtColor.blue
        } catch (NumberFormatException e) {}
        return color
    }
    
    /**
     * Parses a single color string in the form #RRGGBB into a KColor, or null if the string is unparsable.
     */
    static def KColor parseColor(JsonElement jsonColor) {
        if (jsonColor === null || !jsonColor.isJsonPrimitive) return null
        return parseColor(jsonColor.asString)
    }
}
