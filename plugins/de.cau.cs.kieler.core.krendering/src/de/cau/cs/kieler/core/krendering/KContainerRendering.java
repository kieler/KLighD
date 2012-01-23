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
 * A representation of the model object '<em><b>KContainer Rendering</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KContainerRendering#getChildren <em>Children</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KContainerRendering#getChildPlacement <em>Child Placement</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKContainerRendering()
 * @model abstract="true"
 * @generated
 */
public interface KContainerRendering extends KRendering {
    /**
     * Returns the value of the '<em><b>Children</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.core.krendering.KRendering}.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.core.krendering.KRendering#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Children</em>' containment reference list.
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKContainerRendering_Children()
     * @see de.cau.cs.kieler.core.krendering.KRendering#getParent
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
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKContainerRendering_ChildPlacement()
     * @model containment="true"
     * @generated
     */
    KPlacement getChildPlacement();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KContainerRendering#getChildPlacement <em>Child Placement</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Child Placement</em>' containment reference.
     * @see #getChildPlacement()
     * @generated
     */
    void setChildPlacement(KPlacement value);

} // KContainerRendering
