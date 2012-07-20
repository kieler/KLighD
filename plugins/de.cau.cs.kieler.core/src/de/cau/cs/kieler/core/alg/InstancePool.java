/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
package de.cau.cs.kieler.core.alg;

import java.util.LinkedList;

/**
 * A pool for class instances.
 *
 * @param <T> the type of instances that are held by this pool
 * @author msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class InstancePool<T> {
    
    /** an infinite amount of instances. */
    public static final int INFINITE = -1;
    
    /** the instance factory to use for this pool. */
    private IFactory<T> factory;
    /** the list of currently held instances. */
    private LinkedList<T> instances = new LinkedList<T>();
    /** the configured instance limit. */
    private int limit;
    
    /**
     * Create an instance pool with an infinite capacity. 
     * 
     * @param thefactory the instance factory
     */
    public InstancePool(final IFactory<T> thefactory) {
        this(thefactory, INFINITE);
    }
    
    /**
     * Create an instance pool with given capacity.
     * 
     * @param thefactory the instance factory
     * @param thelimit the maximal number of instances that shall be kept in the pool
     */
    public InstancePool(final IFactory<T> thefactory, final int thelimit) {
        assert thefactory != null;
        this.factory = thefactory;
        this.limit = thelimit;
    }
    
    /**
     * Fetch an instance from the pool. If no instance is available, a new one is created.
     * 
     * @return a class instance
     */
    public synchronized T fetch() {
        if (instances.isEmpty()) {
            return factory.create();
        }
        return instances.removeFirst();
    }
    
    /**
     * Release an instance into the pool to be used again. Only instances that are still usable
     * may be released.
     * 
     * @param obj a class instance
     */
    public synchronized void release(final T obj) {
        if (limit < 0 || instances.size() < limit) {
            instances.addLast(obj);
        } else {
            factory.destroy(obj);
        }
    }
    
    /**
     * Clear the instance pool by disposing all instances that are currently held.
     */
    public synchronized void clear() {
        for (T obj : instances) {
            factory.destroy(obj);
        }
        instances.clear();
    }

}
