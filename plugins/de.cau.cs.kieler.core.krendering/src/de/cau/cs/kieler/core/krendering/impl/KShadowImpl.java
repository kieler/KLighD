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
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KShadow;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KShadow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KShadowImpl#getXOffset <em>XOffset</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KShadowImpl#getYOffset <em>YOffset</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KShadowImpl#getBlur <em>Blur</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KShadowImpl#getColor <em>Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KShadowImpl extends KStyleImpl implements KShadow {
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
     * The default value of the '{@link #getBlur() <em>Blur</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBlur()
     * @generated
     * @ordered
     */
    protected static final float BLUR_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getBlur() <em>Blur</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBlur()
     * @generated
     * @ordered
     */
    protected float blur = BLUR_EDEFAULT;

    /**
     * The cached value of the '{@link #getColor() <em>Color</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected KColor color;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KShadowImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KSHADOW;
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
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KSHADOW__XOFFSET, oldXOffset, xOffset));
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
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KSHADOW__YOFFSET, oldYOffset, yOffset));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getBlur() {
        return blur;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBlur(float newBlur) {
        float oldBlur = blur;
        blur = newBlur;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KSHADOW__BLUR, oldBlur, blur));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KColor getColor() {
        if (color != null && color.eIsProxy()) {
            InternalEObject oldColor = (InternalEObject)color;
            color = (KColor)eResolveProxy(oldColor);
            if (color != oldColor) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, KRenderingPackage.KSHADOW__COLOR, oldColor, color));
            }
        }
        return color;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KColor basicGetColor() {
        return color;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setColor(KColor newColor) {
        KColor oldColor = color;
        color = newColor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KSHADOW__COLOR, oldColor, color));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KSHADOW__XOFFSET:
                return getXOffset();
            case KRenderingPackage.KSHADOW__YOFFSET:
                return getYOffset();
            case KRenderingPackage.KSHADOW__BLUR:
                return getBlur();
            case KRenderingPackage.KSHADOW__COLOR:
                if (resolve) return getColor();
                return basicGetColor();
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
            case KRenderingPackage.KSHADOW__XOFFSET:
                setXOffset((Float)newValue);
                return;
            case KRenderingPackage.KSHADOW__YOFFSET:
                setYOffset((Float)newValue);
                return;
            case KRenderingPackage.KSHADOW__BLUR:
                setBlur((Float)newValue);
                return;
            case KRenderingPackage.KSHADOW__COLOR:
                setColor((KColor)newValue);
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
            case KRenderingPackage.KSHADOW__XOFFSET:
                setXOffset(XOFFSET_EDEFAULT);
                return;
            case KRenderingPackage.KSHADOW__YOFFSET:
                setYOffset(YOFFSET_EDEFAULT);
                return;
            case KRenderingPackage.KSHADOW__BLUR:
                setBlur(BLUR_EDEFAULT);
                return;
            case KRenderingPackage.KSHADOW__COLOR:
                setColor((KColor)null);
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
            case KRenderingPackage.KSHADOW__XOFFSET:
                return xOffset != XOFFSET_EDEFAULT;
            case KRenderingPackage.KSHADOW__YOFFSET:
                return yOffset != YOFFSET_EDEFAULT;
            case KRenderingPackage.KSHADOW__BLUR:
                return blur != BLUR_EDEFAULT;
            case KRenderingPackage.KSHADOW__COLOR:
                return color != null;
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
        result.append(" (xOffset: ");
        result.append(xOffset);
        result.append(", yOffset: ");
        result.append(yOffset);
        result.append(", blur: ");
        result.append(blur);
        result.append(')');
        return result.toString();
    }

} //KShadowImpl
