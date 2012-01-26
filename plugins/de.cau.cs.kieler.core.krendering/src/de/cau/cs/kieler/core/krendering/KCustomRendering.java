/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.core.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KCustom Rendering</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KCustomRendering#getClassName <em>Class Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KCustomRendering#getBundleName <em>Bundle Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKCustomRendering()
 * @model
 * @generated
 */
public interface KCustomRendering extends KContainerRendering {
    /**
     * Returns the value of the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Class Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Class Name</em>' attribute.
     * @see #setClassName(String)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKCustomRendering_ClassName()
     * @model required="true"
     * @generated
     */
    String getClassName();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KCustomRendering#getClassName <em>Class Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Class Name</em>' attribute.
     * @see #getClassName()
     * @generated
     */
    void setClassName(String value);

    /**
     * Returns the value of the '<em><b>Bundle Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bundle Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bundle Name</em>' attribute.
     * @see #setBundleName(String)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKCustomRendering_BundleName()
     * @model required="true"
     * @generated
     */
    String getBundleName();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KCustomRendering#getBundleName <em>Bundle Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bundle Name</em>' attribute.
     * @see #getBundleName()
     * @generated
     */
    void setBundleName(String value);

} // KCustomRendering
