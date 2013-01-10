package de.cau.cs.kieler.core.krendering.extensions

import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.HorizontalAlignment
import de.cau.cs.kieler.core.krendering.KBackgroundColor
import de.cau.cs.kieler.core.krendering.KBackgroundAlpha
import de.cau.cs.kieler.core.krendering.KFontBold
import de.cau.cs.kieler.core.krendering.KFontItalic
import de.cau.cs.kieler.core.krendering.KFontSize
import de.cau.cs.kieler.core.krendering.KForegroundColor
import de.cau.cs.kieler.core.krendering.KForegroundAlpha
import de.cau.cs.kieler.core.krendering.KHorizontalAlignment
import de.cau.cs.kieler.core.krendering.KLineWidth
import de.cau.cs.kieler.core.krendering.KPosition
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.KVerticalAlignment
import de.cau.cs.kieler.core.krendering.VerticalAlignment
import de.cau.cs.kieler.core.krendering.KContainerRendering
import de.cau.cs.kieler.core.krendering.KStyle
import de.cau.cs.kieler.core.krendering.KPlacementData
import de.cau.cs.kieler.core.krendering.KColor
import de.cau.cs.kieler.core.krendering.KRoundedRectangle
import org.eclipse.emf.ecore.util.EcoreUtil
import de.cau.cs.kieler.core.kgraph.KGraphElement
import de.cau.cs.kieler.core.krendering.LineStyle
import de.cau.cs.kieler.core.krendering.KLineStyle
import de.cau.cs.kieler.core.krendering.KFontName
import de.cau.cs.kieler.core.krendering.KRotation

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

    /**
     * @returns the child! 
     */
    def <T extends KRendering> KRendering addChild(KContainerRendering parent, T child) {
        return child => [
            parent.children.add(it);
        ];
    }
    
    def KRoundedRectangle addRoundedRectangle(KNode node, float cWidth, float cHeight, int lineWidth){
        return renderingFactory.createKRoundedRectangle => [
            it.cornerWidth = cWidth;
            it.cornerHeight = cHeight;
            it.setLineWidth(lineWidth);
            node.data.add(it)
        ];
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
            it.styles += EcoreUtil::copy(style);
        ];
    }
 
    def KLineWidth getLineWidth(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
        return rendering.styles.filter(typeof(KLineWidth)).last?:(renderingFactory.createKLineWidth => [
            lineWidth = 1
        ]);
    }
 
    def <T extends KRendering> T setLineWidth(T rendering, int with) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KLineWidth)).toList);
        return rendering => [
            it.styles += renderingFactory.createKLineWidth() => [
                it.setLineWidth(with);
            ]
        ];
    }
    
    def KLineStyle getLineStyle(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
        return rendering.styles.filter(typeof(KLineStyle)).last?:(renderingFactory.createKLineStyle => [
            lineStyle = LineStyle::SOLID;
        ]);
    }
 
    def <T extends KRendering> T setLineStyle(T rendering, LineStyle style) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KLineStyle)).toList);
        return rendering => [
            it.styles += renderingFactory.createKLineStyle => [
                it.setLineStyle(style);
            ];
        ];
    }
    
    def KRotation getRotation(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
        return rendering.styles.filter(typeof(KRotation)).last?:renderingFactory.createKRotation;
    }
 
    def <T extends KRendering> T setRotation(T rendering, Float rotation) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KRotation)).toList);
        return rendering => [
            it.styles += renderingFactory.createKRotation => [
                it.setRotation(rotation);
            ];
        ];
    }
    
    def KBackgroundColor getBGColor(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
        return rendering.styles.filter(typeof(KBackgroundColor)).last?:renderingFactory.createKBackgroundColor;
    }
 
    def KBackgroundColor getBackgroundColor(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
        return rendering.styles.filter(typeof(KBackgroundColor)).last?:renderingFactory.createKBackgroundColor;
    }
 
    def <T extends KRendering> T setBackgroundColor(T rendering, KColor color) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackgroundColor)).toList);        
        return rendering => [
            it.styles += renderingFactory.createKBackgroundColor => [
                it.red = color.red;
                it.green = color.green;
                it.blue = color.blue;
            ];
        ];
    }
    
	def <T extends KRendering> T setBackgroundColor(T rendering, int red, int green, int blue) {
		rendering.styles.removeAll(rendering.styles.filter(typeof(KBackgroundColor)).toList);
		return rendering => [
		    it.styles += renderingFactory.createKBackgroundColor => [
    		    it.setRed(red);
                it.setGreen(green);
                it.setBlue(blue);
		    ]; 
		];		
	}
        
    def KForegroundColor getFGColor(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
        return rendering.styles.filter(typeof(KForegroundColor)).last?:renderingFactory.createKForegroundColor;
    }

    def KForegroundColor getForegroundColor(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
        return rendering.styles.filter(typeof(KForegroundColor)).last?:renderingFactory.createKForegroundColor;
    }
 
    def <T extends KRendering> T setForegroundColor(T rendering, KColor color) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForegroundColor)).toList);        
        return rendering => [
            it.styles += renderingFactory.createKForegroundColor => [
                it.red = color.red;
                it.green = color.green;
                it.blue = color.blue;
            ]; 
        ];      
    }
	
	def <T extends KRendering> T setForegroundColor(T rendering, int red, int green, int blue) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForegroundColor)).toList);        
        return rendering => [
            it.styles += renderingFactory.createKForegroundColor => [
                it.setRed(red);
                it.setGreen(green);
                it.setBlue(blue);
            ]; 
        ];      
	}
	
	def <T extends KRendering> T setBackgroundAlpha(T rendering, float alphaValue) {
		rendering.styles.removeAll(rendering.styles.filter(typeof(KBackgroundAlpha)).toList);
		return rendering => [
            it.styles += renderingFactory.createKBackgroundAlpha => [
		        it.setAlpha(alphaValue);
		    ];
		];
	}
		
	
	def <T extends KRendering> T setForegroundAlpha(T rendering, float alphaValue) {
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForegroundAlpha)).toList);
        return rendering => [
            it.styles += renderingFactory.createKForegroundAlpha => [
                it.setAlpha(alphaValue);
            ];
        ];
    }
    
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
	
	
	def <T extends KRendering> T setGridPlacementData(T rendering, float minCellWidth,
	        float minCellHeight, KPosition topLeft, KPosition bottomRight) {
		return rendering => [
		    rendering.placementData = renderingFactory.createKGridPlacementData => [
                it.setMinCellWidth(minCellWidth);
                it.setMinCellHeight(minCellHeight);
                it.setTopLeft(topLeft);
                it.setBottomRight(bottomRight);
            ];
		];
	}
	
	def <T extends KRendering> T setDirectPlacementData(T rendering, KPosition topLeft, KPosition bottomRight){
        return rendering => [
            rendering.placementData = renderingFactory.createKAreaPlacementData => [
                it.setTopLeft(topLeft);
                it.setBottomRight(bottomRight);
            ];
        ];
	}
	
    public val PositionReferenceX LEFT = PositionReferenceX::LEFT;
    public val PositionReferenceX RIGHT = PositionReferenceX::RIGHT;
    
    public val PositionReferenceY TOP = PositionReferenceY::TOP;
    public val PositionReferenceY BOTTOM = PositionReferenceY::BOTTOM;
    
    def KPosition createKPosition(PositionReferenceX px, float absoluteLR, float relativeLR,
                                  PositionReferenceY py, float absoluteTB, float relativeTB){
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
