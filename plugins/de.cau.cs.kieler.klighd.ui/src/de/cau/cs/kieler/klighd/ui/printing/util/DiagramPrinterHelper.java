/******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package de.cau.cs.kieler.klighd.ui.printing.util;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;

/**
 * Utility for using the DiagramPrinter to print diagrams after displaying a print dialog box to the
 * user. Diagrams are printed using the DiagramPrinter and respect the settings chosen by the user
 * in the print dialog.
 * 
 * Basic functionality was factored into this class from the DiagramPrinterUtil. This class and its
 * specializing classes:
 * 
 * @author James Bruck (jbruck)
 */
public class DiagramPrinterHelper {

    /**
     * Prints a diagram with the settings from the helper onto the printer
     * 
     * @param diagramPrinter
     *            the diagram printer that does the work of actually printing the diagrams
     * @param helper
     *            IPrintHelper with the user's choice of settings
     */
    protected void printDiagrams(DiagramPrinter diagramPrinter, PrintHelper helper) {

        if (helper.getDlgPrintRangePages()) {
            diagramPrinter.setPrintRangePageSelection(true);
            diagramPrinter.setPrintRangePages(helper.getDlgPagesFrom(), helper.getDlgPagesTo());
            diagramPrinter.setFitToPage(false);
        }

        if (helper.getDlgScalePercent() == -1) {
            diagramPrinter.setColumns(helper.getDlgScaleFitToM());
            diagramPrinter.setRows(helper.getDlgScaleFitToN());
            diagramPrinter.setFitToPage(true);
        } else {
            diagramPrinter.setScaledPercent(helper.getDlgScalePercent());
            diagramPrinter.setFitToPage(false);
        }

        diagramPrinter.run();
    }

    /**
     * A helper that simply collects printing information.
     * 
     * @param diagramPrinter
     * @param helper
     * @param diagramNames
     * @param editorPart
     * @param diagramMap
     * @return
     */
    protected PrinterData collectPrintInformation(DiagramPrinter diagramPrinter,
            PrintHelper helper, List diagramNames, IDiagramWorkbenchPart editorPart, Map diagramMap) {

        PrinterData printerData;

        IPreferenceStore pref = null;

//            IDiagramGraphicalViewer viewer =
//                    editorPart.getDiagramGraphicalViewer();
//            if (viewer instanceof DiagramGraphicalViewer) {
//
//                pref = ((DiagramGraphicalViewer) viewer).getWorkspaceViewerPreferenceStore();
//
//                if (pref.getBoolean(WorkspaceViewerProperties.PREF_USE_WORKSPACE_SETTINGS)) {
//
//                    if (((IDiagramWorkbenchPart) editorPart).getDiagramEditPart()
//                            .getDiagramPreferencesHint().getPreferenceStore() != null) {
//                        pref =
//                                (IPreferenceStore) ((IDiagramWorkbenchPart) editorPart)
//                                        .getDiagramEditPart().getDiagramPreferencesHint()
//                                        .getPreferenceStore();
//                    }
//                }
//            }
//        if (pref != null) {
//            helper.setDlgOrientation(pref.getBoolean(WorkspaceViewerProperties.PREF_USE_LANDSCAPE));
//
//            PageSetupPageType storedPageType = PageSetupPageType.LETTER; // default value
//            String strPageType = pref.getString(WorkspaceViewerProperties.PREF_PAGE_SIZE);
//            for (int i = 0; i < PageSetupPageType.pages.length; i++) {
//                if (strPageType.startsWith(PageSetupPageType.pages[i].getName())) {
//                    storedPageType = PageSetupPageType.pages[i];
//                    break;
//                }
//            }
//            if (storedPageType.getIndex() == PageSetupPageType.USER_DEFINED.getIndex()) {
//
//                NumberFormat fNumberFormat = NumberFormat.getNumberInstance();
//
//                String strWidth = pref.getString(WorkspaceViewerProperties.PREF_PAGE_WIDTH);
//                String strHeight = pref.getString(WorkspaceViewerProperties.PREF_PAGE_HEIGHT);
//                double width = 0, height = 0;
//
//                try {
//                    Number num = fNumberFormat.parse(strWidth);
//                    width = num.doubleValue() / 0.0394d; // convert from inches to mm
//
//                    num = fNumberFormat.parse(strHeight);
//                    height = num.doubleValue() / 0.0394d;
//
//                    helper.setDlgPaperSize(PageSetupPageType.USER_DEFINED.getIndex(), width, height);
//                } catch (ParseException e) {
//                    Log.warning(DiagramPrintingPlugin.getInstance(),
//                            DiagramPrintingStatusCodes.IGNORED_EXCEPTION_WARNING, e.getMessage(), e);
//                }
//            } else {
//                helper.setDlgPaperSize(storedPageType.getIndex(), 0, 0);
//            }
//        }
//        helper.setScaleFactor(PrintHelperUtil.getScale());
//        helper.setScaleToWidthHeight(PrintHelperUtil.getScaleToWidth(),
//                PrintHelperUtil.getScaleToHeight());

        printerData = helper.openPrintDlg(diagramNames);

        if (printerData != null) {
            printerData.printToFile = true;
            Printer p = new Printer(printerData);
            
            System.out.println(p.getBounds());
            
//            if (helper.getDlgScalePercent() != -1) {
//                PrintHelperUtil.setScale(helper.getDlgScalePercent());
//            }
//            if (helper.getDlgScaleFitToM() != -1) {
//                PrintHelperUtil.setScaleToWidth(helper.getDlgScaleFitToM());
//            }
//            if (helper.getDlgScaleFitToN() != -1) {
//                PrintHelperUtil.setScaleToHeight(helper.getDlgScaleFitToN());
//            }
        }

        return printerData;
    }

}
