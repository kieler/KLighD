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
import de.cau.cs.kieler.core.krendering.KText
import de.cau.cs.kieler.core.krendering.KRendering

/**
 * @author chsch
 */
@ViewSynthesisShared
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
     *  onto the north side of a given shape with the port label text 'label'.
     *  The anchor is related to the given EObjects.
     * 
     * The create nature of these extensions avoids the relocation the an
     *  already created port due to the recall of the initializer extension.
     */
    def KPort createLabeledNPort(KNode node, String label, Object o1) {
        return node.createLabeledNPort(label, newArrayList(o1)) 
    }
    def KPort createLabeledNPort(KNode node, String label, Object o1, Object o2) {
        return node.createLabeledNPort(label, newArrayList(o1, o2));
    }
    def KPort createLabeledNPort(KNode node, String label, Object o1, Object o2, Object o3) {
        return node.createLabeledNPort(label, newArrayList(o1, o2, o3));
    }
    def KPort createLabeledNPort(KNode node, String label, Object o1, Object o2, Object o3, Object o4) {
        return node.createLabeledNPort(label, newArrayList(o1, o2, o3, o4));
    }


    /**
     * Creates an anchor and a related port figure as well as a port label
     *  onto the south side of a given shape with the port label text 'label'.
     *  The anchor is related to the given EObjects.
     * 
     * The create nature of these extensions avoids the relocation the an
     *  already created port due to the recall of the initializer extension.
     */
    def KPort createLabeledSPort(KNode node, String label, Object o1) {
        return node.createLabeledSPort(label, newArrayList(o1)) 
    }
    def KPort createLabeledSPort(KNode node, String label, Object o1, Object o2) {
        return node.createLabeledSPort(label, newArrayList(o1, o2));
    }
    def KPort createLabeledSPort(KNode node, String label, Object o1, Object o2, Object o3) {
        return node.createLabeledSPort(label, newArrayList(o1, o2, o3));
    }
    def KPort createLabeledSPort(KNode node, String label, Object o1, Object o2, Object o3, Object o4) {
        return node.createLabeledSPort(label, newArrayList(o1, o2, o3, o4));
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


    def private KPort create port: os.internalCreatePort() createLabeledEPort(KNode node, String label, ArrayList<Object> os) {
        port => [
            node.ports += it;
            it.setPortSize(portEdgeLength, portEdgeLength)
            it.addLayoutParam(LayoutOptions::PORT_SIDE, PortSide::EAST);
//            it.addLayoutParam(LayoutOptions::OFFSET, -207f);
            it.setPortPos(node.width-1, node.nextEPortYPosition);
            it.data += createEPortRendering(label);
        ];
    }

    def private KPort create port: os.internalCreatePort() createLabeledNPort(KNode node, String label, ArrayList<Object> os) {
        port => [
            node.ports += it;
            it.setPortSize(portEdgeLength, portEdgeLength)
            it.addLayoutParam(LayoutOptions::PORT_SIDE, PortSide::NORTH);
            it.addLayoutParam(LayoutOptions::OFFSET, -portEdgeLength);
            it.setPortPos(node.nextNPortYPosition, 1);
            it.data += createEPortRendering(label).setRotation(-90f)
        ];
    }
    
    def private KPort create port: os.internalCreatePort() createLabeledSPort(KNode node, String label, ArrayList<Object> os) {
        port => [
            node.ports += it;
            it.setPortSize(portEdgeLength, portEdgeLength)
            it.addLayoutParam(LayoutOptions::PORT_SIDE, PortSide::SOUTH);
            it.addLayoutParam(LayoutOptions::OFFSET, 0f);
            it.setPortPos(node.nextSPortYPosition, node.height-1);
            it.data += createEPortRendering(label).setRotation(90f)
        ];
    }
    
    def private KPort create port: os.internalCreatePort() createLabeledWPort(KNode node, String label, ArrayList<Object> os) {
        port => [
            node.ports += it;
            it.setPortSize(portEdgeLength, portEdgeLength)
            it.addLayoutParam(LayoutOptions::PORT_SIDE, PortSide::WEST);
            it.addLayoutParam(LayoutOptions::OFFSET, 0f);
            it.setPortPos(-6, node.nextWPortYPosition);
            it.data += createWPortRendering(label);
        ];
    }
    
    def private KRendering createWPortRendering(String label) {
        return renderingFactory.createKRectangle => [
                it.setForegroundVisibility(true);
                it.setForegroundColor(0,0,0)
                it.setBackgroundVisibility(true)
                it.setBackgroundColor(0,0,0)
                it.children += renderingFactory.createKText.withCopyOf(portLabelFontSize()) => [
                    it.text = label;
                    it.setHorizontalAlignment(
                        if (inlyingPortLabels) HorizontalAlignment::LEFT else HorizontalAlignment::RIGHT
                    );
                    it.setVerticalAlignment(VerticalAlignment::CENTER)
                    it.placementData = renderingFactory.createKDirectPlacementData() => [
                        if (inlyingPortLabels) {
                            topLeft = renderingFactory.createKPosition() => [
                                it.x = renderingFactory.createKRightPosition() => [
                                    it.absolute = -2
                                ];
                                it.y = renderingFactory.createKTopPosition() => [
                                    it.absolute = portEdgeLength/2;
                                ];
                            ]
                            bottomRight = renderingFactory.createKPosition() => [
                                it.x = renderingFactory.createKRightPosition() => [
                                    it.absolute = -2
                                ];
                                it.y = renderingFactory.createKTopPosition() => [
                                    it.absolute = portEdgeLength/2;
                                ];
                            ];
                        } else {
                            topLeft = renderingFactory.createKPosition() => [
                                it.x = renderingFactory.createKLeftPosition() => [
                                    it.absolute = -2
                                ];
                                it.y = renderingFactory.createKTopPosition() => [
                                    it.absolute = portEdgeLength/2;
                                ];
                            ];
                            bottomRight = renderingFactory.createKPosition() => [
                                it.x = renderingFactory.createKLeftPosition() => [
                                    it.absolute = -2
                                ];
                                it.y = renderingFactory.createKTopPosition() => [
                                    it.absolute = portEdgeLength/2;
                                ];
                            ];
                        }
                    ];
                ];
            ];
    }
    
    def private KRendering createEPortRendering(String label) {
        return renderingFactory.createKRectangle => [
                it.setForegroundVisibility(true);
                it.setForegroundColor(0,0,0)
                it.setBackgroundVisibility(true)
                it.setBackgroundColor(0,0,0)
                it.children += renderingFactory.createKText.withCopyOf(portLabelFontSize()) => [
                    it.text = label;
                    it.setHorizontalAlignment(
                        if (inlyingPortLabels) HorizontalAlignment::RIGHT else HorizontalAlignment::LEFT
                    );
                    it.setVerticalAlignment(VerticalAlignment::CENTER)
                    it.placementData = renderingFactory.createKDirectPlacementData() => [
                        if (inlyingPortLabels) {
                            topLeft = renderingFactory.createKPosition() => [
                                it.x = renderingFactory.createKLeftPosition() => [
                                    it.absolute = -2
                                ];
                                it.y = renderingFactory.createKTopPosition() => [
                                    it.absolute = portEdgeLength/2;
                                ];
                            ];
                            bottomRight = renderingFactory.createKPosition() => [
                                it.x = renderingFactory.createKLeftPosition() => [
                                    it.absolute = -2
                                ];
                                it.y = renderingFactory.createKTopPosition() => [
                                    it.absolute = portEdgeLength/2;
                                ];
                            ];
                        } else {
                            topLeft = renderingFactory.createKPosition() => [
                                it.x = renderingFactory.createKRightPosition() => [
                                    it.absolute = -2
                                ];
                                it.y = renderingFactory.createKTopPosition() => [
                                    it.absolute = portEdgeLength/2;
                                ];
                            ]
                            bottomRight = renderingFactory.createKPosition() => [
                                it.x = renderingFactory.createKRightPosition() => [
                                    it.absolute = -2
                                ];
                                it.y = renderingFactory.createKTopPosition() => [
                                    it.absolute = portEdgeLength/2;
                                ];
                            ];
                        }
                    ];
                ];
            ];
    }

    /**
     * Reveals the first KText element of a port KRendering, which is assumed to be the port label.
     * This is useful for additionally linking it with the business element represented by the port.
     */
    def KText getFirstText(KPort port) {
        return port?.getData(typeof(KText))?:(port?.getData(typeof(KRendering))?.eAllContents?.filter(typeof(KText))?.head);
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
    
    def Float getPortEdgeLength() {
        portEdgeLengthMemo.get();
    }
    
    def void setPortEdgeLength(Float f) {
        portEdgeLengthMemo.set(f);
    }
    
    def private Maybe<Float> create it: Maybe::^create() portTopOffsetMemo() {
        it.set(PORT_TOP_OFFSET);
    }
    
    def Float getPortTopOffset() {
        portTopOffsetMemo.get();
    }
    
    def void setPortTopOffset(Float f) {
        portTopOffsetMemo.set(f);
    }
    
    def private Maybe<Float> create it: Maybe::^create() verticalPortSpacingMemo() {
        it.set(VERTICAL_PORT_SPACING);
    }
    
    def Float getVerticalPortSpacing() {
        verticalPortSpacingMemo.get();
    }
    
    def void setVerticalPortSpacing(Float f) {
        verticalPortSpacingMemo.set(f);
    }
    
    def private Maybe<Boolean> create it: Maybe::^create() inlyingPortLabelsMemo() {
        it.set(true);
    }
    
    def Boolean getInlyingPortLabels() {
        inlyingPortLabelsMemo.get();
    }
    
    def void setInlyingPortLabels(Boolean b) {
        inlyingPortLabelsMemo.set(b);
    }
    
    def private Maybe<Float> create it: Maybe::^create() => [it.set(portTopOffset)] ePortYPositionMemo(KNode node) {
    }
    
    def private Maybe<Float> create it: Maybe::^create() => [it.set(portTopOffset)] nPortYPositionMemo(KNode node) {
    }
    
    def private Maybe<Float> create it: Maybe::^create() => [it.set(portTopOffset)] sPortYPositionMemo(KNode node) {
    }

    def private Maybe<Float> create it: Maybe::^create() => [it.set(portTopOffset)] wPortYPositionMemo(KNode node) {
    }
    
    def Float nextEPortYPosition(KNode node) {
        val Maybe<Float> memo = ePortYPositionMemo(node);
        val f = memo.get();
        memo.set(f+verticalPortSpacing);
        return f;
    }
    
    def Float nextNPortYPosition(KNode node) {
        val Maybe<Float> memo = nPortYPositionMemo(node);
        val f = memo.get();
        memo.set(f+verticalPortSpacing);
        return f;
    }
    
    def Float nextSPortYPosition(KNode node) {
        val Maybe<Float> memo = sPortYPositionMemo(node);
        val f = memo.get();
        memo.set(f+verticalPortSpacing);
        return f;
    }
    
    def Float nextWPortYPosition(KNode node) {
        val Maybe<Float> memo = wPortYPositionMemo(node);
        val f = memo.get();
        memo.set(f+verticalPortSpacing);
        return f;
    }
    
}