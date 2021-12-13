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
 * A representation of the model object '<em><b>KRotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Specifies the (clockwise) rotation of the corresponding {@link KRendering}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KRotation#getRotation <em>Rotation</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KRotation#getRotationAnchor <em>Rotation Anchor</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKRotation()
 * @model
 * @generated
 */
public interface KRotation extends KStyle {
    /**
     * Returns the value of the '<em><b>Rotation</b></em>' attribute.
     * The default value is <code>"0.0"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Determines the rotation angle value in degrees.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Rotation</em>' attribute.
     * @see #setRotation(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKRotation_Rotation()
     * @model default="0.0" required="true"
     * @generated
     */
    float getRotation();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KRotation#getRotation <em>Rotation</em>}' attribute.
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
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Determines the rotation anchor wrt. the bounds of the corresponding {@link KRendering};
     * may be <code>null</code>, the center position will be used in that case.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Rotation Anchor</em>' containment reference.
     * @see #setRotationAnchor(KPosition)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKRotation_RotationAnchor()
     * @model containment="true"
     * @generated
     */
    KPosition getRotationAnchor();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KRotation#getRotationAnchor <em>Rotation Anchor</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Rotation Anchor</em>' containment reference.
     * @see #getRotationAnchor()
     * @generated
     */
    void setRotationAnchor(KPosition value);

} // KRotation
