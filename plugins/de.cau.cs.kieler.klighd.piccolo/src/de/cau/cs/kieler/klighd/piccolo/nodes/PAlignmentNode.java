/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
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
package de.cau.cs.kieler.klighd.piccolo.nodes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A Piccolo node that can align its children along the horizontal and vertical axes using
 * translation.
 * 
 * @author mri
 */
public class PAlignmentNode extends PEmptyNode implements PropertyChangeListener {

    private static final long serialVersionUID = -2514462331029707306L;

    /** the alignment data for the aligned children. */
    private Map<PNode, AlignmentData> alignmentData = new HashMap<PNode, AlignmentData>();

    /**
     * The horizontal alignments.
     */
    public enum HAlignment {
        /** left aligned. */
        LEFT,
        /** centered. */
        CENTER,
        /** right aligned. */
        RIGHT
    }

    /**
     * The vertical alignments.
     */
    public enum VAlignment {
        /** top aligned. */
        TOP,
        /** centered. */
        CENTER,
        /** bottom aligned. */
        BOTTOM
    }

    /**
     * Constructs an alignment node.
     */
    public PAlignmentNode() {
        super();
        addPropertyChangeListener(PROPERTY_BOUNDS, this);
    }

    /**
     * {@inheritDoc}
     */
    public void propertyChange(final PropertyChangeEvent event) {
        if (event.getSource() == this && event.getPropertyName().equals(PROPERTY_BOUNDS)) {
            // adjust aligned children
            for (Map.Entry<PNode, AlignmentData> entry : alignmentData.entrySet()) {
                updateAlignedChild(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Adds a child to this node with a given alignment.
     * 
     * @param node
     *            the node
     * @param halignment
     *            the horizontal alignment
     * @param valignment
     *            the vertical alignment
     */
    public void addAlignedChild(final PNode node, final HAlignment halignment,
            final VAlignment valignment) {
        AlignmentData alignment = new AlignmentData(halignment, valignment);
        alignmentData.put(node, alignment);
        addChild(node);
        updateAlignedChild(node, alignment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PNode removeChild(final int index) {
        PNode node = getChild(index);
        if (node != null) {
            alignmentData.remove(node);
            return super.removeChild(index);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAllChildren() {
        alignmentData.clear();
        super.removeAllChildren();
    }

    /**
     * Updates the child with the given alignment data.
     * 
     * @param child
     *            the child
     * @param alignment
     *            the alignment data
     */
    private void updateAlignedChild(final PNode child, final AlignmentData alignment) {
        // get the bounds of the alignment node
        PBounds thisBounds = getBoundsReference();
        // get the bounds of the aligned child
        PBounds childBounds = child.getBounds();
        // adjust the child translation
        PAffineTransform transform = child.getTransformReference(true);
        // horizontal
        switch (alignment.halignment) {
        case LEFT:
            child.translate(-transform.getTranslateX(), 0);
            break;
        case CENTER:
            double dX = thisBounds.width - childBounds.width;
            child.translate(dX / 2 - transform.getTranslateX(), 0);
            break;
        case RIGHT:
            dX = thisBounds.width - childBounds.width;
            child.translate(dX - transform.getTranslateX(), 0);
            break;
        }
        // vertical
        switch (alignment.valignment) {
        case TOP:
            child.translate(0, -transform.getTranslateY());
            break;
        case CENTER:
            double dY = thisBounds.height - childBounds.height;
            child.translate(0, dY / 2 - transform.getTranslateY());
            break;
        case BOTTOM:
            dY = thisBounds.height - childBounds.height;
            child.translate(0, dY - transform.getTranslateY());
            break;
        }
    }

    /**
     * A private data holder class.
     */
    private class AlignmentData {

        /** the horizontal alignment. */
        private HAlignment halignment;
        /** the vertical alignment. */
        private VAlignment valignment;

        /**
         * Constructs an alignment data holder.
         * 
         * @param halignment
         *            the horizontal alignment
         * @param valignment
         *            the vertical alignment
         */
        public AlignmentData(final HAlignment halignment, final VAlignment valignment) {
            this.halignment = halignment;
            this.valignment = valignment;
        }

    }

}
