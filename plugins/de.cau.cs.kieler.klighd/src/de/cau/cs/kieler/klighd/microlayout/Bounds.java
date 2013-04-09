/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.microlayout;

import java.awt.geom.Rectangle2D;

import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;

/**
 * @author chsch
 *
 */
public class Bounds {

    // CHECKSTYLEOFF Visibility

    /** the x-coordinate. */
    float x;
    /** the y-coordinate. */
    float y;
    /** the width. */
    float width;
    /** the height. */
    float height;
    /**
     * the insets used to transport the position of a ChildAreaCell from estimateGridSize to
     * calculateInsets method.
     */
    KInsets insets = null;

    // CHECKSTYLEON Visibility
    
    /**
     * Constructs bounds with the given dimensions and (x,y) coordinates of (0,0).
     * 
     * @param width
     *            the width
     * @param height
     *            the height
     */
    public Bounds(final float width, final float height) {
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
        this.insets = KLayoutDataFactory.eINSTANCE.createKInsets();
    }

    /**
     * Constructs bounds with the given dimensions and (x,y) coordinates of (0,0).
     * 
     * @param width
     *            the width
     * @param height
     *            the height
     */
    public Bounds(final double width, final double height) {
        this.x = 0;
        this.y = 0;
        this.width = (float) width;
        this.height = (float) height;
        this.insets = KLayoutDataFactory.eINSTANCE.createKInsets();
    }

    /**
     * Constructs bounds from the dimensions of the given Bounds.
     * 
     * @param bounds
     *            the Bounds to take the data from
     */
    public Bounds(final Bounds bounds) {
        this.x = bounds.x;
        this.y = bounds.y;
        this.width = bounds.width;
        this.height = bounds.height;
        this.insets = KLayoutDataFactory.eINSTANCE.createKInsets();
    }

    /**
     * Constructs bounds from the dimensions of the given SWT Point with coordinates (0,0).
     * 
     * @param point
     *            the SWT point to take width and height from
     */
    public Bounds(final org.eclipse.swt.graphics.Point point) {
        this.width = point.x;
        this.height = point.y;
        this.insets = KLayoutDataFactory.eINSTANCE.createKInsets();
    }

    /**
     * Constructs bounds from the dimensions of the given AWT geometry {@link Rectangle2D.Float}.
     * 
     * @param rect
     *            the {@link Rectangle2D.Float} to take x, y, width, and height from
     */
    public Bounds(final Rectangle2D.Float rect) {
        this.x = rect.x;
        this.y = rect.y;
        this.width = rect.width;
        this.height = rect.height;
        this.insets = KLayoutDataFactory.eINSTANCE.createKInsets();
    }

    /**
     * Constructs bounds from the dimensions of the given AWT geometry {@link Rectangle2D.Float}.
     * 
     * @param rect
     *            the {@link Rectangle2D} to take x, y, width, and height from
     */
    public Bounds(final Rectangle2D rect) {
        this.x = (float) rect.getX();
        this.y = (float) rect.getY();
        this.width = (float) rect.getWidth();
        this.height = (float) rect.getHeight();
        this.insets = KLayoutDataFactory.eINSTANCE.createKInsets();
    }

    /**
     * Constructs bounds from the given coordinates and dimensions.
     * 
     * @param x
     *            the x-coordinate
     * @param y
     *            the y-coordinate
     * @param width
     *            the width
     * @param height
     *            the height
     */
    public Bounds(final float x, final float y, final float width, final float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.insets = KLayoutDataFactory.eINSTANCE.createKInsets();
    }

    /**
     * Takes the data of the given bounds.
     * 
     * @param bounds
     *            the bounds to take the data from
     */
    void setBounds(final Bounds bounds) {
        this.x = bounds.x;
        this.y = bounds.y;
        this.width = bounds.width;
        this.height = bounds.height;
        this.insets = KLayoutDataFactory.eINSTANCE.createKInsets();
    }

    /**
     * Getter for X coordinate.
     * 
     * @return height
     */
    public float getX() {
        return this.x;
    }

