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

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.ide.model.MessageModel
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.klighd.util.KlighdProperties

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import static extension org.eclipse.emf.ecore.util.EcoreUtil.copy

/**
 * Diagram synthesis for a {@link MessageModel}.
 * 
 * @author als
 */
class MessageModelSynthesis extends AbstractDiagramSynthesis<MessageModel> {

    @Inject
    extension KNodeExtensions

    @Inject
    extension KRenderingExtensions

    @Inject
    extension KContainerRenderingExtensions
    
    @Inject
    extension KColorExtensions
    
    // -------------------------------------------------------------------------
    // Constants
    public static val String ID = "de.cau.cs.kieler.klighd.ui.view.syntheses.MessageModelSynthesis";

    // -------------------------------------------------------------------------
    // Synthesis
    override KNode transform(MessageModel model) {
        val rootNode = createNode();
        
        // color theme
        val colorPrefereces = usedContext.getProperty(KlighdProperties.COLOR_PREFERENCES);
        val fg = if (colorPrefereces !== null) {
            colorPrefereces.foreground;
        } else {
            "#000000".color;
        }
        
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
                    it.foreground = fg.copy;
                    //title
                    if (model.getTitle !== null) {
                        it.addText(model.getTitle) => [
                            it.fontSize = 12;
                            it.setFontBold = true;
                            it.foreground = fg.copy;
                            it.setGridPlacementData().from(LEFT, 8, 0, TOP, 8, 0).to(RIGHT, 8, 0, BOTTOM, 4, 0);
                            it.selectionFontBold = false; // No selection style
                            it.suppressSelectability;
                        ]
                    }
                    //message
                    if (model.getMessage !== null) {
                        it.addText(model.getMessage) => [
                            it.fontSize = 12;
                            it.foreground = fg.copy;
                            if (model.getTitle !== null) {
                                it.setGridPlacementData().from(LEFT, 8, 0, TOP, 0, 0).to(RIGHT, 8, 0, BOTTOM, 4, 0);
                            } else {
                                it.setGridPlacementData().from(LEFT, 8, 0, TOP, 8, 0).to(RIGHT, 8, 0, BOTTOM, 8, 0);
                            }
                            it.selectionFontBold = false; // No selection style
                            it.suppressSelectability;
                        ]
                    }
                ]
            ]
        ]
        return rootNode;
    }
}
