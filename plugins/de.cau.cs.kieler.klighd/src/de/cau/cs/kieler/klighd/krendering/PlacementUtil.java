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
package de.cau.cs.kieler.klighd.krendering;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KChildArea;
import de.cau.cs.kieler.core.krendering.KContainerRendering;
import de.cau.cs.kieler.core.krendering.KDirectPlacementData;
import de.cau.cs.kieler.core.krendering.KFontBold;
import de.cau.cs.kieler.core.krendering.KFontItalic;
import de.cau.cs.kieler.core.krendering.KFontName;
import de.cau.cs.kieler.core.krendering.KFontSize;
import de.cau.cs.kieler.core.krendering.KGridPlacement;
import de.cau.cs.kieler.core.krendering.KGridPlacementData;
import de.cau.cs.kieler.core.krendering.KLeftPosition;
import de.cau.cs.kieler.core.krendering.KPlacement;
import de.cau.cs.kieler.core.krendering.KPlacementData;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KPolylinePlacementData;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KStackPlacement;
import de.cau.cs.kieler.core.krendering.KStackPlacementData;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KTopPosition;
import de.cau.cs.kieler.core.krendering.KXPosition;
import de.cau.cs.kieler.core.krendering.KYPosition;
import de.cau.cs.kieler.core.krendering.util.KRenderingSwitch;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.KlighdConstants;

/**
 * A utility class for evaluating the placement of KRenderings.
 * 
 * @author sgu, chsch
 */
public final class PlacementUtil {

    /**
     * Hidden default constructor.
     */
    private PlacementUtil() {
    }

    // CHECKSTYLEOFF Visibility

    /**
     * A data holder class for points. This class and its fields are intentionally package
     * protected.
     * 
     * @author mri, chsch
     */
    static class Point {

        /** the x-coordinate. */
        float x;
        /** the y-coordinate. */
        float y;

        /**
         * Constructs a point from the given coordinates.
         * 
         * @param x
         *            the x-coordinate
         * @param y
         *            the y-coordinate
         */
        public Point(final float x, final float y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Constructs a point according to the given SWT graphics point's coordinates.
         * 
         * @param point
         *            an SWT graphics point
         */
        public Point(final org.eclipse.swt.graphics.Point point) {
            this.x = point.x;
            this.y = point.y;
        }

        /**
         * Sets x and y equal to x and y of the given point.
         * 
         * @param point
         *            the point to take the coordinates from
         */
        public void setLocation(final Point point) {
            this.x = point.x;
            this.y = point.y;
        }
    }

    /**
     * A data holder class for bounds.
     */
    public static class Bounds {

        /** the x-coordinate. */
        float x;
        /** the y-coordinate. */
        float y;
        /** the width. */
        float width;
        /** the height. */
        float height;

        /**
         * Constructs bounds from the dimensions with coordinates (0,0).
         * 
         * @param width
         *            the width
         * @param height
         *            the height
         */
        public Bounds(final float width, final float height) {
            this.x = 0;
            this.y = 0;
            this.width = width;
            this.height = height;
        }

        /**
         * Constructs bounds from the dimensions of the given Bounds.
         * 
         * @param bounds
         *            the Bounds to take the data from
         */
        public Bounds(final Bounds bounds) {
            this.x = bounds.x;
            this.y = bounds.y;
            this.width = bounds.width;
            this.height = bounds.height;
        }

        /**
         * Constructs bounds from the dimensions of the given SWT Point with coordinates (0,0).
         * 
         * @param point
         *            the SWT point to take width and height from
         */
        public Bounds(final org.eclipse.swt.graphics.Point point) {
            this.width = point.x;
            this.height = point.y;
        }

        /**
         * Constructs bounds from the given coordinates and dimensions.
         * 
         * @param x
         *            the x-coordinate
         * @param y
         *            the y-coordinate
         * @param width
         *            the width
         * @param height
         *            the height
         */
        public Bounds(final float x, final float y, final float width, final float height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
        
        /**
         * Takes the data of the given bounds.
         * 
         * @param bounds
         *            the bounds to take the data from
         */
        public void setBounds(final Bounds bounds) {
            this.x = bounds.x;
            this.y = bounds.y;
            this.width = bounds.width;
            this.height = bounds.height;
        }
        
        /**
         * Getter used in JUnit test of the placement logic.
         * 
         * @return height
         */
        public float getHeight() {
            return height;
        }
        
        /**
         * Getter used in JUnit test of the placement logic.
         * 
         * @return width
         */
        public float getWidth() {
            return width;
        }
    }

