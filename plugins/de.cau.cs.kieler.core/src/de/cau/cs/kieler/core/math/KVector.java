/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.math;

import java.util.Random;
import java.util.StringTokenizer;

import de.cau.cs.kieler.core.util.IDataObject;

/**
 * A simple 2D vector class which supports translation, scaling, normalization etc.
 * 
 * @kieler.rating 2011-01-13 proposed yellow msp
 * @author uru
 * @author owo
 */
public class KVector implements IDataObject, Cloneable {

    /** the serial version UID. */
    private static final long serialVersionUID = -4780985519832787684L;

    // CHECKSTYLEOFF VisibilityModifier
    /** x coordinate. */
    public double x;
    /** y coordinate. */
    public double y;
    // CHECKSTYLEON VisibilityModifier

    /** one full turn in a circle in degrees (360°). */
    public static final double FULL_CIRCLE = 360;

    /**
     * Create vector with default coordinates (0,0).
     */
    public KVector() {
        this.x = 0.0;
        this.y = 0.0;
    }

    /**
     * Constructs a new vector from given values.
     * 
     * @param thex
     *            x value
     * @param they
     *            y value
     */
    public KVector(final double thex, final double they) {
        this.x = thex;
        this.y = they;
    }

    /**
     * Creates an exact copy of a given vector v.
     * 
     * @param v
     *            existing vector
     */
    public KVector(final KVector v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Creates a normalized vector for the passed angle in degree.
     * 
     * @param alpha
     *            angle in [0, 360)
     */
    public KVector(final double alpha) {
        if (alpha < 0 || alpha >= FULL_CIRCLE) {
            throw new IllegalArgumentException(
                    "Value for angle has to be within [0, 360)! Given Value: " + alpha);
        }

        double rad = Math.toRadians(alpha);
        this.x = Math.sin(rad);
        this.y = Math.cos(rad);
    }

    /**
     * returns an exact copy of this vector.
     * 
     * @return identical vector
     */
    @Override
    public KVector clone() {
        return new KVector(this.x, this.y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof KVector) {
            KVector other = (KVector) obj;
            return this.x == other.x && this.y == other.y;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Double.valueOf(x).hashCode() + Integer.reverse(Double.valueOf(y).hashCode());
    }

    /**
     * returns this vector's length.
     * 
     * @return Math.sqrt(x*x + y*y)
     */
    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * returns square length of this vector.
     * 
     * @return x*x + y*y
     */
    public double getSquareLength() {
        return x * x + y * y;
    }

    /**
     * Set vector to (0,0).
     * 
     * @return {@code this}
     */
    public final KVector reset() {
        this.x = 0.0;
        this.y = 0.0;
        return this;
    }

    /**
     * Vector addition.
     * 
     * @param v
     *            vector to add
     * @return <code>this + v</code>
     */
    public final KVector add(final KVector v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    /**
     * Returns the sum of arbitrarily many vectors as a new vector instance.
     * 
     * @param vs vectors to be added
     * @return a new vector containing the sum of given vectors
     */
    public static KVector sum(final KVector... vs) {
        KVector sum = new KVector();
        for (KVector v : vs) {
            sum.x += v.x;
            sum.y += v.y;
        }
        return sum;
    }

    /**
     * Vector subtraction.
     * 
     * @param v
     *            vector to subtract
     * @return {@code this}
     */
    public final KVector sub(final KVector v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    /**
     * Returns the subtraction of the two given vectors as a new vector instance.
     * 
     * @param v1
     *            first vector
     * @param v2
     *            second vector
     * @return new vector first - second
     */
    public static KVector diff(final KVector v1, final KVector v2) {
        return new KVector(v1.x - v2.x, v1.y - v2.y);
    }

    /**
     * Scale the vector.
     * 
     * @param scale
     *            scaling factor
     * @return {@code this}
     */
    public final KVector scale(final double scale) {
        this.x *= scale;
        this.y *= scale;
        return this;
    }

    /**
     * Scale the vector with different values for X and Y coordinate.
     * 
     * @param scalex
     *            the x scaling factor
     * @param scaley
     *            the y scaling factor
     * @return {@code this}
     */
    public final KVector scale(final double scalex, final double scaley) {
        this.x *= scalex;
        this.y *= scaley;
        return this;
    }

    /**
     * Translate the vector.
     * 
     * @param dx
     *            the x offset
     * @param dy
     *            the y offset
     * @return {@code this}
     */
    public final KVector translate(final double dx, final double dy) {
        this.x += dx;
        this.y += dy;
        return this;
    }

    /**
     * Normalize the vector.
     * 
     * @return {@code this}
     */
    public KVector normalize() {
        double length = this.getLength();
        if (length > 0) {
            this.x /= length;
            this.y /= length;
        }
        return this;
    }

    /**
     * scales this vector to the passed length.
     * 
     * @param length
     *            length to scale to
     * @return {@code this}
     */
    public KVector scaleToLength(final double length) {
        this.normalize();
        this.scale(length);
        return this;
    }

    /**
     * Negate the vector.
     * 
     * @return {@code this}
     */
    public KVector negate() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    /**
     * Returns degree representation of this vector in degree.
     * 
     * @return value within [0,360)
     */
    public double toDegrees() {
        KVector temp = this.clone();
        temp.normalize();
        double sin = Math.toDegrees(Math.asin(temp.x));
        double cos = Math.toDegrees(Math.acos(temp.y));

        if (y < 0 && x < 0) {
            return (FULL_CIRCLE / 2) - sin;
        } else if (y < 0) {
            return cos;
        } else if (x < 0) {
            return FULL_CIRCLE + sin;
        } else {
            return cos;
        }
    }

    /**
     * Add some "noise" to this vector.
     * 
     * @param random
     *            the random number generator
     * @param amount
     *            the amount of noise to add
     */
    public final void wiggle(final Random random, final double amount) {
        this.x += random.nextDouble() * amount - (amount / 2);
        this.y += random.nextDouble() * amount - (amount / 2);
    }

    /**
     * Create a scaled version of this vector.
     * 
     * @param lambda
     *            scaling factor
     * @return new vector which is {@code this} scaled by {@code lambda}
     */
    public final KVector scaledCreate(final double lambda) {
        return new KVector(this).scale(lambda);
    }

    /**
     * Create a normalized version of this vector.
     * 
     * @return normalized copy of {@code this}
     */
    public final KVector normalizedCreate() {
        return new KVector(this).normalize();
    }

    /**
     * Create a sum from this vector and another vector.
     * 
     * @param v
     *            second addend
     * @return new vector which is the sum of {@code this} and {@code v}
     */
    public final KVector sumCreate(final KVector v) {
        return new KVector(this).add(v);
    }

    /**
     * Create a difference from this vector and another vector.
     * 
     * @param v
     *            subtrahend
     * @return new vector which is the difference between {@code this} and {@code v}
     */
    public final KVector differenceCreate(final KVector v) {
        return new KVector(this).sub(v);
    }

    /**
     * Returns the distance between two vectors.
     * 
     * @param v2
     *            second vector
     * @return distance between this and second vector
     */
    public double distance(final KVector v2) {
        double dx = this.x - v2.x;
        double dy = this.y - v2.y;
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Returns the distance between two vectors.
     * 
     * @param v1
     *            first vector
     * @param v2
     *            second vector
     * @return distance between first and second
     */
    public static double distance(final KVector v1, final KVector v2) {
        double dx = v1.x - v2.x;
        double dy = v1.y - v2.y;
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Returns the dot product of the two given vectors.
     * 
     * @param v2
     *            second vector
     * @return (this.x * this.x) + (v1.y * v2.y)
     */
    public double productDot(final KVector v2) {
        return ((this.x * v2.x) + (this.y * v2.y));
    }

    /**
     * Returns the dot product of the two given vectors.
     * 
     * @param v1
     *            first vector
     * @param v2
     *            second vector
     * @return (this.x * this.x) + (v1.y * v2.y)
     */
    public static double productDot(final KVector v1, final KVector v2) {
        return ((v1.x * v2.x) + (v1.y * v2.y));
    }

    /**
     * Apply the given bounds to this vector.
     * 
     * @param lowx
     *            the lower bound for x coordinate
     * @param lowy
     *            the lower bound for y coordinate
     * @param highx
     *            the upper bound for x coordinate
     * @param highy
     *            the upper bound for y coordinate
     * @return {@code this}
     * @throws IllegalArgumentException
     *             if highx < lowx or highy < lowy
     */
    public KVector applyBounds(final double lowx, final double lowy, final double highx,
            final double highy) {
        if (highx < lowx || highy < lowy) {
            throw new IllegalArgumentException(
                    "The highx must be bigger then lowx and the highy must be bigger then lowy");
        }
        if (x < lowx) {
            x = lowx;
        } else if (x > highx) {
            x = highx;
        }
        if (y < lowy) {
            y = lowy;
        } else if (y > highy) {
            y = highy;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public void parse(final String string) {
        int start = 0;
        while (start < string.length() && isdelim(string.charAt(start), "([{\"' \t\r\n")) {
            start++;
        }
        int end = string.length();
        while (end > 0 && isdelim(string.charAt(end - 1), ")]}\"' \t\r\n")) {
            end--;
        }
        if (start >= end) {
            throw new IllegalArgumentException("The given string does not contain any numbers.");
        }
        StringTokenizer tokenizer = new StringTokenizer(string.substring(start, end), ",; \t\r\n");
        if (tokenizer.countTokens() != 2) {
            throw new IllegalArgumentException("Exactly two numbers are expected, "
                    + tokenizer.countTokens() + " were found.");
        }
        try {
            x = Double.parseDouble(tokenizer.nextToken());
            y = Double.parseDouble(tokenizer.nextToken());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "The given string contains parts that cannot be parsed as numbers." + exception);
        }
    }

    /**
     * Determine whether the given character is a delimiter.
     * 
     * @param c
     *            a character
     * @param delims
     *            a string of possible delimiters
     * @return true if {@code c} is one of the characters in {@code delims}
     */
    private static boolean isdelim(final char c, final String delims) {
        for (int i = 0; i < delims.length(); i++) {
            if (c == delims.charAt(i)) {
                return true;
            }
        }
        return false;
    }

}
