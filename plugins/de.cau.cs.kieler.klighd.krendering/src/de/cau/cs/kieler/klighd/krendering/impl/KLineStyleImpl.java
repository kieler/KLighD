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

import de.cau.cs.kieler.klighd.krendering.KLineStyle;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;
import de.cau.cs.kieler.klighd.krendering.LineStyle;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KLine Style</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KLineStyleImpl#getLineStyle <em>Line Style</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KLineStyleImpl#getDashPattern <em>Dash Pattern</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KLineStyleImpl#getDashOffset <em>Dash Offset</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KLineStyleImpl extends KStyleImpl implements KLineStyle {
    /**
     * The default value of the '{@link #getLineStyle() <em>Line Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLineStyle()
     * @generated
     * @ordered
     */
    protected static final LineStyle LINE_STYLE_EDEFAULT = LineStyle.SOLID;

    /**
     * The cached value of the '{@link #getLineStyle() <em>Line Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLineStyle()
     * @generated
     * @ordered
     */
    protected LineStyle lineStyle = LINE_STYLE_EDEFAULT;

    /**
     * The cached value of the '{@link #getDashPattern() <em>Dash Pattern</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDashPattern()
     * @generated
     * @ordered
     */
    protected EList<Float> dashPattern;

    /**
     * The default value of the '{@link #getDashOffset() <em>Dash Offset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDashOffset()
     * @generated
     * @ordered
     */
    protected static final float DASH_OFFSET_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getDashOffset() <em>Dash Offset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDashOffset()
     * @generated
     * @ordered
     */
    protected float dashOffset = DASH_OFFSET_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KLineStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KLINE_STYLE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LineStyle getLineStyle() {
        return lineStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLineStyle(LineStyle newLineStyle) {
        LineStyle oldLineStyle = lineStyle;
        lineStyle = newLineStyle == null ? LINE_STYLE_EDEFAULT : newLineStyle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KLINE_STYLE__LINE_STYLE, oldLineStyle, lineStyle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Float> getDashPattern() {
        if (dashPattern == null) {
            dashPattern = new EDataTypeEList<Float>(Float.class, this, KRenderingPackage.KLINE_STYLE__DASH_PATTERN);
        }
        return dashPattern;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getDashOffset() {
        return dashOffset;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDashOffset(float newDashOffset) {
        float oldDashOffset = dashOffset;
        dashOffset = newDashOffset;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KLINE_STYLE__DASH_OFFSET, oldDashOffset, dashOffset));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KLINE_STYLE__LINE_STYLE:
                return getLineStyle();
            case KRenderingPackage.KLINE_STYLE__DASH_PATTERN:
                return getDashPattern();
            case KRenderingPackage.KLINE_STYLE__DASH_OFFSET:
                return getDashOffset();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case KRenderingPackage.KLINE_STYLE__LINE_STYLE:
                setLineStyle((LineStyle)newValue);
                return;
            case KRenderingPackage.KLINE_STYLE__DASH_PATTERN:
                getDashPattern().clear();
                getDashPattern().addAll((Collection<? extends Float>)newValue);
                return;
            case KRenderingPackage.KLINE_STYLE__DASH_OFFSET:
                setDashOffset((Float)newValue);
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
            case KRenderingPackage.KLINE_STYLE__LINE_STYLE:
                setLineStyle(LINE_STYLE_EDEFAULT);
                return;
            case KRenderingPackage.KLINE_STYLE__DASH_PATTERN:
                getDashPattern().clear();
                return;
            case KRenderingPackage.KLINE_STYLE__DASH_OFFSET:
                setDashOffset(DASH_OFFSET_EDEFAULT);
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
            case KRenderingPackage.KLINE_STYLE__LINE_STYLE:
                return lineStyle != LINE_STYLE_EDEFAULT;
            case KRenderingPackage.KLINE_STYLE__DASH_PATTERN:
                return dashPattern != null && !dashPattern.isEmpty();
            case KRenderingPackage.KLINE_STYLE__DASH_OFFSET:
                return dashOffset != DASH_OFFSET_EDEFAULT;
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
        result.append(" (lineStyle: ");
        result.append(lineStyle);
        result.append(", dashPattern: ");
        result.append(dashPattern);
        result.append(", dashOffset: ");
        result.append(dashOffset);
        result.append(')');
        return result.toString();
    }

} //KLineStyleImpl
