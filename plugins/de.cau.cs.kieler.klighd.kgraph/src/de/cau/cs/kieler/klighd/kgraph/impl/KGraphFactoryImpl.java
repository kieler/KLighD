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

import java.util.Map;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;

import org.eclipse.elk.graph.properties.IProperty;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import de.cau.cs.kieler.klighd.kgraph.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KGraphFactoryImpl extends EFactoryImpl implements KGraphFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static KGraphFactory init() {
		try {
			KGraphFactory theKGraphFactory = (KGraphFactory)EPackage.Registry.INSTANCE.getEFactory(KGraphPackage.eNS_URI);
			if (theKGraphFactory != null) {
				return theKGraphFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new KGraphFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KGraphFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case KGraphPackage.KGRAPH_DATA: return createKGraphData();
			case KGraphPackage.KNODE: return createKNode();
			case KGraphPackage.KEDGE: return createKEdge();
			case KGraphPackage.KPORT: return createKPort();
			case KGraphPackage.KLABEL: return createKLabel();
			case KGraphPackage.IPROPERTY_TO_OBJECT_MAP: return (EObject)createIPropertyToObjectMap();
			case KGraphPackage.PERSISTENT_ENTRY: return createPersistentEntry();
			case KGraphPackage.KPOINT: return createKPoint();
			case KGraphPackage.KINSETS: return createKInsets();
			case KGraphPackage.KIDENTIFIER: return createKIdentifier();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case KGraphPackage.IPROPERTY:
				return createIPropertyFromString(eDataType, initialValue);
			case KGraphPackage.KVECTOR:
				return createKVectorFromString(eDataType, initialValue);
			case KGraphPackage.KVECTOR_CHAIN:
				return createKVectorChainFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case KGraphPackage.IPROPERTY:
				return convertIPropertyToString(eDataType, instanceValue);
			case KGraphPackage.KVECTOR:
				return convertKVectorToString(eDataType, instanceValue);
			case KGraphPackage.KVECTOR_CHAIN:
				return convertKVectorChainToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KGraphData createKGraphData() {
		KGraphDataImpl kGraphData = new KGraphDataImpl();
		return kGraphData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KNode createKNode() {
		KNodeImpl kNode = new KNodeImpl();
		return kNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KEdge createKEdge() {
		KEdgeImpl kEdge = new KEdgeImpl();
		return kEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KPort createKPort() {
		KPortImpl kPort = new KPortImpl();
		return kPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KLabel createKLabel() {
		KLabelImpl kLabel = new KLabelImpl();
		return kLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<IProperty<?>, Object> createIPropertyToObjectMap() {
		IPropertyToObjectMapImpl iPropertyToObjectMap = new IPropertyToObjectMapImpl();
		return iPropertyToObjectMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PersistentEntry createPersistentEntry() {
		PersistentEntryImpl persistentEntry = new PersistentEntryImpl();
		return persistentEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KPoint createKPoint() {
		KPointImpl kPoint = new KPointImpl();
		return kPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KInsets createKInsets() {
		KInsetsImpl kInsets = new KInsetsImpl();
		return kInsets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KIdentifier createKIdentifier() {
		KIdentifierImpl kIdentifier = new KIdentifierImpl();
		return kIdentifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IProperty<?> createIPropertyFromString(EDataType eDataType, String initialValue) {
		return (IProperty<?>)super.createFromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIPropertyToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KVector createKVectorFromString(EDataType eDataType, String initialValue) {
		return (KVector)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertKVectorToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KVectorChain createKVectorChainFromString(EDataType eDataType, String initialValue) {
		return (KVectorChain)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertKVectorChainToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KGraphPackage getKGraphPackage() {
		return (KGraphPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static KGraphPackage getPackage() {
		return KGraphPackage.eINSTANCE;
	}

} //KGraphFactoryImpl
