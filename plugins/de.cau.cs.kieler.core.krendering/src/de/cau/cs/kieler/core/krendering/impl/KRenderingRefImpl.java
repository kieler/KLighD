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

import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRenderingRef;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KRenderingRefImpl#getRendering <em>Rendering</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KRenderingRefImpl extends KRenderingImpl implements KRenderingRef {
    /**
     * The cached value of the '{@link #getRendering() <em>Rendering</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRendering()
     * @generated
     * @ordered
     */
    protected KRendering rendering;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KRenderingRefImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KRENDERING_REF;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KRendering getRendering() {
        if (rendering != null && rendering.eIsProxy()) {
            InternalEObject oldRendering = (InternalEObject)rendering;
            rendering = (KRendering)eResolveProxy(oldRendering);
            if (rendering != oldRendering) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, KRenderingPackage.KRENDERING_REF__RENDERING, oldRendering, rendering));
            }
        }
        return rendering;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KRendering basicGetRendering() {
        return rendering;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRendering(KRendering newRendering, NotificationChain msgs) {
        KRendering oldRendering = rendering;
        rendering = newRendering;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KRenderingPackage.KRENDERING_REF__RENDERING, oldRendering, newRendering);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRendering(KRendering newRendering) {
        if (newRendering != rendering) {
            NotificationChain msgs = null;
            if (rendering != null)
                msgs = ((InternalEObject)rendering).eInverseRemove(this, KRenderingPackage.KRENDERING__REFERENCES, KRendering.class, msgs);
            if (newRendering != null)
                msgs = ((InternalEObject)newRendering).eInverseAdd(this, KRenderingPackage.KRENDERING__REFERENCES, KRendering.class, msgs);
            msgs = basicSetRendering(newRendering, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KRENDERING_REF__RENDERING, newRendering, newRendering));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KRENDERING_REF__RENDERING:
                if (rendering != null)
                    msgs = ((InternalEObject)rendering).eInverseRemove(this, KRenderingPackage.KRENDERING__REFERENCES, KRendering.class, msgs);
                return basicSetRendering((KRendering)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KRENDERING_REF__RENDERING:
                return basicSetRendering(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KRENDERING_REF__RENDERING:
                if (resolve) return getRendering();
                return basicGetRendering();
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
            case KRenderingPackage.KRENDERING_REF__RENDERING:
                setRendering((KRendering)newValue);
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
            case KRenderingPackage.KRENDERING_REF__RENDERING:
                setRendering((KRendering)null);
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
            case KRenderingPackage.KRENDERING_REF__RENDERING:
                return rendering != null;
        }
        return super.eIsSet(featureID);
    }

} //KRenderingRefImpl
