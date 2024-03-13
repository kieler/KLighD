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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.lsp.interactive

import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import java.util.List
import org.eclipse.elk.graph.ElkNode
import org.eclipse.emf.ecore.resource.Resource

/**
 * Serializes constraint for an ELK graph by just adding the corresponding properties.
 * 
 * @author sdo
 * 
 */
class ElkGraphConstraintSerializer implements IConstraintSerializer {

    override canHandle(Object graph) {
        return graph instanceof ElkNode
    }

    override serializeConstraints(
        List<ConstraintProperty<Object>> changedNodes,
        Object graph,
        Resource resource
    ) {
        val codeBefore = InteractiveUtil.serializeResource(resource)
        changedNodes.forEach [ c |
            val ElkNode elkNode = c.KNode.getProperty(KlighdInternalProperties.MODEL_ELEMENT) as ElkNode
            elkNode.setProperty(c.property, c.value)
        ]
        
        val codeAfter = InteractiveUtil.serializeResource(resource)

        return InteractiveUtil.calculateTextEdit(codeBefore, codeAfter)
    }

}
