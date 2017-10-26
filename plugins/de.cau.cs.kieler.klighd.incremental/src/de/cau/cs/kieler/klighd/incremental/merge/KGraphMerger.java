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
package de.cau.cs.kieler.klighd.incremental.merge;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;

import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.klighd.incremental.diff.KComparison;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphData;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KInsets;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPoint;
import de.cau.cs.kieler.klighd.kgraph.KPort;
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout;

/**
 * Recursively merge two KGraphs.
 * 
 * @author csp
 */
public class KGraphMerger {

    /** The comparison to merge. */
    private KComparison comparison;
    /** The filter to determine, which KGraphData to merge. */
    private Predicate<KGraphData> filter;

    /** Maps ports in the new model to ports in the base model. */
    private Map<KPort, KPort> newToBasePortMap;
    /** Set of nodes whose ports already have been updated. */
    private Set<KNode> portupdatedNodes;
    /** Map of base and new edges whose ports still need to be updated. */
    private Map<KEdge, KEdge> edgesToDo;

    /**
     * Create a new merger working with the given comparison.
     * 
     * @param comparison
     *            the comparison to work on.
     * @param filter
     *            the filter defining which KGraphData is merged.
     */
    public KGraphMerger(final KComparison comparison, final Predicate<KGraphData> filter) {
        this.comparison = comparison;
        this.filter = filter;
        newToBasePortMap = Maps.newHashMap();
        portupdatedNodes = new HashSet<KNode>();
        edgesToDo = Maps.newHashMap();
    }

