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
import de.cau.cs.kieler.klighd.structuredEditMsg.StructuredEditMsg


/**
 * 
 * 
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class EditSemanticDeclarationAction implements Action {
    public static val LABEL = "Edit sematic declarations"
    public static val KIND = 'SCChart_EditSemanticDeclarations'
    String kind = KIND
    public String id
    
    new() {}
    new(Consumer<EditSemanticDeclarationAction> initializer) {
        initializer.accept(this)
    }
    
    def static InputType[] getInputs() {
        return #[];
    }
    
    def static StructuredEditMsg getMsg() { 
        return new StructuredEditMsg(EditSemanticDeclarationAction.LABEL, 
            EditSemanticDeclarationAction.KIND, 
            false, 
            EditSemanticDeclarationAction.getInputs()
        )
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
class RenameStateAction implements Action {
    public static val LABEL = "Rename state"
    public static val KIND = 'SCChart_graph_RenameState'
    String kind = KIND
    
    public String id
    public String state_name
    
    new() {}
    new(Consumer<RenameStateAction> initializer) {
        initializer.accept(this)
    }
    
    def static InputType[] getInputs() {
        val input1 = new InputType("state_name","String","New Name");
        return #[input1];
    }
    
    def static StructuredEditMsg getMsg() { 
        return new StructuredEditMsg(RenameStateAction.LABEL, 
            RenameStateAction.KIND, 
            false, 
            RenameStateAction.getInputs()
        )
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
class AddTransitionAction implements Action {
    public static val LABEL = "Add new transition"
    public static val KIND = 'SCChart_graph_AddTransition'
    String kind = KIND
    
    public String id
    public String destination
    public String trigger
    public String effect
    
    new() {}
    new(Consumer<AddTransitionAction> initializer) {
        initializer.accept(this)
    }
    
    def static InputType[] getInputs() {
        val input1 = new InputType("destination","Select","Destination");
        val input2 = new InputType("trigger", "String", "Trigger");
        val input3 = new InputType("effect", "String", "Effect")
        return #[input1, input2, input3];
    }
    
    def static StructuredEditMsg getMsg() { 
        return new StructuredEditMsg(AddTransitionAction.LABEL, 
            AddTransitionAction.KIND, 
            false, 
            AddTransitionAction.getInputs()
        )
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
class AddSuccessorStateAction implements Action {
    public static val LABEL = "Add successor state"
    public static val KIND = 'SCChart_graph_AddSuccessorState'
    String kind = KIND
    
    public String id
    public String state_name
    public String trigger
    public String effect
    
    new() {}
    new(Consumer<AddSuccessorStateAction> initializer) {
        initializer.accept(this)
    }
    
    def static InputType[] getInputs() {
        val input1 = new InputType("state_name","String","Name of state");
        val input2 = new InputType("trigger", "String", "Trigger");
        val input3 = new InputType("effect", "String", "Effect")
        return #[input1, input2, input3];
    }
    
    def static StructuredEditMsg getMsg() { 
        return new StructuredEditMsg(AddSuccessorStateAction.LABEL, 
            AddSuccessorStateAction.KIND, 
            false, 
            AddSuccessorStateAction.getInputs()
        )
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
class AddHierarchicalStateAction implements Action {
    public static val LABEL = "Add hierarchical state"
    public static val KIND = 'SCChart_graph_AddHierarchicalState'
    String kind = KIND
    
    public String id
    public String state_name
    public String region_name
    
    new() {}
    new(Consumer<AddHierarchicalStateAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        val input1 = new InputType("state_name","String","State Name");
        val input2 = new InputType("region_name", "String", "Region Name");
        return #[input1, input2];
    }
    
    def static StructuredEditMsg getMsg() { 
        return new StructuredEditMsg(AddHierarchicalStateAction.LABEL, 
            AddHierarchicalStateAction.KIND, 
            false, 
            AddHierarchicalStateAction.getInputs()
        )
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
class MakeInitialStateAction implements Action {
    public static val LABEL = "Make initial state"
    public static val KIND = 'SCChart_graph_MakeInitialState'
    String kind = KIND
    
    public String id
    
    new() {}
    new(Consumer<MakeInitialStateAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        return #[];
    }
    def static StructuredEditMsg getMsg() { 
        return new StructuredEditMsg(MakeInitialStateAction.LABEL, 
            MakeInitialStateAction.KIND, 
            false, 
            MakeInitialStateAction.getInputs()
        )
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
class ToggleFinalStateAction implements Action {
    public static val LABEL = "Toggle final state"
    public static val KIND = 'SCChart_graph_MakeFinalState'
    String kind = KIND
    
    public String id
    
    new() {}
    new(Consumer<ToggleFinalStateAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        return #[];
    }
    def static StructuredEditMsg getMsg() { 
        return new StructuredEditMsg(ToggleFinalStateAction.LABEL, 
            ToggleFinalStateAction.KIND, 
            false, 
            ToggleFinalStateAction.getInputs()
        )
    }
}





