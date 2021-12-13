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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.piccolo.internal.activities;

import java.awt.geom.Point2D;

import de.cau.cs.kieler.klighd.piccolo.IKlighdNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KEdgeNode;
import edu.umd.cs.piccolo.activities.PInterpolatingActivity;

/**
 * A custom {@link edu.umd.cs.piccolo.activities.PInterpolatingActivity PInterpolatingActivity} that
 * fades {@link edu.umd.cs.piccolo.PNode PNodes} representing
 * {@link de.cau.cs.kieler.klighd.kgraph.KEdge KEdges} into diagrams.
 *
 * @author chsch
 */
public class FadeEdgeInActivity extends PInterpolatingActivity implements IStartingAndFinishingActivity {

    /** the edge node for this activity. */
    private final KEdgeNode edgeNode;

    /** the target bends. */
    private Point2D[] targetBends;

    /** the target junctions. */
    private Point2D[] targetJunctions;

    /**
     * Constructs an activity that immediately applies new bend points to a Piccolo edge node and
     * fades it in over a duration.
     *
     * @param edgeNode
     *            the edge node
     * @param newBends
     *            the new bend points
     * @param newJunctions
     *            the new junction points
     * @param duration
     *            the duration
     */
    public FadeEdgeInActivity(final KEdgeNode edgeNode, final Point2D[] newBends,
            final Point2D[] newJunctions, final long duration) {
        super(duration);
        this.edgeNode = edgeNode;
        this.targetBends = newBends;
        this.targetJunctions = newJunctions;
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This customization initializes the new node's position, set it transparent, and releases the
     * invisibility.
     */
    @Override
    public void activityStarted() {
        if (edgeNode.getRenderingController() != null) {
            edgeNode.getRenderingController().modifyStyles();
        }
        edgeNode.setBendPoints(targetBends);
        edgeNode.setJunctionPoints(targetJunctions);
        edgeNode.setTransparency(0);
        edgeNode.setVisible(true);

        super.activityStarted();

        edgeNode.firePropertyChange(0, IKlighdNode.PROPERTY_BOUNDS_FINISHED, null, Boolean.TRUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRelativeTargetValue(final float zeroToOne) {
        edgeNode.setTransparency(zeroToOne);
        super.setRelativeTargetValue(zeroToOne);
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This customization fully exposes the given edge.
     */
    @Override
    public void activityFinished() {
        edgeNode.setTransparency(1);
        super.activityFinished();
    }
}
