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

import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;
import de.cau.cs.kieler.klighd.krendering.KStyle;
import de.cau.cs.kieler.klighd.krendering.KStyleHolder;
import de.cau.cs.kieler.klighd.krendering.KStyleRef;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KStyle Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KStyleRefImpl#getStyleHolder <em>Style Holder</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KStyleRefImpl#getReferencedTypes <em>Referenced Types</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KStyleRefImpl extends KStyleImpl implements KStyleRef {
    /**
     * The cached value of the '{@link #getStyleHolder() <em>Style Holder</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStyleHolder()
     * @generated
     * @ordered
     */
    protected KStyleHolder styleHolder;

    /**
     * The cached value of the '{@link #getReferencedTypes() <em>Referenced Types</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReferencedTypes()
     * @generated
     * @ordered
     */
    protected EList<Class<KStyle>> referencedTypes;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KStyleRefImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KSTYLE_REF;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KStyleHolder getStyleHolder() {
        if (styleHolder != null && styleHolder.eIsProxy()) {
            InternalEObject oldStyleHolder = (InternalEObject)styleHolder;
            styleHolder = (KStyleHolder)eResolveProxy(oldStyleHolder);
            if (styleHolder != oldStyleHolder) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, KRenderingPackage.KSTYLE_REF__STYLE_HOLDER, oldStyleHolder, styleHolder));
            }
        }
        return styleHolder;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KStyleHolder basicGetStyleHolder() {
        return styleHolder;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStyleHolder(KStyleHolder newStyleHolder) {
        KStyleHolder oldStyleHolder = styleHolder;
        styleHolder = newStyleHolder;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KSTYLE_REF__STYLE_HOLDER, oldStyleHolder, styleHolder));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Class<KStyle>> getReferencedTypes() {
        if (referencedTypes == null) {
            referencedTypes = new EDataTypeUniqueEList<Class<KStyle>>(Class.class, this, KRenderingPackage.KSTYLE_REF__REFERENCED_TYPES);
        }
        return referencedTypes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KSTYLE_REF__STYLE_HOLDER:
                if (resolve) return getStyleHolder();
                return basicGetStyleHolder();
            case KRenderingPackage.KSTYLE_REF__REFERENCED_TYPES:
                return getReferencedTypes();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case KRenderingPackage.KSTYLE_REF__STYLE_HOLDER:
                setStyleHolder((KStyleHolder)newValue);
                return;
            case KRenderingPackage.KSTYLE_REF__REFERENCED_TYPES:
                getReferencedTypes().clear();
                getReferencedTypes().addAll((Collection<? extends Class<KStyle>>)newValue);
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
            case KRenderingPackage.KSTYLE_REF__STYLE_HOLDER:
                setStyleHolder((KStyleHolder)null);
                return;
            case KRenderingPackage.KSTYLE_REF__REFERENCED_TYPES:
                getReferencedTypes().clear();
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
            case KRenderingPackage.KSTYLE_REF__STYLE_HOLDER:
                return styleHolder != null;
            case KRenderingPackage.KSTYLE_REF__REFERENCED_TYPES:
                return referencedTypes != null && !referencedTypes.isEmpty();
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
        result.append(" (referencedTypes: ");
        result.append(referencedTypes);
        result.append(')');
        return result.toString();
    }

} //KStyleRefImpl
