/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.microlayout;

import static de.cau.cs.kieler.core.krendering.KRenderingUtil.toNonNullLeftPosition;
import static de.cau.cs.kieler.core.krendering.KRenderingUtil.toNonNullTopPosition;
import static de.cau.cs.kieler.klighd.microlayout.PlacementUtil.CHILD_AREA_POSITION;
import static de.cau.cs.kieler.klighd.microlayout.PlacementUtil.ESTIMATED_GRID_DATA;
import static de.cau.cs.kieler.klighd.microlayout.PlacementUtil.asGridPlacementData;
import static de.cau.cs.kieler.klighd.microlayout.PlacementUtil.estimateSize;
import static de.cau.cs.kieler.klighd.microlayout.PlacementUtil.getHorizontalSizeAvailable;
import static de.cau.cs.kieler.klighd.microlayout.PlacementUtil.getVerticalSizeAvailable;

import java.util.Arrays;
import java.util.List;

import de.cau.cs.kieler.core.krendering.KGridPlacement;
import de.cau.cs.kieler.core.krendering.KGridPlacementData;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KXPosition;
import de.cau.cs.kieler.core.krendering.KYPosition;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil.GridSpacing;
// SUPPRESS CHECKSTYLE Import


/**
 * A utility class for evaluating the grid micro layout of KRenderings.
 * 
 * @author chsch, akoc
 */
public final class GridPlacementUtil {

    /**
     * Hidden standard constructor.
     */
    private GridPlacementUtil() {        
    }

    /**
     * A helper class to calculate bounds for elements placed on a grid.
     */
    public static class GridPlacer {

        private KRendering parent;
        /** the associated grid placement data. */
        private List<KRendering> children;
        /** the number of columns. */
        private int numColumns;
        /** the number of rows. */
        private int numRows;
        /** whether columns have minimum width. */
        private boolean[] columnHasMinWidth;
        /** whether rows have minimum height. */
        private boolean[] rowHasMinHeight;
        /** the column widths. */
        private float[] columnMaxMinWidth;
        /** the row heights. */
        private float[] rowMaxMinHeight;
        /** whether rows have a maximum width. */
        private boolean[] columnHasMaxWidth;
        /** whether rows have a maximum height. */
        private boolean[] rowHasMaxHeight;
        /** the column widths. */
        private float[] columnMinMaxWidth;
        /** the row heights. */
        private float[] rowMinMaxHeight;

        private float[] calculatedRowHeight;

        private float[] calculatedColumnWidth;
        /** the total width hinted. */
        private float minTotalWidth = 0;
        /** the total height hinted. */
        private float minTotalHeight = 0;
        /** the number of variable width columns. */
        private int numVariableColumnMinWidths = 0;
        /** the number of variable height rows. */
        private int numVariableRowMinHeights = 0;

        private int numVariableColumnMaxWidths = 0;
        private int numVariableRowMaxHeights = 0;
        
        private KPosition topLeft = null;
        private KPosition bottomRight = null;
        
        private static final float TOLERANCE = 0.1f;

