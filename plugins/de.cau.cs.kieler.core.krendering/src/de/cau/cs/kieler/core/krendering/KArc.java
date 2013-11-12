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
 * A representation of the model object '<em><b>KArc</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Draws an arc. Needs the startingAngle of the arc (0° = rightmost vertical line) on an ellipse and the angle the arc should cover (counterclockwise on the same ellipse)
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KArc#getStartAngle <em>Start Angle</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KArc#getArcAngle <em>Arc Angle</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KArc#getArcType <em>Arc Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKArc()
 * @model
 * @generated
 */
public interface KArc extends KContainerRendering {
    /**
     * Returns the value of the '<em><b>Start Angle</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Start Angle</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * startingAngle of the arc (0° = rightmost vertical line) on an ellipse
     * <!-- end-model-doc -->
     * @return the value of the '<em>Start Angle</em>' attribute.
     * @see #setStartAngle(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKArc_StartAngle()
     * @model
     * @generated
     */
    float getStartAngle();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KArc#getStartAngle <em>Start Angle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Start Angle</em>' attribute.
     * @see #getStartAngle()
     * @generated
     */
    void setStartAngle(float value);

    /**
     * Returns the value of the '<em><b>Arc Angle</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Arc Angle</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the angle the arc should cover (counterclockwise)
     * <!-- end-model-doc -->
     * @return the value of the '<em>Arc Angle</em>' attribute.
     * @see #setArcAngle(float)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKArc_ArcAngle()
     * @model
     * @generated
     */
    float getArcAngle();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KArc#getArcAngle <em>Arc Angle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Arc Angle</em>' attribute.
     * @see #getArcAngle()
     * @generated
     */
    void setArcAngle(float value);

    /**
     * Returns the value of the '<em><b>Arc Type</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.core.krendering.Arc}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Arc Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Sets the type the arc is supposed to have. See Arc Enum.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Arc Type</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.Arc
     * @see #setArcType(Arc)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKArc_ArcType()
     * @model
     * @generated
     */
    Arc getArcType();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KArc#getArcType <em>Arc Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Arc Type</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.Arc
     * @see #getArcType()
     * @generated
     */
    void setArcType(Arc value);

} // KArc
