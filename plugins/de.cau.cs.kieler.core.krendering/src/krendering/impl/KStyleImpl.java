/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package krendering.impl;

import krendering.KRendering;
import krendering.KRenderingPackage;
import krendering.KStyle;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KStyle</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link krendering.impl.KStyleImpl#getRendering <em>Rendering</em>}</li>
 *   <li>{@link krendering.impl.KStyleImpl#isPropagateToChildren <em>Propagate To Children</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KStyleImpl extends EObjectImpl implements KStyle {
    /**
     * The default value of the '{@link #isPropagateToChildren() <em>Propagate To Children</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isPropagateToChildren()
     * @generated
     * @ordered
     */
    protected static final boolean PROPAGATE_TO_CHILDREN_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isPropagateToChildren() <em>Propagate To Children</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isPropagateToChildren()
     * @generated
     * @ordered
     */
    protected boolean propagateToChildren = PROPAGATE_TO_CHILDREN_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KSTYLE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KRendering getRendering() {
        if (eContainerFeatureID() != KRenderingPackage.KSTYLE__RENDERING) return null;
        return (KRendering)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRendering(KRendering newRendering, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newRendering, KRenderingPackage.KSTYLE__RENDERING, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRendering(KRendering newRendering) {
        if (newRendering != eInternalContainer() || (eContainerFeatureID() != KRenderingPackage.KSTYLE__RENDERING && newRendering != null)) {
            if (EcoreUtil.isAncestor(this, newRendering))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newRendering != null)
                msgs = ((InternalEObject)newRendering).eInverseAdd(this, KRenderingPackage.KRENDERING__STYLES, KRendering.class, msgs);
            msgs = basicSetRendering(newRendering, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KSTYLE__RENDERING, newRendering, newRendering));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isPropagateToChildren() {
        return propagateToChildren;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPropagateToChildren(boolean newPropagateToChildren) {
        boolean oldPropagateToChildren = propagateToChildren;
        propagateToChildren = newPropagateToChildren;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KSTYLE__PROPAGATE_TO_CHILDREN, oldPropagateToChildren, propagateToChildren));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KSTYLE__RENDERING:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetRendering((KRendering)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KSTYLE__RENDERING:
                return basicSetRendering(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case KRenderingPackage.KSTYLE__RENDERING:
                return eInternalContainer().eInverseRemove(this, KRenderingPackage.KRENDERING__STYLES, KRendering.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KSTYLE__RENDERING:
                return getRendering();
            case KRenderingPackage.KSTYLE__PROPAGATE_TO_CHILDREN:
                return isPropagateToChildren();
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
            case KRenderingPackage.KSTYLE__RENDERING:
                setRendering((KRendering)newValue);
                return;
            case KRenderingPackage.KSTYLE__PROPAGATE_TO_CHILDREN:
                setPropagateToChildren((Boolean)newValue);
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
            case KRenderingPackage.KSTYLE__RENDERING:
                setRendering((KRendering)null);
                return;
            case KRenderingPackage.KSTYLE__PROPAGATE_TO_CHILDREN:
                setPropagateToChildren(PROPAGATE_TO_CHILDREN_EDEFAULT);
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
            case KRenderingPackage.KSTYLE__RENDERING:
                return getRendering() != null;
            case KRenderingPackage.KSTYLE__PROPAGATE_TO_CHILDREN:
                return propagateToChildren != PROPAGATE_TO_CHILDREN_EDEFAULT;
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
        result.append(" (propagateToChildren: ");
        result.append(propagateToChildren);
        result.append(')');
        return result.toString();
    }

} //KStyleImpl
