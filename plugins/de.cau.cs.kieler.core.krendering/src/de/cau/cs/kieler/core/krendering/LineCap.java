/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
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
 * A representation of the literals of the enumeration '<em><b>Line Cap</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * LineCapStyles analog to SWT LineCapStyles
 * <!-- end-model-doc -->
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getLineCap()
 * @model
 * @generated
 */
public enum LineCap implements Enumerator {
    /**
     * The '<em><b>CAP FLAT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CAP_FLAT_VALUE
     * @generated
     * @ordered
     */
    CAP_FLAT(0, "CAP_FLAT", "CAP_FLAT"),

    /**
     * The '<em><b>CAP ROUND</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CAP_ROUND_VALUE
     * @generated
     * @ordered
     */
    CAP_ROUND(1, "CAP_ROUND", "CAP_ROUND"),

    /**
     * The '<em><b>CAP SQUARE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CAP_SQUARE_VALUE
     * @generated
     * @ordered
     */
    CAP_SQUARE(2, "CAP_SQUARE", "CAP_SQUARE");

    /**
     * The '<em><b>CAP FLAT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>CAP FLAT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CAP_FLAT
     * @model
     * @generated
     * @ordered
     */
    public static final int CAP_FLAT_VALUE = 0;

    /**
     * The '<em><b>CAP ROUND</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>CAP ROUND</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CAP_ROUND
     * @model
     * @generated
     * @ordered
     */
    public static final int CAP_ROUND_VALUE = 1;

    /**
     * The '<em><b>CAP SQUARE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>CAP SQUARE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CAP_SQUARE
     * @model
     * @generated
     * @ordered
     */
    public static final int CAP_SQUARE_VALUE = 2;

    /**
     * An array of all the '<em><b>Line Cap</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final LineCap[] VALUES_ARRAY =
        new LineCap[] {
            CAP_FLAT,
            CAP_ROUND,
            CAP_SQUARE,
        };

    /**
     * A public read-only list of all the '<em><b>Line Cap</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<LineCap> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Line Cap</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static LineCap get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            LineCap result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Line Cap</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static LineCap getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            LineCap result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Line Cap</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static LineCap get(int value) {
        switch (value) {
            case CAP_FLAT_VALUE: return CAP_FLAT;
            case CAP_ROUND_VALUE: return CAP_ROUND;
            case CAP_SQUARE_VALUE: return CAP_SQUARE;
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
    private LineCap(int value, String name, String literal) {
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
    
} //LineCap
