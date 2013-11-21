/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.examples.circuit.impl;

import de.cau.cs.kieler.klighd.examples.circuit.Circuit;
import de.cau.cs.kieler.klighd.examples.circuit.CircuitPackage;
import de.cau.cs.kieler.klighd.examples.circuit.Connector;
import de.cau.cs.kieler.klighd.examples.circuit.Wire;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Wire</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.impl.WireImpl#getConnectedTo <em>Connected To</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.impl.WireImpl#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WireImpl extends MinimalEObjectImpl.Container implements Wire {
    /**
     * The cached value of the '{@link #getConnectedTo() <em>Connected To</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConnectedTo()
     * @generated
     * @ordered
     */
    protected EList<Connector> connectedTo;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected WireImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CircuitPackage.Literals.WIRE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Connector> getConnectedTo() {
        if (connectedTo == null) {
            connectedTo =
                    new EObjectWithInverseResolvingEList.ManyInverse<Connector>(Connector.class,
                            this, CircuitPackage.WIRE__CONNECTED_TO,
                            CircuitPackage.CONNECTOR__CONNECTED_WIRES);
        }
        return connectedTo;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Circuit getParent() {
        if (eContainerFeatureID() != CircuitPackage.WIRE__PARENT)
            return null;
        return (Circuit) eInternalContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParent(Circuit newParent, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newParent, CircuitPackage.WIRE__PARENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParent(Circuit newParent) {
        if (newParent != eInternalContainer()
                || (eContainerFeatureID() != CircuitPackage.WIRE__PARENT && newParent != null)) {
            if (EcoreUtil.isAncestor(this, newParent))
                throw new IllegalArgumentException("Recursive containment not allowed for "
                        + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newParent != null)
                msgs =
                        ((InternalEObject) newParent).eInverseAdd(this,
                                CircuitPackage.CIRCUIT__INNER_WIRES, Circuit.class, msgs);
            msgs = basicSetParent(newParent, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CircuitPackage.WIRE__PARENT,
                    newParent, newParent));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
            NotificationChain msgs) {
        switch (featureID) {
        case CircuitPackage.WIRE__CONNECTED_TO:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getConnectedTo()).basicAdd(
                    otherEnd, msgs);
        case CircuitPackage.WIRE__PARENT:
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            return basicSetParent((Circuit) otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
            NotificationChain msgs) {
        switch (featureID) {
        case CircuitPackage.WIRE__CONNECTED_TO:
            return ((InternalEList<?>) getConnectedTo()).basicRemove(otherEnd, msgs);
        case CircuitPackage.WIRE__PARENT:
            return basicSetParent(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
        case CircuitPackage.WIRE__PARENT:
            return eInternalContainer().eInverseRemove(this, CircuitPackage.CIRCUIT__INNER_WIRES,
                    Circuit.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case CircuitPackage.WIRE__CONNECTED_TO:
            return getConnectedTo();
        case CircuitPackage.WIRE__PARENT:
            return getParent();
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
        case CircuitPackage.WIRE__CONNECTED_TO:
            getConnectedTo().clear();
            getConnectedTo().addAll((Collection<? extends Connector>) newValue);
            return;
        case CircuitPackage.WIRE__PARENT:
            setParent((Circuit) newValue);
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
        case CircuitPackage.WIRE__CONNECTED_TO:
            getConnectedTo().clear();
            return;
        case CircuitPackage.WIRE__PARENT:
            setParent((Circuit) null);
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
        case CircuitPackage.WIRE__CONNECTED_TO:
            return connectedTo != null && !connectedTo.isEmpty();
        case CircuitPackage.WIRE__PARENT:
            return getParent() != null;
        }
        return super.eIsSet(featureID);
    }

} //WireImpl
