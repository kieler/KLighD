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
package de.cau.cs.kieler.klighd.piccolo.krendering;

import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.core.util.IWrapper;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.klighd.piccolo.nodes.PChildRepresentedNode;
import edu.umd.cs.piccolo.PNode;

/**
 * The Piccolo node for representing a {@code KEdge}.
 * 
 * @author mri
 */
public class KEdgeNode extends PChildRepresentedNode implements IWrapper<KEdge> {

    private static final long serialVersionUID = -1867615197736299487L;

    /** the property for the Piccolo representation. */
    public static final IProperty<KEdgeNode> PREPRESENTATION = new Property<KEdgeNode>(
            "klighd.piccolo.prepresentation");

    /** the property name for changes of the edge's bend points. */
    public static final String PROPERTY_BEND_POINTS = "bendPoints";

    /** the property for remembering load delayed target edges. */
    private static final IProperty<List<KEdge>> DELAYED_TARGET_EDGES = new Property<List<KEdge>>(
            "klighd.piccolo.delayedTargetEges");

    /** the encapsulated {@code KEdge}. */
    private KEdge edge;
    /** the edge layout associated with this node. */
    private KEdgeLayout edgeLayout = null;

    /** the bend points. */
    private Point2D[] bendPoints = new Point2D[2];

    /** the rendering controller. */
    private RenderingController renderingController = null;

    /** the current parent child area. */
    private KChildAreaNode childAreaNode = null;
    /** the source node. */
    private KNodeNode sourceNode = null;
    /** the target node. */
    private KNodeNode targetNode = null;

    /** the current child area offset. */
    private Point2D.Float offset = new Point2D.Float(0, 0);

    /** the nodes currently observed by the update offset listener. */
    private List<PNode> observedNodes = Lists.newLinkedList();

    /**
     * Constructs a Piccolo node for representing a {@code KEdge}.
     * 
     * @param edge
     *            the edge
     */
    public KEdgeNode(final KEdge edge) {
        this.edge = edge;
        setPickable(true);
        RenderingContextData.get(edge).setProperty(PREPRESENTATION, this);
    }

    /**
     * {@inheritDoc}
     */
    public KEdge getWrapped() {
        return edge;
    }

    /**
     * Returns the bend points for the edge.
     * 
     * @return the bend points
     */
    public Point2D[] getBendPoints() {
        return bendPoints;
    }

    /**
     * Updates the rendering.
     */
    public void updateRendering() {
        if (renderingController == null) {
            renderingController = new RenderingController(this);
            renderingController.initialize();
        } else {
            renderingController.updateRendering();
        }
    }

    /**
     * Updates the layout.
     */
    public void updateLayout() {
        // try to get the edge layout
        if (edgeLayout == null) {
            edgeLayout = edge.getData(KEdgeLayout.class);

            // register adapter on the edge layout to stay in sync
            edgeLayout.eAdapters().add(new EContentAdapter() {
                public void notifyChanged(final Notification notification) {
                    super.notifyChanged(notification);
                    Object notifier = notification.getNotifier();
                    int featureId = notification.getFeatureID(KEdgeLayout.class);
                    if ((notifier instanceof KEdgeLayout
                            && (featureId == KLayoutDataPackage.KEDGE_LAYOUT__BEND_POINTS
                            || featureId == KLayoutDataPackage.KEDGE_LAYOUT__SOURCE_POINT
                            || featureId == KLayoutDataPackage.KEDGE_LAYOUT__TARGET_POINT))
                            || notifier instanceof KPoint) {
                        MonitoredOperation.runInUI(new Runnable() {
                            public void run() {
                                updateBendPoints();
                            }
                        }, false);
                    }
                }
            });
        }

        // apply the layout
        if (edgeLayout != null) {
            updateBendPoints();
        }
    }

    /** the listener on changes to the child area offset. */
    private PropertyChangeListener updateOffsetListener = new PropertyChangeListener() {
        public void propertyChange(final PropertyChangeEvent evt) {
            offset.setLocation(0, 0);
            PNode currentNode = sourceNode.getParent();
            while (currentNode != null && currentNode != childAreaNode) {
                currentNode.localToParent(offset);
                currentNode = currentNode.getParent();
            }
        }
    };

    /**
     * Updates the offset of the source parent to the child area containing the edge.
     */
    public void updateOffset() {
        if (childAreaNode != null && sourceNode.getParent() != childAreaNode) {
            // remove the update offset listener from all currently observed nodes
            removeUpdateOffsetListeners();

            // calculate the offset and register the update offset listener
            offset.setLocation(0, 0);
            PNode currentNode = sourceNode.getParent();
            while (currentNode != null && currentNode != childAreaNode) {
                currentNode.localToParent(offset);
                currentNode.addPropertyChangeListener(PNode.PROPERTY_TRANSFORM,
                        updateOffsetListener);
                observedNodes.add(currentNode);
                currentNode = currentNode.getParent();
            }
        } else {
            offset.setLocation(0, 0);
        }

        // update the bend points for the new offset
        updateBendPoints();
    }

