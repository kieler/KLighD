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
 * Action received from the client if the window should switch to the code base.
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls=true)
class EditSemanticDeclarationAction implements Action {
    public static val LABEL = "Edit sematic declarations"
    public static val KIND = 'SCChart_EditSemanticDeclarations'
    String kind = KIND
    public String id

    new() {
    }

    new(Consumer<EditSemanticDeclarationAction> initializer) {
        initializer.accept(this)
    }

    /* Returns the array of inputs requested from the user to perform the action. */
    def static InputType[] getInputs() {
        return #[];
    }

    /* Used in the synthesis to append the supported actions to the root node for the use on the client. */
    def static StructuredEditMsg getMsg() {
        return new StructuredEditMsg(
            EditSemanticDeclarationAction.LABEL,
            EditSemanticDeclarationAction.KIND,
            false,
            EditSemanticDeclarationAction.getInputs()
        )
    }
}

/**
 * Action received from the client if a state should be renamed.
 * The given information is the state id and the new name.
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls=true)
class RenameStateAction implements Action {
    public static val LABEL = "Rename state"
    public static val KIND = 'SCChart_graph_RenameState'
    String kind = KIND

    public String id
    public String state_name

    new() {
    }

    new(Consumer<RenameStateAction> initializer) {
        initializer.accept(this)
    }

    /* Returns the array of inputs requested from the user to perform the action. */
    def static InputType[] getInputs() {
        val input1 = new InputType("state_name", "String", "New Name");
        return #[input1];
    }

    /* Used in the synthesis to append the supported actions to the root node for the use on the client. */
    def static StructuredEditMsg getMsg() {
        return new StructuredEditMsg(
            RenameStateAction.LABEL,
            RenameStateAction.KIND,
            false,
            RenameStateAction.getInputs()
        )
    }
}

/**
 * Action received from client if a new transition should be added.
 * Given from client are the id of the source node the new destination
 * as well as trigger and effect as strings.
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls=true)
class AddTransitionAction implements Action {
    public static val LABEL = "Add new transition"
    public static val KIND = 'SCChart_graph_AddTransition'
    String kind = KIND

    public String id
    public String destination
    public String trigger
    public String effect

    new() {
    }

    new(Consumer<AddTransitionAction> initializer) {
        initializer.accept(this)
    }

    /* Returns the array of inputs requested from the user to perform the action. */
    def static InputType[] getInputs() {
        val input1 = new InputType("destination", "SelectTarget", "Destination");
        val input2 = new InputType("trigger", "String", "Trigger");
        val input3 = new InputType("effect", "String", "Effect")
        return #[input1, input2, input3];
    }

    /* Used in the synthesis to append the supported actions to the root node for the use on the client. */
    def static StructuredEditMsg getMsg() {
        return new StructuredEditMsg(
            AddTransitionAction.LABEL,
            AddTransitionAction.KIND,
            false,
            AddTransitionAction.getInputs()
        )
    }
}

/**
 * Action received from client for adding a new successor state.
 * the given information is the predecessor node the new nodes name as well as
 * trigger and effect
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls=true)
class AddSuccessorStateAction implements Action {
    public static val LABEL = "Add successor state"
    public static val KIND = 'SCChart_graph_AddSuccessorState'
    String kind = KIND

    public String id
    public String state_name
    public String trigger
    public String effect

    new() {
    }

    new(Consumer<AddSuccessorStateAction> initializer) {
        initializer.accept(this)
    }

    /* Returns the array of inputs requested from the user to perform the action. */
    def static InputType[] getInputs() {
        val input1 = new InputType("state_name", "String", "Name of state");
        val input2 = new InputType("trigger", "String", "Trigger");
        val input3 = new InputType("effect", "String", "Effect")
        return #[input1, input2, input3];
    }

    /* Used in the synthesis to append the supported actions to the root node for the use on the client. */
    def static StructuredEditMsg getMsg() {
        return new StructuredEditMsg(
            AddSuccessorStateAction.LABEL,
            AddSuccessorStateAction.KIND,
            false,
            AddSuccessorStateAction.getInputs()
        )
    }
}

/**
 * Action received from the client to add a hirachical behavior to a state.
 * Given information is the states id the new regions name and the new states name.
 * 
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls=true)
class AddHierarchicalStateAction implements Action {
    public static val LABEL = "Add region"
    public static val KIND = 'SCChart_graph_AddHierarchicalState'
    String kind = KIND

    public String id
    public String state_name
    public String region_name

    new() {
    }

    new(Consumer<AddHierarchicalStateAction> initializer) {
        initializer.accept(this)
    }

    /* Returns the array of inputs requested from the user to perform the action. */
    def static InputType[] getInputs() {
        val input1 = new InputType("state_name", "String", "State Name");
        val input2 = new InputType("region_name", "String", "Region Name");
        return #[input1, input2];
    }

    /* Used in the synthesis to append the supported actions to the root node for the use on the client. */
    def static StructuredEditMsg getMsg() {
        return new StructuredEditMsg(
            AddHierarchicalStateAction.LABEL,
            AddHierarchicalStateAction.KIND,
            false,
            AddHierarchicalStateAction.getInputs()
        )
    }
}

/**
 * Action received from the client if a state should be made initial.
 * Given information is the state id of the state that should be initial.
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls=true)
class MakeInitialStateAction implements Action {
    public static val LABEL = "Make initial state"
    public static val KIND = 'SCChart_graph_MakeInitialState'
    String kind = KIND

    public String id

    new() {
    }

    new(Consumer<MakeInitialStateAction> initializer) {
        initializer.accept(this)
    }

    /* Returns the array of inputs requested from the user to perform the action. */
    def static InputType[] getInputs() {
        return #[];
    }

    /* Used in the synthesis to append the supported actions to the root node for the use on the client. */
    def static StructuredEditMsg getMsg() {
        return new StructuredEditMsg(
            MakeInitialStateAction.LABEL,
            MakeInitialStateAction.KIND,
            false,
            MakeInitialStateAction.getInputs()
        )
    }
}

/**
 * Action received to toggle a state to a final state or back.
 * Given information is the state id which should be toggled.
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls=true)
class ToggleFinalStateAction implements Action {
    public static val LABEL = "Toggle final state"
    public static val KIND = 'SCChart_graph_MakeFinalState'
    String kind = KIND

    public String id

    new() {
    }

    new(Consumer<ToggleFinalStateAction> initializer) {
        initializer.accept(this)
    }

    /* Returns the array of inputs requested from the user to perform the action. */
    def static InputType[] getInputs() {
        return #[];
    }

    /* Used in the synthesis to append the supported actions to the root node for the use on the client. */
    def static StructuredEditMsg getMsg() {
        return new StructuredEditMsg(
            ToggleFinalStateAction.LABEL,
            ToggleFinalStateAction.KIND,
            false,
            ToggleFinalStateAction.getInputs()
        )
    }
}
