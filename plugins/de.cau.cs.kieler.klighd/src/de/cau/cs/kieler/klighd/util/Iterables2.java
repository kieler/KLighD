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
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

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
     * This is a counterpart to {@link link
     * com.google.common.collect.Iterators#singletonIterator(Object)
     * Iterators#singletonIterator(Object)}.<br>
     * In case {@code value} is {@code null} an empty {@link Iterable} is returned.
     * 
     * @param <T>
     *            the type of value
     * @param value
     *            the value to be encapsulated
     * @return the required {@link Iterable}
     */
    public static <T> Iterable<T> singletonIterable(final T value) {
        return singletonList(value);
    }

    /**
     * Returns a {@link List} containing only {@code value}.<br>
     * This is a counterpart to
     * {@link com.google.common.collect.Iterators#singletonIterator(Object)
     * Iterators#singletonIterator(Object)}.<br>
     * In case {@code value} is {@code null} an empty {@link List} is returned.
     * 
     * @param <T>
     *            the type of value
     * @param value
     *            the value to be encapsulated, may be null
     * @return the required {@link Iterable}
     */
    public static <T> List<T> singletonList(final T value) {
        return value != null ? ImmutableList.of(value) : ImmutableList.<T>of();
    }
    
    /**
     * Constructs a new {@link Iterable} containing all elements of <code>iterable</code> except the
     * last <code>count</code> ones.
     * 
     * @param <T> the type of value
     * @param iterable
     *            the source {@link Iterable}
     * @param count
     *            number elements to drop
     * @return a new {@link Iterable} containing all elements of <code>iterable</code> except the
     *         last <code>count</code> ones.
     */
    public static <T> Iterable<T> dropLast(final Iterable<T> iterable, final int count) {
        if (iterable == null) {
            throw new NullPointerException("The provided 'iterable' is 'null'!");
        } else if (count == 0) {
            return iterable;
        } else if (count < 0) {
            throw new IllegalArgumentException(
                    "Cannot drop a negative number of elements. Argument 'count' was: " + count);
        }
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return Lists.reverse(
                        Lists.newLinkedList(
                                Iterables.skip(
                                        Lists.reverse(
                                                Lists.newLinkedList(iterable)), count))).iterator();
            }
        };
    }
}
