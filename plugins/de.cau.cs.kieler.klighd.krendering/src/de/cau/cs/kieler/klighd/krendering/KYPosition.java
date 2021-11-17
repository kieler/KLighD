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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KY Position</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * define an y-position by setting absolute and relative position respective to a parent rendering.
 * Both parameters are always included in the calculation of the resulting position. See Subtypes for formula. 
 * Can overlap the parent by setting negative values.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KYPosition#getAbsolute <em>Absolute</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KYPosition#getRelative <em>Relative</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKYPosition()
 * @model abstract="true"
 * @generated
 */
public interface KYPosition<T extends KYPosition<T>> extends EObject {
    /**
     * Returns the value of the '<em><b>Absolute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * define absolute position in pixels
     * <!-- end-model-doc -->
     * @return the value of the '<em>Absolute</em>' attribute.
     * @see #setAbsolute(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKYPosition_Absolute()
     * @model
     * @generated
     */
    float getAbsolute();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KYPosition#getAbsolute <em>Absolute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Absolute</em>' attribute.
     * @see #getAbsolute()
     * @generated
     */
    void setAbsolute(float value);

    /**
     * Returns the value of the '<em><b>Relative</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * define relative position based on parent size
     * <!-- end-model-doc -->
     * @return the value of the '<em>Relative</em>' attribute.
     * @see #setRelative(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKYPosition_Relative()
     * @model
     * @generated
     */
    float getRelative();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KYPosition#getRelative <em>Relative</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Relative</em>' attribute.
     * @see #getRelative()
     * @generated
     */
    void setRelative(float value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Checks the equalilty of the <code>absolute</code> and <code>relative</code> components of <code>this</code> KXPostion instance and the provided <code>other</code> one.
     * Returns false if <code>other</code> is not a KXPosition.<br>
     * {@link de.cau.cs.kieler.klighd.krendering.KRenderingUtil#equals(KXPosition, Object) KRenderingUtil.equals(KXPosition, Object)}.<br>
     * <br>
     * Hint: Equal KXPositions, however, do not imply indentical points in the figure as they may have different parents!
     * 
     * @return <code>true</code> if <code>other</code> is a KXPosition and its <code>absolute</code> and <code>relative</code> components are equal to those of <code>this</code> instance, <code>false</code> otherwise
     * 
     * <!-- end-model-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return de.cau.cs.kieler.klighd.krendering.KRenderingUtil.equals(this,other);'"
     * @generated
     */
    boolean equals(Object other);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring<code>this</code> KYPosition instance.<br>
     * Redirects to {@link de.cau.cs.kieler.klighd.krendering.KRenderingUtil#setPosition(KYPosition, float, float) KRenderingUtil.setPosition(KYPosition, float, float)}.
     * @param absolute define absolute position in pixels
     * @param relative define relative position based on parent size in range of 0 to 1
     * <!-- end-model-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='@SuppressWarnings(\"unchecked\")\nfinal T it = (T) this;\nreturn de.cau.cs.kieler.klighd.krendering.KRenderingUtil.setPosition(it, absolute, relative);'"
     * @generated
     */
    T setPosition(float absolute, float relative);

} // KYPosition
