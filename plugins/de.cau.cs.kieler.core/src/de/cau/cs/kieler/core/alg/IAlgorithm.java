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
package de.cau.cs.kieler.core.alg;

/**
 * Interface that serves as super-interface for all algorithm interfaces.
 * Contains progress monitors to keep track of the progress of an algorithm run.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @see IKielerProgressMonitor
 * @author msp
 */
public interface IAlgorithm {

    /**
     * Reset the internal state of the algorithm without a progress monitor.
     */
    void reset();

    /**
     * Reset the internal state and set the progress monitor for the algorithm.
     * 
     * @param monitor the progress monitor
     */
    void reset(IKielerProgressMonitor monitor);

    /**
     * Set the progress monitor for the algorithm. Algorithm implementations
     * should make proper use of the monitor by notifying at least the start and
     * end of their task.
     * 
     * @param monitor the progress monitor
     */
    void setProgressMonitor(IKielerProgressMonitor monitor);

}
