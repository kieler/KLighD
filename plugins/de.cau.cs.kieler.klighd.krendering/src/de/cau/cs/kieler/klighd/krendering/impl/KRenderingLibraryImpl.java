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

import de.cau.cs.kieler.klighd.kgraph.impl.KGraphDataImpl;

import de.cau.cs.kieler.klighd.krendering.KRenderingLibrary;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;
import de.cau.cs.kieler.klighd.krendering.KStyleHolder;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KRenderingLibraryImpl#getRenderings <em>Renderings</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KRenderingLibraryImpl extends KGraphDataImpl implements KRenderingLibrary {
    /**
     * The cached value of the '{@link #getRenderings() <em>Renderings</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRenderings()
     * @generated
     * @ordered
     */
    protected EList<KStyleHolder> renderings;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KRenderingLibraryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KRENDERING_LIBRARY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<KStyleHolder> getRenderings() {
        if (renderings == null) {
            renderings = new EObjectContainmentEList<KStyleHolder>(KStyleHolder.class, this, KRenderingPackage.KRENDERING_LIBRARY__RENDERINGS);
        }
        return renderings;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KRENDERING_LIBRARY__RENDERINGS:
                return ((InternalEList<?>)getRenderings()).basicRemove(otherEnd, msgs);
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
            case KRenderingPackage.KRENDERING_LIBRARY__RENDERINGS:
                return getRenderings();
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
            case KRenderingPackage.KRENDERING_LIBRARY__RENDERINGS:
                getRenderings().clear();
                getRenderings().addAll((Collection<? extends KStyleHolder>)newValue);
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
            case KRenderingPackage.KRENDERING_LIBRARY__RENDERINGS:
                getRenderings().clear();
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
            case KRenderingPackage.KRENDERING_LIBRARY__RENDERINGS:
                return renderings != null && !renderings.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //KRenderingLibraryImpl