    // CHECKSTYLEON Visibility

    private static final KRenderingPackage KRENDERING_PACKAGE = KRenderingPackage.eINSTANCE;

    
    /**
     * Returns the minimal size of a {@link KNode} based on the minimal size of contained
     * {@link KText KTexts} if present.
     * 
     * @param node the node to estimate the size for
     * @return the estimated size or (0, 0) if no text is contained. 
     */
    public static Bounds estimateSize(final KNode node) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        KRendering nodeRendering = node.getData(KRendering.class);
        
        if (nodeLayout != null && nodeRendering != null) {
            return estimateSize(nodeRendering,
                    new Bounds(nodeLayout.getWidth(), nodeLayout.getHeight()));
        } else {
            return new Bounds(0, 0);
        }
    }
    
    /**
     * Returns the minimal size of a KRendering and updates the placement data of internal
     * KRenderings if necessary (grid placement).
     * 
     * @param rendering
     *            the KRenderings to be evaluated
     * @param defBounds
     *            the size that would be given by default from the containing rendering
     * @return the minimal size
     */
    public static Bounds estimateSize(final KRendering rendering, final Bounds defBounds) {

        int id = KRENDERING_PACKAGE.getKText().isInstance(rendering) ? KRenderingPackage.KTEXT
                : (KRENDERING_PACKAGE.getKContainerRendering().isInstance(rendering)
                        ? KRenderingPackage.KCONTAINER_RENDERING : KRenderingPackage.KRENDERING);

        switch (id) {
        case KRenderingPackage.KTEXT:
            // for a text rendering just calculate the bounds of the text
            // and put the minimal size into 'minBounds'
            return estimateTextSize((KText) rendering);

        case KRenderingPackage.KCONTAINER_RENDERING:
            KContainerRendering container = (KContainerRendering) rendering;

            int placementId = container.getChildPlacement() != null ? container.getChildPlacement()
                    .eClass().getClassifierID() : -1;

            switch (placementId) {
            case KRenderingPackage.KGRID_PLACEMENT:
                // in case of a GridPlacement
                // calculate the number of columns and rows of the grid to the bounds of a inner
                // rendering
                return estimateGridSize(container, defBounds);

            case KRenderingPackage.KSTACK_PLACEMENT:
                // in case of a StackPlacement
                // find the biggest rendering in width and height
                return estimateStackSize(container, defBounds);

            default:
                // in case of a DirectPlacement
                // calculate the offsets of each rendering and find the biggest rendering in width
                // and height
                return estimateDirectSize(container, defBounds);
            }

        default:
            // there is something else than a text box or a container, e.q. a KPolyline
            // just taken the given 'defBounds' as minimal bounds as we consider only KTexts
            // as "atomic minimal bounds provider"
            return defBounds;
        }
    }

