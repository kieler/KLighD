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
import de.cau.cs.kieler.core.util.IWrapper;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.klighd.piccolo.nodes.PChildRepresentedNode;

/**
 * The Piccolo node for representing a {@code KEdge}.
 * 
 * @author mri
 */
public class KEdgeNode extends PChildRepresentedNode implements IWrapper<KEdge> {

    private static final long serialVersionUID = -1867615197736299487L;

    /** the property name for changes of the edge's bend points. */
    public static final String PROPERTY_BEND_POINTS = "bendPoints";

    /** the encapsulated {@code KEdge}. */
    private KEdge edge;
    /** the edge layout associated with this node. */
    private KEdgeLayout edgeLayout = null;

    /** the bend points. */
    private Point2D[] bendPoints = new Point2D[2];

    /** the rendering controller. */
    private RenderingController renderingController;

    /**
     * Constructs a Piccolo node for representing a {@code KEdge}.
     * 
     * @param edge
     *            the edge
     */
    public KEdgeNode(final KEdge edge) {
        this.edge = edge;
        setPickable(true);
    }

    /**
     * {@inheritDoc}
     */
    public KEdge getWrapped() {
        return edge;
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
     * Creates the rendering.
     */
    public void createRendering() {
        if (renderingController == null) {
            renderingController = new RenderingController(this);
            renderingController.initialize();
        }
    }

    /**
     * Updates the layout.
     */
    public void updateLayout() {
        // try to get the edge layout
        if (edgeLayout == null) {
            edgeLayout = edge.getData(KEdgeLayout.class);
            // TODO register adapter
        }

        // apply the layout
        if (edgeLayout != null) {
            // build the bend point array
            Point2D[] points = new Point2D[edgeLayout.getBendPoints().size() + 2];
            int i = 0;
            points[i++] =
                    new Point2D.Float(edgeLayout.getSourcePoint().getX(), edgeLayout
                            .getSourcePoint().getY());
            for (KPoint bend : edgeLayout.getBendPoints()) {
                points[i++] = new Point2D.Float(bend.getX(), bend.getY());
            }
            points[i] =
                    new Point2D.Float(edgeLayout.getTargetPoint().getX(), edgeLayout
                            .getTargetPoint().getY());
            
            // set the new bend points
            setBendPoints(points);
        }
    }

    /**
     * Sets the bend points and fires a property change event.
     * 
     * @param points
     *            the points
     */
    private void setBendPoints(final Point2D[] points) {
        bendPoints = points;
        firePropertyChange(-1, KEdgeNode.PROPERTY_BEND_POINTS, null, bendPoints);
    }

}
