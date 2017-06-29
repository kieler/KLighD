/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.krendering;

import static com.google.common.collect.Iterators.concat;
import static com.google.common.collect.Iterators.singletonIterator;
import static com.google.common.collect.Iterators.transform;

import java.util.Collections;
import java.util.Iterator;

import com.google.common.base.Function;


/**
 * Collection of KRendering related convenience methods and singleton fields.
 * 
 * @author chsch
 */
public final class KRenderingUtil {

    private static final KRenderingFactory FACTORY = KRenderingFactory.eINSTANCE;
    private static final KRenderingPackage PACKAGE = KRenderingPackage.eINSTANCE;
    
    private KRenderingUtil() {
    }
    
    /**
     * Dereference the given rendering by following the chain of references until a rendering
     * is found that is not a {@link KRenderingRef}. Note that the method runs into an infinite
     * loop if there is a cycle in the references.
     * 
     * @param rendering a rendering
     * @return the dereferenced rendering
     */
    public static KRendering dereference(final KRendering rendering) {
        KRendering r = rendering;
        while (r instanceof KRenderingRef) {
            r = ((KRenderingRef) r).getRendering();
        }
        return r;
    }
    
    /**
     * Returns an Iterator allowing to traverse all child {@link KRendering KRenderings} as well as
     * referenced ones and their children.
     * 
     * @param rendering
     *            the {@link KRendering} to start with
     * @return the desired {@link Iterator}
     */
    public static Iterator<KRendering> selfAndAllChildren(final KRendering rendering) {
        Iterator<KRendering> children;
        if (rendering instanceof KRenderingRef) {
            children = selfAndAllChildren(((KRenderingRef) rendering).getRendering());
            
        } else if (rendering instanceof KContainerRendering) {
            children = concat(transform(((KContainerRendering) rendering).getChildren().iterator(),
                    new Function<KRendering, Iterator<KRendering>>() {
                        public Iterator<KRendering> apply(final KRendering rendering) {
                            return selfAndAllChildren(rendering);
                        }
            }));
            
        } else {
            children = Collections.emptyIterator();
        }
        return concat(singletonIterator(rendering), children);
    }
    
    /**
     * Helper method providing the {@link KPlacementData} of a {@link KRendering}. Handles the
     * "inheritance" of {@link KPlacementData} in case of {@link KRenderingRef KRenderingRefs}.
     * 
     * @param rendering
     *            the rendering to provide the {@link KPlacementData} for
     * @return the requested {@link KPlacementData}
     */
    public static KPlacementData getPlacementData(final KRendering rendering) {
        if (rendering.getPlacementData() != null) {
            return rendering.getPlacementData();
        } else if (PACKAGE.getKRenderingRef().isInstance(rendering)) {
            // ... and the ref doesn't contain explicit placement data
            final KRenderingRef ref = (KRenderingRef) rendering;
            if (ref.getRendering() == null) {
                return null;
            } else {
                return getPlacementData(((KRenderingRef) rendering).getRendering());
            }
        } else {
            return null;
        }
    }
    
    /**
     * Casts the given {@link KPlacementData} to {@link KAreaPlacementData}.
     * 
     * @param data
     *            the {@link KPlacement}
     * @return the {@link KAreaPlacementData} or <code>null</code> otherwise
     */
    public static KAreaPlacementData asAreaPlacementData(final KPlacementData data) {
        if (data instanceof KAreaPlacementData) {
            return (KAreaPlacementData) data;
        }
        return null;
    }

    /**
     * Casts the given {@link KPlacementData} to {@link KPointPlacementData}.
     * 
     * @param data
     *            the {@link KPlacement}
     * @return the {@link KPointPlacementData} or <code>null</code> otherwise
     */
    public static KPointPlacementData asPointPlacementData(final KPlacementData data) {
        if (data instanceof KPointPlacementData) {
            return (KPointPlacementData) data;
        }
        return null;
    }

