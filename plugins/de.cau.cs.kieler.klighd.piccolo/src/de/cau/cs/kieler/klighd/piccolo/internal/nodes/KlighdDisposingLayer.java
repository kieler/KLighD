/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.awt.geom.Rectangle2D;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.klighd.piccolo.IKlighdNode;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A sightly extended {@link PLayer} that listens to {@link NodeDisposeListener#DISPOSE} notifications
 * and forwards them to its contained elements.
 *
 * @author chsch
 */
public class KlighdDisposingLayer extends PLayer implements IKlighdNode {

    private static final long serialVersionUID = 4423173127127342353L;

    /**
     * Constructor.
     */
    public KlighdDisposingLayer() {
        this.setPickable(false);
        this.setChildrenPickable(true);
        this.addPropertyChangeListener(NodeDisposeListener.DISPOSE, new NodeDisposeListener(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle2D getAssignedBounds() {
        return getBoundsReference();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PNode asPNode() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChild(final IKlighdNode child) {
        addChild(child.asPNode());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public EObject getViewModelElement() {
        throw new UnsupportedOperationException(
                "A layer does not represent a view model element!");
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSelectable() {
        return false;
    }
    
    /**
     * {@inheritDoc}<br>
     * <br>
     * This specialization always returns <code>true</code> since instances of this class will
     * always respect the bounds of their containing {@link KNodeAbstractNode KNodeAbstractNodes}.<br>
     * <br>
     * Solely {@link KNodeNode KNodeNodes} have to rely on the
     * {@link edu.umd.cs.piccolo.PNode#fullIntersects(Rectangle2D) super implementation} that is
     * reachable via {@link #fullIntersectsOri(Rectangle2D)}.
     *
     */
    @Override
    public boolean fullIntersects(final Rectangle2D parentBounds) {
        return true;
    }

    /**
     * @see #fullIntersects(Rectangle2D)
     *
     * @param parentBounds the bounds to test for intersection against
     *            (specified in parent's coordinate system)
     * @return true if this nodes full bounds intersect the given bounds.
     */
    public boolean fullIntersectsOri(final Rectangle2D parentBounds) {
        return super.fullIntersects(parentBounds);
    }


    private final PBounds tempRect = new PBounds();
    private boolean isValidatingPaint = false;

    // The following two methods shall reduce the load while invalidating diagram parts.
    // See the long explanation in 'KlighdNode' on that!

    // Both methods occur exactly the same way in 'KlighdNode'.

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateFullPaint() {
        isValidatingPaint = true;
        tempRect.resetToZero();

        super.validateFullPaint();

        isValidatingPaint = false;

        if (!tempRect.isEmpty()) {
            repaintFrom(tempRect, this);
        }
    }

    // Don't put further methods in between those two because of the common objective they
    //  are supposed to implement! Both occur exactly the same way in 'KlighdNode'.

    /**
     * {@inheritDoc}
     */
    @Override
    public void repaintFrom(final PBounds localBounds, final PNode childOrThis) {
        if (isValidatingPaint) {
            if (childOrThis != this) {
                this.localToParent(localBounds);
            }
            tempRect.add(localBounds);
        } else {
            super.repaintFrom(localBounds, childOrThis);
        }
    }
}