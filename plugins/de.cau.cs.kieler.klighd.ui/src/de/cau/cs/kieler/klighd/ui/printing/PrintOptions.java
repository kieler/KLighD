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
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.printing;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;

import com.google.common.base.Strings;

import de.cau.cs.kieler.klighd.IExportBranding.Trim;

/**
 * Data record definition providing storage of the information required by the
 * {@link de.cau.cs.kieler.klighd.ui.printing.dialog.KlighdPrintDialog KlighdPrintDialog}.<br>
 * <br>
 * The is class contains only basic print configuration data fields. This way the print dialog can
 * be incorporated for printing non-diagram content either, a subclass of this class can be created
 * enabling the storage of further information.<br>
 * <br>
 * During the KLighD diagram print an instance of {@link DiagramPrintOptions}, which is a subclass
 * of this one as well, is incorporated.
 *
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 * @author chsch
 */
public class PrintOptions {

    /* printer data */

    /** Observables ID for printer data. */
    public static final String PROPERTY_PRINTER_DATA = "printerData"; //$NON-NLS-1$

    /** Observables ID for printer name. */
    public static final String PROPERTY_PRINTER_NAME = "printerName"; //$NON-NLS-1$

    /** Observables ID for number of copies. */
    public static final String PROPERTY_COPIES = "copies"; //$NON-NLS-1$

    /** Observables ID for collate. */
    public static final String PROPERTY_COLLATE = "collate"; //$NON-NLS-1$

    /** Observables ID for all pages range. */
    public static final String PROPERTY_ALL_PAGES = "allPages"; //$NON-NLS-1$

    /** Observables ID for print range from. */
    public static final String PROPERTY_RANGE_FROM = "rangeFrom"; //$NON-NLS-1$

    /** Observables ID for print range to. */
    public static final String PROPERTY_RANGE_TO = "rangeTo"; //$NON-NLS-1$

    /** Observables ID for duplex mode. */
    public static final String PROPERTY_DUPLEX = "duplex"; //$NON-NLS-1$

    /** Observables ID for orientation. */
    public static final String PROPERTY_ORIENTATION = "orientation"; //$NON-NLS-1$


    /* other print options */

    /** Observables ID for scale factor. */
    public static final String PROPERTY_SCALE_FACTOR = "scaleFactor"; //$NON-NLS-1$

    /** Observables ID for displaying the scale factor. */
    public static final String PROPERTY_SCALE_PERCENT = "scalePercent"; //$NON-NLS-1$

    /** Observables ID for number of pages wide. Denotes the number of vertical pages to print on. */
    public static final String PROPERTY_PAGES_WIDE = "pagesWide"; //$NON-NLS-1$

    /** Observables ID for number of pages tall. Denotes the number of horizontal pages to print on. */
    public static final String PROPERTY_PAGES_TALL = "pagesTall"; //$NON-NLS-1$

    /** Observables ID for horizontal centering. */
    public static final String PROPERTY_CENTER_HORIZONTALLY = "horizontallyCentered"; //$NON-NLS-1$

    /** Observables ID for vertical centering. */
    public static final String PROPERTY_CENTER_VERTICALLY = "verticallyCentered"; //$NON-NLS-1$

    /* preference IDs */

    /** Id of the preference PRINTER_DRIVER. */
    private static final String PREFERENCE_PRINTER_DRIVER = "klighd.printing.driver";

    /** Id of the preference PRINTER_NAME. */
    private static final String PREFERENCE_PRINTER_NAME = "klighd.printing.name";

    /** Id of the preference PRINTER_SCALE. */
    private static final String PREFERENCE_PRINTER_SCALE = "klighd.printing.scale";

    /** Id of the preference PRINTER_PAGES_TALL. */
    private static final String PREFERENCE_PRINTER_PAGES_TALL = "klighd.printing.pagesTall";

    /** Id of the preference PRINTER_PAGES_WIDE. */
    private static final String PREFERENCE_PRINTER_PAGES_WIDE = "klighd.printing.pagesWide";

    /** Id of the preference PRINTER_SCOPE. */
    private static final String PREFERENCE_PRINTER_SCOPE = "klighd.printing.scope";

    /** Id of the preference PRINTER_PAGES_START. */
    private static final String PREFERENCE_PRINTER_PAGES_START = "klighd.printing.pagesStart";