    /**
     * Casts the given {@link KPlacementData} to {@link KGridPlacementData}.
     * 
     * @param data
     *            the {@link KPlacement}
     * @return the {@link KGridPlacementData} or <code>null</code> otherwise
     */
    public static KGridPlacementData asGridPlacementData(final KPlacementData data) {
        if (data instanceof KGridPlacementData) {
            return (KGridPlacementData) data;
        }
        return null;
    }

    /**
     * Returns the given {@link KPlacement} as {@link KGridPlacement}.
     * 
     * @param placement
     *            the {@link KPlacement}
     * @return the {@link KGridPlacement} or <code>null</code> otherwise
     */
    public static KGridPlacement asGridPlacement(final KPlacement placement) {
        if (placement instanceof KGridPlacement) {
            return (KGridPlacement) placement;
        }
        return null;
    }


    /* ------------------------- */
    /*    positioning helpers    */
    /* ------------------------- */
    
    /**
     * Singleton {@link KPosition} instance containing {@link KLeftPosition KLeft-} &
     * {@link KTopPosition} components.
     */
    public static final KPosition LEFT_TOP_POS = createLeftTopKPosition();
    
    /**
     * Singleton {@link KPosition} instance containing {@link KRightPosition KRight-} &
     * {@link KBottomPosition} components.
     */
    public static final KPosition RIGHT_BOTTOM_POS = createRightBottomKPosition();
    

    /**
     * Factory method creating a {@link KPosition} containing a {@link KLeftPosition} and
     * {@link KTopPosition}.
     * 
     * @return the created {@link KPosition}
     */
    public static KPosition createLeftTopKPosition() {
        return setPositions(
                FACTORY.createKPosition(),
                FACTORY.createKLeftPosition(),
                FACTORY.createKTopPosition());
    }
    
    /**
     * Factory method creating a {@link KPosition} containing a {@link KRightPosition} and
     * {@link KBottomPosition}.
     * 
     * @return the created {@link KPosition}
     */
    public static KPosition createRightBottomKPosition() {
        return setPositions(
                FACTORY.createKPosition(),
                FACTORY.createKRightPosition(),
                FACTORY.createKBottomPosition());
    }


    /**
     * Convenience method ensuring a <code>null</code>-safe {@link KPosition} usage.
     * 
     * @param position
     *            the {@link KPosition} to be evaluated
     * @return the provided {@link KPosition} if non-<code>null</code> or {@link #LEFT_TOP_POS}
     */
    public static KPosition toNonNullLeftTopPosition(final KPosition position) {
        return position != null ? position : LEFT_TOP_POS;
    }

    /**
     * Convenience method ensuring a <code>null</code>-safe {@link KPosition} usage.
     * 
     * @param position
     *            the {@link KPosition} to be evaluated
     * @return the provided {@link KPosition} if non-<code>null</code> or {@link #RIGHT_BOTTOM_POS}
     */
    public static KPosition toNonNullRightBottomPosition(final KPosition position) {
        return position != null ? position : RIGHT_BOTTOM_POS;
    }
    
    /**
     * Convenience method ensuring a <code>null</code>-safe {@link KXPosition} usage.
     * 
     * @param position
     *            the {@link KXPosition} to be evaluated
     * @return the provided {@link KXPosition} if non-<code>null</code> or {@link #LEFT_TOP_POS}'s X
     *         component.
     */
    public static KXPosition<?> toNonNullLeftPosition(final KXPosition<?> position) {
        return position != null ? position : LEFT_TOP_POS.getX();
    }

    /**
     * Convenience method ensuring a <code>null</code>-safe {@link KXPosition} usage.
     * 
     * @param position
     *            the {@link KXPosition} to be evaluated
     * @return the provided {@link KXPosition} if non-<code>null</code> or {@link #RIGHT_BOTTOM_POS}
     *         's X component.
     */
    public static KXPosition<?> toNonNullRightPosition(final KXPosition<?> position) {
        return position != null ? position : RIGHT_BOTTOM_POS.getX();
    }

