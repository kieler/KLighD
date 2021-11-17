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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KPort</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Each port must be assigned a containing node. A port may contain incoming
 * edges as well as outgoing edges, but usually either one or the other kind is
 * referenced. The list of edges is not opposite to the edges' source or target
 * port reference. However, the list content is automatically updated when those
 * references are set.
 * <p>
 * Since the information contained in this list is redundant, it is marked as transient,
 * i.e. it is not serialized.
 * </p>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KPort#getNode <em>Node</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KPort#getEdges <em>Edges</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKPort()
 * @model
 * @generated
 */
public interface KPort extends KLabeledGraphElement, KShapeLayout {
	/**
     * Returns the value of the '<em><b>Node</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.klighd.kgraph.KNode#getPorts <em>Ports</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Each port must be assigned a containing node. This is especially
     * important because the node is defined to be the container of the
     * port, which is relevant for many EMF features such as XML storage or
     * copying.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Node</em>' container reference.
     * @see #setNode(KNode)
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKPort_Node()
     * @see de.cau.cs.kieler.klighd.kgraph.KNode#getPorts
     * @model opposite="ports" required="true" transient="false"
     * @generated
     */
	KNode getNode();

	/**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KPort#getNode <em>Node</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Node</em>' container reference.
     * @see #getNode()
     * @generated
     */
	void setNode(KNode value);

	/**
     * Returns the value of the '<em><b>Edges</b></em>' reference list.
     * The list contents are of type {@link de.cau.cs.kieler.klighd.kgraph.KEdge}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Edges in this list may be incoming as well as outgoing with respect
     * to the containing node. The list of edges is not opposite to the edges'
     * source or target port reference. Just adding an edge to this list does
     * not imply that the source or target port reference is set, since it is
     * unclear which reference to pick. However, the list content is automatically
     * updated when one of those references is set or unset.
     * Therefore it is advisable not to modify this list directly, but to use
     * {@link KEdge#setSourcePort(KPort)} or {@link KEdge#setTargetPort(KPort)}
     * instead.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Edges</em>' reference list.
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKPort_Edges()
     * @model transient="true" derived="true"
     * @generated
     */
	EList<KEdge> getEdges();

} // KPort
