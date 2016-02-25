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
package de.cau.cs.kieler.core.krendering.impl;

import de.cau.cs.kieler.core.krendering.KColor;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KTextUnderline;
import de.cau.cs.kieler.core.krendering.Underline;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KText Underline</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KTextUnderlineImpl#getUnderline <em>Underline</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KTextUnderlineImpl#getColor <em>Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KTextUnderlineImpl extends KStyleImpl implements KTextUnderline {
    /**
     * The default value of the '{@link #getUnderline() <em>Underline</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUnderline()
     * @generated
     * @ordered
     */
    protected static final Underline UNDERLINE_EDEFAULT = Underline.NONE;

    /**
     * The cached value of the '{@link #getUnderline() <em>Underline</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUnderline()
     * @generated
     * @ordered
     */
    protected Underline underline = UNDERLINE_EDEFAULT;

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
    protected KTextUnderlineImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KTEXT_UNDERLINE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Underline getUnderline() {
        return underline;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUnderline(Underline newUnderline) {
        Underline oldUnderline = underline;
        underline = newUnderline == null ? UNDERLINE_EDEFAULT : newUnderline;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KTEXT_UNDERLINE__UNDERLINE, oldUnderline, underline));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KRenderingPackage.KTEXT_UNDERLINE__COLOR, oldColor, newColor);
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
                msgs = ((InternalEObject)color).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KTEXT_UNDERLINE__COLOR, null, msgs);
            if (newColor != null)
                msgs = ((InternalEObject)newColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KTEXT_UNDERLINE__COLOR, null, msgs);
            msgs = basicSetColor(newColor, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KTEXT_UNDERLINE__COLOR, newColor, newColor));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KTEXT_UNDERLINE__COLOR:
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
            case KRenderingPackage.KTEXT_UNDERLINE__UNDERLINE:
                return getUnderline();
            case KRenderingPackage.KTEXT_UNDERLINE__COLOR:
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
            case KRenderingPackage.KTEXT_UNDERLINE__UNDERLINE:
                setUnderline((Underline)newValue);
                return;
            case KRenderingPackage.KTEXT_UNDERLINE__COLOR:
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
            case KRenderingPackage.KTEXT_UNDERLINE__UNDERLINE:
                setUnderline(UNDERLINE_EDEFAULT);
                return;
            case KRenderingPackage.KTEXT_UNDERLINE__COLOR:
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
            case KRenderingPackage.KTEXT_UNDERLINE__UNDERLINE:
                return underline != UNDERLINE_EDEFAULT;
            case KRenderingPackage.KTEXT_UNDERLINE__COLOR:
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
        result.append(" (underline: ");
        result.append(underline);
        result.append(')');
        return result.toString();
    }

} //KTextUnderlineImpl
