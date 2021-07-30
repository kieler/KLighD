/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
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
package de.cau.cs.kieler.klighd.kgraph.impl;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.IPropertyHolder;
import org.eclipse.elk.graph.properties.IPropertyValueProxy;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder;
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory;
import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.kgraph.PersistentEntry;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EMap Property Holder</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.EMapPropertyHolderImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.EMapPropertyHolderImpl#getPersistentEntries <em>Persistent Entries</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EMapPropertyHolderImpl extends EObjectImpl implements EMapPropertyHolder {
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
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EMapPropertyHolderImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return KGraphPackage.Literals.EMAP_PROPERTY_HOLDER;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EMap<IProperty<?>, Object> getProperties() {
        if (properties == null) {
            properties = new EcoreEMap<IProperty<?>,Object>(KGraphPackage.Literals.IPROPERTY_TO_OBJECT_MAP, IPropertyToObjectMapImpl.class, this, KGraphPackage.EMAP_PROPERTY_HOLDER__PROPERTIES);
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
            persistentEntries = new EObjectContainmentEList<PersistentEntry>(PersistentEntry.class, this, KGraphPackage.EMAP_PROPERTY_HOLDER__PERSISTENT_ENTRIES);
        }
        return persistentEntries;
    }

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated NOT
     */
    public void makePersistent() {
        // chsch: deactivated the delivering of notifications as that feature is not used in KIML
        //  so far and disturbs while working with KLighD
        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
        
        int i = 0;
        List<PersistentEntry> persisEntries = getPersistentEntries();
        
        for (Entry<IProperty<?>, Object> entry : getProperties()) {
            IProperty<?> key = entry.getKey();
            Object value = entry.getValue();
            if (key != null && value != null) {
                PersistentEntry persisEntry;
                if (i >= persisEntries.size()) {
                    persisEntry = KGraphFactory.eINSTANCE.createPersistentEntry();
                    persisEntries.add(persisEntry);
                    i++;
                } else {
                    persisEntry = persisEntries.get(i++);
                }
                
                boolean pEdeliver = persisEntry.eDeliver();
                persisEntry.eSetDeliver(false);
                persisEntry.setKey(key.getId());
                persisEntry.setValue(value.toString());
                persisEntry.eSetDeliver(pEdeliver);
            }
        }
        
        // remove any superfluous persistent entries that are left from previous 
        // 'persist actions'
        ListIterator<PersistentEntry> peIt = persisEntries.listIterator(i);
        while (peIt.hasNext()) {
            peIt.next();
            peIt.remove();
        }
        
        this.eSetDeliver(deliver);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated NOT
     */
    public <T> IPropertyHolder setProperty(final IProperty<? super T> property, final T value) {
        if (value == null) {
            getProperties().removeKey(property);
        } else {
            getProperties().put(property, value);
        }
        
        return this;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated NOT
     */
    @SuppressWarnings("unchecked")
    public <T> T getProperty(IProperty<T> property) {
        Object value = getProperties().get(property);
        if (value instanceof IPropertyValueProxy) {
            value = ((IPropertyValueProxy) value).resolveValue(property);
            if (value != null) {
                getProperties().put(property, value);
                return (T) value;
            }
        } else if (value != null) {
            return (T) value;
        }
        
        T defaultValue = property.getDefault();
        if (defaultValue instanceof Cloneable) {
            setProperty(property, defaultValue);
        }
        
        return defaultValue;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean hasProperty(IProperty<?> property) {
        return getProperties().containsKey(property);
    }

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated NOT
     */
    public IPropertyHolder copyProperties(IPropertyHolder holder) {
        if (holder == null) {
            return this;
        }

        if (holder instanceof EMapPropertyHolder) {
            EMapPropertyHolder other = (EMapPropertyHolder) holder;
            EMap<IProperty<?>, Object> ourProps = this.getProperties();
            for (Map.Entry<IProperty<?>, Object> entry : other.getProperties()) {
                Object value = entry.getValue();
                if (value instanceof IPropertyValueProxy) {
                    IPropertyValueProxy proxy = (IPropertyValueProxy) value;
                    Object newValue = proxy.resolveValue(entry.getKey());
                    if (newValue != null) {
                        entry.setValue(newValue);
                        value = newValue;
                    }
                }
                ourProps.put(entry.getKey(), value);
            }
        } else {
            this.getProperties().putAll(holder.getAllProperties());
        }

        return this;
	}

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated NOT
     */
	public Map<IProperty<?>, Object> getAllProperties() {
        EMap<IProperty<?>, Object> props = getProperties();
        // check for unresolved properties
        for (Map.Entry<IProperty<?>, Object> entry : props) {
            if (entry.getValue() instanceof IPropertyValueProxy) {
                IPropertyValueProxy proxy = (IPropertyValueProxy) entry.getValue();
                // Try to resolve the proxy's value, maybe the layout option was 
                // registered by now. If not, we preserve the proxy. 
                Object value = proxy.resolveValue(entry.getKey());
                if (value != null) {
                    entry.setValue(value);
                }
            }
        }
        return props.map();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KGraphPackage.EMAP_PROPERTY_HOLDER__PROPERTIES:
                return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
            case KGraphPackage.EMAP_PROPERTY_HOLDER__PERSISTENT_ENTRIES:
                return ((InternalEList<?>)getPersistentEntries()).basicRemove(otherEnd, msgs);
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
            case KGraphPackage.EMAP_PROPERTY_HOLDER__PROPERTIES:
                if (coreType) return getProperties();
                else return getProperties().map();
            case KGraphPackage.EMAP_PROPERTY_HOLDER__PERSISTENT_ENTRIES:
                return getPersistentEntries();
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
            case KGraphPackage.EMAP_PROPERTY_HOLDER__PROPERTIES:
                ((EStructuralFeature.Setting)getProperties()).set(newValue);
                return;
            case KGraphPackage.EMAP_PROPERTY_HOLDER__PERSISTENT_ENTRIES:
                getPersistentEntries().clear();
                getPersistentEntries().addAll((Collection<? extends PersistentEntry>)newValue);
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
            case KGraphPackage.EMAP_PROPERTY_HOLDER__PROPERTIES:
                getProperties().clear();
                return;
            case KGraphPackage.EMAP_PROPERTY_HOLDER__PERSISTENT_ENTRIES:
                getPersistentEntries().clear();
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
            case KGraphPackage.EMAP_PROPERTY_HOLDER__PROPERTIES:
                return properties != null && !properties.isEmpty();
            case KGraphPackage.EMAP_PROPERTY_HOLDER__PERSISTENT_ENTRIES:
                return persistentEntries != null && !persistentEntries.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //EMapPropertyHolderImpl
