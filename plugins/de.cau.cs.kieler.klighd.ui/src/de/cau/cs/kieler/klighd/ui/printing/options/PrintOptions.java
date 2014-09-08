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

import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * This class is used as part of the infrastructure required for data-bindings used with the JPS
 * dialog.
 * 
 * @author Christian Damus (cdamus)
 * @author James Bruck (jbruck)
 */
public class PrintOptions extends PrintModelElement {
    public static String PROPERTY_DESTINATION = "destination"; //$NON-NLS-1$

    public static String PROPERTY_SCALE_FACTOR = "scaleFactor"; //$NON-NLS-1$
    public static String PROPERTY_FIT_TO_WIDTH = "fitToPagesWidth"; //$NON-NLS-1$
    public static String PROPERTY_FIT_TO_HEIGHT = "fitToPagesHeight"; //$NON-NLS-1$

    public static String PROPERTY_ALL_PAGES = "allPages"; //$NON-NLS-1$
    public static String PROPERTY_RANGE_FROM = "rangeFrom"; //$NON-NLS-1$
    public static String PROPERTY_RANGE_TO = "rangeTo"; //$NON-NLS-1$

    public static String PROPERTY_COPIES = "copies"; //$NON-NLS-1$
    public static String PROPERTY_COLLATE = "collate"; //$NON-NLS-1$

    public static String PROPERTY_QUALITY_HIGH = "qualityHigh"; //$NON-NLS-1$
    public static String PROPERTY_QUALITY_LOW = "qualityLow"; //$NON-NLS-1$
    public static String PROPERTY_QUALITY_MED = "qualityMed"; //$NON-NLS-1$
    public static String PROPERTY_CHROMATICITY_MONO = "chromaticityMono"; //$NON-NLS-1$
    public static String PROPERTY_CHROMATICITY_COLOR = "chromaticityColor"; //$NON-NLS-1$
    public static String PROPERTY_SIDES_ONESIDED = "sideOneSided"; //$NON-NLS-1$
    public static String PROPERTY_SIDES_TUMBLE = "sideTumble"; //$NON-NLS-1$
    public static String PROPERTY_SIDES_DUPLEX = "sideDuplex"; //$NON-NLS-1$

    public static String PROPERTY_JOB_NAME = "jobName"; //$NON-NLS-1$
    public static String PROPERTY_USER_NAME = "userName"; //$NON-NLS-1$

    public static String PROPERTY_DIAGRAM_CURRENT = "diagramCurrent"; //$NON-NLS-1$
    public static String PROPERTY_DIAGRAM_SELECTION = "diagramSelection"; //$NON-NLS-1$

    private PrintDestination destination;

    private int scaleFactor;
    private int fitToPagesWidth;
    private int fitToPagesHeight;

    private boolean allPages;
    private int rangeFrom;
    private int rangeTo;

    private int copies;
    private boolean collate;

    private boolean qualityHigh;
    private boolean qualityLow;
    private boolean qualityMed;

    private boolean chromaticityColor;
    private boolean chromaticityMono;

    private boolean sideOneSided;
    private boolean sideTumble;
    private boolean sideDuplex;

    private String jobName;
    private String userName;

    private boolean diagramCurrent;
    private boolean diagramSelection;

    private List<String> diagramsToPrint;

    public PrintOptions() {
        super();
        scaleFactor = 100;
        fitToPagesHeight = 1;
        fitToPagesWidth = 1;

        setAllPages(true);
        setRangeFrom(1);
        setRangeTo(1);

        setCopies(1);
        setCollate(false);

        setQualityHigh(true);
        setSideOneSided(true);
        setChromaticityColor(true);

        setDiagramCurrent(true);
    }

    /**
     * @param preferenceStore
     */
    public PrintOptions(IPreferenceStore preferenceStore) {
        this();
        
    }

    public PrintDestination getDestination() {
        return destination;
    }