    /**
     * Returns the minimal bounds for a string.
     * 
     * @param kText
     *            the KText whose size are to be estimated.
     * @return the minimal bounds for the string
     */
    public static Bounds estimateTextSize(final KText kText) {
        
        Object testHeight = kText.getProperties().get(KlighdConstants.KLIGHD_TESTING_HEIGHT);
        Object testWidth = kText.getProperties().get(KlighdConstants.KLIGHD_TESTING_WIDTH);
        if (testHeight != null || testWidth != null) {
            // code for the regression tests
            //  (I don't trust in the different SWT implementations to
            //   provide the same size of a text on different platforms
            //   so given data are to be used)
            float height = testHeight != null ? Float.parseFloat(testHeight.toString()) : 0f;
            float width = testWidth != null ? Float.parseFloat(testWidth.toString()) : 0f;
            return new Bounds(width, height);
        }

        KFontName kFontName = IterableExtensions.head(Iterables.filter(kText.getStyles(),
                KFontName.class));
        String fontName = kFontName != null ? kFontName.getName()
                : KlighdConstants.DEFAULT_FONT_NAME;

        KFontSize kFontSize = IterableExtensions.head(Iterables.filter(kText.getStyles(),
                KFontSize.class));
        int fontSize = kFontSize != null ? kFontSize.getSize() : KlighdConstants.DEFAULT_FONT_SIZE;

        KFontBold kFontBold = IterableExtensions.head(Iterables.filter(kText.getStyles(),
                KFontBold.class));
        KFontItalic kFontItalic = IterableExtensions.head(Iterables.filter(kText.getStyles(),
                KFontItalic.class));
        int fontStyle = kFontBold != null && kFontBold.isBold() ? KlighdConstants.DEFAULT_FONT_STYLE_SWT
                | SWT.BOLD
                : KlighdConstants.DEFAULT_FONT_STYLE_SWT;
        fontStyle = kFontItalic != null && kFontItalic.isItalic() ? fontStyle | SWT.ITALIC
                : fontStyle;

        // In order to estimate the required size of a given string according to the determined
        // font,
        // style, and size a GC is instantiated, configured, and queried for each line of the text.
        // This code has basically taken from PSWTText and condensed.
        GC gc = new GC(Display.getDefault());
        gc.setAntialias(SWT.ON);
        gc.setFont(new Font(Display.getDefault(), fontName, fontSize, fontStyle));
        final FontMetrics fm = gc.getFontMetrics();

        Bounds textBounds = new Bounds(0, 0);

        if (kText == null || Strings.isNullOrEmpty(kText.getText())) {
            // if no text string is given, take the bounds of a space character,
            textBounds = new Bounds(gc.stringExtent(" "));
        } else {
            // else calculate the bounds (according to
            boolean firstLine = true;
            for (String line : KTextUtil.getTextLines(kText)) {
                Bounds lineBounds = new Bounds(gc.stringExtent(line));
                if (firstLine) {
                    textBounds.width = lineBounds.width;
                    textBounds.height += fm.getAscent() + fm.getDescent() + fm.getLeading();
                    firstLine = false;
                } else {
                    textBounds.width = Math.max(lineBounds.width, textBounds.width);
                    textBounds.height += fm.getHeight();
                }
            }
        }
        gc.dispose();

        // Add the default padding the concrete text size in order to
        // establish some margin space around the text.
        // The validity of this has still to be proved!!
        textBounds.width  += 2 * KlighdConstants.DEFAULT_TEXT_PADDING;
        textBounds.height += 2 * KlighdConstants.DEFAULT_TEXT_PADDING;

        return textBounds;
    }

    /**
     * 
     * @param container
     *            b
     * @param minBounds
     *            c
     * @return d
     */
    public static Bounds estimateGridSize(final KContainerRendering container, final Bounds minBounds) {

        int numColumns = ((KGridPlacement) container.getChildPlacement()).getNumColumns();
        List<KRendering> rens = container.getChildren();
        int numRows;
        if (numColumns < 2) {
            // if the number of columns is not set or is 1 then in each row is one rendering
            numColumns = 1;
            numRows = rens.size();

            // Chsch: This is OK for the moment. In future, however, the semantics of a value less
            // the 1 (or 0) might change
        } else {
            // else we have to round up the quotient of rendering and columns
            numRows = (rens.size() + numColumns - 1) / numColumns;
        }

        // initialize the minimal size for each row and column with zero
        float[] minColumnWidths = new float[numColumns];
        float[] minRowHeights = new float[numRows];
        for (int k = 0; k < rens.size(); k++) {
            // calculate the bounds of the inner rendering and updates its placement data if
            // it exceeds its size
            // TODO : check this
            Bounds childMinBounds = estimateSize(rens.get(k), new Bounds(minBounds.width / numColumns,
                    minBounds.height / numRows));
            KPlacementData plcData = rens.get(k).getPlacementData();
            KGridPlacementData gridData = null;

            if (plcData instanceof KGridPlacementData) {
                gridData = (KGridPlacementData) plcData;

                if (gridData.getWidthHint() < childMinBounds.width) {
                    gridData.setWidthHint(childMinBounds.width);
                } else {
                    childMinBounds.width = gridData.getWidthHint();
                }
                if (gridData.getHeightHint() < childMinBounds.height) {
                    gridData.setHeightHint(childMinBounds.height);
                } else {
                    childMinBounds.height = gridData.getHeightHint();
                }
            } else {
                // If there is another placement data than expected
                // we replace it with a grid placement data
                // Chsch: I'm a bit concerned about that ...
                if (((minBounds.width / numColumns) < childMinBounds.width)
                        || ((minBounds.height / numRows) < childMinBounds.height)) {

                    gridData = KRenderingFactory.eINSTANCE.createKGridPlacementData();
                    gridData.setWidthHint(childMinBounds.width);
                    gridData.setHeightHint(childMinBounds.height);
                    rens.get(k).setPlacementData(gridData);
                }
            }

            // compare the width and height of the current rendering with the biggest width
            // and height of the corresponding row and column and update the values with the
            // maximum
            int row = k / numColumns;
            minRowHeights[row] = Math.max(minRowHeights[row], childMinBounds.height);
            int colunm = k - row * numColumns;
            minColumnWidths[colunm] = Math.max(minColumnWidths[colunm], childMinBounds.width);
        }

        // the minimum total bound is the sum of the biggest renderings in height and width
        Bounds childBounds = new Bounds(0, 0);
        for (float width : minColumnWidths) {
            childBounds.width += width;
        }
        for (float height : minRowHeights) {
            childBounds.height += height;
        }

        // compare the minimal need size with the maximal yet found size ad
        // update the minimal size accordingly
//        minBounds.x = Math.max(minBounds.x, childBounds.x);
//        minBounds.y = Math.max(minBounds.y, childBounds.y);

//        return minBounds;
        return childBounds;
    }

