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
package de.cau.cs.kieler.klighd.internal.macrolayout;

import java.util.Iterator;
import java.util.List;

import org.eclipse.elk.core.math.KVector;

import de.cau.cs.kieler.klighd.krendering.KPolygon;
import de.cau.cs.kieler.klighd.krendering.KPosition;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;
import de.cau.cs.kieler.klighd.krendering.KRenderingUtil;
import de.cau.cs.kieler.klighd.krendering.KRoundedRectangle;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;

/**
 * <p>
 * Utility class for anchor point calculation of edges. This class provides two public methods. The
 * first, {@link #nearestBorderPoint(KVector, double, double, KRendering, float)}, takes a reference
 * point and returns the point on the figure's border that is nearest to that reference point. This
 * method is usually used to ensure that edges touch the border of nodes or ports they connect to.
 * The second, {@link #collideTowardsCenter(KVector, double, double, KRendering)}, calculates the
 * point where a line through a given reference point and the figure's center would intersect the
 * figure's border. This is usually used to calculate the end points of edges not included in
 * automatic layout.
 * </p>
 * 
 * <p>
 * All methods assume that the coordinates of the reference point and the returned intersection
 * point are relative to the figure's top left corner.
 * </p>
 * 
 * @author msp
 * @author chsch
 * @author cds
 * 
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch 
 */
public final class AnchorUtil {

    /**
     * Hidden constructor to prevent instantiation.
     */
    private AnchorUtil() {
    }
    

    // This is mathematics stuff; there will be magic numbers, but they are easy and don't require any
    // bloody constants, for god's sake...
    // CHECKSTYLEOFF MagicNumber
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Find Nearest Border Point
    
    /**
     * Returns the coordinates of the point on the border of the given figure that is nearest to the
     * given point. For more information, please see the {@link AnchorUtil class documentation}.
     * 
     * @param point
     *            the other end point of the edge, relative to the upper left corner of the figure's
     *            bounding box.
     * @param width
     *            the width of the figure's bounding box. Must be {@code >=0}.
     * @param height
     *            the height of the figure's bounding box. Must be {@code >=0}.
     * @param rendering
     *            the rendering associated with the figure. If this is {@code null}, a simple
     *            rectangle will be assumed.
     * @param scale
     *            the connected node's
     *            {@link de.cau.cs.kieler.kiml.options.LayoutOptions#SCALE_FACTOR SCALE_FACTOR} for
     *            adjusting corner sizes of {@link KRoundedRectangle KRoundedRectangles} and
     *            port positions
     * @return the point on the figure's border that is nearest to the given point, relative to the
     *         figure's upper left corner.
     * @throws IllegalArgumentException
     *             if {@code width} or {@code height} are negative.
     */
    public static KVector nearestBorderPoint(final KVector point, final double width,
            final double height, final KRendering rendering, final float scale) {

        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("width (" + width + ") and height (" + height
                    + ") must be >= 0.");
        }

        // resolve rendering references
        final KRendering actualRendering = KRenderingUtil.dereference(rendering);
        
        if (actualRendering != null) {
            switch (actualRendering.eClass().getClassifierID()) {
            case KRenderingPackage.KROUNDED_RECTANGLE:
                final KRoundedRectangle roundedRectangle = (KRoundedRectangle) actualRendering;
                
                double cornerWidth = roundedRectangle.getCornerWidth() * scale;
                cornerWidth = 2 * cornerWidth <= width ? cornerWidth : width / 2;
                
                double cornerHeight = roundedRectangle.getCornerHeight() * scale;
                cornerHeight = 2 * cornerHeight <= height ? cornerHeight : height / 2;
                
                return nearestBorderPointRoundedRectangle(point, width, height, cornerWidth,
                        cornerHeight);
            case KRenderingPackage.KELLIPSE:
                // For ellipses we want to draw towards the center
                return collideTowardsEllipseCenter(point, width, height);
            }
        }
        
