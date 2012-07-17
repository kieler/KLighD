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

/**
 * Interface for factories of class instances.
 *
 * @param <T> type of instances that are created by this factory
 * @author msp
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public interface IFactory<T> {

    /**
     * Create an instance of the type that is managed by this factory.
     * 
     * @return a new instance
     */
    T create();
    
    /**
     * Destroy a given instance by freeing all resources that are contained.
     * 
     * @param obj the instance to destroy
     */
    void destroy(T obj);
    
}
