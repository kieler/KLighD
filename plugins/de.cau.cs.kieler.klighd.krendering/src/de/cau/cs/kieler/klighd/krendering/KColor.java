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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KColor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Specifies an RGB color. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KColor#getRed <em>Red</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KColor#getGreen <em>Green</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KColor#getBlue <em>Blue</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKColor()
 * @model
 * @generated
 */
public interface KColor extends EObject {
    /**
     * Returns the value of the '<em><b>Red</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the red component of the color
     * <!-- end-model-doc -->
     * @return the value of the '<em>Red</em>' attribute.
     * @see #setRed(int)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKColor_Red()
     * @model default="0" required="true"
     * @generated
     */
    int getRed();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KColor#getRed <em>Red</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Red</em>' attribute.
     * @see #getRed()
     * @generated
     */
    void setRed(int value);

    /**
     * Returns the value of the '<em><b>Green</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the green component of the color
     * <!-- end-model-doc -->
     * @return the value of the '<em>Green</em>' attribute.
     * @see #setGreen(int)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKColor_Green()
     * @model default="0" required="true"
     * @generated
     */
    int getGreen();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KColor#getGreen <em>Green</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Green</em>' attribute.
     * @see #getGreen()
     * @generated
     */
    void setGreen(int value);

    /**
     * Returns the value of the '<em><b>Blue</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the blue component of the color
     * <!-- end-model-doc -->
     * @return the value of the '<em>Blue</em>' attribute.
     * @see #setBlue(int)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKColor_Blue()
     * @model default="0" required="true"
     * @generated
     */
    int getBlue();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KColor#getBlue <em>Blue</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Blue</em>' attribute.
     * @see #getBlue()
     * @generated
     */
    void setBlue(int value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring<code>this</code> KColor instance.<br>
     * Redirects to {@link de.cau.cs.kieler.klighd.krendering.KRenderingUtil#setColor(KColor, int, int, int) KRenderingUtil.setColor(KColor, int, int, int)}.
     * @param red the red component of the desired color in range of 0 to 255
     * @param green the green component of the desired color in range of 0 to 255
     * @param blue the blue component of the desired color in range of 0 to 255
     * <!-- end-model-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return de.cau.cs.kieler.klighd.krendering.KRenderingUtil.setColor(this, red, green, blue);'"
     * @generated
     */
    KColor setColor(int red, int green, int blue);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring<code>this</code> KColor instance.<br>
     * Redirects to {@link de.cau.cs.kieler.klighd.krendering.KRenderingUtil#setColor(KColor, Colors) KRenderingUtil.setColor(KColor, Colors)}.
     * @param color a color constant from the {@link Colors} enumeration
     * <!-- end-model-doc -->
     * @model colorDataType="de.cau.cs.kieler.klighd.krendering.Colors"
     *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return de.cau.cs.kieler.klighd.krendering.KRenderingUtil.setColor(this, color);'"
     * @generated
     */
    KColor setColor(Colors color);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring<code>this</code> KColor instance.<br>
     * Redirects to {@link de.cau.cs.kieler.klighd.krendering.KRenderingUtil#setColor(KColor, KColor) KRenderingUtil.setColor(KColor, KColor)}.
     * @param kColor
     *   the {@link KColor} instance to take the RGB values from
     *   
     * <!-- end-model-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return de.cau.cs.kieler.klighd.krendering.KRenderingUtil.setColor(this, kColor);'"
     * @generated
     */
    KColor setColor(KColor kColor);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Checks the equality of the RGB components of <code>this</code> KColor instance and the provided <code>other</code> KColor.
     * Returns false if <code>other</code> is not a KColor.<br>
     * {@link de.cau.cs.kieler.klighd.krendering.KRenderingUtil#equals(KColor, Object) KRenderingUtil.equals(KColor, Object)}.
     * 
     * @return <code>true</code> if <code>other</code> is a KColor its RGB components are equal to those of <code>this</code> instance, <code>false</code> otherwise
     * <!-- end-model-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return de.cau.cs.kieler.klighd.krendering.KRenderingUtil.equals(this,other);'"
     * @generated
     */
    boolean equals(Object other);

} // KColor
