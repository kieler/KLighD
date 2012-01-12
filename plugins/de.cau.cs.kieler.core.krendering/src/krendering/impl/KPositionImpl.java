/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package krendering.impl;

import krendering.KPosition;
import krendering.KRenderingPackage;
import krendering.KXPosition;
import krendering.KYPosition;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KPosition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link krendering.impl.KPositionImpl#getX <em>X</em>}</li>
 *   <li>{@link krendering.impl.KPositionImpl#getY <em>Y</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KPositionImpl extends EObjectImpl implements KPosition {
    /**
     * The cached value of the '{@link #getX() <em>X</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getX()
     * @generated
     * @ordered
     */
    protected KXPosition x;

    /**
     * The cached value of the '{@link #getY() <em>Y</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getY()
     * @generated
     * @ordered
     */
    protected KYPosition y;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KPositionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KPOSITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KXPosition getX() {
        return x;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetX(KXPosition newX, NotificationChain msgs) {
        KXPosition oldX = x;
        x = newX;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KRenderingPackage.KPOSITION__X, oldX, newX);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setX(KXPosition newX) {
        if (newX != x) {
            NotificationChain msgs = null;
            if (x != null)
                msgs = ((InternalEObject)x).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KPOSITION__X, null, msgs);
            if (newX != null)
                msgs = ((InternalEObject)newX).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KPOSITION__X, null, msgs);
            msgs = basicSetX(newX, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KPOSITION__X, newX, newX));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KYPosition getY() {
        return y;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetY(KYPosition newY, NotificationChain msgs) {
        KYPosition oldY = y;
        y = newY;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KRenderingPackage.KPOSITION__Y, oldY, newY);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setY(KYPosition newY) {
        if (newY != y) {
            NotificationChain msgs = null;
            if (y != null)
                msgs = ((InternalEObject)y).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KPOSITION__Y, null, msgs);
            if (newY != null)
                msgs = ((InternalEObject)newY).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KPOSITION__Y, null, msgs);
            msgs = basicSetY(newY, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KPOSITION__Y, newY, newY));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KPOSITION__X:
                return basicSetX(null, msgs);
            case KRenderingPackage.KPOSITION__Y:
                return basicSetY(null, msgs);
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
            case KRenderingPackage.KPOSITION__X:
                return getX();
            case KRenderingPackage.KPOSITION__Y:
                return getY();
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
            case KRenderingPackage.KPOSITION__X:
                setX((KXPosition)newValue);
                return;
            case KRenderingPackage.KPOSITION__Y:
                setY((KYPosition)newValue);
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
            case KRenderingPackage.KPOSITION__X:
                setX((KXPosition)null);
                return;
            case KRenderingPackage.KPOSITION__Y:
                setY((KYPosition)null);
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
            case KRenderingPackage.KPOSITION__X:
                return x != null;
            case KRenderingPackage.KPOSITION__Y:
                return y != null;
        }
        return super.eIsSet(featureID);
    }

} //KPositionImpl
