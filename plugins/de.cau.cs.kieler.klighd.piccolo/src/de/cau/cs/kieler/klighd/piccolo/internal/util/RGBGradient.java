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
package de.cau.cs.kieler.klighd.piccolo.internal.util;

import org.eclipse.swt.graphics.RGB;

/**
 * Descriptor class for color gradients in addition to {@link RGB} for colors.
 * 
 * @author chsch
 */
public final class RGBGradient {
    
    private RGB color1 = null;
    private int alpha1 = 0xFF; // SUPPRESS CHECKSTYLE MagicNumber
    private RGB color2 = null;
    private int alpha2 = 0xFF; // SUPPRESS CHECKSTYLE MagicNumber
    private float angle = 0;
    
    /**
     * Constructor.
     * 
     * @param color1 the start color
     * @param alpha1 the start color's alpha
     * @param color2 the target color
     * @param alpha2 the target color's alpha
     * @param angle the gradient's angle
     */
    public RGBGradient(final RGB color1, final int alpha1, final RGB color2, final int alpha2,
            final float angle) {
        this.color1 = color1;
        this.alpha1 = alpha1;
        this.color2 = color2;
        this.alpha2 = alpha2;
        this.angle = angle;
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean equals(final Object theOther) {
        if (theOther instanceof RGBGradient) {
            RGBGradient theOtherGradient = (RGBGradient) theOther;
            return this.color1.equals(theOtherGradient.color1)
                    && this.alpha1 == theOtherGradient.alpha1
                    && this.color2.equals(theOtherGradient.color2)
                    && this.alpha2 == theOtherGradient.alpha2
                    && this.angle == theOtherGradient.angle;
                    
        } else {
            return false;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * @return the color1
     */
    public RGB getColor1() {
        return color1;
    }

    /**
     * @param color1 the color1 to set
     */
    public void setColor1(final RGB color1) {
        this.color1 = color1;
    }

    /**
     * @return the alpha1
     */
    public int getAlpha1() {
        return alpha1;
    }

    /**
     * @param alpha1 the alpha1 to set
     */
    public void setAlpha1(final int alpha1) {
        this.alpha1 = alpha1;
    }

    /**
     * @return the color2
     */
    public RGB getColor2() {
        return color2;
    }

    /**
     * @param color2 the color2 to set
     */
    public void setColor2(final RGB color2) {
        this.color2 = color2;
    }

    /**
     * @return the alpha2
     */
    public int getAlpha2() {
        return alpha2;
    }

    /**
     * @param alpha2 the alpha2 to set
     */
    public void setAlpha2(final int alpha2) {
        this.alpha2 = alpha2;
    }

    /**
     * @return the angle
     */
    public float getAngle() {
        return angle;
    }

    /**
     * @param angle the angle to set
     */
    public void setAngle(final float angle) {
        this.angle = angle;
    }
    
}
