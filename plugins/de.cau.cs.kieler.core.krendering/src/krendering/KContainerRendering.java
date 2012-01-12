/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package krendering;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KContainer Rendering</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link krendering.KContainerRendering#getChildren <em>Children</em>}</li>
 *   <li>{@link krendering.KContainerRendering#getChildPlacement <em>Child Placement</em>}</li>
 * </ul>
 * </p>
 *
 * @see krendering.KRenderingPackage#getKContainerRendering()
 * @model abstract="true"
 * @generated
 */
public interface KContainerRendering extends KRendering {
    /**
     * Returns the value of the '<em><b>Children</b></em>' containment reference list.
     * The list contents are of type {@link krendering.KRendering}.
     * It is bidirectional and its opposite is '{@link krendering.KRendering#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Children</em>' containment reference list.
     * @see krendering.KRenderingPackage#getKContainerRendering_Children()
     * @see krendering.KRendering#getParent
     * @model opposite="parent" containment="true"
     * @generated
     */
    EList<KRendering> getChildren();

    /**
     * Returns the value of the '<em><b>Child Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Child Placement</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Child Placement</em>' containment reference.
     * @see #setChildPlacement(KPlacement)
     * @see krendering.KRenderingPackage#getKContainerRendering_ChildPlacement()
     * @model containment="true"
     * @generated
     */
    KPlacement getChildPlacement();

    /**
     * Sets the value of the '{@link krendering.KContainerRendering#getChildPlacement <em>Child Placement</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Child Placement</em>' containment reference.
     * @see #getChildPlacement()
     * @generated
     */
    void setChildPlacement(KPlacement value);

} // KContainerRendering
