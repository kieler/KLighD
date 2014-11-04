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

import java.awt.geom.Rectangle2D;

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
     * Returns the bounds of this node's exportable area that are required to fully export the
     * (visible) part(s) of this node in case it is completely shown as well as in case the diagram
     * is clipped to this node. In the latter case the node's figures are skipped, only the child
     * area, non-hidden ports, and non-hidden labels drawn.
     *
     * @return the adjusted bounds
     */
    Rectangle2D getExportedBounds();

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
