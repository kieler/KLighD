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
package de.cau.cs.kieler.klighd.ui.printing.util;

import java.awt.geom.AffineTransform;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.export.AbstractDiagramExporter;
import de.cau.cs.kieler.klighd.piccolo.internal.KlighdSWTGraphicsImpl;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.ui.printing.actions.PrintActionHelper;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * @author carsten
 * 
 */

public class PrintExporter extends AbstractDiagramExporter {

    private PiccoloViewer viewer;

    public PrintExporter(PiccoloViewer viewer) {
        this.viewer = viewer;
    }

    public PBounds getDiagramBounds() {
        return getExportedBounds(viewer.getCanvas().getCamera(), false);
    }

    /**
     * @param clip
     * @param scale
     * @param width
     * @param height
     * @return
     */
    public Image exportPreview(int column, int row, int width, int height, double scale) {

        Display display =
                (Display.getCurrent() != null) ? Display.getCurrent() : Display.getDefault();
        Image image = new Image(display, width, height);
        GC gc = new GC(image);
        export(column, row, new Rectangle(0, 0, width, height), scale, gc, display);
        gc.dispose();
        return image;
    }

    public void exportPrint(int column, int row, double scale, Printer printer) {

        GC gc = new GC(printer);
        final Rectangle pageBounds = PrintActionHelper.getPrinterBounds(printer);
        export(column, row, pageBounds, scale, gc, printer);
        gc.dispose();
    }

    private void export(int column, int row, Rectangle bounds, double scale, GC gc, Device device) {

        KlighdSWTGraphics graphics = new KlighdSWTGraphicsImpl(gc, device);

//            Transform transform = new Transform(gc.getDevice());
//            gc.getTransform(transform);
//            transform.translate(-clip.x, -clip.y);
//            gc.setTransform(transform);
        //
//            gc.setClipping(clip);

//        graphics.transform(AffineTransform.getTranslateInstance(bounds.x, bounds.y));
//        graphics.transform(AffineTransform.getTranslateInstance(
//                -column * (bounds.width), -row * (bounds.height)));
//        graphics.transform(AffineTransform.getScaleInstance(scale, scale));
//            graphics.setClip(clip);

//        PiccoloViewer viewer =
//                ((PiccoloViewer) ((IDiagramWorkbenchPart) PlatformUI.getWorkbench()
//                        .getActiveWorkbenchWindow().getActivePage().getActivePart()).getViewer()
//                        .getContextViewer().getActiveViewer());

        drawDiagram(viewer.getCanvas().getCamera(), false, graphics, new PBounds(bounds.x + column * (bounds.width),
                bounds.y + row * (bounds.height), bounds.width, bounds.height), scale);

//            ((PiccoloViewer) ((IDiagramWorkbenchPart) PlatformUI.getWorkbench()
//                    .getActiveWorkbenchWindow().getActivePage().getActivePart()).getViewer()
//                    .getContextViewer().getActiveViewer()).renderOffscreen(gc, new Rectangle(0, 0,
//                    totalWidth, totalHeight));
    }

}
