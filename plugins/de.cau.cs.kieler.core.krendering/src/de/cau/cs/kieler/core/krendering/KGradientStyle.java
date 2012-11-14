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
 * A representation of the model object '<em><b>KGradient Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Creates a colorgradient with startColor and endColor and according angle
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KGradientStyle#getAngle <em>Angle</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KGradientStyle#getStartColor <em>Start Color</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KGradientStyle#getEndColor <em>End Color</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGradientStyle()
 * @model
 * @generated
 */
public interface KGradientStyle extends KStyle {
    /**
     * Returns the value of the '<em><b>Angle</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Angle</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Angle</em>' attribute.
     * @see #setAngle(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGradientStyle_Angle()
     * @model
     * @generated
     */
    float getAngle();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KGradientStyle#getAngle <em>Angle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Angle</em>' attribute.
     * @see #getAngle()
     * @generated
     */
    void setAngle(float value);

    /**
     * Returns the value of the '<em><b>Start Color</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Start Color</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Start Color</em>' reference.
     * @see #setStartColor(KColor)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGradientStyle_StartColor()
     * @model
     * @generated
     */
    KColor getStartColor();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KGradientStyle#getStartColor <em>Start Color</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Start Color</em>' reference.
     * @see #getStartColor()
     * @generated
     */
    void setStartColor(KColor value);

    /**
     * Returns the value of the '<em><b>End Color</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>End Color</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>End Color</em>' reference.
     * @see #setEndColor(KColor)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGradientStyle_EndColor()
     * @model
     * @generated
     */
    KColor getEndColor();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KGradientStyle#getEndColor <em>End Color</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>End Color</em>' reference.
     * @see #getEndColor()
     * @generated
     */
    void setEndColor(KColor value);

} // KGradientStyle
