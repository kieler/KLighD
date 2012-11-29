/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.krendering.util;

import java.awt.geom.Point2D;
import java.util.Arrays;

import javax.swing.tree.VariableHeightLayoutCache;

import de.cau.cs.kieler.core.krendering.KBottomPosition;
import de.cau.cs.kieler.core.krendering.KDecoratorPlacementData;
import de.cau.cs.kieler.core.krendering.KDirectPlacementData;
import de.cau.cs.kieler.core.krendering.KGridPlacement;
import de.cau.cs.kieler.core.krendering.KGridPlacementData;
import de.cau.cs.kieler.core.krendering.KLeftPosition;
import de.cau.cs.kieler.core.krendering.KPlacementData;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRightPosition;
import de.cau.cs.kieler.core.krendering.KTopPosition;
import de.cau.cs.kieler.core.krendering.KXPosition;
import de.cau.cs.kieler.core.krendering.KYPosition;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klighd.piccolo.nodes.PSWTAdvancedPath;
import de.cau.cs.kieler.klighd.piccolo.util.MathUtil;
import edu.umd.cs.piccolo.util.PBounds;

/**
 * A utility class for evaluating the placement of KRenderings.
 * 
 * @author mri
 */
public final class PlacementUtil {

    /**
     * A private constructor to prevent instantiation.
     */
    private PlacementUtil() {
        // do nothing
    }

    /**
     * Returns the bounds for a direct placement data in given parent bounds.
     * 
     * @param dpd
     *            the direct placement data
     * @param parentBounds
     *            the parent bounds
     * @return the bounds
     */
    public static PBounds evaluateDirectPlacement(final KDirectPlacementData dpd,
            final PBounds parentBounds) {
        if (dpd == null) {
            return new PBounds(0, 0, parentBounds.width, parentBounds.height);
        }

        // determine the top-left
        KPosition topLeft = dpd.getTopLeft();
        Point2D topLeftPoint = evaluateDirectPosition(topLeft, parentBounds);

        // determine the bottom-right
        KPosition bottomRight = dpd.getBottomRight();
        Point2D bottomRightPoint = evaluateDirectPosition(bottomRight, parentBounds);

        return new PBounds(topLeftPoint.getX(), topLeftPoint.getY(), bottomRightPoint.getX()
                - topLeftPoint.getX(), bottomRightPoint.getY() - topLeftPoint.getY());
    }

