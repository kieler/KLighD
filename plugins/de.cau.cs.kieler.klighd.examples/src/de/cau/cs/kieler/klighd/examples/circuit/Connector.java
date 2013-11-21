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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.Connector#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.Connector#getConnectedWires <em>Connected Wires</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.Connector#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.klighd.examples.circuit.CircuitPackage#getConnector()
 * @model
 * @generated
 */
public interface Connector extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see de.cau.cs.kieler.klighd.examples.circuit.CircuitPackage#getConnector_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.examples.circuit.Connector#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Connected Wires</b></em>' reference list.
     * The list contents are of type {@link de.cau.cs.kieler.klighd.examples.circuit.Wire}.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.klighd.examples.circuit.Wire#getConnectedTo <em>Connected To</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Connected Wires</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Connected Wires</em>' reference list.
     * @see de.cau.cs.kieler.klighd.examples.circuit.CircuitPackage#getConnector_ConnectedWires()
     * @see de.cau.cs.kieler.klighd.examples.circuit.Wire#getConnectedTo
     * @model opposite="connectedTo"
     * @generated
     */
    EList<Wire> getConnectedWires();

    /**
     * Returns the value of the '<em><b>Parent</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getConnectors <em>Connectors</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parent</em>' container reference.
     * @see #setParent(Circuit)
     * @see de.cau.cs.kieler.klighd.examples.circuit.CircuitPackage#getConnector_Parent()
     * @see de.cau.cs.kieler.klighd.examples.circuit.Circuit#getConnectors
     * @model opposite="connectors" required="true" transient="false"
     * @generated
     */
    Circuit getParent();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.examples.circuit.Connector#getParent <em>Parent</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent</em>' container reference.
     * @see #getParent()
     * @generated
     */
    void setParent(Circuit value);

} // Connector
