/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import de.cau.cs.kieler.core.krendering.KChildArea;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolo.util.PPickPath;

/**
 * A dedicated Piccolo2D node type whose instances represent
 * {@link de.cau.cs.kieler.core.krendering.KChildArea KChildAreas}.<br>
 * <br>
 * Inherits from {@link KlighdDisposingLayer} in order to enable its observation by a
 * {@link edu.umd.cs.piccolo.PCamera PCamera}.
 *
 * @author mri
 * @author chsch
 */
public class KChildAreaNode extends KlighdDisposingLayer implements IKlighdNode.IKlighdFigureNode {

    private static final long serialVersionUID = -403773990520864787L;

    private final KNodeAbstractNode parentNodeNode;

    private final boolean edgesFirst;

    /** the node layer. */
    private PLayer nodeLayer;

    /** the edge layer. */
    private PLayer edgeLayer;

    /** the {@link KChildArea} represented by this {@link KChildAreaNode}, may be <code>null</code>. */
    private KChildArea childArea;

    /** flag indicating whether to clip nodes and edges. */
    private boolean clip = false;

    /**
     * Constructs a child area for a given node.
     *
     * @param parentNodeNode
     *            the {@link KNodeAbstractNode} containing this child area
     * @param edgesFirst
     *            determining whether edges are drawn before nodes, i.e. nodes have priority over
     *            edges
     */
    public KChildAreaNode(final KNodeAbstractNode parentNodeNode, final boolean edgesFirst) {
        super();
        this.setPickable(false);
        this.parentNodeNode = parentNodeNode;
        this.edgesFirst = edgesFirst;
    }

    /**
     * Get the EdgeLayer.
     *
     * @return a dedicated layer accommodating all attached {@link KEdgeNode KEdgeNodes}.
     */
    public PLayer getEdgeLayer() {
        return this.edgeLayer;
    }

    /**
     * Get the NodeLayer.
     *
     * @return a dedicated layer accommodating all attached {@link KNodeNode KNodeNodes}.
     */
    public PLayer getNodeLayer() {
        return this.nodeLayer;
    }

    /**
     * Sets whether to clip nodes and edges at the child areas boundaries.
     *
     * @param clip
     *            true if nodes and edges should be clipped; false else
     */
    public void setClip(final boolean clip) {
        this.clip = clip;
    }

    /**
     * Adds a representation of a node to this child area.
     *
     * @param node
     *            the node representation
     */
    public void addNode(final KNodeNode node) {
        if (nodeLayer == null) {
            nodeLayer = new KlighdDisposingLayer();
            addChild(edgesFirst ? getChildrenCount() : 0, nodeLayer);
        }
        nodeLayer.addChild(node);
        node.setParentNode(parentNodeNode);
    }

    /**
     * Adds a representation of an edge to this child area.
     *
     * @param edge
     *            the edge representation
     */
    public void addEdge(final KEdgeNode edge) {
        if (edgeLayer == null) {
            edgeLayer = new KlighdDisposingLayer();
            addChild(edgesFirst ? 0 : getChildrenCount(), edgeLayer);
        }
        edgeLayer.addChild(edge);
    }

    /**
     * Sets the tracked {@link KChildArea} represented by <code>this</code> {@link KChildAreaNode}.
     *
     * @param rendering
     *            the corresponding {@link KChildArea}
     * @return <code>this</code> for convenience
     */
    public KChildAreaNode setRendering(final KChildArea rendering) {
        this.childArea = rendering;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public KChildArea getViewModelElement() {
        return this.childArea;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSelectable() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean validateFullBounds() {
        if (parentNodeNode.isBoundsValidationRequired()) {
            return super.validateFullBounds();
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateFullPaint() {
        if (parentNodeNode.isBoundsValidationRequired()) {
            super.validateFullPaint();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PBounds getUnionOfChildrenBounds(final PBounds dstBounds) {
        if (parentNodeNode.isBoundsValidationRequired()) {
            return super.getUnionOfChildrenBounds(dstBounds);
        } else {
            return dstBounds;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        if (clip) {
            ((KlighdPaintContext) paintContext).forcedPushClip(getBoundsReference());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintAfterChildren(final PPaintContext paintContext) {
        if (clip) {
            ((KlighdPaintContext) paintContext).forcedPopClip();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean fullPick(final PPickPath pickPath) {
        // special picking when this child area has clipping enabled
        if (clip) {
            // never pick the child area and only pick children in the clipped area
            if (getVisible() && getChildrenPickable() && intersects(pickPath.getPickBounds())) {
                pickPath.pushNode(this);
                pickPath.pushTransform(getTransformReference(false));

                final int count = getChildrenCount();
                for (int i = count - 1; i >= 0; i--) {
                    final PNode child = getChild(i);
                    if (child.fullPick(pickPath)) {
                        return true;
                    }
                }

                pickPath.popTransform(getTransformReference(false));
                pickPath.popNode(this);
            }
        } else {
            return super.fullPick(pickPath);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fullPaint(final PPaintContext paintContext) {
        super.validateFullBounds();
        super.validateFullPaint();

        super.fullPaint(paintContext);
    }
}
