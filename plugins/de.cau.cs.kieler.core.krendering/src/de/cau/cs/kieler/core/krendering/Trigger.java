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
 * A representation of the literals of the enumeration '<em><b>Trigger</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Trigger presets to determine when to execute actions.
 * <!-- end-model-doc -->
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getTrigger()
 * @model
 * @generated
 */
public enum Trigger implements Enumerator {
    /**
     * The '<em><b>SINGLECLICK</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SINGLECLICK_VALUE
     * @generated
     * @ordered
     */
    SINGLECLICK(0, "SINGLECLICK", "SINGLECLICK"),

    /**
     * The '<em><b>DOUBLECLICK</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DOUBLECLICK_VALUE
     * @generated
     * @ordered
     */
    DOUBLECLICK(1, "DOUBLECLICK", "DOUBLECLICK"), /**
     * The '<em><b>SINGLE OR MULTICLICK</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SINGLE_OR_MULTICLICK_VALUE
     * @generated
     * @ordered
     */
    SINGLE_OR_MULTICLICK(2, "SINGLE_OR_MULTICLICK", "SINGLE_OR_MULTICLICK"), /**
     * The '<em><b>MIDDLE SINGLECLICK</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MIDDLE_SINGLECLICK_VALUE
     * @generated
     * @ordered
     */
    MIDDLE_SINGLECLICK(3, "MIDDLE_SINGLECLICK", "MIDDLE_SINGLECLICK"), /**
     * The '<em><b>MIDDLE DOUBLECLICK</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MIDDLE_DOUBLECLICK_VALUE
     * @generated
     * @ordered
     */
    MIDDLE_DOUBLECLICK(4, "MIDDLE_DOUBLECLICK", "MIDDLE_DOUBLECLICK"), /**
     * The '<em><b>MIDDLE SINGLE OR MULTICLICK</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MIDDLE_SINGLE_OR_MULTICLICK_VALUE
     * @generated
     * @ordered
     */
    MIDDLE_SINGLE_OR_MULTICLICK(5, "MIDDLE_SINGLE_OR_MULTICLICK", "MIDDLE_SINGLE_OR_MULTICLICK");

    /**
     * The '<em><b>SINGLECLICK</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SINGLECLICK</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Fires on a left button's single click.&lt;br&gt;
     * Note: Corresponding actions are not fired on the first click of a double, tripple, ... click.
     * Thus, triggering the actions is delayed by the system wide double click period for assuring the absence of subsequent clicks.
     * <!-- end-model-doc -->
     * @see #SINGLECLICK
     * @model
     * @generated
     * @ordered
     */
    public static final int SINGLECLICK_VALUE = 0;

    /**
     * The '<em><b>DOUBLECLICK</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DOUBLECLICK</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Fires on left button's double (and more) click(s).
     * <!-- end-model-doc -->
     * @see #DOUBLECLICK
     * @model
     * @generated
     * @ordered
     */
    public static final int DOUBLECLICK_VALUE = 1;

    /**
     * The '<em><b>SINGLE OR MULTICLICK</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Fires on left button's first click regardless if more clicks follow within the system wide double click period.
     * <!-- end-model-doc -->
     * @see #SINGLE_OR_MULTICLICK
     * @model
     * @generated
     * @ordered
     */
    public static final int SINGLE_OR_MULTICLICK_VALUE = 2;

    /**
     * The '<em><b>MIDDLE SINGLECLICK</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Fires on middle button's single click.&lt;br&gt;
     * Note: Corresponding actions are not fired on the first click of a double, tripple, ... click.
     * Thus, triggering the actions is delayed by the system wide double click period for assuring the absence of subsequent clicks.
     * <!-- end-model-doc -->
     * @see #MIDDLE_SINGLECLICK
     * @model
     * @generated
     * @ordered
     */
    public static final int MIDDLE_SINGLECLICK_VALUE = 3;

    /**
     * The '<em><b>MIDDLE DOUBLECLICK</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Fires on middle button's double (and more) click(s).
     * <!-- end-model-doc -->
     * @see #MIDDLE_DOUBLECLICK
     * @model
     * @generated
     * @ordered
     */
    public static final int MIDDLE_DOUBLECLICK_VALUE = 4;

    /**
     * The '<em><b>MIDDLE SINGLE OR MULTICLICK</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Fires on middle button's first click regardless if more clicks follow within the system wide double click period.
     * <!-- end-model-doc -->
     * @see #MIDDLE_SINGLE_OR_MULTICLICK
     * @model
     * @generated
     * @ordered
     */
    public static final int MIDDLE_SINGLE_OR_MULTICLICK_VALUE = 5;

    /**
     * An array of all the '<em><b>Trigger</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final Trigger[] VALUES_ARRAY =
        new Trigger[] {
            SINGLECLICK,
            DOUBLECLICK,
            SINGLE_OR_MULTICLICK,
            MIDDLE_SINGLECLICK,
            MIDDLE_DOUBLECLICK,
            MIDDLE_SINGLE_OR_MULTICLICK,
        };

    /**
     * A public read-only list of all the '<em><b>Trigger</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<Trigger> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Trigger</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Trigger get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            Trigger result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Trigger</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Trigger getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            Trigger result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Trigger</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Trigger get(int value) {
        switch (value) {
            case SINGLECLICK_VALUE: return SINGLECLICK;
            case DOUBLECLICK_VALUE: return DOUBLECLICK;
            case SINGLE_OR_MULTICLICK_VALUE: return SINGLE_OR_MULTICLICK;
            case MIDDLE_SINGLECLICK_VALUE: return MIDDLE_SINGLECLICK;
            case MIDDLE_DOUBLECLICK_VALUE: return MIDDLE_DOUBLECLICK;
            case MIDDLE_SINGLE_OR_MULTICLICK_VALUE: return MIDDLE_SINGLE_OR_MULTICLICK;
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
    private Trigger(int value, String name, String literal) {
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
    
} //Trigger