    /**
     * Convenience method ensuring a <code>null</code>-safe {@link KYPosition} usage.
     * 
     * @param position
     *            the {@link KYPosition} to be evaluated
     * @return the provided {@link KYPosition} if non-<code>null</code> or {@link #LEFT_TOP_POS}'s Y
     *         component.
     */
    public static KYPosition<?> toNonNullTopPosition(final KYPosition<?> position) {
        return position != null ? position : LEFT_TOP_POS.getY();
    }

    /**
     * Convenience method ensuring a <code>null</code>-safe {@link KYPosition} usage.
     * 
     * @param position
     *            the {@link KYPosition} to be evaluated
     * @return the provided {@link KYPosition} if non-<code>null</code> or {@link #RIGHT_BOTTOM_POS}
     *         's Y component.
     */
    public static KYPosition<?> toNonNullBottomPosition(final KYPosition<?> position) {
        return position != null ? position : RIGHT_BOTTOM_POS.getY();
    }


    /* ------------------------------------------------------- */
    /*    positioning helpers used by meta model operations    */
    /* ------------------------------------------------------- */
    
    /**
     * Convenience setter for configuring the components of {@link KPosition KPositions}.<br>
     * {@link KPosition#setPositions(KXPosition, KYPosition)} redirects to this method.
     * 
     * @param pos
     *            the {@link KPosition} to configure
     * @param xPart
     *            the horizontal component of the desired position
     * @param yPart
     *            the vertical component of the desired position
     * @return the provided <code>pos</code> for convenience
     */
    public static KPosition setPositions(final KPosition pos, final KXPosition<?> xPart,
            final KYPosition<?> yPart) {
        pos.setX(xPart);
        pos.setY(yPart);
        return pos;
    }

    /**
     * Tests the deep equality of two {@link KXPosition} objects.
     * 
     * @param it
     *            first operand
     * @param other
     *            second operand
     * @return <code>true</code> if both operands are deeply equal, <code>false</code> otherwise.
     */
    public static boolean equals(final KPosition it, final Object other) {
        if (it == other) {
            return true;
        } else if (it != null && other instanceof KPosition) {
            final KPosition position = (KPosition) other;
            return it.getClass() == position.getClass()
                    && equals(it.getX(), position.getY())
                    && equals(it.getY(), position.getY());
        } else {
            return false;
        }
    }

    /**
     * Convenience setter for configuring the position data of {@link KXPosition KXPositions}.<br>
     * {@link KXPosition#setPosition(float, float)} redirects to this method.
     * 
     * @param <T>
     *            the concrete type of the provided {@link KXPosition} 
     * @param xPos
     *            the {@link KXPosition} to configure
     * @param absolute
     *            the absolute component of the desired position
     * @param relative
     *            the relative component of the desired position in range of 0 to 1
     * @return the provided <code>xPos</code> for convenience
     */
    public static <T extends KXPosition<T>> T setPosition(final T xPos, final float absolute,
            final float relative) {
        xPos.setAbsolute(absolute);
        xPos.setRelative(relative);
        return xPos;
    }

    /**
     * Tests the deep equality of two {@link KPosition} objects.<br>
     * {@link KXPosition#equals(Object)} redirects to this method.
     * 
     * @param it
     *            first operand
     * @param other
     *            second operand
     * @return <code>true</code> if both operands are deeply equal, <code>false</code> otherwise.
     */
    public static boolean equals(final KXPosition<?> it, final Object other) {
        if (it == other) {
            return true;
        } else if (it != null && other instanceof KXPosition) {
            final KXPosition<?> xPos = (KXPosition<?>) other;
            return it.getClass() == other.getClass()
                    && it.getAbsolute() == xPos.getAbsolute()
                    && it.getRelative() == xPos.getRelative();
        } else {
            return false;
        }
    }

