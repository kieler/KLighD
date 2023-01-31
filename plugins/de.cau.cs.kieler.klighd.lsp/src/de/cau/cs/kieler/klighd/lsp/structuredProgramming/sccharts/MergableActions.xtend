
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
class DeleteAction implements Action {
    public static val LABEL = "Delete"
    public static val KIND = 'SCChart_graph_Delete'
    String kind = KIND
    
    public String id
    public Boolean mergable = true
    
    new() {}
    new(Consumer<DeleteAction> initializer) {
        initializer.accept(this)
    }
    
    def static InputType[] getInputs() {
        return #[];
    }
    def static StructuredEditMsg getMsg() { 
        return new StructuredEditMsg(DeleteAction.LABEL, 
            DeleteAction.KIND, 
            true, 
            DeleteAction.getInputs()
        )
    }
}