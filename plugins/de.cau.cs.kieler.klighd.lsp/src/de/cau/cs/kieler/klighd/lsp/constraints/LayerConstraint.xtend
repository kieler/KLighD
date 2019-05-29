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
class LayerConstraint {
    String uri
    String id
    int layer

    new(String uri, String id, int layer) {
        this.uri = uri
        this.id = id
        this.layer = layer
    }

    def String getUri() {
        return this.uri
    }

    def String getID() {
        return id
    }

    def int getLayer() {
        return layer
    }
}
