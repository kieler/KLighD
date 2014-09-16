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
    public static String PROPERTY_PRINTER_DATA = "printerData"; //$NON-NLS-1$
    public static String PROPERTY_PRINTER_NAME = "printerName"; //$NON-NLS-1$
    public static String PROPERTY_COPIES = "copies"; //$NON-NLS-1$
    public static String PROPERTY_COLLATE = "collate"; //$NON-NLS-1$

    public static String PROPERTY_ALL_PAGES = "allPages"; //$NON-NLS-1$
    public static String PROPERTY_RANGE_FROM = "rangeFrom"; //$NON-NLS-1$
    public static String PROPERTY_RANGE_TO = "rangeTo"; //$NON-NLS-1$

    public static String PROPERTY_DUPLEX = "duplex"; //$NON-NLS-1$

    public static String PROPERTY_ORIENTATION = "orientation"; //$NON-NLS-1$

    /* other data */
    public static String PROPERTY_SCALE_FACTOR = "scaleFactor"; //$NON-NLS-1$
    public static String PROPERTY_FIT_TO_WIDTH = "fitToPagesWidth"; //$NON-NLS-1$
    public static String PROPERTY_FIT_TO_HEIGHT = "fitToPagesHeight"; //$NON-NLS-1$

    /* preference IDs */
    private static String PREFERENCE_PRINTER_DRIVER = "klighd.printing.driver";
    private static String PREFERENCE_PRINTER_NAME = "klighd.printing.name";
    private static String PREFERENCE_PRINTER_SCALE = "klighd.printing.scale";
    private static String PREFERENCE_PRINTER_PAGES_TALL = "klighd.printing.pagesTall";
    private static String PREFERENCE_PRINTER_PAGES_WIDE = "klighd.printing.pagesWide";
    private static String PREFERENCE_PRINTER_ORIENTATION = "klighd.printing.orientation";
    private static String PREFERENCE_PRINTER_DUPLEX = "klighd.printing.duplex";

    private IPreferenceStore preferenceStore;

    private PrinterData printerData;

    private double scaleFactor;
    private int fitToPagesWidth;
    private int fitToPagesHeight;
    private PrintExporter exporter;

    public PrintOptions() {
        super();
        scaleFactor = 1;
        fitToPagesHeight = 1;
        fitToPagesWidth = 1;

    }

    /**
     * @param preferenceStore
     */
    public PrintOptions(IPreferenceStore preferenceStore) {
        this();
        this.preferenceStore = preferenceStore;
        restoreFromPreferences();
    }

    public void restoreFromPreferences() {
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

    public void storeToPreferences() {
        preferenceStore.setValue(PREFERENCE_PRINTER_DRIVER, printerData.driver);
        preferenceStore.setValue(PREFERENCE_PRINTER_NAME, printerData.name);
        preferenceStore.setValue(PREFERENCE_PRINTER_SCALE, scaleFactor);
        preferenceStore.setValue(PREFERENCE_PRINTER_PAGES_TALL, fitToPagesHeight);
        preferenceStore.setValue(PREFERENCE_PRINTER_PAGES_WIDE, fitToPagesWidth);
        preferenceStore.setValue(PREFERENCE_PRINTER_ORIENTATION, printerData.orientation);
        preferenceStore.setValue(PREFERENCE_PRINTER_DUPLEX, printerData.duplex);
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(double scaleFactor) {
        double oldFactor = this.scaleFactor;
        if (scaleFactor > 0) {
            this.scaleFactor = scaleFactor;
        }
        firePropertyChange(PROPERTY_SCALE_FACTOR, oldFactor, this.scaleFactor);
    }

    public int getFitToPagesWidth() {
        return fitToPagesWidth;
    }

    public void setFitToPagesWidth(int fitToPagesWidth) {
        int oldWidth = this.fitToPagesWidth;
        if (fitToPagesWidth > 0) {
            this.fitToPagesWidth = fitToPagesWidth;
        }
        firePropertyChange(PROPERTY_FIT_TO_WIDTH, oldWidth, this.fitToPagesWidth);
    }

    public int getFitToPagesHeight() {
        return fitToPagesHeight;
    }

    public void setFitToPagesHeight(int fitToPagesHeight) {
        int oldHeight = this.fitToPagesHeight;
        if (fitToPagesHeight > 0) {
            this.fitToPagesHeight = fitToPagesHeight;
        }
        firePropertyChange(PROPERTY_FIT_TO_HEIGHT, oldHeight, this.fitToPagesHeight);
    }

    public boolean isAllPages() {
        return printerData.scope == PrinterData.ALL_PAGES;
    }

    public void setAllPages(boolean allPages) {
        boolean oldAll = printerData.scope == PrinterData.ALL_PAGES;
        printerData.scope = allPages ? PrinterData.ALL_PAGES : PrinterData.PAGE_RANGE;
        firePropertyChange(PROPERTY_ALL_PAGES, oldAll, printerData.scope == PrinterData.ALL_PAGES);
    }

    public int getRangeFrom() {
        return printerData.startPage;
    }

    public void setRangeFrom(int rangeFrom) {
        int oldFrom = printerData.startPage;
        printerData.startPage = rangeFrom;
        firePropertyChange(PROPERTY_RANGE_FROM, oldFrom, rangeFrom);
    }

    public int getRangeTo() {
        return printerData.endPage;
    }

    public void setRangeTo(int rangeTo) {
        int oldTo = printerData.endPage;
        printerData.endPage = rangeTo;
        firePropertyChange(PROPERTY_RANGE_TO, oldTo, rangeTo);
    }

    public int getCopies() {
        return printerData.copyCount;
    }

    public void setCopies(int copies) {
        int oldCopies = printerData.copyCount;
        printerData.copyCount = copies;
        firePropertyChange(PROPERTY_COPIES, oldCopies, copies);
    }

    public boolean isCollate() {
        return printerData.collate;
    }

    public void setCollate(boolean collate) {
        boolean oldCollate = printerData.collate;
        printerData.collate = collate;
        firePropertyChange(PROPERTY_COLLATE, oldCollate, collate);
    }

    public int getDuplex() {
        return printerData.duplex;
    }

    public void setDuplex(int duplex) {
        int oldDuplex = printerData.duplex;
        printerData.duplex = duplex;
        firePropertyChange(PROPERTY_DUPLEX, oldDuplex, printerData.duplex);
    }

    public PrinterData getPrinterData() {
        return this.printerData;
    }

    public void setPrinterData(PrinterData printerData) {
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

    public int getOrientation() {
        return printerData.orientation;
    }

    public void setOrientation(int orientation) {
        int oldOrientation = printerData.orientation;
        printerData.orientation = orientation;
        firePropertyChange(PROPERTY_ORIENTATION, oldOrientation, printerData.orientation);
    }

    public String getPrinterName() {
        return printerData.name;
    }

    /**
     * @param exporter
     */
    public void setExporter(PrintExporter exporter) {
        this.exporter = exporter;
    }

    /**
     * @return the exporter
     */
    public PrintExporter getExporter() {
        return exporter;
    }

//    public void setPrinterName(String name) {
//        setPrinterData(new );
//    }
}
