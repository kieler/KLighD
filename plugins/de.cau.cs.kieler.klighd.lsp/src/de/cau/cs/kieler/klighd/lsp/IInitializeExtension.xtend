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
package de.cau.cs.kieler.klighd.lsp

import de.cau.cs.kieler.klighd.lsp.model.KeithInitializationOptions
import org.eclipse.lsp4j.jsonrpc.services.JsonNotification
import org.eclipse.lsp4j.jsonrpc.services.JsonSegment

/**
 * Interface describing methods needed for functionality of resetting Keith specific options sent during initialization.
 * 
 * @author nre
 */
@JsonSegment('keith/initializeOptions')
interface IInitializeOptionsExtension {
    /**
     * Method called by a client to reinitialize the options configured during the LSP initialize method.
     * 
     * @param param Defines the new Keith-specific options
     */
    @JsonNotification('reinitializeOptions')
    public def void reinitializeOptions(KeithInitializationOptions param)
}