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
package de.cau.cs.kieler.klighd.piccolo.internal;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;

/**
 * This interface is a supplement of {@link KlighdSWTGraphics} and provides additional getters
 * methods.<br>
 * Those methods are not to be used by our PNode figures but by adapters of other 2D drawing
 * frameworks. It is currently used in
 * {@link de.cau.cs.kieler.klighd.piccolo.draw2d.GraphicsAdapter}, which is required for employing
 * custom Draw2d-based figures in KLighD-based diagrams. Those figures which save and restore the
 * canvas state before/after they draw their children.<br>
 * <br>
 * <b>Note:</b> There are no getters for {@link org.eclipse.swt.graphics.Pattern Patterns} since it
 * is currently not possible to extract the configured colors as well as the pattern points. (The
 * available method do rely on {@link org.eclipse.swt.graphics.Device Device} independent
 * configuration record data rather than the dependent ones.) For this reason
 * {@link org.eclipse.swt.graphics.Pattern Patterns} cannot be support in non-SWT graphics like an
 * SVG one.
 * 
 * @author chsch
 */
public interface KlighdSWTGraphicsEx extends KlighdSWTGraphics {

    /**
     * Returns the currently configured font attributes in form of a {@link FontData} record
     * structure.
     * 
     * @return a {@link FontData} object reflecting the currently set font attributes.
     */
    FontData getFontData();

    /**
     * Returns the currently configured line attributes in form of a {@link LineAttributes} record
     * structure.
     * 
     * @return a {@link LineAttributes} object reflecting the currently set line attributes.
     */
    LineAttributes getLineAttributes();

    /**
     * Returns the currently configured stroke color in form of an {@link RGB} record structure.
     * 
     * @return an {@link RGB} record reflecting the currently set stroke color.
     */
    RGB getStrokeColor();

    /**
     * Returns the currently configured background (fill) color in form of an {@link RGB} record
     * structure.
     * 
     * @return an {@link RGB} record reflecting the currently set background (fill) color.
     */
    RGB getFillColor();
}
