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
 * A representation of the model object '<em><b>Wire</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.Wire#getConnectedTo <em>Connected To</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.examples.circuit.Wire#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.klighd.examples.circuit.CircuitPackage#getWire()
 * @model
 * @generated
 */
public interface Wire extends EObject {
    /**
     * Returns the value of the '<em><b>Connected To</b></em>' reference list.
     * The list contents are of type {@link de.cau.cs.kieler.klighd.examples.circuit.Connector}.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.klighd.examples.circuit.Connector#getConnectedWires <em>Connected Wires</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Connected To</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Connected To</em>' reference list.
     * @see de.cau.cs.kieler.klighd.examples.circuit.CircuitPackage#getWire_ConnectedTo()
     * @see de.cau.cs.kieler.klighd.examples.circuit.Connector#getConnectedWires
     * @model opposite="connectedWires"
     * @generated
     */
    EList<Connector> getConnectedTo();

    /**
     * Returns the value of the '<em><b>Parent</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.klighd.examples.circuit.Circuit#getInnerWires <em>Inner Wires</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parent</em>' container reference.
     * @see #setParent(Circuit)
     * @see de.cau.cs.kieler.klighd.examples.circuit.CircuitPackage#getWire_Parent()
     * @see de.cau.cs.kieler.klighd.examples.circuit.Circuit#getInnerWires
     * @model opposite="innerWires" required="true" transient="false"
     * @generated
     */
    Circuit getParent();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.examples.circuit.Wire#getParent <em>Parent</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent</em>' container reference.
     * @see #getParent()
     * @generated
     */
    void setParent(Circuit value);

} // Wire
