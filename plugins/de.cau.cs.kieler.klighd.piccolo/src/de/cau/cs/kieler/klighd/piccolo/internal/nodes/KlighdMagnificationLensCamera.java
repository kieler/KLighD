/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import edu.umd.cs.piccolo.PCamera;

/**
 * A specialized {@link PCamera} used for implementing the magnification lens.<br>
 * This dedicated type currently only required for distinguishing the magnification lens camera from
 * the main camera or other cameras, e.g. those employed in {@link KNodeNode KNodeNodes} for
 * properly implementing the diagram clipping, or those employed in {@link KEdgeNode KEdgeNodes} for
 * implementing the junction points.
 * 
 * @author chsch
 */
public class KlighdMagnificationLensCamera extends PCamera {

    private static final long serialVersionUID = 7681288801025861377L;

}
