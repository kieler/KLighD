/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package krendering;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Vertical Alignment</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see krendering.KRenderingPackage#getVerticalAlignment()
 * @model
 * @generated
 */
public enum VerticalAlignment implements Enumerator {
    /**
     * The '<em><b>TOP</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TOP_VALUE
     * @generated
     * @ordered
     */
    TOP(0, "TOP", "TOP"),

    /**
     * The '<em><b>CENTER</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CENTER_VALUE
     * @generated
     * @ordered
     */
    CENTER(1, "CENTER", "CENTER"),

    /**
     * The '<em><b>BOTTOM</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #BOTTOM_VALUE
     * @generated
     * @ordered
     */
    BOTTOM(2, "BOTTOM", "BOTTOM");

    /**
     * The '<em><b>TOP</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>TOP</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #TOP
     * @model
     * @generated
     * @ordered
     */
    public static final int TOP_VALUE = 0;

    /**
     * The '<em><b>CENTER</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>CENTER</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CENTER
     * @model
     * @generated
     * @ordered
     */
    public static final int CENTER_VALUE = 1;

    /**
     * The '<em><b>BOTTOM</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>BOTTOM</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #BOTTOM
     * @model
     * @generated
     * @ordered
     */
    public static final int BOTTOM_VALUE = 2;

    /**
     * An array of all the '<em><b>Vertical Alignment</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final VerticalAlignment[] VALUES_ARRAY =
        new VerticalAlignment[] {
            TOP,
            CENTER,
            BOTTOM,
        };

    /**
     * A public read-only list of all the '<em><b>Vertical Alignment</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<VerticalAlignment> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Vertical Alignment</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static VerticalAlignment get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            VerticalAlignment result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Vertical Alignment</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static VerticalAlignment getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            VerticalAlignment result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Vertical Alignment</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static VerticalAlignment get(int value) {
        switch (value) {
            case TOP_VALUE: return TOP;
            case CENTER_VALUE: return CENTER;
            case BOTTOM_VALUE: return BOTTOM;
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
    private VerticalAlignment(int value, String name, String literal) {
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
    
} //VerticalAlignment
