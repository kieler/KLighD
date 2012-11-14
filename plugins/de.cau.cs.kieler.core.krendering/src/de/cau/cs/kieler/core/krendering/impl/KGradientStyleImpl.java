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
package de.cau.cs.kieler.core.krendering.impl;

import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KGradientStyle;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KGradient Style</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGradientStyleImpl#getAngle <em>Angle</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGradientStyleImpl#getStartColor <em>Start Color</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGradientStyleImpl#getEndColor <em>End Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KGradientStyleImpl extends KStyleImpl implements KGradientStyle {
    /**
     * The default value of the '{@link #getAngle() <em>Angle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAngle()
     * @generated
     * @ordered
     */
    protected static final float ANGLE_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getAngle() <em>Angle</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAngle()
     * @generated
     * @ordered
     */
    protected float angle = ANGLE_EDEFAULT;

    /**
     * The cached value of the '{@link #getStartColor() <em>Start Color</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStartColor()
     * @generated
     * @ordered
     */
    protected KColor startColor;

    /**
     * The cached value of the '{@link #getEndColor() <em>End Color</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEndColor()
     * @generated
     * @ordered
     */
    protected KColor endColor;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KGradientStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KGRADIENT_STYLE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getAngle() {
        return angle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAngle(float newAngle) {
        float oldAngle = angle;
        angle = newAngle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRADIENT_STYLE__ANGLE, oldAngle, angle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KColor getStartColor() {
        if (startColor != null && startColor.eIsProxy()) {
            InternalEObject oldStartColor = (InternalEObject)startColor;
            startColor = (KColor)eResolveProxy(oldStartColor);
            if (startColor != oldStartColor) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, KRenderingPackage.KGRADIENT_STYLE__START_COLOR, oldStartColor, startColor));
            }
        }
        return startColor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KColor basicGetStartColor() {
        return startColor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStartColor(KColor newStartColor) {
        KColor oldStartColor = startColor;
        startColor = newStartColor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRADIENT_STYLE__START_COLOR, oldStartColor, startColor));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KColor getEndColor() {
        if (endColor != null && endColor.eIsProxy()) {
            InternalEObject oldEndColor = (InternalEObject)endColor;
            endColor = (KColor)eResolveProxy(oldEndColor);
            if (endColor != oldEndColor) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, KRenderingPackage.KGRADIENT_STYLE__END_COLOR, oldEndColor, endColor));
            }
        }
        return endColor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KColor basicGetEndColor() {
        return endColor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndColor(KColor newEndColor) {
        KColor oldEndColor = endColor;
        endColor = newEndColor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRADIENT_STYLE__END_COLOR, oldEndColor, endColor));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KGRADIENT_STYLE__ANGLE:
                return getAngle();
            case KRenderingPackage.KGRADIENT_STYLE__START_COLOR:
                if (resolve) return getStartColor();
                return basicGetStartColor();
            case KRenderingPackage.KGRADIENT_STYLE__END_COLOR:
                if (resolve) return getEndColor();
                return basicGetEndColor();
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
            case KRenderingPackage.KGRADIENT_STYLE__ANGLE:
                setAngle((Float)newValue);
                return;
            case KRenderingPackage.KGRADIENT_STYLE__START_COLOR:
                setStartColor((KColor)newValue);
                return;
            case KRenderingPackage.KGRADIENT_STYLE__END_COLOR:
                setEndColor((KColor)newValue);
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
            case KRenderingPackage.KGRADIENT_STYLE__ANGLE:
                setAngle(ANGLE_EDEFAULT);
                return;
            case KRenderingPackage.KGRADIENT_STYLE__START_COLOR:
                setStartColor((KColor)null);
                return;
            case KRenderingPackage.KGRADIENT_STYLE__END_COLOR:
                setEndColor((KColor)null);
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
            case KRenderingPackage.KGRADIENT_STYLE__ANGLE:
                return angle != ANGLE_EDEFAULT;
            case KRenderingPackage.KGRADIENT_STYLE__START_COLOR:
                return startColor != null;
            case KRenderingPackage.KGRADIENT_STYLE__END_COLOR:
                return endColor != null;
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
        result.append(" (angle: ");
        result.append(angle);
        result.append(')');
        return result.toString();
    }

} //KGradientStyleImpl
