/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
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
import edu.umd.cs.piccolo.activities.PInterpolatingActivity;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * The Piccolo activity for applying smart bounds to a Piccolo node.
 * 
 * @author mri
 */
public class ApplySmartBoundsActivity extends PInterpolatingActivity {

    /** the node for this activity. */
    private PNode node;
    
    /** the source bounds. */
    private PBounds sourceBounds;
    /** the target bounds. */
    private PBounds targetBounds;
    /** the delta bounds. */
    private PBounds deltaBounds;
    
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
    public ApplySmartBoundsActivity(final PNode node, final PBounds bounds, final long duration) {
        super(duration);
        this.node = node;
        this.targetBounds = bounds;
    }
    
    /**
     * {@inheritDoc}
     */
    protected void activityStarted() {
        if (getFirstLoop()) {
            this.sourceBounds = NodeUtil.determineSmartBounds(node);
            this.deltaBounds =
                    new PBounds(targetBounds.x - sourceBounds.x, targetBounds.y - sourceBounds.y,
                            targetBounds.width - sourceBounds.width, targetBounds.height
                                    - sourceBounds.height);
        }
        super.activityStarted();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setRelativeTargetValue(final float zeroToOne) {
        super.setRelativeTargetValue(zeroToOne);
        if (zeroToOne == 1.0f) {
            // when the activity completes set the target bounds
            NodeUtil.applySmartBounds(node, targetBounds);
        } else {
            // as long as the activity is not completed use the delta bounds
            NodeUtil.applySmartBounds(node, sourceBounds.getX() + zeroToOne * deltaBounds.getX(),
                    sourceBounds.getY() + zeroToOne * deltaBounds.getY(), sourceBounds.getWidth()
                            + zeroToOne * deltaBounds.getWidth(), sourceBounds.getHeight()
                            + zeroToOne * deltaBounds.getHeight());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isAnimation() {
        return true;
    }

}
