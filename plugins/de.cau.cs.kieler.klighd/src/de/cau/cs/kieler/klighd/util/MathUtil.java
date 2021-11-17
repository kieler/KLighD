/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.util;

import java.awt.geom.Point2D;

import org.eclipse.elk.core.util.Pair;

/**
 * A utility class providing math functionality based on polyline structures.
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
     * @param locationAbsolute
     *            the absolute location offset
     * @param locationRelative
     *            the relative location ranging from 0 to 1
     * @return a pair of the index of the start segment and the concrete point for the relative
     *         location
     */
    public static Pair<Integer, Point2D> getSegmentStartIndexAndPoint(final Point2D[] points,
            final double locationAbsolute, final double locationRelative) {
        // handle special cases
        if (points.length == 0) {
            return new Pair<Integer, Point2D>(-1, new Point2D.Double(0, 0));
        } else if (points.length == 1) {
            return new Pair<Integer, Point2D>(0, (Point2D) points[0].clone());
        }
        
        Point2D point = new Point2D.Double();
        
        // compute total polyline distance
        double totalDistance = 0;
        for (int i = 0; i < points.length - 1; ++i) {
            totalDistance += points[i].distance(points[i + 1]);
        }
        
        // find the segment and point for the location
        int k = -1;
        double searchDistance = locationRelative * totalDistance + locationAbsolute;
        double currentDistance = 0;
        for (int i = 0; i < points.length - 1; ++i) {
            double d = points[i].distance(points[i + 1]);
            if (d <= 0) {
                continue;
            }
            
            if (currentDistance + d >= searchDistance) {
                // memorize the start index of the segment
                k = i;
                // compute the actual point on the polyline
                double rD = searchDistance - currentDistance;
                point.setLocation(
                        points[i].getX() + rD * (points[i + 1].getX() - points[i].getX()) / d,
                        points[i].getY() + rD * (points[i + 1].getY() - points[i].getY()) / d
                );
                break;
            } else {
                currentDistance += d;
            }
        }
        
        // if no segment has been found yet take first or last segment by default;
        //  Will the branch be taken if 'points' contains point != (0,0) ??
        //  (i.e. may it be removed or must locationAbsoluted be incorporated here, too?
        if (k < 0) {
            // CHECKSTYLEOFF MagicNumber
            if (locationRelative < 0.5) {
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
     * Given a polyline specified by a list of points and a relative location, computes the concrete
     * point of this relative location on the polyline.
     * 
     * @param points
     *            the list of points
     * @param locationRelative
     *            the relative location ranging from 0 to 1
     * @return the concrete point for the relative location
     */
    public static Point2D getPoint(final Point2D[] points, final double locationRelative) {
        return getSegmentStartIndexAndPoint(points, 0, locationRelative).getSecond();
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
    
    /**
     * Returns the length of the polyline specified by the given points.
     * 
     * @param points
     *            the list of points
     * @return the length of the polyline
     */
    public static double getLength(final Point2D[] points) {
        if (points.length == 0) {
            return 0f;
        }
        
        Point2D lastBend = points[0];
        double currentLength = 0.0f;
        for (int i = 1; i < points.length; ++i) {
            Point2D currentBend = points[i];
            currentLength += lastBend.distance(currentBend);
            lastBend = currentBend;
        }
        return currentLength;
    }

}
