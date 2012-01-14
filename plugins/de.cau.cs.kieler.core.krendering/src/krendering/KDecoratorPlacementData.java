/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KDecorator Placement Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link krendering.KDecoratorPlacementData#getLocation <em>Location</em>}</li>
 *   <li>{@link krendering.KDecoratorPlacementData#getXOffset <em>XOffset</em>}</li>
 *   <li>{@link krendering.KDecoratorPlacementData#getYOffset <em>YOffset</em>}</li>
 *   <li>{@link krendering.KDecoratorPlacementData#isRelative <em>Relative</em>}</li>
 * </ul>
 * </p>
 *
 * @see krendering.KRenderingPackage#getKDecoratorPlacementData()
 * @model
 * @generated
 */
public interface KDecoratorPlacementData extends KPlacementData {
    /**
     * Returns the value of the '<em><b>Location</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Location</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Location</em>' attribute.
     * @see #setLocation(float)
     * @see krendering.KRenderingPackage#getKDecoratorPlacementData_Location()
     * @model required="true"
     * @generated
     */
    float getLocation();

    /**
     * Sets the value of the '{@link krendering.KDecoratorPlacementData#getLocation <em>Location</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Location</em>' attribute.
     * @see #getLocation()
     * @generated
     */
    void setLocation(float value);

    /**
     * Returns the value of the '<em><b>XOffset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XOffset</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>XOffset</em>' attribute.
     * @see #setXOffset(float)
     * @see krendering.KRenderingPackage#getKDecoratorPlacementData_XOffset()
     * @model
     * @generated
     */
    float getXOffset();

    /**
     * Sets the value of the '{@link krendering.KDecoratorPlacementData#getXOffset <em>XOffset</em>}' attribute.
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
     * <p>
     * If the meaning of the '<em>YOffset</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>YOffset</em>' attribute.
     * @see #setYOffset(float)
     * @see krendering.KRenderingPackage#getKDecoratorPlacementData_YOffset()
     * @model
     * @generated
     */
    float getYOffset();

    /**
     * Sets the value of the '{@link krendering.KDecoratorPlacementData#getYOffset <em>YOffset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>YOffset</em>' attribute.
     * @see #getYOffset()
     * @generated
     */
    void setYOffset(float value);

    /**
     * Returns the value of the '<em><b>Relative</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Relative</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Relative</em>' attribute.
     * @see #setRelative(boolean)
     * @see krendering.KRenderingPackage#getKDecoratorPlacementData_Relative()
     * @model required="true"
     * @generated
     */
    boolean isRelative();

    /**
     * Sets the value of the '{@link krendering.KDecoratorPlacementData#isRelative <em>Relative</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Relative</em>' attribute.
     * @see #isRelative()
     * @generated
     */
    void setRelative(boolean value);

} // KDecoratorPlacementData
