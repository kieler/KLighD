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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.printing.Printer;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.ui.KlighdUIPlugin;
import de.cau.cs.kieler.klighd.ui.printing.dialog.KlighdPrintDialog;

/**
 * An {@link Action} to show a system independent print dialog with print preview.
 * This class can be registered as an action handler. It denotes the main entry point to the KlighD
 * printing infrastructure.<br>
 * <br>
 * Note: A PiccoloViewer must be set to successfully execute the <code>run()</code> method.
 * 
 * @author csp
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
    public PrintAction(final PiccoloViewer viewer) {
        super();
        this.viewer = viewer;
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
    public void setViewer(final PiccoloViewer viewer) {
        this.viewer = viewer;
    }

    /**
     * Show the print dialog and print.
     */
    @Override
    public void run() {
        if (viewer == null) {
            throw new IllegalArgumentException("PrintAction: Viewer not set!");
        }
        final IPreferenceStore preferenceStore = KlighdUIPlugin.getDefault().getPreferenceStore();
        final PrintOptions options = new PrintOptions(preferenceStore);
        final PrintExporter exporter = new PrintExporter(viewer);
        options.setExporter(exporter);

        final KlighdPrintDialog dlg =
                new KlighdPrintDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), options);

        if (dlg.open() != IDialogConstants.OK_ID) {
            return;
        }

        if (options.getPrinterData() != null) {
            final Printer printer = new Printer(options.getPrinterData());
            // start the print job
            printer.startJob("KlighD Printing");
            for (int row = 0; row < options.getPagesTall(); row++) {
                for (int col = 0; col < options.getPagesWide(); col++) {
                    printer.startPage();
                    exporter.exportPrint(col, row, options.getScaleFactor(), printer);
                    printer.endPage();
                }
            }
            // finish and cleanup
            printer.endJob();
            printer.dispose();
        }
    }

}
