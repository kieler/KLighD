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

import org.eclipse.elk.alg.rectpacking.options.RectPackingOptions;
import org.eclipse.elk.graph.ElkNode;

/**
 * Provides methods for the @code rectpacking} algorithm to set interactive or non-interactive options
 * for the {@link InteractiveLayoutConfigurator}.
 * 
 * @author sdo
 *
 */
final class RectPackingInteractiveConfigurator {
    
    private RectPackingInteractiveConfigurator() {
        
    }

     /**
     * Sets the required options for the interactive layout run with the {@code rectpacking} algorithm.
     */
    public static void setRequiredInteractiveOptions(final ElkNode root) {
        root.setProperty(RectPackingOptions.INTERACTIVE, true);
        for (ElkNode n : root.getChildren()) {
            if (!n.getChildren().isEmpty()) {
                InteractiveLayoutConfigurator.setRequiredInteractiveOptions(n);
            }
        }
        return;
    }
}
