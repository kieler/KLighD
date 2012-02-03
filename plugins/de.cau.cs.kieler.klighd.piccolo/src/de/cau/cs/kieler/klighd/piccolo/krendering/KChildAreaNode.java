/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
/**
 * 
 */
package de.cau.cs.kieler.klighd.piccolo.krendering;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.klighd.piccolo.nodes.PZIndexNode;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolo.util.PPickPath;

/**
 * A Piccolo node for the {@code KChildArea}.
 * 
 * @author mri
 */
public class KChildAreaNode extends PZIndexNode {

    private static final long serialVersionUID = -403773990520864787L;

    /** the number of z-layers (nodes and edges). */
    private static final int Z_LAYERS = 2;
    /** the z-index for the node layer. */
    private static final int NODE_LAYER = 0;
    /** the z-index for the edge layer. */
    private static final int EDGE_LAYER = 1;

    /** the node containing this child area. */
    private INode containingNode;

    /** whether to clip nodes and edges. */
    private boolean clip = true;

    /**
     * Constructs a child area for a given node.
     * 
     * @param containingNode
     *            the node containing this child area
     */
    public KChildAreaNode(final INode containingNode) {
        super(Z_LAYERS);
        this.containingNode = containingNode;
        setPickable(false);
    }

    /**
     * Sets whether to clip nodes and edges at the child areas boundaries.
     * 
     * @param clip
     *            true if nodes and edges should be clipped; false else
     */
    public void setClip(final boolean clip) {
        this.clip = clip;
    }

    /**
     * Populates the child area with contents from the given node.
     * 
     * @param node
     *            the node
     */
    public void populate(final KNode node) {
        // create the nodes
        for (KNode child : node.getChildren()) {
            addNode(child);
        }

        // create the edges
        for (KNode source : node.getChildren()) {
            for (KEdge edge : source.getOutgoingEdges()) {
                // create the Piccolo node for the edge
                KEdgeNode edgeNode = new KEdgeNode(edge);
                addChild(edgeNode, EDGE_LAYER);
                edgeNode.updateLayout();
                edgeNode.createRendering();
            }
        }

        // add an adapter on the node's children
        node.eAdapters().add(new AdapterImpl() {
            public void notifyChanged(final Notification notification) {
                if (notification.getFeatureID(KNode.class) == KGraphPackage.KNODE__CHILDREN) {
                    switch (notification.getEventType()) {
                    case Notification.ADD: {
                        final KNode addedNode = (KNode) notification.getNewValue();
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                addNode(addedNode);
                            }
                        }, false);
                        break;
                    }
                    case Notification.ADD_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KNode> addedNodes = (List<KNode>) notification.getNewValue();
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                for (KNode addedNode : addedNodes) {
                                    addNode(addedNode);
                                }
                            }
                        }, false);
                        break;
                    }
                    case Notification.REMOVE: {
                        final KNode removedNode = (KNode) notification.getOldValue();
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                removeNode(removedNode);
                            }
                        }, false);
                        break;
                    }
                    case Notification.REMOVE_MANY: {
                        @SuppressWarnings("unchecked")
                        final List<KNode> removedNodes = (List<KNode>) notification.getOldValue();
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                for (KNode removedNode : removedNodes) {
                                    removeNode(removedNode);
                                }
                            }
                        }, false);
                        break;
                    }
                    }
                }
            }
        });
    }

    /**
     * Adds the representation for the given node to this child area.
     * 
     * @param node
     *            the node
     */
    private void addNode(final KNode node) {
        RenderingContextData data = RenderingContextData.get(node);
        INode nodeRep = data.getProperty(INode.PREPRESENTATION);

        KNodeNode nodeNode;
        if (nodeRep instanceof KNodeTopNode) {
            // if the node is the current top-node something went wrong
            throw new RuntimeException("The top-node can never be made a child node");
        } else {
            nodeNode = (KNodeNode) nodeRep;
        }

        // if there is no Piccolo representation for the node create it
        if (nodeNode == null) {
            nodeNode = new KNodeNode(node, containingNode);
            nodeNode.updateLayout();
        }

        // add the node
        addChild((PNode) nodeNode, NODE_LAYER);
        nodeNode.updateRendering();
        // TODO remove auto expand when other means are available
        nodeNode.expand();
    }

    /**
     * Removes the representation for the given node from this child area.
     * 
     * @param node
     *            the node
     */
    private void removeNode(final KNode node) {
        RenderingContextData data = RenderingContextData.get(node);
        INode nodeRep = data.getProperty(INode.PREPRESENTATION);

        KNodeNode nodeNode;
        if (nodeRep instanceof KNodeTopNode) {
            // if the node is the current top-node something went wrong
            throw new RuntimeException("The top-node can never be removed from a parent node");
        } else {
            nodeNode = (KNodeNode) nodeRep;
        }

        // remove the node representation from the containing child area
        nodeNode.removeFromParent();
    }

    /**
     * Adds an edge to this child area.
     * 
     * @param edge
     *            the edge
     */
    public void addEdge(final KEdgeNode edge) {
        addChild(edge, EDGE_LAYER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        if (clip) {
            paintContext.pushClip(getBoundsReference());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintAfterChildren(final PPaintContext paintContext) {
        if (clip) {
            paintContext.popClip(getBoundsReference());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean fullPick(final PPickPath pickPath) {
        if (clip) {
            // never pick the child area and only pick children in the clipped area
            if (getVisible() && getChildrenPickable() && intersects(pickPath.getPickBounds())) {
                pickPath.pushNode(this);
                pickPath.pushTransform(getTransformReference(false));

                int count = getChildrenCount();
                for (int i = count - 1; i >= 0; i--) {
                    PNode child = getChild(i);
                    if (child.fullPick(pickPath)) {
                        return true;
                    }
                }

                pickPath.popTransform(getTransformReference(false));
                pickPath.popNode(this);
            }
        } else {
            return super.fullPick(pickPath);
        }
        return false;
    }

}
