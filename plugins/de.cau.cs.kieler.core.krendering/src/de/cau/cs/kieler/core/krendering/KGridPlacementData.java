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
 * A representation of the model object '<em><b>KGrid Placement Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Define the placement of elements in a gridPlacement
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KGridPlacementData#getMinCellWidth <em>Min Cell Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KGridPlacementData#getMaxCellWidth <em>Max Cell Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KGridPlacementData#getMinCellHeight <em>Min Cell Height</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KGridPlacementData#getMaxCellHeight <em>Max Cell Height</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGridPlacementData()
 * @model
 * @generated
 */
public interface KGridPlacementData extends KAreaPlacementData {

    /**
     * Returns the value of the '<em><b>Min Cell Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Min Cell Width</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * force layout to make the column that contains the element this placementdata is assigned to as wide as defined here. If another element in that column has a maxCellWidth that is less than this minWidth, the maxWidth is ignored.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Min Cell Width</em>' attribute.
     * @see #setMinCellWidth(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGridPlacementData_MinCellWidth()
     * @model
     * @generated
     */
    float getMinCellWidth();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KGridPlacementData#getMinCellWidth <em>Min Cell Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Min Cell Width</em>' attribute.
     * @see #getMinCellWidth()
     * @generated
     */
    void setMinCellWidth(float value);

    /**
     * Returns the value of the '<em><b>Max Cell Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Max Cell Width</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * hint layout to keep the width of the column the element is assigned to less than defined here. 
     * If another element in this column has a minCellWidth that is higher than this maxWidth, 
     * the maxWidth is ignored.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Max Cell Width</em>' attribute.
     * @see #setMaxCellWidth(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGridPlacementData_MaxCellWidth()
     * @model
     * @generated
     */
    float getMaxCellWidth();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KGridPlacementData#getMaxCellWidth <em>Max Cell Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Max Cell Width</em>' attribute.
     * @see #getMaxCellWidth()
     * @generated
     */
    void setMaxCellWidth(float value);

    /**
     * Returns the value of the '<em><b>Min Cell Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Min Cell Height</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * force layout to make the row that contains the element this placementdata is assigned to as high as defined here. If another element in that column has a maxCellHeight that is less than this minHeight, the maxHeight is ignored.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Min Cell Height</em>' attribute.
     * @see #setMinCellHeight(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGridPlacementData_MinCellHeight()
     * @model
     * @generated
     */
    float getMinCellHeight();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KGridPlacementData#getMinCellHeight <em>Min Cell Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Min Cell Height</em>' attribute.
     * @see #getMinCellHeight()
     * @generated
     */
    void setMinCellHeight(float value);

    /**
     * Returns the value of the '<em><b>Max Cell Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Max Cell Height</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * hint layout to keep the height of the column the element is assigned to less than defined here. 
     * If another element in this column has a minCellHeight that is higher than this maxHeight, 
     * the maxHeight is ignored.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Max Cell Height</em>' attribute.
     * @see #setMaxCellHeight(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKGridPlacementData_MaxCellHeight()
     * @model
     * @generated
     */
    float getMaxCellHeight();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KGridPlacementData#getMaxCellHeight <em>Max Cell Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Max Cell Height</em>' attribute.
     * @see #getMaxCellHeight()
     * @generated
     */
    void setMaxCellHeight(float value);

} // KGridPlacementData
