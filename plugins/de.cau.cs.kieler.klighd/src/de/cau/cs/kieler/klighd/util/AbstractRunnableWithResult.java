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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.util;

/**
 * A simple standard implementation of {@link RunnableWithResult}.
 * 
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
