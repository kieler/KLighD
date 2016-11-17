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

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.klighd.kgraph.KNode;

/**
 * @author carsten
 *
 */
public final class UIDAdapters {

    private static Map<KNode, WeakReference<UIDAdapter>> adapters =
            new WeakHashMap<KNode, WeakReference<UIDAdapter>>();
//            Maps.newHashMap();

    private UIDAdapters() {

    }

    public static UIDAdapter retrieveAdapter(final KNode node) {
        WeakReference<UIDAdapter> candidate = adapters.get(node);
        // If the map yields a value for node, the node's adapter can't be garbage collected,
        // thus it's sufficient to test for candidate != null
        // (and omit the test candidate.get() != null).
        if (candidate != null) {
            if (node.eAdapters().contains(candidate.get())) {
                return candidate.get();
            } else {
                adapters.remove(node);
            }
        }
        UIDAdapter newAdapter = new UIDAdapter();
        node.eAdapters().add(newAdapter);
        adapters.put(node, new WeakReference<UIDAdapter>(newAdapter));
        return newAdapter;
    }

    public static void removeAdapter(final KNode node) {
        WeakReference<UIDAdapter> adapter = adapters.get(node);
        // If the map yields a value for node, the node's adapter can't be garbage collected,
        // thus it's sufficient to test for adapter != null
        // (and omit the test adapter.get() != null).
        if (adapter != null) {
            node.eAdapters().remove(adapter.get());
        }
        adapters.remove(node);
    }

}
