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
 * A representation of the model object '<em><b>KRounded Rectangle</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The rounded rectangle is used to create a rectangle with rounded corners. Corner width and height need to be passed in order to define the style of the corners.
 * The shape fits inside the space defined (a) by the node it is attached to or (b) by the placementData that is attached to the rendering
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KRoundedRectangle#getCornerWidth <em>Corner Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KRoundedRectangle#getCornerHeight <em>Corner Height</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKRoundedRectangle()
 * @model
 * @generated
 */
public interface KRoundedRectangle extends KContainerRendering {
    /**
     * Returns the value of the '<em><b>Corner Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * width of a corner in pixels
     * <!-- end-model-doc -->
     * @return the value of the '<em>Corner Width</em>' attribute.
     * @see #setCornerWidth(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKRoundedRectangle_CornerWidth()
     * @model required="true"
     * @generated
     */
    float getCornerWidth();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KRoundedRectangle#getCornerWidth <em>Corner Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Corner Width</em>' attribute.
     * @see #getCornerWidth()
     * @generated
     */
    void setCornerWidth(float value);

    /**
     * Returns the value of the '<em><b>Corner Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * height of a corner in pixels
     * <!-- end-model-doc -->
     * @return the value of the '<em>Corner Height</em>' attribute.
     * @see #setCornerHeight(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKRoundedRectangle_CornerHeight()
     * @model required="true"
     * @generated
     */
    float getCornerHeight();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KRoundedRectangle#getCornerHeight <em>Corner Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Corner Height</em>' attribute.
     * @see #getCornerHeight()
     * @generated
     */
    void setCornerHeight(float value);

} // KRoundedRectangle
