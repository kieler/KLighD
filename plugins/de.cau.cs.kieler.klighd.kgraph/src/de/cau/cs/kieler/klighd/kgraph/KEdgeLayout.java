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

import org.eclipse.elk.core.math.KVectorChain;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KEdge Layout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This layout data contains special information for edges, such as bend points.
 * Each graph element has either a shape layout or an edge layout attached.
 * <p>
 * All layout coordinates for edges are defined to be relative to the parent of
 * the source node, except when the target node is directly or indirectly contained
 * in the source node, in which case all coordinates are relative to the source node
 * itself. The insets of the reference node are not included in relative coordinates.
 * </p>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KEdgeLayout#getBendPoints <em>Bend Points</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KEdgeLayout#getSourcePoint <em>Source Point</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KEdgeLayout#getTargetPoint <em>Target Point</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKEdgeLayout()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface KEdgeLayout extends KLayoutData {
	/**
     * Returns the value of the '<em><b>Bend Points</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.klighd.kgraph.KPoint}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The coordinates of bend points must obey the general rules for edge
     * coordinates defined above.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Bend Points</em>' containment reference list.
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKEdgeLayout_BendPoints()
     * @model containment="true"
     * @generated
     */
	EList<KPoint> getBendPoints();

	/**
     * Returns the value of the '<em><b>Source Point</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The source point is the point at which the edge touches the source node
     * or source port. The coordinates of source points must obey the general
     * rules for edge coordinates defined above.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Source Point</em>' containment reference.
     * @see #setSourcePoint(KPoint)
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKEdgeLayout_SourcePoint()
     * @model containment="true" required="true"
     * @generated
     */
	KPoint getSourcePoint();

	/**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KEdgeLayout#getSourcePoint <em>Source Point</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Point</em>' containment reference.
     * @see #getSourcePoint()
     * @generated
     */
	void setSourcePoint(KPoint value);

	/**
     * Returns the value of the '<em><b>Target Point</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The target point is the point at which the edge touches the target node
     * or target port. The coordinates of target points must obey the general
     * rules for edge coordinates defined above.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Target Point</em>' containment reference.
     * @see #setTargetPoint(KPoint)
     * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKEdgeLayout_TargetPoint()
     * @model containment="true" required="true"
     * @generated
     */
	KPoint getTargetPoint();

	/**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KEdgeLayout#getTargetPoint <em>Target Point</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Point</em>' containment reference.
     * @see #getTargetPoint()
     * @generated
     */
	void setTargetPoint(KPoint value);

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Set the source point, bend points, and target point of this edge layout
     * from the given vector chain. The vector chain should contain at least
     * two points; the first point is taken as source point, while the last one is
     * taken as target point.
     * @param points the new points for this edge layout
     * <!-- end-model-doc -->
     * @model pointsDataType="de.cau.cs.kieler.klighd.kgraph.KVectorChain"
     * @generated
     */
	void applyVectorChain(KVectorChain points);

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Create a vector chain from the points of this edge layout. The resulting
     * vector chain contains at least two points; the first point is the source point,
     * while the last one is the target point.
     * @return the points of this edge layout
     * <!-- end-model-doc -->
     * @model dataType="de.cau.cs.kieler.klighd.kgraph.KVectorChain"
     * @generated
     */
	KVectorChain createVectorChain();

} // KEdgeLayout