    /**
     * 
     * @param container
     *            b
     * @param minBounds
     *            c
     * @return d
     */
    public static Bounds estimateStackSize(final KContainerRendering container, final Bounds minBounds) {

        for (KRendering rendering : container.getChildren()) {

            float insetWidth = 0;
            float insetHeight = 0;
            if (rendering.getPlacementData() instanceof KStackPlacementData) {
                KStackPlacementData plctData = (KStackPlacementData) rendering.getPlacementData();
                insetWidth = (plctData.getInsetRight() + plctData.getInsetLeft());
                insetHeight = (plctData.getInsetTop() + plctData.getInsetBottom());
            }
            Bounds childBounds = estimateSize(rendering, new Bounds(minBounds.width - insetWidth,
                    minBounds.height - insetHeight));

            minBounds.width = Math.max(minBounds.width, childBounds.width + insetWidth);
            minBounds.height = Math.max(minBounds.height, childBounds.height + insetHeight);
        }
        return minBounds;
    }

    /**
     * 
     * @param container
     *            b
     * @param defBounds
     *            a
     * @return d
     */
    public static Bounds estimateDirectSize(final KContainerRendering container,
            final Bounds defBounds) {
        final Bounds minBounds = new Bounds(defBounds);
        for (KRendering rendering : container.getChildren()) {
            KPlacementData plcData = rendering.getPlacementData();
            float absXOffest = 0;
            float relWidth = 1f;
            float absYOffest = 0;
            float relHeight = 1f;
            if (plcData instanceof KDirectPlacementData) {
                KDirectPlacementData dpd = (KDirectPlacementData) plcData;
                KPosition tL = dpd.getTopLeft();
                KPosition bR = dpd.getBottomRight();

                Pair<Float, Float> horSize = getHorizontalSize(tL, bR);
                absXOffest = horSize.getFirst();
                relWidth = horSize.getSecond();

                Pair<Float, Float> verSize = getVerticalSize(tL, bR);
                absYOffest = verSize.getFirst();
                relHeight = verSize.getSecond();

                final Bounds bounds = evaluateDirectPlacement(dpd, new Bounds(defBounds));

                // determine minimal needed size of the child
                Bounds childMinSize = estimateSize(rendering, minBounds);

                // compute child bounds, by scaling the default bounds with the relative
                // part and subtract the absolute value
                // Point childBounds = new Point(defBounds.x * relWidth - absXOffest, defBounds.y
                // * relHeight - absYOffest);

                if ((bounds.width < childMinSize.width) || (bounds.height < childMinSize.height)) {

                    // compare the minimal need size with the maximal yet found size ad
                    // update the minimal size accordingly
                    // relWidth and relHeight are now the total relative size of the
                    // childBounds according to the maximal yet found size
                    minBounds.width = Math.max(minBounds.width,
                            childMinSize.width / relWidth + Math.abs(absXOffest));
                    minBounds.height = Math.max(minBounds.height,
                            childMinSize.height / relHeight + Math.abs(absYOffest));
                }
            } else {
                // if there is no placement data then we can compare the child bounds
                // directly with the maximal needed bounds
                minBounds.setBounds(estimateSize(rendering, minBounds));
            }
        }
        return minBounds; // scaleBounds(container, minBounds, defBounds);
    }

