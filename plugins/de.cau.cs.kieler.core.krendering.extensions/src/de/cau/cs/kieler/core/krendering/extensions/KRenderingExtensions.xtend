package de.cau.cs.kieler.core.krendering.extensions

import de.cau.cs.kieler.core.kgraph.KGraphElement
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.HorizontalAlignment
import de.cau.cs.kieler.core.krendering.KFontBold
import de.cau.cs.kieler.core.krendering.KFontItalic
import de.cau.cs.kieler.core.krendering.KFontSize
import de.cau.cs.kieler.core.krendering.KHorizontalAlignment
import de.cau.cs.kieler.core.krendering.KLineWidth
import de.cau.cs.kieler.core.krendering.KPosition
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.KVerticalAlignment
import de.cau.cs.kieler.core.krendering.VerticalAlignment
import de.cau.cs.kieler.core.krendering.KStyle
import de.cau.cs.kieler.core.krendering.KPlacementData
import de.cau.cs.kieler.core.krendering.KRoundedRectangle
import de.cau.cs.kieler.core.krendering.LineStyle
import de.cau.cs.kieler.core.krendering.KLineStyle
import de.cau.cs.kieler.core.krendering.KFontName
import de.cau.cs.kieler.core.krendering.KRotation
import de.cau.cs.kieler.core.krendering.KForeground
import de.cau.cs.kieler.core.krendering.KBackground
import de.cau.cs.kieler.core.krendering.KColoring
import de.cau.cs.kieler.core.krendering.KColor
import de.cau.cs.kieler.core.krendering.KInvisibility
import de.cau.cs.kieler.core.krendering.KLineCap
import de.cau.cs.kieler.core.krendering.LineCap

import static extension org.eclipse.emf.ecore.util.EcoreUtil.*
import de.cau.cs.kieler.core.krendering.KStyleRef
import de.cau.cs.kieler.core.krendering.KStyleHolder
import de.cau.cs.kieler.core.krendering.KAreaPlacementData
import de.cau.cs.kieler.core.krendering.KGridPlacementData
import de.cau.cs.kieler.core.krendering.Underline
import de.cau.cs.kieler.core.krendering.KTextUnderline
import de.cau.cs.kieler.core.krendering.KTextStrikeout

/**
 * This utility class contains various methods that are convenient while composing KRendering data.
 * It does not claim to be complete ;-).
 */
class KRenderingExtensions {

    private static val KRenderingFactory renderingFactory = KRenderingFactory::eINSTANCE

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////					KRenderingExtensions
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Reveals the KRendering of a KGraphElement, e.g. for the purpose of highlighting. 
     */
    def KRendering getKRendering(KGraphElement kge) {
        return kge.getData(typeof(KRendering));
    }

    def KRoundedRectangle addRoundedRectangle(KNode node, float cWidth, float cHeight, float lineWidth) {
        return renderingFactory.createKRoundedRectangle => [
            it.cornerWidth = cWidth;
            it.cornerHeight = cHeight;
            it.setLineWidth(lineWidth);
            node.data.add(it)
        ];
    }
    
    def KRoundedRectangle setCornerSize(KRoundedRectangle rect, float cWidth, float cHeight) {
        return rect => [
            it.cornerWidth = cWidth;
            it.cornerHeight = cHeight;
        ]
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
 
    def <T extends KRendering> T addStyleRef(T rendering, KStyleHolder styleHolder) {
        return rendering => [
            it.styles += renderingFactory.createKStyleRef() => [
                it.styleHolder = styleHolder;
            ];
        ];
    }
 
    def KInvisibility getInvisible(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
        return rendering.styles.filter(typeof(KInvisibility)).last?:(renderingFactory.createKInvisibility());
    }
 
    def boolean getBooleanInvisible(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
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
    
    def KLineWidth getLineWidth(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
        return rendering.styles.filter(typeof(KLineWidth)).last?:(renderingFactory.createKLineWidth => [
            lineWidth = 1f
        ]);
    }
 
    def float getLineWidthValue(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
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
    
    def KLineStyle getLineStyle(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
        return rendering.styles.filter(typeof(KLineStyle)).last?:(renderingFactory.createKLineStyle => [
            lineStyle = LineStyle::SOLID;
        ]);
    }
 
    def LineStyle getEnumLineStyle(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
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
    
    def KLineCap getLineCap(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
        return rendering.styles.filter(typeof(KLineCap)).last?:(renderingFactory.createKLineCap => [
            lineCap = LineCap::CAP_FLAT;
        ]);
    }
 
    def LineCap getEnumLineCap(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
        return (rendering.styles.filter(typeof(KLineCap)).last?:(renderingFactory.createKLineCap => [
            lineCap = LineCap::CAP_FLAT;
        ])).lineCap;
    }
 
    def <T extends KRendering> T setLineCap(T rendering, LineCap style) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KLineCap)).toList);
        return rendering => [
            it.styles += renderingFactory.createKLineCap => [
                it.setLineCap(style);
            ];
        ];
    }
    
