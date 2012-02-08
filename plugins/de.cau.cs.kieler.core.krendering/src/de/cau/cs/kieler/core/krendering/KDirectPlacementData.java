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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KDirect Placement Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KDirectPlacementData#getTopLeft <em>Top Left</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KDirectPlacementData#getBottomRight <em>Bottom Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKDirectPlacementData()
 * @model
 * @generated
 */
public interface KDirectPlacementData extends KPlacementData {
    /**
     * Returns the value of the '<em><b>Top Left</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Top Left</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Top Left</em>' containment reference.
     * @see #setTopLeft(KPosition)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKDirectPlacementData_TopLeft()
     * @model containment="true" required="true"
     * @generated
     */
    KPosition getTopLeft();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KDirectPlacementData#getTopLeft <em>Top Left</em>}' containment reference.
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
     * <p>
     * If the meaning of the '<em>Bottom Right</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bottom Right</em>' containment reference.
     * @see #setBottomRight(KPosition)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKDirectPlacementData_BottomRight()
     * @model containment="true" required="true"
     * @generated
     */
    KPosition getBottomRight();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KDirectPlacementData#getBottomRight <em>Bottom Right</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bottom Right</em>' containment reference.
     * @see #getBottomRight()
     * @generated
     */
    void setBottomRight(KPosition value);

} // KDirectPlacementData
