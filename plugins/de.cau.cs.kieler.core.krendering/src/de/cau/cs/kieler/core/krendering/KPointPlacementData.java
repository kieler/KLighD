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
 * A representation of the model object '<em><b>KPoint Placement Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * with this placement it is possible to mount dynamic sized objects at a single point using the defined alignment
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KPointPlacementData#getReferencePoint <em>Reference Point</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KPointPlacementData#getVerticalAlignment <em>Vertical Alignment</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KPointPlacementData#getHorizontalAlignment <em>Horizontal Alignment</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPointPlacementData()
 * @model
 * @generated
 */
public interface KPointPlacementData extends KPlacementData {
    /**
     * Returns the value of the '<em><b>Reference Point</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the point within the parent to use as reference to place element
     * <!-- end-model-doc -->
     * @return the value of the '<em>Reference Point</em>' containment reference.
     * @see #setReferencePoint(KPosition)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPointPlacementData_ReferencePoint()
     * @model containment="true" required="true"
     * @generated
     */
    KPosition getReferencePoint();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KPointPlacementData#getReferencePoint <em>Reference Point</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Reference Point</em>' containment reference.
     * @see #getReferencePoint()
     * @generated
     */
    void setReferencePoint(KPosition value);

    /**
     * Returns the value of the '<em><b>Vertical Alignment</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.core.krendering.VerticalAlignment}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the position the point has on the vertical axis of the element to be placed
     * <!-- end-model-doc -->
     * @return the value of the '<em>Vertical Alignment</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.VerticalAlignment
     * @see #setVerticalAlignment(VerticalAlignment)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPointPlacementData_VerticalAlignment()
     * @model required="true"
     * @generated
     */
    VerticalAlignment getVerticalAlignment();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KPointPlacementData#getVerticalAlignment <em>Vertical Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Vertical Alignment</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.VerticalAlignment
     * @see #getVerticalAlignment()
     * @generated
     */
    void setVerticalAlignment(VerticalAlignment value);

    /**
     * Returns the value of the '<em><b>Horizontal Alignment</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.core.krendering.HorizontalAlignment}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the position the point has on the horizontal axis of the element to be placed
     * <!-- end-model-doc -->
     * @return the value of the '<em>Horizontal Alignment</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.HorizontalAlignment
     * @see #setHorizontalAlignment(HorizontalAlignment)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPointPlacementData_HorizontalAlignment()
     * @model required="true"
     * @generated
     */
    HorizontalAlignment getHorizontalAlignment();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KPointPlacementData#getHorizontalAlignment <em>Horizontal Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Horizontal Alignment</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.HorizontalAlignment
     * @see #getHorizontalAlignment()
     * @generated
     */
    void setHorizontalAlignment(HorizontalAlignment value);

} // KPointPlacementData
