/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019-2022 by
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
import java.util.Deque
import java.util.List
import org.eclipse.lsp4j.jsonrpc.JsonRpcException
import org.eclipse.lsp4j.jsonrpc.MessageConsumer
import org.eclipse.lsp4j.jsonrpc.MessageIssueException
import org.eclipse.lsp4j.jsonrpc.messages.Message
import org.eclipse.lsp4j.jsonrpc.messages.MessageIssue
import org.eclipse.lsp4j.jsonrpc.validation.ReflectiveMessageValidator
import org.eclipse.sprotty.SModelElement
import org.eclipse.lsp4j.jsonrpc.messages.NotificationMessage
import org.eclipse.sprotty.ActionMessage

/**
 * Extension to the lsp4j {@link ReflectiveMessageValidator} to ignore direct or indirect circular references in sent
 * messages for known elements that are or may contain {@link EMapPropertyHolder}, where the map may cause circular
 * paths, that have to be excluded from the Gson serialization to prevent StackOverflowErrors.
 * Excludes {@link SModelElement}s and {@link KImage}s from checking for circular dependencies. This expects the
 * serializer to handle the circular dependencies by not serializing them.
 * 
 * @author nre
 */
class ReflectiveMessageValidatorExcludingSKGraph extends ReflectiveMessageValidator {
    new(MessageConsumer delegate) {
        super(delegate)
    }
    override validate(Object object, List<MessageIssue> issues, Deque<Object> objectStack, Deque<Object> accessorStack) {
        if (object instanceof SModelElement ||
            object instanceof KImage) {
            return
        }
        super.validate(object, issues, objectStack, accessorStack)
    }
    
    
    override void consume(Message message) throws MessageIssueException, JsonRpcException {
        System.out.println(System.currentTimeMillis() + ": *****Server[ReflectiveMessageValidatorExcludingSKGraph]: Gson mapping started");
        if (message instanceof NotificationMessage && (message as NotificationMessage)?.params instanceof ActionMessage) {
            System.out.println(((message as NotificationMessage)?.params as ActionMessage)?.action.kind)
        }
        super.consume(message)
        System.out.println(System.currentTimeMillis() + ": *****Server[ReflectiveMessageValidatorExcludingSKGraph]: Gson mapping finished");
    }
}