package de.cau.cs.kieler.core.krendering.extensions

import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.krendering.KPolyline
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout

import de.cau.cs.kieler.core.properties.IProperty
import de.cau.cs.kieler.kiml.util.KimlUtil
import java.util.ArrayList

/**
 * @author chsch, alb
 */
class KEdgeExtensions {

	private static val KRenderingFactory renderingFactory = KRenderingFactory::eINSTANCE
		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////					KEdgeExtensions
	////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * A convenient getter preserving the element image relation by a create extension.
     */ 
    def private KEdge create port: KimlUtil::createInitializedEdge internalCreateEdge(ArrayList<Object> oc) {
    }

    /**
     * A convenient port getter based on a single business object preserving the
     * element image relation by a create extension.
     */
    def KEdge getEdge(Object o1) {
        return newArrayList(o1).internalCreateEdge()
    }
    
    /**
     * A convenient port getter based on a two business objects preserving the
     * element image relation by a create extension.
     */
    def KEdge getEdge(Object o1, Object o2) {
        return newArrayList(o1, o2).internalCreateEdge()
    }
    
    /**
     * A convenient port getter based on a three business objects preserving the
     * element image relation by a create extension.
     */
    def KEdge getEdge(Object o1, Object o2, Object o3) {
        return newArrayList(o1, o2, o3).internalCreateEdge()
    }
    
    /**
     * A convenient port getter based on a four business objects preserving the
     * element image relation by a create extension.
     */
    def KEdge getEdge(Object o1, Object o2, Object o3, Object o4) {
        return newArrayList(o1, o2, o3, o4).internalCreateEdge()
    }
    
    /**
     * An alias of {@link #getEdge(Object o1)} allowing to express in business that the KEdge will
     * be created at this place. It is just syntactic sugar.  
     */
    def KEdge createEdge(Object o1) {
        return o1.getEdge()
    }
    
    /**
     * An alias of {@link #getEdge(Object o1, Object o2)} allowing to express in business that the
     * KEdge will be created at this place. It is just syntactic sugar.  
     */
    def KEdge createEdge(Object o1, Object o2) {
        return o1.getEdge(o2);
    }
    
    /**
     * An alias of {@link #getEdge(Object o1, Object o2, Object o3)} allowing to express in business
     * that the KEdge will be created at this place. It is just syntactic sugar.  
     */
    def KEdge createEdge(Object o1, Object o2, Object o3) {
        return o1.getEdge(o2, o3);
    }
    
    /**
     * An alias of {@link #getEdge(Object o1, Object o2, Object o3, Object o4)} allowing to express in
     * business that the KEdge will be created at this place. It is just syntactic sugar.  
     */
    def KEdge createEdge(Object o1, Object o2, Object o3, Object o4) {
        return o1.getEdge(o2, o3, o4);
    }
    
    
    def KEdge addLayoutParam(KEdge edge, IProperty<?> property, Object value) {
        edge => [
            it.getData(typeof(KEdgeLayout)).setProperty(property, value)
        ];
    }
    
    def KPolyline addPolyline(KEdge e){
        return renderingFactory.createKPolyline => [
            e.data.add(it)
        ];
    }
    
    def KPolyline addSpline(KEdge e){
        return renderingFactory.createKSpline => [
            e.data.add(it)
        ];
    }
    
}