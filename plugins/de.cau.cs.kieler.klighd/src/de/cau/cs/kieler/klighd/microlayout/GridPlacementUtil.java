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

import static de.cau.cs.kieler.core.krendering.KRenderingUtil.asGridPlacement;
import static de.cau.cs.kieler.core.krendering.KRenderingUtil.asGridPlacementData;
import static de.cau.cs.kieler.core.krendering.KRenderingUtil.getPlacementData;
import static de.cau.cs.kieler.core.krendering.KRenderingUtil.toNonNullBottomPosition;
import static de.cau.cs.kieler.core.krendering.KRenderingUtil.toNonNullLeftPosition;
import static de.cau.cs.kieler.core.krendering.KRenderingUtil.toNonNullRightPosition;
import static de.cau.cs.kieler.core.krendering.KRenderingUtil.toNonNullTopPosition;
import static de.cau.cs.kieler.klighd.microlayout.PlacementUtil.CHILD_AREA_POSITION;
import static de.cau.cs.kieler.klighd.microlayout.PlacementUtil.ESTIMATED_GRID_DATA;
import static de.cau.cs.kieler.klighd.microlayout.PlacementUtil.estimateSize;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.krendering.KContainerRendering;
import de.cau.cs.kieler.core.krendering.KGridPlacement;
import de.cau.cs.kieler.core.krendering.KGridPlacementData;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KXPosition;
import de.cau.cs.kieler.core.krendering.KYPosition;
import de.cau.cs.kieler.core.util.Pair;

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
                final KXPosition x = toNonNullLeftPosition(topLeft.getX());
                final KYPosition y = toNonNullTopPosition(topLeft.getY());
                startX = parentBounds.width * x.getRelative() + x.getAbsolute();
                startY = parentBounds.height * y.getRelative() + y.getAbsolute();
            }
            
            float currentX = startX;
            float currentY = startY;
            
            for (int i = 0; i < children.size(); i++) {
                final KGridPlacementData gpd = asGridPlacementData(getPlacementData(children.get(i)));
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
                    insetLeft = cellWidth
                            - getHorizontalSizeAvailable(cellWidth, gpd.getTopLeft(), null);
                    insetTop = cellHeight
                            - getVerticalSizeAvailable(cellHeight, gpd.getTopLeft(), null);
                    insetRight = cellWidth
                            - getHorizontalSizeAvailable(cellWidth, null, gpd.getBottomRight());
                    insetBottom = cellHeight
                            - getVerticalSizeAvailable(cellHeight, null, gpd.getBottomRight());
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


    // TODO
    // the following 2 constants and 3 methods are to be replaced by existing code in PlacementUtil
    
    /**
     * whether a position is measured in the same direction as the point it is defining e.g. a top
     * left position is measured from left
     */
    private static final int PIMARY = 0;

    /**
     * whether a position is measured contrary to the point it is defining e.g. a top right position
     * is measured from left
     */
    private static final int SECONDARY = 1;

    /**
     * Calculate how much space of a parent is available to place children after considering by tL
     * and bR defined insets.
     * 
     * @param parentSize
     *            the size of the parent
     * @param tL
     *            the KPosition defining the top and Left insets
     * @param bR
     *            the KPosition defining the bottom and right insets
     * @return the size available to place children
     */
    // method is package protected in order to be used by GridPlacementUtil
    static float getHorizontalSizeAvailable(final float parentSize, final KPosition tL,
            final KPosition bR) {

        float abs0, abs1, rel0, rel1;
        int posId0, posId1;

        if (tL == null) {
            // emulate measuring from topLeft
            abs0 = 0;
            rel0 = 0;
            posId0 = PIMARY;
        } else {
            final KXPosition lPos = toNonNullLeftPosition(tL.getX());
            abs0 = lPos.getAbsolute();
            rel0 = lPos.getRelative();
            posId0 = lPos.eClass().getClassifierID() == KRenderingPackage.KLEFT_POSITION ? PIMARY
                    : SECONDARY;
        }

        if (bR == null) {
            // emulate measuring from bottomRight
            abs1 = 0;
            rel1 = 0;
            posId1 = PIMARY;
        } else {
            final KXPosition rPos = toNonNullRightPosition(bR.getX());
            abs1 = rPos.getAbsolute();
            rel1 = rPos.getRelative();
            posId1 = rPos.eClass().getClassifierID() == KRenderingPackage.KRIGHT_POSITION ? PIMARY
                    : SECONDARY;
        }

        return getSizeAvailable(parentSize, abs0, rel0, posId0, abs1, rel1, posId1);
    }

    /**
     * Calculate how much space of a parent is available to place children after considering by tL
     * and bR defined insets.
     * 
     * @param parentSize
     *            the size of the parent
     * @param tL
     *            the KPosition defining the top and Left insets
     * @param bR
     *            the KPosition defining the bottom and right insets
     * @return the size available to place children
     */
    // method is package protected in order to be used by GridPlacementUtil
    static float getVerticalSizeAvailable(final float parentSize, final KPosition tL,
            final KPosition bR) {

        float abs0, abs1, rel0, rel1;
        int posId0, posId1;

        if (tL == null) {
            // emulate measuring from topLeft
            abs0 = 0;
            rel0 = 0;
            posId0 = PIMARY;
        } else {
            final KYPosition tPos = toNonNullTopPosition(tL.getY());
            abs0 = tPos.getAbsolute();
            rel0 = tPos.getRelative();
            posId0 = tPos.eClass().getClassifierID() == KRenderingPackage.KTOP_POSITION ? PIMARY
                    : SECONDARY;
        }

        if (bR == null) {
            // emulate measuring from bottomRight
            abs1 = 0;
            rel1 = 0;
            posId1 = PIMARY;
        } else {
            final KYPosition bPos = toNonNullBottomPosition(bR.getY());
            abs1 = bPos.getAbsolute();
            rel1 = bPos.getRelative();
            posId1 = bPos.eClass().getClassifierID() == KRenderingPackage.KBOTTOM_POSITION ? PIMARY
                    : SECONDARY;
        }

        return getSizeAvailable(parentSize, abs0, rel0, posId0, abs1, rel1, posId1);
    }

    /**
     * Calculate how much space of a parent is available to place children after considering by
     * abs0, abs1, rel0 and rel1 defined defined insets.
     * 
     * @param parentSize
     *            the size of the parent
     * @param abs0
     *            the absolute value of the first inset
     * @param rel0
     *            the relative value of the first inset
     * @param positionId0
     *            the identifier constant informing about the measurement direction (DIRECT,
     *            INDIRECT)
     * @param abs1
     *            the absolute value of the second inset
     * @param rel1
     *            the relative value of the second inset
     * @param positionId1
     *            the identifier constant informing about the measurement direction (DIRECT,
     *            INDIRECT)
     * @return the size available to place children
     */
    private static float getSizeAvailable(final float parentSize, final float abs0,
            final float rel0, final int positionId0, final float abs1, final float rel1,
            final int positionId1) {

        Pair<Float, Float> normalizedInsets =
                PlacementUtil.getSize(abs0, rel0, positionId0, abs1, rel1, positionId1);

        float absOffset = normalizedInsets.getFirst();
        float relSize = normalizedInsets.getSecond();

        float elementWidth = parentSize * relSize - absOffset;

        if (elementWidth < 0) {
            // invalid insets
            return parentSize;
        } else {
            return elementWidth;
        }
    }

    
    /**
     * A data holder class for the spacing of the grid calculated during the size estimation. this
     * class is intended to be used in the above defined IProperty ESTIMATED_GRID_DATA to be
     * evaluated during the actual evaluation of parentBounds to place childElements
     * 
     * @author akoc
     * 
     */
    static class GridSpacing {
        private float[] calculatedColumnWidths;
        private float[] calculatedRowHeights;

        /**
         * 
         */
        public GridSpacing(final GridSpacing other) {
            this.calculatedColumnWidths = other.calculatedColumnWidths.clone();
            this.calculatedRowHeights = other.calculatedRowHeights.clone();
        }

        /**
         * Constructor. Sets the attributes according to given parameters
         * 
         * @param cols
         *            the column widths calculated during the size estimation
         * @param rows
         *            the row heights calculated during the size estimation
         */
        GridSpacing(final float[] cols, final float[] rows) {
            this.calculatedColumnWidths = cols;
            this.calculatedRowHeights = rows;
        }

        /**
         * Setter.
         *  
         * @param cols
         *            the column widths calculated during the size estimation
         * @param rows
         *            the row heights calculated during the size estimation
         */
        public void setCalculatedSizes(final float[] cols, final float[] rows) {
            this.calculatedColumnWidths = cols;
            this.calculatedRowHeights = rows;
        }
        /**
         * Getter.
         * 
         * @return the calculatedColumnWidths
         */
        public float[] getCalculatedColumnWidths() {
            return calculatedColumnWidths;
        }

        /**
         * Getter.
         * 
         * @return the calculatedRowHeights
         */
        public float[] getCalculatedRowHeights() {
            return calculatedRowHeights;
        }

        public String toString() {
            return "(Widths = " + Arrays.toString(this.calculatedColumnWidths) + ", Height = "
                    + Arrays.toString(this.calculatedRowHeights) + ")";
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
            final KRendering r = children.get(i);
            KGridPlacementData gpd = asGridPlacementData(getPlacementData(r));
            if (gpd == null) {
                //no grid placement data present, create dummy to prevent null pointer exceptions
                gpd = KRenderingFactory.eINSTANCE.createKGridPlacementData();
            }
            
            
            //determine size of the rendering to be able to size the colum/row accordingly
            
            final Bounds childMinSize = estimateSize(r, new Bounds(0, 0));
            
            int column = i % placer.numColumns;
            int row = i / placer.numColumns;

            PlacementUtil.inverselyApplyBoundingBoxKPositions(childMinSize, gpd.getTopLeft(),
                    gpd.getBottomRight());
            
            // determine the maximum of the minimum column widths and row heights
            // e.g. how big must a column be to fit all the minSizes
            placer.columnMaxMinWidth[column] = Math.max(
                    //size for this element is size of child elements or minSize
                    Math.max(childMinSize.width, placer.columnMaxMinWidth[column]),
                    gpd.getMinCellWidth());
            placer.rowMaxMinHeight[row] = Math.max(
                    Math.max(childMinSize.height, placer.rowMaxMinHeight[row]),
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
    
    /**
     * Returns the minimal size of a {@link KContainerRendering} with
     * <code>childPlacement instanceof KGridPlacement</code> and updates the placement data of
     * internal KRenderings if necessary.<br>
     * <br>
     * {@link de.cau.cs.kieler.core.properties.IProperty IProperties} for delivering those data.
     * 
     * @param container
     *            the {@link KContainerRendering} to be evaluated
     * @param parentBounds
     *            the size that is currently assigned to 'container'
     * @return the minimal size
     */
    public static Bounds estimateGridSize(final KContainerRendering container,
            final Bounds parentBounds) {
        int numColumns = ((KGridPlacement) container.getChildPlacement()).getNumColumns();
        final List<KRendering> childRenderings = container.getChildren();

        final int numRows;
        if (numColumns == -1) {
            numColumns = childRenderings.size();
            numRows = 1;
        } else if (numColumns < 2) {
            // if the number of columns is not set or is 1 then in each row is one rendering,
            // so numRows = number of elements to place
            numColumns = 1;
            numRows = childRenderings.size();
        } else {
            // else calculate number of rows based on number of child elements and columns
            numRows = (childRenderings.size() + numColumns - 1) / numColumns;
        }

        // initialize the minimal size for each row and column with zero
        final float[] minColumnWidths = new float[numColumns];
        final float[] minRowHeights = new float[numRows];

        // variables to later store the information where an optional childArea might be placed
        int childAreaRowId = -1;
        int childAreaColId = -1;

        // first evaluate the grid to get position and size of each cell
        // to make the layout as compact as possible but have space to place all children,
        // evaluate the space needed for each child
        for (int k = 0; k < childRenderings.size(); k++) {
            final KRendering currentChild = childRenderings.get(k);

            final int row = k / numColumns;
            final int col = k - row * numColumns;

            final LinkedList<KRendering> path = Lists.newLinkedList();
            if (PlacementUtil.findChildArea(currentChild, path)) {
                // if a childArea is contained in the current child (or the child itself is a childarea)
                // remember the position of the area to be able to calculate
                // the size of the parentNode correctly
                // later this is used to calculate insets based on the cellPosition of the childArea
                childAreaColId = col;
                childAreaRowId = row;
            }

            final Bounds cellSize = estimateSize(currentChild, new Bounds(0, 0));
            
            // evaluate the "winning" KGridPlacementData
            final KGridPlacementData gridData = asGridPlacementData(getPlacementData(currentChild));

            if (gridData != null) {
                final KPosition topLeft = gridData.getTopLeft();
                final KPosition bottomRight = gridData.getBottomRight();

                // apply the cell-local "passe-partout" inversely on the cell size
                PlacementUtil.inverselyApplyBoundingBoxKPositions(cellSize, topLeft, bottomRight);
                
                // apply the minimal width & definitions
                Bounds.max(cellSize, Bounds.of(gridData.getMinCellWidth(), gridData.getMinCellHeight()));
            }
            
            // compare the width and height of the current rendering with the biggest width
            // and height of the corresponding row and column and update the values with the maximum
            minRowHeights[row] = Math.max(minRowHeights[row], cellSize.height);
            minColumnWidths[col] = Math.max(minColumnWidths[col], cellSize.width);
        }

        // store the information of the size of the cells in the KRendering containing the grid
        //  in order to be able to re-use that information later
        // the re-usage of already present property objects is done to reduce the notification flood
        //  and the result re-composition of the node figures in the Piccolo binding

        final boolean deliver = container.eDeliver();
        container.eSetDeliver(false);
        
        final GridSpacing pSpacing = container.getProperty(ESTIMATED_GRID_DATA);
        if (pSpacing != null) {
            pSpacing.setCalculatedSizes(minColumnWidths, minRowHeights);
        } else {
            container.setProperty(ESTIMATED_GRID_DATA, new GridSpacing(minColumnWidths,
                    minRowHeights));
        }
        
        final Pair<Integer, Integer> pCAPos = container.getProperty(CHILD_AREA_POSITION);
        if (pCAPos != null) {
            pCAPos.setFirst(childAreaColId);
            pCAPos.setSecond(childAreaRowId);
        } else {
            container.setProperty(CHILD_AREA_POSITION, Pair.of(childAreaColId, childAreaRowId));
        }

        container.eSetDeliver(deliver);

        // the minimum total bound is the sum of the single column widths and row heights
        final Bounds childBounds = new Bounds(0, 0);

        for (float width : minColumnWidths) {
            childBounds.width += width;
        }
        for (float height : minRowHeights) {
            childBounds.height += height;
        }

        // take insets of the grid itself into consideration
        final KGridPlacement placement = asGridPlacement(container.getChildPlacement());
        
        // increase size according to the space needed by the insets
        PlacementUtil.inverselyApplyBoundingBoxKPositions(childBounds, placement.getTopLeft(),
                placement.getBottomRight());
        
        return childBounds;
    }

    // private Bounds determineGridBasedInsets(final Bounds minBounds) {
    //
    // //to make sure the calling function "createNode" is able to place the children correctly
    // //later, we have to transfer a detailed position of the childArea to it.
    // //so calculate the insets for the childArea, if there is one explicitly defined
    // float inset = 0.0f, secondInset = 0.0f;
    // if (childAreaColId >= 0 && childAreaRowId >= 0) {
    // // found a childArea earlier, calculate 'insets' based on position
    // // left inset is everything left of the childAreaCell
    // for (int left = 0; left < childAreaColId; left++) {
    // inset += minColumnWidths[left];
    // }
    //
    // //add insets defined in the GridPlacement itself
    // inset += minBounds.width - getHorizontalSizeAvailable(minBounds.width, topLeft, null);
    //
    // //add insets defined in the GridPlacementData of the cell the childArea is placed in
    // KGridPlacementData gridData = asGridPlacementData(
    // childRenderings.get(childAreaRowId * numColumns + childAreaColId)
    // .getPlacementData());
    // if (gridData != null) {
    // topLeft = gridData.getTopLeft();
    // inset += minBounds.width - getHorizontalSizeAvailable(minBounds.width, topLeft, null);
    // bottomRight = gridData.getBottomRight();
    // secondInset = minBounds.width
    // - getHorizontalSizeAvailable(minBounds.width, null, topLeft);
    // }
    //
    // childBounds.getInsets().setLeft(inset);
    // // right inset is fullWidth-insetLeft-(cellWidth of childArea - rightInset in that cell)
    // childBounds.getInsets().setRight(
    // childBounds.width - inset - (minColumnWidths[childAreaColId]) - secondInset);
    //
    // // reset for next calculation
    // inset = 0.0f;
    // // top inset is everything top of the childAreaCell
    // for (int top = 0; top < childAreaRowId; top++) {
    // inset += minRowHeights[top];
    // }
    //
    // //add insets defined in the GridPlacementData of the cell the childArea is placed in
    // inset += (minBounds.height - getVerticalSizeAvailable(minBounds.height, topLeft, null));
    //
    // if (gridData != null) {
    // bottomRight = gridData.getBottomRight();
    // secondInset = minBounds.height
    // - getVerticalSizeAvailable(minBounds.height, null, bottomRight);
    // }
    //
    // //add insets defined in the GridPlacement itself
    // topLeft = ((KGridPlacement) container.getChildPlacement()).getTopLeft();
    // inset += (minBounds.height - getVerticalSizeAvailable(minBounds.height, topLeft, null));
    //
    // childBounds.getInsets().setTop(inset);
    // // bottom inset is fullHeight-insetTop-childAreaHeight
    // childBounds.getInsets().setBottom(
    // childBounds.height - inset - (minRowHeights[childAreaRowId] - secondInset));
    // }
    //
    // // transport the inset-sums on each side through the position
    // minBounds.insets = childBounds.getInsets();
    // return minBounds;
    // }

}
