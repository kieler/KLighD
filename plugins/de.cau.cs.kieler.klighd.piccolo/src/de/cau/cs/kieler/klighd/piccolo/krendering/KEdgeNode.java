/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.krendering;

import java.awt.geom.Point2D;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.IWrapper;
import de.cau.cs.kieler.klighd.piccolo.krendering.controller.RenderingContextData;
import de.cau.cs.kieler.klighd.piccolo.nodes.PChildRepresentedNode;
import edu.umd.cs.piccolo.PNode;

/**
 * The Piccolo node for representing a {@code KEdge}.
 * 
 * @author mri
 */
public class KEdgeNode extends PChildRepresentedNode implements IWrapper<KEdge>,
        ILabeledGraphElement {

    private static final long serialVersionUID = -1867615197736299487L;

    /** the property for the Piccolo representation of an edge. */
    public static final IProperty<KEdgeNode> EDGE_REP = new Property<KEdgeNode>(
            "klighd.piccolo.prepresentation");

    /** the property name for changes of the edge's bend points. */
    public static final String PROPERTY_BEND_POINTS = "bendPoints";

    /** the encapsulated {@code KEdge}. */
    private KEdge edge;

    /** the bend points. */
    private Point2D[] bendPoints = new Point2D[2];

    /**
     * Constructs a Piccolo node for representing a {@code KEdge}.
     * 
     * @param edge
     *            the edge
     */
    public KEdgeNode(final KEdge edge) {
        this.edge = edge;
        setPickable(true);
        bendPoints[0] = new Point2D.Double();
        bendPoints[1] = new Point2D.Double();
        RenderingContextData.get(edge).setProperty(EDGE_REP, this);
    }

    /**
     * {@inheritDoc}
     */
    public KEdge getWrapped() {
        return edge;
    }

    /**
     * Sets the bend points for the edge.
     * 
     * @param bendPoints
     *            the bend points
     */
    public void setBendPoints(final Point2D[] bendPoints) {
        // set the new bend points and fire a property change event
        this.bendPoints = bendPoints;
        firePropertyChange(-1, PROPERTY_BEND_POINTS, null, bendPoints);
    }

    /**
     * Returns the bend points for the edge.
     * 
     * @return the bend points
     */
    public Point2D[] getBendPoints() {
        return bendPoints;
    }

    /**
     * Returns the child area that contains this edge.
     * 
     * @return the child area containing this edge or null if the edge is not contained in a child
     *         area
     */
    public KChildAreaNode getChildArea() {
        PNode parent = getParent();
        if (parent instanceof KChildAreaNode) {
            return (KChildAreaNode) parent;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void addLabel(final KLabelNode label) {
        addChild(label);
    }

}
