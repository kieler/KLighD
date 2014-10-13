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
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.cau.cs.kieler.klighd.ui.printing.KlighdUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.PrintOptions;

/**
 * A section of the KlighD print dialog that handles the horizontal & vertical alignment of the
 * diagram to print.
 *
 * @author chsch
 */
final class AlignmentBlock implements IDialogBlock {

    private final DataBindingContext bindings;
    private final PrintOptions options;

    /**
     * Instantiates a new alignment block.
     * The bindings are used to bind observable GUI elements to print setting in the given options.
     *
     * @param bindings
     *            the bindings used for observables
     * @param options
     *            the current print options
     * @param printDialog
     *            the print dialog to execute the actions on (e.g. show preview)
     */
    AlignmentBlock(final DataBindingContext bindings, final PrintOptions options) {
        this.bindings = bindings;
        this.options = options;
    }

    /**
     * {@inheritDoc}
     */
    public Control createContents(final Composite parent) {
        final Realm realm = bindings.getValidationRealm();

        final int columns = 3;

        // create group
        final Composite result = DialogUtil.layout(
                DialogUtil.group(parent, KlighdUIPrintingMessages.PrintDialog_Alignment), columns);

        DialogUtil.label(result, "Center diagram");

        final Button centerHorizontally = DialogUtil.check(result,
                KlighdUIPrintingMessages.PrintDialog_Alignment_centerHorizontally);
        final Button centerVertically = DialogUtil.check(result,
                KlighdUIPrintingMessages.PrintDialog_Alignment_centerVertically);

        bindings.bindValue(SWTObservables.observeSelection(centerHorizontally),
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_CENTER_HORIZONTALLY),
                null, null);

        bindings.bindValue(SWTObservables.observeSelection(centerVertically),
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_CENTER_VERTICALLY),
                null, null);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
    }
}