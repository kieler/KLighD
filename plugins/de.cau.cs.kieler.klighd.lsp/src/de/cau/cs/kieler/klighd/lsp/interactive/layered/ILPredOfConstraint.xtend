/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2022 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.interactive.layered

import org.eclipse.xtend.lib.annotations.Data

/**
 * Data class for a 'in layer predecessor of'-constraint.
 * 
 * @author jep
 */
@Data
class ILPredOfConstraint {
    String id
    String otherNode
}