    /**
     * Convenience setter for configuring the position data of {@link KYPosition KYPositions}.<br>
     * {@link KYPosition#setPosition(float, float)} redirects to this method.
     * 
     * @param <T>
     *            the concrete type of the provided {@link KYPosition} 
     * @param yPos
     *            the {@link KYPosition} to configure
     * @param absolute
     *            the absolute component of the desired position
     * @param relative
     *            the relative component of the desired position in range of 0 to 1
     * @return the provided <code>xPos</code> for convenience
     */
    public static <T extends KYPosition<T>> T setPosition(final T yPos, final float absolute,
            final float relative) {
        yPos.setAbsolute(absolute);
        yPos.setRelative(relative);
        return yPos;
    }

    /**
     * Tests the deep equality of two {@link KYPosition} objects.<br>
     * {@link KYPosition#equals(Object)} redirects to this method.
     * 
     * @param it
     *            first operand
     * @param other
     *            second operand
     * @return <code>true</code> if both operands are deeply equal, <code>false</code> otherwise.
     */
    public static boolean equals(final KYPosition<?> it, final Object other) {
        if (it == other) {
            return true;
        } else if (it != null && other instanceof KYPosition) {
            final KYPosition<?> yPos = (KYPosition<?>) other;
            return it.getClass() == other.getClass()
                    && it.getAbsolute() == yPos.getAbsolute()
                    && it.getRelative() == yPos.getRelative();
        } else {
            return false;
        }
    }


    /* ---------------------------------------------------- */
    /*    coloring helpers used by meta model operations    */
    /* ---------------------------------------------------- */
    
    /**
     * Convenience setter for configuring the RGB values of {@link KColor KColors}.<br>
     * {@link KColor#setColor(int, int, int)} redirects to this method.
     * 
     * @param color
     *            the {@link KColor} to configure
     * @param red
     *            the red component of the desired color in range of 0 to 255
     * @param green
     *            the green component of the desired color in range of 0 to 255
     * @param blue
     *            the blue component of the desired color in range of 0 to 255
     * @return the provided <code>color</code> for convenience
     */
    public static KColor setColor(final KColor color, final int red,
            final int green, final int blue) {
        color.setRed(red);
        color.setGreen(green);
        color.setBlue(blue);
        return color;
    }
    
    /**
     * Convenience setter for configuring the RGB values of {@link KColor KColors}.<br>
     * {@link KColor#setColor(Colors)} redirects to this method.
     * 
     * @param color
     *            the {@link KColor} to configure
     * @param colorConstant
     *            a color constant from the {@link Colors} enumeration
     * @return the provided <code>color</code> for convenience
     */
    public static KColor setColor(final KColor color, final Colors colorConstant) {
        return setColor(color, colorConstant.getRed(), colorConstant.getGreen(),
                colorConstant.getBlue());
    }
    
    /**
     * Convenience setter for configuring the RGB values of {@link KColor KColors}.<br>
     * {@link KColor#setColor(KColor)} redirects to this method.
     * 
     * @param color
     *            the {@link KColor} to configure
     * @param kColor
     *            the {@link KColor} instance to take the RGB values from
     * @return the provided <code>color</code> for convenience
     */
    public static KColor setColor(final KColor color, final KColor kColor) {
        return setColor(color, kColor.getRed(), kColor.getBlue(), kColor.getGreen());
    }
    
    /**
     * Tests the equality of two {@link KColor} objects.<br>
     * {@link KColor#equals(Object)} redirects to this method.
     * 
     * @param it
     *            first operand
     * @param other
     *            second operand
     * @return <code>true</code> if the RGB components of both operands are equal,
     *         <code>false</code> otherwise.
     */
    public static boolean equals(final KColor it, final Object other) {
        if (it == other) {
            return true;
        } else if (it != null && other instanceof KColor) {
            final KColor color = (KColor) other;
            return it.getRed() == color.getRed()
                    && it.getGreen() == color.getGreen()
                    && it.getBlue() == color.getBlue();
        }
        return false;
    }

