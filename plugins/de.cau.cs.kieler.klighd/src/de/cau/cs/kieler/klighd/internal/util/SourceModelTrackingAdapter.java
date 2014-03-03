/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.internal.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.InternalEList;

import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.impl.IPropertyToObjectMapImpl;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.klighd.util.KlighdPredicates;

/**
 * A specialized {@link EContentAdapter} realizing efficient source target element tracking by means
 * of {@link Map Maps}.
 * 
 * @author chsch
 */
public class SourceModelTrackingAdapter extends EContentAdapter {

    private static final Predicate<Object> CANDIDATES = KlighdPredicates.instanceOf(
            KGraphElement.class, KLayoutData.class, KRendering.class
            , IPropertyToObjectMapImpl.class
            );
    
    private static final IProperty<Object> MODEL_ELEMENT = KlighdInternalProperties.MODEL_ELEMEMT;

//    private final ViewContext viewContext;

    private Object mapsMonitor = this;
    private Multimap<Object, EObject> sourceTargetsMap = ArrayListMultimap.create();
    private Map<EObject, Object> targetSourceMap = Maps.newHashMap();

//    /**
//     * Constructor.
//     * 
//     * @param theViewContext
//     *            the {@link ViewContext} <code>this</code> Adapter is associated to
//     */
//    public SourceModelTracingAdapter(final ViewContext theViewContext) {
//        this.viewContext = theViewContext;
//    }

    /**
     * Returns the element in the input model that is represented by the given <code>viewElement</code>
     * in the diagram.<br>
     * <b>Note:</b> This method does not check whether <code>viewElement</code> is currently contained
     * in the view model (accessible via {@link #getViewModel()}).
     * 
     * @param viewElement
     *            the diagram element whose source element in the input (source, semantic, or
     *            business) model is requested
     * @return the element in the input model or <code>null</code> if no source element could be
     *         identified
     */
    public Object getSourceElement(final EObject viewElement) {
        if (viewElement == null) {
            return null;
        }
        
        synchronized (mapsMonitor) {
            return this.targetSourceMap.get(viewElement);
        }
    }

    /**
     * Returns the elements in the view model that represent the given <code>element</code> in the
     * diagram.<br>
     * <b>Note:</b> This method does not check whether <code>element</code> is currently contained
     * in the input model being represented (accessible via {@link #getInputModel()}).
     * 
     * @param element
     *            the object in the input (source, semantic, or business) model
     * @return a {@link Collection} of diagram elements representing the given <code>element</code>
     *         or <code>{@link Collections#emptyList()}</code> if no corresponding view model
     *         elements could be identified
     */
    public Collection<EObject> getTargetElements(final Object element) {        
        if (element == null) {
            return Collections.emptyList();
        }
        
        synchronized (mapsMonitor) {
            return this.sourceTargetsMap.get(element);
        }
    }


