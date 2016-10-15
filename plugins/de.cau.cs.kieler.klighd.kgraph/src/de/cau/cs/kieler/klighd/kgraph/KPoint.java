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

import org.eclipse.elk.core.math.KVector;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KPoint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KPoint#getX <em>X</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KPoint#getY <em>Y</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKPoint()
 * @model
 * @generated
 */
public interface KPoint extends EObject {
	/**
     * Returns the value of the '<em><b>X</b></em>' attribute.
     * The default value is <code>"0.0f"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>X</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>X</em>' attribute.
     * @see #setX(float)
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKPoint_X()
     * @model default="0.0f"
     * @generated
     */
	float getX();

	/**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KPoint#getX <em>X</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>X</em>' attribute.
     * @see #getX()
     * @generated
     */
	void setX(float value);

	/**
     * Returns the value of the '<em><b>Y</b></em>' attribute.
     * The default value is <code>"0.0f"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Y</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Y</em>' attribute.
     * @see #setY(float)
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKPoint_Y()
     * @model default="0.0f"
     * @generated
     */
	float getY();

	/**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KPoint#getY <em>Y</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Y</em>' attribute.
     * @see #getY()
     * @generated
     */
	void setY(float value);

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Set a new position for this point.
     * @param x the new x coordinate value
     * @param y the new y coordinate value
     * <!-- end-model-doc -->
     * @model
     * @generated
     */
	void setPos(float x, float y);

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Set the position of this point using a vector.
     * @param pos the vector for the new position
     * <!-- end-model-doc -->
     * @model posDataType="de.cau.cs.kieler.klighd.kgraph.KVector"
     * @generated
     */
	void applyVector(KVector pos);

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Create a vector from this point.
     * @return a vector with the position of this point
     * <!-- end-model-doc -->
     * @model dataType="de.cau.cs.kieler.klighd.kgraph.KVector"
     * @generated
     */
	KVector createVector();

} // KPoint