    private static final int TOP_LEFT_OFFSET = 100;

    private static final int LEFT_LEFT = KRenderingPackage.KLEFT_POSITION * TOP_LEFT_OFFSET
            + KRenderingPackage.KLEFT_POSITION;

    private static final int RIGHT_RIGHT = KRenderingPackage.KRIGHT_POSITION * TOP_LEFT_OFFSET
            + KRenderingPackage.KRIGHT_POSITION;

    private static final int LEFT_RIGHT = KRenderingPackage.KLEFT_POSITION * TOP_LEFT_OFFSET
            + KRenderingPackage.KRIGHT_POSITION;

    private static final int RIGHT_LEFT = KRenderingPackage.KRIGHT_POSITION * TOP_LEFT_OFFSET
            + KRenderingPackage.KLEFT_POSITION;

    private static Pair<Float, Float> getHorizontalSize(final KPosition tL, final KPosition bR) {
        float absXOffest = 0;
        float relWidth = 1f;

        int position = bR.getX().eClass().getClassifierID() * TOP_LEFT_OFFSET
                + tL.getX().eClass().getClassifierID();

        switch (position) {
        case RIGHT_LEFT:
            // bottom right comes from right
            // top left comes from left
            relWidth = (1f - bR.getX().getRelative()) - (tL.getX().getRelative());
            absXOffest = bR.getX().getAbsolute() + tL.getX().getAbsolute();
            break;

        case LEFT_RIGHT:
            // bottom right comes from left
            // top left comes from right
            relWidth = bR.getX().getRelative() + tL.getX().getRelative() - 1f;
            absXOffest = -bR.getX().getAbsolute() - tL.getX().getAbsolute();
            break;

        case RIGHT_RIGHT:
            // bottom right comes from right
            // top left comes from right
            relWidth = tL.getX().getRelative() - bR.getX().getRelative();
            absXOffest = Math.round(bR.getX().getAbsolute() - tL.getX().getAbsolute());
            break;

        case LEFT_LEFT:
            // bottom right comes from left
            // top left comes from left
            relWidth = bR.getX().getRelative() - tL.getX().getRelative();
            absXOffest = Math.round(tL.getX().getAbsolute() - bR.getX().getAbsolute());
            break;

        default:
            // we don't know which position was taken
            // TODO ensure that all placement positions are guaranteed
            System.err.println("Found unknown placement position of x-axis!");
            break;
        }
        return new Pair<Float, Float>(absXOffest, relWidth);
    }

    private static final int TOP_TOP = KRenderingPackage.KTOP_POSITION * TOP_LEFT_OFFSET
            + KRenderingPackage.KTOP_POSITION;

    private static final int BOTTOM_BOTTOM = KRenderingPackage.KBOTTOM_POSITION * TOP_LEFT_OFFSET
            + KRenderingPackage.KBOTTOM_POSITION;

    private static final int TOP_BOTTOM = KRenderingPackage.KTOP_POSITION * TOP_LEFT_OFFSET
            + KRenderingPackage.KBOTTOM_POSITION;

    private static final int BOTTOM_TOP = KRenderingPackage.KBOTTOM_POSITION * TOP_LEFT_OFFSET
            + KRenderingPackage.KTOP_POSITION;