    /** Id of the preference PRINTER_PAGES_END. */
    private static final String PREFERENCE_PRINTER_PAGES_END = "klighd.printing.pagesEnd";

    /** Id of the preference PRINTER_CENTER_HORIZONTALLY. */
    private static final String PREFERENCE_PRINTER_CENTER_HORIZONTALLY =
            "klighd.printing.centerHorizontally";

    /** Id of the preference PRINTER_CENTER_VERTICALLY. */
    private static final String PREFERENCE_PRINTER_CENTER_VERTICALLY =
            "klighd.printing.centerVertically";

    /** Id of the preference PRINTER_ORIENTATION. */
    private static final String PREFERENCE_PRINTER_ORIENTATION = "klighd.printing.orientation";

    /** Id of the preference PRINTER_DUPLEX. */
    private static final String PREFERENCE_PRINTER_DUPLEX = "klighd.printing.duplex";

    /** Id of the preference PRINTER_COLLATE. */
    private static final String PREFERENCE_PRINTER_COLLATE = "klighd.printing.collate";

    /**
     * Abstract preference initializer skeleton contributing default values for preference entries
     * used in {@link PrintOptions}. Concrete implementations must be registered by means of the
     * extension point {@code org.eclipse.core.runtime.preferences}, see e.g.
     * {@link DiagramPrintOptions.Initializer}. {@link #getPreferenceStore()} must return the same
     * {@link IPreferenceStore} provided to the
     * {@link PrintOptions#PrintOptions(IPreferenceStore) constructor} instantiating
     * {@link PrintOptions} or subclasses, of course.
     *
     * @author chsch
     */
    public abstract static class Initializer extends AbstractPreferenceInitializer {

        /**
         * Provides the {@link IPreferenceStore} to be used for storing the configuration.
         *
         * @return the {@link IPreferenceStore} to be used for storing the configuration.
         */
        protected abstract IPreferenceStore getPreferenceStore();

        /**
         * {@inheritDoc}
         */
        @Override
        public void initializeDefaultPreferences() {
            final IPreferenceStore prefStore = getPreferenceStore();
            prefStore.setDefault(PREFERENCE_PRINTER_SCALE, 1f);
            prefStore.setDefault(PREFERENCE_PRINTER_PAGES_TALL, 1);
            prefStore.setDefault(PREFERENCE_PRINTER_PAGES_WIDE, 1);
            prefStore.setDefault(PREFERENCE_PRINTER_SCOPE, PrinterData.ALL_PAGES);
            prefStore.setDefault(PREFERENCE_PRINTER_PAGES_START, 1);
            prefStore.setDefault(PREFERENCE_PRINTER_PAGES_END, 1);
            prefStore.setDefault(PREFERENCE_PRINTER_CENTER_HORIZONTALLY, false);
            prefStore.setDefault(PREFERENCE_PRINTER_CENTER_VERTICALLY, false);
            prefStore.setDefault(PREFERENCE_PRINTER_ORIENTATION, PrinterData.PORTRAIT);
            prefStore.setDefault(PREFERENCE_PRINTER_DUPLEX, PrinterData.DUPLEX_NONE);
            prefStore.setDefault(PREFERENCE_PRINTER_COLLATE, false);
        }
    }

    /** The property change support. */
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private final IPreferenceStore prefStore;
    private PrinterData printerData;
    private double scaleFactor;
    private int pagesWide;
    private int pagesTall;
    private boolean centerHorizontally;
    private boolean centerVertically;

    // some "cache" fields
    private Printer printer = null;
    private Dimension printerBounds = null;
    private Trim printerTrim = null;
    private Point2D centeringOffset = null;


    /**
     * Constructor.
     *
     * @param prefStore the {@link IPreferenceStore} to use for storing and loading the configuration.
     */
    public PrintOptions(final IPreferenceStore prefStore) {
        this.prefStore = prefStore;
        restoreFromPreferences();
    }


