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
 * A representation of the model object '<em><b>KFont Size</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * FontStyle to determine the size of the font.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KFontSize#getSize <em>Size</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KFontSize#isScaleWithZoom <em>Scale With Zoom</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKFontSize()
 * @model
 * @generated
 */
public interface KFontSize extends KStyle {
    /**
     * Returns the value of the '<em><b>Size</b></em>' attribute.
     * The default value is <code>"10"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Desired size of the font.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Size</em>' attribute.
     * @see #setSize(int)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKFontSize_Size()
     * @model default="10" required="true"
     * @generated
     */
    int getSize();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KFontSize#getSize <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Size</em>' attribute.
     * @see #getSize()
     * @generated
     */
    void setSize(int value);

    /**
     * Returns the value of the '<em><b>Scale With Zoom</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * whether the font size should increase when the user is zooming out to have text still be readable even when the containing object shrinks.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Scale With Zoom</em>' attribute.
     * @see #setScaleWithZoom(boolean)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKFontSize_ScaleWithZoom()
     * @model default="false"
     * @generated
     */
    boolean isScaleWithZoom();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KFontSize#isScaleWithZoom <em>Scale With Zoom</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Scale With Zoom</em>' attribute.
     * @see #isScaleWithZoom()
     * @generated
     */
    void setScaleWithZoom(boolean value);

} // KFontSize
