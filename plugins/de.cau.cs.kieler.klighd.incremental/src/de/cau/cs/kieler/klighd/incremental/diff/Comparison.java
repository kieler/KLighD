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
import de.cau.cs.kieler.klighd.kgraph.KNode;

/**
 * @author carsten
 *
 */
public class Comparison {

    private UIDAdapter baseAdapter;
    private UIDAdapter newAdapter;
    private MapDifference<String, KNode> difference;

    public Comparison(UIDAdapter baseAdapter, UIDAdapter newAdapter) {
        this.baseAdapter = baseAdapter;
        this.newAdapter = newAdapter;
        difference = Maps.difference(baseAdapter.getMap(), newAdapter.getMap());
    }
    
    public UIDAdapter getBaseAdapter() {
        return baseAdapter;
    }

    public UIDAdapter getNewAdapter() {
        return newAdapter;
    }

    public KNode lookupBaseNode(final KNode newNode) {
        return baseAdapter.getNode(newAdapter.getId(newNode));
    }
    
    public Map<String, KNode> getAddedNodes() {
        return difference.entriesOnlyOnRight();
    }
    
    public Map<String, KNode> getRemovedNodes() {
        return difference.entriesOnlyOnLeft();
    }
    
    public Map<String, ValueDifference<KNode>> getChangedNodes() {
        return difference.entriesDiffering();
    }
    
    public Map<String, KNode> getUnchangedNodes() {
        return difference.entriesInCommon();
    }

}
