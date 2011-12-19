/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.util;

import java.util.List;

/**
 * An interface for classes that can be identified by an identifier and have
 * dependencies on other objects.
 * 
 * @author mri
 * 
 * @param <T>
 *            a comparable identifier type (commonly String or Integer)
 */
public interface IDepending<T extends Comparable<T>> {

    /**
     * Returns the (unique) identifier for this object.
     * 
     * @return the identifier
     */
    T getId();

    /**
     * Returns a list of dependencies.
     * 
     * @return the list of dependencies or null if the object has no
     *         dependencies
     */
    List<Dependency<T>> getDependencies();
}
