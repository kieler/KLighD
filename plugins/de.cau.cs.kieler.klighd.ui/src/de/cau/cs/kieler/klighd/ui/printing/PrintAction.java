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
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.klighd.DiagramExportConfig;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.ui.printing.dialog.KlighdPrintDialog;

/**
 * An {@link Action} contributing print support to KLighD diagram viewers including a system
 * independent printout configuration dialog a with print preview. The printer selection is
 * delegated to the native 'printer selection' dialog.<br>
 * <br>
 * This class can be subclass in order to contribute a specialized version of the
 * {@link KlighdPrintDialog} by overriding {@link #getPrintDialog(Shell, PrintOptions)}.<br>
 * <br>
 * In order to register the print support for editor parts a corresponding
 * {@link org.eclipse.ui.part.EditorActionBarContributor EditorActionBarContributor} must be created
 * and registered. Our default one is
 * {@link de.cau.cs.kieler.klighd.ui.parts.DiagramEditorPart.PrintActionContributor
 * DiagramEditorPart.PrintActionContributor}, it is registered in the plugin.xml as a contributor of
 * the {@link de.cau.cs.kieler.klighd.ui.parts.DiagramEditorPart DiagramEditorPart}.<br>
 * <br>
 * In order to enable printing for self implemented view parts, this print action must be registered
 * in the view site's action base as done in
 * {@link de.cau.cs.kieler.klighd.ui.parts.DiagramViewPart#registerPrintSupport
 * DiagramViewPart#registerPrintSupport}.<br>
 * <br>
 * Note: A {@link PiccoloViewer} must be set to successfully perform the printing.
 *
 * @author csp
 * @author chsch
 */
public class PrintAction extends Action {

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
                final String msg = "KLighD PrintAction: " //$NON-NLS-1$
                        + "provided IViewer must be/contain an instance of PiccoloViewer!"; //$NON-NLS-1$
                throw new IllegalArgumentException(msg);
            }
        }
    }

    /**
     * Factor method contributing the employed {@link KlighdPrintDialog}, may be overridden if a
     * customized dialog shall be injected.
     *
     * @param parentShell
     *            the parent {@link Shell} required by the dialog
     * @param options
     *            the {@link PrintOptions} instance to work with
     * @return the {@link KlighdPrintDialog} to be used
     */
    protected KlighdPrintDialog getPrintDialog(final Shell parentShell, final PrintOptions options) {
        return new KlighdPrintDialog(parentShell, options);
    }

    /**
     * Show the print dialog and print.
     */
    @Override
    public final void run() {
        if (viewer == null) {
            throw new IllegalStateException(
                    "KLighD PrintAction: The required viewer is not set!"); //$NON-NLS-1$
        }

        final PrintExporter exporter = new PrintExporter(viewer);
        final PrintOptions options = new PrintOptions(exporter);

        final KlighdPrintDialog dlg = getPrintDialog(viewer.getControl().getShell(), options);

        if (dlg.open() != IDialogConstants.OK_ID) {
            return;
        }

        final Printer printer = options.getPrinter();
        if (printer == null) {
            return;
        }

        final PrinterData printerData = printer.getPrinterData();

        // start the print job
        printer.startJob(KlighdUIPrintingMessages.PrintAction_JobName);

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