        /**
         * Evaluates the grid placement for the given parent bounds.
         * 
         * @param parentBounds
         *            the parent bounds
         * @return the bounds for the placed elements
         */
        public Bounds[] evaluate(final Bounds parentBounds) {
            if (children.size() == 0) {
                return new Bounds[0];
            }

            final Bounds[] bounds = new Bounds[children.size()];
            float availableParentWidth = parentBounds.width;
            float availableParentHeight = parentBounds.height;

            // in case there is actually no space - skip the costly computations
            if (availableParentHeight == 0f || availableParentWidth == 0f) {
                Arrays.fill(bounds, new Bounds(0, 0));
                return bounds;
            }

            GridSpacing estimatedGrid = parent.getProperty(ESTIMATED_GRID_DATA);
            Pair<Integer, Integer> childAreaPosition = parent.getProperty(CHILD_AREA_POSITION);
            
            int width = 0, height = 0;
            float widthScale = 1, heightScale = 1;
            
            
            if (estimatedGrid != null) {
                //calculate the width that lead to the distribution before
                for (int i = 0; i < estimatedGrid.getCalculatedColumnWidths().length; i++) {
                    width += estimatedGrid.getCalculatedColumnWidths()[i];
                }
                for (int i = 0; i < estimatedGrid.getCalculatedRowHeights().length; i++) {
                    height += estimatedGrid.getCalculatedRowHeights()[i];
                }
            }

            //if the calculated width differs from the width the parent has, we have to put 
            //that additional width to use
            if (childAreaPosition != null
                    && childAreaPosition.getFirst() > -1
                    && childAreaPosition.getSecond() > -1) {
                //check if there is a childArea and put that width into it's cell

                calculatedColumnWidth = estimatedGrid.getCalculatedColumnWidths().clone();
                calculatedRowHeight = estimatedGrid.getCalculatedRowHeights().clone();
                
                if (parentBounds.width - width > TOLERANCE
                        && childAreaPosition.getFirst() < numColumns) {
                    calculatedColumnWidth[childAreaPosition.getFirst()] 
                            += parentBounds.width - width;
                }
                if (parentBounds.height - height > TOLERANCE
                        && childAreaPosition.getSecond() < numRows) {
                    calculatedRowHeight[childAreaPosition.getSecond()]
                            += parentBounds.height - height;
                }
                
            } else {
                // we have to calculate the data for the single rows / cols

                // calculate if scaling is necessary
                availableParentWidth = getHorizontalSizeAvailable(parentBounds.width, topLeft,
                        bottomRight);
                availableParentHeight = getVerticalSizeAvailable(parentBounds.height, topLeft,
                        bottomRight);

                Pair<Float, float[]> widthData = distributeParentSize(availableParentWidth,
                        minTotalWidth, columnMinMaxWidth, columnMaxMinWidth, 
                        columnHasMinWidth, columnHasMaxWidth,
                        numColumns, numVariableColumnMinWidths, numVariableColumnMaxWidths

                );
                widthScale = widthData.getFirst();
                calculatedColumnWidth = widthData.getSecond();

                Pair<Float, float[]> heightData = distributeParentSize(availableParentHeight,
                        minTotalHeight, rowMinMaxHeight, rowMaxMinHeight, 
                        rowHasMinHeight, rowHasMaxHeight, numRows,
                        numVariableRowMinHeights, numVariableRowMaxHeights

                );
                heightScale = heightData.getFirst();
                calculatedRowHeight = heightData.getSecond();
            }
            
            // variables that are later on used to define the bounds of single objects
            float startX = 0;
            float startY = 0;
            // take insets into consideration
            if (topLeft != null) {
                KXPosition x = toNonNullLeftPosition(topLeft.getX());
                KYPosition y = toNonNullTopPosition(topLeft.getY());
                startX = parentBounds.width * x.getRelative() + x.getAbsolute();
                startY = parentBounds.height * y.getRelative() + y.getAbsolute();
            }
            
            float currentX = startX;
            float currentY = startY;
            
            for (int i = 0; i < children.size(); i++) {
                KGridPlacementData gpd = asGridPlacementData(children.get(i).getPlacementData());
                int column = i % numColumns;
                int row = i / numColumns;

                float cellWidth = calculatedColumnWidth[column];
                float cellHeight = calculatedRowHeight[row];

                // determine insets and width/height hints
                float insetLeft = 0;
                float insetRight = 0;
                float insetTop = 0;
                float insetBottom = 0;
                
                if (gpd != null) {
                        insetLeft = cellWidth - getHorizontalSizeAvailable(cellWidth, 
                                gpd.getTopLeft(), null);
                        insetTop = cellHeight - getVerticalSizeAvailable(cellHeight, 
                                gpd.getTopLeft(), null);
                        insetRight = cellWidth - getHorizontalSizeAvailable(cellWidth, 
                                null, gpd.getBottomRight());
                        insetBottom = cellHeight - getVerticalSizeAvailable(cellHeight, 
                                null, gpd.getBottomRight());
                }

                // determine the elements bounds
                float elementX = currentX + insetLeft * widthScale;
                float elementWidth = (cellWidth - insetLeft - insetRight) * widthScale;
                float elementY = currentY + insetTop * heightScale;
                float elementHeight = (cellHeight - insetTop - insetBottom) * heightScale;
                bounds[i] = new Bounds(elementX, elementY, elementWidth, elementHeight);
                
                bounds[i].getInsets().setLeft(elementX);
                
                
                currentX += cellWidth * widthScale;

                // advance the current y-coordinate if necessary
                if (column == numColumns - 1) {
                    currentY += cellHeight * heightScale;
                    // new row => start from left.
                    currentX = startX;
                }
            }
            return bounds;
        }
    }
    
