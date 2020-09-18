/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd;

import java.util.Iterator;
import java.util.List;

import org.eclipse.elk.graph.properties.IProperty;

/**
 * Interface to register properties that are created during layout and should be preserved and added to the KGraph.
 * 
 * @author sdo
 */
public interface IPreservedProperties extends Iterable<IProperty<?>> {
    
    /**
     * A List of all properties that should be preserved.
     * 
     * @return The preserved properties.
     */
    public List<IProperty<?>> getProperties();
    
    default Iterator<IProperty<?>> iterator() {
        return getProperties().iterator();
    }

}
