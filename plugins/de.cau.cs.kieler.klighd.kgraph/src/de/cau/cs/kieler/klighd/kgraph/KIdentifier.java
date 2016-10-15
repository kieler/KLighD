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
 */
package de.cau.cs.kieler.klighd.kgraph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KIdentifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Can be used for unique identification of KGraph elements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KIdentifier#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKIdentifier()
 * @model
 * @generated
 */
public interface KIdentifier extends KGraphData {
	/**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKIdentifier_Id()
     * @model required="true"
     * @generated
     */
	String getId();

	/**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KIdentifier#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
	void setId(String value);

} // KIdentifier
