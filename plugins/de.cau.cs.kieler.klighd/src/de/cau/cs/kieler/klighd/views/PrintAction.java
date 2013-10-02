/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.views;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;

/**
 * @author uru
 * 
 */
public class PrintAction extends Action {

    private DiagramViewPart viewPart;

    /**
     * @param viewPart
     *            the parent viewpart to be printed.
     */
    public PrintAction(final DiagramViewPart viewPart) {
        this.viewPart = viewPart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {

        // open a dialog, ask for print information!
        PrintDialog printDialog = new PrintDialog(viewPart.getViewSite().getShell(), SWT.NONE);
        printDialog.setText("Print KlighD View");

        PrinterData printerData = printDialog.open();
        if (!(printerData == null)) {

            // evaluate the user specified informations
            Printer p = new Printer(printerData);
            Rectangle trim = p.computeTrim(0, 0, 0, 0);
            Rectangle clientArea = p.getClientArea();
            clientArea =
                    p.computeTrim(clientArea.x, clientArea.y, clientArea.width, clientArea.height);

            Point screenDPI = viewPart.getViewSite().getShell().getDisplay().getDPI();
            Point printerDPI = p.getDPI();
            // assume the dpi is equal for x and y
            float scaleFactor = printerDPI.x / (float) screenDPI.x;

            // start the print job
            p.startJob("Print KlighD View");
            p.startPage();

            // create and scale the GC according to dpis
            GC gc = new GC(p);
            Transform t = new Transform(gc.getDevice());
            t.scale(scaleFactor, scaleFactor);
            t.translate(-trim.x, -trim.y);
            gc.setTransform(t);

            // perform printing
            if (printerData.scope != PrinterData.SELECTION) {
                // print the whole diagram fitted into the page

            } else {
                // only print the visible area

                // fit it into the page dimensions
                Rectangle controlArea = viewPart.getContextViewer().getControl().getBounds();
                float scaleX = controlArea.width / (float) clientArea.width;
                float scaleY = controlArea.height / (float) controlArea.height;
                float minScale = Math.min(scaleX, scaleY);
                t.scale(minScale, minScale);
                gc.setTransform(t);

                viewPart.getContextViewer().getControl().print(gc);
            }

            // finish and cleanup
            p.endPage();
            gc.dispose();
            p.endJob();
            p.dispose();

        }

    }

}
