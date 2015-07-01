/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
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
import de.cau.cs.kieler.core.krendering.HorizontalAlignment
import de.cau.cs.kieler.core.krendering.VerticalAlignment
import de.cau.cs.kieler.core.krendering.KText

/**
 * @author chsch, alb
 * 
 * @containsExtensions
 */
class KPolylineExtensions {

    extension KRenderingFactory = KRenderingFactory::eINSTANCE

    @Inject
    extension KRenderingExtensions

    @Inject
    extension KContainerRenderingExtensions

    @Inject
    extension KColorExtensions

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////                    KPolylineExtensions
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private def KRendering internalAddArrowDecorator(KPolyline pl, boolean head) {
        pl.lineCap = LineCap::CAP_FLAT
        return pl.drawArrow() => [
            it.placementData = createKDecoratorPlacementData => [
                it.rotateWithLine = true;
                it.relative = if (head) 1f else 0f;
                it.absolute = if (head) -2f else 2f;
                it.width = 8;
                it.height = 6;
                it.setXOffset(if (head) -6f else 6f); // chsch: used the regular way here and below, as the alias 
                it.setYOffset(if (head) -3f else 3f); //  name translation convention changed from Xtext 2.3 to 2.4.
            ];
            if (!head) it.rotation = 180f
        ];
    }

    /**
     * @deprecated use {@link #addHeadArrowDecorator(KPolyline)} instead.
     */
    def KRendering addArrowDecorator(KPolyline pl) {
        internalAddArrowDecorator(pl, true)
    }

    def KRendering addHeadArrowDecorator(KPolyline pl) {
        internalAddArrowDecorator(pl, true)
    }

    def KRendering addTailArrowDecorator(KPolyline pl) {
        internalAddArrowDecorator(pl, false)
    }

    def KRendering addJunctionPointDecorator(KPolyline pl) {
        pl.junctionPointRendering = createKEllipse => [
            it.background = "black".color;
            it.placementData = createKPointPlacementData => [
                it.horizontalAlignment = HorizontalAlignment::CENTER;
                it.verticalAlignment = VerticalAlignment::CENTER;
                it.minWidth = 4;
                it.minHeight = 4;
            ];
        ];
        return pl.junctionPointRendering;
    }

    def KRendering setJunctionPointDecorator(KPolyline pl, KRendering decorator, float width, float height) {
        pl.junctionPointRendering = decorator => [
            it.background = "black".color;
            it.placementData = createKPointPlacementData => [
                it.horizontalAlignment = HorizontalAlignment::CENTER;
                it.verticalAlignment = VerticalAlignment::CENTER;
                it.minWidth = width;
                it.minHeight = height;
            ];
        ];
        return decorator;
    }

    def KRendering addInheritanceTriangleArrowDecorator(KPolyline pl) {
        return pl.drawTriangle() => [
            it.placementData = createKDecoratorPlacementData => [
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
    
    def KPolyline addKPosition(KPolyline pl, PositionReferenceX px, float absoluteLR, float relativeLR,
                                  PositionReferenceY py, float absoluteTB, float relativeTB) {
        return pl => [
            pl.points += createKPosition(px, absoluteLR, relativeLR, py, absoluteTB, relativeTB);
        ];        
    }
    
    def KPosition addKPosition(KPolyline pl, KXPosition<?> xPos, KYPosition<?> yPos) {
        return createKPosition => [
            it.x = xPos;
            it.y = yPos;
            pl.points += it;
        ];
    }
    
    /**
     * Experimental!
     * 
     * This way only works if the polyline leaves to the right side.
     */
    def KRendering addBoxedTextDecorator(KPolyline pl, KText text, float textWidth, float textHeight) {
        pl.addRectangle => [ rect |
            rect.background = "white".color
            text.setParent(rect)
            rect.placementData = createKDecoratorPlacementData => [
                //it.rotateWithLine = true;
                it.relative = 0f;
                it.absolute =  0f;
                it.width = textWidth;
                it.height = textHeight;
                //it.setXOffset(-textWidth/2);
                it.setYOffset(-textHeight/2);
            ]; 
        ]
    }
    
}
