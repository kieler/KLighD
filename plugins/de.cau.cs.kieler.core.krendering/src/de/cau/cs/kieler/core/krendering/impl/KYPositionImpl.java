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

import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KYPosition;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KY Position</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KYPositionImpl#getAbsolute <em>Absolute</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KYPositionImpl#getRelative <em>Relative</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class KYPositionImpl extends EObjectImpl implements KYPosition {
    /**
     * The default value of the '{@link #getAbsolute() <em>Absolute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAbsolute()
     * @generated
     * @ordered
     */
    protected static final float ABSOLUTE_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getAbsolute() <em>Absolute</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAbsolute()
     * @generated
     * @ordered
     */
    protected float absolute = ABSOLUTE_EDEFAULT;

    /**
     * The default value of the '{@link #getRelative() <em>Relative</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRelative()
     * @generated
     * @ordered
     */
    protected static final float RELATIVE_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getRelative() <em>Relative</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRelative()
     * @generated
     * @ordered
     */
    protected float relative = RELATIVE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KYPositionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KY_POSITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getAbsolute() {
        return absolute;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAbsolute(float newAbsolute) {
        float oldAbsolute = absolute;
        absolute = newAbsolute;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KY_POSITION__ABSOLUTE, oldAbsolute, absolute));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getRelative() {
        return relative;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRelative(float newRelative) {
        float oldRelative = relative;
        relative = newRelative;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KY_POSITION__RELATIVE, oldRelative, relative));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KY_POSITION__ABSOLUTE:
                return getAbsolute();
            case KRenderingPackage.KY_POSITION__RELATIVE:
                return getRelative();
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
            case KRenderingPackage.KY_POSITION__ABSOLUTE:
                setAbsolute((Float)newValue);
                return;
            case KRenderingPackage.KY_POSITION__RELATIVE:
                setRelative((Float)newValue);
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
            case KRenderingPackage.KY_POSITION__ABSOLUTE:
                setAbsolute(ABSOLUTE_EDEFAULT);
                return;
            case KRenderingPackage.KY_POSITION__RELATIVE:
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
            case KRenderingPackage.KY_POSITION__ABSOLUTE:
                return absolute != ABSOLUTE_EDEFAULT;
            case KRenderingPackage.KY_POSITION__RELATIVE:
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
        result.append(" (absolute: ");
        result.append(absolute);
        result.append(", relative: ");
        result.append(relative);
        result.append(')');
        return result.toString();
    }

} //KYPositionImpl
