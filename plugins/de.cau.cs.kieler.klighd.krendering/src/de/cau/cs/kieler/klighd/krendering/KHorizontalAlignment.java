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
 * A representation of the model object '<em><b>KHorizontal Alignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KHorizontalAlignment#getHorizontalAlignment <em>Horizontal Alignment</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKHorizontalAlignment()
 * @model
 * @generated
 */
public interface KHorizontalAlignment extends KStyle {
    /**
     * Returns the value of the '<em><b>Horizontal Alignment</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.klighd.krendering.HorizontalAlignment}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Horizontal Alignment</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Horizontal Alignment</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.HorizontalAlignment
     * @see #setHorizontalAlignment(HorizontalAlignment)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKHorizontalAlignment_HorizontalAlignment()
     * @model required="true"
     * @generated
     */
    HorizontalAlignment getHorizontalAlignment();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KHorizontalAlignment#getHorizontalAlignment <em>Horizontal Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Horizontal Alignment</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.HorizontalAlignment
     * @see #getHorizontalAlignment()
     * @generated
     */
    void setHorizontalAlignment(HorizontalAlignment value);

} // KHorizontalAlignment
