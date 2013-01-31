/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.krendering;

import de.cau.cs.kieler.core.kgraph.KGraphData;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KRendering</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Abstract class to define members of a shapeType.
 * Is placed inside a parent Container
 * can reference another Rendering instead of redefining it
 * A rendering can contain placementData to define the size of a derived object
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KRendering#getParent <em>Parent</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KRendering#getReferences <em>References</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KRendering#getPlacementData <em>Placement Data</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KRendering#getActions <em>Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKRendering()
 * @model abstract="true"
 * @generated
 */
public interface KRendering extends KGraphData, KStyleHolder {
    /**
     * Returns the value of the '<em><b>Parent</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.core.krendering.KContainerRendering#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the rendering containing this rendering
     * <!-- end-model-doc -->
     * @return the value of the '<em>Parent</em>' container reference.
     * @see #setParent(KContainerRendering)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKRendering_Parent()
     * @see de.cau.cs.kieler.core.krendering.KContainerRendering#getChildren
     * @model opposite="children" transient="false"
     * @generated
     */
    KContainerRendering getParent();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KRendering#getParent <em>Parent</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent</em>' container reference.
     * @see #getParent()
     * @generated
     */
    void setParent(KContainerRendering value);

    /**
     * Returns the value of the '<em><b>References</b></em>' reference list.
     * The list contents are of type {@link de.cau.cs.kieler.core.krendering.KRenderingRef}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>References</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * reference an existing KRendering ref instead of re-defining everything
     * <!-- end-model-doc -->
     * @return the value of the '<em>References</em>' reference list.
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKRendering_References()
     * @model
     * @generated
     */
    EList<KRenderingRef> getReferences();

    /**
     * Returns the value of the '<em><b>Placement Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Placement Data</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Information where and how this Rendering shall be placed.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Placement Data</em>' containment reference.
     * @see #setPlacementData(KPlacementData)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKRendering_PlacementData()
     * @model containment="true"
     * @generated
     */
    KPlacementData getPlacementData();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KRendering#getPlacementData <em>Placement Data</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Placement Data</em>' containment reference.
     * @see #getPlacementData()
     * @generated
     */
    void setPlacementData(KPlacementData value);

    /**
     * Returns the value of the '<em><b>Actions</b></em>' reference list.
     * The list contents are of type {@link de.cau.cs.kieler.core.krendering.KAction}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * actions that should be performed when 
     * <!-- end-model-doc -->
     * @return the value of the '<em>Actions</em>' reference list.
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKRendering_Actions()
     * @model
     * @generated
     */
    EList<KAction> getActions();

} // KRendering
