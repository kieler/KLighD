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

import java.util.LinkedList;
import java.util.List;

/**
 * Base class for implementations of progress monitors. This class performs
 * execution time measurement, keeps track of the amount of completed work, and
 * handles sub-tasks properly.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class BasicProgressMonitor implements IKielerProgressMonitor {

    /** the parent monitor. */
    private BasicProgressMonitor parentMonitor;
    /** indicates whether the monitor has been closed. */
    private boolean closed = false;
    /** list of child monitors. */
    private List<IKielerProgressMonitor> children = new LinkedList<IKielerProgressMonitor>();
    /**
     * the number of work units that will be consumed after completion of the
     * currently active child task.
     */
    private float currentChildWork = -1;
    /** the start time of the associated task, in nanoseconds. */
    private long startTime;
    /** the total time of the associated task, in seconds. */
    private double totalTime;
    /** the name of the associated task. */
    private String taskName;
    /** the amount of work that is completed. */
    private float completedWork = 0.0f;
    /** the number of work units that can be completed in total. */
    private float totalWork;
    /** the maximal number of hierarchy levels for which progress is reported. */
    private int maxLevels;

    /**
     * Creates a progress monitor with infinite number of hierarchy levels.
     */
    public BasicProgressMonitor() {
        this.maxLevels = -1;
    }
    
    /**
     * Creates a progress monitor with the given maximal number of hierarchy levels. If the
     * number is negative, the hierarchy levels are infinite. Otherwise progress is
     * reported to parent monitors only up to the specified number of levels.
     * 
     * @param themaxLevels the maximal number of hierarchy levels for which progress is
     *     reported
     */
    public BasicProgressMonitor(final int themaxLevels) {
        this.maxLevels = themaxLevels;
    }

    /**
     * {@inheritDoc}
     */
    public final boolean begin(final String name, final float thetotalWork) {
        if (closed) {
            throw new IllegalStateException("The task is already done.");
        } else if (this.taskName != null) {
            return false;
        } else {
            if (name == null) {
                throw new NullPointerException();
            }
            this.taskName = name;
            this.totalWork = thetotalWork;
            doBegin(name, thetotalWork, parentMonitor == null, maxLevels);
            startTime = System.nanoTime();
            return true;
        }
    }

    /**
     * Invoked when a task begins, to be overridden by subclasses. This
     * implementation does nothing.
     * 
     * @param name task name
     * @param newTotalWork total amount of work for the new task
     * @param topInstance if true, this progress monitor is the top instance
     * @param maxHierarchyLevels the maximal number of reported hierarchy levels, or -1
     *     for infinite levels
     */
    protected void doBegin(final String name, final float newTotalWork,
            final boolean topInstance, final int maxHierarchyLevels) {
    }

    /**
     * {@inheritDoc}
     */
    public boolean isRunning() {
        return taskName != null && !closed;
    }

    /** factor for nanoseconds. */
    private static final double NANO_FACT = 1e-9;

    /**
     * {@inheritDoc}
     */
    public final void done() {
        if (taskName == null) {
            throw new IllegalStateException("The task has not begun yet.");
        }
        if (!closed) {
            totalTime = (System.nanoTime() - startTime) * NANO_FACT;
            if (completedWork < totalWork) {
                internalWorked(totalWork - completedWork);
            }
            doDone(parentMonitor == null, maxLevels);
            closed = true;
        }
    }

    /**
     * Invoked when a task ends, to be overridden by subclasses. This
     * implementation does nothing.
     * 
     * @param topInstance if true, this progress monitor is the top instance
     * @param maxHierarchyLevels the maximal number of reported hierarchy levels, or -1
     *     for infinite levels
     */
    protected void doDone(final boolean topInstance, final int maxHierarchyLevels) {
    }

    /**
     * {@inheritDoc}
     */
    public final double getExecutionTime() {
        return totalTime;
    }

    /**
     * {@inheritDoc}
     */
    public final List<IKielerProgressMonitor> getSubMonitors() {
        return children;
    }

    /**
     * {@inheritDoc}
     */
    public final IKielerProgressMonitor getParentMonitor() {
        return parentMonitor;
    }

    /**
     * {@inheritDoc}
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * This implementation always returns {@code false}.
     * 
     * @return {@code false}
     */
    public boolean isCanceled() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public final IKielerProgressMonitor subTask(final float work) {
        if (!closed) {
            BasicProgressMonitor subMonitor = doSubTask(work, maxLevels);
            children.add(subMonitor);
            subMonitor.parentMonitor = this;
            currentChildWork = work;
            return subMonitor;
        } else {
            return null;
        }
    }

    /**
     * Invoked when a sub-task is created, to be overridden by subclasses. This
     * implementation creates a new {@code BasicProgressMonitor} instance.
     * 
     * @param work amount of work that is completed in the current monitor
     *         instance when the sub-task ends
     * @param maxHierarchyLevels the maximal number of reported hierarchy levels for the parent
     *         progress monitor, or -1 for infinite levels
     * @return a new progress monitor instance
     */
    protected BasicProgressMonitor doSubTask(final float work, final int maxHierarchyLevels) {
        if (maxHierarchyLevels > 0) {
            return new BasicProgressMonitor(maxHierarchyLevels - 1);
        } else {
            return new BasicProgressMonitor(maxHierarchyLevels);
        }
    }

    /**
     * {@inheritDoc}
     */
    public final void worked(final float work) {
        if (work > 0 && !closed) {
            internalWorked(work);
        }
    }

    /**
     * Sets the current work counters of this monitor and all parent monitors.
     * 
     * @param work amount of work that has been completed
     */
    private void internalWorked(final float work) {
        if (totalWork > 0 && completedWork < totalWork) {
            completedWork += work;
            doWorked(completedWork, totalWork, parentMonitor == null);
            if (parentMonitor != null && parentMonitor.currentChildWork > 0 && maxLevels != 0) {
                parentMonitor.internalWorked(work / totalWork * parentMonitor.currentChildWork);
            }
        }
    }

    /**
     * Invoked when work is done for this progress monitor, to be overridden by
     * subclasses. This implementation does nothing.
     * 
     * @param thecompletedWork total number of work that is done for this task
     * @param thetotalWork total number of work that is targeted for completion
     * @param topInstance if true, this progress monitor is the top instance
     */
    protected void doWorked(final float thecompletedWork, final float thetotalWork,
            final boolean topInstance) {
    }

}
