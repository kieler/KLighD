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
package de.cau.cs.kieler.klighd.ui.printing.options;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.printing.PrinterData;

import de.cau.cs.kieler.klighd.ui.printing.util.PrintExporter;

/**
 * This class is used as part of the infrastructure required for data-bindings used with the JPS
 * dialog.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 */
public class PrintOptions extends PrintModelElement {

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

    /* printer data */

    /** Observables ID for scale factor. */
    public static final String PROPERTY_SCALE_FACTOR = "scaleFactor"; //$NON-NLS-1$
    /** Observables ID for displaying the scale factor. */
    public static final String PROPERTY_SCALE_PERCENT = "scalePercent"; //$NON-NLS-1$
    /** Observables ID for number of pages wide. */
    public static final String PROPERTY_FIT_TO_WIDTH = "fitToPagesWidth"; //$NON-NLS-1$
    /** Observables ID for number of pages tall. */
    public static final String PROPERTY_FIT_TO_HEIGHT = "fitToPagesHeight"; //$NON-NLS-1$

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

    /** The preference store. */
    private IPreferenceStore preferenceStore;

    /** The printer data. */
    private PrinterData printerData;

    /** The scale factor. */
    private double scaleFactor;
    
    /** The fit to pages width. */
    private int fitToPagesWidth;
    
    /** The fit to pages height. */
    private int fitToPagesHeight;
    
    /** The exporter. */
    private PrintExporter exporter;

    /**
     * Create new default print options.
     */
    public PrintOptions() {
        scaleFactor = 1;
        fitToPagesHeight = 1;
        fitToPagesWidth = 1;

    }

    /**
     * Create new print options loaded from the given preference store.
     * 
     * @param preferenceStore
     *            the preference store to load the options from
     */
    public PrintOptions(final IPreferenceStore preferenceStore) {
        this();
        this.preferenceStore = preferenceStore;
        restoreFromPreferences();
    }

    /**
     * Restore the options from preference store if set.
     */
    public void restoreFromPreferences() {
        if (preferenceStore != null) {
            String driver = preferenceStore.getString(PREFERENCE_PRINTER_DRIVER);
            String name = preferenceStore.getString(PREFERENCE_PRINTER_NAME);
            printerData = new PrinterData(driver, name);
            if (printerData != null) {
                setOrientation(preferenceStore.getInt(PREFERENCE_PRINTER_ORIENTATION));
                setDuplex(preferenceStore.getInt(PREFERENCE_PRINTER_DUPLEX));
            }
            setScaleFactor(preferenceStore.getDouble(PREFERENCE_PRINTER_SCALE));
            setFitToPagesHeight(preferenceStore.getInt(PREFERENCE_PRINTER_PAGES_TALL));
            setFitToPagesWidth(preferenceStore.getInt(PREFERENCE_PRINTER_PAGES_WIDE));
        }
    }

