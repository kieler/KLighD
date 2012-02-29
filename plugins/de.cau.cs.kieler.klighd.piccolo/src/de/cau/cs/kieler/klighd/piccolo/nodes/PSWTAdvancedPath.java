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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PAffineTransformException;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolox.swt.SWTGraphics2D;
import edu.umd.cs.piccolox.swt.SWTShapeManager;

/**
 * The {@code PSWTAdvancedPath} is a refinement of the Piccolo {@code PSWTPath}. Provides the
 * possibility to adjust the line width and the line style and can represent polygons.<br />
 * <br />
 * Most of the implementation is copied from {@code PSWTPath}.
 * 
 * @author mri
 */
public class PSWTAdvancedPath extends PNode {

    /**
     * The possible line styles for an advanced path.
     */
    public enum LineStyle {
        /** solid. */
        SOLID,
        /** dashes. */
        DASH,
        /** dots. */
        DOT,
        /** dashes and dots. */
        DASHDOT,
        /** dash followed by two dots. */
        DASHDOTDOT
    }

    private static final long serialVersionUID = 8034306769936734586L;

    private static final Color DEFAULT_STROKE_PAINT = Color.black;

    private static final Rectangle2D.Float TEMP_RECTANGLE = new Rectangle2D.Float();
    private static final RoundRectangle2D.Float TEMP_ROUNDRECTANGLE = new RoundRectangle2D.Float();
    private static final Ellipse2D.Float TEMP_ELLIPSE = new Ellipse2D.Float();
    private static final double BOUNDS_TOLERANCE = 0.01;
    private Paint strokePaint = DEFAULT_STROKE_PAINT;
    private static final BasicStroke BASIC_STROKE = new BasicStroke();

    /** the line width for this path. */
    private double lineWidth = 1.0;
    /** the line style for this path. */
    private int lineStyle = SWT.LINE_SOLID;

    private boolean updatingBoundsFromPath;
    private Shape origShape;
    private Shape shape;

    private PAffineTransform internalXForm;
    private AffineTransform inverseXForm;

    private double[] shapePts;

    private boolean isPolygon = false;

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
     * Creates a path for the poly-line for the given points.
     * 
     * @param points
     *            array of points for the point lines
     * 
     * @return created poly-line for the given points
     */
    public static PSWTAdvancedPath createPolyline(final Point2D[] points) {
        final PSWTAdvancedPath result = new PSWTAdvancedPath();
        result.setPathToPolyline(points);
        result.setPaint(Color.white);
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
        result.setPaint(Color.white);
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
    public Paint getStrokePaint() {
        return strokePaint;
    }

    /**
     * Sets the paint to use when drawing the stroke of the shape.
     * 
     * @param strokeColor
     *            new stroke color
     */
    public void setStrokeColor(final Paint strokeColor) {
        final Paint old = strokePaint;
        strokePaint = strokeColor;
        invalidatePaint();
        firePropertyChange(PPath.PROPERTY_CODE_STROKE_PAINT, PPath.PROPERTY_STROKE_PAINT, old,
                strokePaint);
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
            super.setBounds(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        }
        updatingBoundsFromPath = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        SWTGraphics2D g2 = (SWTGraphics2D) paintContext.getGraphics();
        Paint p = getPaint();
        g2.setLineWidth(lineWidth);
        GC graphicsContext = g2.getGraphicsContext();
        int oldLineStyle = graphicsContext.getLineStyle();
        graphicsContext.setLineStyle(lineStyle);

        if (internalXForm != null) {
            g2.transform(internalXForm);
        }

        if (p != null) {
            g2.setBackground((Color) p);
            fillShape(g2);
        }

        if (strokePaint != null) {
            g2.setColor((Color) strokePaint);
            drawShape(g2);
        }

        if (inverseXForm != null) {
            g2.transform(inverseXForm);
        }
        graphicsContext.setLineStyle(oldLineStyle);
        g2.setLineWidth(1.0);
    }

    // CHECKSTYLEOFF MagicNumber
    private void drawShape(final SWTGraphics2D g2) {
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
        } else {
            g2.draw(shape);
        }
    }

    private void fillShape(final SWTGraphics2D g2) {
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
        } else {
            if (isPolygon) {
                g2.fillPolygon(shapePts);
            } else {
                g2.fill(shape);
            }
        }
    }

    // CHECKSTYLEON MagicNumber

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
     * Sets the path to a sequence of segments described by the points.
     * 
     * @param points
     *            points to that lie along the generated path
     */
    public void setPathToPolyline(final Point2D[] points) {
        final GeneralPath path = new GeneralPath();
        path.reset();
        path.moveTo((float) points[0].getX(), (float) points[0].getY());
        for (int i = 1; i < points.length; i++) {
            path.lineTo((float) points[i].getX(), (float) points[i].getY());
        }
        setShape(path);
    }

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

}
