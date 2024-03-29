// SUPPRESS CHECKSTYLE NEXT Header
/******************************************************************************
 * Copyright (c) 2008, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.ui.printing.dialog;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.BeanProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
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
 * @author chsch
 */
final class PrinterBlock {

    /**
     * Hidden standard constructor.
     */
    private PrinterBlock() {

    }

    /**
     * Creates the 'Printer' block contents.
     * The bindings are used to bind observable GUI elements to print setting in the given options.
     *
     * @param parent
     *            the parent {@link Composite} to use
     * @param bindings
     *            the {@link DataBindingContext} managing the employed
     *            {@link org.eclipse.core.databinding.Binding Bindings}
     * @param options
     *            the {@link PrintOptions} to be used
     * @param printDialog
     *            the parent dialog used for the system print dialog
     * @return the created {@link Group}
     */
    public static Group createContents(final Composite parent, final DataBindingContext bindings,
            final PrintOptions options, final KlighdPrintDialog printDialog) {
        final int columns = 3;

        // create group
        final Group result =
                DialogUtil.group(parent, KlighdUIPrintingMessages.PrintDialog_Printer);
        DialogUtil.layout(result, columns);

        // printer resp. file name label
        DialogUtil.label(result, KlighdUIPrintingMessages.PrintDialog_Name);

        final Label printerLabel = DialogUtil.label(result, "");
        DialogUtil.layoutFillHorizontal(printerLabel, true);

        final Realm realm = bindings.getValidationRealm();

        // Observable to display the printer resp. file name, if print to file is set.
        final ComputedValue<String> computedValue = new ComputedValue<String>(realm) {

            @Override
            protected String calculate() {
                final PrinterData data = 
                        (PrinterData) BeanProperties
                        .value( PrintOptions.class, PrintOptions.PROPERTY_PRINTER_DATA)
                        .observe(realm, options)
                        .getValue();
                if (data.printToFile) {
                    return data.fileName;
                } else {
                    return data.name;
                }
            }
        };
        final ISWTObservableValue<String> labelText = WidgetProperties.text().observe(printerLabel); 
        bindings.bindValue(labelText, computedValue);

        // printer properties button
        final Button propertiesButton =
                DialogUtil.button(result, KlighdUIPrintingMessages.PrintDialog_PrinterSettings);
        propertiesButton.setEnabled(true);

        propertiesButton.addSelectionListener(SelectionListener.widgetSelectedAdapter(e -> {
            // Open the system's native print dialog to gather printer specific settings.
            // If no valid setting are returned (e.g. the user cancels the dialog),
            // nothing is changed.
            final PrintDialog systemPrintDialog = printDialog.getNativePrintDialog();
            systemPrintDialog.setPrinterData(options.getPrinterData());

            final PrinterData data = systemPrintDialog.open();
            if (data != null) {
                options.setPrinterData(data);
            }
        }));

        // while the SWTObservableValues are disposed while disposing the corresponding widgets
        // the ComputedValue should be disposed explicitly
        result.addListener(SWT.Dispose, event -> computedValue.dispose());

        return result;
    }
}
