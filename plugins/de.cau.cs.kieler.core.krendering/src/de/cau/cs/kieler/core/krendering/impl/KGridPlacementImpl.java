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

import de.cau.cs.kieler.core.krendering.KGridPlacement;
import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;

import org.eclipse.emf.common.notify.Notification;

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
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementImpl#getNumColumns <em>Num Columns</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementImpl#getTopLeft <em>Top Left</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementImpl#getBottomRight <em>Bottom Right</em>}</li>
 * </ul>
 * </p>
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
     * The cached value of the '{@link #getTopLeft() <em>Top Left</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTopLeft()
     * @generated
     * @ordered
     */
    protected KPosition topLeft;

    /**
     * The cached value of the '{@link #getBottomRight() <em>Bottom Right</em>}' reference.
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
        if (topLeft != null && topLeft.eIsProxy()) {
            InternalEObject oldTopLeft = (InternalEObject)topLeft;
            topLeft = (KPosition)eResolveProxy(oldTopLeft);
            if (topLeft != oldTopLeft) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, KRenderingPackage.KGRID_PLACEMENT__TOP_LEFT, oldTopLeft, topLeft));
            }
        }
        return topLeft;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPosition basicGetTopLeft() {
        return topLeft;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTopLeft(KPosition newTopLeft) {
        KPosition oldTopLeft = topLeft;
        topLeft = newTopLeft;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT__TOP_LEFT, oldTopLeft, topLeft));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPosition getBottomRight() {
        if (bottomRight != null && bottomRight.eIsProxy()) {
            InternalEObject oldBottomRight = (InternalEObject)bottomRight;
            bottomRight = (KPosition)eResolveProxy(oldBottomRight);
            if (bottomRight != oldBottomRight) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, KRenderingPackage.KGRID_PLACEMENT__BOTTOM_RIGHT, oldBottomRight, bottomRight));
            }
        }
        return bottomRight;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPosition basicGetBottomRight() {
        return bottomRight;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBottomRight(KPosition newBottomRight) {
        KPosition oldBottomRight = bottomRight;
        bottomRight = newBottomRight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT__BOTTOM_RIGHT, oldBottomRight, bottomRight));
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
                if (resolve) return getTopLeft();
                return basicGetTopLeft();
            case KRenderingPackage.KGRID_PLACEMENT__BOTTOM_RIGHT:
                if (resolve) return getBottomRight();
                return basicGetBottomRight();
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

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (numColumns: ");
        result.append(numColumns);
        result.append(')');
        return result.toString();
    }

} //KGridPlacementImpl
