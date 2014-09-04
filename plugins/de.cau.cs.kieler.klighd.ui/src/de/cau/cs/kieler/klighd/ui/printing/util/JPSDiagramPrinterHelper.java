/******************************************************************************
 * Copyright (c) 2008, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
package de.cau.cs.kieler.klighd.ui.printing.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;

/**
 * Utility for using the DiagramPrinter to print diagrams after displaying a print dialog box to the
 * user. Diagrams are printed using the DiagramPrinter and respect the settings chosen by the user
 * in the print dialog.
 * 
 * This class uses the java print service API to accomplish its task. This class was derived from @see
 * org.eclipse.gmf.runtime.diagram.ui.printing.util.DiagramPrinterUtil
 * 
 * 
 * @author James Bruck (jbruck)
 */
public class JPSDiagramPrinterHelper extends DiagramPrinterHelper {

    private static JPSDiagramPrinterHelper jpsDiagramPrinterHelper;

    public static JPSDiagramPrinterHelper getDiagramPrinterHelper() {
        if (jpsDiagramPrinterHelper == null) {
            jpsDiagramPrinterHelper = new JPSDiagramPrinterHelper();
        }
        return jpsDiagramPrinterHelper;
    }

    /**
     * Prevent instantiation.
     */
    private JPSDiagramPrinterHelper() {
        super();
    }

    /**
     * Opens up the print diagrams dialog, allows the user to choose the settings, and prints.
     * 
     * @param editorPart
     *            current editor part
     * @param diagramMap
     *            map of String names to Diagram objects. Should be initialized by caller of this
     *            method. String names will show up in the print dialog that allows the user to
     *            choose which diagrams to print from a list.
     * @param jpsDiagramPrinter
     *            the diagram printer that does the work of actually printing the diagrams
     */
    public void printWithSettings(IDiagramWorkbenchPart editorPart, Map<String, IViewer> diagramMap,
            JPSDiagramPrinter jpsDiagramPrinter) {

        Display display =
                (Display.getCurrent() != null) ? Display.getCurrent() : Display.getDefault();

        try {
            PrintHelper helper = new PrintHelper();
            List<String> diagramNames = new ArrayList<String>(diagramMap.keySet());

            PrinterData printerData =
                    collectPrintInformation(jpsDiagramPrinter, helper, diagramNames, editorPart,
                            diagramMap);

            if (printerData != null) {

                jpsDiagramPrinter.setPrinter(printerData.name);
                jpsDiagramPrinter.setDisplayDPI(display.getDPI());
                jpsDiagramPrinter.setPrintHelper(helper);

                if (helper.getDlgDiagramPrintRangeCurrent()) {
                    IViewer viewer = editorPart.getViewer();
                    if (viewer instanceof PiccoloViewer) {
                        jpsDiagramPrinter.setDiagrams(Collections
                                .singletonList(((PiccoloViewer) viewer)));
                    } else {
                        throw new IllegalArgumentException("Viewer is not of type <PiccoloViewer>");
                    }
                } else if (helper.getDlgDiagramPrintRangeAll()) {
                    jpsDiagramPrinter.setDiagrams((Collection<PiccoloViewer>) Iterables.filter(
                            diagramMap.values(), PiccoloViewer.class));
                } else if (helper.getDlgDiagramPrintRangeSelection()) {
                    Object obj;
                    List<PiccoloViewer> list = new ArrayList<PiccoloViewer>();
                    for (int i = 0; i < diagramNames.size(); i++) {
                        if (helper.isDlgDiagramSelected(i)) {
                            obj = diagramMap.get(diagramNames.get(i));
                            if (obj instanceof PiccoloViewer) {
                                list.add((PiccoloViewer) obj);
                            }
                        }
                    }
                    jpsDiagramPrinter.setDiagrams(list);
                }
                printDiagrams(jpsDiagramPrinter, helper);
            }
        } catch (Throwable e) {
            e.printStackTrace();
//            Trace.catching(DiagramPrintingPlugin.getInstance(),
//                    DiagramPrintingDebugOptions.EXCEPTIONS_CATCHING, DiagramPrinterUtil.class,
//                    e.getMessage(), e);
//            Log.warning(DiagramPrintingPlugin.getInstance(),
//                    DiagramPrintingStatusCodes.RESOURCE_FAILURE, e.getMessage(), e);
//
//            MessageDialog.openError(display.getActiveShell(),
//                    DiagramUIPrintingMessages.JPSDiagramPrinterUtil_ErrorTitle,
//                    DiagramUIPrintingMessages.JPSDiagramPrinterUtil_ErrorMessage);
        }
    }
}
