/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.interactive;

import org.eclipse.elk.alg.layered.options.CrossingMinimizationStrategy;
import org.eclipse.elk.alg.layered.options.CycleBreakingStrategy;
import org.eclipse.elk.alg.layered.options.LayeredOptions;
import org.eclipse.elk.alg.layered.options.LayeringStrategy;
import org.eclipse.elk.alg.rectpacking.options.RectPackingOptions;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.util.IGraphElementVisitor;
import org.eclipse.elk.graph.ElkGraphElement;
import org.eclipse.elk.graph.ElkNode;

/**
 * @author sdo
 *
 */
public class NonInteractiveLayoutConfigurator implements IGraphElementVisitor {

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit(final ElkGraphElement element) {
        // Only apply to root of the graph
        if (element.eContainer() == null && element instanceof ElkNode) {
            ElkNode root = (ElkNode) element;
            if (root.getChildren().size() == 1) {
                root = root.getChildren().get(0);
            }
            setRequiredNonInteractiveOptions(root);
        }
    }

    private static void setRequiredNonInteractiveOptions(final ElkNode root) {
        String algorithm = root.getProperty(CoreOptions.ALGORITHM);
        if ((algorithm == null || algorithm.endsWith("layered")) && root.getChildren() != null
                && !root.getChildren().isEmpty()) {
            root.setProperty(LayeredOptions.SEPARATE_CONNECTED_COMPONENTS, false);
            root.setProperty(LayeredOptions.LAYERING_STRATEGY, LayeringStrategy.NETWORK_SIMPLEX);
            root.setProperty(LayeredOptions.CYCLE_BREAKING_STRATEGY,
                    CycleBreakingStrategy.DEPTH_FIRST);
            root.setProperty(LayeredOptions.CROSSING_MINIMIZATION_STRATEGY,
                    CrossingMinimizationStrategy.LAYER_SWEEP);
        } else if (algorithm.endsWith("rectpacking") && !root.getChildren().isEmpty()) {
            root.setProperty(RectPackingOptions.INTERACTIVE, false);
        } else {
            // Add more cases for different algorithms
        }
        for (ElkNode n : root.getChildren()) {
            if (!n.getChildren().isEmpty()) {
                setRequiredNonInteractiveOptions(n);
            }
        }
        return;
    }

}
