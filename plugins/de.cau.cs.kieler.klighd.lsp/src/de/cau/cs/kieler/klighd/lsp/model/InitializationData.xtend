/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.model

import org.eclipse.xtend.lib.annotations.Data

/**
 * Additional options that can be sent by a client to initialize some options on the server.
 * 
 * @author nre
 */
@Data
class KeithInitializationOptions {
    /**
     * Option to indicate if selected elements in the text should also automatically select and focus the elements in
     * the diagram. Default value is false, if not requested differently by the client.
     */
    boolean shouldSelectDiagram
    
    /**
     * Option to indicate if selected elements in the diagram should also automatically select the elements in the text.
     * Default value is false, if not requested differently by the client.
     */
    boolean shouldSelectText
}