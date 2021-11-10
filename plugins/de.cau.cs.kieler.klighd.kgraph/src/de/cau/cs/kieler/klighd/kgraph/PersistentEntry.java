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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Persistent Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.PersistentEntry#getKey <em>Key</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.PersistentEntry#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getPersistentEntry()
 * @model
 * @generated
 */
public interface PersistentEntry extends EObject {
	/**
     * Returns the value of the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Key</em>' attribute.
     * @see #setKey(String)
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getPersistentEntry_Key()
     * @model required="true"
     * @generated
     */
	String getKey();

	/**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.PersistentEntry#getKey <em>Key</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Key</em>' attribute.
     * @see #getKey()
     * @generated
     */
	void setKey(String value);

	/**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(String)
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getPersistentEntry_Value()
     * @model
     * @generated
     */
	String getValue();

	/**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.PersistentEntry#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
	void setValue(String value);

} // PersistentEntry
