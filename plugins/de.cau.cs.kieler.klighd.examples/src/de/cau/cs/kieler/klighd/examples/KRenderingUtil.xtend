package de.cau.cs.kieler.klighd.examples

import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.kgraph.KGraphElement
import de.cau.cs.kieler.core.krendering.KStyle
import de.cau.cs.kieler.core.krendering.KLineWidth
import de.cau.cs.kieler.core.krendering.KContainerRendering
import de.cau.cs.kieler.core.krendering.KText
import de.cau.cs.kieler.core.kgraph.KEdge
import de.cau.cs.kieler.core.krendering.KPolyline
import de.cau.cs.kieler.core.krendering.KPosition
import de.cau.cs.kieler.core.krendering.KXPosition
import de.cau.cs.kieler.core.krendering.KYPosition
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout
import com.google.inject.Inject
import de.cau.cs.kieler.core.krendering.KFontSize
import de.cau.cs.kieler.core.krendering.KFontBold

class KRenderingUtil {
	
	@Inject
	extension XtendArithmeticExtensions
	
	@Inject
	extension KRenderingColors
	
	def KRenderingFactory factory() {
		return KRenderingFactory::eINSTANCE;
	} 
	
	def KRendering getKRendering(KGraphElement kge) {
		return kge.getData(typeof(KRendering));
	}
	
	def KShapeLayout getKShapeLayout(KGraphElement kge) {
		return kge.getData(typeof(KShapeLayout));
	}
	
	def KEdgeLayout getKEdgeLayout(KGraphElement kge) {
		return kge.getData(typeof(KEdgeLayout));
	}
	
	def dispatch KRendering add(KContainerRendering r, KRendering cr) {
		r.children.add(cr);
		return r
	}
	
	def dispatch KRendering add(KRendering r, KStyle s) {
		r.styles.add(s);
		return r
	}
	
	def KText of(KText text, String content) {
		text.text = content;
		return text;
	}
	
	def KFontSize of(KFontSize style, int size) {
		style.size = size;
		return style;
	}
	
	def KFontBold setbold(KFontBold style) {
		style.bold = true;
		return style;
	}
	
	def KLineWidth of(KLineWidth style, int width) {
		style.lineWidth = width;
		return style;
	}
	
	def KNode create node: KimlUtil::createInitializedNode getNode(Object o) {
	}
	
	def KNode createRectangulareNode(Object o) {
		val node = o.node;
		val rect = KRenderingFactory::eINSTANCE.createKRectangle;
		node.data.add(rect);
		return node;
	}
	
	def KNode createRectangulareNode(Object o, int height, int width) {
		val node = o.createRectangulareNode;
		val shapeLayout = node.getData(typeof(KShapeLayout));
		shapeLayout.height = height;
		shapeLayout.width = width;
		return node;
	}
	
	def KNode createRoundedRectangulareNode(Object o) {
		val node = o.node;
		val rect = KRenderingFactory::eINSTANCE.createKRoundedRectangle;
		rect.setCornerHeight(10);
		rect.setCornerWidth(10);
		node.data.add(rect);
		return node;
	}
	
	def KNode createRoundedRectangulareNode(Object o, int height, int width) {
		val node = o.createRoundedRectangulareNode;
		val shapeLayout = node.getData(typeof(KShapeLayout));
		shapeLayout.height = height;
		shapeLayout.width = width;
		return node;
	}
	
	def KEdge create node: KimlUtil::createInitializedEdge getEdge(Object o) {
	}
	
	def KEdge createPolyLineEdge(Object o) {
		val edge = o.edge;
		edge.data.add(factory.createKPolyline);
		return edge;
	}
	
