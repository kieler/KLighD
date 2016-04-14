/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.export;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

import de.cau.cs.kieler.klighd.DiagramExportConfig;
import de.cau.cs.kieler.klighd.IExportBranding;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;

/**
 * Abstract primitive implementation of {@link IExportBranding}. It is intended to be subclassed by
 * implementations of {@link IExportBranding} instead of implementing that interface directly.
 *
 * @author chsch
 */
public abstract class AbstractExportBranding implements IExportBranding {

    /** Centimeters per inch calculation factor. */
    public static final float CM_PER_INCH = 2.54f;

    private ViewContext viewContext = null;

    /**
     * {@inheritDoc}
     */
    public void setViewContext(final ViewContext viewContext) {
        this.viewContext = viewContext;
    }

    /**
     * Getter.
     *
     * @return the {@link ViewContext} belonging to the diagram being exported.
     */
    protected ViewContext getViewContext() {
        return this.viewContext;
    }

    /**
     * {@inheritDoc}<br>
     * Refer to {@link #getViewContext()} in order to get access to, e.g., the input (domain) model.
     */
    public boolean isEnabled() {
        return true;
    }


    // ----------------------------------------------------------- //
    //  background and overlay drawing helpers and callback hooks  //
    // ----------------------------------------------------------- //

    /**
     * Convenience method calculating the bounds of the drawable area of a diagram tile ensuring a
     * certain constant margin in <b>centimeters</b>. To that end this method builds upon
     * {@link DiagramExportConfig#dotsPerInch config.dotsPerInch}, which may be (0, 0) in case of of
     * vector graphic exports, as well as {@link DiagramExportConfig#deviceTrim config.deviceTrim}.
     * By means of the latter the paper area that cannot be colored by printer is incorporated into
     * the calculation.<br>
     * <br>
     * This method also serves as a template for more specific custom implementations :-).
     *
     * @param marginInCM
     *            the margin in <b>centimeters</b> that shall be guaranteed on each side
     * @param deviceTrim
     *            in case the diagram is printed this object represents the printers technically
     *            required trim, which can be incorporated, e.g., for facilitating margins of
     *            exactly a particular value ({@code bounds + deviceTrim} == the actual page size);
     *            is <code>null</code> otherwise;
     * @param dotsPerInch
     *            the image resolution applied by the employed drawing
     *            {@link org.eclipse.swt.graphics.Device Device}, maybe <code>null</code> if not
     *            valid
     * @return a {@link Rectangle2D} containing the desired bounds in pixels
     */
    public Trim getTrimOfSurroundingMarginInCM(final float marginInCM, final Trim deviceTrim,
            final Point dotsPerInch) {
        if (dotsPerInch == null || deviceTrim == null) {
            return Trim.EMPTY_TRIM;
        }

        final float hor = marginInCM * dotsPerInch.x / CM_PER_INCH;
        final float vert = marginInCM * dotsPerInch.y / CM_PER_INCH;

        return new Trim(hor - deviceTrim.left, hor - deviceTrim.right,
                        vert - deviceTrim.top, vert - deviceTrim.bottom);
    }

    /**
     * Convenience method calculating the bounds of the drawable area of a diagram tile ensuring a
     * certain constant margin in <b>inch</b>. To that end this method builds upon
     * {@link DiagramExportConfig#dotsPerInch config.dotsPerInch}, which may be (0, 0) in case of of
     * vector graphic exports, as well as {@link DiagramExportConfig#deviceTrim config.deviceTrim}.
     * By means of the latter the paper area that cannot be colored by printer is incorporated into
     * the calculation.<br>
     * <br>
     * This method also serves as a template for more specific custom implementations :-).
     *
     * @param marginInIN
     *            the margin in <b>inch</b> that shall be guaranteed on each side
     * @param deviceTrim
     *            in case the diagram is printed this object represents the printers technically
     *            required trim, which can be incorporated, e.g., for facilitating margins of
     *            exactly a particular value ({@code bounds + deviceTrim} == the actual page size);
     *            is <code>null</code> otherwise;
     * @param dotsPerInch
     *            the image resolution applied by the employed drawing
     *            {@link org.eclipse.swt.graphics.Device Device}, maybe <code>null</code> if not
     *            valid
     * @return a {@link Rectangle2D} containing the desired bounds in pixels
     */
    public Trim getTrimOfSurroundingMarginInIN(final float marginInIN, final Trim deviceTrim,
            final Point dotsPerInch) {
        if (dotsPerInch == null || deviceTrim == null) {
            return Trim.EMPTY_TRIM;
        }

        final float hor = marginInIN * dotsPerInch.x;
        final float vert = marginInIN * dotsPerInch.y;

        return new Trim(hor - deviceTrim.left, hor - deviceTrim.right,
                        vert - deviceTrim.top, vert - deviceTrim.bottom);
    }


