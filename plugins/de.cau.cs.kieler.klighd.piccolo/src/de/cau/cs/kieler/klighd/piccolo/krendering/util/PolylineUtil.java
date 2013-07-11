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
package de.cau.cs.kieler.klighd.piccolo.krendering.util;

import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.klighd.piccolo.internal.nodes.PSWTAdvancedPath;

/**
 * Some out-sourced calculations for rendering the different
 * {@link de.cau.cs.kieler.core.krendering.KPolyline KPolylines}. Unfortunately, AWT Geometry itself
 * does not provide related classes for line paths with initialization methods taking arrays of
 * points.
 * 
 * @author chsch
 */
public final class PolylineUtil {

    /**
     * This metric controls the quality of the curve approximation in
     * {@link #createSplineApproximationPath(Path2D)}. For details on its meaning see
     * {@link java.awt.Shape#getPathIterator(java.awt.geom.AffineTransform, double)
     * Shape#getPathIterator(AffineTransform, double)}
     */
    private static final float MAX_APPROX_DISTANCE = 0.5f;
    
    /**
     * The control points of the arcs are moved from the given bend point towards the respective
     * neighbor by this factor times the given bend radius.
     */
    private static final double CONTROL_POINTS_SHARE = 0.25;
    
    private static final boolean DEBUG = false; 
    
    /** Hidden constructor.*/
    private PolylineUtil() {
    }


    /**
     * Resets the given <code>thePath</code> and adds the required segments for drawing a polygon.
     * If <code>thePath</code> is <code>null</code> a new path object is created.
     * 
     * @param thePath
     *            the path object to put the segments into, may be <code>null</code>
     * @param points
     *            the points in form of an integer array like {x0, y0, x1, y1, ...} 
     * @return the path object containing the required segments
     */
    public static Path2D createPolygonPath(final Path2D thePath, final int[] points) {

        Path2D path = thePath != null ? thePath : new Path2D.Float();
        
        path.reset();
        path.moveTo(points[0], points[1]);
        for (int i = 2; i < points.length;) {
            path.lineTo(points[i++], points[i++]);
        }
        path.closePath();
        
        return path;
    }


    /**
     * Resets the given <code>thePath</code> and adds the required segments for drawing a polygon.
     * If <code>thePath</code> is <code>null</code> a new path object is created.
     * 
     * @param thePath
     *            the path object to put the segments into, may be <code>null</code>
     * @param points
     *            an array of AWT Geometry {@link Point2D Point2Ds} 
     * @return the path object containing the required segments
     */
    public static Path2D createPolygonPath(final Path2D thePath, final Point2D[] points) {

        Path2D path = thePath != null ? thePath : new Path2D.Float();
        
        path.reset();
        path.moveTo(points[0].getX(), points[0].getY());
        for (int i = 1; i < points.length; i++) {
            path.lineTo(points[i].getX(), points[i].getY());
        }
        path.closePath();

        return path;
    }


    /**
     * Resets the given <code>thePath</code> and adds the required segments for drawing a polyline.
     * If <code>thePath</code> is <code>null</code> a new path object is created.
     * 
     * @param thePath
     *            the path object to put the segments into, may be <code>null</code>
     * @param points
     *            the points in form of an integer array like {x0, y0, x1, y1, ...} 
     * @return the path object containing the required segments
     */
    public static Path2D createPolylinePath(final Path2D thePath, final int[] points) {

        Path2D path = thePath != null ? thePath : new Path2D.Float();
        
        path.reset();
        path.moveTo(points[0], points[1]);
        for (int i = 2; i < points.length;) {
            path.lineTo(points[i++], points[i++]);
        }
        
        return path;
    }


    /**
     * Resets the given <code>thePath</code> and adds the required segments for drawing a polyline.
     * If <code>thePath</code> is <code>null</code> a new path object is created.
     * 
     * @param thePath
     *            the path object to put the segments into, may be <code>null</code>
     * @param points
     *            an array of AWT Geometry {@link Point2D Point2Ds} 
     * @return the path object containing the required segments
     */
    public static Path2D createPolylinePath(final Path2D thePath, final Point2D[] points) {

        Path2D path = thePath != null ? thePath : new Path2D.Float();
        
        path.reset();
        path.moveTo((float) points[0].getX(), (float) points[0].getY());
        for (int i = 1; i < points.length; i++) {
            path.lineTo((float) points[i].getX(), (float) points[i].getY());
        }

        return path;
    }


