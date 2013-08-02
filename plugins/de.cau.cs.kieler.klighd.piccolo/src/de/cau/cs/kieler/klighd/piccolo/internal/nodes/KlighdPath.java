/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Map;

import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.RGB;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.util.PolylineUtil;
import de.cau.cs.kieler.klighd.piccolo.internal.util.RGBGradient;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolo.util.PPickPath;

/**
 * The KLighD-specific {@link PNode} implementation for displaying primitive figures.<br>
 * It is inspired by the Piccolo2D {@link edu.umd.cs.piccolox.swt.PSWTPath PSWTPath} and
 * is tailored/extended to those features required by KLighD.<br>
 * <br>
 * The stroke lines of closed figures like rectangles, ellipses, and arcs (although arcs are usually
 * not closed), i.e. those whose size is determined by a rectangular bounding box, do not violate
 * the bounds of those figures. Instead they get wider towards the center of the figure when the
 * line width increases. This behavior is realized in {@link #updateShape()}.<br>
 * <br>
 * This, however, does not hold for polylines and polygons as their covered area is determined by
 * the particular line points.<br>
 * <br>
 * <b>Note:</b> All <code>invalidate...</code> and <code>repaint</code> calls are deactivated in
 * order to avoid superfluous repaint activities. The repaint events are fired by
 * {@link #addChild(PNode)}/{@link #removeChild(PNode)} in case of rendering changes, by
 * {@link #setBounds(double, double, double, double)} in case of layout changes, and in case of pure
 * style changes by the {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement} rendering
 * controllers (
 * {@link de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController
 * #updateStyles() AbstractKGERenderingController#updateStyles()}) after all rendering and style
 * changes are performed.
 * 
 * @author mri, chsch
 */
public class KlighdPath extends PNode {

    private static final long serialVersionUID = 8034306769936734586L;

    private static final RGB DEFAULT_STROKE_PAINT = new RGB(0, 0, 0);

    private static final Map<Color, RGB> RGB_CACHE = Maps.newConcurrentMap();

    private LineAttributes lineAttributes = new LineAttributes(1f);
    private BasicStroke stroke = new BasicStroke();

    private int strokeAlpha = KlighdConstants.ALPHA_FULL_OPAQUE;
    private RGB strokePaint = DEFAULT_STROKE_PAINT;
    private RGBGradient strokePaintGradient = null;

    private int paintAlpha = KlighdConstants.ALPHA_FULL_OPAQUE;
    // at most one of the following is allowed to have a non-null value at any time!
    private RGB paint = null;
    private RGBGradient paintGradient = null;

    // the amount of pixels the shadow will cover in horizontal and vertical direction
    private final int shadowExtend = 8;
    private RGB shadow = null;

    // default initialization that avoids null pointer faults in case of failing setPathTo... calls
    private Shape origShape = new Rectangle2D.Float();
    private Shape shape;

    
    // A field to keep the list of points line points in mind. They are required while determining
    // the rotation of edge decorators. By remembering them a re-extraction from the {@link Path2D
    // Path2D} is avoided.
    private Point2D[] linePoints;

