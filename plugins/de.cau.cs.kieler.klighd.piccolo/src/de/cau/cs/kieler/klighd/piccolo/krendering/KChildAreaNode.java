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

import java.util.List;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.klighd.piccolo.nodes.PZIndexNode;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * A Piccolo node for the {@code KChildArea}.
 * 
 * @author mri
 */
public class KChildAreaNode extends PZIndexNode {

    private static final long serialVersionUID = -403773990520864787L;

    /** the property for remembering load delayed target edges. */
    private static final IProperty<List<KEdge>> DELAYED_TARGET_EDGES = new Property<List<KEdge>>(
            "klighd.piccolo.delayedTargetEges");

    /** the number of z-layers (nodes and edges). */
    private static final int Z_LAYERS = 2;
    /** the z-index for the node layer. */
    private static final int NODE_LAYER = 0;
    /** the z-index for the edge layer. */
    private static final int EDGE_LAYER = 1;

    /** the node containing this child area. */
    private INode containingNode;

    /** whether to clip nodes and edges. */
    private boolean clip = true;

    /**
     * Constructs a child area for a given node.
     * 
     * @param containingNode
     *            the node containing this child area
     */
    public KChildAreaNode(final INode containingNode) {
        super(Z_LAYERS);
        this.containingNode = containingNode;
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
     * Populates the child area with contents from the given node.
     * 
     * @param node
     *            the node
     */
    public void populate(final KNode node) {
        // create the nodes
        for (KNode child : node.getChildren()) {
            // create the Piccolo node for the child node
            KNodeNode nodeNode = new KNodeNode(child, containingNode);
            addNode(nodeNode);
        }

        // create the edges
        for (KNode child : node.getChildren()) {
            for (KEdge edge : child.getOutgoingEdges()) {
                KNode target = edge.getTarget();
                KNode parent = findLowestCommonAncestor(edge.getSource(), edge.getTarget());
                // create the Piccolo node for the edge
                KEdgeNode edgeNode = new KEdgeNode(edge);
                edgeNode.updateLayout();
                addChild(edgeNode);

                // create the edge's rendering
                edgeNode.createRendering();
            }
        }
    }

    /**
     * Adds a node to this child area.
     * 
     * @param node
     *            the node
     */
    private void addNode(final KNodeNode node) {
        node.updateLayout();
        addChild(node, NODE_LAYER);
        node.createRendering();
        // TODO remove auto expand when other means are available
        node.expand();
    }

    /**
     * Adds an edge to this child area.
     * 
     * @param edge
     *            the edge
     */
    private void addEdge(final KEdgeNode edge) {
        addChild(edge, EDGE_LAYER);
    }

    /**
     * Returns the lowest common ancestor to both given nodes.
     * 
     * @param initialNode1
     *            the first node
     * @param initialNode2
     *            the second node
     * @return the lowest common ancestor
     */
    private KNode findLowestCommonAncestor(final KNode initialNode1, final KNode initialNode2) {
        KNode node1 = initialNode1.getParent();
        while (node1 != null) {
            KNode node2 = initialNode2.getParent();
            while (node2 != null) {
                if (node1 == node2) {
                    // common ancestor found
                    return node1;
                }
                node2 = node2.getParent();
            }
            node1 = node1.getParent();
        }

        // no common ancestor
        return null;
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

}
