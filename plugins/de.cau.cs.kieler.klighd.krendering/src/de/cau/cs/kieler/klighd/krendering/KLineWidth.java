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
 * A representation of the model object '<em><b>KLine Width</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * specifies a lineWidth for a rendering. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KLineWidth#getLineWidth <em>Line Width</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKLineWidth()
 * @model
 * @generated
 */
public interface KLineWidth extends KStyle {
    /**
     * Returns the value of the '<em><b>Line Width</b></em>' attribute.
     * The default value is <code>"1.0"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the line width of the element
     * <!-- end-model-doc -->
     * @return the value of the '<em>Line Width</em>' attribute.
     * @see #setLineWidth(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKLineWidth_LineWidth()
     * @model default="1.0" required="true"
     * @generated
     */
    float getLineWidth();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KLineWidth#getLineWidth <em>Line Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Line Width</em>' attribute.
     * @see #getLineWidth()
     * @generated
     */
    void setLineWidth(float value);

} // KLineWidth
