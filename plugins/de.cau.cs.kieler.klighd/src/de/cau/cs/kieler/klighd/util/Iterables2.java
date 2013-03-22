/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.util;

import java.util.Iterator;

import com.google.common.collect.Iterators;

/**
 * This class provides some convenience methods that I miss in
 * {@link com.google.common.collect.Iterables Iterables}.
 * 
 * @author chsch
 */
public final class Iterables2 {

    /**
     * Hidden default constructor.
     */
    private Iterables2() {
        
    }
    
    /**
     * Provides a related {@link Iterable} for an {@link Iterator}.
     * 
     * @param <T> the generic type of {@code iterator}
     * @param iterator the {@link Iterator} to wrap
     * @return the wrapping {@link Iterable}
     */
    public static <T> Iterable<T> toIterable(final Iterator<T> iterator) {
        return new Iterable<T>() {

            public Iterator<T> iterator() {
                return iterator;
            }
        };
    }
    
    /**
     * Returns an {@link Iterable} containing only {@code value}.<br>
     * This is a counterpart to {@link Iterators#singletonIterator(Object)}.
     *
     * @param <T> the type of value
     * @param value the value to be encapsulated
     * @return the required {@link Iterable}
     */
    public static <T> Iterable<T> singletonIterable(final T value) {
        return toIterable(Iterators.singletonIterator(value));
    }
}
