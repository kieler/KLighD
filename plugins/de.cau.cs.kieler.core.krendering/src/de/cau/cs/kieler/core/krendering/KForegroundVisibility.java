/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.core.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KForeground Visibility</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KForegroundVisibility#isVisible <em>Visible</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKForegroundVisibility()
 * @model
 * @generated
 */
public interface KForegroundVisibility extends KStyle {
    /**
     * Returns the value of the '<em><b>Visible</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Visible</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Visible</em>' attribute.
     * @see #setVisible(boolean)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKForegroundVisibility_Visible()
     * @model required="true"
     * @generated
     */
    boolean isVisible();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KForegroundVisibility#isVisible <em>Visible</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Visible</em>' attribute.
     * @see #isVisible()
     * @generated
     */
    void setVisible(boolean value);

} // KForegroundVisibility
