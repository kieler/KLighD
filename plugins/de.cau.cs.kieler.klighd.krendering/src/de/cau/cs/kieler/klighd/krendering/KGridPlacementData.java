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
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KGridPlacementData#getMinCellWidth <em>Min Cell Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KGridPlacementData#getMinCellHeight <em>Min Cell Height</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KGridPlacementData#getFlexibleWidth <em>Flexible Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KGridPlacementData#getFlexibleHeight <em>Flexible Height</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKGridPlacementData()
 * @model
 * @generated
 */
public interface KGridPlacementData extends KAreaPlacementData {
    /**
     * Returns the value of the '<em><b>Min Cell Width</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * force layout to make the column that contains the element this placementdata is assigned to as wide as defined here. If another element in that column has a maxCellWidth that is less than this minWidth, the maxWidth is ignored.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Min Cell Width</em>' attribute.
     * @see #setMinCellWidth(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKGridPlacementData_MinCellWidth()
     * @model default="0"
     * @generated
     */
    float getMinCellWidth();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KGridPlacementData#getMinCellWidth <em>Min Cell Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Min Cell Width</em>' attribute.
     * @see #getMinCellWidth()
     * @generated
     */
    void setMinCellWidth(float value);

    /**
     * Returns the value of the '<em><b>Min Cell Height</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * force layout to make the row that contains the element this placementdata is assigned to as high as defined here. If another element in that column has a maxCellHeight that is less than this minHeight, the maxHeight is ignored.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Min Cell Height</em>' attribute.
     * @see #setMinCellHeight(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKGridPlacementData_MinCellHeight()
     * @model default="0"
     * @generated
     */
    float getMinCellHeight();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KGridPlacementData#getMinCellHeight <em>Min Cell Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Min Cell Height</em>' attribute.
     * @see #getMinCellHeight()
     * @generated
     */
    void setMinCellHeight(float value);

    /**
     * Returns the value of the '<em><b>Flexible Width</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * This flag marks the child rendering to cover exactly the required width obtained by means of the minimal size estimation, except for width increases by the macro layout, i.e. by KIML, e.g. in a single column grid.
     * If false, more space may be assigned to the related child rendering, if necessary.<br>
     * <br>
     * This flag is the replacement of the concrete 'maxCellWidth' value and not examined by KLighD, yet.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Flexible Width</em>' attribute.
     * @see #setFlexibleWidth(Boolean)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKGridPlacementData_FlexibleWidth()
     * @model default="true"
     * @generated
     */
    Boolean getFlexibleWidth();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KGridPlacementData#getFlexibleWidth <em>Flexible Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Flexible Width</em>' attribute.
     * @see #getFlexibleWidth()
     * @generated
     */
    void setFlexibleWidth(Boolean value);

    /**
     * Returns the value of the '<em><b>Flexible Height</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * This flag marks the child rendering to cover exactly the required height obtained by means of the minimal size estimation, except for height increases by the macro layout, i.e. by KIML, e.g. in a single row grid.
     * If false, more space may be assigned to the related child rendering, if necessary.<br>
     * <br>
     * This flag is the replacement of the concrete 'maxCellHeight' value and not examined by KLighD, yet.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Flexible Height</em>' attribute.
     * @see #setFlexibleHeight(Boolean)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKGridPlacementData_FlexibleHeight()
     * @model default="true"
     * @generated
     */
    Boolean getFlexibleHeight();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KGridPlacementData#getFlexibleHeight <em>Flexible Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Flexible Height</em>' attribute.
     * @see #getFlexibleHeight()
     * @generated
     */
    void setFlexibleHeight(Boolean value);

} // KGridPlacementData
