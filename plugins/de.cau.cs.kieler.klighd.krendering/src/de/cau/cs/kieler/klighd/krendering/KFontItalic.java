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
 * A representation of the model object '<em><b>KFont Italic</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * FontStyle to dertermine whether to draw it italic or not.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KFontItalic#isItalic <em>Italic</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKFontItalic()
 * @model
 * @generated
 */
public interface KFontItalic extends KStyle {
    /**
     * Returns the value of the '<em><b>Italic</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Set true to draw the text italic.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Italic</em>' attribute.
     * @see #setItalic(boolean)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKFontItalic_Italic()
     * @model default="true" required="true"
     * @generated
     */
    boolean isItalic();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KFontItalic#isItalic <em>Italic</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Italic</em>' attribute.
     * @see #isItalic()
     * @generated
     */
    void setItalic(boolean value);

} // KFontItalic
