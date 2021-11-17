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

import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;
import de.cau.cs.kieler.klighd.krendering.KRoundedRectangle;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KRounded Rectangle</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KRoundedRectangleImpl#getCornerWidth <em>Corner Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KRoundedRectangleImpl#getCornerHeight <em>Corner Height</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KRoundedRectangleImpl extends KContainerRenderingImpl implements KRoundedRectangle {
    /**
     * The default value of the '{@link #getCornerWidth() <em>Corner Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCornerWidth()
     * @generated
     * @ordered
     */
    protected static final float CORNER_WIDTH_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getCornerWidth() <em>Corner Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCornerWidth()
     * @generated
     * @ordered
     */
    protected float cornerWidth = CORNER_WIDTH_EDEFAULT;

    /**
     * The default value of the '{@link #getCornerHeight() <em>Corner Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCornerHeight()
     * @generated
     * @ordered
     */
    protected static final float CORNER_HEIGHT_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getCornerHeight() <em>Corner Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCornerHeight()
     * @generated
     * @ordered
     */
    protected float cornerHeight = CORNER_HEIGHT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KRoundedRectangleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KROUNDED_RECTANGLE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getCornerWidth() {
        return cornerWidth;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCornerWidth(float newCornerWidth) {
        float oldCornerWidth = cornerWidth;
        cornerWidth = newCornerWidth;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KROUNDED_RECTANGLE__CORNER_WIDTH, oldCornerWidth, cornerWidth));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getCornerHeight() {
        return cornerHeight;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCornerHeight(float newCornerHeight) {
        float oldCornerHeight = cornerHeight;
        cornerHeight = newCornerHeight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KROUNDED_RECTANGLE__CORNER_HEIGHT, oldCornerHeight, cornerHeight));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KROUNDED_RECTANGLE__CORNER_WIDTH:
                return getCornerWidth();
            case KRenderingPackage.KROUNDED_RECTANGLE__CORNER_HEIGHT:
                return getCornerHeight();
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
            case KRenderingPackage.KROUNDED_RECTANGLE__CORNER_WIDTH:
                setCornerWidth((Float)newValue);
                return;
            case KRenderingPackage.KROUNDED_RECTANGLE__CORNER_HEIGHT:
                setCornerHeight((Float)newValue);
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
            case KRenderingPackage.KROUNDED_RECTANGLE__CORNER_WIDTH:
                setCornerWidth(CORNER_WIDTH_EDEFAULT);
                return;
            case KRenderingPackage.KROUNDED_RECTANGLE__CORNER_HEIGHT:
                setCornerHeight(CORNER_HEIGHT_EDEFAULT);
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
            case KRenderingPackage.KROUNDED_RECTANGLE__CORNER_WIDTH:
                return cornerWidth != CORNER_WIDTH_EDEFAULT;
            case KRenderingPackage.KROUNDED_RECTANGLE__CORNER_HEIGHT:
                return cornerHeight != CORNER_HEIGHT_EDEFAULT;
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
        result.append(" (cornerWidth: ");
        result.append(cornerWidth);
        result.append(", cornerHeight: ");
        result.append(cornerHeight);
        result.append(')');
        return result.toString();
    }

} //KRoundedRectangleImpl
