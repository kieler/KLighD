/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.ui.view.syntheses.action;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;

import de.cau.cs.kieler.klighd.ide.model.ErrorModel;
import de.cau.cs.kieler.klighd.ide.syntheses.action.AbstractErrorModelShowExceptionAction;

/**
 * Action to open an ErrorDialog to show additional exception information of {@link ErrorModel}.
 * 
 * @author als
 * @kieler.design 2014-07-30 proposed
 * @kieler.rating 2014-07-30 proposed yellow
 * 
 */
public class ErrorModelShowExceptionAction extends AbstractErrorModelShowExceptionAction {

    /**
     * {@inheritDoc}
     */
    public ActionResult execute(final ActionContext context) {
        Object inputModel = context.getViewContext().getInputModel();
        ErrorModel errorModel = null;

        // get error model
        if (inputModel instanceof ErrorModel) {
            errorModel = (ErrorModel) inputModel;
        }
        if (errorModel != null) {
            // if exception present show in error dialog
            if (errorModel.getStackTrace() != null) {
                final Status status = new Status(IStatus.INFO, ID, errorModel.getMessage(),
                        new Exception(errorModel.getStackTrace()));
                ErrorDialog errorDialog = new ErrorDialog(
                        context.getViewContext().getDiagramWorkbenchPart().getSite().getShell(),
                        errorModel.getMessage(), errorModel.getMessage(), status, IStatus.INFO);
                errorDialog.setBlockOnOpen(false);
                errorDialog.create();
                errorDialog.open();
            }
        }
        return ActionResult.createResult(false);

    }

}
