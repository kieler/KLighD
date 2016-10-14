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
 * A representation of the model object '<em><b>KText Underline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * FontStyle to add an unterline to an text element.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KTextUnderline#getUnderline <em>Underline</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KTextUnderline#getColor <em>Color</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKTextUnderline()
 * @model
 * @generated
 */
public interface KTextUnderline extends KStyle {
    /**
     * Returns the value of the '<em><b>Underline</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.klighd.krendering.Underline}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The style of the underline.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Underline</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.Underline
     * @see #setUnderline(Underline)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKTextUnderline_Underline()
     * @model required="true"
     * @generated
     */
    Underline getUnderline();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KTextUnderline#getUnderline <em>Underline</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Underline</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.Underline
     * @see #getUnderline()
     * @generated
     */
    void setUnderline(Underline value);

    /**
     * Returns the value of the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Color the underline is supposed to have.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Color</em>' containment reference.
     * @see #setColor(KColor)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKTextUnderline_Color()
     * @model containment="true"
     * @generated
     */
    KColor getColor();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KTextUnderline#getColor <em>Color</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Color</em>' containment reference.
     * @see #getColor()
     * @generated
     */
    void setColor(KColor value);

} // KTextUnderline
