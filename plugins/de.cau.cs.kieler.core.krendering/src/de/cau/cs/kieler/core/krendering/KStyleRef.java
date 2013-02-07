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
 *   <li>{@link de.cau.cs.kieler.core.krendering.KStyleRef#getStyleHolder <em>Style Holder</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyleRef()
 * @model
 * @generated
 */
public interface KStyleRef extends KStyle {
    /**
     * Returns the value of the '<em><b>Style Holder</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style Holder</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Style Holder</em>' reference.
     * @see #setStyleHolder(KStyleHolder)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyleRef_StyleHolder()
     * @model required="true"
     * @generated
     */
    KStyleHolder getStyleHolder();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KStyleRef#getStyleHolder <em>Style Holder</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Style Holder</em>' reference.
     * @see #getStyleHolder()
     * @generated
     */
    void setStyleHolder(KStyleHolder value);

} // KStyleRef
