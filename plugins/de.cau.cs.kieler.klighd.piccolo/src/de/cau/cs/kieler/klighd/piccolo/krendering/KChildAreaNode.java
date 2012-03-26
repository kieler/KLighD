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
/**
 * 
 */
package de.cau.cs.kieler.klighd.piccolo.krendering;

import de.cau.cs.kieler.klighd.piccolo.nodes.PZIndexNode;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolo.util.PPickPath;

/**
 * A Piccolo node for the {@code KChildArea}.
 * 
 * @author mri
 */
public class KChildAreaNode extends PZIndexNode {

    private static final long serialVersionUID = -403773990520864787L;
    
    /** the property name for changes of the child areas expansion status. */
    public static final String PROPERTY_EXPANSION = "expansion";

    /** the number of z-layers (nodes and edges). */
    private static final int Z_LAYERS = 2;
    /** the z-index for the node layer. */
    private static final int NODE_LAYER = 0;
    /** the z-index for the edge layer. */
    private static final int EDGE_LAYER = 1;

    /** whether to clip nodes and edges. */
    private boolean clip = true;
    
    /** whether the child area is expanded. */
    private boolean expanded = false;

    /**
     * Constructs a child area for a given node.
     * 
     * @param containingNode
     *            the node containing this child area
     */
    public KChildAreaNode(final INode containingNode) {
        super(Z_LAYERS);
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
        addChild(node, NODE_LAYER);
    }
    
    /**
     * Adds a representation of an edge to this child area.
     * 
     * @param edge
     *            the edge representation
     */
    public void addEdge(final KEdgeNode edge) {
        addChild(edge, EDGE_LAYER);
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
            firePropertyChange(-1, PROPERTY_EXPANSION, !expanded, expanded);   
        }
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
    public void fullPaint(final PPaintContext paintContext) {
        if (expanded) {
            super.fullPaint(paintContext);
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
        // if this child area is not expanded don't pick anything
        if (!expanded) {
            return false;
        }
        
        // special picking when this child area has clipping enabled
        if (clip) {
            // never pick the child area and only pick children in the clipped area
            if (getVisible() && getChildrenPickable() && intersects(pickPath.getPickBounds())) {
                pickPath.pushNode(this);
                pickPath.pushTransform(getTransformReference(false));

                int count = getChildrenCount();
                for (int i = count - 1; i >= 0; i--) {
                    PNode child = getChild(i);
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
