// SUPPRESS CHECKSTYLE NEXT Header
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
package de.cau.cs.kieler.klighd.ui.printing.dialogs;

import org.eclipse.core.databinding.DataBindingContext;
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
 * A section of the KlighD print dialog that handles extra actions. In this case,
 * we contribute print preview capabilities.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 * @author csp
 */
class ActionsBlock implements DialogBlock {
    private final PrintOptions options;

    private DataBindingContext bindings;
    private KlighdPrintDialog printDialog;
    private Button printPreview;
    private SelectionListener printPreviewButtonListener = new SelectionAdapter() {

        private PrintPreviewTray tray;

        /**
         * {@inheritDoc}
         */
        @Override
        public void widgetSelected(final SelectionEvent e) {
            try {
                tray = new PrintPreviewTray(bindings, options);
                printDialog.openTray(tray);
                printPreview.setText(DiagramUIPrintingMessages.PrintDialog_Button_PrintPreview
                        + " <<");
            } catch (Exception ex) {
                tray.dispose();
                printDialog.closeTray();
                printPreview.setText(DiagramUIPrintingMessages.PrintDialog_Button_PrintPreview
                        + " >>");
            }
        }
    };

    ActionsBlock(final DataBindingContext bindings, final PrintOptions options,
            final KlighdPrintDialog printDialog) {
        this.options = options;
        this.bindings = bindings;
        this.printDialog = printDialog;
    }

    /**
     * {@inheritDoc}
     */
    public Control createContents(final Composite parent) {

        printPreview = new Button(parent, SWT.PUSH);
        printPreview.setText(DiagramUIPrintingMessages.PrintDialog_Button_PrintPreview + " >>");
        DialogUtil.layoutAlignRight(printPreview);
        printPreview.addSelectionListener(printPreviewButtonListener);

        return printPreview;
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        PrintPreviewTray printPreviewTray = (PrintPreviewTray) printDialog.getTray();
        if (printPreviewTray != null) {
            printPreviewTray.dispose();
        }
        printPreview.removeSelectionListener(printPreviewButtonListener);
    }

}