    // These deactivated internal affine transforms are kept here and later on in the code
    //  for the potential case they are be required in future for realizing a certain feature.
    // The deactivated statements illustrate how such transforms are employed correctly in
    //  PNode implementations.
    //
    // private PAffineTransform internalXForm;
    // private AffineTransform inverseXForm;

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
    public static KlighdPath createRectangle(final float x, final float y, final float width,
            final float height) {
        final KlighdPath result = new KlighdPath();
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
    public static KlighdPath createRoundRectangle(final float x, final float y,
            final float width, final float height, final float arcWidth, final float arcHeight) {
        final KlighdPath result = new KlighdPath();
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
    public static KlighdPath createEllipse(final float x, final float y, final float width,
            final float height) {
        final KlighdPath result = new KlighdPath();
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
     * @param type
     *            one of the constants {@link Arc2D#OPEN}, {@link Arc2D#CHORD}, and
     *            {@link Arc2D#PIE}
     * @return created arc
     */
    public static KlighdPath createArc(final float x, final float y, final float width,
            final float height, final float angStart, final float angExtend, final int type) {
        final KlighdPath result = new KlighdPath();
        result.setPathToArc(x, y, width, height, angStart, angExtend, type);
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
    public static KlighdPath createSpline(final Point2D[] points) {
        final KlighdPath result = new KlighdPath();
        result.setPathToSpline(points);
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
    public static KlighdPath createRoundedBendPolyline(final Point2D[] points,
            final float bendRadius) {
        final KlighdPath result = new KlighdPath();
        result.setPathToRoundedBendPolyline(points, bendRadius);
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
    public static KlighdPath createPolyline(final Point2D[] points) {
        final KlighdPath result = new KlighdPath();
        result.setPathToPolyline(points);
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
    public static KlighdPath createPolygon(final Point2D[] points) {
        final KlighdPath result = new KlighdPath();
        result.setPathToPolygon(points);
        result.setPaint(Color.white);
        return result;
    }

    /**
     * Creates an empty PSWTAdvancedPath.
     */
    public KlighdPath() {
        strokePaint = DEFAULT_STROKE_PAINT;
    }

    /**
     * Provides the information whether is path is a simple line or a shape with a closed periphery.
     * 
     * @return true if the path is a polyline, rounded bend point polyline, or a spline; false
     *         otherwise.
     */
    public boolean isLine() {
        return isPolyline || isRoundedBendsPolyline || isSpline;
    }

    /**
     * Returns the attached line attributes record denoting stroke configuration w.r.t. line cap,
     * join, style, and width.
     * 
     * @return the line related {@link LineAttributes}.
     */
    public LineAttributes getLineAttributes() {
        return this.lineAttributes;
    }

    /**
     * Sets a new line attributes record.
     * 
     * @param theLineAttributes
     *            the desired {@link LineAttributes} record.
     */
    public void setLineAttributes(final LineAttributes theLineAttributes) {
        this.lineAttributes = theLineAttributes;
        this.stroke = new BasicStroke(lineAttributes.width, lineAttributes.cap - 1,
                lineAttributes.join - 1, lineAttributes.miterLimit, lineAttributes.dash,
                lineAttributes.dashOffset);
        updateBoundsFromPath();
        updateShape();
    }

    /**
     * Returns the line width of the path.
     * 
     * @return the line width
     */
    public double getLineWidth() {
        return this.lineAttributes.width;
    }

    /**
     * Sets the line width of the path.
     * 
     * @param width
     *            the line width
     */
    public void setLineWidth(final float width) {
        this.lineAttributes.width = width;
        this.stroke = new BasicStroke(lineAttributes.width, lineAttributes.cap - 1,
                lineAttributes.join - 1, lineAttributes.miterLimit, lineAttributes.dash,
                lineAttributes.dashOffset);
        updateBoundsFromPath();
        updateShape();
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
        // invalidatePaint();
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
        // invalidatePaint();
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
     * @param newPaint
     *            paint that this node should use when painting itself.
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
        // invalidatePaint();
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
     * @param newPaint
     *            paint that this node should use when painting itself.
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
        // invalidatePaint();
        firePropertyChange(PROPERTY_CODE_PAINT, PROPERTY_PAINT, oldPaint, paint);
    }

    /**
     * Set the paint used to paint this node, which may be null.
     * 
     * @param newPaint
     *            paint that this node should use when painting itself.
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
     * Sets the shadow color for this path. Note that this definition will be ignored for non-closed
     * shapes, i.e. polylines and curves.
     * 
     * @param color
     *            the color of the attached shadow
     */
    public void setShadow(final RGB color) {
        if (!isLine() && color != null) {
            this.shadow = color;
        } else {
            this.shadow = null;
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void internalUpdateBounds(final double x, final double y, final double width,
            final double height) {
        // unused (left it the for the case it might be helpful in future,
        //  should not sink into obscurity) 
        //  if it is used again one make sure that no recursive cycles occur with
        //  #updateBoundsFromPath
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
            final Rectangle2D srcBounds = aBounds;
            // see remark on the deactivated 'transform' code above
            //
            // if (internalXForm == null) {
            //     srcBounds = aBounds;
            // } else {
            //     srcBounds = new PBounds(aBounds);
            //     internalXForm.inverseTransform(srcBounds, srcBounds);
            // }

            if (!isLine()) {
                // We changed the condition to treat "frame figures", i.e. non-filled closed shapes
                //  without a given background color, like filled ones with a background color.
                // The distinction w.r.t. to the line attribute abolishes b.t.w. the
                //  picking issue of non-closed lines, which was a problem in the early days of
                //  KLighD.
                return shape.intersects(srcBounds);
            } else if (strokePaint != null || strokePaintGradient != null) {
                return stroke.createStrokedShape(shape).intersects(srcBounds);
            }
        }
        return false;
    }

    
    /**
     * This method realizes the adjustment of the shape bounds according to stroke line width.
     * To this end, the initial shape definition, which is kept in {@link #origShape} is replicated
     * with adjusted bounds. This replicate is stored in {@link #shape} and drawn on the canvas
     * in {@link #paint(PPaintContext)}.<br>
     * <br>
     * In case of lines and polygons the {@link #origShape} is put into {@link #shape}, too, and
     * thus used while drawing.
     * 
     * @author chsch
     */
    private void updateShape() {
        if (isLine() || isPolygon) {
            shape = origShape;
            
        } else {
            final float lW = lineAttributes.width;
            final float halfLW = lineAttributes.width / 2;
            Rectangle2D.Float b = (Rectangle2D.Float) origShape.getBounds2D();

            if (origShape instanceof Arc2D) {
                final Arc2D.Float arc = (Arc2D.Float) origShape;
                shape = new Arc2D.Float(b.x + halfLW, b.y + halfLW, b.width - lW, b.height - lW,
                        arc.start, arc.extent, arc.getArcType());
            } else if (origShape instanceof Ellipse2D) {
                shape = new Ellipse2D.Float(b.x + halfLW, b.y + halfLW, b.width - lW, b.height - lW);
            } else if (origShape instanceof Rectangle2D) {
                shape = new Rectangle2D.Float(b.x + halfLW, b.y + halfLW, b.width - lW, b.height
                        - lW);
            } else if (origShape instanceof RoundRectangle2D) {
                final RoundRectangle2D.Float rect = (RoundRectangle2D.Float) origShape;
                shape = new RoundRectangle2D.Float(b.x + halfLW, b.y + halfLW, b.width - lW,
                        b.height - lW, rect.arcwidth, rect.archeight);
            }
        }
        firePropertyChange(PPath.PROPERTY_CODE_PATH, PPath.PROPERTY_PATH, null, shape);
        // invalidatePaint();
    }


    /**
     * Recalculates the path's bounds by examining it's associated shape.
     */
    private void updateBoundsFromPath() {
        if (isLine() || isPolygon) {
            final Rectangle2D b = shape.getBounds2D();
            final float halfLW = lineAttributes.width / 2;
            
            // By means of the following setting it's guaranteed that the line width is incorporated
            //  in determining the bounding box of horizontal and vertical lines.
            // This is important for being able to pick them, for instance. 
            
            super.setBounds(b.getX() - halfLW, b.getY() - halfLW,
                    b.getWidth() + lineAttributes.width,
                    b.getHeight() + lineAttributes.width);
        } else {
            super.setBounds(origShape.getBounds2D());
        }
    }


    /**
     * {@inheritDoc}<br>
     * <br>
     * This customization extends the fullBounds by the size of the shadow if present. It is called
     * by {@link #repaint()} and makes sure the shadow is cleared out, too!<br>
     * <br>
     * <i>I'm not sure whether it is better to do that this way or by providing a specialized
     * <code>repaint()</code> method.</i><br>
     * This should be observed and changed if necessary.
     * 
     * @author chsch
     */
    public PBounds getFullBoundsReference() {
        PBounds curBounds = super.getFullBoundsReference();
        if (shadow != null) {
            curBounds = new PBounds(super.getFullBoundsReference());
            curBounds.width += shadowExtend;
            curBounds.height += shadowExtend;
        }
        return curBounds;
    }

    @Override
    public boolean fullPick(final PPickPath pickPath) {
        return super.fullPick(pickPath);
    }

    @Override
    public boolean pick(final PPickPath pickPath) {
        if (this.getPickable()) {
            return super.pickAfterChildren(pickPath);
        }
        return super.pick(pickPath);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        KlighdSWTGraphics g2 = (KlighdSWTGraphics) paintContext.getGraphics();
        g2.setLineAttributes(lineAttributes);

        // if (internalXForm != null) {
        //    g2.transform(internalXForm);
        // }

        if (shadow != null) {
            drawShadow(g2);
        }
        final int currentAlpha = g2.getAlpha();
        final float currentAlphaFloat = (float) currentAlpha;

        if (!isLine()) {
            RGB p = getSWTPaint();
            if (p != null) {
                g2.setAlpha(
                        (int) (paintAlpha * (currentAlphaFloat / KlighdConstants.ALPHA_FULL_OPAQUE)));
                g2.setBackground(p);
                g2.fill(shape);
            }

            RGBGradient pg = getSWTPaintGradient();
            if (pg != null) {
                g2.setBackgroundPattern(pg, getBounds());
                g2.fill(shape);
            }
        }

        if (strokePaint != null) {
            g2.setAlpha((int) (strokeAlpha * (currentAlphaFloat / KlighdConstants.ALPHA_FULL_OPAQUE)));
            g2.setColor(strokePaint);
            g2.draw(shape);
        }

        if (strokePaintGradient != null) {
            Rectangle2D bounds = getBounds();
            g2.setPattern(
                    strokePaintGradient,
                    new Rectangle2D.Double(bounds.getMinX() - 1, bounds.getMinY() - 1, bounds
                            .getMaxX() + 1, bounds.getMaxY() + 2));
            g2.draw(shape);
        }

        // if (inverseXForm != null) {
        //    g2.transform(inverseXForm);
        // }

        g2.setAlpha(currentAlpha);
        g2.setLineAttributes(KlighdConstants.DEFAULT_LINE_ATTRIBUTES);
    }

    /**
     * Draws the shadow of the current shape.
     * 
     * @param g2
     *            the {@link KlighdSWTGraphics} to draw on.
     */
    private void drawShadow(final KlighdSWTGraphics g2) {

        final int currentAlpha = g2.getAlpha();

        // the alpha value of the particular shadow shapes
        // note that the alpha values of stacked shapes will kind of accumulate
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
            g2.fill(shape);
            tc.translate(-1, -1);
        }

        // re-set the original transform to the graphics layer
        // and draw a fresh white background on the shadow shape stack at the original position
        // Note, that we accept the incompatibility of transparent shapes and shadow, and thus
        // dismiss the transparency.
        g2.setTransform(t);
        g2.setLineWidth(1);
        g2.setAlpha(KlighdConstants.ALPHA_FULL_OPAQUE);
        g2.setBackground(KlighdConstants.WHITE);
        g2.fill(shape);

        // reset the manipulated settings
        g2.setAlpha(currentAlpha);
        g2.setLineWidth(lineAttributes.width);
    }


    /**
     * Changes the underlying shape of this PSWTPath.
     * 
     * @param newShape
     *            new associated shape of this PSWTPath
     */
    private void setShape(final Shape newShape) {
        origShape = newShape;
        updateShape();
        updateBoundsFromPath();
        invalidatePaint();
    }
    

    /**
     * Returns the points of the shape.
     * 
     * @return the points or <code>null</code> if path is not a line or is not initialized properly.
     */
    public Point2D[] getShapePoints() {
        return this.linePoints;
    }


    /**
     * Resets the path to a rectangle with the dimensions and position provided.
     * 
     * @param x
     *            left of the rectangle
     * @param y
     *            top of the rectangle
     * @param width
     *            width of the rectangle
     * @param height
     *            height of the rectangle
     */
    public void setPathToRectangle(final float x, final float y, final float width,
            final float height) {
        setShape(new Rectangle2D.Float(x, y, width, height));
    }

    /**
     * Resets the path to a rectangle with the dimensions and position provided.
     * 
     * @param x
     *            left of the rectangle
     * @param y
     *            top of the rectangle
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
        setShape(new RoundRectangle2D.Float(x, y, width, height, arcWidth, arcHeight));
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
        setShape(new Ellipse2D.Float(x, y, width, height));
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
     * @param type
     *            one of the constants {@link Arc2D#OPEN}, {@link Arc2D#CHORD}, and
     *            {@link Arc2D#PIE}
     */
    public void setPathToArc(final float x, final float y, final float width, final float height,
            final float angStart, final float angExtend, final int type) {
        setShape(new Arc2D.Float(x, y, width, height, angStart, angExtend, type));
    }


    /**
     * Sets the path to a sequence of segments described by the points.
     * 
     * @param points
     *            points to that lie along the generated path
     */
    public void setPathToSpline(final Point2D[] points) {
        if (points.length == 0) {
            return;
        }

        isSpline = true;
        Path2D spline = PolylineUtil.createSplinePath(new Path2D.Float(), points);
        this.linePoints = PolylineUtil.createSplineApproximationPath(spline);
        setShape(spline);
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
        if (points.length == 0) {
            return;
        }
        
        isRoundedBendsPolyline = true;
        this.linePoints = points;
        setShape(PolylineUtil.createRoundedBendsPolylinePath(new Path2D.Float(), points, bendRadius));
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
        
        this.isPolyline = true;
        this.linePoints = points;
        this.setShape(PolylineUtil.createPolylinePath(new Path2D.Float(), points));
    }


    /**
     * Sets the path to a sequence of segments described by the points.
     * 
     * @param points
     *            points to that lie along the generated path
     */
    public void setPathToPolygon(final Point2D[] points) {
        if (points.length == 0) {
            return;
        }
        
        this.isPolygon = true;
        this.linePoints = points;
        this.setShape(PolylineUtil.createPolygonPath(new Path2D.Float(), points));
    }
}
