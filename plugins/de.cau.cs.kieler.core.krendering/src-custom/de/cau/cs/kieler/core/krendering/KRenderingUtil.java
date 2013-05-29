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

/**
 * Collection of KRendering related convenience methods.
 * 
 * @author chsch
 */
public final class KRenderingUtil {

    private KRenderingUtil() {
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
    public static boolean equals(KPosition it, Object other) {
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
    public static boolean equals(KXPosition it, Object other) {
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
    public static boolean equals(KYPosition it, Object other) {
        return (it == other)
                || (it != null && other instanceof KYPosition
                    && it.getClass() == other.getClass()
                    && it.getAbsolute() == ((KYPosition) other).getAbsolute()
                    && it.getRelative() == ((KYPosition) other).getRelative());
    }
}
