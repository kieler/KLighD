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

import de.cau.cs.kieler.core.kgraph.KGraphElement
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.core.krendering.HorizontalAlignment
import de.cau.cs.kieler.core.krendering.KAreaPlacementData
import de.cau.cs.kieler.core.krendering.KBackground
import de.cau.cs.kieler.core.krendering.KColor
import de.cau.cs.kieler.core.krendering.KColoring
import de.cau.cs.kieler.core.krendering.KEllipse
import de.cau.cs.kieler.core.krendering.KFontBold
import de.cau.cs.kieler.core.krendering.KFontItalic
import de.cau.cs.kieler.core.krendering.KFontName
import de.cau.cs.kieler.core.krendering.KFontSize
import de.cau.cs.kieler.core.krendering.KForeground
import de.cau.cs.kieler.core.krendering.KGridPlacement
import de.cau.cs.kieler.core.krendering.KGridPlacementData
import de.cau.cs.kieler.core.krendering.KHorizontalAlignment
import de.cau.cs.kieler.core.krendering.KInvisibility
import de.cau.cs.kieler.core.krendering.KLineCap
import de.cau.cs.kieler.core.krendering.KLineJoin
import de.cau.cs.kieler.core.krendering.KLineStyle
import de.cau.cs.kieler.core.krendering.KLineWidth
import de.cau.cs.kieler.core.krendering.KPlacementData
import de.cau.cs.kieler.core.krendering.KPolygon
import de.cau.cs.kieler.core.krendering.KPolyline
import de.cau.cs.kieler.core.krendering.KPosition
import de.cau.cs.kieler.core.krendering.KRectangle
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.KRotation
import de.cau.cs.kieler.core.krendering.KRoundedRectangle
import de.cau.cs.kieler.core.krendering.KShadow
import de.cau.cs.kieler.core.krendering.KStyle
import de.cau.cs.kieler.core.krendering.KStyleHolder
import de.cau.cs.kieler.core.krendering.KStyleRef
import de.cau.cs.kieler.core.krendering.KText
import de.cau.cs.kieler.core.krendering.KTextStrikeout
import de.cau.cs.kieler.core.krendering.KTextUnderline
import de.cau.cs.kieler.core.krendering.KVerticalAlignment
import de.cau.cs.kieler.core.krendering.LineCap
import de.cau.cs.kieler.core.krendering.LineJoin
import de.cau.cs.kieler.core.krendering.LineStyle
import de.cau.cs.kieler.core.krendering.Trigger
import de.cau.cs.kieler.core.krendering.Underline
import de.cau.cs.kieler.core.krendering.VerticalAlignment

import static extension org.eclipse.emf.ecore.util.EcoreUtil.*
import de.cau.cs.kieler.core.kgraph.KLabel
import de.cau.cs.kieler.core.krendering.KContainerRendering

/**
 * This utility class contains various methods that are convenient while composing KRendering data.
 * It does not claim to be complete ;-).
 */
class KRenderingExtensions {

    private static val KRenderingFactory renderingFactory = KRenderingFactory::eINSTANCE

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////                    KRenderingExtensions
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Reveals the KRendering of a KGraphElement, e.g. for the purpose of highlighting. 
     */
    def KRendering getKRendering(KGraphElement kge) {
        return kge.getData(typeof(KRendering));
    }

    def KContainerRendering addInvisibleContainerRendering(KNode node){
        return renderingFactory.createKRectangle() => [
            it.invisible = true;
            node.data += it
        ];
    }

    def KContainerRendering addInvisibleContainerRendering(KPort port){
        return renderingFactory.createKRectangle() => [
            it.invisible = true;
            port.data += it
        ];
    }

    def KContainerRendering addInvisibleContainerRendering(KLabel label){
        return renderingFactory.createKRectangle() => [
            it.invisible = true;
            label.data += it
        ];
    }

    def KEllipse addEllipse(KNode node){
        return renderingFactory.createKEllipse() => [
            node.data += it
        ];
    }

    def KEllipse addEllipse(KPort port){
        return renderingFactory.createKEllipse() => [
            port.data += it
        ];
    }

    def KEllipse addEllipse(KLabel label){
        return renderingFactory.createKEllipse() => [
            label.data += it
        ];
    }

    def KPolygon addPolygon(KNode node){
        return renderingFactory.createKPolygon() => [
            node.data += it
        ];
    }

    def KPolygon addPolygon(KPort port){
        return renderingFactory.createKPolygon() => [
            port.data += it
        ];
    }

    def dispatch KRectangle addRectangle(KNode node){
        return renderingFactory.createKRectangle() => [
            node.data += it
        ];
    }

    def dispatch KRectangle addRectangle(KPort port){
        return renderingFactory.createKRectangle() => [
            port.data += it
        ];
    }

    def dispatch KRectangle addRectangle(KLabel label){
        return renderingFactory.createKRectangle() => [
            label.data += it
        ];
    }

    def KRoundedRectangle addRoundedRectangle(KNode node, float cWidth, float cHeight) {
        return renderingFactory.createKRoundedRectangle => [
            it.cornerWidth = cWidth;
            it.cornerHeight = cHeight;
            node.data += it
        ];
    }
    
