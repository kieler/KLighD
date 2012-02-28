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

import de.cau.cs.kieler.core.krendering.KPlacementData;
import de.cau.cs.kieler.core.krendering.KPolylinePlacementData;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KPolyline Placement Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KPolylinePlacementDataImpl#getPoints <em>Points</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KPolylinePlacementDataImpl#getDetailPlacementData <em>Detail Placement Data</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KPolylinePlacementDataImpl extends EObjectImpl implements KPolylinePlacementData {
    /**
     * The cached value of the '{@link #getPoints() <em>Points</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPoints()
     * @generated
     * @ordered
     */
    protected EList<KPosition> points;

    /**
     * The cached value of the '{@link #getDetailPlacementData() <em>Detail Placement Data</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDetailPlacementData()
     * @generated
     * @ordered
     */
    protected KPlacementData detailPlacementData;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KPolylinePlacementDataImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KPOLYLINE_PLACEMENT_DATA;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<KPosition> getPoints() {
        if (points == null) {
            points = new EObjectContainmentEList<KPosition>(KPosition.class, this, KRenderingPackage.KPOLYLINE_PLACEMENT_DATA__POINTS);
        }
        return points;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPlacementData getDetailPlacementData() {
        return detailPlacementData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDetailPlacementData(KPlacementData newDetailPlacementData, NotificationChain msgs) {
        KPlacementData oldDetailPlacementData = detailPlacementData;
        detailPlacementData = newDetailPlacementData;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KRenderingPackage.KPOLYLINE_PLACEMENT_DATA__DETAIL_PLACEMENT_DATA, oldDetailPlacementData, newDetailPlacementData);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDetailPlacementData(KPlacementData newDetailPlacementData) {
        if (newDetailPlacementData != detailPlacementData) {
            NotificationChain msgs = null;
            if (detailPlacementData != null)
                msgs = ((InternalEObject)detailPlacementData).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KPOLYLINE_PLACEMENT_DATA__DETAIL_PLACEMENT_DATA, null, msgs);
            if (newDetailPlacementData != null)
                msgs = ((InternalEObject)newDetailPlacementData).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KPOLYLINE_PLACEMENT_DATA__DETAIL_PLACEMENT_DATA, null, msgs);
            msgs = basicSetDetailPlacementData(newDetailPlacementData, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KPOLYLINE_PLACEMENT_DATA__DETAIL_PLACEMENT_DATA, newDetailPlacementData, newDetailPlacementData));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KPOLYLINE_PLACEMENT_DATA__POINTS:
                return ((InternalEList<?>)getPoints()).basicRemove(otherEnd, msgs);
            case KRenderingPackage.KPOLYLINE_PLACEMENT_DATA__DETAIL_PLACEMENT_DATA:
                return basicSetDetailPlacementData(null, msgs);
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
            case KRenderingPackage.KPOLYLINE_PLACEMENT_DATA__POINTS:
                return getPoints();
            case KRenderingPackage.KPOLYLINE_PLACEMENT_DATA__DETAIL_PLACEMENT_DATA:
                return getDetailPlacementData();
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
            case KRenderingPackage.KPOLYLINE_PLACEMENT_DATA__POINTS:
                getPoints().clear();
                getPoints().addAll((Collection<? extends KPosition>)newValue);
                return;
            case KRenderingPackage.KPOLYLINE_PLACEMENT_DATA__DETAIL_PLACEMENT_DATA:
                setDetailPlacementData((KPlacementData)newValue);
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
            case KRenderingPackage.KPOLYLINE_PLACEMENT_DATA__POINTS:
                getPoints().clear();
                return;
            case KRenderingPackage.KPOLYLINE_PLACEMENT_DATA__DETAIL_PLACEMENT_DATA:
                setDetailPlacementData((KPlacementData)null);
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
            case KRenderingPackage.KPOLYLINE_PLACEMENT_DATA__POINTS:
                return points != null && !points.isEmpty();
            case KRenderingPackage.KPOLYLINE_PLACEMENT_DATA__DETAIL_PLACEMENT_DATA:
                return detailPlacementData != null;
        }
        return super.eIsSet(featureID);
    }

} //KPolylinePlacementDataImpl
