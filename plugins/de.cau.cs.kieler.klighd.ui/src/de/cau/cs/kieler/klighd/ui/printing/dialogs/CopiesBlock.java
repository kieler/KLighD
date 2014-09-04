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
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingPluginImages;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;

/**
 * A section of the JPS print dialog that handles the number of copies of a diagram to print.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 */
class CopiesBlock extends DialogBlock {

    private final DataBindingContext bindings;
    private final PrintOptions options;

    private final Image collateOnImage = DiagramUIPrintingPluginImages.COLLATE_ON.createImage();
    private final Image collateOffImage = DiagramUIPrintingPluginImages.COLLATE_OFF.createImage();

    CopiesBlock(IDialogUnitConverter dluConverter, DataBindingContext bindings, PrintOptions options) {
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

        Composite result = group(parent, DiagramUIPrintingMessages.JPSPrintDialog_Copies);
        layout(result, 2);

        label(result, DiagramUIPrintingMessages.JPSPrintDialog_NumberOfCopies);
        Spinner copiesSpinner = spinner(result, 1, 999);

        bindings.bindValue(SWTObservables.observeSelection(copiesSpinner),
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_COPIES), null,
                null);

        final Label collateImageButton = new Label(result, SWT.CENTER | SWT.SHADOW_NONE);

        layoutAlignRight(collateImageButton);
        collateImageButton.setImage(collateOffImage);

        Button collateCheck = check(result, DiagramUIPrintingMessages.JPSPrintDialog_Collate);

        bindings.bindValue(SWTObservables.observeSelection(collateCheck),
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_COLLATE), null,
                null);

        collateCheck.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent arg0) {
                // do nothing
            }

            public void widgetSelected(SelectionEvent arg0) {
                if (options.isCollate()) {
                    collateImageButton.setImage(collateOnImage);
                } else {
                    collateImageButton.setImage(collateOffImage);
                }
            }
        });

        return result;
    }

    /**
     * Dispose of images.
     */
    public void dispose() {
        collateOnImage.dispose();
        collateOffImage.dispose();
    }

}
