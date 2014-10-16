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
package de.cau.cs.kieler.klighd;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import de.cau.cs.kieler.klighd.IExportBranding.Trim;

/**
 * A record comprising all information required for diagram export supporting all the features like
 * exporting a clipped diagram, providing a preview, applying export customizers, etc.<br>
 * This class is shall not be instantiated by application code but will be by KLighD internally.
 *
 * @author chsch
 */
public class DiagramExportConfig {

    // SUPPRESS CHECKSTYLE NEXT 22 Visibility|Javadoc

    public final Rectangle2D diagramBounds;
    public final Rectangle tileBounds;

    public final double diagramScale;

    public boolean exportViewport = false;
    public boolean exportSemanticData = false;

    public Trim diagramTrim = null;
    public Trim tileTrim = null;
    public Iterable<IExportBranding> exportBrandings = null;

    public int pageNo = 0;
    public int row = 0;
    public int column = 0;

    public boolean firstColumn = false;
    public boolean firstRow = false;
    public boolean lastColumn = false;
    public boolean lastRow = false;


    /**
     * Constructor.
     *
     * @param diagramBounds
     *            the bounds of the diagram area to be exported
     * @param tileBounds
     *            the bounds of the particular diagram tiles
     */
    public DiagramExportConfig(final Rectangle2D diagramBounds, final Rectangle tileBounds) {
        this(diagramBounds, tileBounds, 1d);
    }

    /**
     * Constructor.
     *
     * @param diagramBounds
     *            the bounds of the diagram area to be exported
     * @param tileBounds
     *            the bounds of the particular diagram tiles
     * @param diagramScale
     *            the scale factor to be applied to the diagram (e.g. chosen by the user while
     *            exporting raster images or during printout)
     */
    public DiagramExportConfig(final Rectangle2D diagramBounds, final Rectangle tileBounds,
            final double diagramScale) {
        this.diagramBounds = diagramBounds;
        this.tileBounds = tileBounds;
        this.diagramScale = diagramScale;
    }


    /**
     * Configures the export of the current main diagram's visible area, by default the whole
     * diagram is exported.
     *
     * @param exportViewport
     *            {@code true} if just the view port shall be exported
     *
     * @return this {@link DiagramExportConfig} for convenience
     */
    // SUPPRESS CHECKSTYLE NEXT 2 Field -- don't bother me
    public DiagramExportConfig setExportViewport(final boolean exportViewport) {
        this.exportViewport = exportViewport;

        return this;
    }

    /**
     * Configures the export of the current main diagram's visible area, by default the whole
     * diagram is exported.
     *
     * @param exportViewport
     *            {@code true} if just the view port shall be exported
     *
     * @return this {@link DiagramExportConfig} for convenience
     */
    // SUPPRESS CHECKSTYLE NEXT 2 Field -- don't bother me
    public DiagramExportConfig setExportSemanticData(final boolean exportViewport) {
        this.exportViewport = exportViewport;

        return this;
    }

    /**
     * Combined setter of information concerning the position of the tile to be printed within
     * the whole diagram.
     *
     * @param firstColumn
     *            {@code true} if and only if tile is located in the first column
     * @param firstRow
     *            {@code true} if and only if tile is located in the first row
     * @param lastColumn
     *            {@code true} if and only if tile is located in the last column
     * @param lastRow
     *            {@code true} if and only if tile is located in the last row
     *
     * @return this {@link DiagramExportConfig} for convenience
     */
    // SUPPRESS CHECKSTYLE NEXT 2 Field -- don't bother me
    public DiagramExportConfig setTileInfo(final boolean firstColumn, final boolean firstRow,
            final boolean lastColumn, final boolean lastRow) {
        this.firstColumn = firstColumn;
        this.firstRow = firstRow;
        this.lastColumn = lastColumn;
        this.lastRow = lastRow;

        return this;
    }

    /**
     * Combined setter of information concerning the application of {@link IExportBranding
     * IExportBrandings} and their cumulated diagram and tile trim requirements.
     *
     * @param customizers
     *            the {@link IExportBranding IExportBranding} to be applied
     * @param diagramTrim
     *            the cumulated overall diagram trim required by the particular
     *            {@link IExportBranding IExportBranding}
     * @param tileTrim
     *            the cumulated diagram tile trim required by the particular
     *            {@link IExportBranding}s
     * @return this {@link DiagramExportConfig} for convenience
     */
    // SUPPRESS CHECKSTYLE NEXT 2 Field -- don't bother me
    public DiagramExportConfig setBrandingsAndTrim(final Iterable<IExportBranding> customizers,
            final Trim diagramTrim, final Trim tileTrim) {
        this.exportBrandings = customizers;
        this.diagramTrim = diagramTrim;
        this.tileTrim = tileTrim;

        return this;
    }

    /**
     * Combined setter of information concerning the page/tile numbering.<br>
     * {@link #firstRow} & {@link #firstColumn} are set if {@code rowNo} & {@code columnNo} are
     * equal to zero, {@link #lastRow} & and {@link #lastColumn} are set to {@code false}.
     *
     * @param pageNo
     *            the current page number of the currently printed tile
     * @param row
     *            the row number of the currently printed tile (starting with zero)
     * @param column
     *            the column number of the currently printed tile (starting with zero)
     * @return this {@link DiagramExportConfig} for convenience
     */
    // SUPPRESS CHECKSTYLE NEXT 2 Field -- don't bother me
    public DiagramExportConfig setPageAndTileNumbesr(final int pageNo, final int row,
            final int column) {

        this.setPageAndTileNumbesr(pageNo, row, column, false, false);

        return this;
    }

    /**
     * Combined setter of information concerning the page/tile numbering..<br>
     * {@link #firstRow} & {@link #firstColumn} are set if {@code rowNo} & {@code columnNo} are
     * equal to zero.
     *
     * @param pageNo
     *            the current page number of the currently printed tile
     * @param row
     *            the row number of the currently printed tile (starting with zero)
     * @param column
     *            the column number of the currently printed tile (starting with zero)
     * @param lastRow
     *            shall be {@code true} if the currently printed tile is part of the last row
     * @param lastColumn
     *            shall be {@code true} if the currently printed tile is part of the last column
     * @return this {@link DiagramExportConfig} for convenience
     */
    // SUPPRESS CHECKSTYLE NEXT 2 Field -- don't bother me
    public DiagramExportConfig setPageAndTileNumbesr(final int pageNo, final int row,
            final int column, final boolean lastRow, final boolean lastColumn) {
        this.pageNo = pageNo;
        this.row = row;
        this.column = column;

        this.firstRow = row == 0;
        this.firstColumn = column == 0;

        this.lastRow = lastRow;
        this.lastColumn = lastColumn;

        return this;
    }
}