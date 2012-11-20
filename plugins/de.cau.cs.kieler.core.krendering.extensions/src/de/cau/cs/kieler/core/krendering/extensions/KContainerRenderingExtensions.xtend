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

import de.cau.cs.kieler.core.krendering.KChildArea
import de.cau.cs.kieler.core.krendering.KContainerRendering
import de.cau.cs.kieler.core.krendering.KPolyline
import de.cau.cs.kieler.core.krendering.KRectangle
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.KPolygon
import de.cau.cs.kieler.core.krendering.KGridPlacement
import de.cau.cs.kieler.core.krendering.KPosition

/**
 * @author chsch, alb
 */
class KContainerRenderingExtensions {

	private static val KRenderingFactory renderingFactory = KRenderingFactory::eINSTANCE
	
    @Inject
    extension KRenderingExtensions;
    
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////					KContainerRenderings
	////////////////////////////////////////////////////////////////////////////////////////////////////////////

	def KGridPlacement setGridPlacement(KContainerRendering cr, int cols){
		return renderingFactory.createKGridPlacement => [
		    cr.setChildPlacement(it);	
		    it.setNumColumns(cols);		
		];
	}
	
	
	def KRectangle addGridBox(KContainerRendering cr, float widthHint, float heightHint, 
	    KPosition topLeft, KPosition bottomRight){
		return renderingFactory.createKRectangle => [
		    cr.children.add(it);
            it.setBackgroundAlpha(0.0f);
            it.setForegroundAlpha(0.0f);
            it.setGridPlacementData(widthHint, heightHint, topLeft, bottomRight);
		];
	}
	
	def KPolyline addHorizontalLine(KContainerRendering cr){
		return renderingFactory.createKPolyline => [
		   cr.addChild(it);
		   it.setLineWidth(1);
		   it.points.add(createKPosition(PositionReferenceX::LEFT, 0, 0, PositionReferenceY::BOTTOM, 0, 0));
		   it.points.add(createKPosition(PositionReferenceX::RIGHT, 0, 0, PositionReferenceY::BOTTOM, 0, 0))
        ];
	}
	
	def KPolyline addHorizontalSeperatorLine(KContainerRendering cr, int lineWidth, int spacing){
        return renderingFactory.createKPolyline => [
            cr.addChild(it);
            it.setLineWidth(lineWidth);
            it.points.add(createKPosition(PositionReferenceX::LEFT, 0, 0, PositionReferenceY::TOP, 0, 0.5f))
            it.points.add(createKPosition(PositionReferenceX::RIGHT, 0, 0, PositionReferenceY::TOP, 0, 0.5f))
            it.placementData = renderingFactory.createKGridPlacementData => [
                it.setHeightHint(lineWidth + spacing)
            ]; 
//          TODO: check spacing  
//            it.placementData = renderingFactory.createKPolylinePlacementData => [
//                it.detailPlacementData = renderingFactory.createKGridPlacementData => [
//                    it.setHeightHint(lineWidth + spacing)
//                ]; 
//            ];
        ];
        
    }
	
	def KChildArea addChildArea(KContainerRendering cr){
		return renderingFactory.createKChildArea => [
            cr.children.removeAll(cr.children.filter(typeof(KChildArea)).toList);
		    cr.children.add(it)
		]
	}
	
	def KPolygon drawArrow(KContainerRendering cr){
        return renderingFactory.createKPolygon => [
            cr.addChild(it).withCopyOf(cr.lineWidth).withCopyOf(cr.FGColor);
            it.setBackgroundColor(cr.FGColor);
            it.points.add(createKPosition(PositionReferenceX::LEFT, 0, 0, PositionReferenceY::TOP, 0, 0))
            it.points.add(createKPosition(PositionReferenceX::LEFT, 0, 0.4f, PositionReferenceY::TOP, 0, 0.5f))
            it.points.add(createKPosition(PositionReferenceX::LEFT, 0, 0, PositionReferenceY::BOTTOM, 0, 0))
            it.points.add(createKPosition(PositionReferenceX::RIGHT, 0, 0, PositionReferenceY::BOTTOM, 0, 0.5f))	
       ];    
    }
	
	def KPolygon drawTriangle(KContainerRendering cr){
        return renderingFactory.createKPolygon => [
            cr.addChild(it).withCopyOf(cr.lineWidth).withCopyOf(cr.FGColor);
            it.points.add(createKPosition(PositionReferenceX::LEFT, 0, 0, PositionReferenceY::TOP, 0, 0))
            // ppd.points.add(createKPosition(PositionReferenceX::LEFT, 0, "0.5".f, PositionReferenceY::TOP, 0, 0.5f))
            it.points.add(createKPosition(PositionReferenceX::LEFT, 0, 0, PositionReferenceY::BOTTOM, 0, 0))
            it.points.add(createKPosition(PositionReferenceX::RIGHT, 0, 0, PositionReferenceY::BOTTOM, 0, 0.5f))
        ];
    }

}