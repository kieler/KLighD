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
import de.cau.cs.kieler.core.kgraph.KLabel
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.core.krendering.Colors
import de.cau.cs.kieler.core.krendering.HorizontalAlignment
import de.cau.cs.kieler.core.krendering.KAreaPlacementData
import de.cau.cs.kieler.core.krendering.KBackground
import de.cau.cs.kieler.core.krendering.KColor
import de.cau.cs.kieler.core.krendering.KColoring
import de.cau.cs.kieler.core.krendering.KContainerRendering
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
import org.eclipse.emf.ecore.EClass

import static extension org.eclipse.emf.ecore.util.EcoreUtil.*
import de.cau.cs.kieler.core.krendering.KPointPlacementData

/**
 * This class contains lots of convenient helper functions for configuring KRendering-based view models, 
 * and it does not claim to be complete ;-).<br>
 * In order to be consistent with the further extension classes the extension methods are non-static
 * ones requiring this class to be instantiated. Since this class doesn't declare any fields (i.e.
 * required memory) the instantiation should not be a problem. The instantiation may be done directly
 * by calling 'new KRenderingExtensions()' or by delegating that to a dependency injection framework.<br>
 * <br>
 * NOTE: Do NOT introduce <i>create extensions</i> or other continuous memory in that class!
 * 
 * @containsExtensions
 */
class KRenderingExtensions {

    extension KRenderingFactory = KRenderingFactory::eINSTANCE

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////                    KRenderingExtensions
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Reveals the KRendering of a KGraphElement, e.g. for the purpose of highlighting. 
     */
    def KRendering getKRendering(KGraphElement kge) {
        return kge.getData(typeof(KRendering));
    }

    def KContainerRendering getKContainerRendering(KGraphElement kge) {
        return kge.getData(typeof(KContainerRendering));
    }

    def dispatch KContainerRendering addInvisibleContainerRendering(KNode node){
        return createKRectangle() => [
            it.invisible = true;
            node.data += it
        ];
    }

    def dispatch KContainerRendering addInvisibleContainerRendering(KPort port){
        return createKRectangle() => [
            it.invisible = true;
            port.data += it
        ];
    }

    def dispatch KContainerRendering addInvisibleContainerRendering(KLabel label){
        return createKRectangle() => [
            it.invisible = true;
            label.data += it
        ];
    }

    def dispatch KEllipse addEllipse(KNode node){
        return createKEllipse() => [
            node.data += it
        ];
    }

    def dispatch KEllipse addEllipse(KPort port){
        return createKEllipse() => [
            port.data += it
        ];
    }

    def dispatch KEllipse addEllipse(KLabel label){
        return createKEllipse() => [
            label.data += it
        ];
    }

    def dispatch KPolygon addPolygon(KNode node){
        return createKPolygon() => [
            node.data += it
        ];
    }

    def dispatch KPolygon addPolygon(KPort port){
        return createKPolygon() => [
            port.data += it
        ];
    }

    /**
     * Adds a rectangle to the node.
     * @example
     * node.addRectangle => [
     *   it.background = "green".color
     * ]
     * 
     * @extensionCategory composition  
     */
    def dispatch KRectangle addRectangle(KNode node){
        return createKRectangle() => [
            node.data += it
        ];
    }

    def dispatch KRectangle addRectangle(KPort port){
        return createKRectangle() => [
            port.data += it
        ];
    }

    def dispatch KRectangle addRectangle(KLabel label){
        return createKRectangle() => [
            label.data += it
        ];
    }

    /**
     * @extensionCategory composition  
     */
    def dispatch KRoundedRectangle addRoundedRectangle(KNode node, float cWidth, float cHeight) {
        return createKRoundedRectangle => [
            it.cornerWidth = cWidth;
            it.cornerHeight = cHeight;
            node.data += it
        ];
    }
    
    def dispatch KRoundedRectangle addRoundedRectangle(KLabel label, float cWidth, float cHeight) {
        return createKRoundedRectangle => [
            it.cornerWidth = cWidth;
            it.cornerHeight = cHeight;
            label.data += it
        ];
    }
    
    def dispatch KRoundedRectangle addRoundedRectangle(KNode node, float cWidth, float cHeight, float lineWidth) {
        return createKRoundedRectangle => [
            it.cornerWidth = cWidth;
            it.cornerHeight = cHeight;
            it.setLineWidth(lineWidth);
            node.data += it
        ];
    }
    