    /**
     * Store the options in the preference store if set.
     */
    public void storeToPreferences() {
        if (preferenceStore != null) {
            preferenceStore.setValue(PREFERENCE_PRINTER_DRIVER, printerData.driver);
            preferenceStore.setValue(PREFERENCE_PRINTER_NAME, printerData.name);
            preferenceStore.setValue(PREFERENCE_PRINTER_SCALE, scaleFactor);
            preferenceStore.setValue(PREFERENCE_PRINTER_PAGES_TALL, fitToPagesHeight);
            preferenceStore.setValue(PREFERENCE_PRINTER_PAGES_WIDE, fitToPagesWidth);
            preferenceStore.setValue(PREFERENCE_PRINTER_ORIENTATION, printerData.orientation);
            preferenceStore.setValue(PREFERENCE_PRINTER_DUPLEX, printerData.duplex);
        }
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
     * @param scaleFactor the new scale factor
     */
    public void setScaleFactor(final double scaleFactor) {
        double oldFactor = this.scaleFactor;
        if (scaleFactor > 0) {
            this.scaleFactor = scaleFactor;
        }
        firePropertyChange(PROPERTY_SCALE_FACTOR, oldFactor, this.scaleFactor);
        firePropertyChange(PROPERTY_SCALE_PERCENT, (int) (oldFactor * PERCENT_FACTOR),
                (int) (this.scaleFactor * PERCENT_FACTOR));
    }

    /**
     * Gets the scale percent.
     *
     * @return the scale in percent
     */
    public int getScalePercent() {
        return (int) (scaleFactor * PERCENT_FACTOR);
    }

    /**
     * Sets the scale percent.
     *
     * @param scalePercent the new scale in percent
     */
    public void setScalePercent(final int scalePercent) {
        double oldFactor = this.scaleFactor;
        if (scalePercent > 0) {
            this.scaleFactor = scalePercent / PERCENT_FACTOR;
        }
        firePropertyChange(PROPERTY_SCALE_FACTOR, oldFactor, this.scaleFactor);
        firePropertyChange(PROPERTY_SCALE_PERCENT, (int) (oldFactor * PERCENT_FACTOR),
                (int) (this.scaleFactor * PERCENT_FACTOR));
    }

    /**
     * Gets the fit to pages width.
     *
     * @return the fit to pages width
     */
    public int getFitToPagesWidth() {
        return fitToPagesWidth;
    }

    /**
     * Sets the fit to pages width.
     *
     * @param fitToPagesWidth the new fit to pages width
     */
    public void setFitToPagesWidth(final int fitToPagesWidth) {
        int oldWidth = this.fitToPagesWidth;
        if (fitToPagesWidth > 0) {
            this.fitToPagesWidth = fitToPagesWidth;
        }
        firePropertyChange(PROPERTY_FIT_TO_WIDTH, oldWidth, this.fitToPagesWidth);
    }

    /**
     * Gets the fit to pages height.
     *
     * @return the fit to pages height
     */
    public int getFitToPagesHeight() {
        return fitToPagesHeight;
    }

    /**
     * Sets the fit to pages height.
     *
     * @param fitToPagesHeight the new fit to pages height
     */
    public void setFitToPagesHeight(final int fitToPagesHeight) {
        int oldHeight = this.fitToPagesHeight;
        if (fitToPagesHeight > 0) {
            this.fitToPagesHeight = fitToPagesHeight;
        }
        firePropertyChange(PROPERTY_FIT_TO_HEIGHT, oldHeight, this.fitToPagesHeight);
    }

    /**
     * Checks if is all pages.
     *
     * @return true, if is all pages
     */
    public boolean isAllPages() {
        return printerData.scope == PrinterData.ALL_PAGES;
    }

    /**
     * Sets the all pages.
     *
     * @param allPages the new all pages
     */
    public void setAllPages(final boolean allPages) {
        boolean oldAll = printerData.scope == PrinterData.ALL_PAGES;
        printerData.scope = allPages ? PrinterData.ALL_PAGES : PrinterData.PAGE_RANGE;
        firePropertyChange(PROPERTY_ALL_PAGES, oldAll, printerData.scope == PrinterData.ALL_PAGES);
    }

    /**
     * Gets the range from.
     *
     * @return the range from
     */
    public int getRangeFrom() {
        return printerData.startPage;
    }

    /**
     * Sets the range from.
     *
     * @param rangeFrom the new range from
     */
    public void setRangeFrom(final int rangeFrom) {
        int oldFrom = printerData.startPage;
        printerData.startPage = rangeFrom;
        firePropertyChange(PROPERTY_RANGE_FROM, oldFrom, rangeFrom);
    }

    /**
     * Gets the range to.
     *
     * @return the range to
     */
    public int getRangeTo() {
        return printerData.endPage;
    }

    /**
     * Sets the range to.
     *
     * @param rangeTo the new range to
     */
    public void setRangeTo(final int rangeTo) {
        int oldTo = printerData.endPage;
        printerData.endPage = rangeTo;
        firePropertyChange(PROPERTY_RANGE_TO, oldTo, rangeTo);
    }

    /**
     * Gets the copies.
     *
     * @return the copies
     */
    public int getCopies() {
        return printerData.copyCount;
    }

    /**
     * Sets the copies.
     *
     * @param copies the new copies
     */
    public void setCopies(final int copies) {
        int oldCopies = printerData.copyCount;
        printerData.copyCount = copies;
        firePropertyChange(PROPERTY_COPIES, oldCopies, copies);
    }

    /**
     * Checks if is collate.
     *
     * @return true, if is collate
     */
    public boolean isCollate() {
        return printerData.collate;
    }

    /**
     * Sets the collate.
     *
     * @param collate the new collate
     */
    public void setCollate(final boolean collate) {
        boolean oldCollate = printerData.collate;
        printerData.collate = collate;
        firePropertyChange(PROPERTY_COLLATE, oldCollate, collate);
    }

    /**
     * Gets the duplex.
     *
     * @return the duplex
     */
    public int getDuplex() {
        return printerData.duplex;
    }

    /**
     * Sets the duplex.
     *
     * @param duplex the new duplex
     */
    public void setDuplex(final int duplex) {
        int oldDuplex = printerData.duplex;
        printerData.duplex = duplex;
        firePropertyChange(PROPERTY_DUPLEX, oldDuplex, printerData.duplex);
    }

    /**
     * Gets the printer data.
     *
     * @return the printer data
     */
    public PrinterData getPrinterData() {
        return this.printerData;
    }

    /**
     * Sets the printer data.
     *
     * @param printerData the new printer data
     */
    public void setPrinterData(final PrinterData printerData) {
        PrinterData oldPrinterData = this.printerData;
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
     *
     * @return the orientation
     */
    public int getOrientation() {
        return printerData.orientation;
    }

    /**
     * Sets the orientation.
     *
     * @param orientation the new orientation
     */
    public void setOrientation(final int orientation) {
        int oldOrientation = printerData.orientation;
        printerData.orientation = orientation;
        firePropertyChange(PROPERTY_ORIENTATION, oldOrientation, printerData.orientation);
    }

    /**
     * Gets the printer name.
     *
     * @return the printer name
     */
    public String getPrinterName() {
        return printerData.name;
    }

    /**
     * Sets the exporter.
     *
     * @param exporter the new exporter
     */
    public void setExporter(final PrintExporter exporter) {
        this.exporter = exporter;
    }

    /**
     * Gets the exporter.
     *
     * @return the exporter
     */
    public PrintExporter getExporter() {
        return exporter;
    }

//    public void setPrinterName(String name) {
//        setPrinterData(new );
//    }
}
