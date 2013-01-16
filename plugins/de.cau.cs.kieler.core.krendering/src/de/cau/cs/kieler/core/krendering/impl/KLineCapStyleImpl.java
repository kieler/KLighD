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

import de.cau.cs.kieler.core.krendering.KLineCapStyle;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;

import de.cau.cs.kieler.core.krendering.LineCapStyle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KLine Cap Style</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KLineCapStyleImpl#getLineCapStyle <em>Line Cap Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KLineCapStyleImpl extends KStyleImpl implements KLineCapStyle {
    /**
     * The default value of the '{@link #getLineCapStyle() <em>Line Cap Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLineCapStyle()
     * @generated
     * @ordered
     */
    protected static final LineCapStyle LINE_CAP_STYLE_EDEFAULT = LineCapStyle.CAP_FLAT;
    /**
     * The cached value of the '{@link #getLineCapStyle() <em>Line Cap Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLineCapStyle()
     * @generated
     * @ordered
     */
    protected LineCapStyle lineCapStyle = LINE_CAP_STYLE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KLineCapStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KLINE_CAP_STYLE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LineCapStyle getLineCapStyle() {
        return lineCapStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLineCapStyle(LineCapStyle newLineCapStyle) {
        LineCapStyle oldLineCapStyle = lineCapStyle;
        lineCapStyle = newLineCapStyle == null ? LINE_CAP_STYLE_EDEFAULT : newLineCapStyle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KLINE_CAP_STYLE__LINE_CAP_STYLE, oldLineCapStyle, lineCapStyle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KLINE_CAP_STYLE__LINE_CAP_STYLE:
                return getLineCapStyle();
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
            case KRenderingPackage.KLINE_CAP_STYLE__LINE_CAP_STYLE:
                setLineCapStyle((LineCapStyle)newValue);
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
            case KRenderingPackage.KLINE_CAP_STYLE__LINE_CAP_STYLE:
                setLineCapStyle(LINE_CAP_STYLE_EDEFAULT);
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
            case KRenderingPackage.KLINE_CAP_STYLE__LINE_CAP_STYLE:
                return lineCapStyle != LINE_CAP_STYLE_EDEFAULT;
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
        result.append(" (lineCapStyle: ");
        result.append(lineCapStyle);
        result.append(')');
        return result.toString();
    }

} //KLineCapStyleImpl
