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
import java.util.List

import de.cau.cs.kieler.core.krendering.KArc
import de.cau.cs.kieler.core.krendering.KChildArea
import de.cau.cs.kieler.core.krendering.KContainerRendering
import de.cau.cs.kieler.core.krendering.KPolyline
import de.cau.cs.kieler.core.krendering.KRectangle
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.KPolygon
import de.cau.cs.kieler.core.krendering.KGridPlacement
import de.cau.cs.kieler.core.krendering.KImage
import de.cau.cs.kieler.core.krendering.KPosition
import de.cau.cs.kieler.core.krendering.KRoundedRectangle
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KText
import de.cau.cs.kieler.core.krendering.KEllipse
import de.cau.cs.kieler.core.krendering.LineJoin

/**
 * @author chsch, alb
 * 
 * @containsExtensions
 */
class KContainerRenderingExtensions {

    private static val KRenderingFactory renderingFactory = KRenderingFactory::eINSTANCE
    
    @Inject
    extension KRenderingExtensions;
    
    @Inject
    extension KColorExtensions;
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////                    KContainerRenderings
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @returns the child! 
     */
    def <T extends KRendering> T addChild(KContainerRendering parent, T child) {
        return child => [
            parent.children.add(it);
        ];
    }
    
    def KChildArea addChildArea(KContainerRendering cr) {
        return renderingFactory.createKChildArea => [
            cr.children.removeAll(cr.children.filter(typeof(KChildArea)).toList);
            cr.children.add(it)
        ]
    }


    /**
     * @extensionCategory composition
     */
    def KArc addArc(KContainerRendering cr) {
        return renderingFactory.createKArc() => [
            cr.children += it;
        ];
    }


    /**
     * @extensionCategory composition
     */
    def KEllipse addEllipse(KContainerRendering cr) {
        return renderingFactory.createKEllipse() => [
            cr.children += it;
        ];
    }


    /**
     * @extensionCategory composition
     */
    def KPolygon addPolygon(KContainerRendering cr) {
        return renderingFactory.createKPolygon() => [
            cr.children += it;
        ];
    }

    /**
     * @extensionCategory composition
     */
    def KRectangle addRectangle(KContainerRendering cr) {
        return renderingFactory.createKRectangle() => [
            cr.children += it;
        ];
    }


    /**
     * @extensionCategory composition
     */
    def KRoundedRectangle addRoundedRectangle(KContainerRendering cr, float cWidth, float cHeight) {
        return renderingFactory.createKRoundedRectangle => [
            cr.children += it;
            it.cornerWidth = cWidth;
            it.cornerHeight = cHeight;
        ];
    }


    /**
     * @extensionCategory composition
     */
    def KRoundedRectangle addRoundedRectangle(KContainerRendering cr, float cWidth, float cHeight, float lineWidth) {
        return renderingFactory.createKRoundedRectangle => [
            cr.children += it;
            it.cornerWidth = cWidth;
            it.cornerHeight = cHeight;
            it.lineWidth = lineWidth;
        ];
    }

    def KText addText(KContainerRendering cr, String text) {
        return renderingFactory.createKText() => [
            cr.children += it;
            it.text = text;
        ];
    }

    def KImage addImage(KContainerRendering cr, String bundleId, String imagePath) {
        return renderingFactory.createKImage() => [
            cr.children += it;
            it.bundleName = bundleId;
            it.imagePath = imagePath;
        ];
    }
    
    def KImage addImage(KContainerRendering cr, Object imageObj) {
        return renderingFactory.createKImage() => [
            cr.children += it;
            it.imageObject = imageObj
        ];
    }

    /**
     * @example
     * rectangle.setGridPlacement(1).from(LEFT, 0, 0, TOP, padding - 3, 0).to(RIGHT, 0, 0, BOTTOM, 0, 0)
     * 
     * @extensionCategory microLayout
     */
    def KGridPlacement setGridPlacement(KContainerRendering cr, int cols) {
        return renderingFactory.createKGridPlacement => [
            cr.setChildPlacement(it);    
            it.setNumColumns(cols);        
        ];
    }
    
    
    def KRectangle addGridBox(KContainerRendering cr, float widthHint, float heightHint, 
        KPosition topLeft, KPosition bottomRight) {
        return renderingFactory.createKRectangle => [
            cr.children.add(it);
            it.setBackground(renderingFactory.createKBackground()=>[
                it.alpha=0;
            ]);
            it.setForeground(renderingFactory.createKForeground()=>[
                it.alpha=0;
            ]);
            it.setGridPlacementData(widthHint, heightHint, topLeft, bottomRight);
        ];
    }
    
    def KPolyline addPolyline(KContainerRendering cr) {
        return renderingFactory.createKPolyline => [
            cr.addChild(it);
        ]
    }
    
