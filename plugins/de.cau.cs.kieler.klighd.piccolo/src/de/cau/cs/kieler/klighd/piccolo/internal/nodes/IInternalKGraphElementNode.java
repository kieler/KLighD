/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.awt.geom.Rectangle2D;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode.IKGraphElementNode;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;

/**
 * Common interface of all KLighD-specific {@link edu.umd.cs.piccolo.PNode PNodes} representing the
 * structural view model elements {@link de.cau.cs.kieler.core.kgraph.KNode KNode},
 * {@link de.cau.cs.kieler.core.kgraph.KPort KPort}, {@link de.cau.cs.kieler.core.kgraph.KLabel
 * KLabel}, and {@link de.cau.cs.kieler.core.kgraph.KEdge KEdge}.
 *
 * @author mri
 * @author chsch
 *
 * @param <T>
 *            the concrete type of the {@link KGraphElement}
 */
public interface IInternalKGraphElementNode<T extends KGraphElement> extends IKGraphElementNode {

    /**
     * {@inheritDoc}
     */
    T getViewModelElement();

    /**
     * Setter for memorizing the rendering controller that is in charge of managing the correct
     * display and update of the diagram figures based on
     * {@link de.cau.cs.kieler.core.krendering.KRendering KRendering} definitions attached to the
     * {@link KGraphElement KGraphElements}.
     *
     * @param controller
     *            the deployed rendering controller
     */
    void setRenderingController(
            AbstractKGERenderingController<T, ? extends IInternalKGraphElementNode<T>> controller);

    /**
     * Getter for accessing the rendering controller that is in charge of managing the correct
     * display and update of the diagram figures based on
     * {@link de.cau.cs.kieler.core.krendering.KRendering KRendering} definitions attached to the
     * {@link KGraphElement KGraphElements}.
     *
     * @return the related rendering controller
     */
    AbstractKGERenderingController<T, ? extends IInternalKGraphElementNode<T>> getRenderingController();


    /**
     * Extension of {@link IInternalKGraphElementNode} required by (almost) all nodes representing
     * {@link KLabeledGraphElement KLabeledGraphElements}.
     *
     * @author mri
     * @author chsch
     *
     * @param <T>
     *            the concrete type of the {@link KLabeledGraphElement}
     */
    public interface IKLabeledGraphElementNode<T extends KLabeledGraphElement> extends
            IInternalKGraphElementNode<T> {

        /**
         * Adds the representation of a label to this element.
         *
         * @param label
         *            the label representation
         */
        void addLabel(KLabelNode label);
    }


    /**
     * Common interface {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode KNodeNode} and
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeTopNode KNodeTopNode}, which represent
     * represent {@link KNode KNodes}.
     *
     * @author mri
     * @author chsch
     */
    public interface IKNodeNode extends IInternalKGraphElementNode<KNode> {

        /**
         * Returns the child area of this parent node.
         *
         * @return the child area
         */
        KChildAreaNode getChildAreaNode();

        /**
         * Returns the parent {@link IKNodeNode} that (deeply) contains this {@link IKNodeNode}.
         *
         * @return the parent {@link IKNodeNode}
         */
        IKNodeNode getParentNode();

        /**
         * Returns the bounds of this node's exportable area that are required to fully export the
         * (visible) part(s) of this node in case it is completely shown as well as in case the diagram
         * is clipped to this node. In the latter case the node's figures are skipped, only the child
         * area, non-hidden ports, and non-hidden labels drawn.
         *
         * @return the adjusted bounds
         */
        Rectangle2D getExportedBounds();
    }
}