/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.labels;

import java.util.function.Predicate;

import de.cau.cs.kieler.core.kgraph.KLabel;

/**
 * Compound label manager that executes a child label manager if a given condition is true for a label.
 * 
 * @author ybl
 */
public class ConditionLabelManager extends AbstractKlighdLabelManager {
    
    /** Condition which will be checked before resizing the label. */
    private Predicate<? super KLabel> condition;
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
            final Predicate<? super KLabel> condition) {
        
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
            final Predicate<? super KLabel> condition, final boolean filterOtherLabels) {
        
        this.condition = condition;
        this.labelManager = labelManager;
        this.filterOtherLabels = filterOtherLabels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String resizeLabel(final KLabel label, final double targetWidth) {
        if (condition.test(label)) {
            return labelManager.resizeLabel(label, targetWidth);
        } else {
            if (filterOtherLabels) {
                return "";
            } else {
                return null;
            }
        }
    }

}
