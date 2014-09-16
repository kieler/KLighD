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
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;

/**
 * A section of the JPS print dialog that adds printer options.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 */
class PrinterBlock extends DialogBlock {
    private final DataBindingContext bindings;
    private final PrintOptions options;

    PrinterBlock(IDialogUnitConverter dluConverter, DataBindingContext bindings,
            PrintOptions options) {

        super(dluConverter);

        this.bindings = bindings;
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
        final Realm realm = bindings.getValidationRealm();

        Composite result = group(parent, DiagramUIPrintingMessages.JPSPrintDialog_Printer);
        layout(result, 3);

        label(result, DiagramUIPrintingMessages.JPSPrintDialog_Name);
        Label printerLabel = label(result, "");

        layoutFillHorizontal(printerLabel);

        Button propertiesButton =
                button(result, DiagramUIPrintingMessages.JPSPrintDialog_Properties);
        propertiesButton.setEnabled(true);

        propertiesButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
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
        bindings.bindValue(SWTObservables.observeText(printerLabel), computedValue, null,
                null);

        return result;
    }

    private void openPrintOptionsDialog() {

        PrintDialog printDialog = new PrintDialog(getShell());
        printDialog.setPrinterData(options.getPrinterData());
        PrinterData data = printDialog.open();
        if (data != null) {
            options.setPrinterData(data);
        }
    }
}
