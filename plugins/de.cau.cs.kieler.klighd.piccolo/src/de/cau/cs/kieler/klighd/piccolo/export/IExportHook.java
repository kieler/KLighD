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
package de.cau.cs.kieler.klighd.piccolo.export;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsEx;

/**
 * @author csp
 */
public interface IExportHook {

    AffineTransform drawPreDiagram(KlighdSWTGraphicsEx graphics, Rectangle2D bounds);

    void drawPostDiagram(KlighdSWTGraphicsEx graphics, Rectangle2D bounds);
}
