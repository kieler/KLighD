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

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;

/**
 * A section of the options print dialog that handles the duplex/single sided printing.
 * 
 * @author James Bruck (jbruck)
 */
public class SidesBlock extends DialogBlock {

    private final DataBindingContext bindings;
    private final PrintOptions options;
    private Binding oneSidedBinding;
    private Binding tumbleBinding;
    private Binding duplexBinding;

    SidesBlock(IDialogUnitConverter dluConverter, DataBindingContext bindings, PrintOptions options) {
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

        Composite result = group(parent, DiagramUIPrintingMessages.JPSOptionsDialog_Sides);
        layout(result, 2);

        Button oneSideRadio =
                radio(result, DiagramUIPrintingMessages.JPSOptionsDialog_SidesOneSided);
        layoutSpanHorizontal(oneSideRadio, 4);

        Button tumbleRadio = radio(result, DiagramUIPrintingMessages.JPSOptionsDialog_SidesTumble);
        layoutSpanHorizontal(tumbleRadio, 4);

        Button duplexRadio = radio(result, DiagramUIPrintingMessages.JPSOptionsDialog_SidesDuplex);
        layoutSpanHorizontal(duplexRadio, 4);

        oneSidedBinding =
                bindings.bindValue(SWTObservables.observeSelection(oneSideRadio), BeansObservables
                        .observeValue(realm, options, PrintOptions.PROPERTY_SIDES_ONESIDED), null,
                        null);

        tumbleBinding =
                bindings.bindValue(SWTObservables.observeSelection(tumbleRadio), BeansObservables
                        .observeValue(realm, options, PrintOptions.PROPERTY_SIDES_TUMBLE), null,
                        null);

        duplexBinding =
                bindings.bindValue(SWTObservables.observeSelection(duplexRadio), BeansObservables
                        .observeValue(realm, options, PrintOptions.PROPERTY_SIDES_DUPLEX), null,
                        null);

        return result;
    }

    @Override
    public void dispose() {
        bindings.removeBinding(oneSidedBinding);
        oneSidedBinding.dispose();
        bindings.removeBinding(tumbleBinding);
        tumbleBinding.dispose();
        bindings.removeBinding(duplexBinding);
        duplexBinding.dispose();
    }

}