    private static Pair<Float, Float> getVerticalSize(final KPosition tL, final KPosition bR) {
        float absYOffest = 0;
        float relHeight = 1f;

        int position = bR.getY().eClass().getClassifierID() * TOP_LEFT_OFFSET
                + tL.getY().eClass().getClassifierID();

        switch (position) {
        case BOTTOM_TOP:
            // bottom right comes from bottom
            // top left comes from top
            relHeight = 1f - bR.getY().getRelative() - tL.getY().getRelative();
            absYOffest = bR.getY().getAbsolute() + tL.getY().getAbsolute();
            break;

        case TOP_BOTTOM:
            // bottom right comes from top
            // top left comes from bottom
            relHeight = bR.getY().getRelative() + tL.getY().getRelative() - 1f;
            absYOffest = -bR.getY().getAbsolute() - tL.getY().getAbsolute();
            break;

        case BOTTOM_BOTTOM:
            // bottom right comes from bottom
            // top left comes from bottom
            relHeight = tL.getY().getRelative() - bR.getY().getRelative();
            absYOffest = bR.getY().getAbsolute() - tL.getY().getAbsolute();
            break;

        case TOP_TOP:
            // bottom right comes from top
            // top left comes from top
            relHeight = bR.getY().getRelative() - tL.getY().getRelative();
            absYOffest = tL.getY().getAbsolute() - bR.getY().getAbsolute();
            break;

        default:
            // we don't know which position was taken
            // TODO ensure that all placement positions are guaranteed
            System.err.println("Found unknown placement position of y-axis!");
            break;
        }
        return new Pair<Float, Float>(absYOffest, relHeight);
    }

    private static final int SCALE_XY = 0;
    private static final int SCALE_X = 1;
    private static final int SCALE_Y = 2;
    private static final int SCALE_NONE = 3;

    private static Point scaleBounds(final KContainerRendering container, final Point minBounds,
            final Point defBounds) {

        // test if the minBounds exceeds the size of the defBounds and if so the absolute
        // offsets have to be upscaled by the minimal size
        float xScaling = (float) minBounds.x / (float) defBounds.x;
        float yScaling = (float) minBounds.y / (float) defBounds.y;
        boolean scaleX = 1f < xScaling;
        boolean scaleY = 1f < yScaling;

        // determine if the x and y offset have to be upscaled or just one of them
        // 0 scale x and y
        // 1 scale x
        // 2 (default) scale y
        // 3 neither x nor y
        int cas = (scaleX && scaleY) ? SCALE_XY : scaleX ? SCALE_X : scaleY ? SCALE_Y : SCALE_NONE;
        switch (cas) {
        case SCALE_XY:
            // scale the x and y offset
            for (KRendering rendering : container.getChildren()) {
                KPlacementData plcData = rendering.getPlacementData();
                if (plcData instanceof KDirectPlacementData) {
                    KPosition bR = ((KDirectPlacementData) plcData).getBottomRight();
                    KPosition tL = ((KDirectPlacementData) plcData).getTopLeft();

                    bR.getX().setAbsolute(bR.getX().getAbsolute() * xScaling);
                    tL.getX().setAbsolute(tL.getX().getAbsolute() * xScaling);
                    bR.getY().setAbsolute(bR.getY().getAbsolute() * yScaling);
                    tL.getY().setAbsolute(tL.getY().getAbsolute() * yScaling);

                    ((KDirectPlacementData) plcData).setBottomRight(bR);
                    ((KDirectPlacementData) plcData).setTopLeft(tL);
                    rendering.setPlacementData(plcData);
                }
            }
            break;

        case SCALE_X:
            // scale just the x offset
            for (KRendering rendering : container.getChildren()) {
                KPlacementData plcData = rendering.getPlacementData();
                if (plcData instanceof KDirectPlacementData) {
                    KPosition bR = ((KDirectPlacementData) plcData).getBottomRight();
                    KPosition tL = ((KDirectPlacementData) plcData).getTopLeft();

                    bR.getX().setAbsolute(bR.getX().getAbsolute() * xScaling);
                    tL.getX().setAbsolute(tL.getX().getAbsolute() * xScaling);

                    ((KDirectPlacementData) plcData).setBottomRight(bR);
                    ((KDirectPlacementData) plcData).setTopLeft(tL);
                    rendering.setPlacementData(plcData);
                }
            }
            break;

        case SCALE_Y:
            // scale just the y offset
            for (KRendering rendering : container.getChildren()) {
                KPlacementData plcData = rendering.getPlacementData();
                if (plcData instanceof KDirectPlacementData) {
                    KPosition bR = ((KDirectPlacementData) plcData).getBottomRight();
                    KPosition tL = ((KDirectPlacementData) plcData).getTopLeft();

                    bR.getY().setAbsolute(bR.getY().getAbsolute() * yScaling);
                    tL.getY().setAbsolute(tL.getY().getAbsolute() * yScaling);

                    ((KDirectPlacementData) plcData).setBottomRight(bR);
                    ((KDirectPlacementData) plcData).setTopLeft(tL);
                    rendering.setPlacementData(plcData);
                }
            }
            break;

        default:
            // the bound do not exceeds the default size -- nothing to do
            break;
        }
        return minBounds;
    }

