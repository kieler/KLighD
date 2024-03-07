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
 * Sets the diagram clip to the parent {@link KNode} of the currently clipped {@link KNode} if
 * that parent exists.
 * 
 * @author chsch
 */
public class ClipParentAction implements IAction {

    /**
     * The extension id of this actions. This id is to be mentioned in instances of
     * {@link de.cau.cs.kieler.klighd.krendering.KAction KAction}.
     */
    public static final String ID = "de.cau.cs.kieler.klighd.actions.ClipParentAction";
    
    /**
     * {@inheritDoc}
     */
    public ActionResult execute(final ActionContext context) {        
        final IViewer activeViewer = context.getActiveViewer();        
        final KNode clip = activeViewer.getClip();
        
        if (clip != null && clip.getParent() != null) {
            activeViewer.clip(clip.getParent());
            
            return ActionResult.createResult(true);            
        } else {
            return ActionResult.createResult(false);
        }
    }
}