    /**
     * Restore the options from preference store.
     */
    public void restoreFromPreferences() {
        final String driver = prefStore.getString(PREFERENCE_PRINTER_DRIVER);
        final String name = prefStore.getString(PREFERENCE_PRINTER_NAME);
        printerData = new PrinterData(
                // be careful: driver and name must not be equal to ""
                //  but must be 'null' to get the default printer
                Strings.emptyToNull(driver), Strings.emptyToNull(name));

        if (printerData != null) {
            setAllPages(prefStore.getInt(PREFERENCE_PRINTER_SCOPE) == PrinterData.ALL_PAGES);
            setRangeFrom(prefStore.getInt(PREFERENCE_PRINTER_PAGES_START));
            setRangeTo(prefStore.getInt(PREFERENCE_PRINTER_PAGES_END));
            setOrientation(prefStore.getInt(PREFERENCE_PRINTER_ORIENTATION));
            setDuplex(prefStore.getInt(PREFERENCE_PRINTER_DUPLEX));
            setCollate(prefStore.getBoolean(PREFERENCE_PRINTER_COLLATE));
        }

        setScaleFactor(prefStore.getDouble(PREFERENCE_PRINTER_SCALE));
        setPagesTall(prefStore.getInt(PREFERENCE_PRINTER_PAGES_TALL));
        setPagesWide(prefStore.getInt(PREFERENCE_PRINTER_PAGES_WIDE));
        setHorizontallyCentered(prefStore.getBoolean(PREFERENCE_PRINTER_CENTER_HORIZONTALLY));
        setVerticallyCentered(prefStore.getBoolean(PREFERENCE_PRINTER_CENTER_VERTICALLY));
    }

    /**
     * Store the options in the preference store.
     */
    public void storeToPreferences() {
        prefStore.setValue(PREFERENCE_PRINTER_DRIVER, printerData.driver);
        prefStore.setValue(PREFERENCE_PRINTER_NAME, printerData.name);
        prefStore.setValue(PREFERENCE_PRINTER_SCALE, scaleFactor);
        prefStore.setValue(PREFERENCE_PRINTER_PAGES_TALL, pagesTall);
        prefStore.setValue(PREFERENCE_PRINTER_PAGES_WIDE, pagesWide);
        prefStore.setValue(PREFERENCE_PRINTER_SCOPE, printerData.scope);
        prefStore.setValue(PREFERENCE_PRINTER_PAGES_START, printerData.startPage);
        prefStore.setValue(PREFERENCE_PRINTER_PAGES_END, printerData.endPage);
        prefStore.setValue(PREFERENCE_PRINTER_CENTER_HORIZONTALLY, centerHorizontally);
        prefStore.setValue(PREFERENCE_PRINTER_CENTER_VERTICALLY, centerVertically);
        prefStore.setValue(PREFERENCE_PRINTER_ORIENTATION, printerData.orientation);
        prefStore.setValue(PREFERENCE_PRINTER_DUPLEX, printerData.duplex);
        prefStore.setValue(PREFERENCE_PRINTER_COLLATE, printerData.collate);
    }

    /**
     * Gets the scale factor.
     *
     * @return the scale factor
     */
    public double getScaleFactor() {
        return scaleFactor;
    }

    private static final double PERCENT_FACTOR = 100.0;

    /**
     * Sets the scale factor.
     *
     * @param scaleFactor
     *            the new scale factor greater than zero
     * @throws IllegalArgumentException
     *             if scaleFactor is less or equal zero
     */
    public void setScaleFactor(final double scaleFactor) {
        if (scaleFactor > 0) {
            final double oldFactor = this.scaleFactor;
            this.scaleFactor = scaleFactor;
            firePropertyChange(PROPERTY_SCALE_FACTOR, oldFactor, this.scaleFactor);
            firePropertyChange(PROPERTY_SCALE_PERCENT, (int) (oldFactor * PERCENT_FACTOR),
                    (int) (this.scaleFactor * PERCENT_FACTOR));
            resetCenteringOffset();
        } else {
            throw new IllegalArgumentException("Scale factor out of range: " + scaleFactor
                    + "less or equal zero.");
        }
    }

    /**
     * Gets the scale factor in percent.
     * Used for displaying the scale in percent.
     *
     * @return the scale factor in percent
     */
    public int getScalePercent() {
        return (int) (scaleFactor * PERCENT_FACTOR);
    }

    /**
     * Sets the scale factor in percent.
     * If the percent is less or equal zero, this setter has no effect.
     * Used for setting the scale in percent from a GUI element showing the scale in percent.
     *
     * @param scalePercent
     *            the new scale factor in percent greater than zero
     * @throws IllegalArgumentException
     *             if scalePercent is less or equal zero
     */
    public void setScalePercent(final int scalePercent) {
        if (scalePercent > 0) {
            final double oldFactor = this.scaleFactor;
            this.scaleFactor = scalePercent / PERCENT_FACTOR;
            firePropertyChange(PROPERTY_SCALE_FACTOR, oldFactor, this.scaleFactor);
            firePropertyChange(PROPERTY_SCALE_PERCENT, (int) (oldFactor * PERCENT_FACTOR),
                    (int) (this.scaleFactor * PERCENT_FACTOR));
            resetCenteringOffset();
        } else {
            throw new IllegalArgumentException("Scale percent out of range: " + scalePercent
                    + "less or equal 0.");
        }
    }

