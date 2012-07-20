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

import de.cau.cs.kieler.core.WrappedException;

/**
 * A factory that uses the default constructor to create instances.
 *
 * @param <T> type of instances that are created by this factory
 * @author msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class DefaultFactory<T> implements IFactory<T> {

    /** the class for which instances shall be created. */
    private Class<? extends T> clazz;
    
    /**
     * Creates an instance factory for the given class.
     * 
     * @param theclazz the class for which instances shall be created
     */
    public DefaultFactory(final Class<? extends T> theclazz) {
        this.clazz = theclazz;
    }
    
    /**
     * {@inheritDoc}
     */
    public T create() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException exception) {
            throw new WrappedException(exception);
        } catch (IllegalAccessException exception) {
            throw new WrappedException(exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void destroy(final T obj) {
        // do nothing by default, override in subclasses
    }

}
