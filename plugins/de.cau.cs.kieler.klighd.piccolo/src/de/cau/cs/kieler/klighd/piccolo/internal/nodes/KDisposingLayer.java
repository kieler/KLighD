/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import edu.umd.cs.piccolo.PLayer;

/**
 * A sightly extended {@link PLayer} that listens to {@link NodeDisposeListener#DISPOSE} notifications
 * and forwards them to its contained elements.
 * 
 * @author chsch
 */
public class KDisposingLayer extends PLayer {

    private static final long serialVersionUID = 4423173127127342353L;

    /**
     * Constructor. 
     */
    public KDisposingLayer() {
        this.setPickable(false);
        this.setChildrenPickable(true);
        this.addPropertyChangeListener(NodeDisposeListener.DISPOSE, new NodeDisposeListener(this));
    }


    /**
     * An abstract super class of {@link KNodeTopNode} and {@link KNodeNode} contributing common
     * behavior. The main purpose of it is to reduce code clones that are here anyway since the
     * inheritance of {@link PLayer} forbids the inheritance of {@link KlighdNode.KlighdGraphNode}.
     * 
     * @see KlighdNode.KlighdGraphNode
     */
    public abstract static class KNodeRepresentingLayer extends KDisposingLayer implements INode {

        private static final long serialVersionUID = -4486373398530744260L;

        /** the represented {@link KNode}. */
        private KNode node;

        /**
         * Constructor.
         * 
         * @param node
         *            the node
         */
        public KNodeRepresentingLayer(final KNode node) {
            this.node = node;
            this.setPickable(true);
        }

        /**
         * {@inheritDoc}
         */
        public KNode getGraphElement() {
            return node;
        }

        /**
         * {@inheritDoc}
         */
        public boolean isSelectable() {
            return KlighdProperties.isSelectable(getGraphElement());
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
}
