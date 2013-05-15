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

import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

//import org.eclipse.swt.SWT; // SUPPRESS CHECKSTYLE Unused: Is referenced in lots of Javadoc annotations
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.klighd.piccolo.util.RGBGradient;

/**
 * This interface defines methods to be used by custom {@link edu.umd.cs.piccolo.PNode PNode}
 * implementations to draw there shapes in order to abstract the concrete
 * {@link edu.umd.cs.piccolox.swt.SWTGraphics2D SWTGraphics2D} implementation contributed by the
 * <code>edu.umd.cs.piccolo</code> packages.<br>
 * It is still incomplete.
 * 
 * @author chsch
 */
public interface KlighdSWTGraphics {

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
     * feature is used in the {@link de.cau.cs.kieler.klighd.piccolo.activities.FadeNodeInActivity
     * FadeNodeInActivity} and {@link de.cau.cs.kieler.klighd.piccolo.activities.FadeEdgeInActivity
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
     * Sets the stroke color to the provided RGB color descriptor.
     * 
     * @author chsch
     * 
     * @param color
     *            new stroke color
     */
    void setColor(final RGB color);

    /**
     * Sets the stroke color gradient to the provided RGB gradient descriptor.
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
     * Sets the background color to the provided RGB color descriptor.
     * 
     * @author chsch
     * 
     * @param backgroundColor
     *            new background color
     */
    void setBackground(final RGB backgroundColor);

    /**
     * Sets the background color gradient to the provided RGB gradient descriptor.
     * 
     * @author chsch
     * 
     * @param backgroundGradient
     *            new background gradient
     * @param bounds
     *            the local (non-adjusted) bounds within the gradient is to be applied
     */
    void setBackgroundPattern(final RGBGradient backgroundGradient, final Rectangle2D bounds);

//    /**
//     * Returns the line width to use when drawing shapes.<br>
//     * Result is supposed to be one of the constants {@link SWT#LINE_SOLID}, {@link SWT#LINE_DASH},
//     * {@link SWT#LINE_DOT}, {@link SWT#LINE_DASHDOT} or {@link SWT#LINE_DASHDOTDOT}.
//     * 
//     * @author chsch
//     * 
//     * @return style of lines when drawing shapes
//     */
//    int getLineStyle();
//    
//    /**
//     * Sets the line width to use when drawing shapes. <br>
//     * Must be one of the constants {@link SWT#LINE_SOLID}, {@link SWT#LINE_DASH},
//     * {@link SWT#LINE_DOT}, {@link SWT#LINE_DASHDOT} or {@link SWT#LINE_DASHDOTDOT}.
//     * 
//     * @author chsch
//     * 
//     * @param lineStyle
//     *            style of lines when drawing shapes
//     */
//    void setLineStyle(final int lineStyle);
//
//    /**
//     * Returns the line width to use when drawing shapes. Result is supposed to be one of the
//     * constants {@link SWT#CAP_FLAT}, {@link SWT#CAP_ROUND}, or {@link SWT#CAP_SQUARE}.
//     * 
//     * @author chsch
//     * 
//     * @return cap style of lines when drawing strokes
//     */
//    int getLineCap();
//    
//    /**
//     * Sets the line width to use when drawing shapes. <br>
//     * Must be one of the constants {@link SWT#CAP_FLAT}, {@link SWT#CAP_ROUND}, or
//     * {@link SWT#CAP_SQUARE}.
//     * 
//     * @author chsch
//     * 
//     * @param lineCap
//     *            cap style of lines when drawing strokes
//     */
//    void setLineCap(final int lineCap);

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

    /*------------------------------------------------------------*/
    /* Desired floating-point-based shape drawing methods.        */
    /* They have mostly been defined by the Piccolo2D developers. */
    /*------------------------------------------------------------*/
    
    /**
     * Draws the outline of the specified rectangle. The left and right edges of the rectangle are
     * at x and x + width. The top and bottom edges are at y and y + height. The rectangle is drawn
     * using the graphics context's current color.
     * 
     * @author Interface & documentation has been defined by the Piccolo2D project
     * 
     * @param x
     *            the x coordinate of the rectangle to be drawn.
     * @param y
     *            the y coordinate of the rectangle to be drawn.
     * @param width
     *            the width of the rectangle to be drawn.
     * @param height
     *            the height of the rectangle to be drawn.
     */
    void drawRect(final double x, final double y, final double width, final double height);

    /**
     * Fills the specified rectangle. The left and right edges of the rectangle are at x and x +
     * width - 1. The top and bottom edges are at y and y + height - 1. The resulting rectangle
     * covers an area width pixels wide by height pixels tall. The rectangle is filled using the
     * graphics context's current color.
     * 
     * @author Interface & documentation has been defined by the Piccolo2D project
     * 
     * @param x
     *            the x coordinate of the rectangle to be filled.
     * @param y
     *            the y coordinate of the rectangle to be filled.
     * @param width
     *            the width of the rectangle to be filled.
     * @param height
     *            the height of the rectangle to be filled.
     */
    void fillRect(final double x, final double y, final double width, final double height);

    /**
     * Draws the outline of an oval. The result is a circle or ellipse that fits within the
     * rectangle specified by the x, y, width, and height arguments. The oval covers an area that is
     * width + 1 pixels wide and height + 1 pixels tall.
     * 
     * @author Interface & documentation has been defined by the Piccolo2D project
     * 
     * @param x
     *            the x coordinate of the upper left corner of the oval to be drawn.
     * @param y
     *            the y coordinate of the upper left corner of the oval to be drawn.
     * @param width
     *            the width of the oval to be drawn.
     * @param height
     *            the height of the oval to be drawn.
     */
    void drawOval(final double x, final double y, final double width, final double height);

    /**
     * Fills an oval bounded by the specified rectangle with the current color.
     * 
     * @author Interface & documentation has been defined by the Piccolo2D project
     * 
     * @param x
     *            the x coordinate of the upper left corner of the oval to be filled.
     * @param y
     *            the y coordinate of the upper left corner of the oval to be filled.
     * @param width
     *            the width of the oval to be filled.
     * @param height
     *            the height of the oval to be filled.
     */
    void fillOval(final double x, final double y, final double width, final double height);

    /**
     * Draws the outline of a circular or elliptical arc covering the specified rectangle.
     * 
     * The resulting arc begins at startAngle and extends for arcAngle degrees, using the current
     * color. Angles are interpreted such that 0 degrees is at the 3 o'clock position. A positive
     * value indicates a counter-clockwise rotation while a negative value indicates a clockwise
     * rotation.
     * 
     * The center of the arc is the center of the rectangle whose origin is (x, y) and whose size is
     * specified by the width and height arguments.
     * 
     * The resulting arc covers an area width + 1 pixels wide by height + 1 pixels tall.
     * 
     * The angles are specified relative to the non-square extents of the bounding rectangle such
     * that 45 degrees always falls on the line from the center of the ellipse to the upper right
     * corner of the bounding rectangle. As a result, if the bounding rectangle is noticeably longer
     * in one axis than the other, the angles to the start and end of the arc segment will be skewed
     * farther along the longer axis of the bounds.
     * 
     * @author Interface & documentation has been defined by the Piccolo2D project
     * 
     * @param x
     *            the x coordinate of the upper-left corner of the arc to be drawn.
     * @param y
     *            the y coordinate of the upper-left corner of the arc to be drawn.
     * @param width
     *            the width of the arc to be drawn.
     * @param height
     *            the height of the arc to be drawn.
     * @param startAngle
     *            the beginning angle.
     * @param extent
     *            the angular extent of the arc, relative to the start angle.
     */
    void drawArc(final double x, final double y, final double width, final double height,
            final double startAngle, final double extent);

    /**
     * Draws a filledArc with the options provided.
     * 
     * @author Interface & documentation has been defined by the Piccolo2D project
     * 
     * @param x
     *            the x coordinate of the upper-left corner of the arc to be filled.
     * @param y
     *            the y coordinate of the upper-left corner of the arc to be filled.
     * @param width
     *            the width of the arc to be filled.
     * @param height
     *            the height of the arc to be filled.
     * @param startAngle
     *            the beginning angle.
     * @param extent
     *            the angular extent of the arc, relative to the start angle.
     */
    void fillArc(final double x, final double y, final double width, final double height,
            final double startAngle, final double extent);

    /**
     * Draws an outlined round-cornered rectangle using this graphics context's current color. The
     * left and right edges of the rectangle are at x and x + width, respectively. The top and
     * bottom edges of the rectangle are at y and y + height.
     * 
     * @author Interface & documentation has been defined by the Piccolo2D project
     * 
     * @param x
     *            the x coordinate of the rectangle to be drawn.
     * @param y
     *            the y coordinate of the rectangle to be drawn.
     * @param width
     *            the width of the rectangle to be drawn.
     * @param height
     *            the height of the rectangle to be drawn.
     * @param arcWidth
     *            the horizontal diameter of the arc at the four corners.
     * @param arcHeight
     *            the vertical diameter of the arc at the four corners.
     */
    void drawRoundRect(final double x, final double y, final double width, final double height,
            final double arcWidth, final double arcHeight);

    /**
     * Fills the specified rounded corner rectangle with the current color. The left and right edges
     * of the rectangle are at x and x + width - 1, respectively. The top and bottom edges of the
     * rectangle are at y and y + height - 1.
     * 
     * @author Interface & documentation has been defined by the Piccolo2D project
     * 
     * @param x
     *            the x coordinate of the rectangle to be filled.
     * @param y
     *            the y coordinate of the rectangle to be filled.
     * @param width
     *            the width of the rectangle to be filled.
     * @param height
     *            the height of the rectangle to be filled.
     * @param arcWidth
     *            the horizontal diameter of the arc at the four corners.
     * @param arcHeight
     *            the vertical diameter of the arc at the four corners.
     */
    void fillRoundRect(final double x, final double y, final double width, final double height,
            final double arcWidth, final double arcHeight);

    /**
     * Draw a polygon from the specified double array of points.<br>
     * Derivative of {@link #drawPolyline(double[])}.
     * 
     * @author chsch
     * 
     * @param pts
     *            double array of points
     */
    void drawPolygon(final double[] pts);
    
    /**
     * Fill a polygon from the specified double array of points.
     * 
     * @author Interface & documentation has been defined by the Piccolo2D project
     * 
     * @param pts
     *            double array of points
     */
    void fillPolygon(final double[] pts);
    
    /**
     * Draw a polyline from the specified double array of points.
     * 
     * @author Interface & documentation has been defined by the Piccolo2D project
     * 
     * @param pts
     *            double array of points
     */
    void drawPolyline(final double[] pts);

    /**
     * Draws the provided AWT GeneralPath respecting the current AWT transform without any caching.
     * We need to assess whether this is OK w.r.t. the runtime performance.
     * 
     * @author chsch
     * 
     * @param gp
     *            path to draw
     */
    void drawGeneralPath(final GeneralPath gp);

    /**
     * See {@link java.awt.Graphics2D#draw(Shape)}!
     * 
     * @deprecated Method is deprecated as the position and size data cannot be scaled properly for
     *             general shapes.
     * 
     * @param s
     *            the <code>Shape</code> to be rendered
     */
    void draw(final Shape s);
    
    /**
     * See {@link java.awt.Graphics2D#fill(Shape)}!
     * 
     * @deprecated Method is deprecated as the position and size data cannot be scaled properly for
     *             general shapes.
     * 
     * @param s
     *            the <code>Shape</code> to be filled
     */
    void fill(final Shape s);
    
    
    void drawString(final String text, final int x, final int y);
    
    void setFont(FontData font) ;
}
