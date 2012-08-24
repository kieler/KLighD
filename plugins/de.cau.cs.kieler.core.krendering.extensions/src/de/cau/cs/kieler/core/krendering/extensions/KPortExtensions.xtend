package de.cau.cs.kieler.core.krendering.extensions

import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.kiml.util.KimlUtil
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.util.Maybe
import javax.inject.Inject
import de.cau.cs.kieler.core.krendering.VerticalAlignment
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.KFontSize
import de.cau.cs.kieler.core.krendering.HorizontalAlignment
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.PortSide
import de.cau.cs.kieler.core.properties.IProperty
import java.util.ArrayList

/**
 * @author chsch
 */
class KPortExtensions {
	
    /**
     * A constant determining the vertical margin between the top
     * of a node and the first port on the left or right.
     */
    private static val Float PORT_TOP_OFFSET = 30f;

    /**
     * A constant determining the vertical margin between two ports
     * of a node on the left or right.
     */
    private static val Float VERTICAL_PORT_SPACING = 15f;
    
    /**
     * A constant determining the edge length of the ports' figures that are formed
     * by a black square (if they are created by the extensions of this class).
     */
    private static val Float PORT_FIGURE_EDGE_LENGTH = 7f;

    /**
     * A constant determining the font size of the ports' text labels
     * (if they are created by the extensions of this class).
     */
    private static val Integer PORT_LABEL_FONT_SIZE = 7;

    private static val KRenderingFactory renderingFactory = KRenderingFactory::eINSTANCE
    
    @Inject
    extension KRenderingExtensions;
    
    @Inject
    extension KNodeExtensions;
    
    /**
     * A convenient getter preserving the element image relation by a create extension.
     */ 
    def private KPort create port: KimlUtil::createInitializedPort internalCreatePort(ArrayList<Object> oc) {
    }
    
    /**
     * A convenient port getter based on a single business object preserving the
     * element image relation by a create extension.
     */
    def KPort getPort(Object o1) {
        return newArrayList(o1).internalCreatePort()
    }
    
    /**
     * A convenient port getter based on a two business objects preserving the
     * element image relation by a create extension.
     */
    def KPort getPort(Object o1, Object o2) {
        return newArrayList(o1, o2).internalCreatePort()
    }
    
    /**
     * A convenient port getter based on a three business objects preserving the
     * element image relation by a create extension.
     */
    def KPort getPort(Object o1, Object o2, Object o3) {
        return newArrayList(o1, o2, o3).internalCreatePort()
    }
    
    /**
     * A convenient port getter based on a four business objects preserving the
     * element image relation by a create extension.
     */
    def KPort getPort(Object o1, Object o2, Object o3, Object o4) {
        return newArrayList(o1, o2, o3, o4).internalCreatePort()
    }
    
    /**
     * An alias of {@link #getPort(Object o1)} allowing to express in business that the KPort will
     * be created at this place. It is just syntactic sugar.  
     */
    def KPort createPort(Object o1) {
        return o1.getPort()
    }
    
    /**
     * An alias of {@link #getPort(Object o1, Object o2)} allowing to express in business that the
     * KPort will be created at this place. It is just syntactic sugar.  
     */
    def KPort createPort(Object o1, Object o2) {
        return o1.getPort(o2);
    }
    
    /**
     * An alias of {@link #getPort(Object o1, Object o2, Object o3)} allowing to express in business
     * that the KPort will be created at this place. It is just syntactic sugar.  
     */
    def KPort createPort(Object o1, Object o2, Object o3) {
        return o1.getPort(o2, o3);
    }
    
    /**
     * An alias of {@link #getPort(Object o1, Object o2, Object o3, Object o4)} allowing to express in
     * business that the KPort will be created at this place. It is just syntactic sugar.  
     */
    def KPort createPort(Object o1, Object o2, Object o3, Object o4) {
        return o1.getPort(o2, o3, o4);
    }
    
    /**
     * Creates a port and a related port figure as well as a port label
     *  onto the east side of a given node with the port label text 'label'.
     *  The port is related to the given EObjects.
     * 
     * The create nature of these extensions avoids the relocation the an
     *  already created port due to the recall of the initializer extension.
     */
    def KPort createLabeledEPort(KNode node, String label, Object o1) {
        return node.createLabeledEPort(label, newArrayList(o1));
    }
    def KPort createLabeledEPort(KNode node, String label, Object o1, Object o2) {
        return node.createLabeledEPort(label, newArrayList(o1, o2));
    }
    def KPort createLabeledEPort(KNode node, String label, Object o1, Object o2, Object o3) {
        return node.createLabeledEPort(label, newArrayList(o1, o2, o3));
    }
    def KPort createLabeledEPort(KNode node, String label, Object o1, Object o2, Object o3, Object o4) {
        return node.createLabeledEPort(label, newArrayList(o1, o2, o3, o4));
    }


    /**
     * Creates an anchor and a related port figure as well as a port label
     *  onto the west side of a given shape with the port label text 'label'.
     *  The anchor is related to the given EObjects.
     * 
     * The create nature of these extensions avoids the relocation the an
     *  already created port due to the recall of the initializer extension.
     */
    def KPort createLabeledWPort(KNode node, String label, Object o1) {
        return node.createLabeledWPort(label, newArrayList(o1)) 
    }
    def KPort createLabeledWPort(KNode node, String label, Object o1, Object o2) {
        return node.createLabeledWPort(label, newArrayList(o1, o2));
    }
    def KPort createLabeledWPort(KNode node, String label, Object o1, Object o2, Object o3) {
        return node.createLabeledWPort(label, newArrayList(o1, o2, o3));
    }
    def KPort createLabeledWPort(KNode node, String label, Object o1, Object o2, Object o3, Object o4) {
        return node.createLabeledWPort(label, newArrayList(o1, o2, o3, o4));
    }


