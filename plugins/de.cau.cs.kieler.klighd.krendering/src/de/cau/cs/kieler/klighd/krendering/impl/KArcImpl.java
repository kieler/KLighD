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
package de.cau.cs.kieler.klighd.krendering.impl;

import de.cau.cs.kieler.klighd.krendering.Arc;
import de.cau.cs.kieler.klighd.krendering.KArc;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KArc</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KArcImpl#getStartAngle <em>Start Angle</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KArcImpl#getArcAngle <em>Arc Angle</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KArcImpl#getArcType <em>Arc Type</em>}</li>
 * </ul>
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
     * The default value of the '{@link #getArcType() <em>Arc Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getArcType()
     * @generated
     * @ordered
     */
    protected static final Arc ARC_TYPE_EDEFAULT = Arc.OPEN;

    /**
     * The cached value of the '{@link #getArcType() <em>Arc Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getArcType()
     * @generated
     * @ordered
     */
    protected Arc arcType = ARC_TYPE_EDEFAULT;

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
    public Arc getArcType() {
        return arcType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setArcType(Arc newArcType) {
        Arc oldArcType = arcType;
        arcType = newArcType == null ? ARC_TYPE_EDEFAULT : newArcType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KARC__ARC_TYPE, oldArcType, arcType));
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
            case KRenderingPackage.KARC__ARC_TYPE:
                return getArcType();
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
            case KRenderingPackage.KARC__ARC_TYPE:
                setArcType((Arc)newValue);
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
            case KRenderingPackage.KARC__ARC_TYPE:
                setArcType(ARC_TYPE_EDEFAULT);
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
            case KRenderingPackage.KARC__ARC_TYPE:
                return arcType != ARC_TYPE_EDEFAULT;
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

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (startAngle: ");
        result.append(startAngle);
        result.append(", arcAngle: ");
        result.append(arcAngle);
        result.append(", arcType: ");
        result.append(arcType);
        result.append(')');
        return result.toString();
    }

} //KArcImpl
