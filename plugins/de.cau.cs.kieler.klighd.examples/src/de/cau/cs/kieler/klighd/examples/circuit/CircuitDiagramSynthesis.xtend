/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.examples.circuit

import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.LineCap
import de.cau.cs.kieler.core.krendering.LineJoin
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.core.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KLabelExtensions
import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPortExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.kiml.options.EdgeRouting
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.PortConstraints
import de.cau.cs.kieler.kiml.options.PortSide
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import javax.inject.Inject

/**
 *
 * @author chsch
 */
class CircuitDiagramSynthesis extends AbstractDiagramSynthesis<Circuit> {
    
    @Inject
    extension KNodeExtensions
    
    @Inject
    extension KEdgeExtensions
    
    @Inject
    extension KPortExtensions
    
    @Inject
    extension KLabelExtensions

    @Inject
    extension KRenderingExtensions
    
    @Inject
    extension KContainerRenderingExtensions
    
    @Inject
    extension KPolylineExtensions
    
    @Inject
    extension KColorExtensions
    
    override transform(Circuit circuit) {
        val diagramRoot = createNode().putToLookUpWith(circuit);
        
        circuit.createCircuitNode(diagramRoot);
        
        return diagramRoot;
    }
    
    def KNode createCircuitNode(Circuit circuit, KNode parent) {        
        val circuitNode = circuit.createNode().putToLookUpWith(circuit);
        parent.children += circuitNode;
        
        circuitNode.setLayoutOption(LayoutOptions.EDGE_ROUTING, EdgeRouting.ORTHOGONAL);
            
        val atomicCircuit = circuit.innerCircuits.empty;
        circuit.connectors.forEach[ connector |
            connector.createPort().putToLookUpWith(connector) => [
                circuitNode.ports += it;
                it.setPortSize(5, 2);
                it.setLayoutOption(LayoutOptions.OFFSET, if (atomicCircuit) 0f else -3f);
                it.addRectangle.setLineJoin(LineJoin.JOIN_ROUND).background = "black".color;
                
                connector.createLabel(it).putToLookUpWith(connector)
                    .configureInsidePortLabel(connector.name, 8, KlighdConstants.DEFAULT_FONT_NAME);
            ];
        ];
        
        switch (circuit.type) {
            case "NOT" : circuitNode.createNotGate()
            case "AND" : circuitNode.createAndGate()
            case "OR" : circuitNode.createOrGate()
            default: circuitNode.setNodeSize(40, 40).addRoundedRectangle(2, 5) => [
                it.shadow = "black".color
                it.selectionBackground = "gray".color;
            ]
        }
        
        circuit.innerCircuits.forEach[
            it.createCircuitNode(circuitNode);
        ];
        
        circuit.innerWires.forEach[ wire |
            wire.connectedTo.tail.forEach[ connector |
                createEdge().putToLookUpWith(wire) => [
                    it.source = wire.connectedTo.head?.parent?.node;
                    it.sourcePort = wire.connectedTo.head?.port;
                    
                    it.target = connector.parent?.node;
                    it.targetPort = connector.port;
                    
                    it.addRoundedBendsPolyline(3);
                ];
            ];
        ];
        
        return circuitNode;
    } 
    
    def KRendering createNotGate(KNode node) {
        node.setNodeSize(30,30);
        node.setLayoutOption(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);
        
        node.ports.head.setLayoutOption(LayoutOptions.PORT_SIDE, PortSide.WEST);
        node.ports.last.setLayoutOption(LayoutOptions.PORT_SIDE, PortSide.EAST)
                       .setLayoutOption(LayoutOptions.OFFSET, -1f);
        
        node.addRectangle => [
            it.invisible = true
            it.addPolygon => [
                it.lineWidth = 2
                it.lineCap = LineCap.CAP_ROUND;
                it.lineJoin = LineJoin.JOIN_ROUND;
                it.background = "white".color;
                it.selectionBackground = "gray".color;
                it.addKPosition(LEFT, 0, 0, TOP, 1, 0)
                it.addKPosition(RIGHT, 0, 0, TOP, 0, 0.5f)
                it.addKPosition(LEFT, 0, 0, BOTTOM, 1, 0)
            ];
        ];
    } 
    
