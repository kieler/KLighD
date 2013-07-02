/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.macrolayout;

import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.math.KVector;

/**
 * Utility class for anchor point calculation of edges.
 *
 * @author msp, chsch
 */
public final class AnchorUtil {

    /**
     * Hidden constructor to prevent instantiation.
     */
    private AnchorUtil() {
    }
    
    /**
     * Correct the given anchor point.
     * The point must be transformed to the local coordinates of the corresponding node
     * (or port, if applicable) before this method is called.
     * This means that the point (0,0) marks the upper left corner of the node or port,
     * while (width,height) marks the bottom right corner.
     * 
     * @param point an anchor point of an edge
     * @param width the width of the corresponding node or port
     * @param height the height of the corresponding node or port
     * @param rendering the rendering associated with the node or port, or {@code null} if none
     */
    public static void anchorPoint(final KVector point, final double width, final double height,
            final KRendering rendering) {
        if (rendering == null) {
            // if no rendering is specified, assume a rectangle
            anchorPointRectangle(point, width, height);
        } else {
            switch (rendering.eClass().getClassifierID()) {
            case KRenderingPackage.KROUNDED_RECTANGLE:
                KRoundedRectangle roundedRectangle = (KRoundedRectangle) rendering;
                double cornerWidth = roundedRectangle.getCornerWidth();
                cornerWidth = 2 * cornerWidth <= width ? cornerWidth : width / 2;
                double cornerHeight = roundedRectangle.getCornerHeight();
                cornerHeight = 2 * cornerHeight <= height ? cornerHeight : height / 2;
                
                anchorPointRoundedRectangle(point, width, height, cornerWidth, cornerHeight);
                break;
            case KRenderingPackage.KELLIPSE:
                anchorPointEllipse(point, width, height);
                break;
            default:
                anchorPointRectangle(point, width, height);
            }
        }
    }
    
    /**
     * Correct the given anchor point for rectangle rendering.
     * The point must be transformed to the local coordinates of the corresponding node
     * (or port, if applicable) before this method is called.
     * This means that the point (0,0) marks the upper left corner of the node or port,
     * while (width,height) marks the bottom right corner.
     * 
     * @param point an anchor point of an edge
     * @param width the width of the corresponding node or port
     * @param height the height of the corresponding node or port
     */
    public static void anchorPointRectangle(final KVector point, final double width,
            final double height) {
        if (point.x < 0) {
            point.x = 0;
        } else if (point.x > width) {
            point.x = width;
        }
        
        if (point.y < 0) {
            point.y = 0;
        } else if (point.y > height) {
            point.y = height;
        }
    }
    

    
    /**
     * Correct the given anchor point for rounded rectangle rendering.
     * The point must be transformed to the local coordinates of the corresponding node
     * (or port, if applicable) before this method is called.
     * This means that the point (0,0) marks the upper left corner of the node or port,
     * while (width,height) marks the bottom right corner.
     * 
     * @param point an anchor point of an edge
     * @param rectWidth the width of the corresponding node or port
     * @param rectHeight the height of the corresponding node or port
     * @param cornerWidth the corner width
     * @param cornerHeight the corner height
     */
    public static void anchorPointRoundedRectangle(final KVector point, final double rectWidth,
            final double rectHeight, final double cornerWidth, final double cornerHeight) {
        
        final double rectWidthWithoutCornerWidth = rectWidth - cornerWidth;
        final double rectWidthWithoutTwiceCornerWidth = rectWidthWithoutCornerWidth - cornerWidth;
        final double rectHeightWidthoutCornerHeight = rectHeight - cornerHeight;
        final double rectHeightWithoutTwiceCornerHeight = rectHeightWidthoutCornerHeight - cornerHeight;
        
        final double x = point.x;
        final double y = point.y;
        
        // We determine the movement of the anchors by delegating to the ellipse case.
        //  To this end, we distinguish the following cases and adjust the width and height of the
        //  imaginary ellipse accordingly by subtracting the non rounded size fractions and re-adding
        //  them afterwards.
        
        if (x <= 0) {
            if (y <= cornerHeight) {                
                anchorPointEllipse(point, 2 * cornerWidth, 2 * cornerHeight);
            } else if (y >= rectHeightWidthoutCornerHeight) {
                point.y -= rectHeightWithoutTwiceCornerHeight;
                anchorPointEllipse(point, 2 * cornerWidth, 2 * cornerHeight);
                point.y += rectHeightWithoutTwiceCornerHeight;
            } else {
                point.x = 0;
            }
        }
        
        if (x >= rectWidth) {
            if (y <= cornerHeight) {                
                point.x -= rectWidthWithoutTwiceCornerWidth;
                anchorPointEllipse(point, 2 * cornerWidth, 2 * cornerHeight);
                point.x += rectWidthWithoutTwiceCornerWidth;
            } else if (y >= rectHeightWidthoutCornerHeight) {
                point.x -= rectWidthWithoutTwiceCornerWidth;
                point.y -= rectHeightWithoutTwiceCornerHeight;
                anchorPointEllipse(point, 2 * cornerWidth, 2 * cornerHeight);
                point.x += rectWidthWithoutTwiceCornerWidth;
                point.y += rectHeightWithoutTwiceCornerHeight;
            } else {
                point.x = rectWidth;
            }
        }
        if (y <= 0) {
            if (x <= cornerWidth) {                
                anchorPointEllipse(point, 2 * cornerWidth, 2 * cornerHeight);
            } else if (x >= rectWidthWithoutCornerWidth) {
                point.x -= rectWidthWithoutTwiceCornerWidth;
                anchorPointEllipse(point, 2 * cornerWidth, 2 * cornerHeight);
                point.x += rectWidthWithoutTwiceCornerWidth;
            } else {
                point.y = 0;
            }
        }
        
        if (y >= rectHeight) {
            if (x <= cornerWidth) {                
                point.y -= rectHeightWithoutTwiceCornerHeight;
                anchorPointEllipse(point, 2 * cornerWidth, 2 * cornerHeight);
                point.y += rectHeightWithoutTwiceCornerHeight;
            } else if (x >= rectWidthWithoutCornerWidth) {
                point.x -= rectWidthWithoutTwiceCornerWidth;
                point.y -= rectHeightWithoutTwiceCornerHeight;
                anchorPointEllipse(point, 2 * cornerWidth, 2 * cornerHeight);
                point.x += rectWidthWithoutTwiceCornerWidth;
                point.y += rectHeightWithoutTwiceCornerHeight;
            } else {
                point.y = rectHeight;
            }
        }
    }
    
