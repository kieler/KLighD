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
package de.cau.cs.kieler.klighd.piccolo.internal.activities;

import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IGraphElement;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.activities.PInterpolatingActivity;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A custom {@link edu.umd.cs.piccolo.activities.PInterpolatingActivity PInterpolatingActivity} that
 * fades {@link PNode PNodes} representing {@link de.cau.cs.kieler.core.kgraph.KGraphElement
 * KGraphElements} (except KEdges) into a diagram.
 * 
 * @author chsch
 */
public class FadeNodeInActivity extends PInterpolatingActivity implements IStartingAndFinishingActivity {

    /** the node for this activity. */
    private final PNode node;
    
    /** the target bounds. */
    private PBounds targetBounds;
    
    private float targetScale;
    
    /**
     * Constructs an activity that immediately applies the bounds to a Piccolo2D node and fades it
     * in over a duration.
     * 
     * @param node
     *            the Piccolo2D node
     * @param bounds
     *            the bounds, may be <code>null</code> in case <code>node</code> has already non-empty
     *            bounds (and the scale factor didn't change); that is required if <code>node</code>
     *            is set invisible while re-adding to the diagram (see
     *            {@link de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController#addNode(
     *            de.cau.cs.kieler.klighd.piccolo.internal.nodes.INode,
     *            de.cau.cs.kieler.core.kgraph.KNode, boolean) DiagramController#addNode(...)}),
     *            e.g. after expanding the corresponding parent
     *            {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KChildAreaNode
     *            KChildAreaNode}
     * @param scaleFactor
     *            the scale factor to be applied to <code>node</code>
     * @param duration
     *            the duration
     */
    public FadeNodeInActivity(final PNode node, final PBounds bounds,
            final float scaleFactor, final long duration) {
        super(duration);
        this.node = node;
        this.targetBounds = bounds;
        this.targetScale = scaleFactor;
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This customization initializes the new node's position, set it transparent, and releases the
     * invisibility.
     */
    @Override
    public void activityStarted() {
        final IGraphElement<?> gE = NodeUtil.asIGraphElement(node);

        if (gE.getRenderingController() != null) {
            gE.getRenderingController().modifyStyles();
        }

        node.setScale(targetScale);

        if (targetBounds != null) {
            NodeUtil.applyBounds(node, targetBounds);
        }

        node.setTransparency(0);
        node.setVisible(true);
        super.activityStarted();
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This customization exposes the given node according to the value of 'zeroToOne'.
     */
    @Override
    public void setRelativeTargetValue(final float zeroToOne) {        
        node.setTransparency(zeroToOne);
        super.setRelativeTargetValue(zeroToOne);
    }
    
    /**
     * {@inheritDoc}<br>
     * <br>
     * This customization fully exposes the given node.
     */
    @Override
    public void activityFinished() {
        node.setTransparency(1);
        super.activityFinished();
    }
}
