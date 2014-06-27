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
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.awt.geom.Point2D;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.KEdgeRenderingController;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.klighd.util.KlighdSemanticDiagramData;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * The Piccolo2D node for representing a {@link KEdge}.
 * 
 * @author mri
 * @author chsch
 */
public class KEdgeNode extends PChildRepresentedNode implements ILabeledGraphElement<KEdge> {

    private static final long serialVersionUID = -1867615197736299487L;

    /** the property name for changes of the edge's bend points. */
    public static final String PROPERTY_BEND_POINTS = "bendPoints";

    /** the property name for changes of the edge's bend points. */
    public static final String PROPERTY_JUNCTION_POINTS = "junctionPoints";

    /** the represented {@link KEdge}. */
    private transient KEdge edge;

    /** the edge rendering controller deployed to manage the rendering of {@link #edge}. */
    private KEdgeRenderingController renderingController;

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
        this.edge = edge;
        setPickable(true);
        setChildrenPickable(true);
        bendPoints[0] = new Point2D.Double();
        bendPoints[1] = new Point2D.Double();
    }

    /**
     * {@inheritDoc}
     */
    public KEdge getGraphElement() {
        return edge;
    }

    /**
     * {@inheritDoc}
     */
    public void setRenderingController(
            final AbstractKGERenderingController<KEdge, ? extends IGraphElement<KEdge>> controller) {
        if (controller == null || controller instanceof KEdgeRenderingController) {
            this.renderingController = (KEdgeRenderingController) controller;
        } else {
            String s = "KLighD: Fault occured while building up a concrete KEdge rendering: KEdgeNodes"
                    + " are supposed to be controlled by KEdgeRenderingControllers rather than "
                    + controller.getClass().getCanonicalName();
            throw new IllegalArgumentException(s);
        }
    }

    /**
     * {@inheritDoc}
     */
    public KEdgeRenderingController getRenderingController() {
        return this.renderingController;
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
        PNode parent = getParent();
        if (parent != null && parent.getParent() instanceof KChildAreaNode) {
            return (KChildAreaNode) parent.getParent();
        }
        return null;
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
     */
    protected void paint(final PPaintContext paintContext) {
        KlighdSWTGraphics g2 = (KlighdSWTGraphics) paintContext.getGraphics();
        KlighdSemanticDiagramData sd =
                getGraphElement().getData(KLayoutData.class).getProperty(
                        KlighdProperties.SEMANTIC_DATA);
        g2.startGroup(sd);
        super.paint(paintContext);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintAfterChildren(final PPaintContext paintContext) {
        super.paintAfterChildren(paintContext);
        KlighdSWTGraphics g2 = (KlighdSWTGraphics) paintContext.getGraphics();
        g2.endGroup();
    }
}
