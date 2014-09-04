/******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package de.cau.cs.kieler.klighd.ui.printing.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.cau.cs.kieler.klighd.ui.printing.actions.PrintActionHelper;
import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;
import de.cau.cs.kieler.klighd.ui.printing.preview.PrintPreviewHelper;

/**
 * A section of the JPS print dialog that handles extra actions. In this case, we contribute print
 * preview capabilities.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 */
class ActionsBlock extends DialogBlock {
    private final PrintOptions options;

    private Button printPreview;
    private PrintPreviewHelper printPreviewHelper;
    private PrintActionHelper printActionHelper;

    ActionsBlock(IDialogUnitConverter dluConverter, PrintOptions options) {
        super(dluConverter);
        this.options = options;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.gmf.runtime.common.ui.printing.internal.dialogs.DialogBlock#createContents(org
     * .eclipse.swt.widgets.Composite)
     */
    public Control createContents(Composite parent) {
        printPreview = new Button(parent, SWT.PUSH);
        printPreview.setData(new Integer(2));
        printPreview.setText(DiagramUIPrintingMessages.JPSPrintDialog_Button_PrintPreview);

        printPreview.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                buttonPressed(((Integer) event.widget.getData()).intValue());
            }
        });
        layoutVerticalIndent(layoutAlignLeft(printPreview));

        return printPreview;
    }

    /**
     * Bring up the print preview with printing disabled.
     * 
     * @param buttonId
     */
    protected void buttonPressed(int buttonId) {
        switch (buttonId) {
        case -1:
            break;
        default:
            PrintPreviewHelper previewHelper = getPrintPreviewHelper();

//            PrintHelperUtil.setScale(options.getScaleFactor());
//            PrintHelperUtil.setScaleToWidth(options.getFitToPagesWidth());
//            PrintHelperUtil.setScaleToHeight(options.getFitToPagesHeight());
//            previewHelper.enablePrinting(false);
//            previewHelper.setDestination(options.getDestination());
//
//            previewHelper.setScaleFactor(options.getScaleFactor());
//            previewHelper.setPages(options.getFitToPagesWidth(), options.getFitToPagesHeight());
            
            previewHelper.setOptions(options);

            previewHelper.doPrintPreview(getPrintActionHelper());

//            options.setScaleFactor(PrintHelperUtil.getScale());
//            options.setFitToPagesWidth(PrintHelperUtil.getScaleToWidth());
//            options.setFitToPagesHeight(PrintHelperUtil.getScaleToHeight());
        }
    }

    /**
     * Return the print preview helper responsible for performing the print preview.
     * 
     * @return PrintPreviewHelper the print preview helper.
     */
    private PrintPreviewHelper getPrintPreviewHelper() {
        if (printPreviewHelper == null) {
            printPreviewHelper = new PrintPreviewHelper();
        }
        return printPreviewHelper;
    }

    private PrintActionHelper getPrintActionHelper() {
        if (printActionHelper == null) {
            printActionHelper = new PrintActionHelper();
        }
        return printActionHelper;
    }

    @Override
    public void dispose() {
        // nothing special to dispose
    }

}
