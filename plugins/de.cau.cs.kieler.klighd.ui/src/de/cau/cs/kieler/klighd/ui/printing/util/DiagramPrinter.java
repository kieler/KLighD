/******************************************************************************
 * Copyright (c) 2002, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package de.cau.cs.kieler.klighd.ui.printing.util;

import java.util.Collection;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Point;

import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;

/**
 * Basic page calculation operations have been factored out into this class. This class was derived
 * from the previous DiagramPrinter.
 * 
 * @author Wayne Diu, wdiu
 */
public abstract class DiagramPrinter implements Runnable {

    protected Point display_dpi;
    protected Collection<PiccoloViewer> diagrams;
//    protected PreferencesHint preferencesHint;
//    protected IMapMode mapMode;
    protected Point translated;

    protected float userScale;
    protected boolean isScaledPercent = false;
    protected boolean fitToPage = false;
    protected boolean printRangePageSelection = false;

    protected int pageFrom = 1, pageTo = 1;
    protected int rows = 1;

    protected int columns = 1;

    protected Graphics swtGraphics;
    protected Graphics graphics;

//    public DiagramPrinter(PreferencesHint preferencesHint, IMapMode mm) {
//        super();
//        this.preferencesHint = preferencesHint;
//        this.mapMode = mm;
//    }

    /**
     * Obtains the total number of pages that span columns and rows
     * 
     * @param dgrmEP
     * @return Point.x contains the total number of pages that span in a column Point.y contains the
     *         total number of pages that span in a row
     */
    protected org.eclipse.draw2d.geometry.Point getPageCount(
            Rectangle figureBounds, org.eclipse.draw2d.geometry.Point pageSize,
            boolean applyUserScale) {

            float fNumRows = (figureBounds.height * (applyUserScale ? userScale : 1)) / pageSize.y;
            int numRows = (int) Math.ceil(fNumRows);

            float fNumCols = (figureBounds.width * (applyUserScale ? userScale : 1)) / pageSize.x;
            int numCols = (int) Math.ceil(fNumCols);

            return new org.eclipse.draw2d.geometry.Point(numCols, numRows);
    }

    /**
     * Calculates the row in a grid, given a page number.
     * | 1 | 2 | 3 |
     * | 4 | 5 | 6 |
     * | 7 | 8 | 9 |
     * 
     * Given pageNum=5 and totalNumColumns=3, will return 2 (2nd row).
     * 
     * @param pageNum
     *            the page number in the grid.
     * @param totalNumColumns
     *            total number of columns of the grid.
     * @return row number corresponding to the page number.
     */
    protected int calculateRowFromPage(int pageNum, int totalNumColumns) {
        int row = pageNum / totalNumColumns;
        if (pageNum % totalNumColumns != 0) {
            row++;
        }
        return row;
    }

    /**
     * Calculates the column in a grid, given a page number.
     * | 1 | 2 | 3 |
     * | 4 | 5 | 6 |
     * | 7 | 8 | 9
     * |
     * 
     * Given pageNum=5 and totalNumColumns=3, will return 2 (2nd column).
     * 
     * @param pageNum
     *            the page number in the grid.
     * @param totalNumColumns
     *            total number of columns of the grid.
     * @param cRow
     *            the corresponding row of the page number.
     * @return row number corresponding to the page number.
     */
    protected int calculateColumnFromPage(int pageNum, int totalNumColumns, int cRow) {

        return (pageNum - ((cRow - 1) * totalNumColumns));
    }

    /**
     * Disposes of the resources.
     */
    protected void dispose() {
        if (this.graphics != null) {
            try {
                this.graphics.dispose();
            } catch (NullPointerException e) {
                // do nothing
            } finally {
                this.graphics = null;
            }
        }

        if (this.swtGraphics != null) {
            try {
                this.swtGraphics.dispose();
            } catch (NullPointerException e) {
                // do nothing
            } finally {
                this.swtGraphics = null;
            }
        }
    }

//    /**
//     * Creates the <code>MapModeGraphics</code>.
//     * 
//     * @param theGraphics
//     *            the <code>PrinterGraphics</code> object
//     * @return the new <code>MapModeGraphics</code>
//     */
//    protected MapModeGraphics createMapModeGraphics(Graphics theGraphics) {
//        return new MapModeGraphics(theGraphics, getMapMode());
//    }

    public void setPrintRangePages(int pageFrom, int pageTo) {
        assert pageFrom > 0 : "From page in print range page selection must be bigger than zero."; //$NON-NLS-1$
        assert (pageTo > 0 && pageTo >= pageFrom) : "To page in print range page selection must be bigger than zero and from page."; //$NON-NLS-1$
        this.pageFrom = pageFrom;
        this.pageTo = pageTo;
    }

    public Point getDisplayDPI() {
        return display_dpi;
    }

    public void setDisplayDPI(Point display_dpi) {
        this.display_dpi = display_dpi;
    }

//    public PreferencesHint getPreferencesHint() {
//        return preferencesHint;
//    }
//
//    public void setPreferencesHint(PreferencesHint preferencesHint) {
//        this.preferencesHint = preferencesHint;
//    }
//
//    public IMapMode getMapMode() {
//        return mapMode;
//    }
//
//    public void setMapMode(IMapMode mm) {
//        this.mapMode = mm;
//    }

    public Point getTranslated() {
        return translated;
    }

    public void setTranslated(Point translated) {
        this.translated = translated;
    }

    public float getUserScale() {
        return userScale;
    }

    public void setScaledPercent(int scalePercent) {
        this.isScaledPercent = true;
        this.userScale = (scalePercent) / 100.0f;
    }

    public void setScaledPercent(boolean isScaledPercent) {
        this.isScaledPercent = isScaledPercent;
    }

    public boolean isFitToPage() {
        return fitToPage;
    }

    public void setFitToPage(boolean fitToPage) {
        this.fitToPage = fitToPage;
    }

    public boolean isPrintRangePageSelection() {
        return printRangePageSelection;
    }

    public void setPrintRangePageSelection(boolean printRangePageSelection) {
        this.printRangePageSelection = printRangePageSelection;
    }

    public int getPageFrom() {
        return pageFrom;
    }

    public void setPageFrom(int pageFrom) {
        this.pageFrom = pageFrom;
    }

    public int getPageTo() {
        return pageTo;
    }

    public void setPageTo(int pageTo) {
        this.pageTo = pageTo;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Graphics getSwtGraphics() {
        return swtGraphics;
    }

    public void setSwtGraphics(Graphics swtGraphics) {
        this.swtGraphics = swtGraphics;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public void setDiagrams(Collection<PiccoloViewer> diagrams) {
        this.diagrams = diagrams;
    }

    public Collection<PiccoloViewer> getDiagrams() {
        return diagrams;
    }

}
