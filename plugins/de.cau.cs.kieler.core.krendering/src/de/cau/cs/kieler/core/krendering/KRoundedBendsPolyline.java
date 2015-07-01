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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KRounded Bends Polyline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A polyline with rounded corners at its bendpoints
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KRoundedBendsPolyline#getBendRadius <em>Bend Radius</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKRoundedBendsPolyline()
 * @model
 * @generated
 */
public interface KRoundedBendsPolyline extends KPolyline {
    /**
     * Returns the value of the '<em><b>Bend Radius</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bend Radius</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Radius of the rounded corners.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Bend Radius</em>' attribute.
     * @see #setBendRadius(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKRoundedBendsPolyline_BendRadius()
     * @model
     * @generated
     */
    float getBendRadius();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KRoundedBendsPolyline#getBendRadius <em>Bend Radius</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bend Radius</em>' attribute.
     * @see #getBendRadius()
     * @generated
     */
    void setBendRadius(float value);

} // KRoundedBendsPolyline
