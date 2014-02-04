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

import java.util.LinkedList;

/**
 * Global interface for any Spline interpolator.
 * 
 * @author uru
 * @kieler.design proposed 2012-11-02 cds
 * @kieler.rating proposed yellow 2012-11-02 cds
 */
public interface ISplineInterpolator {

    /**
     * returns a piecewise bezierspline.
     * 
     * @param points
     *            as an array, see implementing class if to prefer Vector or List implementation.
     * @return piecewise bezierspline
     */
    BezierSpline interpolatePoints(KVector[] points);

    /**
     * returns a piecewise bezierspline.
     * 
     * @param points
     *            as an array, see implementing class if to prefer Vector or List implementation.
     * @param startVec
     *            tangent vector specifying to head out of the first node
     * @param endVec
     *            tangent vector specifying to head into the last node
     * @param tangentScale
     *            if true, the tangent is scaled depending on the distance to the next ctr point, if
     *            false the tangent is used as passed
     * @return piecewise bezierspline
     */
    BezierSpline interpolatePoints(KVector[] points, KVector startVec, KVector endVec,
            boolean tangentScale);

    /**
     * returns a piecewise bezierspline.
     * 
     * @param points
     *            as an array, see implementing class if to prefer Vector or List implementation.
     * @return piecewise bezierspline
     */
    BezierSpline interpolatePoints(LinkedList<KVector> points);

    /**
     * returns a piecewise bezierspline.
     * 
     * @param points
     *            as an array, see implementing class if to prefer Vector or List implementation.
     * @param startVec
     *            tangent vector specifying to head out of the first node
     * @param endVec
     *            tangent vector specifying to head into the last node
     * @param tangentScale
     *            if true, the tangent is scaled depending on the distance to the next ctr point, if
     *            false the tangent is used as passed
     * @return piecewise bezierspline
     */
    BezierSpline interpolatePoints(LinkedList<KVector> points, KVector startVec, KVector endVec,
            boolean tangentScale);
}
