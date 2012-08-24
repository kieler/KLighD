package de.cau.cs.kieler.core.krendering.extensions

import de.cau.cs.kieler.core.kgraph.KLabel
import de.cau.cs.kieler.kiml.util.KimlUtil
import org.eclipse.emf.ecore.EObject
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement
import de.cau.cs.kieler.core.properties.IProperty
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout

class KLabelExtensions {
    
//    static KRenderingFactory renderingFactory = KRenderingFactory::eINSTANCE
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////                    KLabelExtensions
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * A convenient getter preserving the element image relation by a create extension.
     */ 
    def KLabel create node: KimlUtil::createInitializedLabel(labeledElement) getLabel(EObject o,
            KLabeledGraphElement labeledElement) {
    }
    
    /**
     * An alias of {@link #getLabel} allowing to express in business that the KLabel will
     * be created at this place. It is just syntactic sugar.  
     */
    def KLabel createLabel(EObject o, KLabeledGraphElement labeledElement) {
        return o.getLabel(labeledElement)
    }
    
    def KLabel addLayoutParam(KLabel node, IProperty<?> property, Object value) {
        return node => [
            it.getData(typeof(KShapeLayout)).setProperty(property, value)
        ];
    }
    
    
}