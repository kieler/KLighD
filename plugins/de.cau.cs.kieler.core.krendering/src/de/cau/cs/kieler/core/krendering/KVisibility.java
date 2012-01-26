/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.core.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KVisibility</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KVisibility#isLineVisible <em>Line Visible</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KVisibility#isFilled <em>Filled</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKVisibility()
 * @model
 * @generated
 */
public interface KVisibility extends KStyle {
    /**
     * Returns the value of the '<em><b>Line Visible</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Visible</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Line Visible</em>' attribute.
     * @see #setLineVisible(boolean)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKVisibility_LineVisible()
     * @model required="true"
     * @generated
     */
    boolean isLineVisible();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KVisibility#isLineVisible <em>Line Visible</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Line Visible</em>' attribute.
     * @see #isLineVisible()
     * @generated
     */
    void setLineVisible(boolean value);

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
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKVisibility_Filled()
     * @model required="true"
     * @generated
     */
    boolean isFilled();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KVisibility#isFilled <em>Filled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Filled</em>' attribute.
     * @see #isFilled()
     * @generated
     */
    void setFilled(boolean value);

} // KVisibility
