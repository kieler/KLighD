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

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Collections;

import org.eclipse.swt.widgets.Display;

import de.cau.cs.kieler.klighd.IExportBranding.Trim;

/**
 * A record comprising all information required for diagram export supporting all the features like
 * exporting a clipped diagram, providing a preview, applying export customizers, etc.<br>
 * This class is shall not be instantiated by application code but will be by KLighD internally.
 *
 * @author chsch
 */
public class DiagramExportConfig {

    // this is just a data record class introduced for reducing method parameters, so
    // CHECKSTYLEOFF Visibility|Field

    /** The (unadjusted) bounds of the diagram to be exported (excluding the diagram trim). */
    public final Rectangle2D diagramBounds;

    /** The (unadjusted) size of the diagram tiles created on printouts or (raster) image exports. */
    public final Rectangle tileBounds;

    /** The scale being chosen by the tool user while creating printouts or (raster) image exports. */
    public final double diagramScale;

    /**
     * The configured image resolution; during printouts this field is set to
     * {@link org.eclipse.swt.printing.Printer#getDPI() Printer#getDPI()} of the chosen printer,
     * otherwise to {@link Display#getDPI()} of the default Display.
     *
     * @see Device#getDPI()
     */
    public final Point dotsPerInch;

    /**
     * Determines whether the diagram part being currently visible on the screen shall be exported
     * (<code>true</code>), or whether the whole currently displayed diagram (part/clip) shall be
     * exported (<code>false</code>).
     */
    public boolean exportViewport = false;

    /**
     * Determines whether semantic information attached to diagram elements shall be exported.<br>
     * This available for SVG exports only.
     */
    public boolean exportSemanticData = false;

    /** The list of {@link IExportBranding}s being enabled for the current diagram export. */
    public Iterable<IExportBranding> exportBrandings = Collections.emptyList();

    /** The cumulated overall diagram {@link Trim} required by the {@link #exportBrandings}. */
    public Trim diagramTrim = null;

    /** The cumulated diagram tile {@link Trim} required by the {@link #exportBrandings}. */
    public Trim tileTrim = null;

    /**
     * The page number of the diagram tile to be printed or exported starting at 1, is updated after
     * each tile print or export.
     */
    public int pageNo = 0;

    /**
     * The row number of the diagram tile to be printed or exported starting at 0, is updated after
     * each tile print or export.
     */
    public int row = 0;

    /**
     * The column number of the diagram tile to be printed or exported starting at 0, is updated
     * after each tile print or export.
     */
    public int column = 0;

    /**
     * Indicates whether the currently handled tile is part of the first row, is updated after each
     * tile print or export.
     */
    public boolean firstRow = false;

    /**
     * Indicates whether the currently handled tile is part of the first column, is updated after
     * each tile print or export.
     */
    public boolean firstColumn = false;

    /**
     * Indicates whether the currently handled tile is part of the last row, is updated after each
     * tile print or export.
     */
    public boolean lastRow = false;

    /**
     * Indicates whether the currently handled tile is part of the last column, is updated after
     * each tile print or export.
     */
    public boolean lastColumn = false;


    /**
     * Constructor.<br>
     * {@link #diagramScale} is set equal to {@code 1d}, {@link #dotsPerInch} is set to
     * {@link Display#getDPI()} of the default Display.
     *
     * @param diagramBounds
     *            the bounds of the diagram area to be exported
     * @param tileBounds
     *            the bounds of the particular diagram tiles
     */
    public DiagramExportConfig(final Rectangle2D diagramBounds, final Rectangle tileBounds) {
        this(diagramBounds, tileBounds, 1d, null);
    }

    /**
     * Constructor.<br>
     * {@link #dotsPerInch} is set to {@link Display#getDPI()} of the default Display.
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
        this(diagramBounds, tileBounds, diagramScale, new Point());

        final org.eclipse.swt.graphics.Point dpi = Display.getDefault().getDPI();
        this.dotsPerInch.setLocation(dpi.x, dpi.y);
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
     * @param dotsPerInch
     *            the image resolution used by the employed drawing
     *            {@link org.eclipse.swt.graphics.Device Device}
     */
    public DiagramExportConfig(final Rectangle2D diagramBounds, final Rectangle tileBounds,
            final double diagramScale, final Point dotsPerInch) {
        this.diagramBounds = diagramBounds;
        this.tileBounds = tileBounds;
        this.diagramScale = diagramScale;
        this.dotsPerInch = dotsPerInch;
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
     * @param exportBrandings
     *            the {@link IExportBranding IExportBranding} to be applied
     * @param diagramTrim
     *            the cumulated overall diagram trim required by the particular
     *            {@link IExportBranding IExportBranding}
     * @param tileTrim
     *            the cumulated diagram tile trim required by the particular
     *            {@link IExportBranding}s
     * @return this {@link DiagramExportConfig} for convenience
     */
    public DiagramExportConfig setBrandingsAndTrim(final Iterable<IExportBranding> exportBrandings,
            final Trim diagramTrim, final Trim tileTrim) {

        this.exportBrandings = exportBrandings != null
                ? exportBrandings : Collections.<IExportBranding>emptyList();
        this.diagramTrim = diagramTrim;
        this.tileTrim = tileTrim;

        return this;
    }

    /**
     * Combined setter of information concerning the page/tile numbering.<br>
     * {@link #firstRow} & {@link #firstColumn} are set if {@code rowNo} & {@code columnNo} are
     * equal to zero, {@link #lastRow} and {@link #lastColumn} are set {@code row == rows - 1} and
     * {@code column == columns - 1}, respectively.
     *
     * @param pageNo
     *            the current page number of the currently printed tile
     * @param row
     *            the row number of the currently printed tile (starting with zero)
     * @param column
     *            the column number of the currently printed tile (starting with zero)
     * @param rows
     *            number of tile rows
     * @param columns
     *            number of tile columns
     * @return this {@link DiagramExportConfig} for convenience
     */
    public DiagramExportConfig setPageAndTileNumbers(final int pageNo, final int row,
            final int column, final int rows, final int columns) {

        this.setPageAndTileNumbers(pageNo, row, column, row == rows - 1, column == columns - 1);

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
    public DiagramExportConfig setPageAndTileNumbers(final int pageNo, final int row,
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