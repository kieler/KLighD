/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.piccolo.internal.activities;

/**
 * Interface to be implemented by custom {@link edu.umd.cs.piccolo.activities.PActivity PActivities}
 * allowing to invoke {@link edu.umd.cs.piccolo.activities.PActivity#activityStarted
 * PActivity#activityStarted} and {@link edu.umd.cs.piccolo.activities.PActivity#activityFinished
 * PActivity#activityFinished}, which are marked <code>protected</code> in
 * {@link edu.umd.cs.piccolo.activities.PActivity PActivity}. This enables the initialization and
 * execution of such activities without scheduling them in case no animation is required.
 *
 * @author chsch
 */
public interface IStartingAndFinishingActivity {

    /**
     * This method is called right before an activity is scheduled to start running. After this
     * method is called step() will be called until the activity finishes. (from
     * {@link edu.umd.cs.piccolo.activities.PActivity#activityStarted PActivity#activityStarted})
     */
    void activityStarted();

    /**
     * This method is called after an activity is has finished running and the
     * activity has been removed from the PActivityScheduler queue. (from
     * {@link edu.umd.cs.piccolo.activities.PActivity#activityFinished PActivity#activityFinished})
     */
    void activityFinished();
}
