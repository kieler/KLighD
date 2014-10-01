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
 * Sets the diagram clip to the parent {@link KNode} of the currently clipped {@link KNode} if
 * that parent exists.
 * 
 * @author chsch
 * 
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class ClipParentAction implements IAction {

    /**
     * The extension id of this actions. This id is to be mentioned in instances of
     * {@link de.cau.cs.kieler.core.krendering.KAction KAction}.
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
