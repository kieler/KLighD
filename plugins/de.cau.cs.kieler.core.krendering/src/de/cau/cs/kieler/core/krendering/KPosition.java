/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.core.krendering;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KPosition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KPosition#getX <em>X</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KPosition#getY <em>Y</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPosition()
 * @model
 * @generated
 */
public interface KPosition extends EObject {
    /**
     * Returns the value of the '<em><b>X</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>X</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>X</em>' containment reference.
     * @see #setX(KXPosition)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPosition_X()
     * @model containment="true" required="true"
     * @generated
     */
    KXPosition getX();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KPosition#getX <em>X</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>X</em>' containment reference.
     * @see #getX()
     * @generated
     */
    void setX(KXPosition value);

    /**
     * Returns the value of the '<em><b>Y</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Y</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Y</em>' containment reference.
     * @see #setY(KYPosition)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPosition_Y()
     * @model containment="true" required="true"
     * @generated
     */
    KYPosition getY();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KPosition#getY <em>Y</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Y</em>' containment reference.
     * @see #getY()
     * @generated
     */
    void setY(KYPosition value);

} // KPosition
