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
package de.cau.cs.kieler.klighd.krendering;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Line Join</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getLineJoin()
 * @model
 * @generated
 */
public enum LineJoin implements Enumerator {
    /**
     * The '<em><b>JOIN MITER</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #JOIN_MITER_VALUE
     * @generated
     * @ordered
     */
    JOIN_MITER(0, "JOIN_MITER", "JOIN_MITER"),

    /**
     * The '<em><b>JOIN ROUND</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #JOIN_ROUND_VALUE
     * @generated
     * @ordered
     */
    JOIN_ROUND(1, "JOIN_ROUND", "JOIN_ROUND"),

    /**
     * The '<em><b>JOIN BEVEL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #JOIN_BEVEL_VALUE
     * @generated
     * @ordered
     */
    JOIN_BEVEL(2, "JOIN_BEVEL", "JOIN_BEVEL");

    /**
     * The '<em><b>JOIN MITER</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>JOIN MITER</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #JOIN_MITER
     * @model
     * @generated
     * @ordered
     */
    public static final int JOIN_MITER_VALUE = 0;

    /**
     * The '<em><b>JOIN ROUND</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>JOIN ROUND</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #JOIN_ROUND
     * @model
     * @generated
     * @ordered
     */
    public static final int JOIN_ROUND_VALUE = 1;

    /**
     * The '<em><b>JOIN BEVEL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>JOIN BEVEL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #JOIN_BEVEL
     * @model
     * @generated
     * @ordered
     */
    public static final int JOIN_BEVEL_VALUE = 2;

    /**
     * An array of all the '<em><b>Line Join</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final LineJoin[] VALUES_ARRAY =
        new LineJoin[] {
            JOIN_MITER,
            JOIN_ROUND,
            JOIN_BEVEL,
        };

    /**
     * A public read-only list of all the '<em><b>Line Join</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<LineJoin> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Line Join</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LineJoin get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            LineJoin result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Line Join</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LineJoin getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            LineJoin result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Line Join</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LineJoin get(int value) {
        switch (value) {
            case JOIN_MITER_VALUE: return JOIN_MITER;
            case JOIN_ROUND_VALUE: return JOIN_ROUND;
            case JOIN_BEVEL_VALUE: return JOIN_BEVEL;
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
    private LineJoin(int value, String name, String literal) {
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
    
} //LineJoin
