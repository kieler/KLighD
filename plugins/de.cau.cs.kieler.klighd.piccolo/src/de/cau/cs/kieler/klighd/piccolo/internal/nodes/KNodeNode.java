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

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.KNodeRenderingController;

/**
 * The Piccolo node for representing a {@code KNode}.
 * 
 * @author mri
 */
public class KNodeNode extends PZIndexNode implements INode, ILabeledGraphElement<KNode> {

    private static final long serialVersionUID = 6311105654943173693L;
    
    /** the number of z-layers (rendering, ports and labels). */
    private static final int Z_LAYERS = 3;
    /** the z-index for the label layer. */
    private static final int LABEL_LAYER = 2;
    /** the z-index for the port layer. */
    private static final int PORT_LAYER = 1;

    /** the parent node. */
    private INode parent;
    /** the represented {@link KNode}. */
    private KNode node;
    /** the node rendering controller deployed to manage the rendering of {@link #node}. */
    private KNodeRenderingController renderingController;

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
        Boolean b = node.getData(KShapeLayout.class).getProperty(
                KlighdConstants.KLIGHD_SELECTION_UNPICKABLE);
        setPickable(b != null && b.equals(Boolean.TRUE) ? false : true);
    }

    /**
     * {@inheritDoc}
     */
    public KNode getGraphElement() {
        return node;
    }
    
    /**
     * {@inheritDoc}
     */
    public void setRenderingController(
            final AbstractKGERenderingController<KNode, ? extends IGraphElement<KNode>> controller) {
        if (controller == null || controller instanceof KNodeRenderingController) {
            this.renderingController = (KNodeRenderingController) controller;
        } else {
            String s = "KLighD: Fault occured while building up a concrete KNode rendering: KNodeNodes"
                    + " are supposed to be controlled by KNodeRenderingControllers rather than "
                    + controller.getClass().getCanonicalName();
            throw new IllegalArgumentException(s);
        }
    }

    /**
     * {@inheritDoc}
     */
    public KNodeRenderingController getRenderingController() {
        return this.renderingController;
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
