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

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KStyle;
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
     * @param <S>
     *            The actual type of the {@link KGraphElement} whose property <code>property</code>
     *            is to be examined. This parameter is required to infer the most precise return type
     *            in case a method call is part of a varArgs method parameter.
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
    public static <S extends KGraphElement, T> Predicate<S> propertyPredicate(
            final IProperty<T> property, final T expected, final boolean unsetEqualsTrue) {
        return new Predicate<S>() {
            public boolean apply(final S node) {

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
    
    /**
     * A static (singleton) predicate definition for testing the selectabilty of view elements.
     * See also {@link #isSelectable()}.
     */
    private static final Predicate<EObject> IS_SELECTABLE = new Predicate<EObject>() {
        
        private final EClass kgraphElement = KGraphPackage.eINSTANCE.getKGraphElement();
        private final EClass ktext = KRenderingPackage.eINSTANCE.getKText();

        public boolean apply(final EObject input) {
            return input == null ? false : kgraphElement.isInstance(input) || ktext.isInstance(input);
        }
    };
    
    /**
     * Provides a static predicate for testing an EObject whether it is a view model element that is
     * allowed to be selected by the user. Currently only {@link KGraphElement KGraphElements} and
     * {@link de.cau.cs.kieler.core.krendering.KText KTexts} can be selected. The returned
     * {@link Predicate} tolerates <code>null</code> values and returns <code>false</code> in that
     * case.
     * 
     * @return the dedicated predicate instance (singleton)
     */
    public static Predicate<EObject> isSelectable() {
        return IS_SELECTABLE;
    }
    
    /**
     * A static (singleton) predicate definition for testing the selection flag of {@link KStyle
     * KStyles}. See also {@link #isSelection()}.
     */
    private static final Predicate<KStyle> IS_SELECTION = new Predicate<KStyle>() {
        public boolean apply(final KStyle input) {
            return input.isSelection();
        }
    };

    /**
     * A.
     * 
     * @return the dedicated predicate instance (singleton)
     */
    public static Predicate<KStyle> isSelection() {
        return IS_SELECTION;
    }
    
    /**
     * An abbreviation of {@link Predicates#not(Predicate) Predicates.not}(
     * {@link Predicates#in(Collection) Predicates.in}(...)).
     * 
     * @param <T>
     *            the type the {@link Predicate} is defined on
     * @param target
     *            the collection to check for the containment of the test object
     * @return the requested {@link Predicate}
     */
    public static <T> Predicate<T> notIn(final Collection<? extends T> target) {
        return Predicates.not(Predicates.in(target));
    }

    /**
     * An abbreviation of {@link Predicates#not(Predicate) Predicates.not}(
     * {@link Predicates#instanceOf(Class) Predicates.instanceOf}(...)).
     * 
     * @param clazz
     *            the class to the instanceof property
     * @return the requested {@link Predicate}
     */
    public static Predicate<Object> notInstanceOf(final Class<?> clazz) {
        return Predicates.not(Predicates.instanceOf(clazz));
    }
}
