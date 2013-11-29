/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.krendering.extensions

import javax.inject.Inject
import de.cau.cs.kieler.core.kgraph.KLabel
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.properties.IProperty
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement
import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KText
import de.cau.cs.kieler.kiml.options.NodeLabelPlacement
import de.cau.cs.kieler.kiml.options.PortLabelPlacement
import java.util.List

/**
 * @author chsch
 * 
 * @containsExtensions
 */
@ViewSynthesisShared
class KLabelExtensions {
    
    static KRenderingFactory renderingFactory = KRenderingFactory::eINSTANCE
    
    @Inject
    extension KNodeExtensions

    @Inject
    extension KRenderingExtensions
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////                    KLabelExtensions
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * A convenient getter preserving the element image relation by a create extension.
     */ 
    def private KLabel create node: KimlUtil::createInitializedLabel(labeledElement) getLabel(List<?> o,
            KLabeledGraphElement labeledElement) {
    }
    
    /**
     * A convenient getter preserving the element image relation by a create extension.
     */
    def KLabel getLabel(Object o, KLabeledGraphElement labeledElement) {
        return newArrayList(o).getLabel(labeledElement)
    }
    
    /**
     * Convenience creator of KLabels that are not related to semantic elements.
     * It is just syntactic sugar.  
     */
    def KLabel createLabel(KLabeledGraphElement labeledElement) {
        return KimlUtil::createInitializedLabel(labeledElement)
    }
    
    /**
     * An alias of {@link #getLabel(Object, KLabeledGraphElement)} allowing to express in business
     *  that the KLabel will be created at this place. It is just syntactic sugar.  
     */
    def KLabel createLabel(Object o, KLabeledGraphElement labeledElement) {
        return newArrayList(o).getLabel(labeledElement)
    }
    
    /**
     * A convenient getter associating the label with to elements
     *  preserving the element image relation by a create extension.
     */
    def KLabel getLabel(Object o1, Object o2, KLabeledGraphElement labeledElement) {
        return newArrayList(o1, o2).getLabel(labeledElement)
    }
    
    /**
     * An alias of {@link #getLabel(Object, Object, KLabeledGraphElement)} allowing to express in business
     *  that the KLabel will be created at this place. It is just syntactic sugar.  
     */
    def KLabel createLabel(Object o1, Object o2, KLabeledGraphElement labeledElement) {
        return newArrayList(o1, o2).getLabel(labeledElement)
    }
    
    /**
     * Configures a central (main) edge label, e.g. a state transition guard/effect label!
     * 
     * @deprecated Use {@link #configureCenteralEdgeLabel(KLabel, String, int, String)} instead!
     */
    def KLabel configureCenteralLabel(KLabel label, String labelText, int fontSize, String fontName) {
        return configureCenteralEdgeLabel(label, labelText, fontSize, fontName);
    }
    
