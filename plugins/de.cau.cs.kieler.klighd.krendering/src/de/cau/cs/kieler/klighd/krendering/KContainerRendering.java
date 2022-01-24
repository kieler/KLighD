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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KContainer Rendering</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * KRendering that can have Children.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KContainerRendering#getChildren <em>Children</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KContainerRendering#getChildPlacement <em>Child Placement</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKContainerRendering()
 * @model abstract="true"
 * @generated
 */
public interface KContainerRendering extends KRendering {
    /**
     * Returns the value of the '<em><b>Children</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.klighd.krendering.KRendering}.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.klighd.krendering.KRendering#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the children of this rendering
     * <!-- end-model-doc -->
     * @return the value of the '<em>Children</em>' containment reference list.
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKContainerRendering_Children()
     * @see de.cau.cs.kieler.klighd.krendering.KRendering#getParent
     * @model opposite="parent" containment="true"
     * @generated
     */
    EList<KRendering> getChildren();

    /**
     * Returns the value of the '<em><b>Child Placement</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the placement type to use for placeing the children
     * <!-- end-model-doc -->
     * @return the value of the '<em>Child Placement</em>' containment reference.
     * @see #setChildPlacement(KPlacement)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKContainerRendering_ChildPlacement()
     * @model containment="true"
     * @generated
     */
    KPlacement getChildPlacement();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KContainerRendering#getChildPlacement <em>Child Placement</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Child Placement</em>' containment reference.
     * @see #getChildPlacement()
     * @generated
     */
    void setChildPlacement(KPlacement value);

} // KContainerRendering
