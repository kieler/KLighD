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
 * A representation of the model object '<em><b>KText Strikeout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * FontStyle to add a strikeout to an text element.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KTextStrikeout#getStruckOut <em>Struck Out</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KTextStrikeout#getColor <em>Color</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKTextStrikeout()
 * @model
 * @generated
 */
public interface KTextStrikeout extends KStyle {
    /**
     * Returns the value of the '<em><b>Struck Out</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Struck Out</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Set true to draw the text with a strikeout.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Struck Out</em>' attribute.
     * @see #setStruckOut(Boolean)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKTextStrikeout_StruckOut()
     * @model default="true" required="true"
     * @generated
     */
    Boolean getStruckOut();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KTextStrikeout#getStruckOut <em>Struck Out</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Struck Out</em>' attribute.
     * @see #getStruckOut()
     * @generated
     */
    void setStruckOut(Boolean value);

    /**
     * Returns the value of the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Color</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Color the strikeout is supposed to have.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Color</em>' containment reference.
     * @see #setColor(KColor)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKTextStrikeout_Color()
     * @model containment="true"
     * @generated
     */
    KColor getColor();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KTextStrikeout#getColor <em>Color</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Color</em>' containment reference.
     * @see #getColor()
     * @generated
     */
    void setColor(KColor value);

} // KTextStrikeout
