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
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementDataImpl#getHorizontalIndent <em>Horizontal Indent</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KGridPlacementDataImpl#getVerticalIndent <em>Vertical Indent</em>}</li>
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
     * The default value of the '{@link #getHorizontalIndent() <em>Horizontal Indent</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHorizontalIndent()
     * @generated
     * @ordered
     */
    protected static final float HORIZONTAL_INDENT_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getHorizontalIndent() <em>Horizontal Indent</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHorizontalIndent()
     * @generated
     * @ordered
     */
    protected float horizontalIndent = HORIZONTAL_INDENT_EDEFAULT;

    /**
     * The default value of the '{@link #getVerticalIndent() <em>Vertical Indent</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVerticalIndent()
     * @generated
     * @ordered
     */
    protected static final float VERTICAL_INDENT_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getVerticalIndent() <em>Vertical Indent</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVerticalIndent()
     * @generated
     * @ordered
     */
    protected float verticalIndent = VERTICAL_INDENT_EDEFAULT;

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
    public float getHorizontalIndent() {
        return horizontalIndent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHorizontalIndent(float newHorizontalIndent) {
        float oldHorizontalIndent = horizontalIndent;
        horizontalIndent = newHorizontalIndent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT_DATA__HORIZONTAL_INDENT, oldHorizontalIndent, horizontalIndent));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getVerticalIndent() {
        return verticalIndent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVerticalIndent(float newVerticalIndent) {
        float oldVerticalIndent = verticalIndent;
        verticalIndent = newVerticalIndent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KGRID_PLACEMENT_DATA__VERTICAL_INDENT, oldVerticalIndent, verticalIndent));
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
            case KRenderingPackage.KGRID_PLACEMENT_DATA__HORIZONTAL_INDENT:
                return getHorizontalIndent();
            case KRenderingPackage.KGRID_PLACEMENT_DATA__VERTICAL_INDENT:
                return getVerticalIndent();
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
            case KRenderingPackage.KGRID_PLACEMENT_DATA__HORIZONTAL_INDENT:
                setHorizontalIndent((Float)newValue);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__VERTICAL_INDENT:
                setVerticalIndent((Float)newValue);
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
            case KRenderingPackage.KGRID_PLACEMENT_DATA__HORIZONTAL_INDENT:
                setHorizontalIndent(HORIZONTAL_INDENT_EDEFAULT);
                return;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__VERTICAL_INDENT:
                setVerticalIndent(VERTICAL_INDENT_EDEFAULT);
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
            case KRenderingPackage.KGRID_PLACEMENT_DATA__HORIZONTAL_INDENT:
                return horizontalIndent != HORIZONTAL_INDENT_EDEFAULT;
            case KRenderingPackage.KGRID_PLACEMENT_DATA__VERTICAL_INDENT:
                return verticalIndent != VERTICAL_INDENT_EDEFAULT;
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
        result.append(", horizontalIndent: ");
        result.append(horizontalIndent);
        result.append(", verticalIndent: ");
        result.append(verticalIndent);
        result.append(')');
        return result.toString();
    }

} //KGridPlacementDataImpl
