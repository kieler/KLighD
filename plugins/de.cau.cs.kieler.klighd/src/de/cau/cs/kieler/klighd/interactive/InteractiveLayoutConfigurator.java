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

import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.util.IGraphElementVisitor;
import org.eclipse.elk.graph.ElkGraphElement;
import org.eclipse.elk.graph.ElkNode;

/**
 * Configures a graph for interactive layout.
 * 
 * @author sdo
 *
 */
public class InteractiveLayoutConfigurator implements IGraphElementVisitor {

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
            setRequiredInteractiveOptions(root);
        }
    }

    static void setRequiredInteractiveOptions(final ElkNode root) {
        String algorithm = root.getProperty(CoreOptions.ALGORITHM);
        if ((algorithm == null || algorithm.endsWith("layered")) && !root.getChildren().isEmpty()) {
            LayeredInteractiveConfigurator.setCoordinatesDepthFirst(root);
        } else if (algorithm.endsWith("rectpacking") && !root.getChildren().isEmpty()) {
            RectPackingInteractiveConfigurator.setRequiredInteractiveOptions(root);
        } else {
            // Add more cases for different algorithms
        }
    }

}
