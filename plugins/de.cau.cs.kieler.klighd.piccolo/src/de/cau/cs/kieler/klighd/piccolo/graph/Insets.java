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
package de.cau.cs.kieler.klighd.piccolo.graph;

/**
 * A simple class for holding inset information.
 * 
 * @author mri
 */
public class Insets {

    /** the right inset. */
    private double top;
    /** the left inset. */
    private double left;
    /** the bottom inset. */
    private double bottom;
    /** the right inset. */
    private double right;

    /**
     * Constructs default insets.
     */
    public Insets() {
        this(0.0, 0.0, 0.0, 0.0);
    }

    /**
     * Constructs the class from the insets.
     * 
     * @param top
     *            the top inset
     * @param left
     *            the left inset
     * @param bottom
     *            the bottom inset
     * @param right
     *            the right inset
     */
    public Insets(final double top, final double left, final double bottom, final double right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    /**
     * Sets the top inset.
     * 
     * @param top
     *            the top inset
     */
    public void setTop(final double top) {
        this.top = top;
    }

    /**
     * Returns the top inset.
     * 
     * @return the top inset
     */
    public double getTop() {
        return top;
    }

    /**
     * Sets the left inset.
     * 
     * @param left
     *            the left inset
     */
    public void setLeft(final double left) {
        this.left = left;
    }

    /**
     * Returns the left inset.
     * 
     * @return the left inset
     */
    public double getLeft() {
        return left;
    }

    /**
     * Sets the bottom inset.
     * 
     * @param bottom
     *            the bottom inset
     */
    public void setBottom(final double bottom) {
        this.bottom = bottom;
    }

    /**
     * Returns the bottom inset.
     * 
     * @return the bottom inset
     */
    public double getBottom() {
        return bottom;
    }

    /**
     * Sets the right inset.
     * 
     * @param right
     *            the right inset
     */
    public void setRight(final double right) {
        this.right = right;
    }

    /**
     * Returns the right inset.
     * 
     * @return the right inset
     */
    public double getRight() {
        return right;
    }

}
