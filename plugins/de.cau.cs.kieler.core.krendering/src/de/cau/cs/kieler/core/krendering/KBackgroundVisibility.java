/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.core.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KBackground Visibility</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KBackgroundVisibility#isVisibility <em>Visibility</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKBackgroundVisibility()
 * @model
 * @generated
 */
public interface KBackgroundVisibility extends KStyle {
    /**
     * Returns the value of the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Visibility</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Visibility</em>' attribute.
     * @see #setVisibility(boolean)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKBackgroundVisibility_Visibility()
     * @model required="true"
     * @generated
     */
    boolean isVisibility();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KBackgroundVisibility#isVisibility <em>Visibility</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Visibility</em>' attribute.
     * @see #isVisibility()
     * @generated
     */
    void setVisibility(boolean value);

} // KBackgroundVisibility
