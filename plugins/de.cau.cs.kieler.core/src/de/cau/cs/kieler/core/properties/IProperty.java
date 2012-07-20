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
package de.cau.cs.kieler.core.properties;

/**
 * Interface for property identifiers. Properties have a type and a default value, and
 * they have an internal mechanism for identification, which should be compatible
 * with their {@link java.lang.Object#equals(Object)} and {@link java.lang.Object#hashCode()}
 * implementations.
 *
 * @kieler.design 2011-01-17 reviewed by haf, cmot, soh
 * @kieler.rating proposed yellow 2012-07-10 msp
 * @param <T> type of the property
 * @author msp
 */
public interface IProperty<T> {
    
    /**
     * Returns the default value of this property.
     * 
     * @return the default value, or {@code null} if the property has no default value
     */
    T getDefault();
    
    /**
     * Returns the lower bound of this property. If there is no lower bound, a
     * comparable is returned that is smaller than everything else.
     * 
     * @return the lower bound
     */
    Comparable<T> getLowerBound();
    
    /**
     * Returns the upper bound of this property. If there is no upper bound, a
     * comparable is returned that is greater than everything else.
     * 
     * @return the upper bound
     */
    Comparable<T> getUpperBound();
    
    /**
     * Returns an identifier string for this property.
     * 
     * @return an identifier
     */
    String getId();

}