    def private KPort createLabeledEPort(KNode node, String label, ArrayList<Object> os) {
        return os.internalCreatePort() => [
            node.ports += it;
            it.setPortSize(portEdgeLength, portEdgeLength)
            it.addLayoutParam(LayoutOptions::PORT_SIDE, PortSide::EAST);
//            it.addLayoutParam(LayoutOptions::OFFSET, -207f);
            it.setPortPos(node.width-1, node.nextEPortYPosition);
            it.data += renderingFactory.createKRectangle => [
                it.setForegroundVisibility(true);
                it.setForegroundColor(0,0,0)
                it.setBackgroundVisibility(true)
                it.setBackgroundColor(0,0,0)
                it.children += renderingFactory.createKText.withCopyOf(portLabelFontSize()) => [
                    it.text = label;
                    it.setHorizontalAlignment(HorizontalAlignment::RIGHT)
                    it.setVerticalAlignment(VerticalAlignment::CENTER)
                    it.placementData = renderingFactory.createKDirectPlacementData() => [
                        topLeft = renderingFactory.createKPosition() => [
                            it.x = renderingFactory.createKLeftPosition() => [
                                it.absolute = -2
                            ];
                            it.y = renderingFactory.createKTopPosition() => [
                                it.absolute = portEdgeLength()/2;
                            ];
                        ];
                        bottomRight = renderingFactory.createKPosition() => [
                            it.x = renderingFactory.createKLeftPosition() => [
                                it.absolute = -2
                            ];
                            it.y = renderingFactory.createKTopPosition() => [
                                it.absolute = portEdgeLength()/2;
                            ];
                        ];
                    ];
                ];
            ];
        ];
    }

    def private KPort createLabeledWPort(KNode node, String label, ArrayList<Object> os) {
        return os.internalCreatePort() => [
            node.ports += it;
            it.setPortSize(portEdgeLength, portEdgeLength)
            it.addLayoutParam(LayoutOptions::PORT_SIDE, PortSide::WEST);
            it.addLayoutParam(LayoutOptions::OFFSET, 0f);
            it.setPortPos(-6, node.nextWPortYPosition);
            it.data += renderingFactory.createKRectangle => [
                it.setForegroundVisibility(true);
                it.setForegroundColor(0,0,0)
                it.setBackgroundVisibility(true)
                it.setBackgroundColor(0,0,0)
                it.children += renderingFactory.createKText.withCopyOf(portLabelFontSize()) => [
                    it.text = label;
                    it.setHorizontalAlignment(HorizontalAlignment::LEFT)
                    it.setVerticalAlignment(VerticalAlignment::CENTER)
                    it.placementData = renderingFactory.createKDirectPlacementData() => [
                        topLeft = renderingFactory.createKPosition() => [
                            it.x = renderingFactory.createKRightPosition() => [
                                it.absolute = -2
                            ];
                            it.y = renderingFactory.createKTopPosition() => [
                                it.absolute = portEdgeLength()/2;
                            ];
                        ]
                        bottomRight = renderingFactory.createKPosition() => [
                            it.x = renderingFactory.createKRightPosition() => [
                                it.absolute = -2
                            ];
                            it.y = renderingFactory.createKTopPosition() => [
                                it.absolute = portEdgeLength()/2;
                            ];
                        ];
                    ];
                ];
            ];
        ];
    }


    def KPort setPortSize(KPort port, float with, float height) {
        return port => [
            getData(typeof(KShapeLayout)).setSize(with, height)
        ];
    }
    
    def KPort setPortPos(KPort port, float x, float y) {
        return port => [
            getData(typeof(KShapeLayout)).setPos(x, y)
        ];
    }
    
    def KPort addLayoutParam(KPort port, IProperty<?> property, Object value) {
        return port => [
            it.getData(typeof(KShapeLayout)).setProperty(property, value)
        ];
    }
    
	def KShapeLayout getShapeLayout(KPort port){
		port.getData(typeof(KShapeLayout))
	}
	
    def KFontSize create it: renderingFactory.createKFontSize portLabelFontSize() {
        it.size = PORT_LABEL_FONT_SIZE;
    }

    def Maybe<Float> create it: Maybe::^create() portEdgeLengthMemo() {
        it.set(PORT_FIGURE_EDGE_LENGTH);
    }
    
    def Float portEdgeLength() {
        portEdgeLengthMemo.get();
    }
    
    def Maybe<Float> create it: Maybe::^create() portTopOffsetMemo() {
        it.set(PORT_TOP_OFFSET);
    }
    
    def Float portTopOffset() {
        portTopOffsetMemo.get();
    }
    
    def Maybe<Float> create it: Maybe::^create() verticalPortSpacingMemo() {
        it.set(VERTICAL_PORT_SPACING);
    }
    
    def Float verticalPortSpacing() {
        verticalPortSpacingMemo.get();
    }
    
    def private Maybe<Float> create it: Maybe::^create() => [it.set(portTopOffset)] ePortYPositionMemo(KNode node) {
    }
    def private Maybe<Float> create it: Maybe::^create() => [it.set(portTopOffset)] wPortYPositionMemo(KNode node) {
    }
    
    def Float nextEPortYPosition(KNode node) {
        val Maybe<Float> memo = ePortYPositionMemo(node);
        val f = memo.get();
        memo.set(f+15);
        return f;
    }
    def Float nextWPortYPosition(KNode node) {
        val Maybe<Float> memo = wPortYPositionMemo(node);
        val f = memo.get();
        memo.set(f+15);
        return f;
    }
    
}