    /**
     * Calculates the offset of the child area in the given node, which equals the insets of the
     * node.<br>
     * <br>
     * To ensure correct layout, changes in the bounds of the node are not allowed to influence the
     * insets. If this is not given the insets can be incorrect.
     * 
     * @param node
     *            the node
     * @param insets
     *            the insets
     */
    public static void calculateInsets(final KNode node, final KInsets insets) {
        // TODO take already set insets into consideration
        KRendering rendering = node.getData(KRendering.class);

        // no rendering so the whole node is the child area
        if (rendering == null) {
            return;
        }

        // find the path to the child area
        LinkedList<KRendering> path = Lists.newLinkedList();
        if (!findChildArea(rendering, path)) {
            // no child area so the whole node is the child area
            return;
        }

        // get the shape layout for the initial bounds of the reference parent
        KShapeLayout shapeLayout = node.getData(KShapeLayout.class);
        if (shapeLayout == null) {
            return;
        }

        // the data of the reference parent
        KContainerRendering currentParent = null;
        Bounds currentBounds = new Bounds(0.0f, 0.0f, shapeLayout.getWidth(),
                shapeLayout.getHeight());

        // the calculated insets
        float leftInset = 0.0f;
        float rightInset = 0.0f;
        float topInset = 0.0f;
        float bottomInset = 0.0f;

        while (!path.isEmpty()) {
            KRendering currentRendering = path.pollFirst();

            // calculate the bounds of the current rendering
            Bounds bounds;
            if (currentParent == null) {
                bounds = calculateBounds(null, currentBounds, null, currentRendering);
            } else {
                bounds = calculateBounds(currentParent.getChildPlacement(), currentBounds,
                        currentParent.getChildren(), currentRendering);
            }

            // update the insets using the new bounds
            leftInset += bounds.x;
            rightInset += currentBounds.width - bounds.x - bounds.width;
            topInset += bounds.y;
            bottomInset += currentBounds.height - bounds.y - bounds.height;

            // dereference all rendering references
            while (currentRendering instanceof KRenderingRef) {
                KRenderingRef renderingRef = (KRenderingRef) currentRendering;
                currentRendering = renderingRef.getRendering();
                path.removeFirst();
            }

            // if the rendering is a container rendering set the new current parent
            if (currentRendering instanceof KContainerRendering) {
                currentParent = (KContainerRendering) currentRendering;
                currentBounds = bounds;
            }
        }

        // set the insets
        insets.setLeft(leftInset);
        insets.setRight(rightInset);
        insets.setTop(topInset);
        insets.setBottom(bottomInset);
    }

