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

import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IInternalKGraphElementNode.IKNodeNode;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolo.util.PPickPath;

/**
 * A dedicated Piccolo2D node type whose instances represent
 * {@link de.cau.cs.kieler.core.krendering.KChildArea KChildAreas}.<br>
 * The method {@link #setExpanded(boolean)} simply forwards to {@link #setVisible(boolean)} so in
 * case the container {@link IKNodeNode} is collapsed <code>this</code> {@link KChildAreaNode} is just
 * set invisible.<br>
 * <br>
 * Inherits from {@link KlighdDisposingLayer} in order to enable its observation by a
 * {@link edu.umd.cs.piccolo.PCamera PCamera}.
 * 
 * @author mri
 * @author chsch
 */
public class KChildAreaNode extends KlighdDisposingLayer {

    private static final long serialVersionUID = -403773990520864787L;
    
    /**
     * The property name for changes of the child areas expansion status.<br>
     * <b>Caution!</b> Don't let listeners rely on the propagated old value as that might not be
     * correct in case they are fired via {@link #touchExpanded()}.
     */
    public static final String PROPERTY_EXPANSION = "expansion";

    private final IKNodeNode containingINode;

    private final boolean edgesFirst;

    /** the node layer. */
    private PLayer nodeLayer;
    
    /** the edge layer. */
    private PLayer edgeLayer;

    /** flag indicating whether to clip nodes and edges. */
    private boolean clip = false;

    /** flag indicating whether the child area is expanded. */
    private boolean expanded = false;

    /**
     * Constructs a child area for a given node.
     * 
     * @param containingNode
     *            the node containing this child area
     * @param edgesFirst
     *            determining whether edges are drawn before nodes, i.e. nodes have priority over
     *            edges
     */
    public KChildAreaNode(final IKNodeNode containingNode, final boolean edgesFirst) {
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
     * Returns whether this child area is expanded.
     * 
     * @return true if this child area is expanded; false else
     */
    public boolean isExpanded() {
        return expanded;
    }

    /**
     * Sets whether this child area is expanded.
     * 
     * @param expanded
     *            true if this child area is expanded; false else
     */
    public void setExpanded(final boolean expanded) {
        if (this.expanded != expanded) {
            this.expanded = expanded;

            setVisible(expanded);

            firePropertyChange(-1, PROPERTY_EXPANSION, !expanded, expanded);   
        }
    }

    /**
     * Touches the expansion state, i.e. fires the listeners on {@link #PROPERTY_EXPANSION} without
     * changing the value. This is required in combination with EMF Compare in case parts of the
     * model have been moved (and EMF Compare noticed that as such). In this case the
     * RenderingContextData are preserved but the diagram needs to be updated accordingly.<br>
     * <br>
     * Unfortunately old and new value must be different, so I set the old value to the inverse.
     */
    public void touchExpanded() {
        firePropertyChange(-1, PROPERTY_EXPANSION, !this.expanded, this.expanded);   
    }

    /**
     * Toggles the expansion state of <code>this</code> {@link KChildAreaNode}.
     */
    public void toggleExpansion() {
        setExpanded(!this.expanded);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        if (clip) {
            paintContext.pushClip(getBoundsReference());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintAfterChildren(final PPaintContext paintContext) {
        if (clip) {
            paintContext.popClip(getBoundsReference());
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
