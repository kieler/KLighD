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
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import de.cau.cs.kieler.klighd.ui.printing.KlighdUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.PrintOptions;

/**
 * A section of the KlighD print dialog that adds page orientation options.<br>
 * It is inspired by the {@link RangeBlock}.
 *
 * @author chsch
 */
final class OrientationBlock implements IDialogBlock {

    private final DataBindingContext bindings;
    private final PrintOptions options;

    /**
     * Instantiates a new orientation block.
     * The bindings are used to bind observable GUI elements to print setting in the given options.
     *
     * @param bindings
     *            the bindings used for observables
     * @param options
     *            the current print options
     */
    OrientationBlock(final DataBindingContext bindings, final PrintOptions options) {
        this.bindings = bindings;
        this.options = options;
    }

    private static final int COLUMNS = 1;

    /**
     * {@inheritDoc}
     */
    public Control createContents(final Composite parent) {
        final Realm realm = bindings.getValidationRealm();

        // create group
        final Composite result =
                DialogUtil.group(parent, KlighdUIPrintingMessages.PrintDialog_Orientation_orientation);
        DialogUtil.layout(result, COLUMNS);

        // radio button for portrait
        final Button portraitRadio =
                DialogUtil.radio(result, KlighdUIPrintingMessages.PrintDialog_Orientation_portrait);
        DialogUtil.layoutSpanHorizontal(portraitRadio, COLUMNS);

        // radio button for landscape
        final Button landscapeRadio =
                DialogUtil.radio(result, KlighdUIPrintingMessages.PrintDialog_Orientation_landscape);
        DialogUtil.layoutSpanHorizontal(landscapeRadio, COLUMNS);

        final SelectObservableValue orientationGroupValue = new SelectObservableValue(realm);
        orientationGroupValue.addOption(PrinterData.PORTRAIT,
                SWTObservables.observeSelection(portraitRadio));
        orientationGroupValue.addOption(PrinterData.LANDSCAPE,
                SWTObservables.observeSelection(landscapeRadio));

        bindings.bindValue(orientationGroupValue,
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_ORIENTATION));

        result.addListener(SWT.Dispose, new Listener() {

            public void handleEvent(final Event event) {
                // although the 'option SWTObservables' are automatically disposed via the radio buttons
                //  the 'SelectObservableValue' is not, so...
                orientationGroupValue.dispose();
            }
        });

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        // nothing to dispose
    }
}
