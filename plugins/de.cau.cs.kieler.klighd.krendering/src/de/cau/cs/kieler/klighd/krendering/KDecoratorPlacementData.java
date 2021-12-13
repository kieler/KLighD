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
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KDecorator Placement Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * defines appearance and position of decorators
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getAbsolute <em>Absolute</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getXOffset <em>XOffset</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getYOffset <em>YOffset</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#isRotateWithLine <em>Rotate With Line</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getWidth <em>Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getHeight <em>Height</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getRelative <em>Relative</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKDecoratorPlacementData()
 * @model
 * @generated
 */
public interface KDecoratorPlacementData extends KPlacementData {
    /**
     * Returns the value of the '<em><b>Absolute</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * can be used to define an absolute indent of the decorator on the decorated object
     * 
     * <!-- end-model-doc -->
     * @return the value of the '<em>Absolute</em>' attribute.
     * @see #setAbsolute(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKDecoratorPlacementData_Absolute()
     * @model required="true"
     * @generated
     */
    float getAbsolute();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getAbsolute <em>Absolute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Absolute</em>' attribute.
     * @see #getAbsolute()
     * @generated
     */
    void setAbsolute(float value);

    /**
     * Returns the value of the '<em><b>XOffset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * place decorator n units in x-direction next to the decorated object
     * <!-- end-model-doc -->
     * @return the value of the '<em>XOffset</em>' attribute.
     * @see #setXOffset(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKDecoratorPlacementData_XOffset()
     * @model
     * @generated
     */
    float getXOffset();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getXOffset <em>XOffset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>XOffset</em>' attribute.
     * @see #getXOffset()
     * @generated
     */
    void setXOffset(float value);

    /**
     * Returns the value of the '<em><b>YOffset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * place decorator n units in y-direction next to the decorated object
     * <!-- end-model-doc -->
     * @return the value of the '<em>YOffset</em>' attribute.
     * @see #setYOffset(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKDecoratorPlacementData_YOffset()
     * @model
     * @generated
     */
    float getYOffset();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getYOffset <em>YOffset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>YOffset</em>' attribute.
     * @see #getYOffset()
     * @generated
     */
    void setYOffset(float value);

    /**
     * Returns the value of the '<em><b>Rotate With Line</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * define whether the decorator should rotate, when the line is rotated. If set to false, an arrow will always point in the same direction, regardless of the direction the line is pointing in
     * <!-- end-model-doc -->
     * @return the value of the '<em>Rotate With Line</em>' attribute.
     * @see #setRotateWithLine(boolean)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKDecoratorPlacementData_RotateWithLine()
     * @model required="true"
     * @generated
     */
    boolean isRotateWithLine();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#isRotateWithLine <em>Rotate With Line</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Rotate With Line</em>' attribute.
     * @see #isRotateWithLine()
     * @generated
     */
    void setRotateWithLine(boolean value);

    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * width of the decorator
     * <!-- end-model-doc -->
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKDecoratorPlacementData_Width()
     * @model required="true"
     * @generated
     */
    float getWidth();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getWidth <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(float value);

    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * height of the decorator
     * <!-- end-model-doc -->
     * @return the value of the '<em>Height</em>' attribute.
     * @see #setHeight(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKDecoratorPlacementData_Height()
     * @model required="true"
     * @generated
     */
    float getHeight();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getHeight <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Height</em>' attribute.
     * @see #getHeight()
     * @generated
     */
    void setHeight(float value);

    /**
     * Returns the value of the '<em><b>Relative</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * relative position based on the size of the decorated object
     * <!-- end-model-doc -->
     * @return the value of the '<em>Relative</em>' attribute.
     * @see #setRelative(float)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKDecoratorPlacementData_Relative()
     * @model
     * @generated
     */
    float getRelative();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KDecoratorPlacementData#getRelative <em>Relative</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Relative</em>' attribute.
     * @see #getRelative()
     * @generated
     */
    void setRelative(float value);

} // KDecoratorPlacementData
