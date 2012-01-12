/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package krendering.impl;

import java.util.Collection;

import krendering.KContainerRendering;
import krendering.KPlacement;
import krendering.KRendering;
import krendering.KRenderingPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KContainer Rendering</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link krendering.impl.KContainerRenderingImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link krendering.impl.KContainerRenderingImpl#getChildPlacement <em>Child Placement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class KContainerRenderingImpl extends KRenderingImpl implements KContainerRendering {
    /**
     * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildren()
     * @generated
     * @ordered
     */
    protected EList<KRendering> children;

    /**
     * The cached value of the '{@link #getChildPlacement() <em>Child Placement</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildPlacement()
     * @generated
     * @ordered
     */
    protected KPlacement childPlacement;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KContainerRenderingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return KRenderingPackage.Literals.KCONTAINER_RENDERING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<KRendering> getChildren() {
        if (children == null) {
            children = new EObjectContainmentWithInverseEList<KRendering>(KRendering.class, this, KRenderingPackage.KCONTAINER_RENDERING__CHILDREN, KRenderingPackage.KRENDERING__PARENT);
        }
        return children;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPlacement getChildPlacement() {
        return childPlacement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetChildPlacement(KPlacement newChildPlacement, NotificationChain msgs) {
        KPlacement oldChildPlacement = childPlacement;
        childPlacement = newChildPlacement;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KRenderingPackage.KCONTAINER_RENDERING__CHILD_PLACEMENT, oldChildPlacement, newChildPlacement);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChildPlacement(KPlacement newChildPlacement) {
        if (newChildPlacement != childPlacement) {
            NotificationChain msgs = null;
            if (childPlacement != null)
                msgs = ((InternalEObject)childPlacement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KCONTAINER_RENDERING__CHILD_PLACEMENT, null, msgs);
            if (newChildPlacement != null)
                msgs = ((InternalEObject)newChildPlacement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KRenderingPackage.KCONTAINER_RENDERING__CHILD_PLACEMENT, null, msgs);
            msgs = basicSetChildPlacement(newChildPlacement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KCONTAINER_RENDERING__CHILD_PLACEMENT, newChildPlacement, newChildPlacement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KRenderingPackage.KCONTAINER_RENDERING__CHILDREN:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
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
            case KRenderingPackage.KCONTAINER_RENDERING__CHILDREN:
                return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
            case KRenderingPackage.KCONTAINER_RENDERING__CHILD_PLACEMENT:
                return basicSetChildPlacement(null, msgs);
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
            case KRenderingPackage.KCONTAINER_RENDERING__CHILDREN:
                return getChildren();
            case KRenderingPackage.KCONTAINER_RENDERING__CHILD_PLACEMENT:
                return getChildPlacement();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case KRenderingPackage.KCONTAINER_RENDERING__CHILDREN:
                getChildren().clear();
                getChildren().addAll((Collection<? extends KRendering>)newValue);
                return;
            case KRenderingPackage.KCONTAINER_RENDERING__CHILD_PLACEMENT:
                setChildPlacement((KPlacement)newValue);
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
            case KRenderingPackage.KCONTAINER_RENDERING__CHILDREN:
                getChildren().clear();
                return;
            case KRenderingPackage.KCONTAINER_RENDERING__CHILD_PLACEMENT:
                setChildPlacement((KPlacement)null);
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
            case KRenderingPackage.KCONTAINER_RENDERING__CHILDREN:
                return children != null && !children.isEmpty();
            case KRenderingPackage.KCONTAINER_RENDERING__CHILD_PLACEMENT:
                return childPlacement != null;
        }
        return super.eIsSet(featureID);
    }

} //KContainerRenderingImpl
