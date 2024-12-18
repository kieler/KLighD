/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.util;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.google.common.base.Preconditions;
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
     * Provides a related {@link Iterable} wrapping the given {@link Iterator} for use in
     * <code>for</code> loops.<br>
     * <b>Caution:</b> The method {@link Iterable#iterator() iterator()} of the returned
     * {@link Iterable} must not be called more than once, an {@link IllegalStateException} is
     * thrown if this condition is violated.
     * 
     * @param <T>
     *            the generic type of {@code iterator}
     * @param iterator
     *            the {@link Iterator} to wrap
     * @throws IllegalStateException
     *             if {@link Iterable#iterator() iterator()} is called more than once on the
     *             returned {@link Iterable}, as the wrapped {@link Iterator} is likely to be
     *             exhausted
     * @return the wrapping {@link Iterable}
     */
    public static <T> Iterable<T> toIterable(final Iterator<T> iterator) {
        return new Iterable<T>() {
            private boolean exhausted = false;
            
            public Iterator<T> iterator() {
                if (exhausted) {
                    throw new IllegalStateException(
                            "The wrapped Iterator has been accessed already,"
                            + " it is forbidden to request it more than once for this Iterable!");
                }

                exhausted = true;
                return iterator;
            }
        };
    }

    /**
     * Constructs a new {@link Iterable} containing all elements of <code>iterable</code> except the
     * last <code>count</code> ones. The provided <code>iterable</code> is lazily evaluated, the
     * evaluation starts with examining the first <code>x</code> elements once
     * {@link Iterator#hasNext() result.iterator().hasNext()} is called. Further elements will be
     * accessed one by one.
     *
     * @param <T>
     *            the type of value
     * @param iterable
     *            the source {@link Iterable}
     * @param count
     *            number elements to drop
     * @return a new {@link Iterable} containing all elements of <code>iterable</code> except the
     *         last <code>count</code> ones.
     */
    public static <T> Iterable<T> skipLast(final Iterable<T> iterable, final int count) {
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
                return new SkipLastIterator<T>(iterable, count);
            }
        };
    }

    /**
     * Constructs a new {@link Iterator} providing all elements of <code>iterator</code> except the
     * last <code>count</code> ones. The provided <code>iterator</code> is lazily evaluated, the
     * evaluation starts with examining the first <code>x</code> elements once
     * {@link Iterator#hasNext() result.hasNext()} is called. Further elements will be accessed one
     * by one.
     *
     * @param <T>
     *            the type of value
     * @param iterator
     *            the source {@link Iterator}
     * @param count
     *            number elements to drop
     * @return a new {@link Iterator} containing all elements of <code>iterator</code> except the
     *         last <code>count</code> ones.
     */
    public static <T> Iterator<T> skipLast(final Iterator<T> iterator, final int count) {
        if (iterator == null) {
            throw new NullPointerException("The provided 'iterator' is 'null'!");
        } else if (count == 0) {
            return iterator;
        } else if (count < 0) {
            throw new IllegalArgumentException(
                    "Cannot drop a negative number of elements. Argument 'count' was: " + count);
        }

        return new SkipLastIterator<T>(iterator, count);
    }

    /**
     * Special {@link Iterator} skipping the <code>n</code> elements of the provided parent
     * {@link Iterator}. The provided <code>iterator</code> is lazily evaluated, the evaluation
     * starts with examining the first <code>x</code> elements once {@link Iterator#hasNext()
     * result.hasNext()} is called. Further elements will be accessed one by one.
     *
     * @param <T>
     *            the type of value
     */
    private static class SkipLastIterator<T> implements Iterator<T> {

        private final Iterable<T> src;
        private final int count;

        private Iterator<T> srcIterator = null;
        private Queue<T> queue = null;

        SkipLastIterator(final Iterable<T> src, final int count) {
            this.src = Preconditions.checkNotNull(src);
            this.count = count;
        }

        SkipLastIterator(final Iterator<T> src, final int count) {
            this.src = null;
            this.srcIterator = Preconditions.checkNotNull(src);
            this.count = count;
        }

        /**
         * Initializes this {@link Iterator} on demand.
         *
         * @return <code>true</code> if there more elements provided by {@link #srcIterator},
         *         <code>false</code> if this {@link Iterator} won't provide anything.
         */
        private boolean initialize() {
            if (srcIterator == null) {
                // this is OK because of the constructors' field initialization
                srcIterator = src.iterator();
            }

            queue = new ArrayDeque<>();

            int i = count;
            while ((i--) != 0) {
                if (srcIterator.hasNext()) {
                    queue.add(srcIterator.next());
                } else {
                    return false;
                }
            }
            return true;
        }

        /**
         * {@inheritDoc}
         */
        public boolean hasNext() {
            // queue == null -> (lazy) initialization required
            if (queue == null && !initialize()) {
                // no more elements available after initialization
                return false;
            }
            return srcIterator.hasNext();
        }

        /**
         * {@inheritDoc}
         */
        public T next() {
            if (srcIterator.hasNext()) {
                queue.add(srcIterator.next());
                return queue.poll();
            } else {
                return null;
            }
        }

        /**
         * {@inheritDoc}
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * @see Iterators#toString()
         */
        @Override
        public String toString() {
            return Iterators.toString(this);
        }
    }

    /**
     * Returns a sequential {@code Stream} with the given {@link Iterable} as its source.
     *
     * @param <T> the type of elements
     * @param iterable the iterable
     * @return a stream backed by the iterable
     */
    public static <T> Stream<T> stream(Iterable<T> iterable) {
        return iterable instanceof Collection //
                ? ((Collection<T>) iterable).stream()
                : StreamSupport.stream(iterable.spliterator(), false);
    }
}
