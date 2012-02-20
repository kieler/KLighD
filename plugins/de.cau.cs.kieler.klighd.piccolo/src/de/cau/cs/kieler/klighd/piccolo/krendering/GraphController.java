/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.krendering;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;

import de.cau.cs.kieler.core.kgraph.KNode;
import edu.umd.cs.piccolo.PNode;

/**
 * The class which controls the transformation of a KGraph with attached KRendering data to Piccolo
 * nodes and the synchronization of these Piccolo nodes with the KGraph model.
 * 
 * @author mri
 */
public class GraphController {

    /** the Piccolo node representing the top node in the graph. */
    private KNodeTopNode topNode;
    /** the graph. */
    private KNode graph;

    /**
     * Constructs a graph controller for the given graph. The Piccolo nodes created for the graph
     * will be parented by the specified parent node.
     * 
     * @param graph
     *            the graph
     * @param parent
     *            the parent Piccolo node
     */
    public GraphController(final KNode graph, final PNode parent) {
        this.graph = graph;
        this.topNode = new KNodeTopNode(graph);
    }

    /**
     * Initializes the graph controller.
     */
    public void initialize() {
        // register an adapter on the graph to stay in sync
        registerAdapter();
    }
    
    private void registerAdapter() {
        graph.eAdapters().add(new EContentAdapter() {
            public void notifyChanged(final Notification notification) {
                super.notifyChanged(notification);
            }
        });
    }

}
