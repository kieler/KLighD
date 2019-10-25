/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016-2019 by
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
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KIdentifier;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPort;
import de.cau.cs.kieler.klighd.util.KlighdPredicates;

/**
 * A self-propagating {@link EContentAdapter} that can calculate unique ids for {@link KNode nodes},
 * {@link KEdge edges}, {@link KLabel labels} and {@link KPort ports}. The ids are constructed from a local id and the
 * containers unique id. If a {@link KIdentifier} is set, it's used as local id, otherwise the
 * following fallbacks are used:
 * <dl>
 * <dt>KNode</dt>
 * <dd>Concatenated labels.<br/>
 * If no labels are present, the index in the parent's list of children.</dd>
 * <dt>KEdge</dt>
 * <dd>The source port's and target node's and port's ids, if applicable.</dd>
 * <dt>KLabel</dt>
 * <dd>The index in the parent's list of labels.</d>
 * <dt>KPort</dt>
 * <dd>The index in the parent's list of ports.</d>
 * </dl>
 * Any ID clashes are then resolved by consecutive numbering.
 * 
 * @author csp, nre
 */
public class UIDAdapter extends EContentAdapter {

    private static final Predicate<Object> CANDIDATES =
            KlighdPredicates.instanceOf(KNode.class, KLabel.class, KEdge.class, KPort.class);
    private static final String ID_SEPARATOR = "$";
    private BiMap<String, KNode> nodes = HashBiMap.create();
    private BiMap<String, KEdge> edges = HashBiMap.create();
    private BiMap<String, KLabel> labels = HashBiMap.create();
    private BiMap<String, KPort> ports = HashBiMap.create();

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
     * Get the associated id.
     * 
     * @param port
     *          the port to get the id for.
     * @return the port's id.
     */
    public String getId(final KPort port) {
        return ports.inverse().get(port);
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
     * Get all ids associated to ports.
     * 
     * @return all port's ids.
     */
    public Set<String> getPortIds() {
        return ports.keySet();
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
     * Get the associated port.
     * 
     * @param id
     *          the id to get the port for.
     * @return the associated port.
     */
    public KPort getPort(final String id) {
        return ports.get(id);
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
     * Get the map associating ids and labels.
     * 
     * @return the id to label map.
     */
    public BiMap<String, KLabel> getLabelMap() {
        return labels;
    }
    
    /**
     * Get the map associating ids and ports.
     * 
     * @return the id to the port map.
     */
    public BiMap<String, KPort> getPortMap() {
        return ports;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTarget(final EObject target) {
        basicSetTarget(target);

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
        } else if (target instanceof KPort) {
            removeId((KPort) target);
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
        id = resolveIDClash(id, nodes);
        nodes.put(id, node);
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
            localId = "E";
            KPort sourcePort = edge.getSourcePort();
            if (sourcePort != null) {
                localId += addId(sourcePort);
            }
            localId += "->";
            
            KNode targetNode = edge.getTargetPort() == null ? edge.getTarget() : edge.getTargetPort().getNode();
            if (targetNode != null) {
                localId += addId(targetNode);
            }
            localId += ":";
            
            KPort targetPort = edge.getTargetPort();
            if (targetPort != null) {
                localId += addId(targetPort);
            }
        }
        id = parentId + ID_SEPARATOR + localId;
        id = resolveIDClash(id, edges);
        edges.put(id, edge);
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
        } else if (parent instanceof KPort) {
            parentId = getId((KPort) parent);
        }
        String localId;
        KIdentifier identifier = label.getData(KIdentifier.class);
        if (identifier != null) {
            localId = identifier.getId();
        } else {
            localId = "L" + parent.getLabels().indexOf(label);
        }
        id = parentId + ID_SEPARATOR + localId;
        id = resolveIDClash(id, labels);
        labels.put(id, label);
        return id;
    }
    
    /**
     * Add a new port id to the map. If the given port has already been added, return its existing
     * id. Returns null if the id already exists for a different port.
     * 
     * @param port
     *          the port to add.
     * @return the new or existing id, or {@code null} if the id is already taken.
     */
    private String addId(final KPort port) {
        String id = getId(port);
        if (id != null) {
            return id;
        }
        KNode parent = port.getNode();
        String parentId = addId(parent);
        String localId = "";
        KIdentifier identifier = port.getData(KIdentifier.class);
        if (identifier != null) {
            localId = identifier.getId();
        } else {
            localId = "P" + parent.getPorts().indexOf(port);
        }
        id = parentId + ID_SEPARATOR + localId;
        id = resolveIDClash(id, ports);
        ports.put(id, port);
        return id;
    }

    /**
     * Resolves any ID clash with consecutive numbering.
     * 
     * @param id The possibly clashing ID
     * @param elements the map to check the clash in
     * @return an ID that does not clash with any other id in {@code elements}.
     */
    private String resolveIDClash(final String id, final BiMap<String, ? extends KGraphElement> elements) {
        if (elements.containsKey(id)) {
            int cnt = 2;
            String copyId;
            do {
                copyId = id + ID_SEPARATOR + ID_SEPARATOR + "copy" + cnt;
                cnt++;
            } while (elements.containsKey(copyId));
            return copyId;
        } else {
            return id;
        }
    }

    /**
     * remove the given node and its associated id.
     * 
     * @param node
     *            the node to remove.
     */
    private void removeId(final KNode node) {
        nodes.inverse().remove(node);
    }

    /**
     * remove the given edge and its associated id.
     * 
     * @param edge
     *            the edge to remove.
     */
    private void removeId(final KEdge edge) {
        edges.inverse().remove(edge);
    }

    /**
     * remove the given label and its associated id.
     * 
     * @param label
     *            the label to remove.
     */
    private void removeId(final KLabel label) {
        labels.inverse().remove(label);
    }
    
    /**
     * remove the given port and its associated id.
     * 
     * @param port
     *          the port to remove.
     */
    private void removeId(final KPort port) {
        ports.inverse().remove(port);
    }

    /**
     * Generate IDs recursively for this {@link KNode} and all its child {@link KGraphElement}s.
     * 
     * @param node the node to start generating IDs from.
     * @param recursive whether the ids for all children should be generated as well.
     */
    public void generateIDs(final KNode node, final boolean recursive) {
        addId(node);
        
        for (KNode childNode : node.getChildren()) {
            generateIDs(childNode, recursive);
        }
        for (KPort port : node.getPorts()) {
            generateIDs(port, recursive);
        }
        for (KLabel label : node.getLabels()) {
            generateIDs(label, recursive);
        }
        for (KEdge edge : node.getOutgoingEdges()) {
            generateIDs(edge, recursive);
        }
    }
    
    /**
     * Generate IDs recursively for this {@link KPort} and all its child {@link KGraphElement}s.
     * 
     * @param port the port to start generating IDs from.
     * @param recursive whether the ids for all children should be generated as well.
     */
    public void generateIDs(final KPort port, final boolean recursive) {
        addId(port);
        
        for (KLabel label : port.getLabels()) {
            generateIDs(label, recursive);
        }
    }
    
    /**
     * Generate IDs recursively for this {@link KLabel} and all its child {@link KGraphElement}s.
     * 
     * @param label the label to start generating IDs from.
     * @param recursive whether the ids for all children should be generated as well.
     */
    public void generateIDs(final KLabel label, final boolean recursive) {
        addId(label);
    }
    
    /**
     * Generate IDs recursively for this {@link KEdge} and all its child {@link KGraphElement}s.
     * 
     * @param edge the edge to start generating IDs from.
     * @param recursive whether the ids for all children should be generated as well.
     */
    public void generateIDs(final KEdge edge, final boolean recursive) {
        addId(edge);
        
        for (KLabel label : edge.getLabels()) {
            generateIDs(label, recursive);
        }
    }

}
