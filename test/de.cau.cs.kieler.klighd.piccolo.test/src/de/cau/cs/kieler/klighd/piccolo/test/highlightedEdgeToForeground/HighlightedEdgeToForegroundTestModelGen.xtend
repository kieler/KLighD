/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.test.highlightedEdgeToForeground

import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.krendering.Colors
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPortExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import javax.inject.Inject
import org.eclipse.elk.core.options.EdgeRouting
import org.eclipse.elk.core.options.PortConstraints
import org.eclipse.elk.core.options.PortSide

import static de.cau.cs.kieler.klighd.syntheses.DiagramLayoutOptions.*
import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import org.eclipse.elk.core.math.ElkPadding
import org.eclipse.elk.alg.layered.options.LayeredOptions

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
        val root = createNode()
            .setLayoutOption(PADDING, new ElkPadding(0))

        root.children += createNode() => [
            // the visible container node
            it.addRectangle.lineWidth = 3;

            it.setLayoutOption(LayeredOptions.PADDING, new ElkPadding(50))
            it.setLayoutOption(LayeredOptions.SPACING_NODE_NODE_BETWEEN_LAYERS, 100d)
            it.setLayoutOption(LayeredOptions.SPACING_EDGE_NODE, 50d)
            it.setLayoutOption(LayeredOptions.SPACING_EDGE_NODE_BETWEEN_LAYERS, 50d)
            it.setLayoutOption(LayeredOptions.SPACING_EDGE_EDGE, 50d)
            it.setLayoutOption(LayeredOptions.SPACING_PORT_PORT, 45d)
            it.setLayoutOption(LayeredOptions.EDGE_ROUTING, EdgeRouting.ORTHOGONAL)

            it.ports += createPort.setLayoutOption(PORT_SIDE, PortSide.WEST).setPortSize(5, 5)
            it.ports += createPort.setLayoutOption(PORT_SIDE, PortSide.EAST).setPortSize(5, 5)

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
            it.addLayoutParam(PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);
            it.ports += createPort.setLayoutOption(PORT_SIDE, PortSide.WEST).setPortSize(5, 5);
            it.ports += createPort.setLayoutOption(PORT_SIDE, PortSide.EAST).setPortSize(5, 5);
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