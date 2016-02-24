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
package de.cau.cs.kieler.core.krendering.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.cau.cs.kieler.core.krendering.KPosition;
import de.cau.cs.kieler.core.krendering.KRenderingPackage;
import de.cau.cs.kieler.core.krendering.KRotation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KRotation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KRotationImpl#getRotation <em>Rotation</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.impl.KRotationImpl#getRotationAnchor <em>Rotation Anchor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KRotationImpl extends KStyleImpl implements KRotation {
    /**
     * The default value of the '{@link #getRotation() <em>Rotation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRotation()
     * @generated
     * @ordered
     */
    protected static final float ROTATION_EDEFAULT = 0.0F;

    /**
     * The cached value of the '{@link #getRotation() <em>Rotation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRotation()
     * @generated
     * @ordered
     */
    protected float rotation = ROTATION_EDEFAULT;

    /**
     * The cached value of the '{@link #getRotationAnchor() <em>Rotation Anchor</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRotationAnchor()
     * @generated
     * @ordered
     */
    protected KPosition rotationAnchor;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KRotationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KROTATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float getRotation() {
        return rotation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRotation(float newRotation) {
        float oldRotation = rotation;
        rotation = newRotation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KROTATION__ROTATION, oldRotation, rotation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPosition getRotationAnchor() {
        return rotationAnchor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRotationAnchor(KPosition newRotationAnchor, NotificationChain msgs) {
        KPosition oldRotationAnchor = rotationAnchor;
        rotationAnchor = newRotationAnchor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KRenderingPackage.KROTATION__ROTATION_ANCHOR, oldRotationAnchor, newRotationAnchor);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRotationAnchor(KPosition newRotationAnchor) {
        if (newRotationAnchor != rotationAnchor) {
            NotificationChain msgs = null;
            if (rotationAnchor != null)
                msgs = ((InternalEObject)rotationAnchor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KROTATION__ROTATION_ANCHOR, null, msgs);
            if (newRotationAnchor != null)
                msgs = ((InternalEObject)newRotationAnchor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KROTATION__ROTATION_ANCHOR, null, msgs);
            msgs = basicSetRotationAnchor(newRotationAnchor, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KROTATION__ROTATION_ANCHOR, newRotationAnchor, newRotationAnchor));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KROTATION__ROTATION_ANCHOR:
                return basicSetRotationAnchor(null, msgs);
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
            case KRenderingPackage.KROTATION__ROTATION:
                return getRotation();
            case KRenderingPackage.KROTATION__ROTATION_ANCHOR:
                return getRotationAnchor();
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
            case KRenderingPackage.KROTATION__ROTATION:
                setRotation((Float)newValue);
                return;
            case KRenderingPackage.KROTATION__ROTATION_ANCHOR:
                setRotationAnchor((KPosition)newValue);
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
            case KRenderingPackage.KROTATION__ROTATION:
                setRotation(ROTATION_EDEFAULT);
                return;
            case KRenderingPackage.KROTATION__ROTATION_ANCHOR:
                setRotationAnchor((KPosition)null);
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
            case KRenderingPackage.KROTATION__ROTATION:
                return rotation != ROTATION_EDEFAULT;
            case KRenderingPackage.KROTATION__ROTATION_ANCHOR:
                return rotationAnchor != null;
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
        result.append(" (rotation: ");
        result.append(rotation);
        result.append(')');
        return result.toString();
    }

} //KRotationImpl
