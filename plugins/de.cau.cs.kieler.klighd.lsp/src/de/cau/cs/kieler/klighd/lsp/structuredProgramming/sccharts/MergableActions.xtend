
package de.cau.cs.kieler.klighd.lsp.structuredProgramming.sccharts

import java.util.function.Consumer
import org.eclipse.sprotty.Action
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.EqualsHashCode
import org.eclipse.xtend.lib.annotations.ToString
/**
 * 
 * 
 * @author fjo
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
class DeleteAction implements Action {
    public static val KIND = 'SCChart_graph_Delete'
    String kind = KIND
    
    public String id
    public Boolean mergable = true
    
    new() {}
    new(Consumer<DeleteAction> initializer) {
        initializer.accept(this)
    }
}