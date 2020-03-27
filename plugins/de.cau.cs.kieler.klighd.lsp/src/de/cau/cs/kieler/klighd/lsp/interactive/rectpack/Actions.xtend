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
public class RectPackSetPositionConstraintAction implements Action {
    public static val KIND = 'rectPackSetPositionConstraint'
    String kind = KIND
    
    RectPackSetPositionConstraint constraint
    
    new() {}
    new(Consumer<RectPackSetPositionConstraintAction> initializer) {
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
public class RectPackDeletePositionConstraintAction implements Action {
    public static val KIND = 'rectPackDeletePositionConstraint'
    String kind = KIND
    
    RectPackDeletePositionConstraint constraint
    
    new() {}
    new(Consumer<RectPackDeletePositionConstraintAction> initializer) {
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
