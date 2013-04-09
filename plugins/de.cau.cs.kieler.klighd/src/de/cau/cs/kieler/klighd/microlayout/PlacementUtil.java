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
package de.cau.cs.kieler.klighd.microlayout;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.krendering.KAreaPlacementData;
import de.cau.cs.kieler.core.krendering.KChildArea;
import de.cau.cs.kieler.core.krendering.KContainerRendering;
import de.cau.cs.kieler.core.krendering.KFontBold;
import de.cau.cs.kieler.core.krendering.KFontItalic;
import de.cau.cs.kieler.core.krendering.KFontName;
import de.cau.cs.kieler.core.krendering.KFontSize;
import de.cau.cs.kieler.core.krendering.KGridPlacement;
import de.cau.cs.kieler.core.krendering.KGridPlacementData;
import de.cau.cs.kieler.core.krendering.KLeftPosition;
import de.cau.cs.kieler.core.krendering.KPlacement;
import de.cau.cs.kieler.core.krendering.KPlacementData;
import de.cau.cs.kieler.core.krendering.KPointPlacementData;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KTopPosition;
import de.cau.cs.kieler.core.krendering.KXPosition;
import de.cau.cs.kieler.core.krendering.KYPosition;
import de.cau.cs.kieler.core.krendering.util.KRenderingSwitch;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klighd.KlighdConstants;
import de.cau.cs.kieler.klighd.krendering.KTextUtil;

/**
 * A utility class for evaluating the micro layout of KRenderings.
 * 
 * @author chsch, sgu, akoc
 */
public final class PlacementUtil {

    /**
     * Hidden default constructor.
     */
    private PlacementUtil() {
    }

    // CHECKSTYLEOFF Visibility

    /**
     * todo.
     */
    public static final IProperty<GridSpacing> ESTIMATED_GRID_DATA = new Property<GridSpacing>(
            "klighd.grid.estimatedGridData");
    /**
     * 
     */
    public static final IProperty<Pair<Integer, Integer>> CHILD_AREA_POSITION = 
            new Property<Pair<Integer, Integer>>("klighd.grid.childAreaPosition");
    
    /**
     * A data holder class for the spacing of the grid calculated during the size estimation.
     * this class is intended to be used in the above defined IProperty ESTIMATED_GRID_DATA to be
     * evaluated during the actual evaluation of parentBounds to place childElements
     * @author akoc
     *
     */
    static class GridSpacing {
        float[] calculatedColumnWidths;
        float[] calculatedRowHeights;
        
        /**
         * Constructor. Sets the attributes according to given parameters 
         * @param cols the col widths calculated during the size estimation
         * @param rows the row heights calculated during the size estimation
         */
        GridSpacing(final float[] cols, final float[] rows) {
            this.calculatedColumnWidths = cols;
            this.calculatedRowHeights = rows;
        }
    }
    

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
        
        /**
         * {@inheritDoc}
         */
        public String toString() {
            return "(" + this.x + "," + this.y + ")"; 
        }
    }

