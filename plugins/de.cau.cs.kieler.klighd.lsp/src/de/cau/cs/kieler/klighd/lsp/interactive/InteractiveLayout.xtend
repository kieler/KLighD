/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.interactive

import com.google.inject.Inject
import com.google.inject.Singleton
import de.cau.cs.kieler.klighd.ViewContext
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.KGraphLayoutEngine
import de.cau.cs.kieler.klighd.lsp.interactive.layered.LayeredInteractiveUtil
import de.cau.cs.kieler.klighd.lsp.interactive.rectpack.RectPackInteractiveUtil
import org.eclipse.elk.alg.layered.options.CrossingMinimizationStrategy
import org.eclipse.elk.alg.layered.options.CycleBreakingStrategy
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.elk.alg.layered.options.LayeringStrategy
import org.eclipse.elk.alg.packing.rectangles.options.RectPackingOptions
import org.eclipse.elk.core.options.CoreOptions

/**
 * @author sdo, jet, cos
 * 
 */
@Singleton
class InteractiveLayout {

    @Inject
    private KGraphDiagramState diagramState

    /**
     * Calculates the layout for graphs that contain constraints.
     * 
     * @param id The identifier used in the SGraph model generation and that is used to store diagram generation
     * relevant data in the {@link KGraphDiagramState}.
     * @param layoutE The KGraphLayoutEngine
     */
    public def calcLayout(String id, KGraphLayoutEngine layoutE) {
        var ViewContext viewContext = null
        synchronized (diagramState) {
            viewContext = diagramState.getKGraphContext(id)
        }
        val root = viewContext.viewModel
        if (root.getProperty(CoreOptions.INTERACTIVE_LAYOUT)) {
            // FIXME check for interactive mode in general to interactive layout from layered alg
            root.setRequiredNonInteractiveOptions
            // Initial layout
            layoutE.onlyLayoutOnKGraph(id)

            root.setRequiredInteractiveOptions
            
            layoutE.onlyLayoutOnKGraph(id)
        }
    }
    
    /**
     * Sets the required options for the non interactive layout run.
     */
    private def void setRequiredNonInteractiveOptions(KNode root) {
        val algorithm = root.getProperty(CoreOptions.ALGORITHM)
        if (("layered".equals(algorithm) || algorithm === null) && !root.children.empty) {
            root.setProperty(LayeredOptions.SEPARATE_CONNECTED_COMPONENTS, false)
            root.setProperty(LayeredOptions.LAYERING_STRATEGY, LayeringStrategy.NETWORK_SIMPLEX)
            root.setProperty(LayeredOptions.CYCLE_BREAKING_STRATEGY, CycleBreakingStrategy.DEPTH_FIRST)
            root.setProperty(LayeredOptions.CROSSING_MINIMIZATION_STRATEGY, CrossingMinimizationStrategy.LAYER_SWEEP)
        } else if ("rectPacking".equals(algorithm) && !root.children.empty) {
            root.setProperty(RectPackingOptions.INTERACTIVE, false)
        } else {
            // Add more cases for different algorithms
        }
        for (n : root.children) {
            if (!n.children.empty) {
                setRequiredNonInteractiveOptions(n)
            }
        }
        return
    }
    
    /**
     * Sets interactive options recursiely for a root node.
     */
    public static def void setRequiredInteractiveOptions(KNode root) {
        val algorithm = root.getProperty(CoreOptions.ALGORITHM)
        if (("layered".equals(algorithm) || algorithm === null) && !root.children.empty) {
            LayeredInteractiveUtil.setCoordinatesDepthFirst(root)
        } else if ("rectPacking".equals(algorithm) && !root.children.empty) {
            RectPackInteractiveUtil.setRequiredInteractiveOptions(root)
        } else {
            // Add more cases for different algorithms
        }
    }
    

}
