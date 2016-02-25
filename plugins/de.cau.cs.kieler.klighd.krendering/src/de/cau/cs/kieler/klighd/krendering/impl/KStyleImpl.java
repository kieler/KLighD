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

import org.eclipse.elk.graph.impl.EMapPropertyHolderImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.cau.cs.kieler.klighd.krendering.KRenderingPackage;
import de.cau.cs.kieler.klighd.krendering.KStyle;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KStyle</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KStyleImpl#isPropagateToChildren <em>Propagate To Children</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KStyleImpl#getModifierId <em>Modifier Id</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.krendering.impl.KStyleImpl#isSelection <em>Selection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class KStyleImpl extends EMapPropertyHolderImpl implements KStyle {
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
     * The default value of the '{@link #getModifierId() <em>Modifier Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModifierId()
     * @generated
     * @ordered
     */
    protected static final String MODIFIER_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getModifierId() <em>Modifier Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModifierId()
     * @generated
     * @ordered
     */
    protected String modifierId = MODIFIER_ID_EDEFAULT;

    /**
     * The default value of the '{@link #isSelection() <em>Selection</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSelection()
     * @generated
     * @ordered
     */
    protected static final boolean SELECTION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isSelection() <em>Selection</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSelection()
     * @generated
     * @ordered
     */
    protected boolean selection = SELECTION_EDEFAULT;

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
    public String getModifierId() {
        return modifierId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setModifierId(String newModifierId) {
        String oldModifierId = modifierId;
        modifierId = newModifierId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KSTYLE__MODIFIER_ID, oldModifierId, modifierId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSelection() {
        return selection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSelection(boolean newSelection) {
        boolean oldSelection = selection;
        selection = newSelection;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KRenderingPackage.KSTYLE__SELECTION, oldSelection, selection));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KRenderingPackage.KSTYLE__PROPAGATE_TO_CHILDREN:
                return isPropagateToChildren();
            case KRenderingPackage.KSTYLE__MODIFIER_ID:
                return getModifierId();
            case KRenderingPackage.KSTYLE__SELECTION:
                return isSelection();
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
            case KRenderingPackage.KSTYLE__PROPAGATE_TO_CHILDREN:
                setPropagateToChildren((Boolean)newValue);
                return;
            case KRenderingPackage.KSTYLE__MODIFIER_ID:
                setModifierId((String)newValue);
                return;
            case KRenderingPackage.KSTYLE__SELECTION:
                setSelection((Boolean)newValue);
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
            case KRenderingPackage.KSTYLE__PROPAGATE_TO_CHILDREN:
                setPropagateToChildren(PROPAGATE_TO_CHILDREN_EDEFAULT);
                return;
            case KRenderingPackage.KSTYLE__MODIFIER_ID:
                setModifierId(MODIFIER_ID_EDEFAULT);
                return;
            case KRenderingPackage.KSTYLE__SELECTION:
                setSelection(SELECTION_EDEFAULT);
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
            case KRenderingPackage.KSTYLE__PROPAGATE_TO_CHILDREN:
                return propagateToChildren != PROPAGATE_TO_CHILDREN_EDEFAULT;
            case KRenderingPackage.KSTYLE__MODIFIER_ID:
                return MODIFIER_ID_EDEFAULT == null ? modifierId != null : !MODIFIER_ID_EDEFAULT.equals(modifierId);
            case KRenderingPackage.KSTYLE__SELECTION:
                return selection != SELECTION_EDEFAULT;
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
        result.append(", modifierId: ");
        result.append(modifierId);
        result.append(", selection: ");
        result.append(selection);
        result.append(')');
        return result.toString();
    }

} //KStyleImpl
