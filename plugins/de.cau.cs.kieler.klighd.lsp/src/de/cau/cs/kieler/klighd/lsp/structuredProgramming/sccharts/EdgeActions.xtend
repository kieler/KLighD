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
class RenameEdgeAction implements Action {
    public static val KIND = 'SCChart_graph_RenameEdge'
    String kind = KIND
    
    public String id
    public String new_name
    
    new() {}
    new(Consumer<RenameEdgeAction> initializer) {
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
class ChangeDestinationAction implements Action {
    public static val KIND = 'SCChart_graph_changeDestination'
    String kind = KIND
    
    public String id
    public String new_dest
    
    new() {}
    new(Consumer<ChangeDestinationAction> initializer) {
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
class ChangeSourceAction implements Action {
    public static val KIND = 'SCChart_graph_changeSource'
    String kind = KIND
    
    public String id
    public String new_Source
    
    new() {}
    new(Consumer<ChangeSourceAction> initializer) {
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
}