    def KPolyline addPolyline(KContainerRendering cr, float lineWidth) {
        return renderingFactory.createKPolyline => [
            cr.addChild(it);
            it.lineWidth = lineWidth;
        ]
    }
    
    def KPolyline addPolyline(KContainerRendering cr, float lineWidth, List<KPosition> points) {
        return renderingFactory.createKPolyline => [
            cr.addChild(it);
            it.lineWidth = lineWidth;
            it.points += points;
        ]
    }
    
    def KPolyline addHorizontalLine(KContainerRendering cr, float leftRightAbsIndent) {
        return cr.addChild(renderingFactory.createKPolyline())  as KPolyline => [
           it.lineWidth = 1;
           it.points += createKPosition(PositionReferenceX::LEFT, leftRightAbsIndent, 0, TOP, 0, 0.5f);
           it.points += createKPosition(PositionReferenceX::RIGHT, leftRightAbsIndent, 0, TOP, 0, 0.5f);
        ];
    }
    
    def KPolyline addHorizontalLine(KContainerRendering cr, float leftRightAbsIndent, float lineWidth) {
        return cr.addHorizontalLine(leftRightAbsIndent).lineWidth = lineWidth;
    }
    
    
    def KPolyline addHorizontalLine(KContainerRendering cr, PositionReferenceY y, float absIndent) {
        return cr.addChild(renderingFactory.createKPolyline())  as KPolyline => [
           it.lineWidth = 1;
           it.points += createKPosition(PositionReferenceX::LEFT, absIndent, 0, y, 0, 0);
           it.points += createKPosition(PositionReferenceX::RIGHT, absIndent, 0, y, 0, 0);
        ];
    }

    def KPolyline addHorizontalLine(KContainerRendering cr, PositionReferenceY y, float absIndent, float lineWidth) {
        return addHorizontalLine(cr, y, absIndent).lineWidth = lineWidth;
    }

    
    def KPolyline addVerticalLine(KContainerRendering cr, PositionReferenceX x, float absIndent) {
        return cr.addChild(renderingFactory.createKPolyline()) => [
           it.lineWidth = 1;
           it.points += createKPosition(x, 0, 0, TOP, absIndent, 0);
           it.points += createKPosition(x, 0, 0, BOTTOM, absIndent, 0);
        ];
    }

    def KPolyline addVerticalLine(KContainerRendering cr, PositionReferenceX x, float absIndent, float lineWidth) {
        return addVerticalLine(cr, x, absIndent).lineWidth = lineWidth;
    }

    
    def KPolyline addHorizontalSeperatorLine(KContainerRendering cr, float lineWidth, int spacing) {
        return renderingFactory.createKPolyline => [
            cr.addChild(it);
            it.setLineWidth(lineWidth);
            it.points.add(createKPosition(PositionReferenceX::LEFT, 0, 0, PositionReferenceY::TOP, 0, 0.5f))
            it.points.add(createKPosition(PositionReferenceX::RIGHT, 0, 0, PositionReferenceY::TOP, 0, 0.5f))
            it.placementData = renderingFactory.createKGridPlacementData => [
                it.setMinCellHeight(lineWidth + spacing)
            ]; 
        ];
    }
    
    def KPolygon drawArrow(KContainerRendering cr) {
        return renderingFactory.createKPolygon => [
            cr.addChild(it).withCopyOf(cr.lineWidth).withCopyOf(cr.foreground).setBackground(cr.foreground).setLineJoin(LineJoin::JOIN_ROUND);
            it.points += createKPosition(PositionReferenceX::LEFT, 0, 0, PositionReferenceY::TOP, 0, 0);
            it.points += createKPosition(PositionReferenceX::LEFT, 0, 0.4f, PositionReferenceY::TOP, 0, 0.5f);
            it.points += createKPosition(PositionReferenceX::LEFT, 0, 0, PositionReferenceY::BOTTOM, 0, 0);
            it.points += createKPosition(PositionReferenceX::RIGHT, 0, 0, PositionReferenceY::BOTTOM, 0, 0.5f);    
       ];    
    }
    
    def KPolygon drawTriangle(KContainerRendering cr) {
        return renderingFactory.createKPolygon => [
            cr.addChild(it).withCopyOf(cr.lineWidth).withCopyOf(cr.foreground).setBackground("white".color);
            it.points += createKPosition(PositionReferenceX::LEFT, 0, 0, PositionReferenceY::TOP, 0, 0);
            it.points += createKPosition(PositionReferenceX::LEFT, 0, 0, PositionReferenceY::BOTTOM, 0, 0);
            it.points += createKPosition(PositionReferenceX::RIGHT, 0, 0, PositionReferenceY::BOTTOM, 0, 0.5f);
        ];
    }
}
