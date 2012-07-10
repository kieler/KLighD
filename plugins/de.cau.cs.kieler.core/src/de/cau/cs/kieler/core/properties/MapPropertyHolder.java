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

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * An abstract holder class for properties that uses a hash map.
 *
 * @kieler.design 2011-01-17 reviewed by haf, cmot, soh
 * @author msp
 */
public class MapPropertyHolder implements IPropertyHolder, Serializable {

    /** the serial version UID. */
    private static final long serialVersionUID = 4507851447415709893L;
    
    /** map of property identifiers to their values. */
    private Map<IProperty<?>, Object> propertyMap = null;
    
    /**
     * {@inheritDoc}
     */
    public void setProperty(final IProperty<?> property, final Object value) {
        if (propertyMap == null) {
            propertyMap = new HashMap<IProperty<?>, Object>();
        }
        if (value == null) {
            propertyMap.remove(property);
        } else {
            propertyMap.put(property, value);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public <T> T getProperty(final IProperty<T> property) {
        if (propertyMap != null) {
            @SuppressWarnings("unchecked")
            T value = (T) propertyMap.get(property);
            if (value != null) {
                return value;
            }
        }
        return property.getDefault();
    }
    
    /**
     * Retrieves a property value for a given class. If the property holder contains multiple instances
     * of the class, the returned instance is selected arbitrarily. This method is less efficient than
     * {@link #getProperty(IProperty)}, so use it with caution.
     * 
     * @param <T> type of property
     * @param clazz a class
     * @return a contained instance of the class, or {@code null} if there is none
     */
    public <T> T getProperty(final Class<T> clazz) {
        if (propertyMap != null) {
            for (Object value : propertyMap.values()) {
                if (clazz.isInstance(value)) {
                    return clazz.cast(value);
                }
            }
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public void copyProperties(final IPropertyHolder other) {
        Map<IProperty<?>, Object> otherMap = other.getAllProperties();
        if (this.propertyMap == null) {
            propertyMap = new HashMap<IProperty<?>, Object>(otherMap);
        } else {
            this.propertyMap.putAll(otherMap);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public Map<IProperty<?>, Object> getAllProperties() {
        if (propertyMap == null) {
            return Collections.emptyMap();
        } else {
            return propertyMap;
        }
    }
    
    /**
     * Check for upper and lower bounds. If a property value does not fit into the bounds,
     * it is reset to the default value.
     * 
     * @param newProperties the properties that shall be checked
     */
    public void checkProperties(final IProperty<?> ... newProperties) {
        for (IProperty<?> property : newProperties) {
            Object value = propertyMap.get(property);
            if (value != null) {
                @SuppressWarnings("unchecked")
                Comparable<Object> lowbo = (Comparable<Object>) property.getLowerBound();
                @SuppressWarnings("unchecked")
                Comparable<Object> uppbo = (Comparable<Object>) property.getUpperBound();
                if (lowbo.compareTo(value) > 0 || uppbo.compareTo(value) < 0) {
                    propertyMap.remove(property);
                }
            }
        }
    }
    
}
