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

import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.krendering.Colors
import de.cau.cs.kieler.klighd.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.klighd.ui.view.syntheses.action.EcoreModelExpandDetailsAction
import de.cau.cs.kieler.klighd.util.KlighdProperties
import java.util.List
import javax.inject.Inject
import org.eclipse.elk.alg.layered.properties.FixedAlignment
import org.eclipse.elk.alg.layered.properties.LayeredOptions
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.util.Pair
import org.eclipse.elk.graph.KNode
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.util.formallang.FollowerFunctionImpl.Direction

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

/**
 * Diagram synthesis of a {@link EcoreModelWrapper}.
 * 
 * @author als
 * @kieler.design 2014-07-30 proposed
 * @kieler.rating 2014-07-30 proposed yellow
 * 
 */
class EcoreModelSynthesis extends AbstractDiagramSynthesis<EObject> {

    @Inject
    extension KNodeExtensions

    @Inject
    extension KEdgeExtensions

    @Inject
    extension KRenderingExtensions

    @Inject
    extension KContainerRenderingExtensions

    @Inject
    extension KPolylineExtensions

    @Inject
    extension KColorExtensions

    // -------------------------------------------------------------------------
    // Constants
    public static val String ID = "de.cau.cs.kieler.klighd.ui.view.syntheses.EcoreModelSynthesis";

    // Options
    public static val SynthesisOption EXPAND_DETAILS = SynthesisOption::createCheckOption("Expand all Details",
        false).setUpdateAction(EcoreModelExpandDetailsAction.ID);

    // Sidebar
    override getDisplayedSynthesisOptions() {
        return newLinkedList(EXPAND_DETAILS);
    }

    override getDisplayedLayoutOptions() {
        return newLinkedList(
            new Pair<IProperty<?>, List<?>>(CoreOptions::DIRECTION, Direction::values.drop(1).sortBy[it.name]),
            new Pair<IProperty<?>, List<?>>(CoreOptions::SPACING_NODE, newArrayList(0, 150))
        );
    }

    // -------------------------------------------------------------------------
    // Synthesis
    override KNode transform(EObject model) {
        val rootNode = createNode();
        
        rootNode.addLayoutParam(CoreOptions::ALGORITHM, "de.cau.cs.kieler.klay.layered");
        rootNode.setLayoutOption(LayeredOptions::FIXED_ALIGNMENT, FixedAlignment.BALANCED);
        
        // transform root object
        rootNode.children += model.translateEObject
        // transform and connect all succeeding objects
        rootNode.children += model.eAllContents.map [
            val child = it.translateEObject;
            val container = it.eContainer;
            if (container != null) {
                createEdge => [
                    it.source = container.translateEObject;
                    it.target = child;
                    it.addPolyline.addHeadArrowDecorator;
                ]
            }
            return child;
        ].toIterable;

        return rootNode;
    }

