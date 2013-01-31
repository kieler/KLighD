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

import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KPolyline</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KPolylineImpl#getPoints <em>Points</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KPolylineImpl#getJointPointRendering <em>Joint Point Rendering</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KPolylineImpl extends KContainerRenderingImpl implements KPolyline {
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
     * The cached value of the '{@link #getJointPointRendering() <em>Joint Point Rendering</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getJointPointRendering()
     * @generated
     * @ordered
     */
    protected KRendering jointPointRendering;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KPolylineImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KPOLYLINE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<KPosition> getPoints() {
        if (points == null) {
            points = new EObjectContainmentEList<KPosition>(KPosition.class, this, KRenderingPackage.KPOLYLINE__POINTS);
        }
        return points;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KRendering getJointPointRendering() {
        return jointPointRendering;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetJointPointRendering(KRendering newJointPointRendering, NotificationChain msgs) {
        KRendering oldJointPointRendering = jointPointRendering;
        jointPointRendering = newJointPointRendering;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KRenderingPackage.KPOLYLINE__JOINT_POINT_RENDERING, oldJointPointRendering, newJointPointRendering);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setJointPointRendering(KRendering newJointPointRendering) {
        if (newJointPointRendering != jointPointRendering) {
            NotificationChain msgs = null;
            if (jointPointRendering != null)
                msgs = ((InternalEObject)jointPointRendering).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KPOLYLINE__JOINT_POINT_RENDERING, null, msgs);
            if (newJointPointRendering != null)
                msgs = ((InternalEObject)newJointPointRendering).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KPOLYLINE__JOINT_POINT_RENDERING, null, msgs);
            msgs = basicSetJointPointRendering(newJointPointRendering, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KPOLYLINE__JOINT_POINT_RENDERING, newJointPointRendering, newJointPointRendering));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KPOLYLINE__POINTS:
                return ((InternalEList<?>)getPoints()).basicRemove(otherEnd, msgs);
            case KRenderingPackage.KPOLYLINE__JOINT_POINT_RENDERING:
                return basicSetJointPointRendering(null, msgs);
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
            case KRenderingPackage.KPOLYLINE__POINTS:
                return getPoints();
            case KRenderingPackage.KPOLYLINE__JOINT_POINT_RENDERING:
                return getJointPointRendering();
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
            case KRenderingPackage.KPOLYLINE__POINTS:
                getPoints().clear();
                getPoints().addAll((Collection<? extends KPosition>)newValue);
                return;
            case KRenderingPackage.KPOLYLINE__JOINT_POINT_RENDERING:
                setJointPointRendering((KRendering)newValue);
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
            case KRenderingPackage.KPOLYLINE__POINTS:
                getPoints().clear();
                return;
            case KRenderingPackage.KPOLYLINE__JOINT_POINT_RENDERING:
                setJointPointRendering((KRendering)null);
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
            case KRenderingPackage.KPOLYLINE__POINTS:
                return points != null && !points.isEmpty();
            case KRenderingPackage.KPOLYLINE__JOINT_POINT_RENDERING:
                return jointPointRendering != null;
        }
        return super.eIsSet(featureID);
    }

} //KPolylineImpl
