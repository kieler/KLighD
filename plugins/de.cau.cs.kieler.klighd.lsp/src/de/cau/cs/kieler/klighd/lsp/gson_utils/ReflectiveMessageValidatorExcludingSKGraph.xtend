/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019,2020 by
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
package de.cau.cs.kieler.klighd.lsp.gson_utils

import de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder
import de.cau.cs.kieler.klighd.krendering.KImage
import de.cau.cs.kieler.klighd.lsp.model.SKGraph
import java.util.Deque
import java.util.List
import org.eclipse.lsp4j.jsonrpc.MessageConsumer
import org.eclipse.lsp4j.jsonrpc.messages.MessageIssue
import org.eclipse.lsp4j.jsonrpc.validation.ReflectiveMessageValidator

/**
 * Extension to the lsp4j {@link ReflectiveMessageValidator} to ignore direct or indirect circular references in sent
 * messages for known elements that are or may contain {@link EMapPropertyHolder}, where the map may cause circular
 * paths, that have to be excluded from the Gson serialization to prevent StackOverflowErrors.
 * Excludes {@link SKGraph}s and {@link KImage}s from checking for circular dependencies. 
 * 
 * @author nre
 */
class ReflectiveMessageValidatorExcludingSKGraph extends ReflectiveMessageValidator {
    new(MessageConsumer delegate) {
        super(delegate)
    }
    override validate(Object object, List<MessageIssue> issues, Deque<Object> objectStack, Deque<Object> accessorStack) {
        if (object instanceof SKGraph ||
            object instanceof KImage) {
            return
        }
        super.validate(object, issues, objectStack, accessorStack)
    }
}