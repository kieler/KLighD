/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Stack;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode.IKNodeNode;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.PRoot;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolo.util.PPickPath;

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
     *
     * @param root
     *            the {@link PRoot} to add <code>this</code> camera to, may be <code>null</code>
     */
    public KlighdMainCamera(final PRoot root) {
        super();

        if (root != null) {
            root.addChild(this);
        }
    }

    /**
     * Getter.
     *
     * @return the currently displayed {@link KNodeAbstractNode}
     */
    public KNodeAbstractNode getDisplayedKNodeNode() {
        if (this.getLayersReference().isEmpty()) {
            return null;
        }

        final PLayer res = this.getLayer(0);
        if (res instanceof KNodeAbstractNode) {
            return (KNodeAbstractNode) res;
        } else {
            return null;
        }
    }

    /**
     * Sets the {@link KNodeAbstractNode} to be displayed on the canvas if it is a {@link PLayer};
     * does nothing otherwise.
     *
     * @param node
     *            the {@link KNodeAbstractNode} to displayed
     */
    public void setDisplayedKNodeNode(final KNodeAbstractNode node) {
        this.addLayer(0, node);

        if (node instanceof KNodeTopNode) {
            // this is only for initialization, has no effect later on
            ((KNodeTopNode) node).setDiagramMainCamera(this);
        }
    }

    private boolean clipsPortsHidden = false;
    private boolean clipsLabelsHidden = false;

    public void initClipsPortAndLabelsVisibility(final boolean hideClipNodePorts, final boolean hideClipNodeLabels) {
        this.clipsPortsHidden = hideClipNodePorts;
        this.clipsLabelsHidden = hideClipNodeLabels;
    }

    /**
     * Returns whether the ports of the represented {@link KNode} shall be invisible if the diagram is
     * clipped to that {@link KNode}.
     * 
     * @return <code>true</code> if ports shall be invisible if the diagram is clipped to the
     *         represented {@link KNode}, <code>false</code> otherwise.
     */
    public boolean isClipsPortsHidden() {
        return this.clipsPortsHidden;
    }

    /**
     * Returns whether the labels of the clipped {@link KNode} shall be invisible.
     * 
     * @return <code>true</code> if labels shall be invisible if the diagram is clipped to the
     *         represented {@link KNode}, <code>false</code> otherwise.
     */
    public boolean isClipsLabelsHidden() {
        return this.clipsLabelsHidden;
    }

    /**
     * Re-targets <code>this</code> camera to the given <code>node</code> by detaching the currently
     * displayed {@link PLayer}, if the <code>node</code> is a {@link PLayer}; does nothing
     * otherwise.
     *
     * @param node
     *            the {@link KNodeAbstractNode} to be now displayed, must be contained in the
     *            diagram's PNode figure tree!
     */
    public void exchangeDisplayedKNodeNode(final KNodeAbstractNode node,
            final Boolean hideClipNodePorts, final Boolean hideClipNodeLabels) {

        final KNodeAbstractNode prevNode = this.getDisplayedKNodeNode();
        final boolean invalidatePaintRequired = (
                hideClipNodePorts != null
                    && hideClipNodePorts.booleanValue() != prevNode.isDiagramClipWithPortsHidden()
                || hideClipNodeLabels != null
                    && hideClipNodeLabels.booleanValue() != prevNode.isDiagramClipWithLabelsHidden());

        if (hideClipNodePorts != null) {
            node.setPortsHiddenWhenClipped(hideClipNodePorts.booleanValue(), true);
        } else {
            node.setPortsHiddenWhenClipped(this.clipsPortsHidden, false);
        }

        if (hideClipNodeLabels != null) {
            node.setLabelsHiddenWhenClipped(hideClipNodeLabels.booleanValue(), true);
        } else {
            node.setLabelsHiddenWhenClipped(this.clipsLabelsHidden, false);
        }

        if (node == prevNode) {
            if (invalidatePaintRequired) {
                this.invalidatePaint();
            }
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
            // In case c) first the closest common ancestor (iKNodeNode) is determined
            final IKNodeNode commonAncestor = NodeUtil.getCommonAncestor(prevNode, node);

            if (commonAncestor == null) {
                // ... which should not happen because
                // if 'prevNode' or 'node' is the root KNodeTopNode one of the above two cases
                //  is given (and if both denote the root KNodeTopNode the top most case is given),
                //  and otherwise the root KNodeTopNode is a common ancestor
                //  (as both 'prevNode' and 'node' are assumed to be contained in the figure tree)

                t = new AffineTransform();

            } else {
                // take the commonAncestor's child area node, ...
                final PNode caChildArea = ((KNodeAbstractNode) commonAncestor).getChildAreaNode();

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
     * A specialized {@link PPickPath}.
     */
    public static class KlighdPickPath extends PPickPath {

        private double cameraZoomScale = 1d;
        private final Stack<Double> cameraScales = new Stack<Double>();

        /**
         * Creates a pick pack originating from the provided camera and with the given screen pick
         * bounds.
         *
         * @param camera
         *            camera from which the pickpath originates
         * @param aScreenPickBounds
         *            bounds of pick area
         */
        public KlighdPickPath(final KlighdMainCamera camera, final PBounds aScreenPickBounds) {
            super(camera, aScreenPickBounds);

            // I shamelessly assume that scaleX == scaleY ;-)
            this.cameraZoomScale = camera.getViewTransformReference().getScaleX();
        }

        /**
         * Provides the current diagram zoom factor as determined by the active
         * {@link KlighdMainCamera}'s view {@link java.awt.geom.AffineTransform transform}, adjusted
         * by the picked parent {@link KNodeNode}'s scale settings.
         *
         * @return the current diagram zoom factor
         */
        public double getCameraZoomScale() {
            return cameraZoomScale;
        }

        /**
         * Saves the (adjusted) {@link #cameraZoomScale} and applies <code>scale</code> to the current
         * value. Is intended to be called from
         * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KChildAreaNode KChildAreaNode} only!
         *
         * @param scale
         *            the scale factor to be applied to the current {@link #cameraZoomScale}
         */
        public void pushNodeScale(final double scale) {
            this.cameraScales.push(cameraZoomScale);
            this.cameraZoomScale *= scale;
        }

        /**
         * Restores the previous logged (adjusted) camera zoom scale.<br>
         * Is intended to be called from
         * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KChildAreaNode KChildAreaNode} only!
         */
        public void popNodeScale() {
            this.cameraZoomScale = cameraScales.pop();
        }
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * Had to copy this method from {@link PCamera} in order to inject the specialized
     * {@link KlighdPickPath}.
     */
    @Override
    public PPickPath pick(final double x, final double y, final double halo) {
        final PBounds b = new PBounds(new Point2D.Double(x, y), -halo, -halo);
        final PPickPath result = new KlighdPickPath(this, b);

        fullPick(result);

        // make sure this camera is pushed.
        if (result.getNodeStackReference().size() == 0) {
            result.pushNode(this);
            result.pushTransform(getTransformReference(false));
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fullPaint(final PPaintContext paintContext) {
        try {
            // In case the following call fails the SWT components employed in
            //  PSWTCanvas#paintComponent(...) may end up in an inconsistent state.
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
