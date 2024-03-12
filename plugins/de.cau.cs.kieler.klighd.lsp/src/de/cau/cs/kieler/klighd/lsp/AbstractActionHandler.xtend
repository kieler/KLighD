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

import java.util.Map
import org.eclipse.sprotty.Action

/**
 * Abstract class to handle Sprotty actions.
 * 
 * @author sdo
 */
abstract class AbstractActionHandler implements IActionHandler {
    
    Map<String, Class<? extends Action>> supportedMessages
        
    override Map<String, Class<? extends Action>> getSupportedMessages() {
        return supportedMessages
    }
        
    def setSupportedMessages(Map<String, Class<? extends Action>> messages) {
        this.supportedMessages = messages
    }
    
    override canHandleAction(String kind) {
        return supportedMessages.containsKey(kind)            
    }
}