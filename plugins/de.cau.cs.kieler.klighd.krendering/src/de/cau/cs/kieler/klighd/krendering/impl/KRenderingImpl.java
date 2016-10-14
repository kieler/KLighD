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

import de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder;
import de.cau.cs.kieler.klighd.kgraph.KGraphData;
import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.kgraph.PersistentEntry;

import de.cau.cs.kieler.klighd.kgraph.impl.IPropertyToObjectMapImpl;

import de.cau.cs.kieler.klighd.krendering.KAction;
import de.cau.cs.kieler.klighd.krendering.KContainerRendering;
import de.cau.cs.kieler.klighd.krendering.KPlacementData;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;

import java.util.Collection;
import java.util.Map;

import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.IPropertyHolder;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KRendering</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KRenderingImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KRenderingImpl#getPersistentEntries <em>Persistent Entries</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KRenderingImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KRenderingImpl#getPlacementData <em>Placement Data</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KRenderingImpl#getActions <em>Actions</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class KRenderingImpl extends KStyleHolderImpl implements KRendering {
    /**
     * The cached value of the '{@link #getProperties() <em>Properties</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProperties()
     * @generated
     * @ordered
     */
    protected EMap<IProperty<?>, Object> properties;

    /**
     * The cached value of the '{@link #getPersistentEntries() <em>Persistent Entries</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPersistentEntries()
     * @generated
     * @ordered
     */
    protected EList<PersistentEntry> persistentEntries;

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
     * The cached value of the '{@link #getActions() <em>Actions</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getActions()
     * @generated
     * @ordered
     */
    protected EList<KAction> actions;

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
    public EMap<IProperty<?>, Object> getProperties() {
        if (properties == null) {
            properties = new EcoreEMap<IProperty<?>,Object>(KGraphPackage.Literals.IPROPERTY_TO_OBJECT_MAP, IPropertyToObjectMapImpl.class, this, KRenderingPackage.KRENDERING__PROPERTIES);
        }
        return properties;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<PersistentEntry> getPersistentEntries() {
        if (persistentEntries == null) {
            persistentEntries = new EObjectContainmentEList<PersistentEntry>(PersistentEntry.class, this, KRenderingPackage.KRENDERING__PERSISTENT_ENTRIES);
        }
        return persistentEntries;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KContainerRendering getParent() {
        if (eContainerFeatureID() != KRenderingPackage.KRENDERING__PARENT) return null;
        return (KContainerRendering)eInternalContainer();
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
    public EList<KAction> getActions() {
        if (actions == null) {
            actions = new EObjectContainmentEList<KAction>(KAction.class, this, KRenderingPackage.KRENDERING__ACTIONS);
        }
        return actions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void makePersistent() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public <T> IPropertyHolder setProperty(IProperty<? super T> property, T value) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public <T> T getProperty(IProperty<T> property) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IPropertyHolder copyProperties(IPropertyHolder holder) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Map<IProperty<?>, Object> getAllProperties() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KRENDERING__PARENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetParent((KContainerRendering)otherEnd, msgs);
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
            case KRenderingPackage.KRENDERING__PROPERTIES:
                return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
            case KRenderingPackage.KRENDERING__PERSISTENT_ENTRIES:
                return ((InternalEList<?>)getPersistentEntries()).basicRemove(otherEnd, msgs);
            case KRenderingPackage.KRENDERING__PARENT:
                return basicSetParent(null, msgs);
            case KRenderingPackage.KRENDERING__PLACEMENT_DATA:
                return basicSetPlacementData(null, msgs);
            case KRenderingPackage.KRENDERING__ACTIONS:
                return ((InternalEList<?>)getActions()).basicRemove(otherEnd, msgs);
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
            case KRenderingPackage.KRENDERING__PROPERTIES:
                if (coreType) return getProperties();
                else return getProperties().map();
            case KRenderingPackage.KRENDERING__PERSISTENT_ENTRIES:
                return getPersistentEntries();
            case KRenderingPackage.KRENDERING__PARENT:
                return getParent();
            case KRenderingPackage.KRENDERING__PLACEMENT_DATA:
                return getPlacementData();
            case KRenderingPackage.KRENDERING__ACTIONS:
                return getActions();
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
            case KRenderingPackage.KRENDERING__PROPERTIES:
                ((EStructuralFeature.Setting)getProperties()).set(newValue);
                return;
            case KRenderingPackage.KRENDERING__PERSISTENT_ENTRIES:
                getPersistentEntries().clear();
                getPersistentEntries().addAll((Collection<? extends PersistentEntry>)newValue);
                return;
            case KRenderingPackage.KRENDERING__PARENT:
                setParent((KContainerRendering)newValue);
                return;
            case KRenderingPackage.KRENDERING__PLACEMENT_DATA:
                setPlacementData((KPlacementData)newValue);
                return;
            case KRenderingPackage.KRENDERING__ACTIONS:
                getActions().clear();
                getActions().addAll((Collection<? extends KAction>)newValue);
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
            case KRenderingPackage.KRENDERING__PROPERTIES:
                getProperties().clear();
                return;
            case KRenderingPackage.KRENDERING__PERSISTENT_ENTRIES:
                getPersistentEntries().clear();
                return;
            case KRenderingPackage.KRENDERING__PARENT:
                setParent((KContainerRendering)null);
                return;
            case KRenderingPackage.KRENDERING__PLACEMENT_DATA:
                setPlacementData((KPlacementData)null);
                return;
            case KRenderingPackage.KRENDERING__ACTIONS:
                getActions().clear();
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
            case KRenderingPackage.KRENDERING__PROPERTIES:
                return properties != null && !properties.isEmpty();
            case KRenderingPackage.KRENDERING__PERSISTENT_ENTRIES:
                return persistentEntries != null && !persistentEntries.isEmpty();
            case KRenderingPackage.KRENDERING__PARENT:
                return getParent() != null;
            case KRenderingPackage.KRENDERING__PLACEMENT_DATA:
                return placementData != null;
            case KRenderingPackage.KRENDERING__ACTIONS:
                return actions != null && !actions.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == IPropertyHolder.class) {
            switch (derivedFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == EMapPropertyHolder.class) {
            switch (derivedFeatureID) {
                case KRenderingPackage.KRENDERING__PROPERTIES: return KGraphPackage.EMAP_PROPERTY_HOLDER__PROPERTIES;
                case KRenderingPackage.KRENDERING__PERSISTENT_ENTRIES: return KGraphPackage.EMAP_PROPERTY_HOLDER__PERSISTENT_ENTRIES;
                default: return -1;
            }
        }
        if (baseClass == KGraphData.class) {
            switch (derivedFeatureID) {
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == IPropertyHolder.class) {
            switch (baseFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == EMapPropertyHolder.class) {
            switch (baseFeatureID) {
                case KGraphPackage.EMAP_PROPERTY_HOLDER__PROPERTIES: return KRenderingPackage.KRENDERING__PROPERTIES;
                case KGraphPackage.EMAP_PROPERTY_HOLDER__PERSISTENT_ENTRIES: return KRenderingPackage.KRENDERING__PERSISTENT_ENTRIES;
                default: return -1;
            }
        }
        if (baseClass == KGraphData.class) {
            switch (baseFeatureID) {
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} //KRenderingImpl
