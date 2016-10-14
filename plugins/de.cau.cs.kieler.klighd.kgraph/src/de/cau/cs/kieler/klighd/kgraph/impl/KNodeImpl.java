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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphData;
import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.kgraph.KInsets;
import de.cau.cs.kieler.klighd.kgraph.KLayoutData;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPort;
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KNode</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KNodeImpl#getXpos <em>Xpos</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KNodeImpl#getYpos <em>Ypos</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KNodeImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KNodeImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KNodeImpl#getInsets <em>Insets</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KNodeImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KNodeImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KNodeImpl#getPorts <em>Ports</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KNodeImpl#getOutgoingEdges <em>Outgoing Edges</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KNodeImpl#getIncomingEdges <em>Incoming Edges</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KNodeImpl extends KLabeledGraphElementImpl implements KNode {
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
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<KNode> children;

	/**
	 * The cached value of the '{@link #getPorts() <em>Ports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPorts()
	 * @generated
	 * @ordered
	 */
	protected EList<KPort> ports;

	/**
	 * The cached value of the '{@link #getOutgoingEdges() <em>Outgoing Edges</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutgoingEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<KEdge> outgoingEdges;

	/**
	 * The cached value of the '{@link #getIncomingEdges() <em>Incoming Edges</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncomingEdges()
	 * @generated
	 * @ordered
	 */
	protected EList<KEdge> incomingEdges;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected KNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KGraphPackage.Literals.KNODE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KNODE__XPOS, oldXpos, xpos));
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
			eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KNODE__YPOS, oldYpos, ypos));
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
			eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KNODE__WIDTH, oldWidth, width));
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
			eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KNODE__HEIGHT, oldHeight, height));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KGraphPackage.KNODE__INSETS, oldInsets, newInsets);
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
				msgs = ((InternalEObject)insets).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KGraphPackage.KNODE__INSETS, null, msgs);
			if (newInsets != null)
				msgs = ((InternalEObject)newInsets).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KGraphPackage.KNODE__INSETS, null, msgs);
			msgs = basicSetInsets(newInsets, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KNODE__INSETS, newInsets, newInsets));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<KNode> getChildren() {
		if (children == null) {
			children = new EObjectContainmentWithInverseEList<KNode>(KNode.class, this, KGraphPackage.KNODE__CHILDREN, KGraphPackage.KNODE__PARENT);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KNode getParent() {
		if (eContainerFeatureID() != KGraphPackage.KNODE__PARENT) return null;
		return (KNode)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(KNode newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, KGraphPackage.KNODE__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(KNode newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != KGraphPackage.KNODE__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, KGraphPackage.KNODE__CHILDREN, KNode.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KNODE__PARENT, newParent, newParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<KPort> getPorts() {
		if (ports == null) {
			ports = new EObjectContainmentWithInverseEList<KPort>(KPort.class, this, KGraphPackage.KNODE__PORTS, KGraphPackage.KPORT__NODE);
		}
		return ports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<KEdge> getOutgoingEdges() {
		if (outgoingEdges == null) {
			outgoingEdges = new EObjectContainmentWithInverseEList<KEdge>(KEdge.class, this, KGraphPackage.KNODE__OUTGOING_EDGES, KGraphPackage.KEDGE__SOURCE);
		}
		return outgoingEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<KEdge> getIncomingEdges() {
		if (incomingEdges == null) {
			incomingEdges = new EObjectWithInverseResolvingEList<KEdge>(KEdge.class, this, KGraphPackage.KNODE__INCOMING_EDGES, KGraphPackage.KEDGE__TARGET);
		}
		return incomingEdges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPos(float x, float y) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void applyVector(KVector pos) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KVector createVector() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSize(float width, float height) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isModified() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void resetModificationFlag() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
			case KGraphPackage.KNODE__CHILDREN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
			case KGraphPackage.KNODE__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((KNode)otherEnd, msgs);
			case KGraphPackage.KNODE__PORTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPorts()).basicAdd(otherEnd, msgs);
			case KGraphPackage.KNODE__OUTGOING_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingEdges()).basicAdd(otherEnd, msgs);
			case KGraphPackage.KNODE__INCOMING_EDGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingEdges()).basicAdd(otherEnd, msgs);
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
			case KGraphPackage.KNODE__INSETS:
				return basicSetInsets(null, msgs);
			case KGraphPackage.KNODE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case KGraphPackage.KNODE__PARENT:
				return basicSetParent(null, msgs);
			case KGraphPackage.KNODE__PORTS:
				return ((InternalEList<?>)getPorts()).basicRemove(otherEnd, msgs);
			case KGraphPackage.KNODE__OUTGOING_EDGES:
				return ((InternalEList<?>)getOutgoingEdges()).basicRemove(otherEnd, msgs);
			case KGraphPackage.KNODE__INCOMING_EDGES:
				return ((InternalEList<?>)getIncomingEdges()).basicRemove(otherEnd, msgs);
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
			case KGraphPackage.KNODE__PARENT:
				return eInternalContainer().eInverseRemove(this, KGraphPackage.KNODE__CHILDREN, KNode.class, msgs);
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
			case KGraphPackage.KNODE__XPOS:
				return getXpos();
			case KGraphPackage.KNODE__YPOS:
				return getYpos();
			case KGraphPackage.KNODE__WIDTH:
				return getWidth();
			case KGraphPackage.KNODE__HEIGHT:
				return getHeight();
			case KGraphPackage.KNODE__INSETS:
				return getInsets();
			case KGraphPackage.KNODE__CHILDREN:
				return getChildren();
			case KGraphPackage.KNODE__PARENT:
				return getParent();
			case KGraphPackage.KNODE__PORTS:
				return getPorts();
			case KGraphPackage.KNODE__OUTGOING_EDGES:
				return getOutgoingEdges();
			case KGraphPackage.KNODE__INCOMING_EDGES:
				return getIncomingEdges();
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
			case KGraphPackage.KNODE__XPOS:
				setXpos((Float)newValue);
				return;
			case KGraphPackage.KNODE__YPOS:
				setYpos((Float)newValue);
				return;
			case KGraphPackage.KNODE__WIDTH:
				setWidth((Float)newValue);
				return;
			case KGraphPackage.KNODE__HEIGHT:
				setHeight((Float)newValue);
				return;
			case KGraphPackage.KNODE__INSETS:
				setInsets((KInsets)newValue);
				return;
			case KGraphPackage.KNODE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends KNode>)newValue);
				return;
			case KGraphPackage.KNODE__PARENT:
				setParent((KNode)newValue);
				return;
			case KGraphPackage.KNODE__PORTS:
				getPorts().clear();
				getPorts().addAll((Collection<? extends KPort>)newValue);
				return;
			case KGraphPackage.KNODE__OUTGOING_EDGES:
				getOutgoingEdges().clear();
				getOutgoingEdges().addAll((Collection<? extends KEdge>)newValue);
				return;
			case KGraphPackage.KNODE__INCOMING_EDGES:
				getIncomingEdges().clear();
				getIncomingEdges().addAll((Collection<? extends KEdge>)newValue);
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
			case KGraphPackage.KNODE__XPOS:
				setXpos(XPOS_EDEFAULT);
				return;
			case KGraphPackage.KNODE__YPOS:
				setYpos(YPOS_EDEFAULT);
				return;
			case KGraphPackage.KNODE__WIDTH:
				setWidth(WIDTH_EDEFAULT);
				return;
			case KGraphPackage.KNODE__HEIGHT:
				setHeight(HEIGHT_EDEFAULT);
				return;
			case KGraphPackage.KNODE__INSETS:
				setInsets((KInsets)null);
				return;
			case KGraphPackage.KNODE__CHILDREN:
				getChildren().clear();
				return;
			case KGraphPackage.KNODE__PARENT:
				setParent((KNode)null);
				return;
			case KGraphPackage.KNODE__PORTS:
				getPorts().clear();
				return;
			case KGraphPackage.KNODE__OUTGOING_EDGES:
				getOutgoingEdges().clear();
				return;
			case KGraphPackage.KNODE__INCOMING_EDGES:
				getIncomingEdges().clear();
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
			case KGraphPackage.KNODE__XPOS:
				return xpos != XPOS_EDEFAULT;
			case KGraphPackage.KNODE__YPOS:
				return ypos != YPOS_EDEFAULT;
			case KGraphPackage.KNODE__WIDTH:
				return width != WIDTH_EDEFAULT;
			case KGraphPackage.KNODE__HEIGHT:
				return height != HEIGHT_EDEFAULT;
			case KGraphPackage.KNODE__INSETS:
				return insets != null;
			case KGraphPackage.KNODE__CHILDREN:
				return children != null && !children.isEmpty();
			case KGraphPackage.KNODE__PARENT:
				return getParent() != null;
			case KGraphPackage.KNODE__PORTS:
				return ports != null && !ports.isEmpty();
			case KGraphPackage.KNODE__OUTGOING_EDGES:
				return outgoingEdges != null && !outgoingEdges.isEmpty();
			case KGraphPackage.KNODE__INCOMING_EDGES:
				return incomingEdges != null && !incomingEdges.isEmpty();
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
				case KGraphPackage.KNODE__XPOS: return KGraphPackage.KSHAPE_LAYOUT__XPOS;
				case KGraphPackage.KNODE__YPOS: return KGraphPackage.KSHAPE_LAYOUT__YPOS;
				case KGraphPackage.KNODE__WIDTH: return KGraphPackage.KSHAPE_LAYOUT__WIDTH;
				case KGraphPackage.KNODE__HEIGHT: return KGraphPackage.KSHAPE_LAYOUT__HEIGHT;
				case KGraphPackage.KNODE__INSETS: return KGraphPackage.KSHAPE_LAYOUT__INSETS;
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
				case KGraphPackage.KSHAPE_LAYOUT__XPOS: return KGraphPackage.KNODE__XPOS;
				case KGraphPackage.KSHAPE_LAYOUT__YPOS: return KGraphPackage.KNODE__YPOS;
				case KGraphPackage.KSHAPE_LAYOUT__WIDTH: return KGraphPackage.KNODE__WIDTH;
				case KGraphPackage.KSHAPE_LAYOUT__HEIGHT: return KGraphPackage.KNODE__HEIGHT;
				case KGraphPackage.KSHAPE_LAYOUT__INSETS: return KGraphPackage.KNODE__INSETS;
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
		StringBuilder builder = new StringBuilder("KNode");
		
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

} //KNodeImpl
