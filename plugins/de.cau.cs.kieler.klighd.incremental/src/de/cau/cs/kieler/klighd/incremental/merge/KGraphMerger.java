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
import java.util.Set;

import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

import de.cau.cs.kieler.klighd.incremental.diff.KComparison;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphData;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KInsets;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout;

/**
 * Recursively merge two KGraphs.
 * 
 * @author csp
 */
public class KGraphMerger {
    
    //FIXME Remove before flight!
    private boolean debug = false;

    private KComparison comparison;
    private KGraphDataFilter filter;

    /**
     * Create a new merger working with the given comparison.
     * 
     * @param comparison
     *            the comparison to work on.
     * @param filter
     *            the filter defining which KGraphData is merged.
     */
    public KGraphMerger(final KComparison comparison, final KGraphDataFilter filter) {
        this.comparison = comparison;
        this.filter = filter;
    }

    /**
     * Initiate the merge.
     */
    public void merge() {
        handleRemovedNodes();
        handleAddedNodes();
        handleChangedNodes();
    }

    private void handleRemovedNodes() {
        for (KNode node : comparison.getRemovedNodes()) {
            if (debug) {
                System.out.println("removing node " + node);
            }
            removeNode(node);
        }
    }

    private void removeNode(final KNode node) {
        for (KEdge edge : node.getOutgoingEdges()) {
            edge.setTarget(null);
        }
        for (KEdge edge : node.getIncomingEdges()) {
            edge.setSource(null);
        }
        node.getParent().getChildren().remove(node);
    }

    private void handleAddedNodes() {
        for (KNode node : comparison.getAddedNodes()) {
            if (debug) {
                System.out.println("adding node " + node);
            }
            addNode(node);
        }
        // Add edges after adding the nodes to ensure that all targets are available.
        for (KNode node : comparison.getAddedNodes()) {
            if (debug) {
                System.out.println("adding edges for node " + node);
            }
            handleEdges(comparison.lookupBaseNode(node), node);
        }
    }

    private void addNode(final KNode node) {
        if (node.getParent() == null) {
            throw new RuntimeException(
                    "could not add node " + comparison.getNewAdapter().getId(node));
        }
        KNode baseParent = comparison.lookupBaseNode(node.getParent());
        if (baseParent == null) {
            // The new node's parent is missing in the base model as well.
            // Add it and its children (including this node) first.
            addNode(node.getParent());
        } else {
            if (comparison.lookupBaseNode(node) == null) {
                KNode nodeCopy = EcoreUtil.copy(node);
                baseParent.getChildren().add(nodeCopy);
            }
        }
    }

    private void handleChangedNodes() {
        for (ValueDifference<KNode> diff : comparison.getMatchedNodes().values()) {
            if (debug) {
                System.out.println("updating node " + diff.leftValue());
            }
            // TODO Maybe check if update is really necessary
            updateKnode(diff.leftValue(), diff.rightValue());
        }
    }

    private void updateKnode(final KNode baseNode, final KNode newNode) {
        updateGraphElement(baseNode, newNode);
        updateShapeLayout(baseNode, newNode);
        copyInsets(newNode.getInsets(), baseNode.getInsets());
        handleLabels(baseNode, newNode);
        handleEdges(baseNode, newNode);
        // TODO handlePorts
    }

    private void handleEdges(final KNode baseNode, final KNode newNode) {
        Set<KEdge> oldEdges = new HashSet<KEdge>(baseNode.getOutgoingEdges());
        for (KEdge newEdge : Lists.newLinkedList(newNode.getOutgoingEdges())) {
            KEdge baseEdge = comparison.lookupBaseEdge(newEdge);
            if (baseEdge == null) {
                if (debug) {
                    System.out.println("new edge " + newEdge);
                }
                baseEdge = EcoreUtil.copy(newEdge);
                updateEdge(baseEdge, newEdge);
            } else {
                if (debug) {
                    System.out.println("update edge " + baseEdge);
                }
                oldEdges.remove(baseEdge);
                updateEdge(baseEdge, newEdge);
            }
        }
        if (debug) {
            System.out.println("remove edges " + oldEdges.toString());
        }
        baseNode.getOutgoingEdges().removeAll(oldEdges);
    }

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
    }

    private void handleLabels(final KLabeledGraphElement baseElement,
            final KLabeledGraphElement newElement) {

        List<KLabel> baseLabels = baseElement.getLabels();
        Set<KLabel> oldLabels = new HashSet<KLabel>(baseLabels);
        LinkedList<KLabel> newLabels = Lists.newLinkedList();
        for (KLabel newLabel : newElement.getLabels()) {
            KLabel baseLabel = comparison.lookupBaseLabel(newLabel);
            if (baseLabel != null) {
                oldLabels.remove(baseLabel);
                updateLabel(baseLabel, newLabel);
            } else {
                newLabels.add(newLabel);
            }
        }
        baseLabels.removeAll(oldLabels);
        baseLabels.addAll(newLabels);
    }

    private void updateLabel(final KLabel baseLabel, final KLabel newLabel) {
        updateGraphElement(baseLabel, newLabel);
        updateShapeLayout(baseLabel, newLabel);
        baseLabel.setText(newLabel.getText());
        copyInsets(newLabel.getInsets(), baseLabel.getInsets());
    }

    private void updateGraphElement(final KGraphElement baseElement,
            final KGraphElement newElement) {
        List<KGraphData> baseData = baseElement.getData();
        List<KGraphData> newData = newElement.getData();
        baseData.removeIf(filter);
        newData.removeIf(filter.negate());
        baseData.addAll(newData);
        baseElement.copyProperties(newElement);
        EMap<IProperty<?>, Object> baseProperties = baseElement.getProperties();
        LinkedList<IProperty<?>> removedProperties = Lists.newLinkedList(Sets.difference(
                baseProperties.keySet(), newElement.getProperties().keySet()));
        for (IProperty<?> property : removedProperties) {
            baseProperties.removeKey(property);
        }
    }

    private void updateShapeLayout(final KShapeLayout baseElement, final KShapeLayout newElement) {
        baseElement.setPos(newElement.getXpos(), newElement.getYpos());
        baseElement.setSize(newElement.getWidth(), newElement.getHeight());
    }

    private void copyInsets(final KInsets sourceInsets, final KInsets targetInsets) {
        if (targetInsets != null && sourceInsets != null) {
            targetInsets.setLeft(sourceInsets.getLeft());
            targetInsets.setRight(sourceInsets.getRight());
            targetInsets.setTop(sourceInsets.getTop());
            targetInsets.setBottom(sourceInsets.getBottom());
        }
    }

}
