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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.util;

/**
 * Extension of {@link Runnable} that may be ask for a result, inspired by
 * {@link org.eclipse.emf.transaction.RunnableWithResult}.
 * 
 * @kieler.design 2014-04-17 reviewed by cds, chsch, tit, uru
 * @kieler.rating 2012-11-02 proposed yellow cds
 * @param <T> the type of the result
 * @author chsch
 */
public interface RunnableWithResult<T> extends Runnable {

    /**
     * Returns a result computed by my {@link Runnable#run()} method.
     * 
     * @return my result, or {@code null} if none
     */
    T getResult();
    
}
