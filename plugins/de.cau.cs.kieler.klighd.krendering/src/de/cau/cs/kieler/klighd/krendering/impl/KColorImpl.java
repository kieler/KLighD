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

import de.cau.cs.kieler.klighd.krendering.Colors;
import de.cau.cs.kieler.klighd.krendering.KColor;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KColor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KColorImpl#getRed <em>Red</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KColorImpl#getGreen <em>Green</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KColorImpl#getBlue <em>Blue</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KColorImpl extends EObjectImpl implements KColor {
    /**
     * The default value of the '{@link #getRed() <em>Red</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRed()
     * @generated
     * @ordered
     */
    protected static final int RED_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getRed() <em>Red</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRed()
     * @generated
     * @ordered
     */
    protected int red = RED_EDEFAULT;

    /**
     * The default value of the '{@link #getGreen() <em>Green</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGreen()
     * @generated
     * @ordered
     */
    protected static final int GREEN_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getGreen() <em>Green</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGreen()
     * @generated
     * @ordered
     */
    protected int green = GREEN_EDEFAULT;

    /**
     * The default value of the '{@link #getBlue() <em>Blue</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBlue()
     * @generated
     * @ordered
     */
    protected static final int BLUE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getBlue() <em>Blue</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBlue()
     * @generated
     * @ordered
     */
    protected int blue = BLUE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KColorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KCOLOR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getRed() {
        return red;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRed(int newRed) {
        int oldRed = red;
        red = newRed;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KCOLOR__RED, oldRed, red));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getGreen() {
        return green;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGreen(int newGreen) {
        int oldGreen = green;
        green = newGreen;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KCOLOR__GREEN, oldGreen, green));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getBlue() {
        return blue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBlue(int newBlue) {
        int oldBlue = blue;
        blue = newBlue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KCOLOR__BLUE, oldBlue, blue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KColor setColor(final int red, final int green, final int blue) {
        return de.cau.cs.kieler.klighd.krendering.KRenderingUtil.setColor(this, red, green, blue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KColor setColor(final Colors color) {
        return de.cau.cs.kieler.klighd.krendering.KRenderingUtil.setColor(this, color);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KColor setColor(final KColor kColor) {
        return de.cau.cs.kieler.klighd.krendering.KRenderingUtil.setColor(this, kColor);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean equals(final Object other) {
        return de.cau.cs.kieler.klighd.krendering.KRenderingUtil.equals(this,other);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KCOLOR__RED:
                return getRed();
            case KRenderingPackage.KCOLOR__GREEN:
                return getGreen();
            case KRenderingPackage.KCOLOR__BLUE:
                return getBlue();
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
            case KRenderingPackage.KCOLOR__RED:
                setRed((Integer)newValue);
                return;
            case KRenderingPackage.KCOLOR__GREEN:
                setGreen((Integer)newValue);
                return;
            case KRenderingPackage.KCOLOR__BLUE:
                setBlue((Integer)newValue);
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
            case KRenderingPackage.KCOLOR__RED:
                setRed(RED_EDEFAULT);
                return;
            case KRenderingPackage.KCOLOR__GREEN:
                setGreen(GREEN_EDEFAULT);
                return;
            case KRenderingPackage.KCOLOR__BLUE:
                setBlue(BLUE_EDEFAULT);
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
            case KRenderingPackage.KCOLOR__RED:
                return red != RED_EDEFAULT;
            case KRenderingPackage.KCOLOR__GREEN:
                return green != GREEN_EDEFAULT;
            case KRenderingPackage.KCOLOR__BLUE:
                return blue != BLUE_EDEFAULT;
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
        result.append(" (red: ");
        result.append(red);
        result.append(", green: ");
        result.append(green);
        result.append(", blue: ");
        result.append(blue);
        result.append(')');
        return result.toString();
    }

} //KColorImpl
