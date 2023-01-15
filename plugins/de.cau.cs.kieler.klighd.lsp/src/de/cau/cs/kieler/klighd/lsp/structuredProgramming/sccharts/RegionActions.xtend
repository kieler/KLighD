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
class RenameRegionAction implements Action {
    public static val KIND = 'SCChart_graph_RenameRegion'
    String kind = KIND
    
    public String id
    public String newName
    
    new() {}
    new(Consumer<RenameRegionAction> initializer) {
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
class AddConcurrentRegionAction implements Action {
    public static val KIND = 'SCChart_graph_AddConcurrentRegion'
    String kind = KIND
    
    public String id
    public String new_name
    public String initialStateName
    
    new() {}
    new(Consumer<AddConcurrentRegionAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        val input1 = new InputType("new_name","String","New Region Name");
        val input2 = new InputType("initialStateName","String","New initial State Name");
        return #[input1, input2];
    }
}

