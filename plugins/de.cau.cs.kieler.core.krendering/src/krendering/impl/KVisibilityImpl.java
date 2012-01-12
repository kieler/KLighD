/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package krendering.impl;

import krendering.KRenderingPackage;
import krendering.KVisibility;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KVisibility</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link krendering.impl.KVisibilityImpl#isLineVisible <em>Line Visible</em>}</li>
 *   <li>{@link krendering.impl.KVisibilityImpl#isFilled <em>Filled</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KVisibilityImpl extends KStyleImpl implements KVisibility {
    /**
     * The default value of the '{@link #isLineVisible() <em>Line Visible</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isLineVisible()
     * @generated
     * @ordered
     */
    protected static final boolean LINE_VISIBLE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isLineVisible() <em>Line Visible</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isLineVisible()
     * @generated
     * @ordered
     */
    protected boolean lineVisible = LINE_VISIBLE_EDEFAULT;

    /**
     * The default value of the '{@link #isFilled() <em>Filled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isFilled()
     * @generated
     * @ordered
     */
    protected static final boolean FILLED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isFilled() <em>Filled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isFilled()
     * @generated
     * @ordered
     */
    protected boolean filled = FILLED_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KVisibilityImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KVISIBILITY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isLineVisible() {
        return lineVisible;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLineVisible(boolean newLineVisible) {
        boolean oldLineVisible = lineVisible;
        lineVisible = newLineVisible;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KVISIBILITY__LINE_VISIBLE, oldLineVisible, lineVisible));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFilled(boolean newFilled) {
        boolean oldFilled = filled;
        filled = newFilled;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KVISIBILITY__FILLED, oldFilled, filled));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KVISIBILITY__LINE_VISIBLE:
                return isLineVisible();
            case KRenderingPackage.KVISIBILITY__FILLED:
                return isFilled();
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
            case KRenderingPackage.KVISIBILITY__LINE_VISIBLE:
                setLineVisible((Boolean)newValue);
                return;
            case KRenderingPackage.KVISIBILITY__FILLED:
                setFilled((Boolean)newValue);
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
            case KRenderingPackage.KVISIBILITY__LINE_VISIBLE:
                setLineVisible(LINE_VISIBLE_EDEFAULT);
                return;
            case KRenderingPackage.KVISIBILITY__FILLED:
                setFilled(FILLED_EDEFAULT);
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
            case KRenderingPackage.KVISIBILITY__LINE_VISIBLE:
                return lineVisible != LINE_VISIBLE_EDEFAULT;
            case KRenderingPackage.KVISIBILITY__FILLED:
                return filled != FILLED_EDEFAULT;
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
        result.append(" (lineVisible: ");
        result.append(lineVisible);
        result.append(", filled: ");
        result.append(filled);
        result.append(')');
        return result.toString();
    }

} //KVisibilityImpl
