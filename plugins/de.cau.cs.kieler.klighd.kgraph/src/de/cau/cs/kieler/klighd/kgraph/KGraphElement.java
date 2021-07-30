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
package de.cau.cs.kieler.klighd.kgraph;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This is the superclass of all elements of a graph such as nodes, edges, ports,
 * and labels. A graph element may contain an arbitrary number of additional
 * data instances.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KGraphElement#getData <em>Data</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKGraphElement()
 * @model abstract="true"
 * @generated
 */
public interface KGraphElement extends EMapPropertyHolder {
	/**
     * Returns the value of the '<em><b>Data</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.klighd.kgraph.KGraphData}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Each element of this list may contain additional data for the model element.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Data</em>' containment reference list.
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKGraphElement_Data()
     * @model containment="true"
     * @generated
     */
	EList<KGraphData> getData();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Returns the first data instance that matches the given class. Classes
     * can be obtained using the static package methods of the corresponding
     * EMF model.
     * @return graph data for the given type, or {@code null} if there is none
     * <!-- end-model-doc -->
     * @model
     * @generated
     */
	KGraphData getData(EClass type);

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Returns the first data instance that matches the given class.
     * @param type the class of graph data to retrieve
     * @return graph data for the given type, or {@code null} if there is none
     * <!-- end-model-doc -->
     * @model
     * @generated
     */
	<T extends KGraphData> T getData(Class<T> type);

} // KGraphElement
