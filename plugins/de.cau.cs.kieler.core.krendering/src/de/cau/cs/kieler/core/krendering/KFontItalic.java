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
 * A representation of the model object '<em><b>KFont Italic</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KFontItalic#isItalic <em>Italic</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKFontItalic()
 * @model
 * @generated
 */
public interface KFontItalic extends KStyle {
    /**
     * Returns the value of the '<em><b>Italic</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Italic</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Italic</em>' attribute.
     * @see #setItalic(boolean)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKFontItalic_Italic()
     * @model required="true"
     * @generated
     */
    boolean isItalic();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KFontItalic#isItalic <em>Italic</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Italic</em>' attribute.
     * @see #isItalic()
     * @generated
     */
    void setItalic(boolean value);

} // KFontItalic
