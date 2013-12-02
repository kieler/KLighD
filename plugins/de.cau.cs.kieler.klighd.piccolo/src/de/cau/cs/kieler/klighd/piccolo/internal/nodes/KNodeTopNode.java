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
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.util.PPickPath;

/**
 * The dedicated root node of our Piccolo2D-powered KLighD diagrams.
 * Nodes of this type represent the top-level {@link KNode} of KGraph+KRendering view models.
 * 
 * @author mri
 * @author chsch
 */
public class KNodeTopNode extends PLayer implements INode {

    private static final long serialVersionUID = 8395163186723344696L;

    /** the encapsulated {@code KNode}. */
    private transient KNode node;

    /** the Piccolo2D node representing the child area. */
    private KChildAreaNode childArea;

    /**
     * Constructs a Piccolo2D node for representing the top-level {@link KNode}.
     * 
     * @param node
     *            the KNode
     */
    public KNodeTopNode(final KNode node) {
        this.setPickable(true);        
        this.node = node;
        
        childArea = new KChildAreaNode(this);
        childArea.setPickable(true);
        childArea.setClip(false);
        
        this.addChild(childArea);
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
        String s = "KLighD: Invalid access occured: invoking setRenderingController()"
                + "is not allowed for KNodeTopNodes!";
        throw new UnsupportedOperationException(s);
    }

    /**
     * {@inheritDoc}
     */
    public AbstractKGERenderingController<KNode, KNodeNode> getRenderingController() {
        String s = "KLighD: Invalid access occured: calling getRenderingController()"
                        + "is not allowed for KNodeTopNodes!";
        throw new UnsupportedOperationException(s);
    }
    
    /**
     * {@inheritDoc}
     */
    public KChildAreaNode getChildArea() {
        return childArea;
    }
    
    /**
     * {@inheritDoc}
     */
    public INode getParentNode() {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean fullPick(final PPickPath pickPath) {
        final boolean fullPick = super.fullPick(pickPath);
        
        if (!fullPick && pickAfterChildren(pickPath)) {
            pickPath.pushNode(this);
            pickPath.pushTransform(getTransform());
            
            return true;
        }
        return fullPick;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean pickAfterChildren(final PPickPath pickPath) {
        return true;
    }
}
