// SUPPRESS CHECKSTYLE NEXT Header
/******************************************************************************
 * Copyright (c) 2002, 2009 IBM Corporation and others.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Observables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.DialogTray;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import de.cau.cs.kieler.klighd.ui.printing.options.PrintOptions;
import de.cau.cs.kieler.klighd.ui.printing.util.PrintExporter;

/**
 * A PrintPreview to be displayed as a dialog tray, e.g. used by {@link KlighdPrintDialog}.
 * 
 * @author csp
 */
public class PrintPreviewTray extends DialogTray {

    private final DataBindingContext bindings;
    private final PrintOptions options;

    /* SWT interface variables */

    private Composite body;
    private Composite composite;

    /* Images */

    /* List of images to dispose */
    private List<Image> imageList = new ArrayList<Image>();

    /* Observables to remove listeners from */
    private IObservableValue delayedScale;
    private IObservableValue delayedPagesWide;
    private IObservableValue delayedPagesTall;
    private IObservableValue delayedResize;
    private IObservableValue printerData;

    /* Listener to be removed from observables. */
    private IValueChangeListener listener;
    
    /** Minimal tile size. */
    protected static final int MINIMAL_TILE_SIZE = 4;

    /** Border size. */
    protected static final int BORDER_SIZE = 20;

    /** delay of scale and pages tall/wide observables. */
    protected static final int OBSERVABLE_DELAY = 100;

    /** Background color. */
    private static final Color BACKGROUND_COLOR = new Color(Display.getDefault(), 124, 124, 124);

    PrintPreviewTray(final DataBindingContext bindings, final PrintOptions options) {
        this.bindings = bindings;
        this.options = options;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Control createContents(final Composite parent) {
        final Realm realm = bindings.getValidationRealm();

        listener = new IValueChangeListener() {

            public void handleValueChange(final ValueChangeEvent event) {
                updateComposite();
            }
        };

        delayedScale =
                Observables.observeDelayedValue(OBSERVABLE_DELAY, BeansObservables.observeValue(
                        realm, options, PrintOptions.PROPERTY_SCALE_FACTOR));

        delayedScale.addValueChangeListener(listener);

        delayedPagesWide =
                Observables.observeDelayedValue(OBSERVABLE_DELAY, BeansObservables.observeValue(
                        realm, options, PrintOptions.PROPERTY_FIT_TO_WIDTH));
        delayedPagesWide.addValueChangeListener(listener);

        delayedPagesTall =
                Observables.observeDelayedValue(OBSERVABLE_DELAY, BeansObservables.observeValue(
                        realm, options, PrintOptions.PROPERTY_FIT_TO_HEIGHT));
        delayedPagesTall.addValueChangeListener(listener);

        delayedResize =
                Observables.observeDelayedValue(OBSERVABLE_DELAY,
                        SWTObservables.observeSize(parent));
        delayedResize.addValueChangeListener(listener);

        printerData =
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_PRINTER_DATA);
        printerData.addValueChangeListener(listener);

        body = new Composite(parent, SWT.NONE);
        body.setBackground(BACKGROUND_COLOR);
        body.setSize(parent.getSize());

        composite = new Composite(body, SWT.NULL);

        updateComposite();

        return body;
    }

    /**
     * Draw the composite centered on the body. Also calls the method to make the images and insert
     * them into the composite.
     */
    public void updateComposite() {
        if (composite == null || composite.isDisposed()) {
            return;
        }

        // stop redrawing to prevent flickering
        body.setRedraw(false);

        Control[] children = composite.getChildren();
        for (int i = 0; i < children.length; i++) {
            children[i].dispose();
        }
        disposeImages();

        // Setting the layout to null removes all cached sizes and
        // forces the later layout to populate the resize to the labels
        // containing the tiles even if the overall size of the body hasn't changed.
        composite.setLayout(null);
        composite.pack();

        composite.setLayout(new GridLayout(options.getFitToPagesWidth(), true));

        // ( body height - top border - bottom border -
        // ((# of rows - 1) x border between images) ) / # of rows
        int imageHeight =
                (body.getSize().y - BORDER_SIZE - BORDER_SIZE - ((options.getFitToPagesHeight() - 1)
                        * BORDER_SIZE)) / options.getFitToPagesHeight();

        // ( body width - left border - right border -
        // ((# of columns - 1) x border between images) ) / # of columns
        int imageWidth =
                (body.getSize().x - BORDER_SIZE - BORDER_SIZE - ((options.getFitToPagesWidth() - 1)
                        * BORDER_SIZE)) / options.getFitToPagesWidth();

        // now adjust to the limiting one based on aspect ratio

        Printer p = new Printer(options.getPrinterData());

        Rectangle pageBounds = PrintExporter.getPrinterBounds(p);

        // width / height
        float printerRatio = ((float) pageBounds.width) / ((float) pageBounds.height);

        if (imageHeight * printerRatio < imageWidth) {
            // round down
            imageWidth = (int) (imageHeight * printerRatio);
        } else if (imageWidth * (1 / printerRatio) < imageHeight) {
            // round down
            imageHeight = (int) (imageWidth * (1.0f / printerRatio));
        }

        // make sure height and width are not 0, if too small <4, don't bother
        if (!(imageHeight <= MINIMAL_TILE_SIZE || imageWidth <= MINIMAL_TILE_SIZE)) {

            double scale = options.getScaleFactor();

            // Adjust the scale according to relation between preview and printing size.
            scale = scale * imageWidth / pageBounds.width;

            for (int i = 0; i < options.getFitToPagesHeight(); i++) {
                for (int j = 0; j < options.getFitToPagesWidth(); j++) {
                    Label label = new Label(composite, SWT.NULL);
                    Image pageImg =
                            options.getExporter().exportPreview(j, i, imageWidth, imageHeight,
                                    scale);
                    label.setImage(pageImg);
                    imageList.add(pageImg);
                }
            }
        }

        composite.pack();

        // Manually center the composite
        Rectangle compositeBounds = composite.getBounds();

        compositeBounds.x = (body.getSize().x - compositeBounds.width) / 2;
        compositeBounds.y = (body.getSize().y - compositeBounds.height) / 2;
        composite.setBounds(compositeBounds);

        // reactivate redrawing
        body.setRedraw(true);
    }

    /**
     * Safely dispose an image.
     * 
     * @param image
     *            the Image to dispose.
     */
    private void safeDisposeImage(final Image image) {
        if (image != null && !image.isDisposed()) {
            image.dispose();
        }
    }

    /**
     * Dispose images.
     */
    public void disposeImages() {
        for (Image image : imageList) {
            safeDisposeImage(image);
        }
        imageList.clear();
    }

    /**
     * Dispose resources.
     */
    public void dispose() {
        disposeImages();
        delayedResize.removeValueChangeListener(listener);
        delayedScale.removeValueChangeListener(listener);
        delayedPagesWide.removeValueChangeListener(listener);
        delayedPagesTall.removeValueChangeListener(listener);
        printerData.removeValueChangeListener(listener);
        BACKGROUND_COLOR.dispose();
    }

}
