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
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
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
 * A section of the KlighD print dialog that handles the number of copies of a diagram to print.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 * @author csp
 */
class CopiesBlock implements DialogBlock {

    private final DataBindingContext bindings;
    private final PrintOptions options;

    private final Image collateOnImage = DiagramUIPrintingPluginImages.COLLATE_ON.createImage();
    private final Image collateOffImage = DiagramUIPrintingPluginImages.COLLATE_OFF.createImage();
    private IObservableValue collateObservable;
    private IValueChangeListener listener;

    CopiesBlock(final DataBindingContext bindings, final PrintOptions options) {
        this.bindings = bindings;
        this.options = options;
    }

    /**
     * {@inheritDoc}
     */
    public Control createContents(final Composite parent) {
        final Realm realm = bindings.getValidationRealm();

        Composite result =
                DialogUtil.group(parent, DiagramUIPrintingMessages.PrintDialog_Copies);
        DialogUtil.layout(result, 2);

        DialogUtil.label(result, DiagramUIPrintingMessages.PrintDialog_NumberOfCopies);
        Spinner copiesSpinner = DialogUtil.spinner(result, 1, Integer.MAX_VALUE);

        bindings.bindValue(SWTObservables.observeSelection(copiesSpinner),
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_COPIES), null,
                null);

        final Label collateImageButton = new Label(result, SWT.CENTER | SWT.SHADOW_NONE);

        DialogUtil.layoutAlignRight(collateImageButton);
        collateImageButton.setImage(collateOffImage);

        Button collateCheck =
                DialogUtil.check(result, DiagramUIPrintingMessages.PrintDialog_Collate);

        collateObservable =
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_COLLATE);
        bindings.bindValue(SWTObservables.observeSelection(collateCheck), collateObservable, null,
                null);

        listener = new IValueChangeListener() {

            public void handleValueChange(final ValueChangeEvent event) {
                if (options.isCollate()) {
                    collateImageButton.setImage(collateOnImage);
                } else {
                    collateImageButton.setImage(collateOffImage);
                }
            }
        };
        collateObservable.addValueChangeListener(listener);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        collateObservable.removeValueChangeListener(listener);
        collateOnImage.dispose();
        collateOffImage.dispose();
    }

}
