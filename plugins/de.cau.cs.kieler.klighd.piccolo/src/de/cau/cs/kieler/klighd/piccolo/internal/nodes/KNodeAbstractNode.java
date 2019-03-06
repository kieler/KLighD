/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.awt.geom.Rectangle2D;

import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.piccolo.IKlighdNode.IKGraphElementNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import de.cau.cs.kieler.klighd.util.KlighdSemanticDiagramData;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * An abstract super class of {@link KNodeTopNode} and {@link KNodeNode} contributing common
 * behavior. The main purpose of it is to reduce code clones that exists anyway since the
 * inheritance of {@link edu.umd.cs.piccolo.PLayer PLayer} forbids the inheritance of
 * {@link KGraphElementNode}. This class is intended to be subclassed by {@link KNodeNode} and
 * {@link KNodeTopNode} only.
 *
 * @see KGraphElementNode
 *
 * @author chsch
 */
public abstract class KNodeAbstractNode extends KlighdDisposingLayer implements
        IKGraphElementNode.IKNodeNode, IInternalKGraphElementNode<KNode> {

    private static final long serialVersionUID = -4486373398530744260L;

    /** the represented {@link KNode}. */
    private KNode node;

    /** the child area for this node. */
    protected final KChildAreaNode childArea; // SUPPRESS CHECKSTYLE Visibility

    /**
     * Constructor.
     *
     * @param node
     *            the represented {@link KNode}
     * @param edgesFirst
     *            determining whether edges are drawn before nodes, i.e. nodes have priority
     *            over edges
     */
    public KNodeAbstractNode(final KNode node, final boolean edgesFirst) {
        this.node = node;
        this.childArea = new KChildAreaNode(this,
                edgesFirst || node.getProperty(KlighdProperties.EDGES_FIRST).booleanValue());

        this.setPickable(true);
    }

    /**
     * {@inheritDoc}
     */
    public KNode getViewModelElement() {
        return node;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSelectable() {
        return KlighdProperties.isSelectable(getViewModelElement());
    }

    /**
     * {@inheritDoc}
     */
    public KChildAreaNode getChildAreaNode() {
        return childArea;
    }

    /**
     * Convenience setter for adding a representation of an edge.
     */
    public void addEdge(KEdgeNode edgeRep) {
        childArea.addEdge(edgeRep);
        edgeRep.setParentNode(this);
    }

    /**
     * Method indicates whether this {@link KNodeAbstractNode} has been drawn once; if so the
     * attached {@link KChildAreaNode} must perform its {@link #validateFullBounds() bounds
     * validation} and {@link #validateFullPaint() paint validation} properly; otherwise the child
     * area may skip those validations and need not visit all its (indirectly) contained nodeNodes
     * and edgeNodes for reducing the initial diagram setup load.
     *
     * @return <code>true</code> if <code>this</code> {@link KNodeAbstractNode} represents the
     *         current diagram clip or has already been drawn at least once or is about to be drawn,
     *         <code>false</code> otherwise
     */
    protected boolean isBoundsValidationRequired() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public abstract KNodeAbstractNode getParentKNodeNode();

    /**
     * Returns the bounds of this node's exportable area that are required to fully export the
     * (visible) part(s) of this node in case it is completely shown as well as in case the diagram
     * is clipped to this node. In the latter case the node's figures are skipped, only the child
     * area, non-hidden ports, and non-hidden labels drawn.
     *
     * @return the adjusted bounds
     */
    public abstract Rectangle2D getExportedBounds();

    private boolean expanded = false;

    /**
     * {@inheritDoc}
     */
    public boolean isExpanded() {
        return expanded;
    }

    /**
     * Sets whether this child area is expanded.
     *
     * @param expanded
     *            true if this child area is expanded; false else
     */
    public void setExpanded(final boolean expanded) {
        if (this.expanded != expanded) {
            this.expanded = expanded;

            getChildAreaNode().setVisible(expanded);

            firePropertyChange(-1, IKNodeNode.PROPERTY_EXPANSION, !expanded, expanded);
        }
    }

    /**
     * Touches the expansion state, i.e. fires the listeners on {@link #PROPERTY_EXPANSION} without
     * changing the value. This is required in combination with EMF Compare in case parts of the
     * model have been moved (and EMF Compare noticed that as such). In this case the
     * RenderingContextData are preserved but the diagram needs to be updated accordingly.<br>
     * <br>
     * Unfortunately old and new value must be different, so I set the old value to the inverse.
     */
    public void touchExpanded() {
        firePropertyChange(-1, IKNodeNode.PROPERTY_EXPANSION, !this.expanded, this.expanded);
    }

    /**
     * Toggles the expansion state of <code>this</code> {@link KChildAreaNode}.
     */
    public void toggleExpansion() {
        setExpanded(!this.expanded);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setScale(final double scale) {
        final double curScale = getScale();

        if (scale == curScale) {
            return;
        } else if (scale == 0) {
            throw new RuntimeException("Can't set scale to 0");
        }
        scale(scale / curScale);
    }

    /**
     * Returns whether the diagram is clipped to the represented {@link KNode} and its ports are hidden.
     * 
     * @return <code>true</code> if the diagram is clipped to the represented {@link KNode} and its
     *         ports are hidden, <code>false</code> otherwise.
     */
    public abstract boolean isDiagramClipWithPortsHidden();

    /**
     * Configures the invisibility of the ports on the main diagram in case it's clipped to this
     * node.
     * 
     * @param portsHidden
     *            if <code>true</code> the ports will be hidden, and displayed otherwise
     * @param force
     *            if <code>true</code> the value of <code>portsHidden</code> will be applied under
     *            all circumstances; otherwise if <code>force == false</code> and <code>this</code>
     *            node is the current clip node the existing configuration is kept and
     *            <code>portsHidden</code> is ignored; otherwise (if <code>force == false</code> and
     *            <code>this</code> node is <em>not</em> the current clip) and a specific definition
     *            is set to the represented {@link KNode} that definition will superimpose the value
     *            of <code>portsHidden</code>; otherwise the value of <code>portsHidden</code> is
     *            applied.
     */
    public void setPortsHiddenWhenClipped(boolean portsHidden, boolean force) {}

    /**
     * Returns whether the diagram is clipped to the represented {@link KNode} and its labels are
     * hidden.
     * 
     * @return <code>true</code> if the diagram is clipped to the represented {@link KNode} and its
     *         labels are hidden, <code>false</code> otherwise.
     */
    public abstract boolean isDiagramClipWithLabelsHidden();

    /**
     * Configures the invisibility of the labels on the main diagram in case it's clipped to this
     * node.
     * 
     * @param labelsHidden
     *            if <code>true</code> the labels will be hidden, and displayed otherwise
     * @param force
     *            if <code>true</code> the value of <code>labelsHidden</code> will be applied under
     *            all circumstances; otherwise if <code>force == false</code> and <code>this</code>
     *            node is the current clip node the existing configuration is kept and
     *            <code>labelsHidden</code> is ignored; otherwise (if <code>force == false</code>
     *            and <code>this</code> node is <em>not</em> the current clip) and a specific
     *            definition is set to the represented {@link KNode} that definition will
     *            superimpose the value of <code>labelsHidden</code>; otherwise the value of
     *            <code>labelsHidden</code> is applied.
     */
    public void setLabelsHiddenWhenClipped(boolean labelsHidden, boolean force) {}

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        final KlighdPaintContext kpc = (KlighdPaintContext) paintContext;

        if (kpc.isAddSemanticData()) {
            final KlighdSemanticDiagramData sd =
                    getViewModelElement().getProperty(KlighdProperties.SEMANTIC_DATA);
            kpc.getKlighdGraphics().startGroup(sd);
        }

        // since the super implementation doesn't contribute any functionality
        //  the call 'super.paint(paintContext)' is omitted here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintAfterChildren(final PPaintContext paintContext) {
        // since the super implementation doesn't contribute any functionality
        //  the call 'super.paintAfterChildren(paintContext)' is omitted here

        final KlighdPaintContext kpc = (KlighdPaintContext) paintContext;

        if (kpc.isAddSemanticData()) {
            kpc.getKlighdGraphics().endGroup();
        }
    }
}