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
     * The extension id of this actions. This id is to be mentioned in instances of
     * {@link de.cau.cs.kieler.core.krendering.KAction KAction}.
     */
    public static final String ID = "de.cau.cs.kieler.klighd.actions.CollapseExpandAction";
    
    /**
     * {@inheritDoc}.<br>
     * <br>
     * This ones expands the KNode provided in 'context'.<br>
     * It is important to notice that collapsing a node means the collapsing of its child area.
     * Thus, the node itself is visible and gets layouted, while all its children and edges
     * originating from or pointing to that children are marked as non-ACTIVE, removed from the
     * diagram rendering (see GraphController#deactivateSubgraph(KNode), and ignored in the layout
     * process. This implies that the collapsed node is now treated as a non-hierarchical node whose
     * size is determined by the values in the KShapeLayout rather than by the MIN_WIDTH/MIN_HEIGHT
     * layout options.
     * 
     */
    public ActionResult execute(final ActionContext context) {

        context.getViewer().toggleExpansion(context.getNode());

        return ActionResult.createResult(true);
    }
    
}