    /**
     * Calculates the bounds of a child rendering inside a parent with given placement and bounds.
     * 
     * @param placement
     *            the placement
     * @param parentBounds
     *            the parent bounds
     * @param children
     *            the other children of the parent containing this rendering
     * @param child
     *            the child rendering
     * @return the bounds of the child rendering inside the parent rendering
     */
    private static Bounds calculateBounds(final KPlacement placement, final Bounds parentBounds,
            final List<KRendering> children, final KRendering child) {
        Bounds bounds = null;
        if (placement == null) {
            bounds = PlacementUtil.evaluateDirectPlacement(
                    asDirectPlacementData(child.getPlacementData()), parentBounds);
        } else {
            bounds = new KRenderingSwitch<Bounds>() {
                public Bounds caseKGridPlacement(final KGridPlacement gridPlacement) {
                    // TODO implement this
                    return new Bounds(0, 0, parentBounds.width, parentBounds.height);
                }

                public Bounds caseKStackPlacement(final KStackPlacement stackPlacement) {
                    return evaluateStackPlacement(asStackPlacementData(child.getPlacementData()),
                            parentBounds);
                }
            } /**/.doSwitch(placement);
        }

        if (child instanceof KPolyline) {
            return evaluatePolylinePlacement(asPolylinePlacementData(child.getPlacementData()),
                    bounds);
        } else {
            return bounds;
        }
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
    public static Bounds evaluateDirectPlacement(final KDirectPlacementData dpd,
            final Bounds parentBounds) {
        if (dpd == null) {
            return new Bounds(0, 0, parentBounds.width, parentBounds.height);
        }

        // determine the top-left
        KPosition topLeft = dpd.getTopLeft();
        Point topLeftPoint = evaluateDirectPosition(topLeft, parentBounds);

        // determine the bottom-right
        KPosition bottomRight = dpd.getBottomRight();
        Point bottomRightPoint = evaluateDirectPosition(bottomRight, parentBounds);

        return new Bounds(topLeftPoint.x, topLeftPoint.y, bottomRightPoint.x - topLeftPoint.x,
                bottomRightPoint.y - topLeftPoint.y);
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
    public static Bounds evaluateStackPlacement(final KStackPlacementData spd,
            final Bounds parentBounds) {
        float width = (float) parentBounds.width;
        float height = (float) parentBounds.height;

        if (spd == null) {
            return new Bounds(0, 0, width, height);
        }

        return new Bounds(spd.getInsetLeft(), spd.getInsetTop(), width - spd.getInsetRight(),
                height - spd.getInsetBottom());
    }

    /**
     * Returns the bounds for a polyline placement data in given parent bounds.
     * 
     * @param ppd
     *            the polyline placement data
     * @param parentBounds
     *            the parent bounds
     * @return the bounds
     */
    public static Bounds evaluatePolylinePlacement(final KPolylinePlacementData ppd,
            final Bounds parentBounds) {
        if (ppd == null || ppd.getPoints().size() == 0) {
            return new Bounds(0, 0, parentBounds.width, parentBounds.height);
        }

        // evaluate the points of the polyline inside the parent bounds to compute the bounding box
        float minX = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE;
        float minY = Float.MAX_VALUE;
        float maxY = Float.MIN_VALUE;
        for (KPosition polyPoint : ppd.getPoints()) {
            Point point = evaluateDirectPosition(polyPoint, parentBounds);
            if (point.x < minX) {
                minX = point.x;
            }
            if (point.x > maxX) {
                maxX = point.x;
            }
            if (point.y < minY) {
                minY = point.y;
            }
            if (point.y > maxY) {
                maxY = point.y;
            }
        }

        return new Bounds(minX, minY, parentBounds.width - maxX, parentBounds.height - maxY);
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
    public static Point evaluateDirectPosition(final KPosition position, final Bounds parentBounds) {
        float width = (float) parentBounds.width;
        float height = (float) parentBounds.height;
        Point point = new Point(0.0f, 0.0f);
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
     * Searches the rendering for a child area and records the path in the given list of renderings.
     * 
     * @param rendering
     *            the rendering
     * @param path
     *            the list of renderings which will contain the path of renderings to the child area
     * @return true if a child area has been found; false else
     */
    public static boolean findChildArea(final KRendering rendering,
            final LinkedList<KRendering> path) {
        path.addLast(rendering);
        if (rendering instanceof KChildArea) {
            return true;
        }
        if (rendering instanceof KContainerRendering) {
            KContainerRendering containerRendering = (KContainerRendering) rendering;
            for (KRendering childRendering : containerRendering.getChildren()) {
                if (findChildArea(childRendering, path)) {
                    return true;
                }
            }
        } else if (rendering instanceof KRenderingRef) {
            KRenderingRef renderingReference = (KRenderingRef) rendering;
            KRendering referencedRendering = renderingReference.getRendering();
            if (referencedRendering != null) {
                if (findChildArea(referencedRendering, path)) {
                    return true;
                }
            }
        }
        path.removeLast();
        return false;
    }
}
