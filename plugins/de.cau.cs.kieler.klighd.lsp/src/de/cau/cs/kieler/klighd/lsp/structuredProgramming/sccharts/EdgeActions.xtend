package de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts

import java.util.function.Consumer
import org.eclipse.sprotty.Action
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.EqualsHashCode
import org.eclipse.xtend.lib.annotations.ToString
import de.cau.cs.kieler.klighd.structuredEditMsg.InputType
import de.cau.cs.kieler.klighd.structuredEditMsg.StructuredEditMsg

/**
 * Action received from client when a change of target is requested.
 * The transmitted information is the new target state id and the id of the edge.
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls=true)
class ChangeTargetStateAction implements Action {
    public static val LABEL = "Change target state"
    public static val KIND = 'SCChart_graph_changeTargetState'
    String kind = KIND

    public String id
    public String new_target

    new() {
    }

    new(Consumer<ChangeTargetStateAction> initializer) {
        initializer.accept(this)
    }

    /* Returns the array of inputs requested from the user to perform the action. */
    def static InputType[] getInputs() {
        val input1 = new InputType("new_target", "SelectTarget", "New target state");
        return #[input1];
    }

    /* Used in the synthesis to append the supported actions to the root node for the use on the client. */
    def static StructuredEditMsg getMsg() {
        return new StructuredEditMsg(
            ChangeTargetStateAction.LABEL,
            ChangeTargetStateAction.KIND,
            false,
            ChangeTargetStateAction.getInputs()
        )
    }
}

/**
 * Action received from client when a change of source is requested.
 * The transmitted information is the new source state id and the id of the edge.
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls=true)
class ChangeSourceStateAction implements Action {
    public static val LABEL = "Change source state"
    public static val KIND = 'SCChart_graph_changeSourceState'
    String kind = KIND

    public String id
    public String new_source

    new() {
    }

    new(Consumer<ChangeSourceStateAction> initializer) {
        initializer.accept(this)
    }

    /* Returns the array of inputs requested from the user to perform the action. */
    def static InputType[] getInputs() {
        val input1 = new InputType("new_source", "SelectSource", "New source state");
        return #[input1];
    }

    /* Used in the synthesis to append the supported actions to the root node for the use on the client. */
    def static StructuredEditMsg getMsg() {
        return new StructuredEditMsg(
            ChangeSourceStateAction.LABEL,
            ChangeSourceStateAction.KIND,
            false,
            ChangeSourceStateAction.getInputs()
        )
    }
}

/**
 * Action received from client if a change of trigger or effect is requested.
 * The given information is the id of the edge and the new trigger and effects as string representations.
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls=true)
class ChangeTriggerEffectAction implements Action {
    public static val LABEL = "Change trigger and effect"
    public static val KIND = 'SCChart_graph_changeTriggerAndEffect'
    String kind = KIND

    public String id
    public String trigger
    public String effect

    new() {
    }

    new(Consumer<ChangeTriggerEffectAction> initializer) {
        initializer.accept(this)
    }

    /* Returns the array of inputs requested from the user to perform the action. */
    def static InputType[] getInputs() {
        val input1 = new InputType("trigger", "String", "New trigger");
        val input2 = new InputType("effect", "String", "New effect");
        return #[input1, input2];
    }

    /* Used in the synthesis to append the supported actions to the root node for the use on the client. */
    def static StructuredEditMsg getMsg() {
        return new StructuredEditMsg(
            ChangeTriggerEffectAction.LABEL,
            ChangeTriggerEffectAction.KIND,
            false,
            ChangeTriggerEffectAction.getInputs()
        )
    }
}

/**
 * Action received from the client if a edge priority should change.
 * The given information is the new priority and the edge id.
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls=true)
class ChangePriorityAction implements Action {
    public static val LABEL = "Change priority"
    public static val KIND = 'SCChart_graph_changePriorityOfEdge'
    String kind = KIND

    public String id
    public String priority

    new() {
    }

    new(Consumer<ChangePriorityAction> initializer) {
        initializer.accept(this)
    }

    /* Returns the array of inputs requested from the user to perform the action. */
    def static InputType[] getInputs() {
        val input1 = new InputType("priority", "String", "Change Priority");
        return #[input1];
    }

    /* Used in the synthesis to append the supported actions to the root node for the use on the client. */
    def static StructuredEditMsg getMsg() {
        return new StructuredEditMsg(
            ChangePriorityAction.LABEL,
            ChangePriorityAction.KIND,
            false,
            ChangePriorityAction.getInputs()
        )
    }
}

/**
 * Action received from the client if a transition should be weak
 * The given information is the id of the edge
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls=true)
class ChangeToWeakTransitionAction implements Action {
    public static val LABEL = "Change to weak transition"
    public static val KIND = 'SCChart_graph_changeToWeakTransition'
    String kind = KIND

    public String id

    new() {
    }

    new(Consumer<ChangeToWeakTransitionAction> initializer) {
        initializer.accept(this)
    }

    /* Returns the array of inputs requested from the user to perform the action. */
    def static InputType[] getInputs() {
        return #[];
    }

    /* Used in the synthesis to append the supported actions to the root node for the use on the client. */
    def static StructuredEditMsg getMsg() {
        return new StructuredEditMsg(
            ChangeToWeakTransitionAction.LABEL,
            ChangeToWeakTransitionAction.KIND,
            false,
            ChangeToWeakTransitionAction.getInputs()
        )
    }
}

/**
 * Action received from the client if a transition should be aborting
 * The given information is the id of the edge
 * 
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls=true)
class ChangeToAbortingTransitionAction implements Action {
    public static val LABEL = "Change to aborting transition"
    public static val KIND = 'SCChart_graph_changeToAbortTransition'
    String kind = KIND

    public String id

    new() {
    }

    new(Consumer<ChangeToAbortingTransitionAction> initializer) {
        initializer.accept(this)
    }

    /* Returns the array of inputs requested from the user to perform the action. */
    def static InputType[] getInputs() {
        return #[];
    }

    /* Used in the synthesis to append the supported actions to the root node for the use on the client. */
    def static StructuredEditMsg getMsg() {
        return new StructuredEditMsg(
            ChangeToAbortingTransitionAction.LABEL,
            ChangeToAbortingTransitionAction.KIND,
            false,
            ChangeToAbortingTransitionAction.getInputs()
        )
    }
}

/**
 * Action received from the client if a transition should be terminating
 * The given information is the id of the edge
 * 
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls=true)
class ChangeToTerminatingTransitionAction implements Action {
    public static val LABEL = "Change to terminating transition"
    public static val KIND = 'SCChart_graph_changeToTerminatingTransition'
    String kind = KIND

    public String id

    new() {
    }

    new(Consumer<ChangeToTerminatingTransitionAction> initializer) {
        initializer.accept(this)
    }

    /* Returns the array of inputs requested from the user to perform the action. */
    def static InputType[] getInputs() {
        return #[];
    }

    /* Used in the synthesis to append the supported actions to the root node for the use on the client. */
    def static StructuredEditMsg getMsg() {
        return new StructuredEditMsg(
            ChangeToTerminatingTransitionAction.LABEL,
            ChangeToTerminatingTransitionAction.KIND,
            false,
            ChangeToTerminatingTransitionAction.getInputs()
        )
    }
}
