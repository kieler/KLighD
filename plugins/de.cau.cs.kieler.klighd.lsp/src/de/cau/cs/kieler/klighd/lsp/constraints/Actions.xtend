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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.constraints

import java.util.function.Consumer
import org.eclipse.sprotty.Action
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.EqualsHashCode
import org.eclipse.xtend.lib.annotations.ToString

/**
 * Sets a static constraint, meaning a position and a layer constraint for a node.
 * 
 * @author sdo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
public class SetStaticConstraintAction implements Action {
    public static val KIND = 'setStaticConstraint'
    String kind = KIND
    
    StaticConstraint constraint
    
    new() {}
    new(Consumer<SetStaticConstraintAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Sets a position constraint for a node.
 * 
 * @author sdo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
public class SetPositionConstraintAction implements Action {
    public static val KIND = 'setPositionConstraint'
    String kind = KIND
    
    PositionConstraint constraint
    
    new() {}
    new(Consumer<SetPositionConstraintAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Sets a layer constraint for a node.
 * 
 * @author sdo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
public class SetLayerConstraintAction implements Action {
    public static val KIND = 'setLayerConstraint'
    String kind = KIND
    
    LayerConstraint constraint
    
    new() {}
    new(Consumer<SetLayerConstraintAction> initializer) {
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
public class DeleteConstraintAction implements Action {
    public static val KIND = 'DeleteConstraint'
    String kind = KIND
    
    DeleteConstraint constraint
    
    new() {}
    new(Consumer<DeleteConstraintAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Refreshes the layout. This is currently not used and might not be feasible in that context.
 * 
 * @author sdo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
public class RefreshLayoutAction implements Action {
    public static val KIND = 'refreshLayout'
    String kind = KIND
    
    new() {}
    new(Consumer<RefreshLayoutAction> initializer) {
        initializer.accept(this)
    }
}