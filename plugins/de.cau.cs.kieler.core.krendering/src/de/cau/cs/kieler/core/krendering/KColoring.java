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
 * A representation of the model object '<em><b>KColoring</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines the alphaChannel and Color of an Object
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KColoring#getAlpha <em>Alpha</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KColoring#getColor <em>Color</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KColoring#getTargetColor <em>Target Color</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KColoring#getTargetAlpha <em>Target Alpha</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KColoring#getGradientAngle <em>Gradient Angle</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKColoring()
 * @model abstract="true"
 * @generated
 */
public interface KColoring<T extends KColoring<T>> extends KStyle {
    /**
     * Returns the value of the '<em><b>Alpha</b></em>' attribute.
     * The default value is <code>"255"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The transparancy value of this color.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Alpha</em>' attribute.
     * @see #setAlpha(int)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKColoring_Alpha()
     * @model default="255"
     * @generated
     */
    int getAlpha();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KColoring#getAlpha <em>Alpha</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Alpha</em>' attribute.
     * @see #getAlpha()
     * @generated
     */
    void setAlpha(int value);

    /**
     * Returns the value of the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The color the object is supposed to get.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Color</em>' containment reference.
     * @see #setColor(KColor)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKColoring_Color()
     * @model containment="true"
     * @generated
     */
    KColor getColor();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KColoring#getColor <em>Color</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Color</em>' containment reference.
     * @see #getColor()
     * @generated
     */
    void setColor(KColor value);

    /**
     * Returns the value of the '<em><b>Target Color</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Optional target color for gradients. If this is not set no gradient will be displayed.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Target Color</em>' containment reference.
     * @see #setTargetColor(KColor)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKColoring_TargetColor()
     * @model containment="true"
     * @generated
     */
    KColor getTargetColor();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KColoring#getTargetColor <em>Target Color</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Color</em>' containment reference.
     * @see #getTargetColor()
     * @generated
     */
    void setTargetColor(KColor value);

    /**
     * Returns the value of the '<em><b>Target Alpha</b></em>' attribute.
     * The default value is <code>"255"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Optional alpha value for gradient target color.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Target Alpha</em>' attribute.
     * @see #setTargetAlpha(int)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKColoring_TargetAlpha()
     * @model default="255"
     * @generated
     */
    int getTargetAlpha();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KColoring#getTargetAlpha <em>Target Alpha</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Alpha</em>' attribute.
     * @see #getTargetAlpha()
     * @generated
     */
    void setTargetAlpha(int value);

    /**
     * Returns the value of the '<em><b>Gradient Angle</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Angle for the optional gradient. Will be ignored if no targetColor is set.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Gradient Angle</em>' attribute.
     * @see #setGradientAngle(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKColoring_GradientAngle()
     * @model default="0"
     * @generated
     */
    float getGradientAngle();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KColoring#getGradientAngle <em>Gradient Angle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Gradient Angle</em>' attribute.
     * @see #getGradientAngle()
     * @generated
     */
    void setGradientAngle(float value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring the color or gradient source color of <code>this</code> KColoring instance.<br>
     * Redirects to {@link de.cau.cs.kieler.core.krendering.KRenderingUtil#setColor(KColoring, int, int, int) KRenderingUtil.setColor(KColoring, int, int, int)}.
     * @param red the red component of the desired color in range of 0 to 255
     * @param green the green component of the desired color in range of 0 to 255
     * @param blue the blue component of the desired color in range of 0 to 255
     * <!-- end-model-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='@SuppressWarnings(\"unchecked\")\nfinal T it = (T) this;\nreturn de.cau.cs.kieler.core.krendering.KRenderingUtil.setColor(it, red, green, blue);'"
     * @generated
     */
    T setColor(int red, int green, int blue);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring the color & alpha or gradient source color & source alpha of <code>this</code> KColoring instance.<br>
     * Redirects to {@link de.cau.cs.kieler.core.krendering.KRenderingUtil#setColor(KColoring, int, int, int, int) KRenderingUtil.setColor(KColoring, int, int, int, int)}.
     * @param red the red component of the desired color in range of 0 to 255
     * @param green the green component of the desired color in range of 0 to 255
     * @param blue the blue component of the desired color in range of 0 to 255
     * @param alpha the alpha component of the desired color in range of 0 to 255
     * <!-- end-model-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='@SuppressWarnings(\"unchecked\")\nfinal T it = (T) this;\nreturn de.cau.cs.kieler.core.krendering.KRenderingUtil.setColor(it, red, green, blue, alpha);'"
     * @generated
     */
    T setColor(int red, int green, int blue, int alpha);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring the gradient target color of <code>this</code> KColoring instance.<br>
     * Redirects to {@link de.cau.cs.kieler.core.krendering.KRenderingUtil#setTargetColor(KColoring, int, int, int) KRenderingUtil.setTargetColor(KColoring, int, int, int)}.
     * @param red the red component of the desired color in range of 0 to 255
     * @param green the green component of the desired color in range of 0 to 255
     * @param blue the blue component of the desired color in range of 0 to 255
     * <!-- end-model-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='@SuppressWarnings(\"unchecked\")\nfinal T it = (T) this;\nreturn de.cau.cs.kieler.core.krendering.KRenderingUtil.setTargetColor(it, red, green, blue);'"
     * @generated
     */
    T setTargetColor(int red, int green, int blue);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring the gradient target color & target alpha of <code>this</code> KColoring instance.<br>
     * Redirects to {@link de.cau.cs.kieler.core.krendering.KRenderingUtil#setTargetColor(KColoring, int, int, int, int) KRenderingUtil.setTargetColor(KColoring, int, int, int, int)}.
     * @param red the red component of the desired color in range of 0 to 255
     * @param green the green component of the desired color in range of 0 to 255
     * @param blue the blue component of the desired color in range of 0 to 255
     * @param alpha the alpha component of the desired color in range of 0 to 255
     * <!-- end-model-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='@SuppressWarnings(\"unchecked\")\nfinal T it = (T) this;\nreturn de.cau.cs.kieler.core.krendering.KRenderingUtil.setTargetColor(it, red, green, blue, alpha);'"
     * @generated
     */
    T setTargetColor(int red, int green, int blue, int alpha);

} // KColoring
