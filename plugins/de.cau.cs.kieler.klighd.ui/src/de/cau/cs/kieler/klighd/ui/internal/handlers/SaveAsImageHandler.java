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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.klighd.IDiagramExporter;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.piccolo.KlighdPiccoloPlugin;

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

        final IViewer viewer;
        if (part instanceof IDiagramWorkbenchPart) {
            viewer = ((IDiagramWorkbenchPart) part).getViewer();
        } else {
            return null;
        }

        final Shell shell = viewer.getControl().getShell();

        // open the dialog to receive the required user input
        final SaveAsImageDialog dialog = new SaveAsImageDialog(viewer.getViewContext(), shell);
        final int code = dialog.open();

        if (code != Dialog.OK) {
            return null;
        }

        // retrieve the exporter from the central registry
        final IDiagramExporter exporter =
                KlighdDataManager.getInstance().getExporter(dialog.getCurrentExporter().exporterId);

        // execute the export process
        final IStatus res = exporter.export(viewer.getControl(), dialog.getExportData());

        if (res == Status.OK_STATUS) {
            final String title = "Diagram export successful.";
            final String msg = "KLighD diagram export finished successfully.";

            MessageDialog.openInformation(shell, title, msg);
            return null;

        } else if (res == null) {
            final String msg = "KLighD diagram export: " + exporter.getClass().getCanonicalName()
                    + " must return an IStatus!";
            StatusManager.getManager().handle(
                    new Status(IStatus.WARNING, KlighdPiccoloPlugin.PLUGIN_ID, msg),
                    StatusManager.SHOW);

        } else if (res.getException() instanceof OutOfMemoryError) {
            final String title = "Out of heap memory.";
            final String msg = "Diagram image could not be exported due to heap space overflow. "
                    + "Retry tiled export with more or smaller image tiles.";
            MessageDialog.openError(shell, title, msg);

        } else {
            StatusManager.getManager().handle(res, StatusManager.BLOCK | StatusManager.SHOW);
        }

        return null;
    }
}