    /* --------------------- */
    /*   the internal part   */
    /* --------------------- */

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTarget(final EObject newTarget) {
        basicSetTarget(newTarget);

        final Iterator<? extends Notifier> eContents =
                resolve() ? newTarget.eContents().iterator()
                        : ((InternalEList<? extends Notifier>) newTarget.eContents())
                                .basicIterator();

        for (Iterator<? extends Notifier> i = Iterators.filter(eContents, CANDIDATES); i.hasNext();) {
            Notifier notifier = i.next();
            addAdapter(notifier);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addAdapter(final Notifier notifier) {
        super.addAdapter(notifier);

        addTracedElement((EObject) notifier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void removeAdapter(final Notifier notifier) {
        super.removeAdapter(notifier);

        removeTracedElement((EObject) notifier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleContainment(final Notification notification) {

        switch (notification.getEventType()) {
        case Notification.SET:
        case Notification.UNSET:
        case Notification.ADD:
        case Notification.REMOVE:
            final EObject newValue = (EObject) notification.getNewValue();

            if (CANDIDATES.apply(notification.getNewValue())
                    || CANDIDATES.apply(notification.getOldValue())) {

                if (newValue instanceof IPropertyToObjectMapImpl
                    && ((IPropertyToObjectMapImpl) newValue).getKey() != MODEL_ELEMENT) {
                    return;
                } else {
                    super.handleContainment(notification);
                }
            }
            break;
        case Notification.ADD_MANY:
        case Notification.REMOVE_MANY:
            final Object oldValues = notification.getOldValue();
            final Object newValues = notification.getNewValue();

            if (oldValues instanceof Iterable && Iterables.all((Iterable<?>) oldValues, CANDIDATES)
                    || newValues instanceof Iterable
                    && Iterables.all((Iterable<?>) newValues, CANDIDATES)) {
                super.handleContainment(notification);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyChanged(final Notification notification) {
        super.notifyChanged(notification);
        
        switch(notification.getEventType()) {
        case Notification.SET:
        case Notification.ADD:
        case Notification.REMOVE:
            break;
        case Notification.UNSET:
        default:
            return;
        }

        final int type = notification.getEventType();
        final Object newValue = notification.getNewValue();

        
        final KGraphData notifier;
        if (notification.getFeature() == KGraphPackage.eINSTANCE.getEMapPropertyHolder_Properties()
                && notification.getNotifier() instanceof KGraphData) {
            notifier = (KGraphData) notification.getNotifier();
            
        } else if (notification.getNotifier() instanceof IPropertyToObjectMapImpl) {
            notifier = (KGraphData) ((EObject) notification.getNotifier()).eContainer();

        } else {
            return;
        }
        
        if (type == Notification.SET
                && ((IPropertyToObjectMapImpl) notification.getNotifier()).getKey() == MODEL_ELEMENT
                && newValue != null) {
            removeAdapter(notifier);
            addTracedElement(notifier);
            return;

        } else if (type == Notification.ADD) {
            if (newValue instanceof IPropertyToObjectMapImpl
                    && ((IPropertyToObjectMapImpl) newValue).getKey() == MODEL_ELEMENT) {
                addTracedElement(notifier);
                return;
            }

        } else if (type == Notification.REMOVE) {
            final Object oldValue = notification.getOldValue();
            if (oldValue instanceof IPropertyToObjectMapImpl
                    && ((IPropertyToObjectMapImpl) oldValue).getKey() == MODEL_ELEMENT) {
                removeTracedElement(notifier);
                return;
            }
        }
    }


    private void addTracedElement(final EObject element) {
        synchronized (mapsMonitor) {

            final EObject viewElement;
            if (element instanceof KLayoutData) {
                viewElement = element.eContainer();
            } else {
                viewElement = element;
            }
            
            final Object sourceElement = internalGetSourceElement(viewElement);

            if (sourceElement != null
                    && !sourceTargetsMap.containsEntry(sourceElement, viewElement)) {
                // since during the additions of KGraphElements this method is called for
                //  their layout data as well, so entries might get duplicated
                // therefore, we here filter that explicitly out
                //  alternatively we might employ a HashMultiMap
                this.sourceTargetsMap.put(sourceElement, viewElement);
                this.targetSourceMap.put(viewElement, sourceElement);
            }
        }
    }

    private Object internalGetSourceElement(final EObject viewElement) {
        final Object model;
        if (KGraphPackage.eINSTANCE.getKGraphData().isInstance(viewElement)) {
            model = ((KGraphData) viewElement).getProperty(MODEL_ELEMENT);
        } else if (KGraphPackage.eINSTANCE.getKGraphElement().isInstance(viewElement)) {
            KLayoutData layoutData = ((KGraphElement) viewElement).getData(KLayoutData.class);
            if (layoutData != null) {
                model = layoutData.getProperty(KlighdInternalProperties.MODEL_ELEMEMT);
            } else {
                model = null;
            }
        } else {
            model = null;
        }

        return model;
    }
    
    private void removeTracedElement(final EObject element) {
        synchronized (mapsMonitor) {
            final EObject viewElement;
            if (element instanceof KLayoutData) {
                viewElement = element.eContainer();
            } else {
                viewElement = element;
            }
            
            final Object o = this.targetSourceMap.remove(viewElement);
            this.sourceTargetsMap.remove(o, viewElement);
        }
    }
}
