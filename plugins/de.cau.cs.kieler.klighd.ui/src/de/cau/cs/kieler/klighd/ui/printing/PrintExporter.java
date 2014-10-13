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

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.Printer;

import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.export.AbstractDiagramExporter;
import de.cau.cs.kieler.klighd.piccolo.export.ExportHooks;
import de.cau.cs.kieler.klighd.piccolo.export.IExportHook;
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

    /**
     * Create new exporter using the given viewer.
     *
     * @param viewer
     *            the viewer to print
     */
    public PrintExporter(final PiccoloViewer viewer) {
        this.viewer = viewer;
    }

    /**
     * Get the diagram bounds.
     *
     * @return the diagram bounds
     */
    public PBounds getDiagramBounds() {
        return getExportedBounds(viewer.getControl().getCamera(), false);
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
     * @param scale
     *            the scale factor
     * @param centeringOffset
     *            the offset to be applied to centrally align the diagram as requested
     * @return the image
     */
    public Image exportPreview(final int column, final int row, final Rectangle bounds,
            final double scale, final Point2D centeringOffset) {

        final Image image = new Image(viewer.getControl().getDisplay(), bounds.width, bounds.height);

        export(image, column, row, new Rectangle2D.Double(0, 0, bounds.width, bounds.height),
                scale, centeringOffset);

        return image;
    }

    private Rectangle2D imageSize = null;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Rectangle2D getBufferImageSize() {
        return imageSize;
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

        export(printer, column, row, new Rectangle2D.Double(0, 0, pageBounds.width,
                pageBounds.height), scale, centeringOffset);
    }

    /**
     *
     * @param drawable
     * @param column
     * @param row
     * @param bounds
     * @param scale
     * @param centeringOffset
     */
    private void export(final Drawable drawable, final int column, final int row,
            final Rectangle2D bounds, final double scale, final Point2D centeringOffset) {

        final Iterable<IExportHook> hooks =
                ExportHooks.getExportHooksByFormat("printout", viewer.getViewContext());

        final GC gc = new GC(drawable);
        final KlighdSWTGraphicsImpl graphics = new KlighdSWTGraphicsImpl(gc, gc.getDevice());

        // initial clip definition
        graphics.setClip(bounds);
        imageSize = bounds;

        // apply translation for tiled export if necessary
        graphics.setTransform(AffineTransform.getTranslateInstance(
                -column * bounds.getWidth() + centeringOffset.getX(),
                -row * bounds.getHeight() + centeringOffset.getY()));

        // apply the scale factor to the employed graphics object
        //  by means of a corresponding affine transform
        graphics.transform(AffineTransform.getScaleInstance(scale, scale));

        drawDiagram(viewer.getControl().getCamera(), false, graphics, null, false, hooks);

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
        final Rectangle pageArea = printer.getClientArea();
        final Rectangle trim = printer.computeTrim(0, 0, 0, 0);
        return new Rectangle(-trim.x, -trim.y, pageArea.width, pageArea.height);
    }
}
