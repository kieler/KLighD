/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.core.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KVertical Alignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KVerticalAlignment#getVerticalAlignment <em>Vertical Alignment</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKVerticalAlignment()
 * @model
 * @generated
 */
public interface KVerticalAlignment extends KStyle {
    /**
     * Returns the value of the '<em><b>Vertical Alignment</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.core.krendering.VerticalAlignment}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Vertical Alignment</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Vertical Alignment</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.VerticalAlignment
     * @see #setVerticalAlignment(VerticalAlignment)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKVerticalAlignment_VerticalAlignment()
     * @model required="true"
     * @generated
     */
    VerticalAlignment getVerticalAlignment();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KVerticalAlignment#getVerticalAlignment <em>Vertical Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Vertical Alignment</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.VerticalAlignment
     * @see #getVerticalAlignment()
     * @generated
     */
    void setVerticalAlignment(VerticalAlignment value);

} // KVerticalAlignment
