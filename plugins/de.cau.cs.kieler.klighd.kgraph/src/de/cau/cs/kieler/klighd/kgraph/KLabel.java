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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KLabel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Each label must be assigned a parent graph element and a text string.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KLabel#getText <em>Text</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KLabel#getParent <em>Parent</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKLabel()
 * @model
 * @generated
 */
public interface KLabel extends KGraphElement, KShapeLayout {
	/**
     * Returns the value of the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Text</em>' attribute.
     * @see #setText(String)
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKLabel_Text()
     * @model
     * @generated
     */
	String getText();

	/**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KLabel#getText <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text</em>' attribute.
     * @see #getText()
     * @generated
     */
	void setText(String value);

	/**
     * Returns the value of the '<em><b>Parent</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement#getLabels <em>Labels</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Parent</em>' container reference.
     * @see #setParent(KLabeledGraphElement)
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKLabel_Parent()
     * @see de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement#getLabels
     * @model opposite="labels" required="true" transient="false"
     * @generated
     */
	KLabeledGraphElement getParent();

	/**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KLabel#getParent <em>Parent</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent</em>' container reference.
     * @see #getParent()
     * @generated
     */
	void setParent(KLabeledGraphElement value);

} // KLabel
