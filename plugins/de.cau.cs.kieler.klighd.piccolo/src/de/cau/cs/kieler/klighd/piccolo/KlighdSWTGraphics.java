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
package de.cau.cs.kieler.klighd.piccolo;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.klighd.piccolo.util.RGBGradient;

/**
 * This interface defines methods to be used by custom {@link edu.umd.cs.piccolo.PNode PNode}
 * implementations to draw there shapes. Its aim is to abstract the concrete
 * {@link edu.umd.cs.piccolox.swt.SWTGraphics2D SWTGraphics2D} implementation contributed by the
 * <code>edu.umd.cs.piccolo</code> packages.<br>
 * <br>
 * For drawing basic figures (except text fields) only {@link #draw(Shape)} and {@link #fill(Shape)}
 * are provided, since drawing and coloring such elements is to be performed by means of
 * {@link java.awt.geom.PathIterator PathIterators}, which are provided by AWT {@link Shape Shapes}
 * via {@link Shape#getPathIterator(AffineTransform)}.<br>
 * <br>
 * The rational of this approach is the decision to draw all basic figures by means of SWT
 * {@link org.eclipse.swt.graphics.Path Path} objects. This is currently the only way of passing
 * floating-point-based coordinates to the {@link org.eclipse.swt.graphics.GC GC}. Such SWT
 * {@link org.eclipse.swt.graphics.Path Paths} can be easily built-up by means of AWT Geometry
 * {@link java.awt.geom.PathIterator PathIterators}, see
 * {@link edu.umd.cs.piccolox.swt.SWTGraphics2D#pathIterator2Path(java.awt.geom.PathIterator)
 * SWTGraphics2D#pathIterator2Path(PathIterator)}.<br>
 * <br>
 * Since the coordinates of the particular figures are relative to their parent figures, all
 * coordinates must be adjusted by the currently visible area and zoom factor. In order to do that
 * the path object must be inspected and the segment values updated. This is task is, fortunately,
 * also performed by the {@link java.awt.geom.PathIterator PathIterators}.<br>
 * <br>
 * A further of this approach is an easier support of rotation of basic figures. This requires the
 * rotation of the coordinates while drawing the figures on the one hand, and the incorporation of
 * the rotation for determining the currently picked figure while processing mouse input events.
 * Relying on the AWT Geometry {@link java.awt.geom.PathIterator PathIterators} enables consistent
 * and homogeneous calculations for both use cases.<br>
 * <br>
 * To be continued for text stuff.
 * 
 * @author chsch
 */
public interface KlighdSWTGraphics {

    /**
     * 
     * @return the {@link Device} to work with
     */
    Device getDevice();

    
    /**
     * This setter allows to (re-) use an object adhering to this interface for multiple paint runs.
     * 
     * @author chsch
     * 
     * @param theDevice
     *            the {@link Device} to work with
     */
    void setDevice(Device theDevice);

    /**
     * This setter allows to (re-) use an object adhering to this interface for multiple paint runs.
     * 
     * @author chsch
     * 
     * @param theGc
     *            the {@link GC} to paint on
     */
    void setGC(GC theGc);

    /**
     * Returns the currently configured line attributes in a {@link LineAttributes} record
     * structure.
     * 
     * @return a {@link LineAttributes} object reflecting the currently set line attributes.
     */
    LineAttributes getLineAttributes();
    
    /**
     * Sets the line attributes to use when drawing shapes.
     * 
     * @param attributes of lines when drawing shapes
     */
    void setLineAttributes(LineAttributes attributes);
    
    /**
     * Returns the line width to use when drawing shapes.
     * 
     * @author chsch
     * 
     * @return width of lines when drawing shapes
     */
    float getLineWidth();

    /**
     * Sets the line width to use when drawing shapes.
     * 
     * @author chsch
     * 
     * @param lineWidth
     *            width of lines when drawing shapes
     */
    void setLineWidth(final float lineWidth);

    /*---------------------------------------------*/
    /* Desired coloring & style getter and setter. */
    /*---------------------------------------------*/
    
    /**
     * Returns the alpha value currently used by the current {@link GC}.
     * 
     * @author chsch
     * 
     * @return the current alpha value in range of 0 to 255.
     */
    int getAlpha();

