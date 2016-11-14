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

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.klighd.incremental.diff.Comparison;
import de.cau.cs.kieler.klighd.incremental.util.UIDAdapter;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KNode;

/**
 * @author carsten
 *
 */
public class KGraphMerger {

    private KNode baseModel;
    private Comparison comparison;

    public KGraphMerger(final KNode baseModel, final Comparison comparison) {
        this.baseModel = baseModel;
        this.comparison = comparison;
    }

    public void merge() {
        handleRemovedNodes();
        handleAddedNodes();
    }

    private void handleRemovedNodes() {
        for (KNode node : comparison.getRemovedNodes().values()) {
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
        for (KNode node : comparison.getAddedNodes().values()) {
            addNode(node);
        }
        // Add edges after adding the nodes to be sure that all targets are available.
        for (KNode node : comparison.getAddedNodes().values()) {
            addEdges(node);
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
            KNode nodeCopy = EcoreUtil.copy(node);
            baseParent.getChildren().add(nodeCopy);
        }
    }

    private void addEdges(final KNode node) {
        KNode source = comparison.lookupBaseNode(node);
        LinkedList<KEdge> oldEdges = Lists.newLinkedList(source.getOutgoingEdges());
        for (KEdge edge : oldEdges) {
            edge.setSource(null);
            edge.setTarget(null);
        }
        LinkedList<KEdge> newEdges = Lists.newLinkedList(node.getOutgoingEdges());
        for (KEdge edge : newEdges) {
            edge.setSource(source);
            KNode target = comparison.lookupBaseNode(edge.getTarget());
            edge.setTarget(target);
        }
    }

}