    /**
     * Gets the number of horizontal pages the diagram is divided into.
     *
     * @return the number of pages wide
     */
    public int getPagesWide() {
        return pagesWide;
    }

    /**
     * Sets the number of horizontal pages to divide the diagram into.
     *
     * @param pagesWide
     *            the new number of pages wide greater than zero
     * @throws IllegalArgumentException
     *             if pagesWide is less or equal zero
     */
    public void setPagesWide(final int pagesWide) {
        if (pagesWide > 0) {
            final int oldPagesWide = this.pagesWide;
            this.pagesWide = pagesWide;
            firePropertyChange(PROPERTY_PAGES_WIDE, oldPagesWide, this.pagesWide);
            resetCenteringOffset();
        } else {
            throw new IllegalArgumentException("Pages wide out of range: " + pagesWide
                    + "less or equal 0.");
        }
    }

    /**
     * Gets the number of vertical pages the diagram is divided into.
     *
     * @return the number of pages tall
     */
    public int getPagesTall() {
        return pagesTall;
    }

    /**
     * Sets the number of vertical pages to divide the diagram into.
     *
     * @param pagesTall
     *            the new number of pages tall greater than zero
     * @throws IllegalArgumentException
     *             if pagesTall is less or equal zero
     */
    public void setPagesTall(final int pagesTall) {
        if (pagesTall > 0) {
            final int oldPagesTall = this.pagesTall;
            this.pagesTall = pagesTall;
            firePropertyChange(PROPERTY_PAGES_TALL, oldPagesTall, this.pagesTall);
            resetCenteringOffset();
        } else {
            throw new IllegalArgumentException("Pages tall out of range: " + pagesTall
                    + "less or equal 0.");
        }
    }

    /**
     * Checks if is the print range is set to all pages.
     *
     * @return true, if printing all pages
     */
    public boolean isAllPages() {
        return printerData.scope == PrinterData.ALL_PAGES;
    }

    /**
     * Sets whether to print all pages.
     *
     * @param allPages
     *            whether to print all pages
     */
    public void setAllPages(final boolean allPages) {
        final boolean oldAll = printerData.scope == PrinterData.ALL_PAGES;
        printerData.scope = allPages ? PrinterData.ALL_PAGES : PrinterData.PAGE_RANGE;
        firePropertyChange(PROPERTY_ALL_PAGES, oldAll, printerData.scope == PrinterData.ALL_PAGES);
        disposePrinter();
    }

    /**
     * Gets the first printed page.
     *
     * @return the range from
     */
    public int getRangeFrom() {
        return printerData.startPage;
    }

    /**
     * Sets the first printed page.
     *
     * @param rangeFrom
     *            the new range from
     */
    public void setRangeFrom(final int rangeFrom) {
        final int oldFrom = printerData.startPage;
        printerData.startPage = rangeFrom;
        firePropertyChange(PROPERTY_RANGE_FROM, oldFrom, rangeFrom);
        disposePrinter();
    }

    /**
     * Gets the last printed page.
     *
     * @return the range to
     */
    public int getRangeTo() {
        return printerData.endPage;
    }

    /**
     * Sets the last printed page.
     *
     * @param rangeTo
     *            the new range to
     */
    public void setRangeTo(final int rangeTo) {
        final int oldTo = printerData.endPage;
        printerData.endPage = rangeTo;
        firePropertyChange(PROPERTY_RANGE_TO, oldTo, rangeTo);
        disposePrinter();
    }

    /**
     * Gets the number of copies.
     *
     * @return the number of copies
     */
    public int getCopies() {
        return printerData.copyCount;
    }

    /**
     * Sets the number of copies.
     *
     * @param copies
     *            the new number copies
     */
    public void setCopies(final int copies) {
        final int oldCopies = printerData.copyCount;
        printerData.copyCount = copies;
        firePropertyChange(PROPERTY_COPIES, oldCopies, copies);
        disposePrinter();
    }

