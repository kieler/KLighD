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
import de.cau.cs.kieler.core.krendering.KVisibility;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KVisibility</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KVisibilityImpl#isIsVisible <em>Is Visible</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KVisibilityImpl extends KStyleImpl implements KVisibility {
    /**
     * The default value of the '{@link #isIsVisible() <em>Is Visible</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsVisible()
     * @generated
     * @ordered
     */
    protected static final boolean IS_VISIBLE_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isIsVisible() <em>Is Visible</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsVisible()
     * @generated
     * @ordered
     */
    protected boolean isVisible = IS_VISIBLE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KVisibilityImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KVISIBILITY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isIsVisible() {
        return isVisible;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIsVisible(boolean newIsVisible) {
        boolean oldIsVisible = isVisible;
        isVisible = newIsVisible;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KVISIBILITY__IS_VISIBLE, oldIsVisible, isVisible));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KVISIBILITY__IS_VISIBLE:
                return isIsVisible();
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
            case KRenderingPackage.KVISIBILITY__IS_VISIBLE:
                setIsVisible((Boolean)newValue);
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
            case KRenderingPackage.KVISIBILITY__IS_VISIBLE:
                setIsVisible(IS_VISIBLE_EDEFAULT);
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
            case KRenderingPackage.KVISIBILITY__IS_VISIBLE:
                return isVisible != IS_VISIBLE_EDEFAULT;
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
        result.append(" (isVisible: ");
        result.append(isVisible);
        result.append(')');
        return result.toString();
    }

} //KVisibilityImpl
