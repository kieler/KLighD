/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016,2020 by
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
package de.cau.cs.kieler.klighd.incremental.merge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference.ValueDifference;
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
 * @author csp, nre
 */
public class KGraphMerger {
    
    private static String INVALID_MOVE_MESSAGE = "Cannot move element to an out of bounds position in the reference " + 
    "list. Check if the synthesis leaves references to elements that are not in the graph via containment or if there "+
    "is an error in this code. The graph may not be mapped correctly now.";

    /** The comparison to merge. */
    private KComparison comparison;
    /** The filter to determine, which KGraphData to merge. */
    private Predicate<KGraphData> filter;
    
    /** A map that gets filled with all updated elements for post-processing. */
    private Map<KGraphElement, KGraphElement> updatedElements = new HashMap<>();

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
    }

    /**
     * Initiate the merge.
     */
    public void merge() {
        handleRemovedNodes();
        handleAddedNodes();
        handleMatchedNodes();
        
        // handle edges after everything else is done, because they are contained in their source, therefore we can't be 
        // sure the target exists until we have gone through the entire model
        handleRemovedEdges();
        handleAddedEdges();
        handleMatchedEdges();
        
        updatePositions();
    }
    
    /**
     * Removes edges from the base model.
     */
    private void handleRemovedEdges() {
        for (KEdge edge : comparison.getRemovedEdges()) {
            edge.setSource(null);
            edge.setTarget(null);
            edge.setSourcePort(null);
            edge.setTargetPort(null);
        }
    }

    /**
     * Adds edges from the new model to the base model.
     */
    private void handleAddedEdges() {
        Set<KNode> nodesWithNewEdges = new HashSet<>();
        Stream<KEdge> newEdges = comparison.getAddedEdges().stream();
        newEdges.forEach(
            (KEdge edge) -> nodesWithNewEdges.add(edge.getSource())
        );
        nodesWithNewEdges.forEach(
            (KNode node) -> addEdges(node)
        );
    }
    
    /**
     * Adds all edges of a node that has some new edge.
     */
    private void addEdges(KNode newEdgeContainer) {
        KNode baseNode = comparison.lookupBaseNode(newEdgeContainer);
        handleEdges(baseNode, newEdgeContainer);
    }
    
    /**
     * Handles edges that are present in both the base model and the new model.
     */
    private void handleMatchedEdges() {
        Set<KNode> nodesWithMatchedEdges = new HashSet<>();
        for (ValueDifference<KEdge> diff : comparison.getMatchedEdges()) {
            nodesWithMatchedEdges.add(diff.leftValue().getSource());
            nodesWithMatchedEdges.add(diff.rightValue().getSource());
        }
        nodesWithMatchedEdges.forEach(
            (KNode node) -> addEdges(node)
        );
        
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
        for (KEdge edge : new ArrayList<KEdge>(node.getOutgoingEdges())) {
            edge.setSource(null);
            edge.setTarget(null);
            edge.setSourcePort(null);
            edge.setTargetPort(null);
        }
        for (KEdge edge : new ArrayList<KEdge>(node.getIncomingEdges())) {
            edge.setSource(null);
            edge.setTarget(null);
            edge.setSourcePort(null);
            edge.setTargetPort(null);
        }
        for (KPort port : node.getPorts()) {
            port.getEdges().clear();
        }
        if (node.getParent() != null) {
            node.getParent().getChildren().remove(node);
        }
    }

    /**
     * Add new from the new model to the base model.
     */
    private void handleAddedNodes() {
        // Before adding the nodes we have to make sure they are added in the same order as they appear in the
        // containment list of their parent to ensure correct generation and mapping of ID-less elements.
        // Add parent-less nodes first, then the sorted nodes with parents.
        Stream<KNode> nodesWithoutParent = comparison.getAddedNodes().stream().filter((KNode n) -> n.getParent() == null);
        Stream<KNode> nodesWithParent    = comparison.getAddedNodes().stream().filter((KNode n) -> n.getParent() != null);
        Stream.concat(nodesWithoutParent, nodesWithParent.sorted(
            (KNode n1, KNode n2) -> n1.getParent().getChildren().indexOf(n1)
                                  - n2.getParent().getChildren().indexOf(n2)
        )).forEachOrdered(
            (KNode node) -> addNode(node)
        );
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
            // A connected node that is however not contained is added to the new model. Handle this in a special case
            // and just copy the node and add it to the base adapter, without putting it into the base model directly.
            if (comparison.lookupBaseNode(node) == null) {
                KNode copiedNode = EcoreUtil.copy(node);
                comparison.getBaseAdapter().generateIDs(copiedNode);
            }
            return;
        }
        // Otherwise, the node has a parent, so add the node to that.
        KNode baseParent = comparison.lookupBaseNode(node.getParent());
        if (baseParent == null) {
            if (!comparison.getAddedNodes().contains(node.getParent())) {
                // The new node's parent is missing in the base model as well and is not scheduled to be added
                // otherwise. Add it and its children (including this node), but leave a warning that there might be an
                // issue.
                System.err.println(this.getClass().getName() + ": There is a unknown node to be added to the base "
                        + "graph. Trying to continue, but this may cause further errors.");
                addNode(node.getParent());
            }
        } else {
            if (comparison.lookupBaseNode(node) == null) {
                int oldPosition = node.getParent().getChildren().indexOf(node);
                KNode copiedNode = EcoreUtil.copy(node);
                baseParent.getChildren().add(oldPosition, copiedNode);
                comparison.getBaseAdapter().generateIDs(copiedNode);
            }
        }
    }

    /**
     * Update nodes that are present in both the base and new model.
     */
    private void handleMatchedNodes() {
        for (ValueDifference<KNode> diff : comparison.getMatchedNodes()) {
            updateKnode(diff.leftValue(), diff.rightValue());
        }
    }
    
    /**
     * Updates the positions of all nodes, edges, ports and labels in their containment and reference lists to match the
     * new model.
     */
    private void updatePositions() {
        for (Entry<KGraphElement, KGraphElement> entry : updatedElements.entrySet()) {
            if (entry.getKey() instanceof KNode) {
                updatePosition((KNode) entry.getKey(), (KNode) entry.getValue());
            }
            if (entry.getKey() instanceof KEdge) {
                updatePosition((KEdge) entry.getKey(), (KEdge) entry.getValue());
            }
            if (entry.getKey() instanceof KPort) {
                updatePosition((KPort) entry.getKey(), (KPort) entry.getValue());
            }
            if (entry.getKey() instanceof KLabel) {
                updatePosition((KLabel) entry.getKey(), (KLabel) entry.getValue());
            }
        }
        updatedElements.clear();
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
        // edges are handled after all possible sources and targets have been handled
        updatedElements.put(baseNode, newNode);
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
            if (baseEdge == null || baseEdge.getTarget() == null) {
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
            for (KEdge oldEdge : oldEdges) {
                oldEdge.setSource(null);
                oldEdge.setTarget(null);
                oldEdge.setSourcePort(null);
                oldEdge.setTargetPort(null);
            }
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
        // Transfer source and target.
        KNode baseTarget = comparison.lookupBaseNode(newEdge.getTarget());
        if (baseTarget != baseEdge.getTarget()) {
            baseEdge.setTarget(baseTarget);
        }
        KNode baseSource = comparison.lookupBaseNode(newEdge.getSource());
        if (baseSource != baseEdge.getSource()) {
            baseEdge.setSource(baseSource);
        }
        
        // Transfer source and target ports.
        KPort baseTargetPort = comparison.lookupBasePort(newEdge.getTargetPort());
        if (baseTargetPort != baseEdge.getTargetPort()) {
            baseEdge.setTargetPort(baseTargetPort);
        }
        KPort baseSourcePort = comparison.lookupBasePort(newEdge.getSourcePort());
        if (baseSourcePort != baseEdge.getSourcePort()) {
            baseEdge.setSourcePort(baseSourcePort);
        }

        // Transfer source and target points from new model to base model
        baseEdge.setSourcePoint(newEdge.getSourcePoint());
        baseEdge.setTargetPoint(newEdge.getTargetPoint());
        // Transfer bend points
        EList<KPoint> bendPoints = baseEdge.getBendPoints();
        bendPoints.clear();
        for (KPoint kPoint : newEdge.getBendPoints()) {
            bendPoints.add(EcoreUtil.copy(kPoint));
        }
        comparison.getBaseAdapter().generateIDs(baseEdge);
        handleLabels(baseEdge, newEdge);
        updatedElements.put(baseEdge, newEdge);
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
        Set<KLabel> oldLabels = null;
        if (baseElement != null) {
            oldLabels = new HashSet<KLabel>(baseElement.getLabels());
        }
        for (KLabel newLabel : Lists.newLinkedList(newElement.getLabels())) {
            KLabel baseLabel = comparison.lookupBaseLabel(newLabel);
            if (baseLabel == null) {
                baseLabel = EcoreUtil.copy(newLabel);
                updateLabel(baseLabel, newLabel);
            } else {
                if (oldLabels != null) {
                    oldLabels.remove(baseLabel);
                }
                updateLabel(baseLabel, newLabel);
            }
        }
        if (baseElement != null) {
            baseElement.getLabels().removeAll(oldLabels);
        }
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
        // Transfer parent.
        KLabeledGraphElement newParent = newLabel.getParent();
        KLabeledGraphElement baseParent = null;
        if (newParent instanceof KNode) {
            baseParent = comparison.lookupBaseNode((KNode) newParent);
        } else if (newParent instanceof KPort) {
            baseParent = comparison.lookupBasePort((KPort) newParent);
        } else if (newParent instanceof KEdge) {
            baseParent = comparison.lookupBaseEdge((KEdge) newParent);
        }
        if (baseParent != baseLabel.getParent()) {
            baseLabel.setParent(baseParent);
        }
        
        updateGraphElement(baseLabel, newLabel);
        updateShapeLayout(baseLabel, newLabel);
        baseLabel.setText(newLabel.getText());
        copyInsets(newLabel.getInsets(), baseLabel.getInsets());
        comparison.getBaseAdapter().generateIDs(baseLabel);
        updatedElements.put(baseLabel, newLabel);
    }

    /**
     * Update the ports of the given node pair. Ports are removed, moved from the new
     * model or updated.
     * 
     * @param baseNode
     *            the node to update to.
     * @param newNode
     *            the node to update from.
     */
    private void handlePorts(final KNode baseNode, final KNode newNode) {
        Set<KPort> oldPorts = null;
        if (baseNode != null) {
            oldPorts = new HashSet<KPort>(baseNode.getPorts());
        }
        for (KPort newPort : Lists.newLinkedList(newNode.getPorts())) {
            KPort basePort = comparison.lookupBasePort(newPort);
            if (basePort == null) {
                basePort = EcoreUtil.copy(newPort);
                updatePort(basePort, newPort);
            } else {
                if (oldPorts != null) {
                    oldPorts.remove(basePort);
                }
                updatePort(basePort, newPort);
            }
        }
        if (baseNode != null) {
            baseNode.getPorts().removeAll(oldPorts);
        }
        
    }

    /**
     * Update the given port pair.
     * 
     * @param basePort
     *            the port to update to.
     * @param newPort
     *            the port to update from.
     */
    private void updatePort(final KPort basePort, final KPort newPort) {
        // Transfer parent.
        KNode baseNode = comparison.lookupBaseNode(newPort.getNode());
        if (baseNode != basePort.getNode()) {
            basePort.setNode(baseNode);
        }
        
        updateGraphElement(basePort, newPort);
        updateShapeLayout(basePort, newPort);
        copyInsets(newPort.getInsets(), basePort.getInsets());
        comparison.getBaseAdapter().generateIDs(basePort);
        handleLabels(basePort, newPort);
        updatedElements.put(basePort, newPort);
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
     * Updates the position of this node in the child list of its parent.
     * 
     * @param baseNode the node to update to.
     * @param newNode the node to update from.
     */
    private void updatePosition(KNode baseNode, KNode newNode) {
        if (baseNode.getParent() != null && newNode.getParent() != null) {
            int newPosition = newNode.getParent().getChildren().indexOf(newNode);
            int oldPosition = baseNode.getParent().getChildren().indexOf(baseNode);
            if (newPosition != oldPosition) {
                if (newPosition >= baseNode.getParent().getChildren().size()) {
                    newPosition = baseNode.getParent().getChildren().size() - 1;
                    System.err.println(this.getClass().getName() + ": " + INVALID_MOVE_MESSAGE);
                }
                baseNode.getParent().getChildren().move(newPosition, oldPosition);
            }
        }
    }

    /**
     * Updates the position of this port in the ports list of its parent node.
     * 
     * @param basePort the port to update to.
     * @param newPort the port to update from.
     */
    private void updatePosition(KPort basePort, KPort newPort) {
        if (basePort.getNode() != null && newPort.getNode() != null) {
            int newPosition = newPort.getNode().getPorts().indexOf(newPort);
            int oldPosition = basePort.getNode().getPorts().indexOf(basePort);
            if (newPosition != oldPosition) {
                if (newPosition >= basePort.getNode().getPorts().size()) {
                    newPosition = basePort.getNode().getPorts().size() - 1;
                    System.err.println(this.getClass().getName() + ": " + INVALID_MOVE_MESSAGE);
                }
                basePort.getNode().getPorts().move(newPosition, oldPosition);
            }
        }
    }

    /**
     * Updates the position of this label in the labels list of its parent node.
     * 
     * @param baseLabel the label to update to.
     * @param newLabel the label to update from.
     */
    private void updatePosition(KLabel baseLabel, KLabel newLabel) {
        if (baseLabel.getParent() != null && newLabel.getParent() != null) {
            int newPosition = newLabel.getParent().getLabels().indexOf(newLabel);
            int oldPosition = baseLabel.getParent().getLabels().indexOf(baseLabel);
            if (newPosition != oldPosition) {
                if (newPosition >= baseLabel.getParent().getLabels().size()) {
                    newPosition = baseLabel.getParent().getLabels().size() - 1;
                    System.err.println(this.getClass().getName() + ": " + INVALID_MOVE_MESSAGE);
                }
                baseLabel.getParent().getLabels().move(newPosition, oldPosition);
            }
        }
    }

    /**
     * Updates the position of this edge in the outgoingEdges list of its source node. Also updates the position in the
     * edges list of source port.
     * 
     * @param baseEdge the edge to update to.
     * @param newEdge the edge to update from.
     */
    private void updatePosition(KEdge baseEdge, KEdge newEdge) {
        if (baseEdge.getSource() != null && newEdge.getSource() != null) {
            int newPosition = newEdge.getSource().getOutgoingEdges().indexOf(newEdge);
            int oldPosition = baseEdge.getSource().getOutgoingEdges().indexOf(baseEdge);
            if (newPosition != oldPosition) {
                if (newPosition >= baseEdge.getSource().getOutgoingEdges().size()) {
                    newPosition = baseEdge.getSource().getOutgoingEdges().size() - 1;
                    System.err.println(this.getClass().getName() + ": " + INVALID_MOVE_MESSAGE);
                }
                baseEdge.getSource().getOutgoingEdges().move(newPosition, oldPosition);
            }
        }
        if (baseEdge.getTarget() != null && newEdge.getTarget() != null) {
            int newPosition = newEdge.getTarget().getIncomingEdges().indexOf(newEdge);
            int oldPosition = baseEdge.getTarget().getIncomingEdges().indexOf(baseEdge);
            if (newPosition != oldPosition) {
                if (newPosition >= baseEdge.getTarget().getIncomingEdges().size()) {
                    newPosition = baseEdge.getTarget().getIncomingEdges().size() - 1;
                    System.err.println(this.getClass().getName() + ": " + INVALID_MOVE_MESSAGE);
                }
                baseEdge.getTarget().getIncomingEdges().move(newPosition, oldPosition);
            }
        }
        if (baseEdge.getSourcePort() != null && newEdge.getSourcePort() != null) {
            int newPosition = newEdge.getSourcePort().getEdges().indexOf(newEdge);
            int oldPosition = baseEdge.getSourcePort().getEdges().indexOf(baseEdge);
            if (newPosition != oldPosition) {
                if (newPosition >= baseEdge.getSourcePort().getEdges().size()) {
                    newPosition = baseEdge.getSourcePort().getEdges().size() - 1;
                    System.err.println(this.getClass().getName() + ": " + INVALID_MOVE_MESSAGE);
                }
                baseEdge.getSourcePort().getEdges().move(newPosition, oldPosition);
            }
        }
        if (baseEdge.getTargetPort() != null && newEdge.getTargetPort() != null) {
            int newPosition = newEdge.getTargetPort().getEdges().indexOf(newEdge);
            int oldPosition = baseEdge.getTargetPort().getEdges().indexOf(baseEdge);
            if (newPosition != oldPosition) {
                if (newPosition >= baseEdge.getTargetPort().getEdges().size()) {
                    newPosition = baseEdge.getTargetPort().getEdges().size() - 1;
                    System.err.println(this.getClass().getName() + ": " + INVALID_MOVE_MESSAGE);
                }
                baseEdge.getTargetPort().getEdges().move(newPosition, oldPosition);
            }
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

}
