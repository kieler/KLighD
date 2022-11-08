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

import java.util.function.Consumer
import org.eclipse.sprotty.Action
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.EqualsHashCode
import org.eclipse.xtend.lib.annotations.ToString

/**
 * Sets the order of a node for the MrTree algorithm.
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
public class MrTreeSetPositionConstraintAction implements Action {
    public static val KIND = 'treeSetPositionConstraint'
    String kind = KIND
    
    MrTreeSetPositionConstraint constraint
    
    new() {}
    new(Consumer<MrTreeSetPositionConstraintAction> initializer) {
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
public class MrTreeDeletePositionConstraintAction implements Action {
    public static val KIND = 'treeDeletePositionConstraint'
    String kind = KIND
    
    MrTreeDeletePositionConstraint constraint
    
    new() {}
    new(Consumer<MrTreeDeletePositionConstraintAction> initializer) {
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
public class SetAspectRatioAction implements Action {
    public static val KIND = 'setAspectRatio'
    String kind = KIND
    
    SetAspectRatio constraint
    
    new() {}
    new(Consumer<SetAspectRatioAction> initializer) {
        initializer.accept(this)
    }
}
