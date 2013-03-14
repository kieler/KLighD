package de.cau.cs.kieler.core.krendering.extensions

import javax.inject.Inject

import de.cau.cs.kieler.core.krendering.KPolyline
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.KXPosition
import de.cau.cs.kieler.core.krendering.KYPosition
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KPosition

/**
 * @author chsch, alb
 */
class KPolylineExtensions {
    
	private static val KRenderingFactory renderingFactory = KRenderingFactory::eINSTANCE
	
    @Inject
    extension KRenderingExtensions
    
    @Inject
    extension KContainerRenderingExtensions
    
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////					KPolylineExtensions
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	def KRendering addArrowDecorator(KPolyline pl) {
		return pl.drawArrow() => [
		    it.placementData = renderingFactory.createKDecoratorPlacementData => [
                it.setRotateWithLine(true);
                it.setRelative(1f);
                it.setAbsolute(-3f);
                it.setWidth(7);
                it.setXOffset(-5f);
                it.setHeight(5);
                it.setYOffset(-2.5f);
            ];
        ];
	}
	
	def KRendering addInheritanceTriangleArrowDecorator(KPolyline pl) {
        return pl.drawTriangle() => [
            it.placementData = renderingFactory.createKDecoratorPlacementData => [
         	    val float scale = pl.lineWidth.lineWidth;
                val float modifiedScale = Math::sqrt(3*scale).floatValue;
                it.setRotateWithLine(true);
                it.setRelative(1);
                it.setHeight(15 * modifiedScale);
                it.setWidth(15 * modifiedScale);
                it.setXOffset(-15 * modifiedScale);
                it.setYOffset(-(it.height + scale/2) / 2);
                it.setAbsolute(-it.width);
            ];
        ];
    }
	
	def KPosition addKPosition(KPolyline pl, KXPosition xPos, KYPosition yPos) {
		renderingFactory.createKPosition => [
	        it.setX(xPos);
		    it.setY(yPos);
		    pl.points.add(it);
		];		
	}	
}