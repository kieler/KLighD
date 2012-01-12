/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KLine Width</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link krendering.KLineWidth#getLineWidth <em>Line Width</em>}</li>
 * </ul>
 * </p>
 *
 * @see krendering.KRenderingPackage#getKLineWidth()
 * @model
 * @generated
 */
public interface KLineWidth extends KStyle {
    /**
     * Returns the value of the '<em><b>Line Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Width</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Line Width</em>' attribute.
     * @see #setLineWidth(int)
     * @see krendering.KRenderingPackage#getKLineWidth_LineWidth()
     * @model required="true"
     * @generated
     */
    int getLineWidth();

    /**
     * Sets the value of the '{@link krendering.KLineWidth#getLineWidth <em>Line Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Line Width</em>' attribute.
     * @see #getLineWidth()
     * @generated
     */
    void setLineWidth(int value);

} // KLineWidth
