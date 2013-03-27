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
 * A representation of the model object '<em><b>KAction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * performs action <id> on <trigger> event
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KAction#getId <em>Id</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KAction#getTrigger <em>Trigger</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKAction()
 * @model
 * @generated
 */
public interface KAction extends EObject {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * id of the action to happen when <trigger> occurs
     * <!-- end-model-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKAction_Id()
     * @model required="true"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KAction#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Trigger</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.core.krendering.Trigger}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * trigger that activates action
     * <!-- end-model-doc -->
     * @return the value of the '<em>Trigger</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.Trigger
     * @see #setTrigger(Trigger)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKAction_Trigger()
     * @model required="true"
     * @generated
     */
    Trigger getTrigger();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KAction#getTrigger <em>Trigger</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Trigger</em>' attribute.
     * @see de.cau.cs.kieler.core.krendering.Trigger
     * @see #getTrigger()
     * @generated
     */
    void setTrigger(Trigger value);

} // KAction
