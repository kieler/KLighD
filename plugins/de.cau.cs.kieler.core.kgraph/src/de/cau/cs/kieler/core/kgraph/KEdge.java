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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An edge must be assigned a source and a target node, but the source and target ports
 * are optional.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.kgraph.KEdge#getSource <em>Source</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.kgraph.KEdge#getTarget <em>Target</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.kgraph.KEdge#getSourcePort <em>Source Port</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.kgraph.KEdge#getTargetPort <em>Target Port</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.kgraph.KGraphPackage#getKEdge()
 * @model
 * @generated
 * @kieler.rating 2011-02-01 yellow
 *     reviewed by cmot, soh
 */
public interface KEdge extends KLabeledGraphElement {
    /**
     * Returns the value of the '<em><b>Source</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.core.kgraph.KNode#getOutgoingEdges <em>Outgoing Edges</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The source node is expected to be set for each edge. This is especially
     * important because the source node is defined to be the container of the
     * edge, which is relevant for many EMF features such as XML storage or
     * copying.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Source</em>' container reference.
     * @see #setSource(KNode)
     * @see de.cau.cs.kieler.core.kgraph.KGraphPackage#getKEdge_Source()
     * @see de.cau.cs.kieler.core.kgraph.KNode#getOutgoingEdges
     * @model opposite="outgoingEdges" required="true" transient="false"
     * @generated
     */
    KNode getSource();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.kgraph.KEdge#getSource <em>Source</em>}' container reference.
     * <!-- begin-user-doc -->
     * This automatically adds the edge to the the source node's list of outgoing
     * edges.
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' container reference.
     * @see #getSource()
     * @generated
     */
    void setSource(KNode value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.core.kgraph.KNode#getIncomingEdges <em>Incoming Edges</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The target node is expected to be set for each edge.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(KNode)
     * @see de.cau.cs.kieler.core.kgraph.KGraphPackage#getKEdge_Target()
     * @see de.cau.cs.kieler.core.kgraph.KNode#getIncomingEdges
     * @model opposite="incomingEdges" required="true"
     * @generated
     */
    KNode getTarget();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.kgraph.KEdge#getTarget <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * This automatically adds the edge to the target node's list of incoming
     * edges.
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
     * <!-- end-model-doc -->
     * @return the value of the '<em>Source Port</em>' reference.
     * @see #setSourcePort(KPort)
     * @see de.cau.cs.kieler.core.kgraph.KGraphPackage#getKEdge_SourcePort()
     * @model
     * @generated
     */
    KPort getSourcePort();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.kgraph.KEdge#getSourcePort <em>Source Port</em>}' reference.
     * <!-- begin-user-doc -->
     * As this reference is not bidirectional, the edge must be added to
     * the list obtained with the {@link KPort#getEdges() getEdges} method
     * of the given port.
     * <p>
     * The node related to the source port must be equal to the source node
     * of this edge.
     * </p>
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
     * <!-- end-model-doc -->
     * @return the value of the '<em>Target Port</em>' reference.
     * @see #setTargetPort(KPort)
     * @see de.cau.cs.kieler.core.kgraph.KGraphPackage#getKEdge_TargetPort()
     * @model
     * @generated
     */
    KPort getTargetPort();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.kgraph.KEdge#getTargetPort <em>Target Port</em>}' reference.
     * <!-- begin-user-doc -->
     * As this reference is not bidirectional, the edge must be added to
     * the list obtained with the {@link KPort#getEdges() getEdges} method
     * of the given port.the given port.
     * <p>
     * The node related to the target port must be equal to the target node
     * of this edge.
     * </p>
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Port</em>' reference.
     * @see #getTargetPort()
     * @generated
     */
    void setTargetPort(KPort value);

} // KEdge