    /**
     * Move anchorPoints of edges that are connected to ellipses to make the edge end on the line of the 
     * Rendering.
     * @param point the current end point of the edge to be changed by this method
     * @param width the width of the ellipse
     * @param height the height of the ellipse
     */
    public static void anchorPointEllipse(final KVector point, final double width, final double height) {

        // By means of the following width-height-ratio we can abstract the ellipse by a circle with
        // the radius 'rad'.
        final double heightRelation = width / height;
        final double radius = width / 2;

        final double normX = point.x;
        final double normY = point.y * heightRelation;

        // The basic idea of this anchor point movement is the shift along the axis of the center of the
        //  imaginary circle and the current point. In order to understand the process easier the
        //  following 4 cases are distinguished, each of them distinguishes two more.
        // First the angle of the above mentioned axis is determined by means of the arcTan function.
        //  By means of this angle and our desired hypotenuse of 'radius' we can calculate the desired
        //  adjacent and opposite sides of the imaginary rectangle. Those two values form our new x and
        //  y coordinates adjusted depending on the current case.   

        if (point.x <= 0) {
            if (normY <= radius) {
                double angle = Math.atan((radius - normY) / radius);
                point.x = radius - Math.cos(angle) * radius;
                point.y = (radius - Math.sin(angle) * radius) / heightRelation;

            } else {
                double angle = Math.atan((normY - radius) / radius);
                point.x = radius - Math.cos(angle) * radius;
                point.y = (radius + Math.sin(angle) * radius) / heightRelation;
            }
        } else if (point.x >= width) {
            if (normY <= radius) {
                double angle = Math.atan((radius - normY) / radius);
                point.x = radius + Math.cos(angle) * radius;
                point.y = (radius - Math.sin(angle) * radius) / heightRelation;

            } else {
                double angle = Math.atan((normY - radius) / radius);
                point.x = radius + Math.cos(angle) * radius;
                point.y = (radius + Math.sin(angle) * radius) / heightRelation;
            }
        } else if (point.y <= 0) {
            if (normX <= radius) {
                double angle = Math.atan((radius - normX) / radius);
                point.x = radius - Math.sin(angle) * radius;
                point.y = (radius - Math.cos(angle) * radius) / heightRelation;

            } else {
                double angle = Math.atan((normX - radius) / radius);
                point.x = radius + Math.sin(angle) * radius;
                point.y = (radius - Math.cos(angle) * radius) / heightRelation;
            }
        } else if (point.y >= height) {
            if (normX <= radius) {
                double angle = Math.atan((radius - normX) / radius);
                point.x = radius - Math.sin(angle) * radius;
                point.y = (radius + Math.cos(angle) * radius) / heightRelation;

            } else {
                double angle = Math.atan((normX - radius) / radius);
                point.x = radius + Math.sin(angle) * radius;
                point.y = (radius + Math.cos(angle) * radius) / heightRelation;
            }
        }
    }
}
