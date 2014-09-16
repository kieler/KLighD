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
package de.cau.cs.kieler.klighd.ui.printing.dialogs;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;

/**
 * A section of the KlighD print dialog that adds printer options.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 * @author csp
 */
class PrinterBlock implements DialogBlock {
    /**
     * 
     */
    private final DataBindingContext bindings;
    private final PrintOptions options;
    private KlighdPrintDialog printDialog;

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

        Composite result =
                DialogUtil.group(parent, DiagramUIPrintingMessages.PrintDialog_Printer);
        DialogUtil.layout(result, COLUMNS);

        DialogUtil.label(result, DiagramUIPrintingMessages.PrintDialog_Name);
        Label printerLabel = DialogUtil.label(result, "");

        DialogUtil.layoutFillHorizontal(printerLabel, true);

        Button propertiesButton =
                DialogUtil.button(result, DiagramUIPrintingMessages.PrintDialog_Properties);
        propertiesButton.setEnabled(true);

        propertiesButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                openPrintOptionsDialog();
            }
        });

        final IObservableValue observablePrinterdata =
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_PRINTER_DATA);
        ComputedValue computedValue = new ComputedValue(realm) {

            @Override
            protected Object calculate() {
                PrinterData data = (PrinterData) observablePrinterdata.getValue();
                if (data.printToFile) {
                    return data.fileName;
                } else {
                    return data.name;
                }
            }
        };
        bindings.bindValue(SWTObservables.observeText(printerLabel), computedValue, null, null);

        return result;
    }

    private void openPrintOptionsDialog() {

        PrintDialog systemPrintDialog = new PrintDialog(printDialog.getShell());
        systemPrintDialog.setPrinterData(options.getPrinterData());
        PrinterData data = systemPrintDialog.open();
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
