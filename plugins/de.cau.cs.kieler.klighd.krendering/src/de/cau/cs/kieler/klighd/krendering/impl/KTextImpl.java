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
package de.cau.cs.kieler.klighd.krendering.impl;

import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;
import de.cau.cs.kieler.klighd.krendering.KText;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KText</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KTextImpl#getText <em>Text</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KTextImpl#isCursorSelectable <em>Cursor Selectable</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KTextImpl#isEditable <em>Editable</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KTextImpl extends KRenderingImpl implements KText {
    /**
     * The default value of the '{@link #getText() <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getText()
     * @generated
     * @ordered
     */
    protected static final String TEXT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getText() <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getText()
     * @generated
     * @ordered
     */
    protected String text = TEXT_EDEFAULT;

    /**
     * The default value of the '{@link #isCursorSelectable() <em>Cursor Selectable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCursorSelectable()
     * @generated
     * @ordered
     */
    protected static final boolean CURSOR_SELECTABLE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isCursorSelectable() <em>Cursor Selectable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCursorSelectable()
     * @generated
     * @ordered
     */
    protected boolean cursorSelectable = CURSOR_SELECTABLE_EDEFAULT;

    /**
     * The default value of the '{@link #isEditable() <em>Editable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isEditable()
     * @generated
     * @ordered
     */
    protected static final boolean EDITABLE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isEditable() <em>Editable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isEditable()
     * @generated
     * @ordered
     */
    protected boolean editable = EDITABLE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KTextImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KTEXT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getText() {
        return text;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setText(String newText) {
        String oldText = text;
        text = newText;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KTEXT__TEXT, oldText, text));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isCursorSelectable() {
        return cursorSelectable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCursorSelectable(boolean newCursorSelectable) {
        boolean oldCursorSelectable = cursorSelectable;
        cursorSelectable = newCursorSelectable;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KTEXT__CURSOR_SELECTABLE, oldCursorSelectable, cursorSelectable));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEditable(boolean newEditable) {
        boolean oldEditable = editable;
        editable = newEditable;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KTEXT__EDITABLE, oldEditable, editable));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KTEXT__TEXT:
                return getText();
            case KRenderingPackage.KTEXT__CURSOR_SELECTABLE:
                return isCursorSelectable();
            case KRenderingPackage.KTEXT__EDITABLE:
                return isEditable();
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
            case KRenderingPackage.KTEXT__TEXT:
                setText((String)newValue);
                return;
            case KRenderingPackage.KTEXT__CURSOR_SELECTABLE:
                setCursorSelectable((Boolean)newValue);
                return;
            case KRenderingPackage.KTEXT__EDITABLE:
                setEditable((Boolean)newValue);
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
            case KRenderingPackage.KTEXT__TEXT:
                setText(TEXT_EDEFAULT);
                return;
            case KRenderingPackage.KTEXT__CURSOR_SELECTABLE:
                setCursorSelectable(CURSOR_SELECTABLE_EDEFAULT);
                return;
            case KRenderingPackage.KTEXT__EDITABLE:
                setEditable(EDITABLE_EDEFAULT);
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
            case KRenderingPackage.KTEXT__TEXT:
                return TEXT_EDEFAULT == null ? text != null : !TEXT_EDEFAULT.equals(text);
            case KRenderingPackage.KTEXT__CURSOR_SELECTABLE:
                return cursorSelectable != CURSOR_SELECTABLE_EDEFAULT;
            case KRenderingPackage.KTEXT__EDITABLE:
                return editable != EDITABLE_EDEFAULT;
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
        result.append(" (text: ");
        result.append(text);
        result.append(", cursorSelectable: ");
        result.append(cursorSelectable);
        result.append(", editable: ");
        result.append(editable);
        result.append(')');
        return result.toString();
    }

} //KTextImpl
