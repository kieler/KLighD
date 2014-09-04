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

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.ColorSupported;
import javax.print.attribute.standard.PrinterName;

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
 * A section of the options print dialog that handles the print color options.
 * 
 * @author James Bruck (jbruck)
 */
public class ColorBlock extends DialogBlock {

    private final DataBindingContext bindings;
    private final PrintOptions options;

    private Button colorRadio;
    private Button monoRadio;

    private Binding colorBinding;
    private Binding monoBinding;

    ColorBlock(IDialogUnitConverter dluConverter, DataBindingContext bindings, PrintOptions options) {
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

        Composite result = group(parent, DiagramUIPrintingMessages.JPSOptionsDialog_Color);
        layout(result, 2);

        colorRadio = radio(result, DiagramUIPrintingMessages.JPSOptionsDialog_ChromaticityColor);
        layoutSpanHorizontal(colorRadio, 4);

        monoRadio =
                radio(result, DiagramUIPrintingMessages.JPSOptionsDialog_ChromaticityMonochrome);
        layoutSpanHorizontal(monoRadio, 4);

        colorBinding =
                bindings.bindValue(SWTObservables.observeSelection(colorRadio), BeansObservables
                        .observeValue(realm, options, PrintOptions.PROPERTY_CHROMATICITY_COLOR),
                        null, null);

        monoBinding =
                bindings.bindValue(SWTObservables.observeSelection(monoRadio), BeansObservables
                        .observeValue(realm, options, PrintOptions.PROPERTY_CHROMATICITY_MONO),
                        null, null);

        initializeControls(options.getDestination().getName());

        return result;
    }

    /**
     * Initialize the enabled state of the controls based on the printer capabilities.
     * 
     * @param printerName
     */
    private void initializeControls(String printerName) {

        AttributeSet attributes =
                new HashPrintServiceAttributeSet(new PrinterName(printerName, null));

        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, attributes);

        PrintService printService = services[0];

        PrintServiceAttributeSet printServiceAttributes = printService.getAttributes();

        ColorSupported colorSupported =
                (ColorSupported) printServiceAttributes.get(ColorSupported.class);

        if (colorSupported == ColorSupported.SUPPORTED) {
            options.setChromaticityColor(true);
            options.setChromaticityMono(false);
            colorRadio.setEnabled(true);
        } else {
            options.setChromaticityColor(false);
            options.setChromaticityMono(true);
            colorRadio.setEnabled(false);
        }
    }

    @Override
    public void dispose() {
        bindings.removeBinding(colorBinding);
        colorBinding.dispose();
        bindings.removeBinding(monoBinding);
        monoBinding.dispose();
    }

}
