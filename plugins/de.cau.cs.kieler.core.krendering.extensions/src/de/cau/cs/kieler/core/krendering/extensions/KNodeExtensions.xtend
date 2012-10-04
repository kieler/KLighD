package de.cau.cs.kieler.core.krendering.extensions

import de.cau.cs.kieler.core.annotations.Annotatable
import de.cau.cs.kieler.core.annotations.AnnotationsPackage
import de.cau.cs.kieler.core.annotations.BooleanAnnotation
import de.cau.cs.kieler.core.annotations.FloatAnnotation
import de.cau.cs.kieler.core.annotations.IntAnnotation
import de.cau.cs.kieler.core.annotations.StringAnnotation
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.properties.IProperty
import de.cau.cs.kieler.core.util.Pair

import de.cau.cs.kieler.kiml.LayoutDataService
import de.cau.cs.kieler.kiml.LayoutOptionData
import de.cau.cs.kieler.kiml.LayoutOptionData$Type
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.kiml.util.KimlUtil
import java.util.ArrayList

class KNodeExtensions {
	
    private static val AnnotationsPackage annotationsPackage = AnnotationsPackage::eINSTANCE;

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////					KNodeExtensions
	////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * A convenient getter preserving the element image relation by a create extension.
     */ 
    def private KNode create node: KimlUtil::createInitializedNode internalCreateNode(ArrayList<Object> oc) {
    }

    /**
     * A convenient getter preserving the element image relation by a create extension.
     */ 
    def KNode getNode(Object o) {
        newArrayList(o).internalCreateNode
    }
    
    /**
     * A convenient getter preserving the element image relation by a create extension.
     */ 
    def KNode getNode(Object o1, Object o2) {
        newArrayList(o1, o2).internalCreateNode
    }
    
    /**
     * A convenience method to create a KNode without relating it to a business object. 
     */
    def KNode createNode() {
        return KimlUtil::createInitializedNode();
    }
    
    /**
     * An alias of {@link #getNode} allowing to express in business that the KNode will
     * be created at this place. It is just syntactic sugar.  
     */
    def KNode createNode(Object o) {
        return o.node
    }
    
    /**
     * An alias of {@link #getNode} allowing to express in business that the KNode will
     * be created at this place. It is just syntactic sugar.  
     */
    def KNode createNode(Object o1, Object o2) {
        return o1.getNode(o2)
    }
    
    def Pair<Float, Float> getNodeSize(KNode node) {
        return new Pair<Float, Float> => [
            val layout = node.getData(typeof(KShapeLayout))
            it.first = layout.height
            it.second = layout.height
        ];
    }

    def float getHeight(KNode node) {
        node.getData(typeof(KShapeLayout)).height;
    }
    
    /**
     * Is used in KPortExtensions
     */    
    def float getWidth(KNode node) {
        node.getData(typeof(KShapeLayout)).width;
    }
    
    def KNode setNodeSize(KNode node, float with, float height) {
        return node => [
            getData(typeof(KShapeLayout)).setSize(with, height)
        ];
    }
    
	def KNode addLayoutParam(KNode node, IProperty<?> property, Object value) {
	    return node => [
	        it.getData(typeof(KShapeLayout)).setProperty(property, value)
	    ];
	}
	
	
    /**
     * Helper transferring Annotations to shapes or the diagram.
     * 
     * @author chsch
     */
    def void transferAnnotationsOf(KNode node, Annotatable a) {
        val service = LayoutDataService::instance;
        a.annotations.filter[!it.name.nullOrEmpty].forEach[
            val LayoutOptionData<?> data =
                service.getOptionData(it.name)?:
                service.getOptionDataBySuffix(it.name);
            
            if (data != null) {
                node.getData(typeof(KShapeLayout)).setProperty(data, switch(it.eClass) {
                    case annotationsPackage.booleanAnnotation:
                      (it as BooleanAnnotation).value
                    case annotationsPackage.intAnnotation: {
                      val value = (it as IntAnnotation).value;
                      if (data.type == LayoutOptionData$Type::FLOAT) new Float(value) else value
                    }
                    case annotationsPackage.floatAnnotation:
                     (it as FloatAnnotation).value
                    case annotationsPackage.stringAnnotation:
                     data.parseValue((it as StringAnnotation).value)
                    default:
                     null
                });
            }
        ];
    }	
}