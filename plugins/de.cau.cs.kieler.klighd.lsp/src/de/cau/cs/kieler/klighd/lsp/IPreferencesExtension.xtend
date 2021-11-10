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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
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