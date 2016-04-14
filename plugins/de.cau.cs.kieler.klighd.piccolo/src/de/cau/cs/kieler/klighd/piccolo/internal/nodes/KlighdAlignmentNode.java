/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import de.cau.cs.kieler.klighd.krendering.HorizontalAlignment;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.VerticalAlignment;
import de.cau.cs.kieler.klighd.piccolo.KlighdNode;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A Piccolo2D node that can align its child {@link KlighdStyledText} along the horizontal and
 * vertical axes using the {@link KlighdStyledText KlighdStyledText's} {@link PAffineTransform}.<br>
 * <br>
 * The implementation of this class contains some specializations for reducing the effort of
 * re-aligning the attached {@link KlighdStyledText} after its font has been changed.
 *
 * @author mri
 * @author chsch
 */
public class KlighdAlignmentNode extends KlighdNode.KlighdFigureNode<KRendering> {

    private static final long serialVersionUID = -2514462331029707306L;

    /**
     * Constructs an alignment node.
     *
     * @param styledText
     *            the {@link KlighdAlignmentNode} to wrap
     */
    public KlighdAlignmentNode(final KlighdStyledText styledText) {
        super();
        if (styledText == null) {
            final String msg = "KLighD: provided argument is 'null'. "
                    + "KlighdAlignmentNodes must have exactly one child "
                    + "that must be provided while calling the constructor.";
            throw new IllegalArgumentException(msg);
        }
        super.addChild(0, styledText);
    }

    /** the horizontal alignment. */
    private HorizontalAlignment halignment = HorizontalAlignment.CENTER;

    /** the vertical alignment. */
    private VerticalAlignment valignment = VerticalAlignment.CENTER;

    /**
     * Sets the horizontal alignment of a child node.
     *
     * @param newHAlignment
     *            the horizontal alignment
     */
    public void setHorizontalAlignment(final HorizontalAlignment newHAlignment) {
        if (halignment == newHAlignment) {
            return;
        }

        halignment = newHAlignment;

        setFullBoundsInvalid(true);
        setChildBoundsInvalid(true);

        invalidatePaint();
    }

    /**
     * Sets the vertical alignment of a child node.
     *
     * @param newVAlignment
     *            the vertical alignment
     */
    public void setVerticalAlignment(final VerticalAlignment newVAlignment) {
        if (valignment == newVAlignment) {
            return;
        }

        valignment = newVAlignment;

        setFullBoundsInvalid(true);
        setChildBoundsInvalid(true);

        invalidatePaint();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void addChild(final int index, final PNode child) {
        final String msg = "KLighD: KlighdAlignmentNodes must have exactly one "
                + "child that must be provided while calling the constructor.";
        throw new UnsupportedOperationException(msg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PNode removeChild(final int index) {
        final String msg =
                "KLighD: KlighdAlignmentNodes must have exactly one child that can't be removed.";
        throw new UnsupportedOperationException(msg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAllChildren() {
        final String msg =
                "KLighD: KlighdAlignmentNodes must have exactly one child that can't be removed.";
        throw new UnsupportedOperationException(msg);
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * The rationale of this specialization is the desire to avoid the calls of 'invalidateFullBounds()'
     *  in {@link KlighdStyledText} in case the font was changed. Such font changes usually require a
     *  re-alignment of the text figure. The aim is perform this update locally without propagating the
     *  'invalidateFullBounds()' notification up to the root figure, which would yield a
     *  'validateFullBounds()' from the root along 'dirty' figures.
     */
    @Override
    public void setChildPaintInvalid(final boolean childPaintInvalid) {
        // this method is called if the attached 'KlighdStyledText' calls 'invalidatePaint()'
        //  ('childPaintInvalid' == 'true'), as well as during 'validateFullPaint()' called
        //  on this node ('childPaintInvalid' == 'false')

        super.setChildPaintInvalid(childPaintInvalid);

        // in the attached 'KlighdStyledText's paint is invalidated ('childPaintInvalid' == 'true')
        //  and its 'fullBoundsInvalid' flag is set, set also this alignment node's
        //  'fullBoundsInvalid' flag and 'childBoundsInvalid' in order get this node's and the
        //  attached styledText's bounds re-validated and the styledText re-aligned on next access!
        //  The alignment is implemented in 'layoutChildren()' (see below), which called during
        //   'validateFullBounds()'.

        if (childPaintInvalid
                && ((KlighdStyledText) getChild(0)).getFullBoundsInvalid()) {
            this.setFullBoundsInvalid(true);
            this.setChildBoundsInvalid(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void layoutChildren() {
        if (this.getBoundsReference().isEmpty()) {
            // in this case aligning the text is senseless,
            //  the bounds will be initialized later, for sure!
            return;
        }

        final KlighdStyledText styledText = (KlighdStyledText) getChild(0);

        updateAlignedChildHorizontal(styledText);
        updateAlignedChildVertical(styledText);
    }


    /**
     * Updates the given aligned child's horizontal alignment.
     *
     * @param alignment
     *            the child's alignment data
     * @param child
     *            the child
     */
    private void updateAlignedChildHorizontal(final KlighdStyledText child) {
        // get the bounds of the alignment node
        final PBounds thisBounds = getBoundsReference();
        // get the bounds of the aligned child
        final PBounds childBounds = child.getBoundsReference();
        // adjust the child translation
        final PAffineTransform transform = child.getTransformReference(true);

        final double x;
        
        if (halignment != null) {
            switch (halignment) {
            case LEFT:
                x = 0;
                break;
            case RIGHT:
                x = thisBounds.width - childBounds.width;
                break;
            default /* CENTER */:
                x = (thisBounds.width - childBounds.width) / 2;
            }
        } else {
            x = (thisBounds.width - childBounds.width) / 2;
        }

        if (x == transform.getTranslateX()) {
            return;
        }

        transform.setOffset(x, transform.getTranslateY());
        child.setFullBoundsInvalid(true);
        child.invalidatePaint();
    }

    /**
     * Updates the given aligned child's vertical alignment.
     *
     * @param alignment
     *            the child's alignment data
     * @param child
     *            the child
     */
    private void updateAlignedChildVertical(final KlighdStyledText child) {
        // get the bounds of the alignment node
        final PBounds thisBounds = getBoundsReference();
        // get the bounds of the aligned child
        final PBounds childBounds = child.getBoundsReference();

        final double y;
        switch (valignment) {
        case TOP:
            y = 0;
            break;
        case BOTTOM:
            y = thisBounds.height - childBounds.height;
            break;
        default /* CENTER */:
            y = (thisBounds.height - childBounds.height) / 2;
        }

        final PAffineTransform transform = child.getTransformReference(true);
        if (y == transform.getTranslateY()) {
            return;
        }

        // adjust the child translation
        transform.setOffset(transform.getTranslateX(), y);
        child.setFullBoundsInvalid(true);
        child.invalidatePaint();
    }
}