    /**
     * Getter for Y coordinate.
     * 
     * @return width
     */
    public float getY() {
        return this.y;
    }

    /**
     * Getter used in JUnit test of the placement logic.
     * 
     * @return height
     */
    public float getHeight() {
        return this.height;
    }

    /**
     * Getter used in JUnit test of the placement logic.
     * 
     * @return width
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * Transforms the current {@link Bounds} object in an AWT geometry {@link Rectangle2D}.
     * 
     * @return a related {@link Rectangle2D}
     */
    public Rectangle2D toRectangle2D() {
        return new Rectangle2D.Float(this.x, this.y, this.width, this.height);
    }

    /**
     * Getter to access the Insets of this Bounds.
     * 
     * @return width
     */
    public KInsets getInsets() {
        return this.insets;
    }
    
    /**
     * {@inheritDoc}
     */
    public String toString() {
        return "(" + this.x + "," + this.y + "|" + this.width + "," + this.height + ")"; 
    }

    /**
     * Determines component-wise the maximum of the given bounds <code>b1</code> and
     * <code>b2</code>. The function <b>modifies</b> <code>b1</code>.
     * 
     * @param b1
     *            the first {@link Bounds} object
     * @param b2
     *            the second {@link Bounds} object
     * @return the object b1 with modified <code>width</code> and <code>height</code> data
     *         containing the particular maximal values.
     */
    public static Bounds max(final Bounds b1, final Bounds b2) {
        b1.width = Math.max(b1.width, b2.width);
        b1.height = Math.max(b1.height, b2.height);
        return b1;
    }
    
    /**
     * Determines component-wise the minimum of the given bounds <code>b1</code> and
     * <code>b2</code>. The function <b>modifies</b> <code>b1</code>.
     * 
     * @param b1
     *            the first {@link Bounds} object
     * @param b2
     *            the second {@link Bounds} object
     * @return the object b1 with modified <code>width</code> and <code>height</code> data
     *         containing the particular minimal values.
     */
    public static Bounds min(final Bounds b1, final Bounds b2) {
        b1.width = Math.min(b1.width, b2.width);
        b1.height = Math.min(b1.height, b2.height);
        return b1;
    }
    
    /**
     * Constructs bounds with the given dimensions and (x,y) coordinates (0,0).
     * 
     * @param width
     *            the width
     * @param height
     *            the height
     * @return the desired {@link Bounds} object
     */
    public static Bounds of(final float width, final float height) {
        return new Bounds(width, height);
    }
   
    /**
     * Constructs bounds from the dimensions of the given AWT geometry {@link Rectangle2D}.
     * 
     * @param rect
     *            the {@link Rectangle2D} to take x, y, width, and height from
     * @return the desired {@link Bounds} object
     */
    public static Bounds of(final Rectangle2D rect) {
        return new Bounds(rect);
    }

    /**
     * An immutable variant of {@link Bounds} that can be used to make sure the bounds are not
     * changed. Note that this can be ensured for usages in different packages only. 
     * 
     * @author chsch
     */
    public static class ImmutableBounds extends Bounds {

        /**
         * Constructs bounds with the given dimensions and (x,y) coordinates of (0,0).
         * 
         * @param width
         *            the width
         * @param height
         *            the height
         */
        public ImmutableBounds(final float width, final float height) {
            super(width, height);
        }

        /**
         * Constructs bounds with the given dimensions and (x,y) coordinates of (0,0).
         * 
         * @param width
         *            the width
         * @param height
         *            the height
         */
        public ImmutableBounds(final double width, final double height) {
            super(width, height);
        }
        
        /**
         * Constructs bounds from the dimensions of the given Bounds.
         * 
         * @param bounds
         *            the Bounds to take the data from
         */
        public ImmutableBounds(final Bounds bounds) {
            super(bounds);
        }

        private static final String MSG = "This bounds object is immutable! Modifying is not allowed.";
        
        void setBounds(final Bounds bounds) {
            throw new UnsupportedOperationException(MSG);
        }
    }
}
