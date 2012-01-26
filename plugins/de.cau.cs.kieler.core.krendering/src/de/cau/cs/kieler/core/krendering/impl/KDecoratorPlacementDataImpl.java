/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.core.krendering.impl;

import de.cau.cs.kieler.core.krendering.KDecoratorPlacementData;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KDecorator Placement Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KDecoratorPlacementDataImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KDecoratorPlacementDataImpl#getXOffset <em>XOffset</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KDecoratorPlacementDataImpl#getYOffset <em>YOffset</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KDecoratorPlacementDataImpl#isRelative <em>Relative</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KDecoratorPlacementDataImpl extends EObjectImpl implements KDecoratorPlacementData {
    /**
     * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLocation()
     * @generated
     * @ordered
     */
    protected static final float LOCATION_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLocation()
     * @generated
     * @ordered
     */
    protected float location = LOCATION_EDEFAULT;

    /**
     * The default value of the '{@link #getXOffset() <em>XOffset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXOffset()
     * @generated
     * @ordered
     */
    protected static final float XOFFSET_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getXOffset() <em>XOffset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXOffset()
     * @generated
     * @ordered
     */
    protected float xOffset = XOFFSET_EDEFAULT;

    /**
     * The default value of the '{@link #getYOffset() <em>YOffset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getYOffset()
     * @generated
     * @ordered
     */
    protected static final float YOFFSET_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getYOffset() <em>YOffset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getYOffset()
     * @generated
     * @ordered
     */
    protected float yOffset = YOFFSET_EDEFAULT;

    /**
     * The default value of the '{@link #isRelative() <em>Relative</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isRelative()
     * @generated
     * @ordered
     */
    protected static final boolean RELATIVE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isRelative() <em>Relative</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isRelative()
     * @generated
     * @ordered
     */
    protected boolean relative = RELATIVE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KDecoratorPlacementDataImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KDECORATOR_PLACEMENT_DATA;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getLocation() {
        return location;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLocation(float newLocation) {
        float oldLocation = location;
        location = newLocation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KDECORATOR_PLACEMENT_DATA__LOCATION, oldLocation, location));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getXOffset() {
        return xOffset;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setXOffset(float newXOffset) {
        float oldXOffset = xOffset;
        xOffset = newXOffset;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KDECORATOR_PLACEMENT_DATA__XOFFSET, oldXOffset, xOffset));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getYOffset() {
        return yOffset;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setYOffset(float newYOffset) {
        float oldYOffset = yOffset;
        yOffset = newYOffset;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KDECORATOR_PLACEMENT_DATA__YOFFSET, oldYOffset, yOffset));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isRelative() {
        return relative;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRelative(boolean newRelative) {
        boolean oldRelative = relative;
        relative = newRelative;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KDECORATOR_PLACEMENT_DATA__RELATIVE, oldRelative, relative));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA__LOCATION:
                return getLocation();
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA__XOFFSET:
                return getXOffset();
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA__YOFFSET:
                return getYOffset();
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA__RELATIVE:
                return isRelative();
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
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA__LOCATION:
                setLocation((Float)newValue);
                return;
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA__XOFFSET:
                setXOffset((Float)newValue);
                return;
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA__YOFFSET:
                setYOffset((Float)newValue);
                return;
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA__RELATIVE:
                setRelative((Boolean)newValue);
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
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA__LOCATION:
                setLocation(LOCATION_EDEFAULT);
                return;
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA__XOFFSET:
                setXOffset(XOFFSET_EDEFAULT);
                return;
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA__YOFFSET:
                setYOffset(YOFFSET_EDEFAULT);
                return;
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA__RELATIVE:
                setRelative(RELATIVE_EDEFAULT);
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
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA__LOCATION:
                return location != LOCATION_EDEFAULT;
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA__XOFFSET:
                return xOffset != XOFFSET_EDEFAULT;
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA__YOFFSET:
                return yOffset != YOFFSET_EDEFAULT;
            case KRenderingPackage.KDECORATOR_PLACEMENT_DATA__RELATIVE:
                return relative != RELATIVE_EDEFAULT;
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
        result.append(" (location: ");
        result.append(location);
        result.append(", xOffset: ");
        result.append(xOffset);
        result.append(", yOffset: ");
        result.append(yOffset);
        result.append(", relative: ");
        result.append(relative);
        result.append(')');
        return result.toString();
    }

} //KDecoratorPlacementDataImpl
