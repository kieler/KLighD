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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KShape Layout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This layout data contains information for graph elements for which rectangular
 * shape is assumed for layout, such as nodes, ports, and labels. Each graph element
 * has either a shape layout or an edge layout attached. The shape layout of nodes
 * has insets.
 * <p>
 * Layout coordinates for nodes, ports, and node labels are relative to the parent
 * node. The insets of the parent node are not included in the relative coordinates
 * of child nodes, but they are included in the relative coordinates of ports and
 * node labels. For edge labels the rules defined in {@link KEdgeLayout} apply.
 * Port labels are relative to their ports.
 * </p>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getXpos <em>Xpos</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getYpos <em>Ypos</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getWidth <em>Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getHeight <em>Height</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getInsets <em>Insets</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKShapeLayout()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface KShapeLayout extends KLayoutData {
	/**
	 * Returns the value of the '<em><b>Xpos</b></em>' attribute.
	 * The default value is <code>"0.0f"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xpos</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xpos</em>' attribute.
	 * @see #setXpos(float)
	 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKShapeLayout_Xpos()
	 * @model default="0.0f"
	 * @generated
	 */
	float getXpos();

	/**
	 * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getXpos <em>Xpos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xpos</em>' attribute.
	 * @see #getXpos()
	 * @generated
	 */
	void setXpos(float value);

	/**
	 * Returns the value of the '<em><b>Ypos</b></em>' attribute.
	 * The default value is <code>"0.0f"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ypos</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ypos</em>' attribute.
	 * @see #setYpos(float)
	 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKShapeLayout_Ypos()
	 * @model default="0.0f"
	 * @generated
	 */
	float getYpos();

	/**
	 * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getYpos <em>Ypos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ypos</em>' attribute.
	 * @see #getYpos()
	 * @generated
	 */
	void setYpos(float value);

	/**
	 * Returns the value of the '<em><b>Width</b></em>' attribute.
	 * The default value is <code>"0.0f"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(float)
	 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKShapeLayout_Width()
	 * @model default="0.0f"
	 * @generated
	 */
	float getWidth();

	/**
	 * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(float value);

	/**
	 * Returns the value of the '<em><b>Height</b></em>' attribute.
	 * The default value is <code>"0.0f"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Height</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Height</em>' attribute.
	 * @see #setHeight(float)
	 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKShapeLayout_Height()
	 * @model default="0.0f"
	 * @generated
	 */
	float getHeight();

	/**
	 * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getHeight <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
	void setHeight(float value);

	/**
	 * Returns the value of the '<em><b>Insets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Insets</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Insets</em>' containment reference.
	 * @see #setInsets(KInsets)
	 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#getKShapeLayout_Insets()
	 * @model containment="true"
	 * @generated
	 */
	KInsets getInsets();

	/**
	 * Sets the value of the '{@link de.cau.cs.kieler.klighd.kgraph.KShapeLayout#getInsets <em>Insets</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Insets</em>' containment reference.
	 * @see #getInsets()
	 * @generated
	 */
	void setInsets(KInsets value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set the position of this shape layout.
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
	 * Set the position of this shape layout by applying the given vector.
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
	 * Create a vector from the position of this shape layout.
	 * @return the position vector
	 * <!-- end-model-doc -->
	 * @model dataType="de.cau.cs.kieler.klighd.kgraph.KVector"
	 * @generated
	 */
	KVector createVector();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set the size of this shape layout.
	 * @param width the new width
	 * @param height the new height
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void setSize(float width, float height);

} // KShapeLayout
