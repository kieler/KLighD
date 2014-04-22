/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.util;

/**
 * A simple standard implementation of {@link RunnableWithResult}.
 * 
 * @kieler.design 2014-04-17 reviewed by cds, chsch, tit, uru
 * @param <T> the type of the result
 * @author chsch
 */
public abstract class AbstractRunnableWithResult<T> implements RunnableWithResult<T> {
    
    private T result = null;
    
    /**
     * {@inheritDoc}
     */
    public final T getResult() {
        return this.result;
    }
    
    /**
     * Setter to be called by subclasses' run methods.
     * 
     * @param theResult the result to be delivered to the caller of the {@link Runnable}.
     */
    protected final void setResult(final T theResult) {
        this.result = theResult;
    }
    
}
