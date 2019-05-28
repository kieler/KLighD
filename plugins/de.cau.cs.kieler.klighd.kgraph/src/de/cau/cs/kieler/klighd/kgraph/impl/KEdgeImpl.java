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
import java.util.ListIterator;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KEdgeLayout;
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory;
import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.kgraph.KLayoutData;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPoint;
import de.cau.cs.kieler.klighd.kgraph.KPort;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KEdge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KEdgeImpl#getBendPoints <em>Bend Points</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KEdgeImpl#getSourcePoint <em>Source Point</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KEdgeImpl#getTargetPoint <em>Target Point</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KEdgeImpl#getSource <em>Source</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KEdgeImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KEdgeImpl#getSourcePort <em>Source Port</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kgraph.impl.KEdgeImpl#getTargetPort <em>Target Port</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KEdgeImpl extends KLabeledGraphElementImpl implements KEdge {
	/**
     * The cached value of the '{@link #getBendPoints() <em>Bend Points</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getBendPoints()
     * @generated
     * @ordered
     */
	protected EList<KPoint> bendPoints;

	/**
     * The cached value of the '{@link #getSourcePoint() <em>Source Point</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSourcePoint()
     * @generated
     * @ordered
     */
	protected KPoint sourcePoint;

	/**
     * The cached value of the '{@link #getTargetPoint() <em>Target Point</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTargetPoint()
     * @generated
     * @ordered
     */
	protected KPoint targetPoint;

	/**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
	protected KNode target;

	/**
     * The cached value of the '{@link #getSourcePort() <em>Source Port</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSourcePort()
     * @generated
     * @ordered
     */
	protected KPort sourcePort;

	/**
     * The cached value of the '{@link #getTargetPort() <em>Target Port</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTargetPort()
     * @generated
     * @ordered
     */
	protected KPort targetPort;
    
    /**
     * <!-- begin-user-doc -->
     * Whether the position or size has been modified.
     * <!-- end-user-doc -->
     * @generated NOT
     */
    protected boolean modified = false;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected KEdgeImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return KGraphPackage.Literals.KEDGE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<KPoint> getBendPoints() {
        if (bendPoints == null) {
            bendPoints = new EObjectContainmentEList<KPoint>(KPoint.class, this, KGraphPackage.KEDGE__BEND_POINTS);
        }
        return bendPoints;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public KPoint getSourcePoint() {
        return sourcePoint;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetSourcePoint(KPoint newSourcePoint, NotificationChain msgs) {
        KPoint oldSourcePoint = sourcePoint;
        sourcePoint = newSourcePoint;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KGraphPackage.KEDGE__SOURCE_POINT, oldSourcePoint, newSourcePoint);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSourcePoint(KPoint newSourcePoint) {
        if (newSourcePoint != sourcePoint) {
            NotificationChain msgs = null;
            if (sourcePoint != null)
                msgs = ((InternalEObject)sourcePoint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KGraphPackage.KEDGE__SOURCE_POINT, null, msgs);
            if (newSourcePoint != null)
                msgs = ((InternalEObject)newSourcePoint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KGraphPackage.KEDGE__SOURCE_POINT, null, msgs);
            msgs = basicSetSourcePoint(newSourcePoint, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KEDGE__SOURCE_POINT, newSourcePoint, newSourcePoint));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public KPoint getTargetPoint() {
        return targetPoint;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTargetPoint(KPoint newTargetPoint, NotificationChain msgs) {
        KPoint oldTargetPoint = targetPoint;
        targetPoint = newTargetPoint;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KGraphPackage.KEDGE__TARGET_POINT, oldTargetPoint, newTargetPoint);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setTargetPoint(KPoint newTargetPoint) {
        if (newTargetPoint != targetPoint) {
            NotificationChain msgs = null;
            if (targetPoint != null)
                msgs = ((InternalEObject)targetPoint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KGraphPackage.KEDGE__TARGET_POINT, null, msgs);
            if (newTargetPoint != null)
                msgs = ((InternalEObject)newTargetPoint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KGraphPackage.KEDGE__TARGET_POINT, null, msgs);
            msgs = basicSetTargetPoint(newTargetPoint, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KEDGE__TARGET_POINT, newTargetPoint, newTargetPoint));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public KNode getSource() {
        if (eContainerFeatureID() != KGraphPackage.KEDGE__SOURCE) return null;
        return (KNode)eInternalContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetSource(KNode newSource, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newSource, KGraphPackage.KEDGE__SOURCE, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSource(KNode newSource) {
        if (newSource != eInternalContainer() || (eContainerFeatureID() != KGraphPackage.KEDGE__SOURCE && newSource != null)) {
            if (EcoreUtil.isAncestor(this, newSource))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newSource != null)
                msgs = ((InternalEObject)newSource).eInverseAdd(this, KGraphPackage.KNODE__OUTGOING_EDGES, KNode.class, msgs);
            msgs = basicSetSource(newSource, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KEDGE__SOURCE, newSource, newSource));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public KNode getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject)target;
            target = (KNode)eResolveProxy(oldTarget);
            if (target != oldTarget) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, KGraphPackage.KEDGE__TARGET, oldTarget, target));
            }
        }
        return target;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public KNode basicGetTarget() {
        return target;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTarget(KNode newTarget, NotificationChain msgs) {
        KNode oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KGraphPackage.KEDGE__TARGET, oldTarget, newTarget);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setTarget(KNode newTarget) {
        if (newTarget != target) {
            NotificationChain msgs = null;
            if (target != null)
                msgs = ((InternalEObject)target).eInverseRemove(this, KGraphPackage.KNODE__INCOMING_EDGES, KNode.class, msgs);
            if (newTarget != null)
                msgs = ((InternalEObject)newTarget).eInverseAdd(this, KGraphPackage.KNODE__INCOMING_EDGES, KNode.class, msgs);
            msgs = basicSetTarget(newTarget, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KEDGE__TARGET, newTarget, newTarget));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public KPort getSourcePort() {
        if (sourcePort != null && sourcePort.eIsProxy()) {
            InternalEObject oldSourcePort = (InternalEObject)sourcePort;
            sourcePort = (KPort)eResolveProxy(oldSourcePort);
            if (sourcePort != oldSourcePort) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, KGraphPackage.KEDGE__SOURCE_PORT, oldSourcePort, sourcePort));
            }
        }
        return sourcePort;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public KPort basicGetSourcePort() {
        return sourcePort;
    }

	/**
	 * <!-- begin-user-doc -->
     * The original generated code is extended so the {@link KPort#getEdges()} list is also updated.
     * <!-- end-user-doc -->
     * @generated NOT
	 */
	public void setSourcePort(KPort newSourcePort) {
        KPort oldSourcePort = sourcePort;
        sourcePort = newSourcePort;
        if (oldSourcePort != null && oldSourcePort != targetPort) {
            oldSourcePort.getEdges().remove(this);
        }
        if (newSourcePort != null && !newSourcePort.getEdges().contains(this)) {
            newSourcePort.getEdges().add(this);
        }
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KEDGE__SOURCE_PORT, oldSourcePort, sourcePort));
        }
	}

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public KPort getTargetPort() {
        if (targetPort != null && targetPort.eIsProxy()) {
            InternalEObject oldTargetPort = (InternalEObject)targetPort;
            targetPort = (KPort)eResolveProxy(oldTargetPort);
            if (targetPort != oldTargetPort) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, KGraphPackage.KEDGE__TARGET_PORT, oldTargetPort, targetPort));
            }
        }
        return targetPort;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public KPort basicGetTargetPort() {
        return targetPort;
    }

	/**
	 * <!-- begin-user-doc -->
     * The original generated code is extended so the {@link KPort#getEdges()} list is also updated.
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setTargetPort(KPort newTargetPort) {
        KPort oldTargetPort = targetPort;
        targetPort = newTargetPort;
        if (oldTargetPort != null && oldTargetPort != sourcePort) {
            oldTargetPort.getEdges().remove(this);
        }
        if (newTargetPort != null && !newTargetPort.getEdges().contains(this)) {
            newTargetPort.getEdges().add(this);
        }
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, KGraphPackage.KEDGE__TARGET_PORT, oldTargetPort, targetPort));
        }
	}

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated NOT
     */
    public void applyVectorChain(KVectorChain points) {
        if (sourcePoint == null) {
            setSourcePoint(KGraphFactory.eINSTANCE.createKPoint());
        }
        KVector firstPoint = points.getFirst();
        sourcePoint.setX((float) firstPoint.x);
        sourcePoint.setY((float) firstPoint.y);
        
        // reuse as many existing bend points as possible
        ListIterator<KPoint> oldPointIter = getBendPoints().listIterator();
        ListIterator<KVector> newPointIter = points.listIterator(1);
        while (newPointIter.nextIndex() < points.size() - 1) {
            KVector nextPoint = newPointIter.next();
            KPoint kpoint;
            if (oldPointIter.hasNext()) {
                kpoint = oldPointIter.next();
                kpoint.setX((float) nextPoint.x);
                kpoint.setY((float) nextPoint.y);
            } else {
                kpoint = KGraphFactory.eINSTANCE.createKPoint();
                kpoint.setX((float) nextPoint.x);
                kpoint.setY((float) nextPoint.y);
                oldPointIter.add(kpoint);
            }
        }
        while (oldPointIter.hasNext()) {
            oldPointIter.next();
            oldPointIter.remove();
        }
        
        if (targetPoint == null) {
            setTargetPoint(KGraphFactory.eINSTANCE.createKPoint());
        }
        KVector lastPoint = points.getLast();
        targetPoint.setX((float) lastPoint.x);
        targetPoint.setY((float) lastPoint.y);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated NOT
     */
    public KVectorChain createVectorChain() {
        KVectorChain vectorChain = new KVectorChain();
        if (sourcePoint != null) {
            vectorChain.add(sourcePoint.getX(), sourcePoint.getY());
        }
        for (KPoint bendPoint : getBendPoints()) {
            vectorChain.add(bendPoint.getX(), bendPoint.getY());
        }
        if (targetPoint != null) {
            vectorChain.add(targetPoint.getX(), targetPoint.getY());
        }
        return vectorChain;
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
            case KGraphPackage.KEDGE__SOURCE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetSource((KNode)otherEnd, msgs);
            case KGraphPackage.KEDGE__TARGET:
                if (target != null)
                    msgs = ((InternalEObject)target).eInverseRemove(this, KGraphPackage.KNODE__INCOMING_EDGES, KNode.class, msgs);
                return basicSetTarget((KNode)otherEnd, msgs);
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
            case KGraphPackage.KEDGE__BEND_POINTS:
                return ((InternalEList<?>)getBendPoints()).basicRemove(otherEnd, msgs);
            case KGraphPackage.KEDGE__SOURCE_POINT:
                return basicSetSourcePoint(null, msgs);
            case KGraphPackage.KEDGE__TARGET_POINT:
                return basicSetTargetPoint(null, msgs);
            case KGraphPackage.KEDGE__SOURCE:
                return basicSetSource(null, msgs);
            case KGraphPackage.KEDGE__TARGET:
                return basicSetTarget(null, msgs);
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
            case KGraphPackage.KEDGE__SOURCE:
                return eInternalContainer().eInverseRemove(this, KGraphPackage.KNODE__OUTGOING_EDGES, KNode.class, msgs);
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
            case KGraphPackage.KEDGE__BEND_POINTS:
                return getBendPoints();
            case KGraphPackage.KEDGE__SOURCE_POINT:
                return getSourcePoint();
            case KGraphPackage.KEDGE__TARGET_POINT:
                return getTargetPoint();
            case KGraphPackage.KEDGE__SOURCE:
                return getSource();
            case KGraphPackage.KEDGE__TARGET:
                if (resolve) return getTarget();
                return basicGetTarget();
            case KGraphPackage.KEDGE__SOURCE_PORT:
                if (resolve) return getSourcePort();
                return basicGetSourcePort();
            case KGraphPackage.KEDGE__TARGET_PORT:
                if (resolve) return getTargetPort();
                return basicGetTargetPort();
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
            case KGraphPackage.KEDGE__BEND_POINTS:
                getBendPoints().clear();
                getBendPoints().addAll((Collection<? extends KPoint>)newValue);
                return;
            case KGraphPackage.KEDGE__SOURCE_POINT:
                setSourcePoint((KPoint)newValue);
                return;
            case KGraphPackage.KEDGE__TARGET_POINT:
                setTargetPoint((KPoint)newValue);
                return;
            case KGraphPackage.KEDGE__SOURCE:
                setSource((KNode)newValue);
                return;
            case KGraphPackage.KEDGE__TARGET:
                setTarget((KNode)newValue);
                return;
            case KGraphPackage.KEDGE__SOURCE_PORT:
                setSourcePort((KPort)newValue);
                return;
            case KGraphPackage.KEDGE__TARGET_PORT:
                setTargetPort((KPort)newValue);
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
            case KGraphPackage.KEDGE__BEND_POINTS:
                getBendPoints().clear();
                return;
            case KGraphPackage.KEDGE__SOURCE_POINT:
                setSourcePoint((KPoint)null);
                return;
            case KGraphPackage.KEDGE__TARGET_POINT:
                setTargetPoint((KPoint)null);
                return;
            case KGraphPackage.KEDGE__SOURCE:
                setSource((KNode)null);
                return;
            case KGraphPackage.KEDGE__TARGET:
                setTarget((KNode)null);
                return;
            case KGraphPackage.KEDGE__SOURCE_PORT:
                setSourcePort((KPort)null);
                return;
            case KGraphPackage.KEDGE__TARGET_PORT:
                setTargetPort((KPort)null);
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
            case KGraphPackage.KEDGE__BEND_POINTS:
                return bendPoints != null && !bendPoints.isEmpty();
            case KGraphPackage.KEDGE__SOURCE_POINT:
                return sourcePoint != null;
            case KGraphPackage.KEDGE__TARGET_POINT:
                return targetPoint != null;
            case KGraphPackage.KEDGE__SOURCE:
                return getSource() != null;
            case KGraphPackage.KEDGE__TARGET:
                return target != null;
            case KGraphPackage.KEDGE__SOURCE_PORT:
                return sourcePort != null;
            case KGraphPackage.KEDGE__TARGET_PORT:
                return targetPort != null;
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
        if (baseClass == KEdgeLayout.class) {
            switch (derivedFeatureID) {
                case KGraphPackage.KEDGE__BEND_POINTS: return KGraphPackage.KEDGE_LAYOUT__BEND_POINTS;
                case KGraphPackage.KEDGE__SOURCE_POINT: return KGraphPackage.KEDGE_LAYOUT__SOURCE_POINT;
                case KGraphPackage.KEDGE__TARGET_POINT: return KGraphPackage.KEDGE_LAYOUT__TARGET_POINT;
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
        if (baseClass == KEdgeLayout.class) {
            switch (baseFeatureID) {
                case KGraphPackage.KEDGE_LAYOUT__BEND_POINTS: return KGraphPackage.KEDGE__BEND_POINTS;
                case KGraphPackage.KEDGE_LAYOUT__SOURCE_POINT: return KGraphPackage.KEDGE__SOURCE_POINT;
                case KGraphPackage.KEDGE_LAYOUT__TARGET_POINT: return KGraphPackage.KEDGE__TARGET_POINT;
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
    	StringBuilder builder = new StringBuilder("KEdge");
    	
        KNode source = getSource();
        if (source != null && target != null) {
            final String sPort = getSourcePort() == null ? "" : (":" + getSourcePort().toString());
            final String tPort = getTargetPort() == null ? "" : (":" + getTargetPort().toString());
            builder
            	.append(" ")
            	.append(source.toString())
            	.append(sPort)
            	.append("->")
            	.append(target.toString())
            	.append(tPort);
        }

        builder.append("( ").append(sourcePoint);
        if (bendPoints != null) {
            for (KPoint p : bendPoints) {
                builder.append(" -> ").append(p);
            }
        }
        builder.append(" -> ").append(targetPoint).append(" )");
        
        return builder.toString();
    }

} //KEdgeImpl
