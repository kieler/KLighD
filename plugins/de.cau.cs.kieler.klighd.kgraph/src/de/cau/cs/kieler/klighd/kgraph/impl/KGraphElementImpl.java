/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
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
package de.cau.cs.kieler.klighd.kgraph.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.cau.cs.kieler.klighd.kgraph.KGraphData;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KGraphElementImpl#getData <em>Data</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class KGraphElementImpl extends EMapPropertyHolderImpl implements KGraphElement {
	/**
     * The cached value of the '{@link #getData() <em>Data</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getData()
     * @generated
     * @ordered
     */
	protected EList<KGraphData> data;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected KGraphElementImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return KGraphPackage.Literals.KGRAPH_ELEMENT;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<KGraphData> getData() {
        if (data == null) {
            data = new EObjectContainmentEList<KGraphData>(KGraphData.class, this, KGraphPackage.KGRAPH_ELEMENT__DATA);
        }
        return data;
    }

    /**
     * <!-- begin-user-doc -->
     * {@inheritDoc}
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public KGraphData getData(EClass type) {
        if (type != null) {
            for (KGraphData graphData : getData()) {
                if (type.equals(graphData.eClass()) || type.isInstance(graphData)) {
                    return graphData;
                }
            }
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * {@inheritDoc}
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public <T extends KGraphData> T getData(Class<T> type) {
        if (type != null) {
            for (KGraphData graphData : getData()) {
                if (type.isInstance(graphData)) {
                    return type.cast(graphData);
                }
            }
        }
        return null;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KGraphPackage.KGRAPH_ELEMENT__DATA:
                return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
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
            case KGraphPackage.KGRAPH_ELEMENT__DATA:
                return getData();
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
            case KGraphPackage.KGRAPH_ELEMENT__DATA:
                getData().clear();
                getData().addAll((Collection<? extends KGraphData>)newValue);
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
            case KGraphPackage.KGRAPH_ELEMENT__DATA:
                getData().clear();
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
            case KGraphPackage.KGRAPH_ELEMENT__DATA:
                return data != null && !data.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //KGraphElementImpl
