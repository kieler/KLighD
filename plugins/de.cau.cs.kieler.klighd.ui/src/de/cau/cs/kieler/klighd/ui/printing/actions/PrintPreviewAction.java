/******************************************************************************
 * Copyright (c) 2002, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package de.cau.cs.kieler.klighd.ui.printing.actions;

import org.eclipse.jface.action.Action;

import de.cau.cs.kieler.klighd.ui.printing.dialogs.PrintPreviewTray;
import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingMessages;

/**
 * This is the action for print preview. It opens a dialog showing how the diagram will look when
 * printed. The <code>PrintPreviewHelper</code> passed into the constructor does the actual work of
 * the print preview. The <code>IPrintActionHelper</code> passed into the constructor does the
 * actual work of showing the print settings dialog and doing the print if the user were to initiate
 * a print from within the print preview dialog.
 * 
 * @author Wayne Diu, wdiu
 */
public class PrintPreviewAction extends Action {

    /**
     * ID of the print preview action.
     */
    public static final String ID = "printPreviewAction"; //$NON-NLS-1$

    /**
     * Print action helper used if the user does a print from within the print preview dialog.
     */
    private PrintActionHelper printActionHelper;

    /**
     * The <code>PrintPreviewHelper</code> on which <code>doPrintPreview()</code> is called.
     */
    private PrintPreviewTray printPreviewHelper;

    /**
     * Creates a new instance.
     * 
     * @param printActionHelper
     *            the helper class used to show the print settings dialog and do a print if the user
     *            were to try to print from within the print preview dialog.
     * @param printPreviewHelper
     *            the helper class that creates that creates the print preview dialog
     */
    protected PrintPreviewAction(PrintActionHelper printActionHelper,
            PrintPreviewTray printPreviewHelper) {
        setId(ID);
        setText(DiagramUIPrintingMessages.PrintPreview_ActionLabel);
        setPrintActionHelper(printActionHelper);
        setPrintPreviewHelper(printPreviewHelper);
    }

    /**
     * Creates a new instance.
     * 
     * @param printActionHelper
     *            the helper class used to show the print settings dialog and perform the actual
     *            printing if the user were to print from within the print preview dialog.
     */
    public PrintPreviewAction(PrintActionHelper printActionHelper) {
        this(printActionHelper, new PrintPreviewTray());
    }

    /**
     * Opens a dialog showing how the diagram will look when printed. Uses the print preview helper
     * and optionally the print action helper.
     */
    public void run() {
        getPrintPreviewHelper().doPrintPreview(getPrintActionHelper());
    }

    /**
     * Return the print action helper that helps perform the print action in the print preview
     * dialog.
     * 
     * @return IPrintActionHelper the print action helper.
     */
    private PrintActionHelper getPrintActionHelper() {
        return printActionHelper;
    }

    /**
     * Set the print action helper that helps perform the print action in the print preview dialog.
     * 
     * @param printActionHelper
     *            the IPrintActionHelper
     */
    protected void setPrintActionHelper(PrintActionHelper printActionHelper) {
        this.printActionHelper = printActionHelper;
    }

    /**
     * Return the print preview helper responsible for performing the print preview.
     * 
     * @return PrintPreviewHelper the print preview helper.
     */
    private PrintPreviewTray getPrintPreviewHelper() {
        return printPreviewHelper;
    }

    /**
     * Set the print preview helper responsible for performing the print preview.
     * 
     * @return printPreviewHelper the PrintPreviewHelper
     */
    protected void setPrintPreviewHelper(PrintPreviewTray printPreviewHelper) {
        this.printPreviewHelper = printPreviewHelper;
    }

}
