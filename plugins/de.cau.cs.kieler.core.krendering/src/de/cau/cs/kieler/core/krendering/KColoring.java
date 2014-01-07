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

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Checks the equalilty of the RGB components of <code>this</code> KColor instance and the provided <code>other</code> KColor.
     * Returns false if <code>other</code> is not a KColor.<br>
     * {@link de.cau.cs.kieler.core.krendering.KRenderingUtil#equals(KColor, Object) KRenderingUtil.equals(KColor, Object)}.
     * 
     * @return <code>true</code> if <code>other</code> is a KColor its RGB components are equal to those of <code>this</code> instance, <code>false</code> otherwise
     * <!-- end-model-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return de.cau.cs.kieler.core.krendering.KRenderingUtil.equals(this,other);'"
     * @generated
     */
    boolean equals(Object other);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring the color or gradient source color of <code>this</code> KColoring instance.<br>
     * Simply redirects to {@link #setColor(KColor)} and returns <code>this</code> KColoring instance instead of <code>void</code>.
     * @param color an instance of {@link KColor}
     * <!-- end-model-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='@SuppressWarnings(\"unchecked\")\nfinal T it = (T) this;\nthis.setColor(color);\nreturn it;'"
     * @generated
     */
    T setColor2(KColor color);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring the color & alpha or gradient source color & alpha of <code>this</code> KColoring instance.<br>
     * Simply redirects to {@link #setColor(KColor)} and {@link #setAlpha(int)}, and returns <code>this</code> KColoring instance instead of <code>void</code>.
     * @param color an instance of {@link KColor}
     * @param alpha the alpha component of the desired color in range of 0 to 255
     * <!-- end-model-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='@SuppressWarnings(\"unchecked\")\nfinal T it = (T) this;\nthis.setColor(color);\nthis.setAlpha(alpha);\nreturn it;'"
     * @generated
     */
    T setColor2(KColor color, int alpha);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring the gradient target color of <code>this</code> KColoring instance.<br>
     * Simply redirects to {@link #setTargetColor(KColor)} and returns <code>this</code> KColoring instance instead of <code>void</code>.
     * @param targetColor an instance of {@link KColor}
     * <!-- end-model-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='@SuppressWarnings(\"unchecked\")\nfinal T it = (T) this;\nthis.setTargetColor(targetColor);\nreturn it;'"
     * @generated
     */
    T setTargetColor2(KColor targetColor);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring the gradient target color & target alpha of <code>this</code> KColoring instance.<br>
     * Simply redirects to {@link #setTargetColor(KColor)} and {@link #setTargetAlpha(int)}, and returns <code>this</code> KColoring instance instead of <code>void</code>.
     * @param targetColor an instance of {@link KColor}
     * @param targetAlpha the alpha component of the desired color in range of 0 to 255
     * <!-- end-model-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='@SuppressWarnings(\"unchecked\")\nfinal T it = (T) this;\nthis.setTargetColor(targetColor);\nthis.setTargetAlpha(targetAlpha);\nreturn it;'"
     * @generated
     */
    T setTargetColor2(KColor targetColor, int targetAlpha);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring the color or gradient source color of <code>this</code> KColoring instance.<br>
     * Redirects to {@link de.cau.cs.kieler.core.krendering.KRenderingUtil#setColor(KColoring, Colors) KRenderingUtil.setColor(KColoring, Colors)}.
     * @param color a color constant from the {@link Colors} enumeration
     * <!-- end-model-doc -->
     * @model colorDataType="de.cau.cs.kieler.core.krendering.Colors"
     *        annotation="http://www.eclipse.org/emf/2002/GenModel body='@SuppressWarnings(\"unchecked\")\nfinal T it = (T) this;\nreturn de.cau.cs.kieler.core.krendering.KRenderingUtil.setColor(it, color);'"
     * @generated
     */
    T setColor(Colors color);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring the color & alpha or gradient source color & source alpha of <code>this</code> KColoring instance.<br>
     * Redirects to {@link de.cau.cs.kieler.core.krendering.KRenderingUtil#setColor(KColoring, Colors, int) KRenderingUtil.setColor(KColoring, Colors, int)}.
     * @param color a color constant from the {@link Colors} enumeration
     * @param alpha the alpha component of the desired color in range of 0 to 255
     * <!-- end-model-doc -->
     * @model colorDataType="de.cau.cs.kieler.core.krendering.Colors"
     *        annotation="http://www.eclipse.org/emf/2002/GenModel body='@SuppressWarnings(\"unchecked\")\nfinal T it = (T) this;\nreturn de.cau.cs.kieler.core.krendering.KRenderingUtil.setColor(it, color, alpha);'"
     * @generated
     */
    T setColor(Colors color, int alpha);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring the gradient target color of <code>this</code> KColoring instance.<br>
     * Redirects to {@link de.cau.cs.kieler.core.krendering.KRenderingUtil#setTargetColor(KColoring, Colors) KRenderingUtil.setTargetColor(KColoring, Colors)}.
     * @param color a color constant from the {@link Colors} enumeration
     * <!-- end-model-doc -->
     * @model colorDataType="de.cau.cs.kieler.core.krendering.Colors"
     *        annotation="http://www.eclipse.org/emf/2002/GenModel body='@SuppressWarnings(\"unchecked\")\nfinal T it = (T) this;\nreturn de.cau.cs.kieler.core.krendering.KRenderingUtil.setTargetColor(it, color);'"
     * @generated
     */
    T setTargetColor(Colors color);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Convenience setter for configuring the gradient target color & target alpha of <code>this</code> KColoring instance.<br>
     * Redirects to {@link de.cau.cs.kieler.core.krendering.KRenderingUtil#setTargetColor(KColoring, Colors, int) KRenderingUtil.setTargetColor(KColoring, Colors, int)}.
     * @param color a color constant from the {@link Colors} enumeration
     * @param alpha the alpha component of the desired color in range of 0 to 255
     * <!-- end-model-doc -->
     * @model colorDataType="de.cau.cs.kieler.core.krendering.Colors"
     *        annotation="http://www.eclipse.org/emf/2002/GenModel body='@SuppressWarnings(\"unchecked\")\nfinal T it = (T) this;\nreturn de.cau.cs.kieler.core.krendering.KRenderingUtil.setTargetColor(it, color, alpha);'"
     * @generated
     */
    T setTargetColor(Colors color, int alpha);

} // KColoring
