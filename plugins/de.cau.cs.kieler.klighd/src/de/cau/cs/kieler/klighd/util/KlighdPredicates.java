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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.util;

import static de.cau.cs.kieler.klighd.util.KlighdProperties.COLLAPSED_RENDERING;
import static de.cau.cs.kieler.klighd.util.KlighdProperties.EXPANDED_RENDERING;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.IPropertyHolder;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KStyle;

/**
 * Various convenience implementations of {@link Predicate}.<br>
 * To be continued :-)
 *
 * @author chsch
 *
 * @kieler.design proposed by chsch
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
     *            The actual type of the {@link IPropertyHolder} whose property <code>property</code>
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
    public static <S extends IPropertyHolder, T> Predicate<S> propertyPredicate(
            final IProperty<T> property, final T expected, final boolean unsetEqualsTrue) {
        return new Predicate<S>() {
            public boolean apply(final S properties) {

                final T value = properties.getProperty(property);
                if (value != null) {
                    return value.equals(expected);
                } else {
                    return unsetEqualsTrue;
                }
            }
        };
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
    public static <S extends KGraphElement, T> Predicate<S> kgePropertyPredicate(
            final IProperty<T> property, final T expected, final boolean unsetEqualsTrue) {
        return new Predicate<S>() {
            public boolean apply(final S node) {

                final T value = node.getProperty(property);
                if (value != null) {
                    return value.equals(expected);
                } else {
                    return unsetEqualsTrue;
                }
            }
        };
    }

    /**
     * A static (singleton) predicate definition for testing the selectabilty of view elements.
     * See also {@link #isSelectable()}.
     */
    private static final Predicate<EObject> IS_SELECTABLE = new Predicate<EObject>() {

        public boolean apply(final EObject input) {
            return KlighdProperties.isSelectable(input);
        }
    };

    /**
     * Provides a static predicate for testing an EObject whether it is a view model element that is
     * allowed to be selected by the user. Currently only {@link KGraphElement KGraphElements} and
     * {@link de.cau.cs.kieler.klighd.krendering.KText KTexts} can be selected. The returned
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
     * Provides a static predicate for testing a {@link KStyle} for its {@link KStyle#isSelection()
     * selection flag} set to <code>true</code>.
     *
     * @return a static predicate testing the provided {@link KStyle} for its selection flag set to
     *         <code>true</code>
     */
    public static Predicate<KStyle> isSelection() {
        return IS_SELECTION;
    }

    /**
     * A predicate for testing a {@link KRendering} (of a {@link KNode}) for being tagged as the
     * <i>collapsed state</i> rendering definition.
     */
    private static final Predicate<KRendering> IS_COLLAPSED_RENDERING = new Predicate<KRendering>() {
        public boolean apply(final KRendering rendering) {
            return rendering.getProperty(COLLAPSED_RENDERING);
        }
    };

    /**
     * Provides a static predicate for testing a {@link KRendering} (of a {@link KNode}) for being
     * tagged as the <i>collapsed state</i> rendering definition.
     *
     * @return a static predicate testing the provided {@link KRendering} for a <i>collapsed
     *         state<i> tag.
     */
    public static Predicate<KRendering> isCollapsedRendering() {
        return IS_COLLAPSED_RENDERING;
    }

    /**
     * A predicate for testing a {@link KRendering} (of a {@link KNode}) for being tagged as the
     * <i>expanded state</i> rendering definition.
     */
    private static final Predicate<KRendering> IS_EXPANDED_RENDERING = new Predicate<KRendering>() {
        public boolean apply(final KRendering rendering) {
            return rendering.getProperty(EXPANDED_RENDERING);
        }
    };

    /**
     * Provides a static predicate for testing a {@link KRendering} (of a {@link KNode}) for being
     * tagged as the <i>expanded state</i> rendering definition.
     *
     * @return a static predicate testing the provided {@link KRendering} for a <i>expanded
     *         state<i> tag.
     */
    public static Predicate<KRendering> isExpandedRendering() {
        return IS_EXPANDED_RENDERING;
    }

    /**
     * A predicate for testing a {@link KRendering} (of a {@link KNode}) for being tagged as the
     * <i>collapsed</i> or <i>expanded state</i> rendering definition.
     */
    private static final Predicate<KRendering> IS_COLLAPSED_OR_EXPANDED_RENDERING =
            Predicates.or(IS_COLLAPSED_RENDERING, IS_EXPANDED_RENDERING);

    /**
     * Provides a static predicate for testing a {@link KRendering} (of a {@link KNode}) for being
     * explicitly tagged as the <i>collapsed</i> or <i>expanded state</i> rendering definition.<br>
     * This predicate returns <code>false</code> if no markers are available.
     *
     * @return a static predicate testing the provided {@link KRendering} for a <i>collapsed</i> or
     *         <i>expanded state<i> tag.
     */
    public static Predicate<KRendering> isCollapsedOrExpandedRendering() {
        return IS_COLLAPSED_OR_EXPANDED_RENDERING;
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


    /**
     * Creates new compound {@link Predicates#instanceOf(Class)} predicates testing for several
     * <code>classes</code>. {@link Predicate#apply(Object) apply(Object)} of this predicate returns
     * <true> if the input is instance of one of the provided classes or interfaces.
     *
     * @param classes
     *            the classes/interface to be 'instanceof' checked
     * @return the compound {@link Predicate}
     */
    public static Predicate<Object> instanceOf(final Class<?>... classes) {
        return Predicates.or(Iterables.transform(Arrays.asList(classes), CLASS_TO_PREDICATE));
    }

    /**
     * Creates new compound {@link Predicates#instanceOf(Class)} predicates testing for several
     * <code>classes</code>. {@link Predicate#apply(Object) apply(Object)} of this predicate returns
     * <true> if the input is instance of one of the provided classes or interfaces.
     *
     * @param classes
     *            the classes/interface to be 'instanceof' checked
     * @return the compound {@link Predicate}
     */
    public static Predicate<Object> instanceOf(final Iterable<Class<?>> classes) {
        return Predicates.or(Iterables.transform(classes, CLASS_TO_PREDICATE));
    }

    /**
     * A singleton helper Function used in {@link #instanceOf(Class...)}.
     */
    private static final Function<Class<?>, Predicate<Object>> CLASS_TO_PREDICATE =
            new Function<Class<?>, Predicate<Object>>() {

                /**
                 * {@inheritDoc}
                 */
                public Predicate<Object> apply(final Class<?> clazz) {
                    return Predicates.instanceOf(clazz);
                }
            };


    /**
     * Creates new compound {@link Predicate Predicates} testing {@link EClass#isInstance(Object)}
     * for several <code>classes</code>. {@link Predicate#apply(Object) apply(Object)} of this
     * predicate returns <true> if the input is instance of one of the provided classes or
     * interfaces.
     *
     * @param classes
     *            the classes/interface to be 'instanceof' checked
     * @return the compound {@link Predicate}
     */
    public static Predicate<EObject> eInstanceOf(final EClass... classes) {
        return Predicates.or(Iterables.transform(Arrays.asList(classes), ECLASS_TO_PREDICATE));
    }

    /**
     * Creates new compound {@link Predicate Predicates} testing {@link EClass#isInstance(Object)}
     * for several <code>classes</code>. {@link Predicate#apply(Object) apply(Object)} of this
     * predicate returns <true> if the input is instance of one of the provided classes or
     * interfaces.
     *
     * @param classes
     *            the classes/interface to be 'instanceof' checked
     * @return the compound {@link Predicate}
     */
    public static Predicate<EObject> eInstanceOf(final Iterable<EClass> classes) {
        return Predicates.or(Iterables.transform(classes, ECLASS_TO_PREDICATE));
    }

    /**
     * A singleton helper Function used in {@link #eInstanceOf(EClass...)}.
     */
    private static final Function<EClass, Predicate<EObject>> ECLASS_TO_PREDICATE =
            new Function<EClass, Predicate<EObject>>() {

                /**
                 * {@inheritDoc}
                 */
                public Predicate<EObject> apply(final EClass clazz) {
                    return new Predicate<EObject>() {
                        public boolean apply(final EObject input) {
                            return clazz.isInstance(input);
                        }
                    };
                }
            };

}
