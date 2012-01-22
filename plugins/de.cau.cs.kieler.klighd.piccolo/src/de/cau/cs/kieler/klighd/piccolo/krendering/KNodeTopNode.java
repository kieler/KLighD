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
package de.cau.cs.kieler.klighd.piccolo.krendering;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.IWrapper;
import de.cau.cs.kieler.klighd.piccolo.nodes.PEmptyNode;

/**
 * The Piccolo node for representing the top-level {@code KNode}.
 * 
 * @author mri
 */
public class KNodeTopNode extends PEmptyNode implements IParent, IWrapper<KNode> {

    private static final long serialVersionUID = 8395163186723344696L;

    /** the encapsulated {@code KNode}. */
    private KNode node;

    /**
     * Constructs a Piccolo node for representing the top-level {@code KNode}.
     * 
     * @param node
     *            the KNode
     */
    public KNodeTopNode(final KNode node) {
        this.node = node;
    }

    /**
     * {@inheritDoc}
     */
    public KNode getWrapped() {
        return node;
    }

    /**
     * {@inheritDoc}
     */
    public void expand() {
        // create nodes
        for (KNode child : node.getChildren()) {
            // create the Piccolo node for the child node
            KNodeNode nodeNode = new KNodeNode(child);
            nodeNode.updateLayout();
            addChild(nodeNode);

            // create the node's rendering
            nodeNode.createRendering();
            nodeNode.expand();
        }
        
        // create edges
        for (KNode child : node.getChildren()) {
            for (KEdge edge : child.getOutgoingEdges()) {
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
     * {@inheritDoc}
     */
    public void collapse() {
        // do nothing, the top-level node cannot be collapsed
    }

}
