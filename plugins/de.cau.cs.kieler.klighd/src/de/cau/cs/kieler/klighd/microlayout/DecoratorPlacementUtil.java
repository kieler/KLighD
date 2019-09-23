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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.microlayout;

import java.awt.geom.Point2D;

import org.eclipse.elk.core.util.Pair;

import de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData;
import de.cau.cs.kieler.klighd.krendering.KPlacementData;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingRef;
import de.cau.cs.kieler.klighd.krendering.KRenderingUtil;
import de.cau.cs.kieler.klighd.util.MathUtil;

/**
 * A utility class for evaluating the placement of decorating KRenderings.
 * 
 * @author mri
 */
public final class DecoratorPlacementUtil {

    /**
     * A private constructor to prevent instantiation.
     */
    private DecoratorPlacementUtil() {
        // do nothing
    }

    /**
     * Returns the bounds and rotation for a decorator placement data on a given path.
     * 
     * @param dpd
     *            the decorator placement data
     * @param path
     *            the points on the path
     * @return the origin, bounds and rotation for the decorator
     */
    public static Decoration evaluateDecoratorPlacement(final KDecoratorPlacementData dpd,
            final Point2D[] path) {
        Decoration decoration = new Decoration();

        Point2D[] points = null; 
        if (path != null) {
            points = path;
        }
        
        if (points == null) {
            points = new Point2D[] {
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 0)
            };
        }
        
        // default case
        if (dpd == null) {
            decoration.origin = (Point2D) points[0].clone();
            decoration.bounds = new Bounds(0, 0);
            decoration.rotation = 0;
            return decoration;
        }

        // get the segment and concrete point the location specifies on the path
        Pair<Integer, Point2D> result = MathUtil.getSegmentStartIndexAndPoint(points,
                dpd.getAbsolute(), dpd.getRelative());
        decoration.origin = result.getSecond();

        // calculate the decorator bounds
        decoration.bounds = new Bounds(dpd.getXOffset(), dpd.getYOffset(), dpd.getWidth(),
                dpd.getHeight());

        // if the decorator placement data specifies it to be relative calculate its rotation
        if (dpd.isRotateWithLine() && points.length > 1) {
            int segmentStart = result.getFirst();
            decoration.rotation = (float) MathUtil.angle(points[segmentStart], points[segmentStart + 1]);
        } else {
            decoration.rotation = 0;
        }

        return decoration;
    }

    /**
     * Returns the given placement data as decorator placement data.
     * 
     * @param data
     *            the placement data
     * @return the decorator placement data or null if the placement data is no decorator placement
     *         data
     */
    public static KDecoratorPlacementData asDecoratorPlacementData(final KPlacementData data) {
        if (data instanceof KDecoratorPlacementData) {
            return (KDecoratorPlacementData) data;
        }
        return null;
    }

    /**
     * Reveals {@link KDecoratorPlacementData} in a given {@link KRendering}.
     * 
     * @param rendering
     *            the {@link KRendering}
     * @return the desired {@link KDecoratorPlacementData}, or <code>null</code> if none are given.
     */
    public static KDecoratorPlacementData getDecoratorPlacementData(final KRendering rendering) {
        final KDecoratorPlacementData data = asDecoratorPlacementData(
                KRenderingUtil.getPlacementData(rendering));
        if (data == null && rendering instanceof KRenderingRef) {
            return getDecoratorPlacementData(((KRenderingRef) rendering).getRendering());
        } else {
            return data;
        }
    }

    /**
     * A data holder class for the result of evaluating a decorator.
     */
    public static class Decoration {

        /** the origin of the decoration. */
        private Point2D origin;
        /** the bounds of the decoration. */
        private Bounds bounds;
        /** the rotation of the decoration. */
        private float rotation;

        /**
         * Returns the origin of the decoration.
         * 
         * @return the origin
         */
        public final Point2D getOrigin() {
            return origin;
        }

        /**
         * Returns the bounds of the decoration.
         * 
         * @return the bounds
         */
        public final Bounds getBounds() {
            return bounds;
        }

        /**
         * Returns the rotation of the decoration.
         * 
         * @return the rotation
         */
        public final double getRotation() {
            return rotation;
        }
    }
    
}
