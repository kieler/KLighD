/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
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
 * Resets the diagram clip the root {@link KNode} of the current view model.
 * Does nothing if the diagram root is the current clip.
 * 
 * @author chsch
 * 
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
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
