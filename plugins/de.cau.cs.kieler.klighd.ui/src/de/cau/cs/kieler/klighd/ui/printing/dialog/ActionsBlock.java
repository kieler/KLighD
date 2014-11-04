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
package de.cau.cs.kieler.klighd.ui.printing.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.cau.cs.kieler.klighd.ui.printing.KlighdUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.PrintOptions;

/**
 * A section of the KlighD print dialog that handles extra actions. In this case,
 * we contribute print preview capabilities.
 *
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 * @author csp
 */
final class ActionsBlock implements IDialogBlock {

    private static final String OPEN_ARROWS = " >>";
    private static final String CLOSE_ARROWS = " <<";

    private final KlighdPrintDialog printDialog;
    private final boolean previewInitiallyOpen;

    private Button printPreview;
    private final SelectionListener printPreviewButtonListener = new SelectionAdapter() {

        /**
         * {@inheritDoc}
         */
        @Override
        public void widgetSelected(final SelectionEvent e) {
            final PrintPreviewTray tray = printDialog.getTray();

            if (tray != null) {
                printDialog.closeTray();
                printPreview.setText(
                        KlighdUIPrintingMessages.PrintDialog_Button_PrintPreview + OPEN_ARROWS);
                PrintOptions.setInitiallyShowPreview(false);
                tray.dispose();

            } else {
                printDialog.openPreview();
                printPreview.setText(
                        KlighdUIPrintingMessages.PrintDialog_Button_PrintPreview + CLOSE_ARROWS);
                PrintOptions.setInitiallyShowPreview(true);
            }
        }
    };

    /**
     * Instantiates a new actions block.
     *
     * @param printDialog
     *            the print dialog to execute the actions on (e.g. show preview)
     * @param previewOpen TODO
     */
    ActionsBlock(final KlighdPrintDialog printDialog, final boolean previewOpen) {
        this.printDialog = printDialog;
        this.previewInitiallyOpen = previewOpen;
    }

    /**
     * {@inheritDoc}
     */
    public Control createContents(final Composite parent) {
        final String arrows = previewInitiallyOpen ? CLOSE_ARROWS : OPEN_ARROWS;

        printPreview = new Button(parent, SWT.PUSH);
        printPreview.setText(KlighdUIPrintingMessages.PrintDialog_Button_PrintPreview + arrows);
        printPreview.addSelectionListener(printPreviewButtonListener);

        DialogUtil.layoutAlignRight(printPreview);

        return printPreview;
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        printPreview.removeSelectionListener(printPreviewButtonListener);
    }
}
