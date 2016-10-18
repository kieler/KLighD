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

import java.util.Collection;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.kgraph.KInsets;
import de.cau.cs.kieler.klighd.kgraph.KLayoutData;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPort;
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KPort</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KPortImpl#getXpos <em>Xpos</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KPortImpl#getYpos <em>Ypos</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KPortImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KPortImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KPortImpl#getInsets <em>Insets</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KPortImpl#getNode <em>Node</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KPortImpl#getEdges <em>Edges</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KPortImpl extends KLabeledGraphElementImpl implements KPort {
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
     * The cached value of the '{@link #getEdges() <em>Edges</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getEdges()
     * @generated
     * @ordered
     */
	protected EList<KEdge> edges;
    
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
	protected KPortImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return KGraphPackage.Literals.KPORT;
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
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KPORT__XPOS, oldXpos, xpos));
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
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KPORT__YPOS, oldYpos, ypos));
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
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KPORT__WIDTH, oldWidth, width));
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
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KPORT__HEIGHT, oldHeight, height));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KGraphPackage.KPORT__INSETS, oldInsets, newInsets);
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
                msgs = ((InternalEObject)insets).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KGraphPackage.KPORT__INSETS, null, msgs);
            if (newInsets != null)
                msgs = ((InternalEObject)newInsets).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KGraphPackage.KPORT__INSETS, null, msgs);
            msgs = basicSetInsets(newInsets, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KPORT__INSETS, newInsets, newInsets));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public KNode getNode() {
        if (eContainerFeatureID() != KGraphPackage.KPORT__NODE) return null;
        return (KNode)eInternalContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetNode(KNode newNode, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newNode, KGraphPackage.KPORT__NODE, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setNode(KNode newNode) {
        if (newNode != eInternalContainer() || (eContainerFeatureID() != KGraphPackage.KPORT__NODE && newNode != null)) {
            if (EcoreUtil.isAncestor(this, newNode))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newNode != null)
                msgs = ((InternalEObject)newNode).eInverseAdd(this, KGraphPackage.KNODE__PORTS, KNode.class, msgs);
            msgs = basicSetNode(newNode, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KPORT__NODE, newNode, newNode));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<KEdge> getEdges() {
        if (edges == null) {
            edges = new EObjectResolvingEList<KEdge>(KEdge.class, this, KGraphPackage.KPORT__EDGES);
        }
        return edges;
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
            case KGraphPackage.KPORT__NODE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetNode((KNode)otherEnd, msgs);
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
            case KGraphPackage.KPORT__INSETS:
                return basicSetInsets(null, msgs);
            case KGraphPackage.KPORT__NODE:
                return basicSetNode(null, msgs);
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
            case KGraphPackage.KPORT__NODE:
                return eInternalContainer().eInverseRemove(this, KGraphPackage.KNODE__PORTS, KNode.class, msgs);
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
            case KGraphPackage.KPORT__XPOS:
                return getXpos();
            case KGraphPackage.KPORT__YPOS:
                return getYpos();
            case KGraphPackage.KPORT__WIDTH:
                return getWidth();
            case KGraphPackage.KPORT__HEIGHT:
                return getHeight();
            case KGraphPackage.KPORT__INSETS:
                return getInsets();
            case KGraphPackage.KPORT__NODE:
                return getNode();
            case KGraphPackage.KPORT__EDGES:
                return getEdges();
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
            case KGraphPackage.KPORT__XPOS:
                setXpos((Float)newValue);
                return;
            case KGraphPackage.KPORT__YPOS:
                setYpos((Float)newValue);
                return;
            case KGraphPackage.KPORT__WIDTH:
                setWidth((Float)newValue);
                return;
            case KGraphPackage.KPORT__HEIGHT:
                setHeight((Float)newValue);
                return;
            case KGraphPackage.KPORT__INSETS:
                setInsets((KInsets)newValue);
                return;
            case KGraphPackage.KPORT__NODE:
                setNode((KNode)newValue);
                return;
            case KGraphPackage.KPORT__EDGES:
                getEdges().clear();
                getEdges().addAll((Collection<? extends KEdge>)newValue);
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
            case KGraphPackage.KPORT__XPOS:
                setXpos(XPOS_EDEFAULT);
                return;
            case KGraphPackage.KPORT__YPOS:
                setYpos(YPOS_EDEFAULT);
                return;
            case KGraphPackage.KPORT__WIDTH:
                setWidth(WIDTH_EDEFAULT);
                return;
            case KGraphPackage.KPORT__HEIGHT:
                setHeight(HEIGHT_EDEFAULT);
                return;
            case KGraphPackage.KPORT__INSETS:
                setInsets((KInsets)null);
                return;
            case KGraphPackage.KPORT__NODE:
                setNode((KNode)null);
                return;
            case KGraphPackage.KPORT__EDGES:
                getEdges().clear();
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
            case KGraphPackage.KPORT__XPOS:
                return xpos != XPOS_EDEFAULT;
            case KGraphPackage.KPORT__YPOS:
                return ypos != YPOS_EDEFAULT;
            case KGraphPackage.KPORT__WIDTH:
                return width != WIDTH_EDEFAULT;
            case KGraphPackage.KPORT__HEIGHT:
                return height != HEIGHT_EDEFAULT;
            case KGraphPackage.KPORT__INSETS:
                return insets != null;
            case KGraphPackage.KPORT__NODE:
                return getNode() != null;
            case KGraphPackage.KPORT__EDGES:
                return edges != null && !edges.isEmpty();
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
        if (baseClass == KLayoutData.class) {
            switch (derivedFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == KShapeLayout.class) {
            switch (derivedFeatureID) {
                case KGraphPackage.KPORT__XPOS: return KGraphPackage.KSHAPE_LAYOUT__XPOS;
                case KGraphPackage.KPORT__YPOS: return KGraphPackage.KSHAPE_LAYOUT__YPOS;
                case KGraphPackage.KPORT__WIDTH: return KGraphPackage.KSHAPE_LAYOUT__WIDTH;
                case KGraphPackage.KPORT__HEIGHT: return KGraphPackage.KSHAPE_LAYOUT__HEIGHT;
                case KGraphPackage.KPORT__INSETS: return KGraphPackage.KSHAPE_LAYOUT__INSETS;
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
        if (baseClass == KLayoutData.class) {
            switch (baseFeatureID) {
                default: return -1;
            }
        }
        if (baseClass == KShapeLayout.class) {
            switch (baseFeatureID) {
                case KGraphPackage.KSHAPE_LAYOUT__XPOS: return KGraphPackage.KPORT__XPOS;
                case KGraphPackage.KSHAPE_LAYOUT__YPOS: return KGraphPackage.KPORT__YPOS;
                case KGraphPackage.KSHAPE_LAYOUT__WIDTH: return KGraphPackage.KPORT__WIDTH;
                case KGraphPackage.KSHAPE_LAYOUT__HEIGHT: return KGraphPackage.KPORT__HEIGHT;
                case KGraphPackage.KSHAPE_LAYOUT__INSETS: return KGraphPackage.KPORT__INSETS;
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
		
		// Labels
        if (getLabels().size() > 0) {
            String text = getLabels().get(0).getText();
            if (text != null && text.length() > 0) {
                builder.append(" \"").append(text).append("\"");
            }
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

} //KPortImpl
