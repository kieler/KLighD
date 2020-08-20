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
package de.cau.cs.kieler.klighd.lsp.interactive.rectpacking

import org.eclipse.xtend.lib.annotations.Data

/**
 * Data class for a position constraint sent from client to server for the rectpacking algorithm.
 * 
 * @author sdo
 */
@Data
class RectpackingSetPositionConstraint {
    String id
    int order
}
