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
 * A representation of the model object '<em><b>KPolyline Placement Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link krendering.KPolylinePlacementData#getPoints <em>Points</em>}</li>
 * </ul>
 * </p>
 *
 * @see krendering.KRenderingPackage#getKPolylinePlacementData()
 * @model
 * @generated
 */
public interface KPolylinePlacementData extends KPlacementData {
    /**
     * Returns the value of the '<em><b>Points</b></em>' containment reference list.
     * The list contents are of type {@link krendering.KPosition}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Points</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Points</em>' containment reference list.
     * @see krendering.KRenderingPackage#getKPolylinePlacementData_Points()
     * @model containment="true" required="true"
     * @generated
     */
    EList<KPosition> getPoints();

} // KPolylinePlacementData
