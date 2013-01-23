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

import de.cau.cs.kieler.core.krendering.KFontUnderlined;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.UnderlineStyle;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KFont Underlined</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KFontUnderlinedImpl#getUnderlineStyle <em>Underline Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KFontUnderlinedImpl extends KStyleImpl implements KFontUnderlined {
    /**
     * The default value of the '{@link #getUnderlineStyle() <em>Underline Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUnderlineStyle()
     * @generated
     * @ordered
     */
    protected static final UnderlineStyle UNDERLINE_STYLE_EDEFAULT = UnderlineStyle.UNDERLINE_ON;

    /**
     * The cached value of the '{@link #getUnderlineStyle() <em>Underline Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUnderlineStyle()
     * @generated
     * @ordered
     */
    protected UnderlineStyle underlineStyle = UNDERLINE_STYLE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KFontUnderlinedImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KFONT_UNDERLINED;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UnderlineStyle getUnderlineStyle() {
        return underlineStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUnderlineStyle(UnderlineStyle newUnderlineStyle) {
        UnderlineStyle oldUnderlineStyle = underlineStyle;
        underlineStyle = newUnderlineStyle == null ? UNDERLINE_STYLE_EDEFAULT : newUnderlineStyle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KFONT_UNDERLINED__UNDERLINE_STYLE, oldUnderlineStyle, underlineStyle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KFONT_UNDERLINED__UNDERLINE_STYLE:
                return getUnderlineStyle();
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
            case KRenderingPackage.KFONT_UNDERLINED__UNDERLINE_STYLE:
                setUnderlineStyle((UnderlineStyle)newValue);
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
            case KRenderingPackage.KFONT_UNDERLINED__UNDERLINE_STYLE:
                setUnderlineStyle(UNDERLINE_STYLE_EDEFAULT);
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
            case KRenderingPackage.KFONT_UNDERLINED__UNDERLINE_STYLE:
                return underlineStyle != UNDERLINE_STYLE_EDEFAULT;
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
        result.append(" (underlineStyle: ");
        result.append(underlineStyle);
        result.append(')');
        return result.toString();
    }

} //KFontUnderlinedImpl
