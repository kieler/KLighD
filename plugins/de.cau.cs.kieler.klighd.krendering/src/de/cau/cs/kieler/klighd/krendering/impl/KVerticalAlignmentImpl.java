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
import de.cau.cs.kieler.klighd.krendering.KVerticalAlignment;
import de.cau.cs.kieler.klighd.krendering.VerticalAlignment;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KVertical Alignment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KVerticalAlignmentImpl#getVerticalAlignment <em>Vertical Alignment</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KVerticalAlignmentImpl extends KStyleImpl implements KVerticalAlignment {
    /**
     * The default value of the '{@link #getVerticalAlignment() <em>Vertical Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVerticalAlignment()
     * @generated
     * @ordered
     */
    protected static final VerticalAlignment VERTICAL_ALIGNMENT_EDEFAULT = VerticalAlignment.TOP;

    /**
     * The cached value of the '{@link #getVerticalAlignment() <em>Vertical Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVerticalAlignment()
     * @generated
     * @ordered
     */
    protected VerticalAlignment verticalAlignment = VERTICAL_ALIGNMENT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KVerticalAlignmentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KVERTICAL_ALIGNMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVerticalAlignment(VerticalAlignment newVerticalAlignment) {
        VerticalAlignment oldVerticalAlignment = verticalAlignment;
        verticalAlignment = newVerticalAlignment == null ? VERTICAL_ALIGNMENT_EDEFAULT : newVerticalAlignment;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KVERTICAL_ALIGNMENT__VERTICAL_ALIGNMENT, oldVerticalAlignment, verticalAlignment));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KVERTICAL_ALIGNMENT__VERTICAL_ALIGNMENT:
                return getVerticalAlignment();
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
            case KRenderingPackage.KVERTICAL_ALIGNMENT__VERTICAL_ALIGNMENT:
                setVerticalAlignment((VerticalAlignment)newValue);
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
            case KRenderingPackage.KVERTICAL_ALIGNMENT__VERTICAL_ALIGNMENT:
                setVerticalAlignment(VERTICAL_ALIGNMENT_EDEFAULT);
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
            case KRenderingPackage.KVERTICAL_ALIGNMENT__VERTICAL_ALIGNMENT:
                return verticalAlignment != VERTICAL_ALIGNMENT_EDEFAULT;
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
        result.append(" (verticalAlignment: ");
        result.append(verticalAlignment);
        result.append(')');
        return result.toString();
    }

} //KVerticalAlignmentImpl
