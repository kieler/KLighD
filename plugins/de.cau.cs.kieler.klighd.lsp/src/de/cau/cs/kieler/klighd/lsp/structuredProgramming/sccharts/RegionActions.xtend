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
class RenameRegionAction implements Action {
    public static val LABEL = "Rename region"
    public static val KIND = 'SCChart_graph_RenameRegion'
    String kind = KIND
    
    public String id
    public String region_name
    
    new() {}
    new(Consumer<RenameRegionAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        val input1 = new InputType("region_name","String","New Name");
        return #[input1];
    }
    def static StructuredEditMsg getMsg() { 
        return new StructuredEditMsg(RenameRegionAction.LABEL, 
            RenameRegionAction.KIND, 
            false, 
            RenameRegionAction.getInputs()
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
class AddConcurrentRegionAction implements Action {
    public static val LABEL = "Add concurrent region"
    public static val KIND = 'SCChart_graph_AddConcurrentRegion'
    String kind = KIND
    
    public String id
    public String region_name
    public String state_name
    
    new() {}
    new(Consumer<AddConcurrentRegionAction> initializer) {
        initializer.accept(this)
    }
    def static InputType[] getInputs() {
        val input1 = new InputType("region_name","String","New Region Name");
        val input2 = new InputType("state_name","String","New initial state name");
        return #[input1, input2];
    }
    def static StructuredEditMsg getMsg() { 
        return new StructuredEditMsg(AddConcurrentRegionAction.LABEL, 
            AddConcurrentRegionAction.KIND, 
            false, 
            AddConcurrentRegionAction.getInputs()
        )
    }
}

