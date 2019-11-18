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

import org.eclipse.xtend.lib.annotations.Data

/**
 * @author jet, cos, sdo
 * 
 */
@Data
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
}
