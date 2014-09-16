/******************************************************************************
 * Copyright (c) 2005, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package de.cau.cs.kieler.klighd.ui.printing.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.ui.KlighdUIPlugin;
import de.cau.cs.kieler.klighd.ui.printing.dialogs.JPSPrintDialog;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;
import de.cau.cs.kieler.klighd.ui.printing.util.PrintExporter;

/**
 * Enhanced printing. The doPrint() method will invoke a dialog prompting the user to choose options
 * for printing. The user will be able to choose from printing diagrams of the current type. If
 * possible, the IFile path of the appicable diagrams will be displayed to the user, when prompting
 * the user to select a diagram for printing. If the diagram does not correspond to an IFile, its
 * part name will be used as the next choice.
 * 
 * This class implements the IPrintActionHelper interface that can be passed into Print Preview,
 * enabling the print action from there.
 * 
 * @author Wayne Diu, wdiu
 */
public class PrintActionHelper {

    /**
     * Show the print dialog and print
     * 
     * @param IWorkbenchPart
     *            the workbenchPart containing the diagram to print
     */

    public void doPrint(PiccoloViewer viewer) {
//        IDiagramWorkbenchPart diagramPart = null;
//
//        if (workbenchPart instanceof IDiagramWorkbenchPart) {
//            diagramPart = (IDiagramWorkbenchPart) workbenchPart;
//        } else {
//            throw new IllegalArgumentException("Invalid IWorkbenchPart.");
//        }

        // receive the preference store
        IPreferenceStore preferenceStore = KlighdUIPlugin.getDefault().getPreferenceStore();

        PrintOptions options = new PrintOptions(preferenceStore);

//        JPSDiagramPrinterHelper.getDiagramPrinterHelper().printWithSettings(diagramPart,
//                createDiagramMap(), new JPSDiagramPrinter(), options);
//        
        Map<String, IViewer> diagramMap = createDiagramMap();

        List<String> diagramNames = new ArrayList<String>(diagramMap.keySet());

        PrintExporter exporter = new PrintExporter(viewer);
        options.setExporter(exporter);
//
        JPSPrintDialog dlg =
                new JPSPrintDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), options,
                        diagramNames);

        if (dlg.open() != IDialogConstants.OK_ID) {
            return;
        }

        if (options.getPrinterData() != null) {

            options.getPrinterData().duplex = PrinterData.DUPLEX_NONE;
            options.getPrinterData().orientation = PrinterData.LANDSCAPE;

            // evaluate the user specified informations
            final Printer p = new Printer(options.getPrinterData());
//            final Rectangle trim = p.computeTrim(0, 0, 0, 0);

            // start the print job
            p.startJob(diagramNames.get(0));

            double scale = options.getScaleFactor();

//            int totalWidth = (int) (options.getFitToPagesWidth() * pageBounds.width * userScale);
//            int totalHeight = (int) (options.getFitToPagesHeight() * pageBounds.height * userScale);

            for (int i = 0; i < options.getFitToPagesHeight(); i++) {
                for (int j = 0; j < options.getFitToPagesWidth(); j++) {
                    p.startPage();

                    // create and scale the GC according to dpis
                    exporter.exportPrint(j, i, scale, p);
//                    Transform t = new Transform(gc.getDevice());
//                    gc.getTransform(t);
////                    t.translate(-trim.x, -trim.y);
//                    t.translate(-j * pageBounds.width, -i * pageBounds.height);
//                    gc.setTransform(t);
//
//                    gc.setClipping(new Rectangle(j * pageBounds.width, i * pageBounds.height,
//                            pageBounds.width, pageBounds.height));
//
//                    viewer.renderOffscreen(gc, new Rectangle(0, 0, totalWidth, totalHeight));

                    p.endPage();
                }
            }

            // finish and cleanup
            p.endJob();
            p.dispose();
        }
    }

    /**
     * Return a Map with diagram name String as key and Diagram as value All entries in the map
     * correspond to open editors.
     * 
     * @return Map with diagram name String as key and Diagram as value All entries in the map
     *         correspond to open editors with the diagramEditor's id.
     */
    protected Map<String, IViewer> createDiagramMap() {

        Map<String, IViewer> diagramMap = new HashMap<String, IViewer>();
        // TODO List all possible KlighD parts for printing;
        // currently only the active part
        List<IWorkbenchPart> diagramEditors =
                Collections.singletonList(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                        .getActivePage().getActivePart());

        Iterator<IWorkbenchPart> it = diagramEditors.iterator();
        while (it.hasNext()) {
            Object obj = it.next();

            if (obj instanceof IDiagramWorkbenchPart) {

                IDiagramWorkbenchPart dEditor = (IDiagramWorkbenchPart) obj;

                diagramMap.put(dEditor.getTitle(), dEditor.getViewer());

            }
        }
        return diagramMap;
    }

    public static Rectangle getPrinterBounds(Printer printer) {
        Rectangle pageArea = printer.getClientArea();
        Rectangle trim = printer.computeTrim(0, 0, 0, 0);
        return new Rectangle(-trim.x, -trim.y, pageArea.width, pageArea.height);
//        return pageArea;
    }

}
