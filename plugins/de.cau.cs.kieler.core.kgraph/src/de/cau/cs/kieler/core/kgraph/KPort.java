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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KPort</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Each port must be assigned a containing node. A port may contain incoming
 * edges as well as outgoing edges, independently of its type.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.kgraph.KPort#getNode <em>Node</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.kgraph.KPort#getEdges <em>Edges</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.kgraph.KGraphPackage#getKPort()
 * @model
 * @generated
 * @kieler.design 2011-02-01 reviewed by cmot, soh
 */
public interface KPort extends KLabeledGraphElement {
    /**
     * Returns the value of the '<em><b>Node</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.core.kgraph.KNode#getPorts <em>Ports</em>}'.
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
     * @see de.cau.cs.kieler.core.kgraph.KGraphPackage#getKPort_Node()
     * @see de.cau.cs.kieler.core.kgraph.KNode#getPorts
     * @model opposite="ports" required="true" transient="false"
     * @generated
     */
    KNode getNode();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.kgraph.KPort#getNode <em>Node</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Node</em>' container reference.
     * @see #getNode()
     * @generated
     */
    void setNode(KNode value);

    /**
     * Returns the value of the '<em><b>Edges</b></em>' reference list.
     * The list contents are of type {@link de.cau.cs.kieler.core.kgraph.KEdge}.
     * <!-- begin-user-doc -->
     * This reference is not bidirectional, so adding edges to it does not
     * affect the source or target port references of the edges.
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Edges in this list may be incoming as well as outgoing with respect
     * to the containing node.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Edges</em>' reference list.
     * @see de.cau.cs.kieler.core.kgraph.KGraphPackage#getKPort_Edges()
     * @model
     * @generated
     */
    EList<KEdge> getEdges();

} // KPort