    /**
     * Resets the given <code>thePath</code> and adds the required segments for drawing a spline.
     * If <code>thePath</code> is <code>null</code> a new path object is created.
     * 
     * @param thePath
     *            the path object to put the segments into, may be <code>null</code>
     * @param points
     *            an array of AWT Geometry {@link Point2D Point2Ds} 
     * @return the path object containing the required segments
     */
    public static Path2D createSplinePath(final Path2D thePath, final Point2D[] points) {

        Path2D path = thePath != null ? thePath : new Path2D.Float();
        
        path.reset();
        int size = points.length;
        
        if (size < 1) {
            return path; // nothing to do
        }
        
        path.moveTo(points[0].getX(), points[0].getY());

        // draw cubic sections
        int i = 1;
        for (; i < size - 2; i += 3) { // SUPPRESS CHECKSTYLE MagicNumber
            path.curveTo(points[i].getX(), points[i].getY(),
                    points[i + 1].getX(), points[i + 1].getY(),
                    points[i + 2].getX(), points[i + 2].getY());
        }

        // draw remaining sections, won't happen if 'Graphviz Dot' was applied
        // size-1: one straight line
        // size-2: one quadratic
        switch (size - i) {
        case 1:
            path.lineTo(points[i].getX(), points[i].getY());
            break;
        case 2:
            path.quadTo(points[i].getX(), points[i].getY(),
                    points[i + 1].getX(), points[i + 1].getY());
            break;
        default:
            // this should not happen
            break;
        }
        
        return path;
    }


    /**
     * Calculates and returns approximation points of the given {@link Path2D} that may contain
     * curve segments.
     * 
     * @param path
     *            the path object to put the segments into, may be <code>null</code>
     * @return the path object containing the required segments
     */
    public static Point2D[] createSplineApproximationPath(final Path2D path) {
        
        float[] coords = new float[2];
        ArrayList<Point2D> approxPoints2 = Lists.newArrayList();
        PathIterator pi = path.getPathIterator(null, MAX_APPROX_DISTANCE);

        while (!pi.isDone()) {
            pi.currentSegment(coords);
            approxPoints2.add(new Point2D.Float(coords[0], coords[1]));
            pi.next();
        }
        
        return Iterables.toArray(approxPoints2, Point2D.class);
    }


    /**
     * Resets the given <code>thePath</code> and adds the required segments for drawing a rounded
     * bends polyline. If <code>thePath</code> is <code>null</code> a new path object is created.
     * 
     * @param thePath
     *            the path object to put the segments into, may be <code>null</code>
     * @param points
     *            an array of AWT Geometry {@link Point2D Point2Ds}
     * @param bendRadius
     *            the bend curve radius as determined in the KRendering description
     * @return the path object containing the required segments
     */
    public static Path2D createRoundedBendsPolylinePath(final Path2D thePath, final Point2D[] points,
            final float bendRadius) {
        
        Path2D path = thePath != null ? thePath : new Path2D.Float();
        createRoundedBendPoints(path, points, bendRadius, null);
        return path;
    }

    // CHECKSTYLEOFF MagicNumber 

