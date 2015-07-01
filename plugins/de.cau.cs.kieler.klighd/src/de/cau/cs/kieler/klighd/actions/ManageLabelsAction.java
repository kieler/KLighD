/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.actions;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.labels.ILabelManager;
import de.cau.cs.kieler.kiml.labels.LabelManagementOptions;
import de.cau.cs.kieler.klighd.IAction;
import de.cau.cs.kieler.klighd.labels.AbstractKlighdLabelManager;

/**
 * An action that toggles the activity state of {@link AbstractKlighdLabelManager} instances attached
 * to a given graph.
 * 
 * @author cds
 */
public final class ManageLabelsAction implements IAction {
    
    /** This action's ID as registered with KLighD. */
    public static final String ID = "de.cau.cs.kieler.klighd.actions.ManageLabelsAction";

    /**
     * {@inheritDoc}
     */
    public ActionResult execute(final ActionContext context) {
        // Walk the graph
        boolean actuallyDidStuff = execute(context.getViewContext().getViewModel());
        return ActionResult.createResult(actuallyDidStuff);
    }
    
    /**
     * Walk the view model tree rooted at the given node and looks for compound nodes that have a
     * label size modifier attached to them. For all label size modifiers we find, toggle their active
     * state.
     * 
     * @param node root of the view model tree to walk.
     * @return {@code true} if we actually found a label size modifier that we switched.
     */
    private boolean execute(final KNode node) {
        boolean actuallyDidStuff = false;
        
        KLayoutData layoutData = node.getData(KLayoutData.class);
        ILabelManager labelManager =
                layoutData.getProperty(LabelManagementOptions.LABEL_MANAGER);
        
        // We are looking for AbstractKlighdLabelManager instances...
        if (labelManager instanceof AbstractKlighdLabelManager) {
            ((AbstractKlighdLabelManager) labelManager).toggleActive();
            actuallyDidStuff = true;
        }
        
        // Look at the children. LOOK AT THEM!
        for (final KNode child : node.getChildren()) {
            actuallyDidStuff |= execute(child);
        }
        
        return actuallyDidStuff;
    }

}
