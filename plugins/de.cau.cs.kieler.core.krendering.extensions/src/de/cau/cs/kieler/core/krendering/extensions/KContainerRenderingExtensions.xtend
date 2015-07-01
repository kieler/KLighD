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

import de.cau.cs.kieler.core.krendering.KArc
import de.cau.cs.kieler.core.krendering.KChildArea
import de.cau.cs.kieler.core.krendering.KContainerRendering
import de.cau.cs.kieler.core.krendering.KCustomRendering
import de.cau.cs.kieler.core.krendering.KEllipse
import de.cau.cs.kieler.core.krendering.KGridPlacement
import de.cau.cs.kieler.core.krendering.KImage
import de.cau.cs.kieler.core.krendering.KPolygon
import de.cau.cs.kieler.core.krendering.KPolyline
import de.cau.cs.kieler.core.krendering.KPosition
import de.cau.cs.kieler.core.krendering.KRectangle
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.KRoundedRectangle
import de.cau.cs.kieler.core.krendering.KText
import de.cau.cs.kieler.core.krendering.LineJoin
import java.util.List

/**
 * This class contains lots of convenient helper functions for composing & configuring
 * KRendering-based view models. In order to be consistent with the further extension classes
 * the extension methods are non-static ones requiring this class to be instantiated. Since this
 * class doesn't declare any fields (i.e. required memory) except the reference of further extensions
 * classes the instantiation should not be a problem. The instantiation may be done directly by calling
 * 'new KContainerRenderingExtensions()' or by delegating that to a dependency injection framework.<br>
 * <br>
 * NOTE: Do NOT introduce <i>create extensions</i> or other continuous memory in that class!
 * 
 * @author chsch, alb
 * 
 * @containsExtensions
 */
class KContainerRenderingExtensions {

    extension KRenderingFactory = KRenderingFactory::eINSTANCE
    
