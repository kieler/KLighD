/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.core.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KFilled</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KFilled#isFilled <em>Filled</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKFilled()
 * @model
 * @generated
 */
public interface KFilled extends KStyle {
    /**
     * Returns the value of the '<em><b>Filled</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filled</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Filled</em>' attribute.
     * @see #setFilled(boolean)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKFilled_Filled()
     * @model required="true"
     * @generated
     */
    boolean isFilled();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KFilled#isFilled <em>Filled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Filled</em>' attribute.
     * @see #isFilled()
     * @generated
     */
    void setFilled(boolean value);

} // KFilled
