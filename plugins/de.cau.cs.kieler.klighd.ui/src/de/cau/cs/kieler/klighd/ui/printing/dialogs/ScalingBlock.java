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
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;

/**
 * A section of the JPS print dialog that adds scaling support.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 */
class ScalingBlock extends DialogBlock {
    private final DataBindingContext bindings;
    private final PrintOptions options;

    ScalingBlock(IDialogUnitConverter dluConverter, DataBindingContext bindings,
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

        Composite result = group(parent, DiagramUIPrintingMessages.JPSPrintDialog_Scaling);
        layout(result, 1);
        
        Composite scalingGroup = new Composite(result, SWT.NONE);
        scalingGroup.setLayout(new GridLayout(4, false));
        layoutFillHorizontal(scalingGroup);

        label(scalingGroup, "Scale diagram to");
        Scale scaleFactor = scale(scalingGroup, 1, 100);
//        layoutSpanHorizontal(scaleFactor, 2);
        layoutFillHorizontal(scaleFactor);
        Label labelScale = label(scalingGroup, "100");
        GC gc = new GC(labelScale);
        layoutWidth(labelScale, gc.textExtent("100").x);
        label(scalingGroup, "percent of");

//        Button adjustRadio = radio(result, DiagramUIPrintingMessages.JPSPrintDialog_Adjust);
//        layoutSpanHorizontal(adjustRadio, 2);
//        Text textScale = text(result, 20);
//        layoutSpanHorizontal(blank(result), 2);

//        final IObservableValue scalingValue =
//                BeansObservables
//                        .observeValue(realm, options, PrintOptions.PROPERTY_PERCENT_SCALING);

//        bindings.bindValue(SWTObservables.observeSelection(adjustRadio), scalingValue, null, null);

        IObservableValue scaleValue =
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_SCALE_FACTOR);
        bindings.bindValue(SWTObservables.observeSelection(scaleFactor), scaleValue, null, null);
        bindings.bindValue(SWTObservables.observeText(labelScale), scaleValue, null, null);

//        Button fitToRadio = radio(result, DiagramUIPrintingMessages.JPSPrintDialog_FitTo);
//
//        IObservableValue fitToValue = new ComputedValue(realm) {
//            protected Object calculate() {
//                return Boolean.valueOf(!((Boolean) scalingValue.getValue()).booleanValue());
//            }
//        };

//        bindings.bindValue(SWTObservables.observeSelection(fitToRadio), fitToValue, null, null);
        
//        blank(result);
        
        Composite pagesGroup = new Composite(result, SWT.NONE);
        pagesGroup.setLayout(new GridLayout(4, false));
        layoutFillHorizontal(pagesGroup);

        Spinner spinnerWide = spinner(pagesGroup, 1, 100);
        layoutWidth(spinnerWide, 20);

        label(pagesGroup, "Pages wide by");

        Spinner spinnerTall = spinner(pagesGroup, 1, 100);
        layoutWidth(spinnerTall, 20);

        label(pagesGroup, "Pages tall");

        bindings.bindValue(SWTObservables.observeSelection(spinnerWide),
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_FIT_TO_WIDTH),
                null, null);

        bindings.bindValue(SWTObservables.observeSelection(spinnerTall),
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_FIT_TO_HEIGHT),
                null, null);

        return result;
    }

    @Override
    public void dispose() {
        // nothing special to dispose currently
    }
}
