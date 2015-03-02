/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KRendering;

/**
 * Common interface of all KLighD-specific {@link edu.umd.cs.piccolo.PNode PNodes} representing view
 * model elements.
 *
 * @author chsch
 */
public interface IKlighdNode {

    /**
     * @return the view model element ({@link de.cau.cs.kieler.core.kgraph.KGraphElement
     *         KGraphElement} or {@link de.cau.cs.kieler.core.krendering.KRendering KRendering})
     *         represented by this {@link IKlighdNode}.
     */
    EObject getViewModelElement();

    /**
     * Provides the permission of the corresponding
     * {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement}/
     * {@link de.cau.cs.kieler.core.krendering.KRendering KRendering} to be selected.
     *
     * @return <code>true</code> if the corresponding
     *         {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement}/
     *         {@link de.cau.cs.kieler.core.krendering.KRendering KRendering} is allowed to be
     *         selected, <code>false</code> otherwise.
     */
    boolean isSelectable();

    /**
     * Common interface of all KLighD-specific {@link edu.umd.cs.piccolo.PNode PNodes} representing the
     * structural view model elements {@link de.cau.cs.kieler.core.kgraph.KNode KNode},
     * {@link de.cau.cs.kieler.core.kgraph.KPort KPort}, {@link de.cau.cs.kieler.core.kgraph.KLabel
     * KLabel}, and {@link de.cau.cs.kieler.core.kgraph.KEdge KEdge}.
     *
     * @author chsch
     */
    public interface IKGraphElementNode extends IKlighdNode {

        /**
         * @return the {@link KGraphElement} represented by this  {@link edu.umd.cs.piccolo.PNode PNode}
         */
        KGraphElement getViewModelElement();
    }

    /**
     * Common interface of all KLighD-specific {@link edu.umd.cs.piccolo.PNode PNodes} representing
     * view model {@link KNode KNodes}.<br>
     * This interface can be relied on in custom figure implementation for navigating on the figure
     * tree, e.g., for filtering the parent figures by means of <code>instanceof</code>.
     *
     * @author chsch
     */
    public interface IKNodeNode extends IKGraphElementNode {

        /**
         * The property name for changes of the child areas expansion status.<br>
         * <b>Caution!</b> Don't let listeners rely on the propagated old value as that might not be
         * correct in case they are fired via {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.
         * IInternalKGraphElementNode.IInternalKNodeNode#touchExpanded()
         * IInternalKNodeNode#touchExpanded()}.
         */
        String PROPERTY_EXPANSION = "expansion";

        /**
         * @return the {@link KNode} represented by this {@link edu.umd.cs.piccolo.PNode PNode}
         */
        KNode getViewModelElement();

        /**
         * Returns the parent {@link IKNodeNode} that (deeply) contains this {@link IKNodeNode}.
         *
         * @return the parent {@link IKNodeNode}
         */
        IKNodeNode getParentNode();

        /**
         * Returns whether this {@link IKNodeNode} is expanded.
         *
         * @return <code>true</code> if this {@link IKNodeNode} is expanded, <code>false</code>
         *         otherwise
         */
        boolean isExpanded();
    }

    /**
     * Common interface of all KLighD-specific {@link edu.umd.cs.piccolo.PNode PNodes} representing the
     * figure defining view model elements, which are subtypes of {@link KRendering}.
     *
     * @author chsch
     */
    public interface IKlighdFigureNode extends IKlighdNode {

        /**
         * @return the {@link KRendering} represented by this {@link edu.umd.cs.piccolo.PNode PNode}
         *         , maybe <code>null</code> if this figure node does not represent a
         *         {@link KRendering} but is part of a
         *         {@link de.cau.cs.kieler.core.krendering.KCustomRendering KCustomRendering}, for
         *         example.
         */
        KRendering getViewModelElement();
    }
}
