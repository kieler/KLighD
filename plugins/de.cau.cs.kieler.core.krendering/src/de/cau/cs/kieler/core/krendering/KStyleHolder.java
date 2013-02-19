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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KStyle Holder</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Element to define styles without attaching them to a specific rendering
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KStyleHolder#getStyles <em>Styles</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyleHolder()
 * @model
 * @generated
 */
public interface KStyleHolder extends EObject {
    /**
     * Returns the value of the '<em><b>Styles</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.core.krendering.KStyle}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Styles</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Styles</em>' containment reference list.
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyleHolder_Styles()
     * @model containment="true"
     * @generated
     */
    EList<KStyle> getStyles();

} // KStyleHolder
