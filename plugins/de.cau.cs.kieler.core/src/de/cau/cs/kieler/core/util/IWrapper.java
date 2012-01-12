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
package de.cau.cs.kieler.core.util;

/**
 * An interface for wrapper classes.
 * 
 * @author mri
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public interface IWrapper<T> {

    /**
     * Returns the wrapped object.
     * 
     * @return the wrapped object
     */
    T getWrapped();

}
