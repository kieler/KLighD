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

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.printing.Printer;

import de.cau.cs.kieler.klighd.DiagramExportConfig;
import de.cau.cs.kieler.klighd.IExportBranding;
import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.IExportBranding.Trim;
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
public class PrintExporter extends AbstractDiagramExporter {

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
            diagramTileTrim = getMaximumDiagramTileTrim(exportBrandings, tileBounds);
        }
        return diagramTileTrim;
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
            diagramBounds.width += trim.left + trim.right;
            diagramBounds.height += trim.top + trim.bottom;
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
     * Export preview. Can export the diagram in tiles to print on multiple pages.
     *
     * @param column
     *            the column of the tile to export
     * @param row
     *            the row of the tile to export
     * @param bounds
     *            the bounds of the diagram preview image to be returned
     * @param pageBounds TODO
     * @param diagramScale
     *            the scale factor
     * @param previewScale
     *            the scale factor
     * @param centeringOffset
     *            the offset to be applied to centrally align the diagram as requested
     * @return the image
     */
    public Image exportPreview(final int column, final int row, final Rectangle bounds,
            final Rectangle pageBounds, final double diagramScale, final double previewScale,
            final Point2D centeringOffset) {

        final Image image = new Image(viewer.getControl().getDisplay(), bounds.width, bounds.height);

        export(image, column, row, bounds, pageBounds, diagramScale, previewScale, centeringOffset);

        return image;
    }

    /**
     * Export print. Can export the diagram in tiles to print on multiple pages.
     *
     * @param printer
     *            the printer to print to
     * @param column
     *            the column of the tile to export
     * @param row
     *            the row of the tile to export
     * @param scale
     *            the scale factor
     * @param centeringOffset
     *            the offset to be applied to centrally align the diagram as requested
     */
    public void print(final Printer printer, final int column, final int row,
            final double scale, final Point2D centeringOffset) {
        final Rectangle pageBounds = getPrinterBounds(printer);
        export(printer, column, row, pageBounds, pageBounds, scale, 1, centeringOffset);
    }

    /**
     *
     * @param drawable
     * @param column
     * @param row
     * @param drawablesBounds
     * @param diagramScale
     * @param centeringOffset
     */
    private void export(final Drawable drawable, final int column, final int row,
            final Rectangle drawablesBounds,
            final Rectangle pageBounds,
            final double diagramScale, final double previewScale,
            final Point2D centeringOffset) {

        final GC gc = new GC(drawable);
        final KlighdSWTGraphicsImpl graphics = new KlighdSWTGraphicsImpl(gc, gc.getDevice());

        final Trim tileTrimScaled = getDiagramTileTrim(pageBounds).getScaled((float) previewScale);

        // initial clip definition
        final Rectangle clip =
                new Rectangle2D.Double(
                        Math.ceil(tileTrimScaled.left),
                        Math.ceil(tileTrimScaled.top),
                        Math.floor(
                                drawablesBounds.getWidth() - tileTrimScaled.left - tileTrimScaled.right),
                        Math.floor(
                                drawablesBounds.getHeight() - tileTrimScaled.top - tileTrimScaled.bottom)
                ).getBounds();
        graphics.setClip(clip);

        // apply translation for tiled export if necessary
        graphics.setTransform(AffineTransform.getTranslateInstance(
                -column * drawablesBounds.getWidth() + centeringOffset.getX(),
                -row * drawablesBounds.getHeight() + centeringOffset.getY()));

        graphics.transform(AffineTransform.getTranslateInstance(
                (column + 1) * tileTrimScaled.left + (column) * tileTrimScaled.right,
                (row + 1) * tileTrimScaled.top + (row) * tileTrimScaled.bottom));

        final AffineTransform tileScaling = AffineTransform.getScaleInstance(previewScale, previewScale);
        graphics.transform(tileScaling);

        // apply the scale factor to the employed graphics object
        //  by means of a corresponding affine transform
        graphics.transform(AffineTransform.getScaleInstance(diagramScale, diagramScale));

        final DiagramExportConfig exportConfig =
                new DiagramExportConfig(null, pageBounds).setBrandingsAndTrim(
                        exportBrandings, getDiagramTrim(), getDiagramTileTrim(null));

        drawDiagram(graphics, viewer.getControl().getCamera(), tileScaling, clip, exportConfig);

        graphics.dispose();
        gc.dispose();
    }

    /**
     * Provides the printer bounds.<br>
     * {@link Rectangle#x} and {@link Rectangle#y} denote the top left point of the printable area.
     * {@link Rectangle#width} and {@link Rectangle#height} are width and height of the printable
     * area.
     *
     * @param printer
     *            the printer
     * @return the printer bounds
     */
    // method is set 'package protected' (no modifier) as it is used here and in PrintOptions
    Rectangle getPrinterBounds(final Printer printer) {
        final org.eclipse.swt.graphics.Rectangle pageArea = printer.getClientArea();
        final org.eclipse.swt.graphics.Rectangle trim = printer.computeTrim(0, 0, 0, 0);
//        return new Rectangle(-trim.x, -trim.y, pageArea.width, pageArea.height);
        return new Rectangle(pageArea.width, pageArea.height);
    }
}
