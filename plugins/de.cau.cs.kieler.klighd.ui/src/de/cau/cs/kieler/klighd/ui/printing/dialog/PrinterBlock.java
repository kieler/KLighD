// SUPPRESS CHECKSTYLE NEXT Header
/******************************************************************************
 * Copyright (c) 2008, 2010 IBM Corporation and others.
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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import de.cau.cs.kieler.klighd.ui.printing.KlighdUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.PrintOptions;

/**
 * A section of the KlighD print dialog that adds printer options. Printer specific options are
 * gathered via the native system print dialog.
 *
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 * @author csp
 */
final class PrinterBlock implements IDialogBlock {

    private final DataBindingContext bindings;
    private final PrintOptions options;
    private final KlighdPrintDialog printDialog;

    /**
     * Instantiates a new printer block.
     * The bindings are used to bind observable GUI elements to print setting in the given options.
     *
     * @param bindings
     *            the bindings used for observables
     * @param options
     *            the current print options
     * @param printDialog
     *            the parent dialog used for the system print dialog
     */
    PrinterBlock(final DataBindingContext bindings, final PrintOptions options,
            final KlighdPrintDialog printDialog) {
        this.bindings = bindings;
        this.options = options;
        this.printDialog = printDialog;
    }

    private static final int COLUMNS = 3;
    /**
     * {@inheritDoc}
     */
    public Control createContents(final Composite parent) {
        final Realm realm = bindings.getValidationRealm();

        // create group
        final Composite result =
                DialogUtil.group(parent, KlighdUIPrintingMessages.PrintDialog_Printer);
        DialogUtil.layout(result, COLUMNS);

        // printer resp. file name label
        DialogUtil.label(result, KlighdUIPrintingMessages.PrintDialog_Name);
        final Label printerLabel = DialogUtil.label(result, "");
        DialogUtil.layoutFillHorizontal(printerLabel, true);

        // Observable to display the printer resp. file name, if print to file is set.
        final ComputedValue computedValue = new ComputedValue(realm) {

            @Override
            protected Object calculate() {
                final PrinterData data =
                        (PrinterData) BeansObservables.observeValue(realm, options,
                                PrintOptions.PROPERTY_PRINTER_DATA).getValue();
                if (data.printToFile) {
                    return data.fileName;
                } else {
                    return data.name;
                }
            }
        };
        bindings.bindValue(SWTObservables.observeText(printerLabel), computedValue, null, null);

        // printer properties button
        final Button propertiesButton =
                DialogUtil.button(result, KlighdUIPrintingMessages.PrintDialog_PrinterSettings);
        propertiesButton.setEnabled(true);

        propertiesButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                openPrintOptionsDialog();
            }
        });

        return result;
    }



    /**
     * Open the system's native print dialog to gather printer specific settings.
     * If no valid setting are returned (e.g. the user cancels the dialog), nothing is changed.
     */
    private void openPrintOptionsDialog() {
        final PrintDialog systemPrintDialog = new PrintDialog(printDialog.getShell());
        systemPrintDialog.setPrinterData(options.getPrinterData());

        final PrinterData data = systemPrintDialog.open();
        if (data != null) {
            options.setPrinterData(data);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        // nothing to dispose
    }
}
