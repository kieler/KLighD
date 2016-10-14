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
package de.cau.cs.kieler.klighd.krendering;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KPolyline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Creates a polyline between two or more points
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KPolyline#getPoints <em>Points</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KPolyline#getJunctionPointRendering <em>Junction Point Rendering</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKPolyline()
 * @model
 * @generated
 */
public interface KPolyline extends KContainerRendering {
    /**
     * Returns the value of the '<em><b>Points</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.klighd.krendering.KPosition}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * points that are visited by the polyline in order of definition
     * <!-- end-model-doc -->
     * @return the value of the '<em>Points</em>' containment reference list.
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKPolyline_Points()
     * @model containment="true"
     * @generated
     */
    EList<KPosition> getPoints();

    /**
     * Returns the value of the '<em><b>Junction Point Rendering</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Optional rendering defining the look of join point figures that are attached to the diagram for each edge join point computed by the automatic (macro) layout.
     * Setting is evaluated for KEdge renderings only, ignored for simple polylines being part of a complex rendering.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Junction Point Rendering</em>' containment reference.
     * @see #setJunctionPointRendering(KRendering)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKPolyline_JunctionPointRendering()
     * @model containment="true"
     * @generated
     */
    KRendering getJunctionPointRendering();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KPolyline#getJunctionPointRendering <em>Junction Point Rendering</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Junction Point Rendering</em>' containment reference.
     * @see #getJunctionPointRendering()
     * @generated
     */
    void setJunctionPointRendering(KRendering value);

} // KPolyline
