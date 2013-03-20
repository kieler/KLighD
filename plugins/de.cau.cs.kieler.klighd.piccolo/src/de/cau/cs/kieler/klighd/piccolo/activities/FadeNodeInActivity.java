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

import de.cau.cs.kieler.klighd.piccolo.util.NodeUtil;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A custom {@link edu.umd.cs.piccolo.activities.PInterpolatingActivity PInterpolatingActivity} that
 * fades {@link PNode PNodes} representing {@link de.cau.cs.kieler.core.kgraph.KGraphElement
 * KGraphElements} (except KEdges) into a diagram.
 * 
 * @author chsch
 */
public class FadeNodeInActivity extends ApplySmartBoundsActivity {

    /**
     * Constructs an activity to apply smart bounds to a Piccolo node over a duration.
     * 
     * @param node
     *            the Piccolo node
     * @param bounds
     *            the bounds
     * @param duration
     *            the duration
     */
    public FadeNodeInActivity(final PNode node, final PBounds bounds,
            final long duration) {
        super(node, bounds, duration);
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This customization sets the new node to transparent and initializes its position.
     */
    public void activityStarted() {
        if (getFirstLoop()) {
            ((PNode) getNode()).setTransparency(0);
            NodeUtil.applySmartBounds((PNode) getNode(), getTargetBounds());
        }
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This customization exposes the given node according to the value of 'zeroToOne'.
     */
    @Override
    public void setRelativeTargetValue(final float zeroToOne) {
        ((PNode) getNode()).setTransparency(1 - zeroToOne);
    }
}
