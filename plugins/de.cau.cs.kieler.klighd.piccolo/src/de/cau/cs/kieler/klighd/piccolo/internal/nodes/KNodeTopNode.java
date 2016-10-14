/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.awt.geom.Rectangle2D;

import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;
import edu.umd.cs.piccolo.util.PPickPath;

/**
 * The dedicated root node of our Piccolo2D-powered KLighD diagrams.
 * Nodes of this type represent the top-level {@link KNode} of KGraph+KRendering view models.
 *
 * @author mri
 * @author chsch
 */
public class KNodeTopNode extends KNodeAbstractNode {

    private static final long serialVersionUID = 8395163186723344696L;

    /** the main camera of the diagram headed by this top node. */
    private KlighdMainCamera diagramMainCamera = null;

    /**
     * Constructs a Piccolo2D node for representing the top-level {@link KNode}.
     *
     * @param node
     *            the represented {@link KNode}
     * @param edgesFirst
     *            determining whether edges are drawn before nodes, i.e. nodes have priority over
     *            edges
     */
    public KNodeTopNode(final KNode node, final boolean edgesFirst) {
        super(node, edgesFirst);

        // add the child area immediately to the children list,
        //  this is not done in the super constructor as it must not be done in KNodeNode
        this.addChild(childArea.asPNode());
    }

    /**
     * Sets the main camera of the diagram headed by this top node. This method may currently only
     * be called from {@link KlighdMainCamera#setDisplayedKNodeNode(KNodeAbstractNode))}.
     *
     * @param camera
     */
    void setDiagramMainCamera(final KlighdMainCamera camera) {
        if (this.diagramMainCamera == null) {
            this.diagramMainCamera = camera;
        }
    }

    /**
     * Getter.
     *
     * @return the main camera of the diagram headed by this top node
     */
    public KlighdMainCamera getDiagramMainCamera() {
        return this.diagramMainCamera;
    }

    /**
     * {@inheritDoc}
     */
    public void setRenderingController(final AbstractKGERenderingController<KNode,
            ? extends IInternalKGraphElementNode<KNode>> controller) {

        final String s = "KLighD: Invalid access occured: invoking setRenderingController()"
                + "is not allowed for KNodeTopNodes!";
        throw new UnsupportedOperationException(s);
    }

    /**
     * {@inheritDoc}
     */
    public AbstractKGERenderingController<KNode, KNodeNode> getRenderingController() {
        final String s = "KLighD: Invalid access occured: calling getRenderingController()"
                        + "is not allowed for KNodeTopNodes!";
        throw new UnsupportedOperationException(s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KNodeAbstractNode getParentKNodeNode() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setExpanded(final boolean expanded) {
//        if (expanded) {
            super.setExpanded(expanded);
//        }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle2D getExportedBounds() {
        return this.getUnionOfChildrenBounds(null);
    }
}
