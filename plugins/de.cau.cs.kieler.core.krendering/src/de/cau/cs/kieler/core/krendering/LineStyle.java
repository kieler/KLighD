/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.core.krendering;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Line Style</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getLineStyle()
 * @model
 * @generated
 */
public enum LineStyle implements Enumerator {
    /**
     * The '<em><b>SOLID</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SOLID_VALUE
     * @generated
     * @ordered
     */
    SOLID(0, "SOLID", "SOLID"),

    /**
     * The '<em><b>DASH</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DASH_VALUE
     * @generated
     * @ordered
     */
    DASH(1, "DASH", "DASH"),

    /**
     * The '<em><b>DOT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DOT_VALUE
     * @generated
     * @ordered
     */
    DOT(2, "DOT", "DOT"),

    /**
     * The '<em><b>DASHDOT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DASHDOT_VALUE
     * @generated
     * @ordered
     */
    DASHDOT(3, "DASHDOT", "DASHDOT"),

    /**
     * The '<em><b>DASHDOTDOT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DASHDOTDOT_VALUE
     * @generated
     * @ordered
     */
    DASHDOTDOT(4, "DASHDOTDOT", "DASHDOTDOT");

    /**
     * The '<em><b>SOLID</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SOLID</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SOLID
     * @model
     * @generated
     * @ordered
     */
    public static final int SOLID_VALUE = 0;

    /**
     * The '<em><b>DASH</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DASH</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DASH
     * @model
     * @generated
     * @ordered
     */
    public static final int DASH_VALUE = 1;

    /**
     * The '<em><b>DOT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DOT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DOT
     * @model
     * @generated
     * @ordered
     */
    public static final int DOT_VALUE = 2;

    /**
     * The '<em><b>DASHDOT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DASHDOT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DASHDOT
     * @model
     * @generated
     * @ordered
     */
    public static final int DASHDOT_VALUE = 3;

    /**
     * The '<em><b>DASHDOTDOT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DASHDOTDOT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DASHDOTDOT
     * @model
     * @generated
     * @ordered
     */
    public static final int DASHDOTDOT_VALUE = 4;

    /**
     * An array of all the '<em><b>Line Style</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final LineStyle[] VALUES_ARRAY =
        new LineStyle[] {
            SOLID,
            DASH,
            DOT,
            DASHDOT,
            DASHDOTDOT,
        };

    /**
     * A public read-only list of all the '<em><b>Line Style</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<LineStyle> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Line Style</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static LineStyle get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            LineStyle result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Line Style</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static LineStyle getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            LineStyle result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Line Style</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static LineStyle get(int value) {
        switch (value) {
            case SOLID_VALUE: return SOLID;
            case DASH_VALUE: return DASH;
            case DOT_VALUE: return DOT;
            case DASHDOT_VALUE: return DASHDOT;
            case DASHDOTDOT_VALUE: return DASHDOTDOT;
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
    private LineStyle(int value, String name, String literal) {
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
    
} //LineStyle
