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

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * An iterator that filters the elements returned by a wrapped iterator.
 *
 * @param <E> the type of elements
 * @author msp
 */
public class FilteredIterator<E> implements ListIterator<E> {

    /** the wrapped list iterator. */
    private ListIterator<E> wrappedIter;
    /** the condition to evaluate on each element. */
    private ICondition<E> condition;
    /** the next and previous element, if there are such. */
    private E next, previous;
    /** the index of the next element in the filtered list. */
    private int nextIndex;
    
    /**
     * An iterable that creates a filtered iterator as iterator.
     */
    public static class Iterable<E> implements java.lang.Iterable<E> {
        private List<E> wrappedList;
        private ICondition<E> condition;

        /**
         * Creates an iterable for a given list and a condition.
         * 
         * @param thewrappedList the wrapped list
         * @param thecondition the condition to evaluate on each element
         */
        public Iterable(final List<E> thewrappedList, final ICondition<E> thecondition) {
            this.wrappedList = thewrappedList;
            this.condition = thecondition;
        }
        
        /**
         * {@inheritDoc}
         */
        public Iterator<E> iterator() {
            return new FilteredIterator<E>(wrappedList.listIterator(), condition);
        }
    }
    
    /**
     * Creates a filtered iterator for a given list iterator and a condition.
     * 
     * @param thewrappedIter the wrapped list iterator
     * @param thecondition the condition to evaluate on each element
     */
    public FilteredIterator(final ListIterator<E> thewrappedIter, final ICondition<E> thecondition) {
        this.wrappedIter = thewrappedIter;
        this.condition = thecondition;
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean hasNext() {
        if (next == null) {
            E elem = null;
            while (wrappedIter.hasNext()) {
                elem = wrappedIter.next();
                if (condition.evaluate(elem)) {
                    break;
                } else {
                    elem = null;
                }
            }
            next = elem;
        }
        return next != null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasPrevious() {
        if (previous == null) {
            E elem = null;
            while (wrappedIter.hasPrevious()) {
                elem = wrappedIter.previous();
                if (condition.evaluate(elem)) {
                    break;
                } else {
                    elem = null;
                }
            }
            previous = elem;
        }
        return previous != null;
    }

    /**
     * {@inheritDoc}
     */
    public E next() {
        if (hasNext()) {
            nextIndex++;
            E newNext = next;
            next = null;
            return newNext;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * {@inheritDoc}
     */
    public int nextIndex() {
         return nextIndex;
    }

    /**
     * {@inheritDoc}
     */
    public E previous() {
        if (hasPrevious()) {
            nextIndex--;
            E newPrevious = previous;
            previous = null;
            return newPrevious;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * {@inheritDoc}
     */
    public int previousIndex() {
        return nextIndex - 1;
    }

    /**
     * {@inheritDoc}
     */
    public void add(final E e) {
        wrappedIter.add(e);
    }

    /**
     * {@inheritDoc}
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    public void set(final E e) {
        throw new UnsupportedOperationException();
    }

}
