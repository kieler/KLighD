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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KStyle</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KStyle#getRendering <em>Rendering</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KStyle#isPropagateToChildren <em>Propagate To Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyle()
 * @model
 * @generated
 */
public interface KStyle extends EObject {
    /**
     * Returns the value of the '<em><b>Rendering</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link de.cau.cs.kieler.core.krendering.KRendering#getStyles <em>Styles</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Rendering</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Rendering</em>' container reference.
     * @see #setRendering(KRendering)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyle_Rendering()
     * @see de.cau.cs.kieler.core.krendering.KRendering#getStyles
     * @model opposite="styles" transient="false"
     * @generated
     */
    KRendering getRendering();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KStyle#getRendering <em>Rendering</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Rendering</em>' container reference.
     * @see #getRendering()
     * @generated
     */
    void setRendering(KRendering value);

    /**
     * Returns the value of the '<em><b>Propagate To Children</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Propagate To Children</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Propagate To Children</em>' attribute.
     * @see #setPropagateToChildren(boolean)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKStyle_PropagateToChildren()
     * @model required="true"
     * @generated
     */
    boolean isPropagateToChildren();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KStyle#isPropagateToChildren <em>Propagate To Children</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Propagate To Children</em>' attribute.
     * @see #isPropagateToChildren()
     * @generated
     */
    void setPropagateToChildren(boolean value);

} // KStyle