    def KRotation getRotation(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
        return rendering.styles.filter(typeof(KRotation)).last?:renderingFactory.createKRotation;
    }
 
    def float getFloatRotation(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
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
    
    def KBackground getBackground(KRendering rendering){
        return rendering.styles?.filter(typeof(KBackground)).last?:(renderingFactory.createKBackground() => [
            it.color = renderingFactory.createKColor();
        ]);
    }
    
    def <T extends KRendering>  T setBackground(T rendering, KColoring coloring){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);
        return rendering => [
            it.styles += renderingFactory.createKBackground => [
                it.alpha = coloring.alpha;
                it.color = coloring.color.copy;
                it.targetAlpha = coloring.targetAlpha;
                it.targetColor = coloring.targetColor.copy;
                it.gradientAngle = coloring.gradientAngle;
                it.functionId = coloring.functionId;
                it.propagateToChildren = coloring.propagateToChildren;
            ];
        ];
    }
    
    def <T extends KRendering>  T setBackground(T rendering, KColor color){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackground)).toList);
        return rendering => [
            it.styles += renderingFactory.createKBackground => [
                it.color = color;
            ];
        ];
    }
    
    def <T extends KRendering>  T setBackgroundInvisible(T rendering, boolean invisible){
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
    
    def <T extends KRendering>  T setBackgroundColor(T rendering, int red, int green, int blue){
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
                it.functionId = coloring.functionId;
                it.propagateToChildren = coloring.propagateToChildren;
            ];
        ];
    }
    
    def <T extends KRendering>  T setForeground(T rendering, KColor color){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForeground)).toList);
        return rendering => [
            it.styles += renderingFactory.createKForeground() => [
                it.color = color;
            ];
        ];
    }
    
    def <T extends KRendering>  T setForegroundInvisible(T rendering, boolean invisible){
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
//TODO: maybe add setters/getters for single components of KForeground/KBackground or simply a method 
//that allows sticking additional Foreground/Background information to the list without removing 
//already defined styles first        
	

	def <T extends KRendering> T setFontBold(T rendering, boolean bold) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KFontBold)).toList);
		return rendering => [
            it.styles += renderingFactory.createKFontBold => [
    		    it.setBold(bold);
		    ];
		];		
	}
	
    def <T extends KRendering> T setFontItalic(T rendering, boolean italic) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KFontItalic)).toList);
        return rendering => [
            it.styles += renderingFactory.createKFontItalic => [
                it.setItalic(italic);
            ];
        ];
    }
    
    def <T extends KRendering> T setTextUnderline(T rendering, Underline underline) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KTextUnderline)).toList);
        return rendering => [
            it.styles += renderingFactory.createKTextUnderline() => [
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
    
    def <T extends KRendering> T setTextStrikeout(T rendering, boolean struckOut) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KTextStrikeout)).toList);
        return rendering => [
            it.styles += renderingFactory.createKTextStrikeout() => [
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
    
    def KFontSize getFontSize(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
        return rendering.styles.filter(typeof(KFontSize)).last?:(renderingFactory.createKFontSize => [
            size = 10
        ]);
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
        // chsch: I'm currently not sure whether the first or the last will win...
        return rendering.styles.filter(typeof(KFontName)).last?:(renderingFactory.createKFontName => [
            name = "Arial"
        ]);
    }
 
    def <T extends KRendering> T setFontName(T rendering, String name) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KFontName)).toList);
        return rendering => [
            it.styles += renderingFactory.createKFontName => [
                it.setName(name);
            ];
        ];      
    }
    
    public val HorizontalAlignment H_LEFT = HorizontalAlignment::LEFT; 
    public val HorizontalAlignment H_CENTRAL = HorizontalAlignment::CENTER; 
    public val HorizontalAlignment H_RIGHT = HorizontalAlignment::RIGHT; 
    
    def KHorizontalAlignment getHorizontalAlignment(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
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
        // chsch: I'm currently not sure whether the first or the last will win...
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

    def KGridPlacementData setGridPlacementData(KRendering rendering) {
        return renderingFactory.createKGridPlacementData() => [
            rendering.placementData = it;
        ];
    }
    
    def KGridPlacementData setMaxCellHeight(KGridPlacementData placementData, float maxCellHeight) {
        return placementData => [
            placementData.maxCellHeight = maxCellHeight;
        ];
    }

    def <T extends KRendering> T setPointPlacementData(T rendering, KPosition referencePoint,
        HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
        float horizontalMargin, float verticalMargin) {
        return rendering => [
            rendering.placementData = renderingFactory.createKPointPlacementData => [
                it.referencePoint = referencePoint;
                it.horizontalAlignment = horizontalAlignment;
                it.verticalAlignment = verticalAlignment;
                it.horizontalMargin = horizontalMargin;
                it.verticalMargin = verticalMargin;
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
