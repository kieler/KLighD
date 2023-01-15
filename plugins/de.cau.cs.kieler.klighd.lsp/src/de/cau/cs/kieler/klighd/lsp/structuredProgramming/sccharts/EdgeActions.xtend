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
class ChangeDestinationAction implements Action {
    public static val KIND = 'SCChart_graph_changeDestination'
    String kind = KIND
    
    public String id
    public String new_dest
    
    new() {}
    new(Consumer<ChangeDestinationAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        val input1 = new InputType("new_dest","String","New Destination");
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
class ChangeSourceAction implements Action {
    public static val KIND = 'SCChart_graph_changeSource'
    String kind = KIND
    
    public String id
    public String new_Source
    
    new() {}
    new(Consumer<ChangeSourceAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        val input1 = new InputType("new_Source","String","New Source");
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
class ChangeIOAction implements Action {
    public static val KIND = 'SCChart_graph_changeIO'
    String kind = KIND
    
    public String id
    public String inputs
    public String outputs
    
    new() {}
    new(Consumer<ChangeIOAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        val input1 = new InputType("inputs","String","New Inputs");
        val input2 = new InputType("outputs","String","New Outputs");
        return #[input1, input2];
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
class ChangeToWeakEdgeAction implements Action {
    public static val KIND = 'SCChart_graph_changeToWeakEdge'
    String kind = KIND
    
    public String id
    
    new() {}
    new(Consumer<ChangeToWeakEdgeAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        return #[];
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
class ChangeToAbortingEdgeAction implements Action {
    public static val KIND = 'SCChart_graph_changeToAbortEdge'
    String kind = KIND
    
    public String id
    
    new() {}
    new(Consumer<ChangeToAbortingEdgeAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        return #[];
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
class ChangeToTerminationgEdgeAction implements Action {
    public static val KIND = 'SCChart_graph_changeToTerminatingEdge'
    String kind = KIND
    
    public String id
    
    new() {}
    new(Consumer<ChangeToTerminationgEdgeAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        return #[];
    }
}


