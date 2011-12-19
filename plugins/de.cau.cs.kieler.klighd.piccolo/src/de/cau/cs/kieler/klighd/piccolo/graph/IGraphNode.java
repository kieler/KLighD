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

import java.util.List;

/**
 * The interface for nodes in the graph.
 * 
 * @author mri
 */
public interface IGraphNode extends IGraphParent {

    /**
     * Returns all ports attached to this node.
     * 
     * @return the ports
     */
    List<IGraphPort> getPorts();

    /**
     * Returns all outgoing edges of this node, including edges attached to ports.
     * 
     * @return the outgoing edges
     */
    List<IGraphEdge> getOutgoingEdges();

}
