/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package krendering.impl;

import de.cau.cs.kieler.core.kgraph.impl.KGraphDataImpl;

import java.util.Collection;

import krendering.KRendering;
import krendering.KRenderingLibrary;
import krendering.KRenderingPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link krendering.impl.KRenderingLibraryImpl#getRenderings <em>Renderings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KRenderingLibraryImpl extends KGraphDataImpl implements KRenderingLibrary {
    /**
     * The cached value of the '{@link #getRenderings() <em>Renderings</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRenderings()
     * @generated
     * @ordered
     */
    protected EList<KRendering> renderings;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KRenderingLibraryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KRENDERING_LIBRARY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<KRendering> getRenderings() {
        if (renderings == null) {
            renderings = new EObjectContainmentEList<KRendering>(KRendering.class, this, KRenderingPackage.KRENDERING_LIBRARY__RENDERINGS);
        }
        return renderings;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KRENDERING_LIBRARY__RENDERINGS:
                return ((InternalEList<?>)getRenderings()).basicRemove(otherEnd, msgs);
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
            case KRenderingPackage.KRENDERING_LIBRARY__RENDERINGS:
                return getRenderings();
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
            case KRenderingPackage.KRENDERING_LIBRARY__RENDERINGS:
                getRenderings().clear();
                getRenderings().addAll((Collection<? extends KRendering>)newValue);
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
            case KRenderingPackage.KRENDERING_LIBRARY__RENDERINGS:
                getRenderings().clear();
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
            case KRenderingPackage.KRENDERING_LIBRARY__RENDERINGS:
                return renderings != null && !renderings.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //KRenderingLibraryImpl
