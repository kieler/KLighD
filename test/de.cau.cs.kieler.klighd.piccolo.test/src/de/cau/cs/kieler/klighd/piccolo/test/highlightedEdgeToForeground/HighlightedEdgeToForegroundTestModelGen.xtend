/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.test.highlightedEdgeToForeground

import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.core.krendering.Colors
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPortExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.kiml.options.EdgeRouting
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.PortConstraints
import de.cau.cs.kieler.kiml.options.PortSide
import javax.inject.Inject

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

/**
 * @author chsch
 *
 */
class HighlightedEdgeToForegroundTestModelGen {
    
    @Inject
    extension KNodeExtensions
    
    @Inject
    extension KPortExtensions
    
    @Inject
    extension KEdgeExtensions
    
    @Inject
    extension KRenderingExtensions
    
    def KNode getTestModel() {
        val root = createNode();
        root.addLayoutParam(LayoutOptions.SPACING, 100f)
        root.addLayoutParam(LayoutOptions.BORDER_SPACING, 0f)

        root.children += createNode() => [
            // the visible container node
            it.addRectangle.lineWidth = 3;

            it.addLayoutParam(LayoutOptions.SPACING, 100f)
            it.addLayoutParam(LayoutOptions.BORDER_SPACING, 50f)
            it.addLayoutParam(LayoutOptions.EDGE_ROUTING, EdgeRouting.ORTHOGONAL);

            // explicitly configured the edge spacing factors
            //  after the introduction of the 'edgeNodeSpacingFactor'
            //  s.t. the earlier determined sample coordinates still hold

            it.setLayoutOption("de.cau.cs.kieler.klay.layered.edgeSpacingFactor", 0.5f)
            it.setLayoutOption("de.cau.cs.kieler.klay.layered.edgeNodeSpacingFactor", 0.5f)

            it.ports += createPort.addLayoutParam(LayoutOptions.PORT_SIDE, PortSide.WEST).setPortSize(5, 5);
            it.ports += createPort.addLayoutParam(LayoutOptions.PORT_SIDE, PortSide.EAST).setPortSize(5, 5);

            it.children += createChildNode;
            it.children += createChildNode;

            // edge from container to first child
            createEdge(
                it, it.ports.head,
                it.children.head, it.children.head.ports.head
            ); 

            // edge from first to second child
            createEdge(
                it.children.head, it.children.head.ports.tail.head,
                it.children.tail.head, it.children.tail.head.ports.head
            );

            // edge from second child to container
            createEdge(
                it.children.tail.head, it.children.tail.head.ports.tail.head,
                it, it.ports.tail.head
            );
            
            val addWport = createPort.setPortSize(5, 5)
            it.ports += addWport

            // edge from container's additional wPort to first child
            createEdge(
                it, addWport,
                it.children.head, it.children.head.ports.head
            ); 
            
            // edge from container's additional wPort to second child
            createEdge(
                it, addWport,
                it.children.tail.head, it.children.tail.head.ports.head
            ); 
            
            val addEport = createPort.setPortSize(5, 5)
            it.ports += addEport

            // edge from second child to container's additional ePorts
            createEdge(
                it.children.tail.head, it.children.tail.head.ports.tail.head,
                it, addEport
            ); 
        ];

        return root;
    }
    
    def private createChildNode() {
        return createNode => [
            it.addRectangle.lineWidth = 3;
            it.setNodeSize(100, 100);
            it.addLayoutParam(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);
            it.ports += createPort.addLayoutParam(LayoutOptions.PORT_SIDE, PortSide.WEST).setPortSize(5, 5);
            it.ports += createPort.addLayoutParam(LayoutOptions.PORT_SIDE, PortSide.EAST).setPortSize(5, 5);
        ]
    }
    
    def private createEdge(KNode source, KPort sourcePort, KNode target, KPort targetPort) {
       return createEdge => [ edge |           
            edge.source = source;
            edge.sourcePort = sourcePort;
            
            edge.target = target;
            edge.targetPort = targetPort;
            
            edge.addPolyline.setLineWidth(3).selectionForeground = Colors.RED            
        ] 
    }
}