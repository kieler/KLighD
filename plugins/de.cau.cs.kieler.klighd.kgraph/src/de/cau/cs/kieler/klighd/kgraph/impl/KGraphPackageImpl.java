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
import org.eclipse.elk.graph.properties.IPropertyHolder;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import de.cau.cs.kieler.klighd.kgraph.EMapPropertyHolder;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KEdgeLayout;
import de.cau.cs.kieler.klighd.kgraph.KGraphData;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory;
import de.cau.cs.kieler.klighd.kgraph.KGraphPackage;
import de.cau.cs.kieler.klighd.kgraph.KIdentifier;
import de.cau.cs.kieler.klighd.kgraph.KInsets;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KLayoutData;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.KPoint;
import de.cau.cs.kieler.klighd.kgraph.KPort;
import de.cau.cs.kieler.klighd.kgraph.KShapeLayout;
import de.cau.cs.kieler.klighd.kgraph.PersistentEntry;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KGraphPackageImpl extends EPackageImpl implements KGraphPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kGraphElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kLabeledGraphElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kGraphDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kEdgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kPortEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kLabelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eMapPropertyHolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iPropertyToObjectMapEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iPropertyHolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass persistentEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kShapeLayoutEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kEdgeLayoutEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kLayoutDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kInsetsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kIdentifierEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iPropertyEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType kVectorEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType kVectorChainEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see de.cau.cs.kieler.klighd.kgraph.KGraphPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private KGraphPackageImpl() {
		super(eNS_URI, KGraphFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link KGraphPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static KGraphPackage init() {
		if (isInited) return (KGraphPackage)EPackage.Registry.INSTANCE.getEPackage(KGraphPackage.eNS_URI);

		// Obtain or create and register package
		KGraphPackageImpl theKGraphPackage = (KGraphPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof KGraphPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new KGraphPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theKGraphPackage.createPackageContents();

		// Initialize created meta-data
		theKGraphPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theKGraphPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(KGraphPackage.eNS_URI, theKGraphPackage);
		return theKGraphPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKGraphElement() {
		return kGraphElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKGraphElement_Data() {
		return (EReference)kGraphElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKLabeledGraphElement() {
		return kLabeledGraphElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKLabeledGraphElement_Labels() {
		return (EReference)kLabeledGraphElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKGraphData() {
		return kGraphDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKNode() {
		return kNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKNode_Children() {
		return (EReference)kNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKNode_Parent() {
		return (EReference)kNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKNode_Ports() {
		return (EReference)kNodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKNode_OutgoingEdges() {
		return (EReference)kNodeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKNode_IncomingEdges() {
		return (EReference)kNodeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKEdge() {
		return kEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKEdge_Source() {
		return (EReference)kEdgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKEdge_Target() {
		return (EReference)kEdgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKEdge_SourcePort() {
		return (EReference)kEdgeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKEdge_TargetPort() {
		return (EReference)kEdgeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKPort() {
		return kPortEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKPort_Node() {
		return (EReference)kPortEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKPort_Edges() {
		return (EReference)kPortEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKLabel() {
		return kLabelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKLabel_Text() {
		return (EAttribute)kLabelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKLabel_Parent() {
		return (EReference)kLabelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEMapPropertyHolder() {
		return eMapPropertyHolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMapPropertyHolder_Properties() {
		return (EReference)eMapPropertyHolderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEMapPropertyHolder_PersistentEntries() {
		return (EReference)eMapPropertyHolderEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIPropertyToObjectMap() {
		return iPropertyToObjectMapEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIPropertyToObjectMap_Key() {
		return (EAttribute)iPropertyToObjectMapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIPropertyToObjectMap_Value() {
		return (EAttribute)iPropertyToObjectMapEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIPropertyHolder() {
		return iPropertyHolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPersistentEntry() {
		return persistentEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPersistentEntry_Key() {
		return (EAttribute)persistentEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPersistentEntry_Value() {
		return (EAttribute)persistentEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKShapeLayout() {
		return kShapeLayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKShapeLayout_Xpos() {
		return (EAttribute)kShapeLayoutEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKShapeLayout_Ypos() {
		return (EAttribute)kShapeLayoutEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKShapeLayout_Width() {
		return (EAttribute)kShapeLayoutEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKShapeLayout_Height() {
		return (EAttribute)kShapeLayoutEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKShapeLayout_Insets() {
		return (EReference)kShapeLayoutEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKEdgeLayout() {
		return kEdgeLayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKEdgeLayout_BendPoints() {
		return (EReference)kEdgeLayoutEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKEdgeLayout_SourcePoint() {
		return (EReference)kEdgeLayoutEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKEdgeLayout_TargetPoint() {
		return (EReference)kEdgeLayoutEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKLayoutData() {
		return kLayoutDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKPoint() {
		return kPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKPoint_X() {
		return (EAttribute)kPointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKPoint_Y() {
		return (EAttribute)kPointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKInsets() {
		return kInsetsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKInsets_Top() {
		return (EAttribute)kInsetsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKInsets_Bottom() {
		return (EAttribute)kInsetsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKInsets_Left() {
		return (EAttribute)kInsetsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKInsets_Right() {
		return (EAttribute)kInsetsEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKIdentifier() {
		return kIdentifierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKIdentifier_Id() {
		return (EAttribute)kIdentifierEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIProperty() {
		return iPropertyEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getKVector() {
		return kVectorEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getKVectorChain() {
		return kVectorChainEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KGraphFactory getKGraphFactory() {
		return (KGraphFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		kGraphElementEClass = createEClass(KGRAPH_ELEMENT);
		createEReference(kGraphElementEClass, KGRAPH_ELEMENT__DATA);

		kLabeledGraphElementEClass = createEClass(KLABELED_GRAPH_ELEMENT);
		createEReference(kLabeledGraphElementEClass, KLABELED_GRAPH_ELEMENT__LABELS);

		kGraphDataEClass = createEClass(KGRAPH_DATA);

		kNodeEClass = createEClass(KNODE);
		createEReference(kNodeEClass, KNODE__CHILDREN);
		createEReference(kNodeEClass, KNODE__PARENT);
		createEReference(kNodeEClass, KNODE__PORTS);
		createEReference(kNodeEClass, KNODE__OUTGOING_EDGES);
		createEReference(kNodeEClass, KNODE__INCOMING_EDGES);

		kEdgeEClass = createEClass(KEDGE);
		createEReference(kEdgeEClass, KEDGE__SOURCE);
		createEReference(kEdgeEClass, KEDGE__TARGET);
		createEReference(kEdgeEClass, KEDGE__SOURCE_PORT);
		createEReference(kEdgeEClass, KEDGE__TARGET_PORT);

		kPortEClass = createEClass(KPORT);
		createEReference(kPortEClass, KPORT__NODE);
		createEReference(kPortEClass, KPORT__EDGES);

		kLabelEClass = createEClass(KLABEL);
		createEAttribute(kLabelEClass, KLABEL__TEXT);
		createEReference(kLabelEClass, KLABEL__PARENT);

		eMapPropertyHolderEClass = createEClass(EMAP_PROPERTY_HOLDER);
		createEReference(eMapPropertyHolderEClass, EMAP_PROPERTY_HOLDER__PROPERTIES);
		createEReference(eMapPropertyHolderEClass, EMAP_PROPERTY_HOLDER__PERSISTENT_ENTRIES);

		iPropertyToObjectMapEClass = createEClass(IPROPERTY_TO_OBJECT_MAP);
		createEAttribute(iPropertyToObjectMapEClass, IPROPERTY_TO_OBJECT_MAP__KEY);
		createEAttribute(iPropertyToObjectMapEClass, IPROPERTY_TO_OBJECT_MAP__VALUE);

		iPropertyHolderEClass = createEClass(IPROPERTY_HOLDER);

		persistentEntryEClass = createEClass(PERSISTENT_ENTRY);
		createEAttribute(persistentEntryEClass, PERSISTENT_ENTRY__KEY);
		createEAttribute(persistentEntryEClass, PERSISTENT_ENTRY__VALUE);

		kShapeLayoutEClass = createEClass(KSHAPE_LAYOUT);
		createEAttribute(kShapeLayoutEClass, KSHAPE_LAYOUT__XPOS);
		createEAttribute(kShapeLayoutEClass, KSHAPE_LAYOUT__YPOS);
		createEAttribute(kShapeLayoutEClass, KSHAPE_LAYOUT__WIDTH);
		createEAttribute(kShapeLayoutEClass, KSHAPE_LAYOUT__HEIGHT);
		createEReference(kShapeLayoutEClass, KSHAPE_LAYOUT__INSETS);

		kEdgeLayoutEClass = createEClass(KEDGE_LAYOUT);
		createEReference(kEdgeLayoutEClass, KEDGE_LAYOUT__BEND_POINTS);
		createEReference(kEdgeLayoutEClass, KEDGE_LAYOUT__SOURCE_POINT);
		createEReference(kEdgeLayoutEClass, KEDGE_LAYOUT__TARGET_POINT);

		kLayoutDataEClass = createEClass(KLAYOUT_DATA);

		kPointEClass = createEClass(KPOINT);
		createEAttribute(kPointEClass, KPOINT__X);
		createEAttribute(kPointEClass, KPOINT__Y);

		kInsetsEClass = createEClass(KINSETS);
		createEAttribute(kInsetsEClass, KINSETS__TOP);
		createEAttribute(kInsetsEClass, KINSETS__BOTTOM);
		createEAttribute(kInsetsEClass, KINSETS__LEFT);
		createEAttribute(kInsetsEClass, KINSETS__RIGHT);

		kIdentifierEClass = createEClass(KIDENTIFIER);
		createEAttribute(kIdentifierEClass, KIDENTIFIER__ID);

		// Create data types
		iPropertyEDataType = createEDataType(IPROPERTY);
		kVectorEDataType = createEDataType(KVECTOR);
		kVectorChainEDataType = createEDataType(KVECTOR_CHAIN);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters
		addETypeParameter(iPropertyEDataType, "T");

		// Set bounds for type parameters

		// Add supertypes to classes
		kGraphElementEClass.getESuperTypes().add(this.getEMapPropertyHolder());
		kLabeledGraphElementEClass.getESuperTypes().add(this.getKGraphElement());
		kGraphDataEClass.getESuperTypes().add(this.getEMapPropertyHolder());
		kNodeEClass.getESuperTypes().add(this.getKLabeledGraphElement());
		kNodeEClass.getESuperTypes().add(this.getKShapeLayout());
		kEdgeEClass.getESuperTypes().add(this.getKLabeledGraphElement());
		kEdgeEClass.getESuperTypes().add(this.getKEdgeLayout());
		kPortEClass.getESuperTypes().add(this.getKLabeledGraphElement());
		kPortEClass.getESuperTypes().add(this.getKShapeLayout());
		kLabelEClass.getESuperTypes().add(this.getKGraphElement());
		kLabelEClass.getESuperTypes().add(this.getKShapeLayout());
		eMapPropertyHolderEClass.getESuperTypes().add(this.getIPropertyHolder());
		kShapeLayoutEClass.getESuperTypes().add(this.getKLayoutData());
		kEdgeLayoutEClass.getESuperTypes().add(this.getKLayoutData());
		kLayoutDataEClass.getESuperTypes().add(this.getKGraphData());
		kIdentifierEClass.getESuperTypes().add(this.getKGraphData());

		// Initialize classes and features; add operations and parameters
		initEClass(kGraphElementEClass, KGraphElement.class, "KGraphElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getKGraphElement_Data(), this.getKGraphData(), null, "data", null, 0, -1, KGraphElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(kGraphElementEClass, this.getKGraphData(), "getData", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEClass(), "type", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(kGraphElementEClass, null, "getData", 0, 1, IS_UNIQUE, IS_ORDERED);
		ETypeParameter t1 = addETypeParameter(op, "T");
		EGenericType g1 = createEGenericType(this.getKGraphData());
		t1.getEBounds().add(g1);
		g1 = createEGenericType(ecorePackage.getEJavaClass());
		EGenericType g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "type", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		initEClass(kLabeledGraphElementEClass, KLabeledGraphElement.class, "KLabeledGraphElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getKLabeledGraphElement_Labels(), this.getKLabel(), this.getKLabel_Parent(), "labels", null, 0, -1, KLabeledGraphElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(kGraphDataEClass, KGraphData.class, "KGraphData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(kNodeEClass, KNode.class, "KNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getKNode_Children(), this.getKNode(), this.getKNode_Parent(), "children", null, 0, -1, KNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKNode_Parent(), this.getKNode(), this.getKNode_Children(), "parent", null, 0, 1, KNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKNode_Ports(), this.getKPort(), this.getKPort_Node(), "ports", null, 0, -1, KNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKNode_OutgoingEdges(), this.getKEdge(), this.getKEdge_Source(), "outgoingEdges", null, 0, -1, KNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKNode_IncomingEdges(), this.getKEdge(), this.getKEdge_Target(), "incomingEdges", null, 0, -1, KNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(kEdgeEClass, KEdge.class, "KEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getKEdge_Source(), this.getKNode(), this.getKNode_OutgoingEdges(), "source", null, 1, 1, KEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKEdge_Target(), this.getKNode(), this.getKNode_IncomingEdges(), "target", null, 1, 1, KEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKEdge_SourcePort(), this.getKPort(), null, "sourcePort", null, 0, 1, KEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKEdge_TargetPort(), this.getKPort(), null, "targetPort", null, 0, 1, KEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(kPortEClass, KPort.class, "KPort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getKPort_Node(), this.getKNode(), this.getKNode_Ports(), "node", null, 1, 1, KPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKPort_Edges(), this.getKEdge(), null, "edges", null, 0, -1, KPort.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(kLabelEClass, KLabel.class, "KLabel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getKLabel_Text(), ecorePackage.getEString(), "text", null, 0, 1, KLabel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKLabel_Parent(), this.getKLabeledGraphElement(), this.getKLabeledGraphElement_Labels(), "parent", null, 1, 1, KLabel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eMapPropertyHolderEClass, EMapPropertyHolder.class, "EMapPropertyHolder", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEMapPropertyHolder_Properties(), this.getIPropertyToObjectMap(), null, "properties", null, 0, -1, EMapPropertyHolder.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEMapPropertyHolder_PersistentEntries(), this.getPersistentEntry(), null, "persistentEntries", null, 0, -1, EMapPropertyHolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(eMapPropertyHolderEClass, null, "makePersistent", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iPropertyToObjectMapEClass, Map.Entry.class, "IPropertyToObjectMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(this.getIProperty());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEAttribute(getIPropertyToObjectMap_Key(), g1, "key", null, 1, 1, Map.Entry.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIPropertyToObjectMap_Value(), ecorePackage.getEJavaObject(), "value", null, 0, 1, Map.Entry.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iPropertyHolderEClass, IPropertyHolder.class, "IPropertyHolder", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(iPropertyHolderEClass, this.getIPropertyHolder(), "setProperty", 0, 1, IS_UNIQUE, IS_ORDERED);
		t1 = addETypeParameter(op, "T");
		g1 = createEGenericType(this.getIProperty());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		EGenericType g3 = createEGenericType(t1);
		g2.setELowerBound(g3);
		addEParameter(op, g1, "property", 1, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(t1);
		addEParameter(op, g1, "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(iPropertyHolderEClass, null, "getProperty", 0, 1, IS_UNIQUE, IS_ORDERED);
		t1 = addETypeParameter(op, "T");
		g1 = createEGenericType(this.getIProperty());
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "property", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = addEOperation(iPropertyHolderEClass, this.getIPropertyHolder(), "copyProperties", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIPropertyHolder(), "holder", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(iPropertyHolderEClass, null, "getAllProperties", 1, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(this.getIProperty());
		g1.getETypeArguments().add(g2);
		g3 = createEGenericType();
		g2.getETypeArguments().add(g3);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		initEClass(persistentEntryEClass, PersistentEntry.class, "PersistentEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPersistentEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, PersistentEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPersistentEntry_Value(), ecorePackage.getEString(), "value", null, 0, 1, PersistentEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(kShapeLayoutEClass, KShapeLayout.class, "KShapeLayout", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getKShapeLayout_Xpos(), ecorePackage.getEFloat(), "xpos", "0.0f", 0, 1, KShapeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKShapeLayout_Ypos(), ecorePackage.getEFloat(), "ypos", "0.0f", 0, 1, KShapeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKShapeLayout_Width(), ecorePackage.getEFloat(), "width", "0.0f", 0, 1, KShapeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKShapeLayout_Height(), ecorePackage.getEFloat(), "height", "0.0f", 0, 1, KShapeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKShapeLayout_Insets(), this.getKInsets(), null, "insets", null, 0, 1, KShapeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(kShapeLayoutEClass, null, "setPos", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEFloat(), "x", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEFloat(), "y", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(kShapeLayoutEClass, null, "applyVector", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getKVector(), "pos", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(kShapeLayoutEClass, this.getKVector(), "createVector", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(kShapeLayoutEClass, null, "setSize", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEFloat(), "width", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEFloat(), "height", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(kEdgeLayoutEClass, KEdgeLayout.class, "KEdgeLayout", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getKEdgeLayout_BendPoints(), this.getKPoint(), null, "bendPoints", null, 0, -1, KEdgeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKEdgeLayout_SourcePoint(), this.getKPoint(), null, "sourcePoint", null, 1, 1, KEdgeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKEdgeLayout_TargetPoint(), this.getKPoint(), null, "targetPoint", null, 1, 1, KEdgeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(kEdgeLayoutEClass, null, "applyVectorChain", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getKVectorChain(), "points", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(kEdgeLayoutEClass, this.getKVectorChain(), "createVectorChain", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(kLayoutDataEClass, KLayoutData.class, "KLayoutData", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(kLayoutDataEClass, ecorePackage.getEBoolean(), "isModified", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(kLayoutDataEClass, null, "resetModificationFlag", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(kPointEClass, KPoint.class, "KPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getKPoint_X(), ecorePackage.getEFloat(), "x", "0.0f", 0, 1, KPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKPoint_Y(), ecorePackage.getEFloat(), "y", "0.0f", 0, 1, KPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(kPointEClass, null, "setPos", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEFloat(), "x", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEFloat(), "y", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(kPointEClass, null, "applyVector", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getKVector(), "pos", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(kPointEClass, this.getKVector(), "createVector", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(kInsetsEClass, KInsets.class, "KInsets", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getKInsets_Top(), ecorePackage.getEFloat(), "top", null, 0, 1, KInsets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKInsets_Bottom(), ecorePackage.getEFloat(), "bottom", null, 0, 1, KInsets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKInsets_Left(), ecorePackage.getEFloat(), "left", null, 0, 1, KInsets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKInsets_Right(), ecorePackage.getEFloat(), "right", null, 0, 1, KInsets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(kIdentifierEClass, KIdentifier.class, "KIdentifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getKIdentifier_Id(), ecorePackage.getEString(), "id", null, 1, 1, KIdentifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(iPropertyEDataType, IProperty.class, "IProperty", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(kVectorEDataType, KVector.class, "KVector", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(kVectorChainEDataType, KVectorChain.class, "KVectorChain", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //KGraphPackageImpl
