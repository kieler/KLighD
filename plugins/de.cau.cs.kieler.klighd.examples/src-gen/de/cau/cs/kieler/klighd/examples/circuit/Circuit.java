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
package de.cau.cs.kieler.klighd.examples.circuit;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Circuit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getConnectors <em>Connectors</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getInnerCircuits <em>Inner Circuits</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getInnerWires <em>Inner Wires</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getType <em>Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getInstanceName <em>Instance Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.klighd.examples.circuit.CircuitPackage#getCircuit()
 * @model
 * @generated
 */
public interface Circuit extends EObject {
    /**
     * Returns the value of the '<em><b>Connectors</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.klighd.examples.circuit.Connector}.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.klighd.examples.circuit.Connector#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Connectors</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Connectors</em>' containment reference list.
     * @see de.cau.cs.kieler.klighd.examples.circuit.CircuitPackage#getCircuit_Connectors()
     * @see de.cau.cs.kieler.klighd.examples.circuit.Connector#getParent
     * @model opposite="parent" containment="true"
     * @generated
     */
    EList<Connector> getConnectors();

    /**
     * Returns the value of the '<em><b>Inner Circuits</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.klighd.examples.circuit.Circuit}.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Inner Circuits</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Inner Circuits</em>' containment reference list.
     * @see de.cau.cs.kieler.klighd.examples.circuit.CircuitPackage#getCircuit_InnerCircuits()
     * @see de.cau.cs.kieler.klighd.examples.circuit.Circuit#getParent
     * @model opposite="parent" containment="true"
     * @generated
     */
    EList<Circuit> getInnerCircuits();

    /**
     * Returns the value of the '<em><b>Inner Wires</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.klighd.examples.circuit.Wire}.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.klighd.examples.circuit.Wire#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Inner Wires</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Inner Wires</em>' containment reference list.
     * @see de.cau.cs.kieler.klighd.examples.circuit.CircuitPackage#getCircuit_InnerWires()
     * @see de.cau.cs.kieler.klighd.examples.circuit.Wire#getParent
     * @model opposite="parent" containment="true"
     * @generated
     */
    EList<Wire> getInnerWires();

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(String)
     * @see de.cau.cs.kieler.klighd.examples.circuit.CircuitPackage#getCircuit_Type()
     * @model
     * @generated
     */
    String getType();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
    void setType(String value);

    /**
     * Returns the value of the '<em><b>Instance Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Instance Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Instance Name</em>' attribute.
     * @see #setInstanceName(String)
     * @see de.cau.cs.kieler.klighd.examples.circuit.CircuitPackage#getCircuit_InstanceName()
     * @model
     * @generated
     */
    String getInstanceName();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getInstanceName <em>Instance Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Instance Name</em>' attribute.
     * @see #getInstanceName()
     * @generated
     */
    void setInstanceName(String value);

    /**
     * Returns the value of the '<em><b>Parent</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getInnerCircuits <em>Inner Circuits</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parent</em>' container reference.
     * @see #setParent(Circuit)
     * @see de.cau.cs.kieler.klighd.examples.circuit.CircuitPackage#getCircuit_Parent()
     * @see de.cau.cs.kieler.klighd.examples.circuit.Circuit#getInnerCircuits
     * @model opposite="innerCircuits" required="true" transient="false"
     * @generated
     */
    Circuit getParent();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getParent <em>Parent</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent</em>' container reference.
     * @see #getParent()
     * @generated
     */
    void setParent(Circuit value);

} // Circuit
