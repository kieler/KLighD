/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.internal.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.klighd.IDiagramExporter;
import de.cau.cs.kieler.klighd.IDiagramExporter.ExportInfo;
import de.cau.cs.kieler.klighd.IDiagramExporter.ExportInfo.TilingInfo;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.KlighdDataManager.ExporterDescriptor;

/**
 * An action which invokes the 'save-as-image' dialog and performs the diagram export.
 * 
 * @author mri
 * @author uru
 * @author chsch
 */
public class SaveAsImageHandler extends AbstractHandler {

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final IWorkbenchPart part = HandlerUtil.getActivePart(event);
        
        final IViewer<?> viewer;
        if (part instanceof IDiagramWorkbenchPart) {
            viewer = ((IDiagramWorkbenchPart) part).getViewer();
        } else {
            return null;
        }
        
        // open the dialog to receive the required user input
        final SaveAsImageDialog dialog = new SaveAsImageDialog(viewer.getControl().getShell());
        final int code = dialog.open();
        if (code == Dialog.OK) {
            // render the canvas to an image and write it to the stream
            toImage(dialog.getFilePath(), dialog.isWorkspacePath(), viewer.getControl(),
                    dialog.isCameraViewport(), dialog.getCurrentExporter(),
                    dialog.getScaleFactor(), dialog.isTextAsShapes(), dialog.isEmbedFonts(),
                    dialog.getTilingInfo());
        }
        
        return null;
    }



    /**
     * Renders a Piccolo2D canvas to an image and writes it in the specified file format to the
     * given stream.
     * 
     * @param path
     *            the path to write the image to (either filesystem or workspace)
     * @param isWorkspacePath
     *            wether the given path should be interpreted as filesystem or workspace relative 
     * @param control
     *            the canvas
     * @param cameraViewport
     *            true if the scene graph should be rendered through the camera, i.e. only render
     *            what is visible on the canvas; false to render the whole scene graph
     * @param exporterDescr
     *            the descriptor of an {@link IViewExporter} selected by the user
     * @param scale
     *            the scale factor to apply while constructing the image
     * @param textAsShapes
     *            whether text in vector graphics should be rendered as shapes
     * @param embedFonts
     *            whether the texts' fonts shall be embedded in the output
     * @param tilingInfo 
     *            defines the way of tiling the later image
     */
    public static void toImage(final IPath path, final boolean isWorkspacePath,
            final Control control, final boolean cameraViewport,
            final ExporterDescriptor exporterDescr, final int scale, final boolean textAsShapes,
            final boolean embedFonts, final TilingInfo tilingInfo) {

        // retrieve the exporter from the central registry
        final IDiagramExporter exporter =
                KlighdDataManager.getInstance().getExporter(exporterDescr.getExporterId());
        // execute the export process
        ExportInfo info = new ExportInfo(path, isWorkspacePath, control, cameraViewport, scale,
                textAsShapes, embedFonts, exporterDescr.getSubFormatId());
        if (exporterDescr.supportsTiling() && tilingInfo.isTiled()) {
            info.setTilingInfo(tilingInfo);
        }
        exporter.export(info);
    }
}
