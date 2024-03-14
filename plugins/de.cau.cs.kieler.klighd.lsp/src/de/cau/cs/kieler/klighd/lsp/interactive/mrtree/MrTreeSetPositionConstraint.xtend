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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.lsp.interactive.mrtree

import org.eclipse.xtend.lib.annotations.Data

/**
 * Data class for a position constraint sent from client to server for the mrtree algorithm.
 * 
 * @author sdo
 */
@Data
class MrTreeSetPositionConstraint {
    String id
    int position
    int positionConstraint
}
