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

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KIdentifier;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.util.KlighdPredicates;

/**
 * A self-propagating {@link EContentAdapter} that calculates unique ids for {@link KNode nodes},
 * {@link KEdge edges} and {@link KLabel labels}. The ids are constructed from a local id and the
 * containers unique id. If a {@link KIdentifier} is set, it's used as local id, otherwise the
 * following fallbacks are used:
 * <dl>
 * <dt>KNode</dt>
 * <dd>Concatenated labels.<br/>
 * If no labels are present, the index in the parent's list of children.</dd>
 * <dt>KEdge</dt>
 * <dd>The index in the source's list of outgoing edges.</dd>
 * <dt>KLabel</dt>
 * <dd>The index in the parent's list of labels.</d>
 * </dl>
 * 
 * @author csp
 */
public class UIDAdapter extends EContentAdapter {

    //FIXME Remove before flight!
    private boolean debug = false;

    private static final Predicate<Object> CANDIDATES =
            KlighdPredicates.instanceOf(KNode.class, KLabel.class, KEdge.class);
    private static final String ID_SEPARATOR = "$";
    private BiMap<String, KNode> nodes = HashBiMap.create();
    private BiMap<String, KEdge> edges = HashBiMap.create();
    private BiMap<String, KLabel> labels = HashBiMap.create();
    private boolean invalid = false;

    /**
     * Get the associated id.
     * 
     * @param node
     *            the node to get the id for.
     * @return the node's id.
     */
    public String getId(final KNode node) {
        return nodes.inverse().get(node);
    }

    /**
     * Get the associated id.
     * 
     * @param edge
     *            the edge to get the id for.
     * @return the edge's id.
     */
    public String getId(final KEdge edge) {
        return edges.inverse().get(edge);
    }

    /**
     * Get the associated id.
     * 
     * @param label
     *            the label to get the id for.
     * @return the label's id.
     */
    public String getId(final KLabel label) {
        return labels.inverse().get(label);
    }

    /**
     * Get all ids associated to nodes.
     * 
     * @return all nodes' ids.
     */
    public Set<String> getNodeIds() {
        return nodes.keySet();
    }

    /**
     * Get all ids associated to edges.
     * 
     * @return all edges' ids.
     */
    public Set<String> getEdgeIds() {
        return edges.keySet();
    }

    /**
     * Get all ids associated to labels.
     * 
     * @return all labels' ids.
     */
    public Set<String> getLabelIds() {
        return labels.keySet();
    }

    /**
     * Get the associated node.
     * 
     * @param id
     *            the id to get the node for.
     * @return the associated node.
     */
    public KNode getNode(final String id) {
        return nodes.get(id);
    }

    /**
     * Get the associated edge.
     * 
     * @param id
     *            the id to get the edge for.
     * @return the associated edge.
     */
    public KEdge getEdge(final String id) {
        return edges.get(id);
    }

    /**
     * Get the associated label.
     * 
     * @param id
     *            the id to get the label for.
     * @return the associated label.
     */
    public KLabel getLabel(final String id) {
        return labels.get(id);
    }

    /**
     * Get the map associating ids and nodes.
     * 
     * @return the id to node map.
     */
    public BiMap<String, KNode> getNodeMap() {
        return nodes;
    }

    /**
     * Get the map associating ids and edges.
     * 
     * @return the id to edge map.
     */
    public BiMap<String, KEdge> getEdgeMap() {
        return edges;
    }

    /**
     * Get the map associating ids and Labels.
     * 
     * @return the id to label map.
     */
    public BiMap<String, KLabel> getLabelMap() {
        return labels;
    }