    def dispatch KRoundedRectangle addRoundedRectangle(KLabel label, float cWidth, float cHeight, float lineWidth) {
        return createKRoundedRectangle => [
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
        return createKText() => [
            node.data += it
            it.text = text;
        ];
    }
    
    def <T extends KRendering> T addAction(T rendering, Trigger trigger, String actionId) {

        rendering.actions += createKAction() => [
            it.trigger = trigger;
            it.actionId = actionId;
        ];
        return rendering;
    }
    
    def <T extends KRendering> T addAction(T rendering, Trigger trigger, String actionId,
            boolean altPressed, boolean ctrlCmdPressed, boolean shiftPressed) {

        rendering.actions += createKAction() => [
            it.trigger = trigger;
            it.actionId = actionId;
            it.altPressed = altPressed;
            it.ctrlCmdPressed = ctrlCmdPressed;
            it.shiftPressed = shiftPressed;
        ];
        return rendering;
    }
    
    def <T extends KRendering> T addSingleClickAction(T rendering, String actionId) {
        return rendering.addAction(Trigger::SINGLECLICK, actionId);
    }

    def <T extends KRendering> T addSingleClickAction(T rendering, String actionId,
            boolean altPressed, boolean ctrlCmdPressed, boolean shiftPressed) {
        return rendering.addAction(Trigger::SINGLECLICK, actionId, altPressed, ctrlCmdPressed, shiftPressed);
    }

    def <T extends KRendering> T addDoubleClickAction(T rendering, String actionId) {
        return rendering.addAction(Trigger::DOUBLECLICK, actionId);
    }

    def <T extends KRendering> T addDoubleClickAction(T rendering, String actionId,
            boolean altPressed, boolean ctrlCmdPressed, boolean shiftPressed) {
        return rendering.addAction(Trigger::DOUBLECLICK, actionId, altPressed, ctrlCmdPressed, shiftPressed);
    }


    def <T extends KRendering> T with(T rendering, KPlacementData pd) {
        rendering.placementData = pd
        return rendering;
    }
 
    def <T extends KRendering> T with(T rendering, KStyle style) {
        rendering.styles += style;
        return rendering;
    }
 
    def <T extends KRendering> T withCopyOf(T rendering, KStyle style) {
        rendering.styles += style.copy;
        return rendering;
    }
    
    private static final (KStyle) => boolean IS_SELECTION = [
        it.selection
    ];

    private static final (KStyle) => void SET_SELECTION = [
        it.selection = true;
    ];

    // styleRef definition

    // how might a getStyleRef look like?
    //  In case we allow multiple that may refine each other the getter returns an iterator?

    def <T extends KRendering> T setStyleRef(T rendering, KStyleHolder styleHolder) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KStyleRef)).toList);

        if (styleHolder == null) {
            return rendering;
        }

        rendering.styles += createKStyleRef() => [
            it.styleHolder = styleHolder;
        ];
        return rendering;
    }
 
    def <T extends KRendering> T setSelectionStyleRef(T rendering, KStyleHolder styleHolder) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KStyleRef)).toList);

        if (styleHolder == null) {
            return rendering;
        }

        rendering.styles += createKStyleRef() => [
            it.selection = true;
            it.styleHolder = styleHolder;
        ];
        return rendering;
    }
 
    def <T extends KRendering> T setStyleRef(T rendering, KStyleHolder styleHolder, Class<KStyle>... styleTypes) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KStyleRef)).toList);

        if (styleHolder == null) {
            return rendering;
        }

        rendering.styles += createKStyleRef() => [
            it.styleHolder = styleHolder;
            it.referencedTypes += styleTypes;
        ];
        return rendering;
    }
 
    def <T extends KRendering> T setSelectionStyleRef(T rendering, KStyleHolder styleHolder, Class<KStyle>... styleTypes) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KStyleRef)).toList);

        if (styleHolder == null) {
            return rendering;
        }

        rendering.styles += createKStyleRef() => [
            it.selection = true;
            it.styleHolder = styleHolder;
            it.referencedTypes += styleTypes;
        ];
        return rendering;
    }
 
    def <T extends KRendering> T setStyleRef(T rendering, KStyleHolder styleHolder, EClass... styleTypes) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KStyleRef)).toList);

        if (styleHolder == null) {
            return rendering;
        }

        rendering.styles += createKStyleRef() => [
            it.styleHolder = styleHolder;
            it.referencedTypes += styleTypes.map[
                    it.instanceClass
                ].filter[
                    typeof(KStyle).isAssignableFrom(it)
                ] as Iterable<?> as Iterable<Class<KStyle>>;
        ];
        return rendering;
    }
 
    def <T extends KRendering> T setSelectionStyleRef(T rendering, KStyleHolder styleHolder, EClass... styleTypes) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KStyleRef)).toList);

        if (styleHolder == null) {
            return rendering;
        }

        rendering.styles += createKStyleRef() => [
            it.selection = true;
            it.styleHolder = styleHolder;
            it.referencedTypes += styleTypes.map[
                    it.instanceClass
                ].filter[
                    typeof(KStyle).isAssignableFrom(it)
                ] as Iterable<?> as Iterable<Class<KStyle>>;
        ];
        return rendering;
    }
 
    def <T extends KRendering> T addStyleRef(T rendering, KStyleHolder styleHolder) {
        if (styleHolder == null) {
            return rendering;
        }

        rendering.styles += createKStyleRef() => [
            it.styleHolder = styleHolder;
        ];
        return rendering;
    }
 
    def <T extends KRendering> T addSelectionStyleRef(T rendering, KStyleHolder styleHolder) {
        if (styleHolder == null) {
            return rendering;
        }

        rendering.styles += createKStyleRef() => [
            it.selection = true;
            it.styleHolder = styleHolder;
        ];
        return rendering;
    }
 
    def <T extends KRendering> T addStyleRef(T rendering, KStyleHolder styleHolder, Class<KStyle>... styleTypes) {
        if (styleHolder == null) {
            return rendering;
        }

        rendering.styles += createKStyleRef() => [
            it.styleHolder = styleHolder;
            it.referencedTypes += styleTypes;
        ];
        return rendering;
    }
 
    def <T extends KRendering> T addSelectionStyleRef(T rendering, KStyleHolder styleHolder, Class<KStyle>... styleTypes) {
        if (styleHolder == null) {
            return rendering;
        }

        rendering.styles += createKStyleRef() => [
            it.selection = true;
            it.styleHolder = styleHolder;
            it.referencedTypes += styleTypes;
        ];
        return rendering;
    }
 
    def <T extends KRendering> T addStyleRef(T rendering, KStyleHolder styleHolder, EClass... styleTypes) {
        if (styleHolder == null) {
            return rendering;
        }

        rendering.styles += createKStyleRef() => [
            it.styleHolder = styleHolder;
            it.referencedTypes += styleTypes.map[
                    it.instanceClass
                ].filter[
                    typeof(KStyle).isAssignableFrom(it)
                ] as Iterable<?> as Iterable<Class<KStyle>>;
        ];
        return rendering;
    }
 
    def <T extends KRendering> T addSelectionStyleRef(T rendering, KStyleHolder styleHolder, EClass... styleTypes) {
        if (styleHolder == null) {
            return rendering;
        }

        rendering.styles += createKStyleRef() => [
            it.selection = true;
            it.styleHolder = styleHolder;
            it.referencedTypes += styleTypes.map[
                    it.instanceClass
                ].filter[
                    typeof(KStyle).isAssignableFrom(it)
                ] as Iterable<?> as Iterable<Class<KStyle>>;
        ];
        return rendering;
    }


    // invisibility definition

    def KInvisibility getInvisible(KRendering rendering) {
        return rendering.styles.filter(typeof(KInvisibility)).last?:(createKInvisibility());
    }
 
    def boolean getInvisibleValue(KRendering rendering) {
        return (
            rendering.styles.filter(typeof(KInvisibility)).last?:(createKInvisibility())
        ).invisible;
    }
 
    def <T extends KRendering> T setInvisible(T rendering, boolean invisible) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KInvisibility)).toList);

        rendering.styles += createKInvisibility() => [
            it.setInvisible(invisible);
        ];
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionInvisible(T rendering, boolean invisible) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KInvisibility)).toList);

        rendering.styles += createKInvisibility() => [
            it.selection = true;
            it.setInvisible(invisible);
        ];
        return rendering;
    }
    
    def KLineWidth getLineWidth(KRendering rendering) {
        return rendering.styles.filter(typeof(KLineWidth)).last?:(createKLineWidth => [
            lineWidth = 1f
        ]);
    }
 
    def float getLineWidthValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KLineWidth)).last?:(createKLineWidth => [
            lineWidth = 1f
        ])).lineWidth;
    }

    /**
     * @extensionCategory style  
     */
    def <T extends KRendering> T setLineWidth(T rendering, float width) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KLineWidth)).toList);

        rendering.styles += createKLineWidth() => [
            it.setLineWidth(width);
        ];
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionLineWidth(T rendering, float width) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KLineWidth)).toList);

        rendering.styles += createKLineWidth() => [
            it.selection = true;
            it.setLineWidth(width);
        ];
        return rendering;
    }
    
    /**
     * @extensionCategory style  
     */
    def KLineStyle getLineStyle(KRendering rendering) {
        return rendering.styles.filter(typeof(KLineStyle)).last?:(createKLineStyle => [
            lineStyle = LineStyle::SOLID;
        ]);
    }
 
    def LineStyle getLineStyleValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KLineStyle)).last?:(createKLineStyle => [
            lineStyle = LineStyle::SOLID;
        ])).lineStyle;
    }
 
    def <T extends KRendering> T setLineStyle(T rendering, LineStyle style) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KLineStyle)).toList);

        rendering.styles += createKLineStyle => [
            it.setLineStyle(style);
        ];
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionLineStyle(T rendering, LineStyle style) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KLineStyle)).toList);

        rendering.styles += createKLineStyle => [
            it.selection = true;
            it.setLineStyle(style);
        ];
        return rendering;
    }
    
    def KLineCap getLineCap(KRendering rendering) {
        return rendering.styles.filter(typeof(KLineCap)).last?:(createKLineCap => [
            lineCap = LineCap::CAP_FLAT;
        ]);
    }
 
    def LineCap getLineCapValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KLineCap)).last?:(createKLineCap => [
            lineCap = LineCap::CAP_FLAT;
        ])).lineCap;
    }
 
    def <T extends KRendering> T setLineCap(T rendering, LineCap style) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KLineCap)).toList);

        rendering.styles += createKLineCap => [
            it.lineCap = style;
        ];
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionLineCap(T rendering, LineCap style) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KLineCap)).toList);

        rendering.styles += createKLineCap => [
            it.selection = true;
            it.lineCap = style;
        ];
        return rendering;
    }
    
    def KLineJoin getLineJoin(KRendering rendering) {
        return rendering.styles.filter(typeof(KLineJoin)).last?:(createKLineJoin => [
            lineJoin = LineJoin::JOIN_MITER;
        ]);
    }
 
    def LineJoin getLineJoinValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KLineJoin)).last?:(createKLineJoin => [
            lineJoin = LineJoin::JOIN_MITER;
        ])).lineJoin;
    }
 
    def <T extends KRendering> T setLineJoin(T rendering, LineJoin style) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KLineJoin)).toList);

        rendering.styles += createKLineJoin => [
            it.lineJoin = style;
        ];
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionLineJoin(T rendering, LineJoin style) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KLineJoin)).toList);

        rendering.styles += createKLineJoin => [
                it.selection = true;
                it.lineJoin = style;
            ];
        return rendering;
    }
    
    def <T extends KRendering> T setLineJoin(T rendering, LineJoin style, float miterLimit) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KLineJoin)).toList);
        rendering.styles += createKLineJoin => [
                it.lineJoin = style;
                it.miterLimit = miterLimit;
            ];
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionLineJoin(T rendering, LineJoin style, float miterLimit) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KLineJoin)).toList);
        rendering.styles += createKLineJoin => [
                it.selection = true;
                it.lineJoin = style;
                it.miterLimit = miterLimit;
            ];
        return rendering;
    }
    
    def KRotation getRotation(KRendering rendering) {
        return rendering.styles.filter(typeof(KRotation)).last?:createKRotation;
    }
 
    def float getRotationValue(KRendering rendering) {
        return (
            rendering.styles.filter(typeof(KRotation)).last?:createKRotation
        ).rotation;
    }
 
    def <T extends KRendering> T setRotation(T rendering, Float rotation) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KRotation)).toList);
        rendering.styles += createKRotation => [
                it.setRotation(rotation);
            ];
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionRotation(T rendering, Float rotation) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KRotation)).toList);
        rendering.styles += createKRotation => [
                it.selection = true;
                it.setRotation(rotation);
            ];
        return rendering;
    }
    
    def KBackground getBackground(KRendering rendering){
        return rendering.styles?.filter(typeof(KBackground)).last?:(createKBackground() => [
            it.color = createKColor();
        ]);
    }
    
    def <T extends KRendering> T setBackground(T rendering, KColoring<?> coloring) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);
        rendering.styles += createKBackground.setColor2(coloring.color.copy, coloring.alpha)
                    .setTargetColor2(coloring.targetColor.copy, coloring.targetAlpha)
                    .setGradientAngle2(coloring.gradientAngle) => [
            it.modifierId = coloring.modifierId;
            it.propagateToChildren = coloring.propagateToChildren;
        ];
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionBackground(T rendering, KColoring<?> coloring) {
        rendering.styles += createKBackground.setColor2(coloring.color.copy, coloring.alpha)
                    .setTargetColor2(coloring.targetColor.copy, coloring.targetAlpha)
                    .setGradientAngle2(coloring.gradientAngle) => [
                it.modifierId = coloring.modifierId;
                it.propagateToChildren = coloring.propagateToChildren;
                it.selection = true;
            ];
        return rendering;
    }
    
    def <T extends KRendering> T setBackground(T rendering, KColor color) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);

        rendering.styles += createKBackground.setColor2(color.copy);
        return rendering;
    }

    def <T extends KRendering> T setSelectionBackground(T rendering, KColor color) {
        rendering.styles += createKBackground.setColor2(color.copy) => SET_SELECTION;
        return rendering;
    }

    def <T extends KRendering> T setBackground(T rendering, KColor color, int alpha) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);

        rendering.styles += createKBackground.setColor2(color.copy, alpha);
        return rendering;
    }

    def <T extends KRendering> T setSelectionBackground(T rendering, KColor color, int alpha) {
        rendering.styles += createKBackground.setColor2(color.copy, alpha) => SET_SELECTION;
        return rendering;
    }

    def <T extends KRendering> T setBackground(T rendering, Colors color) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);

        rendering.styles += createKBackground.setColor(color);
        return rendering;
    }

    def <T extends KRendering> T setSelectionBackground(T rendering, Colors color) {
        rendering.styles += createKBackground.setColor(color) => SET_SELECTION;
        return rendering;
    }

    def <T extends KRendering> T setBackground(T rendering, Colors color, int alpha) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);

        rendering.styles += createKBackground.setColor(color, alpha);
        return rendering;
    }

    def <T extends KRendering> T setSelectionBackground(T rendering, Colors color, int alpha) {
        rendering.styles += createKBackground.setColor(color, alpha) => SET_SELECTION;
        return rendering;
    }

    def <T extends KRendering> T setBackgroundColor(T rendering, int red, int green, int blue){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);

        rendering.styles += createKBackground.setColor(red, green, blue);
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionBackgroundColor(T rendering, int red, int green, int blue){
        rendering.styles += createKBackground.setColor(red, green, blue) => SET_SELECTION;
        return rendering;
    }
    
    def <T extends KRendering> T setBackgroundColor(T rendering, int red, int green, int blue, int alpha){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);

        rendering.styles += createKBackground.setColor(red, green, blue, alpha);
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionBackgroundColor(T rendering, int red, int green, int blue, int alpha){

        rendering.styles += createKBackground.setColor(red, green, blue, alpha) => SET_SELECTION;
        return rendering;
    }
    
    def <T extends KRendering> T setBackgroundGradient(T rendering, KColor color1, KColor color2, float angle) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);

        rendering.styles += createKBackground.setColors(color1, color2, angle);
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionBackgroundGradient(T rendering, KColor color1, KColor color2, float angle) {
        rendering.styles += createKBackground.setColors(color1, color2, angle) => SET_SELECTION;
        return rendering;
    }
    
    def <T extends KRendering> T setBackgroundGradient(T rendering,
            KColor color1, int alpha1, KColor color2, int alpha2, float angle) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);

        rendering.styles += createKBackground.setColors(color1, alpha1, color2, alpha2, angle);
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionBackgroundGradient(T rendering,
            KColor color1, int alpha1, KColor color2, int alpha2, float angle) {
        rendering.styles += createKBackground.setColors(color1, alpha1, color2, alpha2, angle) => SET_SELECTION;
        return rendering;
    }
    
    def <T extends KRendering> T setBackgroundGradient(T rendering, Colors color1, Colors color2, float angle) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);

        rendering.styles += createKBackground.setColors(color1, color2, angle)
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionBackgroundGradient(T rendering, Colors color1, Colors color2, float angle) {
        rendering.styles += createKBackground.setColors(color1, color2, angle) => SET_SELECTION;
        return rendering;
    }
    
    def <T extends KRendering> T setBackgroundGradient(T rendering, Colors color1, int alpha1,
        Colors color2,  int alpha2, float angle) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);

        rendering.styles += createKBackground.setColors(color1, alpha1, color2, alpha2, angle)
        return rendering;
    }

    def <T extends KRendering> T setSelectionBackgroundGradient(T rendering, Colors color1, int alpha1,
        Colors color2,  int alpha2, float angle) {
        rendering.styles += createKBackground.setColors(color1, alpha1, color2, alpha2, angle) => SET_SELECTION;
        return rendering;
    }

    
    def <T extends KRendering> T setBackgroundInvisible(T rendering, boolean invisible){
        return rendering => [
            it.styles?.filter(typeof(KBackground)).last?:(createKBackground() => [
                rendering.styles += it; 
                it.color = createKColor();
            ]) => [
                it.alpha = if (invisible) 0
                    else KRenderingPackage.KColoring_Alpha.defaultValue as Integer;
            ];
        ];
    }
    
    def KForeground getForeground(KRendering rendering){
        return rendering.styles.filter(typeof(KForeground)).last?:(createKForeground() => [
            it.color = createKColor();
        ]);
    }

    def <T extends KRendering>  T setForeground(T rendering, KColoring<?> coloring){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);

        rendering.styles += createKForeground.setColors(
            coloring.color.copy, coloring.alpha, coloring.targetColor.copy, coloring.targetAlpha, coloring.gradientAngle
        ) => [
            it.modifierId = coloring.modifierId;
            it.propagateToChildren = coloring.propagateToChildren;
        ];
        return rendering;
    }
    
    def <T extends KRendering>  T setForeground(T rendering, KColor color){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);

        rendering.styles += createKForeground().setColor2(color);
        return rendering;
    }
    
    def <T extends KRendering>  T setSelectionForeground(T rendering, KColor color){
        rendering.styles += createKForeground().setColor2(color) => SET_SELECTION;
        return rendering;
    }
    
    def <T extends KRendering>  T setForeground(T rendering, KColor color, int alpha){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);

        rendering.styles += createKForeground().setColor2(color, alpha);
        return rendering;
    }
    
    def <T extends KRendering>  T setSelectionForeground(T rendering, KColor color, int alpha){
        rendering.styles += createKForeground().setColor2(color, alpha) => SET_SELECTION;
        return rendering;
    }
    
    def <T extends KRendering>  T setForeground(T rendering, Colors color){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);

        rendering.styles += createKForeground().setColor(color);
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionForeground(T rendering, Colors color) {
        rendering.styles += createKForeground.setColor(color) => SET_SELECTION;
        return rendering;
    }

    def <T extends KRendering>  T setForeground(T rendering, Colors color, int alpha){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);

        rendering.styles += createKForeground().setColor(color, alpha);
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionForeground(T rendering, Colors color, int alpha) {
        rendering.styles += createKForeground.setColor(color, alpha) => SET_SELECTION;
        return rendering;
    }

    def <T extends KRendering>  T setForegroundColor(T rendering, int red, int green, int blue){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);

        rendering.styles += createKForeground().setColor(red, green, blue);
        return rendering;
    }
    
    def <T extends KRendering>  T setSelectionForegroundColor(T rendering, int red, int green, int blue){
        rendering.styles += createKForeground().setColor(red, green, blue) => SET_SELECTION;
        return rendering;
    }
    
    def <T extends KRendering>  T setForegroundColor(T rendering, int red, int green, int blue, int alpha){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);

        rendering.styles += createKForeground().setColor(red, green, blue, alpha)
        return rendering;
    }
    
    def <T extends KRendering>  T setSelectionForegroundColor(T rendering, int red, int green, int blue, int alpha){
        rendering.styles += createKForeground().setColor(red, green, blue, alpha) => SET_SELECTION
        return rendering;
    }
    
    def <T extends KRendering> T setForegroundGradient(T rendering, KColor color1, KColor color2, float angle) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);

        rendering.styles += createKForeground.setColors(color1, color2, angle);
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionForegroundGradient(T rendering, KColor color1, KColor color2, float angle) {
        rendering.styles += createKForeground.setColors(color1, color2, angle) => SET_SELECTION;
        return rendering;
    }
    
    def <T extends KRendering> T setForegroundGradient(T rendering, KColor color1, int alpha1,
        KColor color2, int alpha2, float angle) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);

        rendering.styles += createKForeground.setColors(color1, alpha1, color2, alpha2, angle);
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionForegroundGradient(T rendering, KColor color1, int alpha1,
        KColor color2, int alpha2, float angle) {
        rendering.styles += createKForeground.setColors(color1, alpha1, color2, alpha2, angle) => SET_SELECTION;
        return rendering;
    }
    
    def <T extends KRendering> T setForegroundGradient(T rendering, Colors color1, Colors color2, float angle) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);

        rendering.styles += createKForeground.setColors(color1, color2, angle)
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionForegroundGradient(T rendering, Colors color1, Colors color2, float angle) {
        rendering.styles += createKForeground.setColors(color1, color2, angle) => SET_SELECTION;
        return rendering;
    }
    
    def <T extends KRendering> T setForegroundGradient(T rendering, Colors color1, int alpha1,
        Colors color2,  int alpha2, float angle) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);

        rendering.styles += createKForeground.setColors(color1, alpha1, color2, alpha2, angle);
        return rendering;
    }

    def <T extends KRendering> T setSelectionForegroundGradient(T rendering, Colors color1, int alpha1,
        Colors color2,  int alpha2, float angle) {
        rendering.styles += createKForeground.setColors(color1, alpha1, color2, alpha2, angle) => SET_SELECTION;
        return rendering;
    }

    def <T extends KRendering> T setForegroundInvisible(T rendering, boolean invisible) {
        return rendering => [
            it.styles?.filter(typeof(KForeground)).last?:(createKForeground() => [
                rendering.styles += it; 
                it.color = createKColor();
            ]) => [
                it.alpha = if (invisible) 0
                    else KRenderingPackage.KColoring_Alpha.defaultValue as Integer;
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

        rendering.styles += createKShadow() => [
            rendering.styles += it; 
            it.color = color;
            it.XOffset = xOffset;
            it.YOffset = yOffset;
        ];
        return rendering;
    }

    
    def KFontSize getFontSize(KRendering rendering) {
        return rendering.styles.filter(typeof(KFontSize)).last?:(createKFontSize => [
            size = 10
        ]);
    }
 
    def int getFontSizeValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KFontSize)).last?:(createKFontSize => [
            size = 10
        ])).size;
    }
 
    def <T extends KRendering> T setFontSize(T rendering, int size) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KFontSize)).toList);

        rendering.styles += createKFontSize => [
            it.setSize(size);
        ];
        return rendering;      
    }
    
    def KFontName getFontName(KRendering rendering) {
        return rendering.styles.filter(typeof(KFontName)).last?:(createKFontName => [
            name = "Arial"
        ]);
    }
 
    def String getFontNameValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KFontName)).last?:(createKFontName => [
            name = "Arial"
        ])).name;
    }
 
    def <T extends KRendering> T setFontName(T rendering, String name) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KFontName)).toList);

        rendering.styles += createKFontName => [
            it.setName(name);
        ];
        return rendering;      
    }

    def KFontBold getFontBold(KRendering rendering) {
        return rendering.styles.filter(typeof(KFontBold)).last?:(createKFontBold);
    }
 
    def boolean getFontBoldValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KFontBold)).last?:(createKFontBold)).bold;
    }
 
    def <T extends KRendering> T setFontBold(T rendering, boolean bold) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KFontBold)).toList);

        rendering.styles += createKFontBold() => [
            it.setBold(bold);
        ];
        return rendering;        
    }
    
    def <T extends KRendering> T setSelectionFontBold(T rendering, boolean bold) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KFontBold)).toList);

        rendering.styles += createKFontBold() => [
            it.selection = true;
            it.setBold(bold);
        ];
        return rendering;        
    }
    
    def KFontItalic getFontItalic(KRendering rendering) {
        return rendering.styles.filter(typeof(KFontItalic)).last?:(createKFontItalic);
    }
 
    def boolean getFontItalicValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KFontItalic)).last?:(createKFontItalic)).italic;
    }
 
    def <T extends KRendering> T setFontItalic(T rendering, boolean italic) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KFontItalic)).toList);

        rendering.styles += createKFontItalic => [
            it.setItalic(italic);
        ];
        return rendering;
    }
    
    def KTextUnderline getTextUnderline(KRendering rendering) {
        return rendering.styles.filter(typeof(KTextUnderline)).last?:(createKTextUnderline);
    }
 
    def Underline getTextUnderlineValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KTextUnderline)).last?:(createKTextUnderline)).underline;
    }
 
    def KColor getTextUnderlineColorValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KTextUnderline)).last?:(createKTextUnderline)).color;
    }
 
    def <T extends KRendering> T setTextUnderline(T rendering, Underline underline) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KTextUnderline)).toList);

        rendering.styles += createKTextUnderline() => [
            it.underline = underline;
        ];
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionTextUnderline(T rendering, Underline underline) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KTextUnderline)).toList);

        rendering.styles += createKTextUnderline() => [
            it.selection = true;
            it.underline = underline;
        ];
        return rendering;
    }
    
    def <T extends KRendering> T setTextUnderlineColor(T rendering, KColor color) {
        return rendering => [
            (rendering.styles.filter(typeof(KTextUnderline)).last?:createKTextUnderline()) => [
                it.color = color;
            ];
        ];
    }
    
    def <T extends KRendering> T setSelectionTextUnderlineColor(T rendering, KColor color) {
        return rendering => [
            (rendering.styles.filter(IS_SELECTION).filter(typeof(KTextUnderline)).last?:createKTextUnderline()) => [
                it.selection = true;
                it.color = color;
            ];
        ];
    }
    
    def KTextStrikeout getTextStrikeout(KRendering rendering) {
        return rendering.styles.filter(typeof(KTextStrikeout)).last?:(createKTextStrikeout);
    }
 
    def boolean getTextStrikeoutValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KTextStrikeout)).last?:(createKTextStrikeout)).struckOut;
    }
 
    def KColor getTextStrikeoutColorValue(KRendering rendering) {
        return (rendering.styles.filter(typeof(KTextStrikeout)).last?:(createKTextStrikeout)).color;
    }
 
    def <T extends KRendering> T setTextStrikeout(T rendering, boolean struckOut) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KTextStrikeout)).toList);

        rendering.styles += createKTextStrikeout() => [
            it.struckOut = struckOut;
        ];
        return rendering;
    }
    
    def <T extends KRendering> T setSelectionTextStrikeout(T rendering, boolean struckOut) {
        rendering.styles.removeAll(rendering.styles.filter(IS_SELECTION).filter(typeof(KTextStrikeout)).toList);

        rendering.styles += createKTextStrikeout() => [
            it.selection = true;
            it.struckOut = struckOut;
        ];
        return rendering;
    }
    
    def <T extends KRendering> T setTextStrikeoutColor(T rendering, KColor color) {
        return rendering => [
            (rendering.styles.filter(typeof(KTextStrikeout)).last?:createKTextStrikeout()) => [
                it.color = color;
            ];
        ];
    }

    def <T extends KRendering> T setSelectionTextStrikeoutColor(T rendering, KColor color) {
        return rendering => [
            (rendering.styles.filter(IS_SELECTION).filter(typeof(KTextStrikeout)).last?:createKTextStrikeout()) => [
                it.color = color;
            ];
        ];
    }


    public val HorizontalAlignment H_LEFT = HorizontalAlignment::LEFT; 
    public val HorizontalAlignment H_CENTRAL = HorizontalAlignment::CENTER; 
    public val HorizontalAlignment H_RIGHT = HorizontalAlignment::RIGHT; 
    
    def KHorizontalAlignment getHorizontalAlignment(KRendering rendering) {
        return rendering.styles.filter(typeof(KHorizontalAlignment)).last?:(createKHorizontalAlignment => [
            horizontalAlignment = H_CENTRAL;
        ]);
    }
 
    def <T extends KRendering> T setHorizontalAlignment(T rendering, HorizontalAlignment ha) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KHorizontalAlignment)).toList);

        rendering.styles += createKHorizontalAlignment => [
            it.setHorizontalAlignment(ha);
        ];
        return rendering;        
    }
    
    public val VerticalAlignment V_TOP = VerticalAlignment::TOP; 
    public val VerticalAlignment V_CENTRAL = VerticalAlignment::CENTER; 
    public val VerticalAlignment V_BOTTOM = VerticalAlignment::BOTTOM; 
    
    def KVerticalAlignment getVerticalAlignment(KRendering rendering) {
        return rendering.styles.filter(typeof(KVerticalAlignment)).last?:(createKVerticalAlignment => [
            verticalAlignment = V_CENTRAL;
        ]);
    }
 
    def <T extends KRendering> T setVerticalAlignment(T rendering, VerticalAlignment va) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KVerticalAlignment)).toList);

        rendering.styles += createKVerticalAlignment => [
            it.setVerticalAlignment(va);
        ];
        return rendering;
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
        rendering.placementData = createKAreaPlacementData() => [
            it.setTopLeft(topLeft);
            it.setBottomRight(bottomRight);
        ];
        return rendering;
    }
    
    def KAreaPlacementData setAreaPlacementData(KRendering rendering) {
        return createKAreaPlacementData() => [
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

    /**
     * Adds a grid placement to the rendering element with the specified spacing. 
     *  
     */
    def KGridPlacementData setGridPlacementData(KRendering rendering, float minCellWidth,
            float minCellHeight, KPosition topLeft, KPosition bottomRight) {
        return createKGridPlacementData() => [
            rendering.placementData = it;
            it.setMinCellWidth(minCellWidth);
            it.setMinCellHeight(minCellHeight);
            it.setTopLeft(topLeft);
            it.setBottomRight(bottomRight);
        ];
    }

/**
     * Adds a grid placement to the rendering element with the specified spacing. 
     */
    def KGridPlacementData setGridPlacementData(KRendering rendering, float minCellWidth,
            float minCellHeight) {
        return createKGridPlacementData() => [
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

    /**
     * Adds a grid placement to the rendering element with the specified spacing. 
     *  
     * @example
     * rectangle.setGridPlacementData => [ cell |
     *  cell.from(LEFT, padding, 0, TOP, padding - 2, 0).to(RIGHT, padding, 0, BOTTOM, 0, 0)
     *  cell.minCellHeight = propBarHeight
     *  cell.flexibleHeight = false
     * ]
     */
    def KGridPlacementData setGridPlacementData(KRendering rendering) {
        return createKGridPlacementData() => [
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
        PositionReferenceY py, float absoluteTB, float relativeTB) {

        rendering.placementData = createKPointPlacementData => [
            it.referencePoint = createKPosition(px, absoluteLR, relativeLR, py, absoluteTB, relativeTB);
        ];
        return rendering
    }

    def <T extends KRendering> KPointPlacementData setPointPlacementData(T rendering) {
        return createKPointPlacementData => [
            rendering.placementData = it; 
        ];
    }

    def KPointPlacementData at(KPointPlacementData ppd, KPosition pos) {

        ppd.referencePoint = pos;
        return ppd;
    }

    def KPointPlacementData at(KPointPlacementData ppd, PositionReferenceX px, float absoluteLR, float relativeLR,
        PositionReferenceY py, float absoluteTB, float relativeTB) {

        ppd.referencePoint = createKPosition(px, absoluteLR, relativeLR, py, absoluteTB, relativeTB);
        return ppd;
    }

    def KPointPlacementData setAlignment(KPointPlacementData ppd,
        HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {

        ppd.horizontalAlignment = horizontalAlignment;
        ppd.verticalAlignment = verticalAlignment;
        return ppd;
    }

    def KPointPlacementData setMargins(KPointPlacementData ppd,
        float horizontalMargin, float verticalMargin) {

        ppd.horizontalMargin = horizontalMargin;
        ppd.verticalMargin = verticalMargin;
        return ppd;
    }

    def KPointPlacementData setMinimalSize(KPointPlacementData ppd,
        float minWidth, float minHeight) {

        ppd.minWidth = minWidth;
        ppd.minHeight = minHeight;
        return ppd;
    }

    def KRendering finish(KPointPlacementData ppd) {
        return ppd.eContainer as KRendering
    }

    def <T extends KRendering> T setPointPlacementData(T rendering,
        PositionReferenceX px, float absoluteLR, float relativeLR,
        PositionReferenceY py, float absoluteTB, float relativeTB,
        HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
        float horizontalMargin, float verticalMargin, float minWidth, float minHeight) {

        rendering.placementData = createKPointPlacementData => [
            it.referencePoint = createKPosition(px, absoluteLR, relativeLR, py, absoluteTB, relativeTB);
            it.horizontalAlignment = horizontalAlignment;
            it.verticalAlignment = verticalAlignment;
            it.horizontalMargin = horizontalMargin;
            it.verticalMargin = verticalMargin;
            it.minWidth = minWidth;
            it.minHeight = minHeight;
        ];
        return rendering;
    }

    def <T extends KRendering> T setPointPlacementData(T rendering, KPosition referencePoint,
        HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
        float horizontalMargin, float verticalMargin, float minWidth, float minHeight) {

        rendering.placementData = createKPointPlacementData => [
            it.referencePoint = referencePoint;
            it.horizontalAlignment = horizontalAlignment;
            it.verticalAlignment = verticalAlignment;
            it.horizontalMargin = horizontalMargin;
            it.verticalMargin = verticalMargin;
            it.minWidth = minWidth;
            it.minHeight = minHeight;
        ];
        return rendering;
    }

    def <T extends KRendering> T setDecoratorPlacementData(T rendering, float width,
            float height, float posAbsolute, float posRelative, boolean rotateWithLine) {

        rendering.placementData = createKDecoratorPlacementData() => [
            it.width = width;
            it.height = height;
            it.absolute = posAbsolute;
            it.relative = posRelative;
            it.rotateWithLine = rotateWithLine;
            it.XOffset = -width/2;
            it.YOffset = -height/2;
        ];
        return rendering;
    }
    
    public val PositionReferenceX LEFT = PositionReferenceX::LEFT;
    public val PositionReferenceX RIGHT = PositionReferenceX::RIGHT;
    
    public val PositionReferenceY TOP = PositionReferenceY::TOP;
    public val PositionReferenceY BOTTOM = PositionReferenceY::BOTTOM;
    
    def KPosition createKPosition(PositionReferenceX px, float absoluteLR, float relativeLR,
                                  PositionReferenceY py, float absoluteTB, float relativeTB) {
        return createKPosition => [
            it.x = switch px {
                case PositionReferenceX::LEFT: createKLeftPosition
                case PositionReferenceX::RIGHT: createKRightPosition
            } => [
                it.absolute = absoluteLR;
                it.relative = relativeLR;
            ];
        
            it.y = switch py {
                case PositionReferenceY::TOP: createKTopPosition
                case PositionReferenceY::BOTTOM: createKBottomPosition
            } => [
                it.absolute = absoluteTB;
                it.relative = relativeTB;            
            ];
        ];
    }
}
