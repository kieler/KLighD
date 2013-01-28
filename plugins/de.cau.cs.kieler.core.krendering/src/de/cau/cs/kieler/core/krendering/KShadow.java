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
 * A representation of the model object '<em><b>KShadow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KShadow#getXOffset <em>XOffset</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KShadow#getYOffset <em>YOffset</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KShadow#getBlur <em>Blur</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KShadow#getColor <em>Color</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKShadow()
 * @model
 * @generated
 */
public interface KShadow extends KStyle {
    /**
     * Returns the value of the '<em><b>XOffset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XOffset</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>XOffset</em>' attribute.
     * @see #setXOffset(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKShadow_XOffset()
     * @model
     * @generated
     */
    float getXOffset();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KShadow#getXOffset <em>XOffset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>XOffset</em>' attribute.
     * @see #getXOffset()
     * @generated
     */
    void setXOffset(float value);

    /**
     * Returns the value of the '<em><b>YOffset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>YOffset</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>YOffset</em>' attribute.
     * @see #setYOffset(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKShadow_YOffset()
     * @model
     * @generated
     */
    float getYOffset();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KShadow#getYOffset <em>YOffset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>YOffset</em>' attribute.
     * @see #getYOffset()
     * @generated
     */
    void setYOffset(float value);

    /**
     * Returns the value of the '<em><b>Blur</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Blur</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Blur</em>' attribute.
     * @see #setBlur(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKShadow_Blur()
     * @model
     * @generated
     */
    float getBlur();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KShadow#getBlur <em>Blur</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Blur</em>' attribute.
     * @see #getBlur()
     * @generated
     */
    void setBlur(float value);

    /**
     * Returns the value of the '<em><b>Color</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Color</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Color</em>' reference.
     * @see #setColor(KColor)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKShadow_Color()
     * @model
     * @generated
     */
    KColor getColor();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KShadow#getColor <em>Color</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Color</em>' reference.
     * @see #getColor()
     * @generated
     */
    void setColor(KColor value);

} // KShadow
