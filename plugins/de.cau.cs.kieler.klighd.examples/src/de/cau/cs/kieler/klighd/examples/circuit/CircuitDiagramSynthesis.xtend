/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.examples.circuit

import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory
import de.cau.cs.kieler.klighd.krendering.LineCap
import de.cau.cs.kieler.klighd.krendering.LineJoin
import de.cau.cs.kieler.klighd.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KLabelExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPortExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import javax.inject.Inject
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.EdgeRouting
import org.eclipse.elk.core.options.PortConstraints
import org.eclipse.elk.core.options.PortSide

/**
 * An exemplary diagram synthesis illustrating the generation of diagrams containing nodes with ports.
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

    extension KRenderingFactory = KRenderingFactory.eINSTANCE


    /**
     * {@inheritDoc}<br>
     * <br>
     * This method is called by KLighD for synthesizing a KGraph/KRendering-based view model
     * describing a representation of the given {@link Circuit} <code>circuit</code>.
     */
    override transform(Circuit circuit) {
        // the diagram root node is mandatory and represents the diagrams canvas
        val diagramRoot = createNode().associateWith(circuit);
        
        // translated the given circuit recursively ...
        circuit.createCircuitNode(diagramRoot);
        
        // ... and return the result :-) 
        return diagramRoot;
    }
    
    
    /**
     * This method translates instances of {@link Circuit} recursively into {@link KNode KNodes} and
     * adds it to the given <code>parent</code> node.
     * 
     * @param circuit
     *            the circuit to be translated
     * @param parent
     *            the parent {@link KNode} the new one is to be attached.
     */
    def void createCircuitNode(Circuit circuit, KNode parent) {
        // first create the new KNode and attach it to its designated parent
        val KNode circuitNode = circuit.createNode().associateWith(circuit);
        parent.children += circuitNode;
        
        // we want the (potentially) contained edges to be routed in orthogonal style,
        //  this option is evaluated by the layout algorithm
        circuitNode.setLayoutOption(CoreOptions.EDGE_ROUTING, EdgeRouting.ORTHOGONAL);

        val atomicCircuit = circuit.innerCircuits.empty;
        
        // for each of 'circuit's connectors ...
        circuit.connectors.forEach[ connector |

            // ... create a port, associate it to the connector, and configure it ...
            connector.createPort().associateWith(connector) => [

                // first add it to the knode representing the circuit
                //  this is required for the correct label configuration later on 
                circuitNode.ports += it;
                
                // set the ports size ...
                it.setPortSize(5, 2);
                
                // ... and its offset respective to 'circuitNode's bounds
                //  a negative number causes the port to be moved inside the knode
                // we, however, want this movement only in case of non-atomic circuits,
                //  i.e. such containing an inner circuit network
                it.setLayoutOption(CoreOptions.PORT_BORDER_OFFSET, if (atomicCircuit) 0.0 else -3.0);
                
                // attach a simple rectangular figure filled with black color
                //  and slightly rounded corners 
                it.addRectangle.setBackground("black".color).lineJoin=LineJoin.JOIN_ROUND;
                
                // last but not least add a label exhibiting the ports name
                it.addInsidePortLabel(connector.name, 8, KlighdConstants.DEFAULT_FONT_NAME)
                    .associateWith(connector)
            ];
        ];
        
        // now let's attach figures ...
        //  depending on the string in 'circuit's 'type' field ...
        switch (circuit.type) {
            case "NOT" : circuitNode.createNotGateRendering()
            case "AND" : circuitNode.createAndGateRendering()
            case "OR" : circuitNode.createOrGateRendering()
            
            // in case no known type is found and 'circuit' doesn't contain any inner circuits
            case atomicCircuit : circuitNode.createBasicGateRendering()
            
            // otherwise, i.e. in case of a hierarchic circuit containing nested circuits
            //  configure the minimal node size and the port constraints
            // the expansion aware option setting is only required if collapsing and expanding
            //  of composite circuits is desired - lets give it try here ;-)
            default: circuitNode.setNodeSize(40, 40).setExpansionAwareLayoutOption(
                CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_ORDER, PortConstraints.FREE
            ).addRoundedRectangle(3, 5) => [
                // those hierarchic nodes shall be represented by a rectangle with asymmetrically
                //  rounded corners, this simplifies from distinction from wire bend points
                
                // configure the rectangle as follows
                it.shadow = "black".color
                it.selectionBackground = "gray".color;
                
                // and attach this magic collapse/expand action
                //  actually only the action's id is related to a Trigger
                // the action itself is implemented in CollapseExpandAction,
                //  it is registered in de.cau.cs.kieler.klighd's plugin.xml 
                it.addDoubleClickAction(KlighdConstants.ACTION_COLLAPSE_EXPAND);
            ]
        }
        
        // with the configuration of 'circuitNode' be finished
        //  take care about the nested circuits recursively
        circuit.innerCircuits.forEach[
            it.createCircuitNode(circuitNode);
        ];
        
        // with all inner circuit representing nodes and their ports created
        //  add the wires
        circuit.innerWires.forEach[ wire |
            // the circuit wires are really special wires - they can connect more than 2 connectors :-)
            //  let's shamelessly assume connections from the first connector to all remaining ones in the list
            //  thus, we won't create view model edges for the pairs of second connector and third, fourth, ...
            
            // for each of connector except the first one ... 
            wire.connectedTo.tail.forEach[ connector |

                // create an edge 
                createEdge().associateWith(wire) => [

                    // ... from the first connector = head of list...
                    it.source = wire.connectedTo.head?.parent?.node;
                    it.sourcePort = wire.connectedTo.head?.port;

                    // ... to the current 'connector'
                    it.target = connector.parent?.node;
                    it.targetPort = connector.port;

                    // the functions '....node' & '....port', actually named 'getNode(...)' & 'getPort(...)',
                    //  assist us in revealing the nodes and ports corresponding to the connected
                    //  circuit and connector in our semantic model
                    // they delegate to a so-called 'create extension', a very powerful element of Xtend
                    
                    // for that reason we had to call 'circuit.createNode()' rather than simply 'createNode()'
                    //  while creating 'circuitNode', see begin of this method,
                    //  and 'connector.createPort()' rather than 'createPort()', respectively
                    
                    // similarly to 'getNode(...)' the former '....createNode()' method also refers
                    //  to the 'create extension' that builds up a hidden lookup table in a side effect
                    //  and returns the element associated to the given parameters in the internal lookup
                    //  (if there is no such associated element yet, the create extension will create and store it)    

                    // attach a polyline figure with bend roundings of radius 3
                    it.addRoundedBendsPolyline(3).addJunctionPointDecorator;
                ];
            ];
        ];
    } 
    
    def KRendering createBasicGateRendering(KNode node) {
        node.setNodeSize(40,40);

        node.addRectangle => [
            it.lineWidth = 2
            it.lineCap = LineCap.CAP_ROUND;
            it.lineJoin = LineJoin.JOIN_ROUND;
            it.background = "white".color;
            it.selectionBackground = "gray".color;
        ];
    }

    private val customLightBlue = createKColor.setColor(226, 237, 255);

    def KRendering createNotGateRendering(KNode node) {
        node.setNodeSize(30,30);
        node.setLayoutOption(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);
        
        node.ports.head.setLayoutOption(CoreOptions.PORT_SIDE, PortSide.WEST);
        node.ports.last => [
            it.setLayoutOption(CoreOptions.PORT_SIDE, PortSide.EAST);
            it.setLayoutOption(CoreOptions.PORT_BORDER_OFFSET, 0.0);
            it.setPortSize(5, 5);
            it.data.removeAll(it.data.filter(typeof(KRendering)));
            
            it.addEllipse.setBackground("white".color).lineWidth = 1.5f;
        ];
        
        node.addRectangle => [
            it.invisible = true
            it.addPolygon => [
                it.lineWidth = 2
                it.lineCap = LineCap.CAP_ROUND;
                it.lineJoin = LineJoin.JOIN_ROUND;
                it.background = customLightBlue;
                it.selectionBackground = "gray".color;
                it.addKPosition(LEFT, 0, 0, TOP, 1, 0)
                it.addKPosition(RIGHT, 0, 0, TOP, 0, 0.5f)
                it.addKPosition(LEFT, 0, 0, BOTTOM, 1, 0)
            ];
        ];
    } 
    
    def KRendering createAndGateRendering(KNode node) {
        node.setNodeSize(40,30);
        node.setLayoutOption(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);
        
        node.ports.forEach[
            it.setLayoutOption(CoreOptions.PORT_SIDE, PortSide.WEST);
        ];
        node.ports.last.setLayoutOption(CoreOptions.PORT_SIDE, PortSide.EAST)
                       .setLayoutOption(CoreOptions.PORT_BORDER_OFFSET, -1.0);
        
        node.addRectangle => [
            it.invisible = true;
            
            it.addRectangle => [
                it.lineWidth = 0;
                it.background = customLightBlue;
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
                it.background = customLightBlue;
                it.selectionBackground = "gray".color;
                it.arcAngle = 180;
                it.startAngle = -90;
                it.setAreaPlacementData.from(LEFT, 10, 0, TOP, 0,0);
            ];
        ];
    } 
    
    def KRendering createOrGateRendering(KNode node) {
        node.setNodeSize(40,30);
        node.setLayoutOption(CoreOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);
        
        node.ports.forEach[
            it.setLayoutOption(CoreOptions.PORT_SIDE, PortSide.WEST)
              .setLayoutOption(CoreOptions.PORT_BORDER_OFFSET, -8.0);
        ];
        node.ports.last.setLayoutOption(CoreOptions.PORT_SIDE, PortSide.EAST)
                       .setLayoutOption(CoreOptions.PORT_BORDER_OFFSET, -1.0);
        
        node.addRectangle => [
            it.invisible = true;
            it.lineCap = LineCap.CAP_ROUND;
            it.lineCap.propagateToChildren = true

            it.addRectangle => [
                it.lineWidth = 0;
                it.background = customLightBlue;
                it.selectionBackground = "gray".color;
                it.setAreaPlacementData.from(LEFT, 0.1f, 0, TOP, 0,0).to(RIGHT, 14f, 0, BOTTOM, 0, 0)
            ];
            
            it.addPolyline => [                
                it.lineWidth = 2;
                it.addKPosition(LEFT, 0, 0, TOP, 1, 0);
                it.addKPosition(RIGHT, 15, 0, TOP, 1 ,0);
            ];
            
            it.addPolyline => [
                it.lineWidth = 2;
                it.addKPosition(LEFT, 0, 0, BOTTOM, 1, 0);
                it.addKPosition(RIGHT, 15, 0, BOTTOM, 1,0);
            ];
            
            it.addArc() => [
                it.lineWidth = 2;
                it.arcAngle = 180;
                it.startAngle = -90;
                it.background = "white".color;
                it.setAreaPlacementData.from(LEFT, -10.1f, 0, TOP, 0,0)
                    .to(LEFT, 10, 0, BOTTOM, 0, 0);
            ];

            it.addArc() => [
                it.lineWidth = 2
                it.background = customLightBlue;
                it.selectionBackground = "gray".color;
                it.arcAngle = 180;
                it.startAngle = -90;
                it.setAreaPlacementData.from(LEFT, 10, 0, TOP, 0,0);
            ];
        ];
    }
}