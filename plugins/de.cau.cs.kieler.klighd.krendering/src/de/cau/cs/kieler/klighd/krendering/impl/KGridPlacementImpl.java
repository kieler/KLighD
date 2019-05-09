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

import de.cau.cs.kieler.klighd.krendering.KGridPlacement;
import de.cau.cs.kieler.klighd.krendering.KPosition;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KGrid Placement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KGridPlacementImpl#getNumColumns <em>Num Columns</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KGridPlacementImpl#getTopLeft <em>Top Left</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KGridPlacementImpl#getBottomRight <em>Bottom Right</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KGridPlacementImpl extends EObjectImpl implements KGridPlacement {
    /**
     * The default value of the '{@link #getNumColumns() <em>Num Columns</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNumColumns()
     * @generated
     * @ordered
     */
    protected static final int NUM_COLUMNS_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getNumColumns() <em>Num Columns</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNumColumns()
     * @generated
     * @ordered
     */
    protected int numColumns = NUM_COLUMNS_EDEFAULT;

    /**
     * The cached value of the '{@link #getTopLeft() <em>Top Left</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTopLeft()
     * @generated
     * @ordered
     */
    protected KPosition topLeft;

    /**
     * The cached value of the '{@link #getBottomRight() <em>Bottom Right</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBottomRight()
     * @generated
     * @ordered
     */
    protected KPosition bottomRight;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KGridPlacementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KGRID_PLACEMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getNumColumns() {
        return numColumns;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNumColumns(int newNumColumns) {
        int oldNumColumns = numColumns;
        numColumns = newNumColumns;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT__NUM_COLUMNS, oldNumColumns, numColumns));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPosition getTopLeft() {
        return topLeft;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTopLeft(KPosition newTopLeft, NotificationChain msgs) {
        KPosition oldTopLeft = topLeft;
        topLeft = newTopLeft;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT__TOP_LEFT, oldTopLeft, newTopLeft);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTopLeft(KPosition newTopLeft) {
        if (newTopLeft != topLeft) {
            NotificationChain msgs = null;
            if (topLeft != null)
                msgs = ((InternalEObject)topLeft).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KGRID_PLACEMENT__TOP_LEFT, null, msgs);
            if (newTopLeft != null)
                msgs = ((InternalEObject)newTopLeft).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KGRID_PLACEMENT__TOP_LEFT, null, msgs);
            msgs = basicSetTopLeft(newTopLeft, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT__TOP_LEFT, newTopLeft, newTopLeft));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPosition getBottomRight() {
        return bottomRight;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBottomRight(KPosition newBottomRight, NotificationChain msgs) {
        KPosition oldBottomRight = bottomRight;
        bottomRight = newBottomRight;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT__BOTTOM_RIGHT, oldBottomRight, newBottomRight);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBottomRight(KPosition newBottomRight) {
        if (newBottomRight != bottomRight) {
            NotificationChain msgs = null;
            if (bottomRight != null)
                msgs = ((InternalEObject)bottomRight).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KGRID_PLACEMENT__BOTTOM_RIGHT, null, msgs);
            if (newBottomRight != null)
                msgs = ((InternalEObject)newBottomRight).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KGRID_PLACEMENT__BOTTOM_RIGHT, null, msgs);
            msgs = basicSetBottomRight(newBottomRight, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT__BOTTOM_RIGHT, newBottomRight, newBottomRight));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KGRID_PLACEMENT__TOP_LEFT:
                return basicSetTopLeft(null, msgs);
            case KRenderingPackage.KGRID_PLACEMENT__BOTTOM_RIGHT:
                return basicSetBottomRight(null, msgs);
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
            case KRenderingPackage.KGRID_PLACEMENT__NUM_COLUMNS:
                return getNumColumns();
            case KRenderingPackage.KGRID_PLACEMENT__TOP_LEFT:
                return getTopLeft();
            case KRenderingPackage.KGRID_PLACEMENT__BOTTOM_RIGHT:
                return getBottomRight();
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
            case KRenderingPackage.KGRID_PLACEMENT__NUM_COLUMNS:
                setNumColumns((Integer)newValue);
                return;
            case KRenderingPackage.KGRID_PLACEMENT__TOP_LEFT:
                setTopLeft((KPosition)newValue);
                return;
            case KRenderingPackage.KGRID_PLACEMENT__BOTTOM_RIGHT:
                setBottomRight((KPosition)newValue);
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
            case KRenderingPackage.KGRID_PLACEMENT__NUM_COLUMNS:
                setNumColumns(NUM_COLUMNS_EDEFAULT);
                return;
            case KRenderingPackage.KGRID_PLACEMENT__TOP_LEFT:
                setTopLeft((KPosition)null);
                return;
            case KRenderingPackage.KGRID_PLACEMENT__BOTTOM_RIGHT:
                setBottomRight((KPosition)null);
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
            case KRenderingPackage.KGRID_PLACEMENT__NUM_COLUMNS:
                return numColumns != NUM_COLUMNS_EDEFAULT;
            case KRenderingPackage.KGRID_PLACEMENT__TOP_LEFT:
                return topLeft != null;
            case KRenderingPackage.KGRID_PLACEMENT__BOTTOM_RIGHT:
                return bottomRight != null;
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
        result.append(" (numColumns: ");
        result.append(numColumns);
        result.append(')');
        return result.toString();
    }

} //KGridPlacementImpl