    def KRendering createAndGate(KNode node) {
        node.setNodeSize(40,30);
        node.setLayoutOption(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);
        
        node.ports.forEach[
            it.setLayoutOption(LayoutOptions.PORT_SIDE, PortSide.WEST);
        ];
        node.ports.last.setLayoutOption(LayoutOptions.PORT_SIDE, PortSide.EAST)
                       .setLayoutOption(LayoutOptions.OFFSET, -1f);
        
        node.addRectangle => [
            it.invisible = true;
            
            it.addRectangle => [
                it.lineWidth = 0;
                it.background = "white".color;
                it.selectionBackground = "gray".color;
                it.setAreaPlacementData.from(LEFT, 0, 0, TOP, 0,0).to(RIGHT, 14f, 0, BOTTOM, 0, 0)
            ];
            
            it.addPolyline => [
                it.lineWidth = 2
                it.lineCap = LineCap.CAP_ROUND;
                it.lineJoin = LineJoin.JOIN_ROUND;
                it.addKPosition(RIGHT, 14.5f, 0, TOP, 1 ,0)
                it.addKPosition(LEFT, 0, 0, TOP, 1, 0)
                it.addKPosition(LEFT, 0, 0, BOTTOM, 1, 0)
                it.addKPosition(RIGHT, 14.5f, 0, BOTTOM, 1,0)
            ];
            
            it.addArc() => [
                it.lineWidth = 2
                it.lineCap = LineCap.CAP_ROUND;
                it.background = "white".color;
                it.selectionBackground = "gray".color;
                it.arcAngle = 180;
                it.startAngle = -90;
                it.setAreaPlacementData.from(LEFT, 10, 0, TOP, 0,0);
            ];
        ];
    } 
    
    def KRendering createOrGate(KNode node) {
        node.setNodeSize(40,30);
        node.setLayoutOption(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);
        
        node.ports.forEach[
            it.setLayoutOption(LayoutOptions.PORT_SIDE, PortSide.WEST)
                .setLayoutOption(LayoutOptions.OFFSET, -8f);
        ];
        node.ports.last.setLayoutOption(LayoutOptions.PORT_SIDE, PortSide.EAST)
                       .setLayoutOption(LayoutOptions.OFFSET, -1f);
        
        node.addRectangle => [
            it.invisible = true;

            it.addRectangle => [
                it.lineWidth = 0;
                it.background = "white".color;
                it.selectionBackground = "gray".color;
                it.setAreaPlacementData.from(LEFT, 0, 0, TOP, 0,0).to(RIGHT, 14f, 0, BOTTOM, 0, 0)
            ];
            
            it.addPolyline => [                
                it.lineWidth = 2;
                it.lineCap = LineCap.CAP_ROUND;
                it.lineJoin = LineJoin.JOIN_ROUND;
                it.addKPosition(LEFT, 0, 0, TOP, 1, 0);
                it.addKPosition(RIGHT, 15, 0, TOP, 1 ,0);
            ];
            
            it.addPolyline => [
                it.lineWidth = 2;
                it.lineCap = LineCap.CAP_ROUND;
                it.lineJoin = LineJoin.JOIN_ROUND;
                it.addKPosition(LEFT, 0, 0, BOTTOM, 1, 0);
                it.addKPosition(RIGHT, 15, 0, BOTTOM, 1,0);
            ];
            
            it.addArc() => [
                it.lineWidth = 2;
                it.lineCap = LineCap.CAP_ROUND;
                it.lineJoin = LineJoin.JOIN_ROUND;
                it.arcAngle = 180;
                it.startAngle = -90;
                it.background = "white".color;
                it.setAreaPlacementData.from(LEFT, -10.1f, 0, TOP, 0,0)
                    .to(LEFT, 10, 0, BOTTOM, 0, 0);
            ];

            it.addArc() => [
                it.lineWidth = 2
                it.lineCap = LineCap.CAP_ROUND;
                it.background = "white".color;
                it.selectionBackground = "gray".color;
                it.arcAngle = 180;
                it.startAngle = -90;
                it.setAreaPlacementData.from(LEFT, 10, 0, TOP, 0,0);
            ];
        ];
    }
}