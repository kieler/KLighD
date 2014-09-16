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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;

/**
 * A section of the JPS print dialog that handles extra actions. In this case,
 * we contribute print preview capabilities.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 */
class ActionsBlock extends DialogBlock {
    private final PrintOptions options;

    private DataBindingContext bindings;
    private JPSPrintDialog printDialog;
    private SelectionListener printPreviewButtonListener = new SelectionAdapter() {

        /**
         * {@inheritDoc}
         */
        @Override
        public void widgetSelected(SelectionEvent e) {
            try {
                printDialog.openTray(new PrintPreviewTray(bindings, options, options
                        .getExporter()));
//                ((PrintPreviewTray) getTray()).updateComposite();
                ((Button) e.getSource()).setText("Print Preview <<");
            } catch (Exception ex) {
                ((PrintPreviewTray) printDialog.getTray()).dispose();
                printDialog.closeTray();
                ((Button) e.getSource()).setText("Print Preview >>");
            }
        }
    };

    ActionsBlock(IDialogUnitConverter dluConverter, DataBindingContext bindings,
            PrintOptions options, JPSPrintDialog printDialog) {
        super(dluConverter);
        this.options = options;
        this.bindings = bindings;
        this.printDialog = printDialog;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.gmf.runtime.common.ui.printing.internal.dialogs.DialogBlock#createContents(org
     * .eclipse.swt.widgets.Composite)
     */
    public Control createContents(Composite parent) {

        Button printPreview = new Button(parent, SWT.PUSH);
        printPreview.setText(DiagramUIPrintingMessages.JPSPrintDialog_Button_PrintPreview);
        layoutAlignRight(printPreview);
        printPreview.addSelectionListener(printPreviewButtonListener);

        return printPreview;
    }

}
