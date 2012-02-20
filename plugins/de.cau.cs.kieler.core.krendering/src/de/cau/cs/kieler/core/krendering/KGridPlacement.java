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
 * A representation of the model object '<em><b>KGrid Placement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KGridPlacement#getNumColumns <em>Num Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGridPlacement()
 * @model
 * @generated
 */
public interface KGridPlacement extends KPlacement {
    /**
     * Returns the value of the '<em><b>Num Columns</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Num Columns</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Num Columns</em>' attribute.
     * @see #setNumColumns(int)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGridPlacement_NumColumns()
     * @model required="true"
     * @generated
     */
    int getNumColumns();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KGridPlacement#getNumColumns <em>Num Columns</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Num Columns</em>' attribute.
     * @see #getNumColumns()
     * @generated
     */
    void setNumColumns(int value);

} // KGridPlacement
