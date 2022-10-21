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
package de.cau.cs.kieler.klighd.lsp

import de.cau.cs.kieler.klighd.lsp.interactive.INodeIdProvider
import org.eclipse.elk.graph.ElkNode

/**
 * Service class that returns the id of ELK nodes.
 * 
 * @author sdo
 *
 */
class ElkGraphNodeIdProvider implements INodeIdProvider {
    
    override canHandle(Object graph) {
        return graph instanceof ElkNode
    }
    
    override getNodeId(Object element) {
        return (element as ElkNode).labels.get(0).text
    }
    
}
