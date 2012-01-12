/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KArc</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link krendering.KArc#getStartAngle <em>Start Angle</em>}</li>
 *   <li>{@link krendering.KArc#getArcAngle <em>Arc Angle</em>}</li>
 * </ul>
 * </p>
 *
 * @see krendering.KRenderingPackage#getKArc()
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
     * @return the value of the '<em>Start Angle</em>' attribute.
     * @see #setStartAngle(float)
     * @see krendering.KRenderingPackage#getKArc_StartAngle()
     * @model
     * @generated
     */
    float getStartAngle();

    /**
     * Sets the value of the '{@link krendering.KArc#getStartAngle <em>Start Angle</em>}' attribute.
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
     * @return the value of the '<em>Arc Angle</em>' attribute.
     * @see #setArcAngle(float)
     * @see krendering.KRenderingPackage#getKArc_ArcAngle()
     * @model
     * @generated
     */
    float getArcAngle();

    /**
     * Sets the value of the '{@link krendering.KArc#getArcAngle <em>Arc Angle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Arc Angle</em>' attribute.
     * @see #getArcAngle()
     * @generated
     */
    void setArcAngle(float value);

} // KArc
