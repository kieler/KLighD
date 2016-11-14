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

import de.cau.cs.kieler.klighd.KlighdOptions;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KIdentifier;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.util.KlighdPredicates;
import de.cau.cs.kieler.klighd.util.KlighdProperties;

/**
 * @author carsten
 *
 */
public class UIDAdapter extends EContentAdapter {

    private static final Predicate<Object> CANDIDATES = KlighdPredicates.instanceOf(KNode.class);
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

    public BiMap<String, KNode> getMap() {
        return ids;
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
        } else if (target instanceof KEdge) {
            addId((KEdge) target);
        }

        final Iterator<? extends Notifier> eContents = resolve() ? target.eContents().iterator()
                : ((InternalEList<? extends Notifier>) target.eContents()).basicIterator();

        for (final Iterator<? extends Notifier> i = Iterators.filter(eContents, CANDIDATES); i
                .hasNext();) {
            final Notifier notifier = i.next();
            addAdapter(notifier);
        }
    }

    @Override
    protected void unsetTarget(final EObject target) {
        basicUnsetTarget(target);

        if (CANDIDATES.apply(target)) {
            removeId(target);
        }

        final Iterator<? extends Notifier> eContents = resolve() ? target.eContents().iterator()
                : ((InternalEList<? extends Notifier>) target.eContents()).basicIterator();

        for (final Iterator<? extends Notifier> i = Iterators.filter(eContents, CANDIDATES); i
                .hasNext();) {
            final Notifier notifier = i.next();
            removeAdapter(notifier, false, true);
        }
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
            if (CANDIDATES.apply(notification.getNewValue())
                    || CANDIDATES.apply(notification.getOldValue())) {

                super.handleContainment(notification);
            }
            break;
        case Notification.ADD_MANY:
            // this case has been copied from the super implementation and augmented by the filter

            @SuppressWarnings("unchecked")
            final Iterable<Notifier> newValues = (Iterable<Notifier>) Iterables
                    .filter((Iterable<?>) notification.getNewValue(), CANDIDATES);

            for (final Notifier newOne : newValues) {
                this.addAdapter(newOne);
            }
            break;

        case Notification.REMOVE_MANY:
            // this case has been copied from the super implementation and augmented by the filter

            @SuppressWarnings("unchecked")
            final Iterable<Notifier> oldValues = (Iterable<Notifier>) Iterables
                    .filter((Iterable<?>) notification.getOldValue(), CANDIDATES);

            for (final Notifier oldOne : oldValues) {
                this.removeAdapter(oldOne);
            }
        }
    }

    private void addId(final KNode node) {
        KNode parent = node.getParent();
        String localId = "";
        if (parent == null) {
            localId = "root";
        } else {
            KIdentifier identifier = node.getData(KIdentifier.class);
            if (identifier != null) {
                localId = identifier.getId();
            } else if (node.getLabels().size() > 0) {
                for (KLabel label : node.getLabels()) {
                    localId += label.getText();
                }
            } 
//            else {
//                throw new RuntimeException("Could not creat UID for " + node);
//            }
        }
        String parentId = parent != null ? getId(parent) : "";
        String id = parentId + ID_SEPARATOR + localId;
        System.out.println("new: " + id);
        if (ids.put(id, node) != null) {
            invalid = true;
            System.out.println("invalid: " + id);
        }
    }

    private void addId(final KEdge target) {

    }

    private void removeId(final EObject node) {
        String id = ids.inverse().remove(node);
        System.out.println("removed: " + id);
    }

}