    public void setDestination(PrintDestination destination) {
        PrintDestination oldDestination = this.destination;
        this.destination = destination;
        firePropertyChange(PROPERTY_DESTINATION, oldDestination, destination);
    }

//    public boolean isPercentScaling() {
//        return percentScaling;
//    }
//
//    public void setPercentScaling(boolean percentScaling) {
//        boolean oldScaling = this.percentScaling;
//        this.percentScaling = percentScaling;
//        firePropertyChange(PROPERTY_PERCENT_SCALING, oldScaling, percentScaling);
//    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String name) {
        String oldName = this.jobName;
        this.jobName = name;
        firePropertyChange(PROPERTY_JOB_NAME, oldName, name);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        String oldName = this.userName;
        this.userName = name;
        firePropertyChange(PROPERTY_USER_NAME, oldName, name);
    }

    public void setDiagramsToPrint(List<String> diagramsToPrint) {
        this.diagramsToPrint = diagramsToPrint;
    }

    public List<String> getDiagramsToPrint() {
        return diagramsToPrint;
    }

    public boolean isDiagramCurrent() {
        return this.diagramCurrent;
    }

    public void setDiagramCurrent(boolean diagramCurrent) {
        boolean oldDiagramCurrent = this.diagramCurrent;
        this.diagramCurrent = diagramCurrent;
        firePropertyChange(PROPERTY_DIAGRAM_CURRENT, oldDiagramCurrent, diagramCurrent);
    }

    public boolean isDiagramSelection() {
        return this.diagramSelection;
    }

    public void setDiagramSelection(boolean diagramSelection) {
        boolean oldDiagramSelection = this.diagramSelection;
        this.diagramSelection = diagramSelection;
        firePropertyChange(PROPERTY_DIAGRAM_SELECTION, oldDiagramSelection, diagramSelection);
    }

    public boolean isQualityHigh() {
        return this.qualityHigh;
    }

    public void setQualityHigh(boolean qualityHigh) {
        boolean oldQualityHigh = this.qualityHigh;
        this.qualityHigh = qualityHigh;
        firePropertyChange(PROPERTY_QUALITY_HIGH, oldQualityHigh, qualityHigh);
    }

    public boolean isQualityLow() {
        return this.qualityLow;
    }

    public void setQualityLow(boolean qualityLow) {
        boolean oldQualityLow = this.qualityLow;
        this.qualityLow = qualityLow;
        firePropertyChange(PROPERTY_QUALITY_LOW, oldQualityLow, this.qualityLow);
    }

    public boolean isQualityMed() {
        return this.qualityMed;
    }

    public void setQualityMed(boolean qualityMed) {
        boolean oldQualityMed = this.qualityMed;
        this.qualityMed = qualityMed;
        firePropertyChange(PROPERTY_QUALITY_MED, oldQualityMed, this.qualityMed);
    }

    public int getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(int scaleFactor) {
//        if (scaleFactor > 0 && scaleFactor <= 100) {
            int oldFactor = this.scaleFactor;
            this.scaleFactor = scaleFactor;
            firePropertyChange(PROPERTY_SCALE_FACTOR, oldFactor, scaleFactor);
//            return true;
//        } else {
//            return false;
//        }
    }

    public int getFitToPagesWidth() {
        return fitToPagesWidth;
    }

    public void setFitToPagesWidth(int fitToPagesWidth) {
//        if (fitToPagesWidth > 0) {
            int oldWidth = this.fitToPagesWidth;
            this.fitToPagesWidth = fitToPagesWidth;
            firePropertyChange(PROPERTY_FIT_TO_WIDTH, oldWidth, fitToPagesWidth);
//            return true;
//        } else {
//            return false;
//        }
    }

    public int getFitToPagesHeight() {
        return fitToPagesHeight;
    }

    public boolean setFitToPagesHeight(int fitToPagesHeight) {
        if (fitToPagesHeight > 0) {
            int oldHeight = this.fitToPagesHeight;
            this.fitToPagesHeight = fitToPagesHeight;
            firePropertyChange(PROPERTY_FIT_TO_HEIGHT, oldHeight, fitToPagesHeight);
            return true;
        } else {
            return false;
        }
    }

    public boolean isAllPages() {
        return allPages;
    }

    public void setAllPages(boolean allPages) {
        boolean oldAll = this.allPages;
        this.allPages = allPages;
        firePropertyChange(PROPERTY_ALL_PAGES, oldAll, allPages);
    }

    public int getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(int rangeFrom) {
        int oldFrom = this.rangeFrom;
        this.rangeFrom = rangeFrom;
        firePropertyChange(PROPERTY_RANGE_FROM, oldFrom, rangeFrom);
    }

    public int getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(int rangeTo) {
        int oldTo = this.rangeTo;
        this.rangeTo = rangeTo;
        firePropertyChange(PROPERTY_RANGE_TO, oldTo, rangeTo);
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        int oldCopies = this.copies;
        this.copies = copies;
        firePropertyChange(PROPERTY_COPIES, oldCopies, copies);
    }

    public boolean isCollate() {
        return collate;
    }

    public void setCollate(boolean collate) {
        boolean oldCollate = this.collate;
        this.collate = collate;
        firePropertyChange(PROPERTY_COLLATE, oldCollate, collate);
    }

    public boolean isChromaticityColor() {
        return this.chromaticityColor;
    }

    public void setChromaticityColor(boolean chromaticityColor) {
        boolean oldChromaticityColor = this.chromaticityColor;
        this.chromaticityColor = chromaticityColor;
        firePropertyChange(PROPERTY_CHROMATICITY_COLOR, oldChromaticityColor,
                this.chromaticityColor);
    }

    public boolean isChromaticityMono() {
        return this.chromaticityMono;
    }

    public void setChromaticityMono(boolean chromaticityMono) {
        boolean oldChromaticityMono = this.chromaticityMono;
        this.chromaticityMono = chromaticityMono;
        firePropertyChange(PROPERTY_CHROMATICITY_MONO, oldChromaticityMono, this.chromaticityMono);
    }

    public boolean isSideOneSided() {
        return this.sideOneSided;
    }

    public void setSideOneSided(boolean sideOneSided) {
        boolean oldSideOneSided = this.sideOneSided;
        this.sideOneSided = sideOneSided;
        firePropertyChange(PROPERTY_SIDES_ONESIDED, oldSideOneSided, this.sideOneSided);
    }

    public boolean isSideTumble() {
        return this.sideTumble;
    }

    public void setSideTumble(boolean sideTumble) {
        boolean oldSideTumble = this.sideTumble;
        this.sideTumble = sideTumble;
        firePropertyChange(PROPERTY_SIDES_TUMBLE, oldSideTumble, this.sideTumble);
    }

    public boolean isSideDuplex() {
        return this.sideDuplex;
    }

    public void setSideDuplex(boolean sideDuplex) {
        boolean oldSideDuplex = this.sideDuplex;
        this.sideDuplex = sideDuplex;
        firePropertyChange(PROPERTY_SIDES_DUPLEX, oldSideDuplex, this.sideDuplex);
    }
}
