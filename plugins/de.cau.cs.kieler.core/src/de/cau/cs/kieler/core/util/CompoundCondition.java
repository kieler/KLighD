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
package de.cau.cs.kieler.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A condition that is composed of a set of other conditions.
 * 
 * @param <T> the type for which the condition is used
 * @author msp
 * @deprecated use {@code com.google.common.base.Predicates.and(...)} instead (contained in Guava)
 */
@Deprecated
public class CompoundCondition<T> implements ICondition<T> {

    private List<ICondition<T>> conditions;

    /**
     * getter method for conditions.
     * @return the list of child conditions
     */
    public List<ICondition<T>> getChildConditions() {
        return conditions;
    }
    
    /**
     * Creates a compound condition from a collection of conditions.
     * 
     * @param theconditions
     *            the conditions
     */
    public CompoundCondition(final Collection<ICondition<T>> theconditions) {
        conditions = new ArrayList<ICondition<T>>(theconditions);
    }

    /**
     * Creates a compound condition from an array of conditions.
     * 
     * @param theconditions
     *            the conditions
     */
    public CompoundCondition(final ICondition<T>[] theconditions) {
        conditions = new ArrayList<ICondition<T>>(theconditions.length);
        for (ICondition<T> cond : theconditions) {
            conditions.add(cond);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean evaluate(final T object) {
        for (ICondition<T> cond : conditions) {
            if (cond == null || !cond.evaluate(object)) {
                return false;
            }
        }
        return true;
    }

}
