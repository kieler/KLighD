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
 * A representation of the model object '<em><b>KText Rendering Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference Text to copy formatting but still be able to reset the text
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KTextRenderingRef#getTextRef <em>Text Ref</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KTextRenderingRef#getOverrideText <em>Override Text</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKTextRenderingRef()
 * @model
 * @generated
 */
public interface KTextRenderingRef extends KContainerRendering {
    /**
     * Returns the value of the '<em><b>Text Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the text to be referenced
     * <!-- end-model-doc -->
     * @return the value of the '<em>Text Ref</em>' reference.
     * @see #setTextRef(KText)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKTextRenderingRef_TextRef()
     * @model required="true"
     * @generated
     */
    KText getTextRef();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KTextRenderingRef#getTextRef <em>Text Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text Ref</em>' reference.
     * @see #getTextRef()
     * @generated
     */
    void setTextRef(KText value);

    /**
     * Returns the value of the '<em><b>Override Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the text to be displayed instead of the text defined by the referenced text
     * <!-- end-model-doc -->
     * @return the value of the '<em>Override Text</em>' attribute.
     * @see #setOverrideText(String)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKTextRenderingRef_OverrideText()
     * @model
     * @generated
     */
    String getOverrideText();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KTextRenderingRef#getOverrideText <em>Override Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Override Text</em>' attribute.
     * @see #getOverrideText()
     * @generated
     */
    void setOverrideText(String value);

} // KTextRenderingRef
