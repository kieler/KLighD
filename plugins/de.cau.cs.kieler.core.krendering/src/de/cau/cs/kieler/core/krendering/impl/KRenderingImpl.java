/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.core.krendering.impl;

import de.cau.cs.kieler.core.kgraph.impl.KGraphDataImpl;

import de.cau.cs.kieler.core.krendering.KContainerRendering;
import de.cau.cs.kieler.core.krendering.KPlacementData;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KStyle;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KRendering</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KRenderingImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KRenderingImpl#getReferences <em>References</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KRenderingImpl#getPlacementData <em>Placement Data</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KRenderingImpl#getStyles <em>Styles</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class KRenderingImpl extends KGraphDataImpl implements KRendering {
    /**
     * The cached value of the '{@link #getReferences() <em>References</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReferences()
     * @generated
     * @ordered
     */
    protected EList<KRenderingRef> references;

    /**
     * The cached value of the '{@link #getPlacementData() <em>Placement Data</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPlacementData()
     * @generated
     * @ordered
     */
    protected KPlacementData placementData;

    /**
     * The cached value of the '{@link #getStyles() <em>Styles</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStyles()
     * @generated
     * @ordered
     */
    protected EList<KStyle> styles;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KRenderingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KRENDERING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KContainerRendering getParent() {
        if (eContainerFeatureID() != KRenderingPackage.KRENDERING__PARENT) return null;
        return (KContainerRendering)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParent(KContainerRendering newParent, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newParent, KRenderingPackage.KRENDERING__PARENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParent(KContainerRendering newParent) {
        if (newParent != eInternalContainer() || (eContainerFeatureID() != KRenderingPackage.KRENDERING__PARENT && newParent != null)) {
            if (EcoreUtil.isAncestor(this, newParent))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newParent != null)
                msgs = ((InternalEObject)newParent).eInverseAdd(this, KRenderingPackage.KCONTAINER_RENDERING__CHILDREN, KContainerRendering.class, msgs);
            msgs = basicSetParent(newParent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KRENDERING__PARENT, newParent, newParent));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<KRenderingRef> getReferences() {
        if (references == null) {
            references = new EObjectWithInverseResolvingEList<KRenderingRef>(KRenderingRef.class, this, KRenderingPackage.KRENDERING__REFERENCES, KRenderingPackage.KRENDERING_REF__RENDERING);
        }
        return references;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPlacementData getPlacementData() {
        return placementData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPlacementData(KPlacementData newPlacementData, NotificationChain msgs) {
        KPlacementData oldPlacementData = placementData;
        placementData = newPlacementData;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KRenderingPackage.KRENDERING__PLACEMENT_DATA, oldPlacementData, newPlacementData);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPlacementData(KPlacementData newPlacementData) {
        if (newPlacementData != placementData) {
            NotificationChain msgs = null;
            if (placementData != null)
                msgs = ((InternalEObject)placementData).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KRENDERING__PLACEMENT_DATA, null, msgs);
            if (newPlacementData != null)
                msgs = ((InternalEObject)newPlacementData).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KRENDERING__PLACEMENT_DATA, null, msgs);
            msgs = basicSetPlacementData(newPlacementData, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KRENDERING__PLACEMENT_DATA, newPlacementData, newPlacementData));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<KStyle> getStyles() {
        if (styles == null) {
            styles = new EObjectContainmentWithInverseEList<KStyle>(KStyle.class, this, KRenderingPackage.KRENDERING__STYLES, KRenderingPackage.KSTYLE__RENDERING);
        }
        return styles;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KRENDERING__PARENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetParent((KContainerRendering)otherEnd, msgs);
            case KRenderingPackage.KRENDERING__REFERENCES:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferences()).basicAdd(otherEnd, msgs);
            case KRenderingPackage.KRENDERING__STYLES:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getStyles()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KRENDERING__PARENT:
                return basicSetParent(null, msgs);
            case KRenderingPackage.KRENDERING__REFERENCES:
                return ((InternalEList<?>)getReferences()).basicRemove(otherEnd, msgs);
            case KRenderingPackage.KRENDERING__PLACEMENT_DATA:
                return basicSetPlacementData(null, msgs);
            case KRenderingPackage.KRENDERING__STYLES:
                return ((InternalEList<?>)getStyles()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case KRenderingPackage.KRENDERING__PARENT:
                return eInternalContainer().eInverseRemove(this, KRenderingPackage.KCONTAINER_RENDERING__CHILDREN, KContainerRendering.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KRENDERING__PARENT:
                return getParent();
            case KRenderingPackage.KRENDERING__REFERENCES:
                return getReferences();
            case KRenderingPackage.KRENDERING__PLACEMENT_DATA:
                return getPlacementData();
            case KRenderingPackage.KRENDERING__STYLES:
                return getStyles();
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
            case KRenderingPackage.KRENDERING__PARENT:
                setParent((KContainerRendering)newValue);
                return;
            case KRenderingPackage.KRENDERING__REFERENCES:
                getReferences().clear();
                getReferences().addAll((Collection<? extends KRenderingRef>)newValue);
                return;
            case KRenderingPackage.KRENDERING__PLACEMENT_DATA:
                setPlacementData((KPlacementData)newValue);
                return;
            case KRenderingPackage.KRENDERING__STYLES:
                getStyles().clear();
                getStyles().addAll((Collection<? extends KStyle>)newValue);
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
            case KRenderingPackage.KRENDERING__PARENT:
                setParent((KContainerRendering)null);
                return;
            case KRenderingPackage.KRENDERING__REFERENCES:
                getReferences().clear();
                return;
            case KRenderingPackage.KRENDERING__PLACEMENT_DATA:
                setPlacementData((KPlacementData)null);
                return;
            case KRenderingPackage.KRENDERING__STYLES:
                getStyles().clear();
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
            case KRenderingPackage.KRENDERING__PARENT:
                return getParent() != null;
            case KRenderingPackage.KRENDERING__REFERENCES:
                return references != null && !references.isEmpty();
            case KRenderingPackage.KRENDERING__PLACEMENT_DATA:
                return placementData != null;
            case KRenderingPackage.KRENDERING__STYLES:
                return styles != null && !styles.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //KRenderingImpl
