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
 * A representation of the model object '<em><b>KLine Cap Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * implements different line ending styles
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KLineCapStyle#getLineCapStyle <em>Line Cap Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKLineCapStyle()
 * @model
 * @generated
 */
public interface KLineCapStyle extends KStyle {

    /**
     * Returns the value of the '<em><b>Line Cap Style</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.core.krendering.LineCapStyle}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Cap Style</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Line Cap Style</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.LineCapStyle
     * @see #setLineCapStyle(LineCapStyle)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKLineCapStyle_LineCapStyle()
     * @model required="true"
     * @generated
     */
    LineCapStyle getLineCapStyle();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KLineCapStyle#getLineCapStyle <em>Line Cap Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Line Cap Style</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.LineCapStyle
     * @see #getLineCapStyle()
     * @generated
     */
    void setLineCapStyle(LineCapStyle value);
} // KLineCapStyle
