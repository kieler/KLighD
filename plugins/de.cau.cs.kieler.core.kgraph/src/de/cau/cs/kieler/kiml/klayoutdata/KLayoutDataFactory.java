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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 *
 * $Id$
 */
package de.cau.cs.kieler.kiml.klayoutdata;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage
 * @generated
 */
public interface KLayoutDataFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    KLayoutDataFactory eINSTANCE = de.cau.cs.kieler.kiml.klayoutdata.impl.KLayoutDataFactoryImpl.init();

    /**
     * Returns a new object of class '<em>KShape Layout</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KShape Layout</em>'.
     * @generated
     */
    KShapeLayout createKShapeLayout();

    /**
     * Returns a new object of class '<em>KEdge Layout</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KEdge Layout</em>'.
     * @generated
     */
    KEdgeLayout createKEdgeLayout();

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
    KLayoutDataPackage getKLayoutDataPackage();

} //KLayoutDataFactory
