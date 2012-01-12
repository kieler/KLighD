/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KColor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link krendering.KColor#getRed <em>Red</em>}</li>
 *   <li>{@link krendering.KColor#getGreen <em>Green</em>}</li>
 *   <li>{@link krendering.KColor#getBlue <em>Blue</em>}</li>
 * </ul>
 * </p>
 *
 * @see krendering.KRenderingPackage#getKColor()
 * @model abstract="true"
 * @generated
 */
public interface KColor extends KStyle {
    /**
     * Returns the value of the '<em><b>Red</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Red</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Red</em>' attribute.
     * @see #setRed(int)
     * @see krendering.KRenderingPackage#getKColor_Red()
     * @model required="true"
     * @generated
     */
    int getRed();

    /**
     * Sets the value of the '{@link krendering.KColor#getRed <em>Red</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Red</em>' attribute.
     * @see #getRed()
     * @generated
     */
    void setRed(int value);

    /**
     * Returns the value of the '<em><b>Green</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Green</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Green</em>' attribute.
     * @see #setGreen(int)
     * @see krendering.KRenderingPackage#getKColor_Green()
     * @model required="true"
     * @generated
     */
    int getGreen();

    /**
     * Sets the value of the '{@link krendering.KColor#getGreen <em>Green</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Green</em>' attribute.
     * @see #getGreen()
     * @generated
     */
    void setGreen(int value);

    /**
     * Returns the value of the '<em><b>Blue</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Blue</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Blue</em>' attribute.
     * @see #setBlue(int)
     * @see krendering.KRenderingPackage#getKColor_Blue()
     * @model required="true"
     * @generated
     */
    int getBlue();

    /**
     * Sets the value of the '{@link krendering.KColor#getBlue <em>Blue</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Blue</em>' attribute.
     * @see #getBlue()
     * @generated
     */
    void setBlue(int value);

} // KColor
