package de.cau.cs.kieler.core.krendering.extensions

import javax.inject.Inject

import de.cau.cs.kieler.core.krendering.KPolyline
import de.cau.cs.kieler.core.krendering.KPolylinePlacementData
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
		    (it.placementData as KPolylinePlacementData).detailPlacementData = 
		        renderingFactory.createKDecoratorPlacementData => [
		            it.setRelative(true);
                    it.setLocation(1)
                    it.setXOffset(-8)
                    it.setYOffset(-3)
                    it.setWidth(7)
                    it.setHeight(5)
		        ];
		];
	}
	
	def KRendering addInheritanceTriangleArrowDecorator(KPolyline pl) {
        return pl.drawTriangle() => [
            (it.placementData as KPolylinePlacementData).detailPlacementData = 
                renderingFactory.createKDecoratorPlacementData => [
             	    val int scale = pl.lineWidth.lineWidth;
                    val float modifiedScale = Math::sqrt(3*scale).floatValue;
                    
                    it.setRelative(true);
                    it.setLocation(1)
                    it.setHeight(15 * modifiedScale);
                    it.setWidth(15 * modifiedScale);
                    it.setXOffset(-15 * modifiedScale)
                    it.setYOffset(-(it.height + scale/2) / 2)
                ];
        ];
    }
	
	def KPosition addKPosition(KPolylinePlacementData ppd, KXPosition xPos, KYPosition yPos) {
		renderingFactory.createKPosition => [
	        it.setX(xPos);
		    it.setY(yPos);
		    ppd.points.add(it);
		];		
	}
	
// alternative realization:
//    def KPolyline addConnectionArrow(KPolyline line, float scale, boolean toHead) {
//        return line => [
//            // add a polyline forming the arrow with color and line width of the parent line 
//            it.children += renderingFactory.createKPolyline().withCopyOf(line.FGColor).withCopyOf(line.lineWidth) => [
//                it.placementData = renderingFactory.createKPolylinePlacementData() => [
//                    if (toHead) {
//                        it.points += newArrayList(
//                            createPoint(createLeftPos(0f,0f), createTopPos(0f,0f)),
//                            createPoint(createRightPos(0f,0f), createTopPos(0f,0.5f)),
//                            createPoint(createLeftPos(0f,0f), createBottomPos(0f,0f))
//                         );
//                    } else {
//                        it.points += newArrayList(
//                            createPoint(createRightPos(0f,0f), createTopPos(0f,0f)),
//                            createPoint(createLeftPos(0f,0f), createTopPos(0f,0.5f)),
//                            createPoint(createRightPos(0f,0f), createBottomPos(0f,0f))
//                         );
//                    }
//                    it.detailPlacementData = renderingFactory.createKDecoratorPlacementData() => [
//                        val float actualScale = Math::sqrt(3*scale).floatValue;
//                        it.height = 10 * actualScale;
//                        it.width = 15 * actualScale;
//                        it.XOffset = -15 * actualScale;
//                        it.YOffset = (it.height+scale/2f) / -2f;
//                        it.relative = true; // this directs klighd to rotate the decorator accordingly!!
//                        it.location = if (toHead) 1f else 0;
//                    ];
//                ];
//            ];
//        ];
//    }
//    
//    def KPosition createPoint(KXPosition x, KYPosition y) {
//        return renderingFactory.createKPosition => [
//            it.x = x;
//            it.y = y;
//        ];
//    }
//    
//    def KXPosition createLeftPos(Float abs, Float rel) {
//        return renderingFactory.createKLeftPosition => [
//            it.absolute = abs;
//            it.relative = rel;
//        ];
//    }
//    
//    def KXPosition createRightPos(Float abs, Float rel) {
//        return renderingFactory.createKRightPosition => [
//            it.absolute = abs;
//            it.relative = rel;
//        ];
//    }
//
//    def KYPosition createBottomPos(Float abs, Float rel) {
//        return renderingFactory.createKBottomPosition => [
//            it.absolute = abs;
//            it.relative = rel;
//        ];
//    }
//
//    def KYPosition createTopPos(Float abs, Float rel) {
//        return renderingFactory.createKTopPosition => [
//            it.absolute = abs;
//            it.relative = rel;
//        ];
//    }
}