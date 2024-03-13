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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
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
class SetStaticConstraintAction implements Action {
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
class SetPositionConstraintAction implements Action {
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
class SetLayerConstraintAction implements Action {
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
class DeleteStaticConstraintAction implements Action {
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
class DeletePositionConstraintAction implements Action {
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
class DeleteLayerConstraintAction implements Action {
    public static val KIND = 'deleteLayerConstraint'
    String kind = KIND
    
    DeleteConstraint constraint
    
    new() {}
    new(Consumer<DeleteLayerConstraintAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Sets a 'in layer predecessor of'-constraint for a node.
 * 
 * @author jep
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class SetInLayerPredecessorOfConstraintAction implements Action {
    public static val KIND = 'setILPredOfConstraint'
    String kind = KIND
    
    InLayerPredecessorOfConstraint constraint
    
    new() {}
    new(Consumer<SetInLayerPredecessorOfConstraintAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Sets a 'in layer successor of'-constraint for a node.
 * 
 * @author jep
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class SetInLayerSuccessorOfConstraintAction implements Action {
    public static val KIND = 'setILSuccOfConstraint'
    String kind = KIND
    
    InLayerSuccessorOfConstraint constraint
    
    new() {}
    new(Consumer<SetInLayerSuccessorOfConstraintAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Deletes the relative constraints on the node that is identified by the given id.
 * 
 * @author jep
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class DeleteRelativeConstraintsAction implements Action {
    public static val KIND = 'deleteRelativeConstraints'
    String kind = KIND
    
    DeleteConstraint constraint
    
    new() {}
    new(Consumer<DeleteRelativeConstraintsAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Deletes the iLPredOf constraint on the node that is identified by the given id.
 * 
 * @author jep
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class DeleteInLayerPredecessorOfConstraintAction implements Action {
    public static val KIND = 'deleteILPredOfConstraint'
    String kind = KIND
    
    DeleteConstraint constraint
    
    new() {}
    new(Consumer<DeleteInLayerPredecessorOfConstraintAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Deletes the iLSuccOf constraint on the node that is identified by the given id.
 * 
 * @author jep
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class DeleteInLayerSuccessorOfConstraintAction implements Action {
    public static val KIND = 'deleteILSuccOfConstraint'
    String kind = KIND
    
    DeleteConstraint constraint
    
    new() {}
    new(Consumer<DeleteInLayerSuccessorOfConstraintAction> initializer) {
        initializer.accept(this)
    }
}

