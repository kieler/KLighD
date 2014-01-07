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
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.awt.geom.AffineTransform;

import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;

/**
 * This specialized {@link PCamera} type describes the diagram root cameras.<br>
 * It is not intended to be used for any other purpose! 
 * 
 * @author chsch
 */
public class KlighdMainCamera extends PCamera {

    private static final long serialVersionUID = -1769999483311436492L;
    
    /**
     * Constructor.
     */
    public KlighdMainCamera() {
        super();
    }

    /**
     * Getter.
     * 
     * @return the currently displayed {@link INode}
     */
    public INode getDisplayedINode() {
        final PLayer res = this.getLayer(0);
        if (res instanceof INode) {
            return (INode) res;
        } else {
            return null;
        }
    }
    
    /**
     * Sets the {@link INode} to be displayed on the canvas if it is a {@link PLayer}; does nothing
     * otherwise.
     * 
     * @param node
     *            the {@link INode} to displayed
     */
    public void setDisplayedNode(final INode node) {
        if (node instanceof PLayer) {
            this.setDisplayedNode((PLayer) node);
        }
        if (node instanceof KNodeTopNode) {
            // this is only for initialization, has no effect later on
            ((KNodeTopNode) node).setDiagramMainCamera(this);
        }
    }
    
    /**
     * Sets the {@link PLayer} to be displayed on the canvas.
     * 
     * @param node the {@link PLayer} to displayed
     */
    private void setDisplayedNode(final PLayer node) {
        this.addLayer(0, node);
    }
    
    /**
     * Re-targets <code>this</code> camera to the given <code>node</code> by detaching the currently
     * displayed {@link PLayer}, if the <code>node</code> is a {@link PLayer}; does nothing otherwise.
     * 
     * @param node
     *            the {@link INode} to be now displayed
     */
    public void exchangeDisplayedNode(final INode node) {
        if (node instanceof PLayer) {
            exchangeDisplayedNode((PLayer) node);
        }
    }

    /**
     * Detaches the currently configures displayed {@link PLayer} and re-target to the given
     * <code>node</code>.
     * 
     * @param node
     *            the {@link PLayer} to be now displayed
     */
    public void exchangeDisplayedNode(final PLayer node) {
        
        final PNode prevNode = (PNode) this.getLayer(0);
        
        if (prevNode == node) {
            return;
        }
        
        this.removeLayer(0);

        final AffineTransform t;

        if (prevNode.isAncestorOf(node)) {
            t = NodeUtil.localToParent(node.getParent(), prevNode.getParent());
        } else if (node.isAncestorOf(prevNode)) {
            t = NodeUtil.inverse(NodeUtil.localToParent(prevNode.getParent(), node.getParent()));
        } else {
            // TODO this case should be implemented some day
            t = new AffineTransform();
        }

        this.getViewTransformReference().concatenate(t);
        this.addLayer(0, node);
    }
}
