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
import java.util.Queue;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.util.Pair;

/**
 * This class provides some convenience methods that I miss in
 * {@link com.google.common.collect.Iterables Iterables}.
 *
 * @author chsch
 *
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
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
     * @param <T>
     *            the generic type of {@code iterator}
     * @param iterator
     *            the {@link Iterator} to wrap
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

        public SkipLastIterator(final Iterable<T> src, final int count) {
            this.src = Preconditions.checkNotNull(src);
            this.count = count;
        }

        public SkipLastIterator(final Iterator<T> src, final int count) {
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

            queue = Lists.newLinkedList();

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
     * Performs a <i>fold</i> operation on the elements of the provided {@link Iterable} by applying
     * the provided {@link Function Function<Pair<R, T>, R>} onto each element.
     *
     * @param <E>
     *            type of {@code iterable}'s elements
     * @param <R>
     *            type of the result determined by {@code function}
     * @param iterable
     *            the {@link Iterable} containing the particular elements
     * @param function
     *            the {@link Function} to be applied, is called with a {@link Pair} providing the
     *            value of the previous application ({@link Pair#getFirst() first}, is {@code null}
     *            for first time), and the particular element of {@code iterable} (
     *            {@link Pair#getSecond() second})
     * @return the accumulated result obtained by consecutively applying {@code function} on the
     *         elements of {@code iterable} and the preliminary result of previous application,
     *         starting with {@code null}
     */
    public static <E, R> R fold(final Iterable<E> iterable, final Function<Pair<R, E>, R> function) {

        final Pair<R, E> pair = new Pair<R, E>();
        R result = null;

        for (final E element : iterable) {
            pair.setFirst(result);
            pair.setSecond(element);
            result = function.apply(pair);
        }
        return result;
    }
}
