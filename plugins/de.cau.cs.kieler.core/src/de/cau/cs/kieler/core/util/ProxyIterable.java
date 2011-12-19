/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.util;

import java.util.Iterator;

/**
 * A class for creating iterable instances to a given iterator.
 * 
 * @author mri
 * 
 * @param <T>
 *            the type for the iterator
 */
public final class ProxyIterable<T> implements Iterable<T> {

    /**
     * Creates a proxy iterable to a given iterator.
     * 
     * @param <T>
     *            the type for the iterator
     * @param iterator
     *            the iterator
     * @return the proxy iterable
     */
    public static <T> ProxyIterable<T> create(final Iterator<T> iterator) {
        return new ProxyIterable<T>(iterator);
    }

    /** the iterator. */
    private Iterator<T> iterator;

    /**
     * Constructs a proxy iterable to a given iterator.
     * 
     * @param iterator
     *            the iterator
     */
    public ProxyIterable(final Iterator<T> iterator) {
        this.iterator = iterator;
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<T> iterator() {
        return iterator;
    }

}