        return nearestBorderPointRectangle(point, width, height);
    }
    
    /**
     * Implements {@link #nearestBorderPoint(KVector, double, double, KRendering, float)} for rectangles.
     * 
     * @param point the other end point of the edge, relative to the upper left corner of the figure's
     *              bounding box.
     * @param width the width of the figure's bounding box. Must be {@code >=0}.
     * @param height the height of the figure's bounding box. Must be {@code >=0}.
     * @return the point on the figure's border that is nearest to the given point, relative to the
     *         figure's upper left corner.
     */
    private static KVector nearestBorderPointRectangle(final KVector point, final double width,
            final double height) {
        
        final KVector result = new KVector(point);
        
        if (point.x < 0) {
            result.x = 0;
        } else if (point.x > width) {
            result.x = width;
        }
        
        if (point.y < 0) {
            result.y = 0;
        } else if (point.y > height) {
            result.y = height;
        }
        
        return result;
    }
    
    /**
     * Implements {@link #nearestBorderPoint(KVector, double, double, KRendering, float)} for rounded
     * rectangles with the given dimensions and corner specifications.
     * 
     * @param point the other end point of the edge, relative to the upper left corner of the figure's
     *              bounding box.
     * @param width the width of the figure's bounding box. Must be {@code >=0}.
     * @param height the height of the figure's bounding box. Must be {@code >=0}.
     * @param cornerWidth corner width of the rounded rectangle.
     * @param cornerHeight corner height of the rounded rectangle.
     * @return the point on the figure's border that is nearest to the given point, relative to the
     *         figure's upper left corner.
     */
    private static KVector nearestBorderPointRoundedRectangle(final KVector point, final double width,
            final double height, final double cornerWidth, final double cornerHeight) {

        assert width >= 0 : "width = " + width;
        assert height >= 0 : "height = " + height;
        
        final double rectWidthWithoutCornerWidth = width - cornerWidth;
        final double rectWidthWithoutTwiceCornerWidth = rectWidthWithoutCornerWidth - cornerWidth;
        final double rectHeightWidthoutCornerHeight = height - cornerHeight;
        final double rectHeightWithoutTwiceCornerHeight = rectHeightWidthoutCornerHeight - cornerHeight;
        
        KVector result = new KVector(point);
        
        // We determine the movement of the anchors by delegating to the ellipse case.
        //  To this end, we distinguish the following cases and adjust the width and height of the
        //  imaginary ellipse accordingly by subtracting the non rounded size fractions and re-adding
        //  them afterwards.
        
        if (point.x <= 0) {
            if (point.y <= cornerHeight) {                
                result = collideTowardsEllipseCenter(result, 2 * cornerWidth, 2 * cornerHeight);
            } else if (point.y >= rectHeightWidthoutCornerHeight) {
                result.y -= rectHeightWithoutTwiceCornerHeight;
                result = collideTowardsEllipseCenter(result, 2 * cornerWidth, 2 * cornerHeight);
                result.y += rectHeightWithoutTwiceCornerHeight;
            } else {
                result.x = 0;
            }
        }
        
        if (point.x >= width) {
            if (point.y <= cornerHeight) {                
                result.x -= rectWidthWithoutTwiceCornerWidth;
                result = collideTowardsEllipseCenter(result, 2 * cornerWidth, 2 * cornerHeight);
                result.x += rectWidthWithoutTwiceCornerWidth;
            } else if (point.y >= rectHeightWidthoutCornerHeight) {
                result.x -= rectWidthWithoutTwiceCornerWidth;
                result.y -= rectHeightWithoutTwiceCornerHeight;
                result = collideTowardsEllipseCenter(result, 2 * cornerWidth, 2 * cornerHeight);
                result.x += rectWidthWithoutTwiceCornerWidth;
                result.y += rectHeightWithoutTwiceCornerHeight;
            } else {
                result.x = width;
            }
        }
        
        if (point.y <= 0) {
            if (point.x <= cornerWidth) {                
                result = collideTowardsEllipseCenter(result, 2 * cornerWidth, 2 * cornerHeight);
            } else if (point.x >= rectWidthWithoutCornerWidth) {
                result.x -= rectWidthWithoutTwiceCornerWidth;
                result = collideTowardsEllipseCenter(result, 2 * cornerWidth, 2 * cornerHeight);
                result.x += rectWidthWithoutTwiceCornerWidth;
            } else {
                result.y = 0;
            }
        }
        
        if (point.y >= height) {
            if (point.x <= cornerWidth) {                
                result.y -= rectHeightWithoutTwiceCornerHeight;
                result = collideTowardsEllipseCenter(result, 2 * cornerWidth, 2 * cornerHeight);
                result.y += rectHeightWithoutTwiceCornerHeight;
            } else if (point.x >= rectWidthWithoutCornerWidth) {
                result.x -= rectWidthWithoutTwiceCornerWidth;
                result.y -= rectHeightWithoutTwiceCornerHeight;
                result = collideTowardsEllipseCenter(result, 2 * cornerWidth, 2 * cornerHeight);
                result.x += rectWidthWithoutTwiceCornerWidth;
                result.y += rectHeightWithoutTwiceCornerHeight;
            } else {
                result.y = height;
            }
        }
        
        return result;
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Collide Towards Center
    
    /**
     * Returns the coordinates of the point where an edge from the given point to the center of a figure
     * with the given dimensions would intersect the figure. The coordinate {@code (0,0)} is assumed to
     * mark the upper left corner of the figure's bounding box, while {@code (width, height)} marks the
     * bottom right corner. The coordinates of the given end point of the edge must be relative to the
     * upper left corner of the figure's bounding box. The returned point will be relative to it as well.
     * 
     * @param point the other end point of the edge.
     * @param width the width of the figure's bounding box. Must be {@code >=0}.
     * @param height the height of the figure's bounding box. Must be {@code >=0}.
     * @param rendering the rendering associated with the figure. If this is {@code null}, a simple
     *                  rectangle will be assumed.
     * @return a point where the edge from the given end point to the figure's center would intersect
     *         the figure.
     * @throws IllegalArgumentException if {@code width} or {@code height} are negative.
     */
    public static KVector collideTowardsCenter(final KVector point, final double width,
            final double height, final KRendering rendering) {
        
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("width (" + width + ") and height (" + height
                    + ") must be >= 0.");
        }
        
        if (rendering != null) {
            switch (rendering.eClass().getClassifierID()) {
            case KRenderingPackage.KELLIPSE:
                return collideTowardsEllipseCenter(point, width, height);
            case KRenderingPackage.KPOLYGON:
                final KPolygon polygon = (KPolygon) rendering;
                return collideTowardsPolygonCenter(point, width, height, polygon.getPoints());
            default:
                return collideTowardsRectangleCenter(point, width, height);
            }
        } else {
            return collideTowardsRectangleCenter(point, width, height);
        }
    }
    
    /**
     * Implements {@link #collideTowardsCenter(KVector, double, double, KRendering)} for rectangles.
     * 
     * @param point the point where the line should start.
     * @param width the width of the figure's bounding box. Must be {@code >=0}.
     * @param height the height of the figure's bounding box. Must be {@code >=0}.
     * @return a point where the edge from the given end point to the figure's center would intersect
     *         the figure.
     */
    private static KVector collideTowardsRectangleCenter(final KVector point, final double width,
            final double height) {
        
        assert width >= 0 : "width = " + width;
        assert height >= 0 : "height = " + height;
        
        KVector result;
        final KVector center = new KVector(width / 2.0, height / 2.0);
        
        // Check if the point is outside of the rectangle's bounds (only then do we need to calculate
        // an intersection point)
        if (point.y < 0 || point.y > height || point.x < 0 || point.x > width) {
            // Intersection edge -> top border
            result = intersectLines(0, 0, width, 0, point.x, point.y, center.x, center.y);
            if (result != null) {
                return result;
            }
            
            // Intersection edge -> bottom border
            result = intersectLines(0, height, width, height, point.x, point.y, center.x, center.y);
            if (result != null) {
                return result;
            }
            
            // Intersection edge -> left border
            result = intersectLines(0, 0, 0, height, point.x, point.y, center.x, center.y);
            if (result != null) {
                return result;
            }
            
            // Intersection edge -> right border
            result = intersectLines(width, 0, width, height, point.x, point.y, center.x, center.y);
            if (result != null) {
                return result;
            }
        }
        
        
        // Return the point by default
        return new KVector(center);
    }
    
    /**
     * Implements {@link #collideTowardsCenter(KVector, double, double, KRendering)} for ellipses.
     * 
     * @param point the point where the line should start.
     * @param width the width of the figure's bounding box. Must be {@code >=0}.
     * @param height the height of the figure's bounding box. Must be {@code >=0}.
     * @return a point where the edge from the given end point to the figure's center would intersect
     *         the figure.
     */
    private static KVector collideTowardsEllipseCenter(final KVector point, final double width,
            final double height) {
        
        assert width >= 0 : "width = " + width;
        assert height >= 0 : "height = " + height;
        
        // An ellipse can be defined by the equation x^2 / a^2 + y^2 / b^2 = 1, with the center being
        // at coordinate (0,0)
        final double a = width * 0.5;
        final double b = height * 0.5;
        
        // Since we're assuming (0,0) to be the center of the ellipse instead of its top left corner,
        // we will need to offset the point accordingly
        final KVector offsetPoint = new KVector(point.x - a, point.y - b);
        
        // We will describe our line through (0,0) and offsetPoint by two equations:
        //   x(t) = offsetPoint.x * t
        //   y(t) = offsetPoint.y * t
        // The goal is to find 0 <= t0 <= 1 such, that (x(t0), y(t0)) is the intersection between
        // the ellipse and the line
        final double determinant = offsetPoint.x * offsetPoint.x / (a * a)
                + offsetPoint.y * offsetPoint.y / (b * b);
        if (determinant == 0) {
            // This can only happen if offsetPoint == (0,0)
            return new KVector(point);
        }
        
        // Find t0
        final double t0 = 1.0 / Math.sqrt(determinant);
        
        // The result is the intersection point, corrected by the offset we put on offsetPoint earlier
        return new KVector(offsetPoint.x * t0 + a, offsetPoint.y * t0 + b);
    }
    
    /**
     * Returns the point where a line starting at the given point and ending in the polygon's center
     * would intersect the polygon's border.
     * 
     * @param point the point where the line should start.
     * @param width the width of the figure's bounding box. Must be {@code >=0}.
     * @param height the height of the figure's bounding box. Must be {@code >=0}.
     * @param polygonPoints the points that define the polygon. The first and the last point in the list
     *                      are assumed to be connected by line as well.
     * @return a point where the edge from the given end point to the figure's center would intersect
     *         the figure.
     */
    private static KVector collideTowardsPolygonCenter(final KVector point, final double width,
            final double height, final List<KPosition> polygonPoints) {
        
        assert width >= 0 : "width = " + width;
        assert height >= 0 : "height = " + height;
        
        final Bounds figureBounds = new Bounds(width, height);
        
        // We need at least three points to define a proper polygon
        if (polygonPoints.size() < 3) {
            return new KVector(point);
        }
        
        // We'll start by setting the result to the center of the figure. We then update the result to
        // the intersection point whenever we find one, effectively shortening the edge from the
        // reference point toward the figure's center
        KVector result = new KVector(width / 2, height / 2);
        
        // Iterate over the polygon points, remembering the last two
        final Iterator<KPosition> polygonPointsIterator = polygonPoints.iterator();
        final KVector firstPoint = PlacementUtil.evaluateKPosition(
                polygonPointsIterator.next(), figureBounds, true).toKVector();
        
        KVector segmentStart = null;
        KVector segmentEnd = firstPoint;
        
        while (polygonPointsIterator.hasNext()) {
            // Move to the next polygon segment
            segmentStart = segmentEnd;
            segmentEnd = PlacementUtil.evaluateKPosition(
                    polygonPointsIterator.next(), figureBounds, true).toKVector();
            
            // Check if there is an intersection between the current segment and (point->result)
            final KVector intersection = intersectLines(
                    segmentStart.x, segmentStart.y, segmentEnd.x, segmentEnd.y,
                    point.x, point.y, result.x, result.y);
            
            if (intersection != null) {
                result = intersection;
            }
        }
        
        // We now have the last point of the polygon's points in segmentEnd, but we have not yet checked
        // the last segment (segmentEnd -> polygonPoints.get(0)) for an intersection
        final KVector intersection = intersectLines(
                firstPoint.x, firstPoint.y, segmentEnd.x, segmentEnd.y,
                point.x, point.y, result.x, result.y);
        
        if (intersection != null) {
            result = intersection;
        }
        
        return result;
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Utility Functions
    
    /**
     * Fuzzyness amount allowed to catch rounding errors. In the method below, the calculated
     * intersection point is subjected to a sanity check to find out if it actually is inside the
     * bounding boxes of the two lines that are being intersected. Due to rounding errors, this sanity
     * check can fail even though the result is very nearly correct. The fuzzyness is the fuzzy area
     * around the bounding boxes that we still consider to be part of the bounding box to catch these
     * errors.
     */
    private static final double FA = 0.1;
    
    /**
     * Returns the point where the two lines given by the four points intersect, if they do.
     * 
     * @param x1 x coordinate of the first line's start point.
     * @param y1 y coordinate of the first line's start point.
     * @param x2 x coordinate of the first line's end point.
     * @param y2 y coordinate of the first line's end point.
     * @param x3 x coordinate of the second line's start point.
     * @param y3 y coordinate of the second line's start point.
     * @param x4 x coordinate of the second line's end point.
     * @param y4 y coordinate of the second line's end point.
     * @return the point where the two lines intersect, or {@code null} if they don't.
     */
    // SUPPRESS CHECKSTYLE NEXT ParameterNumber -- we need the 8 points here
    private static KVector intersectLines(final double x1, final double y1, final double x2,
            final double y2, final double x3, final double y3, final double x4, final double y4) {
        
        /* This code is based on calculating line intersections with determinants. See
         * http://en.wikipedia.org/wiki/Line-line_intersection#Mathematics for the mathematical
         * details.
         */
        
        // Calculate the divisor
        final double divisor = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        
        // If the divisor is 0, the lines are parallel
        if (divisor == 0.0) {
            return null;
        }
        
        // Calculate a few values that are used often
        final double x1y2minusy1x2 = x1 * y2 - y1 * x2;
        final double x3y4minusy3x4 = x3 * y4 - y3 * x4;
        
        // Calculate the coordinates of the intersection
        final KVector res = new KVector(
                (x1y2minusy1x2 * (x3 - x4) - (x1 - x2) * x3y4minusy3x4) / divisor,
                (x1y2minusy1x2 * (y3 - y4) - (y1 - y2) * x3y4minusy3x4) / divisor);

        // SUPPRESS CHECKSTYLE NEXT LineLength|ParenPad -- don't fuss,
        if (   ((x1 - FA <= res.x && res.x <= x2 + FA) || (x1 + FA >= res.x && res.x >= x2 - FA))
            && ((x3 - FA <= res.x && res.x <= x4 + FA) || (x3 + FA >= res.x && res.x >= x4 - FA))
            && ((y1 - FA <= res.y && res.y <= y2 + FA) || (y1 + FA >= res.y && res.y >= y2 - FA))
            && ((y3 - FA <= res.y && res.y <= y4 + FA) || (y3 + FA >= res.y && res.y >= y4 - FA))) {
            
            // i.e. if the intersection lies inside the rectangle spanned by the two lines' end points
            return res;

        } else {
            // the lines don't intersect
            return null;
        }
    }
}
