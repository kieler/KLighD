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

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.IAction;
import de.cau.cs.kieler.klighd.IViewer;

/**
 * Resets the diagram clip the root {@link KNode} of the current view model.
 * Does nothing if the diagram root is the current clip.
 * 
 * @author chsch
 */
public class ClipResetAction implements IAction {

    /**
     * {@inheritDoc}
     */
    public ActionResult execute(final ActionContext context) {        
        final IViewer activeViewer = context.getActiveViewer();
        final KNode diagramRoot = context.getViewContext().getViewModel();
        
        if (activeViewer.getClip() != diagramRoot) {
            activeViewer.clip(null);

            return ActionResult.createResult(true);
        } else {
            return ActionResult.createResult(false);
        }
    }
}
