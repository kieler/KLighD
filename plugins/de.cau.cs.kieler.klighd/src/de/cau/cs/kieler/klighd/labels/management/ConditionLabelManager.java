/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015, 2017 by
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
package de.cau.cs.kieler.klighd.labels.management;

import java.util.function.Predicate;

import org.eclipse.elk.graph.ElkLabel;


/**
 * Compound label manager that executes a child label manager if a given condition is true for a
 * label. The condition label manager works regardless of a mode. This is left to the child label
 * manager.
 * 
 * <p>
 * If the condition is only based on what graph element the label belongs to, either subclass
 * {@link AbstractTypeDependentLabelManager} or wrap your label manager in a
 * {@link TypeConditionLabelManager} instead.
 * </p>
 * 
 * @see AbstractTypeDependentLabelManager
 * @see TypeConditionLabelManager
 * @author ybl
 * @author cds
 */
public class ConditionLabelManager extends AbstractKlighdLabelManager {
    
    /** Condition which will be checked before resizing the label. */
    private Predicate<? super ElkLabel> condition;
    /** The label manager that does the actual managing. */
    private AbstractKlighdLabelManager labelManager;
    /** Decides whether the label which don't fullfill the condition are displayed or not. */
    private boolean filterOtherLabels;


    /**
     * Create a new instance with the given condition before resizing the label with the given label
     * manager. Labels the condition doesn't apply to are set to display the empty string.
     * 
     * @param condition
     *            condition which will be checked before resizing the label.
     * @param labelManager
     *            the label manager that will do the resizing if the condition applies.
     */
    public ConditionLabelManager(final AbstractKlighdLabelManager labelManager,
            final Predicate<? super ElkLabel> condition) {
        
        this(labelManager, condition, true);
    }
    
    /**
     * Create a new instance with the given condition before resizing the label with the given label
     * manager.
     * 
     * @param condition
     *            condition which will be checked before resizing the label.
     * @param labelManager
     *            the label manager that will do the resizing if the condition applies.
     * @param filterOtherLabels
     *            whether the label which the condition doesn't apply to are displayed or not. If
     *            this is {@code false}, labels the condition doesn't apply to are left untouched.
     *            If it is {@code true}, labels the condition doesn't apply to are set to display
     *            the empty string.
     */
    public ConditionLabelManager(final AbstractKlighdLabelManager labelManager,
            final Predicate<? super ElkLabel> condition, final boolean filterOtherLabels) {
        
        this.condition = condition;
        this.labelManager = labelManager;
        this.filterOtherLabels = filterOtherLabels;
    }
    

    @Override
    public Result doResizeLabel(final ElkLabel label, final double targetWidth) {
        if (condition.test(label)) {
            return labelManager.manageElkLabelSize(label, targetWidth);
        } else {
            if (filterOtherLabels) {
                return Result.modified("");
            } else {
                return Result.unmodified();
            }
        }
    }

}