    /**
     * Configures a central (main) edge label, e.g. a state transition guard/effect label!
     */
    def KLabel configureCenteralEdgeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        return label => [
            it.text = labelText;
            it.data += renderingFactory.createKText().setFontName(fontName).setFontSize(fontSize);
            it.addLayoutParam(LayoutOptions::EDGE_LABEL_PLACEMENT, EdgeLabelPlacement::CENTER);
            it.addLayoutParam(LayoutOptions::FONT_NAME, fontName);
            it.addLayoutParam(LayoutOptions::FONT_SIZE, fontSize-5);
        ];
    }
    
    /**
     * Configures a head edge label, e.g. the cardinality of a relation in an class diagram!
     * 
     * @deprecated Use {@link #configureHeadEdgeLabel(KLabel, String, int, String)} instead!
     */
    def KLabel configureHeadLabel(KLabel label, String labelText, int fontSize, String fontName) {
        return configureHeadEdgeLabel(label, labelText, fontSize, fontName);
    }

    /**
     * Configures a head edge label, e.g. the cardinality of a relation in an class diagram!
     */
    def KLabel configureHeadEdgeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        return label => [
            it.text = labelText;
            it.data += renderingFactory.createKText().setFontName(fontName).setFontSize(fontSize);
            it.addLayoutParam(LayoutOptions::EDGE_LABEL_PLACEMENT, EdgeLabelPlacement::HEAD);
            it.addLayoutParam(LayoutOptions::FONT_NAME, fontName);
            it.addLayoutParam(LayoutOptions::FONT_SIZE, fontSize);
        ];
    }
    
    /**
     * Configures a tail edge label, e.g. the cardinality of a relation in an class diagram!
     * 
     * @deprecated Use {@link #configureTailEdgeLabel(KLabel, String, int, String)} instead!
     */
    def KLabel configureTailLabel(KLabel label, String labelText, int fontSize, String fontName) {
        return configureTailEdgeLabel(label, labelText, fontSize, fontName);
    }

    /**
     * Configures a tail edge label, e.g. the cardinality of a relation in an class diagram!
     */
    def KLabel configureTailEdgeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        return label => [
            it.text = labelText;
            it.data += renderingFactory.createKText().setFontName(fontName).setFontSize(fontSize);
            it.addLayoutParam(LayoutOptions::EDGE_LABEL_PLACEMENT, EdgeLabelPlacement::TAIL);
            it.addLayoutParam(LayoutOptions::FONT_NAME, fontName);
            it.addLayoutParam(LayoutOptions::FONT_SIZE, fontSize);
        ];
    }
    
    /**
     * Configures a central node label!
     */
    def KLabel configureOutsideCentralBottomNodeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        return label => [
            it.text = labelText;
            it.data += renderingFactory.createKText().setFontName(fontName).setFontSize(fontSize);
            it.addLayoutParam(LayoutOptions::FONT_NAME, fontName);
            it.addLayoutParam(LayoutOptions::FONT_SIZE, fontSize);
            (it.parent as KNode).addLayoutParam(LayoutOptions::NODE_LABEL_PLACEMENT,
                        NodeLabelPlacement::outsideBottomCenter);
        ];
    }

    /**
     * Adds a central node label to KNode 'node'!
     */
    def KLabel addOutsideCentralBottomNodeLabel(KNode node, String labelText, int fontSize, String fontName) {
        return node.createLabel().configureOutsideCentralBottomNodeLabel(labelText, fontSize, fontName);
    }
    
    /**
     * Configures a central node label!
     */
    def KLabel configureOutsideBottomRightNodeLabel(KLabel label, String labelText, int fontSize, String fontName) {
        return label => [
            it.text = labelText;
            it.data += renderingFactory.createKText().setFontName(fontName).setFontSize(fontSize);
            it.addLayoutParam(LayoutOptions::FONT_NAME, fontName);
            it.addLayoutParam(LayoutOptions::FONT_SIZE, fontSize);
            (it.parent as KNode).addLayoutParam(LayoutOptions::NODE_LABEL_PLACEMENT,
                        NodeLabelPlacement::outsideBottomRight);
        ];
    }
    
     /**
     * Adds a central node label to KNode 'node'!
     */
    def KLabel addOutsideBottomRightNodeLabel(KNode node, String labelText, int fontSize, String fontName) {
        return node.createLabel().configureOutsideBottomRightNodeLabel(labelText, fontSize, fontName);
    }

    /**
     * Configures an inside port label!
     */
    def KLabel configureInsidePortLabel(KLabel label, String labelText, int fontSize, String fontName) {
        return label => [
            it.text = labelText;
            it.data += renderingFactory.createKText().setFontName(fontName).setFontSize(fontSize);
            it.addLayoutParam(LayoutOptions::PORT_LABEL_PLACEMENT, PortLabelPlacement::INSIDE);
            it.addLayoutParam(LayoutOptions::FONT_NAME, fontName);
            it.addLayoutParam(LayoutOptions::FONT_SIZE, fontSize);
        ];
    }

    /**
     * Configures a outside port label!
     */
    def KLabel configureOutsidePortLabel(KLabel label, String labelText, int fontSize, String fontName) {
        return label => [
            it.text = labelText;
            it.data += renderingFactory.createKText().setFontName(fontName).setFontSize(fontSize);
            it.addLayoutParam(LayoutOptions::PORT_LABEL_PLACEMENT, PortLabelPlacement::OUTSIDE);
            it.addLayoutParam(LayoutOptions::FONT_NAME, fontName);
            it.addLayoutParam(LayoutOptions::FONT_SIZE, fontSize);
        ];
    }

    def KLabel setLabelSize(KLabel label, float width, float height) {
        return label => [
            getData(typeof(KShapeLayout)).setSize(width, height)
        ];
    }
    
    def KLabel setLabelPos(KLabel label, float x, float y) {
        return label => [
            getData(typeof(KShapeLayout)).setPos(x, y)
        ];
    }
    
    def <T> KLabel addLayoutParam(KLabel node, IProperty<? super T> property, T value) {
        return node => [
            it.getData(typeof(KShapeLayout)).setProperty(property, value)
        ];
    }
    
    /**
     * Reveals the first KText element of a label KRendering, which is assumed to be the label text configuration.
     * This is useful for additionally linking it with the business element represented by the label.
     * 
     * Note: KLabelNodes of the Piccolo binding are configured to ignore the KText element while selecting them.
     * Thus, only the KLabel needs to be linked to the source element. 
     */
    def KText getFirstText(KLabel label) {
        return label?.getData(typeof(KText))?:(label?.getData(typeof(KRendering))?.eAllContents?.filter(typeof(KText))?.head);
    }

    
}