    /**
     * Translate a single EObject with super type and attributes into a node
     */
    private def create node : object.createNode translateEObject(EObject object) {
        node.associateWith(object);
        
        node.setLayoutOption(KlighdProperties::EXPAND, EXPAND_DETAILS.booleanValue);

        val eclass = object.eClass;
        val hasAttributes = !eclass.EAllAttributes.empty
        val hasSuperTypes = !eclass.EAllSuperTypes.empty
        // Expanded Rectangle
        node.createFigure() => [
            it.setProperty(KlighdProperties::EXPANDED_RENDERING, true);
            it.setGridPlacement(1);

            // add name of object as text
            it.addText(object.eClass.name).associateWith(object) => [
                it.fontSize = 11;
                it.setFontBold(true);
                it.setGridPlacementData().from(LEFT, 9, 0, TOP, 8f, 0).to(RIGHT, 8, 0, BOTTOM, 4, 0);
                it.suppressSelectability;
            ];

            // collapse button
            it.addText("[Hide]") => [
                it.foreground = "blue".color
                it.fontSize = 9
                it.addSingleClickAction(KlighdConstants::ACTION_COLLAPSE_EXPAND);
                it.addDoubleClickAction(KlighdConstants::ACTION_COLLAPSE_EXPAND);
                it.setGridPlacementData().from(LEFT, 8, 0, TOP, 0, 0).to(RIGHT, 8, 0, BOTTOM, 0, 0);
            ];

            if (hasAttributes || hasSuperTypes) {
                // Add Details
                it.addRectangle => [
                    it.setGridPlacementData.from(LEFT, 8, 0, TOP, 0, 0).to(RIGHT, 8, 0, BOTTOM, 8, 0);
                    it.setBackground = "white".color;
                    it.foreground = "gray".color
                    it.lineWidth = 1;
                    it.setGridPlacement(1);

                    // Content
                    val container = it;
                    // add all super types as string representation
                    if (hasSuperTypes) {
                        it.addText("SuperTypes") => [
                            it.foreground = "gray".color;
                            it.fontSize = 8;
                            it.setGridPlacementData().from(LEFT, 5, 0, TOP, 2, 0).to(RIGHT, 5, 0, BOTTOM, 2, 0);
                            it.suppressSelectability;
                        ];
                        eclass.EAllSuperTypes.filterNull.forEach [
                            // add a text with name and value of the attribute
                            container.addText(it.name) => [
                                it.fontSize = 9;
                                it.setGridPlacementData().from(LEFT, 5, 0, TOP, 2, 0).to(RIGHT, 5, 0, BOTTOM, 2, 0);
                                it.suppressSelectability;
                            ];
                        ]
                    }
                    // add all attributes as string representation
                    if (hasAttributes) {
                        if (hasSuperTypes) {
                            it.addHorizontalSeperatorLine(1, 1).foreground = "gray".color;
                        }
                        it.addText("Attributes") => [
                            it.foreground = "gray".color;
                            it.fontSize = 8;
                            it.setGridPlacementData().from(LEFT, 5, 0, TOP, 2, 0).to(RIGHT, 5, 0, BOTTOM, 2, 0);
                            it.suppressSelectability;
                        ];
                        eclass.EAllAttributes.filterNull.forEach [
                            // add a text with name and value of the attribute
                            container.addText(it.name + ": " + String::valueOf(object.eGet(it))) => [
                                it.fontSize = 9;
                                it.setGridPlacementData().from(LEFT, 5, 0, TOP, 2, 0).to(RIGHT, 5, 0, BOTTOM, 2, 0);
                                it.suppressSelectability;
                            ];
                        ]
                    }
                ];
            }
        ];

        // Collapse Rectangle
        node.createFigure() =>
            [
                it.setProperty(KlighdProperties::COLLAPSED_RENDERING, true);
                it.setGridPlacement(1);
                // add name of object as text
                it.addText(object.eClass.name).associateWith(object) =>
                    [
                        it.fontSize = 11;
                        it.setFontBold(true);
                        it.setGridPlacementData().from(LEFT, 8, 0, TOP, 8, 0).to(RIGHT, 8, 0, BOTTOM,
                            if(hasAttributes || hasSuperTypes) 4 else 8, 0);
                        it.suppressSelectability;
                    ];
                if (hasAttributes || hasSuperTypes) {
                    // Add details button
                    it.addText("[Details]") => [
                        it.foreground = "blue".color
                        it.fontSize = 9
                        it.addSingleClickAction(KlighdConstants::ACTION_COLLAPSE_EXPAND);
                        it.addDoubleClickAction(KlighdConstants::ACTION_COLLAPSE_EXPAND);
                        it.setGridPlacementData().from(LEFT, 8, 0, TOP, 0, 0).to(RIGHT, 8, 0, BOTTOM, 8, 0);
                    ];
                }
            ];
    }

    /**
     * Creates the visual representation of a node
     */
    private def createFigure(KNode node) {
        val figure = node.addRoundedRectangle(8, 8, 1);
        figure.lineWidth = 1;
        figure.foreground = Colors.GRAY;
        figure.background = Colors.GRAY_95;

        // minimal node size is necessary if no text will be added
        node.setMinimalNodeSize(2 * figure.cornerWidth, 2 * figure.cornerHeight);
        
        return figure;
    }
}
