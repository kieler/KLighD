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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.view.syntheses

import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.klighd.ui.view.model.MessageModel
import javax.inject.Inject

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

/**
 * Diagram synthesis for a {@link MessageModel}.
 * 
 * @author als
 * @kieler.design 2014-07-30 proposed
 * @kieler.rating 2014-07-30 proposed yellow
 *
 */
class MessageModelSynthesis extends AbstractDiagramSynthesis<MessageModel> {

    @Inject
    extension KNodeExtensions

    @Inject
    extension KRenderingExtensions

    @Inject
    extension KContainerRenderingExtensions
    
    // -------------------------------------------------------------------------
    // Constants
    public static val String ID = "de.cau.cs.kieler.klighd.ui.view.syntheses.MessageModelSynthesis";

    // -------------------------------------------------------------------------
    // Synthesis
    override KNode transform(MessageModel model) {
        val rootNode = createNode();
        rootNode.children += createNode(model) => [
            it.addRectangle() => [
                it.invisible = true;
                it.setGridPlacement(1);
                //upper part is icon
                if (model.hasIcon()) {
                    it.addRectangle => [
                        it.invisible = true;
                        //set minimal size
                        it.setGridPlacementData(model.getIconSize, model.getIconSize);
                        it.addImage(model.getIconPlugin, model.getIconPath).setPointPlacementData(LEFT, 0, 0.5f, TOP, 0, 0.5f,
                            H_CENTRAL, V_CENTRAL, 0, 0, model.getIconSize, model.getIconSize).addRectangularClip;
                    ]
                }
                //lower part is message
                it.addRoundedRectangle(7, 7) => [
                    it.setGridPlacement(1);
                    it.lineWidth = 2;
                    //title
                    if (model.getTitle != null) {
                        it.addText(model.getTitle) => [
                            it.fontSize = 12;
                            it.setFontBold = true;
                            it.setGridPlacementData().from(LEFT, 8, 0, TOP, 8, 0).to(RIGHT, 8, 0, BOTTOM, 4, 0);
                            it.suppressSelectability;
                        ]
                    }
                    //message
                    if (model.getMessage != null) {
                        it.addText(model.getMessage) => [
                            it.fontSize = 12;
                            if (model.getTitle != null) {
                                it.setGridPlacementData().from(LEFT, 8, 0, TOP, 0, 0).to(RIGHT, 8, 0, BOTTOM, 4, 0);
                            } else {
                                it.setGridPlacementData().from(LEFT, 8, 0, TOP, 8, 0).to(RIGHT, 8, 0, BOTTOM, 8, 0);
                            }
                            it.suppressSelectability;
                        ]
                    }
                ]
            ]
        ]
        return rootNode;
    }
}
