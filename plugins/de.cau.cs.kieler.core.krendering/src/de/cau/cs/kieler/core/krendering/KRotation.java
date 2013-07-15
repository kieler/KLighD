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
 * A representation of the model object '<em><b>KRotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * rotates element clockwise around the top left point of a parent rendering
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KRotation#getRotation <em>Rotation</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KRotation#getRotationAnchor <em>Rotation Anchor</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKRotation()
 * @model
 * @generated
 */
public interface KRotation extends KStyle {
    /**
     * Returns the value of the '<em><b>Rotation</b></em>' attribute.
     * The default value is <code>"0.0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Rotation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Rotation</em>' attribute.
     * @see #setRotation(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKRotation_Rotation()
     * @model default="0.0" required="true"
     * @generated
     */
    float getRotation();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KRotation#getRotation <em>Rotation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Rotation</em>' attribute.
     * @see #getRotation()
     * @generated
     */
    void setRotation(float value);

    /**
     * Returns the value of the '<em><b>Rotation Anchor</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Rotation Anchor</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Rotation Anchor</em>' containment reference.
     * @see #setRotationAnchor(KPosition)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKRotation_RotationAnchor()
     * @model containment="true"
     * @generated
     */
    KPosition getRotationAnchor();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KRotation#getRotationAnchor <em>Rotation Anchor</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Rotation Anchor</em>' containment reference.
     * @see #getRotationAnchor()
     * @generated
     */
    void setRotationAnchor(KPosition value);

} // KRotation