    /**
     * Checks whether to collate.
     *
     * @return true, if collating is set
     */
    public boolean isCollate() {
        return printerData.collate;
    }

    /**
     * Sets whether to collate.
     *
     * @param collate
     *            whether to collate
     */
    public void setCollate(final boolean collate) {
        final boolean oldCollate = printerData.collate;
        printerData.collate = collate;
        firePropertyChange(PROPERTY_COLLATE, oldCollate, collate);
        disposePrinter();
    }

    /**
     * Gets the duplex mode.
     * Returns one of:
     * <ul>
     * <li> {@link PrinterData#DUPLEX_NONE}</li>
     * <li> {@link PrinterData#DUPLEX_LONG_EDGE}</li>
     * <li> {@link PrinterData#DUPLEX_SHORT_EDGE}</li>
     * </ul>
     *
     * @return the duplex mode
     */
    public int getDuplex() {
        return printerData.duplex;
    }

    /**
     * Sets the duplex mode.
     * Must be one of:
     * <ul>
     * <li> {@link PrinterData#DUPLEX_NONE}</li>
     * <li> {@link PrinterData#DUPLEX_LONG_EDGE}</li>
     * <li> {@link PrinterData#DUPLEX_SHORT_EDGE}</li>
     * </ul>
     *
     * @param duplex
     *            the new duplex mode
     */
    public void setDuplex(final int duplex) {
        final int oldDuplex = printerData.duplex;
        printerData.duplex = duplex;
        firePropertyChange(PROPERTY_DUPLEX, oldDuplex, printerData.duplex);
        disposePrinter();
    }

    /**
     * Gets the printer data. This contains printer specific settings.
     *
     * @return the printer data
     */
    public PrinterData getPrinterData() {
        return this.printerData;
    }

    /**
     * Sets the printer data. This contains printer specific settings.
     *
     * @param printerData
     *            the new printer data
     */
    public void setPrinterData(final PrinterData printerData) {
        final PrinterData oldPrinterData = this.printerData;
        this.printerData = printerData;
        firePropertyChange(PROPERTY_PRINTER_DATA, oldPrinterData, printerData);
        firePropertyChange(PROPERTY_PRINTER_NAME, oldPrinterData.name, printerData.name);
        firePropertyChange(PROPERTY_COPIES, oldPrinterData.copyCount, printerData.copyCount);
        firePropertyChange(PROPERTY_COLLATE, oldPrinterData.collate, printerData.collate);
        firePropertyChange(PROPERTY_ALL_PAGES, oldPrinterData.scope == PrinterData.ALL_PAGES,
                printerData.scope == PrinterData.ALL_PAGES);
        firePropertyChange(PROPERTY_RANGE_FROM, oldPrinterData.startPage, printerData.startPage);
        firePropertyChange(PROPERTY_RANGE_TO, oldPrinterData.endPage, printerData.endPage);
        firePropertyChange(PROPERTY_DUPLEX, oldPrinterData.duplex, printerData.duplex);
        firePropertyChange(PROPERTY_ORIENTATION, oldPrinterData.orientation,
                printerData.orientation);

        disposePrinter();
        resetTrimData();
        resetCenteringOffset();
    }

    /**
     * Gets the orientation.
     * Returns one of:
     * <ul>
     * <li> {@link PrinterData#PORTRAIT}</li>
     * <li> {@link PrinterData#LANDSCAPE}</li>
     * </ul>
     *
     * @return the orientation
     */
    public int getOrientation() {
        return printerData.orientation;
    }

    /**
     * Sets the orientation.
     * Must be one of:
     * <ul>
     * <li> {@link PrinterData#PORTRAIT}</li>
     * <li> {@link PrinterData#LANDSCAPE}</li>
     * </ul>
     *
     * @param orientation
     *            the new orientation
     */
    public void setOrientation(final int orientation) {
        final int oldOrientation = printerData.orientation;
        printerData.orientation = orientation;
        firePropertyChange(PROPERTY_ORIENTATION, oldOrientation, printerData.orientation);
        disposePrinter();
        resetTrimData();
        resetCenteringOffset();
    }

    /**
     * Checks whether the diagram is to be centered horizontally.
     *
     * @return true, if the diagram is to be centered horizontally
     */
    public boolean getHorizontallyCentered() {
        return this.centerHorizontally;
    }

