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
 * A section of the KlighD print dialog that adds range checking.
 * 
 * @author csp
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 */
class RangeBlock implements DialogBlock {
    private final DataBindingContext bindings;
    private final PrintOptions options;

    RangeBlock(final DataBindingContext bindings, final PrintOptions options) {
        this.bindings = bindings;
        this.options = options;
    }

    private static final int COLUMNS = 4;
    private static final int TEXT_WIDTH = 20;
    /**
     * {@inheritDoc}
     */
    public Control createContents(final Composite parent) {
        final Realm realm = bindings.getValidationRealm();

        Composite result =
                DialogUtil.group(parent, DiagramUIPrintingMessages.PrintDialog_PrintRange);
        DialogUtil.layout(result, COLUMNS);

        Button allRadio = DialogUtil.radio(result, DiagramUIPrintingMessages.PrintDialog_All);
        DialogUtil.layoutSpanHorizontal(allRadio, COLUMNS);

        final IObservableValue allValue =
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_ALL_PAGES);
        bindings.bindValue(SWTObservables.observeSelection(allRadio), allValue, null, null);

        Button rangeRadio =
                DialogUtil.radio(result, DiagramUIPrintingMessages.PrintDialog_Pages);
        DialogUtil.layoutSpanHorizontal(rangeRadio, COLUMNS);

        IObservableValue rangeValue = new ComputedValue(realm) {
            protected Object calculate() {
                return Boolean.valueOf(!((Boolean) allValue.getValue()).booleanValue());
            }
        };
        bindings.bindValue(SWTObservables.observeSelection(rangeRadio), rangeValue, null, null);

        DialogUtil.layoutHorizontalIndent(DialogUtil.label(result,
                DiagramUIPrintingMessages.PrintDialog_From));
        Text textFrom = DialogUtil.text(result, TEXT_WIDTH);

        DialogUtil.layoutHorizontalIndent(DialogUtil.label(result,
                DiagramUIPrintingMessages.PrintDialog_To));
        Text textTo = DialogUtil.text(result, TEXT_WIDTH);

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

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        // nothing to dispose
    }
}
