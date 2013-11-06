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
import de.cau.cs.kieler.core.krendering.LineCap

/**
 * @author chsch, alb
 * 
 * @containsExtensions
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
        pl.lineCap = LineCap::CAP_FLAT
        return pl.drawArrow() => [
            it.placementData = renderingFactory.createKDecoratorPlacementData => [
                it.rotateWithLine = true;
                it.relative = 1f;
                it.absolute = -2f;
                it.width = 10;
                it.height = 8;
                it.setXOffset(-8f); // chsch: used the regular way here and below, as the alias 
                it.setYOffset(-4f); //  name translation convention changed from Xtext 2.3 to 2.4.
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
                it.absolute = -5f * modifiedScale;
                it.width = 10 * modifiedScale;
                it.height = 10 * modifiedScale;
                it.setXOffset(-5f * modifiedScale);
                it.setYOffset(-it.height / 2);
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