    def KPolyline addConnectionArrow(KPolyline line, int scale, boolean toHead) {
		val float actualScale = Math::sqrt(3*scale).floatValue;
        val dpd = factory.createKDecoratorPlacementData;
        dpd.height = 10 * actualScale;
        dpd.width = 15 * actualScale;
        dpd.XOffset = -15 * actualScale;
        dpd.YOffset = (dpd.height+scale.float/2.float) / (-2).float;
        dpd.relative = true; // this directs klighd to rotate the decorator accordingly!!
        dpd.location = if (toHead) "1.0".float else 0;
        
        val plp = factory.createKPolylinePlacementData;
		plp.detailPlacementData = dpd;        
        if (toHead) {
            plp.points.addAll(newArrayList(
        	    createPoint(createLeftPos(0,0), createTopPos(0,0)),
        	    createPoint(createRightPos(0,0), createTopPos(0.float,"0.5".float)),
        	    createPoint(createLeftPos(0,0), createBottomPos(0,0))
            ));
        } else {
            plp.points.addAll(newArrayList(
        	    createPoint(createRightPos(0,0), createTopPos(0,0)),
        	    createPoint(createLeftPos(0,0), createTopPos(0.float,"0.5".float)),
        	    createPoint(createRightPos(0,0), createBottomPos(0,0))
            ));
        }

        val linewidth = factory.createKLineWidth;
        linewidth.lineWidth = line.styles.filter(typeof(KLineWidth)).last?.lineWidth;
        
        val arrow =  factory.createKPolyline;
        arrow.add(linewidth);
        arrow.placementData = plp;

        return line.add(arrow) as KPolyline;
    }
    
    def KPolyline addInheritanceConnectionArrow(KPolyline line, int scale, boolean toHead) {
		val float actualScale = Math::sqrt(3*scale).floatValue;
        val dpd = factory.createKDecoratorPlacementData;
        dpd.height = 15 * actualScale;
        dpd.width = 15 * actualScale;
        dpd.XOffset = -15 * actualScale;
        dpd.YOffset = (dpd.height+scale.float/2.float) / (-2).float;
        dpd.relative = true; // this directs klighd to rotate the decorator accordingly!!
        dpd.location = if (toHead) "1.0".float else 0;

        val plp = factory.createKPolylinePlacementData;
		plp.detailPlacementData = dpd;        
        if (toHead) {
            plp.points.addAll(newArrayList(
        	    createPoint(createLeftPos(0,0), createTopPos(0,0)),
        	    createPoint(createRightPos(0,0), createTopPos(0.float,"0.5".float)),
        	    createPoint(createLeftPos(0,0), createBottomPos(0,0))
            ));
        } else {
            plp.points.addAll(newArrayList(
        	    createPoint(createRightPos(0,0), createTopPos(0,0)),
        	    createPoint(createLeftPos(0,0), createTopPos(0.float,"0.5".float)),
        	    createPoint(createRightPos(0,0), createBottomPos(0,0))
            ));
        }

        val linewidth = factory.createKLineWidth.of(line.styles.filter(typeof(KLineWidth)).last?.lineWidth); 
        val arrow =  factory.createKPolygon;
        arrow.add(linewidth).add("white".bgColor);
        arrow.placementData = plp;
        
        return line.add(arrow) as KPolyline;
    }
    
    def KPosition createPoint(KXPosition x, KYPosition y) {
    	val pos = factory.createKPosition;
    	pos.x = x;
    	pos.y = y;
    	return pos;
    }
    
    def KXPosition createLeftPos(Integer abs, Integer rel) {
    	return createLeftPos(abs.float, rel.float);
	}

    def KXPosition createLeftPos(Float abs, Float rel) {
    	val pos = factory.createKLeftPosition;
    	pos.absolute = abs;
    	pos.relative = rel;
    	return pos;
    }
    
    def KXPosition createRightPos(Integer abs, Integer rel) {
    	createRightPos(abs.float, rel.float);
    }
    def KXPosition createRightPos(Float abs, Float rel) {
    	val pos = factory.createKRightPosition;
    	pos.absolute = abs;
    	pos.relative = rel;
    	return pos;
    }

    def KYPosition createBottomPos(Integer abs, Integer rel) {
    	return createBottomPos(abs.getFloat, rel.getFloat);
    }
    def KYPosition createBottomPos(Float abs, Float rel) {
    	val pos = factory.createKBottomPosition;
    	pos.absolute = abs;
    	pos.relative = rel;
    	return pos;
    }

    def KYPosition createTopPos(Integer abs, Integer rel) {
    	return createTopPos(abs.getFloat, rel.getFloat);
    }
    def KYPosition createTopPos(Float abs, Float rel) {
    	val pos = factory.createKTopPosition;
    	pos.absolute = abs;
    	pos.relative = rel;
    	return pos;
    }
    
    def float getFloat(String s) {
    	return Float::valueOf(s);
    }

    def float getFloat(Integer i) {
    	return Float::valueOf(i);
    }
}