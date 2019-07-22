/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.constraints

/**
 * @author jet, cos
 * 
 */
class DeleteConstraint {
    String uri
    String id

    new(String uri, String id) {
        this.uri = uri
        this.id = id
    }

    def String getUri() {
        return this.uri
    }

    def String getID() {
        return id
    }
}
