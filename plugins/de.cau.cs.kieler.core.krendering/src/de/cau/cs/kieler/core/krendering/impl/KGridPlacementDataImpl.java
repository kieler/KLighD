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
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KGrid Placement Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementDataImpl#getWidthHint <em>Width Hint</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementDataImpl#getHeightHint <em>Height Hint</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementDataImpl#getInsetRight <em>Inset Right</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementDataImpl#getInsetBottom <em>Inset Bottom</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementDataImpl#getInsetLeft <em>Inset Left</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementDataImpl#getInsetTop <em>Inset Top</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KGridPlacementDataImpl extends EObjectImpl implements KGridPlacementData {
    /**
     * The default value of the '{@link #getWidthHint() <em>Width Hint</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWidthHint()
     * @generated
     * @ordered
     */
    protected static final float WIDTH_HINT_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getWidthHint() <em>Width Hint</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWidthHint()
     * @generated
     * @ordered
     */
    protected float widthHint = WIDTH_HINT_EDEFAULT;

    /**
     * The default value of the '{@link #getHeightHint() <em>Height Hint</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHeightHint()
     * @generated
     * @ordered
     */
    protected static final float HEIGHT_HINT_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getHeightHint() <em>Height Hint</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHeightHint()
     * @generated
     * @ordered
     */
    protected float heightHint = HEIGHT_HINT_EDEFAULT;

    /**
     * The default value of the '{@link #getInsetRight() <em>Inset Right</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInsetRight()
     * @generated
     * @ordered
     */
    protected static final float INSET_RIGHT_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getInsetRight() <em>Inset Right</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInsetRight()
     * @generated
     * @ordered
     */
    protected float insetRight = INSET_RIGHT_EDEFAULT;

    /**
     * The default value of the '{@link #getInsetBottom() <em>Inset Bottom</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInsetBottom()
     * @generated
     * @ordered
     */
    protected static final float INSET_BOTTOM_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getInsetBottom() <em>Inset Bottom</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInsetBottom()
     * @generated
     * @ordered
     */
    protected float insetBottom = INSET_BOTTOM_EDEFAULT;

    /**
     * The default value of the '{@link #getInsetLeft() <em>Inset Left</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInsetLeft()
     * @generated
     * @ordered
     */
    protected static final float INSET_LEFT_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getInsetLeft() <em>Inset Left</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInsetLeft()
     * @generated
     * @ordered
     */
    protected float insetLeft = INSET_LEFT_EDEFAULT;

    /**
     * The default value of the '{@link #getInsetTop() <em>Inset Top</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInsetTop()
     * @generated
     * @ordered
     */
    protected static final float INSET_TOP_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getInsetTop() <em>Inset Top</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInsetTop()
     * @generated
     * @ordered
     */
    protected float insetTop = INSET_TOP_EDEFAULT;

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
    public float getWidthHint() {
        return widthHint;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWidthHint(float newWidthHint) {
        float oldWidthHint = widthHint;
        widthHint = newWidthHint;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT_DATA__WIDTH_HINT, oldWidthHint, widthHint));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getHeightHint() {
        return heightHint;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHeightHint(float newHeightHint) {
        float oldHeightHint = heightHint;
        heightHint = newHeightHint;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT_DATA__HEIGHT_HINT, oldHeightHint, heightHint));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getInsetRight() {
        return insetRight;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInsetRight(float newInsetRight) {
        float oldInsetRight = insetRight;
        insetRight = newInsetRight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_RIGHT, oldInsetRight, insetRight));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getInsetBottom() {
        return insetBottom;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInsetBottom(float newInsetBottom) {
        float oldInsetBottom = insetBottom;
        insetBottom = newInsetBottom;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_BOTTOM, oldInsetBottom, insetBottom));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getInsetLeft() {
        return insetLeft;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInsetLeft(float newInsetLeft) {
        float oldInsetLeft = insetLeft;
        insetLeft = newInsetLeft;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_LEFT, oldInsetLeft, insetLeft));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getInsetTop() {
        return insetTop;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInsetTop(float newInsetTop) {
        float oldInsetTop = insetTop;
        insetTop = newInsetTop;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_TOP, oldInsetTop, insetTop));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KGRID_PLACEMENT_DATA__WIDTH_HINT:
                return getWidthHint();
            case KRenderingPackage.KGRID_PLACEMENT_DATA__HEIGHT_HINT:
                return getHeightHint();
            case KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_RIGHT:
                return getInsetRight();
            case KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_BOTTOM:
                return getInsetBottom();
            case KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_LEFT:
                return getInsetLeft();
            case KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_TOP:
                return getInsetTop();
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
            case KRenderingPackage.KGRID_PLACEMENT_DATA__WIDTH_HINT:
                setWidthHint((Float)newValue);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__HEIGHT_HINT:
                setHeightHint((Float)newValue);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_RIGHT:
                setInsetRight((Float)newValue);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_BOTTOM:
                setInsetBottom((Float)newValue);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_LEFT:
                setInsetLeft((Float)newValue);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_TOP:
                setInsetTop((Float)newValue);
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
            case KRenderingPackage.KGRID_PLACEMENT_DATA__WIDTH_HINT:
                setWidthHint(WIDTH_HINT_EDEFAULT);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__HEIGHT_HINT:
                setHeightHint(HEIGHT_HINT_EDEFAULT);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_RIGHT:
                setInsetRight(INSET_RIGHT_EDEFAULT);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_BOTTOM:
                setInsetBottom(INSET_BOTTOM_EDEFAULT);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_LEFT:
                setInsetLeft(INSET_LEFT_EDEFAULT);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_TOP:
                setInsetTop(INSET_TOP_EDEFAULT);
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
            case KRenderingPackage.KGRID_PLACEMENT_DATA__WIDTH_HINT:
                return widthHint != WIDTH_HINT_EDEFAULT;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__HEIGHT_HINT:
                return heightHint != HEIGHT_HINT_EDEFAULT;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_RIGHT:
                return insetRight != INSET_RIGHT_EDEFAULT;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_BOTTOM:
                return insetBottom != INSET_BOTTOM_EDEFAULT;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_LEFT:
                return insetLeft != INSET_LEFT_EDEFAULT;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__INSET_TOP:
                return insetTop != INSET_TOP_EDEFAULT;
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
        result.append(" (widthHint: ");
        result.append(widthHint);
        result.append(", heightHint: ");
        result.append(heightHint);
        result.append(", insetRight: ");
        result.append(insetRight);
        result.append(", insetBottom: ");
        result.append(insetBottom);
        result.append(", insetLeft: ");
        result.append(insetLeft);
        result.append(", insetTop: ");
        result.append(insetTop);
        result.append(')');
        return result.toString();
    }

} //KGridPlacementDataImpl