    /**
     * Returns the bounds for a gridPlacement data in given parent bounds.
     * 
     * @param gridPlacement
     *            the definition of the parent gridPlacement defining the column number
     * @param children
     *            the children to be placed inside the grid with their placement data
     * @param parentBounds
     *            the parent bounds
     * @return the bounds
     */
    public static Bounds[] evaluateGridPlacement(final KGridPlacement gridPlacement,
            final List<KRendering> children, final Bounds parentBounds) {
        GridPlacer placer = getGridPlacementObject(gridPlacement, children);
        return placer.evaluate(parentBounds);
    }
    
    // if the related grid placement data fields contain the following default definitions
    //  (obtained from the meta model)
    //  the given fields are interpreted as unspecified (absent) 
    private static final float DEFAULT_MIN_CELL_WIDTH = (Float) KRenderingPackage.eINSTANCE
            .getKGridPlacementData_MinCellWidth().getDefaultValue();
    private static final float DEFAULT_MIN_CELL_HEIGHT = (Float) KRenderingPackage.eINSTANCE
            .getKGridPlacementData_MinCellHeight().getDefaultValue();

    /**
     * returns a gridPlacer to calculate bounds of single elements inside the grid.
     * 
     * @param gridPlacement
     *            the grid data
     * @param children
     *            the children to be placed inside the grid with their placementData
     * @return the gridPlacer
     */
    public static GridPlacer getGridPlacementObject(final KGridPlacement gridPlacement,
            final List<KRendering> children) {
        GridPlacer placer = new GridPlacer();
        placer.parent = (KRendering) gridPlacement.eContainer();
        placer.children = children;

        if (children.size() == 0) {
            // no elements to place
            return placer;
        }

        // the following prepares the placer

        // determine the required number of columns and rows
        int setColumns = gridPlacement.getNumColumns();
        placer.topLeft = gridPlacement.getTopLeft();
        placer.bottomRight = gridPlacement.getBottomRight();
        placer.numColumns = setColumns == -1 ? children.size() : setColumns < 1 ? 1 : gridPlacement
                .getNumColumns();
        placer.numRows = (children.size() - 1) / placer.numColumns + 1;
        
        // determine the column widths and row heights
        // based on the minimum widths/height of cells in that column/row
        // regardless of conflicts with maximum widths/heights in the same column/row
        placer.columnHasMinWidth = new boolean[placer.numColumns];
        placer.columnHasMaxWidth = new boolean[placer.numColumns];
        placer.rowHasMinHeight = new boolean[placer.numRows];
        placer.rowHasMaxHeight = new boolean[placer.numRows];
        
        GridSpacing spacingProperty = placer.parent.getProperty(ESTIMATED_GRID_DATA);
        GridSpacing estimatedSpacing = spacingProperty == null ? null : new GridSpacing(spacingProperty);
        if (estimatedSpacing != null) {
            //take the data already calculated during the size estimation
            placer.calculatedColumnWidth = estimatedSpacing.getCalculatedColumnWidths().clone();
            placer.calculatedRowHeight = estimatedSpacing.getCalculatedRowHeights().clone();
        }
        if (placer.columnMaxMinWidth == null) {
            //start from scratch
            placer.columnMaxMinWidth = new float[placer.numColumns];
            Arrays.fill(placer.columnMaxMinWidth, 0);
        }
        if (placer.rowMaxMinHeight == null) {
            placer.rowMaxMinHeight = new float[placer.numRows];
            Arrays.fill(placer.rowMaxMinHeight, 0);
        }
        
        placer.columnMinMaxWidth = new float[placer.numColumns];
        placer.rowMinMaxHeight = new float[placer.numRows];
        Arrays.fill(placer.columnHasMinWidth, false);
        Arrays.fill(placer.rowHasMinHeight, false);
        Arrays.fill(placer.columnHasMaxWidth, false);
        Arrays.fill(placer.rowHasMaxHeight, false);
        Arrays.fill(placer.columnMinMaxWidth, Float.MAX_VALUE);
        Arrays.fill(placer.rowMinMaxHeight, Float.MAX_VALUE);

        for (int i = 0; i < children.size(); i++) {
            KRendering r = children.get(i);
            KGridPlacementData gpd = asGridPlacementData(r.getPlacementData());
            
            //determine size of the rendering to be able to size the colum/row accordingly
            
            final Bounds childMinSize = estimateSize(r, new Bounds(0, 0));
            
            if (gpd == null) {
                //no grid placement data present, create dummy to prevent null pointer exceptions
                gpd = KRenderingFactory.eINSTANCE.createKGridPlacementData();
            }

            int column = i % placer.numColumns;
            int row = i / placer.numColumns;

            final float adjWidth = PlacementUtil.calculateParentWidth(
                    childMinSize.width, gpd.getTopLeft(), gpd.getBottomRight());
            final float adjHeight = PlacementUtil.calculateParentHeight(
                    childMinSize.height, gpd.getTopLeft(), gpd.getBottomRight());
            
            // determine the maximum of the minimum column widths and row heights
            // e.g. how big must a column be to fit all the minSizes
            placer.columnMaxMinWidth[column] = Math.max(
                    //size for this element is size of child elements or minSize
                    Math.max(adjWidth, placer.columnMaxMinWidth[column]),
                    gpd.getMinCellWidth());
            placer.rowMaxMinHeight[row] = Math.max(
                    Math.max(adjHeight, placer.rowMaxMinHeight[row]),
                    gpd.getMinCellHeight());

            if (gpd.getMaxCellWidth() >= placer.columnMaxMinWidth[column]) {
                // We defined the "min width" to be stronger than the "max width" so  take the given max
                //  width only if it is bigger than the current maximal "min width" in the same column
                placer.columnMinMaxWidth[column] = Math.min(placer.columnMinMaxWidth[column],
                        gpd.getMaxCellWidth());
                placer.columnHasMaxWidth[column] = true;
                
                /*TODO: reset this if minimal width is set to a lower value? 
                question: reset it completely (no more max width in this column) 
                                or fall back to the next bigger max width (if any)?
                                maybe we should determine the minSizes first and 
                                then evaluating the maxSizes
                */
            }
            if (gpd.getMaxCellHeight() >= placer.rowMaxMinHeight[row]) {
                placer.rowMinMaxHeight[row] = Math.min(placer.rowMinMaxHeight[row],
                        gpd.getMaxCellHeight());
                placer.rowHasMaxHeight[row] = true;
            }

            if (gpd.getMinCellWidth() > DEFAULT_MIN_CELL_WIDTH) {
                placer.columnHasMinWidth[column] = true;
            }
            if (gpd.getMinCellHeight() > DEFAULT_MIN_CELL_HEIGHT) {
                placer.rowHasMinHeight[row] = true;
            }
        }

        // calculate the total width and height
        for (int i = 0; i < placer.numColumns; i++) {
            placer.minTotalWidth += placer.columnMaxMinWidth[i];
            placer.numVariableColumnMinWidths += placer.columnHasMinWidth[i] ? 0 : 1;
            placer.numVariableColumnMaxWidths += placer.columnHasMaxWidth[i] ? 0 : 1;
        }
        for (int i = 0; i < placer.numRows; i++) {
            placer.minTotalHeight += placer.rowMaxMinHeight[i];
            placer.numVariableRowMinHeights += placer.rowHasMinHeight[i] ? 0 : 1;
            placer.numVariableRowMaxHeights += placer.rowHasMaxHeight[i] ? 0 : 1;
        }
        return placer;
    }

