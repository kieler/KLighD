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
 */
package de.cau.cs.kieler.core.kgraph;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KLabeled Graph Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Labeled graph elements contain an arbitrary number of labels.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.kgraph.KLabeledGraphElement#getLabels <em>Labels</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.kgraph.KGraphPackage#getKLabeledGraphElement()
 * @model abstract="true"
 * @generated
 */
public interface KLabeledGraphElement extends KGraphElement {
    /**
     * Returns the value of the '<em><b>Labels</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.core.kgraph.KLabel}.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.core.kgraph.KLabel#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * An edge may have multiple labels.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Labels</em>' containment reference list.
     * @see de.cau.cs.kieler.core.kgraph.KGraphPackage#getKLabeledGraphElement_Labels()
     * @see de.cau.cs.kieler.core.kgraph.KLabel#getParent
     * @model opposite="parent" containment="true"
     * @generated
     */
    EList<KLabel> getLabels();

} // KLabeledGraphElement
