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
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.klighd.DiagramExportConfig;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.ui.printing.dialog.KlighdPrintDialog;

/**
 * An {@link Action} to show a system independent print dialog with print preview.
 * This class can be registered as an action handler. It denotes the main entry point to the KlighD
 * printing infrastructure.<br>
 * <br>
 * Note: A {@link PiccoloViewer} must be set to successfully execute the <code>run()</code> method.
 *
 * @author csp
 * @author chsch
 */
public final class PrintAction extends Action {

    private PiccoloViewer viewer;

    /**
     * Create a new PrintAction. A viewer must be set separately.
     */
    public PrintAction() {
        super();
    }

    /**
     * Creates a new PrintAction using the given viewer while printing.
     *
     * @param viewer
     *            the viewer to print.
     */
    public PrintAction(final IViewer viewer) {
        super();
        setViewer(viewer);
    }

    /**
     * Returns the viewer used while printing.
     *
     * @return the viewer to print
     */
    public PiccoloViewer getViewer() {
        return viewer;
    }

    /**
     * Set a new viewer to use while printing.
     *
     * @param viewer
     *            the new viewer to print
     */
    public void setViewer(final IViewer viewer) {
        if (viewer instanceof PiccoloViewer) {
            this.viewer = (PiccoloViewer) viewer;

        } else {
            final IViewer aViewer = viewer.getContextViewer().getActiveViewer();
            if (aViewer instanceof PiccoloViewer) {
                this.viewer = (PiccoloViewer) aViewer;

            } else {
                final String msg = "KLighD PrintAction: "
                        + "provided IViewer must be/contain an instance of PiccoloViewer!";
                throw new IllegalArgumentException(msg);
            }
        }
    }

    /**
     * Show the print dialog and print.
     */
    @Override
    public void run() {
        if (viewer == null) {
            throw new IllegalArgumentException("PrintAction: Viewer not set!");
        }

        final PrintExporter exporter = new PrintExporter(viewer);
        final PrintOptions options = new PrintOptions(exporter);

        final KlighdPrintDialog dlg =
                new KlighdPrintDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), options);

        if (dlg.open() != IDialogConstants.OK_ID) {
            return;
        }

        final Printer printer = options.getPrinter();
        if (printer == null) {
            return;
        }

        final PrinterData printerData = printer.getPrinterData();

        // start the print job
        printer.startJob("KlighD Printing");

        final Dimension pageBounds = exporter.getPrinterBounds(printer);

        final DiagramExportConfig config =
                exporter.createExportConfig(pageBounds, options.getScaleFactor(), printer.getDPI());

        final Rectangle pageClip = exporter.getBasicPageClip(pageBounds, config.tileTrim);

        final Point2D centeringOffset = options.getCenteringOffset();

        final int rows = options.getPagesTall();
        final int columns = options.getPagesWide();

        int pageNo = 0;

        // considering the assumptions to print row by row, i.e. start with all pages of the first row
        //  than proceed to those of the second one etc.

        outerLoop:
        for (int row = 0; row != rows; row++) {
            for (int column = 0; column != columns; column++) {

                ++pageNo;
                final boolean pageEnabled = printerData.scope == PrinterData.ALL_PAGES
                        || printerData.scope == PrinterData.PAGE_RANGE
                                && printerData.startPage <= pageNo && printerData.endPage >= pageNo;

                if (!pageEnabled) {
                    continue;

                } else if (printer.startPage()) {
                    config.setPageAndTileNumbers(pageNo, row, column, rows, columns);
                    exporter.print(config, printer, pageBounds, pageClip, centeringOffset);
                    printer.endPage();

                } else {
                    break outerLoop;
                }
            }
        }

        // finish and cleanup
        printer.endJob();
        printer.dispose();
    }
}
