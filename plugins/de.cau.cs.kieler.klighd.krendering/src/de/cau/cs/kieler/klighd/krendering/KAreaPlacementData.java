/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KArea Placement Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * specifies the area for an element by setting TopLeft and BottomRight corner absolutely
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KAreaPlacementData#getTopLeft <em>Top Left</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KAreaPlacementData#getBottomRight <em>Bottom Right</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKAreaPlacementData()
 * @model
 * @generated
 */
public interface KAreaPlacementData extends KPlacementData {
    /**
     * Returns the value of the '<em><b>Top Left</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * top left corner of the area used by the element the placement is attached to
     * <!-- end-model-doc -->
     * @return the value of the '<em>Top Left</em>' containment reference.
     * @see #setTopLeft(KPosition)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKAreaPlacementData_TopLeft()
     * @model containment="true"
     * @generated
     */
    KPosition getTopLeft();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KAreaPlacementData#getTopLeft <em>Top Left</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Top Left</em>' containment reference.
     * @see #getTopLeft()
     * @generated
     */
    void setTopLeft(KPosition value);

    /**
     * Returns the value of the '<em><b>Bottom Right</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * bottom right corner of the area used by the element the placement is attached to
     * <!-- end-model-doc -->
     * @return the value of the '<em>Bottom Right</em>' containment reference.
     * @see #setBottomRight(KPosition)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKAreaPlacementData_BottomRight()
     * @model containment="true"
     * @generated
     */
    KPosition getBottomRight();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KAreaPlacementData#getBottomRight <em>Bottom Right</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bottom Right</em>' containment reference.
     * @see #getBottomRight()
     * @generated
     */
    void setBottomRight(KPosition value);

} // KAreaPlacementData
