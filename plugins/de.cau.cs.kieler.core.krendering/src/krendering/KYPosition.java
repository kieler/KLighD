/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package krendering;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KY Position</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link krendering.KYPosition#getAbsolute <em>Absolute</em>}</li>
 *   <li>{@link krendering.KYPosition#getRelative <em>Relative</em>}</li>
 * </ul>
 * </p>
 *
 * @see krendering.KRenderingPackage#getKYPosition()
 * @model abstract="true"
 * @generated
 */
public interface KYPosition extends EObject {
    /**
     * Returns the value of the '<em><b>Absolute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Absolute</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Absolute</em>' attribute.
     * @see #setAbsolute(float)
     * @see krendering.KRenderingPackage#getKYPosition_Absolute()
     * @model
     * @generated
     */
    float getAbsolute();

    /**
     * Sets the value of the '{@link krendering.KYPosition#getAbsolute <em>Absolute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Absolute</em>' attribute.
     * @see #getAbsolute()
     * @generated
     */
    void setAbsolute(float value);

    /**
     * Returns the value of the '<em><b>Relative</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Relative</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Relative</em>' attribute.
     * @see #setRelative(float)
     * @see krendering.KRenderingPackage#getKYPosition_Relative()
     * @model
     * @generated
     */
    float getRelative();

    /**
     * Sets the value of the '{@link krendering.KYPosition#getRelative <em>Relative</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Relative</em>' attribute.
     * @see #getRelative()
     * @generated
     */
    void setRelative(float value);

} // KYPosition
