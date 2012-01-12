/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KText</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link krendering.KText#getText <em>Text</em>}</li>
 *   <li>{@link krendering.KText#isClip <em>Clip</em>}</li>
 * </ul>
 * </p>
 *
 * @see krendering.KRenderingPackage#getKText()
 * @model
 * @generated
 */
public interface KText extends KContainerRendering {
    /**
     * Returns the value of the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Text</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Text</em>' attribute.
     * @see #setText(String)
     * @see krendering.KRenderingPackage#getKText_Text()
     * @model
     * @generated
     */
    String getText();

    /**
     * Sets the value of the '{@link krendering.KText#getText <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text</em>' attribute.
     * @see #getText()
     * @generated
     */
    void setText(String value);

    /**
     * Returns the value of the '<em><b>Clip</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Clip</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Clip</em>' attribute.
     * @see #setClip(boolean)
     * @see krendering.KRenderingPackage#getKText_Clip()
     * @model required="true"
     * @generated
     */
    boolean isClip();

    /**
     * Sets the value of the '{@link krendering.KText#isClip <em>Clip</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Clip</em>' attribute.
     * @see #isClip()
     * @generated
     */
    void setClip(boolean value);

} // KText