    /**
     * Sets whether to horizontally center the diagram on the page(s).
     *
     * @param horCentered
     *            whether to horizontally center the diagram
     */
    public void setHorizontallyCentered(final boolean horCentered) {
        final boolean oldHorCentered = this.centerHorizontally;
        this.centerHorizontally = horCentered;
        firePropertyChange(PROPERTY_CENTER_HORIZONTALLY, oldHorCentered, this.centerHorizontally);
        resetCenteringOffset();
    }

    /**
     * Checks whether the diagram is to be centered vertically.
     *
     * @return true, if the diagram is to be centered vertically
     */
    public boolean getVerticallyCentered() {
        return this.centerVertically;
    }

    /**
     * Sets whether to vertically center the diagram on the page(s).
     *
     * @param verCentered
     *            whether to vertically center the diagram
     */
    public void setVerticallyCentered(final boolean verCentered) {
        final boolean oldVerCentered = this.centerVertically;
        this.centerVertically = verCentered;
        firePropertyChange(PROPERTY_CENTER_VERTICALLY, oldVerCentered, this.centerVertically);
        resetCenteringOffset();
    }

    /**
     * Gets the printer name.
     * </br>
     * Note:
     * The printer name cannot be set separately because that would invalidate the printer data.
     * To set a new printer, use {@link #setPrinterData(PrinterData)} with valid PrinterData.
     *
     * @return the printer name
     */
    public String getPrinterName() {
        return printerData.name;
    }

    /**
     * Disposes the cached {@link Printer} instance.
     */
    private void disposePrinter() {
        if (this.printer != null) {
            this.printer.dispose();
        }
        this.printer = null;
        this.printerBounds = null;
        this.printerTrim = null;
    }

    /**
     * Provides a (cached) {@link Printer} according to the configured {@link PrinterData}.
     *
     * @return a {@link Printer} or {@code null} if no valid {@link PrinterData} are configured
     */
    public Printer getPrinter() {
        if (this.printer != null) {
            return printer;

        } else  if (this.printerData != null) {
            if (Strings.isNullOrEmpty(this.printerData.driver)) {
                // printerData's 'driver' and 'name' initialized with the empty
                //  string leads to an error so set at least 'driver' to 'null'
                this.printerData.driver = null;
            }
            
            try {
                this.printer = new Printer(this.printerData);
            } catch (SWTError e) {
                // We could get a 'No more handles' error if we are trying to use a printer that 
                // has been removed from the system. Try the default instead. 
            } finally {
                disposePrinter();
                this.printerData.driver = null;
                this.printerData.name = null;
                this.printer = new Printer(this.printerData);
            }
            return printer;
        } else {
            return null;
        }
    }

    /**
     * Provides a (cached) {@link Dimension} containing the bounds of the currently configured
     * {@link Printer}.
     *
     * @return a {@link Dimension} denoting the printer's bounds, or {@code null} if no valid
     *         printer configuration is present
     */
    public Dimension getPrinterBounds() {
        if (printerBounds != null) {
            return printerBounds;
        }

        final Printer p = getPrinter();
        if (p != null) {
            final org.eclipse.swt.graphics.Rectangle pageArea = printer.getClientArea();
            printerBounds = new Dimension(pageArea.width, pageArea.height);
            return printerBounds;
        }
        return null;
    }

    /**
     * Provides a (cached) {@link Trim} describing the technical trim of the currently configured
     * {@link Printer}. Those margins are not included in {@link #getPrinterBounds()}. Thus,
     * {@link #getPrinterBounds()} + the result of this method == selected paper size
     *
     * @return a {@link Trim} denoting the printer's technical trim, or {@code null} if no valid
     *         printer configuration is present
     */
    public Trim getPrinterTrim() {
        if (printerTrim != null) {
            return printerTrim;
        }

        final Printer p = getPrinter();
        if (p != null) {
            final org.eclipse.swt.graphics.Rectangle trim = p.computeTrim(0, 0, 0, 0);
            printerTrim = new Trim(-trim.x, trim.x + trim.width, -trim.y, trim.y + trim.height);
            return printerTrim;
        }
        return null;
    }

    /**
     * Provides the currently chosen printer's resolution in DPI for both x and y direction.
     *
     * @return a {@link Point} denoting the printer's resolution, or {@code null} if no valid
     *         printer configuration is present
     */
    public Point getPrinterDPI() {
        final Printer p = getPrinter();
        if (p != null) {
            final org.eclipse.swt.graphics.Point dpi = p.getDPI();
            return new Point(dpi.x, dpi.y);
        }
        return null;
    }

