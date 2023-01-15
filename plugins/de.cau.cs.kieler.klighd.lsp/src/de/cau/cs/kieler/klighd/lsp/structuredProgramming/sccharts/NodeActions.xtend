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
import de.cau.cs.kieler.klighd.structuredEditMsg.InputType

/**
 * 
 * 
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class RenameNodeAction implements Action {
    public static val KIND = 'SCChart_graph_RenameNode'
    String kind = KIND
    
    public String id
    public String newName
    
    new() {}
    new(Consumer<RenameNodeAction> initializer) {
        initializer.accept(this)
    }
    
    def static InputType[] getInputs() {
        val input1 = new InputType("newName","String","New Name");
        return #[input1];
    }
}

/**
 * 
 * 
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class AddEdgeAction implements Action {
    public static val KIND = 'SCChart_graph_AddEdge'
    String kind = KIND
    
    public String id
    public String dest_string
    public String inputs_string
    public String outputs_string
    
    new() {}
    new(Consumer<AddEdgeAction> initializer) {
        initializer.accept(this)
    }
    
    def static InputType[] getInputs() {
        val input1 = new InputType("destination","String","Destination");
        val input2 = new InputType("inputs", "String", "Enable Inputs");
        val input3 = new InputType("outputs", "String", "Outputs to set")
        return #[input1, input2, input3];
    }
}

/**
 * 
 * 
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class AddSuccessorNodeAction implements Action {
    public static val KIND = 'SCChart_graph_AddSNode'
    String kind = KIND
    
    public String id
    public String newNodeName
    public String edgeInput
    public String edgeOutput
    
    new() {}
    new(Consumer<AddSuccessorNodeAction> initializer) {
        initializer.accept(this)
    }
    
    def static InputType[] getInputs() {
        val input1 = new InputType("newNodeName","String","Name of Node");
        val input2 = new InputType("edgeInput", "String", "Enable Inputs");
        val input3 = new InputType("edgeOutput", "String", "Outputs to set")
        return #[input1, input2, input3];
    }
}

/**
 * 
 * 
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class AddHirachicalNodeAction implements Action {
    public static val KIND = 'SCChart_graph_AddHNode'
    String kind = KIND
    
    public String id
    public String next_name
    public String region_name
    
    new() {}
    new(Consumer<AddHirachicalNodeAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        val input1 = new InputType("next_name","String","State Name");
        val input2 = new InputType("region_name", "String", "Region Name");
        return #[input1, input2];
    }
}





