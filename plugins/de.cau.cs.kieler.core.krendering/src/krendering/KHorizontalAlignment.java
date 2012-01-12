/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KHorizontal Alignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link krendering.KHorizontalAlignment#getHorizontalAlignment <em>Horizontal Alignment</em>}</li>
 * </ul>
 * </p>
 *
 * @see krendering.KRenderingPackage#getKHorizontalAlignment()
 * @model
 * @generated
 */
public interface KHorizontalAlignment extends KStyle {
    /**
     * Returns the value of the '<em><b>Horizontal Alignment</b></em>' attribute.
     * The literals are from the enumeration {@link krendering.HorizontalAlignment}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Horizontal Alignment</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Horizontal Alignment</em>' attribute.
     * @see krendering.HorizontalAlignment
     * @see #setHorizontalAlignment(HorizontalAlignment)
     * @see krendering.KRenderingPackage#getKHorizontalAlignment_HorizontalAlignment()
     * @model required="true"
     * @generated
     */
    HorizontalAlignment getHorizontalAlignment();

    /**
     * Sets the value of the '{@link krendering.KHorizontalAlignment#getHorizontalAlignment <em>Horizontal Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Horizontal Alignment</em>' attribute.
     * @see krendering.HorizontalAlignment
     * @see #getHorizontalAlignment()
     * @generated
     */
    void setHorizontalAlignment(HorizontalAlignment value);

} // KHorizontalAlignment
