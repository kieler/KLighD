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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KInvisibility</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * defines whether an object is visible or not
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KInvisibility#isInvisible <em>Invisible</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKInvisibility()
 * @model
 * @generated
 */
public interface KInvisibility extends KStyle {
    /**
     * Returns the value of the '<em><b>Invisible</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Invisible</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Set true to make an element invisible.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Invisible</em>' attribute.
     * @see #setInvisible(boolean)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKInvisibility_Invisible()
     * @model default="true" required="true"
     * @generated
     */
    boolean isInvisible();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KInvisibility#isInvisible <em>Invisible</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Invisible</em>' attribute.
     * @see #isInvisible()
     * @generated
     */
    void setInvisible(boolean value);

} // KInvisibility