    /**
     * Convenience setter for configuring the color of {@link KColoring KColorings}.<br>
     * {@link KColoring#setColor(int, int, int)} redirects to this method.
     * 
     * @param <T>
     *            the concrete type of the provided {@link KColoring} 
     * @param coloring
     *            the {@link KColoring} to configure
     * @param red
     *            the red component of the desired color in range of 0 to 255
     * @param green
     *            the green component of the desired color in range of 0 to 255
     * @param blue
     *            the blue component of the desired color in range of 0 to 255
     * @return the provided <code>coloring</code> for convenience
     */
    public static <T extends KColoring<T>> T setColor(final T coloring, final int red,
            final int green, final int blue) {
        coloring.setColor(setColor(FACTORY.createKColor(), red, green, blue));
        return coloring;
    }
    
    /**
     * Convenience setter for configuring the color of {@link KColoring KColorings}.<br>
     * {@link KColoring#setColor(Colors)} redirects to this method.
     * 
     * @param <T>
     *            the concrete type of the provided {@link KColoring} 
     * @param coloring
     *            the {@link KColoring} to configure
     * @param color
     *            a color constant from the {@link Colors} enumeration
     * @return the provided <code>coloring</code> for convenience
     */
    public static <T extends KColoring<T>> T setColor(final T coloring, final Colors color) {
        coloring.setColor(setColor(FACTORY.createKColor(), color));
        return coloring;
    }
    
    /**
     * Convenience setter for configuring the color of {@link KColoring KColorings}.<br>
     * {@link KColoring#setColorCopyOf(KColor)} redirects to this method.
     * 
     * @param <T>
     *            the concrete type of the provided {@link KColoring} 
     * @param coloring
     *            the {@link KColoring} to configure
     * @param kColor
     *            the {@link KColor} instance to take the RGB values from
     * @return the provided <code>coloring</code> for convenience
     */
    public static <T extends KColoring<T>> T setColorCopyOf(final T coloring, final KColor kColor) {
        coloring.setColor(setColor(FACTORY.createKColor(), kColor));
        return coloring;
    }
    
    /**
     * Convenience setter for configuring the color of {@link KColoring KColorings}.<br>
     * {@link KColoring#setColor(int, int, int, int)} redirects to this method.
     * 
     * @param <T>
     *            the concrete type of the provided {@link KColoring} 
     * @param coloring
     *            the {@link KColoring} to configure
     * @param red
     *            the red component of the desired color in range of 0 to 255
     * @param green
     *            the green component of the desired color in range of 0 to 255
     * @param blue
     *            the blue component of the desired color in range of 0 to 255
     * @param alpha
     *            the alpha component of the desired color in range of 0 to 255
     * @return the provided <code>coloring</code> for convenience
     */
    public static <T extends KColoring<T>> T setColor(final T coloring, final int red,
            final int green, final int blue, final int alpha) {
        coloring.setColor(red, green, blue);
        coloring.setAlpha(alpha);
        return coloring;
    }
    
    /**
     * Convenience setter for configuring the color of {@link KColoring KColorings}.<br>
     * {@link KColoring#setColor(Colors)} redirects to this method.
     * 
     * @param <T>
     *            the concrete type of the provided {@link KColoring} 
     * @param coloring
     *            the {@link KColoring} to configure
     * @param color
     *            a color constant from the {@link Colors} enumeration
     * @param alpha
     *            the alpha component of the desired color in range of 0 to 255
     * @return the provided <code>coloring</code> for convenience
     */
    public static <T extends KColoring<T>> T setColor(final T coloring, final Colors color,
            final int alpha) {
        coloring.setColor(setColor(FACTORY.createKColor(), color));
        coloring.setAlpha(alpha);
        return coloring;
    }
    
