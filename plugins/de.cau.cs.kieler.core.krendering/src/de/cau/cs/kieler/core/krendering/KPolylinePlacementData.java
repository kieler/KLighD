/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.krendering;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KPolyline Placement Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KPolylinePlacementData#getPoints <em>Points</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KPolylinePlacementData#getDetailPlacementData <em>Detail Placement Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPolylinePlacementData()
 * @model
 * @generated
 */
public interface KPolylinePlacementData extends KPlacementData {
    /**
     * Returns the value of the '<em><b>Points</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.core.krendering.KPosition}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Points</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Points</em>' containment reference list.
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPolylinePlacementData_Points()
     * @model containment="true" required="true"
     * @generated
     */
    EList<KPosition> getPoints();

    /**
     * Returns the value of the '<em><b>Detail Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Detail Placement Data</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Detail Placement Data</em>' containment reference.
     * @see #setDetailPlacementData(KPlacementData)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPolylinePlacementData_DetailPlacementData()
     * @model containment="true"
     * @generated
     */
    KPlacementData getDetailPlacementData();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KPolylinePlacementData#getDetailPlacementData <em>Detail Placement Data</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Detail Placement Data</em>' containment reference.
     * @see #getDetailPlacementData()
     * @generated
     */
    void setDetailPlacementData(KPlacementData value);

} // KPolylinePlacementData
