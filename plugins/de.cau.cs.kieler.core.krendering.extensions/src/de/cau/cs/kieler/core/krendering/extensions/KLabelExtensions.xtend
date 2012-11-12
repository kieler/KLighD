package de.cau.cs.kieler.core.krendering.extensions

import javax.inject.Inject

import de.cau.cs.kieler.core.kgraph.KLabel
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.properties.IProperty
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement
import de.cau.cs.kieler.kiml.util.KimlUtil

class KLabelExtensions {
    
    static KRenderingFactory renderingFactory = KRenderingFactory::eINSTANCE
    
    @Inject
    extension KRenderingExtensions
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////                    KLabelExtensions
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * A convenient getter preserving the element image relation by a create extension.
     */ 
    def KLabel create node: KimlUtil::createInitializedLabel(labeledElement) getLabel(Object o,
            KLabeledGraphElement labeledElement) {
    }
    
    /**
     * An alias of {@link #getLabel} allowing to express in business that the KLabel will
     * be created at this place. It is just syntactic sugar.  
     */
    def KLabel createLabel(Object o, KLabeledGraphElement labeledElement) {
        return o.getLabel(labeledElement)
    }
    
    def KLabel configureCenteralLabel(KLabel label, String labelText, int fontSize, String fontName) {
        return label => [
            it.text = labelText;
            it.data += renderingFactory.createKText().setFontName(fontName).setFontSize(fontSize);
            it.addLayoutParam(LayoutOptions::EDGE_LABEL_PLACEMENT, EdgeLabelPlacement::CENTER);
            it.addLayoutParam(LayoutOptions::FONT_NAME, fontName);
            it.addLayoutParam(LayoutOptions::FONT_SIZE, fontSize+2);
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
    
    def KLabel addLayoutParam(KLabel node, IProperty<?> property, Object value) {
        return node => [
            it.getData(typeof(KShapeLayout)).setProperty(property, value)
        ];
    }
    
    
}