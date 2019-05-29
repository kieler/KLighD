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

import de.cau.cs.kieler.klighd.krendering.KFontSize;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KFont Size</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KFontSizeImpl#getSize <em>Size</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KFontSizeImpl#isScaleWithZoom <em>Scale With Zoom</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KFontSizeImpl extends KStyleImpl implements KFontSize {
    /**
     * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSize()
     * @generated
     * @ordered
     */
    protected static final int SIZE_EDEFAULT = 10;

    /**
     * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSize()
     * @generated
     * @ordered
     */
    protected int size = SIZE_EDEFAULT;

    /**
     * The default value of the '{@link #isScaleWithZoom() <em>Scale With Zoom</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isScaleWithZoom()
     * @generated
     * @ordered
     */
    protected static final boolean SCALE_WITH_ZOOM_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isScaleWithZoom() <em>Scale With Zoom</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isScaleWithZoom()
     * @generated
     * @ordered
     */
    protected boolean scaleWithZoom = SCALE_WITH_ZOOM_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KFontSizeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KFONT_SIZE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getSize() {
        return size;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSize(int newSize) {
        int oldSize = size;
        size = newSize;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KFONT_SIZE__SIZE, oldSize, size));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isScaleWithZoom() {
        return scaleWithZoom;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setScaleWithZoom(boolean newScaleWithZoom) {
        boolean oldScaleWithZoom = scaleWithZoom;
        scaleWithZoom = newScaleWithZoom;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KFONT_SIZE__SCALE_WITH_ZOOM, oldScaleWithZoom, scaleWithZoom));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KFONT_SIZE__SIZE:
                return getSize();
            case KRenderingPackage.KFONT_SIZE__SCALE_WITH_ZOOM:
                return isScaleWithZoom();
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
            case KRenderingPackage.KFONT_SIZE__SIZE:
                setSize((Integer)newValue);
                return;
            case KRenderingPackage.KFONT_SIZE__SCALE_WITH_ZOOM:
                setScaleWithZoom((Boolean)newValue);
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
            case KRenderingPackage.KFONT_SIZE__SIZE:
                setSize(SIZE_EDEFAULT);
                return;
            case KRenderingPackage.KFONT_SIZE__SCALE_WITH_ZOOM:
                setScaleWithZoom(SCALE_WITH_ZOOM_EDEFAULT);
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
            case KRenderingPackage.KFONT_SIZE__SIZE:
                return size != SIZE_EDEFAULT;
            case KRenderingPackage.KFONT_SIZE__SCALE_WITH_ZOOM:
                return scaleWithZoom != SCALE_WITH_ZOOM_EDEFAULT;
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
        result.append(" (size: ");
        result.append(size);
        result.append(", scaleWithZoom: ");
        result.append(scaleWithZoom);
        result.append(')');
        return result.toString();
    }

} //KFontSizeImpl