    /**
     * Sets the alpha value to be used during the subsequent paint instructions.<br>
     * <b>Caution:</b> This value will also set by Piccolo for realizing the transparency. That
     * feature is used in the
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.activities.FadeNodeInActivity
     * FadeNodeInActivity} and
     * {@link de.cau.cs.kieler.klighd.piccolo.internal.activities.FadeEdgeInActivity
     * FadeEdgeInActivity} activities. Thus do <b>not</b> set the alpha to an absolute value, but to
     * a relative one. The current value can be obtain by {@link #getAlpha()}.
     * 
     * @author chsch
     * 
     * @param alpha
     *            the alpha value to be used in range of 0 to 255
     */
    void setAlpha(final int alpha);

    /**
     * Sets the stroke color to the provided {@link RGB} color descriptor.
     * 
     * @author chsch
     * 
     * @param color
     *            new stroke color
     */
    void setColor(final RGB color);

    /**
     * Sets the stroke color gradient to the provided {@link RGBGradient} descriptor.
     * 
     * @author chsch
     * 
     * @param gradient
     *            new background gradient
     * @param bounds
     *            the local (non-adjusted) bounds within the gradient is to be applied
     */
    void setPattern(final RGBGradient gradient, final Rectangle2D bounds);

    /**
     * Sets the background color to the provided {@link RGB} color descriptor.
     * 
     * @author chsch
     * 
     * @param backgroundColor
     *            new background color
     */
    void setBackground(final RGB backgroundColor);

    /**
     * Sets the background color gradient to the provided {@link RGBGradient} descriptor.
     * 
     * @author chsch
     * 
     * @param backgroundGradient
     *            new background gradient
     * @param bounds
     *            the local (non-adjusted) bounds within the gradient is to be applied
     */
    void setBackgroundPattern(final RGBGradient backgroundGradient, final Rectangle2D bounds);


    /*-----------------------------------------------------------------------*/
    /* Some AffineTransform-related methods required by the PSWTAdvancedPath */
    /* for properly applying the translation and scaling of related shape.   */
    /*-----------------------------------------------------------------------*/
    
    /**
     * Returns a copy of the current <code>Transform</code> in the <code>Graphics2D</code> context.<br>
     * 
     * @author <b>Copied from {@link java.awt.Graphics2D#getTransform()}!</b>
     * 
     * @return the current <code>AffineTransform</code> in the <code>Graphics2D</code> context.
     * @see #transform
     * @see #setTransform
     */
    AffineTransform getTransform();

    /**
     * Composes an <code>AffineTransform</code> object with the <code>Transform</code> in this
     * <code>Graphics2D</code> according to the rule last-specified-first-applied. If the current
     * <code>Transform</code> is Cx, the result of composition with Tx is a new
     * <code>Transform</code> Cx'. Cx' becomes the current <code>Transform</code> for this
     * <code>Graphics2D</code>. Transforming a point p by the updated <code>Transform</code> Cx' is
     * equivalent to first transforming p by Tx and then transforming the result by the original
     * <code>Transform</code> Cx. In other words, Cx'(p) = Cx(Tx(p)). A copy of the Tx is made, if
     * necessary, so further modifications to Tx do not affect rendering.<br>
     * 
     * @author <b>Copied from {@link java.awt.Graphics2D#getTransform()}!</b>
     * 
     * @param transform
     *            the <code>AffineTransform</code> object to be composed with the current
     *            <code>Transform</code>
     * @see #setTransform
     * @see AffineTransform
     */
    void transform(final AffineTransform transform);
    
    /**
     * See {@link java.awt.Graphics2D#setTransform(AffineTransform)}!
     * 
     * @param transform
     *            the <code>AffineTransform</code> that was retrieved from the
     *            <code>getTransform</code> method
     * @see #transform
     * @see #getTransform
     * @see AffineTransform
     */
    void setTransform(final AffineTransform transform);

    /**
     * Draws the provided AWT {@link Shape} by relying on the provided
     * {@link java.awt.geom.PathIterator PathIterator} (
     * {@link Shape#getPathIterator(AffineTransform)}) respecting the current AWT transform without
     * any caching.
     * 
     * @param s
     *            the <code>Shape</code> to be rendered
     */
    void draw(final Shape s);
    
    /**
     * Fills the provided AWT {@link Shape} by relying on the provided
     * {@link java.awt.geom.PathIterator PathIterator} (
     * {@link Shape#getPathIterator(AffineTransform)}) respecting the current AWT transform without
     * any caching.
     * 
     * @param s
     *            the <code>Shape</code> to be filled
     */
    void fill(final Shape s);
    
}
