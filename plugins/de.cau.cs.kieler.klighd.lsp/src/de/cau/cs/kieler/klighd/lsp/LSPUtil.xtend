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

import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.kgraph.KNode
import org.eclipse.elk.graph.properties.IProperty
import de.cau.cs.kieler.klighd.lsp.utils.KGraphElementIDGenerator

/**
 * @author sdo
 * 
 */
class LSPUtil {

    /**
     * Returns the root node of the resource's model behind a given {@cods uri}.
     * 
     * @param diagramState The state of the diagram
     * @param uri The uri that points at the desired resource.
     * @return The root node of the resource's model
     */
    public static def getRoot(KGraphDiagramState diagramState, String uri) {

        var ViewContext viewContext = null
        synchronized (diagramState) {
            viewContext = diagramState.getKGraphContext(uri)
        }

        return viewContext?.viewModel

    }

    /**
     * Returns the {@code KNode of the node described by {@code ID}.
     * Returns null if the {@code ViewContext} of the resource described by {@code uri} is null.
     * Returns null if the element behind the ID is no kNode.
     */
    public static def getKNode(KGraphDiagramState diagramState, String uri, String ID) {

        val mapKToS = diagramState.getKGraphToSModelElementMap(uri)

        // KGraphElement which corresponding SNode has the correct ID
        val kGraphElement = KGraphElementIDGenerator.findElementById(mapKToS, ID)

        if (kGraphElement instanceof KNode) {
            return kGraphElement as KNode
        } else {
            return null
        }
    }
}
