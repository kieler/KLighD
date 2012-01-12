/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KRounded Rectangle</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link krendering.KRoundedRectangle#getCornerWidth <em>Corner Width</em>}</li>
 *   <li>{@link krendering.KRoundedRectangle#getCornerHeight <em>Corner Height</em>}</li>
 * </ul>
 * </p>
 *
 * @see krendering.KRenderingPackage#getKRoundedRectangle()
 * @model
 * @generated
 */
public interface KRoundedRectangle extends KContainerRendering {
    /**
     * Returns the value of the '<em><b>Corner Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Corner Width</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Corner Width</em>' attribute.
     * @see #setCornerWidth(float)
     * @see krendering.KRenderingPackage#getKRoundedRectangle_CornerWidth()
     * @model required="true"
     * @generated
     */
    float getCornerWidth();

    /**
     * Sets the value of the '{@link krendering.KRoundedRectangle#getCornerWidth <em>Corner Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Corner Width</em>' attribute.
     * @see #getCornerWidth()
     * @generated
     */
    void setCornerWidth(float value);

    /**
     * Returns the value of the '<em><b>Corner Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Corner Height</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Corner Height</em>' attribute.
     * @see #setCornerHeight(float)
     * @see krendering.KRenderingPackage#getKRoundedRectangle_CornerHeight()
     * @model required="true"
     * @generated
     */
    float getCornerHeight();

    /**
     * Sets the value of the '{@link krendering.KRoundedRectangle#getCornerHeight <em>Corner Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Corner Height</em>' attribute.
     * @see #getCornerHeight()
     * @generated
     */
    void setCornerHeight(float value);

} // KRoundedRectangle
