/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012-2025 by
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
package de.cau.cs.kieler.klighd.krendering.extensions

/**
 * A convenient helper while creating KPositions. We'll see in future whether it is helpful.
 *    
 * @author chsch
 */
enum PositionReferenceY {

    /** Indicator of relative positioning based on top border of parent. */
    TOP,
    /** Indicator of relative positioning based on bottom border of parent. */
    BOTTOM
    
}
