/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
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
 * An implementation of the model object '<em><b>Connector</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.impl.ConnectorImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.impl.ConnectorImpl#getConnectedWires <em>Connected Wires</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.impl.ConnectorImpl#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectorImpl extends MinimalEObjectImpl.Container implements Connector {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getConnectedWires() <em>Connected Wires</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConnectedWires()
     * @generated
     * @ordered
     */
    protected EList<Wire> connectedWires;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ConnectorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CircuitPackage.Literals.CONNECTOR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CircuitPackage.CONNECTOR__NAME,
                    oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Wire> getConnectedWires() {
        if (connectedWires == null) {
            connectedWires =
                    new EObjectWithInverseResolvingEList.ManyInverse<Wire>(Wire.class, this,
                            CircuitPackage.CONNECTOR__CONNECTED_WIRES,
                            CircuitPackage.WIRE__CONNECTED_TO);
        }
        return connectedWires;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Circuit getParent() {
        if (eContainerFeatureID() != CircuitPackage.CONNECTOR__PARENT)
            return null;
        return (Circuit) eInternalContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParent(Circuit newParent, NotificationChain msgs) {
        msgs =
                eBasicSetContainer((InternalEObject) newParent, CircuitPackage.CONNECTOR__PARENT,
                        msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParent(Circuit newParent) {
        if (newParent != eInternalContainer()
                || (eContainerFeatureID() != CircuitPackage.CONNECTOR__PARENT && newParent != null)) {
            if (EcoreUtil.isAncestor(this, newParent))
                throw new IllegalArgumentException("Recursive containment not allowed for "
                        + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newParent != null)
                msgs =
                        ((InternalEObject) newParent).eInverseAdd(this,
                                CircuitPackage.CIRCUIT__CONNECTORS, Circuit.class, msgs);
            msgs = basicSetParent(newParent, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CircuitPackage.CONNECTOR__PARENT,
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
        case CircuitPackage.CONNECTOR__CONNECTED_WIRES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getConnectedWires())
                    .basicAdd(otherEnd, msgs);
        case CircuitPackage.CONNECTOR__PARENT:
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
        case CircuitPackage.CONNECTOR__CONNECTED_WIRES:
            return ((InternalEList<?>) getConnectedWires()).basicRemove(otherEnd, msgs);
        case CircuitPackage.CONNECTOR__PARENT:
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
        case CircuitPackage.CONNECTOR__PARENT:
            return eInternalContainer().eInverseRemove(this, CircuitPackage.CIRCUIT__CONNECTORS,
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
        case CircuitPackage.CONNECTOR__NAME:
            return getName();
        case CircuitPackage.CONNECTOR__CONNECTED_WIRES:
            return getConnectedWires();
        case CircuitPackage.CONNECTOR__PARENT:
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
        case CircuitPackage.CONNECTOR__NAME:
            setName((String) newValue);
            return;
        case CircuitPackage.CONNECTOR__CONNECTED_WIRES:
            getConnectedWires().clear();
            getConnectedWires().addAll((Collection<? extends Wire>) newValue);
            return;
        case CircuitPackage.CONNECTOR__PARENT:
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
        case CircuitPackage.CONNECTOR__NAME:
            setName(NAME_EDEFAULT);
            return;
        case CircuitPackage.CONNECTOR__CONNECTED_WIRES:
            getConnectedWires().clear();
            return;
        case CircuitPackage.CONNECTOR__PARENT:
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
        case CircuitPackage.CONNECTOR__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case CircuitPackage.CONNECTOR__CONNECTED_WIRES:
            return connectedWires != null && !connectedWires.isEmpty();
        case CircuitPackage.CONNECTOR__PARENT:
            return getParent() != null;
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //ConnectorImpl
