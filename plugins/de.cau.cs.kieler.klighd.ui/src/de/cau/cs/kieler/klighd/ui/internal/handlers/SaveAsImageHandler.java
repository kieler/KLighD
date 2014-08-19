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
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.klighd.IDiagramExporter;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IViewer;
import de.cau.cs.kieler.klighd.KlighdDataManager;

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
            // retrieve the exporter from the central registry
            final IDiagramExporter exporter =
                    KlighdDataManager.getInstance().getExporter(
                            dialog.getCurrentExporter().exporterId);
            // execute the export process
            exporter.export(dialog.getExportData(), viewer.getControl());
        }
        
        return null;
    }
}
