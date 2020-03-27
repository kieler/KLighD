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
package de.cau.cs.kieler.klighd.lsp.interactive.layered

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
public class DeleteStaticConstraintAction implements Action {
    public static val KIND = 'deleteStaticConstraint'
    String kind = KIND
    
    DeleteConstraint constraint
    
    new() {}
    new(Consumer<DeleteStaticConstraintAction> initializer) {
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
public class DeletePositionConstraintAction implements Action {
    public static val KIND = 'deletePositionConstraint'
    String kind = KIND
    
    DeleteConstraint constraint
    
    new() {}
    new(Consumer<DeletePositionConstraintAction> initializer) {
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
public class DeleteLayerConstraintAction implements Action {
    public static val KIND = 'deleteLayerConstraint'
    String kind = KIND
    
    DeleteConstraint constraint
    
    new() {}
    new(Consumer<DeleteLayerConstraintAction> initializer) {
        initializer.accept(this)
    }
}
