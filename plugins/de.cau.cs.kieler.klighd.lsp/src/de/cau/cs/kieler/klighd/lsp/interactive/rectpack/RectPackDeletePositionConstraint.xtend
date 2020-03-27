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
package de.cau.cs.kieler.klighd.lsp.interactive.rectpack

import org.eclipse.xtend.lib.annotations.Data

/**
 * Message send if an order constraint is deleted.
 * Only the node id is requiered to do so, since it has its previous position saved together with its actual position.
 * 
 * @author sdo
 * 
 */
@Data
class RectPackDeletePositionConstraint {
    String id
}
