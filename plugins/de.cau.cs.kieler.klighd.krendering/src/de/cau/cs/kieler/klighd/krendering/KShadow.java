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
 * A representation of the model object '<em><b>KShadow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KShadow#getXOffset <em>XOffset</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KShadow#getYOffset <em>YOffset</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KShadow#getBlur <em>Blur</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KShadow#getColor <em>Color</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKShadow()
 * @model
 * @generated
 */
public interface KShadow extends KStyle {
    /**
     * Returns the value of the '<em><b>XOffset</b></em>' attribute.
     * The default value is <code>"3"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Determines x offset from element.
     * <!-- end-model-doc -->
     * @return the value of the '<em>XOffset</em>' attribute.
     * @see #setXOffset(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKShadow_XOffset()
     * @model default="3"
     * @generated
     */
    float getXOffset();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KShadow#getXOffset <em>XOffset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>XOffset</em>' attribute.
     * @see #getXOffset()
     * @generated
     */
    void setXOffset(float value);

    /**
     * Returns the value of the '<em><b>YOffset</b></em>' attribute.
     * The default value is <code>"3"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Determines y offset from element.
     * <!-- end-model-doc -->
     * @return the value of the '<em>YOffset</em>' attribute.
     * @see #setYOffset(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKShadow_YOffset()
     * @model default="3"
     * @generated
     */
    float getYOffset();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KShadow#getYOffset <em>YOffset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>YOffset</em>' attribute.
     * @see #getYOffset()
     * @generated
     */
    void setYOffset(float value);

    /**
     * Returns the value of the '<em><b>Blur</b></em>' attribute.
     * The default value is <code>"0.0"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The amount of blur for this shadow.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Blur</em>' attribute.
     * @see #setBlur(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKShadow_Blur()
     * @model default="0.0"
     * @generated
     */
    float getBlur();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KShadow#getBlur <em>Blur</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Blur</em>' attribute.
     * @see #getBlur()
     * @generated
     */
    void setBlur(float value);

    /**
     * Returns the value of the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The color this shadow effect should have.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Color</em>' containment reference.
     * @see #setColor(KColor)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKShadow_Color()
     * @model containment="true"
     * @generated
     */
    KColor getColor();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KShadow#getColor <em>Color</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Color</em>' containment reference.
     * @see #getColor()
     * @generated
     */
    void setColor(KColor value);

} // KShadow