    /**
     * Convenience setter for configuring the color of {@link KColoring KColorings}.<br>
     * {@link KColoring#setColorCopyOf(KColor, int)} redirects to this method.
     * 
     * @param <T>
     *            the concrete type of the provided {@link KColoring} 
     * @param coloring
     *            the {@link KColoring} to configure
     * @param kColor
     *            the {@link KColor} instance to take the RGB values from
     * @param alpha
     *            the alpha component of the desired color in range of 0 to 255
     * @return the provided <code>coloring</code> for convenience
     */
    public static <T extends KColoring<T>> T setColorCopyOf(final T coloring, final KColor kColor,
            final int alpha) {
        coloring.setColor(setColor(FACTORY.createKColor(), kColor));
        coloring.setAlpha(alpha);
        return coloring;
    }
    
    /**
     * Convenience setter for configuring the gradient target color of {@link KColoring KColorings}.<br>
     * {@link KColoring#setTargetColor(int, int, int)} redirects to this method.
     * 
     * @param <T>
     *            the concrete type of the provided {@link KColoring} 
     * @param coloring
     *            the {@link KColoring} to configure
     * @param red
     *            the red component of the desired color in range of 0 to 255
     * @param green
     *            the green component of the desired color in range of 0 to 255
     * @param blue
     *            the blue component of the desired color in range of 0 to 255
     * @return the provided <code>coloring</code> for convenience
     */
    public static <T extends KColoring<T>> T setTargetColor(final T coloring, final int red,
            final int green, final int blue) {
        coloring.setTargetColor(setColor(FACTORY.createKColor(), red, green, blue));
        return coloring;
    }
    
    /**
     * Convenience setter for configuring the gradient target color of {@link KColoring KColorings}.<br>
     * {@link KColoring#setTargetColor(Colors)} redirects to this method.
     * 
     * @param <T>
     *            the concrete type of the provided {@link KColoring}
     * @param coloring
     *            the {@link KColoring} to configure
     * @param color
     *            a color constant from the {@link Colors} enumeration
     * @return the provided <code>coloring</code> for convenience
     */
    public static <T extends KColoring<T>> T setTargetColor(final T coloring, final Colors color) {
        coloring.setTargetColor(setColor(FACTORY.createKColor(), color));
        return coloring;
    }
   
    /**
     * Convenience setter for configuring the gradient target color of {@link KColoring KColorings}.<br>
     * {@link KColoring#setTargetColorCopyOf(KColor)} redirects to this method.
     * 
     * @param <T>
     *            the concrete type of the provided {@link KColoring} 
     * @param coloring
     *            the {@link KColoring} to configure
     * @param kColor
     *            the {@link KColor} instance to take the RGB values from
     * @return the provided <code>coloring</code> for convenience
     */
    public static <T extends KColoring<T>> T setTargetColorCopyOf(final T coloring,
            final KColor kColor) {
        coloring.setTargetColor(setColor(FACTORY.createKColor(), kColor));
        return coloring;
    }

    /**
     * Convenience setter for configuring the gradient target color of {@link KColoring KColorings}.<br>
     * {@link KColoring#setTargetColor(int, int, int, int)} redirects to this method.
     * 
     * @param <T>
     *            the concrete type of the provided {@link KColoring} 
     * @param coloring
     *            the {@link KColoring} to configure
     * @param red
     *            the red component of the desired color in range of 0 to 255
     * @param green
     *            the green component of the desired color in range of 0 to 255
     * @param blue
     *            the blue component of the desired color in range of 0 to 255
     * @param alpha
     *            the alpha component of the desired color in range of 0 to 255
     * @return the provided <code>coloring</code> for convenience
     */
    public static <T extends KColoring<T>> T setTargetColor(final T coloring, final int red,
            final int green, final int blue, final int alpha) {
        coloring.setTargetColor(red, green, blue);
        coloring.setTargetAlpha(alpha);
        return coloring;
    }
    
