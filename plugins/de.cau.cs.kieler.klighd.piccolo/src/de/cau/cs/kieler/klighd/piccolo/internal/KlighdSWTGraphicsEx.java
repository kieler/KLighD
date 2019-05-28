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
package de.cau.cs.kieler.klighd.piccolo.internal;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
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
     * Returns the SWT {@link GC} to draw on.
     * 
     * @return the {@link GC} to work with, or <code>null</code>, if a non-SWT canvas is used
     */
    GC getGC();

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
     * Returns the currently configured original line width without any adjustments potentially
     * performed by the (native) rendering layer.
     * 
     * @return the currently configured original <em>unadjusted</em> line width.
     */
    float getLineWidth();

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
    
    /**
     * Disposes incorporated {@link org.eclipse.swt.graphics.Resource Resources} like
     * {@link org.eclipse.swt.graphics.Color Colors} {@link org.eclipse.swt.graphics.Font Fonts},
     * {@link org.eclipse.swt.graphics.TextLayout TextLayouts}, etc.
     */
    void dispose();

    /**
     * First, this method is only effective for the SWT-based implementation, it is effectless for
     * the SVG-based one.<br>
     * <br>
     * It stops the caching of newly instantiated {@link org.eclipse.swt.graphics.Font SWT Fonts}
     * by means of an internal static hash map. That caching is done for performance reasons and
     * because of the limited number of handles for tracking the corresponding native objects.<br>
     * <br>
     * In caching mode (being active by initialization) {@link org.eclipse.swt.graphics.Font SWT
     * Fonts} are always instantiated in context of the current
     * {@link org.eclipse.swt.widgets.Display Display} in order to preserve consistent font
     * initializations amongst all text label parts in the diagram.<br>
     * <br>
     * In non-caching mode activated by this method {@link org.eclipse.swt.graphics.Font SWT Fonts}
     * are instantiated in context of the current {@link #getDevice() Device} or a specific device
     * dedicated to font creation, if configured during initialization of <code>this</code>
     * graphics. Those fonts are kept in a list and disposed while calling
     * {@link #resumeFontCaching()}.<br>
     * <br>
     * This non-caching mode is required for drawing background and overlay text enforced by
     * {@link de.cau.cs.kieler.klighd.IExportBranding IExportBrandings} on printouts. The required
     * fonts must be created in context of the chosen printer since the font size is resolution
     * (DPI) dependent.
     */
    void stopFontCaching();

    /**
     * First, this method is only effective for the SWT-based implementation, it is effectless for
     * the SVG-based one.<br>
     * <br>
     * Re-activates the caching of {@link org.eclipse.swt.graphics.Font SWT Fonts} and disposes all
     * fonts used since the last call {@link #stopFontCaching()}. See {@link #stopFontCaching()} for
     * details.
     */
    void resumeFontCaching();
}
