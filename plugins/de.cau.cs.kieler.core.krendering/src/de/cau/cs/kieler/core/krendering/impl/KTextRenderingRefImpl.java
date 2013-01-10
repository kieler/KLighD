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
package de.cau.cs.kieler.core.krendering.impl;

import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.core.krendering.KTextRenderingRef;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KText Rendering Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KTextRenderingRefImpl#getTextRef <em>Text Ref</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KTextRenderingRefImpl#getOverrideText <em>Override Text</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KTextRenderingRefImpl extends KContainerRenderingImpl implements KTextRenderingRef {
    /**
     * The cached value of the '{@link #getTextRef() <em>Text Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTextRef()
     * @generated
     * @ordered
     */
    protected KText textRef;

    /**
     * The default value of the '{@link #getOverrideText() <em>Override Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOverrideText()
     * @generated
     * @ordered
     */
    protected static final String OVERRIDE_TEXT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOverrideText() <em>Override Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOverrideText()
     * @generated
     * @ordered
     */
    protected String overrideText = OVERRIDE_TEXT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KTextRenderingRefImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KTEXT_RENDERING_REF;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KText getTextRef() {
        if (textRef != null && textRef.eIsProxy()) {
            InternalEObject oldTextRef = (InternalEObject)textRef;
            textRef = (KText)eResolveProxy(oldTextRef);
            if (textRef != oldTextRef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, KRenderingPackage.KTEXT_RENDERING_REF__TEXT_REF, oldTextRef, textRef));
            }
        }
        return textRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KText basicGetTextRef() {
        return textRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTextRef(KText newTextRef) {
        KText oldTextRef = textRef;
        textRef = newTextRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KTEXT_RENDERING_REF__TEXT_REF, oldTextRef, textRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getOverrideText() {
        return overrideText;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOverrideText(String newOverrideText) {
        String oldOverrideText = overrideText;
        overrideText = newOverrideText;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KTEXT_RENDERING_REF__OVERRIDE_TEXT, oldOverrideText, overrideText));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KTEXT_RENDERING_REF__TEXT_REF:
                if (resolve) return getTextRef();
                return basicGetTextRef();
            case KRenderingPackage.KTEXT_RENDERING_REF__OVERRIDE_TEXT:
                return getOverrideText();
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
            case KRenderingPackage.KTEXT_RENDERING_REF__TEXT_REF:
                setTextRef((KText)newValue);
                return;
            case KRenderingPackage.KTEXT_RENDERING_REF__OVERRIDE_TEXT:
                setOverrideText((String)newValue);
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
            case KRenderingPackage.KTEXT_RENDERING_REF__TEXT_REF:
                setTextRef((KText)null);
                return;
            case KRenderingPackage.KTEXT_RENDERING_REF__OVERRIDE_TEXT:
                setOverrideText(OVERRIDE_TEXT_EDEFAULT);
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
            case KRenderingPackage.KTEXT_RENDERING_REF__TEXT_REF:
                return textRef != null;
            case KRenderingPackage.KTEXT_RENDERING_REF__OVERRIDE_TEXT:
                return OVERRIDE_TEXT_EDEFAULT == null ? overrideText != null : !OVERRIDE_TEXT_EDEFAULT.equals(overrideText);
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

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (overrideText: ");
        result.append(overrideText);
        result.append(')');
        return result.toString();
    }

} //KTextRenderingRefImpl
