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
package de.cau.cs.kieler.klighd.ui.printing;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.printing.Printer;

import de.cau.cs.kieler.klighd.DiagramExportConfig;
import de.cau.cs.kieler.klighd.IExportBranding;
import de.cau.cs.kieler.klighd.IExportBranding.Trim;
import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.export.AbstractDiagramExporter;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.internal.util.KlighdPaintContext;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PDimension;

/**
 * Diagram printing exporter providing methods for creating previews and the final printout.
 *
 * @author csp
 * @author chsch
 */
public final class PrintExporter extends AbstractDiagramExporter {

    private final PiccoloViewer viewer;
    private final Iterable<IExportBranding> exportBrandings;

    /**
     * Create new exporter using the given viewer.
     *
     * @param viewer
     *            the viewer to print
     */
    public PrintExporter(final PiccoloViewer viewer) {
        this.viewer = viewer;
        this.exportBrandings =
                KlighdDataManager.getExportBrandingByFormat("printout", viewer.getViewContext());
    }


    // since the diagram bounds, the diagram trim, and the page (tile) trim information
    //  are considered to be constant while configuring the printing those information are cached
    // the trim information, however, are invalidated once the user changes the printer or the
    //  page orientation through #resetTrimInformation()

    private PBounds diagramBounds = null;
    private Trim diagramTrim = null;
    private Trim diagramTileTrim = null;

    /**
     * Internal helper for obtaining the bounds of the diagram being exported.
     *
     * @see #getExportedBounds(de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera,
     *      boolean)
     *
     * @return the diagram's bounds as returned by{@link #getExportedBounds(
     *         de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdMainCamera, boolean)}
     *         with the Boolean parameter is set to {@code false}
     */
    private PBounds getExportedBounds() {
        if (diagramBounds == null) {
            diagramBounds = getExportedBounds(viewer.getControl().getCamera(), false);
        }
        return new PBounds(diagramBounds);
    }


    /**
     * Provides the cumulated diagram {@link Trim} required by the employed {@link IExportBranding
     * IExportBrandings}.
     *
     * @return the required (overall) diagram {@link Trim}
     */
    public Trim getDiagramTrim() {
        if (diagramTrim == null) {
            diagramTrim = getMaximumDiagramTrim(exportBrandings, getExportedBounds());
        }
        return diagramTrim;
    }


    /**
     * Provides the cumulated diagram tile {@link Trim} required by the employed
     * {@link IExportBranding IExportBrandings}.
     *
     * @param tileBounds
     *            the non-scaled bounds of each tile
     * @param printerTrim
     *            the printers technically required trim, i.e., the reduction of its printable area
     * @param dotsPerInch
     *            the image resolution applied by the employed drawing
     *            {@link org.eclipse.swt.graphics.Device Device}, maybe <code>null</code> if not valid
     * @return the required diagram tile {@link Trim}
     */
    public Trim getDiagramTileTrim(final Dimension tileBounds, final Trim printerTrim,
            final Point dotsPerInch) {
        if (diagramTileTrim == null) {
            diagramTileTrim = getMaximumDiagramTileTrim(
                    exportBrandings, new Rectangle(tileBounds), printerTrim, dotsPerInch);
        }
        return diagramTileTrim;
    }


    /**
     * Resets the cached trim information.<br>
     * This method is currently only called from {@link PrintOptions} after the printer data
     * (resolution) and page orientation has been altered.
     */
    void resetTrimInformation() {
        diagramTrim = null;
        diagramTileTrim = null;
    }


    /**
     * Provides the size of the area being available for a diagram excerpt, which is
     * the {@code options.getPrinterBounds()} subtracted by the size of the required tile trim.
     *
     * @param options
     *            the current {@link PrintOptions} configuration
     * @return a {@link Dimension2D} describing the reduced tile size.
     */
    public Dimension2D getTrimmedTileBounds(final PrintOptions options) {
        if (options == null || options.getPrinter() == null) {
            return new PDimension();
        }

        final Dimension tileBounds = options.getPrinterBounds();
        final Trim tileTrim = getDiagramTileTrim(
                tileBounds, options.getPrinterTrim(), options.getPrinterDPI());

        return new PDimension(
                tileBounds.width - tileTrim.getWidth(), tileBounds.height - tileTrim.getHeight());
    }


    /**
     * Provides the printed diagram's size supplemented with the diagram trim.
     *
     * @return the diagram's size supplemented with the diagram trim
     */
    public PDimension getDiagramBoundsIncludingTrim() {
        final PBounds exportedBounds = getExportedBounds();
        final Trim trim = getDiagramTrim();

        if (trim != null) {
            exportedBounds.width += trim.getWidth();
            exportedBounds.height += trim.getHeight();
        }

        return (PDimension) exportedBounds.getSize();
    }


    /**
     * {@inheritDoc}<br>
     * <br>
     * Specialization contributes a {@link KlighdPaintContext} being configured for printouts
     * rather than image exports.
     */
    @Override
    protected KlighdPaintContext createPaintContext(final KlighdSWTGraphics graphics) {
        return KlighdPaintContext.createPrintoutPaintContext(graphics);
    }