    /**
     * Initiate the merge.
     */
    public void merge() {
        handleRemovedNodes();
        handleAddedNodes();
        handleMatchedNodes();
        // Fix ports of unprocessed edges.
        for (Entry<KEdge, KEdge> entry : edgesToDo.entrySet()) {
            handlePorts(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Remove nodes from the base model that are not longer present in the new model.
     */
    private void handleRemovedNodes() {
        for (KNode node : comparison.getRemovedNodes()) {
            removeNode(node);
        }
    }

    /**
     * Remove the given node and all connected edges from its parent.
     * 
     * @param node
     *            the node to remove.
     */
    private void removeNode(final KNode node) {
        for (KEdge edge : node.getOutgoingEdges()) {
            edge.setTarget(null);
        }
        for (KEdge edge : node.getIncomingEdges()) {
            edge.setSource(null);
        }
        node.getParent().getChildren().remove(node);
    }

    /**
     * Add new from the new model to the base model.
     */
    private void handleAddedNodes() {
        for (KNode node : comparison.getAddedNodes()) {
            addNode(node);
        }
        // Add edges after adding the nodes to ensure that all targets are available.
        for (KNode node : comparison.getAddedNodes()) {
            handleEdges(comparison.lookupBaseNode(node), node);
        }
    }

    /**
     * Add a copy of the given node and all its children to the base model. If the given node's
     * parent is not present in the base model, the parent (and all its children) are added instead.
     * 
     * @param node
     *            the node to add.
     */
    private void addNode(final KNode node) {
        if (node.getParent() == null) {
            throw new RuntimeException(
                    "Couldn't add new node " + node + "to base model:\nNode has no parent.");
        }
        KNode baseParent = comparison.lookupBaseNode(node.getParent());
        if (baseParent == null) {
            // The new node's parent is missing in the base model as well.
            // Add it and its children (including this node) first.
            addNode(node.getParent());
        } else {
            if (comparison.lookupBaseNode(node) == null) {
                baseParent.getChildren().add(copyKNode(node));
            }
        }
    }

    /**
     * Update nodes that are present in both the base and new model.
     */
    private void handleMatchedNodes() {
        for (ValueDifference<KNode> diff : comparison.getMatchedNodes()) {
            // TODO Maybe check if update is really necessary
            updateKnode(diff.leftValue(), diff.rightValue());
        }
    }

    /**
     * Update the given node pair.
     * 
     * @param baseNode
     *            the node to update to.
     * @param newNode
     *            the node to update from.
     */
    private void updateKnode(final KNode baseNode, final KNode newNode) {
        updateGraphElement(baseNode, newNode);
        updateShapeLayout(baseNode, newNode);
        copyInsets(newNode.getInsets(), baseNode.getInsets());
        handleLabels(baseNode, newNode);
        handlePorts(baseNode, newNode);
        handleEdges(baseNode, newNode);
    }

    /**
     * Update all outgoing edges of the given node pair. Edges are removed, copied from the new
     * model or updated.
     * 
     * @param baseNode
     *            the node to update to.
     * @param newNode
     *            the node to update from.
     */
    private void handleEdges(final KNode baseNode, final KNode newNode) {
        Set<KEdge> oldEdges = null;
        if (baseNode != null) {
            oldEdges = new HashSet<KEdge>(baseNode.getOutgoingEdges());
        }
        for (KEdge newEdge : Lists.newLinkedList(newNode.getOutgoingEdges())) {
            KEdge baseEdge = comparison.lookupBaseEdge(newEdge);
            if (baseEdge == null) {
                baseEdge = EcoreUtil.copy(newEdge);
                updateEdge(baseEdge, newEdge);
            } else {
                if (oldEdges != null) {
                    oldEdges.remove(baseEdge);
                }
                updateEdge(baseEdge, newEdge);
            }
        }
        if (baseNode != null) {
            baseNode.getOutgoingEdges().removeAll(oldEdges);
        }
    }

    /**
     * Update a given edge pair.
     * 
     * @param baseEdge
     *            the edge to update to.
     * @param newEdge
     *            the edge to update from.
     */
    private void updateEdge(final KEdge baseEdge, final KEdge newEdge) {
        updateGraphElement(baseEdge, newEdge);
        KNode baseTarget = comparison.lookupBaseNode(newEdge.getTarget());
        if (baseTarget != baseEdge.getTarget()) {
            baseEdge.setTarget(baseTarget);
        }
        KNode baseSource = comparison.lookupBaseNode(newEdge.getSource());
        if (baseSource != baseEdge.getSource()) {
            baseEdge.setSource(baseSource);
        }
        handleLabels(baseEdge, newEdge);
        handlePorts(baseEdge, newEdge);

        // Transfer source and target points from new model to base model
        baseEdge.setSourcePoint(newEdge.getSourcePoint());
        baseEdge.setTargetPoint(newEdge.getTargetPoint());
        // Transfer bend points
        EList<KPoint> bendPoints = baseEdge.getBendPoints();
        bendPoints.clear();
        for (KPoint kPoint : newEdge.getBendPoints()) {
            bendPoints.add(EcoreUtil.copy(kPoint));
        }        
    }

    /**
     * Update all contained labels of the given element pair. Labels are removed, moved from the new
     * model or updated.
     * 
     * @param baseElement
     *            the element to update to.
     * @param newElement
     *            the element to update from.
     */
    private void handleLabels(final KLabeledGraphElement baseElement,
            final KLabeledGraphElement newElement) {

        LinkedList<KLabel> newLabels = Lists.newLinkedList();
        for (KLabel newLabel : newElement.getLabels()) {
            KLabel baseLabel = comparison.lookupBaseLabel(newLabel);
            if (baseLabel != null) {
                newLabels.add(baseLabel);
                updateLabel(baseLabel, newLabel);
            } else {
                newLabels.add(newLabel);
            }
        }
        baseElement.getLabels().clear();
        baseElement.getLabels().addAll(newLabels);
    }

    /**
     * Update the given label pair.
     * 
     * @param baseLabel
     *            the label to update to.
     * @param newLabel
     *            the label to update from.
     */
    private void updateLabel(final KLabel baseLabel, final KLabel newLabel) {
        updateGraphElement(baseLabel, newLabel);
        updateShapeLayout(baseLabel, newLabel);
        baseLabel.setText(newLabel.getText());
        copyInsets(newLabel.getInsets(), baseLabel.getInsets());
    }

    /**
     * Update the ports of the given node pair. For now, all existing ports are removed and copied
     * from the new model. Source and target ports of edges need to be updated individually.
     * 
     * @see {@link #handlePorts(KEdge, KEdge)}, {@link #newToBasePortMap}, {@link #portupdatedNodes}
     * @param baseNode
     *            the node to update to.
     * @param newNode
     *            the node to update from.
     */
    private void handlePorts(final KNode baseNode, final KNode newNode) {
        // TODO handle ports better
        EList<KPort> basePorts = baseNode.getPorts();
        basePorts.clear();
        for (KPort newPort : newNode.getPorts()) {
            KPort basePort = EcoreUtil.copy(newPort);
            basePorts.add(basePort);
            newToBasePortMap.put(newPort, basePort);
        }
        portupdatedNodes.add(baseNode);
    }

    /**
     * Update source and target ports of the given edge pair. Both source and target node must have
     * updated ports.
     * 
     * @see {@link #portupdatedNodes}, {@link #newToBasePortMap}
     * @param baseEdge
     *            the edge to update to.
     * @param newEdge
     *            the edge to update from.
     */
    private void handlePorts(final KEdge baseEdge, final KEdge newEdge) {
        if (portupdatedNodes.contains(baseEdge.getSource())
                && portupdatedNodes.contains(baseEdge.getTarget())) {

            baseEdge.setSourcePort(newToBasePortMap.get(newEdge.getSourcePort()));
            baseEdge.setTargetPort(newToBasePortMap.get(newEdge.getTargetPort()));
        } else {
            edgesToDo.put(baseEdge, newEdge);
        }
    }

    /**
     * Update general graph element information such as {@link KGraphData} and properties.
     * 
     * @see {@link #filter graph data filter}
     * @param baseElement
     *            the element to update to.
     * @param newElement
     *            the element to update from.
     */
    private void updateGraphElement(final KGraphElement baseElement,
            final KGraphElement newElement) {
        List<KGraphData> baseData = baseElement.getData();
        List<KGraphData> newData = newElement.getData();
        baseData.removeIf(filter);
        newData.removeIf(filter.negate());
        baseData.addAll(newData);
        baseElement.copyProperties(newElement);
        EMap<IProperty<?>, Object> baseProperties = baseElement.getProperties();
        LinkedList<IProperty<?>> removedProperties = Lists.newLinkedList(
                Sets.difference(baseProperties.keySet(), newElement.getProperties().keySet()));
        for (IProperty<?> property : removedProperties) {
            baseProperties.removeKey(property);
        }
    }

    /**
     * Update shape layout information such as position and size.
     * 
     * @param baseElement
     *            the element to update to.
     * @param newElement
     *            the element to update from.
     */
    private void updateShapeLayout(final KShapeLayout baseElement, final KShapeLayout newElement) {
        baseElement.setPos(newElement.getXpos(), newElement.getYpos());
        baseElement.setSize(newElement.getWidth(), newElement.getHeight());
    }

    
    
    /**
     * Copy inset values. Does nothing if one of the given insets is {@code null}.
     * 
     * @param sourceInsets
     *            the insets to copy values from.
     * @param targetInsets
     *            the insets to copy values to.
     */
    private void copyInsets(final KInsets sourceInsets, final KInsets targetInsets) {
        if (targetInsets != null && sourceInsets != null) {
            targetInsets.setLeft(sourceInsets.getLeft());
            targetInsets.setRight(sourceInsets.getRight());
            targetInsets.setTop(sourceInsets.getTop());
            targetInsets.setBottom(sourceInsets.getBottom());
        }
    }

    /**
     * Create a KNode copy using the {@link KNodeCopier} to track added ports.
     * 
     * @param node
     *            the node to copy.
     * @return a copy of the node.
     */
    private KNode copyKNode(final KNode node) {
        KNodeCopier copier = new KNodeCopier(newToBasePortMap, portupdatedNodes);
        KNode result = (KNode) copier.copy(node);
        copier.copyReferences();
        return result;
    }

}