    def KRoundedRectangle addRoundedRectangle(KLabel label, float cWidth, float cHeight) {
        return renderingFactory.createKRoundedRectangle => [
            it.cornerWidth = cWidth;
            it.cornerHeight = cHeight;
            label.data += it
        ];
    }
    
    def KRoundedRectangle addRoundedRectangle(KNode node, float cWidth, float cHeight, float lineWidth) {
        return renderingFactory.createKRoundedRectangle => [
            it.cornerWidth = cWidth;
            it.cornerHeight = cHeight;
            it.setLineWidth(lineWidth);
            node.data += it
        ];
    }
    
    def KRoundedRectangle addRoundedRectangle(KLabel label, float cWidth, float cHeight, float lineWidth) {
        return renderingFactory.createKRoundedRectangle => [
            it.cornerWidth = cWidth;
            it.cornerHeight = cHeight;
            it.setLineWidth(lineWidth);
            label.data += it
        ];
    }
    
    def KRoundedRectangle setCornerSize(KRoundedRectangle rect, float cWidth, float cHeight) {
        return rect => [
            it.cornerWidth = cWidth;
            it.cornerHeight = cHeight;
        ]
    }

    def KText addText(KNode node, String text){
        return renderingFactory.createKText() => [
            node.data += it
            it.text = text;
        ];
    }
    
    def <T extends KRendering> T addAction(T rendering, Trigger trigger, String actionId) {
        return rendering => [
            it.actions += renderingFactory.createKAction() => [
                it.trigger = trigger;
                it.id = actionId;
            ];
        ];
    }
    
    def <T extends KRendering> T addDoubleClickAction(T rendering, String actionId) {
        return rendering.addAction(Trigger::DOUBLECLICK, actionId);
    }


    def <T extends KRendering> T with(T rendering, KPlacementData pd) {
        return rendering => [
            it.placementData = pd
        ];
    }
 
    def <T extends KRendering> T with(T rendering, KStyle style) {
        return rendering => [
            it.styles += style;
        ];
    }
 
    def <T extends KRendering> T withCopyOf(T rendering, KStyle style) {
        return rendering => [
            it.styles += style.copy;
        ];
    }
    
    private static final (KStyle) => boolean IS_SELECTION = [
        it.selection
    ];
    
    // how might a getStyleRef look like?
    //  In case we allow multiple that may refine each other the getter returns an iterator?
    
