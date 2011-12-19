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

/**
 * The interface for all objects in a graph.
 * 
 * @author mri
 */
public interface IGraphObject {
    
    /**
     * Returns a client attribute for a given key.
     * Enables the use of {@link edu.umd.cs.piccolo.PNode#getAttribute(Object)}.
     * 
     * @author chsch
     * @param o attribute key
     * @return attribute value
     */
    Object getAttribute(Object o);

}
