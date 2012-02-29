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
package de.cau.cs.kieler.klighd.piccolo.util;

import java.awt.geom.Point2D;

import de.cau.cs.kieler.core.util.Pair;

/**
 * A utility class providing math functionality based on data structures used in Piccolo.
 * 
 * @author mri
 */
public final class MathUtil {

    /**
     * A private constructor to prevent instantiation.
     */
    private MathUtil() {
        // do nothing
    }

    /**
     * Given a polyline specified by a list of points and a relative location, computes the segment
     * of the polyline and the concrete point of this relative location on the polyline.
     * 
     * @param points
     *            the list of points
     * @param location
     *            the relative location
     * @return a pair of the index of the start segment and the concrete point for the relative
     *         location
     */
    public static Pair<Integer, Point2D> getSegmentStartIndexAndPoint(final Point2D[] points,
            final float location) {
        Point2D point = new Point2D.Double();
        // compute total polyline distance
        double totalDistance = 0;
        for (int i = 0; i < points.length - 1; ++i) {
            totalDistance += distance(points[i], points[i + 1]);
        }
        // find the segment and point for the location
        int k = -1;
        double searchDistance = location * totalDistance;
        double currentDistance = 0;
        for (int i = 0; i < points.length - 1; ++i) {
            double d = distance(points[i], points[i + 1]);
            if (d <= 0) {
                continue;
            }
            if (currentDistance + d >= searchDistance) {
                // memorize the start index of the segment
                k = i;
                // compute the actual point on the polyline
                double rD = searchDistance - currentDistance;
                point.setLocation(points[i].getX() + rD * (points[i + 1].getX() - points[i].getX())
                        / d, points[i].getY() + rD * (points[i + 1].getY() - points[i].getY()) / d);
                break;
            } else {
                currentDistance += d;
            }
        }
        // take first or last segment by default
        if (k < 0) {
            // CHECKSTYLEOFF MagicNumber
            if (location < 0.5) {
                k = 0;
                point.setLocation(points[0]);
            } else {
                k = points.length - 2;
                point.setLocation(points[k]);
            }
            // CHECKSTYLEON MagicNumber
        }
        return new Pair<Integer, Point2D>(k, point);
    }

    /**
     * Returns the distance between the specified points.
     * 
     * @param p0
     *            the first point
     * @param p1
     *            the second point
     * @return the distance
     */
    public static double distance(final Point2D p0, final Point2D p1) {
        double xD = p1.getX() - p0.getX();
        double yD = p1.getY() - p0.getY();
        return Math.sqrt(xD * xD + yD * yD);
    }

    /**
     * Returns the angle between the specified points.
     * 
     * @param p0
     *            the first point
     * @param p1
     *            the second point
     * @return the angle
     */
    public static double angle(final Point2D p0, final Point2D p1) {
        double xD = p1.getX() - p0.getX();
        double yD = p1.getY() - p0.getY();
        return Math.atan2(yD, xD);
    }

}