    /**
     * {@inheritDoc}
     */
    public Trim getDiagramTrim(final Rectangle2D bounds) {
        return null;
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * The methods mentioned below may be helpful while implementing an own trim calculation.
     *
     * @see #getTrimOfSurroundingMarginInCM(float, Trim, Point)
     * @see #getTrimOfSurroundingMarginInIN(float, Trim, Point)
     */
    public Trim getDiagramTileTrimm(final Rectangle2D bounds, final Point dotsPerInch,
            final Trim deviceTrim) {
        return null;
    }

    // ----------------------------------------------------------- //
    //  background and overlay drawing helpers and callback hooks  //
    // ----------------------------------------------------------- //

    /**
     * Convenience method calculating the bounds of the drawable area of a diagram tile ensuring a
     * certain constant margin in <b>centimeters</b>. To that end this method builds upon
     * {@link DiagramExportConfig#dotsPerInch config.dotsPerInch}, which may be (0, 0) in case of of
     * vector graphic exports, as well as {@link DiagramExportConfig#deviceTrim config.deviceTrim}.<br>
     * <br>
     * This method also serves as a template for more specific custom implementations ;-).
     *
     * @param marginInCM
     *            the margin in <b>centimeters</b> that shall be guaranteed on each side
     * @param config
     *            the {@link DiagramExportConfig} containing the required data
     * @return a {@link Rectangle2D} containing the desired bounds in pixels
     */
    public Rectangle2D getBoundsOfTileWithSurroundingMarginInCM(final float marginInCM,
            final DiagramExportConfig config) {
        final float hor = marginInCM * config.dotsPerInch.x / CM_PER_INCH;
        final float vert = marginInCM * config.dotsPerInch.y / CM_PER_INCH;

        return new Rectangle2D.Double(hor - config.deviceTrim.left, vert - config.deviceTrim.top,
                config.tileBounds.width - 2 * hor + config.deviceTrim.left + config.deviceTrim.right,
                config.tileBounds.height - 2 * vert + config.deviceTrim.top + config.deviceTrim.bottom);
    }

    /**
     * Convenience method calculating the bounds of the drawable area of a diagram tile ensuring a
     * certain constant margin in <b>inch</b>. To that end this method builds upon
     * {@link DiagramExportConfig#dotsPerInch config.dotsPerInch}, which may be (0, 0) in case of of
     * vector graphic exports, as well as {@link DiagramExportConfig#deviceTrim config.deviceTrim}.<br>
     * <br>
     * This method also serves as a template for more specific custom implementations ;-).
     *
     * @param marginInIN
     *            the margin in <b>inch</b> that shall be guaranteed on each side
     * @param config
     *            the {@link DiagramExportConfig} containing the required data
     * @return a {@link Rectangle2D} containing the desired bounds in pixels
     */
    public Rectangle2D getBoundsOfTileWithSurroundingMarginInIN(final float marginInIN,
            final DiagramExportConfig config) {
        final float hor = marginInIN * config.dotsPerInch.x;
        final float vert = marginInIN * config.dotsPerInch.y;

        return new Rectangle2D.Double(hor - config.deviceTrim.left, vert - config.deviceTrim.top,
                config.tileBounds.width - 2 * hor + config.deviceTrim.left + config.deviceTrim.right,
                config.tileBounds.height - 2 * vert + config.deviceTrim.top + config.deviceTrim.bottom);
    }


    /**
     * {@inheritDoc}
     */
    public final void drawDiagramBackground(final Object graphics, final DiagramExportConfig config) {
        drawDiagramBackground(asKlighdSWTGraphics(graphics), config);
    }

    /**
     * This function allows to contribute diagram-wide background drawings, like water marks for
     * example. It is called before the diagram is drawn. The diagram bounds can be obtained via
     * {@link DiagramExportConfig#diagramBounds config.diagramBounds} and have the form
     * <code>(0, 0, width, height)</code> with width and height denoting the overall size of the
     * drawn diagram including the additionally required {@link Trim}, the accumulated diagram trim
     * is available via {@link DiagramExportConfig#diagramTrim config.diagramTrim}, see also
     * {@link DiagramExportConfig#getDiagramBoundsIncludingTrim()
     * config.getDiagramBoundsIncludingTrim()}.
     *
     * @param graphics
     *            the graphics to draw on
     * @param config
     *            an {@link DiagramExportConfig} record containing all required data
     *
     * @see #drawDiagramBackground(Object, Rectangle2D)
     */
    public void drawDiagramBackground(final KlighdSWTGraphics graphics,
            final DiagramExportConfig config) {
    }

    /**
     * {@inheritDoc}
     */
    public final void drawDiagramTileBackground(final Object graphics,
            final DiagramExportConfig config) {
        drawDiagramTileBackground(asKlighdSWTGraphics(graphics), config);
    }

    /**
     * This function allows to contribute tile background drawings, like water marks for example. It
     * is called before the diagram is drawn. The tiles' dimension can be obtained via
     * {@link DiagramExportConfig#tileBounds config.tileBounds} with width and height denoting the
     * overall size of the diagram tile including additionally required {@link Trim}, the
     * accumulated tile trim is available via {@link DiagramExportConfig#tileTrim config.tileTrim}.
     *
     * @param graphics
     *            the graphics to draw on
     * @param config
     *            an {@link DiagramExportConfig} record containing all required data
     *
     * @see #drawDiagramTileBackground(Object, Rectangle2D)
     */
    public void drawDiagramTileBackground(final KlighdSWTGraphics graphics,
            final DiagramExportConfig config) {
    }

    /**
     * {@inheritDoc}
     */
    public final void drawDiagramOverlay(final Object graphics, final DiagramExportConfig config) {
        drawDiagramOverlay(asKlighdSWTGraphics(graphics), config);
    }

    /**
     * This function allows to contribute diagram-wide overlay drawings, like water marks, authoring
     * information, or symbol explanations for example. It is called after the diagram is drawn. The
     * diagram bounds can be obtained via {@link DiagramExportConfig#diagramBounds
     * config.diagramBounds} and have the form <code>(0, 0, width, height)</code> with width and
     * height denoting the overall size of the drawn diagram including the additionally required
     * {@link Trim}, the accumulated diagram trim is available via
     * {@link DiagramExportConfig#diagramTrim config.diagramTrim}, see also
     * {@link DiagramExportConfig#getDiagramBoundsIncludingTrim()
     * config.getDiagramBoundsIncludingTrim()}.
     *
     * @param graphics
     *            the graphics to draw on
     * @param config
     *            an {@link DiagramExportConfig} record containing all required data
     *
     * @see #drawDiagramOverlay(Object, Rectangle2D)
     */
    public void drawDiagramOverlay(final KlighdSWTGraphics graphics,
            final DiagramExportConfig config) {
    }

    /**
     * {@inheritDoc}
     */
    public final void drawDiagramTileOverlay(final Object graphics, final DiagramExportConfig config) {
        drawDiagramTileOverlay(asKlighdSWTGraphics(graphics), config);
    }

    /**
     * This function allows to contribute tile overlay drawings, like watermarks for example or
     * <i>Confidential</i> brandings. It is called after the diagram is drawn. The tiles' dimension
     * can be obtained via {@link DiagramExportConfig#tileBounds config.tileBounds} with width and
     * height denoting the overall size of the diagram tile including additionally required
     * {@link Trim}, the accumulated tile trim is available via {@link DiagramExportConfig#tileTrim
     * config.tileTrim}.
     *
     * @param graphics
     *            the graphics to draw on
     * @param config
     *            an {@link DiagramExportConfig} record containing all required data
     *
     * @see #drawDiagramBackground(Object, Rectangle2D)
     */
    public void drawDiagramTileOverlay(final KlighdSWTGraphics graphics,
            final DiagramExportConfig config) {
    }


    private KlighdSWTGraphics asKlighdSWTGraphics(final Object graphics) {
        if (graphics instanceof KlighdSWTGraphics) {
            return (KlighdSWTGraphics) graphics;
        } else {
            return null;
        }
    }
}
