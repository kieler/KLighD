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
package de.cau.cs.kieler.klighd.util;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.InternalEList;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.klighd.kgraph.KGraphData;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;

/**
 * A specialized EContentAdapter that attaches itself only to children of a certain type. This type
 * is set in the constructor. Once attached to such a child it attaches itself recursively to all
 * child's children.<br>
 * <br>
 * It is used to observe changes in {@link de.cau.cs.kieler.klighd.krendering.KRendering KRendering}
 * of {@link KGraphElement KGraphElements}, for example. The special benefit of this content adapter
 * type is the tolerance of complete exchanges of such objects, which happens e.g. while
 * incorporating EMF Compare.<br>
 * <br>
 * Thus, the adapter is to be attached to the related {@link KGraphElement} and adapts itself to the
 * children of the intended type if they are already present or added later on.
 * 
 * @author chsch
 * 
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class LimitedKGraphContentAdapter extends EContentAdapter {
    
    private Class<? extends KGraphData> layoutDataClass = null;
    
    /**
     * Constructor.
     * 
     * @param theLayoutDataClass the data class this adapter is limited to
     */
    public LimitedKGraphContentAdapter(final Class<? extends KGraphData> theLayoutDataClass) {
        this.layoutDataClass = theLayoutDataClass;
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This specialization handles the initialization case, i.e. the recursive attachment while
     * adding it to a {@link KGraphElement}.
     */
    @Override
    protected void setTarget(final EObject target) {
        if (this.target != null) {
            return;
        }
        if (target instanceof KGraphElement) {
            basicSetTarget(target);
            
            // check if a shape layout is exists
            final KGraphData layoutData = ((KGraphElement) target).getData(this.layoutDataClass);

            if (layoutData != null) {
                // add the adapter to all contents of the shape layout
                final Iterator<? extends Notifier> i = resolve() ? layoutData.eContents().iterator()
                        : ((InternalEList<? extends Notifier>) layoutData.eContents())
                                .basicIterator();
                final Iterator<? extends Notifier> allElements = Iterators.concat(
                        Iterators.singletonIterator(layoutData), i);
                while (allElements.hasNext()) {
                    addAdapter(allElements.next());
                }
            }
        }
    }

    @Override
    public void unsetTarget(final EObject target) {
        super.unsetTarget(target);
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This specialization handles the update case, i.e. the recursive attachment and removal after
     * elements have been (deeply) added or removed to the root target after the adapter has been
     * added.
     */
    @Override
    protected void handleContainment(final Notification n) {
        if (getTarget().equals(n.getNotifier())) {
            switch (n.getEventType()) {
            case Notification.ADD:
                if (n.getNewValue() != null
                        && this.layoutDataClass.isAssignableFrom(n.getNewValue().getClass())) {
                    super.handleContainment(n);
                }
                break;
                
            case Notification.ADD_MANY:                
                @SuppressWarnings("unchecked")
                final
                List<KGraphData> filtered =
                        Lists.newArrayList(Iterables.filter((Iterable<Notifier>) n.getNewValue(),
                                this.layoutDataClass));
                if (!filtered.isEmpty()) {
                    super.handleContainment(new ENotificationImpl(
                            (InternalEObject) n.getNotifier(), n.getEventType(),
                            (EStructuralFeature) n.getFeature(), n.getOldValue(), filtered, n
                                    .getPosition()));
                }
                break;
                
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                super.handleContainment(n);
                break;
                
            case Notification.RESOLVE:
            case Notification.UNSET:
            case Notification.SET:
                // TODO
                // I think the support of these cases doesn't work similarly easy as above since addition
                //  and removal are mixed in the super method. Luckily they are not required, yet, since
                //  KGraphElement has only a collection field.
            }            
        } else {
            super.handleContainment(n);
        }
    }
}
