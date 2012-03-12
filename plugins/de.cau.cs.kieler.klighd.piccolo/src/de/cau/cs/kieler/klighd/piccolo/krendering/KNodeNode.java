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

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.piccolo.krendering.controller.RenderingContextData;
import de.cau.cs.kieler.klighd.piccolo.nodes.PZIndexNode;

/**
 * The Piccolo node for representing a {@code KNode}.
 * 
 * @author mri
 */
public class KNodeNode extends PZIndexNode implements INode, ILabeledGraphElement {

    private static final long serialVersionUID = 6311105654943173693L;
    
    /** the number of z-layers (rendering, ports and labels). */
    private static final int Z_LAYERS = 3;
    /** the z-index for the label layer. */
    private static final int LABEL_LAYER = 2;
    /** the z-index for the port layer. */
    private static final int PORT_LAYER = 1;

    /** the encapsulated {@code KNode}. */
    private KNode node;
    /** the parent node. */
    private INode parent;

    /** the child area for this node. */
    private KChildAreaNode childArea = null;

    /**
     * Constructs a Piccolo node for representing a {@code KNode}.
     * 
     * @param node
     *            the node
     * @param parent
     *            the parent node
     */
    public KNodeNode(final KNode node, final INode parent) {
        super(Z_LAYERS);
        this.node = node;
        this.parent = parent;
        setPickable(true);
        RenderingContextData.get(node).setProperty(NODE_REP, this);
    }

    /**
     * {@inheritDoc}
     */
    public KNode getWrapped() {
        return node;
    }

    /**
     * Adds the representation of a port to this node.
     * 
     * @param port
     *            the port representation
     */
    public void addPort(final KPortNode port) {
        addChild(port, PORT_LAYER);
    }

    /**
     * Adds the representation of a label to this node.
     * 
     * @param label
     *            the label representation
     */
    public void addLabel(final KLabelNode label) {
        addChild(label, LABEL_LAYER);
    }
    
    /**
     * Sets the child area for this node.
     * 
     * @param childArea
     *            the child area
     */
    public void setChildArea(final KChildAreaNode childArea) {
        this.childArea = childArea;
    }

    /**
     * {@inheritDoc}
     */
    public INode getParentNode() {
        return parent;
    }

    /**
     * {@inheritDoc}
     */
    public KChildAreaNode getChildArea() {
        return childArea;
    }

}
