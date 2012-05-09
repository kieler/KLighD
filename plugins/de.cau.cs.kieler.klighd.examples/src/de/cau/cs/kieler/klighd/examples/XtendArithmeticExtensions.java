/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.examples;

/**
 * An extension library contributing basic arithmetic operations to Xtend2.
 * 
 * @author haf, chsch
 */
public class XtendArithmeticExtensions {

    // CHECKSTYLEOFF Method
    
    public Float operator_plus(final Float e1, final Float e2) {
        return e1 + e2;
    }

    public Float operator_minus(final Float e1, final Float e2) {
        return e1 - e2;
    }

    public Float operator_multiply(final Float e1, final Float e2) {
        return e1 * e2;
    }

    public Float operator_divide(final Float e1, final Float e2) {
        return e1 / e2;
    }

    public Float operator_power(final Float e1, final Float e2) {
        return (float) Math.pow(e1, e2);
    }

    public Double operator_plus(final Double e1, final Double e2) {
        return e1 + e2;
    }

    public Double operator_minus(final Double e1, final Double e2) {
        return e1 - e2;
    }

    public Double operator_multiply(final Double e1, final Double e2) {
        return e1 * e2;
    }

    public Double operator_divide(final Double e1, final Double e2) {
        return e1 / e2;
    }

    public Double operator_power(final Double e1, final Double e2) {
        return Math.pow(e1, e2);
    }

    public Long operator_plus(final Long e1, final Long e2) {
        return e1 + e2;
    }

    public Long operator_minus(final Long e1, final Long e2) {
        return e1 - e2;
    }

    public Long operator_multiply(final Long e1, final Long e2) {
        return e1 * e2;
    }

    public Long operator_divide(final Long e1, final Long e2) {
        return e1 / e2;
    }

    public Double operator_divide(final Long e1, final Double e2) {
        return e1 / e2;
    }

    public Long operator_power(final Long e1, final Long e2) {
        return (long) Math.pow(e1, e2);
    }
    
    // CHECKSTYLEON Method
}
