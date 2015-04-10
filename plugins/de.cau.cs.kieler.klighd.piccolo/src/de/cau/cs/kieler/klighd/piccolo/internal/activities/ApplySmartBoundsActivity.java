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
package de.cau.cs.kieler.klighd.piccolo.internal.activities;

import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IInternalKGraphElementNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.activities.PInterpolatingActivity;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * The Piccolo2D activity for applying smart bounds to a Piccolo2D node.
 * 
 * @author mri, chsch
 */
public class ApplySmartBoundsActivity extends PInterpolatingActivity implements
        IStartingAndFinishingActivity {

    /** the node for this activity. */
    private final PNode node;
    
    /** the source bounds. */
    private PBounds sourceBounds;
    /** the target bounds. */
    private PBounds targetBounds;
    /** the delta bounds. */
    private PBounds deltaBounds;

    private double sourceScale;
    private double targetScale;
    private double deltaScale;

    /** a local memory indicating whether a style update took place already. */
    private boolean stylesModified = false;
    
    /**
     * Constructs an activity to apply smart bounds to a Piccolo2D node over a duration.
     * 
     * @param node
     *            the Piccolo2D node
     * @param bounds
     *            the bounds
     * @param scaleFactor
     *            the scale factor to be applied to <code>node</code>
     * @param duration
     *            the duration
     */
    public ApplySmartBoundsActivity(final PNode node, final PBounds bounds,
            final float scaleFactor, final long duration) {
        super(duration);
        this.node = node;
        this.targetBounds = bounds;
        this.targetScale = scaleFactor;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void activityStarted() {
        this.sourceBounds = NodeUtil.determineBounds(node);
        this.deltaBounds = new PBounds(targetBounds.x - sourceBounds.x, targetBounds.y
                - sourceBounds.y, targetBounds.width - sourceBounds.width, targetBounds.height
                - sourceBounds.height);
        
        this.sourceScale = node.getTransformReference(true).getScale();
        this.deltaScale = this.targetScale - this.sourceScale;
        
        node.setVisible(true);
        super.activityStarted();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setRelativeTargetValue(final float zeroToOne) {
        if (zeroToOne == 1.0f) {
            // when the activity completes set the target bounds
            node.setScale(targetScale);
            NodeUtil.applyBounds(node, targetBounds);
        } else {
            // as long as the activity is not completed use the delta values
            node.setScale(sourceScale + zeroToOne * deltaScale);
            NodeUtil.applyBounds(node, sourceBounds.getX() + zeroToOne * deltaBounds.getX(),
                    sourceBounds.getY() + zeroToOne * deltaBounds.getY(), sourceBounds.getWidth()
                            + zeroToOne * deltaBounds.getWidth(), sourceBounds.getHeight()
                            + zeroToOne * deltaBounds.getHeight());
        }
        if (!stylesModified && zeroToOne > 1f / 2f) {
            stylesModified = true;
            final IInternalKGraphElementNode<?> gE = NodeUtil.asKGENode(node);
            if (gE.getRenderingController() != null) {
                gE.getRenderingController().modifyStyles();
            }
        }
        super.setRelativeTargetValue(zeroToOne);
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This customization puts the desired bounds to the node.
     */
    @Override
    public void activityFinished() {
        node.setScale(targetScale);
        NodeUtil.applyBounds(node, targetBounds);
        if (!stylesModified) {
            stylesModified = true;
            final IInternalKGraphElementNode<?> gE = NodeUtil.asKGENode(node);
            if (gE.getRenderingController() != null) {
                gE.getRenderingController().modifyStyles();
            }
        }
        super.activityFinished();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isAnimation() {
        return true;
    }

    /**
     * Getter.
     * 
     * @return the node
     */
    PNode getNode() {
        return node;
    }

    /**
     * Getter.
     * 
     * @return the targetBounds
     */
    PBounds getTargetBounds() {
        return targetBounds;
    }
}
