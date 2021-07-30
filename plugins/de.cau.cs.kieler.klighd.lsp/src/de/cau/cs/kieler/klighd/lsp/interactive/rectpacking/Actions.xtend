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

import java.util.function.Consumer
import org.eclipse.sprotty.Action
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.EqualsHashCode
import org.eclipse.xtend.lib.annotations.ToString

/**
 * Sets the order of a node for the RectPack algorithm.
 * 
 * @author sdo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class RectpackingSetPositionConstraintAction implements Action {
    public static val KIND = 'rectPackSetPositionConstraint'
    String kind = KIND
    
    RectpackingSetPositionConstraint constraint
    
    new() {}
    new(Consumer<RectpackingSetPositionConstraintAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Deletes the constraint on the node that is identified by the given id.
 * 
 * @author sdo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class RectpackingDeletePositionConstraintAction implements Action {
    public static val KIND = 'rectPackDeletePositionConstraint'
    String kind = KIND
    
    RectpackingDeletePositionConstraint constraint
    
    new() {}
    new(Consumer<RectpackingDeletePositionConstraintAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Deletes the constraint on the node that is identified by the given id.
 * 
 * @author sdo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class SetAspectRatioAction implements Action {
    public static val KIND = 'setAspectRatio'
    String kind = KIND
    
    SetAspectRatio constraint
    
    new() {}
    new(Consumer<SetAspectRatioAction> initializer) {
        initializer.accept(this)
    }
}