    /**
     * Returns a placer for grid placement data in given grid placement.
     * 
     * @param gridPlacement
     *            the grid placement
     * @param gpds
     *            the grid placement datas
     * @return the bounds
     */
    public static GridPlacer evaluateGridPlacement(final KGridPlacement gridPlacement,
            final KGridPlacementData[] gpds) {
        GridPlacer placer = new GridPlacer();
        placer.gpds = gpds;

        if (gpds.length == 0) {
            // no elements to place
            return placer;
        }

        // the following prepares the placer

        // determine the required number of columns and rows
        placer.numColumns = gridPlacement.getNumColumns() < 1 ? 1 : gridPlacement.getNumColumns();
        placer.numRows = (gpds.length - 1) / placer.numColumns + 1;

        // determine the column widths and row heights
        // based on the minimum widths/height of cells in that column/row
        // regardless of conflicts with maximum widths/heights in the same column/row
        placer.columnHasMinWidth = new boolean[placer.numColumns];
        placer.columnHasMaxWidth = new boolean[placer.numColumns];
        placer.rowHasMinHeight = new boolean[placer.numRows];
        placer.rowHasMaxHeight = new boolean[placer.numRows];
        placer.columnMaxMinWidth = new float[placer.numColumns];
        placer.rowMaxMinHeight = new float[placer.numRows];
        placer.columnMinMaxWidth = new float[placer.numColumns];
        placer.rowMinMaxHeight = new float[placer.numRows];

        Arrays.fill(placer.columnHasMinWidth, false);
        Arrays.fill(placer.columnHasMaxWidth, false);
        Arrays.fill(placer.rowHasMinHeight, false);
        Arrays.fill(placer.rowHasMaxHeight, false);
        Arrays.fill(placer.columnMaxMinWidth, 0);
        Arrays.fill(placer.rowMaxMinHeight, 0);
        Arrays.fill(placer.columnMinMaxWidth, Float.MAX_VALUE);
        Arrays.fill(placer.rowMinMaxHeight, Float.MAX_VALUE);

        for (int i = 0; i < gpds.length; i++) {
            KGridPlacementData gpd = gpds[i];
            if (gpd == null) {
                continue;
            }

            int column = i % placer.numColumns;
            int row = i / placer.numColumns;

            // determine the maximum of the minimum column widths and row heights
            // e.g. how big must a column be to fit all the minSizes
            placer.columnMaxMinWidth[column] = Math.max(placer.columnMaxMinWidth[column],
                    gpd.getMinCellWidth());
            placer.rowMaxMinHeight[row] = Math.max(placer.rowMaxMinHeight[row],
                    gpd.getMinCellHeight());

            if (gpd.getMaxCellWidth() > placer.columnMaxMinWidth[column]) {
                // take it, if it is smaller than the current Maximum Size
                placer.columnMinMaxWidth[column] = Math.min(placer.columnMinMaxWidth[column],
                        gpd.getMaxCellWidth());
                placer.columnHasMaxWidth[column] = true;
            }
            if (gpd.getMaxCellHeight() > placer.rowMaxMinHeight[row]) {
                placer.rowMinMaxHeight[row] = Math.min(placer.rowMinMaxHeight[row],
                        gpd.getMaxCellHeight());
                placer.rowHasMaxHeight[row] = true;
            }

            // old code, MinWidth was used to describe the element width here, NOT the cellWidth
            // float relativeWidth = 1.0f - gpd.getTopLeft().getX().getRelative()
            // - gpd.getBottomRight().getX().getRelative();
            // float absoluteWidth = gpd.getMinCellWidth() // width of element
            // + gpd.getTopLeft().getX().getAbsolute()// insetLeft
            // + gpd.getBottomRight().getX().getAbsolute();// insetRight
            // float calculatedWidth = absoluteWidth;
            // if (relativeWidth > 0f) {
            // calculatedWidth = absoluteWidth / relativeWidth; // make cell bigger according to
            // // relative indents
            // }
            //
            // float relativeHeight = 1.0f - gpd.getTopLeft().getY().getRelative()
            // - gpd.getBottomRight().getY().getRelative();
            // float absoluteHeight = gpd.getMinCellHeight() + // height of element
            // gpd.getTopLeft().getY().getAbsolute() + // insetTop
            // gpd.getBottomRight().getY().getAbsolute(); // insetBottom
            // float calculatedHeight = absoluteHeight;
            // if (relativeHeight > 0f) {
            // calculatedHeight = absoluteHeight / relativeHeight; // make cell bigger according to
            // // relative indents
            // }
            //
            // if (calculatedWidth > placer.columnMinWidth[column]) {
            // placer.columnMinWidth[column] = calculatedWidth;
            // }
            // if (calculatedHeight > placer.rowMinHeight[row]) {
            // placer.rowMinHeight[row] = calculatedHeight;
            // }
            if (gpd.getMinCellWidth() > 0) {
                placer.columnHasMinWidth[column] = true;
            }
            if (gpd.getMinCellHeight() > 0) {
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
     * A helper class to calculate bounds for elements placed on a grid.
     */
    public static class GridPlacer {

        /** the associated grid placement data. */
        private KGridPlacementData[] gpds;
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

        /**
         * Evaluates the grid placement for the given parent bounds.
         * 
         * @param parentBounds
         *            the parent bounds
         * @return the bounds for the placed elements
         */
        public PBounds[] evaluate(final PBounds parentBounds) {
            if (gpds.length == 0) {
                return new PBounds[0];
            }

            PBounds[] bounds = new PBounds[gpds.length];
            float availableParentWidth = (float) parentBounds.width;
            float availableParentHeight = (float) parentBounds.height;

            // calculate scaling and variable width/height per column/row
            float widthScale = 1;
            // determine the width of columns
            // start by giving each column it's desired minimum width
            calculatedColumnWidth = columnMaxMinWidth;
            if (minTotalWidth > availableParentWidth) {
                // if that's already too big, adapt scale
                widthScale = availableParentWidth / minTotalWidth;
            } else {
                // space left to distribute
                float parentWidthToSpend = availableParentWidth - minTotalWidth;
                float distribute = parentWidthToSpend / numVariableColumnMinWidths;

                // first of all try to distribute the space to columns that have no minimum size
                for (int i = 0; i < numColumns; i++) {
                    if (calculatedColumnWidth[i] == 0) {
                        if (distribute < columnMinMaxWidth[i]) {
                            // give the col what it deserves
                            calculatedColumnWidth[i] = distribute;
                            parentWidthToSpend -= distribute;
                        } else {
                            // give the col what it wants max
                            calculatedColumnWidth[i] = columnMinMaxWidth[i];
                            parentWidthToSpend -= columnMinMaxWidth[i];
                        }
                    }
                }
                if (parentWidthToSpend > 0) {
                    // distribute rest-width to all columns that have no maxWidth defined
                    if (!(numVariableColumnMaxWidths == 0)) {
                        distribute = parentWidthToSpend / numVariableColumnMaxWidths;
                        for (int i = 0; i < numColumns; i++) {
                            if (!columnHasMaxWidth[i]) {
                                calculatedColumnWidth[i] += distribute;
                            }
                        }
                    } // else we could do fixpoint-iteration to distribute it to other elements
                }
            }

            // do the same routine for heights
            float heightScale = 1;
            // determine the height of rows
            // start by giving each row it's desired minimum height
            calculatedRowHeight = rowMaxMinHeight;
            if (minTotalHeight > availableParentHeight) {
                // if that's already too big, adapt scale
                heightScale = availableParentHeight / minTotalHeight;
            } else {
                // space left to distribute
                float parentHeightToSpend = availableParentHeight - minTotalHeight;
                float distribute = parentHeightToSpend / numVariableRowMinHeights;

                // first of all try to distribute the space to rows that have no minimum height
                for (int i = 0; i < numRows; i++) {
                    if (calculatedRowHeight[i] == 0) {
                        if (distribute < rowMinMaxHeight[i]) {
                            // give the col what it deserves
                            calculatedRowHeight[i] = distribute;
                            parentHeightToSpend -= distribute;
                        } else {
                            // give the col what it wants max
                            calculatedRowHeight[i] = rowMinMaxHeight[i];
                            parentHeightToSpend -= rowMinMaxHeight[i];
                        }
                    }
                }
                if (parentHeightToSpend > 0) {
                    // distribute rest-height to all rows that have no maxHeight defined
                    if (!(numVariableRowMaxHeights == 0)) {
                        distribute = parentHeightToSpend / numVariableRowMaxHeights;
                        for (int i = 0; i < numRows; i++) {
                            if (!rowHasMaxHeight[i]) {
                                calculatedRowHeight[i] += distribute;
                            }
                        }
                    } // else we could do fixpoint-iteration to distribute it to other elements
                }
            }

            // create the bounds
            float currentX = 0;
            float currentY = 0;
            for (int i = 0; i < gpds.length; i++) {
                KGridPlacementData gpd = gpds[i];
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
                    insetLeft = gpd.getTopLeft().getX().eClass().getClassifierID() == KRenderingPackage.KLEFT_POSITION ?
                    // left indent measured from left so just take it
                    gpd.getTopLeft().getX().getAbsolute()
                            + (gpd.getTopLeft().getX().getRelative() * cellWidth)
                            : // left indent measured from right, so calculate based on cellWidth
                            cellWidth - gpd.getTopLeft().getX().getAbsolute()
                                    - (gpd.getTopLeft().getX().getRelative() * cellWidth);
                    insetRight = gpd.getBottomRight().getX().eClass().getClassifierID() == KRenderingPackage.KRIGHT_POSITION ?
                    // right indent measured from right so just take it
                    gpd.getBottomRight().getX().getAbsolute()
                            + (gpd.getBottomRight().getX().getRelative() * cellWidth)
                            : // right indent measured from left, so calculate based on cellWidth
                            cellWidth - gpd.getBottomRight().getX().getAbsolute()
                                    - (gpd.getBottomRight().getX().getRelative() * cellWidth);
                    insetTop = gpd.getTopLeft().getY().eClass().getClassifierID() == KRenderingPackage.KTOP_POSITION ?
                    // top indent measured from top so just take it
                    gpd.getTopLeft().getY().getAbsolute()

                    + (gpd.getTopLeft().getY().getRelative() * cellHeight)
                            : // top indent measured from bottom so calculate based on cellHeight
                            cellHeight - gpd.getTopLeft().getY().getAbsolute()
                                    - (gpd.getTopLeft().getY().getRelative() * cellHeight);
                    insetBottom = gpd.getBottomRight().getY().eClass().getClassifierID() == KRenderingPackage.KBOTTOM_POSITION ?
                    // bottom indent measured from bottom so just take it
                    gpd.getBottomRight().getY().getAbsolute()
                            + (gpd.getBottomRight().getY().getRelative() * cellHeight)
                            : // bottom indent measured from top so calculate based on cellHeight
                            cellHeight - gpd.getBottomRight().getY().getAbsolute()
                                    - (gpd.getBottomRight().getY().getRelative() * cellHeight);
                }

                // determine the elements bounds
                float elementX = currentX + insetLeft * widthScale;
                float elementWidth = (cellWidth - insetLeft - insetRight) * widthScale;
                float elementY = currentY + insetTop * heightScale;
                float elementHeight = (cellHeight - insetTop - insetBottom) * heightScale;
                // set bounds
                bounds[i] = new PBounds(elementX, elementY, elementWidth, elementHeight);

                currentX += cellWidth * widthScale;
                
                // advance the current y-coordinate if necessary
                if (column == numColumns - 1) {
                    currentY += cellHeight*heightScale;
                    // new row = start from left.
                    currentX = 0;
                }
            }
            return bounds;
        }
    }

    /**
     * Returns the points for a polyline placement data in given parent bounds.
     * 
     * @param ppd
     *            the polyline placement data
     * @param parentBounds
     *            the parent bounds
     * @return the points
     */
    public static Point2D[] evaluatePolylinePlacement(final KPolyline line,
            final PBounds parentBounds) {
        if (line == null) {
            return new Point2D[] { new Point2D.Float(0, 0) };
        }

        // evaluate the points of the polyline inside the parent bounds
        Point2D[] points = new Point2D[line.getPoints().size()];
        int i = 0;
        for (KPosition point : line.getPoints()) {
            points[i++] = evaluateDirectPosition(point, parentBounds);
        }

        return points;
    }

    /**
     * Returns the bounds and rotation for a decorator placement data on a given path.
     * 
     * @param dpd
     *            the decorator placement data
     * @param path
     *            the path
     * @return the origin, bounds and rotation for the decorator
     */
    public static Decoration evaluateDecoratorPlacement(final KDecoratorPlacementData dpd,
            final PSWTAdvancedPath path) {
        Decoration decoration = new Decoration();

        Point2D[] points = ((PSWTAdvancedPath) path
                .getAttribute(PSWTAdvancedPath.APPROXIMATED_PATH)).getShapePoints();

        // default case
        if (dpd == null) {
            decoration.origin = (Point2D) points[0].clone();
            decoration.bounds = new PBounds(0.0, 0.0, 0.0, 0.0);
            decoration.rotation = 0.0;
            return decoration;
        }

        // get the segment and concrete point the location specifies on the path
        Pair<Integer, Point2D> result = MathUtil.getSegmentStartIndexAndPoint(points,
                dpd.getAbsolute());
        decoration.origin = result.getSecond();

        // calculate the decorator bounds
        decoration.bounds = new PBounds(dpd.getXOffset(), dpd.getYOffset(), dpd.getWidth(),
                dpd.getHeight());

        // if the decorator placement data specifies it to be relative calculate its rotation
        if (dpd.isRotateWithLine() && points.length > 1) {
            int segmentStart = result.getFirst();
            decoration.rotation = MathUtil.angle(points[segmentStart], points[segmentStart + 1]);
        } else {
            decoration.rotation = 0.0;
        }

        return decoration;
    }

    /**
     * Evaluates a position inside given parent bounds.
     * 
     * @param position
     *            the position
     * @param parentBounds
     *            the parent bounds
     * @return the evaluated position
     */
    public static Point2D.Float evaluateDirectPosition(final KPosition position,
            final PBounds parentBounds) {
        float width = (float) parentBounds.width;
        float height = (float) parentBounds.height;
        Point2D.Float point = new Point2D.Float();
        KXPosition xPos = position.getX();
        KYPosition yPos = position.getY();
        if (xPos instanceof KLeftPosition) {
            point.x = xPos.getAbsolute() + xPos.getRelative() * width;
        } else if (xPos instanceof KRightPosition) {
            point.x = width - xPos.getAbsolute() - xPos.getRelative() * width;
        } else { // SUPPRESS CHECKSTYLE EmptyBlock
            // this branch is reached in case xPos has been set to 'null', e.g. by EMF Compare
            // do nothing as the value will be re-set most certainly in near future :-)!
        }
        if (yPos instanceof KTopPosition) {
            point.y = yPos.getAbsolute() + yPos.getRelative() * height;
        } else if (yPos instanceof KBottomPosition) {
            point.y = height - yPos.getAbsolute() - yPos.getRelative() * height;
        } else { // SUPPRESS CHECKSTYLE EmptyBlock
            // this branch is reached in case yPos has been set to 'null', e.g. by EMF Compare
            // do nothing as the value will be re-set most certainly in near future :-)!
        }
        return point;
    }

    /**
     * Returns the given placement data as direct placement data.
     * 
     * @param data
     *            the placement data
     * @return the direct placement data or null if the placement data is no direct placement data
     */
    public static KDirectPlacementData asDirectPlacementData(final KPlacementData data) {
        if (data instanceof KDirectPlacementData) {
            return (KDirectPlacementData) data;
        }
        return null;
    }

    /**
     * Returns the given placement data as grid placement data.
     * 
     * @param data
     *            the placement data
     * @return the grid placement data or null if the placement data is no grid placement data
     */
    public static KGridPlacementData asGridPlacementData(final KPlacementData data) {
        if (data instanceof KGridPlacementData) {
            return (KGridPlacementData) data;
        }
        return null;
    }

    /**
     * Returns the given placement data as decorator placement data.
     * 
     * @param data
     *            the placement data
     * @return the decorator placement data or null if the placement data is no decorator placement
     *         data
     */
    public static KDecoratorPlacementData asDecoratorPlacementData(final KPlacementData data) {
        if (data instanceof KDecoratorPlacementData) {
            return (KDecoratorPlacementData) data;
        }
        return null;
    }

    /**
     * A data holder class for the result of evaluating a decorator.
     */
    public static class Decoration {

        /** the origin of the decoration. */
        private Point2D origin;
        /** the bounds of the decoration. */
        private PBounds bounds;
        /** the rotation of the decoration. */
        private double rotation;

        /**
         * Returns the origin of the decoration.
         * 
         * @return the origin
         */
        public final Point2D getOrigin() {
            return origin;
        }

        /**
         * Returns the bounds of the decoration.
         * 
         * @return the bounds
         */
        public final PBounds getBounds() {
            return bounds;
        }

        /**
         * Returns the rotation of the decoration.
         * 
         * @return the rotation
         */
        public final double getRotation() {
            return rotation;
        }

    }

}
