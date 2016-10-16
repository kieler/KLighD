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
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import de.cau.cs.kieler.klighd.kgraph.KGraphData;
import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.kgraph.KInsets;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KLayoutData;
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KLabel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KLabelImpl#getXpos <em>Xpos</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KLabelImpl#getYpos <em>Ypos</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KLabelImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KLabelImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KLabelImpl#getInsets <em>Insets</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KLabelImpl#getText <em>Text</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KLabelImpl#getParent <em>Parent</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KLabelImpl extends KGraphElementImpl implements KLabel {
	/**
     * The default value of the '{@link #getXpos() <em>Xpos</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getXpos()
     * @generated
     * @ordered
     */
	protected static final float XPOS_EDEFAULT = 0.0F;

	/**
     * The cached value of the '{@link #getXpos() <em>Xpos</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getXpos()
     * @generated
     * @ordered
     */
	protected float xpos = XPOS_EDEFAULT;

	/**
     * The default value of the '{@link #getYpos() <em>Ypos</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getYpos()
     * @generated
     * @ordered
     */
	protected static final float YPOS_EDEFAULT = 0.0F;

	/**
     * The cached value of the '{@link #getYpos() <em>Ypos</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getYpos()
     * @generated
     * @ordered
     */
	protected float ypos = YPOS_EDEFAULT;

	/**
     * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getWidth()
     * @generated
     * @ordered
     */
	protected static final float WIDTH_EDEFAULT = 0.0F;

	/**
     * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getWidth()
     * @generated
     * @ordered
     */
	protected float width = WIDTH_EDEFAULT;

	/**
     * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getHeight()
     * @generated
     * @ordered
     */
	protected static final float HEIGHT_EDEFAULT = 0.0F;

	/**
     * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getHeight()
     * @generated
     * @ordered
     */
	protected float height = HEIGHT_EDEFAULT;

	/**
     * The cached value of the '{@link #getInsets() <em>Insets</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInsets()
     * @generated
     * @ordered
     */
	protected KInsets insets;

	/**
     * The default value of the '{@link #getText() <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getText()
     * @generated
     * @ordered
     */
	protected static final String TEXT_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getText() <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getText()
     * @generated
     * @ordered
     */
	protected String text = TEXT_EDEFAULT;
    
    /**
     * <!-- begin-user-doc -->
     * Whether the position or size has been modified.
     * <!-- end-user-doc -->
     * @generated NOT
     */
    private boolean modified = false;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected KLabelImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return KGraphPackage.Literals.KLABEL;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public float getXpos() {
        return xpos;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setXpos(float newXpos) {
        float oldXpos = xpos;
        xpos = newXpos;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KLABEL__XPOS, oldXpos, xpos));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public float getYpos() {
        return ypos;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setYpos(float newYpos) {
        float oldYpos = ypos;
        ypos = newYpos;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KLABEL__YPOS, oldYpos, ypos));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public float getWidth() {
        return width;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setWidth(float newWidth) {
        float oldWidth = width;
        width = newWidth;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KLABEL__WIDTH, oldWidth, width));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public float getHeight() {
        return height;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setHeight(float newHeight) {
        float oldHeight = height;
        height = newHeight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KLABEL__HEIGHT, oldHeight, height));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public KInsets getInsets() {
        return insets;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetInsets(KInsets newInsets, NotificationChain msgs) {
        KInsets oldInsets = insets;
        insets = newInsets;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KGraphPackage.KLABEL__INSETS, oldInsets, newInsets);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setInsets(KInsets newInsets) {
        if (newInsets != insets) {
            NotificationChain msgs = null;
            if (insets != null)
                msgs = ((InternalEObject)insets).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KGraphPackage.KLABEL__INSETS, null, msgs);
            if (newInsets != null)
                msgs = ((InternalEObject)newInsets).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KGraphPackage.KLABEL__INSETS, null, msgs);
            msgs = basicSetInsets(newInsets, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KLABEL__INSETS, newInsets, newInsets));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getText() {
        return text;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setText(String newText) {
        String oldText = text;
        text = newText;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KLABEL__TEXT, oldText, text));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public KLabeledGraphElement getParent() {
        if (eContainerFeatureID() != KGraphPackage.KLABEL__PARENT) return null;
        return (KLabeledGraphElement)eInternalContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetParent(KLabeledGraphElement newParent, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newParent, KGraphPackage.KLABEL__PARENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setParent(KLabeledGraphElement newParent) {
        if (newParent != eInternalContainer() || (eContainerFeatureID() != KGraphPackage.KLABEL__PARENT && newParent != null)) {
            if (EcoreUtil.isAncestor(this, newParent))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newParent != null)
                msgs = ((InternalEObject)newParent).eInverseAdd(this, KGraphPackage.KLABELED_GRAPH_ELEMENT__LABELS, KLabeledGraphElement.class, msgs);
            msgs = basicSetParent(newParent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KLABEL__PARENT, newParent, newParent));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setPos(float newXpos, float newYpos) {
        float oldXpos = xpos, oldYpos = ypos;
        xpos = newXpos;
        ypos = newYpos;
        if (newXpos != oldXpos || newYpos != oldYpos) {
            modified = true;
        }
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KSHAPE_LAYOUT__YPOS, oldYpos, ypos));
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KSHAPE_LAYOUT__XPOS, oldXpos, xpos));
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated NOT
     */
    public void applyVector(KVector pos) {
        setPos((float) pos.x, (float) pos.y);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated NOT
     */
    public KVector createVector() {
        return new KVector(xpos, ypos);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setSize(float newWidth, float newHeight) {
        float oldWidth = width, oldHeight = height;
        width = newWidth;
        height = newHeight;
        if (newWidth != oldWidth || newHeight != oldHeight) {
            modified = true;
        }
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KSHAPE_LAYOUT__WIDTH, oldWidth, width));
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KSHAPE_LAYOUT__HEIGHT, oldHeight, height));
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean isModified() {
        return modified;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated NOT
     */
    public void resetModificationFlag() {
        modified = false;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KGraphPackage.KLABEL__PARENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetParent((KLabeledGraphElement)otherEnd, msgs);
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
            case KGraphPackage.KLABEL__INSETS:
                return basicSetInsets(null, msgs);
            case KGraphPackage.KLABEL__PARENT:
                return basicSetParent(null, msgs);
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
            case KGraphPackage.KLABEL__PARENT:
                return eInternalContainer().eInverseRemove(this, KGraphPackage.KLABELED_GRAPH_ELEMENT__LABELS, KLabeledGraphElement.class, msgs);
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
            case KGraphPackage.KLABEL__XPOS:
                return getXpos();
            case KGraphPackage.KLABEL__YPOS:
                return getYpos();
            case KGraphPackage.KLABEL__WIDTH:
                return getWidth();
            case KGraphPackage.KLABEL__HEIGHT:
                return getHeight();
            case KGraphPackage.KLABEL__INSETS:
                return getInsets();
            case KGraphPackage.KLABEL__TEXT:
                return getText();
            case KGraphPackage.KLABEL__PARENT:
                return getParent();
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
            case KGraphPackage.KLABEL__XPOS:
                setXpos((Float)newValue);
                return;
            case KGraphPackage.KLABEL__YPOS:
                setYpos((Float)newValue);
                return;
            case KGraphPackage.KLABEL__WIDTH:
                setWidth((Float)newValue);
                return;
            case KGraphPackage.KLABEL__HEIGHT:
                setHeight((Float)newValue);
                return;
            case KGraphPackage.KLABEL__INSETS:
                setInsets((KInsets)newValue);
                return;
            case KGraphPackage.KLABEL__TEXT:
                setText((String)newValue);
                return;
            case KGraphPackage.KLABEL__PARENT:
                setParent((KLabeledGraphElement)newValue);
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
            case KGraphPackage.KLABEL__XPOS:
                setXpos(XPOS_EDEFAULT);
                return;
            case KGraphPackage.KLABEL__YPOS:
                setYpos(YPOS_EDEFAULT);
                return;
            case KGraphPackage.KLABEL__WIDTH:
                setWidth(WIDTH_EDEFAULT);
                return;
            case KGraphPackage.KLABEL__HEIGHT:
                setHeight(HEIGHT_EDEFAULT);
                return;
            case KGraphPackage.KLABEL__INSETS:
                setInsets((KInsets)null);
                return;
            case KGraphPackage.KLABEL__TEXT:
                setText(TEXT_EDEFAULT);
                return;
            case KGraphPackage.KLABEL__PARENT:
                setParent((KLabeledGraphElement)null);
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
            case KGraphPackage.KLABEL__XPOS:
                return xpos != XPOS_EDEFAULT;
            case KGraphPackage.KLABEL__YPOS:
                return ypos != YPOS_EDEFAULT;
            case KGraphPackage.KLABEL__WIDTH:
                return width != WIDTH_EDEFAULT;
            case KGraphPackage.KLABEL__HEIGHT:
                return height != HEIGHT_EDEFAULT;
            case KGraphPackage.KLABEL__INSETS:
                return insets != null;
            case KGraphPackage.KLABEL__TEXT:
                return TEXT_EDEFAULT == null ? text != null : !TEXT_EDEFAULT.equals(text);
            case KGraphPackage.KLABEL__PARENT:
                return getParent() != null;
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == KGraphData.class) {
            switch (derivedFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == KLayoutData.class) {
            switch (derivedFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == KShapeLayout.class) {
            switch (derivedFeatureID) {
                case KGraphPackage.KLABEL__XPOS: return KGraphPackage.KSHAPE_LAYOUT__XPOS;
                case KGraphPackage.KLABEL__YPOS: return KGraphPackage.KSHAPE_LAYOUT__YPOS;
                case KGraphPackage.KLABEL__WIDTH: return KGraphPackage.KSHAPE_LAYOUT__WIDTH;
                case KGraphPackage.KLABEL__HEIGHT: return KGraphPackage.KSHAPE_LAYOUT__HEIGHT;
                case KGraphPackage.KLABEL__INSETS: return KGraphPackage.KSHAPE_LAYOUT__INSETS;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == KGraphData.class) {
            switch (baseFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == KLayoutData.class) {
            switch (baseFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == KShapeLayout.class) {
            switch (baseFeatureID) {
                case KGraphPackage.KSHAPE_LAYOUT__XPOS: return KGraphPackage.KLABEL__XPOS;
                case KGraphPackage.KSHAPE_LAYOUT__YPOS: return KGraphPackage.KLABEL__YPOS;
                case KGraphPackage.KSHAPE_LAYOUT__WIDTH: return KGraphPackage.KLABEL__WIDTH;
                case KGraphPackage.KSHAPE_LAYOUT__HEIGHT: return KGraphPackage.KLABEL__HEIGHT;
                case KGraphPackage.KSHAPE_LAYOUT__INSETS: return KGraphPackage.KLABEL__INSETS;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("KPort");
		
		// Label text
        if (text != null && text.length() > 0) {
            builder.append(" \"").append(text).append("\"");
        }
        
        // Position
        builder
			.append(" (")
			.append(xpos)
			.append(",")
			.append(ypos)
			.append(" | ")
			.append(width)
			.append(",")
			.append(height)
			.append(")");

        return builder.toString();
	}

} //KLabelImpl