    /**
     * Convenience setter for configuring the gradient target color of {@link KColoring KColorings}.<br>
     * {@link KColoring#setTargetColor(Colors, int)} redirects to this method.
     * 
     * @param <T>
     *            the concrete type of the provided {@link KColoring}
     * @param coloring
     *            the {@link KColoring} to configure
     * @param color
     *            a color constant from the {@link Colors} enumeration
     * @param alpha
     *            the alpha component of the desired color in range of 0 to 255
     * @return the provided <code>coloring</code> for convenience
     */
    public static <T extends KColoring<T>> T setTargetColor(final T coloring, final Colors color,
            final int alpha) {
        coloring.setTargetColor(setColor(FACTORY.createKColor(), color));
        coloring.setTargetAlpha(alpha);
        return coloring;
    }
    
    /**
     * Convenience setter for configuring the gradient target color of {@link KColoring KColorings}.<br>
     * {@link KColoring#setTargetColorCopyOf(KColor, int)} redirects to this method.
     * 
     * @param <T>
     *            the concrete type of the provided {@link KColoring} 
     * @param coloring
     *            the {@link KColoring} to configure
     * @param kColor
     *            the {@link KColor} instance to take the RGB values from
     * @param alpha
     *            the alpha component of the desired color in range of 0 to 255
     * @return the provided <code>coloring</code> for convenience
     */
    public static <T extends KColoring<T>> T setTargetColorCopyOf(final T coloring,
            final KColor kColor, final int alpha) {
        coloring.setTargetColor(setColor(FACTORY.createKColor(), kColor));
        coloring.setAlpha(alpha);
        return coloring;
    }
    
    /**
     * Tests the equality of two {@link KColor} objects.<br>
     * {@link KColor#equals(Object)} redirects to this method.
     * 
     * @param it
     *            first operand
     * @param other
     *            second operand
     * @return <code>true</code> if the RGB components of both operands are equal,
     *         <code>false</code> otherwise.
     */
    public static boolean equals(final KColoring<?> it, final Object other) {
        if (it == other) {
            return true;
        } else if (it != null && other instanceof KColoring<?>) {
            final KColoring<?> coloring = (KColoring<?>) other;
            return it.getClass() == coloring.getClass()
                    && it.getAlpha() == coloring.getAlpha()
                    && it.getTargetAlpha() == coloring.getTargetAlpha()
                    && equals(it.getColor(), coloring.getColor())
                    && equals(it.getTargetColor(), coloring.getTargetColor());
        } else {
            return false;
        }
    }
        
    /**
     * Convenient creation of color objects. Allows several names (red, blue, black, etc) and hex
     * strings (#00ff00).
     * 
     * @param colorString
     *            either a name or a css style hex string.
     * @return a corresponding {@link KColor} instance.
     */
    public static KColor getColor(final String colorString) {
        // SUPPRESS CHECKSTYLE NEXT 50 MagicNumber
        // if a rgb color is given, convert it
        if (colorString.startsWith("#")) {

            KColor kcolor = KRenderingFactory.eINSTANCE.createKColor();
            try {
                if (colorString.length() == 4) {
                    // CSS style short color format
                    String r = colorString.substring(1, 2);
                    kcolor.setRed(Integer.valueOf(r + r, 16));
                    String g = colorString.substring(2, 3);
                    kcolor.setGreen(Integer.valueOf(g + g, 16));
                    String b = colorString.substring(3, 4);
                    kcolor.setBlue(Integer.valueOf(b + b, 16));
                    return kcolor;
                }
                kcolor.setRed(Integer.valueOf(colorString.substring(1, 3), 16));
                kcolor.setGreen(Integer.valueOf(colorString.substring(3, 5), 16));
                kcolor.setBlue(Integer.valueOf(colorString.substring(5, 7), 16));
                return kcolor;
            } catch (Exception nfe) {
                // silent
            }

        } else {

            // from name
            Colors c = Colors.getColorByName(colorString);
            if (c != null) {
                return KRenderingFactory.eINSTANCE.createKColor().setColor(c);
            }
        }

        return null;
    }
}
