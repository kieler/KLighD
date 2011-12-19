/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.graph;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * The interface for edges in the graph.
 * 
 * @author mri
 */
public interface IGraphEdge extends IGraphObject {

    /**
     * Returns the source node.
     * 
     * @return the source node
     */
    IGraphNode getSource();

    /**
     * Returns the target node.
     * 
     * @return the target node
     */
    IGraphNode getTarget();

    /**
     * Returns the source port this edge connects to if available.
     * 
     * @return the source port or null if the source is not connected to a port
     */
    IGraphPort getSourcePort();

    /**
     * Returns the target port this edge connects to if available.
     * 
     * @return the target port or null if the target is not connected to a port
     */
    IGraphPort getTargetPort();

    /**
     * Sets the bend points including the points at the nodes or ports the edge attaches to.
     * 
     * @param bends
     *            the bend points (at least two)
     */
    void setBends(List<Point2D> bends);

    /**
     * Returns the bend points including the points at the nodes or ports the edge attaches to.
     * 
     * @return the bend points (at least two)
     */
    List<Point2D> getBends();

}
