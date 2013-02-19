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

import de.cau.cs.kieler.core.krendering.KGridPlacementData;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KGrid Placement Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementDataImpl#getMinCellWidth <em>Min Cell Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementDataImpl#getMaxCellWidth <em>Max Cell Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementDataImpl#getMinCellHeight <em>Min Cell Height</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementDataImpl#getMaxCellHeight <em>Max Cell Height</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KGridPlacementDataImpl extends KAreaPlacementDataImpl implements KGridPlacementData {
    /**
     * The default value of the '{@link #getMinCellWidth() <em>Min Cell Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinCellWidth()
     * @generated
     * @ordered
     */
    protected static final float MIN_CELL_WIDTH_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getMinCellWidth() <em>Min Cell Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinCellWidth()
     * @generated
     * @ordered
     */
    protected float minCellWidth = MIN_CELL_WIDTH_EDEFAULT;

    /**
     * The default value of the '{@link #getMaxCellWidth() <em>Max Cell Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMaxCellWidth()
     * @generated
     * @ordered
     */
    protected static final float MAX_CELL_WIDTH_EDEFAULT = -1.0F;

    /**
     * The cached value of the '{@link #getMaxCellWidth() <em>Max Cell Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMaxCellWidth()
     * @generated
     * @ordered
     */
    protected float maxCellWidth = MAX_CELL_WIDTH_EDEFAULT;

    /**
     * The default value of the '{@link #getMinCellHeight() <em>Min Cell Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinCellHeight()
     * @generated
     * @ordered
     */
    protected static final float MIN_CELL_HEIGHT_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getMinCellHeight() <em>Min Cell Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinCellHeight()
     * @generated
     * @ordered
     */
    protected float minCellHeight = MIN_CELL_HEIGHT_EDEFAULT;

    /**
     * The default value of the '{@link #getMaxCellHeight() <em>Max Cell Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMaxCellHeight()
     * @generated
     * @ordered
     */
    protected static final float MAX_CELL_HEIGHT_EDEFAULT = -1.0F;

    /**
     * The cached value of the '{@link #getMaxCellHeight() <em>Max Cell Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMaxCellHeight()
     * @generated
     * @ordered
     */
    protected float maxCellHeight = MAX_CELL_HEIGHT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KGridPlacementDataImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KGRID_PLACEMENT_DATA;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getMinCellWidth() {
        return minCellWidth;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMinCellWidth(float newMinCellWidth) {
        float oldMinCellWidth = minCellWidth;
        minCellWidth = newMinCellWidth;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT_DATA__MIN_CELL_WIDTH, oldMinCellWidth, minCellWidth));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getMaxCellWidth() {
        return maxCellWidth;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMaxCellWidth(float newMaxCellWidth) {
        float oldMaxCellWidth = maxCellWidth;
        maxCellWidth = newMaxCellWidth;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT_DATA__MAX_CELL_WIDTH, oldMaxCellWidth, maxCellWidth));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getMinCellHeight() {
        return minCellHeight;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMinCellHeight(float newMinCellHeight) {
        float oldMinCellHeight = minCellHeight;
        minCellHeight = newMinCellHeight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT_DATA__MIN_CELL_HEIGHT, oldMinCellHeight, minCellHeight));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getMaxCellHeight() {
        return maxCellHeight;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMaxCellHeight(float newMaxCellHeight) {
        float oldMaxCellHeight = maxCellHeight;
        maxCellHeight = newMaxCellHeight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT_DATA__MAX_CELL_HEIGHT, oldMaxCellHeight, maxCellHeight));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KGRID_PLACEMENT_DATA__MIN_CELL_WIDTH:
                return getMinCellWidth();
            case KRenderingPackage.KGRID_PLACEMENT_DATA__MAX_CELL_WIDTH:
                return getMaxCellWidth();
            case KRenderingPackage.KGRID_PLACEMENT_DATA__MIN_CELL_HEIGHT:
                return getMinCellHeight();
            case KRenderingPackage.KGRID_PLACEMENT_DATA__MAX_CELL_HEIGHT:
                return getMaxCellHeight();
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
            case KRenderingPackage.KGRID_PLACEMENT_DATA__MIN_CELL_WIDTH:
                setMinCellWidth((Float)newValue);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__MAX_CELL_WIDTH:
                setMaxCellWidth((Float)newValue);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__MIN_CELL_HEIGHT:
                setMinCellHeight((Float)newValue);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__MAX_CELL_HEIGHT:
                setMaxCellHeight((Float)newValue);
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
            case KRenderingPackage.KGRID_PLACEMENT_DATA__MIN_CELL_WIDTH:
                setMinCellWidth(MIN_CELL_WIDTH_EDEFAULT);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__MAX_CELL_WIDTH:
                setMaxCellWidth(MAX_CELL_WIDTH_EDEFAULT);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__MIN_CELL_HEIGHT:
                setMinCellHeight(MIN_CELL_HEIGHT_EDEFAULT);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__MAX_CELL_HEIGHT:
                setMaxCellHeight(MAX_CELL_HEIGHT_EDEFAULT);
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
            case KRenderingPackage.KGRID_PLACEMENT_DATA__MIN_CELL_WIDTH:
                return minCellWidth != MIN_CELL_WIDTH_EDEFAULT;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__MAX_CELL_WIDTH:
                return maxCellWidth != MAX_CELL_WIDTH_EDEFAULT;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__MIN_CELL_HEIGHT:
                return minCellHeight != MIN_CELL_HEIGHT_EDEFAULT;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__MAX_CELL_HEIGHT:
                return maxCellHeight != MAX_CELL_HEIGHT_EDEFAULT;
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
        result.append(" (minCellWidth: ");
        result.append(minCellWidth);
        result.append(", maxCellWidth: ");
        result.append(maxCellWidth);
        result.append(", minCellHeight: ");
        result.append(minCellHeight);
        result.append(", maxCellHeight: ");
        result.append(maxCellHeight);
        result.append(')');
        return result.toString();
    }

} //KGridPlacementDataImpl
