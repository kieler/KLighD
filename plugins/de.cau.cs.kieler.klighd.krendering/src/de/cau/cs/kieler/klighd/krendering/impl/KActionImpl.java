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
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.krendering.impl;

import de.cau.cs.kieler.klighd.krendering.KAction;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;
import de.cau.cs.kieler.klighd.krendering.ModifierState;
import de.cau.cs.kieler.klighd.krendering.Trigger;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KAction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KActionImpl#getActionId <em>Action Id</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KActionImpl#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KActionImpl#getAltPressed <em>Alt Pressed</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KActionImpl#getCtrlCmdPressed <em>Ctrl Cmd Pressed</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KActionImpl#getShiftPressed <em>Shift Pressed</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KActionImpl extends EObjectImpl implements KAction {
    /**
     * The default value of the '{@link #getActionId() <em>Action Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getActionId()
     * @generated
     * @ordered
     */
    protected static final String ACTION_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getActionId() <em>Action Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getActionId()
     * @generated
     * @ordered
     */
    protected String actionId = ACTION_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getTrigger() <em>Trigger</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTrigger()
     * @generated
     * @ordered
     */
    protected static final Trigger TRIGGER_EDEFAULT = Trigger.SINGLECLICK;

    /**
     * The cached value of the '{@link #getTrigger() <em>Trigger</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTrigger()
     * @generated
     * @ordered
     */
    protected Trigger trigger = TRIGGER_EDEFAULT;

    /**
     * The default value of the '{@link #getAltPressed() <em>Alt Pressed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAltPressed()
     * @generated
     * @ordered
     */
    protected static final ModifierState ALT_PRESSED_EDEFAULT = ModifierState.DONT_CARE;

    /**
     * The cached value of the '{@link #getAltPressed() <em>Alt Pressed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAltPressed()
     * @generated
     * @ordered
     */
    protected ModifierState altPressed = ALT_PRESSED_EDEFAULT;

    /**
     * The default value of the '{@link #getCtrlCmdPressed() <em>Ctrl Cmd Pressed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCtrlCmdPressed()
     * @generated
     * @ordered
     */
    protected static final ModifierState CTRL_CMD_PRESSED_EDEFAULT = ModifierState.DONT_CARE;

    /**
     * The cached value of the '{@link #getCtrlCmdPressed() <em>Ctrl Cmd Pressed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCtrlCmdPressed()
     * @generated
     * @ordered
     */
    protected ModifierState ctrlCmdPressed = CTRL_CMD_PRESSED_EDEFAULT;

    /**
     * The default value of the '{@link #getShiftPressed() <em>Shift Pressed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getShiftPressed()
     * @generated
     * @ordered
     */
    protected static final ModifierState SHIFT_PRESSED_EDEFAULT = ModifierState.DONT_CARE;

    /**
     * The cached value of the '{@link #getShiftPressed() <em>Shift Pressed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getShiftPressed()
     * @generated
     * @ordered
     */
    protected ModifierState shiftPressed = SHIFT_PRESSED_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KActionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KACTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getActionId() {
        return actionId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setActionId(String newActionId) {
        String oldActionId = actionId;
        actionId = newActionId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KACTION__ACTION_ID, oldActionId, actionId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Trigger getTrigger() {
        return trigger;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTrigger(Trigger newTrigger) {
        Trigger oldTrigger = trigger;
        trigger = newTrigger == null ? TRIGGER_EDEFAULT : newTrigger;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KACTION__TRIGGER, oldTrigger, trigger));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModifierState getAltPressed() {
        return altPressed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAltPressed(ModifierState newAltPressed) {
        ModifierState oldAltPressed = altPressed;
        altPressed = newAltPressed == null ? ALT_PRESSED_EDEFAULT : newAltPressed;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KACTION__ALT_PRESSED, oldAltPressed, altPressed));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModifierState getCtrlCmdPressed() {
        return ctrlCmdPressed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCtrlCmdPressed(ModifierState newCtrlCmdPressed) {
        ModifierState oldCtrlCmdPressed = ctrlCmdPressed;
        ctrlCmdPressed = newCtrlCmdPressed == null ? CTRL_CMD_PRESSED_EDEFAULT : newCtrlCmdPressed;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KACTION__CTRL_CMD_PRESSED, oldCtrlCmdPressed, ctrlCmdPressed));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModifierState getShiftPressed() {
        return shiftPressed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setShiftPressed(ModifierState newShiftPressed) {
        ModifierState oldShiftPressed = shiftPressed;
        shiftPressed = newShiftPressed == null ? SHIFT_PRESSED_EDEFAULT : newShiftPressed;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KACTION__SHIFT_PRESSED, oldShiftPressed, shiftPressed));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KACTION__ACTION_ID:
                return getActionId();
            case KRenderingPackage.KACTION__TRIGGER:
                return getTrigger();
            case KRenderingPackage.KACTION__ALT_PRESSED:
                return getAltPressed();
            case KRenderingPackage.KACTION__CTRL_CMD_PRESSED:
                return getCtrlCmdPressed();
            case KRenderingPackage.KACTION__SHIFT_PRESSED:
                return getShiftPressed();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case KRenderingPackage.KACTION__ACTION_ID:
                setActionId((String)newValue);
                return;
            case KRenderingPackage.KACTION__TRIGGER:
                setTrigger((Trigger)newValue);
                return;
            case KRenderingPackage.KACTION__ALT_PRESSED:
                setAltPressed((ModifierState)newValue);
                return;
            case KRenderingPackage.KACTION__CTRL_CMD_PRESSED:
                setCtrlCmdPressed((ModifierState)newValue);
                return;
            case KRenderingPackage.KACTION__SHIFT_PRESSED:
                setShiftPressed((ModifierState)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case KRenderingPackage.KACTION__ACTION_ID:
                setActionId(ACTION_ID_EDEFAULT);
                return;
            case KRenderingPackage.KACTION__TRIGGER:
                setTrigger(TRIGGER_EDEFAULT);
                return;
            case KRenderingPackage.KACTION__ALT_PRESSED:
                setAltPressed(ALT_PRESSED_EDEFAULT);
                return;
            case KRenderingPackage.KACTION__CTRL_CMD_PRESSED:
                setCtrlCmdPressed(CTRL_CMD_PRESSED_EDEFAULT);
                return;
            case KRenderingPackage.KACTION__SHIFT_PRESSED:
                setShiftPressed(SHIFT_PRESSED_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case KRenderingPackage.KACTION__ACTION_ID:
                return ACTION_ID_EDEFAULT == null ? actionId != null : !ACTION_ID_EDEFAULT.equals(actionId);
            case KRenderingPackage.KACTION__TRIGGER:
                return trigger != TRIGGER_EDEFAULT;
            case KRenderingPackage.KACTION__ALT_PRESSED:
                return altPressed != ALT_PRESSED_EDEFAULT;
            case KRenderingPackage.KACTION__CTRL_CMD_PRESSED:
                return ctrlCmdPressed != CTRL_CMD_PRESSED_EDEFAULT;
            case KRenderingPackage.KACTION__SHIFT_PRESSED:
                return shiftPressed != SHIFT_PRESSED_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (actionId: ");
        result.append(actionId);
        result.append(", trigger: ");
        result.append(trigger);
        result.append(", altPressed: ");
        result.append(altPressed);
        result.append(", ctrlCmdPressed: ");
        result.append(ctrlCmdPressed);
        result.append(", shiftPressed: ");
        result.append(shiftPressed);
        result.append(')');
        return result.toString();
    }

} //KActionImpl
