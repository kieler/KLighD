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
class RenameRegionAction implements Action {
    public static val KIND = 'SCChart_graph_RenameRegion'
    String kind = KIND
    
    public String id
    public String new_name
    
    new() {}
    new(Consumer<RenameRegionAction> initializer) {
        initializer.accept(this)
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
}

