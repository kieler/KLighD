/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
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
package de.cau.cs.kieler.klighd.krendering;

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
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KAction#getActionId <em>Action Id</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KAction#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KAction#getAltPressed <em>Alt Pressed</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KAction#getCtrlCmdPressed <em>Ctrl Cmd Pressed</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.KAction#getShiftPressed <em>Shift Pressed</em>}</li>
 * </ul>
 *
 * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKAction()
 * @model
 * @generated
 */
public interface KAction extends EObject {
    /**
     * Returns the value of the '<em><b>Action Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * id of the action implemenation to be executed when <trigger> occurs; in KLighD such action implementation must be registered via an extension point
     * <!-- end-model-doc -->
     * @return the value of the '<em>Action Id</em>' attribute.
     * @see #setActionId(String)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKAction_ActionId()
     * @model required="true"
     * @generated
     */
    String getActionId();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KAction#getActionId <em>Action Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Action Id</em>' attribute.
     * @see #getActionId()
     * @generated
     */
    void setActionId(String value);

    /**
     * Returns the value of the '<em><b>Trigger</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.klighd.krendering.Trigger}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * trigger that fires the associated action implementation
     * <!-- end-model-doc -->
     * @return the value of the '<em>Trigger</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.Trigger
     * @see #setTrigger(Trigger)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKAction_Trigger()
     * @model required="true"
     * @generated
     */
    Trigger getTrigger();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KAction#getTrigger <em>Trigger</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Trigger</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.Trigger
     * @see #getTrigger()
     * @generated
     */
    void setTrigger(Trigger value);

    /**
     * Returns the value of the '<em><b>Alt Pressed</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.klighd.krendering.ModifierState}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The 'alt' key must, must not or is indifferent to be pressed to let the associated action be executed.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Alt Pressed</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.ModifierState
     * @see #setAltPressed(ModifierState)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKAction_AltPressed()
     * @model
     * @generated
     */
    ModifierState getAltPressed();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KAction#getAltPressed <em>Alt Pressed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Alt Pressed</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.ModifierState
     * @see #getAltPressed()
     * @generated
     */
    void setAltPressed(ModifierState value);

    /**
     * Returns the value of the '<em><b>Ctrl Cmd Pressed</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.klighd.krendering.ModifierState}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The 'ctrl' key, on OSX the 'cmd' key must, must not or is indifferent to be pressed to let the associated action be executed.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Ctrl Cmd Pressed</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.ModifierState
     * @see #setCtrlCmdPressed(ModifierState)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKAction_CtrlCmdPressed()
     * @model
     * @generated
     */
    ModifierState getCtrlCmdPressed();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KAction#getCtrlCmdPressed <em>Ctrl Cmd Pressed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ctrl Cmd Pressed</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.ModifierState
     * @see #getCtrlCmdPressed()
     * @generated
     */
    void setCtrlCmdPressed(ModifierState value);

    /**
     * Returns the value of the '<em><b>Shift Pressed</b></em>' attribute.
     * The literals are from the enumeration {@link de.cau.cs.kieler.klighd.krendering.ModifierState}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The 'shift' key must, must not or is indifferent to be pressed to let the associated action be executed.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Shift Pressed</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.ModifierState
     * @see #setShiftPressed(ModifierState)
     * @see de.cau.cs.kieler.klighd.krendering.KRenderingPackage#getKAction_ShiftPressed()
     * @model
     * @generated
     */
    ModifierState getShiftPressed();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.klighd.krendering.KAction#getShiftPressed <em>Shift Pressed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Shift Pressed</em>' attribute.
     * @see de.cau.cs.kieler.klighd.krendering.ModifierState
     * @see #getShiftPressed()
     * @generated
     */
    void setShiftPressed(ModifierState value);

} // KAction
