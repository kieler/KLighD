/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.kgraph.impl;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.kgraph.KPoint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KPoint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KPointImpl#getX <em>X</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KPointImpl#getY <em>Y</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KPointImpl extends EObjectImpl implements KPoint {
	/**
     * The default value of the '{@link #getX() <em>X</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getX()
     * @generated
     * @ordered
     */
	protected static final float X_EDEFAULT = 0.0F;

	/**
     * The cached value of the '{@link #getX() <em>X</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getX()
     * @generated
     * @ordered
     */
	protected float x = X_EDEFAULT;

	/**
     * The default value of the '{@link #getY() <em>Y</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getY()
     * @generated
     * @ordered
     */
	protected static final float Y_EDEFAULT = 0.0F;

	/**
     * The cached value of the '{@link #getY() <em>Y</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getY()
     * @generated
     * @ordered
     */
	protected float y = Y_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected KPointImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return KGraphPackage.Literals.KPOINT;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public float getX() {
        return x;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setX(float newX) {
        float oldX = x;
        x = newX;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KPOINT__X, oldX, x));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public float getY() {
        return y;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setY(float newY) {
        float oldY = y;
        y = newY;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KPOINT__Y, oldY, y));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setPos(float newX, float newY) {
        float oldX = x, oldY = y;
        x = newX;
        y = newY;
        if ((newX != oldX || newY != oldY) && eContainer instanceof KEdgeImpl) {
            ((KEdgeImpl) eContainer).modified = true;
        }
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KPOINT__X, oldX, x));
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KPOINT__Y, oldY, y));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * {@inheritDoc}
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void applyVector(KVector pos) {
        setPos((float) pos.x, (float) pos.y);
    }

    /**
     * <!-- begin-user-doc -->
     * {@inheritDoc}
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public KVector createVector() {
        return new KVector(x, y);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KGraphPackage.KPOINT__X:
                return getX();
            case KGraphPackage.KPOINT__Y:
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
            case KGraphPackage.KPOINT__X:
                setX((Float)newValue);
                return;
            case KGraphPackage.KPOINT__Y:
                setY((Float)newValue);
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
            case KGraphPackage.KPOINT__X:
                setX(X_EDEFAULT);
                return;
            case KGraphPackage.KPOINT__Y:
                setY(Y_EDEFAULT);
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
            case KGraphPackage.KPOINT__X:
                return x != X_EDEFAULT;
            case KGraphPackage.KPOINT__Y:
                return y != Y_EDEFAULT;
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
        result.append(" (x: ");
        result.append(x);
        result.append(", y: ");
        result.append(y);
        result.append(')');
        return result.toString();
    }

} //KPointImpl
