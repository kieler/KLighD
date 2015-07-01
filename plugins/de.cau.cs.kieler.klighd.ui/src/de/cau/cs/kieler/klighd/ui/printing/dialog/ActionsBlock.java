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
 * + Kiel University
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.cau.cs.kieler.klighd.ui.printing.DiagramPrintOptions;
import de.cau.cs.kieler.klighd.ui.printing.KlighdUIPrintingMessages;

/**
 * A section of the KlighD print dialog that handles extra actions. In this case,
 * we contribute print preview capabilities.
 *
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 * @author csp
 * @author chsch
 */
final class ActionsBlock {

    private static final String OPEN_ARROWS = " >>";
    private static final String CLOSE_ARROWS = " <<";

    /**
     * Hidden standard constructor.
     */
    private ActionsBlock() {
    }

    /**
     * Creates the action block contents.
     * The bindings are used to bind observable GUI elements to print setting in the given options.
     *
     * @param parent
     *            the parent {@link Composite} to use
     * @param printDialog
     *            the print dialog to execute the actions on (e.g. show preview)
     * @param previewEnabled
     *            TODO
     * @param previewInitiallyOpen
     *            TODO
     * @return the created {@link Control}
     */
    public static Control createContents(final Composite parent, final KlighdPrintDialog printDialog,
            final boolean previewEnabled, final boolean previewInitiallyOpen) {
        final String arrows = previewInitiallyOpen ? CLOSE_ARROWS : OPEN_ARROWS;

        final Button printPreview = new Button(parent, SWT.PUSH);
        DialogUtil.layoutAlignRight(printPreview);

        printPreview.setText(KlighdUIPrintingMessages.PrintDialog_Button_PrintPreview + arrows);
        printPreview.setEnabled(previewEnabled);
        printPreview.addSelectionListener(new SelectionAdapter() {

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
                    DiagramPrintOptions.setInitiallyShowPreview(false);

                } else {
                    printDialog.openPreview();
                    printPreview.setText(
                            KlighdUIPrintingMessages.PrintDialog_Button_PrintPreview + CLOSE_ARROWS);
                    DiagramPrintOptions.setInitiallyShowPreview(true);
                }
            }
        });

        return printPreview;
    }
}
