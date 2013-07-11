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
package de.cau.cs.kieler.klighd.piccolo.nodes;

import java.awt.geom.Rectangle2D;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * The base class for nodes which are visually represented by a child node.
 * 
 * @author mri
 */
public class PChildRepresentedNode extends PEmptyNode {

    private static final long serialVersionUID = -2199744336722936653L;

    /** the representing node. */
    private PNode repNode = null;

    /**
     * Sets the child node representing this node if it is a descendent of this node.
     * 
     * @param representationNode
     *            the representation node
     */
    public void setRepresentationNode(final PNode representationNode) {
        repNode = representationNode;
    }

    /**
     * Returns the child node representing this node.
     * 
     * @return the representation node or null if no representation has been set
     */
    public PNode getRepresentationNode() {
        return repNode;
    }

    // redirect required methods to the representation node

    /**
     * {@inheritDoc}
     */
    @Override
    public void startResizeBounds() {
        if (repNode != null) {
            repNode.startResizeBounds();
        } else {
            super.startResizeBounds();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endResizeBounds() {
        if (repNode != null) {
            repNode.endResizeBounds();
        } else {
            super.endResizeBounds();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setX(final double x) {
        if (repNode != null) {
            return repNode.setX(x);
        } else {
            return super.setX(x);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setY(final double y) {
        if (repNode != null) {
            return repNode.setY(y);
        } else {
            return super.setY(y);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setWidth(final double width) {
        if (repNode != null) {
            return repNode.setWidth(width);
        } else {
            return super.setWidth(width);                    
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setHeight(final double height) {
        if (repNode != null) {
            return repNode.setHeight(height);
        } else {
            return super.setHeight(height);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setBounds(final Rectangle2D newBounds) {
        if (repNode != null) {
            return repNode.setBounds(newBounds);
        } else {
            return super.setBounds(newBounds);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setBounds(final double x, final double y, final double width, final double height) {
        if (repNode != null) {
            return repNode.setBounds(x, y, width, height);
        } else {
            return super.setBounds(x, y, width, height);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        if (repNode != null) {
            return repNode.getX();
        } else {
            return super.getX();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        if (repNode != null) {
            return repNode.getY();
        } else {
            return super.getY();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        if (repNode != null) {
            return repNode.getWidth();
        } else {
            return super.getWidth();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        if (repNode != null) {
            return repNode.getHeight();
        } else {
            return super.getHeight();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PBounds getBounds() {
        if (repNode != null) {
            return repNode.getBounds();
        } else {
            return super.getBounds();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PBounds getBoundsReference() {
        if (repNode != null) {
            return repNode.getBoundsReference();
        } else {
            return super.getBoundsReference();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PBounds getGlobalBounds() {
        if (repNode != null) {
            return repNode.getGlobalBounds();
        } else {
            return super.getGlobalBounds();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersects(final Rectangle2D localBounds) {
        if (repNode != null) {
            return repNode.intersects(localBounds);
        } else {
            return super.intersects(localBounds);
        }
    }

}
