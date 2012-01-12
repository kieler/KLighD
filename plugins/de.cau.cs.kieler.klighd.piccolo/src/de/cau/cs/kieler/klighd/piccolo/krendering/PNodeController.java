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
/**
 * 
 */
package de.cau.cs.kieler.klighd.piccolo.krendering;

import java.awt.Color;

import de.cau.cs.kieler.klighd.piccolo.nodes.PSWTAdvancedPath.LineStyle;
import de.cau.cs.kieler.klighd.piccolo.nodes.PSWTAdvancedText.Alignment;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * The abstract base class for Piccolo node controllers.<br />
 * <br />
 * A node controller is a facade on a specific Piccolo node, providing a generic interface to set
 * the bounds and other parameters of the node.
 * 
 * @author mri
 * 
 * @param <T>
 *            the type of the associated node
 */
public abstract class PNodeController<T extends PNode> {

    /** the controller's node. */
    private T node;

    /**
     * Constructs a controller for a given Piccolo node.
     * 
     * @param node
     *            the Piccolo node
     */
    public PNodeController(final T node) {
        this.node = node;
    }

    /**
     * Sets the bounds of the associated node.
     * 
     * @param bounds
     *            the bounds
     */
    public abstract void setBounds(final PBounds bounds);

    /**
     * Sets the foreground color of the associated node.
     * 
     * @param color
     *            the foreground color
     */
    public void setForegroundColor(final Color color) {
        // do nothing
    }

    /**
     * Sets the background color of the associated node.
     * 
     * @param color
     *            the background color
     */
    public void setBackgroundColor(final Color color) {
        // do nothing
    }

    /**
     * Sets the line width of the associated node.
     * 
     * @param lineWidth
     *            the line width
     */
    public void setLineWidth(final float lineWidth) {
        // do nothing
    }

    /**
     * Sets the line visibility of the associated node.
     * 
     * @param lineVisible
     *            the line visibility
     */
    public void setLineVisible(final boolean lineVisible) {
        // do nothing
    }

    /**
     * Sets the filled status of the associated node.
     * 
     * @param filled
     *            the filled status
     */
    public void setFilled(final boolean filled) {
        // do nothing
    }

    /**
     * Sets the line style of the associated node.
     * 
     * @param lineStyle
     *            the line style
     */
    public void setLineStyle(final LineStyle lineStyle) {
        // do nothing
    }

    /**
     * Sets the horizontal alignment of the associated node.
     * 
     * @param alignment
     *            the horizontal alignment
     */
    public void setHorizontalAlignment(final Alignment alignment) {
        // do nothing
    }

    /**
     * Sets the vertical alignment of the associated node.
     * 
     * @param alignment
     *            the vertical alignment
     */
    public void setVerticalAlignment(final Alignment alignment) {
        // do nothing
    }

    /**
     * Returns the associated node.
     * 
     * @return the node
     */
    public T getNode() {
        return node;
    }

}
