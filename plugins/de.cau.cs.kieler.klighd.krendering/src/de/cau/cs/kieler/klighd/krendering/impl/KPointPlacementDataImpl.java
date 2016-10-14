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

import de.cau.cs.kieler.klighd.krendering.HorizontalAlignment;
import de.cau.cs.kieler.klighd.krendering.KPointPlacementData;
import de.cau.cs.kieler.klighd.krendering.KPosition;
import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;
import de.cau.cs.kieler.klighd.krendering.VerticalAlignment;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KPoint Placement Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KPointPlacementDataImpl#getReferencePoint <em>Reference Point</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KPointPlacementDataImpl#getHorizontalAlignment <em>Horizontal Alignment</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KPointPlacementDataImpl#getVerticalAlignment <em>Vertical Alignment</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KPointPlacementDataImpl#getHorizontalMargin <em>Horizontal Margin</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KPointPlacementDataImpl#getVerticalMargin <em>Vertical Margin</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KPointPlacementDataImpl#getMinWidth <em>Min Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KPointPlacementDataImpl#getMinHeight <em>Min Height</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KPointPlacementDataImpl extends EObjectImpl implements KPointPlacementData {
    /**
     * The cached value of the '{@link #getReferencePoint() <em>Reference Point</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReferencePoint()
     * @generated
     * @ordered
     */
    protected KPosition referencePoint;

    /**
     * The default value of the '{@link #getHorizontalAlignment() <em>Horizontal Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHorizontalAlignment()
     * @generated
     * @ordered
     */
    protected static final HorizontalAlignment HORIZONTAL_ALIGNMENT_EDEFAULT = HorizontalAlignment.LEFT;

    /**
     * The cached value of the '{@link #getHorizontalAlignment() <em>Horizontal Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHorizontalAlignment()
     * @generated
     * @ordered
     */
    protected HorizontalAlignment horizontalAlignment = HORIZONTAL_ALIGNMENT_EDEFAULT;

    /**
     * The default value of the '{@link #getVerticalAlignment() <em>Vertical Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVerticalAlignment()
     * @generated
     * @ordered
     */
    protected static final VerticalAlignment VERTICAL_ALIGNMENT_EDEFAULT = VerticalAlignment.TOP;

    /**
     * The cached value of the '{@link #getVerticalAlignment() <em>Vertical Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVerticalAlignment()
     * @generated
     * @ordered
     */
    protected VerticalAlignment verticalAlignment = VERTICAL_ALIGNMENT_EDEFAULT;

    /**
     * The default value of the '{@link #getHorizontalMargin() <em>Horizontal Margin</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHorizontalMargin()
     * @generated
     * @ordered
     */
    protected static final float HORIZONTAL_MARGIN_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getHorizontalMargin() <em>Horizontal Margin</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHorizontalMargin()
     * @generated
     * @ordered
     */
    protected float horizontalMargin = HORIZONTAL_MARGIN_EDEFAULT;

    /**
     * The default value of the '{@link #getVerticalMargin() <em>Vertical Margin</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVerticalMargin()
     * @generated
     * @ordered
     */
    protected static final float VERTICAL_MARGIN_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getVerticalMargin() <em>Vertical Margin</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVerticalMargin()
     * @generated
     * @ordered
     */
    protected float verticalMargin = VERTICAL_MARGIN_EDEFAULT;

    /**
     * The default value of the '{@link #getMinWidth() <em>Min Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinWidth()
     * @generated
     * @ordered
     */
    protected static final float MIN_WIDTH_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getMinWidth() <em>Min Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinWidth()
     * @generated
     * @ordered
     */
    protected float minWidth = MIN_WIDTH_EDEFAULT;

    /**
     * The default value of the '{@link #getMinHeight() <em>Min Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinHeight()
     * @generated
     * @ordered
     */
    protected static final float MIN_HEIGHT_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getMinHeight() <em>Min Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinHeight()
     * @generated
     * @ordered
     */
    protected float minHeight = MIN_HEIGHT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KPointPlacementDataImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KPOINT_PLACEMENT_DATA;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPosition getReferencePoint() {
        return referencePoint;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetReferencePoint(KPosition newReferencePoint, NotificationChain msgs) {
        KPosition oldReferencePoint = referencePoint;
        referencePoint = newReferencePoint;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KRenderingPackage.KPOINT_PLACEMENT_DATA__REFERENCE_POINT, oldReferencePoint, newReferencePoint);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReferencePoint(KPosition newReferencePoint) {
        if (newReferencePoint != referencePoint) {
            NotificationChain msgs = null;
            if (referencePoint != null)
                msgs = ((InternalEObject)referencePoint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KPOINT_PLACEMENT_DATA__REFERENCE_POINT, null, msgs);
            if (newReferencePoint != null)
                msgs = ((InternalEObject)newReferencePoint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KPOINT_PLACEMENT_DATA__REFERENCE_POINT, null, msgs);
            msgs = basicSetReferencePoint(newReferencePoint, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KPOINT_PLACEMENT_DATA__REFERENCE_POINT, newReferencePoint, newReferencePoint));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHorizontalAlignment(HorizontalAlignment newHorizontalAlignment) {
        HorizontalAlignment oldHorizontalAlignment = horizontalAlignment;
        horizontalAlignment = newHorizontalAlignment == null ? HORIZONTAL_ALIGNMENT_EDEFAULT : newHorizontalAlignment;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KPOINT_PLACEMENT_DATA__HORIZONTAL_ALIGNMENT, oldHorizontalAlignment, horizontalAlignment));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVerticalAlignment(VerticalAlignment newVerticalAlignment) {
        VerticalAlignment oldVerticalAlignment = verticalAlignment;
        verticalAlignment = newVerticalAlignment == null ? VERTICAL_ALIGNMENT_EDEFAULT : newVerticalAlignment;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KPOINT_PLACEMENT_DATA__VERTICAL_ALIGNMENT, oldVerticalAlignment, verticalAlignment));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getHorizontalMargin() {
        return horizontalMargin;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHorizontalMargin(float newHorizontalMargin) {
        float oldHorizontalMargin = horizontalMargin;
        horizontalMargin = newHorizontalMargin;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KPOINT_PLACEMENT_DATA__HORIZONTAL_MARGIN, oldHorizontalMargin, horizontalMargin));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getVerticalMargin() {
        return verticalMargin;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVerticalMargin(float newVerticalMargin) {
        float oldVerticalMargin = verticalMargin;
        verticalMargin = newVerticalMargin;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KPOINT_PLACEMENT_DATA__VERTICAL_MARGIN, oldVerticalMargin, verticalMargin));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getMinWidth() {
        return minWidth;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMinWidth(float newMinWidth) {
        float oldMinWidth = minWidth;
        minWidth = newMinWidth;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KPOINT_PLACEMENT_DATA__MIN_WIDTH, oldMinWidth, minWidth));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getMinHeight() {
        return minHeight;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMinHeight(float newMinHeight) {
        float oldMinHeight = minHeight;
        minHeight = newMinHeight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KPOINT_PLACEMENT_DATA__MIN_HEIGHT, oldMinHeight, minHeight));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__REFERENCE_POINT:
                return basicSetReferencePoint(null, msgs);
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
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__REFERENCE_POINT:
                return getReferencePoint();
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__HORIZONTAL_ALIGNMENT:
                return getHorizontalAlignment();
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__VERTICAL_ALIGNMENT:
                return getVerticalAlignment();
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__HORIZONTAL_MARGIN:
                return getHorizontalMargin();
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__VERTICAL_MARGIN:
                return getVerticalMargin();
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__MIN_WIDTH:
                return getMinWidth();
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__MIN_HEIGHT:
                return getMinHeight();
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
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__REFERENCE_POINT:
                setReferencePoint((KPosition)newValue);
                return;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__HORIZONTAL_ALIGNMENT:
                setHorizontalAlignment((HorizontalAlignment)newValue);
                return;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__VERTICAL_ALIGNMENT:
                setVerticalAlignment((VerticalAlignment)newValue);
                return;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__HORIZONTAL_MARGIN:
                setHorizontalMargin((Float)newValue);
                return;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__VERTICAL_MARGIN:
                setVerticalMargin((Float)newValue);
                return;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__MIN_WIDTH:
                setMinWidth((Float)newValue);
                return;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__MIN_HEIGHT:
                setMinHeight((Float)newValue);
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
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__REFERENCE_POINT:
                setReferencePoint((KPosition)null);
                return;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__HORIZONTAL_ALIGNMENT:
                setHorizontalAlignment(HORIZONTAL_ALIGNMENT_EDEFAULT);
                return;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__VERTICAL_ALIGNMENT:
                setVerticalAlignment(VERTICAL_ALIGNMENT_EDEFAULT);
                return;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__HORIZONTAL_MARGIN:
                setHorizontalMargin(HORIZONTAL_MARGIN_EDEFAULT);
                return;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__VERTICAL_MARGIN:
                setVerticalMargin(VERTICAL_MARGIN_EDEFAULT);
                return;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__MIN_WIDTH:
                setMinWidth(MIN_WIDTH_EDEFAULT);
                return;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__MIN_HEIGHT:
                setMinHeight(MIN_HEIGHT_EDEFAULT);
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
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__REFERENCE_POINT:
                return referencePoint != null;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__HORIZONTAL_ALIGNMENT:
                return horizontalAlignment != HORIZONTAL_ALIGNMENT_EDEFAULT;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__VERTICAL_ALIGNMENT:
                return verticalAlignment != VERTICAL_ALIGNMENT_EDEFAULT;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__HORIZONTAL_MARGIN:
                return horizontalMargin != HORIZONTAL_MARGIN_EDEFAULT;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__VERTICAL_MARGIN:
                return verticalMargin != VERTICAL_MARGIN_EDEFAULT;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__MIN_WIDTH:
                return minWidth != MIN_WIDTH_EDEFAULT;
            case KRenderingPackage.KPOINT_PLACEMENT_DATA__MIN_HEIGHT:
                return minHeight != MIN_HEIGHT_EDEFAULT;
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
        result.append(" (horizontalAlignment: ");
        result.append(horizontalAlignment);
        result.append(", verticalAlignment: ");
        result.append(verticalAlignment);
        result.append(", horizontalMargin: ");
        result.append(horizontalMargin);
        result.append(", verticalMargin: ");
        result.append(verticalMargin);
        result.append(", minWidth: ");
        result.append(minWidth);
        result.append(", minHeight: ");
        result.append(minHeight);
        result.append(')');
        return result.toString();
    }

} //KPointPlacementDataImpl
