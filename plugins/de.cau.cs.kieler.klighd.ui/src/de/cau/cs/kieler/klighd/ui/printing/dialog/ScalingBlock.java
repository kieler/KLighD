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
package de.cau.cs.kieler.klighd.ui.printing.dialog;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Spinner;

import de.cau.cs.kieler.klighd.ui.printing.KlighdUIPrintingMessages;
import de.cau.cs.kieler.klighd.ui.printing.PrintExporter;
import de.cau.cs.kieler.klighd.ui.printing.PrintOptions;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A section of the KlighD print dialog that adds scaling support.
 *
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 * @author csp
 * @author chsch
 */
final class ScalingBlock implements IDialogBlock {

    private final DataBindingContext bindings;
    private final PrintOptions options;

    /**
     * Instantiates a new scaling block.
     * The bindings are used to bind observable GUI elements to print setting in the given options.
     *
     * @param bindings
     *            the bindings used for observables
     * @param options
     *            the current print options
     */
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

        // create group
        final Composite result = DialogUtil.group(parent, KlighdUIPrintingMessages.PrintDialog_Scaling);
        DialogUtil.layout(result, 1);

        // This group contains three composites to be independent from the gridlayout's columns in
        // each row.

        // Group containing 3 buttons:
        // - scale to 100%
        // - scale to fit pages
        // - adjust number of pages to fit diagram
        final Composite buttonsGroup = new Composite(result, SWT.NONE);
        DialogUtil.layoutFillHorizontal(buttonsGroup, true);
        buttonsGroup.setLayout(new GridLayout(BUTTONS_GROUP_COLUMNS, false));

        final Button oneToOneBtn =
                DialogUtil
                .button(buttonsGroup, KlighdUIPrintingMessages.PrintDialog_Scaling_to100);
        oneToOneBtn.addSelectionListener(new SelectionAdapter() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                options.setScaleFactor(1);
            }
        });

        final Button fitToPagesBtn = DialogUtil.button(
                buttonsGroup, KlighdUIPrintingMessages.PrintDialog_Scaling_fitPages);

        fitToPagesBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                // Calculate the minimum of necessary horizontal and vertical scale factors to fit the
                // whole diagram on the selected amount of pages.

                final Rectangle printerBounds = PrintExporter.getPrinterBounds(options.getPrinter());
                final PBounds diagramBounds = options.getExporter().getDiagramBounds();

                final double scaleX =
                        printerBounds.width * options.getPagesWide() / diagramBounds.width;
                final double scaleY =
                        printerBounds.height * options.getPagesTall() / diagramBounds.height;

                options.setScaleFactor(Math.min(scaleX, scaleY));
            }
        });

        final Button adjustPagesBtn = DialogUtil.button(
                buttonsGroup, KlighdUIPrintingMessages.PrintDialog_Scaling_adjustPages);

        adjustPagesBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                // Calculate for both horizontal and vertical directions how many pages are necessary
                // to fit the diagram in.

                final Rectangle printerBounds = PrintExporter.getPrinterBounds(options.getPrinter());
                final PBounds size = options.getExporter().getDiagramBounds();

                options.setPagesWide(
                        (int) Math.ceil(size.width * options.getScaleFactor() / printerBounds.width));
                options.setPagesTall(
                        (int) Math.ceil(size.height * options.getScaleFactor() / printerBounds.height));
            }
        });

        // Group containing a spinner and some textlabels for scale settings.
        final Composite scalingGroup = new Composite(result, SWT.NONE);
        DialogUtil.layoutFillHorizontal(scalingGroup, true);
        scalingGroup.setLayout(new GridLayout(SCALING_GROUP_COLUMNS, false));

        DialogUtil.label(scalingGroup, KlighdUIPrintingMessages.PrintDialog_Scaling_lbl_scaleTo);
        final Spinner scaleSpinner = DialogUtil.spinner(scalingGroup, 1, Integer.MAX_VALUE);
        DialogUtil.layoutFillHorizontal(scaleSpinner, true);
        DialogUtil.label(scalingGroup, KlighdUIPrintingMessages.PrintDialog_Scaling_lbl_percent);

        final IObservableValue scaleValue =
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_SCALE_PERCENT);
        bindings.bindValue(SWTObservables.observeSelection(scaleSpinner), scaleValue, null, null);

        // Group containing two spinner (and describing labels) to set the number of pages tall and
        // wide to print on.
        final Composite pagesGroup = new Composite(result, SWT.NONE);
        DialogUtil.layoutFillHorizontal(pagesGroup, true);
        pagesGroup.setLayout(new GridLayout(PAGES_GROUP_COLUMNS, false));

        DialogUtil.label(pagesGroup, KlighdUIPrintingMessages.PrintDialog_Scaling_lbl_printTo);

        final Spinner spinnerWide = DialogUtil.spinner(pagesGroup, 1, MAX_PAGES);
        DialogUtil.layoutWidth(spinnerWide, SPINNER_WIDTH);

        DialogUtil.label(pagesGroup, KlighdUIPrintingMessages.PrintDialog_Scaling_lbl_pagesWide);

        final Spinner spinnerTall = DialogUtil.spinner(pagesGroup, 1, MAX_PAGES);
        DialogUtil.layoutWidth(spinnerTall, SPINNER_WIDTH);

        DialogUtil.label(pagesGroup, KlighdUIPrintingMessages.PrintDialog_Scaling_lbl_pagesTall);

        bindings.bindValue(SWTObservables.observeSelection(spinnerWide),
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_PAGES_WIDE),
                null, null);

        bindings.bindValue(SWTObservables.observeSelection(spinnerTall),
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_PAGES_TALL),
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
