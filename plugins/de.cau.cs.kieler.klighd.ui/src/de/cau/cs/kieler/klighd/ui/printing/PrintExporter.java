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

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.Printer;

import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.export.AbstractDiagramExporter;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * Exporter providing methods for printing and print previews.
 * 
 * @author csp
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
        return getExportedBounds(viewer.getCanvas().getCamera(), false);
    }

    /**
     * Export preview. Can export the diagram in tiles to print on multiple pages.
     * 
     * @param column
     *            the column of the tile to export
     * @param row
     *            the row of the tile to export
     * @param bounds
     *            the bounds of the diagrampart to be exported
     * @param scale
     *            the scale factor
     * @return the image
     */
    public Image exportPreview(final int column, final int row, final Rectangle bounds,
            final double scale) {

        final Image image = new Image(viewer.getControl().getDisplay(), bounds.width, bounds.height);
        final GC gc = new GC(image);
        export(column, row, bounds, scale, gc, viewer.getControl().getDisplay());
        gc.dispose();
        return image;
    }

    /**
     * Export print. Can export the diagram in tiles to print on multiple pages.
     * 
     * @param column
     *            the column of the tile to export
     * @param row
     *            the row of the tile to export
     * @param scale
     *            the scale factor
     * @param printer
     *            the printer to print to
     */
    public void exportPrint(final int column, final int row, final double scale,
            final Printer printer) {

        final GC gc = new GC(printer);
        final Rectangle pageBounds = PrintExporter.getPrinterBounds(printer);
        export(column, row, pageBounds, scale, gc, printer);
        gc.dispose();
    }

    private void export(final int column, final int row, final Rectangle bounds,
            final double scale, final GC gc, final Device device) {

        final KlighdSWTGraphics graphics = new KlighdSWTGraphicsImpl(gc, device);

        drawDiagram(viewer.getCanvas().getCamera(), false, graphics, new PBounds(bounds.x + column
                * (bounds.width), bounds.y + row * (bounds.height), bounds.width, bounds.height),
                scale, false);
    }

    /**
     * Gets the printer bounds.
     * x and y denote the top left point of the printable area.
     * width and height are width and height of the printable area.
     * 
     * @param printer
     *            the printer
     * @return the printer bounds
     */
    public static Rectangle getPrinterBounds(final Printer printer) {
        final Rectangle pageArea = printer.getClientArea();
        final Rectangle trim = printer.computeTrim(0, 0, 0, 0);
        return new Rectangle(-trim.x, -trim.y, pageArea.width, pageArea.height);
    }

}
