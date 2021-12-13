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

import de.cau.cs.kieler.klighd.krendering.HorizontalAlignment;
import de.cau.cs.kieler.klighd.krendering.KHorizontalAlignment;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KHorizontal Alignment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KHorizontalAlignmentImpl#getHorizontalAlignment <em>Horizontal Alignment</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KHorizontalAlignmentImpl extends KStyleImpl implements KHorizontalAlignment {
    /**
     * The default value of the '{@link #getHorizontalAlignment() <em>Horizontal Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHorizontalAlignment()
     * @generated
     * @ordered
     */
    protected static final HorizontalAlignment HORIZONTAL_ALIGNMENT_EDEFAULT = HorizontalAlignment.LEFT;

    /**
     * The cached value of the '{@link #getHorizontalAlignment() <em>Horizontal Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHorizontalAlignment()
     * @generated
     * @ordered
     */
    protected HorizontalAlignment horizontalAlignment = HORIZONTAL_ALIGNMENT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KHorizontalAlignmentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KHORIZONTAL_ALIGNMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHorizontalAlignment(HorizontalAlignment newHorizontalAlignment) {
        HorizontalAlignment oldHorizontalAlignment = horizontalAlignment;
        horizontalAlignment = newHorizontalAlignment == null ? HORIZONTAL_ALIGNMENT_EDEFAULT : newHorizontalAlignment;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KHORIZONTAL_ALIGNMENT__HORIZONTAL_ALIGNMENT, oldHorizontalAlignment, horizontalAlignment));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KHORIZONTAL_ALIGNMENT__HORIZONTAL_ALIGNMENT:
                return getHorizontalAlignment();
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
            case KRenderingPackage.KHORIZONTAL_ALIGNMENT__HORIZONTAL_ALIGNMENT:
                setHorizontalAlignment((HorizontalAlignment)newValue);
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
            case KRenderingPackage.KHORIZONTAL_ALIGNMENT__HORIZONTAL_ALIGNMENT:
                setHorizontalAlignment(HORIZONTAL_ALIGNMENT_EDEFAULT);
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
            case KRenderingPackage.KHORIZONTAL_ALIGNMENT__HORIZONTAL_ALIGNMENT:
                return horizontalAlignment != HORIZONTAL_ALIGNMENT_EDEFAULT;
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
        result.append(" (horizontalAlignment: ");
        result.append(horizontalAlignment);
        result.append(')');
        return result.toString();
    }

} //KHorizontalAlignmentImpl
