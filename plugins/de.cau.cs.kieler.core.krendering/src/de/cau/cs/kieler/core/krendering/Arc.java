/**
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
package de.cau.cs.kieler.core.krendering;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Arc</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The literals mirror the constants {@link java.awt.geom.Arc2D#OPEN}, {@link java.awt.geom.Arc2D#CHORD}, and {@link java.awt.geom.Arc2D#PIE}.
 * This is to be leveraged in implementation, so be careful while modifying this enumeration.
 * <!-- end-model-doc -->
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getArc()
 * @model
 * @generated
 */
public enum Arc implements Enumerator {
    /**
     * The '<em><b>OPEN</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OPEN_VALUE
     * @generated
     * @ordered
     */
    OPEN(0, "OPEN", "OPEN"),

    /**
     * The '<em><b>CHORD</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CHORD_VALUE
     * @generated
     * @ordered
     */
    CHORD(1, "CHORD", "CHORD"),

    /**
     * The '<em><b>PIE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PIE_VALUE
     * @generated
     * @ordered
     */
    PIE(2, "PIE", "PIE");

    /**
     * The '<em><b>OPEN</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * plain arc without any closing line connection from end to beginning
     * {@link Arc2D#OPEN}, {@link Arc2D#CHORD}, and
     *      *            {@link Arc2D#PIE}
     * <!-- end-model-doc -->
     * @see #OPEN
     * @model
     * @generated
     * @ordered
     */
    public static final int OPEN_VALUE = 0;

    /**
     * The '<em><b>CHORD</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * arc with a straight closing line connection from end to beginning via the arc's center
     * <!-- end-model-doc -->
     * @see #CHORD
     * @model
     * @generated
     * @ordered
     */
    public static final int CHORD_VALUE = 1;

    /**
     * The '<em><b>PIE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * arc with a straight closing line connection from end to beginning
     * <!-- end-model-doc -->
     * @see #PIE
     * @model
     * @generated
     * @ordered
     */
    public static final int PIE_VALUE = 2;

    /**
     * An array of all the '<em><b>Arc</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final Arc[] VALUES_ARRAY =
        new Arc[] {
            OPEN,
            CHORD,
            PIE,
        };

    /**
     * A public read-only list of all the '<em><b>Arc</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<Arc> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Arc</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Arc get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            Arc result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Arc</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Arc getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            Arc result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Arc</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Arc get(int value) {
        switch (value) {
            case OPEN_VALUE: return OPEN;
            case CHORD_VALUE: return CHORD;
            case PIE_VALUE: return PIE;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private Arc(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getValue() {
      return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
      return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLiteral() {
      return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }
    
} //Arc
