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
import org.eclipse.swt.widgets.Group;

import de.cau.cs.kieler.klighd.ui.printing.KlighdUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.PrintOptions;

/**
 * A section of the KlighD print dialog that handles the horizontal & vertical alignment of the
 * diagram to print.
 *
 * @author chsch
 */
final class AlignmentBlock {

    /**
     * Hidden standard constructor.
     */
    private AlignmentBlock() {
    }

    /**
     * Creates the 'Alignment' block contents.
     * The bindings are used to bind observable GUI elements to print setting in the given options.
     *
     * @param parent
     *            the parent {@link Composite} to use
     * @param bindings
     *            the bindings used for observables
     * @param options
     *            the current print options
     * @return the created {@link Group}
     */
    public static Group createContents(final Composite parent, final DataBindingContext bindings,
            final PrintOptions options) {
        final int columns = 2;

        // create group
        final Group result =
                DialogUtil.group(parent, KlighdUIPrintingMessages.PrintDialog_Alignment);
        DialogUtil.layout(result, columns);

        DialogUtil.label(result, "Center diagram");

        final Button centerHorizontally = DialogUtil.check(result,
                KlighdUIPrintingMessages.PrintDialog_Alignment_centerHorizontally);

        // a helper widget filling the 3rd quadrant
        DialogUtil.label(result, "").setVisible(false);

        final Button centerVertically = DialogUtil.check(result,
                KlighdUIPrintingMessages.PrintDialog_Alignment_centerVertically);

        final Realm realm = bindings.getValidationRealm();

        // SUPPRESS CHECKSTYLE NEXT 2 LineLength -- it's just one character ;-)
        bindings.bindValue(SWTObservables.observeSelection(centerHorizontally),
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_CENTER_HORIZONTALLY));

        bindings.bindValue(SWTObservables.observeSelection(centerVertically),
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_CENTER_VERTICALLY));

        return result;
    }
}
