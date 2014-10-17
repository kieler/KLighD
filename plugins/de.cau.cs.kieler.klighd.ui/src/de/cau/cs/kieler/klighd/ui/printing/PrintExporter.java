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

import java.awt.Point;
import java.awt.Rectangle;
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


    private Trim diagramTrim = null;
    private Trim diagramTileTrim = null;

    /**
     * Provides the cumulated diagram {@link Trim} required by the employed {@link IExportBranding
     * IExportBrandings}.
     *
     * @return the required (overall) diagram {@link Trim}
     */
    public Trim getDiagramTrim() {
        if (diagramTrim == null) {
            final PBounds diagramBounds = getExportedBounds(viewer.getControl().getCamera(), false);
            diagramTrim = getMaximumDiagramTrim(exportBrandings, diagramBounds);
        }
        return diagramTrim;
    }

    /**
     * Provides the cumulated diagram tile {@link Trim} required by the employed
     * {@link IExportBranding IExportBrandings}.
     *
     * @param tileBounds
     *            the non-scaled bounds of each tile
     * @return the required diagram tile {@link Trim}
     */
    public Trim getDiagramTileTrim(final Rectangle tileBounds) {
        if (diagramTileTrim == null) {
            diagramTileTrim = getMaximumDiagramTileTrim(exportBrandings, tileBounds, true);
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
     *
     * @param tileBounds
     *            the non-scaled bounds of each tile
     * @return a new {@link PBounds} with (width, height) describing the reduced tile bounds.
     */
    public PBounds getTrimmedTileBounds(final Rectangle tileBounds) {
        final Trim tileTrim = getDiagramTileTrim(tileBounds);
        return new PBounds(0, 0,
                tileBounds.width - tileTrim.getWidth(), tileBounds.height - tileTrim.getHeight());
    }


    /**
     * Get the diagram bounds.
     *
     * @return the diagram bounds
     */
    public PBounds getDiagramBounds() {
        final PBounds diagramBounds = getExportedBounds(viewer.getControl().getCamera(), false);
        final Trim trim = getDiagramTrim();

        if (trim != null) {
            diagramBounds.width += trim.getWidth();
            diagramBounds.height += trim.getHeight();
        }

        return diagramBounds;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected KlighdPaintContext createPaintContext(final KlighdSWTGraphics graphics) {
        return KlighdPaintContext.createPrintoutPaintContext(graphics);
    }


    /**
     *
     * @param pageBounds
     *            TODO
     * @param diagramScale
     *            TODO
     * @param dotsPerInch
     *            TODO
     * @return the required {@link DiagramExportConfig}
     */
    public DiagramExportConfig getExportConfig(final Rectangle pageBounds,
            final double diagramScale, final org.eclipse.swt.graphics.Point dotsPerInch) {

        return new DiagramExportConfig(getExportedBounds(viewer.getControl().getCamera(), false),
                pageBounds, diagramScale, new Point(dotsPerInch.x, dotsPerInch.y)).setBrandingsAndTrim(
                    exportBrandings, getDiagramTrim(), getDiagramTileTrim(pageBounds));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getBasicTileClip(final Rectangle drawablesBounds, final Trim tileTrimScaled) {
        return super.getBasicTileClip(drawablesBounds, tileTrimScaled);
    }


    /**
     * Export preview. Can export the diagram in tiles to print on multiple pages.
     *
     * @param config
     *            the employed {@link DiagramExportConfig}
     * @param imageBounds
     *            the bounds of the diagram preview {@link Image} to be returned
     * @param imageClip
     *            the bounds of the diagram print page being previewed
     * @param previewScale
     *            the scale factor
     * @param centeringOffset
     *            the offset to be applied to centrally align the diagram as requested
     * @return the image
     */
    public Image exportPreview(final DiagramExportConfig config, final Rectangle imageBounds,
            final Rectangle imageClip, final double previewScale, final Point2D centeringOffset) {

        final Image image = new Image(
                viewer.getControl().getDisplay(), imageBounds.width, imageBounds.height);
        export(config, image, imageBounds, imageClip, previewScale, centeringOffset);

        return image;
    }


    /**
     * Export print. Can export the diagram in tiles to print on multiple pages.
     *
     * @param config
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
    public void print(final DiagramExportConfig config, final Printer printer,
            final Rectangle pageBounds, final Rectangle pageClip, final Point2D centeringOffset) {
        export(config, printer, pageBounds, pageClip, 1d, centeringOffset);
    }


    /**
     *
     * @param drawable
     * @param column
     * @param row
     * @param drawablesBounds
     * @param pageBounds
     * @param diagramScale
     * @param previewScale
     * @param centeringOffset
     */
    private void export(final DiagramExportConfig exportConfig, final Drawable drawable,
            final Rectangle drawablesBounds, final Rectangle imageClip, final double imageScale,
            final Point2D centeringOffset) {

        final GC gc = new GC(drawable);
        final KlighdSWTGraphicsImpl graphics = new KlighdSWTGraphicsImpl(gc, gc.getDevice());

        drawDiagramTile(exportConfig, graphics, viewer.getControl().getCamera(), drawablesBounds,
                imageClip, imageScale, centeringOffset);

        graphics.dispose();
        gc.dispose();
    }


    /**
     * Provides the printer bounds.<br>
     * {@link Rectangle#width} and {@link Rectangle#height} are width and height of the printable
     * area.
     *
     * @param printer
     *            the printer
     * @return the printer bounds
     */
    // this information is currently not required:
    //    * {@link Rectangle#x} and {@link Rectangle#y} denote the top left point of the printable area.

    // method is set 'package protected' (no modifier) as it is used here and in PrintOptions
    Rectangle getPrinterBounds(final Printer printer) {
        final org.eclipse.swt.graphics.Rectangle pageArea = printer.getClientArea();
//        final org.eclipse.swt.graphics.Rectangle trim = printer.computeTrim(0, 0, 0, 0);
//        return new Rectangle(-trim.x, -trim.y, pageArea.width, pageArea.height);
        return new Rectangle(pageArea.width, pageArea.height);
    }
}