    /**
     * Calculate how much space should be given to each element of a grid. 
     * An element can be a row or a column
     * @param parentSize the size of the parent to spend
     * @param minTotalSize the minimum total size needed by the elements
     * @param minMaxSize an array containing all the minimal maximum sizes of single elements
     * @param maxMinSize an array containing all the maximal minimum sizes of single elements 
     * @param elementHasMinSize an array informing about whether an element has a minimum size or not
     * @param elementHasMaxSize an array informing about whether an element has a maximum size or not
     * @param numElements the number of elements to be placed
     * @param numVariableElementMinSize the number of elements with variable minimum sizes
     * @param numVariableElementMaxSize the number of elements with variable maximim sizes
     * @return a pair containing a needed scaling-factor to place the grid, and the assigned widths of 
     *          the single element
     */
    public static Pair<Float, float[]> distributeParentSize(
            final float parentSize, final float minTotalSize, 
            final float[] minMaxSize, final float[] maxMinSize, 
            final boolean[] elementHasMinSize, final boolean[] elementHasMaxSize, 
            final int numElements, 
            final int numVariableElementMinSize, final int numVariableElementMaxSize
            ) {
        // calculate scaling and variable width/height per column/row
        float scale = 1;
        // determine the width of columns
        // start by giving each column it's desired minimum width
        float[] calculatedElementSize = maxMinSize.clone();
        if (minTotalSize > parentSize) {
            // if that's already too big, adapt scale
            scale = parentSize / minTotalSize;
        } else {
            // space left to distribute
            float parentSizeToSpend = parentSize - minTotalSize;
            float distribute = parentSizeToSpend / numVariableElementMinSize;

            // first of all try to distribute the space to columns that have no minimum size
            for (int i = 0; i < numElements; i++) {
                if (calculatedElementSize[i] == 0 
                        || calculatedElementSize[i] < distribute) {
                    if (distribute < minMaxSize[i]) {
                        // give the col what it deserves
                        // keep track of additionally spent space
                        parentSizeToSpend -= (distribute - calculatedElementSize[i]);
                        calculatedElementSize[i] = distribute;
                    } else {
                        // give the col what it wants max
                        parentSizeToSpend -= (minMaxSize[i] - calculatedElementSize[i]);
                        calculatedElementSize[i] = minMaxSize[i];
                    }
                }
            }
            if (parentSizeToSpend > 0) {
                // distribute rest-width to all columns that have no maxWidth defined
                if (numVariableElementMaxSize != 0) {
                    distribute = parentSizeToSpend / numVariableElementMaxSize;
                    for (int i = 0; i < numElements; i++) {
                        if (!elementHasMaxSize[i]) {
                            calculatedElementSize[i] += distribute;
                        }
                    }
                } else {
                    // we could do fixpoint-iteration here to distribute it to other elements, 
                    // but for now a single iteration has to be enough
                    distribute = parentSizeToSpend / numElements;
                    for (int i = 0; i < numElements; i++) {
                        if (calculatedElementSize[i] + distribute <= minMaxSize[i]) {
                            calculatedElementSize[i] += distribute;
                        } else {
                            calculatedElementSize[i] = minMaxSize[i];
                        }
                    }
                }
            }
        }
        return new Pair<Float, float[]>(scale, calculatedElementSize); 
    }
}
