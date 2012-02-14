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
import java.util.LinkedHashSet;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

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

    /** the attribute key for the alignment data. */
    private static final Object ALIGNMENT_DATA_KEY = new Object();

    /** the currently managed aligned children. */
    private LinkedHashSet<PNode> alignedChildren = Sets.newLinkedHashSet();

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
        if (event.getPropertyName().equals(PROPERTY_BOUNDS)) {
            if (event.getSource() == this) {
                // adjust aligned children
                for (PNode alignedChild : alignedChildren) {
                    AlignmentData data = getAlignmentData(alignedChild);
                    updateAlignedChildHorizontal(data, alignedChild);
                    updateAlignedChildVertical(data, alignedChild);
                }
            } else {
                // adjust the aligned child
                PNode alignedChild = (PNode) event.getSource();
                AlignmentData data = getAlignmentData(alignedChild);
                updateAlignedChildHorizontal(data, alignedChild);
                updateAlignedChildVertical(data, alignedChild);
            }
        }
    }

    /**
     * Sets the horizontal alignment of a child node.
     * 
     * @param node
     *            the child node
     * @param halignment
     *            the horizontal alignment
     */
    public void setHorizontalAlignment(final PNode node, final HAlignment halignment) {
        if (node.getParent() != this) {
            return;
        }

        // apply the alignment
        AlignmentData data = getAlignmentData(node);
        data.halignment = halignment;
        addAlignedChild(node);
        updateAlignedChildHorizontal(data, node);
    }

    /**
     * Sets the vertical alignment of a child node.
     * 
     * @param node
     *            the child node
     * @param valignment
     *            the vertical alignment
     */
    public void setVerticalAlignment(final PNode node, final VAlignment valignment) {
        if (node.getParent() != this) {
            return;
        }

        // apply the alignment
        AlignmentData data = getAlignmentData(node);
        data.valignment = valignment;
        addAlignedChild(node);
        updateAlignedChildVertical(data, node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PNode removeChild(final int index) {
        PNode node = getChild(index);
        if (node != null) {
            removeAlignedChild(node);
            return super.removeChild(index);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAllChildren() {
        List<PNode> removedNodes = Lists.newLinkedList(alignedChildren);
        for (PNode alignedChild : removedNodes) {
            removeAlignedChild(alignedChild);
        }
        super.removeAllChildren();
    }

    /**
     * Updates the given aligned child's horizontal alignment.
     * 
     * @param alignment
     *            the child's alignment data
     * @param child
     *            the child
     */
    private void updateAlignedChildHorizontal(final AlignmentData alignment, final PNode child) {
        // get the bounds of the alignment node
        PBounds thisBounds = getBoundsReference();
        // get the bounds of the aligned child
        PBounds childBounds = child.getBoundsReference();
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
    }

    /**
     * Updates the given aligned child's vertical alignment.
     * 
     * @param alignment
     *            the child's alignment data
     * @param child
     *            the child
     */
    private void updateAlignedChildVertical(final AlignmentData alignment, final PNode child) {
        // get the bounds of the alignment node
        PBounds thisBounds = getBoundsReference();
        // get the bounds of the aligned child
        PBounds childBounds = child.getBoundsReference();
        // adjust the child translation
        PAffineTransform transform = child.getTransformReference(true);
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
     * Adds a node as aligned child.
     * 
     * @param node
     *            the node
     */
    private void addAlignedChild(final PNode node) {
        if (alignedChildren.add(node)) {
            node.addPropertyChangeListener(PNode.PROPERTY_BOUNDS, this);
        }
    }

    /**
     * Removes a node as aligned child.
     * 
     * @param node
     *            the node
     */
    private void removeAlignedChild(final PNode node) {
        if (alignedChildren.remove(node)) {
            node.removePropertyChangeListener(PNode.PROPERTY_BOUNDS, this);
            node.addAttribute(ALIGNMENT_DATA_KEY, null);
        }
    }

    /**
     * Returns an alignment data instance attached to the given node.
     * 
     * @param node
     *            the node
     * @return the alignment data
     */
    private AlignmentData getAlignmentData(final PNode node) {
        Object data = node.getAttribute(ALIGNMENT_DATA_KEY);
        AlignmentData alignmentData;
        if (data == null || !(data instanceof AlignmentData)) {
            alignmentData = new AlignmentData(HAlignment.CENTER, VAlignment.CENTER);
            node.addAttribute(ALIGNMENT_DATA_KEY, alignmentData);
        } else {
            alignmentData = (AlignmentData) data;
        }
        return alignmentData;
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
