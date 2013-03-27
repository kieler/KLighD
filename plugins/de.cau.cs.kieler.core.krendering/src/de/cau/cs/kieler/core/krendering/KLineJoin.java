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
 * A representation of the model object '<em><b>KLine Join</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines the line join style of a rendering by setting one of the available values of the LineJoin enumeration.
 * 'miterLimit' is evaluated if and only if the literal 'MITER' is chosen.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KLineJoin#getLineJoin <em>Line Join</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KLineJoin#getMiterLimit <em>Miter Limit</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKLineJoin()
 * @model
 * @generated
 */
public interface KLineJoin extends KStyle {
    /**
     * Returns the value of the '<em><b>Line Join</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.core.krendering.LineJoin}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Join</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Line Join</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.LineJoin
     * @see #setLineJoin(LineJoin)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKLineJoin_LineJoin()
     * @model required="true"
     * @generated
     */
    LineJoin getLineJoin();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KLineJoin#getLineJoin <em>Line Join</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Line Join</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.LineJoin
     * @see #getLineJoin()
     * @generated
     */
    void setLineJoin(LineJoin value);

    /**
     * Returns the value of the '<em><b>Miter Limit</b></em>' attribute.
     * The default value is <code>"10"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Miter Limit</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Miter Limit</em>' attribute.
     * @see #setMiterLimit(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKLineJoin_MiterLimit()
     * @model default="10"
     * @generated
     */
    float getMiterLimit();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KLineJoin#getMiterLimit <em>Miter Limit</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Miter Limit</em>' attribute.
     * @see #getMiterLimit()
     * @generated
     */
    void setMiterLimit(float value);

} // KLineJoin
