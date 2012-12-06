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

import java.util.Arrays;
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

import de.cau.cs.kieler.core.kgraph.KLabel;
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
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KTopPosition;
import de.cau.cs.kieler.core.krendering.KXPosition;
import de.cau.cs.kieler.core.krendering.KYPosition;
import de.cau.cs.kieler.core.krendering.util.KRenderingSwitch;
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
         * Getter for X
         * 
         * @return height
         */
        public float getX() {
            return this.x;
        }

        /**
         * Getter for Y-coordinate.
         * 
         * @return width
         */
        public float getY() {
            return this.y;
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
    
    /**
     * Data container class that is convenient if a method shall return up to 3 results.
     * 
     * @author chsch
     *
     * @param <A> The return type of result a;
     * @param <B> The return type of result b;
     * @param <C> The return type of result c;
     */
    public static class Triple<A, B, C> {
        
        private A a;
        private B b;
        private C c;
        
        /**
         * Constructor.
         * 
         * @param theA The result a
         * @param theB The result b
         * @param theC The result c
         */
        public Triple(final A theA, final B theB, final C theC) {
            this.a = theA;
            this.b = theB;
            this.c = theC;
        }

        /**
         * @return the a
         */
        public A getA() {
            return this.a;
        }

        /**
         * @return the b
         */
        public B getB() {
            return this.b;
        }

        /**
         * @return the c
         */
        public C getC() {
            return this.c;
        }
        
        
    }

    // CHECKSTYLEON Visibility

    private static final KRenderingPackage KRENDERING_PACKAGE = KRenderingPackage.eINSTANCE;

    /**
     * Returns the minimal size of a {@link KNode} based on the minimal size of contained
     * {@link KText KTexts} if present.
     * 
     * @param node
     *            the node to estimate the size for
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
     * @param givenBounds
     *            the size that is given from the container rendering or the KShapeLayout
     *            respectively
     * @return the minimal size
     */
    public static Bounds estimateSize(final KRendering rendering, final Bounds givenBounds) {

        int id = KRENDERING_PACKAGE.getKText().isInstance(rendering) ? KRenderingPackage.KTEXT
                : (KRENDERING_PACKAGE.getKContainerRendering().isInstance(rendering)
                        ? KRenderingPackage.KCONTAINER_RENDERING
                                : KRENDERING_PACKAGE.getKChildArea().isInstance(rendering)
                                    ? KRenderingPackage.KCHILD_AREA : KRenderingPackage.KRENDERING);

        switch (id) {
        case KRenderingPackage.KTEXT:
            // for a text rendering just calculate the bounds of the text
            // and put the minimal size into 'minBounds'
            return estimateTextSize((KText) rendering);
        case KRenderingPackage.KCHILD_AREA:
            // KChildAreas do not cover any space that is not gathered by the (macro) layout
            return new Bounds(0, 0);

        case KRenderingPackage.KCONTAINER_RENDERING:
            KContainerRendering container = (KContainerRendering) rendering;

            int placementId = container.getChildPlacement() != null ? container.getChildPlacement()
                    .eClass().getClassifierID() : -1;

            switch (placementId) {
            case KRenderingPackage.KGRID_PLACEMENT:
                // in case of a GridPlacement
                // calculate the number of columns and rows of the grid to the bounds of a inner
                // rendering
                return estimateGridSize(container, givenBounds);
            default:
                // in case of a DirectPlacement
                // calculate the offsets of each rendering and find the biggest rendering in width
                // and height
                return estimateDirectSize(container, givenBounds);
            }

        default:
            // there is something else than a text box or a container, e.q. a KPolyline
            // just taken the given 'defBounds' as minimal bounds as we consider only KTexts
            // as "atomic minimal bounds provider"
            return givenBounds;
        }
    }

    /**
     * Returns the minimal bounds for a KText.
     * 
     * @param kText
     *            the KText containing the text string whose size is to be estimated.
     * @return the minimal bounds for the {@link KText}
     */
    public static Bounds estimateTextSize(final KText kText) {
        return estimateTextSize(kText, kText.getText());
    }

    /**
     * Returns the minimal bounds for a KLabel. Font configurations like font name, size, and style
     * may be provided via an attached {@link KText} rendering.
     * 
     * @param kLabel
     *            the {@link KLabel} whose size is to be estimated.
     * @return the minimal bounds for the {@link KLabel}
     */
    public static Bounds estimateTextSize(final KLabel kLabel) {
        return estimateTextSize(kLabel.getData(KText.class), kLabel.getText());
    }

    /**
     * Returns the minimal bounds for a string based on configurations of a {@link KText}. The
     * string is handed over separately in order to allow the text size estimation for
     * {@link KLabel KLabels}, whose text string is given outside of the {@link KText} rendering.
     * 
     * @param kText
     *            the KText providing font configurations like font name, size, and style; maybe
     *            <code>null</code>
     * @param text
     *            the actual text string whose size is to be estimated; maybe <code>null</code>
     * @return the minimal bounds for the string
     */
    public static Bounds estimateTextSize(final KText kText, final String text) {

        KFontName kFontName = null;
        KFontSize kFontSize = null;
        KFontBold kFontBold = null;
        KFontItalic kFontItalic = null;

        if (kText != null) {
            Object testHeight = kText.getProperties().get(KlighdConstants.KLIGHD_TESTING_HEIGHT);
            Object testWidth = kText.getProperties().get(KlighdConstants.KLIGHD_TESTING_WIDTH);
            if (testHeight != null || testWidth != null) {
                // code for the regression tests
                // (I don't trust in the different SWT implementations to
                // provide the same size of a text on different platforms
                // so given data are to be used)
                float height = testHeight != null ? Float.parseFloat(testHeight.toString()) : 0f;
                float width = testWidth != null ? Float.parseFloat(testWidth.toString()) : 0f;
                if (height != 0f || width != 0f) {
                    return new Bounds(width, height);
                }
            }
            kFontName = IterableExtensions
                    .head(Iterables.filter(kText.getStyles(), KFontName.class));

            kFontSize = IterableExtensions
                    .head(Iterables.filter(kText.getStyles(), KFontSize.class));

            kFontBold = IterableExtensions
                    .head(Iterables.filter(kText.getStyles(), KFontBold.class));
            kFontItalic = IterableExtensions.head(Iterables.filter(kText.getStyles(),
                    KFontItalic.class));
        }

        String fontName = kFontName != null ? kFontName.getName()
                : KlighdConstants.DEFAULT_FONT_NAME;

        int fontSize = kFontSize != null ? kFontSize.getSize() : KlighdConstants.DEFAULT_FONT_SIZE;

        int fontStyle = kFontBold != null && kFontBold.isBold() ? KlighdConstants.DEFAULT_FONT_STYLE_SWT
                | SWT.BOLD
                : KlighdConstants.DEFAULT_FONT_STYLE_SWT;
        fontStyle = kFontItalic != null && kFontItalic.isItalic() ? fontStyle | SWT.ITALIC
                : fontStyle;

        // In order to estimate the required size of a given string according to the determined
        // font, style, and size a GC is instantiated, configured, and queried for each line of the
        // text. This code has basically taken from PSWTText and condensed.
        final GC gc = new GC(Display.getDefault());
        gc.setAntialias(SWT.ON);
        gc.setFont(new Font(Display.getDefault(), fontName, fontSize, fontStyle));
        final FontMetrics fm = gc.getFontMetrics();

        Bounds textBounds = new Bounds(0, 0);

        if (Strings.isNullOrEmpty(text)) {
            // if no text string is given, take the bounds of a space character,
            textBounds = new Bounds(gc.stringExtent(" "));
        } else {
            // else calculate the bounds (according to
            boolean firstLine = true;
            for (String line : KTextUtil.getTextLines(text)) {
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
        textBounds.width += 2 * KlighdConstants.DEFAULT_TEXT_PADDING;
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
    public static Bounds estimateGridSize(final KContainerRendering container,
            final Bounds minBounds) {

        int numColumns = ((KGridPlacement) container.getChildPlacement()).getNumColumns();
        List<KRendering> rens = container.getChildren();
        int numRows;
        if (numColumns < 2) {
            // if the number of columns is not set or is 1 then in each row is one rendering
            numColumns = 1;
            numRows = rens.size();

            // Chsch: This is OK for the moment. In future, however, the semantics of a value less
            // than 1 (or 0) might change
        } else {
            // else we have to round up the quotient of rendering and columns
            numRows = (rens.size() + numColumns - 1) / numColumns;
        }

        // initialize the minimal size for each row and column with zero
        float[] minColumnWidths = new float[numColumns];
        float[] minRowHeights = new float[numRows];
        for (int k = 0; k < rens.size(); k++) {
            // calculate the bounds of the inner rendering and update its placement data if
            // it exceeds its size
            // TODO : check this
            Bounds childMinBounds = estimateSize(rens.get(k), new Bounds(minBounds.width
                    / numColumns, minBounds.height / numRows));
            KPlacementData plcData = rens.get(k).getPlacementData();
            KGridPlacementData gridData = null;

            // if (plcData instanceof KGridPlacementData) {
            // gridData = (KGridPlacementData) plcData;
            //
            // if (gridData.getMinCellWidth() < childMinBounds.width) {
            // gridData.setMinCellWidth(childMinBounds.width);
            // } else {
            // childMinBounds.width = gridData.getMinCellWidth();
            // }
            // if (gridData.getMinCellHeight() < childMinBounds.height) {
            // gridData.setMinCellHeight(childMinBounds.height);
            // } else {
            // childMinBounds.height = gridData.getMinCellHeight();
            // }
            // } else {
            // // If there is another placement data than expected
            // // we replace it with a grid placement data
            // // Chsch: I'm a bit concerned about that ...
            // if (((minBounds.width / numColumns) < childMinBounds.width)
            // || ((minBounds.height / numRows) < childMinBounds.height)) {
            //
            // gridData = KRenderingFactory.eINSTANCE.createKGridPlacementData();
            // gridData.setMinCellWidth(childMinBounds.width);
            // gridData.setMinCellHeight(childMinBounds.height);
            // rens.get(k).setPlacementData(gridData);
            // }
            // }

            // compare the width and height of the current rendering with the biggest width
            // and height of the corresponding row and column and update the values with the
            // maximum
            int row = k / numColumns;
            minRowHeights[row] = Math.max(minRowHeights[row], childMinBounds.height);
            int colunm = k - row * numColumns;
            minColumnWidths[colunm] = Math.max(minColumnWidths[colunm], childMinBounds.width);
        }

        // the minimum total bound is the sum of the biggest renderings in height and width
        // Bounds childBounds = new Bounds(0, 0);
        // for (float width : minColumnWidths) {
        // childBounds.width += width;
        // }
        // for (float height : minRowHeights) {
        // childBounds.height += height;
        // }

        // compare the minimal need size with the maximal yet found size and
        // update the minimal size accordingly
        // minBounds.x = Math.max(minBounds.x, childBounds.x);
        // minBounds.y = Math.max(minBounds.y, childBounds.y);

        return minBounds;
        // return childBounds;
    }

    /**
     * 
     * @param container
     *            b
     * @param minBounds
     *            c
     * @return d
     */
    public static Bounds estimateStackSize(final KContainerRendering container,
            final Bounds minBounds) {

        for (KRendering rendering : container.getChildren()) {

            float insetWidth = 0;
            float insetHeight = 0;
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
     * @param givenBounds
     *            a
     * @return d
     */
    public static Bounds estimateDirectSize(final KContainerRendering container,
            final Bounds givenBounds) {
        final Bounds minBounds = new Bounds(givenBounds);        
        for (KRendering rendering : container.getChildren()) {
            estimateDirectlyPlacedChildSize(
                    rendering, minBounds,
                    evaluateDirectPlacement(asDirectPlacementData(rendering.getPlacementData()),
                            givenBounds));
        }
        return minBounds;
    }
    
    /**
     * 
     * @param rendering a
     * @param minBounds c
     * @param givenBounds b
     * @return d
     */
    public static Bounds estimateDirectlyPlacedChildSize(final KRendering rendering,
            final Bounds minBounds, final Bounds givenBounds) {
            
            float absXOffest = 0;
            float relWidth = 1f;
            float absYOffest = 0;
            float relHeight = 1f;
            
            KPlacementData plcData = rendering.getPlacementData();
            if (plcData instanceof KDirectPlacementData) {
                KDirectPlacementData dpd = (KDirectPlacementData) plcData;

                // determine minimal needed size of the child
                final Bounds childMinSize = estimateSize(rendering, givenBounds);
                // determine the being assigned wrt. the 
                final Bounds bounds = evaluateDirectPlacement(dpd, givenBounds);

                if ((bounds.width < childMinSize.width)
                        || (bounds.height < childMinSize.height)
                        || (givenBounds.width < childMinSize.width)
                        || (givenBounds.height < childMinSize.height)
                        || (givenBounds.width  < bounds.width)
                        || (givenBounds.height < bounds.height)
                        ) {

                    KPosition tL = dpd.getTopLeft();
                    KPosition bR = dpd.getBottomRight();

                    Triple<Float, Float, Boolean> horSize = getHorizontalSize(tL, bR);
                    absXOffest = horSize.getA();
                    relWidth = horSize.getB() != 0f ? horSize.getB() : 1f;
                    @SuppressWarnings("unused")
                    boolean absoluteWidth = horSize.getC();
                    // the idea of that variable is to provide an information whether the size of
                    //  figure is influenced by the size of the parent or whether it's fully determined
                    //  by the absolute positioning components; need thinking on that further
                    
                    Triple<Float, Float, Boolean> verSize = getVerticalSize(tL, bR);
                    absYOffest = verSize.getA();
                    relHeight = verSize.getB() != 0f ? verSize.getB() : 1f;
                    
                    // compare the minimal need size with the maximal yet found size ad
                    // update the minimal size accordingly
                    // relWidth and relHeight are now the total relative size of the
                    // childBounds according to the maximal yet found size
                    minBounds.width = Math.max(minBounds.width, childMinSize.width / relWidth
                            + Math.abs(absXOffest));
                    minBounds.height = Math.max(minBounds.height, childMinSize.height / relHeight
                            + Math.abs(absYOffest));
                }
            } else {
                // if there is no placement data then we can compare the child bounds
                // directly with the maximal needed bounds
                minBounds.setBounds(estimateSize(rendering, minBounds));
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

    private static Triple<Float, Float, Boolean> getHorizontalSize(final KPosition tL,
            final KPosition bR) {
        float absXOffest = 0;
        float relWidth = 1f;
        boolean absoluteLength = false;
         // the idea of that variable is to provide an information whether the size of
         //  figure is influenced by the size of the parent or whether it's fully determined
         //  by the absolute positioning components; need thinking on that further

        int position = bR.getX().eClass().getClassifierID() * TOP_LEFT_OFFSET
                + tL.getX().eClass().getClassifierID();

        switch (position) {
        case RIGHT_LEFT:
            // bottom right comes from right
            // top left comes from left
            relWidth = 1f - bR.getX().getRelative() - (tL.getX().getRelative());
            absXOffset = bR.getX().getAbsolute() + tL.getX().getAbsolute();
            break;

        case LEFT_RIGHT:
            // bottom right comes from left
            // top left comes from right
            relWidth = bR.getX().getRelative() + tL.getX().getRelative() - 1f;
            absXOffset = -bR.getX().getAbsolute() - tL.getX().getAbsolute();
            break;

        case RIGHT_RIGHT:
            // bottom right comes from right
            // top left comes from right
            relWidth = tL.getX().getRelative() - bR.getX().getRelative();
            absXOffest = Math.min(bR.getX().getAbsolute(), tL.getX().getAbsolute());
            break;

        case LEFT_LEFT:
            // bottom right comes from left
            // top left comes from left
            relWidth = bR.getX().getRelative() - tL.getX().getRelative();
            absXOffest = Math.min(tL.getX().getAbsolute(), bR.getX().getAbsolute());
            break;

        default:
            // we don't know which position was taken
            // TODO ensure that all placement positions are guaranteed
            System.err.println("Found unknown placement position of x-axis!");
            break;
        }
        return new Triple<Float, Float, Boolean>(absXOffest, relWidth, absoluteLength);
    }

    private static final int TOP_TOP = KRenderingPackage.KTOP_POSITION * TOP_LEFT_OFFSET
            + KRenderingPackage.KTOP_POSITION;

    private static final int BOTTOM_BOTTOM = KRenderingPackage.KBOTTOM_POSITION * TOP_LEFT_OFFSET
            + KRenderingPackage.KBOTTOM_POSITION;

    private static final int TOP_BOTTOM = KRenderingPackage.KTOP_POSITION * TOP_LEFT_OFFSET
            + KRenderingPackage.KBOTTOM_POSITION;

    private static final int BOTTOM_TOP = KRenderingPackage.KBOTTOM_POSITION * TOP_LEFT_OFFSET
            + KRenderingPackage.KTOP_POSITION;

    private static Triple<Float, Float, Boolean> getVerticalSize(final KPosition tL,
            final KPosition bR) {
        float absYOffest = 0;
        float relHeight = 1f;
        boolean absoluteLength = false;

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
            absYOffest = Math.min(bR.getY().getAbsolute(), tL.getY().getAbsolute());
            break;

        case TOP_TOP:
            // bottom right comes from top
            // top left comes from top
            relHeight = bR.getY().getRelative() - tL.getY().getRelative();
            absYOffest = Math.min(tL.getY().getAbsolute(), bR.getY().getAbsolute());
            break;

        default:
            // we don't know which position was taken
            // TODO ensure that all placement positions are guaranteed
            System.err.println("Found unknown placement position of y-axis!");
            break;
        }
        return new Triple<Float, Float, Boolean>(absYOffest, relHeight, absoluteLength);
    }

    private static final int SCALE_XY = 0;
    private static final int SCALE_X = 1;
    private static final int SCALE_Y = 2;
    private static final int SCALE_NONE = 3;

    
    /**
     * This method manipulates direct placement configurations in order to scale figure parts
     *  proportionally. It is currently unused as I'm not convinced of that idea. (chsch) 
     * 
     * @param container
     * @param minBounds
     * @param defBounds
     * @return
     */    
    @SuppressWarnings("unused")
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
                // currentParent = (KContainerRendering) currentRendering;
                // currentBounds = bounds;
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
                    // placement
                    // bounds
                    // children

                    // collect the grid placement data
                    final KGridPlacementData[] gpds = new KGridPlacementData[children.size()];
                    int i = 0;
                    for (KRendering rendering : children) {
                        gpds[i++] = asGridPlacementData(rendering.getPlacementData());
                    }

                    return evaluateGridPlacement(gridPlacement, gpds, parentBounds)[children
                            .lastIndexOf(child)];
                }
            }.doSwitch(placement);
        }

        if (child instanceof KPolyline) {
            return evaluatePolylinePlacement((KPolyline) child, bounds);
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
     * Returns the bounds for a gridPlacement data in given parent bounds.
     * 
     * @param dpd
     *            the grid placement data
     * @param parentBounds
     *            the parent bounds
     * @return the bounds
     */
    public static Bounds[] evaluateGridPlacement(final KGridPlacement gridPlacement,
            final KGridPlacementData[] gpds, final Bounds parentBounds) {
        GridPlacer placer = getGridPlacementObject(gridPlacement, gpds);
        return placer.evaluate(parentBounds);
    }

    /**
     * returns a gridPlacer to calculate bounds of single elements inside the grid
     * @param gridPlacement the grid data
     * @param gpds the placement data of elements inside the grid
     * @param parentBounds the bounds to consider
     * @return the gridPlacer
     */
    public static GridPlacer getGridPlacementObject(final KGridPlacement gridPlacement,
            final KGridPlacementData[] gpds) {
        // public static Bounds evaluateGridPlacement(final KGridPlacementData gpds, final Bounds
        // parentBounds){
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
        public Bounds[] evaluate(final Bounds parentBounds) {
            if (gpds.length == 0) {
                return new Bounds[0];
            }

            Bounds[] bounds = new Bounds[gpds.length];
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
                bounds[i] = new Bounds(elementX, elementY, elementWidth, elementHeight);

                currentX += cellWidth * widthScale;

                // advance the current y-coordinate if necessary
                if (column == numColumns - 1) {
                    currentY += cellHeight * heightScale;
                    // new row = start from left.
                    currentX = 0;
                }
            }
            return bounds;
        }
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
    public static Bounds evaluatePolylinePlacement(final KPolyline line, final Bounds parentBounds) {
        if (line == null || line.getPoints().size() == 0) {
            return new Bounds(0, 0, parentBounds.width, parentBounds.height);
        }

        // evaluate the points of the polyline inside the parent bounds to compute the bounding box
        float minX = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE;
        float minY = Float.MAX_VALUE;
        float maxY = Float.MIN_VALUE;
        for (KPosition polyPoint : line.getPoints()) {
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
