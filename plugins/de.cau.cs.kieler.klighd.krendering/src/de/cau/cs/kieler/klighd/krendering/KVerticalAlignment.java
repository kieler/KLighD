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
 * A representation of the model object '<em><b>KVertical Alignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KVerticalAlignment#getVerticalAlignment <em>Vertical Alignment</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKVerticalAlignment()
 * @model
 * @generated
 */
public interface KVerticalAlignment extends KStyle {
    /**
     * Returns the value of the '<em><b>Vertical Alignment</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.klighd.krendering.VerticalAlignment}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Vertical Alignment</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Vertical Alignment</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.VerticalAlignment
     * @see #setVerticalAlignment(VerticalAlignment)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKVerticalAlignment_VerticalAlignment()
     * @model required="true"
     * @generated
     */
    VerticalAlignment getVerticalAlignment();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KVerticalAlignment#getVerticalAlignment <em>Vertical Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Vertical Alignment</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.VerticalAlignment
     * @see #getVerticalAlignment()
     * @generated
     */
    void setVerticalAlignment(VerticalAlignment value);

} // KVerticalAlignment
