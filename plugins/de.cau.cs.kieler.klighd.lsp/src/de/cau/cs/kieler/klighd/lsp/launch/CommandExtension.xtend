/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018,2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.launch

import java.util.concurrent.CompletableFuture
import org.eclipse.lsp4j.jsonrpc.services.JsonRequest
import org.eclipse.lsp4j.jsonrpc.services.JsonSegment

/**
 * Interface to the LSP extension commands for language registration.
 * 
 * @author sdo
 */
@JsonSegment('keith/registration')
interface CommandExtension {
    
    /**
     * Returns all languages registered in language server with information to register them in a Monaco editor.
     */
    @JsonRequest('get-languages')
    def CompletableFuture<Object> getLanguages();
    
}
