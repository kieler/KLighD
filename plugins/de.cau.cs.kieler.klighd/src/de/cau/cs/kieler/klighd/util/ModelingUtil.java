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

import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

/**
 * A collection of some modeling related convenience functions.
 * 
 * @author chsch
 */
public final class ModelingUtil {
    
    private ModelingUtil() {
    }
    

    /**
     * Returns an {@link Iterable} containing all recursively contained elements of type
     * {@code value}'s type.
     * 
     * @param <T>
     *            the required type of {@code value}
     * @param value
     *            the value
     * @param clazz
     *            the required type
     * @return the requested {@link Iterable}
     */
    public static <T extends EObject> Iterable<T> eAllContentsOfType(final EObject value,
            final Class<T> clazz) {
        return Iterables2.toIterable(Iterators.filter(value.eAllContents(), clazz));
    }

    
    /**
     * Returns an {@link Iterable} containing {@code value} itself and all recursively contained
     * elements.
     * 
     * @param value
     *            the value
     * @return the required {@link Iterable}
     */
    public static Iterable<? extends EObject> selfAndEAllContents(final EObject value) {
        return Iterables2.toIterable(
                Iterators.concat(
                        Iterators.singletonIterator(value),
                        value.eAllContents()
                )
        );
    }

    
    /**
     * Returns an {@link Iterable} containing {@code value} itself and all recursively contained
     * elements of {@code value}'s type.
     * 
     * @param <T>
     *            the generic type of {@code value}
     * @param value
     *            the value
     * @return the required {@link Iterable}
     */
    public static <T extends EObject> Iterable<T> selfAndEAllContentsOfSameType(final T value) {
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) value.getClass();
        return Iterables2.toIterable(
                Iterators.concat(
                        Iterators.singletonIterator(value),
                        Iterators.filter(value.eAllContents(), clazz)
                )
        );
    }

    
    /**
     * Returns the first element of type <code>eClass</code> in <code>eObject</code>'s containment
     * hierarchy.
     * 
     * @param eObject
     *            the element reveal the required parent
     * @param eClass
     *            the type of the required parent
     * @return the required parent
     */
    public static EObject eContainerOfType(final EObject eObject, final EClass eClass) {
        EObject eo = eObject;
        while (eo != null && eo.eContainer() != null) {
            if (eClass.isInstance(eo.eContainer())) {
                return eo.eContainer();
            } else {
                eo = eo.eContainer();
            }
        }
        return null;
    }

    /**
     * Returns the first element of type <code>eClass</code> in <code>eObject</code>'s containment
     * hierarchy including <code>eObject</code> itself.
     * 
     * @param eObject
     *            the element reveal the required parent
     * @param eClass
     *            the type of the required parent
     * @return the required parent
     */
    public static EObject eContainerOrSelfOfType(final EObject eObject, final EClass eClass) {
        if (eClass.isInstance(eObject)) {
            return eObject;
        } else {
            return eContainerOfType(eObject, eClass);
        }
    }

    /**
     * Returns the first element of type <code>eClass</code> in <code>eObject</code>'s containment
     * hierarchy.
     * 
     * @param <T>
     *            the type of the required parent
     * @param eObject
     *            the element reveal the required parent
     * @param clazz
     *            the type of the required parent
     * @return the required parent
     */
    public static <T> T eContainerOfType(final EObject eObject, final Class<T> clazz) {
        EObject eo = eObject;
        while (eo != null && eo.eContainer() != null) {
            if (clazz.isInstance(eo.eContainer())) {
                @SuppressWarnings("unchecked")
                final T res = ((T) eo.eContainer());
                return res;
            } else {
                eo = eo.eContainer();
            }
        }
        return null;
    }

    /**
     * Returns the first element of type <code>eClass</code> in <code>eObject</code>'s containment
     * hierarchy including <code>eObject</code> itself.
     * 
     * @param <T>
     *            the type of the required parent
     * @param eObject
     *            the element reveal the required parent
     * @param clazz
     *            the type of the required parent
     * @return the required parent
     */
    public static <T> T eContainerOrSelfOfType(final EObject eObject, final Class<T> clazz) {
        if (clazz.isInstance(eObject)) {
            @SuppressWarnings("unchecked")
            final T res = ((T) eObject);
            return res;
        } else {
            return eContainerOfType(eObject, clazz);
        }
    }

    /**
     * Creates an iterator traversing along the 'eContainer' chain of an {@link EObject}.
     * 
     * @param eObject
     *            the element to start with
     * @return the an {@link Iterator} visiting all eContainers.
     */
    public static Iterator<EObject> eAllContainers(final EObject eObject) {
        return new EContainerIterator(eObject, false);
    }
    
    /**
     * Creates an iterator traversing along the 'eContainer' chain of an {@link EObject} including
     * (starting with) <code>eOject</code> itself.
     * 
     * @param eObject
     *            the element to start with
     * @return the an {@link Iterator} visiting <code>eObject</code> and all eContainers.
     */
    public static Iterator<EObject> selfAndEAllContainers(final EObject eObject) {
        return new EContainerIterator(eObject, true);
    }
    
    /**
     * A simple implementation of the {@link Iterator} interface allowing to traverse the
     * 'eContainer' chain of {@link EObject EObjects}.
     * 
     * @author chsch
     */
    private static class EContainerIterator implements Iterator<EObject> {

        private EObject element;
        
        public EContainerIterator(final EObject theElement, final boolean includingSelf) {
            if (theElement == null) {
                throw new IllegalArgumentException("Class EContainerIterator:"
                        + "Constructor of EContainerIterator requires a non-null input.");
            }
            this.element = includingSelf ? theElement : theElement.eContainer();
        }
        
        public boolean hasNext() {
            return element != null;
        }

        public EObject next() {
            if (element == null) {
                throw new IllegalStateException("Class EContainerIterator: There is no more element.");
            }
            final EObject res = element;
            element = element.eContainer();
            return res;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "Removing elements from a containment hierarchy is not allowed!");
        }
    }


    /**
     * A modified version of {@link EObject#eAllContents()} allowing to filter the traversed
     * elements based on its type. In contrast to
     * 
     * <pre>
     * Iterators.filter(eObject.eAllContents(), ...)
     * </pre>
     * 
     * or {@link ModelingUtil#eAllContentsOfType(EObject, Class)} this function does not visit
     * elements being not of one of the given types. Consequently, elements of one of the given types
     * being (deeply) contained by those skipped elements are not found.
     * 
     * @param eObject
     *            the {@link EObject} whose contents are to be traversed
     * @param types
     *            the types of elements to be visited (varArgs)
     * @return the tailored {@link TreeIterator}
     */
    public static TreeIterator<EObject> eAllContentsOfType2(final EObject eObject,
            final Class<?>... types) {
        if (types == null) {
            return eObject.eAllContents();
        }

        final Predicate<Object> p = KlighdPredicates.instanceOf(Arrays.asList(types));

        return new AbstractTreeIterator<EObject>(eObject, false) {
            private static final long serialVersionUID = 1L;

            @Override
            public Iterator<EObject> getChildren(final Object object) {
                return Iterators.filter(((EObject) object).eContents().iterator(), p);
            }
        };
    }



    /**
     * A little shortcut for removing {@link org.eclipse.emf.common.notify.Adapter Adapters} from an
     * {@link EObject} by their type.<br>
     * <br>
     * Is to be used in instead of
     * {@link com.google.common.collect.Iterables#removeIf(Iterable, Predicate)
     * Iterables#removeIf(Iterable, Predicate)}, which may lead to unintended behavior wrt. to the
     * caused notifications. That one moves elements via {@link java.util.List#set(int, Object)
     * List#set(int, Object)} leading to duplicate entries in the adapters list, and removes the
     * duplicates later on causing {@link org.eclipse.emf.common.notify.Notification#REMOVE
     * Notification#REMOVE} or {@link org.eclipse.emf.common.notify.Notification#REMOVE_MANY
     * #REMOVE_MANY} notifications for elements that are still in the list!
     * 
     * @param eObject
     *            the {@link EObject} to remove {@link org.eclipse.emf.common.notify.Adapter
     *            Adapters} from
     * @param adapterTypes
     *            the types of the {@link org.eclipse.emf.common.notify.Adapter Adapters} to be
     *            removed
     */
    public static void removeAdapters(final EObject eObject, final Class<?>... adapterTypes) {
        final Predicate<Object> p = KlighdPredicates.instanceOf(Arrays.asList(adapterTypes));
        Iterators.removeIf(eObject.eAdapters().iterator(), p);
    }

    /**
     * A little shortcut for removing {@link org.eclipse.emf.common.notify.Adapter Adapters} from an
     * {@link EObject} by means of a {@link Predicate}.<br>
     * <br>
     * Is to be used in instead of
     * {@link com.google.common.collect.Iterables#removeIf(Iterable, Predicate)
     * Iterables#removeIf(Iterable, Predicate)}, which may lead to unintended behavior wrt. to the
     * caused notifications. That one moves elements via {@link java.util.List#set(int, Object)
     * List#set(int, Object)} leading to duplicate entries in the adapters list, and removes the
     * duplicates later on causing {@link org.eclipse.emf.common.notify.Notification#REMOVE
     * Notification#REMOVE} or {@link org.eclipse.emf.common.notify.Notification#REMOVE_MANY
     * #REMOVE_MANY} notifications for elements that are still in the list!
     * 
     * @param eObject
     *            the {@link EObject} to remove {@link org.eclipse.emf.common.notify.Adapter
     *            Adapters} from
     * @param predicate
     *            the predicate to be applied to the list of
     *            {@link org.eclipse.emf.common.notify.Adapter Adapters}
     */
    public static void removeAdapters(final EObject eObject, final Predicate<Object> predicate) {
        Iterators.removeIf(eObject.eAdapters().iterator(), predicate);
    }
}
