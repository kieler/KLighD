/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.constraints

import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.kgraph.KNode
import org.eclipse.elk.alg.layered.options.LayeredOptions

/**
 * @author cos
 *
 */
class Reevaluation {
    
    def static reevaluatePositionConstraints (KGraphDiagramState diagramState, KNode target, PositionConstraint pc){
          val allNodes = target.parent.children
//          allNodes.stream
//          .filter([KNode n | n.getProperty(LayeredOptions.LAYERING_LAYER_I_D) == target.getProperty(LayeredOptions.LAYERING_LAYER_I_D)])
//          .filter([KNode n | n.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT)>=pc.position])
//          .forEach([KNode n| n.setProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT, 
//              n.getProperty(LayeredOptions.CROSSING_MINIMIZATION_POSITION_CHOICE_CONSTRAINT)+1)])
          
            
    }
    
}