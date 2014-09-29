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

import de.cau.cs.kieler.klighd.ui.printing.KlighdUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.PrintOptions;

/**
 * A section of the KlighD print dialog that adds print range options.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 * @author csp
 */
final class RangeBlock implements IDialogBlock {
    private final DataBindingContext bindings;
    private final PrintOptions options;

    /**
     * Instantiates a new range block.
     * The bindings are used to bind observable GUI elements to print setting in the given options.
     * 
     * @param bindings
     *            the bindings used for observables
     * @param options
     *            the current print options
     */
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

        // create group
        final Composite result =
                DialogUtil.group(parent, KlighdUIPrintingMessages.PrintDialog_PrintRange);
        DialogUtil.layout(result, COLUMNS);

        // radiobutton for print all
        final Button allRadio = DialogUtil.radio(result, KlighdUIPrintingMessages.PrintDialog_All);
        DialogUtil.layoutSpanHorizontal(allRadio, COLUMNS);

        final IObservableValue allValue =
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_ALL_PAGES);
        bindings.bindValue(SWTObservables.observeSelection(allRadio), allValue, null, null);

        //radiobutton for defining a print range
        final Button rangeRadio =
                DialogUtil.radio(result, KlighdUIPrintingMessages.PrintDialog_Pages);
        DialogUtil.layoutSpanHorizontal(rangeRadio, COLUMNS);

        final IObservableValue rangeValue = new ComputedValue(realm) {
            @Override
            protected Object calculate() {
                return Boolean.valueOf(!((Boolean) allValue.getValue()).booleanValue());
            }
        };
        bindings.bindValue(SWTObservables.observeSelection(rangeRadio), rangeValue, null, null);

        // range from (label & textfield)
        DialogUtil.layoutHorizontalIndent(DialogUtil.label(result,
                KlighdUIPrintingMessages.PrintDialog_From));
        final Text textFrom = DialogUtil.text(result, TEXT_WIDTH);

        bindings.bindValue(SWTObservables.observeText(textFrom, SWT.Modify),
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_RANGE_FROM),
                null, null);
        bindings.bindValue(SWTObservables.observeEnabled(textFrom), rangeValue, null, null);

        // range to (label & textfield)
        DialogUtil.layoutHorizontalIndent(DialogUtil.label(result,
                KlighdUIPrintingMessages.PrintDialog_To));
        final Text textTo = DialogUtil.text(result, TEXT_WIDTH);

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
