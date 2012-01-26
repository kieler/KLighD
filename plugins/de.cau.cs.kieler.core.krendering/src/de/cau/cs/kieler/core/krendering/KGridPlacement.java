/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.core.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KGrid Placement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KGridPlacement#getNumColumns <em>Num Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGridPlacement()
 * @model
 * @generated
 */
public interface KGridPlacement extends KPlacement {
    /**
     * Returns the value of the '<em><b>Num Columns</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Num Columns</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Num Columns</em>' attribute.
     * @see #setNumColumns(int)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGridPlacement_NumColumns()
     * @model required="true"
     * @generated
     */
    int getNumColumns();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KGridPlacement#getNumColumns <em>Num Columns</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Num Columns</em>' attribute.
     * @see #getNumColumns()
     * @generated
     */
    void setNumColumns(int value);

} // KGridPlacement
