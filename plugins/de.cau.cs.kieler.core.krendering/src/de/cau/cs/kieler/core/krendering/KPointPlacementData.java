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
 *   <li>{@link de.cau.cs.kieler.core.krendering.KPointPlacementData#getHorizontalAlignment <em>Horizontal Alignment</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KPointPlacementData#getVerticalAlignment <em>Vertical Alignment</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KPointPlacementData#getHorizontalMargin <em>Horizontal Margin</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KPointPlacementData#getVerticalMargin <em>Vertical Margin</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KPointPlacementData#getMinWidth <em>Min Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KPointPlacementData#getMinHeight <em>Min Height</em>}</li>
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
     * @model containment="true"
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
     * The default value is <code>"TOP"</code>.
     * The literals are from the enumeration {@link de.cau.cs.kieler.core.krendering.VerticalAlignment}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Vertical alignment of the child w.r.t. to the reference point that is based on the parent's bounds.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Vertical Alignment</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.VerticalAlignment
     * @see #setVerticalAlignment(VerticalAlignment)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPointPlacementData_VerticalAlignment()
     * @model default="TOP"
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
     * Returns the value of the '<em><b>Horizontal Margin</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Some horizontal margin that will be added on the right (left) for a LEFT (RIGHT) aligned child and to both left and right sides in case of a centrically aligned child.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Horizontal Margin</em>' attribute.
     * @see #setHorizontalMargin(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPointPlacementData_HorizontalMargin()
     * @model
     * @generated
     */
    float getHorizontalMargin();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KPointPlacementData#getHorizontalMargin <em>Horizontal Margin</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Horizontal Margin</em>' attribute.
     * @see #getHorizontalMargin()
     * @generated
     */
    void setHorizontalMargin(float value);

    /**
     * Returns the value of the '<em><b>Vertical Margin</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Some vertical margin that will be added on the bottom (top) for a TOP (BOTTOM) aligned child and to both top and bottom sides in case of a centrically aligned child.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Vertical Margin</em>' attribute.
     * @see #setVerticalMargin(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPointPlacementData_VerticalMargin()
     * @model
     * @generated
     */
    float getVerticalMargin();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KPointPlacementData#getVerticalMargin <em>Vertical Margin</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Vertical Margin</em>' attribute.
     * @see #getVerticalMargin()
     * @generated
     */
    void setVerticalMargin(float value);

    /**
     * Returns the value of the '<em><b>Min Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Min Width</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Minimal width of the element. When children are smaller or not present, this size will be used.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Min Width</em>' attribute.
     * @see #setMinWidth(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPointPlacementData_MinWidth()
     * @model
     * @generated
     */
    float getMinWidth();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KPointPlacementData#getMinWidth <em>Min Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Min Width</em>' attribute.
     * @see #getMinWidth()
     * @generated
     */
    void setMinWidth(float value);

    /**
     * Returns the value of the '<em><b>Min Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Min Height</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Minimal height of the element. When children are smaller or not present, this size will be used.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Min Height</em>' attribute.
     * @see #setMinHeight(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPointPlacementData_MinHeight()
     * @model
     * @generated
     */
    float getMinHeight();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KPointPlacementData#getMinHeight <em>Min Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Min Height</em>' attribute.
     * @see #getMinHeight()
     * @generated
     */
    void setMinHeight(float value);

    /**
     * Returns the value of the '<em><b>Horizontal Alignment</b></em>' attribute.
     * The default value is <code>"LEFT"</code>.
     * The literals are from the enumeration {@link de.cau.cs.kieler.core.krendering.HorizontalAlignment}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Horizontal alignment of the child w.r.t. to the reference point that is based on the parent's bounds.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Horizontal Alignment</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.HorizontalAlignment
     * @see #setHorizontalAlignment(HorizontalAlignment)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKPointPlacementData_HorizontalAlignment()
     * @model default="LEFT"
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
