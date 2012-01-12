/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package krendering.impl;

import krendering.KArc;
import krendering.KRenderingPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KArc</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link krendering.impl.KArcImpl#getStartAngle <em>Start Angle</em>}</li>
 *   <li>{@link krendering.impl.KArcImpl#getArcAngle <em>Arc Angle</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KArcImpl extends KContainerRenderingImpl implements KArc {
    /**
     * The default value of the '{@link #getStartAngle() <em>Start Angle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStartAngle()
     * @generated
     * @ordered
     */
    protected static final float START_ANGLE_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getStartAngle() <em>Start Angle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStartAngle()
     * @generated
     * @ordered
     */
    protected float startAngle = START_ANGLE_EDEFAULT;

    /**
     * The default value of the '{@link #getArcAngle() <em>Arc Angle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getArcAngle()
     * @generated
     * @ordered
     */
    protected static final float ARC_ANGLE_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getArcAngle() <em>Arc Angle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getArcAngle()
     * @generated
     * @ordered
     */
    protected float arcAngle = ARC_ANGLE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KArcImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KARC;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getStartAngle() {
        return startAngle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStartAngle(float newStartAngle) {
        float oldStartAngle = startAngle;
        startAngle = newStartAngle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KARC__START_ANGLE, oldStartAngle, startAngle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getArcAngle() {
        return arcAngle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setArcAngle(float newArcAngle) {
        float oldArcAngle = arcAngle;
        arcAngle = newArcAngle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KARC__ARC_ANGLE, oldArcAngle, arcAngle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KARC__START_ANGLE:
                return getStartAngle();
            case KRenderingPackage.KARC__ARC_ANGLE:
                return getArcAngle();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case KRenderingPackage.KARC__START_ANGLE:
                setStartAngle((Float)newValue);
                return;
            case KRenderingPackage.KARC__ARC_ANGLE:
                setArcAngle((Float)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case KRenderingPackage.KARC__START_ANGLE:
                setStartAngle(START_ANGLE_EDEFAULT);
                return;
            case KRenderingPackage.KARC__ARC_ANGLE:
                setArcAngle(ARC_ANGLE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case KRenderingPackage.KARC__START_ANGLE:
                return startAngle != START_ANGLE_EDEFAULT;
            case KRenderingPackage.KARC__ARC_ANGLE:
                return arcAngle != ARC_ANGLE_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (startAngle: ");
        result.append(startAngle);
        result.append(", arcAngle: ");
        result.append(arcAngle);
        result.append(')');
        return result.toString();
    }

} //KArcImpl