    def <T extends KRendering> T setStyleRef(T rendering, KStyleHolder styleHolder) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KStyleRef)).toList);
        return rendering => [
            it.styles += renderingFactory.createKStyleRef() => [
                it.styleHolder = styleHolder;
            ];
        ];
    }
 
    def <T extends KRendering> T setSelectionStyleRef(T rendering, KStyleHolder styleHolder) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KStyleRef)).toList);
        return rendering => [
            it.styles += renderingFactory.createKStyleRef() => [
                it.selection = true;
                it.styleHolder = styleHolder;
            ];
        ];
    }
 
    def <T extends KRendering> T addStyleRef(T rendering, KStyleHolder styleHolder) {
        return rendering => [
            it.styles += renderingFactory.createKStyleRef() => [
                it.styleHolder = styleHolder;
            ];
        ];
    }
 
    def <T extends KRendering> T addSelectionStyleRef(T rendering, KStyleHolder styleHolder) {
        return rendering => [
            it.styles += renderingFactory.createKStyleRef() => [
                it.selection = true;
                it.styleHolder = styleHolder;
            ];
        ];
    }
 
    def KInvisibility getInvisible(KRendering rendering) {
        return rendering.styles.filter(typeof(KInvisibility)).last?:(renderingFactory.createKInvisibility());
    }
 
    def boolean getInvisibleValue(KRendering rendering) {
        return (
            rendering.styles.filter(typeof(KInvisibility)).last?:(renderingFactory.createKInvisibility())
        ).invisible;
    }
 
    def <T extends KRendering> T setInvisible(T rendering, boolean invisible) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KInvisibility)).toList);
        return rendering => [
            it.styles += renderingFactory.createKInvisibility() => [
                it.setInvisible(invisible);
            ]
        ];
    }
    
    def <T extends KRendering> T setSelectionInvisible(T rendering, boolean invisible) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KInvisibility)).toList);
        return rendering => [
            it.styles += renderingFactory.createKInvisibility() => [
                it.selection = true;
                it.setInvisible(invisible);
            ]
        ];
    }
    
    def KLineWidth getLineWidth(KRendering rendering) {
        return rendering.styles.filter(typeof(KLineWidth)).last?:(renderingFactory.createKLineWidth => [
            lineWidth = 1f
        ]);
    }
 
    def float getLineWidthValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KLineWidth)).last?:(renderingFactory.createKLineWidth => [
            lineWidth = 1f
        ])).lineWidth;
    }
 
    def <T extends KRendering> T setLineWidth(T rendering, float width) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KLineWidth)).toList);
        return rendering => [
            it.styles += renderingFactory.createKLineWidth() => [
                it.setLineWidth(width);
            ]
        ];
    }
    
    def <T extends KRendering> T setSelectionLineWidth(T rendering, float width) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KLineWidth)).toList);
        return rendering => [
            it.styles += renderingFactory.createKLineWidth() => [
                it.selection = true;
                it.setLineWidth(width);
            ]
        ];
    }
    
    def KLineStyle getLineStyle(KRendering rendering) {
        return rendering.styles.filter(typeof(KLineStyle)).last?:(renderingFactory.createKLineStyle => [
            lineStyle = LineStyle::SOLID;
        ]);
    }
 
    def LineStyle getLineStyleValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KLineStyle)).last?:(renderingFactory.createKLineStyle => [
            lineStyle = LineStyle::SOLID;
        ])).lineStyle;
    }
 
    def <T extends KRendering> T setLineStyle(T rendering, LineStyle style) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KLineStyle)).toList);
        return rendering => [
            it.styles += renderingFactory.createKLineStyle => [
                it.setLineStyle(style);
            ];
        ];
    }
    
    def <T extends KRendering> T setSelectionLineStyle(T rendering, LineStyle style) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KLineStyle)).toList);
        return rendering => [
            it.styles += renderingFactory.createKLineStyle => [
                it.selection = true;
                it.setLineStyle(style);
            ];
        ];
    }
    
    def KLineCap getLineCap(KRendering rendering) {
        return rendering.styles.filter(typeof(KLineCap)).last?:(renderingFactory.createKLineCap => [
            lineCap = LineCap::CAP_FLAT;
        ]);
    }
 
    def LineCap getLineCapValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KLineCap)).last?:(renderingFactory.createKLineCap => [
            lineCap = LineCap::CAP_FLAT;
        ])).lineCap;
    }
 
    def <T extends KRendering> T setLineCap(T rendering, LineCap style) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KLineCap)).toList);
        return rendering => [
            it.styles += renderingFactory.createKLineCap => [
                it.lineCap = style;
            ];
        ];
    }
    
    def <T extends KRendering> T setSelectionLineCap(T rendering, LineCap style) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KLineCap)).toList);
        return rendering => [
            it.styles += renderingFactory.createKLineCap => [
                it.selection = true;
                it.lineCap = style;
            ];
        ];
    }
    
    def KLineJoin getLineJoin(KRendering rendering) {
        return rendering.styles.filter(typeof(KLineJoin)).last?:(renderingFactory.createKLineJoin => [
            lineJoin = LineJoin::JOIN_MITER;
        ]);
    }
 
    def LineJoin getLineJoinValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KLineJoin)).last?:(renderingFactory.createKLineJoin => [
            lineJoin = LineJoin::JOIN_MITER;
        ])).lineJoin;
    }
 
    def <T extends KRendering> T setLineJoin(T rendering, LineJoin style) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KLineJoin)).toList);
        return rendering => [
            it.styles += renderingFactory.createKLineJoin => [
                it.lineJoin = style;
            ];
        ];
    }
    
    def <T extends KRendering> T setSelectionLineJoin(T rendering, LineJoin style) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KLineJoin)).toList);
        return rendering => [
            it.styles += renderingFactory.createKLineJoin => [
                it.selection = true;
                it.lineJoin = style;
            ];
        ];
    }
    
    def <T extends KRendering> T setLineJoin(T rendering, LineJoin style, float miterLimit) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KLineJoin)).toList);
        return rendering => [
            it.styles += renderingFactory.createKLineJoin => [
                it.lineJoin = style;
                it.miterLimit = miterLimit;
            ];
        ];
    }
    
    def <T extends KRendering> T setSelectionLineJoin(T rendering, LineJoin style, float miterLimit) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KLineJoin)).toList);
        return rendering => [
            it.styles += renderingFactory.createKLineJoin => [
                it.selection = true;
                it.lineJoin = style;
                it.miterLimit = miterLimit;
            ];
        ];
    }
    
    def KRotation getRotation(KRendering rendering) {
        return rendering.styles.filter(typeof(KRotation)).last?:renderingFactory.createKRotation;
    }
 
    def float getRotationValue(KRendering rendering) {
        return (
            rendering.styles.filter(typeof(KRotation)).last?:renderingFactory.createKRotation
        ).rotation;
    }
 
    def <T extends KRendering> T setRotation(T rendering, Float rotation) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KRotation)).toList);
        return rendering => [
            it.styles += renderingFactory.createKRotation => [
                it.setRotation(rotation);
            ];
        ];
    }
    
    def <T extends KRendering> T setSelectionRotation(T rendering, Float rotation) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KRotation)).toList);
        return rendering => [
            it.styles += renderingFactory.createKRotation => [
                it.selection = true;
                it.setRotation(rotation);
            ];
        ];
    }
    
    def KBackground getBackground(KRendering rendering){
        return rendering.styles?.filter(typeof(KBackground)).last?:(renderingFactory.createKBackground() => [
            it.color = renderingFactory.createKColor();
        ]);
    }
    
    def <T extends KRendering> T setBackground(T rendering, KColoring coloring){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);
        return rendering => [
            it.styles += renderingFactory.createKBackground => [
                it.alpha = coloring.alpha;
                it.color = coloring.color.copy;
                it.targetAlpha = coloring.targetAlpha;
                it.targetColor = coloring.targetColor.copy;
                it.gradientAngle = coloring.gradientAngle;
                it.modifierId = coloring.modifierId;
                it.propagateToChildren = coloring.propagateToChildren;
            ];
        ];
    }
    
    def <T extends KRendering> T setBackground(T rendering, KColor color){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);
        return rendering => [
            it.styles += renderingFactory.createKBackground => [
                it.color = color.copy;
            ];
        ];
    }
    
    def <T extends KRendering> T setBackgroundColor(T rendering, int red, int green, int blue){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);
        return rendering => [
            it.styles += renderingFactory.createKBackground => [
                it.color = renderingFactory.createKColor => [
                    it.red = red;
                    it.green = green;
                    it.blue = blue;
                ];
            ];
        ];
    }
    
    def <T extends KRendering> T setBackgroundColor(T rendering, int red, int green, int blue, int alpha){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);
        return rendering => [
            it.styles += renderingFactory.createKBackground => [
                it.alpha = alpha;
                it.color = renderingFactory.createKColor => [
                    it.red = red;
                    it.green = green;
                    it.blue = blue;
                ];
            ];
        ];
    }
    
    def <T extends KRendering> T setBackgroundGradient(T rendering, KColor color1, KColor color2, int angle) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);
        return rendering => [
            it.styles += renderingFactory.createKBackground => [
                it.color = color1;                
                it.targetColor = color2;
                it.gradientAngle = angle;
            ];
        ];
    }
    
    def <T extends KRendering> T setBackgroundGradient(T rendering, KColor color1, int alpha1,
        KColor color2, int alpha2, int angle
    ) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);
        return rendering => [
            it.styles += renderingFactory.createKBackground => [
                it.color = color1;
                it.alpha = alpha1;                
                it.targetColor = color2;
                it.targetAlpha = alpha2;
                it.gradientAngle = angle;
            ];
        ];
    }
    
    def <T extends KRendering> T setBackgroundInvisible(T rendering, boolean invisible){
        return rendering => [
            it.styles?.filter(typeof(KBackground)).last?:(renderingFactory.createKBackground() => [
                rendering.styles += it; 
                it.color = renderingFactory.createKColor();
            ]) => [
                it.alpha = if (invisible) 0
                    else renderingFactory.KRenderingPackage.KColoring_Alpha.defaultValue as Integer;
            ];
        ];
    }
    
    def KForeground getForeground(KRendering rendering){
        return rendering.styles.filter(typeof(KForeground)).last?:(renderingFactory.createKForeground() => [
            it.color = renderingFactory.createKColor();
        ]);
    }

    def <T extends KRendering>  T setForeground(T rendering, KColoring coloring){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);
        return rendering => [
            it.styles += renderingFactory.createKForeground => [
                it.alpha = coloring.alpha;
                it.color = coloring.color.copy;
                it.targetAlpha = coloring.targetAlpha;
                it.targetColor = coloring.targetColor.copy;
                it.gradientAngle = coloring.gradientAngle;
                it.modifierId = coloring.modifierId;
                it.propagateToChildren = coloring.propagateToChildren;
            ];
        ];
    }
    
    def <T extends KRendering>  T setForeground(T rendering, KColor color){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);
        return rendering => [
            it.styles += renderingFactory.createKForeground() => [
                it.color = color.copy;
            ];
        ];
    }
    
    def <T extends KRendering>  T setForegroundColor(T rendering, int red, int green, int blue){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);
        return rendering => [
            it.styles += renderingFactory.createKForeground() => [
                it.color = renderingFactory.createKColor() => [
                    it.red = red;
                    it.green = green;
                    it.blue = blue;
                ];
            ];
        ];
    }
    
    def <T extends KRendering>  T setForegroundColor(T rendering, int red, int green, int blue, int alpha){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);
        return rendering => [
            it.styles += renderingFactory.createKForeground() => [
                it.alpha = alpha;
                it.color = renderingFactory.createKColor() => [
                    it.red = red;
                    it.green = green;
                    it.blue = blue;
                ];
            ];
        ];
    }
    
    def <T extends KRendering> T setForegroundGradient(T rendering, KColor color1, KColor color2, int angle) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);
        return rendering => [
            it.styles += renderingFactory.createKForeground => [
                it.color = color1;                
                it.targetColor = color2;
                it.gradientAngle = angle;
            ];
        ];
    }
    
    def <T extends KRendering> T setForegroundGradient(T rendering, KColor color1, int alpha1,
        KColor color2, int alpha2, int angle
    ) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);
        return rendering => [
            it.styles += renderingFactory.createKForeground => [
                it.color = color1;
                it.alpha = alpha1;                
                it.targetColor = color2;
                it.targetAlpha = alpha2;
                it.gradientAngle = angle;
            ];
        ];
    }
    
    def <T extends KRendering> T setForegroundInvisible(T rendering, boolean invisible) {
        return rendering => [
            it.styles?.filter(typeof(KForeground)).last?:(renderingFactory.createKForeground() => [
                rendering.styles += it; 
                it.color = renderingFactory.createKColor();
            ]) => [
                it.alpha = if (invisible) 0
                    else renderingFactory.KRenderingPackage.KColoring_Alpha.defaultValue as Integer;
            ];
        ];
    }
        
