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
 * A representation of the model object '<em><b>KLine Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines the line style of a rendering by setting one of the available values of the LineStyle enumeration.
 * 'dashPattern' and 'dashOffset' are evaluated if and only if the literal 'CUSTOM' is chosen.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KLineStyle#getLineStyle <em>Line Style</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KLineStyle#getDashPattern <em>Dash Pattern</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KLineStyle#getDashOffset <em>Dash Offset</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKLineStyle()
 * @model
 * @generated
 */
public interface KLineStyle extends KStyle {
    /**
     * Returns the value of the '<em><b>Line Style</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.klighd.krendering.LineStyle}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Style</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Line Style</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.LineStyle
     * @see #setLineStyle(LineStyle)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKLineStyle_LineStyle()
     * @model required="true"
     * @generated
     */
    LineStyle getLineStyle();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KLineStyle#getLineStyle <em>Line Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Line Style</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.LineStyle
     * @see #getLineStyle()
     * @generated
     */
    void setLineStyle(LineStyle value);

    /**
     * Returns the value of the '<em><b>Dash Pattern</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Float}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dash Pattern</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dash Pattern</em>' attribute list.
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKLineStyle_DashPattern()
     * @model
     * @generated
     */
    EList<Float> getDashPattern();

    /**
     * Returns the value of the '<em><b>Dash Offset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dash Offset</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dash Offset</em>' attribute.
     * @see #setDashOffset(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKLineStyle_DashOffset()
     * @model
     * @generated
     */
    float getDashOffset();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KLineStyle#getDashOffset <em>Dash Offset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Dash Offset</em>' attribute.
     * @see #getDashOffset()
     * @generated
     */
    void setDashOffset(float value);

} // KLineStyle
