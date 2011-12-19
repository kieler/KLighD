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
package de.cau.cs.kieler.klighd.util;

/**
 * A data holding class to store color information.
 * 
 * @author mri
 */
public class KlighdColor {
    
    /** the red component. */
    private int r = 0;
    /** the green component. */
    private int g = 0;
    /** the blue component. */
    private int b = 0;

    /**
     * Constructs the default color (black).
     */
    public KlighdColor() {
        // do nothing
    }

    /**
     * Constructs a color from the given components.
     * 
     * @param r
     *            the red component (0-255)
     * @param g
     *            the green component (0-255)
     * @param b
     *            the blue component (0-255)
     */
    public KlighdColor(final int r, final int g, final int b) {
        // CHECKSTYLEOFF MagicNumber
        this.r = Math.max(0, Math.min(255, r));
        this.g = Math.max(0, Math.min(255, g));
        this.b = Math.max(0, Math.min(255, b));
        // CHECKSTYLEON MagicNumber
    }

    /**
     * Constructs a color from the given components.
     * 
     * @param r
     *            the red component (0.0f - 1.0f)
     * @param g
     *            the green component (0.0f - 1.0f)
     * @param b
     *            the blue component (0.0f - 1.0f)
     */
    public KlighdColor(final float r, final float g, final float b) {
        // CHECKSTYLEOFF MagicNumber
        this.r = Math.max(0, Math.min(255, (int) (r * 255)));
        this.g = Math.max(0, Math.min(255, (int) (g * 255)));
        this.b = Math.max(0, Math.min(255, (int) (b * 255)));
        // CHECKSTYLEON MagicNumber
    }

    /**
     * Constructs a color from the given components.
     * 
     * @param r
     *            the red component (0.0 - 1.0)
     * @param g
     *            the green component (0.0 - 1.0)
     * @param b
     *            the blue component (0.0 - 1.0)
     */
    public KlighdColor(final double r, final double g, final double b) {
        // CHECKSTYLEOFF MagicNumber
        this.r = Math.max(0, Math.min(255, (int) (r * 255)));
        this.g = Math.max(0, Math.min(255, (int) (g * 255)));
        this.b = Math.max(0, Math.min(255, (int) (b * 255)));
        // CHECKSTYLEON MagicNumber
    }

    /**
     * Returns the red color component.
     * 
     * @return the red color component
     */
    public int getR() {
        return r;
    }

    /**
     * Sets the red color component.
     * 
     * @param r
     *            the red color component
     */
    public void setR(final int r) {
        this.r = r;
    }

    /**
     * Returns the green color component.
     * 
     * @return the green color component
     */
    public int getG() {
        return g;
    }

    /**
     * Sets the green color component.
     * 
     * @param g
     *            the green color component
     */
    public void setG(final int g) {
        this.g = g;
    }

    /**
     * Returns the blue color component.
     * 
     * @return the blue color component
     */
    public int getB() {
        return b;
    }

    /**
     * Sets the blue color component.
     * 
     * @param b
     *            the blue color component
     */
    public void setB(final int b) {
        this.b = b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "(" + r + "," + g + "," + b + ")";
    }

}
