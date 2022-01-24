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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage
 * @generated
 */
public interface KGraphFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	KGraphFactory eINSTANCE = de.cau.cs.kieler.klighd.kgraph.impl.KGraphFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Data</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Data</em>'.
     * @generated
     */
	KGraphData createKGraphData();

	/**
     * Returns a new object of class '<em>KNode</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>KNode</em>'.
     * @generated
     */
	KNode createKNode();

	/**
     * Returns a new object of class '<em>KEdge</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>KEdge</em>'.
     * @generated
     */
	KEdge createKEdge();

	/**
     * Returns a new object of class '<em>KPort</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>KPort</em>'.
     * @generated
     */
	KPort createKPort();

	/**
     * Returns a new object of class '<em>KLabel</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>KLabel</em>'.
     * @generated
     */
	KLabel createKLabel();

	/**
     * Returns a new object of class '<em>Persistent Entry</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Persistent Entry</em>'.
     * @generated
     */
	PersistentEntry createPersistentEntry();

	/**
     * Returns a new object of class '<em>KPoint</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>KPoint</em>'.
     * @generated
     */
	KPoint createKPoint();

	/**
     * Returns a new object of class '<em>KInsets</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>KInsets</em>'.
     * @generated
     */
	KInsets createKInsets();

	/**
     * Returns a new object of class '<em>KIdentifier</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>KIdentifier</em>'.
     * @generated
     */
	KIdentifier createKIdentifier();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	KGraphPackage getKGraphPackage();

} //KGraphFactory
