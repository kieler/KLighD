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

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import de.cau.cs.kieler.klighd.DiagramExportConfig;
import de.cau.cs.kieler.klighd.ui.printing.DiagramPrintOptions;
import de.cau.cs.kieler.klighd.ui.printing.PrintExporter;
import de.cau.cs.kieler.klighd.ui.printing.PrintOptions;

/**
 * A PrintPreview to be displayed as a dialog tray, e.g. used by {@link KlighdPrintDialog}.
 * </br>
 * The implementation is inspired by
 * {@link org.eclipse.gmf.runtime.diagram.ui.printing.internal.printpreview.PrintPreviewHelper
 * PrintPreviewHelper} of the GMF project, esp.
 * <code>PrintPreviewHelper.updateCompositeForNumberOfColumns(int, int)</code>.
 *
 * @author csp
 * @author chsch
 */
public class PrintPreviewTray extends DialogTray {

    private final DataBindingContext bindings;
    private final DiagramPrintOptions options;

    /* SWT interface variables */

    private Composite body;
    private Composite composite;

    /** List of images to dispose. */
    private final List<Image> imageList = new ArrayList<Image>();

    /* Observables to remove listeners from */
    private IObservableValue delayedResize;
    private IObservableValue delayedPrinterData;
    private IObservableValue delayedScale;
    private IObservableValue delayedPagesWide;
    private IObservableValue delayedPagesTall;
    private IObservableValue delayedHorCentered;
    private IObservableValue delayedVerCentered;

    /** Listener to be removed from observables. */
    private IValueChangeListener listener;

    /** Minimal tile size. */
    protected static final int MINIMAL_TILE_SIZE = 4;

    /** Border size. */
    protected static final int BORDER_SIZE = 20;

    /**
     * Delay (in milliseconds) of scale and pages tall/wide observables and the resize event before
     * the preview gets updated. This prevents massive redrawings e.g. while resizing the window.
     */
    protected static final int OBSERVABLE_DELAY = 100;

    PrintPreviewTray(final DataBindingContext bindings, final DiagramPrintOptions options) {
        this.bindings = bindings;
        this.options = options;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Control createContents(final Composite parent) {
        final Realm realm = bindings.getValidationRealm();

        body = new Composite(parent, SWT.NONE);
        body.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
        body.setSize(parent.getSize());

        composite = new Composite(body, SWT.NULL);

        updateComposite();

        listener = new IValueChangeListener() {

            public void handleValueChange(final ValueChangeEvent event) {
                updateComposite();
            }
        };

        delayedResize = Observables.observeDelayedValue(OBSERVABLE_DELAY,
                SWTObservables.observeSize(body));
        delayedResize.addValueChangeListener(listener);

        delayedPrinterData = Observables.observeDelayedValue(OBSERVABLE_DELAY,
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_PRINTER_DATA));
        delayedPrinterData.addValueChangeListener(listener);

        delayedScale = Observables.observeDelayedValue(OBSERVABLE_DELAY,
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_SCALE_FACTOR));
        delayedScale.addValueChangeListener(listener);

        delayedPagesWide = Observables.observeDelayedValue(OBSERVABLE_DELAY,
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_PAGES_WIDE));
        delayedPagesWide.addValueChangeListener(listener);

        delayedPagesTall = Observables.observeDelayedValue(OBSERVABLE_DELAY,
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_PAGES_TALL));
        delayedPagesTall.addValueChangeListener(listener);

        delayedHorCentered = Observables.observeDelayedValue(OBSERVABLE_DELAY,
                BeansObservables.observeValue(realm, options,
                        PrintOptions.PROPERTY_CENTER_HORIZONTALLY));
        delayedHorCentered.addValueChangeListener(listener);

        delayedVerCentered = Observables.observeDelayedValue(OBSERVABLE_DELAY,
                BeansObservables.observeValue(realm, options, PrintOptions.PROPERTY_CENTER_VERTICALLY));
        delayedVerCentered.addValueChangeListener(listener);

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

        final Control[] children = composite.getChildren();
        for (int i = 0; i < children.length; i++) {
            children[i].dispose();
        }
        disposeImages();

        // Setting the layout to null removes all cached sizes and
        // forces the later layout to populate the resize to the labels
        // containing the tiles even if the overall size of the body hasn't changed.
        composite.setLayout(null);
        composite.pack();

        composite.setLayout(new GridLayout(options.getPagesWide(), true));
        final Point bodySize = body.getSize();

        // (body height - top border - bottom border - (# of rows - 1) x border between images)
        //   / # of rows
        int imageHeight =
                (bodySize.y - (2 + options.getPagesTall() - 1) * BORDER_SIZE)
                        / options.getPagesTall();

        // ( body width - left border - right border - (# of columns - 1) x border between images)
        //   / # of columns
        int imageWidth =
                (bodySize.x - (2 + options.getPagesWide() - 1) * BORDER_SIZE)
                        / options.getPagesWide();

        // now adjust to the limiting one based on aspect ratio

        final Dimension pageBounds = options.getPrinterBounds();

        // width / height
        final float printerRatio = ((float) pageBounds.width) / ((float) pageBounds.height);

        if (imageHeight * printerRatio < imageWidth) {
            // round down
            imageWidth = (int) (imageHeight * printerRatio);
        } else if (imageWidth * (1 / printerRatio) < imageHeight) {
            // round down
            imageHeight = (int) (imageWidth * (1.0f / printerRatio));
        }

        // make sure height and width are not 0, if too small <4, don't bother
        if (!(imageHeight <= MINIMAL_TILE_SIZE || imageWidth <= MINIMAL_TILE_SIZE)) {

            final PrintExporter exporter = options.getExporter();

            final int rows = options.getPagesTall();
            final int columns = options.getPagesWide();

            final DiagramExportConfig config = exporter.createExportConfig(options);

            final Dimension imageBounds = new Dimension(imageWidth, imageHeight);

            // Adjust the scale according to relation between preview and printing size.
            final double previewScale = (double) (imageWidth) / pageBounds.width;

            final Rectangle imageClip = exporter.getBasicPageClip(imageBounds,
                    config.tileTrim.getScaled((float) previewScale));

            final Point2D centeringOffset = options.getCenteringOffset(previewScale);

            int pageNo = 0;

            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {

                    config.setPageAndTileNumbers(++pageNo, row, column, rows, columns);

                    final Image pageImg = exporter.exportPreview(
                            config, imageBounds, imageClip, previewScale, centeringOffset);
                    imageList.add(pageImg);

                    new Label(composite, SWT.NULL).setImage(pageImg);
                }
            }
        }

        composite.pack();

        // Manually center the composite
        final org.eclipse.swt.graphics.Rectangle compositeBounds = composite.getBounds();

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
    private void disposeImages() {
        for (final Image image : imageList) {
            safeDisposeImage(image);
        }
        imageList.clear();
    }

    /**
     * Safely removes the listener from the given observable.
     *
     * @param observable
     *            the observable to remove the listener from
     */
    private void safeRemoveListener(final IObservableValue observable) {
        if (observable != null) {
            observable.removeValueChangeListener(listener);
        }
    }

    /**
     * Dispose resources.
     */
    public void dispose() {
        disposeImages();
        safeRemoveListener(delayedResize);
        safeRemoveListener(delayedPrinterData);
        safeRemoveListener(delayedScale);
        safeRemoveListener(delayedPagesWide);
        safeRemoveListener(delayedPagesTall);
        safeRemoveListener(delayedHorCentered);
        safeRemoveListener(delayedVerCentered);
    }

}
