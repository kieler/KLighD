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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KInsets</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Insets specify the amount of space that is required between the
 * children of a node and the node's boundary. Usually the inset values must
 * be added to the bounding box of the contained subgraph.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KInsets#getTop <em>Top</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KInsets#getBottom <em>Bottom</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KInsets#getLeft <em>Left</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KInsets#getRight <em>Right</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKInsets()
 * @model
 * @generated
 */
public interface KInsets extends EObject {
	/**
	 * Returns the value of the '<em><b>Top</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is the minimal distance to the top side of a parent node.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Top</em>' attribute.
	 * @see #setTop(float)
	 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKInsets_Top()
	 * @model
	 * @generated
	 */
	float getTop();

	/**
	 * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KInsets#getTop <em>Top</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Top</em>' attribute.
	 * @see #getTop()
	 * @generated
	 */
	void setTop(float value);

	/**
	 * Returns the value of the '<em><b>Bottom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is the minimal distance to the bottom side of a parent node.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bottom</em>' attribute.
	 * @see #setBottom(float)
	 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKInsets_Bottom()
	 * @model
	 * @generated
	 */
	float getBottom();

	/**
	 * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KInsets#getBottom <em>Bottom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bottom</em>' attribute.
	 * @see #getBottom()
	 * @generated
	 */
	void setBottom(float value);

	/**
	 * Returns the value of the '<em><b>Left</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is the minimal distance to the left side of a parent node.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Left</em>' attribute.
	 * @see #setLeft(float)
	 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKInsets_Left()
	 * @model
	 * @generated
	 */
	float getLeft();

	/**
	 * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KInsets#getLeft <em>Left</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left</em>' attribute.
	 * @see #getLeft()
	 * @generated
	 */
	void setLeft(float value);

	/**
	 * Returns the value of the '<em><b>Right</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right</em>' attribute.
	 * @see #setRight(float)
	 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKInsets_Right()
	 * @model
	 * @generated
	 */
	float getRight();

	/**
	 * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KInsets#getRight <em>Right</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right</em>' attribute.
	 * @see #getRight()
	 * @generated
	 */
	void setRight(float value);

} // KInsets
