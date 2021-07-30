/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
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
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.KEdgeRenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolo.util.PPickPath;

/**
 * The Piccolo2D node for representing a {@link KEdge}.<br>
 * <b>Note:</b> the bounds of KEdgeNodes are updated in the
 * {@link de.cau.cs.kieler.klighd.piccolo.internal.controller.PNodeController PNodeController}
 * implementations in {@link KEdgeRenderingController} that are in charge of updating the attached
 * polyline/spline figure's bend points. Without that update the picking of edges (and may be even
 * the correct drawing) will not work correctly.
 * 
 * @author mri
 * @author chsch
 */
public class KEdgeNode extends KGraphElementNode<KEdge> implements
        IInternalKGraphElementNode.IKLabeledGraphElementNode<KEdge> {

    private static final long serialVersionUID = -1867615197736299487L;

    /** the property name for changes of the edge's bend points. */
    public static final String PROPERTY_BEND_POINTS = "bendPoints";

    /** the property name for changes of the edge's bend points. */
    public static final String PROPERTY_JUNCTION_POINTS = "junctionPoints";

    /** the edge rendering controller deployed to manage the rendering of {@link #edge}. */
    private KEdgeRenderingController renderingController;

    /** the {@link KNodeAbstractNode} this edgeNode is attached to, is just convenience. */
    private KNodeAbstractNode parentNode;

    /** the bend points. */
    private Point2D[] bendPoints = new Point2D[2];

    /** the junction points. */
    private Point2D[] junctionPoints = new Point2D[0];

    /**
     * Constructs a Piccolo2D node for representing a {@link KEdge}.
     * 
     * @param edge
     *            the edge
     */
    public KEdgeNode(final KEdge edge) {
        super(edge);
        setPickable(true);
        setChildrenPickable(true);
        bendPoints[0] = new Point2D.Double();
        bendPoints[1] = new Point2D.Double();
    }

    /**
     * {@inheritDoc}
     */
    public void setRenderingController(final AbstractKGERenderingController<KEdge,
            ? extends IInternalKGraphElementNode<KEdge>> controller) {

        if (controller == null || controller instanceof KEdgeRenderingController) {
            this.renderingController = (KEdgeRenderingController) controller;
        } else {
            final String s = "KLighD: Fault occured while building up a concrete KEdge rendering: "
                    + "KEdgeNodes are supposed to be controlled by KEdgeRenderingControllers rather "
                    + "than " + controller.getClass().getCanonicalName();
            throw new IllegalArgumentException(s);
        }
    }

    /**
     * {@inheritDoc}
     */
    public KEdgeRenderingController getRenderingController() {
        return this.renderingController;
    }

    public void setParentNode(final KNodeAbstractNode parent) {
        this.parentNode = parent;
    }

    /**
     * {@inheritDoc}
     */
    public void addLabel(final KLabelNode label) {
        addChild(label);
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
     * Sets the junction points for the edge.
     * 
     * @param junctionPoints
     *            the bend points
     */
    public void setJunctionPoints(final Point2D[] junctionPoints) {
        // set the new bend points and fire a property change event
        this.junctionPoints = junctionPoints;
        firePropertyChange(-1, PROPERTY_JUNCTION_POINTS, null, junctionPoints);
    }

    /**
     * Returns the junction points for the edge.
     * 
     * @return the junction points
     */
    public Point2D[] getJunctionPoints() {
        return junctionPoints;
    }

    /**
     * Returns the child area that contains this edge.
     * 
     * @return the child area containing this edge or null if the edge is not contained in a child
     *         area
     */
    public KChildAreaNode getParentChildArea() {
        final PNode parent = getParent();
        if (parent != null && parent.getParent() instanceof KChildAreaNode) {
            return (KChildAreaNode) parent.getParent();
        }
        return null;
    }

    /**
     *
     * @return the bounds of the attached {@link KlighdFigureNode}
     */
    public PBounds getPathBoundsReference() {
        for (final Object child : getChildrenReference()) {
            if (child instanceof KlighdFigureNode<?>) {
                return ((PNode) child).getBoundsReference();
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersects(final Rectangle2D localBounds) {
        // By our design there should be only one child
        //  that is an instance of KlighdPath.
        // However, IMO this way is more precise wrt. the general API
        for (final Object child : getChildrenReference()) {
            if (((PNode) child).intersects(localBounds)) {
                return true;
            }
        }
        return false; 
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This specialization has been introduced together with the calls of
     * <code>KEdgeNode.repaint()</code> in {@link KEdgeRenderingController} in order to paint over
     * thin "artifact" lines that may survive due to widened anti-aliased edge drawings.
     */
    @Override
    public void repaintFrom(final PBounds localBounds, final PNode childOrThis) {
        if (childOrThis == this) {
            localBounds.setRect(
                localBounds.x - 1, localBounds.y - 1, localBounds.width + 2, localBounds.height + 2);
        }
        super.repaintFrom(localBounds, childOrThis);
    }

    /**
     * {@inheritDoc}
     * <br>
     * This specialization has been introduced to filter the edges of the currently clipped node,
     * if the clipped ports should be hidden. 
     */
    @Override
    public boolean fullPick(PPickPath pickPath) {
        // check if the parentNode is currently clipped to and if the ports should be hidden
        // on the main camera
        if (parentNode != null && parentNode.isDiagramClipWithPortsHidden()) {
            // the check whether we're on a clip-able diagram (see fullPaint) is skipped here,
            //  as non-clip-able diagrams like the outline view are not expected to support
            //  element picking (selection, highlighting, hover)

            // Check if the edge is connected to the parentNode
            final KNode parentKNode = parentNode.getViewModelElement();
            final KEdge kEdge = getViewModelElement();
            if (kEdge.getSource() == parentKNode || kEdge.getTarget() == parentKNode) {
                // This is a short hierarchy edge connected to the parent node -> skip it
                return false;
            }
        }

        return super.fullPick(pickPath);
    }
    
    /**
     * {@inheritDoc}
     * <br>
     * This specialization has been introduced to filter the edges of the currently clipped node,
     * if the clipped ports should be hidden. 
     */
    @Override
    public void fullPaint(final PPaintContext paintContext) {
        // check if the parentNode is currently clipped to and if the ports should be hidden on the main camera
        if (parentNode != null && parentNode.isDiagramClipWithPortsHidden()
                && paintContext instanceof KlighdPaintContext) {
            final KlighdPaintContext kpc = (KlighdPaintContext) paintContext;

            // via the following check the drawing of cross level edges connecting 'parentNode' and
            //  one of its (nested) children, which are added to the 'parentNode's childArea,
            //  see DiagramControllerHelper.updateEdgeParent(...),
            //  is skipped for the main diagram, but not skipped for e.g. the outline view;
            if (kpc.isMainDiagram()) {
                // Check if the edge is connected to the parentNode
                final KEdge kEdge = getViewModelElement();
                final KNode parentKNode = parentNode.getViewModelElement();
                if (kEdge.getSource() == parentKNode || kEdge.getTarget() == parentKNode) {
                    // This is a short hierarchy edge connected to the parent -> skip it
                    // note: checking whether the edge is actually connected via a port is skipped
                    //  as it seems unnatural to distinct edges wrt. that
                    //  and we don't want to have dangling non-ported edges 
                    return;
                }
            }
        }

        super.fullPaint(paintContext);
    }
}
