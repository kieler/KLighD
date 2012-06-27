/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.math;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a piecewise bezier spline. This means a collection of bezier curves adding up to a
 * smooth spline.
 * 
 * @author uru
 * 
 */
public class BezierSpline {

    /**
     * internal storage for all pieces, use LinkedList, as there usually is added an arbitrary
     * number of piecewise curves to the end.
     */
    private LinkedList<BezierCurve> curves;

    /**
     * Default Constructor.
     */
    public BezierSpline() {
        this.curves = new LinkedList<BezierCurve>();
    }

    /**
     * Add a new piece of bezierCurve to the whole spline.
     * 
     * @param curve
     *            BezierCurve to add
     */
    public void addCurve(final BezierCurve curve) {
        this.curves.add(curve);
    }

    /**
     * add a whole piecewise spline to this spline.
     * 
     * @param spline
     *            spline being added
     * @param beginning
     *            if true, the new spline is added at the beginning, otherwise at the end
     */
    public void addSpline(final BezierSpline spline, final boolean beginning) {
        if (!beginning) {
            this.curves.addAll(spline.getCurves());
        } else {
            this.curves.addAll(0, spline.getCurves());
        }

    }

    /**
     * Adds a new curve to this piecewise bezier spline. The curve is represented by the startPnt,
     * endPnt and its control points.
     * 
     * @param startPnt
     *            starting point
     * @param fstCtrPnt
     *            first control point
     * @param sndCtrPnt
     *            snd control point
     * @param endPnt
     *            end point
     */
    public void addCurve(final KVector startPnt, final KVector fstCtrPnt, final KVector sndCtrPnt,
            final KVector endPnt) {
        this.curves.add(new BezierCurve(startPnt, fstCtrPnt, sndCtrPnt, endPnt));
    }

    /**
     * returns the first point of the first piece of the spline.
     * 
     * @return starting point
     */
    public KVector getStartPoint() {
        return curves.getFirst().start;
    }

    /**
     * returns the last point of the last piece of the spline.
     * 
     * @return end point
     */
    public KVector getEndPoint() {
        return curves.getLast().end;
    }

    /**
     * returns the inner points of this piecewise bezier spline.
     * 
     * @return all inner points
     */
    public KVector[] getInnerPoints() {

        if (curves.isEmpty()) {
            return new KVector[0];
        }

        // we are sure about this size
        // CHECKSTYLEOFF Magic Numbers
        int size = (curves.size() * 3) - 1;
        // CHECKSTYLEON Magic Numbers
        KVector[] points = new KVector[size];

        int i = 0;
        if (curves.size() > 1) {
            // is there a better way?
            BezierCurve lastCurve = curves.removeLast();

            // start and middle pieces
            for (BezierCurve cu : curves) {
                points[i++] = cu.fstControlPnt;
                points[i++] = cu.sndControlPnt;
                points[i++] = cu.end;
            }

            // end piece
            points[i++] = lastCurve.fstControlPnt;
            points[i++] = lastCurve.sndControlPnt;
            curves.add(lastCurve);

        } else {
            points[i++] = curves.getFirst().fstControlPnt;
            points[i++] = curves.getFirst().sndControlPnt;
        }

        return points;
    }

    /**
     * returns just the base points, including start and end point. those are the points REALLY
     * lying on the curve.
     * 
     * @return base points
     */
    public KVector[] getBasePoints() {
        int size = curves.size() + 1;
        KVector[] ret = new KVector[size];

        ret[0] = curves.getFirst().start;
        int i = 1;
        for (BezierCurve curve : curves) {
            ret[i++] = curve.end;
        }

        return ret;
    }

    /**
     * Returns a sequence of points, representing this spline as an approximated polyline.
     * 
     * @param accuracy
     *            number of points per curve
     * @return approximated set of points
     */
    public KVector[] getPolylineApprx(final int accuracy) {

        // there are #accuracy points per curve, plus the additional start point
        KVector[] apprx = new KVector[(curves.size() * accuracy) + 1];

        // add initial point
        apprx[0] = curves.getFirst().start;

        int i = 1;
        // add all further points
        for (BezierCurve curve : curves) {
            KVector[] pts = KielerMath.calcBezierPoints(curve.asVectorList(), accuracy);
            for (KVector p : pts) {
                // clone in case of latter changes
                apprx[i++] = p.clone();
            }
        }
        return apprx;
    }

    /**
     * Returns piecewise curves.
     * 
     * @return the curves
     */
    public LinkedList<BezierCurve> getCurves() {
        return curves;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (BezierCurve cu : curves) {
            s.append("" + cu.start + " -> " + cu.fstControlPnt + " -> " + cu.sndControlPnt + " -> "
                    + cu.end + "\n");
        }
        return s.toString();
    }

    /**
     * Represents a part of the whole spline consisting of start and end point, and two control
     * points.
     * 
     * Think about using a plain Vector with 4 elements!
     * 
     * @author uru
     * 
     */
    public static class BezierCurve {

        // for easier handling.
        // CHECKSTYLEOFF VisibilityModifier
        /**
         * start point.
         */
        public KVector start;
        /**
         * first control point.
         */
        public KVector fstControlPnt;
        /**
         * snd control point.
         */
        public KVector sndControlPnt;
        /**
         * end point.
         */
        public KVector end;

        // CHECKSTYLEON VisibilityModifier
        /**
         * .
         * 
         * @param startPnt
         *            starting point
         * @param fstCtrPnt
         *            first control point
         * @param sndCtrPnt
         *            snd control point
         * @param endPnt
         *            end point
         */
        public BezierCurve(final KVector startPnt, final KVector fstCtrPnt,
                final KVector sndCtrPnt, final KVector endPnt) {
            this.start = startPnt;
            this.fstControlPnt = fstCtrPnt;
            this.sndControlPnt = sndCtrPnt;
            this.end = endPnt;
        }

        /**
         * Returns this segment of the bezierspline as a list of Points.
         * 
         * @return list with points as {@code KVector}
         */
        public List<KVector> asVectorList() {
            // we are sure about this size
            // CHECKSTYLEOFF Magic Numbers
            ArrayList<KVector> list = new ArrayList<KVector>(4);
            // CHECKSTYLEON Magic Numbers
            list.add(start);
            list.add(fstControlPnt);
            list.add(sndControlPnt);
            list.add(end);
            return list;
        }
    }
}
