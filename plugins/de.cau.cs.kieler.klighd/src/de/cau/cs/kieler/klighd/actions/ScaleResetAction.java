/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.actions;

import de.cau.cs.kieler.klighd.IAction;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.kgraph.KNode;

/**
 * Sets the chosen {@link KNode}'s scale to one unit, i.e. 100 percent.
 * 
 * @author chsch
 * 
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class ScaleResetAction implements IAction {

    /**
     * {@inheritDoc}
     */
    public ActionResult execute(final ActionContext context) {
        final IViewer viewer = context.getActiveViewer();
        final KNode node = context.getKNode();

        viewer.scale(node, 1);        

        return ActionResult.createResult(true).dontAnimateLayout();
    }
}
