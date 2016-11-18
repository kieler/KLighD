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
package de.cau.cs.kieler.klighd.incremental.diff;

import java.util.Map;

import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.klighd.incremental.util.UIDAdapter;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KNode;

/**
 * Recursively compares two {@link KNodes} via their corresponding {@link UIDAdapter}s.
 * 
 * @author csp
 */
public class KComparison {

    private UIDAdapter baseAdapter;
    private UIDAdapter newAdapter;
    private MapDifference<String, KNode> nodeDifference;

    /**
     * Create new comparison.
     * 
     * @param baseAdapter
     *            the adapter corresponding to the base node.
     * @param newAdapter
     *            the adapter corresponding to the new node.
     */
    public KComparison(final UIDAdapter baseAdapter, final UIDAdapter newAdapter) {
        this.baseAdapter = baseAdapter;
        this.newAdapter = newAdapter;
        nodeDifference = Maps.difference(baseAdapter.getNodeMap(), newAdapter.getNodeMap());
    }

    /**
     * Return the base adapter.
     * 
     * @return the base adapter.
     */
    public UIDAdapter getBaseAdapter() {
        return baseAdapter;
    }

    /**
     * Return the new adapter.
     * 
     * @return the new adapter.
     */
    public UIDAdapter getNewAdapter() {
        return newAdapter;
    }

    /**
     * Get the node from the base model with the same id as the given node.
     * 
     * @param newNode
     *            the node to get the corresponding base node to.
     * @return the base node, or {@code null} if none found.
     */
    public KNode lookupBaseNode(final KNode newNode) {
        return baseAdapter.getNode(newAdapter.getId(newNode));
    }

    /**
     * Get the edge from the base model with the same id as the given edge.
     * 
     * @param newEdge
     *            the edge to get the corresponding base edge to.
     * @return the base edge, or {@code null} if none found.
     */
    public KEdge lookupBaseEdge(final KEdge newEdge) {
        return baseAdapter.getEdge(newAdapter.getId(newEdge));
    }

    /**
     * Get the label from the base model with the same id as the given label.
     * 
     * @param newLabel
     *            the label to get the corresponding base label to.
     * @return the base label, or {@code null} if none found.
     */
    public KLabel lookupBaseLabel(final KLabel newLabel) {
        return baseAdapter.getLabel(newAdapter.getId(newLabel));
    }

    /**
     * Get newly added nodes.
     * 
     * @return a map from ids to newly added nodes.
     */
    public Map<String, KNode> getAddedNodes() {
        return nodeDifference.entriesOnlyOnRight();
    }

    /**
     * Get removed nodes.
     * 
     * @return a map from ids to removed nodes.
     */
    public Map<String, KNode> getRemovedNodes() {
        return nodeDifference.entriesOnlyOnLeft();
    }

    /**
     * Get matched nodes, that are present in both models.
     * 
     * @return a map from ids to pairs of nodes.
     */
    public Map<String, ValueDifference<KNode>> getMatchedNodes() {
        return nodeDifference.entriesDiffering();
    }

}
