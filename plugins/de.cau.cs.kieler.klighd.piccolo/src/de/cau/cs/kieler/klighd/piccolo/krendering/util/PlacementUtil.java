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

import de.cau.cs.kieler.core.krendering.KDecoratorPlacementData;
import de.cau.cs.kieler.core.krendering.KDirectPlacementData;
import de.cau.cs.kieler.core.krendering.KGridPlacement;
import de.cau.cs.kieler.core.krendering.KGridPlacementData;
import de.cau.cs.kieler.core.krendering.KLeftPosition;
import de.cau.cs.kieler.core.krendering.KPlacementData;
import de.cau.cs.kieler.core.krendering.KPolylinePlacementData;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KStackPlacementData;
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
            return placer;
        }
        
        // the following prepares the placer

        // determine the required number of columns and rows
        placer.numColumns = gridPlacement.getNumColumns() < 1 ? 1 : gridPlacement.getNumColumns();
        placer.numRows = (gpds.length - 1) / placer.numColumns + 1;

        // determine the column widths and row heights
        placer.fixedColumns = new boolean[placer.numColumns];
        placer.fixedRows = new boolean[placer.numRows];
        placer.colWidths = new float[placer.numColumns];
        Arrays.fill(placer.colWidths, 0);
        placer.rowHeights = new float[placer.numRows];
        Arrays.fill(placer.rowHeights, 0);
        for (int i = 0; i < gpds.length; ++i) {
            KGridPlacementData gpd = gpds[i];
            if (gpd == null) {
                continue;
            }
            
            int col = i % placer.numColumns;
            int row = i / placer.numColumns;
            
            // determine the maximum col width and row height
            float widthHint = gpd.getWidthHint() + gpd.getInsetLeft() + gpd.getInsetRight();
            float heightHint = gpd.getHeightHint() + gpd.getInsetTop() + gpd.getInsetBottom();
            if (widthHint > 0 && widthHint > placer.colWidths[col]) {
                placer.colWidths[col] = widthHint;
            }
            if (heightHint > 0 && heightHint > placer.rowHeights[row]) {
                placer.rowHeights[row] = heightHint;
            }
            
            // determine the cols/rows with variable width/height
            if (gpd.getWidthHint() > 0) {
                placer.fixedColumns[col] = true;
            }
            if (gpd.getHeightHint() > 0) {
                placer.fixedRows[row] = true;
            }
        }

        // calculate the total width and height hints
        for (int i = 0; i < placer.numColumns; ++i) {
            placer.totalWidth += placer.colWidths[i];
            placer.numVariableColumns += placer.fixedColumns[i] ? 0 : 1;
        }
        for (int i = 0; i < placer.numRows; ++i) {
            placer.totalHeight += placer.rowHeights[i];
            placer.numVariableRows += placer.fixedRows[i] ? 0 : 1;
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
        /** whether columns have fixed width. */
        private boolean[] fixedColumns;
        /** whether rows have fixed height. */
        private boolean[] fixedRows;
        /** the column widths. */
        private float[] colWidths;
        /** the row heights. */
        private float[] rowHeights;
        /** the total width hinted. */
        private float totalWidth = 0;
        /** the total height hinted.  */
        private float totalHeight = 0;
        /** the number of variable width columns. */
        private int numVariableColumns = 0;
        /** the number of variable height rows. */
        private int numVariableRows = 0;
        
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
            float width = (float) parentBounds.width;
            float height = (float) parentBounds.height;
            
            // calculate scaling and variable width/height per column/row
            float variableWidth;
            float variableHeight;
            float widthScale = 1;
            float heightScale = 1;
            if (totalWidth >= width) {
                variableWidth = 0;
                widthScale = width / totalWidth;
            } else {
                variableWidth = (width - totalWidth) / numVariableColumns;
            }
            if (totalHeight >= height) {
                variableHeight = 0;
                heightScale = height / totalHeight;
            } else {
                variableHeight = (height - totalHeight) / numVariableRows;
            }

            // create the bounds
            float currentX = 0;
            float currentY = 0;
            for (int i = 0; i < gpds.length; ++i) {
                KGridPlacementData gpd = gpds[i];
                int col = i % numColumns;
                int row = i / numColumns;
                if (col == 0) {
                    currentX = 0;
                }
                
                // determine insets and width/height hints
                float insetLeft = 0;
                float insetRight = 0;
                float insetTop = 0;
                float insetBottom = 0;
                float widthHint = 0;
                float heightHint = 0;
                if (gpd != null) {
                    insetLeft = gpd.getInsetLeft();
                    insetRight = gpd.getInsetRight();
                    insetTop = gpd.getInsetTop();
                    insetBottom = gpd.getInsetBottom();
                    widthHint = gpd.getWidthHint();
                    heightHint = gpd.getHeightHint();
                }
                
                // determine the elements bounds
                float elementX;
                float elementY;
                float elementWidth;
                float elementHeight;                  
                // determine x-coordinate and width
                if (fixedColumns[col]) {
                    // column has a fixed width
                    if (widthHint > 0) {
                        float inset =
                                (colWidths[col] - widthHint - insetLeft - insetRight) / 2.0f;
                        elementX = currentX + (inset + insetLeft) * widthScale;
                        elementWidth = widthHint * widthScale;
                    } else {
                        elementX = currentX + insetLeft * widthScale;
                        elementWidth = (colWidths[col] - insetLeft - insetRight) * widthScale;
                    }
                    currentX += colWidths[col] * widthScale;
                } else {
                    // column has no fixed width
                    float colWidth = colWidths[col] + variableWidth;
                    elementX = currentX + insetLeft * widthScale;
                    elementWidth = (colWidth - insetLeft - insetRight) * widthScale;
                    currentX += colWidth * widthScale;
                }

                // determine y-coordinate and height
                if (fixedRows[row]) {
                    // row has a fixed height
                    if (heightHint > 0) {
                        float inset =
                                (rowHeights[row] - heightHint - insetTop - insetBottom) / 2.0f;
                        elementY = currentY + (inset + insetTop) * heightScale;
                        elementHeight = heightHint * heightScale;
                    } else {
                        elementY = currentY + insetTop * heightScale;
                        elementHeight = (rowHeights[row] - insetTop - insetBottom) * heightScale;
                    }
                } else {
                    // row has no fixed height
                    float rowHeight = rowHeights[row] + variableHeight;
                    elementY = currentY + insetTop * heightScale;
                    elementHeight = (rowHeight - insetTop - insetBottom) * heightScale;
                }
                
                // create the bounds
                bounds[i] = new PBounds(elementX, elementY, elementWidth, elementHeight);
                
                // advance the current y-coordinate if necessary
                if (col == numColumns - 1) {
                    if (fixedRows[row]) {
                        currentY += rowHeights[row] * heightScale;
                    } else {
                        currentY += rowHeights[row] + variableHeight * heightScale;
                    }
                }
            }
            
            return bounds;
        }
        
    }

    /**
     * Returns the bounds for a stack placement data in given parent bounds.
     * 
     * @param spd
     *            the stack placement data
     * @param parentBounds
     *            the parent bounds
     * @return the bounds
     */
    public static PBounds evaluateStackPlacement(final KStackPlacementData spd,
            final PBounds parentBounds) {
        float width = (float) parentBounds.width;
        float height = (float) parentBounds.height;

        if (spd == null) {
            return new PBounds(0, 0, width, height);
        }

        return new PBounds(spd.getInsetLeft(), spd.getInsetTop(), width - spd.getInsetRight(),
                height - spd.getInsetBottom());
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
    public static Point2D[] evaluatePolylinePlacement(final KPolylinePlacementData ppd,
            final PBounds parentBounds) {
        if (ppd == null) {
            return new Point2D[] { new Point2D.Float(0, 0) };
        }

        // evaluate the points of the polyline inside the parent bounds
        Point2D[] points = new Point2D[ppd.getPoints().size()];
        int i = 0;
        for (KPosition point : ppd.getPoints()) {
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
        Point2D[] points = path.getShapePoints();
        
        // default case
        if (dpd == null) {
            decoration.origin = (Point2D) points[0].clone();
            decoration.bounds = new PBounds(0.0, 0.0, 0.0, 0.0);
            decoration.rotation = 0.0;
            return decoration;
        }
        
        // get the segment and concrete point the location specifies on the path
        Pair<Integer, Point2D> result =
                MathUtil.getSegmentStartIndexAndPoint(points, dpd.getLocation());
        decoration.origin = result.getSecond();

        // calculate the decorator bounds
        decoration.bounds =
                new PBounds(dpd.getXOffset(), dpd.getYOffset(), dpd.getWidth(), dpd.getHeight());

        // if the decorator placement data specifies it to be relative calculate its rotation
        if (dpd.isRelative() && points.length > 1) {
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
        } else {
            point.x = width - xPos.getAbsolute() - xPos.getRelative() * width;
        }
        if (yPos instanceof KTopPosition) {
            point.y = yPos.getAbsolute() + yPos.getRelative() * height;
        } else {
            point.y = height - yPos.getAbsolute() - yPos.getRelative() * height;
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
        } else if (data instanceof KPolylinePlacementData) {
            KPolylinePlacementData polylinePlacementData = (KPolylinePlacementData) data;
            return asGridPlacementData(polylinePlacementData.getDetailPlacementData());
        }
        return null;
    }

    /**
     * Returns the given placement data as stack placement data.
     * 
     * @param data
     *            the placement data
     * @return the stack placement data or null if the placement data is no stack placement data
     */
    public static KStackPlacementData asStackPlacementData(final KPlacementData data) {
        if (data instanceof KStackPlacementData) {
            return (KStackPlacementData) data;
        } else if (data instanceof KPolylinePlacementData) {
            KPolylinePlacementData polylinePlacementData = (KPolylinePlacementData) data;
            return asStackPlacementData(polylinePlacementData.getDetailPlacementData());
        }
        return null;
    }

    /**
     * Returns the given placement data as polyline placement data.
     * 
     * @param data
     *            the placement data
     * @return the polyline placement data or null if the placement data is no polyline placement
     *         data
     */
    public static KPolylinePlacementData asPolylinePlacementData(final KPlacementData data) {
        if (data instanceof KPolylinePlacementData) {
            return (KPolylinePlacementData) data;
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
        } else if (data instanceof KPolylinePlacementData) {
            KPolylinePlacementData polylinePlacementData = (KPolylinePlacementData) data;
            return asDecoratorPlacementData(polylinePlacementData.getDetailPlacementData());
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
