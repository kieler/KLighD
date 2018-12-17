/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.gson_utils

import de.cau.cs.kieler.klighd.lsp.model.SKGraph
import java.util.Deque
import java.util.List
import org.eclipse.lsp4j.jsonrpc.MessageConsumer
import org.eclipse.lsp4j.jsonrpc.messages.MessageIssue
import org.eclipse.lsp4j.jsonrpc.validation.ReflectiveMessageValidator

/**
 * Extension to the lsp4j {@link ReflectiveMessageValidator} to ignore direct or indirect circular references in sent
 * messages for {@link SKGraph} objects. All circular dependencies therefore of {@link SKGraph} elements have to be 
 * excluded from the Gson serialization to prevent StackOverflowErrors during serialization.
 * 
 * @author nre
 */
class ReflectiveMessageValidatorExcludingSKGraph extends ReflectiveMessageValidator {
    new(MessageConsumer delegate) {
        super(delegate)
    }
    override validate(Object object, List<MessageIssue> issues, Deque<Object> objectStack, Deque<Object> accessorStack) {
        if (object instanceof SKGraph) {
            return
        }
        super.validate(object, issues, objectStack, accessorStack)
    }
}