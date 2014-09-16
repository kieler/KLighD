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
package de.cau.cs.kieler.klighd.ui.printing.actions;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.printing.Printer;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.ui.KlighdUIPlugin;
import de.cau.cs.kieler.klighd.ui.printing.dialogs.KlighdPrintDialog;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;
import de.cau.cs.kieler.klighd.ui.printing.util.PrintExporter;

/**
 * The print() method will invoke a dialog prompting the user to choose options
 * for printing and providing a print preview.
 * 
 * @author csp
 */
public class PrintActionHelper {

    /**
     * Show the print dialog and print.
     * 
     * @param viewer
     *            the viewer whose diagram to be printed
     */
    public void print(final PiccoloViewer viewer) {

        // receive the preference store
        IPreferenceStore preferenceStore = KlighdUIPlugin.getDefault().getPreferenceStore();
        PrintOptions options = new PrintOptions(preferenceStore);
        PrintExporter exporter = new PrintExporter(viewer);
        options.setExporter(exporter);

        KlighdPrintDialog dlg =
                new KlighdPrintDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), options);

        if (dlg.open() != IDialogConstants.OK_ID) {
            return;
        }

        if (options.getPrinterData() != null) {

            final Printer p = new Printer(options.getPrinterData());

            // start the print job
            p.startJob("KlighD Printing");

            for (int i = 0; i < options.getFitToPagesHeight(); i++) {
                for (int j = 0; j < options.getFitToPagesWidth(); j++) {
                    p.startPage();
                    exporter.exportPrint(j, i, options.getScaleFactor(), p);
                    p.endPage();
                }
            }

            // finish and cleanup
            p.endJob();
            p.dispose();
        }
    }

}
