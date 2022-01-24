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
 * A representation of the model object '<em><b>KEdge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An edge must be assigned a source and a target node, but the source and target ports
 * are optional. The source and target references are opposite to the lists of outgoing and
 * incoming edges of nodes, respectively. The source and target port references are
 * not opposite to the ports' list of edges, but despite that, setting these references will
 * automatically update the edges reference of the corresponding port.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KEdge#getSource <em>Source</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KEdge#getTarget <em>Target</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KEdge#getSourcePort <em>Source Port</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KEdge#getTargetPort <em>Target Port</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKEdge()
 * @model
 * @generated
 */
public interface KEdge extends KLabeledGraphElement, KEdgeLayout {
	/**
     * Returns the value of the '<em><b>Source</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.klighd.kgraph.KNode#getOutgoingEdges <em>Outgoing Edges</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The source node is expected to be set for each edge. This is especially
     * important because the source node is defined to be the container of the
     * edge, which is relevant for many EMF features such as XML storage or
     * copying. The source reference is opposite to the nodes' list of outgoing
     * edges, hence those references are synchronized automatically.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Source</em>' container reference.
     * @see #setSource(KNode)
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKEdge_Source()
     * @see de.cau.cs.kieler.klighd.kgraph.KNode#getOutgoingEdges
     * @model opposite="outgoingEdges" required="true" transient="false"
     * @generated
     */
	KNode getSource();

	/**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KEdge#getSource <em>Source</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' container reference.
     * @see #getSource()
     * @generated
     */
	void setSource(KNode value);

	/**
     * Returns the value of the '<em><b>Target</b></em>' reference.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.klighd.kgraph.KNode#getIncomingEdges <em>Incoming Edges</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The target node is expected to be set for each edge.
     * The target reference is opposite to the nodes' list of incoming
     * edges, hence those references are synchronized automatically.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(KNode)
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKEdge_Target()
     * @see de.cau.cs.kieler.klighd.kgraph.KNode#getIncomingEdges
     * @model opposite="incomingEdges" required="true"
     * @generated
     */
	KNode getTarget();

	/**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KEdge#getTarget <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
	void setTarget(KNode value);

	/**
     * Returns the value of the '<em><b>Source Port</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * This reference is optional, as a node may have no ports.
     * The reference is not opposite to the list of edges stored by ports,
     * but setting it automatically updates that list.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Source Port</em>' reference.
     * @see #setSourcePort(KPort)
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKEdge_SourcePort()
     * @model
     * @generated
     */
	KPort getSourcePort();

	/**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KEdge#getSourcePort <em>Source Port</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Port</em>' reference.
     * @see #getSourcePort()
     * @generated
     */
	void setSourcePort(KPort value);

	/**
     * Returns the value of the '<em><b>Target Port</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * This reference is optional, as a node may have no ports.
     * The reference is not opposite to the list of edges stored by ports,
     * but setting it automatically updates that list.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Target Port</em>' reference.
     * @see #setTargetPort(KPort)
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKEdge_TargetPort()
     * @model
     * @generated
     */
	KPort getTargetPort();

	/**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KEdge#getTargetPort <em>Target Port</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Port</em>' reference.
     * @see #getTargetPort()
     * @generated
     */
	void setTargetPort(KPort value);

} // KEdge