    /**
     * Whether a duplicate id has occurred.
     * 
     * @return {@code true} if a duplicate id has occurred, {@code false} otherwise.
     */
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
        } else if (target instanceof KLabel) {
            addId((KLabel) target);
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

        if (target instanceof KNode) {
            removeId((KNode) target);
        } else if (target instanceof KEdge) {
            removeId((KEdge) target);
        } else if (target instanceof KLabel) {
            removeId((KLabel) target);
        }

        final Iterator<? extends Notifier> eContents = resolve() ? target.eContents().iterator()
                : ((InternalEList<? extends Notifier>) target.eContents()).basicIterator();

        for (final Iterator<? extends Notifier> i = Iterators.filter(eContents, CANDIDATES); i
                .hasNext();) {
            final Notifier notifier = i.next();
            removeAdapter(notifier);
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

    /**
     * Add a new node id to the map. If the given node has already been added, return its existing
     * id. Returns null if the id already exists for a different node.
     * 
     * @param node
     *            the node to add.
     * @return the new or existing id, or {@code null} if the id is already taken.
     */
    private String addId(final KNode node) {
        String id = getId(node);
        if (id != null) {
            return id;
        }
        KNode parent = node.getParent();
        String parentId;
        String localId = "";
        if (parent == null) {
            parentId = "";
            localId = "root";
        } else {
            parentId = getId(parent);
            KIdentifier identifier = node.getData(KIdentifier.class);
            if (identifier != null) {
                localId = identifier.getId();
            } else if (node.getLabels().size() > 0) {
                for (KLabel label : node.getLabels()) {
                    localId += label.getText();
                }
            } else {
                localId = "N" + parent.getChildren().indexOf(node);
            }
        }
        id = parentId + ID_SEPARATOR + localId;
        if (debug) {
            System.out.println("new node: " + id);
        }
        if (nodes.put(id, node) != null) {
            invalid = true;
            if (debug) {
                System.out.println("invalid node: " + id);
            }
            return null;
        }
        return id;
    }

    /**
     * Add a new edge id to the map. If the given edge has already been added, return its existing
     * id. Returns null if the id already exists for a different edge.
     * 
     * @param edge
     *            the edge to add.
     * @return the new or existing id, or {@code null} if the id is already taken.
     */
    private String addId(final KEdge edge) {
        String id = getId(edge);
        if (id != null) {
            return id;
        }
        KNode parent = edge.getSource();
        String parentId = getId(parent);
        String localId = "";
        KIdentifier identifier = edge.getData(KIdentifier.class);
        if (identifier != null) {
            localId = identifier.getId();
        } else {
            localId = "E" + parent.getOutgoingEdges().indexOf(edge);
        }
        id = parentId + ID_SEPARATOR + localId;
        if (debug) {
            System.out.println("new edge: " + id);
        }
        if (edges.put(id, edge) != null) {
            invalid = true;
            if (debug) {
                System.out.println("invalid edge: " + id);
            }
            return null;
        }
        return id;
    }

    /**
     * Add a new label id to the map. If the given label has already been added, return its existing
     * id. Returns null if the id already exists for a different label.
     * 
     * @param label
     *            the label to add.
     * @return the new or existing id, or {@code null} if the id is already taken.
     */
    private String addId(final KLabel label) {
        String id = getId(label);
        if (id != null) {
            return id;
        }
        KLabeledGraphElement parent = label.getParent();
        String parentId = "";
        if (parent instanceof KNode) {
            parentId = getId((KNode) parent);
        } else if (parent instanceof KEdge) {
            parentId = getId((KEdge) parent);
        }
        String localId;
        KIdentifier identifier = label.getData(KIdentifier.class);
        if (identifier != null) {
            localId = identifier.getId();
        } else {
            localId = "L" + parent.getLabels().indexOf(label);
        }
        id = parentId + ID_SEPARATOR + localId;
        if (debug) {
            System.out.println("new label: " + id);
        }
        if (labels.put(id, label) != null) {
            invalid = true;
            if (debug) {
                System.out.println("invalid label: " + id);
            }
            return null;
        }
        return id;
    }

    /**
     * remove the given node and its associated id.
     * 
     * @param node
     *            the node to remove.
     */
    private void removeId(final KNode node) {
        String id = nodes.inverse().remove(node);
        if (debug) {
            System.out.println("removed node: " + id);
        }
    }

    /**
     * remove the given edge and its associated id.
     * 
     * @param edge
     *            the edge to remove.
     */
    private void removeId(final KEdge edge) {
        String id = edges.inverse().remove(edge);
        if (debug) {
            System.out.println("removed edge: " + id);
        }
    }

    /**
     * remove the given label and its associated id.
     * 
     * @param label
     *            the label to remove.
     */
    private void removeId(final KLabel label) {
        String id = labels.inverse().remove(label);
        if (debug) {
            System.out.println("removed label: " + id);
        }
    }

}
