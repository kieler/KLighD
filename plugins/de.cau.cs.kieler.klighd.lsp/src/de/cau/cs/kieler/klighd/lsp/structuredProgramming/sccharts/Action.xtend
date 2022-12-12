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
package de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts

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
class DeleteAction implements Action {
    public static val KIND = 'graph_Delete'
    String kind = KIND
    
    String[] toDelete
    
    new() {}
    new(Consumer<DeleteAction> initializer) {
        initializer.accept(this)
    }
}

/**
 * Sets a static constraint, meaning a position and a layer constraint for a node.
 * 
 * @author sdo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class AddSuccessorNodeAction implements Action {
    public static val KIND = 'graph_AddSNode'
    String kind = KIND
    
    String node
    String inputs
    String outputs
    String next_name
    
    new() {}
    new(Consumer<AddSuccessorNodeAction> initializer) {
        initializer.accept(this)
    }
}