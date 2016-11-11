/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.incremental.util;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.InternalEList;

import com.google.common.base.Predicate;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.util.KlighdPredicates;

/**
 * @author carsten
 *
 */
public class UIDAdapter extends EContentAdapter {

    private static final Predicate<Object> INSTANCE_OF_KNODE =
            KlighdPredicates.instanceOf(KNode.class);
    private static final String ID_SEPARATOR = "$";
    private BiMap<String, KNode> ids = HashBiMap.create();
    private boolean invalid = false;
    
    /**
     * @param node
     * @return
     */
    public String getId(final KNode node) {
        return ids.inverse().get(node);
    }
    
    public Set<String> getIds() {
        return ids.keySet();
    }
    
    public KNode getNode(final String id) {
        return ids.get(id);
    }
    
    public boolean isInvalid() {
        return invalid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTarget(final EObject target) {
        basicSetTarget(target);
        
        if (target instanceof KNode) {
            addId((KNode) target);
        }

        final Iterator<? extends Notifier> eContents =
                resolve() ? target.eContents().iterator()
                        : ((InternalEList<? extends Notifier>) target.eContents())
                                .basicIterator();

        for (final Iterator<? extends Notifier> i =
                Iterators.filter(eContents, INSTANCE_OF_KNODE); i.hasNext();) {
            final Notifier notifier = i.next();
            addAdapter(notifier);
        }
    }
    
    @Override
    protected void unsetTarget(final EObject target) {
        basicUnsetTarget(target);
        
        if (target instanceof KNode) {
            removeId((KNode) target);
        }
        
        final Iterator<? extends Notifier> eContents =
                resolve() ? target.eContents().iterator()
                        : ((InternalEList<? extends Notifier>) target.eContents())
                                .basicIterator();

        for (final Iterator<? extends Notifier> i =
                Iterators.filter(eContents, INSTANCE_OF_KNODE); i.hasNext();) {
            final Notifier notifier = i.next();
            removeAdapter(notifier, false, true);
        }
    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected void addAdapter(final Notifier notifier) {
//        super.addAdapter(notifier);
//        
////        System.out.println("added adapter to " + notifier);
//        if (notifier instanceof KNode) {
//            addId((KNode) notifier);
//        }
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected void removeAdapter(final Notifier notifier) {
//        super.removeAdapter(notifier);
//
////        System.out.println("removed adapter from " + notifier);
//        if (notifier instanceof KNode) {
//            removeId((KNode) notifier);
//        }
//    }

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
            if (INSTANCE_OF_KNODE.apply(notification.getNewValue())
                    || INSTANCE_OF_KNODE.apply(notification.getOldValue())) {

                super.handleContainment(notification);
            }
            break;
        case Notification.ADD_MANY:
            // this case has been copied from the super implementation and augmented by the filter

            @SuppressWarnings("unchecked")
            final Iterable<Notifier> newValues =
                    (Iterable<Notifier>) Iterables.filter((Iterable<?>) notification.getNewValue(),
                            INSTANCE_OF_KNODE);

            for (final Notifier newOne : newValues) {
                this.addAdapter(newOne);
            }
            break;
            
        case Notification.REMOVE_MANY:
            // this case has been copied from the super implementation and augmented by the filter

            @SuppressWarnings("unchecked")
            final Iterable<Notifier> oldValues =
                    (Iterable<Notifier>) Iterables.filter((Iterable<?>) notification.getOldValue(),
                            INSTANCE_OF_KNODE);

            for (final Notifier oldOne : oldValues) {
                this.removeAdapter(oldOne);
            }
        }
    }

    private void addId(final KNode node) {
        KNode parent = node.getParent();
        
        StringBuilder builder = new StringBuilder();
        // Labels
        if (node.getLabels().size() > 0) {
            String text = node.getLabels().get(0).getText();
            if (text != null && text.length() > 0) {
                builder.append(text);
            }
        }
        String localId =
//                node.getProperty(null);
                parent != null ? builder.toString() : "root";
        String parentId = parent != null ? getId(parent) : "";
        String id = parentId + ID_SEPARATOR + localId;
        System.out.println("new: " + id);
        if (ids.put(id, node) != null) {
            invalid = true;
            System.out.println("invalid: " + id);
        }
    }

    private void removeId(final KNode node) {
        String id = ids.inverse().remove(node);
        System.out.println("removed: " + id);
    }

}
