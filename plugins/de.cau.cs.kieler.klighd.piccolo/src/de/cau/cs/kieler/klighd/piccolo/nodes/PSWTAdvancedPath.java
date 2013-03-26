/*
 * Copyright (c) 2008-2011, Piccolo2D project, http://piccolo2d.org
 * Copyright (c) 1998-2008, University of Maryland
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 * and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * None of the name of the University of Maryland, the name of the Piccolo2D project, or the names of its
 * contributors may be used to endorse or promote products derived from this software without specific
 * prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
// SUPPRESS CHECKSTYLE PREVIOUS 30 Header
package de.cau.cs.kieler.klighd.piccolo.nodes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.krendering.LineCap;
import de.cau.cs.kieler.core.krendering.LineStyle;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.krendering.util.PolylineUtil;
import de.cau.cs.kieler.klighd.piccolo.util.RGBGradient;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PAffineTransformException;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolox.swt.SWTShapeManager;

/**
 * The {@code PSWTAdvancedPath} is a refinement of the Piccolo {@code PSWTPath}. Provides the
 * possibility to adjust the line width and the line style and can represent polygons.
 * <p>
 * Most of the implementation is copied from {@code edu.umd.cs.piccolox.swt.PSWTPath},
 * therefore the original copyright header is retained.
 * </p>
 * 
 * @author mri, chsch
 */
public class PSWTAdvancedPath extends PNode {

    /**
     * A property identifier leading to the approximated path if the path is a Bézier curve or to
     * itself otherwise. This approximated path is needed while computing the decorator rotations.
     */
    public static final String APPROXIMATED_PATH = "ApproximatedPath";

    private static final long serialVersionUID = 8034306769936734586L;

    private static final RGB DEFAULT_STROKE_PAINT = new RGB(0, 0, 0);

    private static final Rectangle2D.Float TEMP_RECTANGLE = new Rectangle2D.Float();
    private static final RoundRectangle2D.Float TEMP_ROUNDRECTANGLE = new RoundRectangle2D.Float();
    private static final Ellipse2D.Float TEMP_ELLIPSE = new Ellipse2D.Float();
    private static final Arc2D.Float TEMP_ARC = new Arc2D.Float();
    private static final double BOUNDS_TOLERANCE = 0.01;
    private static final BasicStroke BASIC_STROKE = new BasicStroke();
    
    private static final Map<Color, RGB> RGB_CACHE = Maps.newConcurrentMap(); 

    private int strokeAlpha = KlighdConstants.ALPHA_FULL_OPAQUE;
    private RGB strokePaint = DEFAULT_STROKE_PAINT;
    private RGBGradient strokePaintGradient = null;
    
    // at most one of the following is allowed to have a non-null value at any time!
    private int paintAlpha = KlighdConstants.ALPHA_FULL_OPAQUE;
    private RGB paint = null;
    private RGBGradient paintGradient = null;
    
    private RGB shadow = null;

    /** the line width for this path. */
    private double lineWidth = 1.0;
    /** the line style for this path. */
    private int lineStyle = SWT.LINE_SOLID;
    /** the line cap style for this path. */
    private int lineCapStyle = SWT.CAP_FLAT;

    private boolean updatingBoundsFromPath;
    private Shape origShape;
    private Shape shape;

    private PAffineTransform internalXForm;
    private AffineTransform inverseXForm;

    private double[] shapePts;

    private boolean isPolygon = false;
    private boolean isPolyline = false;
    private boolean isRoundedBendsPolyline = false;
    private boolean isSpline = false;

    /**
     * Creates a path representing the rectangle provided.
     * 
     * @param x
     *            left of rectangle
     * @param y
     *            top of rectangle
     * @param width
     *            width of rectangle
     * @param height
     *            height of rectangle
     * @return created rectangle
     */
    public static PSWTAdvancedPath createRectangle(final float x, final float y, final float width,
            final float height) {
        final PSWTAdvancedPath result = new PSWTAdvancedPath();
        result.setPathToRectangle(x, y, width, height);
        result.setPaint(Color.white);
        return result;
    }

    /**
     * Creates a path representing the rounded rectangle provided.
     * 
     * @param x
     *            left of rectangle
     * @param y
     *            top of rectangle
     * @param width
     *            width of rectangle
     * @param height
     *            height of rectangle
     * @param arcWidth
     *            width of the arc at the corners
     * @param arcHeight
     *            height of arc at the corners
     * @return created rounded rectangle
     */
    public static PSWTAdvancedPath createRoundRectangle(final float x, final float y,
            final float width, final float height, final float arcWidth, final float arcHeight) {
        final PSWTAdvancedPath result = new PSWTAdvancedPath();
        result.setPathToRoundRectangle(x, y, width, height, arcWidth, arcHeight);
        result.setPaint(Color.white);
        return result;
    }

