/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.properties;

/**
 * A proxy object for properties that are resolved lazily.
 *
 * @kieler.rating proposed yellow 2012-11-06 cds
 * @kieler.design proposed 2012-11-06 cds
 * @author msp
 */
public interface IPropertyValueProxy {
    
    /**
     * Resolve the value associated with the given property.
     * 
     * @param <T> property type
     * @param property a property
     * @return the corresponding value, or {@code null} if the value cannot be resolved
     */
    <T> T resolveValue(IProperty<T> property);

}
