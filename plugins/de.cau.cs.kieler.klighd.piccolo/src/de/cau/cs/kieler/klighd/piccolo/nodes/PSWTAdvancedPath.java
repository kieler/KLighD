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
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;

import edu.umd.cs.piccolo.util.PAffineTransform;
import edu.umd.cs.piccolo.util.PAffineTransformException;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolox.swt.PSWTPath;
import edu.umd.cs.piccolox.swt.SWTGraphics2D;

/**
 * The {@code PSWTAdvancedPath} is an extension of the Piccolo {@code PSWTPath}. Provides the
 * possibility to adjust the line width and the line style and can represent polygons.
 * 
 * @author mri
 */
public class PSWTAdvancedPath extends PSWTPath {

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

    private static final double BOUNDS_TOLERANCE = 0.01;
    private Paint strokePaint = DEFAULT_STROKE_PAINT;
    private static final BasicStroke BASIC_STROKE = new BasicStroke();

    /** the line width for this path. */
    private double lineWidth = 1.0;
    /** indicates whether or not this path describes a polygon. */
    private boolean isPolygon = false;
    /** the line style for this path. */
    private int lineStyle = SWT.LINE_SOLID;

    private boolean updatingBoundsFromPath;
    private Shape origShape;

    private PAffineTransform internalXForm;
    private AffineTransform inverseXForm;

    private double[] pts;
    private int[] xs;
    private int[] ys;

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
     * {@inheritDoc}
     */
    @Override
    protected void internalUpdateBounds(final double x, final double y, final double width,
            final double height) {
        // duplicate code and computations here to handle limited visibility on some members of
        // PSWTPath for polygons
        if (isPolygon) {
            if (updatingBoundsFromPath) {
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
        } else {
            super.internalUpdateBounds(x, y, width, height);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean intersects(final Rectangle2D aBounds) {
        if (isPolygon) {
            if (aBounds == null || getBoundsReference().intersects(aBounds)) {
                final Rectangle2D srcBounds;
                if (internalXForm == null) {
                    srcBounds = aBounds;
                } else {
                    srcBounds = new PBounds(aBounds);
                    internalXForm.inverseTransform(srcBounds, srcBounds);
                }

                if (getPaint() != null && origShape.intersects(srcBounds)) {
                    return true;
                } else if (strokePaint != null) {
                    return BASIC_STROKE.createStrokedShape(origShape).intersects(srcBounds);
                }
            }
            return false;
        } else {
            return super.intersects(aBounds);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paint(final PPaintContext paintContext) {
        SWTGraphics2D g2 = (SWTGraphics2D) paintContext.getGraphics();
        g2.setLineWidth(lineWidth);
        GC graphicsContext = g2.getGraphicsContext();
        int oldLineStyle = graphicsContext.getLineStyle();
        graphicsContext.setLineStyle(lineStyle);
        if (isPolygon) {
            final Paint p = getPaint();

            if (internalXForm != null) {
                g2.transform(internalXForm);
            }

            if (p != null) {
                g2.setBackground((Color) p);
                g2.fillPolygon(pts);
            }

            if (strokePaint != null) {
                g2.setColor((Color) strokePaint);
                g2.drawPolygon(xs, ys, xs.length);
            }

            if (inverseXForm != null) {
                g2.transform(inverseXForm);
            }
        } else {
            super.paint(paintContext);
        }
        graphicsContext.setLineStyle(oldLineStyle);
        g2.setLineWidth(1.0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShape(final Shape newShape) {
        super.setShape(newShape);
        isPolygon = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPathToRectangle(final float x, final float y, final float width,
            final float height) {
        super.setPathToRectangle(x, y, width, height);
        isPolygon = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPathToRoundRectangle(final float x, final float y, final float width,
            final float height, final float arcWidth, final float arcHeight) {
        super.setPathToRoundRectangle(x, y, width, height, arcWidth, arcHeight);
        isPolygon = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPathToEllipse(final float x, final float y, final float width, final float height) {
        super.setPathToEllipse(x, y, width, height);
        isPolygon = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPathToPolyline(final Point2D[] points) {
        super.setPathToPolyline(points);
        isPolygon = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPathToPolyline(final float[] xp, final float[] yp) {
        super.setPathToPolyline(xp, yp);
        isPolygon = false;
    }

    /**
     * Sets the path to a sequence of segments described by the points.
     * 
     * @param points
     *            points to that lie along the generated path
     */
    public void setPathToPolygon(final Point2D[] points) {
        pts = new double[points.length * 2];
        xs = new int[points.length];
        ys = new int[points.length];
        final GeneralPath path = new GeneralPath();
        path.reset();
        path.moveTo((float) points[0].getX(), (float) points[0].getY());
        pts[0] = points[0].getX();
        pts[1] = points[0].getY();
        xs[0] = (int) pts[0];
        ys[0] = (int) pts[1];
        int j = 1;
        for (int i = 1; i < points.length; i++) {
            path.lineTo((float) points[i].getX(), (float) points[i].getY());
            pts[j * 2] = points[i].getX();
            pts[j * 2 + 1] = points[i].getY();
            xs[j] = (int) points[i].getX();
            ys[j] = (int) points[i].getY();
            j++;
        }
        path.lineTo((float) points[0].getX(), (float) points[0].getY());
        setShape(path);
        origShape = path;
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
        pts = new double[xp.length * 2];
        xs = new int[xp.length];
        ys = new int[xp.length];
        final GeneralPath path = new GeneralPath();
        path.reset();
        path.moveTo(xp[0], yp[0]);
        pts[0] = xp[0];
        pts[1] = yp[0];
        xs[0] = (int) pts[0];
        ys[0] = (int) pts[1];
        int j = 1;
        for (int i = 1; i < xp.length; i++) {
            path.lineTo(xp[i], yp[i]);
            pts[j * 2] = xp[i];
            pts[j * 2 + 1] = yp[i];
            xs[j] = (int) xp[i];
            ys[j] = (int) yp[i];
            j++;
        }
        path.lineTo(xp[0], yp[0]);
        setShape(path);
        origShape = path;
        isPolygon = true;
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
     * {@inheritDoc}
     */
    @Override
    public void updateBoundsFromPath() {
        updatingBoundsFromPath = true;
        super.updateBoundsFromPath();
        updatingBoundsFromPath = false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setStrokeColor(final Paint strokeColor) {
        strokePaint = strokeColor;
        super.setStrokeColor(strokeColor);
    }

}