    /**
     * Creates a path representing an ellipse that covers the rectangle provided.
     * 
     * @param x
     *            left of rectangle
     * @param y
     *            top of rectangle
     * @param width
     *            width of rectangle
     * @param height
     *            height of rectangle
     * @return created ellipse
     */
    public static PSWTAdvancedPath createEllipse(final float x, final float y, final float width,
            final float height) {
        final PSWTAdvancedPath result = new PSWTAdvancedPath();
        result.setPathToEllipse(x, y, width, height);
        result.setPaint(Color.white);
        return result;
    }

    /**
     * Creates a path representing an arc positioned at the coordinate provided with the dimensions,
     * angular start and angular extent provided.
     * 
     * @param x
     *            left of the arc
     * @param y
     *            top of the arc
     * @param width
     *            width of the arc
     * @param height
     *            height of the arc
     * @param angStart
     *            angular start of the arc
     * @param angExtend
     *            angular extent of the arc
     * @return created arc
     */
    public static PSWTAdvancedPath createArc(final float x, final float y, final float width,
            final float height, final float angStart, final float angExtend) {
        final PSWTAdvancedPath result = new PSWTAdvancedPath();
        result.setPathToArc(x, y, width, height, angStart, angExtend);
        result.setPaint(Color.white);
        return result;
    }

    /**
     * Creates a path for the spline for the given points.
     * 
     * @author chsch
     * 
     * @param points
     *            array of points for the point lines
     * @return created spline for the given points
     */
    public static PSWTAdvancedPath createSpline(final Point2D[] points) {
        final PSWTAdvancedPath result = new PSWTAdvancedPath();
        result.setPathToSpline(points);
        // chsch: do not set the paint of a line as this will impair the
        //  selection determination (using #intersects(), see below)
        // result.setPaint(Color.white);
        return result;
    }

    /**
     * Creates a path for the poly-line with rounded bend points for the given points.
     * 
     * @author chsch
     *
     * @param points
     *            array of points for the point lines
     * @param bendRadius
     *            the radius of the bend points
     * 
     * @return created polyline with rounded bend points for the given points
     */
    public static PSWTAdvancedPath createRoundedBendPolyline(final Point2D[] points,
            final float bendRadius) {
        final PSWTAdvancedPath result = new PSWTAdvancedPath();
        result.setPathToRoundedBendPolyline(points, bendRadius);
        // chsch: do not set the paint of a line as this will impair the
        //  selection determination (using #intersects(), see below)
        // result.setPaint(Color.white);
        return result;
    }

    /**
     * Creates a path for the poly-line for the given points.
     * 
     * @param points
     *            array of points for the point lines
     * 
     * @return created polyline for the given points
     */
    public static PSWTAdvancedPath createPolyline(final Point2D[] points) {
        final PSWTAdvancedPath result = new PSWTAdvancedPath();
        result.setPathToPolyline(points);
        // chsch: do not set the paint of a line as this will impair the
        //  selection determination (using #intersects(), see below)
        // result.setPaint(Color.white);
        return result;
    }

    /**
     * Creates a path for the poly-line for the given points.
     * 
     * @param xp
     *            array of x components of the points of the poly-lines
     * @param yp
     *            array of y components of the points of the poly-lines
     * 
     * @return created poly-line for the given points
     */
    public static PSWTAdvancedPath createPolyline(final float[] xp, final float[] yp) {
        final PSWTAdvancedPath result = new PSWTAdvancedPath();
        result.setPathToPolyline(xp, yp);
        // chsch: do not set the paint of a line as this will impair the
        //  selection determination (using #intersects(), see below)
        // result.setPaint(Color.white);
        return result;
    }

    /**
     * Creates a path for the polygon for the given points.
     * 
     * @param points
     *            array of points for the point lines
     * 
     * @return created polygon for the given points
     */
    public static PSWTAdvancedPath createPolygon(final Point2D[] points) {
        final PSWTAdvancedPath result = new PSWTAdvancedPath();
        result.setPathToPolygon(points);
        result.setPaint(Color.white);
        return result;
    }

    /**
     * Creates a path for the polygon for the given points.
     * 
     * @param xp
     *            array of x components of the points of the polygon
     * @param yp
     *            array of y components of the points of the polygon
     * 
     * @return created polygon for the given points
     */
    public static PSWTAdvancedPath createPolygon(final float[] xp, final float[] yp) {
        final PSWTAdvancedPath result = new PSWTAdvancedPath();
        result.setPathToPolygon(xp, yp);
        result.setPaint(Color.white);
        return result;
    }

    /**
     * Creates an empty PSWTAdvancedPath.
     */
    public PSWTAdvancedPath() {
        strokePaint = DEFAULT_STROKE_PAINT;
    }

    /**
     * Creates a SWTAdvancedPath in the given shape with the default paint and stroke.
     * 
     * @param aShape
     *            the desired shape
     */
    public PSWTAdvancedPath(final Shape aShape) {
        this();
        setShape(aShape);
    }

