/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode.IKGraphElementNode;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;

/**
 * Common interface of all KLighD-specific {@link edu.umd.cs.piccolo.PNode PNodes} representing the
 * structural view model elements {@link de.cau.cs.kieler.klighd.kgraph.KNode KNode},
 * {@link de.cau.cs.kieler.klighd.kgraph.KPort KPort}, {@link de.cau.cs.kieler.klighd.kgraph.KLabel
 * KLabel}, and {@link de.cau.cs.kieler.klighd.kgraph.KEdge KEdge}.
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
     * {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering} definitions attached to the
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
     * {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering} definitions attached to the
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
}