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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.actions;

import de.cau.cs.kieler.klighd.IAction;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.kgraph.KNode;

/**
 * Scales the chosen {@link KNode} down by (roughly) one unit, i.e. 100 percent points.
 * 
 * @author chsch
 */
public class ScaleDownAction implements IAction {

    /**
     * {@inheritDoc}
     */
    public ActionResult execute(final ActionContext context) {
        final IViewer viewer = context.getActiveViewer();
        final KNode node = context.getKNode();

        if (node == context.getViewContext().getViewModel()) {
            // ... i.e. 'node' is the view model's root node
            // scaling makes no sense
            return ActionResult.createResult(false);

        } else {
            final double scale = viewer.getScale(node);

            if (scale > 1) {
                viewer.scale(node, viewer.getScale(node) - 1);
                return ActionResult.createResult(true).dontAnimateLayout();

            } else {
                return ActionResult.createResult(false).dontAnimateLayout();
            }
        }
    }
}
