/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2021 by
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
package de.cau.cs.kieler.klighd.piccolo.test;

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPort;
import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil;

/**
 * 
 * Some utility methods for creating or loading test models.
 * 
 * @author ckru
 * 
 */
public class KlighdTestUtil {
    /**
     * Generate a kgraph that has every feature to test against.
     * 
     * @param root
     *            root node of the graph to fill.
     * @return first child of testgraph
     */
    public static KNode makeTestGraph(final KNode root) {
        KNode next = KlighdTestUtil.addchild(root);
        KlighdTestUtil.addport(next);
        KlighdTestUtil.addport(next);
        KlighdTestUtil.addport(next);
        KNode a = KlighdTestUtil.addchild(next);
        KNode b = KlighdTestUtil.addchild(next);
        KlighdTestUtil.connectNodes(a, b, null, null);
        return next;
    }
    
    /**
     * Adds a child KNode to the given node.
     * 
     * @param node
     *            the node to add a child to
     * @return the new child node
     */
    public static KNode addchild(final KNode node) {
        KNode child = KGraphUtil.createInitializedNode();
        KLabel l = KGraphUtil.createInitializedLabel(child);
        l.setText(node.toString() + "child" + node.getChildren().size());
        // child.getLabels().add(l);
        node.getChildren().add(child);
        return child;
    }
    
    /**
     * Connect two KNode with an edge. Optionally connect them via ports.
     * 
     * @param source
     *            source node
     * @param target
     *            target node
     * @param sourcePort
     *            sourceport, may be null
     * @param targetPort
     *            targetport, may be null
     * @return the edge that connects the given nodes
     */
    public static KEdge connectNodes(final KNode source, final KNode target, final KPort sourcePort,
            final KPort targetPort) {
        KEdge e = KGraphUtil.createInitializedEdge();
        e.setSource(source);
        e.setTarget(target);
        e.setSourcePort(sourcePort);
        e.setTargetPort(targetPort);
        KLabel l = KGraphUtil.createInitializedLabel(e);
        l.setText(source.toString() + " to " + target.toString());
        return e;
    }
    
    /**
     * Add a port to the given KNode.
     * 
     * @param node
     *            the KNode to add a port to.
     * @return the added port
     */
    public static KPort addport(final KNode node) {
        KPort p = KGraphUtil.createInitializedPort();
        KLabel l = KGraphUtil.createInitializedLabel(p);
        l.setText("port" + node.getPorts().size());
        node.getPorts().add(p);
        return p;
    }
    
}
