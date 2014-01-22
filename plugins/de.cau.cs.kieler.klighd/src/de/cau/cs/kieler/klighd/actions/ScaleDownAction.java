/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
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
 * @author chsch
 *
 */
public class ScaleDownAction implements IAction {

    /**
     * {@inheritDoc}
     */
    public ActionResult execute(final ActionContext context) {
        final IViewer<?> viewer = context.getActiveViewer();
        final KNode node = context.getKNode();

        final float scale = viewer.getScale(node);
        if (scale > 1) {
            viewer.scale(node, viewer.getScale(node) - 1);        
        }

        return ActionResult.createResult(true).dontAnimateLayout();
    }

}
