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

import java.awt.Point;
import java.awt.geom.Rectangle2D;

import de.cau.cs.kieler.klighd.DiagramExportConfig;
import de.cau.cs.kieler.klighd.IExportBranding;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.IExportBranding.Trim;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;

/**
 * Abstract primitive implementation of {@link IExportBranding}. It is intended to be subclassed by
 * implementations of {@link IExportBranding} instead of implementing that interface directly.
 *
 * @author chsch
 */
public abstract class AbstractExportBranding implements IExportBranding {

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

    /**
     * {@inheritDoc}
     */
    public Trim getDiagramTrim(final Rectangle2D bounds, final Point dotsPerInch) {
        return getDiagramTrim(bounds);
    }

    /**
     * Simplified method hook to be overridden by configuring the diagram trim.
     *
     * @see #getDiagramTrimm(Rectangle2D, Point, boolean)
     *
     * @param bounds
     *            the size of overall (scaled) diagram
     *
     * @return the required {@link Trim}
     */
    public Trim getDiagramTrim(final Rectangle2D bounds) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Trim getDiagramTileTrimm(final Rectangle2D bounds, final Point dotsPerInch,
            final boolean fixSizedTiles) {
        return getDiagramTileTrimm(bounds, fixSizedTiles);
    }

    /**
     * Simplified method hook to be overridden by configuring the diagram tile trim.
     *
     * @see #getDiagramTileTrimm(Rectangle2D, Point, boolean)
     *
     * @param bounds
     *            depending on {@code fixSizedTiles} the absolute size of either the diagram tile
     *            itself, or the diagram excerpt drawn on the tile respectively
     * @param fixSizedTiles
     *            if {@code true} the returned {@link Trim} will reduce the area being available
     *            for drawing, otherwise the tile is increased by the provided {@link Trim}
     *
     * @return the required {@link Trim}
     */
    public Trim getDiagramTileTrimm(final Rectangle2D bounds, final boolean fixSizedTiles) {
        return null;
    }

    private KlighdSWTGraphics asKlighdSWTGraphics(final Object graphics) {
        if (graphics instanceof KlighdSWTGraphics) {
            return (KlighdSWTGraphics) graphics;
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void drawDiagramBackground(final Object graphics, final DiagramExportConfig config) {
        drawDiagramBackground(asKlighdSWTGraphics(graphics), config);
    }

    /**
    /**
     * This function allows to contribute diagram-wide background drawings, like water marks for
     * example. It is called before the diagram is drawn. The bounds have the form
     * <code>(0, 0, width, height)</code> with width and height denoting the overall size of the
     * drawn diagram including the additionally required {@link Trim}.<br>
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
    public void drawDiagramTileBackground(final Object graphics, final DiagramExportConfig config) {
        drawDiagramTileBackground(asKlighdSWTGraphics(graphics), config);
    }

    /**
     * This function allows to contribute tile background drawings, like water marks for example.
     * It is called before the diagram is drawn. The bounds have the form
     * <code>(0, 0, width, height)</code> with width and height denoting the overall size of the
     * diagram tile including additionally required {@link Trim}.<br>
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
    public void drawDiagramOverlay(final Object graphics, final DiagramExportConfig config) {
        drawDiagramOverlay(asKlighdSWTGraphics(graphics), config);
    }

    /**
     * This function allows to contribute diagram-wide overlay drawings, like water marks, authoring
     * information, or symbol explanations for example. It is called before the diagram is drawn.
     * The bounds have the form <code>(0, 0, width, height)</code> with width and height denoting
     * the overall size of the drawn diagram including the additionally required {@link Trim}.<br>
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
    public void drawDiagramTileOverlay(final Object graphics, final DiagramExportConfig config) {
        drawDiagramTileOverlay(asKlighdSWTGraphics(graphics), config);
    }

    /**
     * This function allows to contribute tile overlay drawings, like watermarks for example or
     * <i>Confidential</i> brandings. It is called before the diagram is drawn. The bounds have the
     * form <code>(0, 0, width, height)</code> with width and height denoting the overall size of
     * the diagram tile including additionally required {@link Trim}.<br>
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
}
