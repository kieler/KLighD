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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KX Position</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KXPosition#getAbsolute <em>Absolute</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KXPosition#getRelative <em>Relative</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKXPosition()
 * @model abstract="true"
 * @generated
 */
public interface KXPosition extends EObject {
    /**
     * Returns the value of the '<em><b>Absolute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Absolute</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Absolute</em>' attribute.
     * @see #setAbsolute(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKXPosition_Absolute()
     * @model
     * @generated
     */
    float getAbsolute();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KXPosition#getAbsolute <em>Absolute</em>}' attribute.
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
     * <p>
     * If the meaning of the '<em>Relative</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Relative</em>' attribute.
     * @see #setRelative(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKXPosition_Relative()
     * @model
     * @generated
     */
    float getRelative();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KXPosition#getRelative <em>Relative</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Relative</em>' attribute.
     * @see #getRelative()
     * @generated
     */
    void setRelative(float value);

} // KXPosition
