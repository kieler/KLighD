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
package de.cau.cs.kieler.klighd.lsp.constraints


/**
 * @author jet, cos
 * 
 */
class PositionConstraint {
    String uri
    String id
    int position
    int posCons

    new(String uri, String id, int position, int posCons) {
        this.uri = uri
        this.id = id
        this.position = position
    }

    def String getUri() {
        return this.uri
    }

    def String getID() {
        return id
    }

    def int getPosition() {
        return position
    }
    
    def int getPosCons(){
        return posCons
    }
}