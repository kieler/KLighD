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
 * A representation of the model object '<em><b>KLine Join</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines the line join style of a rendering by setting one of the available values of the LineJoin enumeration.
 * 'miterLimit' is evaluated if and only if the literal 'MITER' is chosen.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KLineJoin#getLineJoin <em>Line Join</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KLineJoin#getMiterLimit <em>Miter Limit</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKLineJoin()
 * @model
 * @generated
 */
public interface KLineJoin extends KStyle {
    /**
     * Returns the value of the '<em><b>Line Join</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.klighd.krendering.LineJoin}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Join</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Line Join</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.LineJoin
     * @see #setLineJoin(LineJoin)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKLineJoin_LineJoin()
     * @model required="true"
     * @generated
     */
    LineJoin getLineJoin();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KLineJoin#getLineJoin <em>Line Join</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Line Join</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.LineJoin
     * @see #getLineJoin()
     * @generated
     */
    void setLineJoin(LineJoin value);

    /**
     * Returns the value of the '<em><b>Miter Limit</b></em>' attribute.
     * The default value is <code>"10"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Miter Limit</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Miter Limit</em>' attribute.
     * @see #setMiterLimit(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKLineJoin_MiterLimit()
     * @model default="10"
     * @generated
     */
    float getMiterLimit();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KLineJoin#getMiterLimit <em>Miter Limit</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Miter Limit</em>' attribute.
     * @see #getMiterLimit()
     * @generated
     */
    void setMiterLimit(float value);

} // KLineJoin
