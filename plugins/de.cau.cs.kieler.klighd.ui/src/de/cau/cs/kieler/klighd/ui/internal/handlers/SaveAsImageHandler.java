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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.klighd.ExporterManager;
import de.cau.cs.kieler.klighd.ExporterManager.ExporterDescriptor;
import de.cau.cs.kieler.klighd.IDiagramExporter;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.ui.KlighdUIPlugin;
import de.cau.cs.kieler.klighd.ui.internal.Messages;

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
            try {
                // open the stream
                final OutputStream stream =
                        createOutputStream(dialog.getFilePath(), dialog.isWorkspacePath());
                // render the canvas to an image and write it to the stream
                toImage(stream, viewer.getControl(), dialog.isCameraViewport(),
                        dialog.getCurrentExporter(), dialog.getScaleFactor(),
                        dialog.isTextAsShapes(), dialog.isEmbedFonts());
                stream.close();
            } catch (final IOException exception) {
                final Status myStatus =
                        new Status(IStatus.WARNING, KlighdUIPlugin.PLUGIN_ID,
                                Messages.SaveAsImageAction_save_as_image_error, exception);
                StatusManager.getManager().handle(myStatus,
                        StatusManager.BLOCK | StatusManager.SHOW);
            }
        }
        
        return null;
    }

    private OutputStream createOutputStream(final IPath path, final boolean isWorkspacePath)
            throws IOException {
        if (isWorkspacePath) {
            // workspace path
            final URI fileURI = URI.createPlatformResourceURI(path.toOSString(), true);
            final OutputStream outputStream = URIConverter.INSTANCE.createOutputStream(fileURI);
            return outputStream;
        } else {
            // file system path
            final File file = new File(path.toString());
            final FileOutputStream outputStream = new FileOutputStream(file);
            return outputStream;
        }
    }

    /**
     * Renders a Piccolo2D canvas to an image and writes it in the specified file format to the
     * given stream.
     * 
     * @param stream
     *            the output stream to write the image data to
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
     */
    public static void toImage(final OutputStream stream, final Control control,
            final boolean cameraViewport, final ExporterDescriptor exporterDescr, final int scale,
            final boolean textAsShapes, final boolean embedFonts) {

        // retrieve the exporter from the central registry
        final IDiagramExporter exporter =
                ExporterManager.getInstance().getExporter(exporterDescr.getExporterId());
        // execute the export process
        exporter.export(stream, control, cameraViewport, scale, textAsShapes,
                embedFonts, exporterDescr.getSubFormatId());

    }

}
