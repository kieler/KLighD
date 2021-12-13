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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KLabeled Graph Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KLabeledGraphElementImpl#getLabels <em>Labels</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class KLabeledGraphElementImpl extends KGraphElementImpl implements KLabeledGraphElement {
	/**
     * The cached value of the '{@link #getLabels() <em>Labels</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLabels()
     * @generated
     * @ordered
     */
	protected EList<KLabel> labels;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected KLabeledGraphElementImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return KGraphPackage.Literals.KLABELED_GRAPH_ELEMENT;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<KLabel> getLabels() {
        if (labels == null) {
            labels = new EObjectContainmentWithInverseEList<KLabel>(KLabel.class, this, KGraphPackage.KLABELED_GRAPH_ELEMENT__LABELS, KGraphPackage.KLABEL__PARENT);
        }
        return labels;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KGraphPackage.KLABELED_GRAPH_ELEMENT__LABELS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getLabels()).basicAdd(otherEnd, msgs);
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
            case KGraphPackage.KLABELED_GRAPH_ELEMENT__LABELS:
                return ((InternalEList<?>)getLabels()).basicRemove(otherEnd, msgs);
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
            case KGraphPackage.KLABELED_GRAPH_ELEMENT__LABELS:
                return getLabels();
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
            case KGraphPackage.KLABELED_GRAPH_ELEMENT__LABELS:
                getLabels().clear();
                getLabels().addAll((Collection<? extends KLabel>)newValue);
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
            case KGraphPackage.KLABELED_GRAPH_ELEMENT__LABELS:
                getLabels().clear();
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
            case KGraphPackage.KLABELED_GRAPH_ELEMENT__LABELS:
                return labels != null && !labels.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //KLabeledGraphElementImpl
