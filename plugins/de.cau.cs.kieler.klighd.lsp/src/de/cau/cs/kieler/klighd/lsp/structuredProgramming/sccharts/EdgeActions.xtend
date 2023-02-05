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
class ChangeTargetStateAction implements Action {
    public static val LABEL = "Change target state"
    public static val KIND = 'SCChart_graph_changeTargetState'
    String kind = KIND
    
    public String id
    public String new_target
    
    new() {}
    new(Consumer<ChangeTargetStateAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        val input1 = new InputType("new_target","Select","New target state");
        return #[input1];
    }
    
    def static StructuredEditMsg getMsg() { 
        return new StructuredEditMsg(ChangeTargetStateAction.LABEL, 
            ChangeTargetStateAction.KIND, 
            false, 
            ChangeTargetStateAction.getInputs()
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
class ChangeSourceStateAction implements Action {
    public static val LABEL = "Change source state"
    public static val KIND = 'SCChart_graph_changeSourceState'
    String kind = KIND
    
    public String id
    public String new_source
    
    new() {}
    new(Consumer<ChangeSourceStateAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        val input1 = new InputType("new_source","Select","New source state");
        return #[input1];
    }
    
    def static StructuredEditMsg getMsg() { 
        return new StructuredEditMsg(ChangeSourceStateAction.LABEL, 
            ChangeSourceStateAction.KIND, 
            false, 
            ChangeSourceStateAction.getInputs()
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
class ChangeTriggerEffectAction implements Action {
    public static val LABEL = "Change trigger and effect"
    public static val KIND = 'SCChart_graph_changeTriggerAndEffect'
    String kind = KIND
    
    public String id
    public String trigger
    public String effect
    
    new() {}
    new(Consumer<ChangeTriggerEffectAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        val input1 = new InputType("trigger","String","New trigger");
        val input2 = new InputType("effect","String","New effect");
        return #[input1, input2];
    }
    
    def static StructuredEditMsg getMsg() { 
        return new StructuredEditMsg(ChangeTriggerEffectAction.LABEL, 
            ChangeTriggerEffectAction.KIND, 
            false, 
            ChangeTriggerEffectAction.getInputs()
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
class ChangeToWeakTransitionAction implements Action {
    public static val LABEL = "Change to weak transition"
    public static val KIND = 'SCChart_graph_changeToWeakTransition'
    String kind = KIND
    
    public String id
    
    new() {}
    new(Consumer<ChangeToWeakTransitionAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        return #[];
    }
    
    def static StructuredEditMsg getMsg() { 
        return new StructuredEditMsg(ChangeToWeakTransitionAction.LABEL, 
            ChangeToWeakTransitionAction.KIND, 
            false, 
            ChangeToWeakTransitionAction.getInputs()
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
class ChangeToAbortingTransitionAction implements Action {
    public static val LABEL = "Change to aborting transition"
    public static val KIND = 'SCChart_graph_changeToAbortTransition'
    String kind = KIND
    
    public String id
    
    new() {}
    new(Consumer<ChangeToAbortingTransitionAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        return #[];
    }
    
    def static StructuredEditMsg getMsg() { 
        return new StructuredEditMsg(ChangeToAbortingTransitionAction.LABEL, 
            ChangeToAbortingTransitionAction.KIND, 
            false, 
            ChangeToAbortingTransitionAction.getInputs()
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
class ChangeToTerminatingTransitionAction implements Action {    
    public static val LABEL = "Change to terminating transition"
    public static val KIND = 'SCChart_graph_changeToTerminatingTransition'
    String kind = KIND
    
    public String id
    
    new() {}
    new(Consumer<ChangeToTerminatingTransitionAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        return #[];
    }
    
    def static StructuredEditMsg getMsg() { 
        return new StructuredEditMsg(ChangeToTerminatingTransitionAction.LABEL, 
            ChangeToTerminatingTransitionAction.KIND, 
            false, 
            ChangeToTerminatingTransitionAction.getInputs()
        )
    }
}


