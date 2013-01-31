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
 * A representation of the model object '<em><b>KStyle Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KStyleRef#getEReference0 <em>EReference0</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyleRef()
 * @model
 * @generated
 */
public interface KStyleRef extends KStyle {
    /**
     * Returns the value of the '<em><b>EReference0</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>EReference0</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>EReference0</em>' reference.
     * @see #setEReference0(KStyleHolder)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyleRef_EReference0()
     * @model required="true"
     * @generated
     */
    KStyleHolder getEReference0();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KStyleRef#getEReference0 <em>EReference0</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>EReference0</em>' reference.
     * @see #getEReference0()
     * @generated
     */
    void setEReference0(KStyleHolder value);

} // KStyleRef
