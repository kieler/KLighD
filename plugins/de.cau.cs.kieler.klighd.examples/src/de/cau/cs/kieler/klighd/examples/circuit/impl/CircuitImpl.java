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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Circuit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitImpl#getConnectors <em>Connectors</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitImpl#getInnerCircuits <em>Inner Circuits</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitImpl#getInnerWires <em>Inner Wires</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitImpl#getInstanceName <em>Instance Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitImpl#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CircuitImpl extends MinimalEObjectImpl.Container implements Circuit {
    /**
     * The cached value of the '{@link #getConnectors() <em>Connectors</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConnectors()
     * @generated
     * @ordered
     */
    protected EList<Connector> connectors;

    /**
     * The cached value of the '{@link #getInnerCircuits() <em>Inner Circuits</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInnerCircuits()
     * @generated
     * @ordered
     */
    protected EList<Circuit> innerCircuits;

    /**
     * The cached value of the '{@link #getInnerWires() <em>Inner Wires</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInnerWires()
     * @generated
     * @ordered
     */
    protected EList<Wire> innerWires;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final String TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected String type = TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getInstanceName() <em>Instance Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInstanceName()
     * @generated
     * @ordered
     */
    protected static final String INSTANCE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getInstanceName() <em>Instance Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInstanceName()
     * @generated
     * @ordered
     */
    protected String instanceName = INSTANCE_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CircuitImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CircuitPackage.Literals.CIRCUIT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Connector> getConnectors() {
        if (connectors == null) {
            connectors =
                    new EObjectContainmentWithInverseEList<Connector>(Connector.class, this,
                            CircuitPackage.CIRCUIT__CONNECTORS, CircuitPackage.CONNECTOR__PARENT);
        }
        return connectors;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Circuit> getInnerCircuits() {
        if (innerCircuits == null) {
            innerCircuits =
                    new EObjectContainmentWithInverseEList<Circuit>(Circuit.class, this,
                            CircuitPackage.CIRCUIT__INNER_CIRCUITS, CircuitPackage.CIRCUIT__PARENT);
        }
        return innerCircuits;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Wire> getInnerWires() {
        if (innerWires == null) {
            innerWires =
                    new EObjectContainmentWithInverseEList<Wire>(Wire.class, this,
                            CircuitPackage.CIRCUIT__INNER_WIRES, CircuitPackage.WIRE__PARENT);
        }
        return innerWires;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(String newType) {
        String oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CircuitPackage.CIRCUIT__TYPE,
                    oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getInstanceName() {
        return instanceName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInstanceName(String newInstanceName) {
        String oldInstanceName = instanceName;
        instanceName = newInstanceName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    CircuitPackage.CIRCUIT__INSTANCE_NAME, oldInstanceName, instanceName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Circuit getParent() {
        if (eContainerFeatureID() != CircuitPackage.CIRCUIT__PARENT)
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
                eBasicSetContainer((InternalEObject) newParent, CircuitPackage.CIRCUIT__PARENT,
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
                || (eContainerFeatureID() != CircuitPackage.CIRCUIT__PARENT && newParent != null)) {
            if (EcoreUtil.isAncestor(this, newParent))
                throw new IllegalArgumentException("Recursive containment not allowed for "
                        + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newParent != null)
                msgs =
                        ((InternalEObject) newParent).eInverseAdd(this,
                                CircuitPackage.CIRCUIT__INNER_CIRCUITS, Circuit.class, msgs);
            msgs = basicSetParent(newParent, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CircuitPackage.CIRCUIT__PARENT,
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
        case CircuitPackage.CIRCUIT__CONNECTORS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getConnectors()).basicAdd(
                    otherEnd, msgs);
        case CircuitPackage.CIRCUIT__INNER_CIRCUITS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getInnerCircuits())
                    .basicAdd(otherEnd, msgs);
        case CircuitPackage.CIRCUIT__INNER_WIRES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getInnerWires()).basicAdd(
                    otherEnd, msgs);
        case CircuitPackage.CIRCUIT__PARENT:
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
        case CircuitPackage.CIRCUIT__CONNECTORS:
            return ((InternalEList<?>) getConnectors()).basicRemove(otherEnd, msgs);
        case CircuitPackage.CIRCUIT__INNER_CIRCUITS:
            return ((InternalEList<?>) getInnerCircuits()).basicRemove(otherEnd, msgs);
        case CircuitPackage.CIRCUIT__INNER_WIRES:
            return ((InternalEList<?>) getInnerWires()).basicRemove(otherEnd, msgs);
        case CircuitPackage.CIRCUIT__PARENT:
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
        case CircuitPackage.CIRCUIT__PARENT:
            return eInternalContainer().eInverseRemove(this,
                    CircuitPackage.CIRCUIT__INNER_CIRCUITS, Circuit.class, msgs);
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
        case CircuitPackage.CIRCUIT__CONNECTORS:
            return getConnectors();
        case CircuitPackage.CIRCUIT__INNER_CIRCUITS:
            return getInnerCircuits();
        case CircuitPackage.CIRCUIT__INNER_WIRES:
            return getInnerWires();
        case CircuitPackage.CIRCUIT__TYPE:
            return getType();
        case CircuitPackage.CIRCUIT__INSTANCE_NAME:
            return getInstanceName();
        case CircuitPackage.CIRCUIT__PARENT:
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
        case CircuitPackage.CIRCUIT__CONNECTORS:
            getConnectors().clear();
            getConnectors().addAll((Collection<? extends Connector>) newValue);
            return;
        case CircuitPackage.CIRCUIT__INNER_CIRCUITS:
            getInnerCircuits().clear();
            getInnerCircuits().addAll((Collection<? extends Circuit>) newValue);
            return;
        case CircuitPackage.CIRCUIT__INNER_WIRES:
            getInnerWires().clear();
            getInnerWires().addAll((Collection<? extends Wire>) newValue);
            return;
        case CircuitPackage.CIRCUIT__TYPE:
            setType((String) newValue);
            return;
        case CircuitPackage.CIRCUIT__INSTANCE_NAME:
            setInstanceName((String) newValue);
            return;
        case CircuitPackage.CIRCUIT__PARENT:
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
        case CircuitPackage.CIRCUIT__CONNECTORS:
            getConnectors().clear();
            return;
        case CircuitPackage.CIRCUIT__INNER_CIRCUITS:
            getInnerCircuits().clear();
            return;
        case CircuitPackage.CIRCUIT__INNER_WIRES:
            getInnerWires().clear();
            return;
        case CircuitPackage.CIRCUIT__TYPE:
            setType(TYPE_EDEFAULT);
            return;
        case CircuitPackage.CIRCUIT__INSTANCE_NAME:
            setInstanceName(INSTANCE_NAME_EDEFAULT);
            return;
        case CircuitPackage.CIRCUIT__PARENT:
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
        case CircuitPackage.CIRCUIT__CONNECTORS:
            return connectors != null && !connectors.isEmpty();
        case CircuitPackage.CIRCUIT__INNER_CIRCUITS:
            return innerCircuits != null && !innerCircuits.isEmpty();
        case CircuitPackage.CIRCUIT__INNER_WIRES:
            return innerWires != null && !innerWires.isEmpty();
        case CircuitPackage.CIRCUIT__TYPE:
            return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
        case CircuitPackage.CIRCUIT__INSTANCE_NAME:
            return INSTANCE_NAME_EDEFAULT == null ? instanceName != null : !INSTANCE_NAME_EDEFAULT
                    .equals(instanceName);
        case CircuitPackage.CIRCUIT__PARENT:
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
        result.append(" (type: ");
        result.append(type);
        result.append(", instanceName: ");
        result.append(instanceName);
        result.append(')');
        return result.toString();
    }

} //CircuitImpl
