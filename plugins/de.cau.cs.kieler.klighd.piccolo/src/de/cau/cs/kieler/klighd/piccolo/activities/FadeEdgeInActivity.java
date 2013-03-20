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
package de.cau.cs.kieler.klighd.piccolo.activities;

import java.awt.geom.Point2D;

import de.cau.cs.kieler.klighd.piccolo.krendering.KEdgeNode;

/**
 * A custom {@link edu.umd.cs.piccolo.activities.PInterpolatingActivity PInterpolatingActivity} that
 * fades {@link PNode PNodes} representing {@link de.cau.cs.kieler.core.kgraph.KEdge KEdges} into
 * diagrams.
 * 
 * @author chsch
 */
public class FadeEdgeInActivity extends ApplyBendPointsActivity {

    /**
     * Constructs an activity to apply new bend points to an edge node over a specified duration.
     * 
     * @param edgeNode
     *            the edge node
     * @param newBends
     *            the new bend points
     * @param duration
     *            the duration
     */
    public FadeEdgeInActivity(final KEdgeNode edgeNode, final Point2D[] newBends, final long duration) {
        super(edgeNode, newBends, duration);
    }

    /**
     * {@inheritDoc}
     */
    public void activityStarted() {
        super.activityStarted();
        if (getFirstLoop()) {
            getEdgeNode().setTransparency(0);
            getEdgeNode().setBendPoints(getTargetBends());
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setRelativeTargetValue(final float zeroToOne) {
        getEdgeNode().setTransparency(1 - zeroToOne);
    }
}
