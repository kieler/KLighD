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

import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KTextStrikeout;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KText Strikeout</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KTextStrikeoutImpl#getStruckOut <em>Struck Out</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KTextStrikeoutImpl#getColor <em>Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KTextStrikeoutImpl extends KStyleImpl implements KTextStrikeout {
    /**
     * The default value of the '{@link #getStruckOut() <em>Struck Out</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStruckOut()
     * @generated
     * @ordered
     */
    protected static final Boolean STRUCK_OUT_EDEFAULT = Boolean.TRUE;

    /**
     * The cached value of the '{@link #getStruckOut() <em>Struck Out</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStruckOut()
     * @generated
     * @ordered
     */
    protected Boolean struckOut = STRUCK_OUT_EDEFAULT;

    /**
     * The cached value of the '{@link #getColor() <em>Color</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected KColor color;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KTextStrikeoutImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KTEXT_STRIKEOUT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Boolean getStruckOut() {
        return struckOut;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStruckOut(Boolean newStruckOut) {
        Boolean oldStruckOut = struckOut;
        struckOut = newStruckOut;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KTEXT_STRIKEOUT__STRUCK_OUT, oldStruckOut, struckOut));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KColor getColor() {
        return color;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetColor(KColor newColor, NotificationChain msgs) {
        KColor oldColor = color;
        color = newColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KRenderingPackage.KTEXT_STRIKEOUT__COLOR, oldColor, newColor);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setColor(KColor newColor) {
        if (newColor != color) {
            NotificationChain msgs = null;
            if (color != null)
                msgs = ((InternalEObject)color).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KTEXT_STRIKEOUT__COLOR, null, msgs);
            if (newColor != null)
                msgs = ((InternalEObject)newColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KTEXT_STRIKEOUT__COLOR, null, msgs);
            msgs = basicSetColor(newColor, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KTEXT_STRIKEOUT__COLOR, newColor, newColor));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KTEXT_STRIKEOUT__COLOR:
                return basicSetColor(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KTEXT_STRIKEOUT__STRUCK_OUT:
                return getStruckOut();
            case KRenderingPackage.KTEXT_STRIKEOUT__COLOR:
                return getColor();
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
            case KRenderingPackage.KTEXT_STRIKEOUT__STRUCK_OUT:
                setStruckOut((Boolean)newValue);
                return;
            case KRenderingPackage.KTEXT_STRIKEOUT__COLOR:
                setColor((KColor)newValue);
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
            case KRenderingPackage.KTEXT_STRIKEOUT__STRUCK_OUT:
                setStruckOut(STRUCK_OUT_EDEFAULT);
                return;
            case KRenderingPackage.KTEXT_STRIKEOUT__COLOR:
                setColor((KColor)null);
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
            case KRenderingPackage.KTEXT_STRIKEOUT__STRUCK_OUT:
                return STRUCK_OUT_EDEFAULT == null ? struckOut != null : !STRUCK_OUT_EDEFAULT.equals(struckOut);
            case KRenderingPackage.KTEXT_STRIKEOUT__COLOR:
                return color != null;
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
        result.append(" (struckOut: ");
        result.append(struckOut);
        result.append(')');
        return result.toString();
    }

} //KTextStrikeoutImpl
