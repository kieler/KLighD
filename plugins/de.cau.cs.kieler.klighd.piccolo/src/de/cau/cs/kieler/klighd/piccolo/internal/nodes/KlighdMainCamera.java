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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IKGraphElementNode.INode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;

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
        return (INode) getDisplayedLayer();
    }

    /**
     * Getter.
     *
     * @return the currently displayed {@link INode} casted to {@link PLayer}.
     */
    public PLayer getDisplayedLayer() {
        if (this.getLayersReference().isEmpty()) {
            return null;
        }

        final PLayer res = this.getLayer(0);
        if (res instanceof INode) {
            return res;
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
     *            the {@link PLayer} to be now displayed, must be contained in the diagram's PNode
     *            figure tree!
     */
    public void exchangeDisplayedNode(final PLayer node) {

        final PNode prevNode = this.getDisplayedLayer();
        if (prevNode == node) {
            return;
        }

        // remove the currently displayed layer (a KNodeTopNode or KNodeNode)
        //  'this.removeLayer(prevNode)' would work as well but the displayed K...Node
        //  shall always the the first layer to be drawn, so..
        this.removeLayer(0);

        // In order keep the diagram part(s) being visible before and after the clipping operation
        //  at exactly the same position the main camera's view transform must be updated.
        // There 3 cases:
        //  a) the new clip node is (part of) a child of the current clip node
        //  b) the new clip node is a (parents') parent of the current clip node
        //  c) the new clip node is (child of) a sibling of the current clip node ('s parents)
        //  (the case new clip node == current clip node is catch above already)

        final AffineTransform t;
        if (prevNode.isAncestorOf(node)) {
            // For case a) the sum of the (x,y) vectors (the 'translates') of the nodes in
            //  between must be applied to the camera's view transform.
            // Since, while drawing, the camera applies its view transform first and for each layer
            //  being observed its particular transform (the (x,y) translate and optional scaling;
            //  see PCamera.paintCameraView on that) the required sum must also contain the current
            //  clip node's transform, as it was applied on top of the view transform as yet.
            // For that reason 'NodeUtil.localToParent(..., prevNode.getParent()) is called below.
            // Consequently, the new clip node's transform must not be part of the sum as this will
            //  applied on top of the (modified) view transform. This leads to
            //  'NodeUtil.localToParent(node.getParent(), ...)'.

            // Since node.getParent() returns the parent (i)node's kChildAreaNode an optional
            //  resizing, translation, and integration into the parent inode's figure of that
            //  child area node is automatically respected.

            t = NodeUtil.localToParent(node.getParent(), prevNode.getParent());

        } else if (node.isAncestorOf(prevNode)) {
            // Case b) is symmetric to a): child & ancestor are exchanged and the resulting
            //  transform must be inverted since it is to be applied "outward".

            t = NodeUtil.invert(NodeUtil.localToParent(prevNode.getParent(), node.getParent()));

        } else {
            // In case c) first the closest common ancestor (inode) is determined
            final INode commonAncestor = NodeUtil.getCommonAncestor((INode) prevNode, (INode) node);

            if (commonAncestor == null) {
                // ... which should not happen because
                // if 'prevNode' or 'node' is the root KNodeTopNode one of the above two cases
                //  is given (and if both denote the root KNodeTopNode the top most case is given),
                //  and otherwise the root KNodeTopNode is a common ancestor
                //  (as both 'prevNode' and 'node' are assumed to be contained in the figure tree)

                t = new AffineTransform();

            } else {
                // take the commonAncestor's child area node, ...
                final PNode caChildArea = commonAncestor.getChildAreaNode();

                // ... apply case b) between 'prevNode's parent (child area node) and
                //  'commonAncestor's child area node, ...
                t = NodeUtil.invert(NodeUtil.localToParent(prevNode.getParent(), caChildArea));

                // ... and apply case a) between 'node's parent (child area node) and
                //  'commonAncestor's child area node.
                t.concatenate(NodeUtil.localToParent(node.getParent(), caChildArea));
            }
        }

        // note that the following view transform update will NOT cause any property change notification
        this.getViewTransformReference().concatenate(t);
        this.addLayer(0, node);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void fullPaint(final PPaintContext paintContext) {
        try {
            // In case the following call fails the SWT components employed in
            //  PSWCanvas#paintComponent(...) may end up in an inconsistent state.
            // Hence, this try catch block is added here in order to let (at least)
            //  this#fullPaint(...) return properly.
            super.fullPaint(paintContext);

        } catch (final Throwable t) {
            final String msg = "KLighD (MainCamera): Drawing diagram failed due to exception: ";
            KlighdPiccoloPlugin.getDefault().getLog()
                    .log(new Status(IStatus.ERROR, KlighdPiccoloPlugin.PLUGIN_ID, msg, t));
        }
    }
}