    /**
     * Transforms polyline bend points into rounded curves and attaches them to the given path.
     * 
     * @param path
     *            the {@link Path2D} being treated
     * @param points
     *            the original bend points (as provided by the layouters)
     * @param bendRadius
     *            the bend curve radius as determined in the KRendering description
     * @param pnode
     *            the {@link edu.umd.cs.piccolo.PNode PNode} being treatment (only needed for
     *            attaching debug visualization points)
     */
    public static void createRoundedBendPoints(final Path2D path, final Point2D[] points,
            final float bendRadius, final PSWTAdvancedPath pnode) {

        if (DEBUG) {
            if (pnode != null) {
                // in case there're already debug points throw them away 
                pnode.removeAllChildren();
            }
        }

        path.reset();

        // start from the first point
        path.moveTo((float) points[0].getX(), (float) points[0].getY());

        // for all of the bend points (not start and end point)...
        for (int i = 1; i < points.length - 1; i++) {

            double dx = points[i + 1].getX() - points[i].getX();
            double dy = points[i + 1].getY() - points[i].getY();

            // examine whether two bend points (this one and the next one) are located within a
            // distance of less than twice the radius, and there is still one more point available
            // for determine the "leaving" angle of the line
            if (i + 2 < points.length && Math.abs(dx) < 2 * bendRadius
                    && Math.abs(dy) < 2 * bendRadius) {

                // if so, apply the short distance approximation
                double[] cPoints = PolylineUtil.getShortDistanceApproximationPoints(points[i - 1],
                        points[i], points[i + 1], points[i + 2]);

                // draw a straight line to the start of the curve
                path.lineTo((float) cPoints[0], (float) cPoints[1]);

                // draw the curve according to the determined points
                path.curveTo((float) cPoints[2], (float) cPoints[3], (float) cPoints[4],
                        (float) cPoints[5], (float) cPoints[6], (float) cPoints[7]);
                
                if (DEBUG) {
                    if (pnode != null) {
                        // and provide visualizations of the determined points
                        PolylineUtil.visualizeShortDistanceApproximationPoints(pnode, cPoints);
                    }
                }
                
                // finally, skip the next point, since it has been treated by the above procedure. 
                i++;
                continue;

            } else {
                
                // determine the curve points for the rounded arc
                double[] cPoints = PolylineUtil.getRoundedBendControlPoints(points[i - 1],
                        points[i], points[i + 1], bendRadius);

                // draw a straight line to the start of the curve
                path.lineTo(cPoints[0], cPoints[1]);
                
                // draw the curve according to the determined points
                path.curveTo(cPoints[2], cPoints[3], cPoints[4], cPoints[5], cPoints[6], cPoints[7]);
                
                if (DEBUG) {
                    if (pnode != null) {
                        // and provide visualizations of the determined points
                        PolylineUtil.visualizeRoundedBendControlPoints(pnode, points[i].getX(),
                                points[i].getY(), cPoints);
                    }
                }
            }
        }
        
        // draw the line to the final (target) point
        path.lineTo((float) points[points.length - 1].getX(),
                (float) points[points.length - 1].getY());
    }

    /**
     * Determination of beginning, end, and control points of a polyline bend point to be rendered
     * with rounded bends. This method does not care on flaws resulting from bend points having a
     * distance smaller than twice the given bend radius. That situation will be (partly) handled by
     * {@link #getShortDistanceApproximationPoints(double, double, double, double, double, double,
     * double, double)}.
     * 
     * The beginning and end points are moved from the original bend point towards the respective
     * neighbors by the amount of 'bendRadius' along the line, the control points by the amount of
     * 'bendRadius' times 'CONTROL_POINTS_SHARE'.
     * 
     * @param p0
     *            the bend point's predecessor
     * @param p1
     *            the bend point
     * @param p2
     *            the bend point's successor'
     * @param bendRadius
     *            the radius of the bend arc
     * 
     * @return an array of four double precision point coordinates with<br>
     *         [0] = beginning x,<br>
     *         [1] = beginning y,<br>
     *         [2] = control1 x,<br>
     *         [3] = control1 y,<br>
     *         [4] = control2 x,<br>
     *         [5] = control2 y,<br>
     *         [6] = end x,<br>
     *         [7] = end y<br>
     */
    public static double[] getRoundedBendControlPoints(
            final Point2D p0, final Point2D p1, final Point2D p2,
            final double bendRadius) {
        
        double[] result = new double[8];
        
        final double px1 = p1.getX();
        final double py1 = p1.getY();
        final double dx1 = px1 - p0.getX();
        final double dy1 = py1 - p0.getY();
        final double dx2 = p2.getX() - px1;
        final double dy2 = p2.getY() - py1;
        
        // caution: the angles are given in range of [-pi/2, pi/2) (or maybe (-pi/2, pi/2] ...)
        double angle1 = Math.atan(dy1 / dx1);
        double angle2 = Math.atan(dy2 / dx2);

        if (dx1 < 0) {
            result[0] = px1 + bendRadius * Math.cos(angle1);
            result[1] = py1 + bendRadius * Math.sin(angle1);
            result[2] = px1 + CONTROL_POINTS_SHARE * bendRadius * Math.cos(angle1);
            result[3] = py1 + CONTROL_POINTS_SHARE * bendRadius * Math.sin(angle1);
        } else if (dx1 > 0) {
            result[0] = px1 - bendRadius * Math.cos(angle1);
            result[1] = py1 - bendRadius * Math.sin(angle1);
            result[2] = px1 - CONTROL_POINTS_SHARE * bendRadius * Math.cos(angle1);
            result[3] = py1 - CONTROL_POINTS_SHARE * bendRadius * Math.sin(angle1);
        } else {
            if (dy1 != 0) {
                // sin(angle1) € {-1, 1} 
                // cos(angle1) = 0
                result[0] = px1;
                result[1] = py1 - bendRadius * Math.sin(angle1);
                result[2] = px1;
                result[3] = py1 - CONTROL_POINTS_SHARE * bendRadius * Math.sin(angle1);
            } else {
                // angle1 = NaN
                result[0] = px1;
                result[1] = py1;
                result[2] = px1;
                result[3] = py1;
            }
        }

        if (dx2 < 0) {
            result[4] = px1 - CONTROL_POINTS_SHARE * bendRadius * Math.cos(angle2);
            result[5] = py1 - CONTROL_POINTS_SHARE * bendRadius * Math.sin(angle2);
            result[6] = px1 - bendRadius * Math.cos(angle2);
            result[7] = py1 - bendRadius * Math.sin(angle2);
        } else if (dx2 > 0) {
            result[4] = px1 + CONTROL_POINTS_SHARE * bendRadius * Math.cos(angle2);
            result[5] = py1 + CONTROL_POINTS_SHARE * bendRadius * Math.sin(angle2);
            result[6] = px1 + bendRadius * Math.cos(angle2);
            result[7] = py1 + bendRadius * Math.sin(angle2);
        } else {
            if (dy2 != 0) {
                // sin(angle2) € {-1, 1} 
                // cos(angle2) = 0
                result[4] = px1;
                result[5] = py1 + CONTROL_POINTS_SHARE * bendRadius * Math.sin(angle2);
                result[6] = px1;
                result[7] = py1 + bendRadius * Math.sin(angle2);
            } else {
                // angle2 = NaN
                result[4] = px1;
                result[5] = py1;
                result[6] = px1;
                result[7] = py1;
            }
        }
        return result;
    }
    
