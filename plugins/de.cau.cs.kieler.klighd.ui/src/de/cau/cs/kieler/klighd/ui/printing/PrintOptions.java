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
package de.cau.cs.kieler.klighd.ui.printing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.printing.PrinterData;

/**
 * This class is used as part of the infrastructure required for data-bindings used with the JPS
 * dialog.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 */
public final class PrintOptions {

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
    /**
     * Observables ID for number of pages wide.
     * Denotes the number of vertical pages to print on.
     */
    public static final String PROPERTY_PAGES_WIDE = "pagesWide"; //$NON-NLS-1$
    /**
     * Observables ID for number of pages tall.
     * Denotes the number of horizontal pages to print on.
     */
    public static final String PROPERTY_PAGES_TALL = "pagesTall"; //$NON-NLS-1$

    /* preference IDs */
    /** The Constant PREFERENCE_PRINTER_DRIVER. */
    private static final String PREFERENCE_PRINTER_DRIVER = "klighd.printing.driver";
    /** The Constant PREFERENCE_PRINTER_NAME. */
    private static final String PREFERENCE_PRINTER_NAME = "klighd.printing.name";
    /** The Constant PREFERENCE_PRINTER_SCALE. */
    private static final String PREFERENCE_PRINTER_SCALE = "klighd.printing.scale";
    /** The Constant PREFERENCE_PRINTER_PAGES_TALL. */
    private static final String PREFERENCE_PRINTER_PAGES_TALL = "klighd.printing.pagesTall";
    /** The Constant PREFERENCE_PRINTER_PAGES_WIDE. */
    private static final String PREFERENCE_PRINTER_PAGES_WIDE = "klighd.printing.pagesWide";
    /** The Constant PREFERENCE_PRINTER_ORIENTATION. */
    private static final String PREFERENCE_PRINTER_ORIENTATION = "klighd.printing.orientation";
    /** The Constant PREFERENCE_PRINTER_DUPLEX. */
    private static final String PREFERENCE_PRINTER_DUPLEX = "klighd.printing.duplex";

    private final IPreferenceStore preferenceStore;

    /** The property change support. */
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private PrinterData printerData;
    private double scaleFactor;
    private int pagesWide;
    private int pagesTall;
    private PrintExporter exporter;

    /**
     * Create new print options loaded from the given preference store.
     * 
     * @param preferenceStore
     *            the preference store to load the options from
     */
    public PrintOptions(final IPreferenceStore preferenceStore) {
        this.preferenceStore = preferenceStore;
        scaleFactor = 1;
        pagesTall = 1;
        pagesWide = 1;
        restoreFromPreferences();
    }

    /**
     * Restore the options from preference store.
     */
    public void restoreFromPreferences() {
        final String driver = preferenceStore.getString(PREFERENCE_PRINTER_DRIVER);
        final String name = preferenceStore.getString(PREFERENCE_PRINTER_NAME);
        printerData = new PrinterData(driver, name);
        if (printerData != null) {
            setOrientation(preferenceStore.getInt(PREFERENCE_PRINTER_ORIENTATION));
            setDuplex(preferenceStore.getInt(PREFERENCE_PRINTER_DUPLEX));
        }
        setScaleFactor(preferenceStore.getDouble(PREFERENCE_PRINTER_SCALE));
        setPagesTall(preferenceStore.getInt(PREFERENCE_PRINTER_PAGES_TALL));
        setPagesWide(preferenceStore.getInt(PREFERENCE_PRINTER_PAGES_WIDE));
    }

    /**
     * Store the options in the preference store.
     */
    public void storeToPreferences() {
        preferenceStore.setValue(PREFERENCE_PRINTER_DRIVER, printerData.driver);
        preferenceStore.setValue(PREFERENCE_PRINTER_NAME, printerData.name);
        preferenceStore.setValue(PREFERENCE_PRINTER_SCALE, scaleFactor);
        preferenceStore.setValue(PREFERENCE_PRINTER_PAGES_TALL, pagesTall);
        preferenceStore.setValue(PREFERENCE_PRINTER_PAGES_WIDE, pagesWide);
        preferenceStore.setValue(PREFERENCE_PRINTER_ORIENTATION, printerData.orientation);
        preferenceStore.setValue(PREFERENCE_PRINTER_DUPLEX, printerData.duplex);
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
     * Sets the exporter to use when printing or showing the preview.
     * 
     * @param exporter
     *            the new exporter
     */
    public void setExporter(final PrintExporter exporter) {
        this.exporter = exporter;
    }

    /**
     * Gets the exporter to use when printing or showing the preview.
     * 
     * @return the exporter
     */
    public PrintExporter getExporter() {
        return exporter;
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