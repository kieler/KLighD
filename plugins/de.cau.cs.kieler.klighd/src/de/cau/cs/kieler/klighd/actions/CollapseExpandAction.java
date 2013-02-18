/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.actions;

import de.cau.cs.kieler.klighd.IAction;

/**
 * This is first draft of an {@link IAction} realizing the collapsing and expanding of
 * {@link de.cau.cs.kieler.core.kgraph.KNode KNodes}.
 * 
 * @author chsch
 */
public class CollapseExpandAction implements IAction {
    
    /**
     * {@inheritDoc}.<br>
     * <br>
     * This ones expands the KNode provided in 'context'.
     */
    public boolean execute(final ActionContext context) {
        context.getViewer().toggleExpansion(context.getNode());
        return true;
    }
    
}
