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
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Spinner;

import de.cau.cs.kieler.klighd.ui.printing.internal.DiagramUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;
import de.cau.cs.kieler.klighd.ui.printing.util.PrintExporter;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A section of the KlighD print dialog that adds scaling support.
 * 
 * @author csp
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 */
class ScalingBlock implements DialogBlock {

    private final DataBindingContext bindings;
    private final PrintOptions options;

    ScalingBlock(final DataBindingContext bindings, final PrintOptions options) {
        this.bindings = bindings;
        this.options = options;
    }

    private static final int MAX_PAGES = 100;
    private static final int BUTTONS_GROUP_COLUMNS = 3;
    private static final int PAGES_GROUP_COLUMNS = 5;
    private static final int SCALING_GROUP_COLUMNS = 4;
    private static final int SPINNER_WIDTH = 20;

    /**
     * {@inheritDoc}
     */
    public Control createContents(final Composite parent) {
        final Realm realm = bindings.getValidationRealm();

        Composite result = DialogUtil.group(parent, DiagramUIPrintingMessages.PrintDialog_Scaling);
        DialogUtil.layout(result, 1);

        Composite buttonsGroup = new Composite(result, SWT.NONE);
        DialogUtil.layoutFillHorizontal(buttonsGroup, true);
        buttonsGroup.setLayout(new GridLayout(BUTTONS_GROUP_COLUMNS, false));

        Button oneToOneBtn =
                DialogUtil
                        .button(buttonsGroup, DiagramUIPrintingMessages.PrintDialog_Scaling_to100);
        oneToOneBtn.addSelectionListener(new SelectionAdapter() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                options.setScaleFactor(1);
            }
        });

        Button fitToPagesBtn =
                DialogUtil.button(buttonsGroup,
                        DiagramUIPrintingMessages.PrintDialog_Scaling_fitPages);
        fitToPagesBtn.addSelectionListener(new SelectionAdapter() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                Rectangle printerBounds = PrintExporter.getPrinterBounds(new Printer(options.getPrinterData()));
                PBounds diagramBounds = options.getExporter().getDiagramBounds();
                double scaleX =
                        ((double) printerBounds.width) * options.getFitToPagesWidth()
                                / diagramBounds.width;
                double scaleY =
                        ((double) printerBounds.height) * options.getFitToPagesHeight()
                                / diagramBounds.height;
                options.setScaleFactor(Math.min(scaleX, scaleY));
            }
        });

        Button adjustPagesBtn =
                DialogUtil.button(buttonsGroup,
                        DiagramUIPrintingMessages.PrintDialog_Scaling_adjustPages);
        adjustPagesBtn.addSelectionListener(new SelectionAdapter() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                Rectangle bounds =
                        PrintExporter.getPrinterBounds(new Printer(options.getPrinterData()));
                PBounds size = options.getExporter().getDiagramBounds();
                options.setFitToPagesWidth((int) Math.ceil(size.width * options.getScaleFactor()
                        / bounds.width));
                options.setFitToPagesHeight((int) Math.ceil(size.height * options.getScaleFactor()
                        / bounds.height));
            }
        });

        Composite scalingGroup = new Composite(result, SWT.NONE);
        DialogUtil.layoutFillHorizontal(scalingGroup, true);
        scalingGroup.setLayout(new GridLayout(SCALING_GROUP_COLUMNS, false));

        DialogUtil.label(scalingGroup, DiagramUIPrintingMessages.PrintDialog_Scaling_lbl_scaleTo);
        final Scale scaleFactor = DialogUtil.scale(scalingGroup, 1, 200);
//        layoutSpanHorizontal(scaleFactor, 2);
        DialogUtil.layoutFillHorizontal(scaleFactor, true);
        Label labelScale = DialogUtil.label(scalingGroup, "");
        GC gc = new GC(labelScale);
        DialogUtil.layoutWidth(labelScale, gc.textExtent("0.00").x);
        DialogUtil.label(scalingGroup, DiagramUIPrintingMessages.PrintDialog_Scaling_lbl_percent);

        IObservableValue scaleValue =
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_SCALE_FACTOR);
        final ISWTObservableValue scaleSelectionRaw = SWTObservables.observeSelection(scaleFactor);

        ComputedValue scaleSelection = new ComputedValue(realm) {
            @Override
            protected Object calculate() {
                Integer value = (Integer) scaleSelectionRaw.getValue();
                // TODO find better scaling control (maybe something logarithmic)
                // SUPPRESS CHECKSTYLE NEXT 4 MagicNumber
                if (value < 100) {
                    return value / 100f;
                } else {
                    return (value - 80) * 5 / 100f;
                }
            }
        };
        bindings.bindValue(scaleSelection, scaleValue, null, null);
        bindings.bindValue(SWTObservables.observeText(labelScale), scaleValue, null, null);

        Composite pagesGroup = new Composite(result, SWT.NONE);
        DialogUtil.layoutFillHorizontal(pagesGroup, true);
        pagesGroup.setLayout(new GridLayout(PAGES_GROUP_COLUMNS, false));

        DialogUtil.label(pagesGroup, DiagramUIPrintingMessages.PrintDialog_Scaling_lbl_printTo);

        Spinner spinnerWide = DialogUtil.spinner(pagesGroup, 1, MAX_PAGES);
        DialogUtil.layoutWidth(spinnerWide, SPINNER_WIDTH);

        DialogUtil.label(pagesGroup, DiagramUIPrintingMessages.PrintDialog_Scaling_lbl_pagesWide);

        Spinner spinnerTall = DialogUtil.spinner(pagesGroup, 1, MAX_PAGES);
        DialogUtil.layoutWidth(spinnerTall, SPINNER_WIDTH);

        DialogUtil.label(pagesGroup, DiagramUIPrintingMessages.PrintDialog_Scaling_lbl_pagesTall);

        bindings.bindValue(SWTObservables.observeSelection(spinnerWide),
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_FIT_TO_WIDTH),
                null, null);

        bindings.bindValue(SWTObservables.observeSelection(spinnerTall),
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_FIT_TO_HEIGHT),
                null, null);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        // nothing to dispose
    }
}
