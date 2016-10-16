/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.kgraph.util;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeLabelPlacement;
import org.eclipse.elk.core.options.NodeLabelPlacement;
import org.eclipse.elk.core.options.PortSide;
import org.eclipse.elk.core.options.SizeConstraint;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Strings;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory;
import de.cau.cs.kieler.klighd.kgraph.KIdentifier;
import de.cau.cs.kieler.klighd.kgraph.KInsets;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPort;

/**
 * Utility methods to operate on KGraphs.
 */
public final class KGraphUtil {
    
    /** We will need this. A lot. */
    private static final KGraphFactory FACTORY = KGraphFactory.eINSTANCE;
    /** Default minimal width for nodes. */
    public static final float DEFAULT_MIN_WIDTH = 20.0f;
    /** Default minimal height for nodes. */
    public static final float DEFAULT_MIN_HEIGHT = 20.0f;
    
    /** Utility class is not meant to be instantiated. */
    private KGraphUtil() {
        throw new IllegalAccessError();
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    // CREATION

    /**
     * Creates an initialized node. Its insets are set to zero.
     * 
     * @return initialized node.
     */
    public static KNode createInitializedNode() {
        KNode node = FACTORY.createKNode();
        node.setInsets(FACTORY.createKInsets());
        return node;
    }

    /**
     * Creates an initialized port. Its insets are set to zero.
     * 
     * @return initialized port.
     */
    public static KPort createInitializedPort() {
        KPort port = FACTORY.createKPort();
        port.setInsets(FACTORY.createKInsets());
        return port;
    }

    /**
     * Creates an intialized label and adds it to the given element. Its insets are set to zero
     * and its text is set to the empty string.
     * 
     * @param element the element to add the label to.
     * @return the initialized label.
     */
    public static KLabel createInitializedLabel(final KLabeledGraphElement element) {
        KLabel label = FACTORY.createKLabel();
        label.setInsets(FACTORY.createKInsets());
        label.setText("");
        label.setParent(element);
        return label;
    }

    /**
     * Creates an initialized edge. Its source and target point are set to zero.
     * 
     * @return the initialized edge.
     */
    public static KEdge createInitializedEdge() {
        KEdge edge = FACTORY.createKEdge();
        edge.setSourcePoint(FACTORY.createKPoint());
        edge.setTargetPoint(FACTORY.createKPoint());
        return edge;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    // CONFIGURATION

    /**
     * Ensures that each element contained in the given graph is attributed correctly for
     * usage in KIML. {@link KGraphElement}
     * 
     * @param graph the parent node of a graph 
     */
    public static void validate(final KNode graph) {
        // construct an iterator that first returns the root node, i.e. 'graph',
        //  and all contained {@link KGraphElement KGraphElements} afterwards
        //  ({@link KGraphData} are omitted for performance reasons)
        Iterator<KGraphElement> contentIter = Iterators.concat(
                Lists.newArrayList(graph).iterator(),
                Iterators.filter(graph.eAllContents(), KGraphElement.class));
        
        // Note that using an iterator and adding elements works here
        //  as the eAllContents() iterator relies on the lists provided by eContents()
        //  of EObjects that, in turn, provides a mirrored list of all contained elements.
        while (contentIter.hasNext()) {
            EObject element = contentIter.next();
            // Make sure nodes are OK
            if (element instanceof KNode) {
                KNode node = (KNode) element;
                if (node.getInsets() == null) {
                    node.setInsets(FACTORY.createKInsets());
                }
            // Make sure labels are OK
            } else if (element instanceof KLabel) {
                KLabel label = (KLabel) element;
                if (label.getText() == null) {
                    label.setText("");
                }
            // Make sure edges are OK
            } else if (element instanceof KEdge) {
                KEdge edge = (KEdge) element;
                if (edge.getSourcePoint() == null) {
                    edge.setSourcePoint(FACTORY.createKPoint());
                }
                if (edge.getTargetPoint() == null) {
                    edge.setTargetPoint(FACTORY.createKPoint());
                }
                // ports and edges are not opposite, so check whether they are connected properly
                KPort sourcePort = edge.getSourcePort();
                if (sourcePort != null) {
                    if (!sourcePort.getEdges().contains(edge)) {
                        sourcePort.getEdges().add(edge);
                    }
                }
                KPort targetPort = edge.getTargetPort();
                if (targetPort != null) {
                    if (!targetPort.getEdges().contains(edge)) {
                        targetPort.getEdges().add(edge);
                    }
                }
            }
        }
    }

    /**
     * Adds some default values to the passed node. This includes a reasonable size, a label based
     * on the node's {@link KIdentifier} and a inside center node label placement.
     * 
     * Such default values are useful for fast test case generation.
     * 
     * @param node
     *            a node of a graph
     */
    public static void configureWithDefaultValues(final KNode node) {
        // Make sure the node has a size if the size constraints are fixed
        Set<SizeConstraint> sc = node.getProperty(CoreOptions.NODE_SIZE_CONSTRAINTS);
        
        if (sc.equals(SizeConstraint.fixed()) && node.getWidth() == 0f && node.getHeight() == 0f) {
            node.setWidth(DEFAULT_MIN_WIDTH * 2 * 2);
            node.setHeight(DEFAULT_MIN_HEIGHT * 2 * 2);
        }
        
        // Label
        ensureLabel(node);
        Set<NodeLabelPlacement> nlp = node.getProperty(CoreOptions.NODE_LABELS_PLACEMENT);
        if (nlp.equals(NodeLabelPlacement.fixed())) {
            node.setProperty(CoreOptions.NODE_LABELS_PLACEMENT, NodeLabelPlacement.insideCenter());
        }
    }

    /**
     * Adds some default values to the passed port. This includes a reasonable size 
     * and a label based on the port's {@link KIdentifier}.
     * 
     * Such default values are useful for fast test case generation.
     * 
     * @param port
     *            a port of a node of a graph
     */
    public static void configureWithDefaultValues(final KPort port) {
        if (port != null && port.getWidth() == 0f && port.getHeight() == 0f) {
            port.setWidth(DEFAULT_MIN_WIDTH / 2 / 2);
            port.setHeight(DEFAULT_MIN_HEIGHT / 2 / 2);
        }
        
        ensureLabel(port);
    }

    /**
     * Configures the {@link EdgeLabelPlacement} of the passed edge to be center of the edge.
     * 
     * @param edge
     *            an edge of a graph
     */
    public static void configureWithDefaultValues(final KEdge edge) {
        EdgeLabelPlacement elp = edge.getProperty(CoreOptions.EDGE_LABELS_PLACEMENT);
        if (elp == EdgeLabelPlacement.UNDEFINED) {
            edge.setProperty(CoreOptions.EDGE_LABELS_PLACEMENT, EdgeLabelPlacement.CENTER);
        }
    }

    /**
     * If the element does not already own a label, a label is created based on the element's
     * {@link KIdentifier} (if it exists, that is).
     * 
     * @param element the element to create a label for.
     */
    private static void ensureLabel(final KLabeledGraphElement element) {
        if (element.getLabels().isEmpty()) {
            KIdentifier id = element.getData(KIdentifier.class);
            if (id != null && !Strings.isNullOrEmpty(id.getId())) {
                KLabel label = createInitializedLabel(element);
                label.setText(id.getId());
            }
        }
    }

    /**
     * Persists all {@link EMapPropertyHolder}s of a KGraph by serializing the contained properties
     * into {@link de.cau.cs.kieler.klighd.kgraph.PersistentEntry} tuples.
     *
     * @param graph the root element of the graph to persist elements of.
     */
    public static void persistDataElements(final KNode graph) {
        TreeIterator<EObject> iterator = graph.eAllContents();
        while (iterator.hasNext()) {
            EObject eObject = iterator.next();
            if (eObject instanceof EMapPropertyHolder) {
                ((EMapPropertyHolder) eObject).makePersistent();
            }
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    // LAYOUT

    /**
     * Converts the given relative point to an absolute location. The insets of the parent node
     * are included in this calculation.
     * 
     * @param point a relative point
     * @param parent the parent node to which the point is relative to
     * @return {@code point} for convenience
     */
    public static KVector toAbsolute(final KVector point, final KNode parent) {
        KNode node = parent;
        while (node != null) {
            KInsets insets = node.getInsets();
            point.add(node.getXpos() + insets.getLeft(), node.getYpos() + insets.getTop());
            node = node.getParent();
        }
        return point;
    }

    /**
     * Converts the given absolute point to a relative location. The insets of the parent node
     * are included in this calculation.
     * 
     * @param point an absolute point
     * @param parent the parent node to which the point shall be made relative to
     * @return {@code point} for convenience
     */
    public static KVector toRelative(final KVector point, final KNode parent) {
        KNode node = parent;
        while (node != null) {
            KInsets insets = node.getInsets();
            point.add(-node.getXpos() - insets.getLeft(), -node.getYpos() - insets.getTop());
            node = node.getParent();
        }
        return point;
    }

    /**
     * Determines the port side for the given port from its relative position at
     * its corresponding node.
     * 
     * @param port port to analyze
     * @param direction the overall layout direction
     * @return the port side relative to its containing node
     * @throws IllegalArgumentException if the containing node is {@code null}.
     */
    public static PortSide calcPortSide(final KPort port, final Direction direction) {
        KNode node = port.getNode();
        if (node == null) {
            throw new IllegalArgumentException("port must be assigned to a node");
        }
        
        // if the node has zero size, we cannot decide anything
        float nodeWidth = node.getWidth();
        float nodeHeight = node.getHeight();
        if (nodeWidth <= 0 && nodeHeight <= 0) {
            return PortSide.UNDEFINED;
        }

        // check direction-dependent criterion
        float xpos = port.getXpos();
        float ypos = port.getYpos();
        switch (direction) {
        case LEFT:
        case RIGHT:
            if (xpos < 0) {
                return PortSide.WEST;
            } else if (xpos + port.getWidth() > nodeWidth) {
                return PortSide.EAST;
            }
            break;
        case UP:
        case DOWN:
            if (ypos < 0) {
                return PortSide.NORTH;
            } else if (ypos + port.getHeight() > nodeHeight) {
                return PortSide.SOUTH;
            }
        }
        
        // check general criterion
        float widthPercent = (xpos + port.getWidth() / 2) / nodeWidth;
        float heightPercent = (ypos + port.getHeight() / 2) / nodeHeight;
        if (widthPercent + heightPercent <= 1
                && widthPercent - heightPercent <= 0) {
            // port is on the left
            return PortSide.WEST;
        } else if (widthPercent + heightPercent >= 1
                && widthPercent - heightPercent >= 0) {
            // port is on the right
            return PortSide.EAST;
        } else if (heightPercent < 1.0f / 2) {
            // port is on the top
            return PortSide.NORTH;
        } else {
            // port is on the bottom
            return PortSide.SOUTH;
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    // MISCELLANEOUS

    /**
     * Determines whether the given child node is a descendant of the parent node. This method does
     * not regard a node as its own descendant.
     * 
     * @param child a child node
     * @param parent a parent node
     * @return {@code true} if {@code child} is a direct or indirect child of {@code parent}.
     */
    public static boolean isDescendant(final KNode child, final KNode parent) {
        KNode current = child;
        while (current.getParent() != null) {
            current = current.getParent();
            if (current == parent) {
                return true;
            }
        }
        return false;
    }

}
