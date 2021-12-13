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

import de.cau.cs.kieler.klighd.krendering.KCustomRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KCustom Rendering</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KCustomRenderingImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KCustomRenderingImpl#getBundleName <em>Bundle Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KCustomRenderingImpl#getFigureObject <em>Figure Object</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KCustomRenderingImpl extends KContainerRenderingImpl implements KCustomRendering {
    /**
     * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getClassName()
     * @generated
     * @ordered
     */
    protected static final String CLASS_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getClassName()
     * @generated
     * @ordered
     */
    protected String className = CLASS_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getBundleName() <em>Bundle Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBundleName()
     * @generated
     * @ordered
     */
    protected static final String BUNDLE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBundleName() <em>Bundle Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBundleName()
     * @generated
     * @ordered
     */
    protected String bundleName = BUNDLE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getFigureObject() <em>Figure Object</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFigureObject()
     * @generated
     * @ordered
     */
    protected static final Object FIGURE_OBJECT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFigureObject() <em>Figure Object</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFigureObject()
     * @generated
     * @ordered
     */
    protected Object figureObject = FIGURE_OBJECT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KCustomRenderingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KCUSTOM_RENDERING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getClassName() {
        return className;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setClassName(String newClassName) {
        String oldClassName = className;
        className = newClassName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KCUSTOM_RENDERING__CLASS_NAME, oldClassName, className));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getBundleName() {
        return bundleName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBundleName(String newBundleName) {
        String oldBundleName = bundleName;
        bundleName = newBundleName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KCUSTOM_RENDERING__BUNDLE_NAME, oldBundleName, bundleName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getFigureObject() {
        return figureObject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFigureObject(Object newFigureObject) {
        Object oldFigureObject = figureObject;
        figureObject = newFigureObject;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KCUSTOM_RENDERING__FIGURE_OBJECT, oldFigureObject, figureObject));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KCUSTOM_RENDERING__CLASS_NAME:
                return getClassName();
            case KRenderingPackage.KCUSTOM_RENDERING__BUNDLE_NAME:
                return getBundleName();
            case KRenderingPackage.KCUSTOM_RENDERING__FIGURE_OBJECT:
                return getFigureObject();
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
            case KRenderingPackage.KCUSTOM_RENDERING__CLASS_NAME:
                setClassName((String)newValue);
                return;
            case KRenderingPackage.KCUSTOM_RENDERING__BUNDLE_NAME:
                setBundleName((String)newValue);
                return;
            case KRenderingPackage.KCUSTOM_RENDERING__FIGURE_OBJECT:
                setFigureObject(newValue);
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
            case KRenderingPackage.KCUSTOM_RENDERING__CLASS_NAME:
                setClassName(CLASS_NAME_EDEFAULT);
                return;
            case KRenderingPackage.KCUSTOM_RENDERING__BUNDLE_NAME:
                setBundleName(BUNDLE_NAME_EDEFAULT);
                return;
            case KRenderingPackage.KCUSTOM_RENDERING__FIGURE_OBJECT:
                setFigureObject(FIGURE_OBJECT_EDEFAULT);
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
            case KRenderingPackage.KCUSTOM_RENDERING__CLASS_NAME:
                return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
            case KRenderingPackage.KCUSTOM_RENDERING__BUNDLE_NAME:
                return BUNDLE_NAME_EDEFAULT == null ? bundleName != null : !BUNDLE_NAME_EDEFAULT.equals(bundleName);
            case KRenderingPackage.KCUSTOM_RENDERING__FIGURE_OBJECT:
                return FIGURE_OBJECT_EDEFAULT == null ? figureObject != null : !FIGURE_OBJECT_EDEFAULT.equals(figureObject);
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
        result.append(" (className: ");
        result.append(className);
        result.append(", bundleName: ");
        result.append(bundleName);
        result.append(", figureObject: ");
        result.append(figureObject);
        result.append(')');
        return result.toString();
    }

} //KCustomRenderingImpl
