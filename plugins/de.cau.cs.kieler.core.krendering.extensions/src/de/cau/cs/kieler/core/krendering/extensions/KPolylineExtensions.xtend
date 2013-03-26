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

import de.cau.cs.kieler.core.krendering.KPolyline
import de.cau.cs.kieler.core.krendering.KPosition
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.KXPosition
import de.cau.cs.kieler.core.krendering.KYPosition

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
    ////////////////////////                    KPolylineExtensions
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    def KRendering addArrowDecorator(KPolyline pl) {
        return pl.drawArrow() => [
            it.placementData = renderingFactory.createKDecoratorPlacementData => [
                it.rotateWithLine = true;
                it.relative = 1f;
                it.absolute = -3f;
                it.width = 7;
                it.height = 5;
                it.xOffset = -5f;
                it.yOffset = -2.5f;
            ];
        ];
    }
    
    def KRendering addInheritanceTriangleArrowDecorator(KPolyline pl) {
        return pl.drawTriangle() => [
            it.placementData = renderingFactory.createKDecoratorPlacementData => [
                val float scale = pl.lineWidth.lineWidth;
                val float modifiedScale = Math::sqrt(3*scale).floatValue;
                it.rotateWithLine = true;
                it.relative = 1f;
                it.absolute = -7.5f * modifiedScale;
                it.width = 15 * modifiedScale;
                it.height = 15 * modifiedScale;
                it.xOffset = -7.5f * modifiedScale;
                it.yOffset = -it.height / 2;
            ];
        ];
    }
    
    def KPosition addKPosition(KPolyline pl, KXPosition xPos, KYPosition yPos) {
        renderingFactory.createKPosition => [
            it.x = xPos;
            it.y = yPos;
            pl.points += it;
        ];        
    }
}