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
package de.cau.cs.kieler.core.krendering;

import static com.google.common.collect.Iterators.concat;
import static com.google.common.collect.Iterators.emptyIterator;
import static com.google.common.collect.Iterators.singletonIterator;
import static com.google.common.collect.Iterators.transform;

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
        final KPosition result = FACTORY.createKPosition();
        result.setX(FACTORY.createKLeftPosition());
        result.setY(FACTORY.createKTopPosition());
        return result;
    }
    
    /**
     * Factory method creating a {@link KPosition} containing a {@link KRightPosition} and
     * {@link KBottomPosition}.
     * 
     * @return the created {@link KPosition}
     */
    public static KPosition createRightBottomKPosition() {
        final KPosition result = FACTORY.createKPosition();
        result.setX(FACTORY.createKRightPosition());
        result.setY(FACTORY.createKBottomPosition());
        return result;
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
    public static KXPosition toNonNullLeftPosition(final KXPosition position) {
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
    public static KXPosition toNonNullRightPosition(final KXPosition position) {
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
    public static KYPosition toNonNullTopPosition(final KYPosition position) {
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
    public static KYPosition toNonNullBottomPosition(final KYPosition position) {
        return position != null ? position : RIGHT_BOTTOM_POS.getY();
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
        return (it == other)
                || (it != null && other instanceof KPosition
                        && equals(it.getX(), ((KPosition) other).getX())
                        && equals(it.getY(), ((KPosition) other).getY()));
    }

    /**
     * Tests the deep equality of two {@link KPosition} objects.
     * 
     * @param it
     *            first operand
     * @param other
     *            second operand
     * @return <code>true</code> if both operands are deeply equal, <code>false</code> otherwise.
     */
    public static boolean equals(final KXPosition it, final Object other) {
        return (it == other)
                || (it != null && other instanceof KXPosition
                    && it.getClass() == other.getClass()
                    && it.getAbsolute() == ((KXPosition) other).getAbsolute()
                    && it.getRelative() == ((KXPosition) other).getRelative());
    }

    /**
     * Tests the deep equality of two {@link KYPosition} objects.
     * 
     * @param it
     *            first operand
     * @param other
     *            second operand
     * @return <code>true</code> if both operands are deeply equal, <code>false</code> otherwise.
     */
    public static boolean equals(final KYPosition it, final Object other) {
        return (it == other)
                || (it != null && other instanceof KYPosition
                    && it.getClass() == other.getClass()
                    && it.getAbsolute() == ((KYPosition) other).getAbsolute()
                    && it.getRelative() == ((KYPosition) other).getRelative());
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
            children = emptyIterator();
        }
        return concat(singletonIterator(rendering), children);
    }
}