    /**
     * Returns the paint to use when drawing the stroke of the shape.
     * 
     * @return path's stroke paint
     */
    public RGB getStrokePaint() {
        return strokePaint;
    }

    /**
     * Returns the stroke paint gradient used while painting this node. This value may be null.
     * 
     * @return the stroke paint gradient used while painting this node.
     */
    public RGBGradient getStrokePaintGradient() {
        return this.paintGradient;
    }

    /**
     * Sets the paint to use when drawing the stroke of the shape.
     * 
     * @param strokeColor
     *            new stroke color
     */
    public void setStrokeColor(final RGB strokeColor) {
        final RGB old = strokePaint;
        strokePaint = strokeColor;
        strokePaintGradient = null;
        invalidatePaint();
        firePropertyChange(PPath.PROPERTY_CODE_STROKE_PAINT, PPath.PROPERTY_STROKE_PAINT, old,
                strokePaint);
    }

    /**
     * Set the alpha used to draw the stroke of the shape.
     * 
     * @param alpha
     *            the alpha value
     */
    public void setStrokeAlpha(final int alpha) {
        this.strokeAlpha = alpha;
    }
    
    /**
     * Sets the paint to use when drawing the stroke of the shape.
     * 
     * @param strokeGradient
     *            new stroke color
     */
    public void setStrokeColor(final RGBGradient strokeGradient) {
        if (strokePaintGradient != null && strokePaintGradient.equals(strokeGradient)) {
            return;
        }
        Object oldPaint = null;
        if (strokePaintGradient != null) {
            oldPaint = strokePaintGradient;   
        } else if (strokePaint != null) {
            oldPaint = strokePaint;
            strokePaint = null;
        }
        strokePaintGradient = strokeGradient;
        invalidatePaint();
        firePropertyChange(PPath.PROPERTY_CODE_STROKE_PAINT, PPath.PROPERTY_STROKE_PAINT, oldPaint,
                strokePaintGradient);
    }

    /**
     * Returns the paint used while painting this node. This value may be null.
     * 
     * @return the paint used while painting this node.
     */
    public RGB getSWTPaint() {
        return this.paint;
    }

    /**
     * Returns the paint gradient used while painting this node. This value may be null.
     * 
     * @return the paint gradient used while painting this node.
     */
    public RGBGradient getSWTPaintGradient() {
        return this.paintGradient;
    }

    /**
     * Set the paint used to paint this node, which may be null.
     * 
     * @param newPaint paint that this node should use when painting itself.
     */
    public void setPaint(final RGB newPaint) {
        if (paint != null && paint.equals(newPaint)) {
            return;
        }
        Object oldPaint = null;
        if (paintGradient != null) {
            oldPaint = paintGradient;
            paintGradient = null;
        } else if (paint != null) {
            oldPaint = paint;
        }
        paint = newPaint;
        invalidatePaint();
        firePropertyChange(PROPERTY_CODE_PAINT, PROPERTY_PAINT, oldPaint, paint);
    }
    
    /**
     * Set the alpha used to paint this node.
     * 
     * @param alpha
     *            the alpha value
     */
    public void setPaintAlpha(final int alpha) {
        this.paintAlpha = alpha;
    }
    
    /**
     * Set the paint used to paint this node, which may be null.
     * 
     * @param newPaint paint that this node should use when painting itself.
     */
    public void setPaint(final RGBGradient newPaint) {
        if (paintGradient != null && paintGradient.equals(newPaint)) {
            return;
        }
        Object oldPaint = null;
        if (paintGradient != null) {
            oldPaint = paintGradient;   
        } else if (paint != null) {
            oldPaint = paint;
            paint = null;
        }
        paintGradient = newPaint;
        invalidatePaint();
        firePropertyChange(PROPERTY_CODE_PAINT, PROPERTY_PAINT, oldPaint, paint);
    }
    
