/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 *
 * $Id$
 */
package de.cau.cs.kieler.core.kgraph;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.core.kgraph.KGraphPackage
 * @generated
 * @kieler.design 2011-02-01 reviewed by cmot, soh
 */
public interface KGraphFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    KGraphFactory eINSTANCE = de.cau.cs.kieler.core.kgraph.impl.KGraphFactoryImpl.init();

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
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    KGraphPackage getKGraphPackage();

} //KGraphFactory
