/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.util;

/**
 * Extension of {@link Runnable} that may be ask for a result, inspired by
 * {@link org.eclipse.emf.transaction.RunnableWithResult}.
 * I need that for handing over some executable to the KLighD view in order
 * to ask the model for a newer version while updating the diagram.
 * 
 * @kieler.design proposed 2012-11-02 cds
 * @kieler.rating 2012-11-02 proposed yellow cds
 * @param <T> the type of the result
 * @author chsch
 */
public interface RunnableWithResult<T> extends Runnable {

    /**
     * Returns a result computed by my {@link Runnable#run()} method.
     * 
     * @return my result, or <code>null</code> if none
     */
    T getResult();
    
    /**
     * A simple standard implementation of {@link RunnableWithResult}.
     * 
     *  @param <T> 
     */
    public abstract static class Impl<T> implements RunnableWithResult<T> {
        
        private T result = null;
        
        /**
         * {@inheritDoc}
         */
        public T getResult() {
            return this.result;
        }
        
        /**
         * Setter to be called by subclasses' run methods.
         * @param theResult theResult to be delivered to the caller of the {@link Runnable}.
         */
        protected void setResult(final T theResult) {
            this.result = theResult;
        }
    }
}
