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

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Object that may contain another object, inspired by the Haskell type <i>Maybe</i>.
 * <p>
 * This class can be used to wrap objects for anonymous classes:
 * <pre>
 * Myclass foo() {
 *     final Maybe<Myclass> maybe = new Maybe<Myclass>();
 *     PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
 *         public void run() {
 *             maybe.set(new Myclass("bar"));
 *         }
 *     });
 *     return maybe.get();
 * }
 * </pre>
 * </p>
 * <p>
 * Another use is as a wrapper for synchronization on objects that may be {@code null}:
 * <pre>
 * Maybe<Myclass> maybe = new Maybe<Myclass>();
 * 
 * void thread1() {
 *     maybe.set(new Myclass("foo"));
 *     synchronized (maybe) {
 *         maybe.notify();
 *     )
 * }
 * 
 * void thread2() {
 *     synchronized (maybe) {
 *         if (maybe.get() == null) {
 *             maybe.wait();
 *         }
 *     }
 *     maybe.get().bar();
 * }
 * </pre>
 * </p>
 * 
 * @kieler.design proposed 2012-11-02 cds
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @param <T> type of contained object
 * @author msp
 */
public class Maybe<T> extends AbstractCollection<T> {
    
    /**
     * Create a maybe with inferred generic type.
     * 
     * @param <D> the generic type
     * @return a new instance of given type
     */
    public static <D> Maybe<D> create() {
        return new Maybe<D>();
    }

    /** the contained object, which may be {@code null}. */
    private T object;
    
    /**
     * Creates a maybe without an object.
     */
    public Maybe() {
        this.object = null;
    }
    
    /**
     * Creates a maybe with the given object.
     * 
     * @param theobject the object to contain
     */
    public Maybe(final T theobject) {
        this.object = theobject;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Maybe<?>) {
            Maybe<?> other = (Maybe<?>) obj;
            return this.object == null ? other.object == null
                    : this.object.equals(other.object);
        } else {
            return false;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        if (object == null) {
            return 0;
        } else {
            return object.hashCode();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (object == null) {
            return "maybe(null)";
        } else {
            return "maybe(" + object.toString() + ")";
        }
    }

    /**
     * Sets the contained object.
     *
     * @param theobject the object to set
     */
    public void set(final T theobject) {
        this.object = theobject;
    }

    /**
     * Returns the contained object.
     *
     * @return the contained object
     */
    public T get() {
        return object;
    }

    /**
     * {@inheritDoc}
     */
    public int size() {
        return object == null ? 0 : 1;
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private boolean visited = false;
            public boolean hasNext() {
                return !visited && object != null;
            }
            public T next() {
                if (visited || object == null) {
                    throw new NoSuchElementException();
                }
                visited = true;
                return object;
            }
            public void remove() {
                if (!visited || object == null) {
                    throw new IllegalStateException();
                }
                object = null;
            }
        };
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(final T e) {
        if (object != null) {
            throw new IllegalStateException();
        }
        if (e == null) {
            throw new NullPointerException();
        }
        object = e;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(final Object o) {
        if (object != null && o.equals(object)) {
            object = null;
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        object = null;
    }
    
}
