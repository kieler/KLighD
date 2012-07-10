/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A simple pair implementation.
 * 
 * @kieler.design 2011-03-14 reviewed by cmot, cds
 * @kieler.rating 2012-07-10 proposed yellow msp
 * @param <F> type of first contained object
 * @param <S> type of second contained object
 * @author msp
 */
public class Pair<F, S> {

    /** the first element. */
    private F first;
    /** the second element. */
    private S second;
    
    /**
     * Constructs a pair with {@code null} elements.
     *
     * @param <T1> type of first element
     * @param <T2> type of second element
     * @return a new pair
     */
    public static <T1, T2> Pair<T1, T2> create() {
        return new Pair<T1, T2>();
    }
    
    /**
     * Constructs a pair given both elements.
     * 
     * @param first the first element
     * @param second the second element
     * @param <T1> type of first element
     * @param <T2> type of second element
     * @return a new pair
     */
    public static <T1, T2> Pair<T1, T2> create(final T1 first, final T2 second) {
        return new Pair<T1, T2>(first, second);
    }
    
    /**
     * Constructs a pair with {@code null} elements.
     */
    public Pair() {
    }

    /**
     * Constructs a pair given both elements.
     * 
     * @param thefirst the first element
     * @param thesecond the second element
     */
    public Pair(final F thefirst, final S thesecond) {
        this.first = thefirst;
        this.second = thesecond;
    }
    
    /**
     * Constructs a pair from a map entry.
     * 
     * @param entry an entry of a map
     */
    public Pair(final Entry<F, S> entry) {
        this.first = entry.getKey();
        this.second = entry.getValue();
    }
    
    /**
     * Constructs a list of pairs from the entries of a map.
     * 
     * @param <G> type of the map keys
     * @param <T> type of the map values
     * @param map a map
     * @return a list of map entries as pairs
     */
    public static <G, T> List<Pair<G, T>> fromMap(final Map<G, T> map) {
        List<Pair<G, T>> list = new ArrayList<Pair<G, T>>(map.size());
        for (Entry<G, T> entry : map.entrySet()) {
            list.add(new Pair<G, T>(entry));
        }
        return list;
    }
    
    /**
     * Comparator that uses the first element as base.
     */
    public static class FirstComparator<F extends Comparable<F>, S> implements Comparator<Pair<F, S>>,
            Serializable {
        private static final long serialVersionUID = 1;

        /**
         * {@inheritDoc}
         */
        public int compare(final Pair<F, S> o1, final Pair<F, S> o2) {
            return o1.first.compareTo(o2.first);
        }
    }

    /**
     * Comparator that uses the second element as base.
     */
    public static class SecondComparator<F, S extends Comparable<S>> implements Comparator<Pair<F, S>>,
            Serializable {
        private static final long serialVersionUID = 1;

        /**
         * {@inheritDoc}
         */
        public int compare(final Pair<F, S> o1, final Pair<F, S> o2) {
            return o1.second.compareTo(o2.second);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Pair<?, ?>) {
            Pair<?, ?> other = (Pair<?, ?>) obj;
            return this.first == null ? other.first == null
                    && (this.second == null ? other.second == null
                    : this.second.equals(other.second))
                    : this.first.equals(other.first)
                    && (this.second == null ? other.second == null
                    : this.second.equals(other.second));
        } else {
            return false;
        }
    }

    private static final int HALF_WORD = Integer.SIZE / 2;
    private static final int MASK1 = (1 << HALF_WORD) - 1;
    private static final int MASK2 = ~MASK1;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int firstCode = first == null ? 0 : first.hashCode();
        int first1 = firstCode & MASK1;
        int first2 = firstCode & MASK2;
        int secondCode = second == null ? 0 : second.hashCode();
        int second1 = secondCode & MASK1;
        int second2 = secondCode & MASK2;
        return (first1 ^ ((second2 >> HALF_WORD) & MASK1))
                | (first2 ^ (second1 << HALF_WORD));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (first == null && second == null) {
            return "pair(null,null)";
        } else if (first == null) {
            return "pair(null," + second.toString() + ")";
        } else if (second == null) {
            return "pair(" + first.toString() + ",null)";
        } else {
            return "pair(" + first.toString() + "," + second.toString() + ")";
        }
    }

    /**
     * Sets the first element.
     *
     * @param thefirst the first element to set
     */
    public void setFirst(final F thefirst) {
        this.first = thefirst;
    }

    /**
     * Returns the first element.
     *
     * @return the first element
     */
    public F getFirst() {
        return first;
    }

    /**
     * Sets the second element.
     *
     * @param thesecond the second element to set
     */
    public void setSecond(final S thesecond) {
        this.second = thesecond;
    }

    /**
     * Returns the second element.
     *
     * @return the second element
     */
    public S getSecond() {
        return second;
    }

}
