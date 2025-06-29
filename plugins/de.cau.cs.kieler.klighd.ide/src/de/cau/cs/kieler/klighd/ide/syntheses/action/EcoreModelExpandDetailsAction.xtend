/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
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
package de.cau.cs.kieler.klighd.ide.syntheses.action;

import java.util.Iterator;

import de.cau.cs.kieler.klighd.IAction;
import de.cau.cs.kieler.klighd.ide.syntheses.EObjectFallbackSynthesis;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.util.ModelingUtil;

/**
 * Expands all details areas of the ecore representation of the model.
 * 
 * @author als
 */
class EcoreModelExpandDetailsAction implements IAction {
    /** The action id. */
    public static final String ID =
            "de.cau.cs.kieler.klighd.ui.view.syntheses.action.EcoreModelExpandDetailsAction";

    /**
     * {@inheritDoc}
     */
    override execute(ActionContext context) {
        if (context.getKNode() !== null) {
            val Iterator<KNode> nodeIter =
                    ModelingUtil.eAllContentsOfType(context.getKNode(), KNode);
            // Expand or collapse all nodes
            while (nodeIter.hasNext()) {
                if (context.getViewContext()
                        .getOptionValue(EObjectFallbackSynthesis.EXPAND_DETAILS) as Boolean) {
                    context.getActiveViewer().expand(nodeIter.next());
                } else {
                    context.getActiveViewer().collapse(nodeIter.next());
                }
            }
            return ActionResult.createResult(true);
        }
        return ActionResult.createResult(false);
    }
}