//    /**
//     * A data holder class for bounds.
//     */
//    private static class Bounds {
//
//        /** the x-coordinate. */
//        float x;
//        /** the y-coordinate. */
//        float y;
//        /** the width. */
//        float width;
//        /** the height. */
//        float height;
//        /**
//         * the insets used to transport the position of a ChildAreaCell from estimateGridSize to
//         * calculateInsets method.
//         */
//        KInsets insets = null;
//
//        /**
//         * Constructs bounds with the given dimensions and (x,y) coordinates of (0,0).
//         * 
//         * @param width
//         *            the width
//         * @param height
//         *            the height
//         */
//        public Bounds(final float width, final float height) {
//            this.x = 0;
//            this.y = 0;
//            this.width = width;
//            this.height = height;
//            this.insets = KLayoutDataFactory.eINSTANCE.createKInsets();
//        }
//
//        /**
//         * Constructs bounds with the given dimensions and (x,y) coordinates of (0,0).
//         * 
//         * @param width
//         *            the width
//         * @param height
//         *            the height
//         */
//        public Bounds(final double width, final double height) {
//            this.x = 0;
//            this.y = 0;
//            this.width = (float) width;
//            this.height = (float) height;
//            this.insets = KLayoutDataFactory.eINSTANCE.createKInsets();
//        }
//
//        /**
//         * Constructs bounds from the dimensions of the given Bounds.
//         * 
//         * @param bounds
//         *            the Bounds to take the data from
//         */
//        public Bounds(final Bounds bounds) {
//            this.x = bounds.x;
//            this.y = bounds.y;
//            this.width = bounds.width;
//            this.height = bounds.height;
//            this.insets = KLayoutDataFactory.eINSTANCE.createKInsets();
//        }
//
//        /**
//         * Constructs bounds from the dimensions of the given SWT Point with coordinates (0,0).
//         * 
//         * @param point
//         *            the SWT point to take width and height from
//         */
//        public Bounds(final org.eclipse.swt.graphics.Point point) {
//            this.width = point.x;
//            this.height = point.y;
//            this.insets = KLayoutDataFactory.eINSTANCE.createKInsets();
//        }
//
//        /**
//         * Constructs bounds from the dimensions of the given AWT geometry {@link Rectangle2D.Float}.
//         * 
//         * @param rect
//         *            the {@link Rectangle2D.Float} to take x, y, width, and height from
//         */
//        public Bounds(final Rectangle2D.Float rect) {
//            this.x = rect.x;
//            this.y = rect.y;
//            this.width = rect.width;
//            this.height = rect.height;
//            this.insets = KLayoutDataFactory.eINSTANCE.createKInsets();
//        }
//
//        /**
//         * Constructs bounds from the dimensions of the given AWT geometry {@link Rectangle2D.Float}.
//         * 
//         * @param rect
//         *            the {@link Rectangle2D} to take x, y, width, and height from
//         */
//        public Bounds(final Rectangle2D rect) {
//            this.x = (float) rect.getX();
//            this.y = (float) rect.getY();
//            this.width = (float) rect.getWidth();
//            this.height = (float) rect.getHeight();
//            this.insets = KLayoutDataFactory.eINSTANCE.createKInsets();
//        }
//
//        /**
//         * Constructs bounds from the given coordinates and dimensions.
//         * 
//         * @param x
//         *            the x-coordinate
//         * @param y
//         *            the y-coordinate
//         * @param width
//         *            the width
//         * @param height
//         *            the height
//         */
//        public Bounds(final float x, final float y, final float width, final float height) {
//            this.x = x;
//            this.y = y;
//            this.width = width;
//            this.height = height;
//            this.insets = KLayoutDataFactory.eINSTANCE.createKInsets();
//        }
//
//        /**
//         * Takes the data of the given bounds.
//         * 
//         * @param bounds
//         *            the bounds to take the data from
//         */
//        void setBounds(final Bounds bounds) {
//            this.x = bounds.x;
//            this.y = bounds.y;
//            this.width = bounds.width;
//            this.height = bounds.height;
//            this.insets = KLayoutDataFactory.eINSTANCE.createKInsets();
//        }
//
//        /**
//         * Getter for X coordinate.
//         * 
//         * @return height
//         */
//        public float getX() {
//            return this.x;
//        }
//
//        /**
//         * Getter for Y coordinate.
//         * 
//         * @return width
//         */
//        public float getY() {
//            return this.y;
//        }
//
//        /**
//         * Getter used in JUnit test of the placement logic.
//         * 
//         * @return height
//         */
//        public float getHeight() {
//            return this.height;
//        }
//
//        /**
//         * Getter used in JUnit test of the placement logic.
//         * 
//         * @return width
//         */
//        public float getWidth() {
//            return this.width;
//        }
//
//        /**
//         * Transforms the current {@link Bounds} object in an AWT geometry {@link Rectangle2D}.
//         * 
//         * @return a related {@link Rectangle2D}
//         */
//        public Rectangle2D toRectangle2D() {
//            return new Rectangle2D.Float(this.x, this.y, this.width, this.height);
//        }
//
//        /**
//         * Getter to access the Insets of this Bounds.
//         * 
//         * @return width
//         */
//        public KInsets getInsets() {
//            return this.insets;
//        }
//        
//        /**
//         * {@inheritDoc}
//         */
//        public String toString() {
//            return "(" + this.x + "," + this.y + "|" + this.width + "," + this.height + ")"; 
//        }
//
//        /**
//         * Determines component-wise the maximum of the given bounds <code>b1</code> and
//         * <code>b2</code>. The function <b>modifies</b> <code>b1</code>.
//         * 
//         * @param b1
//         *            the first {@link Bounds} object
//         * @param b2
//         *            the second {@link Bounds} object
//         * @return the object b1 with modified <code>width</code> and <code>height</code> data
//         *         containing the particular maximal values.
//         */
//        public static Bounds max(final Bounds b1, final Bounds b2) {
//            b1.width = Math.max(b1.width, b2.width);
//            b1.height = Math.max(b1.height, b2.height);
//            return b1;
//        }
//        
//        /**
//         * Determines component-wise the minimum of the given bounds <code>b1</code> and
//         * <code>b2</code>. The function <b>modifies</b> <code>b1</code>.
//         * 
//         * @param b1
//         *            the first {@link Bounds} object
//         * @param b2
//         *            the second {@link Bounds} object
//         * @return the object b1 with modified <code>width</code> and <code>height</code> data
//         *         containing the particular minimal values.
//         */
//        public static Bounds min(final Bounds b1, final Bounds b2) {
//            b1.width = Math.min(b1.width, b2.width);
//            b1.height = Math.min(b1.height, b2.height);
//            return b1;
//        }
//        
//        /**
//         * Constructs bounds with the given dimensions and (x,y) coordinates (0,0).
//         * 
//         * @param width
//         *            the width
//         * @param height
//         *            the height
//         * @return the desired {@link Bounds} object
//         */
//        public static Bounds of(final float width, final float height) {
//            return new Bounds(width, height);
//        }
//       
//        /**
//         * Constructs bounds from the dimensions of the given AWT geometry {@link Rectangle2D}.
//         * 
//         * @param rect
//         *            the {@link Rectangle2D} to take x, y, width, and height from
//         * @return the desired {@link Bounds} object
//         */
//        public static Bounds of(final Rectangle2D rect) {
//            return new Bounds(rect);
//        }
//    }
    
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
     * Estimates the minimal size of a KRendering.<br>
     * The the previous defined size is incorporated while resolving relative placement/size
     * constraints.
     * 
     * @param rendering
     *            the KRenderings to be evaluated
     * @param givenBounds
     *            the size that is currently assigned to 'rendering's container or the related
     *            KShapeLayout respectively
     * @return the minimal size
     */
    public static Bounds estimateSize(final KRendering rendering, final Bounds givenBounds) {
        //determine the type of the rendering
        int id = KRENDERING_PACKAGE.getKText().isInstance(rendering) ? KRenderingPackage.KTEXT
                : (KRENDERING_PACKAGE.getKContainerRendering().isInstance(rendering) 
                        ? KRenderingPackage.KCONTAINER_RENDERING
                        : KRENDERING_PACKAGE.getKChildArea().isInstance(rendering) 
                        ? KRenderingPackage.KCHILD_AREA
                                : KRENDERING_PACKAGE.getKRenderingRef().isInstance(rendering) 
                                ? KRenderingPackage.KRENDERING_REF
                                        : KRenderingPackage.KRENDERING);

        //calculate size based on type
        switch (id) {
        case KRenderingPackage.KTEXT:
            // for a text rendering just calculate the bounds of the text
            // and put the minimal size into 'minBounds'
            return estimateTextSize((KText) rendering);
        case KRenderingPackage.KCHILD_AREA:
            // KChildAreas do not cover any space that is not gathered by the (macro) layout
            return new Bounds(0, 0);
        case KRenderingPackage.KRENDERING_REF:
            // calculate the size of the referenced Rendering instead
            return estimateSize(((KRenderingRef) rendering).getRendering(), givenBounds);
        case KRenderingPackage.KCONTAINER_RENDERING:
            KContainerRendering container = (KContainerRendering) rendering;

            int placementId = container.getChildPlacement() != null
                    ? container.getChildPlacement().eClass().getClassifierID() : -1;
            switch (placementId) {
            case KRenderingPackage.KGRID_PLACEMENT:
                // in case of a GridPlacement calculate the number of columns and rows of the grid
                // to the bounds of an inner rendering
                return estimateGridSize(container, givenBounds);

            default:
                // in case of no placement definition calculate the size of each child rendering and
                // find the biggest rendering in width and height
                Bounds maxSize = new Bounds(givenBounds);
                for (KRendering child : container.getChildren()) {
                    if (child.getPlacementData() instanceof KPointPlacementData) {
                        Bounds.max(maxSize, estimatePointPlacedChildSize(child));
                    } else if (child.getPlacementData() instanceof KAreaPlacementData) {
                        Bounds.max(maxSize, estimateAreaPlacedChildSize(child, givenBounds));
                    } else {
                        // in case no valid placement data are given we assume the size of the
                        // parent by the size of the child
                        Bounds.max(maxSize, estimateSize(child, givenBounds));      
                    }
                }
                return maxSize;
            }
        default:
            // there is something else than a text box or a container, e.g. a KPolyline
            // just take the given 'defBounds' as minimal bounds as we consider only KTexts
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
                //  (I don't trust in the different SWT implementations to
                //   provide the same size of a text on different platforms
                //   so given data are to be used)
                float height = testHeight != null ? Float.parseFloat(testHeight.toString()) : 0f;
                float width = testWidth != null ? Float.parseFloat(testWidth.toString()) : 0f;
                if (height != 0f || width != 0f) {
                    return new Bounds(width, height);
                }
            }
            kFontName = Iterables.getFirst(Iterables.filter(kText.getStyles(), KFontName.class), null);
            kFontSize = Iterables.getFirst(Iterables.filter(kText.getStyles(), KFontSize.class), null);
            kFontBold = Iterables.getFirst(Iterables.filter(kText.getStyles(), KFontBold.class), null);
            kFontItalic = Iterables.getFirst(
                    Iterables.filter(kText.getStyles(), KFontItalic.class), null);
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
        final Font font = new Font(Display.getDefault(), fontName, fontSize, fontStyle);
        gc.setAntialias(SWT.ON);
        gc.setFont(font);
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
                    textBounds.height += fm.getLeading() + fm.getAscent(); // + fm.getDescent() + ;
                    firstLine = false;
                } else {
                    textBounds.width = Math.max(lineBounds.width, textBounds.width);
                    textBounds.height += fm.getHeight();
                }
            }
        }
        font.dispose();
        gc.dispose();
        return textBounds;
    }


    /**
     * Returns the required minimal size of a {@link KRendering} width attached
     * {@link KPointPlacementData}.
     * 
     * @param container
     *            the {@link KRendering} to be evaluated
     * 
     * @return the minimal required size
     */
    private static Bounds estimatePointPlacedChildSize(final KRendering rendering) {
        KPointPlacementData ppd = (KPointPlacementData) rendering.getPlacementData();

        // determine minimal needed size of the child
        //  for point-based placement the parent size does not matter for the size! 
        final Bounds minimalSize = Bounds.of(ppd.getMinWidth(), ppd.getMinHeight());
        final Bounds cSize = Bounds.max(minimalSize, estimateSize(rendering, minimalSize));
                
        float requiredWidth = getHorizontalSize(ppd, cSize.getWidth());
        float requiredHeight = getVerticalSize(ppd, cSize.getHeight());

        return Bounds.of(requiredWidth, requiredHeight);
    }

    /**
     * Determines the horizontal size value for a point-based placed child.
     * 
     * @param ppd
     *            the {@link KPointPlacementData} containing the required declarations
     * @param minWidth
     *            the estimated minimal width of the child
     * @return a {@link Pair} of the absolute size offset and the relative size divisor
     */
    private static float getHorizontalSize(final KPointPlacementData ppd, final float minWidth) {
        if (ppd == null) {
            return minWidth;
        }

        KPosition pos = ppd.getReferencePoint();
        float abs = pos != null && pos.getX() != null ? pos.getX().getAbsolute() : 0f;
        float calculatedWidth = 0f;

        switch (ppd.getHorizontalAlignment()) {
        case LEFT:
        case RIGHT:
            // the child requires its minWidth and the absolute margin defined by pos.getX()
            calculatedWidth = abs + Math.max(minWidth, ppd.getMinWidth()) + ppd.getHorizontalMargin();
            break;
        case CENTER:
            float halfWidth = Math.max(minWidth, ppd.getMinWidth()) / 2;
            if (abs > halfWidth) {
                // in this case the child requires, depending on type of pos.getX, on one side more
                //  space than on the other, so:
                calculatedWidth = abs + halfWidth; 
            } else {
                // in case one might argue the same way, but there's still the relative part
                //  so I think potentially shrinking the width is not reasonable; thus:
                calculatedWidth = Math.max(minWidth, ppd.getMinWidth());
            }
            calculatedWidth += 2 * ppd.getHorizontalMargin();
        }
        
        return calculatedWidth;
    }

    /**
     * Determines the vertical size value for a point-based placed child.
     * 
     * @param ppd
     *            the {@link KPointPlacementData} containing the required declarations
     * @param minHeight
     *            the estimated minimal height of the child
     * @return a {@link Pair} of the absolute size offset and the relative size divisor
     */
    private static float getVerticalSize(final KPointPlacementData ppd, final float minHeight) {
        if (ppd == null) {
            return minHeight;
        }

        KPosition pos = ppd.getReferencePoint();
        float abs = pos != null && pos.getY() != null ? pos.getY().getAbsolute() : 0f;
        float calculatedHeight = 0f;

        switch (ppd.getVerticalAlignment()) {
        case TOP:
        case BOTTOM:
            // the child requires its minHeight and the absolute margin defined by pos.getY()
            calculatedHeight = abs + Math.max(minHeight, ppd.getMinHeight()) + ppd.getVerticalMargin();
            break;
        case CENTER:
            float halfHeight = Math.max(minHeight, ppd.getMinHeight()) / 2;
            if (abs > halfHeight) {
                // in this case the child requires, depending on type of pos.getY, on one side more
                //  space than on the other, so:
                calculatedHeight = abs + halfHeight; 
            } else {
                // in case one might argue the same way, but there's still the relative part
                //  so I think potentially shrinking the width is not reasonable; thus:
                calculatedHeight = Math.max(minHeight, ppd.getMinHeight());
            }
            calculatedHeight += 2 * ppd.getVerticalMargin();
        }
        return calculatedHeight;
    }


    /**
     * Returns the required minimal size of a {@link KRendering} width attached
     * {@link KAreaPlacementData}.
     * 
     * @param container
     *            the {@link KRendering} to be evaluated
     * @param givenBounds
     *            the size that is currently assigned to <code>rendering</code>'s container.
     * 
     * @return the minimal required size
     */
    private static Bounds estimateAreaPlacedChildSize(final KRendering rendering,
            final Bounds initialSize) {
        KAreaPlacementData apd = (KAreaPlacementData) rendering.getPlacementData();

        final Bounds cSize = evaluateAreaPlacement(apd, initialSize);
        // determine minimal needed size of the child
        final Bounds containerMinSize = estimateSize(rendering, cSize);
        
        KPosition tL = apd.getTopLeft();
        KPosition bR = apd.getBottomRight();

        Pair<Float, Float> horSize = getHorizontalSize(tL, bR);
        float absXOffest = horSize.getFirst();
        float relWidth = horSize.getSecond();

        Pair<Float, Float> verSize = getVerticalSize(tL, bR);
        float absYOffest = verSize.getFirst();
        float relHeight = verSize.getSecond();

        containerMinSize.width = (relWidth == 0f ? 0f : containerMinSize.width / relWidth) + absXOffest;
        containerMinSize.height = (relHeight == 0f ? 0f : containerMinSize.height / relHeight)
                + absYOffest;
        
        return containerMinSize;
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
        List<KRendering> childRenderings = container.getChildren();

        //Bounds minBounds = new Bounds(parentBounds);
        Bounds minBounds = new Bounds(0.0f, 0.0f, 0.0f, 0.0f);
        
        int numRows;
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
        float[] minColumnWidths = new float[numColumns];
        float[] minRowHeights = new float[numRows];

        //variables to later store the information where an optional childArea might be placed
        int childAreaRowId = -1;
        int childAreaColId = -1;

        // first evaluate the grid to get position and size of each cell
        // to make the layout as compact as possible but have space to place all children,
        // evaluate the space needed for each child
        for (int k = 0; k < childRenderings.size(); k++) {
            KRendering currentChild = childRenderings.get(k);
            
            int row = k / numColumns;
            int col = k - row * numColumns;

            LinkedList<KRendering> path = Lists.newLinkedList();
            if (findChildArea(currentChild, path)) {
                //if a childArea is contained in the current child (or the child itself is a childarea)
                // remember the position of the area to be able to calculate
                // the size of the parentNode correctly
                // later this is used to calculate insets based on the cellPosition of the childArea
                childAreaColId = col;
                childAreaRowId = row;
            }

            Bounds childMinBounds = estimateSize(currentChild, new Bounds(0, 0));

            float elementHeight = childMinBounds.getHeight();
            float elementWidth = childMinBounds.getWidth();

            // evaluate given PlacementData
            KGridPlacementData gridData = asGridPlacementData(currentChild.getPlacementData());

            if (gridData != null) {
                KPosition topLeft = gridData.getTopLeft();
                KPosition bottomRight = gridData.getBottomRight();

                //take insets into consideration
                elementWidth = calculateParentWidth(elementWidth, topLeft, bottomRight);
                elementHeight = calculateParentWidth(elementHeight, topLeft, bottomRight);
                   
                elementHeight = Math.max(gridData.getMinCellHeight(), elementHeight);
                elementWidth = Math.max(gridData.getMinCellWidth(), elementWidth);
            }
            // compare the width and height of the current rendering with the biggest width
            // and height of the corresponding row and column and update the values with the maximum
            minRowHeights[row] = Math.max(minRowHeights[row], elementHeight);
            minColumnWidths[col] = Math.max(minColumnWidths[col], elementWidth);
        }
        
        //store the information of the size of the cells into the KRendering the grid is attached to
        //to be able to reUse that information later
        container.setProperty(ESTIMATED_GRID_DATA, new GridSpacing(minColumnWidths, minRowHeights));
        container.setProperty(CHILD_AREA_POSITION,  
                new Pair<Integer, Integer>(childAreaColId, childAreaRowId));
        
        // the minimum total bound is the sum of the single column widths and row heights
        Bounds childBounds = new Bounds(0, 0, 0, 0);
        for (float width : minColumnWidths) {
            childBounds.width += width;
        }
        for (float height : minRowHeights) {
            childBounds.height += height;
        }
        
        //take insets of the grid itself into consideration
        KPosition topLeft = ((KGridPlacement) container.getChildPlacement()).getTopLeft();
        KPosition bottomRight =  ((KGridPlacement) container.getChildPlacement()).getBottomRight();
        //increase size according to the space needed by the insets
        childBounds.width = calculateParentWidth(childBounds.width, topLeft, bottomRight);
        childBounds.height = calculateParentHeight(childBounds.height, topLeft, bottomRight);
        
        minBounds.width = childBounds.width;
        minBounds.height = childBounds.height;
                
        //to make sure the calling function "createNode" is able to place the children correctly later, 
        //we have to transfer a detailed position of the childArea to it.
        //so calculate the insets for the childArea, if there is one explicitly defined
        float inset = 0.0f, secondInset = 0.0f;
        if (childAreaColId >= 0 && childAreaRowId >= 0) {
            // found a childArea earlier, calculate 'insets' based on position
            // left inset is everything left of the childAreaCell
            for (int left = 0; left < childAreaColId; left++) {
                inset += minColumnWidths[left];
            }
            
            //add insets defined in the GridPlacement itself 
            inset += minBounds.width - getHorizontalSizeAvailable(minBounds.width, topLeft, null);
            
            //add insets defined in the GridPlacementData of the cell the childArea is placed in
            KGridPlacementData gridData = asGridPlacementData(
                    childRenderings.get(childAreaRowId * numColumns + childAreaColId)
                        .getPlacementData());
            if (gridData != null) {
                topLeft = gridData.getTopLeft();
                inset += minBounds.width - getHorizontalSizeAvailable(minBounds.width, topLeft, null);
                bottomRight = gridData.getBottomRight();
                secondInset =  minBounds.width 
                        - getHorizontalSizeAvailable(minBounds.width, null, topLeft);
            }
            
            childBounds.getInsets().setLeft(inset);
            // right inset is fullWidth-insetLeft-(cellWidth of childArea - rightInset in that cell)
            childBounds.getInsets().setRight(
                    childBounds.width - inset - (minColumnWidths[childAreaColId]) - secondInset);

            // reset for next calculation
            inset = 0.0f;
            // top inset is everything top of the childAreaCell
            for (int top = 0; top < childAreaRowId; top++) {
                inset += minRowHeights[top];
            }
            
            //add insets defined in the GridPlacementData of the cell the childArea is placed in
            inset += (minBounds.height - getVerticalSizeAvailable(minBounds.height, topLeft, null));
                
            if (gridData != null) {
                bottomRight = gridData.getBottomRight();
                secondInset =  minBounds.height
                        - getVerticalSizeAvailable(minBounds.height, null, bottomRight);
            }
            
            //add insets defined in the GridPlacement itself 
            topLeft = ((KGridPlacement) container.getChildPlacement()).getTopLeft();
            inset += (minBounds.height - getVerticalSizeAvailable(minBounds.height,  topLeft,  null));
            
            childBounds.getInsets().setTop(inset);
            // bottom inset is fullHeight-insetTop-childAreaHeight
            childBounds.getInsets().setBottom(
                    childBounds.height - inset - (minRowHeights[childAreaRowId] - secondInset));
        }

        // transport the inset-sums on each side through the position
        minBounds.insets = childBounds.getInsets();
        return minBounds;
    }
    
    /* whether a position is measured in the same direction as the point it is defining 
     * e.g. a top left position is measured from left*/
    private static final int DIRECT = 0;
    /* whether a position is measured contrary to the point it is defining 
     * e.g. a top right position is measured from left*/
    private static final int INDIRECT = 1;
    
    /* offset to be used to calculate below defined constants to determine how positions interact 
     * first positions are left or top.
     */
    private static final int FIRST_OFFSET = 100;
    
    /* both positions are measured direct*/
    private static final int DIRECT_DIRECT = DIRECT * FIRST_OFFSET + DIRECT;
    /* first position is measured directly, second position is measured indirectly */
    private static final int DIRECT_INDIRECT = DIRECT * FIRST_OFFSET + INDIRECT;
    /* first position is measured indirectly, second position is measured directly */
    private static final int INDIRECT_DIRECT = INDIRECT * FIRST_OFFSET + DIRECT;
    /* both positions are measured indirectly */
    private static final int INDIRECT_INDIRECT = INDIRECT * FIRST_OFFSET + INDIRECT;
    
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
            posId0 = DIRECT;
        } else {
            abs0 = tL.getX().getAbsolute();
            rel0 = tL.getX().getRelative();
            posId0 = tL.getX().eClass().getClassifierID() == KRenderingPackage.KLEFT_POSITION ? DIRECT
                    : INDIRECT;
        }

        if (bR == null) {
            // emulate measuring from bottomRight
            abs1 = 0;
            rel1 = 0;
            posId1 = DIRECT;
        } else {
            abs1 = bR.getX().getAbsolute();
            rel1 = bR.getX().getRelative();
            posId1 = bR.getX().eClass().getClassifierID() == KRenderingPackage.KRIGHT_POSITION ? DIRECT
                    : INDIRECT;
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
            posId0 = DIRECT;
        } else {
            abs0 = tL.getY().getAbsolute();
            rel0 = tL.getY().getRelative();
            posId0 = tL.getY().eClass().getClassifierID() == KRenderingPackage.KTOP_POSITION ? DIRECT
                    : INDIRECT;
        }

        if (bR == null) {
            // emulate measuring from bottomRight
            abs1 = 0;
            rel1 = 0;
            posId1 = DIRECT;
        } else {
            abs1 = bR.getY().getAbsolute();
            rel1 = bR.getY().getRelative();
            posId1 = bR.getY().eClass().getClassifierID() == KRenderingPackage.KBOTTOM_POSITION ? DIRECT
                    : INDIRECT;
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
    private static float getSizeAvailable(
            final float parentSize,
            final float abs0, final float rel0, final int positionId0,
            final float abs1, final float rel1, final int positionId1) {
        
        Pair<Float, Float> normalizedInsets = getSize(abs0, rel0, positionId0,
                abs1, rel1, positionId1);
        
        float absOffset = normalizedInsets.getFirst();
        float relSize = normalizedInsets.getSecond();
        
        float elementWidth = parentSize * relSize - absOffset;
        
        
        if (elementWidth < 0) {
            //invalid insets
            return parentSize; 
        } else {
            return elementWidth;
        }
    }
 
    /**
     * Calculate the needed size of a parent based on the size of an element to be placed inside that 
     * parent and the insets defined for that child.
     * @param elementSize the size of the element to be placed
     * @param tL the KPosition defining the top and Left insets
     * @param bR the KPosition defining the bottom and right insets
     * @return the size needed for the parent to be able to place the element with the given size
     */
    private static float calculateParentWidth(final float elementSize, final KPosition tL,
            final KPosition bR) {
        Pair<Float, Float> normalizedSize = getHorizontalSize(tL, bR);
        Float parentWidth = elementSize;
        
        if (normalizedSize.getFirst() > 0) {
            //parent has to be bigger than child
            parentWidth += normalizedSize.getFirst();
        }
        
        if (normalizedSize.getSecond() > 0 
                && normalizedSize.getSecond() < 1) {
         //if child should be bigger than parent or no space is left for child, ignore relative Values
            parentWidth /= normalizedSize.getSecond();
        }
        
        return parentWidth;
    }
    
    /**
     * Calculate the needed size of a parent based on the size of an element to be placed inside that 
     * parent and the insets defined for that child.
     * @param elementSize the size of the element to be placed
     * @param tL the KPosition defining the top and Left insets
     * @param bR the KPosition defining the bottom and right insets
     * @return the size needed for the parent to be able to place the element with the given size
     */
    private static float calculateParentHeight(final float elementSize, final KPosition tL,
            final KPosition bR) {
        Pair<Float, Float> normalizedSize = getVerticalSize(tL, bR);
        Float parentHeight = elementSize;
        
        if (normalizedSize.getFirst() > 0) {
            //parent has to be bigger than child
            parentHeight += normalizedSize.getFirst();
        }
        
        if (normalizedSize.getSecond() > 0 
                && normalizedSize.getSecond() < 1) {
         //if child should be bigger than parent or no space is left for child, ignore relative Values
            parentHeight /= normalizedSize.getSecond();
        }
        
        return parentHeight;
    }


    /**
     * Determines the horizontal correction values for area-based placed child.
     * 
     * @param tL
     *            the topLeft position
     * @param bR
     *            the bottomRight position
     * @return a {@link Pair} of the absolute size offset (first component)
     *             and the relative size divisor (second component)
     */
    private static Pair<Float, Float> getHorizontalSize(final KPosition tL, final KPosition bR) {
        //  the idea of that variable is to provide an information whether the size of
        //  figure is influenced by the size of the parent or whether it's fully determined
        //  by the absolute positioning components; need thinking on that further
        //boolean absoluteLength = false;
        
        float abs0, abs1, rel0, rel1;
        int posId0, posId1;
        
        if (tL == null) {
            // emulate measuring from topLeft
            abs0 = 0;
            rel0 = 0;
            posId0 = DIRECT;
        } else {
            abs0 = tL.getX().getAbsolute();
            rel0 = tL.getX().getRelative();
            posId0 = tL.getX().eClass().getClassifierID() == KRenderingPackage.KLEFT_POSITION 
                    ? DIRECT : INDIRECT;
        }

        if (bR == null) {
            // emulate measuring from bottomRight
            abs1 = 0;
            rel1 = 0;
            posId1 = DIRECT;
        } else {
            abs1 = bR.getX().getAbsolute();
            rel1 = bR.getX().getRelative();
            posId1 = bR.getX().eClass().getClassifierID() == KRenderingPackage.KRIGHT_POSITION 
                    ? DIRECT : INDIRECT;
        }
        
        return getSize(abs0, rel0, posId0, abs1, rel1, posId1);
    }

    /**
     * Determines the vertical correction values for area-based placed child.
     * 
     * @param tL
     *            the topLeft position
     * @param bR
     *            the bottomRight position
     * @return a {@link Pair} of the absolute size offset and the relative size divisor
     */
    private static Pair<Float, Float> getVerticalSize(final KPosition tL, final KPosition bR) {
        float abs0, abs1, rel0, rel1;
        int posId0, posId1;
        
        if (tL == null) {
            // emulate measuring from topLeft
            abs0 = 0;
            rel0 = 0;
            posId0 = DIRECT;
        } else {
            abs0 = tL.getY().getAbsolute();
            rel0 = tL.getY().getRelative();
            posId0 = tL.getY().eClass().getClassifierID() == KRenderingPackage.KTOP_POSITION 
                    ? DIRECT : INDIRECT;
        }

        if (bR == null) {
            // emulate measuring from bottomRight
            abs1 = 0;
            rel1 = 0;
            posId1 = DIRECT;
        } else {
            abs1 = bR.getY().getAbsolute();
            rel1 = bR.getY().getRelative();
            posId1 = bR.getY().eClass().getClassifierID() == KRenderingPackage.KBOTTOM_POSITION 
                    ? DIRECT : INDIRECT;
        }
        
        return getSize(abs0, rel0, posId0, abs1, rel1, posId1);
    }


    /**
     * Normalize given insets to find out how big a child is in relation to it's parent and how much
     * space is needed to add absolute insets.
     * 
     * @param abs0
     *            the absolute value of the position defining first inset
     * @param rel0
     *            the relative value of the position defining first inset
     * @param positionId0
     *            the identifier constant informing about the measurement direction (DIRECT,
     *            INDIRECT)
     * @param abs1
     *            the absolute value of the position defining first inset
     * @param rel1
     *            the relative value of the position defining the first inset
     * @param positionId1
     *            the identifier constant informing about the measurement direction (DIRECT,
     *            INDIRECT)
     * @return a pair informing about the absolute value the child size is smaller than the parent
     *         and the relative size of a child w.r.t. to the size of the parent
     */
    private static Pair<Float, Float> getSize(
            final float abs0, final float rel0, final int positionId0,
            final float abs1, final float rel1, final int positionId1) {
        float absOffset = 0;
        float relWidth = 1f;
        
        // boolean absoluteLength = false;
        // the idea of that variable is to provide an information whether the size of
        //  figure is influenced by the size of the parent or whether it's fully determined
        //  by the absolute positioning components; need thinking on that further
        
        int position = positionId0 * FIRST_OFFSET + positionId1;
        
        switch (position) {
        case DIRECT_DIRECT:
            // top left comes from left
            // bottom right comes from right
            relWidth = 1f - rel1 - rel0;
            absOffset = abs0 + abs1;
            break;
            
        case DIRECT_INDIRECT:
            // top left comes from left
            // bottom right comes from left
            relWidth = rel1 - rel0;
            absOffset = abs0 - abs1;
            break;
            
        case INDIRECT_DIRECT:
            // top left comes from right
            // bottom right comes from right
            relWidth = rel0 - rel1;
            absOffset = -abs0 + abs1; 
            break;
            
        case INDIRECT_INDIRECT:
            // top left comes from right
            // bottom right comes from left
            relWidth = rel1 + rel0 - 1f;
            absOffset = -abs0 - abs1;
            break;
            
        default:
            // we don't know which position was taken
            // TODO ensure that all placement positions are guaranteed
            System.err.println("Found unknown placement position for size calculation");
            break;
        }
        return new Pair<Float, Float>(absOffset, relWidth);
        // return new Triple<Float, Float, Boolean>(absXOffest, relWidth, absoluteLength);
    }


    /**
     * Calculates the offset of the child area in the given <code>rootRendering</code>, which is
     * equal to the insets of the related node.<br>
     * <br>
     * To ensure correct layout, changes in the bounds of the node are not allowed to influence the
     * insets. If this is not given the insets may be incorrect.
     * 
     * @param rootRendering
     *            the related node's root KRendering element
     * @param insets
     *            the insets
     * @param minSize
     *            the minimal size of the related node (might not be reflected in the node's layout data)
     */
    public static void calculateInsets(final KRendering rootRendering, final KInsets insets,
            final Bounds minSize) {

        // no rendering so the whole node is the child area
        if (rootRendering == null) {
            return;
        }

        // find the path to the child area
        LinkedList<KRendering> path = Lists.newLinkedList();
        if (!findChildArea(rootRendering, path)) {
            // no child area so the whole node is the child area
            return;
        }
        
        Bounds currentBounds = minSize;

        // the data of the reference parent
        KContainerRendering currentParent = null;

        // the calculated insets
        float leftInset = 0;
        float rightInset = 0;
        float topInset = 0;
        float bottomInset = 0;
        
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
            bounds = PlacementUtil.evaluateAreaPlacement(
                    asAreaPlacementData(child.getPlacementData()), parentBounds);
        } else {
            bounds = new KRenderingSwitch<Bounds>() {
                public Bounds caseKGridPlacement(final KGridPlacement gridPlacement) {
                    // evaluate grid based on the children, their placementData and size 
                    //  and get placement for current child
                    Bounds[] childBounds = GridPlacementUtil.evaluateGridPlacement(gridPlacement,
                            children, parentBounds);
                    int index = children.lastIndexOf(child);
                    return childBounds[index];
                }
            }
            .doSwitch(placement);
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
    public static Bounds evaluateAreaPlacement(final KAreaPlacementData dpd,
            final Bounds parentBounds) {
        if (dpd == null) {
            return new Bounds(parentBounds);
        }

        // determine the top-left
        KPosition topLeft = dpd.getTopLeft();
        Point topLeftPoint = evaluateFlexiblePosition(topLeft, parentBounds);

        // determine the bottom-right
        KPosition bottomRight = dpd.getBottomRight();
        Point bottomRightPoint = evaluateFlexiblePosition(bottomRight, parentBounds);

        return new Bounds(topLeftPoint.x, topLeftPoint.y, bottomRightPoint.x - topLeftPoint.x,
                bottomRightPoint.y - topLeftPoint.y);
    }

    /**
     * Returns the bounds for a polyline placement data in given parent bounds.
     * 
     * @param line
     *            the polyline with its points
     * @param parentBounds
     *            the parent bounds
     * @return the bounds
     */
    public static Bounds evaluatePolylinePlacement(final KPolyline line, final Bounds parentBounds) {
        if (line == null || line.getPoints().isEmpty()) {
            return new Bounds(0, 0, parentBounds.width, parentBounds.height);
        }

        // evaluate the points of the polyline inside the parent bounds to compute the bounding box
        float minX = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE;
        float minY = Float.MAX_VALUE;
        float maxY = Float.MIN_VALUE;
        for (KPosition polylinePoint : line.getPoints()) {
            Point point = evaluateFlexiblePosition(polylinePoint, parentBounds);
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
    public static Point evaluateFlexiblePosition(final KPosition position, final Bounds parentBounds) {
        float width = (float) parentBounds.width;
        float height = (float) parentBounds.height;
        Point point = new Point(0.0f, 0.0f);
        
        if (position == null) {
            return point;
        }
        
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
    public static KAreaPlacementData asAreaPlacementData(final KPlacementData data) {
        if (data instanceof KAreaPlacementData) {
            return (KAreaPlacementData) data;
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
