// SUPPRESS CHECKSTYLE NEXT Header
/******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
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
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import de.cau.cs.kieler.klighd.ui.printing.KlighdUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.PrintOptions;

/**
 * A section of the KlighD print dialog that adds print range options.
 *
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 * @author csp
 * @author chsch
 */
final class RangeBlock {

    /**
     * Hidden standard constructor.
     */
    private RangeBlock() {
    }

    /**
     * Creates the 'Range' block contents.
     * The bindings are used to bind observable GUI elements to print setting in the given options.
     *
     * @param parent
     *            the parent {@link Composite} to use
     * @param bindings
     *            the {@link DataBindingContext} managing the employed
     *            {@link org.eclipse.core.databinding.Binding Bindings}
     * @param options
     *            the {@link PrintOptions} to be used
     * @return the created {@link Group}
     */
    public static Group createContents(final Composite parent, final DataBindingContext bindings,
            final PrintOptions options) {
        final int columns = 2;
        final int textWidth = 20;

        final Realm realm = bindings.getValidationRealm();

        // create group
        final Group result =
                DialogUtil.group(parent, KlighdUIPrintingMessages.PrintDialog_PrintRange);
        DialogUtil.layout(result, columns);

        // radio button for print all
        final Button allRadio = DialogUtil.radio(result, KlighdUIPrintingMessages.PrintDialog_All);
        DialogUtil.layoutSpanHorizontal(allRadio, columns);
        
        final IObservableValue<Object> allValue =
                BeanProperties.value(PrintOptions.class, PrintOptions.PROPERTY_ALL_PAGES).observe(realm, options);
        ISWTObservableValue<Object> observedAllPages = WidgetProperties.widgetSelection().observe(allRadio); 
        bindings.bindValue(observedAllPages, allValue);

        // radio button for defining a print range
        final Button rangeRadio = DialogUtil.radio(result, KlighdUIPrintingMessages.PrintDialog_Pages);
        DialogUtil.layoutSpanHorizontal(rangeRadio, columns);

        // the following ComputedValue is required for properly initializing the selection of
        //  'rangeRadio', which won't be done if 'rangeValue' would be initialized with just
        //  'SWTObservables.observeSelection(rangeRadio)' (during initialization from preferences
        //  as well as changes performed in the native print dialog);
        // note that the executions of 'calculate' are monitored and calls of 'allValue.getValue()'
        //  cause the attachment of valueChangeListeners on 'allValue' notifying 'rangeValue'
        // interestingly a set operation on 'rangeRadio', which would then cause a set operation
        //  on 'rangeValue', leads to a (caught) exception in AbstractObservableValue#doSetValue()
        //  since that method is not overridden by 'ComputedValue';
        // the update of 'rangeValue' is than caused by the de-selection of 'allValue' leading to
        //  a re-computation of rangValue by means of the above mentioned listeners (crazy shit ...);
        //  the than calculated result is used below for enabling the "from" & "to" text fields.
        final IObservableValue<Boolean> rangeValue = new ComputedValue<Boolean>(realm) {

            @Override
            protected Boolean calculate() {
                return ((Boolean) allValue.getValue()).booleanValue() ? Boolean.FALSE : Boolean.TRUE;
            }
        };
        ISWTObservableValue<Object> selection = WidgetProperties.widgetSelection().observe(rangeRadio);
        bindings.bindValue(selection, rangeValue);

        // range from (label & textfield)
        DialogUtil.layoutHorizontalIndent(DialogUtil.label(result,
                KlighdUIPrintingMessages.PrintDialog_From));

        final Text textFrom = DialogUtil.text(result, textWidth);

        final IObservableValue<Object> rangeFrom = 
                BeanProperties.value(PrintOptions.class, PrintOptions.PROPERTY_RANGE_FROM).observe(realm, options);
        ISWTObservableValue<String> observedModifiedTextFrom = WidgetProperties.text(SWT.Modify).observe(textFrom); 
        bindings.bindValue(observedModifiedTextFrom, rangeFrom);
        ISWTObservableValue<Boolean> observedEnabledFrom = WidgetProperties.enabled().observe(textFrom);
        bindings.bindValue(observedEnabledFrom, rangeValue);

        // range to (label & textfield)
        DialogUtil.layoutHorizontalIndent(
                DialogUtil.label(result, KlighdUIPrintingMessages.PrintDialog_To));

        final Text textTo = DialogUtil.text(result, textWidth);

        final IObservableValue<Object> rangeTo =
                BeanProperties.value(PrintOptions.class, PrintOptions.PROPERTY_RANGE_TO).observe(realm, options);
        
        ISWTObservableValue<String> observedModifyTextTo = WidgetProperties.text(SWT.Modify).observe(textTo);
        bindings.bindValue(observedModifyTextTo, rangeTo);
        ISWTObservableValue<Boolean> observedEnabledTo = WidgetProperties.enabled().observe(textTo);
        bindings.bindValue(observedEnabledTo, rangeValue);

        result.addListener(SWT.Dispose, new Listener() {

            public void handleEvent(final Event event) {
                // while the SWTObservableValues are disposed while disposing the corresponding widgets
                //  the Beans-based ones should be disposed explicitly
                allValue.dispose();
                rangeValue.dispose();
                rangeFrom.dispose();
                rangeTo.dispose();
            }
        });

        return result;
    }
}