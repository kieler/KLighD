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
package de.cau.cs.kieler.klighd.lsp

import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.lsp.interactive.ConstraintProperty
import de.cau.cs.kieler.klighd.lsp.interactive.IConstraintSerializer
import java.io.ByteArrayOutputStream
import java.util.List
import java.util.Map
import org.eclipse.elk.graph.ElkNode
import org.eclipse.lsp4j.Position
import org.eclipse.lsp4j.Range
import org.eclipse.lsp4j.TextEdit

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
    
    override serializeConstraints(List<ConstraintProperty<Object>> changedNodes,
        Object graph,
        String uri,
        KGraphLanguageServerExtension ls,
        KGraphLanguageClient client
    ) {
        // Serialize model into given uri.
        val resource = ls.getResource(uri)
            
        // Get previous file content as String
        var outputStream = new ByteArrayOutputStream
        resource.save(outputStream, emptyMap)
        val codeBefore = outputStream.toString
        changedNodes.forEach[c|
            val ElkNode elkNode = c.KNode.getProperty(KlighdInternalProperties.MODEL_ELEMENT) as ElkNode
            elkNode.setProperty(c.property, c.value)
        ]
        val Map<String, List<TextEdit>> changes = newHashMap 
        // Get changed file as String
        outputStream = new ByteArrayOutputStream
        resource.save(outputStream, emptyMap)
        val String codeAfter = outputStream.toString().trim
        
        // The range is the length of the previous file.
        // Just make sure the range is big enough
        val Range range = new Range(new Position(0, 0), new Position(codeBefore.split("\r\n|\r|\n").length * 2, 0))
        val TextEdit textEdit = new TextEdit(range, codeAfter)
        changes.put(uri, #[textEdit]);
        client.replaceContentInFile(uri, codeAfter, range)
    }
    
}
