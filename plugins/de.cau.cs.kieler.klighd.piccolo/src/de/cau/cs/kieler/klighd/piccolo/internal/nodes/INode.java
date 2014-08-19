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
/**
 * 
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * The interface for Piccolo2D nodes that represent a {@link KNode} (currently {@link KNodeNode} and
 * {@link KNodeTopNode}).
 * 
 * @author mri
 * @author chsch
 */
public interface INode extends IGraphElement<KNode> {

    /**
     * Returns the child area of this parent node.
     * 
     * @return the child area
     */
    KChildAreaNode getChildAreaNode();

    /**
     * Returns the parent {@link INode} that (deeply) contains this {@link INode}.
     * 
     * @return the parent {@link INode}
     */
    INode getParentNode();

    /**
     * Provides the permission of the corresponding {@link KNode} to be selected.
     * 
     * @see KlighdNode#isSelectable()
     * 
     * @return <code>true</code> if the corresponding {@link KNode} is allowed to be selected,
     *         <code>false</code> otherwise.
     */
    boolean isSelectable();
}
