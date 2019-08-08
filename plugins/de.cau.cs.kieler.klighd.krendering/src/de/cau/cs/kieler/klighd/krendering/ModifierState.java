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
 * A representation of the literals of the enumeration '<em><b>Modifier State</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The state of a modifier that it has to be in in order for some action to be performed.
 * <!-- end-model-doc -->
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getModifierState()
 * @model
 * @generated
 */
public enum ModifierState implements Enumerator {
    /**
     * The '<em><b>DONT CARE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DONT_CARE_VALUE
     * @generated
     * @ordered
     */
    DONT_CARE(0, "DONT_CARE", "DONT_CARE"),

    /**
     * The '<em><b>PRESSED</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PRESSED_VALUE
     * @generated
     * @ordered
     */
    PRESSED(1, "PRESSED", "PRESSED"),

    /**
     * The '<em><b>NOT PRESSED</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NOT_PRESSED_VALUE
     * @generated
     * @ordered
     */
    NOT_PRESSED(2, "NOT_PRESSED", "NOT_PRESSED");

    /**
     * The '<em><b>DONT CARE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DONT_CARE
     * @model
     * @generated
     * @ordered
     */
    public static final int DONT_CARE_VALUE = 0;

    /**
     * The '<em><b>PRESSED</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PRESSED
     * @model
     * @generated
     * @ordered
     */
    public static final int PRESSED_VALUE = 1;

    /**
     * The '<em><b>NOT PRESSED</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NOT_PRESSED
     * @model
     * @generated
     * @ordered
     */
    public static final int NOT_PRESSED_VALUE = 2;

    /**
     * An array of all the '<em><b>Modifier State</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final ModifierState[] VALUES_ARRAY =
        new ModifierState[] {
            DONT_CARE,
            PRESSED,
            NOT_PRESSED,
        };

    /**
     * A public read-only list of all the '<em><b>Modifier State</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<ModifierState> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Modifier State</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ModifierState get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ModifierState result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Modifier State</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ModifierState getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ModifierState result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Modifier State</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ModifierState get(int value) {
        switch (value) {
            case DONT_CARE_VALUE: return DONT_CARE;
            case PRESSED_VALUE: return PRESSED;
            case NOT_PRESSED_VALUE: return NOT_PRESSED;
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
    private ModifierState(int value, String name, String literal) {
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
    
} //ModifierState
