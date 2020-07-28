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

/**
 * Interface for handling information, warning, or error messages to be visible for the user.
 * 
 * @author nre
 */
interface INotificationHandler {
    
    /**
     * Send a message that will be displayed as an <b>error</b> to the client.
     * 
     * @param message The message to the client.
     * @return {@code true}, if the language client that supports this message is connected.
     */
    def boolean sendError(String message)
    
    /**
     * Send a message that will be displayed as a <b>warning</b> to the client.
     * 
     * @param message The message to the client.
     * @return {@code true}, if the language client that supports this message is connected.
     */
    def boolean sendWarning(String message)
    
    /**
     * Send a message that will be displayed as an <b>info</b> message to the client.
     * 
     * @param message The message to the client.
     * @return {@code true}, if the language client that supports this message is connected.
     */
    def boolean sendInfo(String message)
    
}