    /**
     * Visualizes the points of a rounded bend point's approximation curve.
     * 
     * @param path
     *            the path to put the points on
     * @param px
     *            the x coordinate of the original bend point
     * @param py
     *            the y coordinate of the original bend point
     * @param cPoints
     *            the points and control points of the bend points to be approximated, as provided
     *            by {@link #getRoundedBendControlPoints(double, double, double, double, double,
     *            double, double)}
     */
    public static void visualizeRoundedBendControlPoints(final PSWTAdvancedPath path,
            final double px, final double py, final double[] cPoints) {
        PSWTAdvancedPath e = PSWTAdvancedPath.createEllipse((float) px, (float) py, 2, 2);
        path.addChild(e);
        PSWTAdvancedPath a = PSWTAdvancedPath.createEllipse((float) cPoints[0], (float) cPoints[1],
                2, 2);
        a.setStrokeColor(Display.getDefault().getSystemColor(SWT.COLOR_RED).getRGB());
        path.addChild(a);
        PSWTAdvancedPath b = PSWTAdvancedPath.createEllipse((float) cPoints[2], (float) cPoints[3],
                2, 2);
        b.setStrokeColor(Display.getDefault().getSystemColor(SWT.COLOR_GREEN).getRGB());
        path.addChild(b);
        PSWTAdvancedPath c = PSWTAdvancedPath.createEllipse((float) cPoints[4], (float) cPoints[5],
                2, 2);
        c.setStrokeColor(Display.getDefault().getSystemColor(SWT.COLOR_BLUE).getRGB());
        path.addChild(c);
        PSWTAdvancedPath d = PSWTAdvancedPath.createEllipse((float) cPoints[6], (float) cPoints[7],
                2, 2);
        d.setStrokeColor(Display.getDefault().getSystemColor(SWT.COLOR_YELLOW).getRGB());
        path.addChild(d);
    }
    
    
    /**
     * Determination of beginning, end, and control points of a curve (mostly S-shaped)
     * approximating two bend points that are closely placed together form a little displacement of
     * an edge. This method does not care on flaws resulting from groups of bend points with a
     * number of more than two each having a distance smaller than twice the given bend radius to
     * their neighbors.
     * 
     * The control points are formed by the original bend points. The beginning and end points are
     * moved from the original bend points towards the "outer" neighbors along the line by the
     * amount of half the distance of the treated bend point pair.
     * 
     * @param p0
     *            the bend point's predecessor
     * @param p1
     *            the bend point
     * @param p2
     *            the bend point's successor
     * @param p3
     *            the bend point's successor's successor
     * 
     * @return an array of four double precision point coordinates with<br>
     *         [0] = beginning x,<br>
     *         [1] = beginning y,<br>
     *         [2] = control1 x,<br>
     *         [3] = control1 y,<br>
     *         [4] = control2 x,<br>
     *         [5] = control2 y,<br>
     *         [6] = end x,<br>
     *         [7] = end y<br>
     */
    public static double[] getShortDistanceApproximationPoints(final Point2D p0, final Point2D p1,
            final Point2D p2, final Point2D p3) {
        double[] result = new double[8];
        
        final double px0 = p0.getX();
        final double py0 = p0.getY();
        final double px1 = p1.getX();
        final double py1 = p1.getY();
        final double px2 = p2.getX();
        final double py2 = p2.getY();
        final double px3 = p3.getX();
        final double py3 = p3.getY();

        double dx1 = px1 - px0;
        double dy1 = py1 - py0;
        double dx2 = px2 - px1;
        double dy2 = py2 - py1;
        double dx3 = px3 - px2;
        double dy3 = py3 - py2;

        // Pythagorean theorem
        double radius = Math.sqrt(dx2 * dx2 + dy2 * dy2) / 2d;

        // caution: the angles are given in range of [-pi/2, pi/2) (or maybe (-pi/2, pi/2] ...)
        double angle1 = Math.atan(dy1 / dx1);
        double angle3 = Math.atan(dy3 / dx3);

        if (dx1 < 0) {
            result[0] = px1 + radius * Math.cos(angle1);
            result[1] = py1 + radius * Math.sin(angle1);
        } else if (dx1 > 0) {
            result[0] = px1 - radius * Math.cos(angle1);
            result[1] = py1 - radius * Math.sin(angle1);
        } else {
            if (dy1 != 0) {
                // sin(angle1) € {-1, 1}
                // cos(angle1) = 0
                result[0] = px1; // - radius * Math.cos(angle1);
                result[1] = py1 - radius * Math.sin(angle1);
            } else {
                // angle1 == NaN
                result[0] = px1;
                result[1] = py1;
            }
        }
        
        result[2] = px1;
        result[3] = py1;
        result[4] = px2;
        result[5] = py2;
        
        if (dx3 < 0) {
            result[6] = px2 - radius * Math.cos(angle3);
            result[7] = py2 - radius * Math.sin(angle3);
        } else if (dx3 > 0) {
            result[6] = px2 + radius * Math.cos(angle3);
            result[7] = py2 + radius * Math.sin(angle3);
        } else {
            if (dy3 != 0) {
                // sin(angle1) € {-1, 1} 
                // cos(angle1) = 0
                result[6] = px2; // + radius * Math.cos(angle3);
                result[7] = py2 + radius * Math.sin(angle3);
            } else {
                // angle3 == NaN
                result[6] = px2;
                result[7] = py2;
            }
        }
        return result;
    }

