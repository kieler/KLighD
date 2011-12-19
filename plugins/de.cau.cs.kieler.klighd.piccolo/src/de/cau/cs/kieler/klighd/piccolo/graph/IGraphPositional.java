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
package de.cau.cs.kieler.klighd.piccolo.graph;

import edu.umd.cs.piccolo.util.PBounds;

/**
 * The interface for all objects in a graph which have a position and bounds.
 * 
 * @author mri
 */
public interface IGraphPositional {

    /**
     * Sets the bounds of this object.
     * 
     * @param bounds
     *            the bounds
     */
    void setRelativeBounds(PBounds bounds);
    
    /**
     * Returns the bounds of this object.
     * 
     * @return the bounds
     */
    PBounds getRelativeBounds();
    
}
