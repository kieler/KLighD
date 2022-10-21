/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2022 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.interactive

/**
 * Service interface that can provide a node id for a given graph element.
 * 
 * @author sdo
 *
 */
interface INodeIdProvider {
    /**
     * Checks whether this serializer can handle a graph type
     * 
     * @param graph The graph
     * @return true if the node provider supports the given graph
     */
    def boolean canHandle(Object graph);
    
    /**
     * Returns an id string of a given graph element
     * 
     * @param  element The graph element
     * @return An id string
     */
    def String getNodeId(Object element);
}