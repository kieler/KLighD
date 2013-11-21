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
package de.cau.cs.kieler.klighd.examples.circuit;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.klighd.examples.circuit.CircuitFactory
 * @model kind="package"
 * @generated
 */
public interface CircuitPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "circuit";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://kieler.cs.cau.de/KLighD/circuit";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "circuit";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    CircuitPackage eINSTANCE = de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitPackageImpl
            .init();

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitImpl <em>Circuit</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitImpl
     * @see de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitPackageImpl#getCircuit()
     * @generated
     */
    int CIRCUIT = 0;

    /**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CIRCUIT__CONNECTORS = 0;

    /**
     * The feature id for the '<em><b>Inner Circuits</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CIRCUIT__INNER_CIRCUITS = 1;

    /**
     * The feature id for the '<em><b>Inner Wires</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CIRCUIT__INNER_WIRES = 2;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CIRCUIT__TYPE = 3;

    /**
     * The feature id for the '<em><b>Instance Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CIRCUIT__INSTANCE_NAME = 4;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CIRCUIT__PARENT = 5;

    /**
     * The number of structural features of the '<em>Circuit</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CIRCUIT_FEATURE_COUNT = 6;

    /**
     * The number of operations of the '<em>Circuit</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CIRCUIT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.examples.circuit.impl.ConnectorImpl <em>Connector</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.examples.circuit.impl.ConnectorImpl
     * @see de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitPackageImpl#getConnector()
     * @generated
     */
    int CONNECTOR = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTOR__NAME = 0;

    /**
     * The feature id for the '<em><b>Connected Wires</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTOR__CONNECTED_WIRES = 1;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTOR__PARENT = 2;

    /**
     * The number of structural features of the '<em>Connector</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTOR_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Connector</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECTOR_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.klighd.examples.circuit.impl.WireImpl <em>Wire</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.examples.circuit.impl.WireImpl
     * @see de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitPackageImpl#getWire()
     * @generated
     */
    int WIRE = 2;

    /**
     * The feature id for the '<em><b>Connected To</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WIRE__CONNECTED_TO = 0;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WIRE__PARENT = 1;

    /**
     * The number of structural features of the '<em>Wire</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WIRE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Wire</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WIRE_OPERATION_COUNT = 0;

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit <em>Circuit</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Circuit</em>'.
     * @see de.cau.cs.kieler.klighd.examples.circuit.Circuit
     * @generated
     */
    EClass getCircuit();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getConnectors <em>Connectors</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Connectors</em>'.
     * @see de.cau.cs.kieler.klighd.examples.circuit.Circuit#getConnectors()
     * @see #getCircuit()
     * @generated
     */
    EReference getCircuit_Connectors();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getInnerCircuits <em>Inner Circuits</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Inner Circuits</em>'.
     * @see de.cau.cs.kieler.klighd.examples.circuit.Circuit#getInnerCircuits()
     * @see #getCircuit()
     * @generated
     */
    EReference getCircuit_InnerCircuits();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getInnerWires <em>Inner Wires</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Inner Wires</em>'.
     * @see de.cau.cs.kieler.klighd.examples.circuit.Circuit#getInnerWires()
     * @see #getCircuit()
     * @generated
     */
    EReference getCircuit_InnerWires();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see de.cau.cs.kieler.klighd.examples.circuit.Circuit#getType()
     * @see #getCircuit()
     * @generated
     */
    EAttribute getCircuit_Type();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getInstanceName <em>Instance Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Instance Name</em>'.
     * @see de.cau.cs.kieler.klighd.examples.circuit.Circuit#getInstanceName()
     * @see #getCircuit()
     * @generated
     */
    EAttribute getCircuit_InstanceName();

    /**
     * Returns the meta object for the container reference '{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Parent</em>'.
     * @see de.cau.cs.kieler.klighd.examples.circuit.Circuit#getParent()
     * @see #getCircuit()
     * @generated
     */
    EReference getCircuit_Parent();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.examples.circuit.Connector <em>Connector</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Connector</em>'.
     * @see de.cau.cs.kieler.klighd.examples.circuit.Connector
     * @generated
     */
    EClass getConnector();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.examples.circuit.Connector#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.cau.cs.kieler.klighd.examples.circuit.Connector#getName()
     * @see #getConnector()
     * @generated
     */
    EAttribute getConnector_Name();

    /**
     * Returns the meta object for the reference list '{@link de.cau.cs.kieler.klighd.examples.circuit.Connector#getConnectedWires <em>Connected Wires</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Connected Wires</em>'.
     * @see de.cau.cs.kieler.klighd.examples.circuit.Connector#getConnectedWires()
     * @see #getConnector()
     * @generated
     */
    EReference getConnector_ConnectedWires();

    /**
     * Returns the meta object for the container reference '{@link de.cau.cs.kieler.klighd.examples.circuit.Connector#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Parent</em>'.
     * @see de.cau.cs.kieler.klighd.examples.circuit.Connector#getParent()
     * @see #getConnector()
     * @generated
     */
    EReference getConnector_Parent();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.examples.circuit.Wire <em>Wire</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Wire</em>'.
     * @see de.cau.cs.kieler.klighd.examples.circuit.Wire
     * @generated
     */
    EClass getWire();

    /**
     * Returns the meta object for the reference list '{@link de.cau.cs.kieler.klighd.examples.circuit.Wire#getConnectedTo <em>Connected To</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Connected To</em>'.
     * @see de.cau.cs.kieler.klighd.examples.circuit.Wire#getConnectedTo()
     * @see #getWire()
     * @generated
     */
    EReference getWire_ConnectedTo();

    /**
     * Returns the meta object for the container reference '{@link de.cau.cs.kieler.klighd.examples.circuit.Wire#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Parent</em>'.
     * @see de.cau.cs.kieler.klighd.examples.circuit.Wire#getParent()
     * @see #getWire()
     * @generated
     */
    EReference getWire_Parent();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    CircuitFactory getCircuitFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each operation of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitImpl <em>Circuit</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitImpl
         * @see de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitPackageImpl#getCircuit()
         * @generated
         */
        EClass CIRCUIT = eINSTANCE.getCircuit();

        /**
         * The meta object literal for the '<em><b>Connectors</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CIRCUIT__CONNECTORS = eINSTANCE.getCircuit_Connectors();

        /**
         * The meta object literal for the '<em><b>Inner Circuits</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CIRCUIT__INNER_CIRCUITS = eINSTANCE.getCircuit_InnerCircuits();

        /**
         * The meta object literal for the '<em><b>Inner Wires</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CIRCUIT__INNER_WIRES = eINSTANCE.getCircuit_InnerWires();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CIRCUIT__TYPE = eINSTANCE.getCircuit_Type();

        /**
         * The meta object literal for the '<em><b>Instance Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CIRCUIT__INSTANCE_NAME = eINSTANCE.getCircuit_InstanceName();

        /**
         * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CIRCUIT__PARENT = eINSTANCE.getCircuit_Parent();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.examples.circuit.impl.ConnectorImpl <em>Connector</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.examples.circuit.impl.ConnectorImpl
         * @see de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitPackageImpl#getConnector()
         * @generated
         */
        EClass CONNECTOR = eINSTANCE.getConnector();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CONNECTOR__NAME = eINSTANCE.getConnector_Name();

        /**
         * The meta object literal for the '<em><b>Connected Wires</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONNECTOR__CONNECTED_WIRES = eINSTANCE.getConnector_ConnectedWires();

        /**
         * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CONNECTOR__PARENT = eINSTANCE.getConnector_Parent();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.klighd.examples.circuit.impl.WireImpl <em>Wire</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.klighd.examples.circuit.impl.WireImpl
         * @see de.cau.cs.kieler.klighd.examples.circuit.impl.CircuitPackageImpl#getWire()
         * @generated
         */
        EClass WIRE = eINSTANCE.getWire();

        /**
         * The meta object literal for the '<em><b>Connected To</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference WIRE__CONNECTED_TO = eINSTANCE.getWire_ConnectedTo();

        /**
         * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference WIRE__PARENT = eINSTANCE.getWire_Parent();

    }

} //CircuitPackage
