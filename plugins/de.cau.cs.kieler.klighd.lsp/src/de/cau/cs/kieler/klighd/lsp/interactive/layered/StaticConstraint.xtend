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
package de.cau.cs.kieler.klighd.lsp.interactive.layered

import org.eclipse.xtend.lib.annotations.Data

/**
 * Data class for a combined position and layer constraint.
 * 
 * @author jet, cos, sdo
 */
@Data
class StaticConstraint {

    /**
     * Id of the target node
     */
    String id

    /**
     * Value for the layer id
     */
    int layer

    /**
     * Value for the position id
     */
    int position
    
    /**
     * Value for the layer constraint
     */
    int layerCons
    
    /**
     * Value for the position constraint
     */
    int posCons
    
}
