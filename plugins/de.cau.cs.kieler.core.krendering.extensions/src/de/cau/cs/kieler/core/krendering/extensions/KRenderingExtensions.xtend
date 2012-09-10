package de.cau.cs.kieler.core.krendering.extensions

import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.HorizontalAlignment
import de.cau.cs.kieler.core.krendering.KBackgroundColor
import de.cau.cs.kieler.core.krendering.KBackgroundVisibility
import de.cau.cs.kieler.core.krendering.KFontBold
import de.cau.cs.kieler.core.krendering.KFontItalic
import de.cau.cs.kieler.core.krendering.KFontSize
import de.cau.cs.kieler.core.krendering.KForegroundColor
import de.cau.cs.kieler.core.krendering.KForegroundVisibility
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

    def KLineWidth getLineWidth(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
        return rendering.styles.filter(typeof(KLineWidth)).last?:renderingFactory.createKLineWidth => [
            lineWidth = 1
        ];
    }
 
    def KForegroundColor getFGColor(KRendering rendering) {
        // chsch: I'm currently not sure whether the first or the last will win...
        return rendering.styles.filter(typeof(KForegroundColor)).last?:renderingFactory.createKForegroundColor;
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
 
	def setLineWidth(KRendering rendering, int with){
		return renderingFactory.createKLineWidth => [
            it.setLineWidth(with);
		    rendering.styles.removeAll(rendering.styles.filter(typeof(KLineWidth)).toList);
		    rendering.styles.add(it);
		];
	}
	
    def void setBackgroundColor(KRendering rendering, KColor color){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KBackgroundColor)).toList);        
        rendering.styles += renderingFactory.createKBackgroundColor => [
            it.red = color.red;
            it.green = color.green;
            it.blue = color.blue;
        ];
    }
    
	def setBackgroundColor(KRendering rendering, int red, int green, int blue){
		return renderingFactory.createKBackgroundColor => [
		    it.setRed(red);
            it.setGreen(green);
            it.setBlue(blue);
		    rendering.styles.removeAll(rendering.styles.filter(typeof(KBackgroundColor)).toList);
            rendering.styles += it;
		];		
	}
    
    def void setForegroundColor(KRendering rendering, KColor color){
        rendering.styles.removeAll(rendering.styles.filter(typeof(KForegroundColor)).toList);        
        rendering.styles += renderingFactory.createKForegroundColor => [
            it.red = color.red;
            it.green = color.green;
            it.blue = color.blue;
        ];
    }
	
	def setForegroundColor(KRendering rendering, int red, int green, int blue){
        return renderingFactory.createKForegroundColor => [
            it.setRed(red);
            it.setGreen(green);
            it.setBlue(blue);
            rendering.styles.removeAll(rendering.styles.filter(typeof(KForegroundColor)).toList);
            rendering.styles += it;
        ];      
	}
	
	def setBackgroundVisibility(KRendering rendering, boolean visible){
		return renderingFactory.createKBackgroundVisibility => [
		    it.setVisible(visible);
		    rendering.styles.removeAll(rendering.styles.filter(typeof(KBackgroundVisibility)).toList);
		    rendering.styles.add(it);
		];
	}
		
	
	def setForegroundVisibility(KRendering rendering, boolean visible){
        return renderingFactory.createKForegroundVisibility => [
            it.setVisible(visible);
            rendering.styles.removeAll(rendering.styles.filter(typeof(KForegroundVisibility)).toList);
            rendering.styles.add(it);
        ];
	}
	
	def setFontBold(KRendering rendering, boolean bold){
		return renderingFactory.createKFontBold => [
		    it.setBold(bold);
            rendering.styles.removeAll(rendering.styles.filter(typeof(KFontBold)).toList);
		    rendering.styles.add(it);
		];		
	}
	
	def setFontItalic(KRendering rendering, boolean italic){
        return renderingFactory.createKFontItalic => [
		    it.setItalic(italic);
            rendering.styles.removeAll(rendering.styles.filter(typeof(KFontItalic)).toList);
            rendering.styles.add(it);            
        ];
	}
	
	def setFontSize(KRendering rendering, int size){
		return renderingFactory.createKFontSize => [
            it.setSize(size);
            rendering.styles.removeAll(rendering.styles.filter(typeof(KFontSize)).toList);
            rendering.styles.add(it);
		]		
		
	}
	
	def setHorizontalAlignment(KRendering rendering, HorizontalAlignment ha){
		return renderingFactory.createKHorizontalAlignment => [
            it.setHorizontalAlignment(ha);
            rendering.styles.removeAll(rendering.styles.filter(typeof(KHorizontalAlignment)).toList);
            rendering.styles.add(it);
		];		
	}
	
	def setVerticalAlignment(KRendering rendering, VerticalAlignment va){
		return renderingFactory.createKVerticalAlignment => [
            it.setVerticalAlignment(va);
            rendering.styles.removeAll(rendering.styles.filter(typeof(KVerticalAlignment)).toList);
            rendering.styles.add(it);		
		];
	}
	
	
	def KRendering setStackPlacementData(KRendering rendering, float insetRight,
            float insetBottom, float insetLeft, float insetTop) {
		return rendering => [
		    rendering.placementData = renderingFactory.createKStackPlacementData => [
                it.insetRight = insetRight;
                it.insetBottom = insetBottom;
                it.insetLeft = insetLeft;
                it.insetTop = insetTop;
                rendering.setPlacementData(it);            
           ]; 
		];		
	}
	
	def KRendering setGridPlacementData(KRendering rendering, float widthHint,
	        float heightHint, float insetLeft, float insetRight, float insetTop, float insetBottom) {
		return rendering => [
		    rendering.placementData = renderingFactory.createKGridPlacementData => [
                it.setWidthHint(widthHint);
                it.setHeightHint(heightHint);
                it.setInsetRight(insetRight);
                it.setInsetLeft(insetLeft);
                it.setInsetTop(insetTop);
                it.setInsetBottom(insetBottom);
    		];
		];
	}
	
	def KRendering setDirectPlacementData(KRendering rendering, KPosition topLeft, KPosition bottomRight){
        return rendering => [
            rendering.placementData = renderingFactory.createKDirectPlacementData => [
                it.setTopLeft(topLeft);
                it.setBottomRight(bottomRight);
            ];
        ];
	}
	
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