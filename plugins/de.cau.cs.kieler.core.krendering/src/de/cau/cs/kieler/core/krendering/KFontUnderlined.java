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
 * A representation of the model object '<em><b>KFont Underlined</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KFontUnderlined#getUnderlineStyle <em>Underline Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKFontUnderlined()
 * @model
 * @generated
 */
public interface KFontUnderlined extends KStyle {
    /**
     * Returns the value of the '<em><b>Underline Style</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.core.krendering.UnderlineStyle}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Underline Style</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Underline Style</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.UnderlineStyle
     * @see #setUnderlineStyle(UnderlineStyle)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKFontUnderlined_UnderlineStyle()
     * @model required="true"
     * @generated
     */
    UnderlineStyle getUnderlineStyle();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KFontUnderlined#getUnderlineStyle <em>Underline Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Underline Style</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.UnderlineStyle
     * @see #getUnderlineStyle()
     * @generated
     */
    void setUnderlineStyle(UnderlineStyle value);

} // KFontUnderlined
