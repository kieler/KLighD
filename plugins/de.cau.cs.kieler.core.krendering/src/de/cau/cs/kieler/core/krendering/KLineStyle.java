/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.core.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KLine Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KLineStyle#getLineStyle <em>Line Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKLineStyle()
 * @model
 * @generated
 */
public interface KLineStyle extends KStyle {
    /**
     * Returns the value of the '<em><b>Line Style</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.core.krendering.LineStyle}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Style</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Line Style</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.LineStyle
     * @see #setLineStyle(LineStyle)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKLineStyle_LineStyle()
     * @model required="true"
     * @generated
     */
    LineStyle getLineStyle();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KLineStyle#getLineStyle <em>Line Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Line Style</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.LineStyle
     * @see #getLineStyle()
     * @generated
     */
    void setLineStyle(LineStyle value);

} // KLineStyle