    /**
     * Set the paint used to paint this node, which may be null.
     * 
     * @param newPaint paint that this node should use when painting itself.
     */
    public void setPaint(final Color newPaint) {
        RGB rgb = RGB_CACHE.get(newPaint);
        if (rgb == null) {
            rgb = new RGB(newPaint.getRed(), newPaint.getGreen(), newPaint.getBlue());
            RGB_CACHE.put(newPaint, rgb);
        }
        this.setPaint(rgb);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void internalUpdateBounds(final double x, final double y, final double width,
            final double height) {
        if (updatingBoundsFromPath) {
            return;
        }
        if (origShape == null) {
            return;
        }

        final Rectangle2D pathBounds = origShape.getBounds2D();

        if (Math.abs(x - pathBounds.getX()) / x < BOUNDS_TOLERANCE
                && Math.abs(y - pathBounds.getY()) / y < BOUNDS_TOLERANCE
                && Math.abs(width - pathBounds.getWidth()) / width < BOUNDS_TOLERANCE
                && Math.abs(height - pathBounds.getHeight()) / height < BOUNDS_TOLERANCE) {
            return;
        }

        if (internalXForm == null) {
            internalXForm = new PAffineTransform();
        }
        internalXForm.setToIdentity();
        internalXForm.translate(x, y);
        internalXForm.scale(width / pathBounds.getWidth(), height / pathBounds.getHeight());
        internalXForm.translate(-pathBounds.getX(), -pathBounds.getY());

        try {
            inverseXForm = internalXForm.createInverse();
        } catch (final Exception e) {
            throw new PAffineTransformException("unable to invert transform", internalXForm);
        }
    }

    /**
     * Returns true if path crosses the provided bounds. Takes visibility of path into account.
     * 
     * @param aBounds
     *            bounds being tested for intersection
     * @return true if path visibly crosses bounds
     */
    public boolean intersects(final Rectangle2D aBounds) {
        if (super.intersects(aBounds)) {
            final Rectangle2D srcBounds;
            if (internalXForm == null) {
                srcBounds = aBounds;
            } else {
                srcBounds = new PBounds(aBounds);
                internalXForm.inverseTransform(srcBounds, srcBounds);
            }

            if (getPaint() != null && shape.intersects(srcBounds)) {
                return true;
            } else if (strokePaint != null) {
                return BASIC_STROKE.createStrokedShape(shape).intersects(srcBounds);
            }
        }
        return false;
    }

    /**
     * Recalculates the path's bounds by examining it's associated shape.
     */
    public void updateBoundsFromPath() {
        updatingBoundsFromPath = true;

        if (origShape == null) {
            resetBounds();
        } else {
            final Rectangle2D b = origShape.getBounds2D();
            // the original code creates more problems than it solves here
            super.setBounds(b.getX(), b.getY(),
                    b.getWidth() == 0 ? BOUNDS_TOLERANCE : b.getWidth(),
                    b.getHeight() == 0 ? BOUNDS_TOLERANCE : b.getHeight());
        }
        updatingBoundsFromPath = false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        KlighdSWTGraphics g2 = (KlighdSWTGraphics) paintContext.getGraphics();
        g2.setLineWidth(lineWidth);
        g2.setLineStyle(lineStyle);
        g2.setLineCap(lineCapStyle);

        if (internalXForm != null) {
            g2.transform(internalXForm);
        }

        if (!isPolyline && shadow != null) {
            drawShadow(g2);
        }
        final int currentAlpha = g2.getAlpha();
        final float currentAlphaFloat = (float) currentAlpha;
        
        RGB p = getSWTPaint();
        if (p != null) {
            g2.setAlpha((int) (paintAlpha * (currentAlphaFloat / KlighdConstants.ALPHA_FULL_OPAQUE)));
            g2.setBackground(p);
            fillShape(g2);
        }
        
        RGBGradient pg = getSWTPaintGradient();
        if (pg != null && g2 instanceof KlighdSWTGraphics) {
            ((KlighdSWTGraphics) g2).setBackgroundPattern(pg, getBounds());            
            fillShape(g2);
        }

        if (strokePaint != null) {
            g2.setAlpha((int) (strokeAlpha * (currentAlphaFloat / KlighdConstants.ALPHA_FULL_OPAQUE)));
            g2.setColor(strokePaint);
            drawShape(g2);
        }

        if (strokePaintGradient != null && g2 instanceof KlighdSWTGraphics) {
            Rectangle2D bounds = getBounds();
            ((KlighdSWTGraphics) g2).setPattern(
                    strokePaintGradient,
                    new Rectangle2D.Double(bounds.getMinX() - 1, bounds.getMinY() - 1, bounds
                            .getMaxX() + 1, bounds.getMaxY() + 2));
            drawShape(g2);
        }

        if (inverseXForm != null) {
            g2.transform(inverseXForm);
        }

        g2.setAlpha(currentAlpha);
        g2.setLineWidth(1.0);
        g2.setLineStyle(SWT.LINE_SOLID);
        g2.setLineCap(SWT.CAP_FLAT);
    }
    
    
    /**
     * Draws the shadow of the current shape.
     * 
     * @param g2 the {@link SWTGraphics2D} to draw on.
     */
    private void drawShadow(final KlighdSWTGraphics g2) {
        // the amount of pixels the shadow will cover in horizontal and vertical direction
        final int shadowExtend = 8;

        final int currentAlpha = g2.getAlpha();
        
        // the alpha value of the particular shadow shapes
        //  note that the alpha values of stacked shapes will kind of accumulate
        final float shadowAlpha = 25f;
        
        // determine the movement of the shape coordinates by means of an affine transform
        AffineTransform t = g2.getTransform();
        AffineTransform tc = new AffineTransform(t);
        tc.translate(shadowExtend, shadowExtend);
        
        // configure the graphics layer
        g2.setBackground(this.shadow);
        g2.setLineWidth(0);
        g2.setAlpha((int) ((float) currentAlpha * shadowAlpha / KlighdConstants.ALPHA_FULL_OPAQUE));
        
        // draw a bunch of shape copies, each of them is moved a bit towards the original position
        for (int i = 0; i < shadowExtend; i++) {
            g2.setTransform(tc);
            fillShape(g2);
            tc.translate(-1, -1);
        }

        // re-set the original transform to the graphics layer
        //  and draw a fresh white background on the shadow shape stack at the original position
        // Note, that we accept the incompatibility of transparent shapes and shadow, and thus
        //  dismiss the transparency.
        g2.setTransform(t);
        g2.setLineWidth(1);
        g2.setAlpha(KlighdConstants.ALPHA_FULL_OPAQUE);            
        g2.setBackground(KlighdConstants.WHITE);
        fillShape(g2);
        
        // reset the manipulated settings
        g2.setAlpha(currentAlpha);
        g2.setLineWidth(lineWidth);
    }

    // CHECKSTYLEOFF MagicNumber

    @SuppressWarnings("deprecation")
    private void drawShape(final KlighdSWTGraphics g2) { //SWTGraphics2D g2) {
        final double lw = g2.getLineWidth();
        if (shape instanceof Rectangle2D) {
            g2.drawRect(shapePts[0] + lw / 2, shapePts[1] + lw / 2, shapePts[2] - lw, shapePts[3]
                    - lw);
        } else if (shape instanceof Ellipse2D) {
            g2.drawOval(shapePts[0] + lw / 2, shapePts[1] + lw / 2, shapePts[2] - lw, shapePts[3]
                    - lw);
        } else if (shape instanceof Arc2D) {
            g2.drawArc(shapePts[0] + lw / 2, shapePts[1] + lw / 2, shapePts[2] - lw, shapePts[3]
                    - lw, shapePts[4], shapePts[5]);
        } else if (shape instanceof RoundRectangle2D) {
            g2.drawRoundRect(shapePts[0] + lw / 2, shapePts[1] + lw / 2, shapePts[2] - lw,
                    shapePts[3] - lw, shapePts[4], shapePts[5]);
        } else if (shape instanceof GeneralPath) {
            if (isPolygon) {
                g2.drawPolygon(shapePts);
            } else if (isPolyline) {
                g2.drawPolyline(shapePts);
            } else if (isRoundedBendsPolyline || isSpline) {
                g2.drawGeneralPath((GeneralPath) shape);
            }
        } else {
            // chsch: executing this branch should be avoided under all circumstances
            //  as it most likely results in calling SWTGraphics2D#drawPath(Path)
            //  that in turn is a kind of fall back method, which doesn't correctly handle
            //  the floating point 2 integer (awtToSwt) conversion! 
            g2.draw(shape);
        }
        
    }

    @SuppressWarnings("deprecation")
    private void fillShape(final KlighdSWTGraphics g2) { // SWTGraphics2D g2) {
        final double lw = g2.getLineWidth();
        if (shape instanceof Rectangle2D) {
            g2.fillRect(shapePts[0] + lw / 2, shapePts[1] + lw / 2, shapePts[2] - lw, shapePts[3]
                    - lw);
        } else if (shape instanceof Ellipse2D) {
            g2.fillOval(shapePts[0] + lw / 2, shapePts[1] + lw / 2, shapePts[2] - lw, shapePts[3]
                    - lw);
        } else if (shape instanceof Arc2D) {
            g2.fillArc(shapePts[0] + lw / 2, shapePts[1] + lw / 2, shapePts[2] - lw, shapePts[3]
                    - lw, shapePts[4], shapePts[5]);
        } else if (shape instanceof RoundRectangle2D) {
            g2.fillRoundRect(shapePts[0] + lw / 2, shapePts[1] + lw / 2, shapePts[2] - lw,
                    shapePts[3] - lw, shapePts[4], shapePts[5]);
        } else if (isPolygon) { // shape instanceof GeneralPath will be satisfied!
            g2.fillPolygon(shapePts);
        } else {
            // chsch: executing this branch should be avoided under all circumstances
            //  as it most likely results in calling SWTGraphics2D#fillPath(Path)
            //  that in turn is a kind of fall back method, which doesn't correctly handle
            //  the floating point 2 integer (awtToSwt) conversion! 
            g2.fill(shape);
        }
    }

    // CHECKSTYLEON Magic Number

    /**
     * Changes the underlying shape of this PSWTPath.
     * 
     * @param newShape
     *            new associated shape of this PSWTPath
     */
    public void setShape(final Shape newShape) {
        shape = cloneShape(newShape);
        origShape = shape;
        updateShapePoints(newShape);

        firePropertyChange(PPath.PROPERTY_CODE_PATH, PPath.PROPERTY_PATH, null, shape);
        updateBoundsFromPath();
        invalidatePaint();
    }

    /**
     * Returns the points of the shape.
     * 
     * @return the points
     */
    public Point2D[] getShapePoints() {
        Point2D[] points = new Point2D[shapePts.length / 2];
        for (int i = 0; i < points.length; ++i) {
            points[i] = new Point2D.Double(shapePts[2 * i], shapePts[2 * i + 1]);
        }
        return points;
    }

    /**
     * Updates the internal points used to draw the shape.
     * 
     * @param aShape
     *            shape to read points from
     */
    // CHECKSTYLEOFF MagicNumber
    public void updateShapePoints(final Shape aShape) {
        if (aShape instanceof Rectangle2D) {
            if (shapePts == null || shapePts.length < 4) {
                shapePts = new double[4];
            }

            shapePts[0] = ((Rectangle2D) shape).getX();
            shapePts[1] = ((Rectangle2D) shape).getY();
            shapePts[2] = ((Rectangle2D) shape).getWidth();
            shapePts[3] = ((Rectangle2D) shape).getHeight();
        } else if (aShape instanceof Ellipse2D) {
            if (shapePts == null || shapePts.length < 4) {
                shapePts = new double[4];
            }

            shapePts[0] = ((Ellipse2D) shape).getX();
            shapePts[1] = ((Ellipse2D) shape).getY();
            shapePts[2] = ((Ellipse2D) shape).getWidth();
            shapePts[3] = ((Ellipse2D) shape).getHeight();
        } else if (aShape instanceof Arc2D) {
            if (shapePts == null || shapePts.length < 6) {
                shapePts = new double[6];
            }

            shapePts[0] = ((Arc2D) shape).getX();
            shapePts[1] = ((Arc2D) shape).getY();
            shapePts[2] = ((Arc2D) shape).getWidth();
            shapePts[3] = ((Arc2D) shape).getHeight();
            shapePts[4] = ((Arc2D) shape).getAngleStart();
            shapePts[5] = ((Arc2D) shape).getAngleExtent();
        } else if (aShape instanceof RoundRectangle2D) {
            if (shapePts == null || shapePts.length < 6) {
                shapePts = new double[6];
            }

            shapePts[0] = ((RoundRectangle2D) shape).getX();
            shapePts[1] = ((RoundRectangle2D) shape).getY();
            shapePts[2] = ((RoundRectangle2D) shape).getWidth();
            shapePts[3] = ((RoundRectangle2D) shape).getHeight();
            shapePts[4] = ((RoundRectangle2D) shape).getArcWidth();
            shapePts[5] = ((RoundRectangle2D) shape).getArcHeight();
        } else {
            shapePts = SWTShapeManager.shapeToPolyline(shape);
        }
    }

    // CHECKSTYLEON MagicNumber

    /**
     * Clone's the shape provided.
     * 
     * @param aShape
     *            shape to be cloned
     * 
     * @return a cloned version of the provided shape
     */
    public Shape cloneShape(final Shape aShape) {
        if (aShape instanceof Rectangle2D) {
            return new PBounds((Rectangle2D) aShape);
        } else if (aShape instanceof Ellipse2D) {
            final Ellipse2D e2 = (Ellipse2D) aShape;
            return new Ellipse2D.Double(e2.getX(), e2.getY(), e2.getWidth(), e2.getHeight());
        } else if (aShape instanceof Arc2D) {
            final Arc2D a2 = (Arc2D) aShape;
            return new Arc2D.Double(a2.getX(), a2.getY(), a2.getWidth(), a2.getHeight(),
                    a2.getAngleStart(), a2.getAngleExtent(), a2.getArcType());
        } else if (aShape instanceof RoundRectangle2D) {
            final RoundRectangle2D r2 = (RoundRectangle2D) aShape;
            return new RoundRectangle2D.Double(r2.getX(), r2.getY(), r2.getWidth(), r2.getHeight(),
                    r2.getArcWidth(), r2.getArcHeight());
        } else if (aShape instanceof Line2D) {
            final Line2D l2 = (Line2D) aShape;
            return new Line2D.Double(l2.getP1(), l2.getP2());
        } else {
            final GeneralPath aPath = new GeneralPath();
            aPath.append(aShape, false);
            return aPath;
        }
    }

    /**
     * Resets the path to a rectangle with the dimensions and position provided.
     * 
     * @param x
     *            left of the rectangle
     * @param y
     *            top of te rectangle
     * @param width
     *            width of the rectangle
     * @param height
     *            height of the rectangle
     */
    public void setPathToRectangle(final float x, final float y, final float width,
            final float height) {
        TEMP_RECTANGLE.setFrame(x, y, width, height);
        setShape(TEMP_RECTANGLE);
    }

    /**
     * Resets the path to a rectangle with the dimensions and position provided.
     * 
     * @param x
     *            left of the rectangle
     * @param y
     *            top of te rectangle
     * @param width
     *            width of the rectangle
     * @param height
     *            height of the rectangle
     * @param arcWidth
     *            width of arc in the corners of the rectangle
     * @param arcHeight
     *            height of arc in the corners of the rectangle
     */
    public void setPathToRoundRectangle(final float x, final float y, final float width,
            final float height, final float arcWidth, final float arcHeight) {
        TEMP_ROUNDRECTANGLE.setRoundRect(x, y, width, height, arcWidth, arcHeight);
        setShape(TEMP_ROUNDRECTANGLE);
    }

    /**
     * Resets the path to an ellipse positioned at the coordinate provided with the dimensions
     * provided.
     * 
     * @param x
     *            left of the ellipse
     * @param y
     *            top of the ellipse
     * @param width
     *            width of the ellipse
     * @param height
     *            height of the ellipse
     */
    public void setPathToEllipse(final float x, final float y, final float width, final float height) {
        TEMP_ELLIPSE.setFrame(x, y, width, height);
        setShape(TEMP_ELLIPSE);
    }

    /**
     * Resets the path to an arc positioned at the coordinate provided with the dimensions, angular
     * start and angular extent provided.
     * 
     * @param x
     *            left of the arc
     * @param y
     *            top of the arc
     * @param width
     *            width of the arc
     * @param height
     *            height of the arc
     * @param angStart
     *            angular start of the arc
     * @param angExtend
     *            angular extent of the arc
     */
    public void setPathToArc(final float x, final float y, final float width, final float height,
            final float angStart, final float angExtend) {
        TEMP_ARC.setArc(x, y, width, height, angStart, angExtend, Arc2D.OPEN);
        setShape(TEMP_ARC);
    }

    /**
     * @see de.cau.cs.kieler.core.model.gmf.figures.SplineConnection#outlineShape
     * 
     * Sets the path to a sequence of segments described by the points.
     * 
     * @param points
     *            points to that lie along the generated path
     */
    public void setPathToSpline(final Point2D[] points) {
        final GeneralPath path = new GeneralPath();
        path.reset();
        int size = points.length;
        if (size < 1) {
            return; // nothing to do
        }
        path.moveTo((float) points[0].getX(), (float) points[0].getY());

        // draw cubic sections
        int i = 1;
        for (; i < size - 2; i += 3) { // SUPPRESS CHECKSTYLE MagicNumber
            path.curveTo((float) points[i].getX(), (float) points[i].getY(),
                    (float) points[i + 1].getX(), (float) points[i + 1].getY(),
                    (float) points[i + 2].getX(), (float) points[i + 2].getY());
        }

        // draw remaining sections, won't happen if 'Graphviz Dot' was applied
        // size-1: one straight line
        // size-2: one quadratic
        switch (size - i) {
        case 1:
            path.lineTo((float) points[i].getX(), (float) points[i].getY());
            break;
        case 2:
            path.quadTo((float) points[i].getX(), (float) points[i].getY(),
                    (float) points[i + 1].getX(), (float) points[i + 1].getY());
            break;
        default:
            // this should not happen
            break;
        }
        
        // supplement (chsch):
        PSWTAdvancedPath approxPath = new PSWTAdvancedPath();
        KVectorChain chain = new KVectorChain();
        for (Point2D p : points) {
            chain.add(p.getX(), p.getY());
        }
        chain = KielerMath.approximateSpline(chain);
        ArrayList<Point2D> approxPoints = new ArrayList<Point2D>(points.length);
        for (KVector v : chain) {
            approxPoints.add(new Point2D.Double(v.x, v.y));
        }
        approxPath.setPathToPolyline(approxPoints.toArray(new Point2D.Double[points.length]));
        this.addAttribute(APPROXIMATED_PATH, approxPath);

        // this operation finally integrates the path and fires the change listeners
        setShape(path);
        isSpline = true;
    }

    /**
     * Sets the path to a sequence of segments described by the points.
     * 
     * @param points
     *            points to that lie along the generated path
     * @param bendRadius
     *            the radius of the bend points
     */
    public void setPathToRoundedBendPolyline(final Point2D[] points, final float bendRadius) {
        final GeneralPath path = new GeneralPath();
        path.reset();

        PolylineUtil.createRoundedBendPoints(path, points, bendRadius, this);
        
        // supplement (chsch):
        this.addAttribute(APPROXIMATED_PATH, this);

        // this operation finally integrates the path and fires the change listeners
        setShape(path);
        isRoundedBendsPolyline = true;
    }

    /**
     * Sets the path to a sequence of segments described by the points.
     * 
     * @param points
     *            points to that lie along the generated path
     */
    public void setPathToPolyline(final Point2D[] points) {
        if (points.length == 0) {
            return;
        }
        
        final GeneralPath path = new GeneralPath();
        path.reset();
        path.moveTo((float) points[0].getX(), (float) points[0].getY());
        for (int i = 1; i < points.length; i++) {
            path.lineTo((float) points[i].getX(), (float) points[i].getY());
        }

        // supplement (chsch):
        this.addAttribute(APPROXIMATED_PATH, this);

        // this operation finally integrates the path and fires the change listeners
        setShape(path);
        isPolyline = true;
    }

    // CHECKSTYLEOFF MagicNumber
    
    /**
     * Sets the path to a sequence of segments described by the point components provided.
     * 
     * @param xp
     *            the x components of the points along the path
     * @param yp
     *            the y components of the points along the path
     */
    public void setPathToPolyline(final float[] xp, final float[] yp) {
        final GeneralPath path = new GeneralPath();
        path.reset();
        path.moveTo(xp[0], yp[0]);
        for (int i = 1; i < xp.length; i++) {
            path.lineTo(xp[i], yp[i]);
        }
        setShape(path);
        isPolyline = true;
    }

    /**
     * Sets the path to a sequence of segments described by the points.
     * 
     * @param points
     *            points to that lie along the generated path
     */
    public void setPathToPolygon(final Point2D[] points) {
        final GeneralPath path = new GeneralPath();
        path.reset();
        path.moveTo((float) points[0].getX(), (float) points[0].getY());
        for (int i = 1; i < points.length; i++) {
            path.lineTo((float) points[i].getX(), (float) points[i].getY());
        }
        path.closePath();
        setShape(path);
        isPolygon = true;
    }

    /**
     * Sets the path to a sequence of segments described by the point components provided.
     * 
     * @param xp
     *            the x components of the points along the path
     * @param yp
     *            the y components of the points along the path
     */
    public void setPathToPolygon(final float[] xp, final float[] yp) {
        final GeneralPath path = new GeneralPath();
        path.reset();
        path.moveTo(xp[0], yp[0]);
        for (int i = 1; i < xp.length; i++) {
            path.lineTo(xp[i], yp[i]);
        }
        path.closePath();
        setShape(path);
        isPolygon = true;
    }

    /**
     * Return the center of this SWT path node, based on its bounds.
     * 
     * @return the center of this SWT path node, based on its bounds
     */
    public Point2D getCenter() {
        PBounds bounds = getBoundsReference();
        return new Point2D.Double(bounds.x + (bounds.width / 2.0), bounds.y + (bounds.height / 2.0));
    }

    /**
     * Sets the line width of the path.
     * 
     * @param width
     *            the line width
     */
    public void setLineWidth(final double width) {
        lineWidth = width;
    }

    /**
     * Returns the line width of the path.
     * 
     * @return the line width
     */
    public double getLineWidth() {
        return lineWidth;
    }

    /**
     * Sets the line style for this path.
     * 
     * @param newLineStyle
     *            the line style
     */
    public void setLineStyle(final LineStyle newLineStyle) {
        switch (newLineStyle) {
        case SOLID:
            lineStyle = SWT.LINE_SOLID;
            break;
        case DASH:
            lineStyle = SWT.LINE_DASH;
            break;
        case DOT:
            lineStyle = SWT.LINE_DOT;
            break;
        case DASHDOT:
            lineStyle = SWT.LINE_DASHDOT;
            break;
        case DASHDOTDOT:
            lineStyle = SWT.LINE_DASHDOTDOT;
            break;
        }
    }

    /**
     * Returns the line style of the path.
     * 
     * @return the line style
     */
    public LineStyle getLineStyle() {
        switch (lineStyle) {
        case SWT.LINE_DASH:
            return LineStyle.DASH;
        case SWT.LINE_DOT:
            return LineStyle.DOT;
        case SWT.LINE_DASHDOT:
            return LineStyle.DASHDOT;
        case SWT.LINE_DASHDOTDOT:
            return LineStyle.DASHDOTDOT;
        case SWT.LINE_SOLID:
        default:
            return LineStyle.SOLID;
        }
    }
    
    /**
     * Sets the line cap style for this path.
     * 
     * @param newLineCapStyle
     *            the line cap style
     */
    public void setLineCapStyle(final LineCap newLineCapStyle) {
        switch (newLineCapStyle) {
        case CAP_FLAT:
            lineCapStyle = SWT.CAP_FLAT;
            break;
        case CAP_ROUND:
            lineCapStyle = SWT.CAP_ROUND;
            break;
        case CAP_SQUARE:
            lineCapStyle = SWT.CAP_SQUARE;
            break;
        }
    }

    /**
     * Returns the line style of the path.
     * 
     * @return the line style
     */
    public LineCap getLineCapStyle() {
        switch (lineCapStyle) {
        case SWT.CAP_ROUND:
            return LineCap.CAP_ROUND;
        case SWT.CAP_SQUARE:
            return LineCap.CAP_SQUARE;
        case SWT.CAP_FLAT:
        default:
            return LineCap.CAP_FLAT;
        }
    }
    
    /**
     * Sets the shadow color for this path. Note that this definition will be ignored for non-closed
     * shapes, i.e. polylines and curves.
     * 
     * @param color
     *            the color of the attached shadow
     */
    public void setShadow(final RGB color) {
        this.shadow = color;
    }

}