//TODO: maybe add setters/getters for single components of KForeground/KBackground or simply a method 
//that allows sticking additional Foreground/Background information to the list without removing 
//already defined styles first        
    
    val defaultShadowSize = 4;
    
    def KShadow getShadow(KRendering rendering){
        return rendering.styles.filter(typeof(KShadow)).last?:null;
    }

    def <T extends KRendering> T setShadow(T rendering, KColor color) {
        setShadow(rendering, color, defaultShadowSize)
    }
    
    def <T extends KRendering> T setShadow(T rendering, KColor color, float size) {
        setShadow(rendering, color, size, size)
    }

    def <T extends KRendering> T setShadow(T rendering, KColor color, float xOffset, float yOffset) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KShadow)).toList);
        return rendering => [
            it.styles += renderingFactory.createKShadow() => [
                rendering.styles += it; 
                it.color = color;
                it.XOffset = xOffset;
                it.YOffset = yOffset;
            ];
        ];
    }

    
    def KFontSize getFontSize(KRendering rendering) {
        return rendering.styles.filter(typeof(KFontSize)).last?:(renderingFactory.createKFontSize => [
            size = 10
        ]);
    }
 
    def int getFontSizeValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KFontSize)).last?:(renderingFactory.createKFontSize => [
            size = 10
        ])).size;
    }
 
    def <T extends KRendering> T setFontSize(T rendering, int size) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KFontSize)).toList);
        return rendering => [
            it.styles += renderingFactory.createKFontSize => [
                it.setSize(size);
            ];
        ];      
    }
    
    def KFontName getFontName(KRendering rendering) {
        return rendering.styles.filter(typeof(KFontName)).last?:(renderingFactory.createKFontName => [
            name = "Arial"
        ]);
    }
 
    def String getFontNameValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KFontName)).last?:(renderingFactory.createKFontName => [
            name = "Arial"
        ])).name;
    }
 
    def <T extends KRendering> T setFontName(T rendering, String name) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KFontName)).toList);
        return rendering => [
            it.styles += renderingFactory.createKFontName => [
                it.setName(name);
            ];
        ];      
    }

    def KFontBold getFontBold(KRendering rendering) {
        return rendering.styles.filter(typeof(KFontBold)).last?:(renderingFactory.createKFontBold);
    }
 
    def boolean getFontBoldValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KFontBold)).last?:(renderingFactory.createKFontBold)).bold;
    }
 
    def <T extends KRendering> T setFontBold(T rendering, boolean bold) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KFontBold)).toList);
        return rendering => [
            it.styles += renderingFactory.createKFontBold() => [
                it.setBold(bold);
            ];
        ];        
    }
    
    def <T extends KRendering> T setSelectionFontBold(T rendering, boolean bold) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KFontBold)).toList);
        return rendering => [
            it.styles += renderingFactory.createKFontBold() => [
                it.selection = true;
                it.setBold(bold);
            ];
        ];        
    }
    
    def KFontItalic getFontItalic(KRendering rendering) {
        return rendering.styles.filter(typeof(KFontItalic)).last?:(renderingFactory.createKFontItalic);
    }
 
    def boolean getFontItalicValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KFontItalic)).last?:(renderingFactory.createKFontItalic)).italic;
    }
 
    def <T extends KRendering> T setFontItalic(T rendering, boolean italic) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KFontItalic)).toList);
        return rendering => [
            it.styles += renderingFactory.createKFontItalic => [
                it.setItalic(italic);
            ];
        ];
    }
    
    def KTextUnderline getTextUnderline(KRendering rendering) {
        return rendering.styles.filter(typeof(KTextUnderline)).last?:(renderingFactory.createKTextUnderline);
    }
 
    def Underline getTextUnderlineValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KTextUnderline)).last?:(renderingFactory.createKTextUnderline)).underline;
    }
 
    def KColor getTextUnderlineColorValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KTextUnderline)).last?:(renderingFactory.createKTextUnderline)).color;
    }
 
    def <T extends KRendering> T setTextUnderline(T rendering, Underline underline) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KTextUnderline)).toList);
        return rendering => [
            it.styles += renderingFactory.createKTextUnderline() => [
                it.underline = underline;
            ];
        ];
    }
    
    def <T extends KRendering> T setSelectionTextUnderline(T rendering, Underline underline) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KTextUnderline)).toList);
        return rendering => [
            it.styles += renderingFactory.createKTextUnderline() => [
                it.selection = true;
                it.underline = underline;
            ];
        ];
    }
    
    def <T extends KRendering> T setTextUnderlineColor(T rendering, KColor color) {
        return rendering => [
            (rendering.styles.filter(typeof(KTextUnderline)).last?:renderingFactory.createKTextUnderline()) => [
                it.color = color;
            ];
        ];
    }
    
    def <T extends KRendering> T setSelectionTextUnderlineColor(T rendering, KColor color) {
        return rendering => [
            (rendering.styles.filter(IS_SELECTION).filter(typeof(KTextUnderline)).last?:renderingFactory.createKTextUnderline()) => [
                it.selection = true;
                it.color = color;
            ];
        ];
    }
    
    def KTextStrikeout getTextStrikeout(KRendering rendering) {
        return rendering.styles.filter(typeof(KTextStrikeout)).last?:(renderingFactory.createKTextStrikeout);
    }
 
    def boolean getTextStrikeoutValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KTextStrikeout)).last?:(renderingFactory.createKTextStrikeout)).struckOut;
    }
 
    def KColor getTextStrikeoutColorValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KTextStrikeout)).last?:(renderingFactory.createKTextStrikeout)).color;
    }
 
    def <T extends KRendering> T setTextStrikeout(T rendering, boolean struckOut) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KTextStrikeout)).toList);
        return rendering => [
            it.styles += renderingFactory.createKTextStrikeout() => [
                it.struckOut = struckOut;
            ];
        ];
    }
    
    def <T extends KRendering> T setSelectionTextStrikeout(T rendering, boolean struckOut) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KTextStrikeout)).toList);
        return rendering => [
            it.styles += renderingFactory.createKTextStrikeout() => [
                it.selection = true;
                it.struckOut = struckOut;
            ];
        ];
    }
    
    def <T extends KRendering> T setTextStrikeoutColor(T rendering, KColor color) {
        return rendering => [
            (rendering.styles.filter(typeof(KTextStrikeout)).last?:renderingFactory.createKTextStrikeout()) => [
                it.color = color;
            ];
        ];
    }

    def <T extends KRendering> T setSelectionTextStrikeoutColor(T rendering, KColor color) {
        return rendering => [
            (rendering.styles.filter(IS_SELECTION).filter(typeof(KTextStrikeout)).last?:renderingFactory.createKTextStrikeout()) => [
                it.color = color;
            ];
        ];
    }


    public val HorizontalAlignment H_LEFT = HorizontalAlignment::LEFT; 
    public val HorizontalAlignment H_CENTRAL = HorizontalAlignment::CENTER; 
    public val HorizontalAlignment H_RIGHT = HorizontalAlignment::RIGHT; 
    
    def KHorizontalAlignment getHorizontalAlignment(KRendering rendering) {
        return rendering.styles.filter(typeof(KHorizontalAlignment)).last?:(renderingFactory.createKHorizontalAlignment => [
            horizontalAlignment = H_CENTRAL;
        ]);
    }
 
    def <T extends KRendering> T setHorizontalAlignment(T rendering, HorizontalAlignment ha) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KHorizontalAlignment)).toList);
        return rendering => [
            it.styles += renderingFactory.createKHorizontalAlignment => [
                it.setHorizontalAlignment(ha);
            ];
        ];        
    }
    
    public val VerticalAlignment V_TOP = VerticalAlignment::TOP; 
    public val VerticalAlignment V_CENTRAL = VerticalAlignment::CENTER; 
    public val VerticalAlignment V_BOTTOM = VerticalAlignment::BOTTOM; 
    
    def KVerticalAlignment getVerticalAlignment(KRendering rendering) {
        return rendering.styles.filter(typeof(KVerticalAlignment)).last?:(renderingFactory.createKVerticalAlignment => [
            verticalAlignment = V_CENTRAL;
        ]);
    }
 
    def <T extends KRendering> T setVerticalAlignment(T rendering, VerticalAlignment va) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KVerticalAlignment)).toList);
        return rendering => [
            it.styles += renderingFactory.createKVerticalAlignment => [
                it.setVerticalAlignment(va);
            ];
        ];
    }
    
    
    def KPolyline from(KPolyline polyline, 
                    PositionReferenceX px, float absoluteLR, float relativeLR,
                    PositionReferenceY py, float absoluteTB, float relativeTB) {
        return polyline => [
            it.points += createKPosition(px, absoluteLR, relativeLR, py, absoluteTB, relativeTB);
        ];
    }

    def KPolyline to(KPolyline polyline, 
                    PositionReferenceX px, float absoluteLR, float relativeLR,
                    PositionReferenceY py, float absoluteTB, float relativeTB) {
        return polyline => [
            it.points += createKPosition(px, absoluteLR, relativeLR, py, absoluteTB, relativeTB);
        ];
    }

    def <T extends KRendering> T setAreaPlacementData(T rendering, KPosition topLeft, KPosition bottomRight){
        return rendering => [
            rendering.placementData = renderingFactory.createKAreaPlacementData() => [
                it.setTopLeft(topLeft);
                it.setBottomRight(bottomRight);
            ];
        ];
    }
    
    def KAreaPlacementData setAreaPlacementData(KRendering rendering) {
        return renderingFactory.createKAreaPlacementData() => [
            rendering.placementData = it;
        ];
    }
    
    def KAreaPlacementData from(KAreaPlacementData placementData, KPosition topLeft) {
        return placementData => [
            it.topLeft = topLeft; 
        ];
    }
    
    def KAreaPlacementData from(KAreaPlacementData placementData, 
                    PositionReferenceX px, float absoluteLR, float relativeLR,
                    PositionReferenceY py, float absoluteTB, float relativeTB) {
        return placementData.from(createKPosition(
            px, absoluteLR, relativeLR, py, absoluteTB, relativeTB
        ));
    }
    
    def KRendering to(KAreaPlacementData placementData, KPosition bottomRight) {
        placementData.bottomRight = bottomRight; 
        return placementData.eContainer() as KRendering;
    }
    
    def KRendering to(KAreaPlacementData placementData, 
                    PositionReferenceX px, float absoluteLR, float relativeLR,
                    PositionReferenceY py, float absoluteTB, float relativeTB) {
        return placementData.to(createKPosition(
            px, absoluteLR, relativeLR, py, absoluteTB, relativeTB
        ));
    }
    
    def <T extends KRendering> T setSurroundingSpace(T rendering, float abs, float rel) {
        return rendering.setAreaPlacementData(
            createKPosition(LEFT, abs, rel, TOP, abs, rel),
            createKPosition(RIGHT, abs, rel, BOTTOM, abs, rel)
        );
    }

    def <T extends KRendering> T setSurroundingSpace(T rendering, float hAbs, float hRel, float vAbs, float vRel) {
        return rendering.setAreaPlacementData(
            createKPosition(LEFT, hAbs, hRel, TOP, vAbs, vRel),
            createKPosition(RIGHT, hAbs, hRel, BOTTOM, vAbs, vRel)
        );
    }

    def KGridPlacementData setGridPlacementData(KRendering rendering, float minCellWidth,
            float minCellHeight, KPosition topLeft, KPosition bottomRight) {
        return renderingFactory.createKGridPlacementData() => [
            rendering.placementData = it;
            it.setMinCellWidth(minCellWidth);
            it.setMinCellHeight(minCellHeight);
            it.setTopLeft(topLeft);
            it.setBottomRight(bottomRight);
        ];
    }

    def KGridPlacementData setGridPlacementData(KRendering rendering, float minCellWidth,
            float minCellHeight) {
        return renderingFactory.createKGridPlacementData() => [
            rendering.placementData = it;
            it.setMinCellWidth(minCellWidth);
            it.setMinCellHeight(minCellHeight);
        ];
    }

    def KGridPlacement from(KGridPlacement placement, KPosition topLeft) {
        placement.topLeft = topLeft;
        return placement;
    }
    
    def KGridPlacement from(KGridPlacement placement, 
                    PositionReferenceX px, float absoluteLR, float relativeLR,
                    PositionReferenceY py, float absoluteTB, float relativeTB) {
        placement.from(createKPosition(
            px, absoluteLR, relativeLR, py, absoluteTB, relativeTB
        ));
        return placement;
    }
    
    def KGridPlacement to(KGridPlacement placement, KPosition bottomRight) {
        placement.bottomRight = bottomRight; 
        return placement;
    }
    
    def KGridPlacement to(KGridPlacement placement, 
                    PositionReferenceX px, float absoluteLR, float relativeLR,
                    PositionReferenceY py, float absoluteTB, float relativeTB) {
        placement.to(createKPosition(
            px, absoluteLR, relativeLR, py, absoluteTB, relativeTB
        ));
        return placement;
    }
    
    def KGridPlacementData setSurroundingSpaceGrid(KRendering rendering, float abs, float rel) {
        return rendering.setGridPlacementData(0f, 0f,
            createKPosition(LEFT, abs, rel, TOP, abs, rel),
            createKPosition(RIGHT, abs, rel, BOTTOM, abs, rel)
        );
    }

    def KGridPlacementData setGridPlacementData(KRendering rendering) {
        return renderingFactory.createKGridPlacementData() => [
            rendering.placementData = it;
        ];
    }
    
    def <T extends KRendering> T setLeftTopAlignedPointPlacementData(T rendering,
        float leftMargin, float topMargin, float rightMargin, float bottomMargin) {
        return rendering.setLeftTopAlignedPointPlacementData(
            leftMargin, 0, topMargin, 0, rightMargin, bottomMargin, 0, 0
        );
    }

    def <T extends KRendering> T setLeftTopAlignedPointPlacementData(T rendering,
        float absoluteLR, float relativeLR,
        float absoluteTB, float relativeTB,
        float horizontalMargin, float verticalMargin, float minWidth, float minHeight) {
        return rendering.setPointPlacementData(
            LEFT, absoluteLR, relativeLR, TOP, absoluteTB, relativeTB, H_LEFT, V_TOP, 0, 0, 0, 0
        );
    }

    def <T extends KRendering> T setLeftBottomAlignedPointPlacementData(T rendering,
        float leftMargin, float topMargin, float rightMargin, float bottomMargin) {
        return rendering.setLeftTopAlignedPointPlacementData(
            leftMargin, 0, bottomMargin, 0, rightMargin, topMargin, 0, 0
        );
    }

    def <T extends KRendering> T setLeftBottomAlignedPointPlacementData(T rendering,
        float absoluteLR, float relativeLR,
        float absoluteTB, float relativeTB,
        float horizontalMargin, float verticalMargin, float minWidth, float minHeight) {
        return rendering.setPointPlacementData(
            LEFT, absoluteLR, relativeLR, BOTTOM, absoluteTB, relativeTB, H_LEFT, V_BOTTOM, 0, 0, 0, 0
        );
    }

    def <T extends KRendering> T setRightTopAlignedPointPlacementData(T rendering,
        float leftMargin, float topMargin, float rightMargin, float bottomMargin) {
        return rendering.setLeftTopAlignedPointPlacementData(
            rightMargin, 0, topMargin, 0, leftMargin, bottomMargin, 0, 0
        );
    }

    def <T extends KRendering> T setRightTopAlignedPointPlacementData(T rendering,
        float absoluteLR, float relativeLR,
        float absoluteTB, float relativeTB,
        float horizontalMargin, float verticalMargin, float minWidth, float minHeight) {
        return rendering.setPointPlacementData(
            RIGHT, absoluteLR, relativeLR, TOP, absoluteTB, relativeTB, H_RIGHT, V_TOP, 0, 0, 0, 0
        );
    }

    def <T extends KRendering> T setRightBottomAlignedPointPlacementData(T rendering,
        float leftMargin, float topMargin, float rightMargin, float bottomMargin) {
        return rendering.setLeftTopAlignedPointPlacementData(
            rightMargin, 0, bottomMargin, 0, leftMargin, topMargin, 0, 0
        );
    }

    def <T extends KRendering> T setRightBottomAlignedPointPlacementData(T rendering,
        float absoluteLR, float relativeLR,
        float absoluteTB, float relativeTB,
        float horizontalMargin, float verticalMargin, float minWidth, float minHeight) {
        return rendering.setPointPlacementData(
            RIGHT, absoluteLR, relativeLR, BOTTOM, absoluteTB, relativeTB, H_RIGHT, V_BOTTOM, 0, 0, 0, 0
        );
    }

    def <T extends KRendering> T setPointPlacementData(T rendering,
        PositionReferenceX px, float absoluteLR, float relativeLR,
        PositionReferenceY py, float absoluteTB, float relativeTB,
        HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
        float horizontalMargin, float verticalMargin, float minWidth, float minHeight) {
        return rendering => [
            rendering.placementData = renderingFactory.createKPointPlacementData => [
                it.referencePoint = createKPosition(px, absoluteLR, relativeLR, py, absoluteTB, relativeTB);
                it.horizontalAlignment = horizontalAlignment;
                it.verticalAlignment = verticalAlignment;
                it.horizontalMargin = horizontalMargin;
                it.verticalMargin = verticalMargin;
                it.minWidth = minWidth;
                it.minHeight = minHeight;
            ];
        ];
    }

    def <T extends KRendering> T setPointPlacementData(T rendering, KPosition referencePoint,
        HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
        float horizontalMargin, float verticalMargin, float minWidth, float minHeight) {
        return rendering => [
            rendering.placementData = renderingFactory.createKPointPlacementData => [
                it.referencePoint = referencePoint;
                it.horizontalAlignment = horizontalAlignment;
                it.verticalAlignment = verticalAlignment;
                it.horizontalMargin = horizontalMargin;
                it.verticalMargin = verticalMargin;
                it.minWidth = minWidth;
                it.minHeight = minHeight;
            ];
        ];
    }

    def <T extends KRendering> T setDecoratorPlacementData(T rendering, float width,
            float height, float posAbsolute, float posRelative, boolean rotateWithLine) {
        return rendering => [
            rendering.placementData = renderingFactory.createKDecoratorPlacementData() => [
                it.width = width;
                it.height = height;
                it.absolute = posAbsolute;
                it.relative = posRelative;
                it.rotateWithLine = rotateWithLine;
                it.XOffset = -width/2;
                it.YOffset = -height/2;
            ];
        ];
    }
    
    public val PositionReferenceX LEFT = PositionReferenceX::LEFT;
    public val PositionReferenceX RIGHT = PositionReferenceX::RIGHT;
    
    public val PositionReferenceY TOP = PositionReferenceY::TOP;
    public val PositionReferenceY BOTTOM = PositionReferenceY::BOTTOM;
    
    def KPosition createKPosition(PositionReferenceX px, float absoluteLR, float relativeLR,
                                  PositionReferenceY py, float absoluteTB, float relativeTB) {
        return renderingFactory.createKPosition => [
            it.x = switch px {
                case PositionReferenceX::LEFT: renderingFactory.createKLeftPosition
                case PositionReferenceX::RIGHT: renderingFactory.createKRightPosition
            } => [
                it.absolute = absoluteLR;
                it.relative = relativeLR;
            ];
        
            it.y = switch py {
                case PositionReferenceY::TOP: renderingFactory.createKTopPosition
                case PositionReferenceY::BOTTOM: renderingFactory.createKBottomPosition
            } => [
                it.absolute = absoluteTB;
                it.relative = relativeTB;            
            ];
        ];
    }
}
