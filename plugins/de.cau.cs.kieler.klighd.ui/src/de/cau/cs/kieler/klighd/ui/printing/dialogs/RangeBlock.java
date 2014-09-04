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
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;

/**
 * A section of the JPS print dialog that adds range checking.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 */
class RangeBlock extends DialogBlock {
    private final DataBindingContext bindings;
    private final PrintOptions options;

    RangeBlock(IDialogUnitConverter dluConverter, DataBindingContext bindings, PrintOptions options) {
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

        Composite result = group(parent, DiagramUIPrintingMessages.JPSPrintDialog_PrintRange);
        layout(result, 4);

        Button allRadio = radio(result, DiagramUIPrintingMessages.JPSPrintDialog_All);
        layoutSpanHorizontal(allRadio, 4);

        final IObservableValue allValue =
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_ALL_PAGES);
        bindings.bindValue(SWTObservables.observeSelection(allRadio), allValue, null, null);

        Button rangeRadio = radio(result, DiagramUIPrintingMessages.JPSPrintDialog_Pages);
        layoutSpanHorizontal(rangeRadio, 4);

        IObservableValue rangeValue = new ComputedValue(realm) {
            protected Object calculate() {
                return Boolean.valueOf(!((Boolean) allValue.getValue()).booleanValue());
            }
        };
        bindings.bindValue(SWTObservables.observeSelection(rangeRadio), rangeValue, null, null);

        layoutHorizontalIndent(label(result, DiagramUIPrintingMessages.JPSPrintDialog_From));
        Text textFrom = text(result, 20);

        layoutHorizontalIndent(label(result, DiagramUIPrintingMessages.JPSPrintDialog_To));
        Text textTo = text(result, 20);

        bindings.bindValue(SWTObservables.observeText(textFrom, SWT.Modify),
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_RANGE_FROM),
                null, null);
        bindings.bindValue(SWTObservables.observeEnabled(textFrom), rangeValue, null, null);
        bindings.bindValue(SWTObservables.observeText(textTo, SWT.Modify),
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_RANGE_TO),
                null, null);
        bindings.bindValue(SWTObservables.observeEnabled(textTo), rangeValue, null, null);

        return result;
    }

    @Override
    public void dispose() {
        // nothing special to dispose currently
    }
}
