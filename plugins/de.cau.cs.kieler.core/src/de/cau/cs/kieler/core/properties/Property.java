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
 * A property that uses a string for identification.
 *
 * @kieler.design 2011-01-17 reviewed by haf, cmot, soh
 * @kieler.rating 2012-07-10 proposed yellow msp
 * @param <T> type of the property
 * @author msp
 */
public class Property<T> implements IProperty<T>, Comparable<IProperty<?>> {
    
    /** the default lower bound, which is smaller than everything else. */
    public static final Comparable<?> NEGATIVE_INFINITY = new Comparable<Object>() {
        public int compareTo(final Object other) {
            // Ignore FindBugs warning
            return -1;
        }
    };
    /** the default upper bound, which is greater than everything else. */
    public static final Comparable<?> POSITIVE_INFINITY = new Comparable<Object>() {
        public int compareTo(final Object other) {
            // Ignore FindBugs warning
            return 1;
        }
    };
    
    /** identifier of this property. */
    private String id;
    /** the default value of this property. */
    private T defaultValue;
    /** the lower bound of this property. */
    @SuppressWarnings("unchecked")
    private Comparable<T> lowerBound = (Comparable<T>) NEGATIVE_INFINITY;
    /** the upper bound of this property. */
    @SuppressWarnings("unchecked")
    private Comparable<T> upperBound = (Comparable<T>) POSITIVE_INFINITY;
    
    /**
     * Creates a property with given identifier and {@code null} as default value.
     * 
     * @param theid the identifier
     */
    public Property(final String theid) {
        this.id = theid;
    }
    
    /**
     * Creates a property with given identifier and default value.
     * 
     * @param theid the identifier
     * @param thedefaultValue the default value
     */
    public Property(final String theid, final T thedefaultValue) {
        this(theid);
        this.defaultValue = thedefaultValue;
    }
    
    /**
     * Creates a property with given identifier, default value, and lower bound.
     * 
     * @param theid the identifier
     * @param thedefaultValue the default value
     * @param thelowerBound the lower bound
     */
    public Property(final String theid, final T thedefaultValue, final Comparable<T> thelowerBound) {
        this(theid, thedefaultValue);
        if (thelowerBound != null) {
            this.lowerBound = thelowerBound;
        }
    }
    
    /**
     * Creates a property with given identifier, default value, and lower and upper bound.
     * 
     * @param theid the identifier
     * @param thedefaultValue the default value
     * @param thelowerBound the lower bound, or {@code null} if the default lower bound shall be taken
     * @param theupperBound the upper bound
     */
    public Property(final String theid, final T thedefaultValue, final Comparable<T> thelowerBound,
            final Comparable<T> theupperBound) {
        this(theid, thedefaultValue, thelowerBound);
        if (theupperBound != null) {
            this.upperBound = theupperBound;
        }
    }
    
    /**
     * Creates a property using another property as identifier, but changing
     * the default value.
     * 
     * @param other another property
     * @param thedefaultValue the new default value
     */
    public Property(final IProperty<T> other, final T thedefaultValue) {
        this(other.getId(), thedefaultValue, other.getLowerBound(),
                other.getUpperBound());
    }
    
    /**
     * Creates a property using another property as identifier, but changing the
     * default value and lower bound.
     * 
     * @param other another property
     * @param thedefaultValue the new default value
     * @param thelowerBound the new lower bound
     */
    public Property(final IProperty<T> other, final T thedefaultValue,
            final Comparable<T> thelowerBound) {
        this(other.getId(), thedefaultValue, thelowerBound, other.getUpperBound());
    }
    
    /**
     * Creates a property using another property as identifier, but changing the
     * default value, lower bound, and upper bound.
     * 
     * @param other another property
     * @param thedefaultValue the new default value
     * @param thelowerBound the new lower bound
     * @param theupperBound the new upper bound
     */
    public Property(final IProperty<T> other, final T thedefaultValue,
            final Comparable<T> thelowerBound, final Comparable<T> theupperBound) {
        this(other.getId(), thedefaultValue, thelowerBound, theupperBound);
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean equals(final Object obj) {
        if (obj instanceof IProperty<?>) {
            return this.id.equals(((IProperty<?>) obj).getId());
        } else {
            return false;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return id.hashCode();
    }
    
    /**
     * {@inheritDoc}
     */
    public String toString() {
        return id;
    }
    
    /**
     * Returns the default value of this property.
     * 
     * @return the default value
     */
    public T getDefault() {
        return defaultValue;
    }

    /**
     * {@inheritDoc}
     */
    public Comparable<T> getLowerBound() {
        return lowerBound;
    }

    /**
     * {@inheritDoc}
     */
    public Comparable<T> getUpperBound() {
        return upperBound;
    }

    /**
     * {@inheritDoc}
     */
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(final IProperty<?> other) {
        return id.compareTo((String) other.getId());
    }

}
