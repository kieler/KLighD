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
package de.cau.cs.kieler.klighd.piccolo.viewer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;

/**
 * @author uru
 * @author chsch
 */
public class PrintAction implements Runnable {

    private PiccoloViewer viewer;

    /**
     * @param viewer
     *            Piccolo2D viewer we know how to print.
     */
    public PrintAction(final PiccoloViewer viewer) {
        this.viewer = viewer;
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        
        // open a dialog, ask for print information!
        PrintDialog printDialog = new PrintDialog(viewer.getControl().getShell(), SWT.NONE);
        printDialog.setText("Print KlighD View");

        PrinterData printerData = printDialog.open();
        if (!(printerData == null)) {

            // evaluate the user specified informations
            final Printer p = new Printer(printerData);
            final Rectangle trim = p.computeTrim(0, 0, 0, 0);
            final Rectangle clientArea = p.getClientArea();
            final Rectangle pageBounds = new Rectangle(clientArea.x - trim.x, clientArea.y - trim.y,
                    clientArea.width - trim.width, clientArea.height - trim.height);

            // start the print job
            p.startJob("Print KlighD View");
            p.startPage();

            // create and scale the GC according to dpis
            GC gc = new GC(p);

            // perform printing
            if (printerData.scope != PrinterData.SELECTION) {
                // print the whole diagram fitted into the page

                // let the Piccolo2d viewer take care of the rendering
                viewer.renderOffscreen(gc, pageBounds);

            } else {
                // only print the visible area

                // adjust to page's trim area
                Transform t = new Transform(gc.getDevice());
                gc.getTransform(t);
                t.translate(-trim.x, -trim.y);

                // fit it into the page dimensions
                Rectangle controlArea = viewer.getControl().getBounds();
                float scaleX = controlArea.width / (float) pageBounds.width;
                float scaleY = controlArea.height / (float) pageBounds.height;
                float minScale = 1 / Math.max(scaleX, scaleY);
                t.scale(minScale, minScale);

                gc.setTransform(t);

                viewer.getControl().print(gc);
            }

            // finish and cleanup
            p.endPage();
            gc.dispose();
            p.endJob();
            p.dispose();
        }
    }
}
