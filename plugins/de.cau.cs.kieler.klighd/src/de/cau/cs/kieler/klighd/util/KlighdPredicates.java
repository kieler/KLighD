/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.util;

import com.google.common.base.Predicate;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;

/**
 * Various convenience implementations of {@link Predicate}.<br>
 * To be continued :-)
 * 
 * @author chsch
 */
public final class KlighdPredicates {

    /**
     * Hidden constructor.
     */
    private KlighdPredicates() {
    }

    /**
     * Provider method creating a {@link IsNotEmptyAndExpandedPredicate} for testing the expansion
     * state of {@link KNode KNodes}.
     * 
     * @return a new instance of the required predicate
     */
    public static IsNotEmptyAndExpandedPredicate isNotEmptyAndExpanded() {
        return new IsNotEmptyAndExpandedPredicate();
    }

    /**
     * Named {@link Predicate} implementation allowing to added setter for context specific
     * information in future, e.g. the current viewer.
     * 
     * @author chsch
     */
    public static class IsNotEmptyAndExpandedPredicate implements Predicate<KNode> {

        /**
         * {@inheritDoc}
         */
        public boolean apply(final KNode node) {
            return !node.getChildren().isEmpty()
                    && RenderingContextData.get(node).getProperty(
                            KlighdInternalProperties.POPULATED);
        }
    }

    /**
     * @param <T>
     *            The type of the properties value.
     * @param property
     *            the property to check
     * @param expected
     *            the expected value
     * @param unsetEqualsTrue
     *            if set to true, the predicate evaluates to true if the property is not set.
     * @return a {@link Predicate} that tests if the specified property equals the expected value.
     *         If the property is not set, the predicate evaluates to false.
     */
    public static <T> Predicate<KNode> propertyPredicate(final IProperty<T> property,
            final T expected, final boolean unsetEqualsTrue) {
        return new Predicate<KNode>() {
            public boolean apply(final KNode node) {

                KLayoutData data = node.getData(KLayoutData.class);
                if (data != null) {
                    T value = data.getProperty(property);
                    if (value != null) {
                        return value.equals(expected);
                    } else {
                        return unsetEqualsTrue;
                    }
                }

                return false;
            }
        };
    }
}
