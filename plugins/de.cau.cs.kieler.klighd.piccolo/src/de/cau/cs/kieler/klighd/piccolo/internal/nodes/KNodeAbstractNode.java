/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.IInternalKGraphElementNode.IInternalKNodeNode;
import de.cau.cs.kieler.klighd.util.KlighdProperties;

/**
 * An abstract super class of {@link KNodeTopNode} and {@link KNodeNode} contributing common
 * behavior. The main purpose of it is to reduce code clones that exists anyway since the
 * inheritance of {@link edu.umd.cs.piccolo.PLayer PLayer} forbids the inheritance of
 * {@link KGraphElementNode}. This class is intended to be subclassed by {@link KNodeNode} and
 * {@link KNodeTopNode} only and is, thus, configured 'package protected' by intention.
 *
 * @see KGraphElementNode
 *
 * @author chsch
 */
abstract class KNodeAbstractNode extends KlighdDisposingLayer implements IInternalKNodeNode {

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
        this.childArea = new KChildAreaNode(this, edgesFirst);

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
     * {@inheritDoc}
     */
    public abstract KNodeAbstractNode getParentNode();


    private boolean expanded = false;

    /**
     * Returns whether this child area is expanded.
     *
     * @return true if this child area is expanded; false else
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
}