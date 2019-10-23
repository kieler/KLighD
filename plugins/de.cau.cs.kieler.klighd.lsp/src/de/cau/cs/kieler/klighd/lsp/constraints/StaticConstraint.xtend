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
class StaticConstraint {

    /**
     * URI of the resource as String
     */
    String uri

    /**
     * Id of the target node
     */
    String id

    /**
     * Value for the layer constraint
     */
    int layer

    /**
     * Value for the positional constraint
     */
    int position
    
    int posCons
    
    int layerCons

    new(String uri, String id, int layer, int layerCons, int position, int posCons) {
        this.uri = uri
        this.id = id
        this.layer = layer
        this.position = position
        this.posCons = posCons
        this.layerCons = layerCons
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

    def int getPosition() {
        return position
    }
    
    def int getLayerCons(){
        return layerCons
    }
    
    def int getPosCons(){
        return posCons
    }

}