    /**
     * Visualizes the points of an approximation curve of a pair of closely placed bend points
     * forming a little curve displacement.
     * 
     * @param path
     *            the path to put the points on
     * @param cPoints
     *            the points and control points of the bend points to be approximated, as provided
     *            by {@link #getShortDistanceRoundedBendControlPoints(double, double, double, double,
     *            double, double, double, double)}
     */
    public static void visualizeShortDistanceApproximationPoints(final PSWTAdvancedPath path,
            final double[] cPoints) {
        PSWTAdvancedPath a = PSWTAdvancedPath.createEllipse((float) cPoints[0], (float) cPoints[1],
                2, 2);
        a.setStrokeColor(Display.getDefault().getSystemColor(SWT.COLOR_RED).getRGB());
        path.addChild(a);
        PSWTAdvancedPath b = PSWTAdvancedPath.createEllipse((float) cPoints[2], (float) cPoints[3],
                2, 2);
        b.setStrokeColor(Display.getDefault().getSystemColor(SWT.COLOR_GREEN).getRGB());
        path.addChild(b);
        PSWTAdvancedPath c = PSWTAdvancedPath.createEllipse((float) cPoints[4], (float) cPoints[5],
                2, 2);
        c.setStrokeColor(Display.getDefault().getSystemColor(SWT.COLOR_BLUE).getRGB());
        path.addChild(c);
        PSWTAdvancedPath d = PSWTAdvancedPath.createEllipse((float) cPoints[6], (float) cPoints[7],
                2, 2);
        d.setStrokeColor(Display.getDefault().getSystemColor(SWT.COLOR_YELLOW).getRGB());
        path.addChild(d);

    }

}
