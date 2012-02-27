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

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.IWrapper;
import de.cau.cs.kieler.klighd.piccolo.krendering.controller.RenderingContextData;
import de.cau.cs.kieler.klighd.piccolo.nodes.PChildRepresentedNode;
import edu.umd.cs.piccolo.PNode;

/**
 * The Piccolo node for representing a {@code KEdge}.
 * 
 * @author mri
 */
public class KEdgeNode extends PChildRepresentedNode implements IWrapper<KEdge>,
        ILabeledGraphElement {

    private static final long serialVersionUID = -1867615197736299487L;

    /** the property for the Piccolo representation of an edge. */
    public static final IProperty<KEdgeNode> EDGE_REP = new Property<KEdgeNode>(
            "klighd.piccolo.prepresentation");

    /** the property name for changes of the edge's bend points. */
    public static final String PROPERTY_BEND_POINTS = "bendPoints";

    /** the encapsulated {@code KEdge}. */
    private KEdge edge;

    /** the bend points. */
    private Point2D[] bendPoints = new Point2D[2];

    /**
     * Constructs a Piccolo node for representing a {@code KEdge}.
     * 
     * @param edge
     *            the edge
     */
    public KEdgeNode(final KEdge edge) {
        this.edge = edge;
        setPickable(true);
        RenderingContextData.get(edge).setProperty(EDGE_REP, this);
    }

    /**
     * {@inheritDoc}
     */
    public KEdge getWrapped() {
        return edge;
    }

    /**
     * Sets the bend points for the edge.
     * 
     * @param bendPoints
     *            the bend points
     */
    public void setBendPoints(final Point2D[] bendPoints) {
        // set the new bend points and fire a property change event
        this.bendPoints = bendPoints;
        firePropertyChange(-1, PROPERTY_BEND_POINTS, null, bendPoints);
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
     * Returns the child area that contains this edge.
     * 
     * @return the child area containing this edge or null if the edge is not contained in a child
     *         area
     */
    public KChildAreaNode getChildArea() {
        PNode parent = getParent();
        if (parent instanceof KChildAreaNode) {
            return (KChildAreaNode) parent;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void addLabel(final KLabelNode label) {
        addChild(label);
    }

    // /** the listener on changes to the child area offset. */
    // private PropertyChangeListener updateOffsetListener = new PropertyChangeListener() {
    // public void propertyChange(final PropertyChangeEvent evt) {
    // offset.setLocation(0, 0);
    // PNode currentNode = sourceNode.getParent();
    // while (currentNode != null && currentNode != childAreaNode) {
    // currentNode.localToParent(offset);
    // currentNode = currentNode.getParent();
    // }
    // }
    // };
    //
    // /**
    // * Updates the offset of the source parent to the child area containing the edge.
    // */
    // public void updateOffset() {
    // if (childAreaNode != null && sourceNode.getParent() != childAreaNode) {
    // // remove the update offset listener from all currently observed nodes
    // removeUpdateOffsetListeners();
    //
    // // calculate the offset and register the update offset listener
    // offset.setLocation(0, 0);
    // PNode currentNode = sourceNode.getParent();
    // while (currentNode != null && currentNode != childAreaNode) {
    // currentNode.localToParent(offset);
    // currentNode.addPropertyChangeListener(PNode.PROPERTY_TRANSFORM,
    // updateOffsetListener);
    // observedNodes.add(currentNode);
    // currentNode = currentNode.getParent();
    // }
    // } else {
    // offset.setLocation(0, 0);
    // }
    //
    // // update the bend points for the new offset
    // updateBendPoints();
    // }
    //
    // /**
    // * Updates the edge by determining the currently valid child area to contain it.
    // */
    // public void updateParent() {
    // sourceNode = getRepresentation(edge.getSource());
    // targetNode = getRepresentation(edge.getTarget());
    //
    // // find the parent child area for the edge
    // findParent();
    //
    // // update the offset
    // updateOffset();
    // }
    //
    // /**
    // * Initializes the edge.
    // */
    // public void initialize() {
    // // update the edge parent
    // updateParent();
    //
    // // register an adapter on the edge to stay in sync
    // edge.eAdapters().add(new AdapterImpl() {
    // public void notifyChanged(final Notification notification) {
    // int featureId = notification.getFeatureID(KEdge.class);
    // if (featureId == KGraphPackage.KEDGE__SOURCE
    // || featureId == KGraphPackage.KEDGE__TARGET) {
    // MonitoredOperation.runInUI(new Runnable() {
    // public void run() {
    // updateParent();
    // }
    // }, false);
    // }
    // }
    // });
    // }
    //
    // /**
    // * Removes the update offset listener from all currently observed nodes.
    // */
    // private void removeUpdateOffsetListeners() {
    // PNode observedNode = observedNodes.remove(0);
    // while (observedNode != null) {
    // observedNode.removePropertyChangeListener(PNode.PROPERTY_TRANSFORM,
    // updateOffsetListener);
    // observedNode = observedNodes.remove(0);
    // }
    // }

}
