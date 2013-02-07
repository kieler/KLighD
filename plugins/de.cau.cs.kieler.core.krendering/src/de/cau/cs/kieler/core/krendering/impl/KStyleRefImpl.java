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
import de.cau.cs.kieler.core.krendering.KStyleHolder;
import de.cau.cs.kieler.core.krendering.KStyleRef;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KStyle Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KStyleRefImpl#getStyleHolder <em>Style Holder</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KStyleRefImpl extends KStyleImpl implements KStyleRef {
    /**
     * The cached value of the '{@link #getStyleHolder() <em>Style Holder</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStyleHolder()
     * @generated
     * @ordered
     */
    protected KStyleHolder styleHolder;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KStyleRefImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KSTYLE_REF;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KStyleHolder getStyleHolder() {
        if (styleHolder != null && styleHolder.eIsProxy()) {
            InternalEObject oldStyleHolder = (InternalEObject)styleHolder;
            styleHolder = (KStyleHolder)eResolveProxy(oldStyleHolder);
            if (styleHolder != oldStyleHolder) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, KRenderingPackage.KSTYLE_REF__STYLE_HOLDER, oldStyleHolder, styleHolder));
            }
        }
        return styleHolder;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KStyleHolder basicGetStyleHolder() {
        return styleHolder;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStyleHolder(KStyleHolder newStyleHolder) {
        KStyleHolder oldStyleHolder = styleHolder;
        styleHolder = newStyleHolder;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KSTYLE_REF__STYLE_HOLDER, oldStyleHolder, styleHolder));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KSTYLE_REF__STYLE_HOLDER:
                if (resolve) return getStyleHolder();
                return basicGetStyleHolder();
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
            case KRenderingPackage.KSTYLE_REF__STYLE_HOLDER:
                setStyleHolder((KStyleHolder)newValue);
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
            case KRenderingPackage.KSTYLE_REF__STYLE_HOLDER:
                setStyleHolder((KStyleHolder)null);
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
            case KRenderingPackage.KSTYLE_REF__STYLE_HOLDER:
                return styleHolder != null;
        }
        return super.eIsSet(featureID);
    }

} //KStyleRefImpl
