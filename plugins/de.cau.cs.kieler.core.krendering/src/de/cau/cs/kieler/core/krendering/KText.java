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
 * A representation of the model object '<em><b>KText</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KText#getText <em>Text</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KText#isClip <em>Clip</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKText()
 * @model
 * @generated
 */
public interface KText extends KContainerRendering {
    /**
     * Returns the value of the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Text</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Text</em>' attribute.
     * @see #setText(String)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKText_Text()
     * @model
     * @generated
     */
    String getText();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KText#getText <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text</em>' attribute.
     * @see #getText()
     * @generated
     */
    void setText(String value);

    /**
     * Returns the value of the '<em><b>Clip</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Clip</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Clip</em>' attribute.
     * @see #setClip(boolean)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKText_Clip()
     * @model required="true"
     * @generated
     */
    boolean isClip();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KText#isClip <em>Clip</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Clip</em>' attribute.
     * @see #isClip()
     * @generated
     */
    void setClip(boolean value);

} // KText
