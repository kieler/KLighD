/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Path;

import de.cau.cs.kieler.klighd.KlighdConstants;

/**
 * A collection of convenience methods for creating {@link KlighdPath} instances.<br>
 * They are outsourced in order to reduce the size of the {@link KlighdPath} class.<br>
 * <br>
 * Some of these methods are inspired by those of {@link edu.umd.cs.piccolox.swt.PSWTPath PSWTPath}.
 * 
 * @author chsch
 */
public final class KlighdPaths {

    /**
     * Standard hidden constructor.
     */
    private KlighdPaths() {
    }

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
     * @return the created rectangle
     */
    public static KlighdPath createRectangle(final float x, final float y, final float width,
            final float height) {
        final KlighdPath result = new KlighdPath();
        result.setPathToRectangle(x, y, width, height);
        result.setPaint(KlighdConstants.WHITE);
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
     * @return the created rounded rectangle
     */
    public static KlighdPath createRoundRectangle(final float x, final float y,
            final float width, final float height, final float arcWidth, final float arcHeight) {
        final KlighdPath result = new KlighdPath();
        result.setPathToRoundRectangle(x, y, width, height, arcWidth, arcHeight);
        result.setPaint(KlighdConstants.WHITE);
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
     * @return the created ellipse
     */
    public static KlighdPath createEllipse(final float x, final float y, final float width,
            final float height) {
        final KlighdPath result = new KlighdPath();
        result.setPathToEllipse(x, y, width, height);
        result.setPaint(KlighdConstants.WHITE);
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
     *            one of the constants {@link java.awt.geom.Arc2D#OPEN Arc2D#OPEN},
     *            {@link java.awt.geom.Arc2D#CHORD Arc2D#CHORD}, and {@link java.awt.geom.Arc2D#PIE
     *            Arc2D#PIE}
     * @return the created arc
     */
    public static KlighdPath createArc(final float x, final float y, final float width,
            final float height, final float angStart, final float angExtend, final int type) {
        final KlighdPath result = new KlighdPath();
        result.setPathToArc(x, y, width, height, angStart, angExtend, type);
        result.setPaint(KlighdConstants.WHITE);
        return result;
    }

    /**
     * Creates a path for the spline for the given points.
     * 
     * @param points
     *            array of points for the point lines
     * @return the created spline for the given points
     */
    public static KlighdPath createSpline(final Point2D[] points) {
        final KlighdPath result = new KlighdPath();
        result.setPathToSpline(points);
        return result;
    }

    /**
     * Creates a path for the poly-line with rounded bend points for the given points.
     * 
     * @param points
     *            array of points for the point lines
     * @param bendRadius
     *            the radius of the bend points
     * 
     * @return the created polyline with rounded bend points for the given points
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
     * @return the created polyline for the given points
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
     * @return the created polygon for the given points
     */
    public static KlighdPath createPolygon(final Point2D[] points) {
        final KlighdPath result = new KlighdPath();
        result.setPathToPolygon(points);
        result.setPaint(KlighdConstants.WHITE);
        return result;
    }


    /**
     * Builds up a SWT {@link Path} according to a given AWT Geometry {@link PathIterator}.
     * 
     * @param pathIterator
     *            provides the segments the new path shall contain.
     * @param device
     *            the device to create the path on
     * @return the desired {@link Path} object.
     */
    public static Path createSWTPath(final PathIterator pathIterator, final Device device) {
        // SUPPRESS CHECKSTYLE NEXT 30 MagicNumber

        final Path path = new Path(device);

        final float[] coords = new float[6];

        while (!pathIterator.isDone()) {
            switch (pathIterator.currentSegment(coords)) {
            case PathIterator.SEG_MOVETO:
                path.moveTo(coords[0], coords[1]);
                break;
            case PathIterator.SEG_LINETO:
                path.lineTo(coords[0], coords[1]);
                break;
            case PathIterator.SEG_CLOSE:
                path.close();
                break;
            case PathIterator.SEG_QUADTO:
                path.quadTo(coords[0], coords[1], coords[2], coords[3]);
                break;
            case PathIterator.SEG_CUBICTO:
                path.cubicTo(coords[0], coords[1], coords[2], coords[3], coords[4], coords[5]);
                break;
            default:
            }
            pathIterator.next();
        }
        return path;
    }
}
