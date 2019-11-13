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

import java.util.Map
import org.eclipse.lsp4j.jsonrpc.services.JsonNotification
import org.eclipse.lsp4j.jsonrpc.services.JsonSegment

/**
 * Interface describing methods needed for functionality of setting and updating KEITH specific preferences stored on
 * the client.
 * 
 * @author nre
 */
@JsonSegment('keith/preferences')
interface IPreferencesExtension {
    /**
     * Method called by a client to configure the preferences needed on the server.
     * 
     * @param param Defines the KEITH-server-specific preferences.
     */
    @JsonNotification('setPreferences')
    def void setPreferences(Map<String, Object> param)
}