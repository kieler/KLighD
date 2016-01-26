/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo;

import java.awt.geom.Rectangle2D;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KRendering;
import edu.umd.cs.piccolo.PNode;

/**
 * Common interface of all KLighD-specific {@link edu.umd.cs.piccolo.PNode PNodes} representing view
 * model elements.
 *
 * @author chsch
 */
public interface IKlighdNode {

    /**
     * Property name definition for indicating the completed update of a {@link IKlighdNode}'s
     * {@link edu.umd.cs.piccolo.PNode#getBoundsReference() bounds}, e.g. after animations are
     * finished.<br>
     * Such events are fired by the KLighD runtime and must not be fired by by application code!
     */
    String PROPERTY_BOUNDS_FINISHED = "boundsFinished";

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
     * @return this node's bounds object as defined by {@link PNode#getBoundsReference()}
     */
    Rectangle2D getBoundsReference();
    
    /**
     * Convenience getter.
     * 
     * @return <code>this</code> {@link IKlighdNode} casted to {@link PNode}
     */
    PNode asPNode();
    
    /**
     * @param child the child {@link IKlighdNode} to be added to the node's list of children
     * 
     * @see PNode#addChild(PNode)
     */
    void addChild(IKlighdNode child);

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
         * @return the {@link KGraphElement} represented by this {@link IKGraphElementNode}
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
         * The property name used while notifying changes of {@link IKNodeNode}'s expansion status.<br>
         * <b>Caution!</b> Don't let listeners rely on the propagated old value as that might not be
         * correct in case a notification is fired via
         * {@link de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeAbstractNode#touchExpanded()
         * KNodeAbstractNode#touchExpanded()}.
         */
        String PROPERTY_EXPANSION = "expansion";

        /**
         * @return the {@link KNode} represented by this {@link IKNodeNode}
         */
        KNode getViewModelElement();

        /**
         * @return the parent {@link IKNodeNode} that (deeply) contains this {@link IKNodeNode}
         */
        IKNodeNode getParentKNodeNode();

        /**
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
         * @return the {@link KRendering} represented by this {@link IKlighdFigureNode}, maybe
         *         <code>null</code> if this figure node does not represent a {@link KRendering} but
         *         is part of a custom figure contributed via a
         *         {@link de.cau.cs.kieler.core.krendering.KCustomRendering KCustomRendering}, for
         *         example.
         */
        KRendering getViewModelElement();
    }
}
