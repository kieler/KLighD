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

import de.cau.cs.kieler.core.krendering.KLineJoin;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.LineJoin;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KLine Join</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KLineJoinImpl#getLineJoin <em>Line Join</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KLineJoinImpl#getMiterLimit <em>Miter Limit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KLineJoinImpl extends KStyleImpl implements KLineJoin {
    /**
     * The default value of the '{@link #getLineJoin() <em>Line Join</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLineJoin()
     * @generated
     * @ordered
     */
    protected static final LineJoin LINE_JOIN_EDEFAULT = LineJoin.JOIN_MITER;

    /**
     * The cached value of the '{@link #getLineJoin() <em>Line Join</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLineJoin()
     * @generated
     * @ordered
     */
    protected LineJoin lineJoin = LINE_JOIN_EDEFAULT;

    /**
     * The default value of the '{@link #getMiterLimit() <em>Miter Limit</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMiterLimit()
     * @generated
     * @ordered
     */
    protected static final float MITER_LIMIT_EDEFAULT = 10.0F;

    /**
     * The cached value of the '{@link #getMiterLimit() <em>Miter Limit</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMiterLimit()
     * @generated
     * @ordered
     */
    protected float miterLimit = MITER_LIMIT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KLineJoinImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KLINE_JOIN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LineJoin getLineJoin() {
        return lineJoin;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLineJoin(LineJoin newLineJoin) {
        LineJoin oldLineJoin = lineJoin;
        lineJoin = newLineJoin == null ? LINE_JOIN_EDEFAULT : newLineJoin;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KLINE_JOIN__LINE_JOIN, oldLineJoin, lineJoin));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getMiterLimit() {
        return miterLimit;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMiterLimit(float newMiterLimit) {
        float oldMiterLimit = miterLimit;
        miterLimit = newMiterLimit;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KLINE_JOIN__MITER_LIMIT, oldMiterLimit, miterLimit));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KLINE_JOIN__LINE_JOIN:
                return getLineJoin();
            case KRenderingPackage.KLINE_JOIN__MITER_LIMIT:
                return getMiterLimit();
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
            case KRenderingPackage.KLINE_JOIN__LINE_JOIN:
                setLineJoin((LineJoin)newValue);
                return;
            case KRenderingPackage.KLINE_JOIN__MITER_LIMIT:
                setMiterLimit((Float)newValue);
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
            case KRenderingPackage.KLINE_JOIN__LINE_JOIN:
                setLineJoin(LINE_JOIN_EDEFAULT);
                return;
            case KRenderingPackage.KLINE_JOIN__MITER_LIMIT:
                setMiterLimit(MITER_LIMIT_EDEFAULT);
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
            case KRenderingPackage.KLINE_JOIN__LINE_JOIN:
                return lineJoin != LINE_JOIN_EDEFAULT;
            case KRenderingPackage.KLINE_JOIN__MITER_LIMIT:
                return miterLimit != MITER_LIMIT_EDEFAULT;
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
        result.append(" (lineJoin: ");
        result.append(lineJoin);
        result.append(", miterLimit: ");
        result.append(miterLimit);
        result.append(')');
        return result.toString();
    }

} //KLineJoinImpl
