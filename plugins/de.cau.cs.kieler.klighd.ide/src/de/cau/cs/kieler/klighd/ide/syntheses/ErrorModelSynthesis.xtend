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
package de.cau.cs.kieler.klighd.ide.syntheses

import de.cau.cs.kieler.klighd.ide.model.MessageModel
import de.cau.cs.kieler.klighd.ide.syntheses.MessageModelSynthesis
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.Colors
import de.cau.cs.kieler.klighd.krendering.KRectangle
import de.cau.cs.kieler.klighd.krendering.KRoundedRectangle
import de.cau.cs.kieler.klighd.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.klighd.ide.model.ErrorModel
import de.cau.cs.kieler.klighd.ide.syntheses.action.AbstractErrorModelShowExceptionAction
import javax.inject.Inject

/**
 * Diagram synthesis for a {@link MessageModel}.
 * 
 * @author als
 */
class ErrorModelSynthesis extends AbstractDiagramSynthesis<ErrorModel> {

    @Inject
    extension KRenderingExtensions

    @Inject
    extension KContainerRenderingExtensions

    @Inject
    MessageModelSynthesis delegate

    // -------------------------------------------------------------------------
    // Constants
    public static val String ID = "de.cau.cs.kieler.klighd.ui.view.syntheses.ErrorModelSynthesis";

    // -------------------------------------------------------------------------
    // Synthesis
    override KNode transform(ErrorModel model) {
        // create basic representation with super synthesis
        delegate.use(usedContext);
        val rootNode = delegate.transform(model as MessageModel);
        // Adjust diagram
        if (rootNode !== null && !rootNode.children.empty) {
            val messageNode = rootNode.children.head;
            val rect = messageNode.data.head as KRectangle;
            val messageRect = rect.children.filter(KRoundedRectangle).head;
            if (messageRect !== null) {
                // title text red
                messageRect.children.head.foreground = Colors.RED;
                // link to exception if available
                if (model.stackTrace !== null) {
                    // Add text below message
                    messageRect.addText("[Show Exception]") => [
                        foreground = Colors.BLUE;
                        fontSize = 9;
                        addSingleClickAction(AbstractErrorModelShowExceptionAction.ID);
                        addDoubleClickAction(AbstractErrorModelShowExceptionAction.ID);
                        setGridPlacementData().from(LEFT, 8, 0, TOP, 4, 0).to(RIGHT, 8, 0, BOTTOM, 8, 0);
                    ]
                }
            }
        }
        return rootNode;
    }
}
