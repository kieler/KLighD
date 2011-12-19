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
 * The interface for ports attached to nodes.
 * 
 * @author mri
 */
public interface IGraphPort extends IGraphPositional, IGraphObject {

    /**
     * Returns all outgoing edges of this port.
     * 
     * @return the outgoing edges
     */
    List<IGraphEdge> getOutgoingEdges();
    
}
