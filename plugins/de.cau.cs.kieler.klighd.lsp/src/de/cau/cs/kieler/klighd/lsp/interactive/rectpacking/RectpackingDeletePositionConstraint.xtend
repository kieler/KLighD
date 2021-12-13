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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.lsp.interactive.rectpacking

import org.eclipse.xtend.lib.annotations.Data

/**
 * Message send if an order constraint is deleted.
 * Only the node id is required to do so, since it has its previous position saved together with its actual position.
 * 
 * @author sdo
 */
@Data
class RectpackingDeletePositionConstraint {
    String id
}
