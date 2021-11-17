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

import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.IPropertyHolder;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EMap Property Holder</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A property holder implementation based on {@link org.eclipse.emf.common.util.EMap} which can be used in Ecore models.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder#getProperties <em>Properties</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder#getPersistentEntries <em>Persistent Entries</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getEMapPropertyHolder()
 * @model abstract="true" superTypes="de.cau.cs.kieler.klighd.kgraph.IPropertyHolder"
 * @generated
 */
public interface EMapPropertyHolder extends EObject, IPropertyHolder {
	/**
     * Returns the value of the '<em><b>Properties</b></em>' map.
     * The key is of type {@link org.eclipse.elk.graph.properties.IProperty<?>},
     * and the value is of type {@link java.lang.Object},
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Properties</em>' map.
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getEMapPropertyHolder_Properties()
     * @model mapType="de.cau.cs.kieler.klighd.kgraph.IPropertyToObjectMap<de.cau.cs.kieler.klighd.kgraph.IProperty<?>, org.eclipse.emf.ecore.EJavaObject>" transient="true"
     * @generated
     */
	EMap<IProperty<?>, Object> getProperties();

	/**
     * Returns the value of the '<em><b>Persistent Entries</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.klighd.kgraph.PersistentEntry}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Persistent Entries</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Persistent Entries</em>' containment reference list.
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getEMapPropertyHolder_PersistentEntries()
     * @model containment="true"
     * @generated
     */
	EList<PersistentEntry> getPersistentEntries();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Serialize all entries of the properties map using {@link Object#toString()}
     * and write them into the list of persistent entries. The previous content is cleared.
     * <!-- end-model-doc -->
     * @model
     * @generated
     */
	void makePersistent();

} // EMapPropertyHolder