    /**
     * Convenience helper method creating the {@link DiagramExportConfig DiagramExportConfigs} that
     * are required for printing diagrams.
     *
     * @param options
     *            the current {@link PrintOptions} configuration
     *
     * @return the required {@link DiagramExportConfig}
     */
    public DiagramExportConfig createExportConfig(final PrintOptions options) {
        if (options == null || options.getPrinter() == null) {
            return null;
        }

        final Dimension pageBounds = options.getPrinterBounds();
        final Trim printerTrim = options.getPrinterTrim();
        final Point dpi = options.getPrinterDPI();
        final int pages = options.getPagesTall() * options.getPagesWide();

        return new DiagramExportConfig(viewer.getViewContext(), getExportedBounds(), pageBounds,
            options.getScaleFactor(), dpi, printerTrim, pages).setBrandingsAndTrim(exportBrandings,
                getDiagramTrim(), getDiagramTileTrim(pageBounds, printerTrim, dpi));
    }


    /**
     * @see AbstractDiagramExporter#getBasicTileClip(Dimension, Trim)
     *
     * @param drawablesBounds
     *            the actual bounds of the employed {@link org.eclipse.swt.graphics.Drawable
     *            Drawable}
     * @param tileTrimScaled
     *            the cumulated required {@link Trim} scaled to {@code drawablesBounds} if necessary
     * @return a {@link Rectangle} describing the unadjusted clip rectangle that is to be applied to
     *         the employed {@link KlighdSWTGraphics} without having configured any
     *         {@link java.awt.geom.AffineTransform transform} on that graphics
     */
    public Rectangle getBasicPageClip(final Dimension drawablesBounds, final Trim tileTrimScaled) {
        return super.getBasicTileClip(drawablesBounds, tileTrimScaled);
    }


    /**
     * Export preview. Can export the diagram in tiles to print on multiple pages.
     *
     * @param config
     *            the employed {@link DiagramExportConfig}
     * @param imageBounds
     *            the absolute (unadjusted) bounds of the diagram preview {@link Image} to be
     *            returned
     * @param imageClip
     *            the basic clip of the diagram printout page being previewed as provided by
     *            {@link #getBasicTileClip(Dimension, Trim)}, can be provided here in order to avoid
     *            re-computations (of the same values) for each diagram tile, maybe {@code null}
     * @param previewScale
     *            the scale factor to be applied to the whole tile while drawing the preview image
     * @param centeringOffset
     *            the offset to be applied to centrally align the diagram as requested
     * @return the image
     */
    public Image exportPreview(final DiagramExportConfig config, final Dimension imageBounds,
            final Rectangle imageClip, final double previewScale, final Point2D centeringOffset) {

        final Image image = new Image(
                viewer.getControl().getDisplay(), imageBounds.width, imageBounds.height);
        export(config, image, imageBounds, imageClip, previewScale, centeringOffset);

        return image;
    }


    /**
     * Export print. Can export the diagram in tiles to print on multiple pages.
     *
     * @param exportConfig
     *            the employed {@link DiagramExportConfig}
     * @param printer
     *            the printer to print to
     * @param pageBounds
     *            the bounds of the diagram preview {@link Image} to be returned
     * @param pageClip
     *            the bounds of the diagram print page being previewed
     * @param centeringOffset
     *            the offset to be applied to centrally align the diagram as requested
     */
    public void print(final DiagramExportConfig exportConfig, final Printer printer,
            final Dimension pageBounds, final Rectangle pageClip, final Point2D centeringOffset) {
        export(exportConfig, printer, pageBounds, pageClip, 1d, centeringOffset);
    }


    /**
     *
     * @param exportConfig
     *            the employed {@link DiagramExportConfig}
     * @param drawable
     *            the {@link Drawable} to draw the diagram on, usually an {@link Image} or a
     *            {@link Printer}
     * @param drawablesBounds
     *            the actual (unadjusted) pure bounds, which may differ from
     *            {@code exportConfig.tileBounds} in case the tiles are scaled (e.g. for print
     *            previews)
     * @param baseTileClip
     *            the basic tile clip as provided by {@link #getBasicTileClip(Dimension, Trim)}, can
     *            be provided here in order to avoid re-computation (of the same values) for each
     *            diagram tile, maybe {@code null}
     * @param imageScale
     *            the scale factor to be applied to the whole tile, e.g. while drawing preview images
     * @param centeringOffset
     *            the offset to be applied to centrally align the diagram as requested
     */
    private void export(final DiagramExportConfig exportConfig, final Drawable drawable,
            final Dimension drawablesBounds, final Rectangle baseTileClip, final double imageScale,
            final Point2D centeringOffset) {

        final GC gc = new GC(drawable);
        final KlighdSWTGraphicsImpl graphics = new KlighdSWTGraphicsImpl(gc);

        drawDiagramTile(exportConfig, graphics, viewer.getControl().getCamera(), drawablesBounds,
                baseTileClip, imageScale, centeringOffset);

        graphics.dispose();
        gc.dispose();
    }
}
