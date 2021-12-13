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
 * A representation of the model object '<em><b>KLine Cap</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * implements different line ending styles
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KLineCap#getLineCap <em>Line Cap</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKLineCap()
 * @model
 * @generated
 */
public interface KLineCap extends KStyle {
    /**
     * Returns the value of the '<em><b>Line Cap</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.klighd.krendering.LineCap}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Cap</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Line Cap</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.LineCap
     * @see #setLineCap(LineCap)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKLineCap_LineCap()
     * @model required="true"
     * @generated
     */
    LineCap getLineCap();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KLineCap#getLineCap <em>Line Cap</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Line Cap</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.LineCap
     * @see #getLineCap()
     * @generated
     */
    void setLineCap(LineCap value);

} // KLineCap