    /**
     * Updates the edge by determining the currently valid child area to contain it.
     */
    public void updateParent() {
        sourceNode = getRepresentation(edge.getSource());
        targetNode = getRepresentation(edge.getTarget());

        // find the parent child area for the edge
        findParent();

        // update the offset
        updateOffset();
    }

    /**
     * Initializes the edge.
     */
    public void initialize() {
        // update the edge parent
        updateParent();

        // register an adapter on the edge to stay in sync
        edge.eAdapters().add(new AdapterImpl() {
            public void notifyChanged(final Notification notification) {
                int featureId = notification.getFeatureID(KEdge.class);
                if (featureId == KGraphPackage.KEDGE__SOURCE
                        || featureId == KGraphPackage.KEDGE__TARGET) {
                    MonitoredOperation.runInUI(new Runnable() {
                        public void run() {
                            updateParent();
                        }
                    }, false);
                }
            }
        });
    }

    /**
     * Removes the edge from its current parent child area and adds it to the currently valid child
     * area if possible.
     */
    private void findParent() {
        // remove from the current parent
        removeFromParent();
        childAreaNode = null;

        // if the edge misses a source or target node keep it floating
        if (sourceNode != null && targetNode != null) {
            KNode commonParent =
                    findLowestCommonAncestor(sourceNode.getWrapped(), targetNode.getWrapped());
            INode commonParentNode =
                    RenderingContextData.get(commonParent).getProperty(INode.PREPRESENTATION);
            if (commonParentNode != null) {
                childAreaNode = commonParentNode.getChildArea();
                childAreaNode.addEdgeNode(this);
            }
        }
    }

    /**
     * Updates the bend points of this edge to the ones specified in the associated
     * {@code KEdgeLayout}.
     */
    private void updateBendPoints() {
        if (edgeLayout != null) {
            // build the bend point array
            Point2D[] points = new Point2D[edgeLayout.getBendPoints().size() + 2];
            int i = 0;
            points[i++] =
                    new Point2D.Float(offset.x + edgeLayout.getSourcePoint().getX(), offset.y
                            + edgeLayout.getSourcePoint().getY());
            for (KPoint bend : edgeLayout.getBendPoints()) {
                points[i++] = new Point2D.Float(offset.x + bend.getX(), offset.y + bend.getY());
            }
            points[i] =
                    new Point2D.Float(offset.x + edgeLayout.getTargetPoint().getX(), offset.y
                            + edgeLayout.getTargetPoint().getY());

            // set the new bend points
            setBendPoints(points);
        }
    }

    /**
     * Sets the bend points and fires a property change event.
     * 
     * @param points
     *            the points
     */
    private void setBendPoints(final Point2D[] points) {
        bendPoints = points;
        firePropertyChange(-1, PROPERTY_BEND_POINTS, null, bendPoints);
    }

    /**
     * Returns the Piccolo representation of the given node.
     * 
     * @param node
     *            the node
     * @return the Piccolo representation or null if there is no representation for the node
     */
    private KNodeNode getRepresentation(final KNode node) {
        if (node != null) {
            INode nodeRep = RenderingContextData.get(node).getProperty(INode.PREPRESENTATION);
            if (nodeRep instanceof KNodeNode) {
                return (KNodeNode) nodeRep;
            }
        }
        return null;
    }

    /**
     * Returns the lowest common ancestor to both given nodes.
     * 
     * @param initialNode1
     *            the first node
     * @param initialNode2
     *            the second node
     * @return the lowest common ancestor
     */
    private KNode findLowestCommonAncestor(final KNode initialNode1, final KNode initialNode2) {
        KNode node1 = initialNode1.getParent();
        while (node1 != null) {
            KNode node2 = initialNode2.getParent();
            while (node2 != null) {
                if (node1 == node2) {
                    // common ancestor found
                    return node1;
                }
                node2 = node2.getParent();
            }
            node1 = node1.getParent();
        }

        // no common ancestor
        return null;
    }

    /**
     * Removes the update offset listener from all currently observed nodes.
     */
    private void removeUpdateOffsetListeners() {
        PNode observedNode = observedNodes.remove(0);
        while (observedNode != null) {
            observedNode.removePropertyChangeListener(PNode.PROPERTY_TRANSFORM,
                    updateOffsetListener);
            observedNode = observedNodes.remove(0);
        }
    }

}