    extension KRenderingExtensions = new KRenderingExtensions();    
    extension KColorExtensions = new KColorExtensions;
    
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
        return createKChildArea => [
            cr.children.removeAll(cr.children.filter(typeof(KChildArea)).toList);
            cr.children.add(it)
        ]
    }


    /**
     * @extensionCategory composition
     */
    def KArc addArc(KContainerRendering cr) {
        return createKArc() => [
            cr.children += it;
        ];
    }


    /**
     * @extensionCategory composition
     */
    def KEllipse addEllipse(KContainerRendering cr) {
        return createKEllipse() => [
            cr.children += it;
        ];
    }


    /**
     * @extensionCategory composition
     */
    def KPolygon addPolygon(KContainerRendering cr) {
        return createKPolygon() => [
            cr.children += it;
        ];
    }

    /**
     * @extensionCategory composition
     */
    def KRectangle addRectangle(KContainerRendering cr) {
        return createKRectangle() => [
            cr.children += it;
        ];
    }


    /**
     * @extensionCategory composition
     */
    def KRoundedRectangle addRoundedRectangle(KContainerRendering cr, float cWidth, float cHeight) {
        return createKRoundedRectangle => [
            cr.children += it;
            it.cornerWidth = cWidth;
            it.cornerHeight = cHeight;
        ];
    }


    /**
     * @extensionCategory composition
     */
    def KRoundedRectangle addRoundedRectangle(KContainerRendering cr, float cWidth, float cHeight, float lineWidth) {
        return createKRoundedRectangle => [
            cr.children += it;
            it.cornerWidth = cWidth;
            it.cornerHeight = cHeight;
            it.lineWidth = lineWidth;
        ];
    }

    def KText addText(KContainerRendering cr, String text) {
        return createKText() => [
            cr.children += it;
            it.text = text;
        ];
    }

    def KImage addImage(KContainerRendering cr, String bundleId, String imagePath) {
        return createKImage() => [
            cr.children += it;
            it.bundleName = bundleId;
            it.imagePath = imagePath;
        ];
    }
    
    def KImage addImage(KContainerRendering cr, Object imageObj) {
        return createKImage() => [
            cr.children += it;
            it.imageObject = imageObj
        ];
    }

    /**
     * Sets a {@link KRectangle} clip shape on the provided <code>image</code>.<br>
     * Set area or point placement data on this (returned) rectangle as usual in order
     * to determine the clip area.
     *  
     * @extensionCategory composition
     * 
     * @example
     * ...addImage(...).addRectangularClip
     *          .addAreaPlacementData.from(LEFT, 3, 0, TOP, 3, 0).to(RIGHT, 3, 0, BOTTOM, 3, 0);
     */
    def KRectangle addRectangularClip(KImage image) {
        val rect = createKRectangle;
        image.clipShape = rect;
        return rect;
    }

    /**
     * Sets a {@link KEllipse} clip shape on the provided <code>image</code>.<br>
     * Set area or point placement data on this (returned) ellipse as usual in order
     * to determine the clip area. 
     *  
     * @extensionCategory composition
     * 
     * @example
     * ...addImage(...).addEllipticalClip
     *          .addAreaPlacementData.from(LEFT, 3, 0, TOP, 3, 0).to(RIGHT, 3, 0, BOTTOM, 3, 0);
     */
    def KEllipse addEllipticalClip(KImage image) {
        val ellipse = createKEllipse;
        image.clipShape = ellipse;
        return ellipse;
    }

    /**
     * Sets a {@link KPolygon} clip shape on the provided <code>image</code>.<br>
     * Add {@link KPosition KPositions} on this (returned) polygon as usual in order
     * to determine the clip area. 
     *  
     * @extensionCategory composition
     * 
     * @example
     * ..addImage(...).addPolygonClip.addKPosition(LEFT, 3, 0, TOP, 3, 0)
     *          .addKPosition(RIGHT, 3, 0, TOP, 3, 0)
     *          .addKPosition(LEFT, 0, 0.5f, BOTTOM, 3, 0);
     */
    def KPolygon addPolygonClip(KImage image) {
        val polygon = createKPolygon;
        image.clipShape = polygon
        return polygon;      
    }

    def KCustomRendering addCustomRendering(KContainerRendering cr) {
        return createKCustomRendering => [
            cr.children += it;
        ]
    }

    def KCustomRendering addCustomRendering(KContainerRendering cr, Object figureObject) {
        return createKCustomRendering => [
            cr.children += it;
            it.figureObject = figureObject;
        ]
    }

    /**
     * @example
     * rectangle.setGridPlacement(1).from(LEFT, 0, 0, TOP, padding - 3, 0).to(RIGHT, 0, 0, BOTTOM, 0, 0)
     * 
     * @extensionCategory microLayout
     */
    def KGridPlacement setGridPlacement(KContainerRendering cr, int cols) {
        return createKGridPlacement => [
            cr.setChildPlacement(it);    
            it.setNumColumns(cols);        
        ];
    }
    
    
    def KRectangle addGridBox(KContainerRendering cr, float widthHint, float heightHint, 
        KPosition topLeft, KPosition bottomRight) {
        return createKRectangle => [
            cr.children.add(it);
            it.setBackground(createKBackground()=>[
                it.alpha=0;
            ]);
            it.setForeground(createKForeground()=>[
                it.alpha=0;
            ]);
            it.setGridPlacementData(widthHint, heightHint, topLeft, bottomRight);
        ];
    }
    
    def KPolyline addPolyline(KContainerRendering cr) {
        return createKPolyline => [
            cr.addChild(it);
        ]
    }
    
    def KPolyline addPolyline(KContainerRendering cr, float lineWidth) {
        return createKPolyline => [
            cr.addChild(it);
            it.lineWidth = lineWidth;
        ]
    }
    
    def KPolyline addPolyline(KContainerRendering cr, float lineWidth, List<KPosition> points) {
        return createKPolyline => [
            cr.addChild(it);
            it.lineWidth = lineWidth;
            it.points += points;
        ]
    }
    
    def KPolyline addHorizontalLine(KContainerRendering cr, float leftRightAbsIndent) {
        return cr.addChild(createKPolyline())  as KPolyline => [
           it.lineWidth = 1;
           it.points += createKPosition(PositionReferenceX::LEFT, leftRightAbsIndent, 0, TOP, 0, 0.5f);
           it.points += createKPosition(PositionReferenceX::RIGHT, leftRightAbsIndent, 0, TOP, 0, 0.5f);
        ];
    }
    
    def KPolyline addHorizontalLine(KContainerRendering cr, float leftRightAbsIndent, float lineWidth) {
        return cr.addHorizontalLine(leftRightAbsIndent).lineWidth = lineWidth;
    }
    
    
    def KPolyline addHorizontalLine(KContainerRendering cr, PositionReferenceY y, float absIndent) {
        return cr.addChild(createKPolyline())  as KPolyline => [
           it.lineWidth = 1;
           it.points += createKPosition(PositionReferenceX::LEFT, absIndent, 0, y, 0, 0);
           it.points += createKPosition(PositionReferenceX::RIGHT, absIndent, 0, y, 0, 0);
        ];
    }

    def KPolyline addHorizontalLine(KContainerRendering cr, PositionReferenceY y, float absIndent, float lineWidth) {
        return addHorizontalLine(cr, y, absIndent).lineWidth = lineWidth;
    }

    
    def KPolyline addVerticalLine(KContainerRendering cr, PositionReferenceX x, float absIndent) {
        return cr.addChild(createKPolyline()) => [
           it.lineWidth = 1;
           it.points += createKPosition(x, 0, 0, TOP, absIndent, 0);
           it.points += createKPosition(x, 0, 0, BOTTOM, absIndent, 0);
        ];
    }

    def KPolyline addVerticalLine(KContainerRendering cr, PositionReferenceX x, float absIndent, float lineWidth) {
        return addVerticalLine(cr, x, absIndent).lineWidth = lineWidth;
    }

    
    def KPolyline addHorizontalSeperatorLine(KContainerRendering cr, float lineWidth, int spacing) {
        return createKPolyline => [
            cr.addChild(it);
            it.setLineWidth(lineWidth);
            it.points.add(createKPosition(PositionReferenceX::LEFT, 0, 0, PositionReferenceY::TOP, 0, 0.5f))
            it.points.add(createKPosition(PositionReferenceX::RIGHT, 0, 0, PositionReferenceY::TOP, 0, 0.5f))
            it.placementData = createKGridPlacementData => [
                it.setMinCellHeight(lineWidth + spacing)
            ]; 
        ];
    }
    
    def KPolygon drawArrow(KContainerRendering cr) {
        return createKPolygon => [
            cr.addChild(it).withCopyOf(cr.lineWidth).withCopyOf(cr.foreground).setBackground(cr.foreground).setLineJoin(LineJoin::JOIN_ROUND);
            it.points += createKPosition(PositionReferenceX::LEFT, 0, 0, PositionReferenceY::TOP, 0, 0);
            it.points += createKPosition(PositionReferenceX::LEFT, 0, 0.4f, PositionReferenceY::TOP, 0, 0.5f);
            it.points += createKPosition(PositionReferenceX::LEFT, 0, 0, PositionReferenceY::BOTTOM, 0, 0);
            it.points += createKPosition(PositionReferenceX::RIGHT, 0, 0, PositionReferenceY::BOTTOM, 0, 0.5f);    
       ];    
    }
    
    def KPolygon drawTriangle(KContainerRendering cr) {
        return createKPolygon => [
            cr.addChild(it).withCopyOf(cr.lineWidth).withCopyOf(cr.foreground).setBackground("white".color);
            it.points += createKPosition(PositionReferenceX::LEFT, 0, 0, PositionReferenceY::TOP, 0, 0);
            it.points += createKPosition(PositionReferenceX::LEFT, 0, 0, PositionReferenceY::BOTTOM, 0, 0);
            it.points += createKPosition(PositionReferenceX::RIGHT, 0, 0, PositionReferenceY::BOTTOM, 0, 0.5f);
        ];
    }
}
