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

import de.cau.cs.kieler.core.krendering.KRendering;
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
    
    public static void anchorPoint(final KVector point, final double width, final double height,
            final KRendering rendering) {
        if (rendering == null) {
            // if no rendering is specified, assume a rectangle
            anchorPointRectangle(point, width, height);
        } else {
            switch (rendering.eClass().getClassifierID()) {
            
            default:
                anchorPointRectangle(point, width, height);
            }
        }
    }
    
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

}
