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
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IInternalKGraphElementNode.IInternalKNodeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
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
public class KChildAreaNode extends KlighdDisposingLayer {

    private static final long serialVersionUID = -403773990520864787L;

    private final IInternalKNodeNode containingINode;

    private final boolean edgesFirst;

    /** the node layer. */
    private PLayer nodeLayer;

    /** the edge layer. */
    private PLayer edgeLayer;

    /** flag indicating whether to clip nodes and edges. */
    private boolean clip = false;

    /**
     * Constructs a child area for a given node.
     *
     * @param containingNode
     *            the node containing this child area
     * @param edgesFirst
     *            determining whether edges are drawn before nodes, i.e. nodes have priority over
     *            edges
     */
    public KChildAreaNode(final IInternalKNodeNode containingNode, final boolean edgesFirst) {
        super();
        this.setPickable(false);
        this.containingINode = containingNode;
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
        node.setParentNode(containingINode);
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
}
