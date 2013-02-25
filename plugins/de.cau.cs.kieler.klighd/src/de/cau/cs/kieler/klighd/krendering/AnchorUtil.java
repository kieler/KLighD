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
package de.cau.cs.kieler.klighd.krendering;

import de.cau.cs.kieler.core.krendering.KEllipse;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.math.KVector;

/**
 * Utility class for anchor point calculation of edges.
 *
 * @author msp
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
                anchorPointRoundedRectangle(point, width, height, roundedRectangle.getCornerWidth(),
                        roundedRectangle.getCornerHeight());
                break;
            case KRenderingPackage.KELLIPSE:
                anchorPointEllipse(point, width, height);
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
//        TODO
//        point.x = 10;
//        point.y = 10;
    }

    /**
     * Move anchorPoints of edges that are connected to ellipses to make the edge end on the line of the 
     * Rendering.
     * @param point the current end point of the edge to be changed by this method
     * @param width the width of the ellipse
     * @param height the height of the ellipse
     */
    public static void anchorPointEllipse(final KVector point, final double width, final double height) {
        
        //keep in mind that the radius of the ellipse is also the centerPoint of the ellipse
        double xRad = width / 2;
        double yRad = width / 2;
        
        //calculate the direction to the center of the rendering
        double xDir = point.x - xRad;
        double yDir = point.y - yRad;
        
        //calculate distance of point to center
        
        double xScale = width / height;
        double yScale = height / width;
        
        if (point.x == 0) {
            //edge is attached to the left side: calculate where the line is for the given y coordinate
            //and manipulate x coordinate accordingly
            point.x = xRad - Math.sqrt(xRad * yRad - Math.pow(yRad - point.y * xScale, 2));
        } else if (point.x == width) {
            point.x = xRad + Math.sqrt(xRad * yRad - Math.pow(yRad - point.y * xScale, 2));
        } else if (point.y == 0) {
            //TODO
            //manipulate y coordinate
//          point.y = yRad - Math.sqrt(xRad * yRad - Math.pow((point.x - xRad), 2));
//          point.y = yRad - Math.sqrt(xRad * yRad - Math.pow(xRad-point.x*xScale, 2));
        } else if (point.y == height) {
//          point.y = Math.sqrt(xRad * yRad - Math.pow(xRad - point.x * yScale, 2));
        }
     }
}