    /**
     * Resets cached {@link Trim} information, which is necessary, e.g., after changing the paper
     * format or page orientation. Must be implemented by subclasses if necessary.
     */
    protected void resetTrimData() {
    }

    /**
     * Disposes the existing center offset leading to the re-computation on demand.
     */
    private void resetCenteringOffset() {
        centeringOffset = null;
    }

    /**
     * Provides the absolute offset that is required to horizontally and/or vertically center the
     * diagram on the page(s) as desired (configured).
     *
     * @return a {@link Point2D} providing the {@link Point2D#getX() x} and {@link Point2D#getY() y}
     *         offset
     */
    public Point2D getCenteringOffset() {
        return getCenteringOffset(1d);
    }

    /**
     * Provides the absolute offset that is required to horizontally and/or vertically center the
     * diagram on the page(s) as desired (configured), adjusted by the given {@code scale} factor,
     * which is be required, e.g., in order to create the print preview properly.
     *
     * @param scale
     *            the scale factor to be applied to the result's x and y components before returning
     * @return a {@link Point2D} providing the {@link Point2D#getX() x} and {@link Point2D#getY() y}
     *         offset
     */
    public Point2D getCenteringOffset(final double scale) {
        if (centeringOffset == null) {
            centeringOffset = updateCenteringOffset();
        }

        if (centeringOffset == null) {
            return new Point2D.Double();
        } else {
            return new Point2D.Double(
                    centerHorizontally ? centeringOffset.getX() * scale : 0,
                    centerVertically ? centeringOffset.getY() * scale : 0);
        }
    }

    /**
     * Re-calculates the (x,y) offset being required for aligning the page content centrally and/or
     * vertically, which is necessary, e.g., after changing the paper format or page orientation.
     * Must be implemented by subclasses if necessary.
     *
     * @return a {@link Point2D} determining the (x,y) offset, or <code>null</code> if not supported
     */
    protected Point2D updateCenteringOffset() {
        return null;
    }

    /**
     * Adds a property change listener.
     *
     * @param listener
     *            the listener to add
     * @see {@link PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)}
     */
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Adds a property change listener for a specific property.
     *
     * @param propertyName
     *            the property to listen on
     * @param listener
     *            the listener to add
     * @see {@link PropertyChangeSupport#addPropertyChangeListener(String, PropertyChangeListener)}
     */
    public void addPropertyChangeListener(final String propertyName,
            final PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Gets all registered property change listeners.
     *
     * @return the registered property change listeners
     * @see {@link PropertyChangeSupport#getPropertyChangeListeners()}
     */
    public PropertyChangeListener[] getPropertyChangeListeners() {
        return propertyChangeSupport.getPropertyChangeListeners();
    }

    /**
     * Gets all registered property change listeners for a specific property.
     *
     * @param propertyName
     *            the property being listened to
     * @return the registered property change listeners
     * @see {@link PropertyChangeSupport#getPropertyChangeListeners(String)}
     */
    public PropertyChangeListener[] getPropertyChangeListeners(final String propertyName) {
        return propertyChangeSupport.getPropertyChangeListeners(propertyName);
    }

    /**
     * Checks if there are listener registered on a specific property.
     *
     * @param propertyName
     *            the property being listened on
     * @return true, if there are listeners registered
     * @see {@link PropertyChangeSupport#hasListeners(String)}
     */
    public boolean hasListeners(final String propertyName) {
        return propertyChangeSupport.hasListeners(propertyName);
    }

    /**
     * Removes a property change listener.
     *
     * @param listener
     *            the listener to remove
     * @see {@link PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)}
     */
    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener from a specific property.
     *
     * @param propertyName
     *            the property being listened to
     * @param listener
     *            the listener to remove
     * @see {@link PropertyChangeSupport#removePropertyChangeListener(String, PropertyChangeListener)}
     */
    public void removePropertyChangeListener(final String propertyName,
            final PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    /**
     * Fires a property change.
     *
     * @param propertyName
     *            the property being changed
     * @param oldValue
     *            the old value of the property
     * @param newValue
     *            the new value of the property
     * @see {@link PropertyChangeSupport#firePropertyChange(String, Object, Object)}
     */
    protected void firePropertyChange(final String propertyName, final Object oldValue,
            final Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
}
