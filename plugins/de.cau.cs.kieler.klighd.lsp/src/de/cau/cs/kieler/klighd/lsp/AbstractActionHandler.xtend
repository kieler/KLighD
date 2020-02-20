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

import java.util.HashMap
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * @author sdo
 *
 */
abstract class AbstractActionHandler implements IActionHandler {
    
    @Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
    HashMap<String, Class<?>> supportedMessages = newHashMap
    
    override canHandleAction(String kind) {
        return supportedMessages.containsKey(kind)
            
    }    
}